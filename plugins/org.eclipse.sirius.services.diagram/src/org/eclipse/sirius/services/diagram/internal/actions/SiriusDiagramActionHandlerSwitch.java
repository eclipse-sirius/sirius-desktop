/*******************************************************************************
 * Copyright (c) 2018 Obeo.
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
package org.eclipse.sirius.services.diagram.internal.actions;

import java.util.Optional;

import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramExecuteNodeCreationToolAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestModelAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestToolsAction;

/**
 * Switch used to retrieve the handler for each kind of action.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramActionHandlerSwitch extends SiriusDiagramActionSwitch<Optional<ISiriusDiagramActionHandler>> {

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.SiriusDiagramActionSwitch#getDefaultValue()
     */
    @Override
    protected Optional<ISiriusDiagramActionHandler> getDefaultValue() {
        return Optional.empty();
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.SiriusDiagramActionSwitch#caseExecuteNodeCreationToolAction(org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramExecuteNodeCreationToolAction)
     */
    @Override
    public Optional<ISiriusDiagramActionHandler> caseExecuteNodeCreationToolAction(SiriusDiagramExecuteNodeCreationToolAction action) {
        return Optional.of(new SiriusDiagramExecuteNodeCreationToolActionHandler());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.SiriusDiagramActionSwitch#caseRequestModelAction(org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestModelAction)
     */
    @Override
    public Optional<ISiriusDiagramActionHandler> caseRequestModelAction(SiriusDiagramRequestModelAction action) {
        return Optional.of(new SiriusDiagramRequestModelActionHandler());
    }

    /**
     * {@inheritDoc}
     *
     * @see org.eclipse.sirius.services.diagram.internal.actions.SiriusDiagramActionSwitch#caseRequestToolsAction(org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestToolsAction)
     */
    @Override
    public Optional<ISiriusDiagramActionHandler> caseRequestToolsAction(SiriusDiagramRequestToolsAction action) {
        return Optional.of(new SiriusDiagramRequestToolsActionHandler());
    }
}
