package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;

@Entity
public class CivilServant extends Person {


	@ManyToMany(mappedBy = "appointees", fetch=FetchType.EAGER)
	private Set<Position> positions;


	//Getters and setters

	public Set<Position> getPositions() {
		return positions;
	}
	public void setPositions(Set<Position> positions) {
		this.positions = positions;
	}

	// constructors
	public CivilServant() {
		super();
	}
	public CivilServant(String lastname, String firstname) {
		super(lastname, firstname);
		this.positions=new HashSet<Position>();
	}

	@Override
	public void addPosition(Position pos) {
		this.positions.add(pos);
		pos.addCivilServant(this);
	}
}
