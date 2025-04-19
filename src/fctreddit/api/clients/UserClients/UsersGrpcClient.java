package fctreddit.api.clients.UserClients;

import fctreddit.api.User;
import fctreddit.api.java.Result;

import java.net.URI;

public class UsersGrpcClient extends UsersClient {

    URI selectedServiceURI;

    public UsersGrpcClient(URI selectedServiceURI) {
        this.selectedServiceURI = selectedServiceURI;
    }

    @Override
    public Result<User> getUser(String userId, String password) {
        return null;
    }

    @Override
    public Result<Void> updateUser(User user, String userId, String password) {
        return null;
    }
}
