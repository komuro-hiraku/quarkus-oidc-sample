package jp.classmethod.quarkus.sample;

import io.quarkus.oidc.IdToken;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/users")
public class UsersResource {

    private static final Logger LOGGER = Logger.getLogger(UsersResource.class);

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

        private final String name;

        User(JsonWebToken token) {
            this.userName = token.getName();
            this.name = String.format("%s, %s", token.getClaim("family_name"), token.getClaim("given_name"));
            LOGGER.infov("JsonWebToken: {0}", token);
        }

        public String getUserName() {
            return userName;
        }

        public String getName() { return name; }
    }
}
