package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.FormationRequest;

public class FormationRequestDAO {
	
	@PersistenceContext(unitName = "formationsopra")
	EntityManager em;

	public List<FormationRequest> getAll() {

		return em.createQuery("SELECT fr from FormationRequest fr", FormationRequest.class).getResultList();

	}

	public FormationRequest getById(int id) {

		return em.find(FormationRequest.class, id);

	}

	public FormationRequest createOrUpdate(FormationRequest formationrequest) {

		if (formationrequest.getFormationRequestId() == null) {
			em.persist(formationrequest);
			return formationrequest;
		}

		return em.merge(formationrequest);

	}

	public void delete(int id) {

		em.remove(getById(id));

	}

}
