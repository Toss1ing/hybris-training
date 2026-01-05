package org.training.converters;

import de.hybris.platform.converters.impl.AbstractPopulatingConverter;
import org.training.data.BandData;
import org.training.model.BandModel;

public class BandConverter extends AbstractPopulatingConverter<BandModel, BandData> {

    public BandConverter() {
        super();
        setTargetClass(BandData.class);
    }

}
