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

import java.util.List;

/**
 * The main DTO for the representations.
 *
 * @author sbegaudeau
 */
public class RepresentationsDto {
	/**
	 * The representations.
	 */
	private List<RepresentationDto> representations;

	/**
	 * The constructor.
	 * 
	 * @param representations
	 *            The representations
	 */
	public RepresentationsDto(List<RepresentationDto> representations) {
		this.representations = representations;
	}

	/**
	 * Return the representations.
	 *
	 * @return the representations
	 */
	public List<RepresentationDto> getRepresentations() {
		return this.representations;
	}
}
