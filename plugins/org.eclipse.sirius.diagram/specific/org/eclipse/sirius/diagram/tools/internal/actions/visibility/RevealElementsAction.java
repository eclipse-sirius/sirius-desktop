/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions.visibility;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.sirius.diagram.tools.api.requests.RequestConstants;

/**
 * Reveal (un-hide) a {@link org.eclipse.sirius.viewpoint.DDiagramElement} from a
 * {@link org.eclipse.sirius.viewpoint.DDiagram}.
 * 
 * @author dlecan
 * @deprecated since 2.5,
 *             {@link org.eclipse.sirius.diagram.tools.internal.actions.visibility.RevealAllElementsAction}
 *             must be used instead.
 */
@Deprecated
public class RevealElementsAction extends AbstractRevealElementsAction<EditPart> {

    /**
     * Constructor.
     */
    public RevealElementsAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public RevealElementsAction(final String text) {
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
    public RevealElementsAction(final String text, final ImageDescriptor image) {
        super(text, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean doRun(final EditPart element) {
        final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REVEAL_ALLS);
        element.performRequest(refreshRequest);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Class<EditPart> getElementType() {
        return EditPart.class;
    }

}
