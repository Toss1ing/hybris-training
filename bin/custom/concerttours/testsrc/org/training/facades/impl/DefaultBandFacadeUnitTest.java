package org.training.facades.impl;

import de.hybris.bootstrap.annotations.UnitTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.training.converters.BandConverter;
import org.training.data.BandData;
import org.training.model.BandModel;
import org.training.service.BandService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@UnitTest
public class DefaultBandFacadeUnitTest {

    private DefaultBandFacade bandFacade;
    private BandService bandService;
    private BandConverter bandConverter;

    private static final String BAND_CODE = "ROCK-11";
    private static final String BAND_NAME = "Ladies of Rock";
    private static final Long ALBUMS_SOLD = 42000L;
    private static final String BAND_HISTORY = "All female rock band formed in Munich in the late 1990s";

    private BandModel configTestBand() {
        BandModel band = new BandModel();
        band.setCode(BAND_CODE);
        band.setName(BAND_NAME);
        band.setAlbumSales(ALBUMS_SOLD);
        band.setHistory(BAND_HISTORY);
        return band;
    }

    private List<BandModel> dummyDataBandList() {
        List<BandModel> bands = new ArrayList<>();
        bands.add(configTestBand());
        return bands;
    }

    @Before
    public void setUp() {
        bandService = mock(BandService.class);
        bandConverter = mock(BandConverter.class);

        bandFacade = new DefaultBandFacade(bandService, bandConverter);
    }

    @Test
    public void testGetAllBands() {
        List<BandModel> bands = dummyDataBandList();

        BandData dto = new BandData();
        dto.setId(BAND_CODE);
        dto.setName(BAND_NAME);
        dto.setAlbumsSold(ALBUMS_SOLD);
        dto.setDescription(BAND_HISTORY);

        when(bandService.getBands()).thenReturn(bands);
        when(bandConverter.convert(any(BandModel.class))).thenReturn(dto);

        List<BandData> result = bandFacade.getBands();

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(BAND_CODE, result.get(0).getId());
        Assert.assertEquals(BAND_NAME, result.get(0).getName());
        Assert.assertEquals(ALBUMS_SOLD, result.get(0).getAlbumsSold());
        Assert.assertEquals(BAND_HISTORY, result.get(0).getDescription());

        verify(bandService, times(1)).getBands();
        verify(bandConverter, times(1)).convert(any(BandModel.class));
    }

    @Test
    public void testGetBandByCode() {
        BandModel band = configTestBand();

        BandData dto = new BandData();
        dto.setId(BAND_CODE);
        dto.setName(BAND_NAME);
        dto.setAlbumsSold(ALBUMS_SOLD);
        dto.setDescription(BAND_HISTORY);

        when(bandService.getBandForCode(BAND_CODE)).thenReturn(band);
        when(bandConverter.convert(band)).thenReturn(dto);

        BandData result = bandFacade.getBand(BAND_CODE);

        Assert.assertNotNull(result);
        Assert.assertEquals(BAND_CODE, result.getId());
        Assert.assertEquals(BAND_NAME, result.getName());
        Assert.assertEquals(ALBUMS_SOLD, result.getAlbumsSold());
        Assert.assertEquals(BAND_HISTORY, result.getDescription());

        verify(bandService, times(1)).getBandForCode(BAND_CODE);
        verify(bandConverter, times(1)).convert(band);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetBandNullCode() {
        bandFacade.getBand(null);
    }
}
