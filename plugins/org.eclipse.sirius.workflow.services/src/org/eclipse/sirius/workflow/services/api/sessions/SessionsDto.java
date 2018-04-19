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
package org.eclipse.sirius.workflow.services.api.sessions;

import java.util.List;

/**
 * The list of the sessions.
 *
 * @author sbegaudeau
 */
public class SessionsDto {
	/**
	 * The sessions.
	 */
	private List<SessionDto> sessions;

	/**
	 * The constructor.
	 *
	 * @param sessions
	 *            The sessions.
	 */
	public SessionsDto(List<SessionDto> sessions) {
		this.sessions = sessions;
	}

	/**
	 * Return the sessions.
	 *
	 * @return the sessions
	 */
	public List<SessionDto> getSessions() {
		return this.sessions;
	}
}
