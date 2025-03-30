package fctreddit.api.server;
import fctreddit.api.User;
import fctreddit.api.rest.RestUsers;

import java.util.List;

public class UserRestService implements RestUsers {

    @Override
    public String createUser(User user) {
        return "";
    }

    @Override
    public User getUser(String userId, String password) {
        return null;
    }

    @Override
    public User updateUser(String userId, String password, User user) {
        return null;
    }

    @Override
    public User deleteUser(String userId, String password) {
        return null;
    }

    @Override
    public List<User> searchUsers(String pattern) {
        return List.of();
    }
}
