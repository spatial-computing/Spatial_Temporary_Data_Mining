package edu.usc.spatialcomputing.crawlers;

import java.awt.print.Book;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreateJsonRead {

	public JSONObject createJsonObect(IEEEArticle article) {
		JSONObject obj = new JSONObject();
		obj.put("abstract", article.getAbstractText());
		obj.put("arnumber", article.getArnumber());
		obj.put("doi", article.getDoi());
		obj.put("epage", article.getEpage());
		obj.put("isbn", article.getIsbn());
		obj.put("mdurl", article.getMdurl());
		obj.put("partnum", article.getPartnum());
		obj.put("pdf", article.getPdf());
		obj.put("publicationid", article.getPublicationId());
		obj.put("publisher", article.getPublisher());
		obj.put("pubtitle", article.getPubTitle());
		obj.put("pubtype", article.getPubType());
		obj.put("punumber", article.getPuNumber());
		obj.put("py", article.getPy());
		obj.put("spage",article.getSpage());
		obj.put("rank", article.getRank());
		obj.put("title", article.getTitle());
		JSONArray arr = new JSONArray();
		for(IEEEAuthor ath : article.getAuthorDetails()){
			JSONObject nest = new JSONObject();
			nest.put("name", ath.getPreferredName());
			nest.put("affiliations", ath.getAffiliations());
			arr.add(nest);
		}
		obj.put("author",arr);
		

		return obj;
	}

	

	

	

	public void writeToFile(JSONArray results, String location) {
		try {

			FileWriter file = new FileWriter(location);
			file.write(results.toJSONString());
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
}
