/**
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.ContainerStyle;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.FilterVariableHistory;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.FoldingFilter;
import org.eclipse.sirius.diagram.FoldingPointFilter;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.diagram.GraphicalFilter;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelCapabilityStyle;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;

/**
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance hierarchy. It supports the call
 * {@link #doSwitch(EObject) doSwitch(object)} to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the inheritance hierarchy until a non-null result is
 * returned, which is the result of the switch. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage
 * @generated
 */
public class DiagramSwitch<T> {
    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DiagramPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DiagramSwitch() {
        if (DiagramSwitch.modelPackage == null) {
            DiagramSwitch.modelPackage = DiagramPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == DiagramSwitch.modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that
     * result. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the first non-null result returned by a <code>caseXXX</code> call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case DiagramPackage.DDIAGRAM: {
            DDiagram dDiagram = (DDiagram) theEObject;
            T result = caseDDiagram(dDiagram);
            if (result == null) {
                result = caseDRepresentation(dDiagram);
            }
            if (result == null) {
                result = caseDocumentedElement(dDiagram);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dDiagram);
            }
            if (result == null) {
                result = caseIdentifiedElement(dDiagram);
            }
            if (result == null) {
                result = caseDModelElement(dDiagram);
            }
            if (result == null) {
                result = caseDRefreshable(dDiagram);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DSEMANTIC_DIAGRAM: {
            DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) theEObject;
            T result = caseDSemanticDiagram(dSemanticDiagram);
            if (result == null) {
                result = caseDDiagram(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDRepresentation(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDocumentedElement(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dSemanticDiagram);
            }
            if (result == null) {
                result = caseIdentifiedElement(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDModelElement(dSemanticDiagram);
            }
            if (result == null) {
                result = caseDRefreshable(dSemanticDiagram);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DDIAGRAM_ELEMENT: {
            DDiagramElement dDiagramElement = (DDiagramElement) theEObject;
            T result = caseDDiagramElement(dDiagramElement);
            if (result == null) {
                result = caseDRepresentationElement(dDiagramElement);
            }
            if (result == null) {
                result = caseDMappingBased(dDiagramElement);
            }
            if (result == null) {
                result = caseDStylizable(dDiagramElement);
            }
            if (result == null) {
                result = caseDRefreshable(dDiagramElement);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dDiagramElement);
            }
            if (result == null) {
                result = caseIdentifiedElement(dDiagramElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.GRAPHICAL_FILTER: {
            GraphicalFilter graphicalFilter = (GraphicalFilter) theEObject;
            T result = caseGraphicalFilter(graphicalFilter);
            if (result == null) {
                result = caseIdentifiedElement(graphicalFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.HIDE_FILTER: {
            HideFilter hideFilter = (HideFilter) theEObject;
            T result = caseHideFilter(hideFilter);
            if (result == null) {
                result = caseGraphicalFilter(hideFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(hideFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.HIDE_LABEL_FILTER: {
            HideLabelFilter hideLabelFilter = (HideLabelFilter) theEObject;
            T result = caseHideLabelFilter(hideLabelFilter);
            if (result == null) {
                result = caseGraphicalFilter(hideLabelFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(hideLabelFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.FOLDING_POINT_FILTER: {
            FoldingPointFilter foldingPointFilter = (FoldingPointFilter) theEObject;
            T result = caseFoldingPointFilter(foldingPointFilter);
            if (result == null) {
                result = caseGraphicalFilter(foldingPointFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(foldingPointFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.FOLDING_FILTER: {
            FoldingFilter foldingFilter = (FoldingFilter) theEObject;
            T result = caseFoldingFilter(foldingFilter);
            if (result == null) {
                result = caseGraphicalFilter(foldingFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(foldingFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS: {
            AppliedCompositeFilters appliedCompositeFilters = (AppliedCompositeFilters) theEObject;
            T result = caseAppliedCompositeFilters(appliedCompositeFilters);
            if (result == null) {
                result = caseGraphicalFilter(appliedCompositeFilters);
            }
            if (result == null) {
                result = caseIdentifiedElement(appliedCompositeFilters);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.ABSOLUTE_BOUNDS_FILTER: {
            AbsoluteBoundsFilter absoluteBoundsFilter = (AbsoluteBoundsFilter) theEObject;
            T result = caseAbsoluteBoundsFilter(absoluteBoundsFilter);
            if (result == null) {
                result = caseGraphicalFilter(absoluteBoundsFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(absoluteBoundsFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.ABSTRACT_DNODE: {
            AbstractDNode abstractDNode = (AbstractDNode) theEObject;
            T result = caseAbstractDNode(abstractDNode);
            if (result == null) {
                result = caseDDiagramElement(abstractDNode);
            }
            if (result == null) {
                result = caseDRepresentationElement(abstractDNode);
            }
            if (result == null) {
                result = caseDMappingBased(abstractDNode);
            }
            if (result == null) {
                result = caseDStylizable(abstractDNode);
            }
            if (result == null) {
                result = caseDRefreshable(abstractDNode);
            }
            if (result == null) {
                result = caseDSemanticDecorator(abstractDNode);
            }
            if (result == null) {
                result = caseIdentifiedElement(abstractDNode);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DNODE: {
            DNode dNode = (DNode) theEObject;
            T result = caseDNode(dNode);
            if (result == null) {
                result = caseAbstractDNode(dNode);
            }
            if (result == null) {
                result = caseEdgeTarget(dNode);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dNode);
            }
            if (result == null) {
                result = caseDDiagramElement(dNode);
            }
            if (result == null) {
                result = caseDRepresentationElement(dNode);
            }
            if (result == null) {
                result = caseDMappingBased(dNode);
            }
            if (result == null) {
                result = caseDStylizable(dNode);
            }
            if (result == null) {
                result = caseDRefreshable(dNode);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dNode);
            }
            if (result == null) {
                result = caseIdentifiedElement(dNode);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER: {
            DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) theEObject;
            T result = caseDDiagramElementContainer(dDiagramElementContainer);
            if (result == null) {
                result = caseAbstractDNode(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseEdgeTarget(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDDiagramElement(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDRepresentationElement(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDMappingBased(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDStylizable(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDRefreshable(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dDiagramElementContainer);
            }
            if (result == null) {
                result = caseIdentifiedElement(dDiagramElementContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DNODE_CONTAINER: {
            DNodeContainer dNodeContainer = (DNodeContainer) theEObject;
            T result = caseDNodeContainer(dNodeContainer);
            if (result == null) {
                result = caseDDiagramElementContainer(dNodeContainer);
            }
            if (result == null) {
                result = caseAbstractDNode(dNodeContainer);
            }
            if (result == null) {
                result = caseEdgeTarget(dNodeContainer);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dNodeContainer);
            }
            if (result == null) {
                result = caseDDiagramElement(dNodeContainer);
            }
            if (result == null) {
                result = caseDRepresentationElement(dNodeContainer);
            }
            if (result == null) {
                result = caseDMappingBased(dNodeContainer);
            }
            if (result == null) {
                result = caseDStylizable(dNodeContainer);
            }
            if (result == null) {
                result = caseDRefreshable(dNodeContainer);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dNodeContainer);
            }
            if (result == null) {
                result = caseIdentifiedElement(dNodeContainer);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DNODE_LIST: {
            DNodeList dNodeList = (DNodeList) theEObject;
            T result = caseDNodeList(dNodeList);
            if (result == null) {
                result = caseDDiagramElementContainer(dNodeList);
            }
            if (result == null) {
                result = caseAbstractDNode(dNodeList);
            }
            if (result == null) {
                result = caseEdgeTarget(dNodeList);
            }
            if (result == null) {
                result = caseDragAndDropTarget(dNodeList);
            }
            if (result == null) {
                result = caseDDiagramElement(dNodeList);
            }
            if (result == null) {
                result = caseDRepresentationElement(dNodeList);
            }
            if (result == null) {
                result = caseDMappingBased(dNodeList);
            }
            if (result == null) {
                result = caseDStylizable(dNodeList);
            }
            if (result == null) {
                result = caseDRefreshable(dNodeList);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dNodeList);
            }
            if (result == null) {
                result = caseIdentifiedElement(dNodeList);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DNODE_LIST_ELEMENT: {
            DNodeListElement dNodeListElement = (DNodeListElement) theEObject;
            T result = caseDNodeListElement(dNodeListElement);
            if (result == null) {
                result = caseAbstractDNode(dNodeListElement);
            }
            if (result == null) {
                result = caseDDiagramElement(dNodeListElement);
            }
            if (result == null) {
                result = caseDRepresentationElement(dNodeListElement);
            }
            if (result == null) {
                result = caseDMappingBased(dNodeListElement);
            }
            if (result == null) {
                result = caseDStylizable(dNodeListElement);
            }
            if (result == null) {
                result = caseDRefreshable(dNodeListElement);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dNodeListElement);
            }
            if (result == null) {
                result = caseIdentifiedElement(dNodeListElement);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DEDGE: {
            DEdge dEdge = (DEdge) theEObject;
            T result = caseDEdge(dEdge);
            if (result == null) {
                result = caseDDiagramElement(dEdge);
            }
            if (result == null) {
                result = caseEdgeTarget(dEdge);
            }
            if (result == null) {
                result = caseDRepresentationElement(dEdge);
            }
            if (result == null) {
                result = caseDMappingBased(dEdge);
            }
            if (result == null) {
                result = caseDStylizable(dEdge);
            }
            if (result == null) {
                result = caseDRefreshable(dEdge);
            }
            if (result == null) {
                result = caseDSemanticDecorator(dEdge);
            }
            if (result == null) {
                result = caseIdentifiedElement(dEdge);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.NODE_STYLE: {
            NodeStyle nodeStyle = (NodeStyle) theEObject;
            T result = caseNodeStyle(nodeStyle);
            if (result == null) {
                result = caseLabelStyle(nodeStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(nodeStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(nodeStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(nodeStyle);
            }
            if (result == null) {
                result = caseStyle(nodeStyle);
            }
            if (result == null) {
                result = caseDRefreshable(nodeStyle);
            }
            if (result == null) {
                result = caseCustomizable(nodeStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(nodeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DOT: {
            Dot dot = (Dot) theEObject;
            T result = caseDot(dot);
            if (result == null) {
                result = caseNodeStyle(dot);
            }
            if (result == null) {
                result = caseLabelStyle(dot);
            }
            if (result == null) {
                result = caseBorderedStyle(dot);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(dot);
            }
            if (result == null) {
                result = caseBasicLabelStyle(dot);
            }
            if (result == null) {
                result = caseStyle(dot);
            }
            if (result == null) {
                result = caseDRefreshable(dot);
            }
            if (result == null) {
                result = caseCustomizable(dot);
            }
            if (result == null) {
                result = caseIdentifiedElement(dot);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.GAUGE_SECTION: {
            GaugeSection gaugeSection = (GaugeSection) theEObject;
            T result = caseGaugeSection(gaugeSection);
            if (result == null) {
                result = caseCustomizable(gaugeSection);
            }
            if (result == null) {
                result = caseIdentifiedElement(gaugeSection);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.CONTAINER_STYLE: {
            ContainerStyle containerStyle = (ContainerStyle) theEObject;
            T result = caseContainerStyle(containerStyle);
            if (result == null) {
                result = caseLabelStyle(containerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(containerStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(containerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(containerStyle);
            }
            if (result == null) {
                result = caseStyle(containerStyle);
            }
            if (result == null) {
                result = caseDRefreshable(containerStyle);
            }
            if (result == null) {
                result = caseCustomizable(containerStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(containerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.FLAT_CONTAINER_STYLE: {
            FlatContainerStyle flatContainerStyle = (FlatContainerStyle) theEObject;
            T result = caseFlatContainerStyle(flatContainerStyle);
            if (result == null) {
                result = caseContainerStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseLabelStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseStyle(flatContainerStyle);
            }
            if (result == null) {
                result = caseDRefreshable(flatContainerStyle);
            }
            if (result == null) {
                result = caseCustomizable(flatContainerStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(flatContainerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.SHAPE_CONTAINER_STYLE: {
            ShapeContainerStyle shapeContainerStyle = (ShapeContainerStyle) theEObject;
            T result = caseShapeContainerStyle(shapeContainerStyle);
            if (result == null) {
                result = caseContainerStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseLabelStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseStyle(shapeContainerStyle);
            }
            if (result == null) {
                result = caseDRefreshable(shapeContainerStyle);
            }
            if (result == null) {
                result = caseCustomizable(shapeContainerStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(shapeContainerStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.SQUARE: {
            Square square = (Square) theEObject;
            T result = caseSquare(square);
            if (result == null) {
                result = caseNodeStyle(square);
            }
            if (result == null) {
                result = caseLabelStyle(square);
            }
            if (result == null) {
                result = caseBorderedStyle(square);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(square);
            }
            if (result == null) {
                result = caseBasicLabelStyle(square);
            }
            if (result == null) {
                result = caseStyle(square);
            }
            if (result == null) {
                result = caseDRefreshable(square);
            }
            if (result == null) {
                result = caseCustomizable(square);
            }
            if (result == null) {
                result = caseIdentifiedElement(square);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.ELLIPSE: {
            Ellipse ellipse = (Ellipse) theEObject;
            T result = caseEllipse(ellipse);
            if (result == null) {
                result = caseNodeStyle(ellipse);
            }
            if (result == null) {
                result = caseLabelStyle(ellipse);
            }
            if (result == null) {
                result = caseBorderedStyle(ellipse);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(ellipse);
            }
            if (result == null) {
                result = caseBasicLabelStyle(ellipse);
            }
            if (result == null) {
                result = caseStyle(ellipse);
            }
            if (result == null) {
                result = caseDRefreshable(ellipse);
            }
            if (result == null) {
                result = caseCustomizable(ellipse);
            }
            if (result == null) {
                result = caseIdentifiedElement(ellipse);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.LOZENGE: {
            Lozenge lozenge = (Lozenge) theEObject;
            T result = caseLozenge(lozenge);
            if (result == null) {
                result = caseNodeStyle(lozenge);
            }
            if (result == null) {
                result = caseLabelStyle(lozenge);
            }
            if (result == null) {
                result = caseBorderedStyle(lozenge);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(lozenge);
            }
            if (result == null) {
                result = caseBasicLabelStyle(lozenge);
            }
            if (result == null) {
                result = caseStyle(lozenge);
            }
            if (result == null) {
                result = caseDRefreshable(lozenge);
            }
            if (result == null) {
                result = caseCustomizable(lozenge);
            }
            if (result == null) {
                result = caseIdentifiedElement(lozenge);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.BUNDLED_IMAGE: {
            BundledImage bundledImage = (BundledImage) theEObject;
            T result = caseBundledImage(bundledImage);
            if (result == null) {
                result = caseNodeStyle(bundledImage);
            }
            if (result == null) {
                result = caseLabelStyle(bundledImage);
            }
            if (result == null) {
                result = caseBorderedStyle(bundledImage);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(bundledImage);
            }
            if (result == null) {
                result = caseBasicLabelStyle(bundledImage);
            }
            if (result == null) {
                result = caseStyle(bundledImage);
            }
            if (result == null) {
                result = caseDRefreshable(bundledImage);
            }
            if (result == null) {
                result = caseCustomizable(bundledImage);
            }
            if (result == null) {
                result = caseIdentifiedElement(bundledImage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.WORKSPACE_IMAGE: {
            WorkspaceImage workspaceImage = (WorkspaceImage) theEObject;
            T result = caseWorkspaceImage(workspaceImage);
            if (result == null) {
                result = caseNodeStyle(workspaceImage);
            }
            if (result == null) {
                result = caseContainerStyle(workspaceImage);
            }
            if (result == null) {
                result = caseLabelStyle(workspaceImage);
            }
            if (result == null) {
                result = caseBorderedStyle(workspaceImage);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(workspaceImage);
            }
            if (result == null) {
                result = caseBasicLabelStyle(workspaceImage);
            }
            if (result == null) {
                result = caseStyle(workspaceImage);
            }
            if (result == null) {
                result = caseDRefreshable(workspaceImage);
            }
            if (result == null) {
                result = caseCustomizable(workspaceImage);
            }
            if (result == null) {
                result = caseIdentifiedElement(workspaceImage);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.CUSTOM_STYLE: {
            CustomStyle customStyle = (CustomStyle) theEObject;
            T result = caseCustomStyle(customStyle);
            if (result == null) {
                result = caseNodeStyle(customStyle);
            }
            if (result == null) {
                result = caseLabelStyle(customStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(customStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(customStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(customStyle);
            }
            if (result == null) {
                result = caseStyle(customStyle);
            }
            if (result == null) {
                result = caseDRefreshable(customStyle);
            }
            if (result == null) {
                result = caseCustomizable(customStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(customStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.EDGE_TARGET: {
            EdgeTarget edgeTarget = (EdgeTarget) theEObject;
            T result = caseEdgeTarget(edgeTarget);
            if (result == null) {
                result = caseIdentifiedElement(edgeTarget);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.EDGE_STYLE: {
            EdgeStyle edgeStyle = (EdgeStyle) theEObject;
            T result = caseEdgeStyle(edgeStyle);
            if (result == null) {
                result = caseStyle(edgeStyle);
            }
            if (result == null) {
                result = caseDRefreshable(edgeStyle);
            }
            if (result == null) {
                result = caseCustomizable(edgeStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(edgeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.GAUGE_COMPOSITE_STYLE: {
            GaugeCompositeStyle gaugeCompositeStyle = (GaugeCompositeStyle) theEObject;
            T result = caseGaugeCompositeStyle(gaugeCompositeStyle);
            if (result == null) {
                result = caseNodeStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseLabelStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseBorderedStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseBasicLabelStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseStyle(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseDRefreshable(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseCustomizable(gaugeCompositeStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(gaugeCompositeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.BORDERED_STYLE: {
            BorderedStyle borderedStyle = (BorderedStyle) theEObject;
            T result = caseBorderedStyle(borderedStyle);
            if (result == null) {
                result = caseStyle(borderedStyle);
            }
            if (result == null) {
                result = caseDRefreshable(borderedStyle);
            }
            if (result == null) {
                result = caseCustomizable(borderedStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(borderedStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.NOTE: {
            Note note = (Note) theEObject;
            T result = caseNote(note);
            if (result == null) {
                result = caseNodeStyle(note);
            }
            if (result == null) {
                result = caseLabelStyle(note);
            }
            if (result == null) {
                result = caseBorderedStyle(note);
            }
            if (result == null) {
                result = caseHideLabelCapabilityStyle(note);
            }
            if (result == null) {
                result = caseBasicLabelStyle(note);
            }
            if (result == null) {
                result = caseStyle(note);
            }
            if (result == null) {
                result = caseDRefreshable(note);
            }
            if (result == null) {
                result = caseCustomizable(note);
            }
            if (result == null) {
                result = caseIdentifiedElement(note);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.FILTER_VARIABLE_HISTORY: {
            FilterVariableHistory filterVariableHistory = (FilterVariableHistory) theEObject;
            T result = caseFilterVariableHistory(filterVariableHistory);
            if (result == null) {
                result = caseIdentifiedElement(filterVariableHistory);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.COLLAPSE_FILTER: {
            CollapseFilter collapseFilter = (CollapseFilter) theEObject;
            T result = caseCollapseFilter(collapseFilter);
            if (result == null) {
                result = caseGraphicalFilter(collapseFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(collapseFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.INDIRECTLY_COLLAPSE_FILTER: {
            IndirectlyCollapseFilter indirectlyCollapseFilter = (IndirectlyCollapseFilter) theEObject;
            T result = caseIndirectlyCollapseFilter(indirectlyCollapseFilter);
            if (result == null) {
                result = caseCollapseFilter(indirectlyCollapseFilter);
            }
            if (result == null) {
                result = caseGraphicalFilter(indirectlyCollapseFilter);
            }
            if (result == null) {
                result = caseIdentifiedElement(indirectlyCollapseFilter);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.BEGIN_LABEL_STYLE: {
            BeginLabelStyle beginLabelStyle = (BeginLabelStyle) theEObject;
            T result = caseBeginLabelStyle(beginLabelStyle);
            if (result == null) {
                result = caseBasicLabelStyle(beginLabelStyle);
            }
            if (result == null) {
                result = caseCustomizable(beginLabelStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(beginLabelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.CENTER_LABEL_STYLE: {
            CenterLabelStyle centerLabelStyle = (CenterLabelStyle) theEObject;
            T result = caseCenterLabelStyle(centerLabelStyle);
            if (result == null) {
                result = caseBasicLabelStyle(centerLabelStyle);
            }
            if (result == null) {
                result = caseCustomizable(centerLabelStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(centerLabelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.END_LABEL_STYLE: {
            EndLabelStyle endLabelStyle = (EndLabelStyle) theEObject;
            T result = caseEndLabelStyle(endLabelStyle);
            if (result == null) {
                result = caseBasicLabelStyle(endLabelStyle);
            }
            if (result == null) {
                result = caseCustomizable(endLabelStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(endLabelStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.BRACKET_EDGE_STYLE: {
            BracketEdgeStyle bracketEdgeStyle = (BracketEdgeStyle) theEObject;
            T result = caseBracketEdgeStyle(bracketEdgeStyle);
            if (result == null) {
                result = caseEdgeStyle(bracketEdgeStyle);
            }
            if (result == null) {
                result = caseStyle(bracketEdgeStyle);
            }
            if (result == null) {
                result = caseDRefreshable(bracketEdgeStyle);
            }
            if (result == null) {
                result = caseCustomizable(bracketEdgeStyle);
            }
            if (result == null) {
                result = caseIdentifiedElement(bracketEdgeStyle);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY: {
            ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = (ComputedStyleDescriptionRegistry) theEObject;
            T result = caseComputedStyleDescriptionRegistry(computedStyleDescriptionRegistry);
            if (result == null) {
                result = caseIdentifiedElement(computedStyleDescriptionRegistry);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.DRAG_AND_DROP_TARGET: {
            DragAndDropTarget dragAndDropTarget = (DragAndDropTarget) theEObject;
            T result = caseDragAndDropTarget(dragAndDropTarget);
            if (result == null) {
                result = caseIdentifiedElement(dragAndDropTarget);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.HIDE_LABEL_CAPABILITY_STYLE: {
            HideLabelCapabilityStyle hideLabelCapabilityStyle = (HideLabelCapabilityStyle) theEObject;
            T result = caseHideLabelCapabilityStyle(hideLabelCapabilityStyle);
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.VARIABLE_VALUE: {
            VariableValue variableValue = (VariableValue) theEObject;
            T result = caseVariableValue(variableValue);
            if (result == null) {
                result = caseIdentifiedElement(variableValue);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.TYPED_VARIABLE_VALUE: {
            TypedVariableValue typedVariableValue = (TypedVariableValue) theEObject;
            T result = caseTypedVariableValue(typedVariableValue);
            if (result == null) {
                result = caseVariableValue(typedVariableValue);
            }
            if (result == null) {
                result = caseIdentifiedElement(typedVariableValue);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        case DiagramPackage.EOBJECT_VARIABLE_VALUE: {
            EObjectVariableValue eObjectVariableValue = (EObjectVariableValue) theEObject;
            T result = caseEObjectVariableValue(eObjectVariableValue);
            if (result == null) {
                result = caseVariableValue(eObjectVariableValue);
            }
            if (result == null) {
                result = caseIdentifiedElement(eObjectVariableValue);
            }
            if (result == null) {
                result = defaultCase(theEObject);
            }
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DDiagram</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DDiagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagram(DDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DSemantic Diagram</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DSemantic Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDiagram(DSemanticDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DDiagram Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DDiagram Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramElement(DDiagramElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Graphical Filter</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Graphical Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphicalFilter(GraphicalFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hide Filter</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hide Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideFilter(HideFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hide Label Filter</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hide Label Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideLabelFilter(HideLabelFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Folding Point Filter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Folding Point Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFoldingPointFilter(FoldingPointFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Folding Filter</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Folding Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFoldingFilter(FoldingFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Applied Composite Filters</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Applied Composite Filters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAppliedCompositeFilters(AppliedCompositeFilters object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Absolute Bounds Filter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Absolute Bounds Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteBoundsFilter(AbsoluteBoundsFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Abstract DNode</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Abstract DNode</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractDNode(AbstractDNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DNode</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DNode</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNode(DNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DDiagram Element Container</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DDiagram Element Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramElementContainer(DDiagramElementContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DNode Container</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DNode Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeContainer(DNodeContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DNode List</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DNode List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeList(DNodeList object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DNode List Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DNode List Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeListElement(DNodeListElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DEdge</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DEdge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDEdge(DEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Node Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Node Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeStyle(NodeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Dot</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Dot</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDot(Dot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gauge Section</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gauge Section</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeSection(GaugeSection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Container Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerStyle(ContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Flat Container Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Flat Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlatContainerStyle(FlatContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Shape Container Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Shape Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeContainerStyle(ShapeContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Square</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Square</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSquare(Square object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Ellipse</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Ellipse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEllipse(Ellipse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Lozenge</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Lozenge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLozenge(Lozenge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bundled Image</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bundled Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBundledImage(BundledImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Workspace Image</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Workspace Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWorkspaceImage(WorkspaceImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Custom Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Custom Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomStyle(CustomStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Target</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeTarget(EdgeTarget object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Edge Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Edge Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeStyle(EdgeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Gauge Composite Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Gauge Composite Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeCompositeStyle(GaugeCompositeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bordered Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bordered Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBorderedStyle(BorderedStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Note</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Note</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNote(Note object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Filter Variable History</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Filter Variable History</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilterVariableHistory(FilterVariableHistory object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Collapse Filter</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Collapse Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCollapseFilter(CollapseFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Indirectly Collapse Filter</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Indirectly Collapse Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndirectlyCollapseFilter(IndirectlyCollapseFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Begin Label Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Begin Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBeginLabelStyle(BeginLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Center Label Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Center Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCenterLabelStyle(CenterLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>End Label Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>End Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndLabelStyle(EndLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Bracket Edge Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Bracket Edge Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBracketEdgeStyle(BracketEdgeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Computed Style Description Registry</em>'.
     * <!-- begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Computed Style Description Registry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComputedStyleDescriptionRegistry(ComputedStyleDescriptionRegistry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Drag And Drop Target</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Drag And Drop Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDragAndDropTarget(DragAndDropTarget object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Hide Label Capability Style</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Hide Label Capability Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideLabelCapabilityStyle(HideLabelCapabilityStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Variable Value</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Variable Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseVariableValue(VariableValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Typed Variable Value</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Typed Variable Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseTypedVariableValue(TypedVariableValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject Variable Value</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject Variable Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEObjectVariableValue(EObjectVariableValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Identified Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Identified Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIdentifiedElement(IdentifiedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Documented Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRefreshable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRefreshable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRefreshable(DRefreshable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DModel Element</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRepresentation</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRepresentation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentation(DRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DSemantic Decorator</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DSemantic Decorator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDecorator(DSemanticDecorator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DMapping Based</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DMapping Based</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDMappingBased(DMappingBased object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DStylizable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DStylizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDStylizable(DStylizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>DRepresentation Element</em>'. <!--
     * begin-user-doc --> This implementation returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>DRepresentation Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationElement(DRepresentationElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Customizable</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Customizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomizable(Customizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Basic Label Style</em>'. <!-- begin-user-doc
     * --> This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Basic Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyle(BasicLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Label Style</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyle(LabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '<em>EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate the switch, but this is the last case
     * anyway. <!-- end-user-doc -->
     *
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // DiagramSwitch
