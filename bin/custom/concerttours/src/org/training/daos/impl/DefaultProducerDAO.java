package org.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.daos.ProducerDAO;
import org.training.model.ProducerModel;

import java.util.List;

public class DefaultProducerDAO implements ProducerDAO {

    private static final String TYPECODE = ProducerModel._TYPECODE;

    private static final String QUERY_FIND_ALL_PRODUCERS =
            "SELECT {p:" + ProducerModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS p} ";

    private static final String QUERY_FIND_PRODUCER_BY_CODE =
            "SELECT {p:" + ProducerModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS p} " +
                    "WHERE {p:" + ProducerModel.CODE + "} = ?code ";

    private static final String CODE_ATTRIBUTE = "code";

    private final FlexibleSearchService flexibleSearchService;

    public DefaultProducerDAO(FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<ProducerModel> findAllProducers() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_ALL_PRODUCERS);

        return flexibleSearchService.<ProducerModel>search(query).getResult();
    }

    @Override
    public ProducerModel findProducerByCode(String code) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_PRODUCER_BY_CODE);
        query.addQueryParameter(CODE_ATTRIBUTE, code);

        return flexibleSearchService.searchUnique(query);
    }
    
}
