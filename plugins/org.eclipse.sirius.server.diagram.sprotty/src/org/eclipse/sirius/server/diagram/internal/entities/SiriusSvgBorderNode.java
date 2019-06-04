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
 * A Sirius border node with a SVG image style.
 *
 * @author sbegaudeau
 */
public class SiriusSvgBorderNode extends SPort {
	/**
	 * The type.
	 */
	public static final String TYPE = "port:image"; //$NON-NLS-1$

	/**
	 * The data of the SVG figure.
	 */
	private String url;

	/**
	 * The constructor.
	 *
	 * @param identifier
	 *            The identifier
	 */
	public SiriusSvgBorderNode(String identifier) {
		this.setId(identifier);
		this.setType(TYPE);
		this.setChildren(new ArrayList<>());
	}

	/**
	 * Sets the url.
	 *
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Return the url.
	 *
	 * @return the url
	 */
	public String getUrl() {
		return this.url;
	}
}