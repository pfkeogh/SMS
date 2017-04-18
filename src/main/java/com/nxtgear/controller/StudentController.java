package com.nxtgear.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nxtgear.pojo.Student;
import com.nxtgear.service.StudentService;

/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Main controller for Student page
**/
@Controller
public class StudentController {
	/**
	 * Logger
	 */
	private static final Logger log = Logger.getLogger(StudentController.class);
	
	/**
	 * Student service bean
	 */
	@Autowired
	StudentService studentService;
	
	/**
	 * Home page mapping, loads list of students and a student into model
	 */
	@RequestMapping("/")
	public String homePage(Model model){
		 if (log.isDebugEnabled()) {
	            log.debug("StudentController :: Entering homePage method...");
	        }
		model.addAttribute("students", studentService.list());
		model.addAttribute("student", new Student());
		return "students";
	}
	
	/**
	 * Creates new student
	 */
	@RequestMapping(value="/",  method=RequestMethod.POST)
	public String newStudent(@Valid Student student, Errors errors, Model model){
		if (log.isDebugEnabled()) {
            log.debug("StudentController :: Entering newStudent method...");
        }
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Errors in form");
			model.addAttribute("students", studentService.list());
			return "students";
		}
		System.out.println(student);
		studentService.saveOrUpdate(student);
		model.addAttribute("success", "Student "+student.getFirstName()+" created successfully");
		return "redirect:/";
	}
	
	/**
	 * Transfers student information to form for editing
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editStudent(@PathVariable String id, ModelAndView mv) {
		if (log.isDebugEnabled()) {
            log.debug("StudentController :: Entering editStudent method...");
        }
		Student student = studentService.load(Integer.parseInt(id));
    	mv.addObject("student", student);
    	mv.addObject("students", studentService.list());
    	mv.setViewName("students");
    	return mv;
    }
	
	/**
	 * Submits edited student data
	 */
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public String editSubmitStudent(@Valid Student student, Errors errors, Model model, @PathVariable String id) {
		if (log.isDebugEnabled()) {
            log.debug("StudentController :: Entering editSubmitStudent method...");
        }
		if (errors.hasErrors()) {
			model.addAttribute("feedback", "Unable to update student");
			model.addAttribute("students", studentService.list());
			return "students";
		}
		studentService.saveOrUpdate(student);
		model.addAttribute("success", "Student "+student.getFirstName()+" updated successfully");
    	model.addAttribute("student", student);
    	model.addAttribute("students", studentService.list());
    	return "redirect:/";      
    }
     
	/**
	 * Deletes selected student
	 */
    @RequestMapping(value = "/delete/{id}")
    public String deleteStudent(@PathVariable String id, Model model) {
    	if (log.isDebugEnabled()) {
            log.debug("StudentController :: Entering deleteStudent method...");
        }
    	model.addAttribute("feedback", "Student removed");
    	studentService.delete(Integer.parseInt(id));
    	return "redirect:/";
    }
}
