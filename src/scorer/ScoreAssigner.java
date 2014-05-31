package scorer;

import java.util.ArrayList;
import java.util.Arrays;

import concept.Concept;
import concept.MetaConcept;

public class ScoreAssigner {
	
	private static final Integer multiplicandIncrement = 1;
	
	public static Integer getNaiveScore(ArrayList<Concept> conceptList){
		return conceptList.size();
	}
	
	public static Integer getMatchingScore(ArrayList<Concept> conceptList){
		Integer multiplicand = 1;
		Integer[] conceptExists = new Integer[MetaConcept.numOfConcepts];
		Arrays.fill(conceptExists, 0);
		
		for(Concept currConcept : conceptList){
			if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.FINANCE))
				conceptExists[MetaConcept.FINANCE] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.PARTY))
				conceptExists[MetaConcept.PARTY] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.LEGAL))
				conceptExists[MetaConcept.LEGAL] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.TRANSFER))
				conceptExists[MetaConcept.TRANSFER] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.SUSPENSION))
				conceptExists[MetaConcept.SUSPENSION] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.INFORMATION))
				conceptExists[MetaConcept.INFORMATION] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.OWNERSHIP))
				conceptExists[MetaConcept.OWNERSHIP] = 1;
			else if (MetaConcept.getConceptId(currConcept.getName()).equals(MetaConcept.USAGE))
				conceptExists[MetaConcept.USAGE] = 1;
		}
		
		if(conceptExists[MetaConcept.FINANCE] == 1 && conceptExists[MetaConcept.PARTY] == 1)
			multiplicand += multiplicandIncrement;
		if(conceptExists[MetaConcept.INFORMATION] == 1 && conceptExists[MetaConcept.PARTY] == 1)
			multiplicand += multiplicandIncrement;
		
		
		return 0;
	}

}
