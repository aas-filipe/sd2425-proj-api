package fctreddit.api.clients.UserClients;

import fctreddit.api.User;
import fctreddit.api.java.Result;

public abstract class UsersClient {

    /**
     * Gets the user if it exists and if the password matches.
     * @param userId target user of this operation.
     * @param password password of the user.
     * @return <OK, User> if the user exists and the password matches, NOT_FOUND if the user doesn't exist
     * FORBIDDEN if the password doesn't match.
     */
    abstract public Result<User> getUser(String userId, String password);

    /**
     * Updates the user if it exists.
     * @param user target user
     * @return <NO_CONTENT> if the user was updated successfully, NOT_FOUND if it doesn't exist
     * , FORBIDDEN if the password doesn't match.
     */
    abstract public Result<Void> updateUser(User user, String userId, String password);

}
