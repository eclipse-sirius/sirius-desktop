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
package org.eclipse.sirius.services.diagram.internal.actions;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramExecuteNodeCreationToolAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestModelAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramRequestToolsAction;
import org.eclipse.sirius.services.diagram.api.actions.SiriusDiagramSetModelAction;

/**
 * Switch used to handle the various actions.
 *
 * @author sbegaudeau
 *
 * @param <T>
 *            The type of result expected by the switch
 */
public class SiriusDiagramActionSwitch<T> {
    /**
     * Returns the default value.
     *
     * @return The default value
     */
    protected T getDefaultValue() {
        return null;
    }

    /**
     * Dispatch the action.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T doSwitch(AbstractSiriusDiagramAction action) {
        T result = this.getDefaultValue();
        switch (action.getKind()) {
        case SiriusDiagramExecuteNodeCreationToolAction.KIND:
            if (action instanceof SiriusDiagramExecuteNodeCreationToolAction) {
                result = this.caseExecuteNodeCreationToolAction((SiriusDiagramExecuteNodeCreationToolAction) action);
            }
            break;
        case SiriusDiagramRequestModelAction.KIND:
            if (action instanceof SiriusDiagramRequestModelAction) {
                result = this.caseRequestModelAction((SiriusDiagramRequestModelAction) action);
            }
            break;
        case SiriusDiagramRequestToolsAction.KIND:
            if (action instanceof SiriusDiagramRequestToolsAction) {
                result = this.caseRequestToolsAction((SiriusDiagramRequestToolsAction) action);
            }
            break;
        case SiriusDiagramSetModelAction.KIND:
            if (action instanceof SiriusDiagramSetModelAction) {
                result = this.caseSetModelAction((SiriusDiagramSetModelAction) action);
            }
            break;
        default:
            result = this.defaultCase(action);
        }
        return result;
    }

    /**
     * Handles the {@link SiriusDiagramExecuteNodeCreationToolAction}.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T caseExecuteNodeCreationToolAction(SiriusDiagramExecuteNodeCreationToolAction action) {
        return this.getDefaultValue();
    }

    /**
     * Handles the {@link SiriusDiagramRequestModelAction}.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T caseRequestModelAction(SiriusDiagramRequestModelAction action) {
        return this.getDefaultValue();
    }

    /**
     * Handles the {@link SiriusDiagramRequestToolsAction}.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T caseRequestToolsAction(SiriusDiagramRequestToolsAction action) {
        return this.getDefaultValue();
    }

    /**
     * Handles the {@link SiriusDiagramSetModelAction}.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T caseSetModelAction(SiriusDiagramSetModelAction action) {
        return this.getDefaultValue();
    }

    /**
     * Handles the default case.
     *
     * @param action
     *            The action
     * @return The expected result
     */
    public T defaultCase(AbstractSiriusDiagramAction action) {
        return this.getDefaultValue();
    }
}
