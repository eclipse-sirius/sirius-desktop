/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
    @Override
    public LostElementDataState addDiagramElementInCorrespondingParentContainer(final DDiagram diagram, final DDiagramElement diagramElement) {
        return diagram.getOwnedDiagramElements().add(diagramElement) ? LostElementDataState.CREATED : LostElementDataState.NOT_CREATED;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return super.toString() + " Diagram data"; //$NON-NLS-1$
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSimilarTo(final DSemanticDecorator semanticDecorator) {
        // Works for null values too
        return getTarget() == semanticDecorator.getTarget();
    }
}
