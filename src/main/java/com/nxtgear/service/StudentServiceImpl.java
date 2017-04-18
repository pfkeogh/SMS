/**
* @author: Patrick F
* @Date:Apr 17, 2017
**/

package com.nxtgear.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nxtgear.dao.StudentDao;
import com.nxtgear.pojo.Student;

/**
 * Implementation of student service
 *
 */
@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentDao studentDao;
	/**
	 * Save or updates student by calling dao layer
	 */
	public void saveOrUpdate(Student student) {
		 studentDao.saveOrUpdate(student);
	}
	/**
	 * Deletes student by calling dao layer
	 */
	public void delete(long id) {
		studentDao.delete(id);
	}
	/**
	 * Lists students by calling dao layer
	 */
	public List<Student> list() {
		return studentDao.list();
	}
	/**
	 * Loads student by id by calling dao layer
	 */
	public Student load(long id) {
		return studentDao.load(id);
	}
	/**
	 * Loads student by name by calling dao layer
	 */
	public Student load(String firstName) {
		return studentDao.load(firstName);
	}
	
	
}
