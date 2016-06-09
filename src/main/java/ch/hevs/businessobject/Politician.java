package ch.hevs.businessobject;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Politician extends Person {


	@ManyToOne
	private Party party;
	
	@ManyToMany(mappedBy = "appointees")
	private Set<Position> positions;

	//Getters and setters

	public Party getParty() {
		return party;
	}
	public void setParty(Party party) {
		this.party = party;
	}
	
	public Set<Position> getPositions() {
		return positions;
	}
	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}
	


	// constructors
	public Politician() {
		super();
	}
	public Politician(String lastname, String firstname) {
		super(lastname, firstname);
		
		this.positions=new HashSet<Position>();

	}
	
	public void addPosition(Position pos) {
		this.positions.add(pos);
	}
}
