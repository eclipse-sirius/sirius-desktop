/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.workflow.services.internal.sessions;

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.workflow.services.api.SiriusServicesUtils;
import org.eclipse.sirius.workflow.services.api.sessions.ISiriusSessionService;
import org.eclipse.sirius.workflow.services.api.sessions.SessionDto;
import org.eclipse.sirius.workflow.services.api.sessions.SessionsDto;

/**
 * The implementation of the Sirius session service.
 *
 * @author sbegaudeau
 */
public class SiriusSessionService implements ISiriusSessionService {

	@Override
	public SessionsDto getSessions() {
		List<SessionDto> sessionDtos = SessionManager.INSTANCE.getSessions().stream().map(session -> {
			return new SessionDto(SiriusServicesUtils.encode(session.getID()));
		}).collect(Collectors.toList());
		return new SessionsDto(sessionDtos);
	}

}
