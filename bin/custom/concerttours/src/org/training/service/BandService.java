package org.training.service;

import org.training.model.BandModel;

import java.util.List;

public interface BandService {
    List<BandModel> getBands();

    BandModel getBandForCode(String code);
}
