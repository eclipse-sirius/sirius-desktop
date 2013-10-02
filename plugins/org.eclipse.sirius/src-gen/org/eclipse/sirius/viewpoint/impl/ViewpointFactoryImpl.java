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
package org.eclipse.sirius.viewpoint.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.business.internal.metamodel.spec.BundledImageSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.CustomStyleSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DDiagramLinkSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DDiagramSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DEdgeSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeListElementSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DRepresentationContainerSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DSemanticDiagramSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DSourceFileLinkSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.DotSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.EdgeStyleSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.EllipseSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.FlatContainerStyleSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.GaugeCompositeStyleSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.LozengeSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.NoteSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.ShapeContainerStyleSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.SquareSpec;
import org.eclipse.sirius.business.internal.metamodel.spec.WorkspaceImageSpec;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.viewpoint.AbsoluteBoundsFilter;
import org.eclipse.sirius.viewpoint.AlignmentKind;
import org.eclipse.sirius.viewpoint.AppliedCompositeFilters;
import org.eclipse.sirius.viewpoint.ArrangeConstraint;
import org.eclipse.sirius.viewpoint.BackgroundStyle;
import org.eclipse.sirius.viewpoint.BasicLabelStyle;
import org.eclipse.sirius.viewpoint.BeginLabelStyle;
import org.eclipse.sirius.viewpoint.BorderedStyle;
import org.eclipse.sirius.viewpoint.BracketEdgeStyle;
import org.eclipse.sirius.viewpoint.BundledImage;
import org.eclipse.sirius.viewpoint.BundledImageShape;
import org.eclipse.sirius.viewpoint.CenterLabelStyle;
import org.eclipse.sirius.viewpoint.CollapseFilter;
import org.eclipse.sirius.viewpoint.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.viewpoint.ContainerLayout;
import org.eclipse.sirius.viewpoint.ContainerShape;
import org.eclipse.sirius.viewpoint.CustomStyle;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DAnalysisCustomData;
import org.eclipse.sirius.viewpoint.DAnalysisSessionEObject;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramLink;
import org.eclipse.sirius.viewpoint.DDiagramSet;
import org.eclipse.sirius.viewpoint.DEObjectLink;
import org.eclipse.sirius.viewpoint.DEdge;
import org.eclipse.sirius.viewpoint.DFile;
import org.eclipse.sirius.viewpoint.DFolder;
import org.eclipse.sirius.viewpoint.DModel;
import org.eclipse.sirius.viewpoint.DNode;
import org.eclipse.sirius.viewpoint.DNodeContainer;
import org.eclipse.sirius.viewpoint.DNodeList;
import org.eclipse.sirius.viewpoint.DNodeListElement;
import org.eclipse.sirius.viewpoint.DProject;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DResourceContainer;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.DSourceFileLink;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.Dot;
import org.eclipse.sirius.viewpoint.DragAndDropTarget;
import org.eclipse.sirius.viewpoint.EdgeArrows;
import org.eclipse.sirius.viewpoint.EdgeRouting;
import org.eclipse.sirius.viewpoint.EdgeStyle;
import org.eclipse.sirius.viewpoint.Ellipse;
import org.eclipse.sirius.viewpoint.EndLabelStyle;
import org.eclipse.sirius.viewpoint.FilterVariableHistory;
import org.eclipse.sirius.viewpoint.FilterVariableValue;
import org.eclipse.sirius.viewpoint.FlatContainerStyle;
import org.eclipse.sirius.viewpoint.FoldingFilter;
import org.eclipse.sirius.viewpoint.FoldingPointFilter;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.GaugeCompositeStyle;
import org.eclipse.sirius.viewpoint.GaugeSection;
import org.eclipse.sirius.viewpoint.HideFilter;
import org.eclipse.sirius.viewpoint.HideLabelFilter;
import org.eclipse.sirius.viewpoint.IndirectlyCollapseFilter;
import org.eclipse.sirius.viewpoint.LabelAlignment;
import org.eclipse.sirius.viewpoint.LabelPosition;
import org.eclipse.sirius.viewpoint.LabelStyle;
import org.eclipse.sirius.viewpoint.LineStyle;
import org.eclipse.sirius.viewpoint.Lozenge;
import org.eclipse.sirius.viewpoint.MetaModelExtension;
import org.eclipse.sirius.viewpoint.Note;
import org.eclipse.sirius.viewpoint.RGBValues;
import org.eclipse.sirius.viewpoint.ResizeKind;
import org.eclipse.sirius.viewpoint.SessionManagerEObject;
import org.eclipse.sirius.viewpoint.ShapeContainerStyle;
import org.eclipse.sirius.viewpoint.Square;
import org.eclipse.sirius.viewpoint.SyncStatus;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.WorkspaceImage;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class ViewpointFactoryImpl extends EFactoryImpl implements ViewpointFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static ViewpointFactory init() {
        try {
            ViewpointFactory theViewpointFactory = (ViewpointFactory) EPackage.Registry.INSTANCE.getEFactory(ViewpointPackage.eNS_URI);
            if (theViewpointFactory != null) {
                return theViewpointFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new ViewpointFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public ViewpointFactoryImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public EObject create(EClass eClass) {
        switch (eClass.getClassifierID()) {
        case ViewpointPackage.DANALYSIS:
            return createDAnalysis();
        case ViewpointPackage.DREPRESENTATION_CONTAINER:
            return createDRepresentationContainer();
        case ViewpointPackage.DVIEW:
            return createDView();
        case ViewpointPackage.META_MODEL_EXTENSION:
            return createMetaModelExtension();
        case ViewpointPackage.DDIAGRAM:
            return createDDiagram();
        case ViewpointPackage.DSEMANTIC_DIAGRAM:
            return createDSemanticDiagram();
        case ViewpointPackage.HIDE_FILTER:
            return createHideFilter();
        case ViewpointPackage.HIDE_LABEL_FILTER:
            return createHideLabelFilter();
        case ViewpointPackage.FOLDING_POINT_FILTER:
            return createFoldingPointFilter();
        case ViewpointPackage.FOLDING_FILTER:
            return createFoldingFilter();
        case ViewpointPackage.APPLIED_COMPOSITE_FILTERS:
            return createAppliedCompositeFilters();
        case ViewpointPackage.ABSOLUTE_BOUNDS_FILTER:
            return createAbsoluteBoundsFilter();
        case ViewpointPackage.DECORATION:
            return createDecoration();
        case ViewpointPackage.DE_OBJECT_LINK:
            return createDEObjectLink();
        case ViewpointPackage.DDIAGRAM_LINK:
            return createDDiagramLink();
        case ViewpointPackage.DSOURCE_FILE_LINK:
            return createDSourceFileLink();
        case ViewpointPackage.DNODE:
            return createDNode();
        case ViewpointPackage.DNODE_CONTAINER:
            return createDNodeContainer();
        case ViewpointPackage.DNODE_LIST:
            return createDNodeList();
        case ViewpointPackage.DNODE_LIST_ELEMENT:
            return createDNodeListElement();
        case ViewpointPackage.DEDGE:
            return createDEdge();
        case ViewpointPackage.DDIAGRAM_SET:
            return createDDiagramSet();
        case ViewpointPackage.DOT:
            return createDot();
        case ViewpointPackage.GAUGE_SECTION:
            return createGaugeSection();
        case ViewpointPackage.FLAT_CONTAINER_STYLE:
            return createFlatContainerStyle();
        case ViewpointPackage.SHAPE_CONTAINER_STYLE:
            return createShapeContainerStyle();
        case ViewpointPackage.SQUARE:
            return createSquare();
        case ViewpointPackage.ELLIPSE:
            return createEllipse();
        case ViewpointPackage.LOZENGE:
            return createLozenge();
        case ViewpointPackage.BUNDLED_IMAGE:
            return createBundledImage();
        case ViewpointPackage.WORKSPACE_IMAGE:
            return createWorkspaceImage();
        case ViewpointPackage.CUSTOM_STYLE:
            return createCustomStyle();
        case ViewpointPackage.EDGE_STYLE:
            return createEdgeStyle();
        case ViewpointPackage.DANALYSIS_CUSTOM_DATA:
            return createDAnalysisCustomData();
        case ViewpointPackage.GAUGE_COMPOSITE_STYLE:
            return createGaugeCompositeStyle();
        case ViewpointPackage.LABEL_STYLE:
            return createLabelStyle();
        case ViewpointPackage.BORDERED_STYLE:
            return createBorderedStyle();
        case ViewpointPackage.NOTE:
            return createNote();
        case ViewpointPackage.DRAG_AND_DROP_TARGET:
            return createDragAndDropTarget();
        case ViewpointPackage.FILTER_VARIABLE_HISTORY:
            return createFilterVariableHistory();
        case ViewpointPackage.FILTER_VARIABLE_VALUE:
            return createFilterVariableValue();
        case ViewpointPackage.RGB_VALUES:
            return createRGBValues();
        case ViewpointPackage.DANALYSIS_SESSION_EOBJECT:
            return createDAnalysisSessionEObject();
        case ViewpointPackage.COLLAPSE_FILTER:
            return createCollapseFilter();
        case ViewpointPackage.INDIRECTLY_COLLAPSE_FILTER:
            return createIndirectlyCollapseFilter();
        case ViewpointPackage.SESSION_MANAGER_EOBJECT:
            return createSessionManagerEObject();
        case ViewpointPackage.DFILE:
            return createDFile();
        case ViewpointPackage.DRESOURCE_CONTAINER:
            return createDResourceContainer();
        case ViewpointPackage.DPROJECT:
            return createDProject();
        case ViewpointPackage.DFOLDER:
            return createDFolder();
        case ViewpointPackage.DMODEL:
            return createDModel();
        case ViewpointPackage.BASIC_LABEL_STYLE:
            return createBasicLabelStyle();
        case ViewpointPackage.BEGIN_LABEL_STYLE:
            return createBeginLabelStyle();
        case ViewpointPackage.CENTER_LABEL_STYLE:
            return createCenterLabelStyle();
        case ViewpointPackage.END_LABEL_STYLE:
            return createEndLabelStyle();
        case ViewpointPackage.BRACKET_EDGE_STYLE:
            return createBracketEdgeStyle();
        case ViewpointPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY:
            return createComputedStyleDescriptionRegistry();
        case ViewpointPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT:
            return (EObject) createDiagramElementMapping2ModelElement();
        case ViewpointPackage.MODEL_ELEMENT2_VIEW_VARIABLE:
            return (EObject) createModelElement2ViewVariable();
        case ViewpointPackage.VIEW_VARIABLE2_CONTAINER_VARIABLE:
            return (EObject) createViewVariable2ContainerVariable();
        case ViewpointPackage.CONTAINER_VARIABLE2_STYLE_DESCRIPTION:
            return (EObject) createContainerVariable2StyleDescription();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public Object createFromString(EDataType eDataType, String initialValue) {
        switch (eDataType.getClassifierID()) {
        case ViewpointPackage.CONTAINER_LAYOUT:
            return createContainerLayoutFromString(eDataType, initialValue);
        case ViewpointPackage.LABEL_POSITION:
            return createLabelPositionFromString(eDataType, initialValue);
        case ViewpointPackage.CONTAINER_SHAPE:
            return createContainerShapeFromString(eDataType, initialValue);
        case ViewpointPackage.BACKGROUND_STYLE:
            return createBackgroundStyleFromString(eDataType, initialValue);
        case ViewpointPackage.BUNDLED_IMAGE_SHAPE:
            return createBundledImageShapeFromString(eDataType, initialValue);
        case ViewpointPackage.LINE_STYLE:
            return createLineStyleFromString(eDataType, initialValue);
        case ViewpointPackage.EDGE_ARROWS:
            return createEdgeArrowsFromString(eDataType, initialValue);
        case ViewpointPackage.EDGE_ROUTING:
            return createEdgeRoutingFromString(eDataType, initialValue);
        case ViewpointPackage.ALIGNMENT_KIND:
            return createAlignmentKindFromString(eDataType, initialValue);
        case ViewpointPackage.FONT_FORMAT:
            return createFontFormatFromString(eDataType, initialValue);
        case ViewpointPackage.RESIZE_KIND:
            return createResizeKindFromString(eDataType, initialValue);
        case ViewpointPackage.LABEL_ALIGNMENT:
            return createLabelAlignmentFromString(eDataType, initialValue);
        case ViewpointPackage.ARRANGE_CONSTRAINT:
            return createArrangeConstraintFromString(eDataType, initialValue);
        case ViewpointPackage.SYNC_STATUS:
            return createSyncStatusFromString(eDataType, initialValue);
        case ViewpointPackage.EXTENDED_PACKAGE:
            return createExtendedPackageFromString(eDataType, initialValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */

    @Override
    public String convertToString(EDataType eDataType, Object instanceValue) {
        switch (eDataType.getClassifierID()) {
        case ViewpointPackage.CONTAINER_LAYOUT:
            return convertContainerLayoutToString(eDataType, instanceValue);
        case ViewpointPackage.LABEL_POSITION:
            return convertLabelPositionToString(eDataType, instanceValue);
        case ViewpointPackage.CONTAINER_SHAPE:
            return convertContainerShapeToString(eDataType, instanceValue);
        case ViewpointPackage.BACKGROUND_STYLE:
            return convertBackgroundStyleToString(eDataType, instanceValue);
        case ViewpointPackage.BUNDLED_IMAGE_SHAPE:
            return convertBundledImageShapeToString(eDataType, instanceValue);
        case ViewpointPackage.LINE_STYLE:
            return convertLineStyleToString(eDataType, instanceValue);
        case ViewpointPackage.EDGE_ARROWS:
            return convertEdgeArrowsToString(eDataType, instanceValue);
        case ViewpointPackage.EDGE_ROUTING:
            return convertEdgeRoutingToString(eDataType, instanceValue);
        case ViewpointPackage.ALIGNMENT_KIND:
            return convertAlignmentKindToString(eDataType, instanceValue);
        case ViewpointPackage.FONT_FORMAT:
            return convertFontFormatToString(eDataType, instanceValue);
        case ViewpointPackage.RESIZE_KIND:
            return convertResizeKindToString(eDataType, instanceValue);
        case ViewpointPackage.LABEL_ALIGNMENT:
            return convertLabelAlignmentToString(eDataType, instanceValue);
        case ViewpointPackage.ARRANGE_CONSTRAINT:
            return convertArrangeConstraintToString(eDataType, instanceValue);
        case ViewpointPackage.SYNC_STATUS:
            return convertSyncStatusToString(eDataType, instanceValue);
        case ViewpointPackage.EXTENDED_PACKAGE:
            return convertExtendedPackageToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DAnalysis createDAnalysis() {
        DAnalysisImpl dAnalysis = new DAnalysisImpl();
        return dAnalysis;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DView createDView() {
        DViewImpl dView = new DViewImpl();
        return dView;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public MetaModelExtension createMetaModelExtension() {
        MetaModelExtensionImpl metaModelExtension = new MetaModelExtensionImpl();
        return metaModelExtension;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DDiagram createDDiagram() {
        DDiagramImpl dDiagram = new DDiagramSpec();
        return dDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DSemanticDiagram createDSemanticDiagram() {
        DSemanticDiagramImpl dSemanticDiagram = new DSemanticDiagramSpec();
        return dSemanticDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public HideFilter createHideFilter() {
        HideFilterImpl hideFilter = new HideFilterImpl();
        return hideFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public HideLabelFilter createHideLabelFilter() {
        HideLabelFilterImpl hideLabelFilter = new HideLabelFilterImpl();
        return hideLabelFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FoldingPointFilter createFoldingPointFilter() {
        FoldingPointFilterImpl foldingPointFilter = new FoldingPointFilterImpl();
        return foldingPointFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FoldingFilter createFoldingFilter() {
        FoldingFilterImpl foldingFilter = new FoldingFilterImpl();
        return foldingFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AppliedCompositeFilters createAppliedCompositeFilters() {
        AppliedCompositeFiltersImpl appliedCompositeFilters = new AppliedCompositeFiltersImpl();
        return appliedCompositeFilters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AbsoluteBoundsFilter createAbsoluteBoundsFilter() {
        AbsoluteBoundsFilterImpl absoluteBoundsFilter = new AbsoluteBoundsFilterImpl();
        return absoluteBoundsFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Decoration createDecoration() {
        DecorationImpl decoration = new DecorationImpl();
        return decoration;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DEObjectLink createDEObjectLink() {
        DEObjectLinkImpl deObjectLink = new DEObjectLinkImpl();
        return deObjectLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DDiagramLink createDDiagramLink() {
        DDiagramLinkImpl dDiagramLink = new DDiagramLinkSpec();
        return dDiagramLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DSourceFileLink createDSourceFileLink() {
        DSourceFileLinkImpl dSourceFileLink = new DSourceFileLinkSpec();
        return dSourceFileLink;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DNode createDNode() {
        DNodeImpl dNode = new DNodeSpec();
        return dNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DNodeContainer createDNodeContainer() {
        DNodeContainerImpl dNodeContainer = new DNodeContainerSpec();
        return dNodeContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DNodeList createDNodeList() {
        DNodeListImpl dNodeList = new DNodeListSpec();
        return dNodeList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DNodeListElement createDNodeListElement() {
        DNodeListElementImpl dNodeListElement = new DNodeListElementSpec();
        return dNodeListElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DEdge createDEdge() {
        DEdgeImpl dEdge = new DEdgeSpec();
        return dEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DDiagramSet createDDiagramSet() {
        DDiagramSetImpl dDiagramSet = new DDiagramSetImpl();
        return dDiagramSet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Dot createDot() {
        DotImpl dot = new DotSpec();
        return dot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Square createSquare() {
        SquareImpl square = new SquareSpec();
        return square;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public BundledImage createBundledImage() {
        BundledImageImpl bundledImage = new BundledImageSpec();
        return bundledImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public WorkspaceImage createWorkspaceImage() {
        WorkspaceImageImpl workspaceImage = new WorkspaceImageSpec();
        return workspaceImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public CustomStyle createCustomStyle() {
        CustomStyleImpl customStyle = new CustomStyleSpec();
        return customStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public GaugeSection createGaugeSection() {
        GaugeSectionImpl gaugeSection = new GaugeSectionImpl();
        return gaugeSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public FlatContainerStyle createFlatContainerStyle() {
        FlatContainerStyleImpl flatContainerStyle = new FlatContainerStyleSpec();
        return flatContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public ShapeContainerStyle createShapeContainerStyle() {
        ShapeContainerStyleImpl shapeContainerStyle = new ShapeContainerStyleSpec();
        return shapeContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public EdgeStyle createEdgeStyle() {
        EdgeStyleImpl edgeStyle = new EdgeStyleSpec();
        return edgeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BracketEdgeStyle createBracketEdgeStyle() {
        BracketEdgeStyleImpl bracketEdgeStyle = new BracketEdgeStyleImpl();
        return bracketEdgeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ComputedStyleDescriptionRegistry createComputedStyleDescriptionRegistry() {
        ComputedStyleDescriptionRegistryImpl computedStyleDescriptionRegistry = new ComputedStyleDescriptionRegistryImpl();
        return computedStyleDescriptionRegistry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Map.Entry<DiagramElementMapping, EMap<EObject, EMap<EObject, EMap<EObject, StyleDescription>>>> createDiagramElementMapping2ModelElement() {
        DiagramElementMapping2ModelElementImpl diagramElementMapping2ModelElement = new DiagramElementMapping2ModelElementImpl();
        return diagramElementMapping2ModelElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Map.Entry<EObject, EMap<EObject, EMap<EObject, StyleDescription>>> createModelElement2ViewVariable() {
        ModelElement2ViewVariableImpl modelElement2ViewVariable = new ModelElement2ViewVariableImpl();
        return modelElement2ViewVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Map.Entry<EObject, EMap<EObject, StyleDescription>> createViewVariable2ContainerVariable() {
        ViewVariable2ContainerVariableImpl viewVariable2ContainerVariable = new ViewVariable2ContainerVariableImpl();
        return viewVariable2ContainerVariable;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public Map.Entry<EObject, StyleDescription> createContainerVariable2StyleDescription() {
        ContainerVariable2StyleDescriptionImpl containerVariable2StyleDescription = new ContainerVariable2StyleDescriptionImpl();
        return containerVariable2StyleDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerLayout createContainerLayoutFromString(EDataType eDataType, String initialValue) {
        ContainerLayout result = ContainerLayout.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertContainerLayoutToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DAnalysisCustomData createDAnalysisCustomData() {
        DAnalysisCustomDataImpl dAnalysisCustomData = new DAnalysisCustomDataImpl();
        return dAnalysisCustomData;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public DRepresentationContainer createDRepresentationContainer() {
        DRepresentationContainerImpl representationContainer = new DRepresentationContainerSpec();
        return representationContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public GaugeCompositeStyle createGaugeCompositeStyle() {
        GaugeCompositeStyleImpl gaugeCompositeStyle = new GaugeCompositeStyleSpec();
        return gaugeCompositeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelStyle createLabelStyle() {
        LabelStyleImpl labelStyle = new LabelStyleImpl();
        return labelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BorderedStyle createBorderedStyle() {
        BorderedStyleImpl borderedStyle = new BorderedStyleImpl();
        return borderedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Note createNote() {
        NoteImpl note = new NoteSpec();
        return note;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DragAndDropTarget createDragAndDropTarget() {
        DragAndDropTargetImpl dragAndDropTarget = new DragAndDropTargetImpl();
        return dragAndDropTarget;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterVariableHistory createFilterVariableHistory() {
        FilterVariableHistoryImpl filterVariableHistory = new FilterVariableHistoryImpl();
        return filterVariableHistory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FilterVariableValue createFilterVariableValue() {
        FilterVariableValueImpl filterVariableValue = new FilterVariableValueImpl();
        return filterVariableValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RGBValues createRGBValues() {
        RGBValuesImpl rgbValues = new RGBValuesImpl();
        return rgbValues;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DAnalysisSessionEObject createDAnalysisSessionEObject() {
        DAnalysisSessionEObjectImpl dAnalysisSessionEObject = new DAnalysisSessionEObjectImpl();
        return dAnalysisSessionEObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CollapseFilter createCollapseFilter() {
        CollapseFilterImpl collapseFilter = new CollapseFilterImpl();
        return collapseFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public IndirectlyCollapseFilter createIndirectlyCollapseFilter() {
        IndirectlyCollapseFilterImpl indirectlyCollapseFilter = new IndirectlyCollapseFilterImpl();
        return indirectlyCollapseFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SessionManagerEObject createSessionManagerEObject() {
        SessionManagerEObjectImpl sessionManagerEObject = new SessionManagerEObjectImpl();
        return sessionManagerEObject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DFile createDFile() {
        DFileImpl dFile = new DFileImpl();
        return dFile;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DResourceContainer createDResourceContainer() {
        DResourceContainerImpl dResourceContainer = new DResourceContainerImpl();
        return dResourceContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DProject createDProject() {
        DProjectImpl dProject = new DProjectImpl();
        return dProject;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DFolder createDFolder() {
        DFolderImpl dFolder = new DFolderImpl();
        return dFolder;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public DModel createDModel() {
        DModelImpl dModel = new DModelImpl();
        return dModel;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BasicLabelStyle createBasicLabelStyle() {
        BasicLabelStyleImpl basicLabelStyle = new BasicLabelStyleImpl();
        return basicLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BeginLabelStyle createBeginLabelStyle() {
        BeginLabelStyleImpl beginLabelStyle = new BeginLabelStyleImpl();
        return beginLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public CenterLabelStyle createCenterLabelStyle() {
        CenterLabelStyleImpl centerLabelStyle = new CenterLabelStyleImpl();
        return centerLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EndLabelStyle createEndLabelStyle() {
        EndLabelStyleImpl endLabelStyle = new EndLabelStyleImpl();
        return endLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Ellipse createEllipse() {
        EllipseImpl ellipse = new EllipseSpec();
        return ellipse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @not-generated
     */
    public Lozenge createLozenge() {
        LozengeImpl lozenge = new LozengeSpec();
        return lozenge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LabelPosition createLabelPositionFromString(EDataType eDataType, String initialValue) {
        LabelPosition result = LabelPosition.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertLabelPositionToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ContainerShape createContainerShapeFromString(EDataType eDataType, String initialValue) {
        ContainerShape result = ContainerShape.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertContainerShapeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BackgroundStyle createBackgroundStyleFromString(EDataType eDataType, String initialValue) {
        BackgroundStyle result = BackgroundStyle.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertBackgroundStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BundledImageShape createBundledImageShapeFromString(EDataType eDataType, String initialValue) {
        BundledImageShape result = BundledImageShape.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertBundledImageShapeToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public LineStyle createLineStyleFromString(EDataType eDataType, String initialValue) {
        LineStyle result = LineStyle.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertLineStyleToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeArrows createEdgeArrowsFromString(EDataType eDataType, String initialValue) {
        EdgeArrows result = EdgeArrows.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertEdgeArrowsToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EdgeRouting createEdgeRoutingFromString(EDataType eDataType, String initialValue) {
        EdgeRouting result = EdgeRouting.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertEdgeRoutingToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public AlignmentKind createAlignmentKindFromString(EDataType eDataType, String initialValue) {
        AlignmentKind result = AlignmentKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertAlignmentKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public FontFormat createFontFormatFromString(EDataType eDataType, String initialValue) {
        FontFormat result = FontFormat.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertFontFormatToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ResizeKind createResizeKindFromString(EDataType eDataType, String initialValue) {
        ResizeKind result = ResizeKind.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertResizeKindToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     */
    public LabelAlignment createLabelAlignmentFromString(EDataType eDataType, String initialValue) {
        LabelAlignment result = LabelAlignment.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc -->
     * 
     * @since 2.0 <!-- end-user-doc -->
     * @generated
     */
    public String convertLabelAlignmentToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ArrangeConstraint createArrangeConstraintFromString(EDataType eDataType, String initialValue) {
        ArrangeConstraint result = ArrangeConstraint.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertArrangeConstraintToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public SyncStatus createSyncStatusFromString(EDataType eDataType, String initialValue) {
        SyncStatus result = SyncStatus.get(initialValue);
        if (result == null)
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
        return result;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertSyncStatusToString(EDataType eDataType, Object instanceValue) {
        return instanceValue == null ? null : instanceValue.toString();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ModelAccessor createExtendedPackageFromString(EDataType eDataType, String initialValue) {
        return (ModelAccessor) super.createFromString(eDataType, initialValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String convertExtendedPackageToString(EDataType eDataType, Object instanceValue) {
        return super.convertToString(eDataType, instanceValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public ViewpointPackage getViewpointPackage() {
        return (ViewpointPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static ViewpointPackage getPackage() {
        return ViewpointPackage.eINSTANCE;
    }

} // SiriusFactoryImpl
