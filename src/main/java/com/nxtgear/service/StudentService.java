/**
* @author: Patrick F
* @Date:Apr 17, 2017
**/

package com.nxtgear.service;

import java.util.List;

import com.nxtgear.pojo.Student;

/**
 * Service for student database operations
 * 
 */
public interface StudentService {
	
	/**
	 * For Saving or updating student 
	 */
	public void saveOrUpdate(Student student);
	/**
	 * For deleting student by ID
	 * @param id
	 */
	public void delete(long id);
	/**
	 * Returns list of all students
	 */
	public List<Student> list();
	/**
	 * @param id
	 * Loads student by ID
	 */
	public Student load(long id);
	/**
	 * @param firstName
	 * Loads student by first name
	 */
	public Student load(String firstName);
}
