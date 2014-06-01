package orchestrate;

import java.io.File;
import java.util.ArrayList;

import jsonWrapper.JSONConverter;
import annotate.Annotator;
import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;

public class Orchestrator {
	public static String delic(String pathname) throws Exception {
		String fileName = "lic";
		String conceptDirectory = "data/concepts";
		Document licenseDoc = new Document(new File(fileName));
		System.out.println("Text : " + licenseDoc.getDocText());
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		File conceptDir = new File(conceptDirectory);
		for(File conceptFile : conceptDir.listFiles()) {
			concepts.add(new Concept(conceptFile.getAbsolutePath()));
		}
		Annotator annon = new Annotator(concepts);
		ArrayList<AnnotatedSentence> annotatedSentences = annon.annotateDoc(licenseDoc);
		String jsonData = JSONConverter.getJSON(annotatedSentences);
		return jsonData;
	}
	
	public static void main(String args[]) throws Exception {
		
		System.out.println(Orchestrator.delic("data/licenses/analytics.lic"));
	}
}
