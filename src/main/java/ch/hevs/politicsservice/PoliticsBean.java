package ch.hevs.politicsservice;

import java.util.List;

import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import ch.hevs.businessobject.CivilServant;
import ch.hevs.businessobject.Party;
import ch.hevs.businessobject.Person;
import ch.hevs.businessobject.Politician;
import ch.hevs.businessobject.Position;

@Stateless
@RolesAllowed(value={"Visitor", "Administrator"})
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class PoliticsBean implements Politics {

	@PersistenceContext(name = "PoliticsPU", type = PersistenceContextType.TRANSACTION)
	private EntityManager em;

	@Resource
	private SessionContext ctx;

	@Override
	public List<Politician> getPoliticians() {
		return (List<Politician>) em.createQuery("FROM Politician").getResultList();
	}

	@Override
	public List<Party> getParties() {
		return (List<Party>) em.createQuery("FROM Party").getResultList();
	}

	@Override
	public List<CivilServant> getCivilServants() {
		return (List<CivilServant>) em.createQuery("FROM CivilServant").getResultList();
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
		Query query = em.createQuery("SELECT p FROM Party p WHERE p.partyInitials =:initials");
		query.setParameter("initials", initials);
		return (Party) query.getSingleResult();
	}

	@Override
	public void changeParty(Politician politician, Party newParty) {

		politician.setParty(newParty);
		politician = em.merge(politician);
	}

	@Override
	public void addPosition(Person person, Position position) {
		person = em.merge(person);
		position = em.merge(position);
		person.addPosition(position);
	}

	@Override
	public Position getPositionFromName(String positionName) {
		Query query = em.createQuery("SELECT pos FROM Position pos WHERE pos.positionName = :positionName");
		query.setParameter("positionName", positionName);
		return (Position) query.getSingleResult();
	}

	@Override
	public Politician getPoliticianFromLastname(String politicianLastname) {
		Query query = em.createQuery("SELECT pol FROM Politician pol WHERE pol.lastname = :politicianLastname");
		query.setParameter("politicianLastname", politicianLastname);
		return (Politician) query.getSingleResult();
	}

	@Override
	public CivilServant getCivilServantFromLastname(String civilServantLastname) {
		Query query = em.createQuery("SELECT civ FROM CivilServant civ WHERE civ.lastname = :civilServantLastname");
		query.setParameter("civilServantLastname", civilServantLastname);
		return (CivilServant) query.getSingleResult();
	}

	//method used to allow a transaction considering the user's role 
	public boolean checkRole() {
		boolean notAllowed= false;
		if ( !ctx.isCallerInRole("Administrator")) { // verify that the caller has the correct role 
			notAllowed=true;				
		}
		return notAllowed;
	}
}
