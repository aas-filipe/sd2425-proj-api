package fctreddit.api.clients;

import fctreddit.api.java.Result.ErrorCode;
import fctreddit.api.server.serviceDiscovery.Discovery;
import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.function.Supplier;
import java.util.logging.Logger;

public class AbstractClient {
    private static final int MIN_REPLIES = 1;
    private static final int MAX_RETRIES = 1000;
    private static final int RETRY_SLEEP = 500;

    private static Logger Log = Logger.getLogger(AbstractClient.class.getName());

    protected Response executeOperation(Supplier<Response> func) {
        for (int i = 0; i < MAX_RETRIES; i++) {
            try {
                return func.get();
            } catch (ProcessingException x) {
                Log.info(x.getMessage());
            }
            try {
                Thread.sleep(RETRY_SLEEP);
            } catch (InterruptedException x) {
                x.printStackTrace();
            }
        }
        return null;
    }



    protected ErrorCode getErrorCodeFrom(int status) {
        return switch (status) {
            case 200, 209 -> ErrorCode.OK;
            case 409 -> ErrorCode.CONFLICT;
            case 403 -> ErrorCode.FORBIDDEN;
            case 404 -> ErrorCode.NOT_FOUND;
            case 400 -> ErrorCode.BAD_REQUEST;
            case 501 -> ErrorCode.NOT_IMPLEMENTED;
            default -> ErrorCode.INTERNAL_ERROR;
        };
    }

    protected String getService(String serviceName, Discovery discovery) {
        URI[] uris = new URI[0];
        try {
            uris = discovery.knownUrisOf(serviceName, MIN_REPLIES);
        } catch (InterruptedException e) {
            System.err.println("Interrupted while getting known URIs for " + serviceName);
            return null;
        }

        for (URI uri : uris) {
            if (uri.toString().contains("/rest")) {
                return uri.toString();
            }
        }
        return null;
    }
}
