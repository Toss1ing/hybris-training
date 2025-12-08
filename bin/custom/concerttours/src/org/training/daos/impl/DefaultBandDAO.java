package org.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.daos.BandDAO;
import org.training.model.BandModel;

import java.util.List;

public class DefaultBandDAO implements BandDAO {

    private static final String TYPECODE = BandModel._TYPECODE;

    private static final String QUERY_FIND_ALL_BANDS =
            "SELECT {p:" + BandModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS p} ";

    private static final String QUERY_FIND_BANDS_BY_CODE =
            "SELECT {p:" + BandModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS p} " +
                    "WHERE {p:" + BandModel.CODE + "} = ?code ";

    private static final String PARAM_CODE = "code";

    private final FlexibleSearchService flexibleSearchService;

    public DefaultBandDAO(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<BandModel> findAllBands() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_ALL_BANDS);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }

    @Override
    public List<BandModel> findBandByCode(final String code) {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_BANDS_BY_CODE);
        query.addQueryParameter(PARAM_CODE, code);
        return flexibleSearchService.<BandModel>search(query).getResult();
    }
}
