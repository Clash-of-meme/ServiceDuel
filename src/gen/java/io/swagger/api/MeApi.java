package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.MeApiService;
import io.swagger.api.factories.MeApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Duel;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/me")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
@io.swagger.annotations.Api(description = "the me API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public class MeApi  {
   private final MeApiService delegate = MeApiServiceFactory.getMeApi();

    @GET
    @Path("/{token}/duel")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère la liste des duels de l'utilisateur", response = Duel.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error, Pas de duel.", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "La base de donnée ne répond pas.", response = Duel.class, responseContainer = "List") })
    public Response meTokenDuelGet(@ApiParam(value = "Le token d'un utilisateur",required=true) @PathParam("token") String token
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.meTokenDuelGet(token,securityContext);
    }
}
