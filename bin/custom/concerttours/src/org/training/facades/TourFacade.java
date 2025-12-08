package org.training.facades;

import org.training.data.TourData;

public interface TourFacade {
    TourData getTourDetails(final String tourId);
}
