package delic;

public class Sentence {
	public String sentenceStr;
	public boolean isLastSentence;

	public Sentence(String sentenceStr) {
		this.sentenceStr = sentenceStr;
		this.isLastSentence = true;
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
