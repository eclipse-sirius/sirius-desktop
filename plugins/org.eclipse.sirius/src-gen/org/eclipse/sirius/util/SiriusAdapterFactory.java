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
package org.eclipse.sirius.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.sirius.AbsoluteBoundsFilter;
import org.eclipse.sirius.AbstractDNode;
import org.eclipse.sirius.AppliedCompositeFilters;
import org.eclipse.sirius.BasicLabelStyle;
import org.eclipse.sirius.BeginLabelStyle;
import org.eclipse.sirius.BorderedStyle;
import org.eclipse.sirius.BracketEdgeStyle;
import org.eclipse.sirius.BundledImage;
import org.eclipse.sirius.CenterLabelStyle;
import org.eclipse.sirius.CollapseFilter;
import org.eclipse.sirius.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.ContainerStyle;
import org.eclipse.sirius.CustomStyle;
import org.eclipse.sirius.Customizable;
import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DAnalysisCustomData;
import org.eclipse.sirius.DAnalysisSessionEObject;
import org.eclipse.sirius.DContainer;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramElement;
import org.eclipse.sirius.DDiagramElementContainer;
import org.eclipse.sirius.DDiagramLink;
import org.eclipse.sirius.DDiagramSet;
import org.eclipse.sirius.DEObjectLink;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.DFeatureExtension;
import org.eclipse.sirius.DFile;
import org.eclipse.sirius.DFolder;
import org.eclipse.sirius.DLabelled;
import org.eclipse.sirius.DMappingBased;
import org.eclipse.sirius.DModel;
import org.eclipse.sirius.DNavigable;
import org.eclipse.sirius.DNavigationLink;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.DProject;
import org.eclipse.sirius.DRefreshable;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DRepresentationContainer;
import org.eclipse.sirius.DRepresentationElement;
import org.eclipse.sirius.DResource;
import org.eclipse.sirius.DResourceContainer;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.DSourceFileLink;
import org.eclipse.sirius.DStylizable;
import org.eclipse.sirius.DValidable;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.Decoration;
import org.eclipse.sirius.Dot;
import org.eclipse.sirius.DragAndDropTarget;
import org.eclipse.sirius.EdgeStyle;
import org.eclipse.sirius.EdgeTarget;
import org.eclipse.sirius.Ellipse;
import org.eclipse.sirius.EndLabelStyle;
import org.eclipse.sirius.ExtensibleRepresentation;
import org.eclipse.sirius.FilterVariableHistory;
import org.eclipse.sirius.FilterVariableValue;
import org.eclipse.sirius.FlatContainerStyle;
import org.eclipse.sirius.FoldingFilter;
import org.eclipse.sirius.FoldingPointFilter;
import org.eclipse.sirius.GaugeCompositeStyle;
import org.eclipse.sirius.GaugeSection;
import org.eclipse.sirius.GraphicalFilter;
import org.eclipse.sirius.HideFilter;
import org.eclipse.sirius.HideLabelFilter;
import org.eclipse.sirius.IndirectlyCollapseFilter;
import org.eclipse.sirius.LabelStyle;
import org.eclipse.sirius.Lozenge;
import org.eclipse.sirius.MetaModelExtension;
import org.eclipse.sirius.NodeStyle;
import org.eclipse.sirius.Note;
import org.eclipse.sirius.RGBValues;
import org.eclipse.sirius.SessionManagerEObject;
import org.eclipse.sirius.ShapeContainerStyle;
import org.eclipse.sirius.Square;
import org.eclipse.sirius.Style;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.WorkspaceImage;
import org.eclipse.sirius.description.DModelElement;
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.DocumentedElement;
import org.eclipse.sirius.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.SiriusPackage
 * @generated
 */
public class SiriusAdapterFactory extends AdapterFactoryImpl {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected static SiriusPackage modelPackage;

