package org.training.service.impl;

import org.training.daos.ProducerDAO;
import org.training.model.ProducerModel;
import org.training.service.ProducerService;

import java.util.List;

public class DefaultProducerService implements ProducerService {

    private final ProducerDAO producerDAO;

    public DefaultProducerService(ProducerDAO producerDAO) {
        this.producerDAO = producerDAO;
    }

    @Override
    public List<ProducerModel> getProducers() {
        return producerDAO.findAllProducers();
    }

    @Override
    public ProducerModel getProducerByCode(String code) {
        return producerDAO.findProducerByCode(code);
    }
}
