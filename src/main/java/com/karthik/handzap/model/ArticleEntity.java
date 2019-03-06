package com.karthik.handzap.model;

public class ArticleEntity {
	private String date;
	private String city;
	private String title;
	private String url;
	private String tags;
	private String author;
	private String description;
	private String category;

	public ArticleEntity(String date, String city, String title, String url, String tags, String author,
			String description, String category) {
		super();
		this.date = date;
		this.city = city;
		this.title = title;
		this.url = url;
		this.tags = tags;
		this.author = author;
		this.description = description;
		this.category = category;

	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public ArticleEntity() {
		super();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
