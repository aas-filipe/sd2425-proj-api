package fctreddit.api.java.resources;
import fctreddit.api.java.Image;
import fctreddit.api.java.Result;

public class ImageJava implements Image {
    @Override
    public Result<String> createImage(String userId, byte[] imageContents, String password) {
        return null;
    }

    @Override
    public Result<byte[]> getImage(String userId, String imageId) {
        return null;
    }

    @Override
    public Result<Void> deleteImage(String userId, String imageId, String password) {
        return null;
    }
}
