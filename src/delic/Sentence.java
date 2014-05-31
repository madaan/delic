package delic;

public class Sentence {
	public String sentenceStr;
	public boolean lastSentence;

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
