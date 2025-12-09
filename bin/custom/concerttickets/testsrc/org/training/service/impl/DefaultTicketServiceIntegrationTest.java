package org.training.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.enums.TicketStatus;
import org.training.model.TicketsModel;
import org.training.service.TicketService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultTicketServiceIntegrationTest extends ServicelayerTest {

    @Resource
    private TicketService ticketService;

    @Resource
    private ModelService modelService;

    private TicketsModel ticketModel;

    private static final String TICKET_CODE = "TK-1";
    private static final Double TICKET_PRICE = 55.50;
    private static final Integer TICKET_COUNT = 5;

    @Before
    public void setUp() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException ignored) {
        }


        ticketModel = modelService.create(TicketsModel.class);
        ticketModel.setCode(TICKET_CODE);
        ticketModel.setPrice(TICKET_PRICE);
        ticketModel.setTicketCount(TICKET_COUNT);
        ticketModel.setTicketStatus(TicketStatus.REGULAR);
    }

    @Test
    public void testTicketService() {
        List<TicketsModel> tickets = ticketService.getTickets();
        int size = tickets.size();

        modelService.save(ticketModel);

        tickets = ticketService.getTickets();
        assertEquals(size + 1, tickets.size());

        assertEquals(
                "Unexpected ticket found",
                ticketModel,
                tickets.get(tickets.size() - 1)
        );
    }
}
