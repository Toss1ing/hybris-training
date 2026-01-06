package org.training.populators;

import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.media.MediaContainerModel;
import de.hybris.platform.core.model.media.MediaFormatModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.i18n.I18NService;
import de.hybris.platform.servicelayer.media.MediaService;
import org.springframework.beans.factory.annotation.Value;
import org.training.data.BandData;
import org.training.data.ProducerData;
import org.training.data.TourSummaryData;
import org.training.model.BandModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BandBasePopulator implements Populator<BandModel, BandData> {

    @Value("${media.attribute.small}")
    private String smallImageAttribute;

    @Value("${media.attribute.big}")
    private String bigImageAttribute;

    private final I18NService i18NService;
    private final MediaService mediaService;

    public BandBasePopulator(
            I18NService i18NService,
            MediaService mediaService
    ) {
        this.i18NService = i18NService;
        this.mediaService = mediaService;
    }

    @Override
    public void populate(BandModel source, BandData target) {
        populateBasicInfo(source, target);
        populateGenres(source, target);
        populateTours(source, target);
        populateAlbums(source, target);
        populateSocialMedia(source, target);
        populateImages(source, target);
        populateProducer(source, target);
    }

    private void populateBasicInfo(BandModel source, BandData target) {
        target.setId(source.getCode());
        target.setName(source.getName());
        target.setDescription(source.getHistory(i18NService.getCurrentLocale()));
        target.setAlbumsSold(source.getAlbumSales());
    }

    private void populateGenres(BandModel source, BandData target) {
        List<String> genres = new ArrayList<>();
        if (source.getTypes() != null) {
            source.getTypes().forEach(type -> genres.add(type.getCode()));
        }
        target.setGenres(genres);
    }

    private void populateTours(BandModel source, BandData target) {
        List<TourSummaryData> tourHistory = new ArrayList<>();
        if (source.getTours() != null) {
            for (ProductModel tour : source.getTours()) {
                TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(i18NService.getCurrentLocale()));
                summary.setNumberOfConcerts(
                        tour.getVariants() != null ? Integer.toString(tour.getVariants().size()) : "0"
                );
                tourHistory.add(summary);
            }
        }
        target.setTours(tourHistory);
    }

    private void populateAlbums(BandModel source, BandData target) {
        if (source.getAlbumSet() != null) {
            target.setAlbumSet(source.getAlbumSet());
        }
    }

    private void populateSocialMedia(BandModel source, BandData target) {
        if (source.getSocialMediaLinks() != null) {
            target.setSocialMediaLinks(Map.copyOf(source.getSocialMediaLinks()));
        }
    }

    private void populateImages(BandModel source, BandData target) {
        MediaContainerModel container = source.getImage();
        if (container != null) {
            MediaFormatModel smallFormat = mediaService.getFormat(smallImageAttribute);
            MediaFormatModel bigFormat = mediaService.getFormat(bigImageAttribute);

            if (smallFormat != null && mediaService.getMediaByFormat(container, smallFormat) != null) {
                target.setSmallImageURL(mediaService.getMediaByFormat(container, smallFormat).getDownloadURL());
            }
            if (bigFormat != null && mediaService.getMediaByFormat(container, bigFormat) != null) {
                target.setBigImageURL(mediaService.getMediaByFormat(container, bigFormat).getDownloadURL());
            }
        }
    }

    private void populateProducer(BandModel source, BandData target) {
        if (source.getProducer() != null) {
            ProducerData producerData = new ProducerData();
            producerData.setId(source.getProducer().getCode());
            producerData.setName(source.getProducer().getName());
            target.setProducer(producerData);
        }
    }

}
