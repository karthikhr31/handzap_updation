package com.karthik.handzap.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.karthik.handzap.model.ArticleEntity;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
	private static Set<ArticleEntity> articles = new HashSet<ArticleEntity>();

	@Override
	public void getArticles() {
		List<String> articlesUrlList = new ArrayList<>();
		AtomicInteger count = new AtomicInteger(0);
		List<Element> eleList = new ArrayList<>();

		try {
			Document doc = Jsoup.connect("https://www.thehindu.com/archive/").get();
			Element element = doc.getElementById("archiveWebContainer");
			Elements elements = element.getElementsByAttribute("href");
			Map<String, List<String>> dateMap = new HashMap<>();
			eleList = elements;
			eleList.stream().filter(predicate -> count.get() < 1).forEach(ele -> {
				count.incrementAndGet();
				String link = ele.absUrl("href");
				try {
					Document monthDoc = Jsoup.connect(link).get();
					monthDoc.getElementsByClass("ui-state-default").forEach(month -> {
						String daysLink = month.absUrl("href");
						String date = daysLink.substring(37, 47);
						try {
							Document articleDoc = Jsoup.connect(daysLink).get();
							articleDoc.getElementsByClass("archive-list").forEach(article -> {
								Elements arcEle = article.getElementsByTag("a");
								arcEle.forEach(arc -> {
									String arcLink = arc.absUrl("href");
									articlesUrlList.add(arcLink);
								});
							});
							// System.out.println(articlesUrlList);
							dateMap.put(date, articlesUrlList);
							// System.out.println(dateMap);
						} catch (IOException e) {
							e.printStackTrace();
						}
					});
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		count.set(0);
		Set<String> urlSet = new HashSet<>(articlesUrlList);
		List<String> uniqueUrlList = new ArrayList<>(urlSet);
		uniqueUrlList.stream().filter(predicate -> count.get() < 200).forEach(arcLink -> {
			count.incrementAndGet();
			try {
				Document articleDocument = Jsoup.connect(arcLink).get();
				String content = "content";
				Elements elementsByTag = articleDocument.getElementsByTag("meta");
				elementsByTag.stream().forEach(action -> {
					String category = "", title = "", description = "", date = "", author = "", tagsString = "";
					if (action.attr("property").equals("article:section")) {
						category = action.attr(content);
					}
					if (action.attr("name").equals("title")) {
						title = action.attr(content);
					}
					if (action.attr("name").equals("description")) {
						description = action.attr(content);
					}
					if (action.attr("name").equals("publish-date")) {
						date = action.attr(content).substring(0, 10);
					}
					if (action.attr("name").equals("twitter:creator")) {
						author = action.attr(content);
					}
					if (action.attr("name").equals("keywords")) {
						tagsString = action.attr(content);
					}
					String articledata = articleDocument.getElementsByClass("mobile-ut-container").get(0).text();
					String[] stringdata = articledata.split(",");
					String url = arcLink;
					ArticleEntity item = new ArticleEntity(date, stringdata[0], title, url, tagsString, author,
							description, category);
					articles.add(item);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	@Override
	public List<String> getAuthors() {
		List<String> availableAuthorsList = articles.stream().map(action -> action.getAuthor())
				.collect(Collectors.toList());
		Set<String> availableAuthorsSet = new HashSet<>(availableAuthorsList);
		return new ArrayList<>(availableAuthorsSet);
	}

	@Override
	public List<ArticleEntity> searchAuthors(String author) {
		List<ArticleEntity> titleList = articles.stream()
				.filter(predicate -> predicate.getAuthor().equalsIgnoreCase(author)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(titleList)) {
			return null;
		} else {
			return titleList;
		}
	}

	@Override
	public ArticleEntity searchTitle(String title) {
		List<ArticleEntity> scraperItem = articles.stream()
				.filter(predicate -> predicate.getTitle().equalsIgnoreCase(title)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(scraperItem)) {
			return null;
		} else {
			return scraperItem.get(0);
		}
	}

	@Override
	public List<ArticleEntity> searchDate(String date) {
		List<ArticleEntity> scraperItem = articles.stream()
				.filter(predicate -> predicate.getDate().equalsIgnoreCase(date)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(scraperItem)) {
			return null;
		} else {
			return scraperItem;
		}
	}

	@Override
	public Set<ArticleEntity> searchCity(String city) {
		Set<ArticleEntity> scraperItem = articles.stream()
				.filter(predicate -> predicate.getCity().equalsIgnoreCase(city)).collect(Collectors.toSet());
		if (CollectionUtils.isEmpty(scraperItem)) {
			return null;
		} else {
			return scraperItem;
		}
	}

	@Override
	public List<String> getAuthorsOnDate(String date) {
		List<String> availableAuthorsList = articles.stream()
				.filter(predicate -> predicate.getDate().equalsIgnoreCase(date)).map(action -> action.getAuthor())
				.collect(Collectors.toList());
		Set<String> availableAuthorsSet = new HashSet<>(availableAuthorsList);
		return new ArrayList<>(availableAuthorsSet);
	}

	@Override
	public List<ArticleEntity> getCategoryList(String category) {
		List<ArticleEntity> scraperItem = articles.stream()
				.filter(predicate -> predicate.getCategory().equalsIgnoreCase(category)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(scraperItem)) {
			return null;
		} else {
			return scraperItem;
		}
	}

	@Override
	public List<ArticleEntity> getTagList(String tags) {
		List<ArticleEntity> scraperItem = articles.stream()
				.filter(predicate -> predicate.getTags().equalsIgnoreCase(tags)).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(scraperItem)) {
			return null;
		} else {
			return scraperItem;
		}

	}
}
