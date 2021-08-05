/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage
 * @generated
 */
public interface DiagramFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    DiagramFactory eINSTANCE = org.eclipse.sirius.diagram.impl.DiagramFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DDiagram</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DDiagram</em>'.
     * @generated
     */
    DDiagram createDDiagram();

    /**
     * Returns a new object of class '<em>DSemantic Diagram</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DSemantic Diagram</em>'.
     * @generated
     */
    DSemanticDiagram createDSemanticDiagram();

    /**
     * Returns a new object of class '<em>Hide Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Hide Filter</em>'.
     * @generated
     */
    HideFilter createHideFilter();

    /**
     * Returns a new object of class '<em>Hide Label Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Hide Label Filter</em>'.
     * @generated
     */
    HideLabelFilter createHideLabelFilter();

    /**
     * Returns a new object of class '<em>Folding Point Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Folding Point Filter</em>'.
     * @generated
     */
    FoldingPointFilter createFoldingPointFilter();

    /**
     * Returns a new object of class '<em>Folding Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Folding Filter</em>'.
     * @generated
     */
    FoldingFilter createFoldingFilter();

    /**
     * Returns a new object of class '<em>Applied Composite Filters</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Applied Composite Filters</em>'.
     * @generated
     */
    AppliedCompositeFilters createAppliedCompositeFilters();

    /**
     * Returns a new object of class '<em>Absolute Bounds Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Absolute Bounds Filter</em>'.
     * @generated
     */
    AbsoluteBoundsFilter createAbsoluteBoundsFilter();

    /**
     * Returns a new object of class '<em>DNode</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DNode</em>'.
     * @generated
     */
    DNode createDNode();

    /**
     * Returns a new object of class '<em>DNode Container</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DNode Container</em>'.
     * @generated
     */
    DNodeContainer createDNodeContainer();

    /**
     * Returns a new object of class '<em>DNode List</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DNode List</em>'.
     * @generated
     */
    DNodeList createDNodeList();

    /**
     * Returns a new object of class '<em>DNode List Element</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DNode List Element</em>'.
     * @generated
     */
    DNodeListElement createDNodeListElement();

    /**
     * Returns a new object of class '<em>DEdge</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>DEdge</em>'.
     * @generated
     */
    DEdge createDEdge();

    /**
     * Returns a new object of class '<em>Dot</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Dot</em>'.
     * @generated
     */
    Dot createDot();

    /**
     * Returns a new object of class '<em>Gauge Section</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gauge Section</em>'.
     * @generated
     */
    GaugeSection createGaugeSection();

    /**
     * Returns a new object of class '<em>Flat Container Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Flat Container Style</em>'.
     * @generated
     */
    FlatContainerStyle createFlatContainerStyle();

    /**
     * Returns a new object of class '<em>Shape Container Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Shape Container Style</em>'.
     * @generated
     */
    ShapeContainerStyle createShapeContainerStyle();

    /**
     * Returns a new object of class '<em>Square</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Square</em>'.
     * @generated
     */
    Square createSquare();

    /**
     * Returns a new object of class '<em>Ellipse</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ellipse</em>'.
     * @generated
     */
    Ellipse createEllipse();

    /**
     * Returns a new object of class '<em>Lozenge</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Lozenge</em>'.
     * @generated
     */
    Lozenge createLozenge();

    /**
     * Returns a new object of class '<em>Bundled Image</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bundled Image</em>'.
     * @generated
     */
    BundledImage createBundledImage();

    /**
     * Returns a new object of class '<em>Workspace Image</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Workspace Image</em>'.
     * @generated
     */
    WorkspaceImage createWorkspaceImage();

    /**
     * Returns a new object of class '<em>Custom Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Custom Style</em>'.
     * @generated
     */
    CustomStyle createCustomStyle();

    /**
     * Returns a new object of class '<em>Edge Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Edge Style</em>'.
     * @generated
     */
    EdgeStyle createEdgeStyle();

    /**
     * Returns a new object of class '<em>Gauge Composite Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Gauge Composite Style</em>'.
     * @generated
     */
    GaugeCompositeStyle createGaugeCompositeStyle();

    /**
     * Returns a new object of class '<em>Bordered Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bordered Style</em>'.
     * @generated
     */
    BorderedStyle createBorderedStyle();

    /**
     * Returns a new object of class '<em>Note</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Note</em>'.
     * @generated
     */
    Note createNote();

    /**
     * Returns a new object of class '<em>Filter Variable History</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Filter Variable History</em>'.
     * @generated
     */
    FilterVariableHistory createFilterVariableHistory();

    /**
     * Returns a new object of class '<em>Collapse Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Collapse Filter</em>'.
     * @generated
     */
    CollapseFilter createCollapseFilter();

    /**
     * Returns a new object of class '<em>Indirectly Collapse Filter</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Indirectly Collapse Filter</em>'.
     * @generated
     */
    IndirectlyCollapseFilter createIndirectlyCollapseFilter();

    /**
     * Returns a new object of class '<em>Begin Label Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Begin Label Style</em>'.
     * @generated
     */
    BeginLabelStyle createBeginLabelStyle();

    /**
     * Returns a new object of class '<em>Center Label Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Center Label Style</em>'.
     * @generated
     */
    CenterLabelStyle createCenterLabelStyle();

    /**
     * Returns a new object of class '<em>End Label Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>End Label Style</em>'.
     * @generated
     */
    EndLabelStyle createEndLabelStyle();

    /**
     * Returns a new object of class '<em>Bracket Edge Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Bracket Edge Style</em>'.
     * @generated
     */
    BracketEdgeStyle createBracketEdgeStyle();

    /**
     * Returns a new object of class '<em>Computed Style Description Registry</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Computed Style Description Registry</em>'.
     * @generated
     */
    ComputedStyleDescriptionRegistry createComputedStyleDescriptionRegistry();

    /**
     * Returns a new object of class '<em>Drag And Drop Target</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Drag And Drop Target</em>'.
     * @generated
     */
    DragAndDropTarget createDragAndDropTarget();

    /**
     * Returns a new object of class '<em>Typed Variable Value</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Typed Variable Value</em>'.
     * @generated
     */
    TypedVariableValue createTypedVariableValue();

    /**
     * Returns a new object of class '<em>EObject Variable Value</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>EObject Variable Value</em>'.
     * @generated
     */
    EObjectVariableValue createEObjectVariableValue();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    DiagramPackage getDiagramPackage();

} // DiagramFactory
