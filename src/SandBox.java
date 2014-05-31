import java.io.Reader;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

//sg
public class SandBox {

	public SandBox() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String args[]) {
		String paragraph = "I live in the US. We got problems, Mr. Stegeta have you read a.txt?";
		Reader reader = new StringReader(paragraph);
		DocumentPreprocessor dp = new DocumentPreprocessor(reader);
		List<String> sentenceList = new LinkedList<String>();
		Iterator<List<HasWord>> it = dp.iterator();
		while (it.hasNext()) {
		   StringBuilder sentenceSb = new StringBuilder();
		   List<HasWord> sentence = it.next();
		   for (HasWord token : sentence) {
		      if(sentenceSb.length() >= 1) {
		         sentenceSb.append(" ");
		      }
		      sentenceSb.append(token);
		   }
		   sentenceList.add(sentenceSb.toString());
		}

		for(String sentence:sentenceList) {
		   System.out.println(sentence);
		}	
	}

}
