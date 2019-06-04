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
 * A Sirius node with a SVG image style.
 *
 * @author sbegaudeau
 */
public class SiriusSvgNode extends SNode {
	/**
	 * The type of the {@link SiriusSvgNode}.
	 */
	private static final String TYPE = "node:svg"; //$NON-NLS-1$

	/**
	 * The data of the SVG figure.
	 */
	private String svgData;

	/**
	 * The constructor.
	 *
	 * @param identifier
	 *            The identifier
	 */
	public SiriusSvgNode(String identifier) {
		this.setId(identifier);
		this.setType(TYPE);
		this.setChildren(new ArrayList<>());
	}

	/**
	 * Sets the svgData.
	 *
	 * @param svgData
	 *            the svgData to set
	 */
	public void setSvgData(String svgData) {
		this.svgData = svgData;
	}

	/**
	 * Return the svgData.
	 *
	 * @return the svgData
	 */
	public String getSvgData() {
		return this.svgData;
	}
}
