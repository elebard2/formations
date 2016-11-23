package service;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.FormationRequestDAO;
import entities.FormationRequest;

@Path("/formationsrequests")
public class FormationRequestWS {
	
	@EJB
	FormationRequestDAO dao;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<FormationRequest> getAll(){
		return dao.getAll();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public FormationRequest getOne( @PathParam ("id") int id){
		return dao.getById(id);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FormationRequest create(FormationRequest formationrequest){
		
		return dao.createOrUpdate(formationrequest);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public FormationRequest update(FormationRequest formationrequest){
		
		return dao.createOrUpdate(formationrequest);
	}
	
	@DELETE
	@Path("{id}")
	public void delete (@PathParam("id") int id){
		dao.delete(id);
	}

}
