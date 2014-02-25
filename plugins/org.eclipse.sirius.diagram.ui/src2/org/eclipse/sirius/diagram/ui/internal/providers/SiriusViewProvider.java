/*******************************************************************************
 * Copyright (c) 2007, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.IHintedType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.business.internal.bracket.BracketEdgeFactory;
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
import org.eclipse.sirius.diagram.ui.internal.view.factories.BundledImageViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.CustomStyleViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DDiagramViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DEdgeBeginNameViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DEdgeEndNameViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DEdgeNameViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DEdgeViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNode2ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNode3ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNode4ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeContainer2ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeContainerNameViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeContainerViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeContainerViewNodeContainerCompartment2ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeContainerViewNodeContainerCompartmentViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeList2ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeListElementViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeListViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeListViewNodeListCompartment2ViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeListViewNodeListCompartmentViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeNameViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DNodeViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.DotViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.EllipseViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.GaugeCompositeViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.LozengeViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.NoteViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.SquareViewFactory;
import org.eclipse.sirius.diagram.ui.internal.view.factories.WorkspaceImageViewFactory;
import org.eclipse.sirius.diagram.ui.part.SiriusVisualIDRegistry;

/**
 * @was-generated
 */
public class SiriusViewProvider extends AbstractViewProvider {

    /**
     * @was-generated
     */
    protected Class<?> getDiagramViewClass(IAdaptable semanticAdapter, String diagramKind) {
        EObject semanticElement = getSemanticElement(semanticAdapter);
        if (DDiagramEditPart.MODEL_ID.equals(diagramKind) && SiriusVisualIDRegistry.getDiagramVisualID(semanticElement) != -1) {
            return DDiagramViewFactory.class;
        }
        return null;
    }

