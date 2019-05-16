package capas.labo5tarea.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import capas.labo5tarea.domain.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@PersistenceContext(unitName="capas")
	private EntityManager entityManager;
	
	@Override
	public List<Student> findAll() throws DataAccessException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("select * from public.student");
		Query query = entityManager.createNativeQuery(sb.toString(), Student.class);
		List<Student> resulset = query.getResultList();
		
		return resulset;
	}

	@Override
	public Student findOne(Integer code) throws DataAccessException {
		
		Student student = entityManager.find(Student.class, code);
	
		return student;
	}
}
