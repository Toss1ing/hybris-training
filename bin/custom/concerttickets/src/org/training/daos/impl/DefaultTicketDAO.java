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

    private final FlexibleSearchService flexibleSearchService;

    public DefaultTicketDAO(final FlexibleSearchService flexibleSearchService) {
        this.flexibleSearchService = flexibleSearchService;
    }

    @Override
    public List<TicketsModel> findAllTickets() {
        FlexibleSearchQuery query = new FlexibleSearchQuery(QUERY_FIND_ALL_TICKETS);
        return flexibleSearchService.<TicketsModel>search(query).getResult();
    }
}
