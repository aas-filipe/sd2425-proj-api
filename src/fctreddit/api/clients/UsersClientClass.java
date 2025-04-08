package fctreddit.api.clients;

import fctreddit.api.User;
import fctreddit.api.server.serviceDiscovery.Discovery;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import org.glassfish.jersey.client.ClientConfig;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class UsersClientClass implements UsersClient {
    private static final int  MIN_REPLIES = 1;
    Discovery discovery;

    Client client;

    public UsersClientClass(Discovery discovery) throws IOException {
        this.discovery = discovery;
        client = ClientBuilder.newClient(new ClientConfig());
    }

    private URI getService(String serviceName) {
        URI[] uris = new URI[0];
        try {
            uris = discovery.knownUrisOf(serviceName, MIN_REPLIES);
        } catch (InterruptedException e ) {
            System.err.println("Interrupted while getting known URIs for " + serviceName);
            return null;
        }

        for (URI uri : uris) {
            if (uri.toString().contains("/rest")) {
                return uri;
            }
        }
        return null;
    }

   @Override
    public String createUser(User user) {
        return null;
    }

    @Override
    public User getUser(String userId) {
        URI usersServiceURI = getService("Users");
        WebTarget target = client.target(usersServiceURI);
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public List<User> searchUsers(String pattern) {
        return List.of();
    }
}
