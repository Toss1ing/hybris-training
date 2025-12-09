package org.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.data.TourData;
import org.training.facades.TourFacade;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Controller
public class TourController {
    private static final String CATALOG_ID = "concertToursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    private static final String TOUR_ATTRIBUTE = "tour";

    private static final String TOUR_DETAILS_PAGE = "tourDetails";

    private static final String ENCODING = "UTF-8";

    private final CatalogVersionService catalogVersionService;
    private final TourFacade tourFacade;

    public TourController(
            CatalogVersionService catalogVersionService,
            TourFacade tourFacade
    ) {
        this.catalogVersionService = catalogVersionService;
        this.tourFacade = tourFacade;
    }

    @RequestMapping(value = "/tours/{tourId}")
    public String showTourDetails(@PathVariable final String tourId, final Model model)
            throws UnsupportedEncodingException {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        String decodedTourId = URLDecoder.decode(tourId, ENCODING);
        TourData tour = tourFacade.getTourDetails(decodedTourId);
        model.addAttribute(TOUR_ATTRIBUTE, tour);
        return TOUR_DETAILS_PAGE;
    }
}