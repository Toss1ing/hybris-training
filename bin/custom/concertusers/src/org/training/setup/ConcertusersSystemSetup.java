/*
 * Copyright (c) 2021 SAP SE or an SAP affiliate company. All rights reserved.
 */
package org.training.setup;

import static org.training.constants.ConcertusersConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import org.training.constants.ConcertusersConstants;
import org.training.service.ConcertusersService;


@SystemSetup(extension = ConcertusersConstants.EXTENSIONNAME)
public class ConcertusersSystemSetup
{
	private final ConcertusersService concertusersService;

	public ConcertusersSystemSetup(final ConcertusersService concertusersService)
	{
		this.concertusersService = concertusersService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		concertusersService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return ConcertusersSystemSetup.class.getResourceAsStream("/concertusers/sap-hybris-platform.png");
	}
}
