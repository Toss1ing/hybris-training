package org.training.daos;

import org.training.model.TicketsModel;

import java.util.List;

public interface TicketDAO {
    List<TicketsModel> findAllTickets();
}
