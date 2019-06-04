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

import org.eclipse.sprotty.SEdge;

/**
 * A Sirius specific edge.
 *
 * @author sbegaudeau
 */
public class SiriusEdge extends SEdge {
	/**
	 * The style of the edge.
	 */
	private String style;

	/**
	 * The constructor.
	 */
	public SiriusEdge() {
		super();
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

	/**
	 * Return the style.
	 *
	 * @return the style
	 */
	public String getStyle() {
		return this.style;
	}
}
