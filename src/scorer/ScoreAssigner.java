package scorer;

import java.util.ArrayList;
import concept.Concept;

public class ScoreAssigner {
	
	public static Integer getNaiveScore(ArrayList<Concept> conceptList){
		return conceptList.size();
	}

}
