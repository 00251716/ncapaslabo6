package capas.labo5tarea.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import capas.labo5tarea.dao.StudentDAO;
import capas.labo5tarea.domain.Student;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class MainController {

	//Objeto Logger
	static Logger log = Logger.getLogger(MainController.class.getName());
	
	@Autowired
	private StudentDAO studentDao;
	
	@RequestMapping("/")
	public ModelAndView initMain(){
		
		log.info("Entrando a la función init-main "+ log.getName());
		
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
		 students = studentDao.findAll();
		 log.info("Terminando de buscar en la base de datos");
		}
		catch(Exception e){
			log.log(Level.SEVERE, "Exception occured", e);
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
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public ModelAndView insert() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("student", new Student());
		mav.setViewName("form");
		return mav;
	}
	
	
	@RequestMapping(value="/formData")
	public ModelAndView save(@ModelAttribute Student s) {
		ModelAndView mav = new ModelAndView();
		List<Student> students = null;
		try {
			log.info("Agregando nuevo usuario");
			studentDao.save(s, 1);
		} catch(Exception e) {
			log.info("Error: "+ e.toString());
		}
		students = studentDao.findAll();
		log.info(students.get(0).getlName());
		mav.addObject("students", students);
		mav.setViewName("main");
		return mav;
	}
	
	
}
