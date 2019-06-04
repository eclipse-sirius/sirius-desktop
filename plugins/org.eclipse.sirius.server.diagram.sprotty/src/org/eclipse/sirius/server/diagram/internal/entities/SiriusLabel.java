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

import org.eclipse.sprotty.SLabel;

/**
 * The Sirius label.
 *
 * @author sbegaudeau
 */
public class SiriusLabel extends SLabel {

	/**
	 * The type of the left-aligned labels located inside a node.
	 */
	public static final String INSIDE_LEFT__LABEL_TYPE = "label:inside-left"; //$NON-NLS-1$

	/**
	 * The type of the center-aligned labels located inside a node.
	 */
	public static final String INSIDE_CENTER__LABEL_TYPE = "label:inside-center"; //$NON-NLS-1$

	/**
	 * The type of the right-aligned labels located inside a node.
	 */
	public static final String INSIDE_RIGHT__LABEL_TYPE = "label:inside-right"; //$NON-NLS-1$

	/**
	 * The type of the left-aligned labels located outside a node.
	 */
	public static final String OUTSIDE_LEFT__LABEL_TYPE = "label:outside-left"; //$NON-NLS-1$

	/**
	 * The type of the center-aligned labels located outside a node.
	 */
	public static final String OUTSIDE_CENTER__LABEL_TYPE = "label:outside-center"; //$NON-NLS-1$

	/**
	 * The type of the right-aligned labels located outside a node.
	 */
	public static final String OUTSIDE_RIGHT__LABEL_TYPE = "label:outside-right"; //$NON-NLS-1$

	/**
	 * The style of the edge.
	 */
	private String style;

	/**
	 * The constructor.
	 *
	 * @param identifier
	 *            The identifier
	 * @param label
	 *            The label
	 */
	public SiriusLabel(String identifier, String label) {
		super();
		this.setId(identifier);
		this.setText(label);
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
