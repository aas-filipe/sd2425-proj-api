package fctreddit.api.clients.UserClients;

import fctreddit.api.User;
import fctreddit.api.clients.util.RestClientHelper;
import fctreddit.api.java.Result;
import fctreddit.api.rest.RestUsers;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

import java.net.URI;

import static org.glassfish.jersey.client.ClientProperties.CONNECT_TIMEOUT;
import static org.glassfish.jersey.client.ClientProperties.READ_TIMEOUT;

public class UsersRestClient extends UsersClient {


    final URI serverURI;
    final Client client;
    final ClientConfig config;

    final WebTarget target;

    public UsersRestClient( URI serverURI ) {
        this.serverURI = serverURI;

        this.config = new ClientConfig();

        config.property( READ_TIMEOUT, READ_TIMEOUT);
        config.property( CONNECT_TIMEOUT, CONNECT_TIMEOUT);

        this.client = ClientBuilder.newClient(config);

        target = client.target( serverURI ).path( RestUsers.PATH );
    }
    @Override
    public Result<User> getUser(String userId, String password) {
        Invocation.Builder builder = target.path( userId )
                .queryParam(RestUsers.PASSWORD, password ).request()
                .accept(MediaType.APPLICATION_JSON);
        Response r = RestClientHelper.executeOperation(builder::get);
        int status = r.getStatus();
        if ( status == Response.Status.OK.getStatusCode() && r.hasEntity() ) {
            return Result.ok(r.readEntity(User.class));
        } else {
            return Result.error(RestClientHelper.getErrorCodeFrom(status));
        }
    }

    @Override
    public Result<Void> updateUser(User user, String userId, String password) {
        Invocation.Builder builder = target.path( userId )
                .queryParam(RestUsers.PASSWORD, password ).request()
                .accept(MediaType.APPLICATION_JSON);
        Response r = RestClientHelper.executeOperation(() -> builder.put(Entity.entity(user, MediaType.APPLICATION_JSON)));

        int status = r.getStatus();
        if ( status == Response.Status.NO_CONTENT.getStatusCode()) {
            return Result.ok();
        } else {
            return Result.error(RestClientHelper.getErrorCodeFrom(status));
        }
    }
}
