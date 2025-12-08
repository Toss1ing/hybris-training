package org.training.service.impl;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.training.daos.TicketDAO;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import java.util.List;

public class DefaultTicketService implements TicketService {

    private static final String MSG_TICKET_NOT_FOUND =
            "Ticket with code '%s' not found!";

    private static final String MSG_TICKET_NOT_UNIQUE =
            "Ticket code '%s' is not unique, %d tickets found!";

    private final TicketDAO ticketDAO;

    public DefaultTicketService(final TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    @Override
    public List<TicketsModel> getTickets() {
        return ticketDAO.findAllTickets();
    }

    @Override
    public TicketsModel getTicketsByCode(final String code)
            throws AmbiguousIdentifierException, UnknownIdentifierException {

        List<TicketsModel> result = ticketDAO.findTicketsByCode(code);

        if (result.isEmpty()) {
            throw new UnknownIdentifierException(
                    String.format(MSG_TICKET_NOT_FOUND, code));
        } else if (result.size() > 1) {
            throw new AmbiguousIdentifierException(
                    String.format(MSG_TICKET_NOT_UNIQUE, code, result.size()));
        }

        return result.get(0);
    }
}
