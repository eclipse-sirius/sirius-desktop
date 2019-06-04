/*******************************************************************************
 * Copyright (c) 2018 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.server.diagram.internal.actions;

import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;

import org.eclipse.sprotty.Action;

/**
 * Utility interface to handle an {@link Action}.
 *
 * @author sbegaudeau
 */
public interface ISiriusActionHandler {

	/**
	 * Indicates if the handler can handler the given {@link Action}.
	 *
	 * @param server
	 *            The server
	 * @param action
	 *            The action
	 * @return <code>true</code> if the action can be handled, <code>false</code> otherwise
	 */
	boolean canHandle(SiriusDiagramServer server, Action action);

	/**
	 * Handles the given {@link Action}.
	 *
	 * @param server
	 *            The server
	 * @param action
	 *            The action
	 */
	void handle(SiriusDiagramServer server, Action action);
}
