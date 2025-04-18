package fctreddit.api.java.resources;

import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Users;
import fctreddit.api.server.persistence.Hibernate;
import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.logging.Logger;

public class UsersJava implements Users {
    private static final String SERVICE_NAME = "Users";
    private static final String SERVICE_PROTOCOL = "/rest";

    private static Logger Log = Logger.getLogger(UsersJava.class.getName());
    private Hibernate hibernate;

    public UsersJava() throws IOException {
        Hibernate hibernate = Hibernate.getInstance();
        Discovery discovery = new Discovery(Discovery.DISCOVERY_ADDR, SERVICE_NAME, InetAddress.getLocalHost().getHostAddress() + SERVICE_PROTOCOL);
        discovery.start();
    }

    @Override
    public Result<String> createUser(User user) {
        if(user == null) {
            return Result.error(Result.ErrorCode.BAD_REQUEST);
        }
        if(hibernate.get(User.class, user.getUserId()) != null) {
            return Result.error(Result.ErrorCode.CONFLICT);
        }
        try {
            hibernate.persist(user);
        } catch (Exception e) {
            return Result.error(Result.ErrorCode.INTERNAL_ERROR);
        }
        return Result.ok(user.getUserId());
    }

    @Override
    public Result<User> getUser(String userId, String password) {
        if(userId == null || password == null) {
            return Result.error(Result.ErrorCode.BAD_REQUEST);
        }
        User user = hibernate.get(User.class, userId);
        if(user == null) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(!password.equals(user.getPassword())) {
            return Result.error(Result.ErrorCode.FORBIDDEN);
        }
        return Result.ok(user);
    }

    @Override
    public Result<User> updateUser(String userId, String password, User user) {
        if(userId == null || password == null) {
            return Result.error(Result.ErrorCode.BAD_REQUEST);
        }
        User oldUser = hibernate.get(User.class, userId);
        if(oldUser == null) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(!password.equals(oldUser.getPassword())) {
            return Result.error(Result.ErrorCode.FORBIDDEN);
        }
        if(user.getEmail() != null && user.getFullName() != null && !user.getFullName().isEmpty() ) {}
        hibernate.update(user);
        return Result.ok(user);
    }

    @Override
    public Result<User> deleteUser(String userId, String password) {
        if(userId == null || password == null) {
            return Result.error(Result.ErrorCode.BAD_REQUEST);
        }
        User user = hibernate.get(User.class, userId);
        if(user == null) {
            return Result.error(Result.ErrorCode.NOT_FOUND);
        }
        if(!password.equals(user.getPassword())) {
            return Result.error(Result.ErrorCode.FORBIDDEN);
        }
        hibernate.delete(user);
        return Result.ok(user);
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        //TODO : JPQL querry, provavelmente usar chatgpt seria a way, ou ver os ppts
        return null;
    }
}
