package fctreddit.api.rest;
import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Users;
import fctreddit.api.java.resources.UsersResource;
import jakarta.ws.rs.WebApplicationException;

import java.util.List;
import java.util.logging.Logger;

public class UserRestService implements RestUsers {

    final Users impl;

    public UserRestService() {
        this.impl = new UsersResource();
    }

    Logger Log = Logger.getLogger(String.valueOf(UserRestService.class));

    @Override
    public String createUser(User user) {
        Log.info("createUser : " + user.toString());

        Result<String> res = impl.createUser(user);
        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public User getUser(String userId, String password) {
        Log.info("getUser : " + userId);

        Result<User> res = impl.getUser(userId, password);
        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public User updateUser(String userId, String password, User user) {

        Result<User> res = impl.updateUser(userId, password,);
        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public User deleteUser(String userId, String password) {

        Result<User> res = impl.deleteUser(userId, password);
        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public List<User> searchUsers(String pattern) {

        Result<List<User>> res = impl.searchUsers(pattern);
        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }
}
