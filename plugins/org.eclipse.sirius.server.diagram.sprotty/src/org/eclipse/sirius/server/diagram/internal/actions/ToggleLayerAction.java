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

import org.eclipse.sprotty.Action;

/**
 * Sent from the client to the server in order to toggle a layer.
 *
 * @author gcoutable
 */
public class ToggleLayerAction implements Action {

	/**
	 * The kind of the action.
	 */
	public static final String KIND = "toggleLayer"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = ToggleLayerAction.KIND;

	/**
	 * The name of the layer.
	 */
	private String layerName;

	/**
	 * The new state of the layer.
	 */
	private boolean newState;

	@Override
	public String getKind() {
		return this.kind;
	}

	/**
	 * Return the layerName.
	 *
	 * @return the layerName
	 */
	public String getLayerName() {
		return this.layerName;
	}

	/**
	 * Return the newState.
	 *
	 * @return the newState
	 */
	public boolean isNewState() {
		return this.newState;
	}

}
