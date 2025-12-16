package org.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.daos.TokenDAO;
import org.training.model.TokenItemModel;

import java.util.List;

public class DefaultTokenDAO implements TokenDAO {

    private static final String TYPECODE = TokenItemModel._TYPECODE;

    private static final String QUERY_FIND_TOKEN =
            "SELECT {t:" + TokenItemModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS t}";

    private final FlexibleSearchService flexibleSearchService;

    public DefaultTokenDAO(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public TokenItemModel findTokenItem() {
        FlexibleSearchQuery query =
                new FlexibleSearchQuery(QUERY_FIND_TOKEN);

        List<TokenItemModel> result =
                flexibleSearchService.<TokenItemModel>search(query).getResult();

        return result.isEmpty() ? null : result.get(0);
    }
}
