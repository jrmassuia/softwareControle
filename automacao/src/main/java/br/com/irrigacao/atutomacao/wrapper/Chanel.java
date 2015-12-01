package br.com.irrigacao.atutomacao.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import br.com.irrigacao.automacao.entity.Tempo;

@XStreamAlias("chanel")
public class Chanel {

	@XStreamAlias("title")
	private String title;

	@XStreamAlias("link")
	private String link;

	@XStreamAlias("description")
	private String description;

	@XStreamAlias("lastBuildDate")
	private String lastBuildDate;

	@XStreamAlias("generator")
	private String generator;

	@XStreamImplicit
	private List<Item> item;

	private List<Tempo> previsaoTempo;

	public List<Tempo> getPrevisaoTempo() {
		if (this.previsaoTempo == null) {
			this.previsaoTempo = new ArrayList<Tempo>();
		}
		return previsaoTempo;
	}

	public void setPrevisaoTempo(List<Tempo> previsaoTempo) {
		this.previsaoTempo = previsaoTempo;
	}

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

	public String getLastBuildDate() {
		return lastBuildDate;
	}

	public void setLastBuildDate(String lastBuildDate) {
		this.lastBuildDate = lastBuildDate;
	}

	public String getGenerator() {
		return generator;
	}

	public void setGenerator(String generator) {
		this.generator = generator;
	}

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

}
