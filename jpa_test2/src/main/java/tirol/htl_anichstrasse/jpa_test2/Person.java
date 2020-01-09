package tirol.htl_anichstrasse.jpa_test2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	private int age;
	private String firstName;
	private String lastName;
	@Setter
	@Getter
	private Hobby2 hoppy;
	
	@Transient
	private long aliveSince;
	
	public Person(long id, int age, String firstName, String lastName) {
		this.id=id;
		this.age=age;
		this.firstName=firstName;
		this.lastName=lastName;
		this.aliveSince = System.currentTimeMillis();
		
	}
	public Person() {}
	public long getId() {
		return id;
	}
	

	@Override
	public String toString() {
		return "Person [id=" + id + ", age=" + age + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", aliveSince=" + aliveSince + "]";
	}
	public void setId(long id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
	

}
