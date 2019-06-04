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

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.server.diagram.internal.SiriusDiagramServer;
import org.eclipse.sirius.server.diagram.internal.actions.ExecuteToolAction;
import org.eclipse.sirius.server.diagram.internal.actions.ISiriusActionHandler;
import org.eclipse.sirius.server.diagram.internal.interpreter.InterpreterVariables;
import org.eclipse.sirius.server.diagram.internal.interpreter.SiriusServerInterpreter;
import org.eclipse.sirius.viewpoint.description.tool.ModelOperation;
import org.eclipse.sirius.viewpoint.description.tool.ToolDescription;
import org.eclipse.sprotty.Action;

/**
 * Handler for the {@link ExecuteToolAction} received by the {@link SiriusDiagramServer}.
 *
 * @author sbegaudeau
 */
public class SiriusExecuteToolActionHandler implements ISiriusActionHandler {
	/**
	 * The aql expression used to execute a {@link ModelOperation} programmatically.
	 */
	private static final String AQL_EXECUTE_OPERATION = "aql:self.executeOperation('%1$s')"; //$NON-NLS-1$

	@Override
	public boolean canHandle(SiriusDiagramServer server, Action action) {
		return action instanceof ExecuteToolAction;
	}

	@Override
	public void handle(SiriusDiagramServer siriusDiagramServer, Action action) {
		if (action instanceof ExecuteToolAction) {
			this.executeToolAction(siriusDiagramServer, (ExecuteToolAction) action);
		}
	}

	/**
	 * Execute the tool identified by its name.
	 *
	 * @param siriusDiagramServer
	 *            The {@link SiriusDiagramServer}
	 * @param action
	 *            The action
	 */
	private void executeToolAction(SiriusDiagramServer siriusDiagramServer, ExecuteToolAction action) {
		Session session = siriusDiagramServer.getSession();
		DDiagram dDiagram = siriusDiagramServer.getDDiagram();

		// @formatter:off
		Optional<ToolDescription> optionalDescription = dDiagram.getDescription()
			.getAllTools()
			.stream()
			.filter(tool -> action.getToolName().equals(tool.getName()))
			.filter(ToolDescription.class::isInstance)
			.map(ToolDescription.class::cast)
			.findFirst();
		// @formatter:on

		optionalDescription.ifPresent(tool -> this.executeTool(session, dDiagram, tool));
	}

	/**
	 * Execute a {@link ToolDescription} tool.
	 *
	 * @param session
	 *            The session
	 * @param representation
	 *            The representation
	 * @param tool
	 *            The {@link ToolDescription} tool
	 */
	private void executeTool(Session session, DDiagram representation, ToolDescription tool) {
		ModelOperation modelOperation = tool.getInitialOperation().getFirstModelOperations();

		String expression = EcoreUtil.getURI(modelOperation).toString();
		expression = expression.replace("'", "\\'"); //$NON-NLS-1$//$NON-NLS-2$
		String expr = String.format(AQL_EXECUTE_OPERATION, expression);
		Map<String, Object> variables = new HashMap<>();
		if (representation instanceof DSemanticDiagram) {
			DSemanticDiagram dsd = (DSemanticDiagram) representation;
			variables.put(InterpreterVariables.SELF_VARIABLE, dsd.getTarget());
		}

		SiriusServerInterpreter.executeExpression(session, variables, expr);
	}
}
