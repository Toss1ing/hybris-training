package org.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import org.training.data.BandData;
import org.training.data.ProducerData;
import org.training.model.ProducerModel;

import java.util.ArrayList;
import java.util.List;

public class ProducerBasePopulator implements Populator<ProducerModel, ProducerData> {

    @Override
    public void populate(ProducerModel producerModel, ProducerData producerData) throws ConversionException {
        producerData.setId(producerModel.getCode());
        producerData.setName(producerModel.getName());
        producerData.setExperienceYears(producerModel.getExperienceYears());
        producerData.setCountry(producerModel.getCountry());


        List<BandData> bandsList = new ArrayList<>();
        if (producerModel.getBands() != null) {
            bandsList = producerModel.getBands().stream().map(band -> {
                BandData b = new BandData();
                b.setId(band.getCode());
                b.setName(band.getName());
                return b;
            }).toList();
        }

        producerData.setBands(bandsList);
    }
}
