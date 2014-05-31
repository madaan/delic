package annotate;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import scorer.ScoreAssigner;
import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;
import delic.Sentence;

public class HTMLAnnotator {

	ArrayList<Concept> concepts;
	Document licenseDoc;
	ScoreAssigner scorer;
	/**
	 * Takes a document and a directory of concepts and sets up
	 * stage for license annotation
	 * @param doc
	 * @param conceptDirectory
	 * @throws Exception 
	 */
	public HTMLAnnotator(ArrayList<Concept> concepts) throws Exception {
		this.concepts = new ArrayList<Concept>();
	}
	public HTMLAnnotator() {
		
	}
	
	Document annotate(Document doc) {
		this.licenseDoc = doc;
		Document annotated = new Document();
		annotated.addLine("<html><body>");
		Iterator<Sentence> sentenceItr = licenseDoc.getSentenceIterator();
		while(sentenceItr.hasNext()) {
			int numMatches = 0;
			ArrayList<Concept> conceptsContained = new ArrayList<Concept>();
			Sentence str = sentenceItr.next();
			for(Concept c : concepts) {
				if(c.isContainedIn(str)) {
					conceptsContained.add(c);
				}
			}
			if(str.getIsLastSentence()) {
				annotated.addLine("<br/>");
			}
			System.out.println(conceptsContained);
			int score = ScoreAssigner.getNaiveScore(conceptsContained);
			System.out.println("Score : " + score);
		if(score < 1) {
			annotated.addLine(str.getSentenceStr());
		} else {
			String colorCode = getColorCode(numMatches);
			String res = "<font color = " + colorCode + ">" + str.getSentenceStr() + "</font>";
			annotated.addLine(res);
		}
		}
		annotated.addLine("</body></html>");
		return annotated;
	}
	
	private boolean between(int num, int a, int b) {
		return (num > a && num <= b);
	}
	/**
	 * Returns an apt color code for given number of matches
	 * @param numMatches
	 * @return
	 */
	String getColorCode(int numMatches) {
		if(numMatches == 1) {
			return "#88cddc";
		} else if(between(numMatches, 1, 3)) {
			return "#dcc788";
		} else if(between(numMatches, 3, 5)) {
			return "#dc8888";			
		}
		return "#ff0000";
	}
	
	public static void main(String args[]) throws Exception {
		String fileName = "lic";
		String conceptDirectory = "data/concepts";
		Document licenseDoc = new Document(new File(fileName));
		System.out.println("Text : " + licenseDoc.getDocText());
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		File conceptDir = new File(conceptDirectory);
		for(File conceptFile : conceptDir.listFiles()) {
			concepts.add(new Concept(conceptFile.getAbsolutePath()));
		}
		
		HTMLAnnotator annon = new HTMLAnnotator(concepts);
		Document annotated = annon.annotate(licenseDoc);
		annotated.writeDocToDisk("lic.html");

	}
	
}