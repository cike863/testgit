package com.xsbweb.vo.extend;

import java.util.ArrayList;
import java.util.List;

import com.xsbweb.vo.News;

public class NewsVO {

	private News news = new News();
	
	private List<News> newsList = new ArrayList<News>();

	private Integer prcFlag;
	
	public Integer getPrcFlag() {
		return prcFlag;
	}

	public void setPrcFlag(Integer prcFlag) {
		this.prcFlag = prcFlag;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
}
