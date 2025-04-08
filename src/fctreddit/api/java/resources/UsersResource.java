package fctreddit.api.java.resources;

import fctreddit.api.User;
import fctreddit.api.clients.ContentClientClass;
import fctreddit.api.clients.ImageClientClass;
import fctreddit.api.java.Result;
import fctreddit.api.java.Users;
import fctreddit.api.server.persistence.Hibernate;
import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.logging.Logger;

public class UsersResource implements Users {
    private static final String SERVICE_NAME = "Users";
    private static final String SERVICE_PROTOCOL = "/rest";

    private static Logger Log = Logger.getLogger(UsersResource.class.getName());
    private ImageClientClass imagesClient;
    private ContentClientClass contentClient;
    private Hibernate hibernate;

    public UsersResource() throws IOException {
        Hibernate hibernate = Hibernate.getInstance();
        Discovery discovery = new Discovery(Discovery.DISCOVERY_ADDR, SERVICE_NAME, InetAddress.getLocalHost().getHostAddress() + SERVICE_PROTOCOL);
        discovery.start();
        this.imagesClient = new ImageClientClass(discovery);
        this.contentClient = new ContentClientClass(discovery);
    }

    @Override
    public Result<String> createUser(User user) {
        return null;
    }

    @Override
    public Result<User> getUser(String userId, String password) {
        return null;
    }

    @Override
    public Result<User> updateUser(String userId, String password, User user) {
        return null;
    }

    @Override
    public Result<User> deleteUser(String userId, String password) {
        return null;
    }

    @Override
    public Result<List<User>> searchUsers(String pattern) {
        return null;
    }
}
