/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.part;

import java.text.MessageFormat;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.tools.internal.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.CustomStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode3EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNode4EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainer2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerViewNodeContainerCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeList2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListElementEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartment2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListViewNodeListCompartmentEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DotEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.EllipseEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.GaugeCompositeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.LozengeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NotationViewIDs;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.NoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SquareEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.WorkspaceImageEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.viewpoint.Style;

/**
 * This registry is used to determine which type of visual object should be created for the corresponding Diagram, Node,
 * ChildNode or Link represented by a domain model object.
 * 
 * @was-generated
 */
public class SiriusVisualIDRegistry {

    /**
     * @was-generated
     */
    private static final String DEBUG_KEY = DiagramUIPlugin.getPlugin().getSymbolicName() + "/debug/visualID"; //$NON-NLS-1$

    /**
     * @was-generated
     */
    public static int getVisualID(View view) {
        if (view instanceof Diagram) {
            if (DDiagramEditPart.MODEL_ID.equals(view.getType())) {
                return DDiagramEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        return org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry.getVisualID(view.getType());
    }

    /**
     * @was-generated
     */
    public static String getModelID(View view) {
        View diagram = view.getDiagram();
        while (view != diagram && view != null) {
            EAnnotation annotation = view.getEAnnotation("Shortcut"); //$NON-NLS-1$
            if (annotation != null) {
                return annotation.getDetails().get("modelID"); //$NON-NLS-1$
            }
            EObject viewContainer = view.eContainer();
            view = viewContainer instanceof View ? (View) viewContainer : null;
        }
        return diagram != null ? diagram.getType() : null;
    }

    /**
     * @was-generated
     */
    public static int getVisualID(String type) {
        try {
            return Integer.parseInt(type);
        } catch (NumberFormatException e) {
            if (Boolean.TRUE.toString().equalsIgnoreCase(Platform.getDebugOption(DEBUG_KEY))) {
                DiagramPlugin.getDefault().logError(MessageFormat.format(Messages.SiriusVisualIDRegistry_parseError, type));
            }
        }
        return -1;
    }

    /**
     * @was-generated
     */
    public static String getType(int visualID) {
        return String.valueOf(visualID);
    }

    /**
     * @was-generated
     */
    public static int getDiagramVisualID(EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        if (DiagramPackage.eINSTANCE.getDDiagram().isSuperTypeOf(domainElement.eClass()) && isDiagram((DDiagram) domainElement)) {
            return DDiagramEditPart.VISUAL_ID;
        }
        return -1;
    }

    /**
     * @was-generated
     */
    public static int getNodeVisualID(View containerView, EObject domainElement) {
        if (domainElement == null) {
            return -1;
        }
        String containerModelID = org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry.getModelID(containerView);
        if (!DDiagramEditPart.MODEL_ID.equals(containerModelID)) {
            return -1;
        }
        int containerVisualID;
        if (DDiagramEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry.getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = DDiagramEditPart.VISUAL_ID;
            } else {
                return -1;
            }
        }
        switch (containerVisualID) {
        case DNodeEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode2EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDot().isSuperTypeOf(domainElement.eClass())) {
                return DotEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getSquare().isSuperTypeOf(domainElement.eClass())) {
                return SquareEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
                return EllipseEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getLozenge().isSuperTypeOf(domainElement.eClass())) {
                return LozengeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getBundledImage().isSuperTypeOf(domainElement.eClass())) {
                return BundledImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getNote().isSuperTypeOf(domainElement.eClass())) {
                return NoteEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getWorkspaceImage().isSuperTypeOf(domainElement.eClass())) {
                return WorkspaceImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getGaugeCompositeStyle().isSuperTypeOf(domainElement.eClass())) {
                return GaugeCompositeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getCustomStyle().isSuperTypeOf(domainElement.eClass())) {
                return CustomStyleEditPart.VISUAL_ID;
            }
            break;
        case DNodeContainerEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode4EditPart.VISUAL_ID;
            }
            break;
        case DNodeListEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode4EditPart.VISUAL_ID;
            }
            break;
        case DNode2EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getBundledImage().isSuperTypeOf(domainElement.eClass())) {
                return BundledImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDot().isSuperTypeOf(domainElement.eClass())) {
                return DotEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getGaugeCompositeStyle().isSuperTypeOf(domainElement.eClass())) {
                return GaugeCompositeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getSquare().isSuperTypeOf(domainElement.eClass())) {
                return SquareEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
                return EllipseEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getLozenge().isSuperTypeOf(domainElement.eClass())) {
                return LozengeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getWorkspaceImage().isSuperTypeOf(domainElement.eClass())) {
                return WorkspaceImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode2EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getCustomStyle().isSuperTypeOf(domainElement.eClass())) {
                return CustomStyleEditPart.VISUAL_ID;
            }
            break;
        case DNode3EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode2EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDot().isSuperTypeOf(domainElement.eClass())) {
                return DotEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getSquare().isSuperTypeOf(domainElement.eClass())) {
                return SquareEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
                return EllipseEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getLozenge().isSuperTypeOf(domainElement.eClass())) {
                return LozengeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getBundledImage().isSuperTypeOf(domainElement.eClass())) {
                return BundledImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getNote().isSuperTypeOf(domainElement.eClass())) {
                return NoteEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getWorkspaceImage().isSuperTypeOf(domainElement.eClass())) {
                return WorkspaceImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getGaugeCompositeStyle().isSuperTypeOf(domainElement.eClass())) {
                return GaugeCompositeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getCustomStyle().isSuperTypeOf(domainElement.eClass())) {
                return CustomStyleEditPart.VISUAL_ID;
            }
            break;
        case DNodeContainer2EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode4EditPart.VISUAL_ID;
            }
            break;
        case DNodeList2EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode4EditPart.VISUAL_ID;
            }
            break;
        case DNode4EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getBundledImage().isSuperTypeOf(domainElement.eClass())) {
                return BundledImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDot().isSuperTypeOf(domainElement.eClass())) {
                return DotEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getGaugeCompositeStyle().isSuperTypeOf(domainElement.eClass())) {
                return GaugeCompositeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getSquare().isSuperTypeOf(domainElement.eClass())) {
                return SquareEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getEllipse().isSuperTypeOf(domainElement.eClass())) {
                return EllipseEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getLozenge().isSuperTypeOf(domainElement.eClass())) {
                return LozengeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getWorkspaceImage().isSuperTypeOf(domainElement.eClass())) {
                return WorkspaceImageEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode4EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getCustomStyle().isSuperTypeOf(domainElement.eClass())) {
                return CustomStyleEditPart.VISUAL_ID;
            }
            break;
        case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode3EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeContainer().isSuperTypeOf(domainElement.eClass())) {
                return DNodeContainer2EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeList().isSuperTypeOf(domainElement.eClass())) {
                return DNodeList2EditPart.VISUAL_ID;
            }
            break;
        case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNode3EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeContainer().isSuperTypeOf(domainElement.eClass())) {
                return DNodeContainer2EditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeList().isSuperTypeOf(domainElement.eClass())) {
                return DNodeList2EditPart.VISUAL_ID;
            }
            break;
        case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNodeListElement().isSuperTypeOf(domainElement.eClass())) {
                return DNodeListElementEditPart.VISUAL_ID;
            }
            break;
        case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNodeListElement().isSuperTypeOf(domainElement.eClass())) {
                return DNodeListElementEditPart.VISUAL_ID;
            }
            break;
        case DDiagramEditPart.VISUAL_ID:
            if (DiagramPackage.eINSTANCE.getDNode().isSuperTypeOf(domainElement.eClass())) {
                return DNodeEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeContainer().isSuperTypeOf(domainElement.eClass())) {
                return DNodeContainerEditPart.VISUAL_ID;
            }
            if (DiagramPackage.eINSTANCE.getDNodeList().isSuperTypeOf(domainElement.eClass())) {
                return DNodeListEditPart.VISUAL_ID;
            }
            break;
        }
        return -1;
    }

    /**
     * @was-generated
     */
    public static boolean canCreateNode(View containerView, int nodeVisualID) {
        String containerModelID = org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry.getModelID(containerView);
        if (!DDiagramEditPart.MODEL_ID.equals(containerModelID)) {
            return false;
        }
        int containerVisualID;
        if (DDiagramEditPart.MODEL_ID.equals(containerModelID)) {
            containerVisualID = org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry.getVisualID(containerView);
        } else {
            if (containerView instanceof Diagram) {
                containerVisualID = DDiagramEditPart.VISUAL_ID;
            } else {
                return false;
            }
        }
        switch (containerVisualID) {
        case DNodeEditPart.VISUAL_ID:
            if (NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DotEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (SquareEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (LozengeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (BundledImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (NoteEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (WorkspaceImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (GaugeCompositeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CustomStyleEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeContainerEditPart.VISUAL_ID:
            if (DNodeContainerNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeListEditPart.VISUAL_ID:
            if (DNodeListNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeListViewNodeListCompartment2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNode2EditPart.VISUAL_ID:
            if (NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (BundledImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DotEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (GaugeCompositeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (SquareEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (LozengeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (WorkspaceImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CustomStyleEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNode3EditPart.VISUAL_ID:
            if (NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DotEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (SquareEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (LozengeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (BundledImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (NoteEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (WorkspaceImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (GaugeCompositeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CustomStyleEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeContainer2EditPart.VISUAL_ID:
            if (DNodeContainerName2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeList2EditPart.VISUAL_ID:
            if (DNodeListName2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeListViewNodeListCompartmentEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNode4EditPart.VISUAL_ID:
            if (NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (BundledImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DotEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (GaugeCompositeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (SquareEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (EllipseEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (LozengeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (WorkspaceImageEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNode4EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (CustomStyleEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
        case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
            if (DNode3EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeContainer2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeList2EditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
        case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
            if (DNodeListElementEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DDiagramEditPart.VISUAL_ID:
            if (DNodeEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeContainerEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DNodeListEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        case DEdgeEditPart.VISUAL_ID:
            if (DEdgeNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DEdgeEndNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            if (DEdgeBeginNameEditPart.VISUAL_ID == nodeVisualID) {
                return true;
            }
            break;
        }
        return false;
    }

    /**
     * Now that we have dimension specific edge, we must return the {@link BracketEdgeEditPart#VISUAL_ID} for a
     * {@link DEdge} with a {@link DimensionStyle}.
     * 
     * @not-generated
     */
    public static int getLinkWithClassVisualID(EObject domainElement) {
        int visualID = -1;
        if (domainElement != null) {
            if (DiagramPackage.eINSTANCE.getDEdge().isSuperTypeOf(domainElement.eClass())) {
                DEdge dEdge = (DEdge) domainElement;
                Style style = dEdge.getStyle();
                if (style instanceof BracketEdgeStyle) {
                    visualID = BracketEdgeEditPart.VISUAL_ID;
                } else {
                    visualID = DEdgeEditPart.VISUAL_ID;
                }
            }
        }
        return visualID;
    }

    /**
     * User can change implementation of this method to handle some specific situations not covered by default logic.
     * 
     * @was-generated
     */
    private static boolean isDiagram(DDiagram element) {
        return true;
    }

}
