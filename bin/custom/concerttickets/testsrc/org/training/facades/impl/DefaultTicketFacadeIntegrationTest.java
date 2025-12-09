package org.training.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.data.TicketData;
import org.training.model.TicketsModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultTicketFacadeIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private DefaultTicketFacade defaultTicketFacade;

    @Resource
    private ModelService modelService;

    private TicketsModel ticketModel;

    private static final String TICKET_CODE = "TICKET-101";
    private static final Double TICKET_PRICE = 99.99;
    private static final Integer TICKET_COUNT = 50;

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
        modelService.save(ticketModel);
    }

    @Test
    public void testGetTickets() {
        List<TicketData> tickets = defaultTicketFacade.getTickets();
        assertNotNull(tickets);
        assertEquals(1, tickets.size());
        TicketData data = tickets.get(0);
        assertEquals(TICKET_CODE, data.getId());
        assertEquals(TICKET_PRICE, data.getPrice());
        assertEquals(TICKET_COUNT, data.getTicketCount());
    }

    @After
    public void teardown() {
    }
}
