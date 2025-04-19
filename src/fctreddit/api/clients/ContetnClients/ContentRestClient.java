package fctreddit.api.clients.ContetnClients;

import fctreddit.api.User;
import fctreddit.api.clients.util.RestClientHelper;
import fctreddit.api.java.Result;
import fctreddit.api.rest.RestContent;
import fctreddit.api.rest.RestImage;
import fctreddit.api.rest.RestUsers;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

import java.net.URI;

import static org.glassfish.jersey.client.ClientProperties.CONNECT_TIMEOUT;
import static org.glassfish.jersey.client.ClientProperties.READ_TIMEOUT;

public class ContentRestClient extends ContentClient {

    final URI serverURI;
    final Client client;
    final ClientConfig config;

    final WebTarget target;

    public ContentRestClient ( URI serverURI ) {
        this.serverURI = serverURI;

        this.config = new ClientConfig();

        config.property( READ_TIMEOUT, READ_TIMEOUT);
        config.property( CONNECT_TIMEOUT, CONNECT_TIMEOUT);

        this.client = ClientBuilder.newClient(config);

        target = client.target( serverURI ).path( RestContent.PATH );
    }

    @Override
    public Result<Void> removeAllUserVotes(String userId, String password) {
        Invocation.Builder builder = target.path( userId )
                .queryParam(RestContent.PASSWORD, password).request();

        Response r = RestClientHelper.executeOperation(builder::delete);
        int status = r.getStatus();
        if (status == Response.Status.OK.getStatusCode()) {
            return Result.ok();
        } else {
            return Result.error(RestClientHelper.getErrorCodeFrom(status));
        }
    }

    @Override
    public Result<Void> deletePostOwner(String authorId, String password) {
        Invocation.Builder builder = target.path(authorId)
                .queryParam(RestContent.PASSWORD, password).request();

        Response r = RestClientHelper.executeOperation(builder::delete);

        int status = r.getStatus();
        if (status == Response.Status.OK.getStatusCode()) {
            return Result.ok();
        } else {
            return Result.error(RestClientHelper.getErrorCodeFrom(status));
        }
    }

}
