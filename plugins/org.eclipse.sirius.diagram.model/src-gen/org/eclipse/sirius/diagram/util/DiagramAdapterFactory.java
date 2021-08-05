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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage
 * @generated
 */
public class DiagramAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected static DiagramPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public DiagramAdapterFactory() {
        if (DiagramAdapterFactory.modelPackage == null) {
            DiagramAdapterFactory.modelPackage = DiagramPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
     * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
     * the model. <!-- end-user-doc -->
     *
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == DiagramAdapterFactory.modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == DiagramAdapterFactory.modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected DiagramSwitch<Adapter> modelSwitch = new DiagramSwitch<Adapter>() {
        @Override
        public Adapter caseDDiagram(DDiagram object) {
            return createDDiagramAdapter();
        }

        @Override
        public Adapter caseDSemanticDiagram(DSemanticDiagram object) {
            return createDSemanticDiagramAdapter();
        }

        @Override
        public Adapter caseDDiagramElement(DDiagramElement object) {
            return createDDiagramElementAdapter();
        }

        @Override
        public Adapter caseGraphicalFilter(GraphicalFilter object) {
            return createGraphicalFilterAdapter();
        }

        @Override
        public Adapter caseHideFilter(HideFilter object) {
            return createHideFilterAdapter();
        }

        @Override
        public Adapter caseHideLabelFilter(HideLabelFilter object) {
            return createHideLabelFilterAdapter();
        }

        @Override
        public Adapter caseFoldingPointFilter(FoldingPointFilter object) {
            return createFoldingPointFilterAdapter();
        }

        @Override
        public Adapter caseFoldingFilter(FoldingFilter object) {
            return createFoldingFilterAdapter();
        }

        @Override
        public Adapter caseAppliedCompositeFilters(AppliedCompositeFilters object) {
            return createAppliedCompositeFiltersAdapter();
        }

        @Override
        public Adapter caseAbsoluteBoundsFilter(AbsoluteBoundsFilter object) {
            return createAbsoluteBoundsFilterAdapter();
        }

        @Override
        public Adapter caseAbstractDNode(AbstractDNode object) {
            return createAbstractDNodeAdapter();
        }

        @Override
        public Adapter caseDNode(DNode object) {
            return createDNodeAdapter();
        }

        @Override
        public Adapter caseDDiagramElementContainer(DDiagramElementContainer object) {
            return createDDiagramElementContainerAdapter();
        }

        @Override
        public Adapter caseDNodeContainer(DNodeContainer object) {
            return createDNodeContainerAdapter();
        }

        @Override
        public Adapter caseDNodeList(DNodeList object) {
            return createDNodeListAdapter();
        }

        @Override
        public Adapter caseDNodeListElement(DNodeListElement object) {
            return createDNodeListElementAdapter();
        }

        @Override
        public Adapter caseDEdge(DEdge object) {
            return createDEdgeAdapter();
        }

        @Override
        public Adapter caseNodeStyle(NodeStyle object) {
            return createNodeStyleAdapter();
        }

        @Override
        public Adapter caseDot(Dot object) {
            return createDotAdapter();
        }

        @Override
        public Adapter caseGaugeSection(GaugeSection object) {
            return createGaugeSectionAdapter();
        }

        @Override
        public Adapter caseContainerStyle(ContainerStyle object) {
            return createContainerStyleAdapter();
        }

        @Override
        public Adapter caseFlatContainerStyle(FlatContainerStyle object) {
            return createFlatContainerStyleAdapter();
        }

        @Override
        public Adapter caseShapeContainerStyle(ShapeContainerStyle object) {
            return createShapeContainerStyleAdapter();
        }

        @Override
        public Adapter caseSquare(Square object) {
            return createSquareAdapter();
        }

        @Override
        public Adapter caseEllipse(Ellipse object) {
            return createEllipseAdapter();
        }

        @Override
        public Adapter caseLozenge(Lozenge object) {
            return createLozengeAdapter();
        }

        @Override
        public Adapter caseBundledImage(BundledImage object) {
            return createBundledImageAdapter();
        }

        @Override
        public Adapter caseWorkspaceImage(WorkspaceImage object) {
            return createWorkspaceImageAdapter();
        }

        @Override
        public Adapter caseCustomStyle(CustomStyle object) {
            return createCustomStyleAdapter();
        }

        @Override
        public Adapter caseEdgeTarget(EdgeTarget object) {
            return createEdgeTargetAdapter();
        }

        @Override
        public Adapter caseEdgeStyle(EdgeStyle object) {
            return createEdgeStyleAdapter();
        }

        @Override
        public Adapter caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return createGaugeCompositeStyleAdapter();
        }

        @Override
        public Adapter caseBorderedStyle(BorderedStyle object) {
            return createBorderedStyleAdapter();
        }

        @Override
        public Adapter caseNote(Note object) {
            return createNoteAdapter();
        }

        @Override
        public Adapter caseFilterVariableHistory(FilterVariableHistory object) {
            return createFilterVariableHistoryAdapter();
        }

        @Override
        public Adapter caseCollapseFilter(CollapseFilter object) {
            return createCollapseFilterAdapter();
        }

        @Override
        public Adapter caseIndirectlyCollapseFilter(IndirectlyCollapseFilter object) {
            return createIndirectlyCollapseFilterAdapter();
        }

        @Override
        public Adapter caseBeginLabelStyle(BeginLabelStyle object) {
            return createBeginLabelStyleAdapter();
        }

        @Override
        public Adapter caseCenterLabelStyle(CenterLabelStyle object) {
            return createCenterLabelStyleAdapter();
        }

        @Override
        public Adapter caseEndLabelStyle(EndLabelStyle object) {
            return createEndLabelStyleAdapter();
        }

        @Override
        public Adapter caseBracketEdgeStyle(BracketEdgeStyle object) {
            return createBracketEdgeStyleAdapter();
        }

        @Override
        public Adapter caseComputedStyleDescriptionRegistry(ComputedStyleDescriptionRegistry object) {
            return createComputedStyleDescriptionRegistryAdapter();
        }

        @Override
        public Adapter caseDragAndDropTarget(DragAndDropTarget object) {
            return createDragAndDropTargetAdapter();
        }

        @Override
        public Adapter caseHideLabelCapabilityStyle(HideLabelCapabilityStyle object) {
            return createHideLabelCapabilityStyleAdapter();
        }

        @Override
        public Adapter caseVariableValue(VariableValue object) {
            return createVariableValueAdapter();
        }

        @Override
        public Adapter caseTypedVariableValue(TypedVariableValue object) {
            return createTypedVariableValueAdapter();
        }

        @Override
        public Adapter caseEObjectVariableValue(EObjectVariableValue object) {
            return createEObjectVariableValueAdapter();
        }

        @Override
        public Adapter caseIdentifiedElement(IdentifiedElement object) {
            return createIdentifiedElementAdapter();
        }

        @Override
        public Adapter caseDModelElement(DModelElement object) {
            return createDModelElementAdapter();
        }

        @Override
        public Adapter caseDRefreshable(DRefreshable object) {
            return createDRefreshableAdapter();
        }

        @Override
        public Adapter caseDRepresentation(DRepresentation object) {
            return createDRepresentationAdapter();
        }

        @Override
        public Adapter caseDSemanticDecorator(DSemanticDecorator object) {
            return createDSemanticDecoratorAdapter();
        }

        @Override
        public Adapter caseDMappingBased(DMappingBased object) {
            return createDMappingBasedAdapter();
        }

        @Override
        public Adapter caseDStylizable(DStylizable object) {
            return createDStylizableAdapter();
        }

        @Override
        public Adapter caseDRepresentationElement(DRepresentationElement object) {
            return createDRepresentationElementAdapter();
        }

        @Override
        public Adapter caseCustomizable(Customizable object) {
            return createCustomizableAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyle(BasicLabelStyle object) {
            return createBasicLabelStyleAdapter();
        }

        @Override
        public Adapter caseLabelStyle(LabelStyle object) {
            return createLabelStyleAdapter();
        }

        @Override
        public Adapter caseStyle(Style object) {
            return createStyleAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param target
     *            the object to adapt.
     * @return the adapter for the <code>target</code>.
     * @generated
     */
    @Override
    public Adapter createAdapter(Notifier target) {
        return modelSwitch.doSwitch((EObject) target);
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DDiagram <em>DDiagram</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DDiagram
     * @generated
     */
    public Adapter createDDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DSemanticDiagram <em>DSemantic
     * Diagram</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DSemanticDiagram
     * @generated
     */
    public Adapter createDSemanticDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DDiagramElement <em>DDiagram
     * Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DDiagramElement
     * @generated
     */
    public Adapter createDDiagramElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.GraphicalFilter <em>Graphical
     * Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.GraphicalFilter
     * @generated
     */
    public Adapter createGraphicalFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.HideFilter <em>Hide
     * Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.HideFilter
     * @generated
     */
    public Adapter createHideFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.HideLabelFilter <em>Hide Label
     * Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.HideLabelFilter
     * @generated
     */
    public Adapter createHideLabelFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.FoldingPointFilter <em>Folding
     * Point Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.FoldingPointFilter
     * @generated
     */
    public Adapter createFoldingPointFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.FoldingFilter <em>Folding
     * Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.FoldingFilter
     * @generated
     */
    public Adapter createFoldingFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.AppliedCompositeFilters
     * @generated
     */
    public Adapter createAppliedCompositeFiltersAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.AbsoluteBoundsFilter <em>Absolute
     * Bounds Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.AbsoluteBoundsFilter
     * @generated
     */
    public Adapter createAbsoluteBoundsFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.AbstractDNode <em>Abstract
     * DNode</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.AbstractDNode
     * @generated
     */
    public Adapter createAbstractDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.DNode <em>DNode</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DNode
     * @generated
     */
    public Adapter createDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DDiagramElementContainer
     * @generated
     */
    public Adapter createDDiagramElementContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DNodeContainer <em>DNode
     * Container</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DNodeContainer
     * @generated
     */
    public Adapter createDNodeContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DNodeList <em>DNode List</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DNodeList
     * @generated
     */
    public Adapter createDNodeListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DNodeListElement <em>DNode List
     * Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DNodeListElement
     * @generated
     */
    public Adapter createDNodeListElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.DEdge <em>DEdge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DEdge
     * @generated
     */
    public Adapter createDEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.NodeStyle <em>Node Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.NodeStyle
     * @generated
     */
    public Adapter createNodeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.Dot <em>Dot</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.Dot
     * @generated
     */
    public Adapter createDotAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.GaugeSection <em>Gauge
     * Section</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.GaugeSection
     * @generated
     */
    public Adapter createGaugeSectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.ContainerStyle <em>Container
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.ContainerStyle
     * @generated
     */
    public Adapter createContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.FlatContainerStyle <em>Flat
     * Container Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.FlatContainerStyle
     * @generated
     */
    public Adapter createFlatContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.ShapeContainerStyle <em>Shape
     * Container Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.ShapeContainerStyle
     * @generated
     */
    public Adapter createShapeContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.Square <em>Square</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.Square
     * @generated
     */
    public Adapter createSquareAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.Ellipse
     * @generated
     */
    public Adapter createEllipseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.Lozenge
     * @generated
     */
    public Adapter createLozengeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.BundledImage <em>Bundled
     * Image</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.BundledImage
     * @generated
     */
    public Adapter createBundledImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.WorkspaceImage <em>Workspace
     * Image</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.WorkspaceImage
     * @generated
     */
    public Adapter createWorkspaceImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.CustomStyle <em>Custom
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.CustomStyle
     * @generated
     */
    public Adapter createCustomStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.EdgeTarget <em>Edge
     * Target</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.EdgeTarget
     * @generated
     */
    public Adapter createEdgeTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.EdgeStyle <em>Edge Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
     * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.EdgeStyle
     * @generated
     */
    public Adapter createEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.GaugeCompositeStyle <em>Gauge
     * Composite Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.GaugeCompositeStyle
     * @generated
     */
    public Adapter createGaugeCompositeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.BorderedStyle <em>Bordered
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.BorderedStyle
     * @generated
     */
    public Adapter createBorderedStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.diagram.Note <em>Note</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.Note
     * @generated
     */
    public Adapter createNoteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.FilterVariableHistory <em>Filter
     * Variable History</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.FilterVariableHistory
     * @generated
     */
    public Adapter createFilterVariableHistoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.CollapseFilter <em>Collapse
     * Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.CollapseFilter
     * @generated
     */
    public Adapter createCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.IndirectlyCollapseFilter
     * @generated
     */
    public Adapter createIndirectlyCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.BeginLabelStyle <em>Begin Label
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.BeginLabelStyle
     * @generated
     */
    public Adapter createBeginLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.CenterLabelStyle <em>Center Label
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.CenterLabelStyle
     * @generated
     */
    public Adapter createCenterLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.EndLabelStyle <em>End Label
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.EndLabelStyle
     * @generated
     */
    public Adapter createEndLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.BracketEdgeStyle <em>Bracket Edge
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.BracketEdgeStyle
     * @generated
     */
    public Adapter createBracketEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc --> This default implementation returns null
     * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry
     * @generated
     */
    public Adapter createComputedStyleDescriptionRegistryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.DragAndDropTarget <em>Drag And
     * Drop Target</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.DragAndDropTarget
     * @generated
     */
    public Adapter createDragAndDropTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.HideLabelCapabilityStyle <em>Hide
     * Label Capability Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.HideLabelCapabilityStyle
     * @generated
     */
    public Adapter createHideLabelCapabilityStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.VariableValue <em>Variable
     * Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.VariableValue
     * @generated
     */
    public Adapter createVariableValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.TypedVariableValue <em>Typed
     * Variable Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.TypedVariableValue
     * @generated
     */
    public Adapter createTypedVariableValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.diagram.EObjectVariableValue <em>EObject
     * Variable Value</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.diagram.EObjectVariableValue
     * @generated
     */
    public Adapter createEObjectVariableValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.IdentifiedElement
     * <em>Identified Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.IdentifiedElement
     * @generated
     */
    public Adapter createIdentifiedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DRefreshable
     * <em>DRefreshable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @generated
     */
    public Adapter createDRefreshableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DModelElement
     * @generated
     */
    public Adapter createDModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DRepresentation
     * <em>DRepresentation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentation
     * @generated
     */
    public Adapter createDRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
     * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator
     * @generated
     */
    public Adapter createDSemanticDecoratorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DMappingBased <em>DMapping
     * Based</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @generated
     */
    public Adapter createDMappingBasedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DStylizable
     * <em>DStylizable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @generated
     */
    public Adapter createDStylizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
     * end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement
     * @generated
     */
    public Adapter createDRepresentationElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.Customizable
     * <em>Customizable</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
     * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
     * -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Customizable
     * @generated
     */
    public Adapter createCustomizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.BasicLabelStyle <em>Basic Label
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle
     * @generated
     */
    public Adapter createBasicLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link org.eclipse.sirius.viewpoint.LabelStyle <em>Label
     * Style</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.LabelStyle
     * @generated
     */
    public Adapter createLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class ' {@link org.eclipse.sirius.viewpoint.Style <em>Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
     * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Style
     * @generated
     */
    public Adapter createStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
     * <!-- end-user-doc -->
     *
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // DiagramAdapterFactory
