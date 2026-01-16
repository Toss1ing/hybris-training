package org.training.service.impl;

import org.training.daos.NewsDao;
import org.training.model.NewsModel;
import org.training.service.NewsService;

import java.util.List;

public class DefaultNewsService implements NewsService {

    private final NewsDao newsDao;

    public DefaultNewsService(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Override
    public List<NewsModel> getNews() {
        return newsDao.findAllNews();
    }

}
