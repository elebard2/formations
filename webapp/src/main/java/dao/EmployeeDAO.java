package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Employee;

@Stateless
public class EmployeeDAO {

	@PersistenceContext(unitName = "formationsopra")
	EntityManager em;

	public List<Employee> getAll() {

		return em.createQuery("SELECT e from Employee e", Employee.class).getResultList();

	}

	public Employee getById(int id) {

		return em.find(Employee.class, id);

	}

	public Employee createOrUpdate(Employee employee) {

		if (employee.getEmployeeId() == null) {
			em.persist(employee);
			return employee;
		}

		return em.merge(employee);

	}

	public void delete(int id) {

		em.remove(getById(id));

	}

}