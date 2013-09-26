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
package org.eclipse.sirius.viewpoint.util;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage
 * @generated
 */
public class ViewpointSwitch<T> {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->O
     * 
     * @generated
     */
    protected static ViewpointPackage modelPackage;

    /**
     * Creates an instance of the switch. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointSwitch() {
        if (modelPackage == null) {
            modelPackage = ViewpointPackage.eINSTANCE;
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    public T doSwitch(EObject theEObject) {
        return doSwitch(theEObject.eClass(), theEObject);
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(EClass theEClass, EObject theEObject) {
        if (theEClass.eContainer() == modelPackage) {
            return doSwitch(theEClass.getClassifierID(), theEObject);
        } else {
            List<EClass> eSuperTypes = theEClass.getESuperTypes();
            return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(eSuperTypes.get(0), theEObject);
        }
    }

    /**
     * Calls <code>caseXXX</code> for each class of the model until one returns
     * a non null result; it yields that result. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @return the first non-null result returned by a <code>caseXXX</code>
     *         call.
     * @generated
     */
    protected T doSwitch(int classifierID, EObject theEObject) {
        switch (classifierID) {
        case ViewpointPackage.DANALYSIS: {
            DAnalysis dAnalysis = (DAnalysis) theEObject;
            T result = caseDAnalysis(dAnalysis);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DFEATURE_EXTENSION: {
            DFeatureExtension dFeatureExtension = (DFeatureExtension) theEObject;
            T result = caseDFeatureExtension(dFeatureExtension);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DVALIDABLE: {
            DValidable dValidable = (DValidable) theEObject;
            T result = caseDValidable(dValidable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNAVIGABLE: {
            DNavigable dNavigable = (DNavigable) theEObject;
            T result = caseDNavigable(dNavigable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DSTYLIZABLE: {
            DStylizable dStylizable = (DStylizable) theEObject;
            T result = caseDStylizable(dStylizable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DREFRESHABLE: {
            DRefreshable dRefreshable = (DRefreshable) theEObject;
            T result = caseDRefreshable(dRefreshable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DLABELLED: {
            DLabelled dLabelled = (DLabelled) theEObject;
            T result = caseDLabelled(dLabelled);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DMAPPING_BASED: {
            DMappingBased dMappingBased = (DMappingBased) theEObject;
            T result = caseDMappingBased(dMappingBased);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DCONTAINER: {
            DContainer dContainer = (DContainer) theEObject;
            T result = caseDContainer(dContainer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DREPRESENTATION_CONTAINER: {
            DRepresentationContainer dRepresentationContainer = (DRepresentationContainer) theEObject;
            T result = caseDRepresentationContainer(dRepresentationContainer);
            if (result == null)
                result = caseDView(dRepresentationContainer);
            if (result == null)
                result = caseDRefreshable(dRepresentationContainer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DSEMANTIC_DECORATOR: {
            DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) theEObject;
            T result = caseDSemanticDecorator(dSemanticDecorator);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DREPRESENTATION: {
            DRepresentation dRepresentation = (DRepresentation) theEObject;
            T result = caseDRepresentation(dRepresentation);
            if (result == null)
                result = caseDocumentedElement(dRepresentation);
            if (result == null)
                result = caseDRefreshable(dRepresentation);
            if (result == null)
                result = caseDModelElement(dRepresentation);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DREPRESENTATION_ELEMENT: {
            DRepresentationElement dRepresentationElement = (DRepresentationElement) theEObject;
            T result = caseDRepresentationElement(dRepresentationElement);
            if (result == null)
                result = caseDLabelled(dRepresentationElement);
            if (result == null)
                result = caseDMappingBased(dRepresentationElement);
            if (result == null)
                result = caseDStylizable(dRepresentationElement);
            if (result == null)
                result = caseDRefreshable(dRepresentationElement);
            if (result == null)
                result = caseDSemanticDecorator(dRepresentationElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DVIEW: {
            DView dView = (DView) theEObject;
            T result = caseDView(dView);
            if (result == null)
                result = caseDRefreshable(dView);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.META_MODEL_EXTENSION: {
            MetaModelExtension metaModelExtension = (MetaModelExtension) theEObject;
            T result = caseMetaModelExtension(metaModelExtension);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DDIAGRAM: {
            DDiagram dDiagram = (DDiagram) theEObject;
            T result = caseDDiagram(dDiagram);
            if (result == null)
                result = caseDRepresentation(dDiagram);
            if (result == null)
                result = caseDragAndDropTarget(dDiagram);
            if (result == null)
                result = caseDValidable(dDiagram);
            if (result == null)
                result = caseDContainer(dDiagram);
            if (result == null)
                result = caseDocumentedElement(dDiagram);
            if (result == null)
                result = caseDRefreshable(dDiagram);
            if (result == null)
                result = caseDModelElement(dDiagram);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DSEMANTIC_DIAGRAM: {
            DSemanticDiagram dSemanticDiagram = (DSemanticDiagram) theEObject;
            T result = caseDSemanticDiagram(dSemanticDiagram);
            if (result == null)
                result = caseDDiagram(dSemanticDiagram);
            if (result == null)
                result = caseDSemanticDecorator(dSemanticDiagram);
            if (result == null)
                result = caseDRepresentation(dSemanticDiagram);
            if (result == null)
                result = caseDragAndDropTarget(dSemanticDiagram);
            if (result == null)
                result = caseDValidable(dSemanticDiagram);
            if (result == null)
                result = caseDContainer(dSemanticDiagram);
            if (result == null)
                result = caseDocumentedElement(dSemanticDiagram);
            if (result == null)
                result = caseDRefreshable(dSemanticDiagram);
            if (result == null)
                result = caseDModelElement(dSemanticDiagram);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DDIAGRAM_ELEMENT: {
            DDiagramElement dDiagramElement = (DDiagramElement) theEObject;
            T result = caseDDiagramElement(dDiagramElement);
            if (result == null)
                result = caseDRepresentationElement(dDiagramElement);
            if (result == null)
                result = caseDValidable(dDiagramElement);
            if (result == null)
                result = caseDNavigable(dDiagramElement);
            if (result == null)
                result = caseDLabelled(dDiagramElement);
            if (result == null)
                result = caseDMappingBased(dDiagramElement);
            if (result == null)
                result = caseDStylizable(dDiagramElement);
            if (result == null)
                result = caseDRefreshable(dDiagramElement);
            if (result == null)
                result = caseDSemanticDecorator(dDiagramElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.GRAPHICAL_FILTER: {
            GraphicalFilter graphicalFilter = (GraphicalFilter) theEObject;
            T result = caseGraphicalFilter(graphicalFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.HIDE_FILTER: {
            HideFilter hideFilter = (HideFilter) theEObject;
            T result = caseHideFilter(hideFilter);
            if (result == null)
                result = caseGraphicalFilter(hideFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.HIDE_LABEL_FILTER: {
            HideLabelFilter hideLabelFilter = (HideLabelFilter) theEObject;
            T result = caseHideLabelFilter(hideLabelFilter);
            if (result == null)
                result = caseGraphicalFilter(hideLabelFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.FOLDING_POINT_FILTER: {
            FoldingPointFilter foldingPointFilter = (FoldingPointFilter) theEObject;
            T result = caseFoldingPointFilter(foldingPointFilter);
            if (result == null)
                result = caseGraphicalFilter(foldingPointFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.FOLDING_FILTER: {
            FoldingFilter foldingFilter = (FoldingFilter) theEObject;
            T result = caseFoldingFilter(foldingFilter);
            if (result == null)
                result = caseGraphicalFilter(foldingFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.APPLIED_COMPOSITE_FILTERS: {
            AppliedCompositeFilters appliedCompositeFilters = (AppliedCompositeFilters) theEObject;
            T result = caseAppliedCompositeFilters(appliedCompositeFilters);
            if (result == null)
                result = caseGraphicalFilter(appliedCompositeFilters);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.ABSOLUTE_BOUNDS_FILTER: {
            AbsoluteBoundsFilter absoluteBoundsFilter = (AbsoluteBoundsFilter) theEObject;
            T result = caseAbsoluteBoundsFilter(absoluteBoundsFilter);
            if (result == null)
                result = caseGraphicalFilter(absoluteBoundsFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DECORATION: {
            Decoration decoration = (Decoration) theEObject;
            T result = caseDecoration(decoration);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNAVIGATION_LINK: {
            DNavigationLink dNavigationLink = (DNavigationLink) theEObject;
            T result = caseDNavigationLink(dNavigationLink);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DE_OBJECT_LINK: {
            DEObjectLink deObjectLink = (DEObjectLink) theEObject;
            T result = caseDEObjectLink(deObjectLink);
            if (result == null)
                result = caseDNavigationLink(deObjectLink);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DDIAGRAM_LINK: {
            DDiagramLink dDiagramLink = (DDiagramLink) theEObject;
            T result = caseDDiagramLink(dDiagramLink);
            if (result == null)
                result = caseDNavigationLink(dDiagramLink);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DSOURCE_FILE_LINK: {
            DSourceFileLink dSourceFileLink = (DSourceFileLink) theEObject;
            T result = caseDSourceFileLink(dSourceFileLink);
            if (result == null)
                result = caseDNavigationLink(dSourceFileLink);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.ABSTRACT_DNODE: {
            AbstractDNode abstractDNode = (AbstractDNode) theEObject;
            T result = caseAbstractDNode(abstractDNode);
            if (result == null)
                result = caseDDiagramElement(abstractDNode);
            if (result == null)
                result = caseDRepresentationElement(abstractDNode);
            if (result == null)
                result = caseDValidable(abstractDNode);
            if (result == null)
                result = caseDNavigable(abstractDNode);
            if (result == null)
                result = caseDLabelled(abstractDNode);
            if (result == null)
                result = caseDMappingBased(abstractDNode);
            if (result == null)
                result = caseDStylizable(abstractDNode);
            if (result == null)
                result = caseDRefreshable(abstractDNode);
            if (result == null)
                result = caseDSemanticDecorator(abstractDNode);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNODE: {
            DNode dNode = (DNode) theEObject;
            T result = caseDNode(dNode);
            if (result == null)
                result = caseAbstractDNode(dNode);
            if (result == null)
                result = caseEdgeTarget(dNode);
            if (result == null)
                result = caseDragAndDropTarget(dNode);
            if (result == null)
                result = caseDDiagramElement(dNode);
            if (result == null)
                result = caseDRepresentationElement(dNode);
            if (result == null)
                result = caseDValidable(dNode);
            if (result == null)
                result = caseDNavigable(dNode);
            if (result == null)
                result = caseDLabelled(dNode);
            if (result == null)
                result = caseDMappingBased(dNode);
            if (result == null)
                result = caseDStylizable(dNode);
            if (result == null)
                result = caseDRefreshable(dNode);
            if (result == null)
                result = caseDSemanticDecorator(dNode);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DDIAGRAM_ELEMENT_CONTAINER: {
            DDiagramElementContainer dDiagramElementContainer = (DDiagramElementContainer) theEObject;
            T result = caseDDiagramElementContainer(dDiagramElementContainer);
            if (result == null)
                result = caseAbstractDNode(dDiagramElementContainer);
            if (result == null)
                result = caseEdgeTarget(dDiagramElementContainer);
            if (result == null)
                result = caseDragAndDropTarget(dDiagramElementContainer);
            if (result == null)
                result = caseDContainer(dDiagramElementContainer);
            if (result == null)
                result = caseDDiagramElement(dDiagramElementContainer);
            if (result == null)
                result = caseDRepresentationElement(dDiagramElementContainer);
            if (result == null)
                result = caseDValidable(dDiagramElementContainer);
            if (result == null)
                result = caseDNavigable(dDiagramElementContainer);
            if (result == null)
                result = caseDLabelled(dDiagramElementContainer);
            if (result == null)
                result = caseDMappingBased(dDiagramElementContainer);
            if (result == null)
                result = caseDStylizable(dDiagramElementContainer);
            if (result == null)
                result = caseDRefreshable(dDiagramElementContainer);
            if (result == null)
                result = caseDSemanticDecorator(dDiagramElementContainer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNODE_CONTAINER: {
            DNodeContainer dNodeContainer = (DNodeContainer) theEObject;
            T result = caseDNodeContainer(dNodeContainer);
            if (result == null)
                result = caseDDiagramElementContainer(dNodeContainer);
            if (result == null)
                result = caseAbstractDNode(dNodeContainer);
            if (result == null)
                result = caseEdgeTarget(dNodeContainer);
            if (result == null)
                result = caseDragAndDropTarget(dNodeContainer);
            if (result == null)
                result = caseDContainer(dNodeContainer);
            if (result == null)
                result = caseDDiagramElement(dNodeContainer);
            if (result == null)
                result = caseDRepresentationElement(dNodeContainer);
            if (result == null)
                result = caseDValidable(dNodeContainer);
            if (result == null)
                result = caseDNavigable(dNodeContainer);
            if (result == null)
                result = caseDLabelled(dNodeContainer);
            if (result == null)
                result = caseDMappingBased(dNodeContainer);
            if (result == null)
                result = caseDStylizable(dNodeContainer);
            if (result == null)
                result = caseDRefreshable(dNodeContainer);
            if (result == null)
                result = caseDSemanticDecorator(dNodeContainer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNODE_LIST: {
            DNodeList dNodeList = (DNodeList) theEObject;
            T result = caseDNodeList(dNodeList);
            if (result == null)
                result = caseDDiagramElementContainer(dNodeList);
            if (result == null)
                result = caseAbstractDNode(dNodeList);
            if (result == null)
                result = caseEdgeTarget(dNodeList);
            if (result == null)
                result = caseDragAndDropTarget(dNodeList);
            if (result == null)
                result = caseDContainer(dNodeList);
            if (result == null)
                result = caseDDiagramElement(dNodeList);
            if (result == null)
                result = caseDRepresentationElement(dNodeList);
            if (result == null)
                result = caseDValidable(dNodeList);
            if (result == null)
                result = caseDNavigable(dNodeList);
            if (result == null)
                result = caseDLabelled(dNodeList);
            if (result == null)
                result = caseDMappingBased(dNodeList);
            if (result == null)
                result = caseDStylizable(dNodeList);
            if (result == null)
                result = caseDRefreshable(dNodeList);
            if (result == null)
                result = caseDSemanticDecorator(dNodeList);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DNODE_LIST_ELEMENT: {
            DNodeListElement dNodeListElement = (DNodeListElement) theEObject;
            T result = caseDNodeListElement(dNodeListElement);
            if (result == null)
                result = caseAbstractDNode(dNodeListElement);
            if (result == null)
                result = caseDDiagramElement(dNodeListElement);
            if (result == null)
                result = caseDRepresentationElement(dNodeListElement);
            if (result == null)
                result = caseDValidable(dNodeListElement);
            if (result == null)
                result = caseDNavigable(dNodeListElement);
            if (result == null)
                result = caseDLabelled(dNodeListElement);
            if (result == null)
                result = caseDMappingBased(dNodeListElement);
            if (result == null)
                result = caseDStylizable(dNodeListElement);
            if (result == null)
                result = caseDRefreshable(dNodeListElement);
            if (result == null)
                result = caseDSemanticDecorator(dNodeListElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DEDGE: {
            DEdge dEdge = (DEdge) theEObject;
            T result = caseDEdge(dEdge);
            if (result == null)
                result = caseDDiagramElement(dEdge);
            if (result == null)
                result = caseEdgeTarget(dEdge);
            if (result == null)
                result = caseDRepresentationElement(dEdge);
            if (result == null)
                result = caseDValidable(dEdge);
            if (result == null)
                result = caseDNavigable(dEdge);
            if (result == null)
                result = caseDLabelled(dEdge);
            if (result == null)
                result = caseDMappingBased(dEdge);
            if (result == null)
                result = caseDStylizable(dEdge);
            if (result == null)
                result = caseDRefreshable(dEdge);
            if (result == null)
                result = caseDSemanticDecorator(dEdge);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DDIAGRAM_SET: {
            DDiagramSet dDiagramSet = (DDiagramSet) theEObject;
            T result = caseDDiagramSet(dDiagramSet);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.NODE_STYLE: {
            NodeStyle nodeStyle = (NodeStyle) theEObject;
            T result = caseNodeStyle(nodeStyle);
            if (result == null)
                result = caseLabelStyle(nodeStyle);
            if (result == null)
                result = caseBorderedStyle(nodeStyle);
            if (result == null)
                result = caseBasicLabelStyle(nodeStyle);
            if (result == null)
                result = caseStyle(nodeStyle);
            if (result == null)
                result = caseDRefreshable(nodeStyle);
            if (result == null)
                result = caseCustomizable(nodeStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DOT: {
            Dot dot = (Dot) theEObject;
            T result = caseDot(dot);
            if (result == null)
                result = caseNodeStyle(dot);
            if (result == null)
                result = caseLabelStyle(dot);
            if (result == null)
                result = caseBorderedStyle(dot);
            if (result == null)
                result = caseBasicLabelStyle(dot);
            if (result == null)
                result = caseStyle(dot);
            if (result == null)
                result = caseDRefreshable(dot);
            if (result == null)
                result = caseCustomizable(dot);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.GAUGE_SECTION: {
            GaugeSection gaugeSection = (GaugeSection) theEObject;
            T result = caseGaugeSection(gaugeSection);
            if (result == null)
                result = caseCustomizable(gaugeSection);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.CONTAINER_STYLE: {
            ContainerStyle containerStyle = (ContainerStyle) theEObject;
            T result = caseContainerStyle(containerStyle);
            if (result == null)
                result = caseLabelStyle(containerStyle);
            if (result == null)
                result = caseBorderedStyle(containerStyle);
            if (result == null)
                result = caseBasicLabelStyle(containerStyle);
            if (result == null)
                result = caseStyle(containerStyle);
            if (result == null)
                result = caseDRefreshable(containerStyle);
            if (result == null)
                result = caseCustomizable(containerStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.FLAT_CONTAINER_STYLE: {
            FlatContainerStyle flatContainerStyle = (FlatContainerStyle) theEObject;
            T result = caseFlatContainerStyle(flatContainerStyle);
            if (result == null)
                result = caseContainerStyle(flatContainerStyle);
            if (result == null)
                result = caseLabelStyle(flatContainerStyle);
            if (result == null)
                result = caseBorderedStyle(flatContainerStyle);
            if (result == null)
                result = caseBasicLabelStyle(flatContainerStyle);
            if (result == null)
                result = caseStyle(flatContainerStyle);
            if (result == null)
                result = caseDRefreshable(flatContainerStyle);
            if (result == null)
                result = caseCustomizable(flatContainerStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.SHAPE_CONTAINER_STYLE: {
            ShapeContainerStyle shapeContainerStyle = (ShapeContainerStyle) theEObject;
            T result = caseShapeContainerStyle(shapeContainerStyle);
            if (result == null)
                result = caseContainerStyle(shapeContainerStyle);
            if (result == null)
                result = caseLabelStyle(shapeContainerStyle);
            if (result == null)
                result = caseBorderedStyle(shapeContainerStyle);
            if (result == null)
                result = caseBasicLabelStyle(shapeContainerStyle);
            if (result == null)
                result = caseStyle(shapeContainerStyle);
            if (result == null)
                result = caseDRefreshable(shapeContainerStyle);
            if (result == null)
                result = caseCustomizable(shapeContainerStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.SQUARE: {
            Square square = (Square) theEObject;
            T result = caseSquare(square);
            if (result == null)
                result = caseNodeStyle(square);
            if (result == null)
                result = caseLabelStyle(square);
            if (result == null)
                result = caseBorderedStyle(square);
            if (result == null)
                result = caseBasicLabelStyle(square);
            if (result == null)
                result = caseStyle(square);
            if (result == null)
                result = caseDRefreshable(square);
            if (result == null)
                result = caseCustomizable(square);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.ELLIPSE: {
            Ellipse ellipse = (Ellipse) theEObject;
            T result = caseEllipse(ellipse);
            if (result == null)
                result = caseNodeStyle(ellipse);
            if (result == null)
                result = caseLabelStyle(ellipse);
            if (result == null)
                result = caseBorderedStyle(ellipse);
            if (result == null)
                result = caseBasicLabelStyle(ellipse);
            if (result == null)
                result = caseStyle(ellipse);
            if (result == null)
                result = caseDRefreshable(ellipse);
            if (result == null)
                result = caseCustomizable(ellipse);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.LOZENGE: {
            Lozenge lozenge = (Lozenge) theEObject;
            T result = caseLozenge(lozenge);
            if (result == null)
                result = caseNodeStyle(lozenge);
            if (result == null)
                result = caseLabelStyle(lozenge);
            if (result == null)
                result = caseBorderedStyle(lozenge);
            if (result == null)
                result = caseBasicLabelStyle(lozenge);
            if (result == null)
                result = caseStyle(lozenge);
            if (result == null)
                result = caseDRefreshable(lozenge);
            if (result == null)
                result = caseCustomizable(lozenge);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.BUNDLED_IMAGE: {
            BundledImage bundledImage = (BundledImage) theEObject;
            T result = caseBundledImage(bundledImage);
            if (result == null)
                result = caseNodeStyle(bundledImage);
            if (result == null)
                result = caseLabelStyle(bundledImage);
            if (result == null)
                result = caseBorderedStyle(bundledImage);
            if (result == null)
                result = caseBasicLabelStyle(bundledImage);
            if (result == null)
                result = caseStyle(bundledImage);
            if (result == null)
                result = caseDRefreshable(bundledImage);
            if (result == null)
                result = caseCustomizable(bundledImage);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.WORKSPACE_IMAGE: {
            WorkspaceImage workspaceImage = (WorkspaceImage) theEObject;
            T result = caseWorkspaceImage(workspaceImage);
            if (result == null)
                result = caseNodeStyle(workspaceImage);
            if (result == null)
                result = caseContainerStyle(workspaceImage);
            if (result == null)
                result = caseLabelStyle(workspaceImage);
            if (result == null)
                result = caseBorderedStyle(workspaceImage);
            if (result == null)
                result = caseBasicLabelStyle(workspaceImage);
            if (result == null)
                result = caseStyle(workspaceImage);
            if (result == null)
                result = caseDRefreshable(workspaceImage);
            if (result == null)
                result = caseCustomizable(workspaceImage);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.CUSTOM_STYLE: {
            CustomStyle customStyle = (CustomStyle) theEObject;
            T result = caseCustomStyle(customStyle);
            if (result == null)
                result = caseNodeStyle(customStyle);
            if (result == null)
                result = caseLabelStyle(customStyle);
            if (result == null)
                result = caseBorderedStyle(customStyle);
            if (result == null)
                result = caseBasicLabelStyle(customStyle);
            if (result == null)
                result = caseStyle(customStyle);
            if (result == null)
                result = caseDRefreshable(customStyle);
            if (result == null)
                result = caseCustomizable(customStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.EDGE_TARGET: {
            EdgeTarget edgeTarget = (EdgeTarget) theEObject;
            T result = caseEdgeTarget(edgeTarget);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.EDGE_STYLE: {
            EdgeStyle edgeStyle = (EdgeStyle) theEObject;
            T result = caseEdgeStyle(edgeStyle);
            if (result == null)
                result = caseStyle(edgeStyle);
            if (result == null)
                result = caseDRefreshable(edgeStyle);
            if (result == null)
                result = caseCustomizable(edgeStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA: {
            DAnalysisCustomData dAnalysisCustomData = (DAnalysisCustomData) theEObject;
            T result = caseDAnalysisCustomData(dAnalysisCustomData);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.GAUGE_COMPOSITE_STYLE: {
            GaugeCompositeStyle gaugeCompositeStyle = (GaugeCompositeStyle) theEObject;
            T result = caseGaugeCompositeStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseNodeStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseLabelStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseBorderedStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseBasicLabelStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseStyle(gaugeCompositeStyle);
            if (result == null)
                result = caseDRefreshable(gaugeCompositeStyle);
            if (result == null)
                result = caseCustomizable(gaugeCompositeStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.LABEL_STYLE: {
            LabelStyle labelStyle = (LabelStyle) theEObject;
            T result = caseLabelStyle(labelStyle);
            if (result == null)
                result = caseBasicLabelStyle(labelStyle);
            if (result == null)
                result = caseCustomizable(labelStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.STYLE: {
            Style style = (Style) theEObject;
            T result = caseStyle(style);
            if (result == null)
                result = caseDRefreshable(style);
            if (result == null)
                result = caseCustomizable(style);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.BORDERED_STYLE: {
            BorderedStyle borderedStyle = (BorderedStyle) theEObject;
            T result = caseBorderedStyle(borderedStyle);
            if (result == null)
                result = caseStyle(borderedStyle);
            if (result == null)
                result = caseDRefreshable(borderedStyle);
            if (result == null)
                result = caseCustomizable(borderedStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.NOTE: {
            Note note = (Note) theEObject;
            T result = caseNote(note);
            if (result == null)
                result = caseNodeStyle(note);
            if (result == null)
                result = caseLabelStyle(note);
            if (result == null)
                result = caseBorderedStyle(note);
            if (result == null)
                result = caseBasicLabelStyle(note);
            if (result == null)
                result = caseStyle(note);
            if (result == null)
                result = caseDRefreshable(note);
            if (result == null)
                result = caseCustomizable(note);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DRAG_AND_DROP_TARGET: {
            DragAndDropTarget dragAndDropTarget = (DragAndDropTarget) theEObject;
            T result = caseDragAndDropTarget(dragAndDropTarget);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.FILTER_VARIABLE_HISTORY: {
            FilterVariableHistory filterVariableHistory = (FilterVariableHistory) theEObject;
            T result = caseFilterVariableHistory(filterVariableHistory);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.FILTER_VARIABLE_VALUE: {
            FilterVariableValue filterVariableValue = (FilterVariableValue) theEObject;
            T result = caseFilterVariableValue(filterVariableValue);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.RGB_VALUES: {
            RGBValues rgbValues = (RGBValues) theEObject;
            T result = caseRGBValues(rgbValues);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT: {
            DAnalysisSessionEObject dAnalysisSessionEObject = (DAnalysisSessionEObject) theEObject;
            T result = caseDAnalysisSessionEObject(dAnalysisSessionEObject);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.COLLAPSE_FILTER: {
            CollapseFilter collapseFilter = (CollapseFilter) theEObject;
            T result = caseCollapseFilter(collapseFilter);
            if (result == null)
                result = caseGraphicalFilter(collapseFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.INDIRECTLY_COLLAPSE_FILTER: {
            IndirectlyCollapseFilter indirectlyCollapseFilter = (IndirectlyCollapseFilter) theEObject;
            T result = caseIndirectlyCollapseFilter(indirectlyCollapseFilter);
            if (result == null)
                result = caseCollapseFilter(indirectlyCollapseFilter);
            if (result == null)
                result = caseGraphicalFilter(indirectlyCollapseFilter);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.SESSION_MANAGER_EOBJECT: {
            SessionManagerEObject sessionManagerEObject = (SessionManagerEObject) theEObject;
            T result = caseSessionManagerEObject(sessionManagerEObject);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DRESOURCE: {
            DResource dResource = (DResource) theEObject;
            T result = caseDResource(dResource);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DFILE: {
            DFile dFile = (DFile) theEObject;
            T result = caseDFile(dFile);
            if (result == null)
                result = caseDResource(dFile);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DRESOURCE_CONTAINER: {
            DResourceContainer dResourceContainer = (DResourceContainer) theEObject;
            T result = caseDResourceContainer(dResourceContainer);
            if (result == null)
                result = caseDResource(dResourceContainer);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DPROJECT: {
            DProject dProject = (DProject) theEObject;
            T result = caseDProject(dProject);
            if (result == null)
                result = caseDResourceContainer(dProject);
            if (result == null)
                result = caseDResource(dProject);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DFOLDER: {
            DFolder dFolder = (DFolder) theEObject;
            T result = caseDFolder(dFolder);
            if (result == null)
                result = caseDResourceContainer(dFolder);
            if (result == null)
                result = caseDResource(dFolder);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DMODEL: {
            DModel dModel = (DModel) theEObject;
            T result = caseDModel(dModel);
            if (result == null)
                result = caseDFile(dModel);
            if (result == null)
                result = caseDResource(dModel);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.BASIC_LABEL_STYLE: {
            BasicLabelStyle basicLabelStyle = (BasicLabelStyle) theEObject;
            T result = caseBasicLabelStyle(basicLabelStyle);
            if (result == null)
                result = caseCustomizable(basicLabelStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.BEGIN_LABEL_STYLE: {
            BeginLabelStyle beginLabelStyle = (BeginLabelStyle) theEObject;
            T result = caseBeginLabelStyle(beginLabelStyle);
            if (result == null)
                result = caseBasicLabelStyle(beginLabelStyle);
            if (result == null)
                result = caseCustomizable(beginLabelStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.CENTER_LABEL_STYLE: {
            CenterLabelStyle centerLabelStyle = (CenterLabelStyle) theEObject;
            T result = caseCenterLabelStyle(centerLabelStyle);
            if (result == null)
                result = caseBasicLabelStyle(centerLabelStyle);
            if (result == null)
                result = caseCustomizable(centerLabelStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.END_LABEL_STYLE: {
            EndLabelStyle endLabelStyle = (EndLabelStyle) theEObject;
            T result = caseEndLabelStyle(endLabelStyle);
            if (result == null)
                result = caseBasicLabelStyle(endLabelStyle);
            if (result == null)
                result = caseCustomizable(endLabelStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.BRACKET_EDGE_STYLE: {
            BracketEdgeStyle bracketEdgeStyle = (BracketEdgeStyle) theEObject;
            T result = caseBracketEdgeStyle(bracketEdgeStyle);
            if (result == null)
                result = caseEdgeStyle(bracketEdgeStyle);
            if (result == null)
                result = caseStyle(bracketEdgeStyle);
            if (result == null)
                result = caseDRefreshable(bracketEdgeStyle);
            if (result == null)
                result = caseCustomizable(bracketEdgeStyle);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.CUSTOMIZABLE: {
            Customizable customizable = (Customizable) theEObject;
            T result = caseCustomizable(customizable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY: {
            ComputedStyleDescriptionRegistry computedStyleDescriptionRegistry = (ComputedStyleDescriptionRegistry) theEObject;
            T result = caseComputedStyleDescriptionRegistry(computedStyleDescriptionRegistry);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT: {
            @SuppressWarnings("unchecked")
            Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> diagramElementMapping2ModelElement = (Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>>) theEObject;
            T result = caseDiagramElementMapping2ModelElement(diagramElementMapping2ModelElement);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.MODEL_ELEMENT2_VIEW_VARIABLE: {
            @SuppressWarnings("unchecked")
            Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> modelElement2ViewVariable = (Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>) theEObject;
            T result = caseModelElement2ViewVariable(modelElement2ViewVariable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.VIEW_VARIABLE2_CONTAINER_VARIABLE: {
            @SuppressWarnings("unchecked")
            Map.Entry<EObject, EMap<EObject, StyleDescription>> viewVariable2ContainerVariable = (Map.Entry<EObject, EMap<EObject, StyleDescription>>) theEObject;
            T result = caseViewVariable2ContainerVariable(viewVariable2ContainerVariable);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        case ViewpointPackage.CONTAINER_VARIABLE2_STYLE_DESCRIPTION: {
            @SuppressWarnings("unchecked")
            Map.Entry<EObject, StyleDescription> containerVariable2StyleDescription = (Map.Entry<EObject, StyleDescription>) theEObject;
            T result = caseContainerVariable2StyleDescription(containerVariable2StyleDescription);
            if (result == null)
                result = defaultCase(theEObject);
            return result;
        }
        default:
            return defaultCase(theEObject);
        }
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysis(DAnalysis object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFeature Extension</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFeature Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFeatureExtension(DFeatureExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DValidable</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DValidable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDValidable(DValidable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNavigable</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNavigable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNavigable(DNavigable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DStylizable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DStylizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDStylizable(DStylizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRefreshable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRefreshable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRefreshable(DRefreshable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DLabelled</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DLabelled</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDLabelled(DLabelled object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DMapping Based</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DMapping Based</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDMappingBased(DMappingBased object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DContainer</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DContainer</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDContainer(DContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation Container</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationContainer(DRepresentationContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DSemantic Decorator</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DSemantic Decorator</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDecorator(DSemanticDecorator object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentation(DRepresentation object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DRepresentation Element</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DRepresentation Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDRepresentationElement(DRepresentationElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DView</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DView</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDView(DView object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Meta Model Extension</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Meta Model Extension</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseMetaModelExtension(MetaModelExtension object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DDiagram</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DDiagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagram(DDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DSemantic Diagram</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DSemantic Diagram</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSemanticDiagram(DSemanticDiagram object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DDiagram Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DDiagram Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramElement(DDiagramElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Graphical Filter</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Graphical Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGraphicalFilter(GraphicalFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hide Filter</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hide Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideFilter(HideFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Hide Label Filter</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Hide Label Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseHideLabelFilter(HideLabelFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Folding Point Filter</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Folding Point Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFoldingPointFilter(FoldingPointFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Folding Filter</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Folding Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFoldingFilter(FoldingFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Applied Composite Filters</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Applied Composite Filters</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAppliedCompositeFilters(AppliedCompositeFilters object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Absolute Bounds Filter</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Absolute Bounds Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbsoluteBoundsFilter(AbsoluteBoundsFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Decoration</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Decoration</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDecoration(Decoration object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNavigation Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNavigation Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNavigationLink(DNavigationLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DE Object Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DE Object Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDEObjectLink(DEObjectLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DDiagram Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DDiagram Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramLink(DDiagramLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DSource File Link</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DSource File Link</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDSourceFileLink(DSourceFileLink object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Abstract DNode</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Abstract DNode</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseAbstractDNode(AbstractDNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNode</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNode</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNode(DNode object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DDiagram Element Container</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DDiagram Element Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramElementContainer(DDiagramElementContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNode Container</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNode Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeContainer(DNodeContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNode List</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNode List</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeList(DNodeList object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DNode List Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DNode List Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDNodeListElement(DNodeListElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DEdge</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DEdge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDEdge(DEdge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DDiagram Set</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DDiagram Set</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDDiagramSet(DDiagramSet object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Node Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Node Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNodeStyle(NodeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Dot</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Dot</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDot(Dot object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerStyle(ContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Flat Container Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Flat Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFlatContainerStyle(FlatContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Shape Container Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Shape Container Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseShapeContainerStyle(ShapeContainerStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Square</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Square</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSquare(Square object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bundled Image</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bundled Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBundledImage(BundledImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Workspace Image</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Workspace Image</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseWorkspaceImage(WorkspaceImage object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Custom Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Custom Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomStyle(CustomStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Section</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Section</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeSection(GaugeSection object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Target</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeTarget(EdgeTarget object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Edge Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Edge Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEdgeStyle(EdgeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bracket Edge Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bracket Edge Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBracketEdgeStyle(BracketEdgeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Customizable</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Customizable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCustomizable(Customizable object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Computed Style Description Registry</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Computed Style Description Registry</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseComputedStyleDescriptionRegistry(ComputedStyleDescriptionRegistry object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Diagram Element Mapping2 Model Element</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Diagram Element Mapping2 Model Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDiagramElementMapping2ModelElement(Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Model Element2 View Variable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Model Element2 View Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseModelElement2ViewVariable(Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>View Variable2 Container Variable</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>View Variable2 Container Variable</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseViewVariable2ContainerVariable(Map.Entry<EObject, EMap<EObject, StyleDescription>> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Container Variable2 Style Description</em>'. <!-- begin-user-doc -->
     * This implementation returns null; returning a non-null result will
     * terminate the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Container Variable2 Style Description</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseContainerVariable2StyleDescription(Map.Entry<EObject, StyleDescription> object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis Custom Data</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis Custom Data</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysisCustomData(DAnalysisCustomData object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Gauge Composite Style</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Gauge Composite Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseGaugeCompositeStyle(GaugeCompositeStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLabelStyle(LabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Style</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseStyle(Style object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Bordered Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Bordered Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBorderedStyle(BorderedStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Note</em>'. <!-- begin-user-doc --> This implementation returns null;
     * returning a non-null result will terminate the switch. <!-- end-user-doc
     * -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Note</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseNote(Note object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Drag And Drop Target</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Drag And Drop Target</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDragAndDropTarget(DragAndDropTarget object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Filter Variable History</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Filter Variable History</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilterVariableHistory(FilterVariableHistory object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Filter Variable Value</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Filter Variable Value</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseFilterVariableValue(FilterVariableValue object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>RGB Values</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>RGB Values</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseRGBValues(RGBValues object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DAnalysis Session EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DAnalysis Session EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDAnalysisSessionEObject(DAnalysisSessionEObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Collapse Filter</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Collapse Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCollapseFilter(CollapseFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Indirectly Collapse Filter</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Indirectly Collapse Filter</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseIndirectlyCollapseFilter(IndirectlyCollapseFilter object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Session Manager EObject</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Session Manager EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseSessionManagerEObject(SessionManagerEObject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DResource</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DResource</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDResource(DResource object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFile</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFile</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFile(DFile object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DResource Container</em>'. <!-- begin-user-doc --> This
     * implementation returns null; returning a non-null result will terminate
     * the switch. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DResource Container</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDResourceContainer(DResourceContainer object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DProject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DProject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDProject(DProject object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DFolder</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DFolder</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDFolder(DFolder object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DModel</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DModel</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModel(DModel object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Basic Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Basic Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBasicLabelStyle(BasicLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Begin Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Begin Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseBeginLabelStyle(BeginLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Center Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Center Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseCenterLabelStyle(CenterLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>End Label Style</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>End Label Style</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEndLabelStyle(EndLabelStyle object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Ellipse</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Ellipse</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseEllipse(Ellipse object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Lozenge</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Lozenge</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseLozenge(Lozenge object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>Documented Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>Documented Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDocumentedElement(DocumentedElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>DModel Element</em>'. <!-- begin-user-doc --> This implementation
     * returns null; returning a non-null result will terminate the switch. <!--
     * end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>DModel Element</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
     * @generated
     */
    public T caseDModelElement(DModelElement object) {
        return null;
    }

    /**
     * Returns the result of interpreting the object as an instance of '
     * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
     * null; returning a non-null result will terminate the switch, but this is
     * the last case anyway. <!-- end-user-doc -->
     * 
     * @param object
     *            the target of the switch.
     * @return the result of interpreting the object as an instance of '
     *         <em>EObject</em>'.
     * @see #doSwitch(org.eclipse.emf.ecore.EObject)
     * @generated
     */
    public T defaultCase(EObject object) {
        return null;
    }

} // SiriusSwitch
