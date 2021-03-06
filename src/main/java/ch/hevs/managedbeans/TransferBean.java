package ch.hevs.managedbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import ch.hevs.businessobject.CivilServant;
import ch.hevs.businessobject.Party;
import ch.hevs.businessobject.Politician;
import ch.hevs.businessobject.Position;
import ch.hevs.politicsservice.Politics;



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
	private String politicianDestinationPositionName;
	private String civilServantDestinationPositionName;
	private String politicianName;
	private String civilServantName;
	private String transactionResult;
	private Politics politics;
	private boolean checkRole;

	@PostConstruct
	public void initialize() throws NamingException {



		// use JNDI to inject reference to EJB
		InitialContext ctx = new InitialContext();
		politics = (Politics) ctx.lookup("java:global/Politics-0.0.1-SNAPSHOT/PoliticsBean!ch.hevs.politicsservice.Politics");    	

		//get the role of the user
		this.checkRole = politics.checkRole();

		// get politicians
		List<Politician> politicianList = politics.getPoliticians();
		this.politicianNames = new ArrayList<String>();
		for (Politician politician : politicianList) {
			this.politicianNames.add(politician.getLastname());
		}

		// get civil servants
		List<CivilServant> civilServantList = politics.getCivilServants();
		this.civilServantNames = new ArrayList<String>();
		for (CivilServant civilServant : civilServantList) {
			this.civilServantNames.add(civilServant.getLastname());
		}

		// initialize party initials
		List<Party> partiesList = politics.getParties();
		this.partiesInitials = new ArrayList<String>();
		for (Party party : partiesList ) {
			this.partiesInitials.add(party.getPartyInitials());
		}

		// initialize party name of politicians
		sourcePartyInitials = politics.getPartyFromPoliticianLastname(politicianList.get(0).getLastname()).getPartyInitials();


		// initialize position name of politicians
		this.politicianSourcePositionNames= new ArrayList<String>();
		List<Position> positionsPoliticians = politics.getPositionFromPoliticianLastname(politicianList.get(0).getLastname());
		if (positionsPoliticians.size()==0) {
		}
		else {
			for (Position position : positionsPoliticians) {
				this.politicianSourcePositionNames.add(position.getPositionName());
			}
		}

		// initialize position name of civil servants
		this.civilServantSourcePositionNames= new ArrayList<String>();
		List<Position> positionsCivilServants = politics.getPositionFromCivilServantLastname(civilServantList.get(0).getLastname());
		if (positionsCivilServants.size()==0){

		}
		else {
			for (Position position : positionsCivilServants) {
				this.civilServantSourcePositionNames.add(position.getPositionName());
			}
		}

		//initialize destination position names
		this.destinationPositionNames = new ArrayList<String>();
		List<Position> positionList = politics.getPositions();
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

	//	politicianSourcePositionNames returns a string without brackets
	public String getPoliticianSourcePositionNamesString() {

		if (politicianSourcePositionNames!=null) {
			return politicianSourcePositionNames.toString().replace("[", "").replace("]", "");

		}

		return null;
	}


	//	civilServantSourcePositionNames
	public List<String> getCivilServantSourcePositionNames() {
		return civilServantSourcePositionNames;
	}

	//	civilServantSourcePositionNames returns a string without brackets
	public String getCivilServantSourcePositionNamesString() {

		if (civilServantSourcePositionNames!=null) {
			return civilServantSourcePositionNames.toString().replace("[", "").replace("]", "");

		}

		return null;
	}

	// DestinationPositionNames
	public List<String> getDestinationPositionNames() {
		return destinationPositionNames;
	}



	// sourcePartyInitials
	public String getSourcePartyInitials() {
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


	//	PoliticianDestinationPositionName
	public String getPoliticianDestinationPositionName() {
		return politicianDestinationPositionName;
	}

	public void setPoliticianDestinationPositionName(String politicianDestinationPositionName) {
		this.politicianDestinationPositionName = politicianDestinationPositionName;
	}


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

	// checkRole
	public boolean getCheckRole() {
		return checkRole;
	}

	public void setCheckRole(boolean checkRole) {
		this.checkRole = checkRole;
	}


	public void updatePartyAndPosition(ValueChangeEvent event) {
		this.politicianName = (String)event.getNewValue();

		Party party = politics.getPartyFromPoliticianLastname(this.politicianName);

		sourcePartyInitials=party.getPartyInitials();

		List<Position> positions = politics.getPositionFromPoliticianLastname(politicianName);
		this.politicianSourcePositionNames = new ArrayList<String>();
		for (Position position : positions) {
			this.politicianSourcePositionNames.add(position.getPositionName());
		}
	}

	public void updatePositionsCivilServant(ValueChangeEvent event) {

		this.civilServantName = (String)event.getNewValue();

		List<Position> positions = politics.getPositionFromCivilServantLastname(civilServantName);
		this.civilServantSourcePositionNames = new ArrayList<String>();
		for (Position position : positions) {
			this.civilServantSourcePositionNames.add(position.getPositionName());
		}
	}


	public String performPartyChange() {

		try {
			if (sourcePartyInitials.equals(destinationPartyInitials))  {

				this.transactionResult="Error: parties are identical!";
			} 
			else {

				Politician politician = politics.getPoliticianFromLastname(politicianName);
				Party partyDest = politics.getPartyFromInitials(destinationPartyInitials);

				// Transfer
				politics.changeParty (politician, partyDest);

				this.transactionResult="Party changed from " +sourcePartyInitials+" to " +destinationPartyInitials +" for politician " +politicianName +".";

				//For updating the initials source
				sourcePartyInitials=destinationPartyInitials;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	} 

	public String performPositionAddingPolitician() {

		try {
			for(String position:politicianSourcePositionNames){
				if (position.equals(politicianDestinationPositionName))  {

					this.transactionResult="Error: position already exists!";
					return "showTransferResult";
				} 

			}
			Politician politician = politics.getPoliticianFromLastname(politicianName);
			Position newPosition = politics.getPositionFromName(politicianDestinationPositionName);

			// Transfer
			politics.addPosition (politician, newPosition);
			//For updating the position source
			politicianSourcePositionNames.add(politicianDestinationPositionName);
			this.transactionResult="Position " +politicianDestinationPositionName +" added to politician " +politicianName +".";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	}

	public String performPositionAddingCivilServant() {

		try {
			for(String position:civilServantSourcePositionNames){
				if (position.equals(civilServantDestinationPositionName))  {

					this.transactionResult="Error: position already exists!";
					return "showTransferResult";
				} 
			}

			CivilServant civilServant = politics.getCivilServantFromLastname(civilServantName);
			Position newPosition = politics.getPositionFromName(civilServantDestinationPositionName);

			// Transfer
			politics.addPosition (civilServant, newPosition);
			//For updating the position source
			civilServantSourcePositionNames.add(civilServantDestinationPositionName);
			this.transactionResult="Position " +civilServantDestinationPositionName +" added to civil servant " +civilServantName +".";

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showTransferResult"; //  the String value returned represents the outcome used by the navigation handler to determine what page to display next.
	}
}