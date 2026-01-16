package org.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.daos.NewsDao;
import org.training.model.NewsModel;

import java.util.List;

public class DefaultNewsDao implements NewsDao {

    private static final String TYPECODE = NewsModel._TYPECODE;

    private static final String QUERY_FIND_ALL_NEWS =
            "SELECT {n:" + NewsModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS n} ";

    private final FlexibleSearchService flexibleSearchService;

    public DefaultNewsDao(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<NewsModel> findAllNews() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_ALL_NEWS);
        return flexibleSearchService.<NewsModel>search(query).getResult();
    }

}
