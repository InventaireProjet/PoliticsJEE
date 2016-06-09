package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.CivilServant;
import ch.hevs.businessobject.Party;
import ch.hevs.businessobject.Politician;
import ch.hevs.businessobject.Position;

/**
 * TransferBean.java
 * 
 */

public class TransferBean
{
	private List<Politician> politicians;
	private List<String> politicianNames;
	private List<CivilServant> civilServants;
	private List<String> civilServantNames;
	private List<String> partiesInitials;
	private List<String> politicianSourcePositionNames;
	private List<String> civilServantSourcePositionNames;
	private List<String> destinationPositionNames; 
	private String sourcePartyInitials;
	private String destinationPartyInitials;
	//private String politicianSourcePositionName;
	private String politicianDestinationPositionName;
	//	private String civilServantSourcePositionName;
	private String civilServantDestinationPositionName;
	private String politicianName;
	private String civilServantName;
	private String transactionResult;
	private Bank bank;

	@PostConstruct
	public void initialize() throws NamingException {

		// use JNDI to inject reference to EJB

		//TODO Changer références
		InitialContext ctx = new InitialContext();
		bank = (Bank) ctx.lookup("java:global/PoliticsJEE-0.0.1-SNAPSHOT/BankBean!ch.hevs.bankservice.Bank");    	

		// get politicians
		List<Politician> politicianList = bank.getPoliticians();
		this.politicianNames = new ArrayList<String>();
		for (Politician politician : politicianList) {
			this.politicianNames.add(politician.getLastname());
		}

		// get civil servants
		List<CivilServant> civilServantList = bank.getCivilServants();
		this.civilServantNames = new ArrayList<String>();
		for (CivilServant civilServant : civilServantList) {
			this.civilServantNames.add(civilServant.getLastname());
		}

		// initialize party initials
		this.partiesInitials = new ArrayList<String>();
		Party party = bank.getPartyFromPoliticianLastname(politicianList.get(0).getLastname());
		this.partiesInitials.add(party.getPartyInitials());

		// initialize position name of politicians
		this.politicianSourcePositionNames= new ArrayList<String>();
		List<Position> positionsPoliticians = bank.getPositionFromPoliticianLastname(politicianList.get(0).getLastname());
		this.politicianSourcePositionNames.add(positionsPoliticians.get(0).getPositionName());


		// initialize position name of civil servants
		this.civilServantSourcePositionNames= new ArrayList<String>();
		List<Position> positionsCivilServants = bank.getPositionFromCivilServantLastname(civilServantList.get(0).getLastname());
		this.civilServantSourcePositionNames.add(positionsCivilServants.get(0).getPositionName());

		//initialize destination position names
		this.destinationPositionNames = new ArrayList<String>();
		List<Position> positionList = bank.getPositionNames());
		for (Position position : positionList) {
			this.destinationPositionNames.add(position.getPositionName());
		}
	}



	//	Politicians
	public List<Politician> getPoliticians() {
		return politicians;
	}


	//	PoliticianNames
	public List<String> getPoliticianNames() {
		return politicianNames;
	}


	//	CivilServants
	public List<CivilServant> getCivilServants() {
		return civilServants;
	}

	//	CivilServantNames
	public List<String> getCivilServantNames() {
		return civilServantNames;
	}

	//	PartiesInitials
	public List<String> getPartiesInitials() {
		return partiesInitials;
	}

	//	politicianSourcePositionNames
	public List<String> getPoliticianSourcePositionNames() {
		return politicianSourcePositionNames;
	}

	//	civilServantSourcePositionNames
	public List<String> getCivilServantSourcePositionNames() {
		return civilServantSourcePositionNames;
	}

	// DestinationPositionNames
	public List<String> getDestinationPositionNames() {
		return destinationPositionNames;
	}



	// sourcePartyInitials
	public String getSourcePartInitials() {
		return sourcePartyInitials;
	}
	public void setSourcePartyInitials(String sourcePartyInitials) {
		this.sourcePartyInitials = sourcePartyInitials;
	}

	// destinationPartyInitials
	public String getDestinationPartyInitials() {
		return destinationPartyInitials;
	}
	public void setDestinationPartyInitials(
			String destinationPartyInitials) {
		this.destinationPartyInitials = destinationPartyInitials;
	}

	/*	//	PoliticianSourcePositionName
	public String getPoliticianSourcePositionName() {
		return politicianSourcePositionName;
	}

	public void setPoliticianSourcePositionName(String politicianSourcePositionName) {
		this.politicianSourcePositionName = politicianSourcePositionName;
	}
	 **/

	//	PoliticianDestinationPositionName
	public String getPoliticianDestinationPositionName() {
		return politicianDestinationPositionName;
	}

	public void setPoliticianDestinationPositionName(String politicianDestinationPositionName) {
		this.politicianDestinationPositionName = politicianDestinationPositionName;
	}

	/*
	//	CivilServantSourcePositionName
	public String getCivilServantSourcePositionName() {
		return civilServantSourcePositionName;
	}

	public void setCivilServantSourcePositionName(String civilServantSourcePositionName) {
		this.civilServantSourcePositionName = civilServantSourcePositionName;
	}

	 */
	//	CivilServantDestinationPositionName
	public String getCivilServantDestinationPositionName() {
		return civilServantDestinationPositionName;
	}

	public void setCivilServantDestinationPositionName(String civilServantDestinationPositionName) {
		this.civilServantDestinationPositionName = civilServantDestinationPositionName;
	}


	//	PoliticianName
	public String getPoliticianName() {
		return politicianName;
	}

	public void setPoliticianName(String politicianName) {
		this.politicianName = politicianName;
	}



	// CivilServantName
	public String getCivilServantName() {
		return civilServantName;
	}

	public void setCivilServantName(String civilServantName) {
		this.civilServantName = civilServantName;
	}


	// transactionResult
	public String getTransactionResult () {
		return transactionResult;
	}
	public void setTransactionResult(String transactionResult) {
		this.transactionResult = transactionResult;
	}


	public void updateParty(ValueChangeEvent event) {
		this.politicianName = (String)event.getNewValue();

		Party party = bank.getPartyFromPoliticianLastname(this.politicianName);
		this.sourcePartyInitials = new String();
		sourcePartyInitials=party.getPartyInitials();

	}
	public void updatePositionsPolitician(ValueChangeEvent event) {
		this.politicianName = (String)event.getNewValue();

		List<Position> positions = bank.getPositionFromPoliticianLastname(politicianName);
		this.politicianSourcePositionNames = new ArrayList<String>();
		for (Position position : positions) {
			this.politicianSourcePositionNames.add(position.getPositionName());
		}
	}

	public void updatePositionsCivilServant(ValueChangeEvent event) {

		this.civilServantName = (String)event.getNewValue();

		List<Position> positions = bank.getPositionFromCivilServantLastname(civilServantName);
		this.civilServantSourcePositionNames = new ArrayList<String>();
		for (Position position : positions) {
			this.civilServantSourcePositionNames.add(position.getPositionName());
		}
	}


	public String performPartyChange() {

		try {
			if (partiesInitials.equals(destinationPartyInitials))  {

				this.transactionResult="Error: parties are identical!";
			} 
			else {

				Party partySrc = bank.getPartyFromInitials(sourcePartyInitials);
				Party partyDest = bank.getParty(destinationPartyInitials);

				// Transfer
				bank.changeParty (partySrc, partyDest);
				this.transactionResult="Success!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 
	
	public String performPostionAddingPolitician() {

		try {
			for(String position:politicianSourcePositionNames)
			if (position.equals(politicianDestinationPositionName))  {

				this.transactionResult="Error: position already exists!";
			} 
			else {

				Position newPosition = bank.getPositionFromName(politicianDestinationPositionName);

				// Transfer
				bank.addPosition (newPosition);
				this.transactionResult="Success!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	}
	
	public String performPostionAddingCivilServant() {

		try {
			for(String position:civilServantSourcePositionNames)
			if (position.equals(politicianDestinationPositionName))  {

				this.transactionResult="Error: position already exists!";
			} 
			else {

				Position newPosition = bank.getPositionFromName(civilServantDestinationPositionName);

				// Transfer
				bank.addPosition (newPosition);
				this.transactionResult="Success!";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	}
}