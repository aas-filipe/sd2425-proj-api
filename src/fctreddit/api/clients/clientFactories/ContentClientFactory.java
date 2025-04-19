package fctreddit.api.clients.clientFactories;

import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;

public class ContentClientFactory {

    private static ContentClientFactory instance;

    Discovery discovery;

    private ContentClientFactory() throws IOException {
        discovery = new Discovery(Discovery.DISCOVERY_ADDR);
    }

    public static ContentClientFactory getInstance() throws IOException {
        if (instance == null) {
            instance = new ContentClientFactory();
        }
        return instance;
    }
}
