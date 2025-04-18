package fctreddit.api.clients;

import fctreddit.api.User;
import fctreddit.api.java.Result;
import fctreddit.api.java.Result.*;
import fctreddit.api.rest.UserResource;
import fctreddit.api.server.UsersServer;
import fctreddit.api.server.serviceDiscovery.Discovery;
import io.grpc.StatusException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation.Builder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
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



    @Override
    public Result<User> getUser(String userId, String password) {

        String serverURL = getService(UsersServer.SERVICE, discovery);

        if (serverURL == null )
            return Result.error(ErrorCode.NOT_FOUND);

        WebTarget target = client.target(serverURL).path(UserResource.PATH);
        Builder req = target
                .path(userId).queryParam("password", password)
                .request()
                .accept(MediaType.APPLICATION_JSON);
        Response r = executeOperation(req::get);

        int status = r.getStatus();
        if (Status.OK.getStatusCode() != status || !r.hasEntity()) {
            return Result.error(getErrorCodeFrom(status));
        }
        return Result.ok(r.readEntity(User.class));
    }


}