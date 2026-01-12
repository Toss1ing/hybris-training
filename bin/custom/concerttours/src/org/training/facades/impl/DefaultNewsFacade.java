package org.training.facades.impl;

import org.training.converters.NewsConverter;
import org.training.data.NewsData;
import org.training.facades.NewsFacade;
import org.training.model.NewsModel;
import org.training.service.NewsService;

import java.util.List;

public class DefaultNewsFacade implements NewsFacade {

    private final NewsService newsService;
    private final NewsConverter newsConverter;

    public DefaultNewsFacade(NewsService newsService, NewsConverter newsConverter) {
        this.newsService = newsService;
        this.newsConverter = newsConverter;
    }

    @Override
    public List<NewsData> getNews() {
        List<NewsModel> newsModels = newsService.getNews();
        return newsModels.stream()
                .map(newsConverter::convert)
                .toList();
    }
}
