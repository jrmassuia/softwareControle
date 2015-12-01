package br.com.irrigacao.atutomacao.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("item")
public class Item {

	@XStreamAlias("title")
	private String title;

	@XStreamAlias("link")
	private String link;

	@XStreamAlias("description")
	private String description;

	@XStreamAlias("author")
	private String author;

	@XStreamAlias("pubDate")
	private String pubDate;

	@XStreamAlias("guid")
	private String guid;

	private List<String> descriptions;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public List<String> getDescriptions() {
		if (this.descriptions == null) {
			this.descriptions = new ArrayList<String>();
		}
		return descriptions;
	}

	public void setDescriptions(List<String> descriptions) {
		this.descriptions = descriptions;
	}
}
