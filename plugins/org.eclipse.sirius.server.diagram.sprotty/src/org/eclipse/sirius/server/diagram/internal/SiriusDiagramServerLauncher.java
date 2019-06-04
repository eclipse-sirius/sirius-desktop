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
package org.eclipse.sirius.server.diagram.internal;

import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;

/**
 * The main application for the Sirius Server.
 *
 * @author gcoutable
 *
 */
public class SiriusDiagramServerLauncher implements IApplication {

	/**
	 * The application context.
	 */
	private IApplicationContext appContext;

	@Override
	public Object start(IApplicationContext context) throws Exception {
		this.appContext = context;
		return IApplicationContext.EXIT_ASYNC_RESULT;
	}

	@Override
	public void stop() {
		appContext.setResult(EXIT_OK, this);
	}

}
