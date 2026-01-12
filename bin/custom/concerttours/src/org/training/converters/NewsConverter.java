package org.training.converters;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.training.data.NewsData;
import org.training.model.NewsModel;

public class NewsConverter extends AbstractPopulatingConverter<NewsModel, NewsData> {

    public NewsConverter() {
        super();
        setTargetClass(NewsData.class);
    }

}