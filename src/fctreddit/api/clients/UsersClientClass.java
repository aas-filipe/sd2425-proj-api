package fctreddit.api.clients;

import fctreddit.api.User;
import fctreddit.api.rest.RestUsers;
import fctreddit.api.server.serviceDiscovery.Discovery;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.glassfish.jersey.client.ClientConfig;

import java.io.IOException;
import java.net.URI;
import java.util.List;

public class UsersClientClass implements UsersClient {
    private static final int MIN_REPLIES = 1;
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
        } catch (InterruptedException e) {
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
    public User getUser(String userId, String password) {
        URI usersServiceURI = getService("Users");
        User user = null;
        WebTarget target = client.target(usersServiceURI);

        Response r = target.path(userId)
                .queryParam(RestUsers.PASSWORD, password).request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        if(r.getStatus() != Status.OK.getStatusCode() && r.hasEntity())  {
            System.out.println("Successfully retrieved user " + userId );
            user = r.readEntity(User.class);
            System.out.println("User :"  + user);
            return user;
        } else {
            System.out.println("Error, HTTP error status: " + r.getStatus());
            throw new WebApplicationException(r.getStatus());
        }
    }

    @Override
    public User updateUser(User user, String password) {
        return null;
    }

    @Override
    public void deleteUser(String userId, String password) {

    }

    @Override
    public List<User> searchUsers(String pattern) {
        return List.of();
    }
}
