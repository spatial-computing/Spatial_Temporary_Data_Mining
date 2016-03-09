package edu.usc.spatialcomputing.crawlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IeeeCrawler {

	private String URL = "http://ieeexplore.ieee.org/gateway/ipsSearch.jsp?";

	private static Document retrieveDocumentFromUrl(String url) throws IOException, InterruptedException {
		Thread.currentThread().sleep(1000);
		Document doc = null;
		System.out.println(url);
		doc = Jsoup.connect(url).timeout(10 * 1000).get();
		return doc;
	}

	private void apiSearch(String folder, Map<String, String> parameters) throws IOException, InterruptedException {

		String searchUri = URL + mergeParameter(parameters);

		// PrintWriter writer = new PrintWriter(folder, "UTF-8");

		int count = 1;
		// while(count <= 52){
		Document webPage = retrieveDocumentFromUrl(searchUri);
		List<IEEEArticle> articles = new ArrayList<IEEEArticle>();
		String records = webPage.select("totalfound").get(0).text();
		int totalRecords = Integer.valueOf(records);
		int start = 1;
		while (start <= totalRecords) {
			start = articles.size()+1;
			parameters.put("rs", String.valueOf(start));
			parameters.put("hc", "1000");
			try{
			webPage = retrieveDocumentFromUrl(URL + mergeParameter(parameters));
			}
			catch(Exception e){
				break;
			}
			Elements documents = webPage.select("document");
			for (Element docu : documents) {
				IEEEArticle art = new IEEEArticle();
				art.setAbstractText(docu.select("abstract").text());
				art.setTitle(docu.select("title").text());
				art.setArnumber(docu.select("arnumber").text());
				art.setDoi(docu.select("doi").text());
				art.setEpage(docu.select("epage").text());
				art.setIsbn(docu.select("isbn").text());
				art.setMdurl(docu.select("mdurl").text());
				art.setPartnum(docu.select("partnum").text());
				art.setPdf(docu.select("pdf").text());
				art.setPublicationId(docu.select("publicationid").text());
				art.setPublisher(docu.select("publisher").text());
				art.setPubTitle(docu.select("pubtitle").text());
				art.setPubType(docu.select("pubtype").text());
				art.setPy(docu.select("py").text());
				art.setRank(docu.select("rank").text());
				art.setSpage(docu.select("spage").text());
				art.setPuNumber(docu.select("punumber").text());
				// getAuthor details
				if(art.getMdurl() == null || art.getMdurl().trim().length() == 0){
					continue;
				}
				try {
				addAuthorDetails(art);
				articles.add(art);
				}
				catch (Exception e){
					continue;
				}
			}
			// for(Element elem : contents){
			// System.out.println(elem);
			// }
			// }
			// writer.close();
		}
		CreateJsonRead jsRead = new CreateJsonRead();
		JSONArray arr = new JSONArray();
		for(IEEEArticle arti : articles){
		JSONObject jsob = new JSONObject();
		jsob.put(arti.getMdurl(),jsRead.createJsonObect(arti));
		arr.add(jsob);
		}
		
		jsRead.writeToFile(arr, folder);
	}

	private void addAuthorDetails(IEEEArticle art) throws IOException, InterruptedException {
		
		Document webPage = retrieveDocumentFromUrl(art.getMdurl().replace("articleDetails.jsp", "abstractAuthors.jsp"));
		
	    Elements authors = webPage.select("div.art-authors");
	    for(Element elem : authors){
	    	Elements ahref = elem.select("a[href=#]");
	    	for(Element author : ahref){
	    		IEEEAuthor ath = new IEEEAuthor();
	    		ath.setPreferredName(author.select("span[id=preferredName]").get(0).attr("class"));
	    		ath.setAffiliations(author.select("span[id=authorAffiliations]").get(0).attr("class"));
	    		art.addAuthorDetails(ath);	    		
	    	}
	    }
	}

	private String mergeParameter(Map<String, String> parameters) {
		StringBuilder param = new StringBuilder();
		for (Map.Entry<String, String> par : parameters.entrySet()) {
			param.append(par.getKey() + "=" + par.getValue() + "&");
		}
		return param.toString();
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		IeeeCrawler crawler = new IeeeCrawler();
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		// set parameters
		parameters.put("querytext", "fuel cells".replaceAll(" ", "%20"));
		parameters.put("pys", "2011"); // start of publication year
		parameters.put("pye", "2012"); // end of publication year
		parameters.put("pu", "IEEE"); // publication type "IEEE/AIP/IET/AVS/IBM"
		parameters.put("ctype", "Conferences"); // content type
												// "Conferences/Journals/Books/Early
												// Access/Standards/Educational
												// Courses"
		// parameters.put("sortfield", "pys"); // Sort field
		crawler.apiSearch("result/metadata/ts_11_12.json", parameters);

	}

}
