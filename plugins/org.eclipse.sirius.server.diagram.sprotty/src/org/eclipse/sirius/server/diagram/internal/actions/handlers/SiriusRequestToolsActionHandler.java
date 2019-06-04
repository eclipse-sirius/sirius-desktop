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

import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.RequestToolsAction;
import org.eclipse.sirius.server.diagram.internal.actions.SetToolsAction;
import org.eclipse.sirius.server.diagram.internal.actions.SiriusTool;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sprotty.Action;

/**
 * Handler for the {@link RequestToolsAction} received by the {@link SiriusDiagramServer}.
 *
 * @author gcoutable
 */
public class SiriusRequestToolsActionHandler implements ISiriusActionHandler {

	@Override
	public boolean canHandle(SiriusDiagramServer server, Action action) {
		return action instanceof RequestToolsAction;
	}

	@Override
	public void handle(SiriusDiagramServer siriusDiagramServer, Action action) {
		if (action instanceof RequestToolsAction) {
			SetToolsAction setToolsAction = this.computeToolsList(siriusDiagramServer, (RequestToolsAction) action);
			siriusDiagramServer.dispatch(setToolsAction);
		}
	}

	/**
	 * Computes the lists of available sirius tools for the requested representation.
	 *
	 * @param siriusDiagramServer
	 *            The {@link SiriusDiagramServer}
	 *
	 * @param action
	 *            The action
	 *
	 * @return The sirius tools list
	 */
	private SetToolsAction computeToolsList(SiriusDiagramServer siriusDiagramServer, RequestToolsAction action) {
		DDiagram dDiagram = siriusDiagramServer.getDDiagram();
		List<AbstractToolDescription> dDiagramTools = this.getAllRepresentationTools(dDiagram);
		List<SiriusTool> tools = dDiagramTools.stream().map(this::mapToolFromToolDescription).collect(Collectors.toList());
		return new SetToolsAction(tools);
	}

	/**
	 * Returns the list of all {@link AbstractToolDescription}.
	 *
	 * @param dDiagram
	 *            The DDiagram
	 *
	 * @return The tools of the representation
	 */
	private List<AbstractToolDescription> getAllRepresentationTools(DDiagram dDiagram) {
		// @formatter:off
		return dDiagram.getActivatedLayers().stream()
				.map(Layer::getAllTools)
				.flatMap(List::stream)
				.collect(Collectors.toList());
		// @formatter:on
	}

	/**
	 * Creates a {@link SiriusTool} from the given {@link AbstractToolDescription}.
	 *
	 * @param toolDesc
	 *            The {@link AbstractToolDescription}
	 *
	 * @return The {@link SiriusTool} created from the {@link AbstractToolDescription}
	 */
	private SiriusTool mapToolFromToolDescription(AbstractToolDescription toolDesc) {
		SiriusTool tool = new SiriusTool();
		tool.setId(toolDesc.getName());
		tool.setName(toolDesc.getLabel());
		tool.setToolType(toolDesc.getClass().getSimpleName());
		return tool;
	}
}
