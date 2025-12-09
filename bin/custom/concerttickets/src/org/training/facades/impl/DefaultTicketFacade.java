package org.training.facades.impl;

import org.training.data.TicketData;
import org.training.facades.TicketFacade;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import java.util.ArrayList;
import java.util.List;

public class DefaultTicketFacade implements TicketFacade {

    private final TicketService ticketService;

    public DefaultTicketFacade(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public List<TicketData> getTickets() {
        List<TicketsModel> models = ticketService.getTickets();
        List<TicketData> result = new ArrayList<>();

        for (TicketsModel model : models) {
            result.add(mapToTicketData(model));
        }

        return result;
    }

    private TicketData mapToTicketData(final TicketsModel ticket) {
        TicketData ticketData = new TicketData();
        ticketData.setId(ticket.getCode());
        ticketData.setCode(ticket.getCode());
        ticketData.setPrice(ticket.getPrice());
        ticketData.setTicketCount(ticket.getTicketCount());

        if (ticket.getTicketStatus() != null) {
            ticketData.setTicketStatus(ticket.getTicketStatus().getCode());
        }

        return ticketData;
    }
}
