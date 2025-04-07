package fctreddit.api.java.resources;

import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Users;
import fctreddit.api.server.persistence.Hibernate;

import java.util.List;
import java.util.logging.Logger;

public class UsersResource implements Users {

    private static Logger Log = Logger.getLogger(UsersResource.class.getName());

    private Hibernate hibernate;

    public UsersResource() {
        Hibernate hibernate = Hibernate.getInstance();
    }

    @Override
    public Result<String> createUser(User user) {
        if (user.getUserId() == null) {}
    }

    @Override
    public Result<User> getUser(String userId, String password) {
        return null;
    }

    @Override
    public Result<User> updateUser(String userId, String password, User user) {
        return null;
    }

    @Override
    public Result<User> deleteUser(String userId, String password) {
        return null;
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        return null;
    }
}
