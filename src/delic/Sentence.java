package delic;

public class Sentence {
	String sentenceStr;
	boolean lastSentence;

	public Sentence(String sentenceStr) {
		this.sentenceStr = sentenceStr;
		this.lastSentence = true;
	}

	public void setSentenceStr(String sentenceStr) {
		this.sentenceStr = sentenceStr;
	}

	public String getSentenceStr() {
		return sentenceStr;
	}

}
