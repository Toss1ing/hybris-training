package org.training.service.impl;

import org.training.daos.TicketDAO;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import java.util.List;

public class DefaultTicketService implements TicketService {

    private final TicketDAO ticketDAO;

    public DefaultTicketService(final TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public List<TicketsModel> getTickets() {
        return ticketDAO.findAllTickets();
    }
}
