package ch.hevs.businessobject;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String partyName;
	private String partyInitials;

	@OneToMany(mappedBy = "party")
	private Set<Politician> members;
	@Embedded
	private Address partyAddress;


	//Getters and setters
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyInitials() {
		return partyInitials;
	}
	public void setPartyInitials(String partyInitials) {
		this.partyInitials = partyInitials;
	}

	public Set<Politician> getMembers() {
		return members;
	}
	public void setMembers(Set<Politician> members) {
		this.members = members;
	}

	public Address getPartyAddress() {
		return partyAddress;
	}
	public void setPartyAddress(Address partyAddress) {
		this.partyAddress = partyAddress;
	}


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	// constructors
	public Party() {
	}


	public Party(String partyName, String partyInitials) {

		this.partyName = partyName;
		this.partyInitials = partyInitials;
		this.members = new HashSet<Politician>();
	}

	// TODO helper method useful ?

	public void addMember(Politician politician) {
		members.add(politician);
		politician.setParty(this);
	}
}
