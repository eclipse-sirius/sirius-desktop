/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.sirius.diagram.DDiagram;

/**
 * An abstract runnable to change activation status.
 * 
 * @author mchauvin
 */
public abstract class AbstractChangeActivation implements Runnable {

    /** The diagram. */
    protected DDiagram diagram;

    /** the activation status. */
    protected boolean activate;

    /** The workbench part. */
    private final IDiagramWorkbenchPart diagramPart;

    /**
     * Construct a new instance.
     * 
     * @param part
     *            the diagram workbench part
     * @param diagram
     *            the diagram
     * @param activate
     *            the activation
     */
    public AbstractChangeActivation(final IDiagramWorkbenchPart part, final DDiagram diagram, final boolean activate) {
        this.diagram = diagram;
        this.activate = activate;
        this.diagramPart = part;
    }

    /**
     * Get diagram edit part.
     * 
     * @return diagram edit part.
     */
    protected EditPart getDiagramEditPart() {
        return diagramPart.getDiagramEditPart();
    }

    /**
     * Get root edit part.
     * 
     * @return root edit part.
     */
    protected RootEditPart getRootEditPart() {
        return diagramPart.getDiagramGraphicalViewer().getRootEditPart();
    }

    /**
     * Returns the diagramPart.
     * 
     * @return The diagramPart.
     */
    protected final IDiagramWorkbenchPart getDiagramPart() {
        return diagramPart;
    }

    /**
     * Return the activation status.
     * 
     * @return the activation status.
     */
    public boolean isActivate() {
        return activate;
    }
}
