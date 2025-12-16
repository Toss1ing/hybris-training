package org.training.attributeHandler;

import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.training.model.ConcertModel;

import java.time.Duration;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class ConcertDaysUntilAttributeHandler extends AbstractDynamicAttributeHandler<Long, ConcertModel> {

    @Override
    public Long get(ConcertModel model) {
        if (model.getDate() == null) {
            return null;
        }
        ZonedDateTime concertDate = model.getDate().toInstant().atZone(ZoneId.systemDefault());
        ZonedDateTime now = ZonedDateTime.now();
        if (concertDate.isBefore(now)) {
            return 0L;
        }
        return Duration.between(now, concertDate).toDays();
    }

}