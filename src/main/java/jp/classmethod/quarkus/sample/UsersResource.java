package jp.classmethod.quarkus.sample;

import io.quarkus.oidc.IdToken;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.jwt.JsonWebToken;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/users")
public class UsersResource {

    @Inject
    @IdToken
    JsonWebToken idToken;


    @GET
    @Path("/me")
    @Produces(MediaType.APPLICATION_JSON)
    public User me() {
        return new User(idToken);
    }

    @RegisterForReflection
    public static class User {
        private final String userName;

        User(JsonWebToken token) {
            this.userName = token.getName();
        }

        public String getUserName() {
            return userName;
        }
    }
}
