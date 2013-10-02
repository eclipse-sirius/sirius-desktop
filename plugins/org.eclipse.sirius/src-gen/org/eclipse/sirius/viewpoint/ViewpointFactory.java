/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a
 * create method for each non-abstract class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public interface ViewpointFactory extends EFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.\nAll rights reserved. This program and the accompanying materials\nare made available under the terms of the Eclipse Public License v1.0\nwhich accompanies this distribution, and is available at\nhttp://www.eclipse.org/legal/epl-v10.html\n\nContributors:\n   Obeo - initial API and implementation\n";

    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    ViewpointFactory eINSTANCE = org.eclipse.sirius.viewpoint.impl.ViewpointFactoryImpl.init();

    /**
     * Returns a new object of class '<em>DAnalysis</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DAnalysis</em>'.
     * @generated
     */
    DAnalysis createDAnalysis();

    /**
     * Returns a new object of class '<em>DRepresentation Container</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DRepresentation Container</em>'.
     * @generated
     */
    DRepresentationContainer createDRepresentationContainer();

    /**
     * Returns a new object of class '<em>DView</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DView</em>'.
     * @generated
     */
    DView createDView();

    /**
     * Returns a new object of class '<em>Meta Model Extension</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Meta Model Extension</em>'.
     * @generated
     */
    MetaModelExtension createMetaModelExtension();

    /**
     * Returns a new object of class '<em>DDiagram</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DDiagram</em>'.
     * @generated
     */
    DDiagram createDDiagram();

    /**
     * Returns a new object of class '<em>DSemantic Diagram</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DSemantic Diagram</em>'.
     * @generated
     */
    DSemanticDiagram createDSemanticDiagram();

    /**
     * Returns a new object of class '<em>Hide Filter</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Hide Filter</em>'.
     * @generated
     */
    HideFilter createHideFilter();

    /**
     * Returns a new object of class '<em>Hide Label Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Hide Label Filter</em>'.
     * @generated
     */
    HideLabelFilter createHideLabelFilter();

    /**
     * Returns a new object of class '<em>Folding Point Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Folding Point Filter</em>'.
     * @generated
     */
    FoldingPointFilter createFoldingPointFilter();

    /**
     * Returns a new object of class '<em>Folding Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Folding Filter</em>'.
     * @generated
     */
    FoldingFilter createFoldingFilter();

    /**
     * Returns a new object of class '<em>Applied Composite Filters</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Applied Composite Filters</em>'.
     * @generated
     */
    AppliedCompositeFilters createAppliedCompositeFilters();

    /**
     * Returns a new object of class '<em>Absolute Bounds Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Absolute Bounds Filter</em>'.
     * @generated
     */
    AbsoluteBoundsFilter createAbsoluteBoundsFilter();

    /**
     * Returns a new object of class '<em>Decoration</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Decoration</em>'.
     * @generated
     */
    Decoration createDecoration();

    /**
     * Returns a new object of class '<em>DE Object Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DE Object Link</em>'.
     * @generated
     */
    DEObjectLink createDEObjectLink();

    /**
     * Returns a new object of class '<em>DDiagram Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DDiagram Link</em>'.
     * @generated
     */
    DDiagramLink createDDiagramLink();

    /**
     * Returns a new object of class '<em>DSource File Link</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DSource File Link</em>'.
     * @generated
     */
    DSourceFileLink createDSourceFileLink();

    /**
     * Returns a new object of class '<em>DNode</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DNode</em>'.
     * @generated
     */
    DNode createDNode();

    /**
     * Returns a new object of class '<em>DNode Container</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DNode Container</em>'.
     * @generated
     */
    DNodeContainer createDNodeContainer();

    /**
     * Returns a new object of class '<em>DNode List</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DNode List</em>'.
     * @generated
     */
    DNodeList createDNodeList();

    /**
     * Returns a new object of class '<em>DNode List Element</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DNode List Element</em>'.
     * @generated
     */
    DNodeListElement createDNodeListElement();

    /**
     * Returns a new object of class '<em>DEdge</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DEdge</em>'.
     * @generated
     */
    DEdge createDEdge();

    /**
     * Returns a new object of class '<em>DDiagram Set</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DDiagram Set</em>'.
     * @generated
     */
    DDiagramSet createDDiagramSet();

    /**
     * Returns a new object of class '<em>Dot</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Dot</em>'.
     * @generated
     */
    Dot createDot();

    /**
     * Returns a new object of class '<em>Square</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Square</em>'.
     * @generated
     */
    Square createSquare();

    /**
     * Returns a new object of class '<em>Bundled Image</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Bundled Image</em>'.
     * @generated
     */
    BundledImage createBundledImage();

    /**
     * Returns a new object of class '<em>Workspace Image</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Workspace Image</em>'.
     * @generated
     */
    WorkspaceImage createWorkspaceImage();

    /**
     * Returns a new object of class '<em>Custom Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Custom Style</em>'.
     * @generated
     */
    CustomStyle createCustomStyle();

    /**
     * Returns a new object of class '<em>Gauge Section</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Gauge Section</em>'.
     * @generated
     */
    GaugeSection createGaugeSection();

    /**
     * Returns a new object of class '<em>Flat Container Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Flat Container Style</em>'.
     * @generated
     */
    FlatContainerStyle createFlatContainerStyle();

    /**
     * Returns a new object of class '<em>Shape Container Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Shape Container Style</em>'.
     * @generated
     */
    ShapeContainerStyle createShapeContainerStyle();

    /**
     * Returns a new object of class '<em>Edge Style</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Edge Style</em>'.
     * @generated
     */
    EdgeStyle createEdgeStyle();

    /**
     * Returns a new object of class '<em>Bracket Edge Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Bracket Edge Style</em>'.
     * @generated
     */
    BracketEdgeStyle createBracketEdgeStyle();

    /**
     * Returns a new object of class '
     * <em>Computed Style Description Registry</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '
     *         <em>Computed Style Description Registry</em>'.
     * @generated
     */
    ComputedStyleDescriptionRegistry createComputedStyleDescriptionRegistry();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return the package supported by this factory.
     * @generated
     */
    ViewpointPackage getViewpointPackage();

    /**
     * Returns a new object of class '<em>DAnalysis Custom Data</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DAnalysis Custom Data</em>'.
     * @generated
     */
    DAnalysisCustomData createDAnalysisCustomData();

    /**
     * Returns a new object of class '<em>Gauge Composite Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Gauge Composite Style</em>'.
     * @generated
     */
    GaugeCompositeStyle createGaugeCompositeStyle();

    /**
     * Returns a new object of class '<em>Label Style</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Label Style</em>'.
     * @generated
     */
    LabelStyle createLabelStyle();

    /**
     * Returns a new object of class '<em>Bordered Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Bordered Style</em>'.
     * @generated
     */
    BorderedStyle createBorderedStyle();

    /**
     * Returns a new object of class '<em>Note</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Note</em>'.
     * @generated
     */
    Note createNote();

    /**
     * Returns a new object of class '<em>Drag And Drop Target</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Drag And Drop Target</em>'.
     * @generated
     */
    DragAndDropTarget createDragAndDropTarget();

    /**
     * Returns a new object of class '<em>Filter Variable History</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Filter Variable History</em>'.
     * @generated
     */
    FilterVariableHistory createFilterVariableHistory();

    /**
     * Returns a new object of class '<em>Filter Variable Value</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Filter Variable Value</em>'.
     * @generated
     */
    FilterVariableValue createFilterVariableValue();

    /**
     * Returns a new object of class '<em>RGB Values</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>RGB Values</em>'.
     * @generated
     */
    RGBValues createRGBValues();

    /**
     * Returns a new object of class '<em>DAnalysis Session EObject</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DAnalysis Session EObject</em>'.
     * @generated
     */
    DAnalysisSessionEObject createDAnalysisSessionEObject();

    /**
     * Returns a new object of class '<em>Collapse Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Collapse Filter</em>'.
     * @generated
     */
    CollapseFilter createCollapseFilter();

    /**
     * Returns a new object of class '<em>Indirectly Collapse Filter</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Indirectly Collapse Filter</em>'.
     * @generated
     */
    IndirectlyCollapseFilter createIndirectlyCollapseFilter();

    /**
     * Returns a new object of class '<em>Session Manager EObject</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Session Manager EObject</em>'.
     * @generated
     */
    SessionManagerEObject createSessionManagerEObject();

    /**
     * Returns a new object of class '<em>DFile</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DFile</em>'.
     * @generated
     */
    DFile createDFile();

    /**
     * Returns a new object of class '<em>DResource Container</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DResource Container</em>'.
     * @generated
     */
    DResourceContainer createDResourceContainer();

    /**
     * Returns a new object of class '<em>DProject</em>'. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DProject</em>'.
     * @generated
     */
    DProject createDProject();

    /**
     * Returns a new object of class '<em>DFolder</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DFolder</em>'.
     * @generated
     */
    DFolder createDFolder();

    /**
     * Returns a new object of class '<em>DModel</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>DModel</em>'.
     * @generated
     */
    DModel createDModel();

    /**
     * Returns a new object of class '<em>Basic Label Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Basic Label Style</em>'.
     * @generated
     */
    BasicLabelStyle createBasicLabelStyle();

    /**
     * Returns a new object of class '<em>Begin Label Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Begin Label Style</em>'.
     * @generated
     */
    BeginLabelStyle createBeginLabelStyle();

    /**
     * Returns a new object of class '<em>Center Label Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Center Label Style</em>'.
     * @generated
     */
    CenterLabelStyle createCenterLabelStyle();

    /**
     * Returns a new object of class '<em>End Label Style</em>'. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>End Label Style</em>'.
     * @generated
     */
    EndLabelStyle createEndLabelStyle();

    /**
     * Returns a new object of class '<em>Ellipse</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Ellipse</em>'.
     * @generated
     */
    Ellipse createEllipse();

    /**
     * Returns a new object of class '<em>Lozenge</em>'. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @return a new object of class '<em>Lozenge</em>'.
     * @generated
     */
    Lozenge createLozenge();

} // SiriusFactory
