package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Formation;

@Stateless
public class FormationDAO {
	
	@PersistenceContext( unitName = "formationsopra")
	EntityManager em;

	public List<Formation> getAll() {
		
		return em.createQuery("SELECT f from Formation F", Formation.class).getResultList();
		
	}

	public Formation getById(int id) {
		
		return em.find(Formation.class, id);
		
	}

	public Formation createOrUpdate(Formation formation) {
		
		if(formation.getFormationId()==null){
			em.persist(formation);
			return formation;
		}
		
		return em.merge(formation);
		
	}

	public void delete(int id) {
		
		em.remove(getById(id));
		
	}

}
