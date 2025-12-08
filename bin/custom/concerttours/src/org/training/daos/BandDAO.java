package org.training.daos;

import org.training.model.BandModel;

import java.util.List;

public interface BandDAO {
    List<BandModel> findAllBands();

    List<BandModel> findBandByCode(String code);
}
