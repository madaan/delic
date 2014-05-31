package delic;

import java.util.ArrayList;

public class AnnotatedSentence extends Sentence {

	int score;
	ArrayList<String> keywords;

	public AnnotatedSentence(String sentenceStr, ArrayList<String> keywords,
			int score) {
		super(sentenceStr);
		this.score = score;
		this.keywords = new ArrayList<>(keywords);
	}

}
