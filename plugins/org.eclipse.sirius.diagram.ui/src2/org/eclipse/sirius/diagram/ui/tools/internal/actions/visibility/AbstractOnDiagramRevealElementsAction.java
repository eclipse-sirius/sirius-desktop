/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.graphical.edit.part.DDiagramHelper;

/**
 * Common behavior for all actions which need to reveal elements with a request
 * performed on the diagram instead of the selected element.
 * 
 * @author dlecan
 */
public abstract class AbstractOnDiagramRevealElementsAction extends AbstractRevealElementsAction<EditPart> {

    /**
     * Constructor.
     */
    public AbstractOnDiagramRevealElementsAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public AbstractOnDiagramRevealElementsAction(final String text) {
        super(text);
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     * @param image
     *            ImageDescriptor
     */
    public AbstractOnDiagramRevealElementsAction(final String text, final ImageDescriptor image) {
        super(text, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final EditPart element) {
        final IDDiagramEditPart parent = DDiagramHelper.findParentDiagram(element);

        boolean result = true;

        if (parent != null) {
            final Request revealRequest = createRequest(parent);

            if (revealRequest != null) {
                parent.performRequest(revealRequest);
                result = false;
            }
        }
        return result;
    }

    /**
     * Create the request.
     * 
     * @param parent
     *            parent diagram edit part.
     * @return The request built.
     */
    protected abstract Request createRequest(IDDiagramEditPart parent);

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<EditPart> getElementType() {
        return EditPart.class;
    }
}
