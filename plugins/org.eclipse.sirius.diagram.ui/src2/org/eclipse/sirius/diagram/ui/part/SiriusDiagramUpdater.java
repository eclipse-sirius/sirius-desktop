/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.part;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.ui.graphical.edit.part.specific.BracketEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.BundledImageEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.CustomStyleEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEditPart;
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
import org.eclipse.sirius.diagram.ui.internal.providers.SiriusElementTypes;

/**
 * @was-generated NOT
 */
public class SiriusDiagramUpdater {

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getSemanticChildren(View view) {
        switch (SiriusVisualIDRegistry.getVisualID(view)) {
        case DNodeEditPart.VISUAL_ID:
            return getDNode_2001SemanticChildren(view);
        case DNodeContainerEditPart.VISUAL_ID:
            return getDNodeContainer_2002SemanticChildren(view);
        case DNodeListEditPart.VISUAL_ID:
            return getDNodeList_2003SemanticChildren(view);
        case DNode2EditPart.VISUAL_ID:
            return getDNode_3001SemanticChildren(view);
        case DNode3EditPart.VISUAL_ID:
            return getDNode_3007SemanticChildren(view);
        case DNodeContainer2EditPart.VISUAL_ID:
            return getDNodeContainer_3008SemanticChildren(view);
        case DNodeList2EditPart.VISUAL_ID:
            return getDNodeList_3009SemanticChildren(view);
        case DNode4EditPart.VISUAL_ID:
            return getDNode_3012SemanticChildren(view);
        case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
            return getDNodeContainerViewNodeContainerCompartment_7001SemanticChildren(view);
        case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
            return getDNodeContainerViewNodeContainerCompartment_7002SemanticChildren(view);
        case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
            return getDNodeListViewNodeListCompartment_7003SemanticChildren(view);
        case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
            return getDNodeListViewNodeListCompartment_7004SemanticChildren(view);
        case DDiagramEditPart.VISUAL_ID:
            return getDDiagram_1000SemanticChildren(view);
        }
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNode_2001SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNode modelElement = (DNode) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        {
            NodeStyle childElement = modelElement.getOwnedStyle();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DotEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == SquareEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == EllipseEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == LozengeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == BundledImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == NoteEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == WorkspaceImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == GaugeCompositeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == CustomStyleEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeContainer_2002SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode4EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeList_2003SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNodeList modelElement = (DNodeList) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode4EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNode_3001SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNode modelElement = (DNode) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        {
            NodeStyle childElement = modelElement.getOwnedStyle();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == BundledImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == DotEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == GaugeCompositeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == SquareEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == EllipseEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == LozengeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == WorkspaceImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == CustomStyleEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
        }
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNode_3007SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNode modelElement = (DNode) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        {
            NodeStyle childElement = modelElement.getOwnedStyle();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DotEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == SquareEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == EllipseEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == LozengeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == BundledImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == NoteEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == WorkspaceImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == GaugeCompositeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == CustomStyleEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeContainer_3008SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode4EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeList_3009SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNodeList modelElement = (DNodeList) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode4EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNode_3012SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DNode modelElement = (DNode) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        {
            NodeStyle childElement = modelElement.getOwnedStyle();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == BundledImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == DotEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == GaugeCompositeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == SquareEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == EllipseEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == LozengeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == WorkspaceImageEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
            if (visualID == CustomStyleEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
            }
        }
        for (Iterator<DNode> it = modelElement.getOwnedBorderedNodes().iterator(); it.hasNext();) {
            DNode childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode4EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeContainerViewNodeContainerCompartment_7001SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }

        /* handle the case of a container change its style from list to normal */
        final EObject container = containerView.getElement();
        if (container instanceof DNodeList)
            return getDNodeListViewNodeListCompartment_7004SemanticChildren(view);

        DNodeContainer modelElement = (DNodeContainer) container;

        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DDiagramElement> it = modelElement.getOwnedDiagramElements().iterator(); it.hasNext();) {
            DDiagramElement childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode3EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeContainer2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeList2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeContainerViewNodeContainerCompartment_7002SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }

        /* handle the case of a container change its style from list to normal */
        final EObject container = containerView.getElement();
        if (container instanceof DNodeList)
            return getDNodeListViewNodeListCompartment_7003SemanticChildren(view);

        DNodeContainer modelElement = (DNodeContainer) container;
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DDiagramElement> it = modelElement.getOwnedDiagramElements().iterator(); it.hasNext();) {
            DDiagramElement childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNode3EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeContainer2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeList2EditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeListViewNodeListCompartment_7003SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }

        /* handle the case of a container change its style from normal to list */
        final EObject container = containerView.getElement();
        if (container instanceof DNodeContainer)
            return getDNodeContainerViewNodeContainerCompartment_7002SemanticChildren(view);

        DNodeList modelElement = (DNodeList) container;
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNodeListElement> it = modelElement.getOwnedElements().iterator(); it.hasNext();) {
            DNodeListElement childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNodeListElementEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDNodeListViewNodeListCompartment_7004SemanticChildren(View view) {
        if (false == view.eContainer() instanceof View) {
            return Collections.emptyList();
        }
        View containerView = (View) view.eContainer();
        if (!containerView.isSetElement()) {
            return Collections.emptyList();
        }

        /* handle the case of a container change its style from normal to list */
        final EObject container = containerView.getElement();
        if (container instanceof DNodeContainer)
            return getDNodeContainerViewNodeContainerCompartment_7001SemanticChildren(view);

        DNodeList modelElement = (DNodeList) container;
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DNodeListElement> it = modelElement.getOwnedElements().iterator(); it.hasNext();) {
            DNodeListElement childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNodeListElementEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusNodeDescriptor> getDDiagram_1000SemanticChildren(View view) {
        if (!view.isSetElement()) {
            return Collections.emptyList();
        }
        DDiagram modelElement = (DDiagram) view.getElement();
        List<SiriusNodeDescriptor> result = new LinkedList<SiriusNodeDescriptor>();
        for (Iterator<DDiagramElement> it = modelElement.getOwnedDiagramElements().iterator(); it.hasNext();) {
            DDiagramElement childElement = it.next();
            int visualID = SiriusVisualIDRegistry.getNodeVisualID(view, childElement);
            if (visualID == DNodeEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeContainerEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
            if (visualID == DNodeListEditPart.VISUAL_ID) {
                result.add(new SiriusNodeDescriptor(childElement, visualID));
                continue;
            }
        }
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getContainedLinks(View view) {
        switch (SiriusVisualIDRegistry.getVisualID(view)) {
        case DDiagramEditPart.VISUAL_ID:
            return getDDiagram_1000ContainedLinks(view);
        case DNodeEditPart.VISUAL_ID:
            return getDNode_2001ContainedLinks(view);
        case DNodeContainerEditPart.VISUAL_ID:
            return getDNodeContainer_2002ContainedLinks(view);
        case DNodeListEditPart.VISUAL_ID:
            return getDNodeList_2003ContainedLinks(view);
        case DNode2EditPart.VISUAL_ID:
            return getDNode_3001ContainedLinks(view);
        case BundledImageEditPart.VISUAL_ID:
            return getBundledImage_3004ContainedLinks(view);
        case DotEditPart.VISUAL_ID:
            return getDot_3002ContainedLinks(view);
        case GaugeCompositeEditPart.VISUAL_ID:
            return getGaugeCompositeStyle_3006ContainedLinks(view);
        case SquareEditPart.VISUAL_ID:
            return getSquare_3003ContainedLinks(view);
        case EllipseEditPart.VISUAL_ID:
            return getEllipse_3016ContainedLinks(view);
        case LozengeEditPart.VISUAL_ID:
            return getLozenge_3017ContainedLinks(view);
        case WorkspaceImageEditPart.VISUAL_ID:
            return getWorkspaceImage_3005ContainedLinks(view);
        case NoteEditPart.VISUAL_ID:
            return getNote_3013ContainedLinks(view);
        case CustomStyleEditPart.VISUAL_ID:
            return getCustomStyle_3014ContainedLinks(view);
        case DNode3EditPart.VISUAL_ID:
            return getDNode_3007ContainedLinks(view);
        case DNodeContainer2EditPart.VISUAL_ID:
            return getDNodeContainer_3008ContainedLinks(view);
        case DNodeList2EditPart.VISUAL_ID:
            return getDNodeList_3009ContainedLinks(view);
        case DNodeListElementEditPart.VISUAL_ID:
            return getDNodeListElement_3010ContainedLinks(view);
        case DNode4EditPart.VISUAL_ID:
            return getDNode_3012ContainedLinks(view);
        case DEdgeEditPart.VISUAL_ID:
            return getDEdge_4001ContainedLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getIncomingLinks(View view) {
        switch (SiriusVisualIDRegistry.getVisualID(view)) {
        case DNodeEditPart.VISUAL_ID:
            return getDNode_2001IncomingLinks(view);
        case DNodeContainerEditPart.VISUAL_ID:
            return getDNodeContainer_2002IncomingLinks(view);
        case DNodeListEditPart.VISUAL_ID:
            return getDNodeList_2003IncomingLinks(view);
        case DNode2EditPart.VISUAL_ID:
            return getDNode_3001IncomingLinks(view);
        case BundledImageEditPart.VISUAL_ID:
            return getBundledImage_3004IncomingLinks(view);
        case DotEditPart.VISUAL_ID:
            return getDot_3002IncomingLinks(view);
        case GaugeCompositeEditPart.VISUAL_ID:
            return getGaugeCompositeStyle_3006IncomingLinks(view);
        case SquareEditPart.VISUAL_ID:
            return getSquare_3003IncomingLinks(view);
        case EllipseEditPart.VISUAL_ID:
            return getEllipse_3016IncomingLinks(view);
        case LozengeEditPart.VISUAL_ID:
            return getLozenge_3017IncomingLinks(view);
        case WorkspaceImageEditPart.VISUAL_ID:
            return getWorkspaceImage_3005IncomingLinks(view);
        case NoteEditPart.VISUAL_ID:
            return getNote_3013IncomingLinks(view);
        case CustomStyleEditPart.VISUAL_ID:
            return getCustomStyle_3014IncomingLinks(view);
        case DNode3EditPart.VISUAL_ID:
            return getDNode_3007IncomingLinks(view);
        case DNodeContainer2EditPart.VISUAL_ID:
            return getDNodeContainer_3008IncomingLinks(view);
        case DNodeList2EditPart.VISUAL_ID:
            return getDNodeList_3009IncomingLinks(view);
        case DNodeListElementEditPart.VISUAL_ID:
            return getDNodeListElement_3010IncomingLinks(view);
        case DNode4EditPart.VISUAL_ID:
            return getDNode_3012IncomingLinks(view);
        case DEdgeEditPart.VISUAL_ID:
            return getDEdge_4001IncomingLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getOutgoingLinks(View view) {
        switch (SiriusVisualIDRegistry.getVisualID(view)) {
        case DNodeEditPart.VISUAL_ID:
            return getDNode_2001OutgoingLinks(view);
        case DNodeContainerEditPart.VISUAL_ID:
            return getDNodeContainer_2002OutgoingLinks(view);
        case DNodeListEditPart.VISUAL_ID:
            return getDNodeList_2003OutgoingLinks(view);
        case DNode2EditPart.VISUAL_ID:
            return getDNode_3001OutgoingLinks(view);
        case BundledImageEditPart.VISUAL_ID:
            return getBundledImage_3004OutgoingLinks(view);
        case DotEditPart.VISUAL_ID:
            return getDot_3002OutgoingLinks(view);
        case GaugeCompositeEditPart.VISUAL_ID:
            return getGaugeCompositeStyle_3006OutgoingLinks(view);
        case SquareEditPart.VISUAL_ID:
            return getSquare_3003OutgoingLinks(view);
        case EllipseEditPart.VISUAL_ID:
            return getEllipse_3016OutgoingLinks(view);
        case LozengeEditPart.VISUAL_ID:
            return getLozenge_3017OutgoingLinks(view);
        case WorkspaceImageEditPart.VISUAL_ID:
            return getWorkspaceImage_3005OutgoingLinks(view);
        case NoteEditPart.VISUAL_ID:
            return getNote_3013OutgoingLinks(view);
        case CustomStyleEditPart.VISUAL_ID:
            return getCustomStyle_3014OutgoingLinks(view);
        case DNode3EditPart.VISUAL_ID:
            return getDNode_3007OutgoingLinks(view);
        case DNodeContainer2EditPart.VISUAL_ID:
            return getDNodeContainer_3008OutgoingLinks(view);
        case DNodeList2EditPart.VISUAL_ID:
            return getDNodeList_3009OutgoingLinks(view);
        case DNodeListElementEditPart.VISUAL_ID:
            return getDNodeListElement_3010OutgoingLinks(view);
        case DNode4EditPart.VISUAL_ID:
            return getDNode_3012OutgoingLinks(view);
        case DEdgeEditPart.VISUAL_ID:
            return getDEdge_4001OutgoingLinks(view);
        }
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDDiagram_1000ContainedLinks(View view) {
        DDiagram modelElement = (DDiagram) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getContainedTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNode_2001ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_2002ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeList_2003ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNode_3001ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDot_3002ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getSquare_3003ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getEllipse_3016ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getLozenge_3017ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getBundledImage_3004ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getWorkspaceImage_3005ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getNote_3013ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getGaugeCompositeStyle_3006ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getCustomStyle_3014ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNode_3007ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_3008ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeList_3009ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeListElement_3010ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNode_3012ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDEdge_4001ContainedLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_2001IncomingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_2002IncomingLinks(View view) {
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeList_2003IncomingLinks(View view) {
        DNodeList modelElement = (DNodeList) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNode_3001IncomingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDot_3002IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getSquare_3003IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getEllipse_3016IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getLozenge_3017IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getBundledImage_3004IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getWorkspaceImage_3005IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getNote_3013IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getGaugeCompositeStyle_3006IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getCustomStyle_3014IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_3007IncomingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_3008IncomingLinks(View view) {
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeList_3009IncomingLinks(View view) {
        DNodeList modelElement = (DNodeList) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeListElement_3010IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_3012IncomingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences = EcoreUtil.CrossReferencer.find(view.eResource().getResourceSet().getResources());
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getIncomingTypeModelFacetLinks_DEdge(modelElement, crossReferences));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDEdge_4001IncomingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_2001OutgoingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_2002OutgoingLinks(View view) {
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeList_2003OutgoingLinks(View view) {
        DNodeList modelElement = (DNodeList) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_3001OutgoingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDot_3002OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getSquare_3003OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getEllipse_3016OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getLozenge_3017OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getBundledImage_3004OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getWorkspaceImage_3005OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getNote_3013OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getGaugeCompositeStyle_3006OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getCustomStyle_3014OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_3007OutgoingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeContainer_3008OutgoingLinks(View view) {
        DNodeContainer modelElement = (DNodeContainer) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNodeList_3009OutgoingLinks(View view) {
        DNodeList modelElement = (DNodeList) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDNodeListElement_3010OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * @was-generated NOT
     */
    public static List<SiriusLinkDescriptor> getDNode_3012OutgoingLinks(View view) {
        DNode modelElement = (DNode) view.getElement();
        List<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        result.addAll(getOutgoingTypeModelFacetLinks_DEdge(modelElement));
        return result;
    }

    /**
     * @was-generated
     */
    public static List<SiriusLinkDescriptor> getDEdge_4001OutgoingLinks(View view) {
        return Collections.emptyList();
    }

    /**
     * Now that we have dimension specific edge, we must return a descriptor for
     * dimension specific edge.
     * 
     * @not-generated
     */
    private static Collection<SiriusLinkDescriptor> getContainedTypeModelFacetLinks_DEdge(DDiagram container) {
        Collection<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        for (Iterator<DDiagramElement> links = container.getOwnedDiagramElements().iterator(); links.hasNext();) {
            DDiagramElement linkObject = links.next();
            if (false == linkObject instanceof DEdge) {
                continue;
            }
            DEdge link = (DEdge) linkObject;
            int visualID = SiriusVisualIDRegistry.getLinkWithClassVisualID(link);
            if (DEdgeEditPart.VISUAL_ID != visualID && BracketEdgeEditPart.VISUAL_ID != visualID) {
                continue;
            }
            EdgeTarget dst = link.getTargetNode();
            EdgeTarget src = link.getSourceNode();
            if (DEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, dst, link, SiriusElementTypes.DEdge_4001, DEdgeEditPart.VISUAL_ID));
            } else if (BracketEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, dst, link, SiriusElementTypes.BracketEdge_4002, BracketEdgeEditPart.VISUAL_ID));
            }
        }
        return result;
    }

    /**
     * @was-generated NOT
     */
    private static Collection<SiriusLinkDescriptor> getIncomingTypeModelFacetLinks_DEdge(EdgeTarget target, Map<EObject, Collection<EStructuralFeature.Setting>> crossReferences) {
        Collection<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        Collection<EStructuralFeature.Setting> settings = crossReferences.get(target);
        for (Iterator<EStructuralFeature.Setting> it = settings.iterator(); it.hasNext();) {
            EStructuralFeature.Setting setting = it.next();
            if (setting.getEStructuralFeature() != DiagramPackage.eINSTANCE.getDEdge_TargetNode() || false == setting.getEObject() instanceof DEdge) {
                continue;
            }
            DEdge link = (DEdge) setting.getEObject();
            int visualID = SiriusVisualIDRegistry.getLinkWithClassVisualID(link);
            if (DEdgeEditPart.VISUAL_ID != visualID && BracketEdgeEditPart.VISUAL_ID != visualID) {
                continue;
            }
            EdgeTarget src = link.getSourceNode();
            if (DEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, target, link, SiriusElementTypes.DEdge_4001, DEdgeEditPart.VISUAL_ID));
            } else if (BracketEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, target, link, SiriusElementTypes.BracketEdge_4002, BracketEdgeEditPart.VISUAL_ID));
            }
        }
        return result;
    }

    /**
     * @was-generated NOT
     */
    private static Collection<SiriusLinkDescriptor> getOutgoingTypeModelFacetLinks_DEdge(EdgeTarget source) {
        DDiagram container = null;
        // Find container element for the link.
        // Climb up by containment hierarchy starting from the source
        // and return the first element that is instance of the container class.
        for (EObject element = source; element != null && container == null; element = element.eContainer()) {
            if (element instanceof DDiagram) {
                container = (DDiagram) element;
            }
        }
        if (container == null) {
            return Collections.emptyList();
        }
        Collection<SiriusLinkDescriptor> result = new LinkedList<SiriusLinkDescriptor>();
        for (Iterator<DDiagramElement> links = container.getOwnedDiagramElements().iterator(); links.hasNext();) {
            DDiagramElement linkObject = links.next();
            if (false == linkObject instanceof DEdge) {
                continue;
            }
            DEdge link = (DEdge) linkObject;
            int visualID = SiriusVisualIDRegistry.getLinkWithClassVisualID(link);
            if (DEdgeEditPart.VISUAL_ID != visualID && BracketEdgeEditPart.VISUAL_ID != visualID) {
                continue;
            }
            EdgeTarget dst = link.getTargetNode();
            EdgeTarget src = link.getSourceNode();
            if (src != source) {
                continue;
            }
            if (DEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, dst, link, SiriusElementTypes.DEdge_4001, DEdgeEditPart.VISUAL_ID));
            } else if (BracketEdgeEditPart.VISUAL_ID == visualID) {
                result.add(new SiriusLinkDescriptor(src, dst, link, SiriusElementTypes.BracketEdge_4002, BracketEdgeEditPart.VISUAL_ID));
            }
        }
        return result;
    }

}
