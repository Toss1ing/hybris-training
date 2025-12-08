package org.training.daos.impl;

import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.training.daos.TicketDAO;
import org.training.model.TicketsModel;

import java.util.List;

public class DefaultTicketDAO implements TicketDAO {

    private static final String TYPECODE = TicketsModel._TYPECODE;

    private static final String QUERY_FIND_ALL_TICKETS =
            "SELECT {t:" + TicketsModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS t}";

    private static final String QUERY_FIND_TICKETS_BY_CODE =
            "SELECT {t:" + TicketsModel.PK + "} " +
                    "FROM {" + TYPECODE + " AS t} " +
                    "WHERE {t:" + TicketsModel.CODE + "} = ?code";

    private static final String PARAM_CODE = "code";

    private final FlexibleSearchService flexibleSearchService;

    public DefaultTicketDAO(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<TicketsModel> findAllTickets() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_ALL_TICKETS);
        return flexibleSearchService.<TicketsModel>search(query).getResult();
    }

    @Override
    public List<TicketsModel> findTicketsByCode(final String code) {
        final FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_TICKETS_BY_CODE);
        query.addQueryParameter(PARAM_CODE, code);
        return flexibleSearchService.<TicketsModel>search(query).getResult();
    }
}
