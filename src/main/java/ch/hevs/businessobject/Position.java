package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "`POSITION`")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String positionName;


	@Embedded
	private Address positionAddress;

	@ManyToMany
	private Set<Politician> politicians;
	
	@ManyToMany (cascade = CascadeType.ALL)
	private Set<CivilServant> appointees;

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

	public Set<Politician> getPoliticians() {
		return politicians;
	}

	public void setPoliticians(Set<Politician> politicians) {
		this.politicians = politicians;
	}

	public Set<CivilServant> getAppointees() {
		return appointees;
	}

	public void setAppointees(Set<CivilServant> appointees) {
		this.appointees = appointees;
	}


	//Constructors

	

	public Position() {
	}

	public Position(String positionName, Address positionAddress) {

		this.positionName = positionName;
		this.positionAddress = positionAddress;
		this.appointees = new HashSet<CivilServant>();
		this.politicians = new HashSet<Politician>();
	}
	
	/**
	 * NEVER USE THIS METHOD IN CODE
	 * @param politician
	 */
	public void addPolitician(Politician politician) {
		this.politicians.add(politician);
	}
	
	/**
	 * NEVER USE THIS METHOD IN CODE
	 * @param civilServant
	 */
	public void addCivilServant(CivilServant civilServant) {
		this.appointees.add(civilServant);
	}
}
