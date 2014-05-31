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
	ScoreAssigner scorer;
	/**
	 * Takes a document and a directory of concepts and sets up
	 * stage for license annotation
	 * @param doc
	 * @param conceptDirectory
	 * @throws Exception 
	 */
	public HTMLAnnotator(ArrayList<Concept> concepts) throws Exception {
		this.concepts = new ArrayList<Concept>(concepts);
	}
	public HTMLAnnotator() {
		
	}
	
	Document annotate(Document doc) {
		
		Document annotated = new Document();
		annotated.addLine("<html><body>");
		Iterator<Sentence> sentenceItr = doc.getSentenceIterator();
		while(sentenceItr.hasNext()) {
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
			//int score = ScoreAssigner.getMatchingScore(conceptsContained);
			int score = ScoreAssigner.getNaiveScore(conceptsContained);
			
			System.out.println("Score : " + score);
		if(score < 2) {
			annotated.addLine(str.getSentenceStr() + "(" + score + ")");
		} else {
			String colorCode = getColorCode(score);
			String res = "<font color = " + colorCode + ">" + str.getSentenceStr() + "</font>, (" + score + ")";
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
		String colorCode[] = 
			{"#000000","#080000","#100000","#180000","#200000","#280000","#300000",
			"#380000","#400000","#480000","#500000","#580000","#600000","#680000",
			"#700000","#780000","#800000","#880000","#900000","#980000","#A00000",
			"#A80000","#B00000","#B80000","#C00000","#C80000","#D00000","#D80000",
			"#E00000","#E80000","#F00000","#F80000","#FF0000"};

			return colorCode[numMatches + 20];
	}
	
	public static void main(String args[]) throws Exception {
		String fileName = "data/licenses/Atlassian.lic";
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