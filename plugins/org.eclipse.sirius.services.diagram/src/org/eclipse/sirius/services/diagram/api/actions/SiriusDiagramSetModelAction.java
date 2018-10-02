/*******************************************************************************
 * Copyright (c) 2018 TypeFox and others.
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
import org.eclipse.sirius.services.diagram.api.entities.SiriusDiagram;

/**
 * This action is used to set the model of the client.
 *
 * @author sbegaudeau
 */
public class SiriusDiagramSetModelAction extends AbstractSiriusDiagramAction {
    /**
     * The kind of the action.
     */
    public static final String KIND = "setModel"; //$NON-NLS-1$

    /**
     * The new root of the model.
     */
    private SiriusDiagram newRoot;

    /**
     * The constructor.
     *
     * @param diagram
     *            The new version of the diagram
     */
    public SiriusDiagramSetModelAction(SiriusDiagram diagram) {
        super(KIND);
        this.newRoot = diagram;
    }

    /**
     * Return the newRoot.
     *
     * @return the newRoot
     */
    public SiriusDiagram getNewRoot() {
        return this.newRoot;
    }

}
