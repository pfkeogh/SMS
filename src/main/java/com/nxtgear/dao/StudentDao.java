
/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Interface of Student DAO layer
**/

package com.nxtgear.dao;

import java.util.List;

import com.nxtgear.pojo.Student;

public interface StudentDao {
	
	/**
	 * For student saving and updating operations
	 */
	public void saveOrUpdate(Student student);
	/**
	 * For student delete operations
	 */
	public void delete(long id);
	/**
	 * For fetching list of student from DB
	 */
	public List<Student> list();
	/**
	 * For loading student by ID
	 */
	public Student load(long id);
	/**
	 * For loading student by first name
	 */
	public Student load(String firstName);
}
