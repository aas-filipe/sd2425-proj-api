package fctreddit.api.clients;

import fctreddit.api.java.Result.ErrorCode;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.client.Invocation.Builder;

import java.util.function.Supplier;

public interface Client {
    Response executeOperation(Supplier<Response> func);
    ErrorCode getErrorCodeFrom(int status);
}
