package org.training.facades;

import org.training.data.NewsData;

import java.util.List;

public interface NewsFacade {
    List<NewsData> getNews();
}
