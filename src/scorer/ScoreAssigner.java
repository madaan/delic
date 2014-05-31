package scorer;

import java.util.ArrayList;
import concept.Concept;

public class ScoreAssigner {
	
	public static Integer getNaiveScore(ArrayList<Concept> conceptList){
		return conceptList.size();
	}
	
	public static Integer getMatchingScore(ArrayList<Concept> conceptList){
		boolean hasFinance = false;
		boolean hasParty = false;
		boolean hasLegql = false;
		boolean hasTransfer = false;
		boolean hasSuspension = false;
		boolean hasInformation = false;
		boolean hasOwnership = false;
		boolean hasUsage = false;
		
		for(Concept currConcept : conceptList){
			if(currConcept.)
		}
	}

}
