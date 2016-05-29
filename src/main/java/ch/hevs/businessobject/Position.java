package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String positionName;


	@Embedded
	private Address positionAddress;

	@ManyToMany
	private Set<Person> appointees;

	//Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Address getPositionAddress() {
		return positionAddress;
	}

	public void setPositionAddress(Address positionAddress) {
		this.positionAddress = positionAddress;
	}

	public Set<Person> getAppointees() {
		return appointees;
	}

	public void setAppointees(Set<Person> appointees) {
		this.appointees = appointees;
	}


	//Constructors

	public Position() {
	}

	public Position(String positionName, Address positionAddress) {

		this.positionName = positionName;
		this.positionAddress = positionAddress;
		this.appointees = new HashSet<Person>();
	}
}
