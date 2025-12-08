package org.training.facades.impl;

import de.hybris.platform.core.model.product.ProductModel;
import org.training.data.BandData;
import org.training.data.TourSummaryData;
import org.training.enums.MusicType;
import org.training.facades.BandFacade;
import org.training.model.BandModel;
import org.training.service.BandService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DefaultBandFacade implements BandFacade {

    private static final String CODE_NULL_EXCEPTION_MESSAGE = "The code cannot be null";

    private final BandService bandService;

    public DefaultBandFacade(BandService bandService) {
        this.bandService = bandService;
    }

    @Override
    public List<BandData> getBands() {
        List<BandModel> bandModels = bandService.getBands();
        List<BandData> bandFacadeData = new ArrayList<>();
        for (BandModel sm : bandModels) {
            BandData sfd = new BandData();
            sfd.setId(sm.getCode());
            sfd.setName(sm.getName());
            sfd.setDescription(sm.getHistory());
            sfd.setAlbumsSold(sm.getAlbumSales());
            bandFacadeData.add(sfd);
        }
        return bandFacadeData;
    }

    @Override
    public BandData getBand(final String code) {
        if (code == null) {
            throw new IllegalArgumentException(CODE_NULL_EXCEPTION_MESSAGE);
        }
        BandModel band = bandService.getBandForCode(code);
        if (band == null) {
            return null;
        }

        List<String> genres = new ArrayList<>();
        if (band.getTypes() != null) {
            for (final MusicType musicType : band.getTypes()) {
                genres.add(musicType.getCode());
            }
        }
        final List<TourSummaryData> tourHistory = new ArrayList<>();
        if (band.getTours() != null) {
            for (final ProductModel tour : band.getTours()) {
                final TourSummaryData summary = new TourSummaryData();
                summary.setId(tour.getCode());
                summary.setTourName(tour.getName(Locale.ENGLISH));
                summary.setNumberOfConcerts(Integer.toString(tour.getVariants().size()));
                tourHistory.add(summary);
            }
        }
        BandData bandData = new BandData();
        bandData.setId(band.getCode());
        bandData.setName(band.getName());
        bandData.setAlbumsSold(band.getAlbumSales());
        bandData.setDescription(band.getHistory());
        bandData.setGenres(genres);
        bandData.setTours(tourHistory);
        return bandData;
    }
}