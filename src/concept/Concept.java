package concept;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import delic.Sentence;

/**
 * This class represents a concept. The concept has a name and a list of
 * relevant words.
 * 
 * @author aman
 * 
 */
public class Concept {
	String conceptName;
	ArrayList<String> conceptWords;

	public Concept() {
		
	}
	
	public Concept(String conceptFileName) throws Exception{
		InputStream is = null; 
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    
	    try{
	         is = new FileInputStream(conceptFileName);
	         isr = new InputStreamReader(is);
	         br = new BufferedReader(isr);   
	         String currLine = null;
	   
	         if ((currLine = br.readLine()) != null) {
	        	 conceptFileName = new String(conceptFileName);
	        	 conceptWords = new ArrayList<String>(Arrays.asList(currLine.replaceAll("\\s*,\\s*", ",").split(",")));  
	        	 conceptName = (new File(conceptFileName)).getName();
	        	 //System.out.println(conceptWords.toString());
	         }
	         else{
	        	 System.out.println("Error: check the contents of file " + conceptFileName);
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
	
	public String getName(){
		return conceptName;
	}
	
	public String toString(){
		return conceptName;
	}

	public boolean isContainedIn(Sentence sentence) {
		boolean match = false;
		for (String cw : conceptWords) {
			match = sentence.getSentenceStr().contains(cw);
			if (match) {
				return true;
			}
		}
		return false;
	}

}