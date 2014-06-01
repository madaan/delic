package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import jsonWrapper.JSONConverter;

import annotate.Annotator;

import concept.Concept;

import delic.AnnotatedSentence;
import delic.Document;
/*
import orchestrate.Orchestrator;

import jsonWrapper.JSONConverter;

import annotate.Annotator;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import concept.Concept;
import delic.AnnotatedSentence;
import delic.Document;
*/
@Path("/delic")
public class WebService {
	// This method is called if TEXT_PLAIN is request
	/*
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_PLAIN) public String sayPlainTextHello() {
	 * return "Hello Jersey"; }
	 */
	@Context
	ServletContext context;
	@POST
	@Consumes("text/plain")
	@Produces("aplication/json")
	public String post(String message) throws Exception {
		System.out.println(message);
		
		Integer threshold = Integer.parseInt("" + message.charAt(0));
		message = message.substring(2, message.length());
		return delic(message, threshold);
	}
	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces("text/plain")
	public String uploadFile(
			@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail) {

		String uploadedFileLocation = "~/"
				+ fileDetail.getFileName();
		System.out.println(fileDetail.getFileName());
		// save it
		writeToFile(uploadedInputStream, uploadedFileLocation);

		String output = "File uploaded to : " + uploadedFileLocation;
		System.out.println(output);
		return "sdgsjsk";

	}
	

	public  String delic(String licenseText, Integer threshold ) throws Exception {
		// String fileName = "lic";
		String conceptDirectory = "data/concepts";
		Document licenseDoc = new Document(licenseText);
		System.out.println("Text : " + licenseDoc.getDocText());
		ArrayList<Concept> concepts = new ArrayList<Concept>();
		File conceptDir = new File(context.getRealPath(conceptDirectory));
		for(File conceptFile : conceptDir.listFiles()) {
			concepts.add(new Concept(conceptFile.getAbsolutePath()));
		}
		Annotator annon = new Annotator(concepts, threshold);
		ArrayList<AnnotatedSentence> annotatedSentences = annon.annotateDoc(licenseDoc);
		String jsonData = JSONConverter.getJSON(annotatedSentences);
		System.out.println(jsonData);
		return jsonData;
	}
	private void writeToFile(InputStream uploadedInputStream,
			String uploadedFileLocation) {

		try {
			OutputStream out = new FileOutputStream(new File(
					uploadedFileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(new File(uploadedFileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
	/*
	 * // This method is called if XML is request
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_XML) public String sayXMLHello() { return
	 * "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>"; }
	 * 
	 * // This method is called if HTML is request
	 * 
	 * @GET
	 * 
	 * @Produces(MediaType.TEXT_HTML) public String sayHtmlHello() { return
	 * "<html> " + "<title>" + "Hello Jersey" + "</title>" + "<body><h1>" +
	 * "Hello Jersey" + "</body></h1>" + "</html> "; }
	 */
	
}
