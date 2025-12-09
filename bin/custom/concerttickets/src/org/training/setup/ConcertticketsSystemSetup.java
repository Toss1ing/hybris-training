/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ConcertticketsConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.ConcertticketsConstants;


@SystemSetup(extension = ConcertticketsConstants.EXTENSIONNAME)
public class ConcertticketsSystemSetup
{
	private final ConcertticketsService concertticketsService;

	public ConcertticketsSystemSetup(final ConcertticketsService concertticketsService)
	{
		this.concertticketsService = concertticketsService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concertticketsService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcertticketsSystemSetup.class.getResourceAsStream("/concerttickets/sap-hybris-platform.png");
	}
}
