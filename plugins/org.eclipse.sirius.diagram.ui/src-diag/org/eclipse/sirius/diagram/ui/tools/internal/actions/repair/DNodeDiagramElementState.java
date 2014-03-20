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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.repair;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * Diagram element state for {@link DNode}.
 * 
 * @author dlecan
 */
public class DNodeDiagramElementState extends AbstractAbstractDNodeDiagramElementState<DNode> {

    /** Color of the element before migration. */
    protected RGBValues colorValue;

    /**
     * Constructor.
     * 
     * @param id
     *            Identifier
     * @param crossReferencer
     *            the cross-referencer
     */
    public DNodeDiagramElementState(Identifier id, DiagramCrossReferencer crossReferencer) {
        super(id, crossReferencer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeElementState(EObject target, DiagramElementMapping mapping, DNode element) {
        super.storeElementState(target, mapping, element);

        if (element.getOwnedStyle() instanceof BundledImage) {
            colorValue = ((BundledImage) element.getOwnedStyle()).getColor();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreElementState(DNode element) {
        super.restoreElementState(element);

        if (element.getOwnedStyle() instanceof BundledImage) {
            ((BundledImage) element.getOwnedStyle()).setColor(colorValue);
        }
    }

}
