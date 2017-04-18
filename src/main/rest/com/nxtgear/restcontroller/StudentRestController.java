/**
* @author: Patrick F
* @Date:Apr 17, 2017
* 
* Student REST controller 
**/

package com.nxtgear.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nxtgear.pojo.Student;
import com.nxtgear.service.StudentService;

@RestController
@RequestMapping("/rest")
public class StudentRestController {

	@Autowired
	StudentService studentService;
	/**
	 * Returns json list of students
	 */
	@RequestMapping("/students")
	public List<Student> students(){
		return studentService.list();
	}
	
}
