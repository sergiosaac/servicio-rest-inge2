/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab5.service;

import com.lab5.Vacuna;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Usuario
 */
@Stateless
@Path("vacunas")
public class VacunaFacadeREST extends AbstractFacade<Vacuna> {

    //@PersistenceContext(unitName = "WebApplication3PU")
    private EntityManager em;

    public VacunaFacadeREST() {
        super(Vacuna.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Vacuna entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Vacuna entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Vacuna find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacuna> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Vacuna> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        em = Persistence.createEntityManagerFactory("WebApplication3PU").createEntityManager();
        return em;
    }
    
    @GET
    @Path("vacuna/{idHijo}")
    @Produces({MediaType.APPLICATION_JSON})
    public String obtenerVacunas(@PathParam("idHijo") Integer idHijo) {
        return super.obtenerVacunas(idHijo);
    }
    
     @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("obtenerVacunasPost")
    @Produces({MediaType.APPLICATION_JSON})
    public String findMail(String hijo) throws IOException, ParseException {
        return super.obtenerVacunasPost(hijo);
    }
    
    
}
