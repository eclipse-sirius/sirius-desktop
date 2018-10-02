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
package org.eclipse.sirius.services.diagram.api.actions;

import org.eclipse.sirius.services.diagram.api.AbstractSiriusDiagramAction;

/**
 * The action used to execute the creation of a node.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramExecuteNodeCreationToolAction extends AbstractSiriusDiagramAction {

    /**
     * The kind of the action.
     */
    public static final String KIND = "executeNodeCreateTool"; //$NON-NLS-1$

    /**
     * The identifier of the tool to execute.
     */
    private String identifier;

    /**
     * The identifier of the container on which the tool will be used.
     */
    private String containerIdentifier;

    /**
     * The constructor.
     */
    public SiriusDiagramExecuteNodeCreationToolAction() {
        super(KIND);
    }

    /**
     * Return the identifier.
     *
     * @return the identifier
     */
    public String getIdentifier() {
        return this.identifier;
    }

    /**
     * Return the containerIdentifier.
     *
     * @return the containerIdentifier
     */
    public String getContainerIdentifier() {
        return this.containerIdentifier;
    }

}
