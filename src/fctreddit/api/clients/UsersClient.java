package fctreddit.api.clients;

import fctreddit.api.User;
import fctreddit.api.java.Result;

import java.util.List;

public interface UsersClient {


    Result<User> getUser(String userId, String password);

}
