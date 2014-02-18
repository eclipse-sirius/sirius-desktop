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
package org.eclipse.sirius.diagram.business.internal.repair.resource.session.diagram.data;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Lost diagram element data.
 * 
 * @author dlecan
 */
public class LostDiagramData extends AbstractLostElementDataWithTarget implements ILostElementDataContainer {

    /**
     * {@inheritDoc}
     */
    public LostElementDataState addDiagramElementInCorrespondingParentContainer(final DDiagram diagram, final DDiagramElement diagramElement) {
        return diagram.getOwnedDiagramElements().add(diagramElement) ? LostElementDataState.CREATED : LostElementDataState.NOT_CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " Diagram data";
    }

    /**
     * {@inheritDoc}
     */
    public boolean isSimilarTo(final DSemanticDecorator semanticDecorator) {
        // Works for null values too
        return getTarget() == semanticDecorator.getTarget();
    }
}
