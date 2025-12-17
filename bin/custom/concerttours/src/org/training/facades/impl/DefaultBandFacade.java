package org.training.facades.impl;

import org.training.converters.BandConverter;
import org.training.data.BandData;
import org.training.facades.BandFacade;
import org.training.model.BandModel;
import org.training.service.BandService;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultBandFacade implements BandFacade {

    private static final String CODE_NOT_NULL_MESSAGE = "The code cannot be null";

    private final BandService bandService;
    private final BandConverter bandConverter;

    public DefaultBandFacade(BandService bandService, BandConverter bandConverter) {
        this.bandService = bandService;
        this.bandConverter = bandConverter;
    }

    @Override
    public List<BandData> getBands() {
        List<BandModel> bandModels = bandService.getBands();
        return bandModels.stream()
                .map(bandConverter::convert)
                .collect(Collectors.toList());
    }

    @Override
    public BandData getBand(String code) {
        if (code == null) {
            throw new IllegalArgumentException(CODE_NOT_NULL_MESSAGE);
        }

        BandModel band = bandService.getBandForCode(code);
        if (band == null) {
            return null;
        }

        return bandConverter.convert(band);
    }
}
