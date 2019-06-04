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
package org.eclipse.sirius.server.diagram.internal.actions.handlers;

import org.eclipse.sirius.server.diagram.internal.SiriusDiagramGenerator;
import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sprotty.Action;
import org.eclipse.sprotty.RequestModelAction;
import org.eclipse.sprotty.SGraph;

/**
 * Handler for the {@link RequestModelAction} received by the {@link SiriusDiagramServer}.
 *
 * @author sbegaudeau
 */
public class SiriusRequestModelActionHandler implements ISiriusActionHandler {

	/**
	 * The diagram generator.
	 */
	private SiriusDiagramGenerator diagramGenerator = new SiriusDiagramGenerator();

	@Override
	public boolean canHandle(SiriusDiagramServer server, Action action) {
		return action instanceof RequestModelAction;
	}

	@Override
	public void handle(SiriusDiagramServer siriusDiagramServer, Action action) {
		if (action instanceof RequestModelAction) {
			SGraph sGraph = this.diagramGenerator.computeDiagram(siriusDiagramServer, (RequestModelAction) action);
			siriusDiagramServer.updateModel(sGraph);
		}
	}

}
