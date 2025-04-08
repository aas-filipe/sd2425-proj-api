package fctreddit.api.clients;

import fctreddit.api.User;

import java.util.List;

public interface UsersClient {

    String createUser(User user);

    User getUser(String userId, String password);

    User updateUser(User user, String password);

    void deleteUser(String userId, String password);

    List<User> searchUsers(String pattern);
}
