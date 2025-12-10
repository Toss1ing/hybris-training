package org.training.facades.impl;

import org.training.data.TicketData;
import org.training.facades.TicketFacade;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultTicketFacade implements TicketFacade {

    private final TicketService ticketService;

    public DefaultTicketFacade(final TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public List<TicketData> getTickets() {
        List<TicketsModel> models = ticketService.getTickets();
        return models.stream()
                .map(this::mapToTicketData)
                .collect(Collectors.toList());
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
