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
package org.eclipse.sirius.workflow.services.api.workflow;

/**
 * The action.
 *
 * @author sbegaudeau
 */
public class ActionDto {
	/**
	 * The identifier.
	 */
	private String identifier;

	/**
	 * The label.
	 */
	private String label;

	/**
	 * The constructor.
	 *
	 * @param identifier
	 *            The identifier
	 *
	 * @param label
	 *            The label
	 */
	public ActionDto(String identifier, String label) {
		this.identifier = identifier;
		this.label = label;
	}

	/**
	 * Return the identifier.
	 *
	 * @return the identifier
	 */
	public String getIdentifier() {
		return this.identifier;
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
