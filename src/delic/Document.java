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
	
	
	public Document(){
		docText = new String("");
	}
	
	public Document(String docText){
		this.docText = new String(docText);
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
