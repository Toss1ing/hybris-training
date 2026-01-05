package org.training.facades.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.impex.jalo.ImpExException;
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

    private static final String SMALL_IMAGE_URL = "/media/bandList/101-JAZ-small.jpg";
    private static final String BIG_IMAGE_URL = "/media/bandDetail/101-JAZ-big.jpg";

    @Before
    public void setUp() throws ImpExException {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
            new JdbcTemplate(Registry.getCurrentTenant().getDataSource()).execute("CHECKPOINT");
            Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        } catch (InterruptedException ignored) {
        }

        importCsv("/impex/essentialdata-mediaformats.impex", "UTF-8");
        importCsv("/impex/projectdata-concerttours-media.impex", "UTF-8");
        importCsv("/impex/projectdata-concerttours.impex", "UTF-8");

        bandModel = modelService.create(BandModel.class);
        bandModel.setCode(BAND_CODE);
        bandModel.setName(BAND_NAME);
        bandModel.setHistory(BAND_HISTORY);
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

        BandData persistedBandData = bandListData.get(size);
        assertEquals(BAND_CODE, persistedBandData.getId());
        assertEquals(BAND_NAME, persistedBandData.getName());
        assertEquals(ALBUMS_SOLD, persistedBandData.getAlbumsSold());
        assertEquals(BAND_HISTORY, persistedBandData.getDescription());

        assertEquals(SMALL_IMAGE_URL, persistedBandData.getSmallImageURL());
        assertEquals(BIG_IMAGE_URL, persistedBandData.getBigImageURL());

        BandData persistedBandDataByCode = defaultBandFacade.getBand(BAND_CODE);
        assertNotNull(persistedBandDataByCode);
        assertEquals(BAND_CODE, persistedBandDataByCode.getId());
        assertEquals(BAND_NAME, persistedBandDataByCode.getName());
        assertEquals(ALBUMS_SOLD, persistedBandDataByCode.getAlbumsSold());
        assertEquals(BAND_HISTORY, persistedBandDataByCode.getDescription());
    }

    @After
    public void teardown() {
    }
}
