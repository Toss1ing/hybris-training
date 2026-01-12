package org.training.facades.impl;

import org.training.converters.ProducerBaseConverter;
import org.training.data.ProducerData;
import org.training.facades.ProducerFacade;
import org.training.model.ProducerModel;
import org.training.service.ProducerService;

import java.util.List;

public class DefaultProducerFacade implements ProducerFacade {

    private static final String CODE_NOT_FOUND_MESSAGE = "Code must not bu null";

    private final ProducerService producerService;
    private final ProducerBaseConverter producerBaseConverter;

    public DefaultProducerFacade(
            ProducerService producerService,
            ProducerBaseConverter producerBaseConverter
    ) {
        this.producerService = producerService;
        this.producerBaseConverter = producerBaseConverter;
    }

    @Override
    public List<ProducerData> getProducers() {
        List<ProducerModel> bandModels = producerService.getProducers();
        return bandModels.stream()
                .map(producerBaseConverter::convert)
                .toList();
    }

    @Override
    public ProducerData getProducerByCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException(CODE_NOT_FOUND_MESSAGE);
        }

        ProducerModel producerModel = producerService.getProducerByCode(code);

        return producerBaseConverter.convert(producerModel);
    }
}
