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

	public Concept(String conceptFileName) throws Exception {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			is = new FileInputStream(conceptFileName);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String currLine = null;

			if ((currLine = br.readLine()) != null) {
				conceptFileName = new String(conceptFileName);
				conceptWords = new ArrayList<String>(Arrays.asList(currLine
						.replaceAll("\\s*,\\s*", ",").split(",")));
				conceptName = (new File(conceptFileName)).getName();
				// System.out.println(conceptWords.toString());
			} else {
				System.out.println("Error: check the contents of file "
						+ conceptFileName);
			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			if (is != null)
				is.close();
			if (isr != null)
				isr.close();
			if (br != null)
				br.close();
		}
	}

	public Concept(ArrayList<String> concepts) {
		this.conceptWords = concepts;
	}
	
	public String getName() {
		return conceptName;
	}

	public String toString() {
		return conceptName;
	}

	public boolean isContainedIn(Sentence sentence) {
		boolean match = false;
		String lowerString = sentence.getSentenceStr().toLowerCase();
		String words[] = lowerString.split(" ");
		for (String cw : conceptWords) {
			// match = sentence.getSentenceStr().toLowerCase().contains(cw);
			match = Arrays.asList(words).contains(cw);
			if (match) {
				int beginBold = lowerString.indexOf(cw, 0);
				lowerString = lowerString.substring(0, beginBold)
						+ "<b>"
						+ cw
						+ "</b>"
						+ lowerString.substring(beginBold + cw.length(),
								lowerString.length());
			}
		}
		sentence.setSentenceStr(lowerString);
		return match;
	}
	
	public static void main(String args[]) {
		Sentence str = new Sentence("This is a transaction and will finish in time");
		ArrayList<String> cptList = new ArrayList<String>();
		cptList.add("transaction");
		cptList.add("finish");
		Concept c = new Concept(cptList);
		c.isContainedIn(str);
		System.out.println(str.getSentenceStr());
	}

}