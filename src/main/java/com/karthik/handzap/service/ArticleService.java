package com.karthik.handzap.service;

import java.util.List;
import java.util.Set;

import com.karthik.handzap.model.ArticleEntity;


public interface ArticleService {

	void getArticles();

	List<String> getAuthors();

	List<ArticleEntity> searchAuthors(String author);

	ArticleEntity searchTitle(String title);

	List<ArticleEntity> searchDate(String date);

	Set<ArticleEntity> searchCity(String city);
	
	List<String> getAuthorsOnDate(String date);

	List<ArticleEntity> getCategoryList(String category);

	List<ArticleEntity> getTagList(String tags);

}