    /**
     * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public SiriusAdapterFactory() {
        if (modelPackage == null) {
            modelPackage = SiriusPackage.eINSTANCE;
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
     * The switch the delegates to the <code>createXXX</code> methods. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected SiriusSwitch<Adapter> modelSwitch = new SiriusSwitch<Adapter>() {
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
        public Adapter caseExtensibleRepresentation(ExtensibleRepresentation object) {
            return createExtensibleRepresentationAdapter();
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
     * {@link org.eclipse.sirius.DAnalysis <em>DAnalysis</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DAnalysis
     * @generated
     */
    public Adapter createDAnalysisAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DFeatureExtension
     * <em>DFeature Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DFeatureExtension
     * @generated
     */
    public Adapter createDFeatureExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DValidable <em>DValidable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DValidable
     * @generated
     */
    public Adapter createDValidableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNavigable <em>DNavigable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNavigable
     * @generated
     */
    public Adapter createDNavigableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DStylizable <em>DStylizable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DStylizable
     * @generated
     */
    public Adapter createDStylizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DRefreshable <em>DRefreshable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DRefreshable
     * @generated
     */
    public Adapter createDRefreshableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DLabelled <em>DLabelled</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DLabelled
     * @generated
     */
    public Adapter createDLabelledAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DMappingBased <em>DMapping Based</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DMappingBased
     * @generated
     */
    public Adapter createDMappingBasedAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DContainer <em>DContainer</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DContainer
     * @generated
     */
    public Adapter createDContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DRepresentationContainer
     * <em>DRepresentation Container</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DRepresentationContainer
     * @generated
     */
    public Adapter createDRepresentationContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DSemanticDecorator
     * <em>DSemantic Decorator</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DSemanticDecorator
     * @generated
     */
    public Adapter createDSemanticDecoratorAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DRepresentation <em>DRepresentation</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DRepresentation
     * @generated
     */
    public Adapter createDRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.ExtensibleRepresentation
     * <em>Extensible Representation</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.ExtensibleRepresentation
     * @generated
     */
    public Adapter createExtensibleRepresentationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DRepresentationElement
     * <em>DRepresentation Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DRepresentationElement
     * @generated
     */
    public Adapter createDRepresentationElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DView <em>DView</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DView
     * @generated
     */
    public Adapter createDViewAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.MetaModelExtension
     * <em>Meta Model Extension</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.MetaModelExtension
     * @generated
     */
    public Adapter createMetaModelExtensionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DDiagram <em>DDiagram</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DDiagram
     * @generated
     */
    public Adapter createDDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DSemanticDiagram <em>DSemantic Diagram</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DSemanticDiagram
     * @generated
     */
    public Adapter createDSemanticDiagramAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DDiagramElement <em>DDiagram Element</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DDiagramElement
     * @generated
     */
    public Adapter createDDiagramElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.GraphicalFilter <em>Graphical Filter</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.GraphicalFilter
     * @generated
     */
    public Adapter createGraphicalFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.HideFilter <em>Hide Filter</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.HideFilter
     * @generated
     */
    public Adapter createHideFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.HideLabelFilter <em>Hide Label Filter</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.HideLabelFilter
     * @generated
     */
    public Adapter createHideLabelFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.FoldingPointFilter
     * <em>Folding Point Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.FoldingPointFilter
     * @generated
     */
    public Adapter createFoldingPointFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.FoldingFilter <em>Folding Filter</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.FoldingFilter
     * @generated
     */
    public Adapter createFoldingFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.AppliedCompositeFilters
     * <em>Applied Composite Filters</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.AppliedCompositeFilters
     * @generated
     */
    public Adapter createAppliedCompositeFiltersAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.AbsoluteBoundsFilter
     * <em>Absolute Bounds Filter</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.AbsoluteBoundsFilter
     * @generated
     */
    public Adapter createAbsoluteBoundsFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Decoration <em>Decoration</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Decoration
     * @generated
     */
    public Adapter createDecorationAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNavigationLink <em>DNavigation Link</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNavigationLink
     * @generated
     */
    public Adapter createDNavigationLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DEObjectLink <em>DE Object Link</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DEObjectLink
     * @generated
     */
    public Adapter createDEObjectLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DDiagramLink <em>DDiagram Link</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DDiagramLink
     * @generated
     */
    public Adapter createDDiagramLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DSourceFileLink <em>DSource File Link</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DSourceFileLink
     * @generated
     */
    public Adapter createDSourceFileLinkAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.AbstractDNode <em>Abstract DNode</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.AbstractDNode
     * @generated
     */
    public Adapter createAbstractDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNode <em>DNode</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNode
     * @generated
     */
    public Adapter createDNodeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DDiagramElementContainer
     * <em>DDiagram Element Container</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DDiagramElementContainer
     * @generated
     */
    public Adapter createDDiagramElementContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNodeContainer <em>DNode Container</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNodeContainer
     * @generated
     */
    public Adapter createDNodeContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNodeList <em>DNode List</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNodeList
     * @generated
     */
    public Adapter createDNodeListAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DNodeListElement
     * <em>DNode List Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DNodeListElement
     * @generated
     */
    public Adapter createDNodeListElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DEdge <em>DEdge</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DEdge
     * @generated
     */
    public Adapter createDEdgeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DDiagramSet <em>DDiagram Set</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DDiagramSet
     * @generated
     */
    public Adapter createDDiagramSetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.NodeStyle <em>Node Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.NodeStyle
     * @generated
     */
    public Adapter createNodeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Dot <em>Dot</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Dot
     * @generated
     */
    public Adapter createDotAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.ContainerStyle <em>Container Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.ContainerStyle
     * @generated
     */
    public Adapter createContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.FlatContainerStyle
     * <em>Flat Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.FlatContainerStyle
     * @generated
     */
    public Adapter createFlatContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.ShapeContainerStyle
     * <em>Shape Container Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.ShapeContainerStyle
     * @generated
     */
    public Adapter createShapeContainerStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Square <em>Square</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Square
     * @generated
     */
    public Adapter createSquareAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.BundledImage <em>Bundled Image</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.BundledImage
     * @generated
     */
    public Adapter createBundledImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.WorkspaceImage <em>Workspace Image</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.WorkspaceImage
     * @generated
     */
    public Adapter createWorkspaceImageAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.CustomStyle <em>Custom Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.CustomStyle
     * @generated
     */
    public Adapter createCustomStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.GaugeSection <em>Gauge Section</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.GaugeSection
     * @generated
     */
    public Adapter createGaugeSectionAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.EdgeTarget <em>Edge Target</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.EdgeTarget
     * @generated
     */
    public Adapter createEdgeTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.EdgeStyle <em>Edge Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.EdgeStyle
     * @generated
     */
    public Adapter createEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.BracketEdgeStyle
     * <em>Bracket Edge Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.BracketEdgeStyle
     * @generated
     */
    public Adapter createBracketEdgeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Customizable <em>Customizable</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Customizable
     * @generated
     */
    public Adapter createCustomizableAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.ComputedStyleDescriptionRegistry
     * <em>Computed Style Description Registry</em>}'. <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.ComputedStyleDescriptionRegistry
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
     * {@link org.eclipse.sirius.DAnalysisCustomData
     * <em>DAnalysis Custom Data</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DAnalysisCustomData
     * @generated
     */
    public Adapter createDAnalysisCustomDataAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.GaugeCompositeStyle
     * <em>Gauge Composite Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.GaugeCompositeStyle
     * @generated
     */
    public Adapter createGaugeCompositeStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.LabelStyle <em>Label Style</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.LabelStyle
     * @generated
     */
    public Adapter createLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Style <em>Style</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Style
     * @generated
     */
    public Adapter createStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.BorderedStyle <em>Bordered Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.BorderedStyle
     * @generated
     */
    public Adapter createBorderedStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Note <em>Note</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Note
     * @generated
     */
    public Adapter createNoteAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DragAndDropTarget
     * <em>Drag And Drop Target</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DragAndDropTarget
     * @generated
     */
    public Adapter createDragAndDropTargetAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.FilterVariableHistory
     * <em>Filter Variable History</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.FilterVariableHistory
     * @generated
     */
    public Adapter createFilterVariableHistoryAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.FilterVariableValue
     * <em>Filter Variable Value</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.FilterVariableValue
     * @generated
     */
    public Adapter createFilterVariableValueAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.RGBValues <em>RGB Values</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.RGBValues
     * @generated
     */
    public Adapter createRGBValuesAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DAnalysisSessionEObject
     * <em>DAnalysis Session EObject</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DAnalysisSessionEObject
     * @generated
     */
    public Adapter createDAnalysisSessionEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.CollapseFilter <em>Collapse Filter</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.CollapseFilter
     * @generated
     */
    public Adapter createCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.IndirectlyCollapseFilter
     * <em>Indirectly Collapse Filter</em>}'. <!-- begin-user-doc --> This
     * default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases
     * anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.IndirectlyCollapseFilter
     * @generated
     */
    public Adapter createIndirectlyCollapseFilterAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.SessionManagerEObject
     * <em>Session Manager EObject</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.SessionManagerEObject
     * @generated
     */
    public Adapter createSessionManagerEObjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DResource <em>DResource</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DResource
     * @generated
     */
    public Adapter createDResourceAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DFile <em>DFile</em>}'. <!-- begin-user-doc
     * --> This default implementation returns null so that we can easily ignore
     * cases; it's useful to ignore a case when inheritance will catch all the
     * cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DFile
     * @generated
     */
    public Adapter createDFileAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DResourceContainer
     * <em>DResource Container</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DResourceContainer
     * @generated
     */
    public Adapter createDResourceContainerAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DProject <em>DProject</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DProject
     * @generated
     */
    public Adapter createDProjectAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DFolder <em>DFolder</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DFolder
     * @generated
     */
    public Adapter createDFolderAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.DModel <em>DModel</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.DModel
     * @generated
     */
    public Adapter createDModelAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.BasicLabelStyle <em>Basic Label Style</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.BasicLabelStyle
     * @generated
     */
    public Adapter createBasicLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.BeginLabelStyle <em>Begin Label Style</em>}
     * '. <!-- begin-user-doc --> This default implementation returns null so
     * that we can easily ignore cases; it's useful to ignore a case when
     * inheritance will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.BeginLabelStyle
     * @generated
     */
    public Adapter createBeginLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.CenterLabelStyle
     * <em>Center Label Style</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.CenterLabelStyle
     * @generated
     */
    public Adapter createCenterLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.EndLabelStyle <em>End Label Style</em>}'.
     * <!-- begin-user-doc --> This default implementation returns null so that
     * we can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.EndLabelStyle
     * @generated
     */
    public Adapter createEndLabelStyleAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Ellipse <em>Ellipse</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Ellipse
     * @generated
     */
    public Adapter createEllipseAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.Lozenge <em>Lozenge</em>}'. <!--
     * begin-user-doc --> This default implementation returns null so that we
     * can easily ignore cases; it's useful to ignore a case when inheritance
     * will catch all the cases anyway. <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.Lozenge
     * @generated
     */
    public Adapter createLozengeAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DocumentedElement
     * <em>Documented Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DocumentedElement
     * @generated
     */
    public Adapter createDocumentedElementAdapter() {
        return null;
    }

    /**
     * Creates a new adapter for an object of class '
     * {@link org.eclipse.sirius.description.DModelElement
     * <em>DModel Element</em>}'. <!-- begin-user-doc --> This default
     * implementation returns null so that we can easily ignore cases; it's
     * useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
     * 
     * @return the new adapter.
     * @see org.eclipse.sirius.description.DModelElement
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

} // SiriusAdapterFactory
