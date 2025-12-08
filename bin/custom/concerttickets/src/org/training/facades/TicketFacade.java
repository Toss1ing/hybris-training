package org.training.facades;

import org.training.data.TicketData;

import java.util.List;

public interface TicketFacade {
    TicketData getTicket(String code);

    List<TicketData> getTickets();
}
