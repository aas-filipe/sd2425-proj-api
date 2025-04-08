package fctreddit.api.clients;

import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;
import java.net.URI;

public class ContentClientClass implements ContentClient {
    private static final int MIN_REPLIES = 1;
    Discovery discovery;

    public ContentClientClass(Discovery discovery) throws IOException {
        this.discovery = discovery;
    }
    private String getService(String serviceName) {
        URI[] uris = new URI[0];
        try {
            uris = discovery.knownUrisOf(serviceName, MIN_REPLIES);
        } catch (InterruptedException e ) {
            System.err.println("Interrupted while getting known URIs for " + serviceName);
        }

        for (URI uri : uris) {
            if (uri.toString().contains("/rest")) {
                return uri.toString();
            }
        }
        return null;
    }
}
