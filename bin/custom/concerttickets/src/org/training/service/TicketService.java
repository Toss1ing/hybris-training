package org.training.service;

import org.training.model.TicketsModel;

import java.util.List;

public interface TicketService {
    List<TicketsModel> getTickets();

    TicketsModel getTicketsByCode(String code);
}
