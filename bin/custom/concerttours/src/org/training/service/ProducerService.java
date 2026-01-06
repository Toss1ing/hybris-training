package org.training.service;

import org.training.model.ProducerModel;

import java.util.List;

public interface ProducerService {
    List<ProducerModel> getProducers();

    ProducerModel getProducerByCode(String code);
}
