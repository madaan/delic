package concept;
/**
 * This class stores information about the concepts involved, basically ids etc.
 * @author Harshit
 *
 */
public class MetaConcept {
	
	public static final Integer FINANCE = 0;
	public static final Integer PARTY = 1;
	public static final Integer LEGAL = 2;
	public static final Integer TRANSFER = 3;
	public static final Integer SUSPENSION = 4;
	public static final Integer INFORMATION = 5;
	public static final Integer OWNERSHIP = 6;
	public static final Integer USAGE = 7;//this must always be the last in this list because numOfConcepts depends on it being the last 
	
	public static final Integer numOfConcepts = USAGE + 1;
	
	public static Integer getConceptId(String concept){
		if (concept.equals("finance.cpt"))
			return FINANCE;
		else if (concept.equals("party.cpt"))
			return PARTY;
		else if (concept.equals("legalese.cpt"))
			return LEGAL;
		else if (concept.equals("transfer.cpt"))
			return TRANSFER;
		else if (concept.equals("suspension.cpt"))
			return SUSPENSION;
		else if (concept.equals("information.cpt"))
			return INFORMATION;	
		else if (concept.equals("ownership.cpt"))
			return OWNERSHIP;
		else if (concept.equals("usage.cpt"))
			return USAGE;
		else
			return -1;
	}
	
	public static String getConceptString(Integer id){
		if (id.equals(0))
			return "finance.cpt";
		else if (id.equals(1))
			return "party.cpt";
		else if (id.equals(2))
			return "legalese.cpt";
		else if (id.equals(3))
			return "transfer.cpt";
		else if (id.equals(4))
			return "suspension.cpt";
		else if (id.equals(5))
			return "information.cpt";	
		else if (id.equals(6))
			return "ownership.cpt";
		else if (id.equals(7))
			return "usage.cpt";
		else
			return "";
	}
}
