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
package org.eclipse.sirius.server.diagram.internal.entities;

import java.util.ArrayList;

import org.eclipse.sprotty.SNode;

/**
 * A Sirius free form container with a flat based style.
 *
 * @author sbegaudeau
 */
public class SiriusFreeFormFlatContainerNode extends SNode {
	/**
	 * The type of the {@link SiriusFreeFormFlatContainerNode}.
	 */
	private static final String TYPE = "node:freeformflatcontainer"; //$NON-NLS-1$

	/**
	 * The style of the node.
	 */
	private String style;

	/**
	 * The constructor.
	 *
	 * @param identifier
	 *            The identifier
	 */
	public SiriusFreeFormFlatContainerNode(String identifier) {
		this.setId(identifier);
		this.setType(TYPE);
		this.setChildren(new ArrayList<>());
	}

	/**
	 * Return the style.
	 *
	 * @return the style
	 */
	public String getStyle() {
		return this.style;
	}

	/**
	 * Sets the style.
	 *
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
}
