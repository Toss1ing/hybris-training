package org.training.daos.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.daos.TicketDAO;
import org.training.enums.TicketStatus;
import org.training.model.TicketsModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@IntegrationTest
public class DefaultTicketDAOIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private TicketDAO ticketDAO;

    @Resource
    private ModelService modelService;

    private static final String TICKET_CODE = "TICKET-001";
    private static final Double TICKET_PRICE = 99.99;
    private static final Integer TICKET_COUNT = 100;

    @Before
    public void setUp() throws Exception {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException ignored) {
        }
    }

    @Test
    public void ticketDAOTest() {
        List<TicketsModel> allTickets = ticketDAO.findAllTickets();
        int size = allTickets.size();

        TicketsModel ticketModel = modelService.create(TicketsModel.class);
        ticketModel.setCode(TICKET_CODE);
        ticketModel.setPrice(TICKET_PRICE);
        ticketModel.setTicketCount(TICKET_COUNT);
        ticketModel.setTicketStatus(TicketStatus.REGULAR);

        modelService.save(ticketModel);

        allTickets = ticketDAO.findAllTickets();
        Assert.assertEquals(size + 1, allTickets.size());
        Assert.assertTrue("Ticket not found", allTickets.contains(ticketModel));
    }

    @After
    public void tearDown() {
    }
}
