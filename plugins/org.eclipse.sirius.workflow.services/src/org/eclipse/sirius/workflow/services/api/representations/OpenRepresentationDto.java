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
 * The command used to open e representation.
 *
 * @author sbegaudeau
 */
public class OpenRepresentationDto {
	/**
	 * The identifier of the representation.
	 */
	private String representationId;

	/**
	 * Return the representationId.
	 *
	 * @return the representationId
	 */
	public String getRepresentationId() {
		return this.representationId;
	}

	/**
	 * Sets the representationId.
	 *
	 * @param representationId
	 *            the representationId to set
	 */
	public void setRepresentationId(String representationId) {
		this.representationId = representationId;
	}
}
