package annotate;

import java.util.ArrayList;
import java.util.Iterator;

import scorer.ScoreAssigner;
import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;
import delic.Sentence;

/**
 * This is the workhorse, responsible for annotating the 
 * given document. Gets a document and returns an arraylist of
 * @author aman
 *
 */
public class Annotator {

	ArrayList<Concept> conceptList;
	
	public Annotator(ArrayList<Concept> concepts) {
		this.conceptList = concepts;
	}
	
	/**
	 * This function receives a document and returns a list of annotated sentences
	 * Where the annotated sentence is as defined by the class delic.AnnotatedSentence 
	 * @param licenseDoc
	 * @return
	 */
	public ArrayList<AnnotatedSentence> annotateDoc(Document licenseDoc) {
		ArrayList<AnnotatedSentence> annotations = new ArrayList<AnnotatedSentence>();
		Iterator<Sentence> sentenceItr = licenseDoc.getSentencePOSBased();
		while(sentenceItr.hasNext()) { //for each sentence
			ArrayList<Concept> matchedConceptList = new ArrayList<Concept>(); //maintain a list of concepts that are matched
			Sentence currSentence = sentenceItr.next();
			for(Concept c : this.conceptList) {
				if(c.isContainedIn(currSentence)) {
					matchedConceptList.add(c);
				}
			}
			int score = ScoreAssigner.getNaiveScore(matchedConceptList);
			annotations.add(new AnnotatedSentence(currSentence, matchedConceptList, score));
		}
		return annotations;
	}
	
	
	
	
}
