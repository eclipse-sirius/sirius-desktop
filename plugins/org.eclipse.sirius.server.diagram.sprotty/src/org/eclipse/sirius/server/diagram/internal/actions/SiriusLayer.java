/*******************************************************************************
 * Copyright (c) 2018, 2019 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.diagram.internal.actions;

import org.eclipse.sirius.diagram.description.Layer;

/**
 * Class used to be serialize from the server in order to send data about {@link Layer} to the client.
 *
 * @author gcoutable
 */
public class SiriusLayer {

	/**
	 * The name of the {@link org.eclipse.sirius.diagram.description.Layer}.
	 */
	private String id;

	/**
	 * The label of the {@link org.eclipse.sirius.diagram.description.Layer}.
	 */
	private String name;

	/**
	 * If the layer is active.
	 */
	private boolean isActive;

	/**
	 * Return the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Return the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Return the isActive.
	 *
	 * @return the isActive
	 */
	public boolean isActive() {
		return this.isActive;
	}

	/**
	 * Sets the isActive.
	 *
	 * @param isActive
	 *            the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

}
