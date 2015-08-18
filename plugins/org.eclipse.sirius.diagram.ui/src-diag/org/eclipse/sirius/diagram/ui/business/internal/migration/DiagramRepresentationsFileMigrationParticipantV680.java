/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.business.internal.migration;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Bounds;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.LayoutConstraint;
import org.eclipse.gmf.runtime.notation.Location;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.gmf.runtime.notation.NotationFactory;
import org.eclipse.sirius.diagram.ui.business.api.query.ViewQuery;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;
import org.osgi.framework.Version;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * The migration code of Sirius 6.8.0.
 * 
 * @author fbarbin
 * 
 */
public class DiagramRepresentationsFileMigrationParticipantV680 {

    /**
     * The VP version for this migration.
     */
    public static final Version MIGRATION_VERSION = new Version("6.8.0.201307151200"); //$NON-NLS-1$

    /**
     * Removes layout constraints from compartments.
     * 
     * @param diagrams
     *            list of GMF Diagram to migrate.
     */
    public void migrateCompartmentsWithLayoutConstraints(List<Diagram> diagrams) {
        for (Diagram diagram : diagrams) {
            Iterator<EObject> compartmentIterator = Iterators.filter(diagram.eAllContents(), new IsCompartmentPredicate());
            while (compartmentIterator.hasNext()) {
                Node node = (Node) compartmentIterator.next();
                if (node.getLayoutConstraint() != null) {
                    node.setLayoutConstraint(null);
                }
            }
        }
    }

    /**
     * Replace Location layout constraints of edge labels nodes by Bounds to
     * re-enable label resize. The location were created during
     * PasteLayoutDataCommand execution for labels with (-1,-1) size.
     * 
     * @param diagrams
     *            list of GMF Diagram to migrate.
     */
    public void migrateEdgeLabelLocationToBounds(List<Diagram> diagrams) {
        for (Diagram diagram : diagrams) {
            for (Edge edge : Iterables.filter(diagram.getEdges(), Edge.class)) {
                for (Node edgeLabel : Iterables.filter(edge.getChildren(), Node.class)) {
                    LayoutConstraint layoutConstraint = edgeLabel.getLayoutConstraint();
                    if (new ViewQuery(edgeLabel).isForEdgeNameEditPart() && !(layoutConstraint instanceof Bounds) && layoutConstraint instanceof Location) {
                        Location location = (Location) layoutConstraint;
                        Bounds bounds = NotationFactory.eINSTANCE.createBounds();
                        bounds.setX(location.getX());
                        bounds.setY(location.getY());
                        edgeLabel.setLayoutConstraint(bounds);
                    }
                }
            }
        }
    }

    /**
     * A predicate that checks if the element represents a compartment.
     * 
     * @author fbarbin
     * 
     */
    private class IsCompartmentPredicate implements Predicate<EObject> {

        public boolean apply(EObject arg0) {
            if (arg0 instanceof Node) {
                int id = SiriusVisualIDRegistry.getVisualID(((Node) arg0).getType());
                return id == DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID || id == DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID;
            }
            return false;
        }

    }
}
