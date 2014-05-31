package delic;

import java.util.ArrayList;

import concept.Concept;

public class AnnotatedSentence extends Sentence {

	int score;
	ArrayList<Concept> matchedConcepts;

	public AnnotatedSentence(Sentence sentence, ArrayList<Concept> matchedConcepts,
			int score) {
		super(sentence.getSentenceStr());
		this.score = score;
		this.matchedConcepts = new ArrayList<Concept>(matchedConcepts);
	}

}
