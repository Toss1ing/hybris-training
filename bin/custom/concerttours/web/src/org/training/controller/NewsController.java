package org.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.data.NewsData;
import org.training.facades.NewsFacade;

import java.util.List;

@Controller
public class NewsController {
    private static final String NEWS_ATTRIBUTE_NAME = "newsList";

    private static final String NEWS_LIST_PAGE = "newsList";

    private final NewsFacade newsFacade;

    public NewsController(final NewsFacade newsFacade) {
        this.newsFacade = newsFacade;
    }

    @RequestMapping(value = "/news")
    public String getNews(Model model) {
        List<NewsData> newsData = newsFacade.getNews();
        model.addAttribute(NEWS_ATTRIBUTE_NAME, newsData);

        return NEWS_LIST_PAGE;
    }
}
