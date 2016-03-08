package edu.usc.spatialcomputing.crawlers;

import java.util.ArrayList;
import java.util.List;

public class IEEEArticle {
	public IEEEArticle() {
		this.authorDetails = new ArrayList<IEEEAuthor>();
	}
	private List<IEEEAuthor> authorDetails;
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getControlledTerms() {
		return controlledTerms;
	}
	public void setControlledTerms(List<String> controlledTerms) {
		this.controlledTerms = controlledTerms;
	}
	public List<String> getThesaurusTerms() {
		return thesaurusTerms;
	}
	public void setThesaurusTerms(List<String> thesaurusTerms) {
		this.thesaurusTerms = thesaurusTerms;
	}
	public String getPubTitle() {
		return pubTitle;
	}
	public void setPubTitle(String pubTitle) {
		this.pubTitle = pubTitle;
	}
	public String getPuNumber() {
		return puNumber;
	}
	public void setPuNumber(String puNumber) {
		this.puNumber = puNumber;
	}
	public String getPubType() {
		return pubType;
	}
	public void setPubType(String pubType) {
		this.pubType = pubType;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getSpage() {
		return spage;
	}
	public void setSpage(String spage) {
		this.spage = spage;
	}
	public String getEpage() {
		return epage;
	}
	public void setEpage(String epage) {
		this.epage = epage;
	}
	public String getAbstractText() {
		return abstractText;
	}
	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getArnumber() {
		return arnumber;
	}
	public void setArnumber(String arnumber) {
		this.arnumber = arnumber;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getPublicationId() {
		return publicationId;
	}
	public void setPublicationId(String publicationId) {
		this.publicationId = publicationId;
	}
	public String getPartnum() {
		return partnum;
	}
	public void setPartnum(String partnum) {
		this.partnum = partnum;
	}
	public String getMdurl() {
		return mdurl;
	}
	public void setMdurl(String mdurl) {
		this.mdurl = mdurl;
	}
	public String getPdf() {
		return pdf;
	}
	public void setPdf(String pdf) {
		this.pdf = pdf;
	}
	public List<IEEEAuthor> getAuthorDetails() {
		return authorDetails;
	}
	public void addAuthorDetails(IEEEAuthor authorDetails) {
		this.authorDetails.add(authorDetails);
	}
	String rank;
	String title;
	List<String> controlledTerms;
	List<String> thesaurusTerms;
	String pubTitle;
	String puNumber;
	String pubType;
	String publisher;
	String py;
	String spage;
	String epage;
	String abstractText;
	String isbn;
	String arnumber;
	String doi;
	String publicationId;
	String partnum;
	String mdurl;
	String pdf;
}
