/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.viewpoint.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter;
import org.eclipse.sirius.viewpoint.AbstractDNode;
import org.eclipse.sirius.viewpoint.AppliedCompositeFilters;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.BeginLabelStyle;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.BracketEdgeStyle;
import org.eclipse.sirius.viewpoint.BundledImage;
import org.eclipse.sirius.viewpoint.CenterLabelStyle;
import org.eclipse.sirius.viewpoint.CollapseFilter;
import org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.viewpoint.ContainerStyle;
import org.eclipse.sirius.viewpoint.CustomStyle;
import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DContainer;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;
import org.eclipse.sirius.viewpoint.DDiagramElementContainer;
import org.eclipse.sirius.viewpoint.DDiagramLink;
import org.eclipse.sirius.viewpoint.DDiagramSet;
import org.eclipse.sirius.viewpoint.DEObjectLink;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DFeatureExtension;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DLabelled;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DNavigable;
import org.eclipse.sirius.viewpoint.DNavigationLink;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRefreshable;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DResource;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSourceFileLink;
import org.eclipse.sirius.viewpoint.DStylizable;
import org.eclipse.sirius.viewpoint.DValidable;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.Dot;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.EdgeTarget;
import org.eclipse.sirius.viewpoint.Ellipse;
import org.eclipse.sirius.viewpoint.EndLabelStyle;
import org.eclipse.sirius.viewpoint.FilterVariableHistory;
import org.eclipse.sirius.viewpoint.FilterVariableValue;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.FoldingFilter;
import org.eclipse.sirius.viewpoint.FoldingPointFilter;
import org.eclipse.sirius.viewpoint.GaugeCompositeStyle;
import org.eclipse.sirius.viewpoint.GaugeSection;
import org.eclipse.sirius.viewpoint.GraphicalFilter;
import org.eclipse.sirius.viewpoint.HideFilter;
import org.eclipse.sirius.viewpoint.HideLabelFilter;
import org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.Lozenge;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.NodeStyle;
import org.eclipse.sirius.viewpoint.Note;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.Square;
import org.eclipse.sirius.viewpoint.Style;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.eclipse.sirius.viewpoint.description.DModelElement;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.DocumentedElement;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public class ViewpointAdapterFactory extends AdapterFactoryImpl {
    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static ViewpointPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = ViewpointPackage.eINSTANCE;
        }
    }

    /**
     * Returns whether this factory is applicable for the type of the object.
     * <!-- begin-user-doc --> This implementation returns <code>true</code> if
     * the object is either the model's package or is an instance object of the
     * model. <!-- end-user-doc -->
     * 
     * @return whether this factory is applicable for the type of the object.
     * @generated
     */
    @Override
    public boolean isFactoryForType(Object object) {
        if (object == modelPackage) {
            return true;
        }
        if (object instanceof EObject) {
            return ((EObject) object).eClass().getEPackage() == modelPackage;
        }
        return false;
    }

    /**
     * The switch that delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected ViewpointSwitch<Adapter> modelSwitch = new ViewpointSwitch<Adapter>() {
        @Override
        public Adapter caseDAnalysis(DAnalysis object) {
            return createDAnalysisAdapter();
        }

        @Override
        public Adapter caseDFeatureExtension(DFeatureExtension object) {
            return createDFeatureExtensionAdapter();
        }

        @Override
        public Adapter caseDValidable(DValidable object) {
            return createDValidableAdapter();
        }

        @Override
        public Adapter caseDNavigable(DNavigable object) {
            return createDNavigableAdapter();
        }

        @Override
        public Adapter caseDStylizable(DStylizable object) {
            return createDStylizableAdapter();
        }

        @Override
        public Adapter caseDRefreshable(DRefreshable object) {
            return createDRefreshableAdapter();
        }

        @Override
        public Adapter caseDLabelled(DLabelled object) {
            return createDLabelledAdapter();
        }

        @Override
        public Adapter caseDMappingBased(DMappingBased object) {
            return createDMappingBasedAdapter();
        }

        @Override
        public Adapter caseDContainer(DContainer object) {
            return createDContainerAdapter();
        }

        @Override
        public Adapter caseDRepresentationContainer(DRepresentationContainer object) {
            return createDRepresentationContainerAdapter();
        }

        @Override
        public Adapter caseDSemanticDecorator(DSemanticDecorator object) {
            return createDSemanticDecoratorAdapter();
        }

        @Override
        public Adapter caseDRepresentation(DRepresentation object) {
            return createDRepresentationAdapter();
        }

        @Override
        public Adapter caseDRepresentationElement(DRepresentationElement object) {
            return createDRepresentationElementAdapter();
        }

        @Override
        public Adapter caseDView(DView object) {
            return createDViewAdapter();
        }

        @Override
        public Adapter caseMetaModelExtension(MetaModelExtension object) {
            return createMetaModelExtensionAdapter();
        }

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
        public Adapter caseDecoration(Decoration object) {
            return createDecorationAdapter();
        }

        @Override
        public Adapter caseDNavigationLink(DNavigationLink object) {
            return createDNavigationLinkAdapter();
        }

        @Override
        public Adapter caseDEObjectLink(DEObjectLink object) {
            return createDEObjectLinkAdapter();
        }

        @Override
        public Adapter caseDDiagramLink(DDiagramLink object) {
            return createDDiagramLinkAdapter();
        }

        @Override
        public Adapter caseDSourceFileLink(DSourceFileLink object) {
            return createDSourceFileLinkAdapter();
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
        public Adapter caseDDiagramSet(DDiagramSet object) {
            return createDDiagramSetAdapter();
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
        public Adapter caseDAnalysisCustomData(DAnalysisCustomData object) {
            return createDAnalysisCustomDataAdapter();
        }

        @Override
        public Adapter caseGaugeCompositeStyle(GaugeCompositeStyle object) {
            return createGaugeCompositeStyleAdapter();
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
        public Adapter caseBorderedStyle(BorderedStyle object) {
            return createBorderedStyleAdapter();
        }

        @Override
        public Adapter caseNote(Note object) {
            return createNoteAdapter();
        }

        @Override
        public Adapter caseDragAndDropTarget(DragAndDropTarget object) {
            return createDragAndDropTargetAdapter();
        }

        @Override
        public Adapter caseFilterVariableHistory(FilterVariableHistory object) {
            return createFilterVariableHistoryAdapter();
        }

        @Override
        public Adapter caseFilterVariableValue(FilterVariableValue object) {
            return createFilterVariableValueAdapter();
        }

        @Override
        public Adapter caseRGBValues(RGBValues object) {
            return createRGBValuesAdapter();
        }

        @Override
        public Adapter caseDAnalysisSessionEObject(DAnalysisSessionEObject object) {
            return createDAnalysisSessionEObjectAdapter();
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
        public Adapter caseSessionManagerEObject(SessionManagerEObject object) {
            return createSessionManagerEObjectAdapter();
        }

        @Override
        public Adapter caseDResource(DResource object) {
            return createDResourceAdapter();
        }

        @Override
        public Adapter caseDFile(DFile object) {
            return createDFileAdapter();
        }

        @Override
        public Adapter caseDResourceContainer(DResourceContainer object) {
            return createDResourceContainerAdapter();
        }

        @Override
        public Adapter caseDProject(DProject object) {
            return createDProjectAdapter();
        }

        @Override
        public Adapter caseDFolder(DFolder object) {
            return createDFolderAdapter();
        }

        @Override
        public Adapter caseDModel(DModel object) {
            return createDModelAdapter();
        }

        @Override
        public Adapter caseBasicLabelStyle(BasicLabelStyle object) {
            return createBasicLabelStyleAdapter();
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
        public Adapter caseCustomizable(Customizable object) {
            return createCustomizableAdapter();
        }

        @Override
        public Adapter caseComputedStyleDescriptionRegistry(ComputedStyleDescriptionRegistry object) {
            return createComputedStyleDescriptionRegistryAdapter();
        }

        @Override
        public Adapter caseDiagramElementMapping2ModelElement(Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> object) {
            return createDiagramElementMapping2ModelElementAdapter();
        }

        @Override
        public Adapter caseModelElement2ViewVariable(Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> object) {
            return createModelElement2ViewVariableAdapter();
        }

        @Override
        public Adapter caseViewVariable2ContainerVariable(Map.Entry<EObject, EMap<EObject, StyleDescription>> object) {
            return createViewVariable2ContainerVariableAdapter();
        }

        @Override
        public Adapter caseContainerVariable2StyleDescription(Map.Entry<EObject, StyleDescription> object) {
            return createContainerVariable2StyleDescriptionAdapter();
        }

        @Override
        public Adapter caseDocumentedElement(DocumentedElement object) {
            return createDocumentedElementAdapter();
        }

        @Override
        public Adapter caseDModelElement(DModelElement object) {
            return createDModelElementAdapter();
        }

        @Override
        public Adapter defaultCase(EObject object) {
            return createEObjectAdapter();
        }
    };

    /**
     * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
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
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysis
     * @generated
     */
    public Adapter createDAnalysisAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFeatureExtension
     * <em>DFeature Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFeatureExtension
     * @generated
     */
    public Adapter createDFeatureExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DValidable <em>DValidable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DValidable
     * @generated
     */
    public Adapter createDValidableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNavigable <em>DNavigable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNavigable
     * @generated
     */
    public Adapter createDNavigableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DStylizable <em>DStylizable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DStylizable
     * @generated
     */
    public Adapter createDStylizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRefreshable <em>DRefreshable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRefreshable
     * @generated
     */
    public Adapter createDRefreshableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DLabelled <em>DLabelled</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DLabelled
     * @generated
     */
    public Adapter createDLabelledAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DMappingBased
     * <em>DMapping Based</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DMappingBased
     * @generated
     */
    public Adapter createDMappingBasedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DContainer <em>DContainer</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DContainer
     * @generated
     */
    public Adapter createDContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationContainer
     * <em>DRepresentation Container</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentationContainer
     * @generated
     */
    public Adapter createDRepresentationContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DSemanticDecorator
     * @generated
     */
    public Adapter createDSemanticDecoratorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentation
     * <em>DRepresentation</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentation
     * @generated
     */
    public Adapter createDRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DRepresentationElement
     * @generated
     */
    public Adapter createDRepresentationElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DView <em>DView</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DView
     * @generated
     */
    public Adapter createDViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.MetaModelExtension
     * <em>Meta Model Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.MetaModelExtension
     * @generated
     */
    public Adapter createMetaModelExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DDiagram <em>DDiagram</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DDiagram
     * @generated
     */
    public Adapter createDDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DSemanticDiagram
     * <em>DSemantic Diagram</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DSemanticDiagram
     * @generated
     */
    public Adapter createDSemanticDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElement
     * <em>DDiagram Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DDiagramElement
     * @generated
     */
    public Adapter createDDiagramElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.GraphicalFilter
     * <em>Graphical Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.GraphicalFilter
     * @generated
     */
    public Adapter createGraphicalFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.HideFilter <em>Hide Filter</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.HideFilter
     * @generated
     */
    public Adapter createHideFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.HideLabelFilter
     * <em>Hide Label Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.HideLabelFilter
     * @generated
     */
    public Adapter createHideLabelFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.FoldingPointFilter
     * <em>Folding Point Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.FoldingPointFilter
     * @generated
     */
    public Adapter createFoldingPointFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.FoldingFilter
     * <em>Folding Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.FoldingFilter
     * @generated
     */
    public Adapter createFoldingFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.AppliedCompositeFilters
     * @generated
     */
    public Adapter createAppliedCompositeFiltersAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter
     * <em>Absolute Bounds Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter
     * @generated
     */
    public Adapter createAbsoluteBoundsFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Decoration <em>Decoration</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Decoration
     * @generated
     */
    public Adapter createDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNavigationLink
     * <em>DNavigation Link</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNavigationLink
     * @generated
     */
    public Adapter createDNavigationLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DEObjectLink <em>DE Object Link</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DEObjectLink
     * @generated
     */
    public Adapter createDEObjectLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramLink <em>DDiagram Link</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DDiagramLink
     * @generated
     */
    public Adapter createDDiagramLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DSourceFileLink
     * <em>DSource File Link</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DSourceFileLink
     * @generated
     */
    public Adapter createDSourceFileLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.AbstractDNode
     * <em>Abstract DNode</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.AbstractDNode
     * @generated
     */
    public Adapter createAbstractDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNode <em>DNode</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNode
     * @generated
     */
    public Adapter createDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DDiagramElementContainer
     * @generated
     */
    public Adapter createDDiagramElementContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNodeContainer
     * <em>DNode Container</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNodeContainer
     * @generated
     */
    public Adapter createDNodeContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNodeList
     * @generated
     */
    public Adapter createDNodeListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DNodeListElement
     * <em>DNode List Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DNodeListElement
     * @generated
     */
    public Adapter createDNodeListElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DEdge <em>DEdge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DEdge
     * @generated
     */
    public Adapter createDEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DDiagramSet <em>DDiagram Set</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DDiagramSet
     * @generated
     */
    public Adapter createDDiagramSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.NodeStyle
     * @generated
     */
    public Adapter createNodeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Dot <em>Dot</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Dot
     * @generated
     */
    public Adapter createDotAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.GaugeSection <em>Gauge Section</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.GaugeSection
     * @generated
     */
    public Adapter createGaugeSectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.ContainerStyle
     * <em>Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.ContainerStyle
     * @generated
     */
    public Adapter createContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.FlatContainerStyle
     * @generated
     */
    public Adapter createFlatContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.ShapeContainerStyle
     * @generated
     */
    public Adapter createShapeContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Square <em>Square</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Square
     * @generated
     */
    public Adapter createSquareAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Ellipse
     * @generated
     */
    public Adapter createEllipseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Lozenge
     * @generated
     */
    public Adapter createLozengeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BundledImage <em>Bundled Image</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BundledImage
     * @generated
     */
    public Adapter createBundledImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.WorkspaceImage
     * <em>Workspace Image</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.WorkspaceImage
     * @generated
     */
    public Adapter createWorkspaceImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.CustomStyle <em>Custom Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.CustomStyle
     * @generated
     */
    public Adapter createCustomStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.EdgeTarget <em>Edge Target</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.EdgeTarget
     * @generated
     */
    public Adapter createEdgeTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.EdgeStyle
     * @generated
     */
    public Adapter createEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * <em>DAnalysis Custom Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysisCustomData
     * @generated
     */
    public Adapter createDAnalysisCustomDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.GaugeCompositeStyle
     * @generated
     */
    public Adapter createGaugeCompositeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.LabelStyle <em>Label Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.LabelStyle
     * @generated
     */
    public Adapter createLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Style <em>Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Style
     * @generated
     */
    public Adapter createStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BorderedStyle
     * <em>Bordered Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BorderedStyle
     * @generated
     */
    public Adapter createBorderedStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Note <em>Note</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Note
     * @generated
     */
    public Adapter createNoteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DragAndDropTarget
     * <em>Drag And Drop Target</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DragAndDropTarget
     * @generated
     */
    public Adapter createDragAndDropTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.FilterVariableHistory
     * @generated
     */
    public Adapter createFilterVariableHistoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.FilterVariableValue
     * <em>Filter Variable Value</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.FilterVariableValue
     * @generated
     */
    public Adapter createFilterVariableValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.RGBValues <em>RGB Values</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.RGBValues
     * @generated
     */
    public Adapter createRGBValuesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * <em>DAnalysis Session EObject</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DAnalysisSessionEObject
     * @generated
     */
    public Adapter createDAnalysisSessionEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.CollapseFilter
     * <em>Collapse Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.CollapseFilter
     * @generated
     */
    public Adapter createCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter
     * @generated
     */
    public Adapter createIndirectlyCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.SessionManagerEObject
     * <em>Session Manager EObject</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.SessionManagerEObject
     * @generated
     */
    public Adapter createSessionManagerEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DResource
     * @generated
     */
    public Adapter createDResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFile <em>DFile</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFile
     * @generated
     */
    public Adapter createDFileAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DResourceContainer
     * <em>DResource Container</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DResourceContainer
     * @generated
     */
    public Adapter createDResourceContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DProject <em>DProject</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DProject
     * @generated
     */
    public Adapter createDProjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DFolder <em>DFolder</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DFolder
     * @generated
     */
    public Adapter createDFolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.DModel <em>DModel</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.DModel
     * @generated
     */
    public Adapter createDModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BasicLabelStyle
     * <em>Basic Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BasicLabelStyle
     * @generated
     */
    public Adapter createBasicLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BeginLabelStyle
     * <em>Begin Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BeginLabelStyle
     * @generated
     */
    public Adapter createBeginLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.CenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.CenterLabelStyle
     * @generated
     */
    public Adapter createCenterLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.EndLabelStyle
     * <em>End Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.EndLabelStyle
     * @generated
     */
    public Adapter createEndLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.BracketEdgeStyle
     * <em>Bracket Edge Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.BracketEdgeStyle
     * @generated
     */
    public Adapter createBracketEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.Customizable <em>Customizable</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.Customizable
     * @generated
     */
    public Adapter createCustomizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry
     * @generated
     */
    public Adapter createComputedStyleDescriptionRegistryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry
     * <em>Diagram Element Mapping2 Model Element</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createDiagramElementMapping2ModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry
     * <em>Model Element2 View Variable</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createModelElement2ViewVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry
     * <em>View Variable2 Container Variable</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createViewVariable2ContainerVariableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '{@link java.util.Map.Entry
     * <em>Container Variable2 Style Description</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see java.util.Map.Entry
     * @generated
     */
    public Adapter createContainerVariable2StyleDescriptionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.viewpoint.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.viewpoint.description.DModelElement
     * @generated
     */
    public Adapter createDModelElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for the default case. <!-- begin-user-doc --> This
     * default implementation returns null. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @generated
     */
    public Adapter createEObjectAdapter() {
        return null;
    }

} // ViewpointAdapterFactory
