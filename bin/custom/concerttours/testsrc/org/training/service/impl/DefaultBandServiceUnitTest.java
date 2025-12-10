package org.training.service.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Before;
import org.junit.Test;
import org.training.daos.BandDAO;
import org.training.model.BandModel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@UnitTest
public class DefaultBandServiceUnitTest {

    private DefaultBandService bandService;
    private BandDAO bandDAO;
    private BandModel bandModel;
    private static final String BAND_CODE = "Ch00X";
    private static final String BAND_NAME = "Singers All";
    private static final String BAND_HISTORY = "Medieval choir formed in 2001, based in Munich famous for authentic monastic chants";

    @Before
    public void setUp() {
        bandDAO = mock(BandDAO.class);
        bandService = new DefaultBandService(bandDAO);
        bandModel = new BandModel();
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setAlbumSales(1000L);
        bandModel.setHistory(BAND_HISTORY);
    }

    @Test
    public void testGetAllBands() {
        List<BandModel> bandModels = Arrays.asList(bandModel);
        when(bandDAO.findAllBands()).thenReturn(bandModels);
        List<BandModel> result = bandService.getBands();
        assertEquals("We should find one", 1, result.size());
        assertEquals("And should equals what the mock returned", bandModel, result.get(0));
    }

    @Test
    public void testGetBand() {
        when(bandDAO.findBandByCode(BAND_CODE)).thenReturn(Collections.singletonList(bandModel));
        BandModel result = bandService.getBandForCode(BAND_CODE);
        assertEquals("Band should equals() what the mock returned", bandModel, result);
    }
}