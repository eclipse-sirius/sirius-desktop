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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.ui.internal.operation.CenterEdgeEndModelChangeOperation;
import org.eclipse.sirius.diagram.ui.tools.api.migration.DiagramCrossReferencer;

import com.google.common.collect.Iterables;

/**
 * Diagram element state for {@link DEdge}.
 * 
 * @author dlecan
 */
public class DEdgeDiagramElementState extends AbstractDiagramElementState<DEdge> {

    /**
     * Store the arrange constraints as pin status.
     */
    private List<ArrangeConstraint> arrangeConstraints;

    /**
     * Constructor.
     * 
     * @param id
     *            Identifier
     * @param crossReferencer
     *            the cross-referencer
     */
    public DEdgeDiagramElementState(Identifier id, DiagramCrossReferencer crossReferencer) {
        super(id, crossReferencer);
        this.arrangeConstraints = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void storeElementState(EObject target, DiagramElementMapping mapping, DEdge element) {
        super.storeElementState(target, mapping, element);

        Iterable<ArrangeConstraint> existingArrangeConstraints = Iterables.filter(element.getArrangeConstraints(), ArrangeConstraint.class);
        if (!Iterables.isEmpty(existingArrangeConstraints)) {
            Iterables.addAll(arrangeConstraints, existingArrangeConstraints);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restoreElementState(DEdge element) {
        super.restoreElementState(element);

        if (!arrangeConstraints.isEmpty()) {
            element.getArrangeConstraints().addAll(arrangeConstraints);
        }

        // Center the edge ends if needed. In the case where the centering
        // property has been updated within the VSM, we now make sure the GMF
        // edge bendpoints are refreshed in addition of the edgeStyle "centered"
        // property.
        for (Edge edge : edges) {
            CenterEdgeEndModelChangeOperation operation = new CenterEdgeEndModelChangeOperation(edge, false);
            operation.execute();
        }
    }

}
