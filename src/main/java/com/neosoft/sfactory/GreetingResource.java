package com.neosoft.sfactory;

import java.net.http.HttpResponse;
import java.util.List;

import org.jboss.resteasy.reactive.RestResponse;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Transactional
@Path("/utilisateurs")
public class GreetingResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public RestResponse<List<UtilisateurBody>> getAll() {
        return RestResponse.ok(Utilisateur.<Utilisateur>listAll().stream().map(utilisateur -> new UtilisateurBody(utilisateur.nom, utilisateur.prenom)).toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    public RestResponse<UtilisateurBody> addUser(UtilisateurBody utilisateurBody) {
        if(utilisateurBody==null || utilisateurBody.nom()==null || utilisateurBody.prenom()==null){
            return RestResponse.status(Status.BAD_REQUEST);
        }
        Utilisateur entity1 = new Utilisateur();
          entity1.nom = utilisateurBody.nom();
          entity1.prenom = utilisateurBody.prenom();
          entity1.persist();

        return RestResponse.ok(new UtilisateurBody(entity1.nom, entity1.prenom));
    }

    private record UtilisateurBody(String nom, String prenom) {}
}
