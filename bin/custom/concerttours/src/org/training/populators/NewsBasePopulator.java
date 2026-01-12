package org.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.data.NewsData;
import org.training.model.NewsModel;

public class NewsBasePopulator implements Populator<NewsModel, NewsData> {

    @Override
    public void populate(NewsModel newsModel, NewsData newsData) throws ConversionException {
        newsData.setId(newsModel.getCode());
        newsData.setContent(newsModel.getContent());
        newsData.setDate(newsModel.getDate());
        newsData.setHeadline(newsModel.getHeadline());
    }

}
