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
package org.eclipse.sirius.diagram.ui.tools.api.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.business.internal.query.DDiagramElementContainerExperimentalQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Helper class to test and manipulate the "pinned" status of
 * {@link DDiagramElement}.
 * 
 * @author pcdavid
 * 
 * @since 0.9.0
 */
public final class PinHelper {

    /**
     * Set of {@link ArrangeConstraint} to specify pinned
     * {@link DDiagramElement}.
     */
    public static final Collection<ArrangeConstraint> PINNED_CONSTRAINTS = new ArrayList<ArrangeConstraint>();

    static {
        PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_LOCATION);
        PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_SIZE);
        PINNED_CONSTRAINTS.add(ArrangeConstraint.KEEP_RATIO);
    }

    /**
     * Get the pinned status of the {@link DDiagramElement} associated to this
     * {@link IDiagramElementEditPart} <code>self</code>.
     * 
     * @param self
     *            the edit part from which to test the pinned status of the
     *            associated {@link DDiagramElement}.
     * 
     * @return <code>true</code> if the associated {@link DDiagramElement} is
     *         pinned, false else.
     * 
     * @see {@link PinHelper#isPinned(DDiagramElement)}
     */
    public boolean isPinned(final IDiagramElementEditPart self) {
        boolean isPinned = false;
        EObject diagramElement = self.resolveDiagramElement();
        if (diagramElement instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) diagramElement;
            isPinned = isPinned(dDiagramElement);
        }
        return isPinned;
    }

    /**
     * Get the pinned status of the {@link DDiagramElement}
     * <code>dDiagramElement</code>. The pinned status is defined by having the
     * following {@link ArrangeConstraint} through
     * {@link AbstractDNode#getArrangeConstraints()} or
     * {@link DEdge#getArrangeConstraints()} :
     * 
     * <ol>
     * <li>
     * {@link ArrangeConstraint#KEEP_LOCATION}</li>
     * <li>
     * {@link ArrangeConstraint#KEEP_SIZE}</li>
     * <li>
     * {@link ArrangeConstraint#KEEP_RATIO}</li>
     * </ol>
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} from which to test the pinned
     *            status.
     * 
     * @return <code>true</code> if the associated {@link DDiagramElement} is
     *         pinned, false else.
     */
    public boolean isPinned(final DDiagramElement dDiagramElement) {
        boolean isPinned = false;
        List<ArrangeConstraint> constraints = getArrangeConstraints(dDiagramElement);
        isPinned = constraints != null && constraints.containsAll(PINNED_CONSTRAINTS);
        return isPinned;
    }

    /**
     * Mark a {@link DDiagramElement} as pinned if possible (depending on its
     * actual type : {@link AbstractDNode} or {@link DEdge}).
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} to pin
     * 
     * @return <code>true</code> if <code>dDiagramElement</code> has been
     *         pinned, <code>false</code> if it could not
     */
    public boolean markAsPinned(DDiagramElement dDiagramElement) {
        boolean pinned = false;
        List<ArrangeConstraint> constraints = getArrangeConstraints(dDiagramElement);
        if (constraints != null) {
            constraints.addAll(PINNED_CONSTRAINTS);
            pinned = true;
        }
        return pinned;
    }

    /**
     * Mark a {@link DDiagramElement} as un-pinned if possible (depending on its
     * actual type : {@link AbstractDNode} or {@link DEdge}).
     * 
     * @param dDiagramElement
     *            the {@link DDiagramElement} to un-pin
     * 
     * @return <code>true</code> if the <code>dDiagramElement</code> was
     *         un-pinned, <code>false</code> if it could not
     */
    public boolean markAsUnpinned(DDiagramElement dDiagramElement) {
        boolean pinned = false;
        List<ArrangeConstraint> constraints = getArrangeConstraints(dDiagramElement);
        if (constraints != null) {
            constraints.removeAll(PINNED_CONSTRAINTS);
            pinned = true;
        }
        return pinned;
    }

    private List<ArrangeConstraint> getArrangeConstraints(final EObject diagramElement) {
        List<ArrangeConstraint> constraints = null;
        if (diagramElement instanceof AbstractDNode) {
            final AbstractDNode node = (AbstractDNode) diagramElement;
            constraints = node.getArrangeConstraints();
        } else if (diagramElement instanceof DEdge) {
            final DEdge edge = (DEdge) diagramElement;
            constraints = edge.getArrangeConstraints();
        }
        return constraints;
    }

    /**
     * Indicates if the given ddiagram is allowing pin/unpin.
     * 
     * @param diagram
     *            the diagram to inspect
     * @return true if the given ddiagram is allowing layouting mode, false
     *         otherwise
     */
    public static Predicate<DDiagramElement> allowsPinUnpin(DDiagram diagram) {
        // default return value is true for non-Region element (for basic
        // DDiagram that are not handled
        // by any DiagramDescriptionProvider).
        Predicate<DDiagramElement> result = new Predicate<DDiagramElement>() {
            public boolean apply(DDiagramElement dde) {
                if (dde instanceof DDiagramElementContainer) {
                    DDiagramElementContainerExperimentalQuery query = new DDiagramElementContainerExperimentalQuery((DDiagramElementContainer) dde);
                    return !query.isRegion();
                }
                return true;
            }
        };

        // If an aird has been opened from the Package Explorer View, then
        // we return false as no diagram is associated to this editor
        if (diagram == null || diagram.getDescription() == null) {
            return Predicates.alwaysFalse();
        }

        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagram.getDescription().eClass().getEPackage())) {
                // This DiagramDescriptionProvider may forbid pin/unpin actions.
                Predicate<DDiagramElement> allowsPinUnpin = diagramTypeDescriptor.getDiagramDescriptionProvider().allowsPinUnpin();
                if (allowsPinUnpin != null) {
                    result = allowsPinUnpin;
                    break;
                }
            }
        }

        return result;
    }

}
