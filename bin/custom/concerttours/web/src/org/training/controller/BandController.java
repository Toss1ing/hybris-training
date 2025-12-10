package org.training.controller;

import de.hybris.platform.catalog.CatalogVersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.training.data.BandData;
import org.training.facades.BandFacade;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class BandController {
    private static final String CATALOG_ID = "concertToursProductCatalog";
    private static final String CATALOG_VERSION_NAME = "Online";

    private static final String BANDS_ATTRIBUTE = "bands";
    private static final String BAND_ATTRIBUTE = "band";

    private static final String BAND_LIST_PAGE = "bandList";
    private static final String BAND_DETAILS_PAGE = "bandDetails";

    private static final String ENCODING = "UTF-8";

    private final CatalogVersionService catalogVersionService;
    private final BandFacade bandFacade;

    public BandController(
            CatalogVersionService catalogVersionService,
            BandFacade bandFacade
    ) {
        this.catalogVersionService = catalogVersionService;
        this.bandFacade = bandFacade;
    }

    @RequestMapping(value = "/bands")
    public String showBands(final Model model) {
        List<BandData> bands = bandFacade.getBands();
        model.addAttribute(BANDS_ATTRIBUTE, bands);

        return BAND_LIST_PAGE;
    }

    @RequestMapping(value = "/bands/{bandId}")
    public String showBandDetails(@PathVariable final String bandId, final Model model) throws UnsupportedEncodingException {
        catalogVersionService.setSessionCatalogVersion(CATALOG_ID, CATALOG_VERSION_NAME);
        String decodedBandId = URLDecoder.decode(bandId, ENCODING);

        BandData band = bandFacade.getBand(decodedBandId);
        model.addAttribute(BAND_ATTRIBUTE, band);

        return BAND_DETAILS_PAGE;
    }
}