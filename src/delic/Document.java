//sg
package delic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.process.DocumentPreprocessor;

public class Document {

	String docText;

	public Document() {
		docText = new String("");
	}

	public Document(File fileName) throws Exception {
		docText = "";
		// licenseSentences = null;

		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		try {
			is = new FileInputStream(fileName);
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String currLine = null;

			while ((currLine = br.readLine()) != null) {
				docText += currLine;
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

	public void addLine(String str) {
		docText += ("\n" + str);
	}

	public Document(String docText) {
		this.docText = new String(docText);
	}

	/**
	 * Chunk a document into sentences
	 * 
	 * @return ArrayList
	 */
	public Iterator<Sentence> getSentenceIterator() {

		ArrayList<Sentence> sentences = new ArrayList<Sentence>();

		char docWords[] = docText.toCharArray();

		String sentenceString = "";
		boolean sawFullstop = false;
		Sentence sentence = new Sentence("");
		for (int i = 0; i < docWords.length; i++) {
			if (docWords[i] == '\n' && sawFullstop) {
				sentence.setIsLastSentence(true);
				sentenceString = "";
				sentences.add(sentence);
				sentence = new Sentence("");
				sawFullstop = false;
			} else if (docWords[i] == '.' && (i < docWords.length - 4)
					&& docText.substring(i + 1, i + 4).equals("com")) {
				sentenceString = sentenceString + docText.substring(i, i + 4);
				i = i + 3;
			} else if (docWords[i] == '.'
					&& (i > 4)
					&& (docText.substring(i - 4, i - 1).equals("etc")
							|| docText.substring(i - 4, i - 1).equals("www") || docText
							.subSequence(i - 4, i - 1).equals("Inc"))) {
				sentenceString = sentenceString + docWords[i];
			} else if (docWords[i] == '.' || docWords[i] == '(') {
				sawFullstop = true;
				sentenceString = sentenceString + docWords[i];
				sentence.setSentenceStr(sentenceString);
			} else if (sawFullstop) {
				sentences.add(sentence);
				sentence = new Sentence("");
				sentenceString = "" + docWords[i];
				sawFullstop = false;
			} else {
				sentenceString = sentenceString + docWords[i];
			}
		}

		if (!sentence.getSentenceStr().equals(""))
			sentences.add(sentence);

		return sentences.iterator();

	}

	public static void main(String args[]) {
		String docText = "Stupid world.com is new site at Yahoo! Inc. in  form [username].tumblr.com.\n This works, even then. I am not sure.\n";
		Document doc = new Document(docText);
		Iterator<Sentence> iterator = doc.getSentenceIterator();
		while (iterator.hasNext()) {
			Sentence sentence = iterator.next();
			System.out.println(sentence.toString());
		}
		// System.out.println(doc.getSentenceIterator());
	}

	/**
	 * This is a stub based on regular expression TODO : This should be replaced
	 * with NLP based splitter
	 * 
	 * @return
	 */
	public Iterator<Sentence> getSentenceIteratorNaive() {
		ArrayList<String> licenseSplit = new ArrayList<String>(
				Arrays.asList(docText.split("[\n.?!]")));
		ArrayList<Sentence> licenseSentences = new ArrayList<Sentence>();

		for (String s : licenseSplit) {
			Sentence currSentence = new Sentence(s);
			licenseSentences.add(currSentence);
		}
		return licenseSentences.iterator();
	}

	/**
	 * Stanford NLP library based sentence segmentation
	 * @return Arraylist of sentences
	 */
	public Iterator<Sentence> getSentencePOSBased() {
		Reader reader = new StringReader(this.docText);
		DocumentPreprocessor dp = new DocumentPreprocessor(reader);
		ArrayList<Sentence> sentenceList = new ArrayList<Sentence>();
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
		   sentenceList.add(new Sentence(sentenceSb.toString()));
		}
		return sentenceList.iterator();
	}
	
	public String getDocText() {
		return docText;
	}

	public void writeDocToDisk(String fileName) throws FileNotFoundException {
		PrintWriter pw = new PrintWriter(new File(fileName));
		pw.write(docText);
		pw.close();
	}

}
