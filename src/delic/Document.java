//sg
package delic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class Document {
	
	String docText;

	public Document(){
		docText = new String("");
	}
	public Document(File fileName) throws Exception{
		docText = "";
		//licenseSentences = null;
		
		InputStream is = null; 
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    
	    try{
	         is = new FileInputStream(fileName);
	         isr = new InputStreamReader(is);
	         br = new BufferedReader(isr);   
	         String currLine = null;
	   
	         while ((currLine = br.readLine()) != null) {
	             docText += currLine; 	 
	          } 
	         
	      }
	    
	    catch(Exception e){
	         e.printStackTrace();
	    }
		
	    finally{	         
	         if(is != null)
	        	is.close();
	         if(isr != null)
	            isr.close();
	         if(br != null)
	            br.close();
	      }
	}
	

	public Document(String docText){
		this.docText = new String(docText);
	}

	public ArrayList<Sentence> getSentences(){
		
		ArrayList<Sentence> sentences = new ArrayList<Sentence>();
		
		char docWords[] = docText.toCharArray();
		
		String sentenceString = "";
		boolean sawFullstop = false;
		Sentence sentence = new Sentence("");
		for(int i = 0; i < docWords.length; i++){
			if(docWords[i] == '\n' && sawFullstop){
				sentence.setIsLastSentence(true);
				sentenceString = "";
				sentences.add(sentence);
				sentence = new Sentence("");
				sawFullstop = false;
			}else if(docWords[i] == '.' || docWords[i] == '!'){
				sawFullstop = true;
				sentenceString = sentenceString+docWords[i];
				sentence.setSentenceStr(sentenceString);
			}else if(sawFullstop){
				sentences.add(sentence);
				sentence = new Sentence("");
				sentenceString = ""+docWords[i];
				sawFullstop = false;
			}else{
				sentenceString = sentenceString+docWords[i];
			}
		}
		
		if(!sentence.getSentenceStr().equals(""))
			sentences.add(sentence);
	
		return sentences;
		
	}
	
	public static void main(String args[]){
		String docText = "Stupid world.\n This works, even then. I am not sure.\n";
		Document doc = new Document(docText);
		System.out.println(doc.getSentences());
	}
	
	/**
	 * This is a stub based on regular expression
	 * TODO : This should be replaced with NLP based  
	 * @return
	 */
	public Iterator<Sentence> getSentenceIterator(){
		ArrayList<String> licenseSplit = new ArrayList<String>(Arrays.asList(docText.split("[\n.?!]")));
		ArrayList<Sentence>licenseSentences = new ArrayList<Sentence>();
		
		for(String s: licenseSplit){
			Sentence currSentence = new Sentence(s);
			licenseSentences.add(currSentence);
		}	
		return licenseSentences.iterator();
	}
}
