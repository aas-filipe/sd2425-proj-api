package fctreddit.api.clients.ContetnClients;

import fctreddit.api.User;
import fctreddit.api.java.Result;


public abstract class ContentClient {
    /**
     * When a user is deleted this method calls the content server and removes all interactions a user has ever made
     * @param user target user of this operation
     * @return NO_CONTENT if all the interactions were removed, INTERNAL_SERVER_ERROR otherwise.
     */
    abstract public Result<Void> removeAllUserVotes(User user);


}
