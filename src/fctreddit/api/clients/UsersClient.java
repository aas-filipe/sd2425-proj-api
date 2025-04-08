package fctreddit.api.clients;

import fctreddit.api.User;

import java.util.List;

public interface UsersClient {

    String createUser(User user);

    User getUser(String userId);

    User updateUser(User user);

    void deleteUser(String userId);

    List<User> searchUsers(String pattern);
}
