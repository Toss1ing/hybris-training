package org.training.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.training.daos.TicketDAO;
import org.training.model.TicketsModel;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultTicketServiceUnitTest {

    private DefaultTicketService ticketService;
    private TicketDAO ticketDAO;
    private TicketsModel ticketModel;

    private static final String TICKET_CODE = "TK-01";

    @Before
    public void setUp() {
        ticketDAO = mock(TicketDAO.class);
        ticketService = new DefaultTicketService(ticketDAO);

        ticketModel = new TicketsModel();
        ticketModel.setCode(TICKET_CODE);
        ticketModel.setTicketCount(10);
        ticketModel.setPrice(10.0);
    }

    @Test
    public void testGetAllTickets() {
        List<TicketsModel> tickets = Arrays.asList(ticketModel);
        when(ticketDAO.findAllTickets()).thenReturn(tickets);

        List<TicketsModel> result = ticketService.getTickets();

        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", ticketModel, result.get(0));
    }
}
