/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Student DAO Implementation
**/

package com.nxtgear.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nxtgear.dao.StudentDaoImpl;
import com.nxtgear.pojo.Student;

@Repository
public class StudentDaoImpl implements StudentDao {
	@Autowired
	private SessionFactory sessionFactory;

	public StudentDaoImpl() {
		super();
	}

	public StudentDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * For saving for updating student
	 * 
	 */
	@Transactional
	public void saveOrUpdate(Student s) {
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}

	/**
	 * For deleting student
	 * 
	 */
	@Transactional
	public void delete(long id) {
		sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Student.class, id));
	}

	/**
	 * For loading student with id
	 * 
	 */
	@Transactional
	public Student load(long id) {
		String hql = "from Student where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Student> StudentList = (List<Student>) query.getResultList();

		if (StudentList != null && !StudentList.isEmpty()) {
			return StudentList.get(0);
		}
		return null;
	}

	/**
	 * For listing all students
	 * 
	 */
	@Transactional
	public List<Student> list() {
		@SuppressWarnings({ "deprecation", "unchecked" })
		List<Student> StudentList = (List<Student>) sessionFactory.getCurrentSession().createQuery("From Student")
				.list();
		if (StudentList != null && !StudentList.isEmpty()) {
			return StudentList;
		}
		return null;
	}

	/**
	 * For loading student by first name
	 * 
	 */
	@Transactional
	public Student load(String firstName) {
		String hql = "from Student where firstName='" + firstName + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Student> StudentList = (List<Student>) query.getResultList();

		if (StudentList != null && !StudentList.isEmpty()) {
			return StudentList.get(0);
		}
		return null;
	}
}
