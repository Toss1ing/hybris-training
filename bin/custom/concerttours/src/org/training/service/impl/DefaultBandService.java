package org.training.service.impl;

import de.hybris.platform.servicelayer.exceptions.AmbiguousIdentifierException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import org.training.daos.BandDAO;
import org.training.model.BandModel;
import org.training.service.BandService;

import java.util.List;

public class DefaultBandService implements BandService {

    private static final String MSG_BAND_NOT_FOUND =
            "Band with code '%s' not found!";

    private static final String MSG_BAND_NOT_UNIQUE =
            "Band code '%s' is not unique, %d bands found!";

    private final BandDAO bandDAO;

    public DefaultBandService(final BandDAO bandDAO) {
        this.bandDAO = bandDAO;
    }

    @Override
    public List<BandModel> getBands() {
        return bandDAO.findAllBands();
    }

    @Override
    public BandModel getBandForCode(final String code)
            throws AmbiguousIdentifierException, UnknownIdentifierException {

        List<BandModel> result = bandDAO.findBandByCode(code);

        if (result.isEmpty()) {
            throw new UnknownIdentifierException(
                    String.format(MSG_BAND_NOT_FOUND, code));
        } else if (result.size() > 1) {
            throw new AmbiguousIdentifierException(
                    String.format(MSG_BAND_NOT_UNIQUE, code, result.size()));
        }

        return result.get(0);
    }
}
