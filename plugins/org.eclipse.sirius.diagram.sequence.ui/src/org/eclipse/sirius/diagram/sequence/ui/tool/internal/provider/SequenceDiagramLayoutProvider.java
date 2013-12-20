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
package org.eclipse.sirius.diagram.sequence.ui.tool.internal.provider;

import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.services.layout.AbstractLayoutEditPartProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.layout.SequenceLayoutProvider;
import org.eclipse.sirius.diagram.ui.tools.api.layout.provider.LayoutProvider;

/**
 * This provider contributes the appropriate layout provider for sequence
 * diagrams.
 * 
 * @author ymortier, mporhel
 */
public class SequenceDiagramLayoutProvider implements LayoutProvider {

    /**
     * The GMF layout provider.
     */
    private final AbstractLayoutEditPartProvider layoutProvider = new SequenceLayoutProvider();

    /**
     * {@inheritDoc}
     */
    public AbstractLayoutEditPartProvider getLayoutNodeProvider(final IGraphicalEditPart container) {
        return layoutProvider;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isDiagramLayoutProvider() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(final IGraphicalEditPart container) {
        View notationView = container.getNotationView();
        if (notationView != null) {
            Diagram diagram = notationView.getDiagram();
            return ISequenceElementAccessor.getSequenceDiagram(diagram).some();
        }
        return false;
    }

}
