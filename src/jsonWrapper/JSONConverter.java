package jsonWrapper;

import java.util.ArrayList;
import com.google.gson.Gson;

import delic.AnnotatedSentence;

public class JSONConverter {

	public String getJSON(ArrayList<AnnotatedSentence> annotatedSentences) {
		
		Gson gson = new Gson();
		String json = gson.toJson(annotatedSentences);
		
		return json;
	}
}
