package org.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import org.training.data.BandData;
import org.training.data.TourSummaryData;
import org.training.enums.MusicType;
import org.training.model.BandModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BandBasePopulator implements Populator<BandModel, BandData> {

    private final static String SMALL_IMAGE_ATTRIBUTE = "bandList";
    private final static String BIG_IMAGE_ATTRIBUTE = "bandDetail";

    private final I18NService i18NService;
    private final MediaService mediaService;

    public BandBasePopulator(I18NService i18NService, MediaService mediaService) {
        this.i18NService = i18NService;
        this.mediaService = mediaService;
    }

    @Override
    public void populate(BandModel source, BandData target) {
        target.setId(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getHistory(i18NService.getCurrentLocale()));
        target.setAlbumsSold(source.getAlbumSales());

        List<String> genres = new ArrayList<>();
        if (source.getTypes() != null) {
            source.getTypes().forEach(type -> genres.add(type.getCode()));
        }
        target.setGenres(genres);

        List<TourSummaryData> tourHistory = new ArrayList<>();
        if (source.getTours() != null) {
            for (ProductModel tour : source.getTours()) {
                TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(i18NService.getCurrentLocale()));
                summary.setNumberOfConcerts(tour.getVariants() != null ? Integer.toString(tour.getVariants().size()) : "0");
                tourHistory.add(summary);
            }
        }
        target.setTours(tourHistory);

        if (source.getAlbumSet() != null) {
            target.setAlbumSet(source.getAlbumSet());
        }

        if (source.getSocialMediaLinks() != null) {
            target.setSocialMediaLinks(Map.copyOf(source.getSocialMediaLinks()));
        }

        MediaContainerModel container = source.getImage();
        if (container != null) {
            MediaFormatModel smallFormat = mediaService.getFormat(SMALL_IMAGE_ATTRIBUTE);
            MediaFormatModel bigFormat = mediaService.getFormat(BIG_IMAGE_ATTRIBUTE);

            if (smallFormat != null && mediaService.getMediaByFormat(container, smallFormat) != null) {
                target.setSmallImageURL(mediaService.getMediaByFormat(container, smallFormat).getDownloadURL());
            }
            if (bigFormat != null && mediaService.getMediaByFormat(container, bigFormat) != null) {
                target.setBigImageURL(mediaService.getMediaByFormat(container, bigFormat).getDownloadURL());
            }
        }
    }
}
