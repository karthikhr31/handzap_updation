package com.karthik.handzap.controller;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.karthik.handzap.model.ArticleEntity;
import com.karthik.handzap.service.ArticleService;

@Controller
@RequestMapping("/handzap")
public class ArticleController {
	private final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	@Resource(name = "articleService")
	private ArticleService service;

	@PostConstruct
	public void getAllarticles(){
		service.getArticles();
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		logger.debug("index()");
		
		model.addAttribute("attr1", new ArticleEntity());
		model.addAttribute("attr2", new ArticleEntity());
		model.addAttribute("attr3", new ArticleEntity());
		model.addAttribute("attr4", new ArticleEntity());
		model.addAttribute("attr5", new ArticleEntity());
		model.addAttribute("attr6", new ArticleEntity());
		model.addAttribute("attr7", new ArticleEntity());
		model.addAttribute("attr8", new ArticleEntity());
		return "frontpage";
	}

	@RequestMapping(value = "/getAuthors", method = RequestMethod.GET)
	public String getAuthors(Model model) {
		model.addAttribute("authorsList", service.getAuthors());
		return "all-authors";
	}

	@RequestMapping(value = "/searchAuthors", method = RequestMethod.POST)
	public String searchAuthors(@ModelAttribute("attr2") ArticleEntity item, Model model) {
		model.addAttribute("titleList", service.searchAuthors(item.getAuthor()));
		return "title";
	}

	@RequestMapping(value = "/searchTitle", method = RequestMethod.POST)
	public String searchTitle(@ModelAttribute("attr3") ArticleEntity item, Model model) {
		model.addAttribute("total", service.searchTitle(item.getTitle()));
		return "final";
	}

	@RequestMapping(value = "/searchDate", method = RequestMethod.POST)
	public String searchDate(@ModelAttribute("attr4") ArticleEntity item, Model model) {
		model.addAttribute("dateList", service.searchDate(item.getDate()));
		return "dateList";
	}

	@RequestMapping(value = "/searchCity", method = RequestMethod.POST)
	public String searchCity(@ModelAttribute("attr5") ArticleEntity item, Model model) {
		model.addAttribute("cityList", service.searchCity(item.getCity()));
		return "city";
	}

	@RequestMapping(value = "/getAuthorsOnDate", method = RequestMethod.POST)
	public String getAuthorsOnDate(@ModelAttribute("attr6") ArticleEntity item, Model model) {
		model.addAttribute("authorsList", service.getAuthorsOnDate(item.getDate()));
		return "authorDate";
	}

	@RequestMapping(value = "/categoryList", method = RequestMethod.POST)
	public String categoryList(@ModelAttribute("attr7") ArticleEntity item, Model model) {
		model.addAttribute("categoryList", service.getCategoryList(item.getCategory()));
		return "category";
	}

	@RequestMapping(value = "/tagList", method = RequestMethod.POST)
	public String tagList(@ModelAttribute("attr8") ArticleEntity item, Model model) {
		model.addAttribute("tagList", service.getTagList(item.getTags()));
		return "tags";
	}
}
