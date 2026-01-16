package org.training.service;

import org.training.model.NewsModel;

import java.util.List;

public interface NewsService {
    List<NewsModel> getNews();
}
