package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.DuelApiService;
import io.swagger.api.factories.DuelApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.Duel;
import io.swagger.model.DemandePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;

@Path("/duel")
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
@io.swagger.annotations.Api(description = "the duel API")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public class DuelApi  {
   private final DuelApiService delegate = DuelApiServiceFactory.getDuelApi();

    @POST
    @Path("/demande")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On envoye une demande de duel.", response = Duel.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 204, message = "Successful response. Pas de duel en cours.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = Duel.class) })
    public Response duelDemandePost(@ApiParam(value = "On envoie l'id du meme qui veut entrer en duel" ,required=true) DemandePattern demande
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.duelDemandePost(demande,securityContext);
    }
    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère une liste de duel. ", response = Duel.class, responseContainer = "List", authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Duel.class, responseContainer = "List"),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = Duel.class, responseContainer = "List") })
    public Response duelGet(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.duelGet(securityContext);
    }
    @GET
    @Path("/{id}")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On récupère un meme avec son ID.", response = Duel.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "Successful response", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez êtes identifier pour accéder à cette ressource.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Error, Pas de duel.", response = Duel.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "La base de donnée ne répond pas.", response = Duel.class) })
    public Response duelIdGet(@ApiParam(value = "L'id d'un Meme",required=true) @PathParam("id") Integer id
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.duelIdGet(id,securityContext);
    }
    @POST
    @Path("/{id}/vote")
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/json", "application/xml" })
    @io.swagger.annotations.ApiOperation(value = "", notes = "On vote pour un meme en duel.", response = void.class, authorizations = {
        @io.swagger.annotations.Authorization(value = "API key")
    }, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 201, message = "Successful response. Le vote a été pris en compte", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 401, message = "Vous devez être identifié pour accéder à cette ressource.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 403, message = "Vous n'avez pas l'autorisation d'accéder à cette ressource.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 404, message = "Erreur.", response = void.class),
        
        @io.swagger.annotations.ApiResponse(code = 503, message = "Problème de connexion avec la base de donnée.", response = void.class) })
    public Response duelIdVotePost(@ApiParam(value = "ID du duel",required=true) @PathParam("id") Integer id
,@ApiParam(value = "ID du meme pour lequel on veut voter",required=true) @QueryParam("id_meme") Integer idMeme
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.duelIdVotePost(id,idMeme,securityContext);
    }
}
