package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.Duel;
import io.swagger.model.DemandePattern;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2016-12-11T15:09:34.277Z")
public abstract class DuelApiService {
    public abstract Response duelDemandePost(DemandePattern demande,SecurityContext securityContext) throws NotFoundException;
    public abstract Response duelGet(SecurityContext securityContext) throws NotFoundException;
    public abstract Response duelIdGet(Integer id,SecurityContext securityContext) throws NotFoundException;
    public abstract Response duelIdVotePost(Integer id,Integer idMeme,SecurityContext securityContext) throws NotFoundException;
}
