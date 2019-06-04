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
 * Action used to request the layers of the diagram.
 *
 * @author gcoutable
 */
public class RequestLayersAction implements Action {

	/**
	 * The kind of the action.
	 */
	public static final String KIND = "requestLayers"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = KIND;

	@Override
	public String getKind() {
		return kind;
	}

}
