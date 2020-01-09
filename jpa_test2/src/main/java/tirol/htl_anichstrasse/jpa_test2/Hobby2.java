package tirol.htl_anichstrasse.jpa_test2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Hobby2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter
	@Getter
	private long id;
	@Setter
	@Getter
	private String name;
	@Setter
	@Getter
	private int hoursPerWeek;
	@Setter
	@Getter
	private double expenses;
	
	public Hobby2(String n, int hours, double expenses) {
		
		this.name = n;
		this.hoursPerWeek = hours;
		this.expenses=expenses;
	}
	public Hobby2() {}
}
