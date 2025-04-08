package fctreddit.api.clients;

import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Result.*;
import fctreddit.api.server.serviceDiscovery.Discovery;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import org.glassfish.jersey.client.ClientConfig;

import java.io.IOException;
import java.net.URI;
import java.util.List;


public class UsersClientClass extends AbstractClient implements UsersClient {
    private static final int MIN_REPLIES = 1;
    Discovery discovery;
    Client client;

    private UsersClientClass(Discovery discovery) throws IOException {
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
    public Result<User> getUser(String userId, String password) {
        return null;
    }


}