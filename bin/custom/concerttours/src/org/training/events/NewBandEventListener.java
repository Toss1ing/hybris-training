package org.training.events;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.catalog.model.CatalogVersionModel;
import de.hybris.platform.servicelayer.event.events.AfterItemCreationEvent;
import de.hybris.platform.servicelayer.event.impl.AbstractEventListener;
import de.hybris.platform.servicelayer.model.ModelService;
import org.training.model.BandModel;
import org.training.model.NewsModel;

import java.util.Date;
import java.util.UUID;


public class NewBandEventListener extends AbstractEventListener<AfterItemCreationEvent> {

    private static final String CATALOG_NAME = "Default";
    private static final String CATALOG_VERSION = "Staged";

    private static final String NEW_BAND_HEADLINE = "New band, %s";
    private static final String NEW_BAND_CONTENT = "There is a new band in town called, %s. Tour news to be announced soon.";

    private final ModelService modelService;
    private final CatalogVersionService catalogVersionService;

    public NewBandEventListener(ModelService modelService, CatalogVersionService catalogVersionService) {
        this.modelService = modelService;
        this.catalogVersionService = catalogVersionService;
    }

    @Override
    protected void onEvent(final AfterItemCreationEvent event) {
        if (event != null && event.getSource() != null) {
            final Object object = modelService.get(event.getSource());
            if (object instanceof BandModel band) {
                String headline = String.format(NEW_BAND_HEADLINE, band.getName());
                String content = String.format(NEW_BAND_CONTENT, band.getName());
                NewsModel news = modelService.create(NewsModel.class);
                news.setCode(UUID.randomUUID().toString());

                news.setDate(new Date());
                news.setHeadline(headline);
                news.setContent(content);

                CatalogVersionModel catalogVersion = catalogVersionService
                        .getCatalogVersion(CATALOG_NAME, CATALOG_VERSION);
                news.setCatalogVersion(catalogVersion);

                modelService.save(news);
            }
        }
    }

}