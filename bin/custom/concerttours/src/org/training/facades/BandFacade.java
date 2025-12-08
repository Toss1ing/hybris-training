package org.training.facades;

import org.training.data.BandData;

import java.util.List;

public interface BandFacade {
    BandData getBand(String code);

    List<BandData> getBands();
}
