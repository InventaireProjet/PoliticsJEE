package ch.hevs.politicsservice;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ch.hevs.businessobject.CivilServant;
import ch.hevs.businessobject.Party;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Politician;
import ch.hevs.businessobject.Position;

@Stateless
public class PoliticsBean implements Politics {
	@PersistenceContext(name = "PoliticsPU")
	private EntityManager em;

	@Override
	public List<Politician> getPoliticians() {
		return em.createQuery("FROM Politician").getResultList();
	}

	@Override
	public List<Party> getParties() {
		return em.createQuery("FROM Party").getResultList();
	}

	@Override
	public List<CivilServant> getCivilServants() {
		return em.createQuery("FROM CivilServant").getResultList();
	}

	@Override
	public Party getPartyFromPoliticianLastname(String politicianLastname) {
		Query query = em.createQuery("SELECT party FROM Party party, IN(party.members) m where m.lastname = :lastname");
		query.setParameter("lastname", politicianLastname);
		
		return (Party) query.getSingleResult();
	}

	@Override
	public List<Position> getPositionFromPoliticianLastname(String politicianLastname) {
		Query query = em.createQuery("SELECT posi FROM Position posi, IN(posi.politicians) pol WHERE pol.lastname = :lastname");
		query.setParameter("lastname", politicianLastname);
		return (List<Position>) query.getResultList();
	}

	@Override
	public List<Position> getPositionFromCivilServantLastname(String civilServantLastname) {
		Query query = em.createQuery("SELECT posi FROM Position posi, IN(posi.appointees) app WHERE app.lastname = :lastname");
		query.setParameter("lastname", civilServantLastname);
		return (List<Position>) query.getResultList();
	}

	@Override
	public List<Position> getPositions() {
		return em.createQuery("FROM Position").getResultList();
	}

	@Override
	public Party getPartyFromInitials(String initials) {
		Query query = em.createQuery("SELECT p FROM Party WHERE p.initials =:initials");
		query.setParameter("initials", initials);
		return (Party) query.getSingleResult();
	}

	@Override
	public void changeParty(Politician politician, Party newParty) {
		politician.setParty(newParty);
		politician = em.merge(politician);
		// TODO Contr�ler �a...
	}

	@Override
	public void addPosition(Person person, Position position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position getPositionFromName(String positionName) {
		Query query = em.createQuery("SELECT pos FROM Position pos WHERE pos.positionName = :positionName");
		query.setParameter("positionName", positionName);
		return (Position) query.getSingleResult();
	}

}
