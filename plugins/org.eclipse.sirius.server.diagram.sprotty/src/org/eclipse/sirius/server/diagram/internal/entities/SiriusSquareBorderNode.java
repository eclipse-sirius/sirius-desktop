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

import org.eclipse.sprotty.SPort;

/**
 * A Sirius border node with a square based style.
 *
 * @author hmarchadour
 */
public class SiriusSquareBorderNode extends SPort {
	/**
	 * The type of the {@link SiriusSquareBorderNode}.
	 */
	public static final String TYPE = "port:square"; //$NON-NLS-1$

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
	public SiriusSquareBorderNode(String identifier) {
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