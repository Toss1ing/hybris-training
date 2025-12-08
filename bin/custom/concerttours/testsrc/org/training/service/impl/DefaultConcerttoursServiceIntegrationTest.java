/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.service.impl;

import de.hybris.bootstrap.annotations.IntegrationTest;
import de.hybris.platform.core.model.media.MediaModel;
import de.hybris.platform.servicelayer.ServicelayerBaseTest;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.FlexibleSearchService;
import org.junit.Before;
import org.junit.Test;
import org.training.service.ConcerttoursService;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.training.constants.ConcerttoursConstants.PLATFORM_LOGO_CODE;


@IntegrationTest
public class DefaultConcerttoursServiceIntegrationTest extends ServicelayerBaseTest {
    @Resource
    private ConcerttoursService concerttoursService;
    @Resource
    private FlexibleSearchService flexibleSearchService;

    @Before
    public void setUp() throws Exception {
        concerttoursService.createLogo(PLATFORM_LOGO_CODE);
    }

    @Test
    public void shouldReturnProperUrlForLogo() throws Exception {
        String logoCode = "concerttoursPlatformLogo";

        String logoUrl = concerttoursService.getHybrisLogoUrl(logoCode);

        assertThat(logoUrl).isNotNull();
        assertThat(logoUrl).isEqualTo(findLogoMedia(logoCode).getURL());
    }

    private MediaModel findLogoMedia(final String logoCode) {
        FlexibleSearchQuery fQuery = new FlexibleSearchQuery("SELECT {PK} FROM {Media} WHERE {code}=?code");
        fQuery.addQueryParameter("code", logoCode);

        return flexibleSearchService.searchUnique(fQuery);
    }

}
