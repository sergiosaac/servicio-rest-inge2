/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lab5.service;

import com.lab5.Hijo;
import com.lab5.Usuario;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Usuario
 */
public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public List<T> findAll() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        return getEntityManager().createQuery(cq).getResultList();
    }

    public List<T> findRange(int[] range) {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public int count() {
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        javax.persistence.Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    public String validarUsuario(String email) throws IOException, ParseException {

        JSONObject obj = new JSONObject();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(email);

        JSONObject jsonObject = (JSONObject) json;
        String emailConsulta = (String) jsonObject.get("email");
        
        boolean valido = getEntityManager().createNativeQuery("SELECT * FROM Usuario u WHERE u.correo = '" + emailConsulta + "'")
                .getResultList().isEmpty();
        
        String nombre = getEntityManager().createNativeQuery("SELECT nombre FROM Usuario u WHERE u.correo = '" + emailConsulta + "'")
                .getResultList().toString();
        
        String id = getEntityManager().createNativeQuery("SELECT id_usuario FROM Usuario u WHERE u.correo = '" + emailConsulta + "'")
                .getResultList().toString();
        
        String nombreParaObjeto = nombre.substring(1,nombre.length()-1);
        
        String idParaObjeto = id.substring(1,2);
        
        if (valido) {

            obj.put("valido", false);
            obj.put("email", emailConsulta);
            obj.put("nombre", nombreParaObjeto);
            obj.put("idUsuario", idParaObjeto);
            
        } else {
            obj.put("valido", true);
            obj.put("email", emailConsulta);
            obj.put("nombre", nombreParaObjeto);
            obj.put("idUsuario", idParaObjeto);
            
        }

        StringWriter out = new StringWriter();
        obj.writeJSONString(out);

        String jsonText = out.toString();

        return jsonText;
    }
    
    public String obtenerHijos(Integer idPadre) {
          
        return getEntityManager().createNativeQuery("SELECT to_json(c.*) FROM hijo c WHERE c.id_padre = '"+idPadre+"'")
                .getResultList().toString();
    }
    
    public String obtenerVacunas(Integer idHijo) {
          
        return getEntityManager().createNativeQuery("SELECT to_json(v.*) FROM vacuna v WHERE v.id_hijo = '"+idHijo+"'")
                .getResultList().toString();
    }
    
    public String obtenerHijosPost(String email) throws IOException, ParseException {

        JSONObject obj = new JSONObject();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(email);

        JSONObject jsonObject = (JSONObject) json;
        String idPadre = (String) jsonObject.get("idPadre");
        
        
        return getEntityManager().createNativeQuery("SELECT to_json(c.*) FROM hijo c WHERE c.id_padre = '"+idPadre+"'")
                .getResultList().toString();

    }
    
    public String obtenerVacunasPost(String email) throws IOException, ParseException {

        JSONObject obj = new JSONObject();

        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(email);

        JSONObject jsonObject = (JSONObject) json;
        String idHijo = (String) jsonObject.get("idHijo");
        String order = (String) jsonObject.get("order");
        
        return getEntityManager().createNativeQuery("SELECT to_json(c.*) FROM vacuna c WHERE c.id_hijo = '"+idHijo+"' ORDER BY "+order+" asc")
                .getResultList().toString();

    }
    
    
}
