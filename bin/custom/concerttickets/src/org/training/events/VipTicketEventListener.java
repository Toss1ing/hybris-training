package org.training.events;

import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.training.model.TicketsModel;
import org.training.model.VipTicketEventModel;

import java.util.Date;

public class VipTicketEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private final static String TICKET_STATUS_VIP = "VIP";

    private final ModelService modelService;

    public VipTicketEventListener(ModelService modelService) {
        this.modelService = modelService;
    }

    @Override
    protected void onEvent(AfterItemCreationEvent event) {
        if (event != null && event.getSource() != null) {
            Object source = modelService.get(event.getSource());
            if (source instanceof TicketsModel ticket && TICKET_STATUS_VIP.equals(ticket.getTicketStatus().getCode())) {
                VipTicketEventModel vipEvent = modelService.create(VipTicketEventModel.class);
                vipEvent.setTicketCode(ticket.getCode());
                vipEvent.setConcertCode(ticket.getConcert().getCode());
                vipEvent.setEventTime(new Date());
                modelService.save(vipEvent);
            }
        }
    }
}
