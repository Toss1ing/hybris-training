package org.training.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.Registry;
import de.hybris.platform.servicelayer.ServicelayerTest;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;
import de.hybris.platform.servicelayer.model.ModelService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.training.model.BandModel;
import org.training.service.BandService;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@IntegrationTest
public class DefaultBandServiceIntegrationTest extends ServicelayerTest {
    @Resource
    private BandService bandService;
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
        bandModel.setAlbumSales(ALBUMS_SOLD);
        bandModel.setHistory(BAND_HISTORY);
    }

    @Test(expected = UnknownIdentifierException.class)
    public void testFailBehavior() {
        bandService.getBandForCode(BAND_CODE);
    }

    @Test
    public void testBandService() {
        List<BandModel> bandModels = bandService.getBands();
        int size = bandModels.size();
        modelService.save(bandModel);
        bandModels = bandService.getBands();
        assertEquals(size + 1, bandModels.size());
        assertEquals("Unexpected band found", bandModel, bandModels.get(bandModels.size() - 1));
        BandModel persistedBandModel = bandService.getBandForCode(BAND_CODE);
        assertNotNull("No band found", persistedBandModel);
        assertEquals("Different band found", bandModel, persistedBandModel);
    }
}