    /**
     * @was-generated
     */
    protected Class<?> getNodeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        if (containerView == null) {
            return null;
        }
        IElementType elementType = getSemanticElementType(semanticAdapter);
        EObject domainElement = getSemanticElement(semanticAdapter);
        int visualID;
        if (semanticHint == null) {
            // Semantic hint is not specified. Can be a result of call from
            // CanonicalEditPolicy.
            // In this situation there should be NO elementType, visualID will
            // be determined
            // by VisualIDRegistry.getNodeVisualID() for domainElement.
            if (elementType != null || domainElement == null) {
                return null;
            }
            visualID = SiriusVisualIDRegistry.getNodeVisualID(containerView, domainElement);
        } else {
            visualID = SiriusVisualIDRegistry.getVisualID(semanticHint);
            if (elementType != null) {
                // Semantic hint is specified together with element type.
                // Both parameters should describe exactly the same diagram
                // element.
                // In addition we check that visualID returned by
                // VisualIDRegistry.getNodeVisualID() for
                // domainElement (if specified) is the same as in element type.
                if (!SiriusElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
                    return null; // foreign element type
                }
                String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
                if (!semanticHint.equals(elementTypeHint)) {
                    return null; // if semantic hint is specified it should
                                 // be the same as in element type
                }
                if (domainElement != null && visualID != SiriusVisualIDRegistry.getNodeVisualID(containerView, domainElement)) {
                    return null; // visual id for node EClass should match
                                 // visual id from element type
                }
            } else {
                // Element type is not specified. Domain element should be
                // present (except pure design elements).
                // This method is called with EObjectAdapter as parameter from:
                // - ViewService.createNode(View container, EObject eObject,
                // String type, PreferencesHint preferencesHint)
                // - generated ViewFactory.decorateView() for parent element
                if (!DDiagramEditPart.MODEL_ID.equals(SiriusVisualIDRegistry.getModelID(containerView))) {
                    return null; // foreign diagram
                }
                switch (visualID) {
                case DNodeEditPart.VISUAL_ID:
                case DNodeContainerEditPart.VISUAL_ID:
                case BundledImageEditPart.VISUAL_ID:
                case DotEditPart.VISUAL_ID:
                case GaugeCompositeEditPart.VISUAL_ID:
                case SquareEditPart.VISUAL_ID:
                case EllipseEditPart.VISUAL_ID:
                case LozengeEditPart.VISUAL_ID:
                case WorkspaceImageEditPart.VISUAL_ID:
                case NoteEditPart.VISUAL_ID:
                case CustomStyleEditPart.VISUAL_ID:
                case DNodeList2EditPart.VISUAL_ID:
                case DNodeListElementEditPart.VISUAL_ID:
                case DNodeListEditPart.VISUAL_ID:
                case DNode2EditPart.VISUAL_ID:
                case DNode3EditPart.VISUAL_ID:
                case DNodeContainer2EditPart.VISUAL_ID:
                case DNode4EditPart.VISUAL_ID:
                    if (domainElement == null || visualID != SiriusVisualIDRegistry.getNodeVisualID(containerView, domainElement)) {
                        return null; // visual id in semantic hint should
                                     // match visual id for domain element
                    }
                    break;
                case NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID:
                    if (DNodeEditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case DNodeContainerNameEditPart.VISUAL_ID:
                case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
                    if (DNodeContainerEditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case DNodeListNameEditPart.VISUAL_ID:
                case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
                    if (DNodeListEditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID:
                    if (DNode2EditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID:
                    if (DNode3EditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case DNodeContainerName2EditPart.VISUAL_ID:
                case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
                    if (DNodeContainer2EditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case DNodeListName2EditPart.VISUAL_ID:
                case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
                    if (DNodeList2EditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID:
                    if (DNode4EditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                case DEdgeNameEditPart.VISUAL_ID:
                case DEdgeEndNameEditPart.VISUAL_ID:
                case DEdgeBeginNameEditPart.VISUAL_ID:
                    if (DEdgeEditPart.VISUAL_ID != SiriusVisualIDRegistry.getVisualID(containerView) || containerView.getElement() != domainElement) {
                        return null; // wrong container
                    }
                    break;
                default:
                    return null;
                }
            }
        }
        return getNodeViewClass(containerView, visualID);
    }

    /**
     * @was-generated
     */
    protected Class<?> getNodeViewClass(View containerView, int visualID) {
        if (containerView == null || !SiriusVisualIDRegistry.canCreateNode(containerView, visualID)) {
            return null;
        }
        switch (visualID) {
        case DNodeEditPart.VISUAL_ID:
            return DNodeViewFactory.class;
        case DNodeContainerEditPart.VISUAL_ID:
            return DNodeContainerViewFactory.class;
        case DNodeContainerNameEditPart.VISUAL_ID:
        case DNodeContainerName2EditPart.VISUAL_ID:
        case DNodeListNameEditPart.VISUAL_ID:
        case DNodeListName2EditPart.VISUAL_ID:
            return DNodeContainerNameViewFactory.class;
        case DNodeListEditPart.VISUAL_ID:
            return DNodeListViewFactory.class;
        case DNode2EditPart.VISUAL_ID:
            return DNode2ViewFactory.class;
        case NotationViewIDs.DNODE_NAME_EDIT_PART_VISUAL_ID:
        case NotationViewIDs.DNODE_NAME_2_EDIT_PART_VISUAL_ID:
        case NotationViewIDs.DNODE_NAME_3_EDIT_PART_VISUAL_ID:
        case NotationViewIDs.DNODE_NAME_4_EDIT_PART_VISUAL_ID:
            return DNodeNameViewFactory.class;
        case BundledImageEditPart.VISUAL_ID:
            return BundledImageViewFactory.class;
        case DotEditPart.VISUAL_ID:
            return DotViewFactory.class;
        case GaugeCompositeEditPart.VISUAL_ID:
            return GaugeCompositeViewFactory.class;
        case SquareEditPart.VISUAL_ID:
            return SquareViewFactory.class;
        case EllipseEditPart.VISUAL_ID:
            return EllipseViewFactory.class;
        case LozengeEditPart.VISUAL_ID:
            return LozengeViewFactory.class;
        case WorkspaceImageEditPart.VISUAL_ID:
            return WorkspaceImageViewFactory.class;
        case NoteEditPart.VISUAL_ID:
            return NoteViewFactory.class;
        case CustomStyleEditPart.VISUAL_ID:
            return CustomStyleViewFactory.class;
        case DNode3EditPart.VISUAL_ID:
            return DNode3ViewFactory.class;
        case DNodeContainer2EditPart.VISUAL_ID:
            return DNodeContainer2ViewFactory.class;
        case DNodeList2EditPart.VISUAL_ID:
            return DNodeList2ViewFactory.class;
        case DNodeListElementEditPart.VISUAL_ID:
            return DNodeListElementViewFactory.class;
        case DNode4EditPart.VISUAL_ID:
            return DNode4ViewFactory.class;
        case DNodeContainerViewNodeContainerCompartmentEditPart.VISUAL_ID:
            return DNodeContainerViewNodeContainerCompartmentViewFactory.class;
        case DNodeContainerViewNodeContainerCompartment2EditPart.VISUAL_ID:
            return DNodeContainerViewNodeContainerCompartment2ViewFactory.class;
        case DNodeListViewNodeListCompartmentEditPart.VISUAL_ID:
            return DNodeListViewNodeListCompartmentViewFactory.class;
        case DNodeListViewNodeListCompartment2EditPart.VISUAL_ID:
            return DNodeListViewNodeListCompartment2ViewFactory.class;
        case DEdgeNameEditPart.VISUAL_ID:
            return DEdgeNameViewFactory.class;
        case DEdgeBeginNameEditPart.VISUAL_ID:
            return DEdgeBeginNameViewFactory.class;
        case DEdgeEndNameEditPart.VISUAL_ID:
            return DEdgeEndNameViewFactory.class;
        }
        return null;
    }

    /**
     * @was-generated
     */
    protected Class<?> getEdgeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        IElementType elementType = getSemanticElementType(semanticAdapter);
        if (!SiriusElementTypes.isKnownElementType(elementType) || (!(elementType instanceof IHintedType))) {
            return null; // foreign element type
        }
        String elementTypeHint = ((IHintedType) elementType).getSemanticHint();
        if (elementTypeHint == null) {
            return null; // our hint is visual id and must be specified
        }
        if (semanticHint != null && !semanticHint.equals(elementTypeHint)) {
            return null; // if semantic hint is specified it should be the
                         // same as in element type
        }
        int visualID = SiriusVisualIDRegistry.getVisualID(elementTypeHint);
        EObject domainElement = getSemanticElement(semanticAdapter);
        if (domainElement != null && visualID != SiriusVisualIDRegistry.getLinkWithClassVisualID(domainElement)) {
            return null; // visual id for link EClass should match visual id
                         // from element type
        }
        return getEdgeViewClass(visualID);
    }

    /**
     * Now that we have dimension specific edge, we must return the
     * DimensionEdgeFactory factory to create the corresponding Edge.
     * 
     * @not-generated
     */
    protected Class<?> getEdgeViewClass(int visualID) {
        switch (visualID) {
        case DEdgeEditPart.VISUAL_ID:
            return DEdgeViewFactory.class;
        case BracketEdgeEditPart.VISUAL_ID:
            return BracketEdgeFactory.class;
        }
        return null;
    }

    /**
     * @was-generated
     */
    private IElementType getSemanticElementType(IAdaptable semanticAdapter) {
        if (semanticAdapter == null) {
            return null;
        }
        return (IElementType) semanticAdapter.getAdapter(IElementType.class);
    }
}
