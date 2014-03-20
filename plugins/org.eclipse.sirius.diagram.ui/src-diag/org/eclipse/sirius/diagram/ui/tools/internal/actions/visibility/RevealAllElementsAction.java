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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility;

import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * Reveal (un-hide) a {@link org.eclipse.sirius.diagram.DDiagramElement} from a
 * {@link org.eclipse.sirius.diagram.DDiagram}.
 * 
 * @author dlecan
 */
public class RevealAllElementsAction extends AbstractOnDiagramRevealElementsAction {

    /**
     * Constructor.
     */
    public RevealAllElementsAction() {
        super();
    }

    /**
     * Constructor.
     * 
     * @param text
     *            String
     */
    public RevealAllElementsAction(final String text) {
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
    public RevealAllElementsAction(final String text, final ImageDescriptor image) {
        super(text, image);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Request createRequest(final IDDiagramEditPart parent) {
        return new GroupRequest(RequestConstants.REQ_REVEAL_ALLS);
    }

}
