package fctreddit.api.rest;

import fctreddit.api.java.resources.ImageResource;
import fctreddit.api.java.Result;
import jakarta.ws.rs.WebApplicationException;

import java.util.logging.Logger;

public class ImageRestService implements RestImage {
    private Logger Log = Logger.getLogger(String.valueOf(ImageRestService.class));
    private ImageResource impl;

    public ImageRestService() {
        impl = new ImageResource();
    }

    @Override
    public String createImage(String userId, byte[] imageContents, String password) {
        Log.info("createImage by user: " + userId);

        Result<String> res = impl.createImage(userId, imageContents, password);

        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public byte[] getImage(String userId, String imageId) {
        Log.info("getImage of user: " + userId);

        Result<byte[]> res = impl.getImage(userId, imageId);

        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
        return res.value();
    }

    @Override
    public void deleteImage(String userId, String imageId, String password) {
        Log.info("deleteImage by user: " + userId);

        Result<Void> res = impl.deleteImage(userId, imageId, password);

        if(!res.isOK()){
            throw new WebApplicationException(errorCodeToStatus(res.error()));
        }
    }
}
