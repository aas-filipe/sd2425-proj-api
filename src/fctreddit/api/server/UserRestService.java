package fctreddit.api.server;
import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.rest.RestUsers;
import fctreddit.api.server.persistence.Hibernate;

import java.util.List;
import java.util.logging.Logger;

public class UserRestService implements RestUsers {

    private static Logger logger = Logger.getLogger(UserRestService.class.getName());

    private Hibernate hibernate;

    public UserRestService() { this.hibernate = Hibernate.getInstance(); }

    @Override
    public String createUser(User user) {
        User u = hibernate.get(User.class, user.getUserId());
        if (u != null) {
            return Result;
        }


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
