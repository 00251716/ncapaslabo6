package capas.labo5tarea.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import capas.labo5tarea.domain.Student;

public interface StudentDAO {
	public List<Student> findAll() throws DataAccessException;
	
	public Student findOne(Integer code) throws DataAccessException;
}
