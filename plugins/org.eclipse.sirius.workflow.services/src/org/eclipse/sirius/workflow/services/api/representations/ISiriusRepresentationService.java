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
package org.eclipse.sirius.workflow.services.api.representations;

import java.util.Optional;

/**
 * The services used to manipulate Sirius sessions.
 *
 * @author sbegaudeau
 */
public interface ISiriusRepresentationService {
	/**
	 * Returns the representations of the session with the given session identifier.
	 * 
	 * @param sessionId
	 *            The session identifier
	 * @return The representations of the session with the given session identifier
	 */
	RepresentationsDto getRepresentations(String sessionId);

	/**
	 * Creates a new representation in the session with the given sessionId.
	 *
	 * @param sessionId
	 *            The identifier of the session
	 * @param createRepresentationDto
	 *            The details of the representation to create
	 * @return An optional with the representation created or an empty optional if it could not be created
	 */
	Optional<RepresentationDto> createRepresentation(String sessionId, CreateRepresentationDto createRepresentationDto);

	/**
	 * Opens a representation in the session with the given sessionId.
	 *
	 * @param sessionId
	 *            The identifier of the session
	 * @param openRepresentationDto
	 *            The details of the representation to open
	 * @return An optional with the representation opened or an empty optional if it could not be opened
	 */
	Optional<RepresentationDto> openRepresentation(String sessionId, OpenRepresentationDto openRepresentationDto);
}
