package fctreddit.api.clients.ContetnClients;

import fctreddit.api.User;
import fctreddit.api.java.Result;

public class ContentGrpcClient extends ContentClient {

    @Override
    public Result<Void> removeAllUserVotes(String userId, String password) {
        return null;
    }

    @Override
    public Result<Void> deletePostOwner(String authorId, String password) {
        return null;
    }
}
