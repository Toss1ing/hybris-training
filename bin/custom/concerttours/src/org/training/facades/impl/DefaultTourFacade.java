package org.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.product.impl.DefaultProductService;
import de.hybris.platform.variants.model.VariantProductModel;
import org.training.data.ConcertSummaryData;
import org.training.data.TourData;
import org.training.enums.ConcertType;
import org.training.facades.TourFacade;
import org.training.model.ConcertModel;

import java.util.ArrayList;
import java.util.List;

public class DefaultTourFacade implements TourFacade {
    private static final String TOUR_ID_NULL_EXCEPTION_MESSAGE = "tourId cannot be null";
    private final ProductService productService;

    public DefaultTourFacade(DefaultProductService defaultProductService) {
        this.productService = defaultProductService;
    }

    @Override
    public TourData getTourDetails(final String tourId) {
        if (tourId == null) {
            throw new IllegalArgumentException(TOUR_ID_NULL_EXCEPTION_MESSAGE);
        }

        ProductModel product = productService.getProductForCode(tourId);

        if (product == null) {
            return null;
        }

        List<ConcertSummaryData> concerts = new ArrayList<>();
        if (product.getVariants() != null) {
            for (final VariantProductModel variant : product.getVariants()) {
                if (variant instanceof ConcertModel concert) {
                    final ConcertSummaryData summary = new ConcertSummaryData();
                    summary.setId(concert.getCode());
                    summary.setDate(concert.getDate());
                    summary.setVenue(concert.getVenue());
                    summary.setType(concert.getConcertType() == ConcertType.OPENAIR ? "Outdoors" : "Indoors");
                    summary.setIsPast(concert.getIsPast());
                    summary.setDaysUntil(concert.getDaysUntil());
                    concerts.add(summary);
                }
            }
        }

        TourData tourData = new TourData();
        tourData.setId(product.getCode());
        tourData.setTourName(product.getName());
        tourData.setDescription(product.getDescription());
        tourData.setConcerts(concerts);
        return tourData;
    }
}