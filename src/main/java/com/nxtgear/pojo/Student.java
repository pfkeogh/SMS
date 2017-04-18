/**
* @author: Patrick F
* @Date:Apr 17, 2017
* Student plain old java object, Hibernate Mappings, Validation
**/

package com.nxtgear.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author pfkeo
 *
 */
/**
 * @author pfkeo
 *
 */
@Entity
@Table(name = "nxtgear_student")
public class Student {
	@Id
	@GeneratedValue
	private long id;
	@NotNull(message="Please fill out first name")
	@Size(min = 1, max = 16, message="First Name must be between 1 and 16 characters")
	private String firstName;
	@NotNull(message="Please fill out last name")
	@Size(min = 1, max = 16, message="Last Name must be between 1 and 16 characters")
	private String lastName;
	@NotNull(message="Please fill out school")
	@Size(min = 3, max = 16, message="School name must be between 3 and 16 characters")
	private String School;
	@NotNull(message="Please select a gender")
	@Size(min = 4, max = 6)
	private String gender;
	@NotNull(message="Please enter your age")
	@Min(value=1, message="Age cannot be less than one")
	private int age;

	/**
	 * student toString
	 */
	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", School=" + School
				+ ", gender=" + gender + ", age=" + age + "]";
	}

	/**
	 * Default constructor
	 */
	public Student() {
	};

	/**
	 * Parameterized constructor
	 */
	public Student(String firstName, String lastName, String school, String gender, int age) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		School = school;
		this.gender = gender;
		this.age = age;
	}
	
	/**
	 * Student hashCode
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((School == null) ? 0 : School.hashCode());
		result = prime * result + age;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}
	/**
	 * Student equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (School == null) {
			if (other.School != null)
				return false;
		} else if (!School.equals(other.School))
			return false;
		if (age != other.age)
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSchool() {
		return School;
	}

	public void setSchool(String school) {
		School = school;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
