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

import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;

/**
 * Class used to be serialize from the server in order to send data about {@link AbstractToolDescription} to the client
 *
 * @author gcoutable
 */
public class SiriusTool {

	/**
	 * The name of the {@link AbstractToolDescription}.
	 */
	private String id;

	/**
	 * The label of the {@link AbstractToolDescription}.
	 */
	private String name;

	/**
	 * The simple class name of the {@link AbstractToolDescription} subclass.
	 */
	private String toolType;

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
	 * Return the toolType.
	 *
	 * @return the toolType
	 */
	public String getToolType() {
		return this.toolType;
	}

	/**
	 * Sets the toolType.
	 *
	 * @param toolType
	 *            the toolType to set
	 */
	public void setToolType(String toolType) {
		this.toolType = toolType;
	}
}