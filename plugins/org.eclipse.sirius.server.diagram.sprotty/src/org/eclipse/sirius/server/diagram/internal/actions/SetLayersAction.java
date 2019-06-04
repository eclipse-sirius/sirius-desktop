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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sprotty.Action;

/**
 * Sent from the server to the client in order to give the layers.
 *
 * @author gcoutable
 */
public class SetLayersAction implements Action {

	/**
	 * The kind of the action.
	 */
	public static final String KIND = "setLayers"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = KIND;

	/**
	 * The layers.
	 */
	private List<SiriusLayer> layers;

	/**
	 * Constructor.
	 *
	 * @param layers
	 *            The list of layers
	 */
	public SetLayersAction(List<SiriusLayer> layers) {
		this.layers = new ArrayList<>(layers);
	}

	@Override
	public String getKind() {
		return kind;
	}

	/**
	 * Return the layers.
	 *
	 * @return the layers
	 */
	public List<SiriusLayer> getLayers() {
		return this.layers;
	}

}
