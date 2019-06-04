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
 * Sent from the client to the server in order to request applicable tools.
 *
 * @author gcoutable
 */
public class RequestToolsAction implements Action {

	/**
	 * The kind of the action.
	 */
	public static final String KIND = "requestTools"; //$NON-NLS-1$

	/**
	 * The kind of the action.
	 */
	private String kind = KIND;

	@Override
	public String getKind() {
		return this.kind;
	}

}
