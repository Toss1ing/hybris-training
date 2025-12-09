package org.training.facades.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.data.TicketData;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultTicketFacadeUnitTest {

    private DefaultTicketFacade ticketFacade;
    private TicketService ticketService;

    private static final String TICKET_CODE = "TICKET-101";
    private static final Double TICKET_PRICE = 99.99;
    private static final Integer TICKET_COUNT = 50;

    private TicketsModel ticketModel;

    @Before
    public void setUp() {
        ticketService = mock(TicketService.class);
        ticketFacade = new DefaultTicketFacade(ticketService);

        ticketModel = new TicketsModel();
        ticketModel.setCode(TICKET_CODE);
        ticketModel.setPrice(TICKET_PRICE);
        ticketModel.setTicketCount(TICKET_COUNT);
    }

    @Test
    public void testGetTickets() {
        when(ticketService.getTickets()).thenReturn(Collections.singletonList(ticketModel));
        List<TicketData> dataList = ticketFacade.getTickets();
        Assert.assertEquals(1, dataList.size());
        TicketData data = dataList.get(0);
        Assert.assertEquals(TICKET_CODE, data.getId());
        Assert.assertEquals(TICKET_PRICE, data.getPrice());
        Assert.assertEquals(TICKET_COUNT, data.getTicketCount());
    }
}
