package org.training.facades;

import org.training.data.ProducerData;

import java.util.List;

public interface ProducerFacade {
    List<ProducerData> getProducers();

    ProducerData getProducerByCode(String code);
}
