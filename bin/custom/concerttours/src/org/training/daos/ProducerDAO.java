package org.training.daos;

import org.training.model.ProducerModel;

import java.util.List;

public interface ProducerDAO {
    List<ProducerModel> findAllProducers();

    ProducerModel findProducerByCode(String code);
}
