package fctreddit.api.java.resources;

import fctreddit.api.Post;
import fctreddit.api.User;
import fctreddit.api.java.Content;
import fctreddit.api.java.Image;
import fctreddit.api.java.Result;
import fctreddit.api.java.util.Content.VoteType;
import fctreddit.api.rest.RestUsers;
import fctreddit.api.server.persistence.Hibernate;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;

public class ContentJava implements Content {

    Hibernate hibernate;
    RestUsers userService;
    Image imageService;


    public ContentJava() {
        hibernate=Hibernate.getInstance();
    }
    @Override
    public Result<String> createPost(Post post, String userPassword) {
        try {
            User res = userService.getUser(post.getAuthorId(), userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        hibernate.persist(post);
        return Result.ok(post.getPostId());
    }

    @Override
    public Result<List<String>> getPosts(long timestamp, String sortOrder) {
        return null;
    }

    @Override
    public Result<Post> getPost(String postId) {
        try {
            return Result.ok(hibernate.get(Post.class, postId));
        }catch (Exception e) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
    }

    @Override
    public Result<List<String>> getPostAnswers(String postId, long maxTimeout) {
        return null;
    }

    @Override
    public Result<Post> updatePost(String postId, String userPassword, Post post) {
        try {
            User res = userService.getUser(post.getAuthorId(), userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        hibernate.update(post);
        return Result.ok(post);
    }

    @Override
    public Result<Void> deletePost(String postId, String userPassword) {
        return null;
    }

    @Override
    public Result<Void> upVotePost(String postId, String userId, String userPassword) {
        Post post;
        User user;
        try {
            user = userService.getUser(userId, userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        try {
           post = hibernate.get(Post.class, postId);
        }catch (Exception e) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(post.hasVote(userId)){
            return Result.error(Result.ErrorCode.CONFLICT);
        }
        post.upVote(userId);
        hibernate.update(post);
        return Result.ok();
    }

    @Override
    public Result<Void> removeUpVotePost(String postId, String userId, String userPassword) {
        Post post;
        User user;
        try {
            user = userService.getUser(userId, userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        try {
            post = hibernate.get(Post.class, postId);
        }catch (Exception e) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(!post.hasVote(userId)){
            return Result.error(Result.ErrorCode.CONFLICT);
        }
        post.removeUpVote(userId);
        hibernate.update(post);
        return Result.ok();
    }

    @Override
    public Result<Void> downVotePost(String postId, String userId, String userPassword) {
        Post post;
        User user;
        try {
            user = userService.getUser(userId, userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        try {
            post = hibernate.get(Post.class, postId);
        }catch (Exception e) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(post.hasVote(userId)){
            return Result.error(Result.ErrorCode.CONFLICT);
        }
        post.downVote(userId);
        hibernate.update(post);
        return Result.ok();
    }

    @Override
    public Result<Void> removeDownVotePost(String postId, String userId, String userPassword) {
        Post post;
        User user;
        try {
            user = userService.getUser(userId, userPassword);
        }catch (WebApplicationException e) {
            return Result.error((Result.ErrorCode) e.getResponse().getEntity());
        }
        try {
            post = hibernate.get(Post.class, postId);
        }catch (Exception e) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(!post.hasVote(userId)){
            return Result.error(Result.ErrorCode.CONFLICT);
        }
        post.removeDownVote(userId);
        hibernate.update(post);
        return Result.ok();
    }

    @Override
    public Result<Integer> getupVotes(String postId) {
        return null;
    }

    @Override
    public Result<Integer> getDownVotes(String postId) {
        return null;
    }

    @Override
    public Result<VoteType> getUserVote(String postId, String userId) {
        return null;
    }

    @Override
    public Result<Void> removePostFromUser(String userId) {
        return null;
    }
}
