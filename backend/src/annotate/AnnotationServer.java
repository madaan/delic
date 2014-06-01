//sg
package annotate;
import java.io.File;
import java.util.ArrayList;

import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;

public class AnnotationServer {

	public AnnotationServer() {
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
		
		Annotator annon = new Annotator(concepts);
		ArrayList<AnnotatedSentence> annotatedSentences = annon.annotateDoc(licenseDoc);
		for(AnnotatedSentence as: annotatedSentences)
			System.out.println(as);
		//String jsonData = JSONConverter.getJSON(annotatedSentences);
		//System.out.println(jsonData);
		
	}
}