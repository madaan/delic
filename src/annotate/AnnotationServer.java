//sg
package annotate;
import java.util.ArrayList;
import jsonWrapper.JSONConverter;
import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;

public class AnnotationServer {

	public AnnotationServer() {
	}
	
	public static void main() {
		String fileName = "lic";
		Document licenseDoc = new Document(fileName);
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		Annotator annon = new Annotator(concepts);
		ArrayList<AnnotatedSentence> annotatedSentences = annon.annotateDoc(licenseDoc);
		String jsonData = JSONConverter.getJSON(annotatedSentences);
		System.out.println(jsonData.toString());
	}
}