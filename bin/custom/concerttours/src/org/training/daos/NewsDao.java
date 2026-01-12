package org.training.daos;

import org.training.model.NewsModel;

import java.util.List;

public interface NewsDao {
    List<NewsModel> findAllNews();
}
