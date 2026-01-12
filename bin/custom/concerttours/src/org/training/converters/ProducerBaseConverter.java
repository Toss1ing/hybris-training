package org.training.converters;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.training.data.ProducerData;
import org.training.model.ProducerModel;

public class ProducerBaseConverter extends AbstractPopulatingConverter<ProducerModel, ProducerData> {

    public ProducerBaseConverter() {
        super();
        setTargetClass(ProducerData.class);
    }

}
