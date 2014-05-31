package delic;

public class Sentence {
	String sentenceStr;
	boolean isLastSentence;

	public Sentence(String sentenceStr) {
		this.sentenceStr = sentenceStr;
		this.isLastSentence = false;
	}

	public void setSentenceStr(String sentenceStr) {
		this.sentenceStr = sentenceStr;
	}

	public String getSentenceStr() {
		return sentenceStr;
	}
	
	public void setIsLastSentence(boolean flag){
		this.isLastSentence = flag;
	}
	
	public boolean getIsLastSentence(){
		return this.isLastSentence;
	}

	public String toString(){
		return sentenceStr+" "+isLastSentence;
		
	}
}
