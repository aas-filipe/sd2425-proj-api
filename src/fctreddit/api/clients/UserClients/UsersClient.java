package fctreddit.api.clients.UserClients;

import fctreddit.api.User;
import fctreddit.api.java.Result;

public abstract class UsersClient {

    abstract public Result<User> getUser(String userId, String password);
}
