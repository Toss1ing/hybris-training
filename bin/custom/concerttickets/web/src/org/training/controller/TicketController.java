package org.training.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.data.TicketData;
import org.training.facades.TicketFacade;

import java.util.List;

@Controller
public class TicketController {
    private static final String TICKETS_ATTRIBUTE = "tickets";

    private static final String TICKET_LIST_PAGE = "ticketList";

    private final TicketFacade ticketFacade;

    public TicketController(TicketFacade ticketFacade) {
        this.ticketFacade = ticketFacade;
    }

    @RequestMapping(value = "/tickets")
    public String showTickets(final Model model) {
        List<TicketData> tickets = ticketFacade.getTickets();
        model.addAttribute(TICKETS_ATTRIBUTE, tickets);

        return TICKET_LIST_PAGE;
    }

}
