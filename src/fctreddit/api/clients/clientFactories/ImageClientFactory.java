package fctreddit.api.clients.clientFactories;

import fctreddit.api.server.serviceDiscovery.Discovery;

import java.io.IOException;

public class ImageClientFactory {

    private static ImageClientFactory instance;

    Discovery discovery;

    private ImageClientFactory() throws IOException {
        discovery = new Discovery(Discovery.DISCOVERY_ADDR);
    }

    public static ImageClientFactory getInstance() throws IOException {
        if (instance == null) {
            instance = new ImageClientFactory();
        }
        return instance;
    }


}
