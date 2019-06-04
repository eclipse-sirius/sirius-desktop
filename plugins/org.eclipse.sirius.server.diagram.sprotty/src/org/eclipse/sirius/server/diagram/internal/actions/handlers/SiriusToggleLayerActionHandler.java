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

import java.util.Optional;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.api.command.ChangeLayerActivationCommand;
import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.ToggleLayerAction;
import org.eclipse.sprotty.Action;

/**
 * Handler used to toggle on and off layers of the diagram.
 *
 * @author gcoutable
 */
public class SiriusToggleLayerActionHandler implements ISiriusActionHandler {

	@Override
	public boolean canHandle(SiriusDiagramServer server, Action action) {
		return action instanceof ToggleLayerAction;
	}

	@Override
	public void handle(SiriusDiagramServer siriusDiagramServer, Action action) {
		if (action instanceof ToggleLayerAction) {
			this.executeToggleLayerAction(siriusDiagramServer, (ToggleLayerAction) action);
		}
	}

	/**
	 * Toggles the layer depending on the new state of the layer returned by the client.
	 *
	 * @param siriusDiagramServer
	 *            The {@link SiriusDiagramServer}
	 * @param action
	 *            The action
	 */
	private void executeToggleLayerAction(SiriusDiagramServer siriusDiagramServer, ToggleLayerAction action) {
		Session session = siriusDiagramServer.getSession();
		DDiagram dDiagram = siriusDiagramServer.getDDiagram();

		//@formatter:off

		Optional<Layer> optionalLayer = LayerHelper.getAllLayers(dDiagram.getDescription())
				.stream()
				.filter(Layer -> Layer.getName().equals(action.getLayerName()))
				.findFirst();
		optionalLayer.ifPresent(layer -> this.toggleLayer(session, dDiagram, layer));
		//@formatter:on
	}

	/**
	 * Toggles the layer.
	 *
	 * @param session
	 *            The sirius session
	 * @param dDiagram
	 *            The diagram description
	 * @param layer
	 *            The layer to toggle
	 */
	private void toggleLayer(Session session, DDiagram dDiagram, Layer layer) {
		TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
		ChangeLayerActivationCommand command = new ChangeLayerActivationCommand(ted, dDiagram, layer, new NullProgressMonitor());
		ted.getCommandStack().execute(command);
	}

}
