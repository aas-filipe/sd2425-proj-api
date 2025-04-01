package fctreddit.api.server;
import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.rest.RestUsers;
import fctreddit.api.server.persistence.Hibernate;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.logging.Logger;

public class UserRestService implements RestUsers {

    private static Logger logger = Logger.getLogger(UserRestService.class.getName());

    private Hibernate hibernate;

    public UserRestService() { this.hibernate = Hibernate.getInstance(); }

    @Override
    public String createUser(User user) throws WebApplicationException {
        User u = hibernate.get(User.class, user.getUserId());
        if (u != null) {
            throw new WebApplicationException(Response.Status.CONFLICT);
        }
        //TODO: BAD REQUEST RESPONSE
        try {
            hibernate.persist(user);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return user.getUserId();
    }

    @Override
    public User getUser(String userId, String password) {
        User u = authenticateUser(userId, password);

        logger.fine("User with id " + userId + " found");
        return u;
    }

    @Override
    public User updateUser(String userId, String password, User user) {
        authenticateUser(userId, password);

        try {
            hibernate.persist(user);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }

        return user;

    }

    @Override
    public User deleteUser(String userId, String password) {
        User u = authenticateUser(userId, password);

        try {
            hibernate.delete(u);
        } catch (Exception e) {
            throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
        }
        return u;
    }

    private User authenticateUser(String userId, String password) {
        User u = hibernate.get(User.class, userId);
        if (u == null) {
            logger.warning("User with id " + userId + " not found");
            throw new WebApplicationException(Response.Status.NOT_FOUND);
        }
        if (!u.getPassword().equals(password)) {
            logger.warning("Password does not match");
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
        return u;
    }

    @Override
    public List<User> searchUsers(String pattern) {
        // TODO : JPQL QUERY
        return List.of();
    }
}
