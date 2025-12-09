package org.training.facades;

import org.training.data.TicketData;

import java.util.List;

public interface TicketFacade {
    List<TicketData> getTickets();
}
