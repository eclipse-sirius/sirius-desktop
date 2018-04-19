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

/**
 * A representation DTO.
 *
 * @author sbegaudeau
 */
public class RepresentationDto {
	/**
	 * The identifier.
	 */
	private String representationId;

	/**
	 * The label.
	 */
	private String label;

	/**
	 * The constructor.
	 * 
	 * @param representationId
	 *            The identifier
	 * @param label
	 *            The label
	 */
	public RepresentationDto(String representationId, String label) {
		this.representationId = representationId;
		this.label = label;
	}

	/**
	 * Return the representationId.
	 *
	 * @return the representationId
	 */
	public String getRepresentationId() {
		return this.representationId;
	}

	/**
	 * Return the label.
	 *
	 * @return the label
	 */
	public String getLabel() {
		return this.label;
	}
}
