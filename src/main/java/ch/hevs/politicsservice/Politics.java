package ch.hevs.politicsservice;

import java.util.List;

import ch.hevs.businessobject.CivilServant;
import ch.hevs.businessobject.Party;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Politician;
import ch.hevs.businessobject.Position;

public interface Politics {
	
	public List<Politician> getPoliticians();
	public List<Party> getParties();
	public List<Position> getPositions();
	public List<CivilServant> getCivilServants();
	public Party getPartyFromPoliticianLastname(String politicianLastname);
	public Party getPartyFromInitials(String initials);
	public List<Position> getPositionFromPoliticianLastname(String politicianLastname);
	public List<Position> getPositionFromCivilServantLastname(String civilServantLastname);
	public Position getPositionFromName(String positionName);
	public void changeParty(Politician politician, Party newParty);
	public void addPosition(Person person, Position position);
	
	
}