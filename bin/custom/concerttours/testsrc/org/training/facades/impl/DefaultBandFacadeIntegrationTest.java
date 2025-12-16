package org.training.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTransactionalTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.data.BandData;
import org.training.model.BandModel;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultBandFacadeIntegrationTest extends ServicelayerTransactionalTest {

    @Resource
    private DefaultBandFacade defaultBandFacade;

    @Resource
    private ModelService modelService;

    private BandModel bandModel;
    private static final String BAND_CODE = "101-JAZ";
    private static final String BAND_NAME = "Tight Notes";
    private static final String BAND_HISTORY = "New contemporary, 7-piece Jaz unit from London, formed in 2015";
    private static final Long ALBUMS_SOLD = 10L;

    @Before
    public void setUp() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException ignored) {
        }
        bandModel = modelService.create(BandModel.class);
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);

        bandModel.setHistory(BAND_HISTORY, Locale.ENGLISH);
        bandModel.setAlbumSales(ALBUMS_SOLD);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testInvalidParameter() {
        defaultBandFacade.getBand(BAND_NAME);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullParameter() {
        defaultBandFacade.getBand(null);
    }

    @Test
    public void testBandFacade() {
        List<BandData> bandListData = defaultBandFacade.getBands();
        assertNotNull(bandListData);
        int size = bandListData.size();
        modelService.save(bandModel);
        bandListData = defaultBandFacade.getBands();
        assertNotNull(bandListData);
        assertEquals(size + 1, bandListData.size());
        assertEquals(BAND_CODE, bandListData.get(size).getId());
        assertEquals(BAND_NAME, bandListData.get(size).getName());
        assertEquals(ALBUMS_SOLD, bandListData.get(size).getAlbumsSold());
        assertEquals(BAND_HISTORY, bandListData.get(size).getDescription());
        BandData persistedBandData = defaultBandFacade.getBand(BAND_CODE);
        assertNotNull(persistedBandData);
        assertEquals(BAND_CODE, persistedBandData.getId());
        assertEquals(BAND_NAME, persistedBandData.getName());
        assertEquals(ALBUMS_SOLD, persistedBandData.getAlbumsSold());
        assertEquals(BAND_HISTORY, persistedBandData.getDescription());
    }

    @After
    public void teardown() {
    }
}