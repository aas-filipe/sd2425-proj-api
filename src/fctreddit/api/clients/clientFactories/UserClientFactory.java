package fctreddit.api.clients.clientFactories;

import fctreddit.api.User;
import fctreddit.api.clients.UserClients.UsersClient;
import fctreddit.api.clients.UserClients.UsersGrpcClient;
import fctreddit.api.clients.UserClients.UsersRestClient;
import fctreddit.api.java.Result;
import fctreddit.api.server.UsersServer;
import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;
import java.net.URI;

public class UserClientFactory {

    private static UserClientFactory instance;

    Discovery discovery ;

    private UserClientFactory() throws IOException {
        discovery = new Discovery(Discovery.DISCOVERY_ADDR);
    }

    public static UserClientFactory getInstance() throws IOException {
        if (instance == null) {
            instance = new UserClientFactory();
        }
        return instance;
    }

    public Result<User> getUser(String userId, String password) throws InterruptedException {
        URI[] serviceURIs = discovery.knownUrisOf(UsersServer.SERVICE, 1);
        URI selectedServiceURI = serviceURIs[0];

        UsersClient client = null;
        if (selectedServiceURI.toString().contains("/rest")) {
            client = new UsersRestClient(selectedServiceURI);
        } else {
            client = new UsersGrpcClient(selectedServiceURI);
        }
        return client.getUser( userId, password);
    }

}
