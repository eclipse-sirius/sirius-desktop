/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.refresh;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.CustomStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DotEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.EllipseEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.GaugeCompositeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.LozengeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * A switch to determine if a GMF View is orphaned.
 * 
 * @author smonnier
 */
public class IsOrphanedSwitch {

    private View gmfView;

    private Collection<EObject> semanticChildren;

    private View parentView;

    /**
     * Creates a new switch.
     * 
     * @param view
     *            {@link View}
     * @param semanticChildren
     *            semantic children
     * @param parentView
     *            the parent {@link View}
     */
    public IsOrphanedSwitch(final View view, final Collection<EObject> semanticChildren, final View parentView) {
        this.gmfView = view;
        this.semanticChildren = semanticChildren;
        this.parentView = parentView;
    }

    /**
     * Returns <code>true</code> if gmfView is orphaned.
     * 
     * @param visualId
     *            the visual id of the parent view.
     * @return <code>true</code> if gmfView is orphaned.
     */
    public Boolean doSwitch(final int visualId) {
        Boolean res = Boolean.FALSE;
        switch (visualId) {
        case DDiagramEditPart.VISUAL_ID:
            res = caseDDiagramEditPart();
            break;
        case DNode2EditPart.VISUAL_ID:
            res = caseDNode2EditPart();
            break;
        case DNode3EditPart.VISUAL_ID:
            res = caseDNode3EditPart();
            break;
        case DNodeContainer2EditPart.VISUAL_ID:
            res = caseDNodeContainer2EditPart();
            break;
        case DNode4EditPart.VISUAL_ID:
            res = caseDNode4EditPart();
            break;
        case DNodeEditPart.VISUAL_ID:
            res = caseDNodeEditPart();
            break;
        case DNodeContainerEditPart.VISUAL_ID:
            res = caseDNodeContainerEditPart();
            break;
        case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
            res = caseDNodeContainerViewNodeContainerCompartmentEditPart();
            break;
        case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
            res = caseDNodeContainerViewNodeContainerCompartment2EditPart();
            break;
        case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
            res = caseDNodeListViewNodeListCompartmentEditPart();
            break;
        case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
            res = caseDNodeListViewNodeListCompartment2EditPart();
            break;
        case DNodeListEditPart.VISUAL_ID:
            res = caseDNodeListEditPart();
            break;
        case DNodeList2EditPart.VISUAL_ID:
            res = caseDNodeList2EditPart();
            break;
        default:
            break;
        }
        return res;
    }

    /**
     * Checks an element owned by the diagram.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDDiagramEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNodeEditPart.VISUAL_ID:
        case DNodeContainerEditPart.VISUAL_ID:
        case DNodeListEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNode2EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNode2EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case BundledImageEditPart.VISUAL_ID:
        case DotEditPart.VISUAL_ID:
        case GaugeCompositeEditPart.VISUAL_ID:
        case SquareEditPart.VISUAL_ID:
        case EllipseEditPart.VISUAL_ID:
        case LozengeEditPart.VISUAL_ID:
        case WorkspaceImageEditPart.VISUAL_ID:
        case DNode2EditPart.VISUAL_ID:
        case CustomStyleEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNode3EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNode3EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode2EditPart.VISUAL_ID:
        case DotEditPart.VISUAL_ID:
        case SquareEditPart.VISUAL_ID:
        case EllipseEditPart.VISUAL_ID:
        case LozengeEditPart.VISUAL_ID:
        case BundledImageEditPart.VISUAL_ID:
        case NoteEditPart.VISUAL_ID:
        case WorkspaceImageEditPart.VISUAL_ID:
        case GaugeCompositeEditPart.VISUAL_ID:
        case CustomStyleEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeContainer2EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeContainer2EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode4EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeList2EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeList2EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode4EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNode4EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNode4EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case BundledImageEditPart.VISUAL_ID:
        case DotEditPart.VISUAL_ID:
        case GaugeCompositeEditPart.VISUAL_ID:
        case SquareEditPart.VISUAL_ID:
        case EllipseEditPart.VISUAL_ID:
        case LozengeEditPart.VISUAL_ID:
        case WorkspaceImageEditPart.VISUAL_ID:
        case DNode4EditPart.VISUAL_ID:
        case CustomStyleEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeEditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode2EditPart.VISUAL_ID:
        case DotEditPart.VISUAL_ID:
        case SquareEditPart.VISUAL_ID:
        case EllipseEditPart.VISUAL_ID:
        case LozengeEditPart.VISUAL_ID:
        case BundledImageEditPart.VISUAL_ID:
        case NoteEditPart.VISUAL_ID:
        case WorkspaceImageEditPart.VISUAL_ID:
        case GaugeCompositeEditPart.VISUAL_ID:
        case CustomStyleEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeContainerEditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeContainerEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode4EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeListEditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeListEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode4EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a
     * DNodeContainerViewNodeContainerCompartmentEditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeContainerViewNodeContainerCompartmentEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode3EditPart.VISUAL_ID:
        case DNodeContainer2EditPart.VISUAL_ID:
        case DNodeList2EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a
     * DNodeContainerViewNodeContainerCompartment2EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeContainerViewNodeContainerCompartment2EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNode3EditPart.VISUAL_ID:
        case DNodeContainer2EditPart.VISUAL_ID:
        case DNodeList2EditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeListViewNodeListCompartmentEditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeListViewNodeListCompartmentEditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNodeListElementEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

    /**
     * Checks an element owned by a DNodeListViewNodeListCompartment2EditPart.
     * 
     * @return true if the view is orphaned.
     */
    public Boolean caseDNodeListViewNodeListCompartment2EditPart() {
        final int visualID = SiriusVisualIDRegistry.getVisualID(gmfView);
        switch (visualID) {
        case DNodeListElementEditPart.VISUAL_ID:
            return Boolean.valueOf(!semanticChildren.contains(gmfView.getElement()) || visualID != SiriusVisualIDRegistry.getNodeVisualID(parentView, gmfView.getElement()));
        default:
            break;
        }
        return Boolean.FALSE;
    }

}
