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

import java.util.Optional;

import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusExecuteContainerCreationToolActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusExecuteNodeCreationToolActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusExecuteToolActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusRequestLayersActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusRequestModelActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusRequestToolsActionHandler;
import org.eclipse.sirius.server.diagram.internal.actions.handlers.SiriusToggleLayerActionHandler;
import org.eclipse.sprotty.Action;
import org.eclipse.sprotty.RequestModelAction;

/**
 * Switch used to return optional consumers of {@link Action}.
 *
 * @author sbegaudeau
 */
public class SiriusOptionalActionHandlerSwitch extends SiriusActionSwitch<Optional<ISiriusActionHandler>> {

	@Override
	protected Optional<ISiriusActionHandler> getDefaultValue() {
		return Optional.empty();
	}

	@Override
	public Optional<ISiriusActionHandler> caseRequestModelAction(RequestModelAction action) {
		return Optional.of(new SiriusRequestModelActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseExecuteContainerCreationToolAction(ExecuteContainerCreationToolAction action) {
		return Optional.of(new SiriusExecuteContainerCreationToolActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseExecuteNodeCreationToolAction(ExecuteNodeCreationToolAction action) {
		return Optional.of(new SiriusExecuteNodeCreationToolActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseExecuteToolAction(ExecuteToolAction action) {
		return Optional.of(new SiriusExecuteToolActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseRequestToolsAction(RequestToolsAction action) {
		return Optional.of(new SiriusRequestToolsActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseRequestLayersAction(RequestLayersAction action) {
		return Optional.of(new SiriusRequestLayersActionHandler());
	}

	@Override
	public Optional<ISiriusActionHandler> caseToggleLayerAction(ToggleLayerAction action) {
		return Optional.of(new SiriusToggleLayerActionHandler());
	}
}
