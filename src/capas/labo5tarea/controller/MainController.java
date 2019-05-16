package capas.labo5tarea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import capas.labo5tarea.dao.StudentDAO;
import capas.labo5tarea.domain.Student;

@Controller
public class MainController {

	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain(){
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		 students = studentDao.findAll();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		mav.addObject("students",students);
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(path = "/", method = RequestMethod.POST)
	public ModelAndView searchStudent(@RequestParam(name = "studentId") Integer code) {
		ModelAndView mav = new ModelAndView();
		Student student = null;
		try {
			student = studentDao.findOne(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (student != null) {
			System.out.println(student.toString());
			mav.addObject("student", student);
		} else {
			mav.addObject("errorMessage", "No se encontró un estudiante con ese ID. Intente de nuevo.");
		}
		mav.setViewName("main");
		return mav;
	}
	
}
