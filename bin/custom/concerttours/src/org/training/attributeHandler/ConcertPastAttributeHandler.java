package org.training.attributeHandler;

import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import org.training.model.ConcertModel;

import java.util.Date;

public class ConcertPastAttributeHandler
        implements DynamicAttributeHandler<Boolean, ConcertModel> {

    @Override
    public Boolean get(ConcertModel concert) {
        Date date = concert.getDate();
        if (date == null) {
            return false;
        }
        return date.before(new Date());
    }

    @Override
    public void set(ConcertModel model, Boolean value) {
        throw new UnsupportedOperationException("past is read-only");
    }
}
