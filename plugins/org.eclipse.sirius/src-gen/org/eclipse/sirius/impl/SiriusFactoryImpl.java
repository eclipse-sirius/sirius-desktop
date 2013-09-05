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
package org.eclipse.sirius.impl;

import java.util.Map;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.sirius.AbsoluteBoundsFilter;
import org.eclipse.sirius.AlignmentKind;
import org.eclipse.sirius.AppliedCompositeFilters;
import org.eclipse.sirius.ArrangeConstraint;
import org.eclipse.sirius.BackgroundStyle;
import org.eclipse.sirius.BasicLabelStyle;
import org.eclipse.sirius.BeginLabelStyle;
import org.eclipse.sirius.BorderedStyle;
import org.eclipse.sirius.BracketEdgeStyle;
import org.eclipse.sirius.BundledImage;
import org.eclipse.sirius.BundledImageShape;
import org.eclipse.sirius.CenterLabelStyle;
import org.eclipse.sirius.CollapseFilter;
import org.eclipse.sirius.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.ContainerLayout;
import org.eclipse.sirius.ContainerShape;
import org.eclipse.sirius.CustomStyle;
import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DAnalysisCustomData;
import org.eclipse.sirius.DAnalysisSessionEObject;
import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.DDiagramLink;
import org.eclipse.sirius.DDiagramSet;
import org.eclipse.sirius.DEObjectLink;
import org.eclipse.sirius.DEdge;
import org.eclipse.sirius.DFile;
import org.eclipse.sirius.DFolder;
import org.eclipse.sirius.DModel;
import org.eclipse.sirius.DNode;
import org.eclipse.sirius.DNodeContainer;
import org.eclipse.sirius.DNodeList;
import org.eclipse.sirius.DNodeListElement;
import org.eclipse.sirius.DProject;
import org.eclipse.sirius.DRepresentationContainer;
import org.eclipse.sirius.DResourceContainer;
import org.eclipse.sirius.DSemanticDiagram;
import org.eclipse.sirius.DSourceFileLink;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.Decoration;
import org.eclipse.sirius.Dot;
import org.eclipse.sirius.DragAndDropTarget;
import org.eclipse.sirius.EdgeArrows;
import org.eclipse.sirius.EdgeRouting;
import org.eclipse.sirius.EdgeStyle;
import org.eclipse.sirius.Ellipse;
import org.eclipse.sirius.EndLabelStyle;
import org.eclipse.sirius.FilterVariableHistory;
import org.eclipse.sirius.FilterVariableValue;
import org.eclipse.sirius.FlatContainerStyle;
import org.eclipse.sirius.FoldingFilter;
import org.eclipse.sirius.FoldingPointFilter;
import org.eclipse.sirius.FontFormat;
import org.eclipse.sirius.GaugeCompositeStyle;
import org.eclipse.sirius.GaugeSection;
import org.eclipse.sirius.HideFilter;
import org.eclipse.sirius.HideLabelFilter;
import org.eclipse.sirius.IndirectlyCollapseFilter;
import org.eclipse.sirius.LabelAlignment;
import org.eclipse.sirius.LabelPosition;
import org.eclipse.sirius.LabelStyle;
import org.eclipse.sirius.LineStyle;
import org.eclipse.sirius.Lozenge;
import org.eclipse.sirius.MetaModelExtension;
import org.eclipse.sirius.Note;
import org.eclipse.sirius.RGBValues;
import org.eclipse.sirius.ResizeKind;
import org.eclipse.sirius.SessionManagerEObject;
import org.eclipse.sirius.ShapeContainerStyle;
import org.eclipse.sirius.Square;
import org.eclipse.sirius.SyncStatus;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.WorkspaceImage;
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
import org.eclipse.sirius.description.DiagramElementMapping;
import org.eclipse.sirius.description.style.StyleDescription;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class SiriusFactoryImpl extends EFactoryImpl implements SiriusFactory {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static SiriusFactory init() {
        try {
            SiriusFactory theSiriusFactory = (SiriusFactory) EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/sirius/1.1.0");
            if (theSiriusFactory != null) {
                return theSiriusFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new SiriusFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public SiriusFactoryImpl() {
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
        case SiriusPackage.DANALYSIS:
            return createDAnalysis();
        case SiriusPackage.DREPRESENTATION_CONTAINER:
            return createDRepresentationContainer();
        case SiriusPackage.DVIEW:
            return createDView();
        case SiriusPackage.META_MODEL_EXTENSION:
            return createMetaModelExtension();
        case SiriusPackage.DDIAGRAM:
            return createDDiagram();
        case SiriusPackage.DSEMANTIC_DIAGRAM:
            return createDSemanticDiagram();
        case SiriusPackage.HIDE_FILTER:
            return createHideFilter();
        case SiriusPackage.HIDE_LABEL_FILTER:
            return createHideLabelFilter();
        case SiriusPackage.FOLDING_POINT_FILTER:
            return createFoldingPointFilter();
        case SiriusPackage.FOLDING_FILTER:
            return createFoldingFilter();
        case SiriusPackage.APPLIED_COMPOSITE_FILTERS:
            return createAppliedCompositeFilters();
        case SiriusPackage.ABSOLUTE_BOUNDS_FILTER:
            return createAbsoluteBoundsFilter();
        case SiriusPackage.DECORATION:
            return createDecoration();
        case SiriusPackage.DE_OBJECT_LINK:
            return createDEObjectLink();
        case SiriusPackage.DDIAGRAM_LINK:
            return createDDiagramLink();
        case SiriusPackage.DSOURCE_FILE_LINK:
            return createDSourceFileLink();
        case SiriusPackage.DNODE:
            return createDNode();
        case SiriusPackage.DNODE_CONTAINER:
            return createDNodeContainer();
        case SiriusPackage.DNODE_LIST:
            return createDNodeList();
        case SiriusPackage.DNODE_LIST_ELEMENT:
            return createDNodeListElement();
        case SiriusPackage.DEDGE:
            return createDEdge();
        case SiriusPackage.DDIAGRAM_SET:
            return createDDiagramSet();
        case SiriusPackage.DOT:
            return createDot();
        case SiriusPackage.GAUGE_SECTION:
            return createGaugeSection();
        case SiriusPackage.FLAT_CONTAINER_STYLE:
            return createFlatContainerStyle();
        case SiriusPackage.SHAPE_CONTAINER_STYLE:
            return createShapeContainerStyle();
        case SiriusPackage.SQUARE:
            return createSquare();
        case SiriusPackage.ELLIPSE:
            return createEllipse();
        case SiriusPackage.LOZENGE:
            return createLozenge();
        case SiriusPackage.BUNDLED_IMAGE:
            return createBundledImage();
        case SiriusPackage.WORKSPACE_IMAGE:
            return createWorkspaceImage();
        case SiriusPackage.CUSTOM_STYLE:
            return createCustomStyle();
        case SiriusPackage.EDGE_STYLE:
            return createEdgeStyle();
        case SiriusPackage.DANALYSIS_CUSTOM_DATA:
            return createDAnalysisCustomData();
        case SiriusPackage.GAUGE_COMPOSITE_STYLE:
            return createGaugeCompositeStyle();
        case SiriusPackage.LABEL_STYLE:
            return createLabelStyle();
        case SiriusPackage.BORDERED_STYLE:
            return createBorderedStyle();
        case SiriusPackage.NOTE:
            return createNote();
        case SiriusPackage.DRAG_AND_DROP_TARGET:
            return createDragAndDropTarget();
        case SiriusPackage.FILTER_VARIABLE_HISTORY:
            return createFilterVariableHistory();
        case SiriusPackage.FILTER_VARIABLE_VALUE:
            return createFilterVariableValue();
        case SiriusPackage.RGB_VALUES:
            return createRGBValues();
        case SiriusPackage.DANALYSIS_SESSION_EOBJECT:
            return createDAnalysisSessionEObject();
        case SiriusPackage.COLLAPSE_FILTER:
            return createCollapseFilter();
        case SiriusPackage.INDIRECTLY_COLLAPSE_FILTER:
            return createIndirectlyCollapseFilter();
        case SiriusPackage.SESSION_MANAGER_EOBJECT:
            return createSessionManagerEObject();
        case SiriusPackage.DFILE:
            return createDFile();
        case SiriusPackage.DRESOURCE_CONTAINER:
            return createDResourceContainer();
        case SiriusPackage.DPROJECT:
            return createDProject();
        case SiriusPackage.DFOLDER:
            return createDFolder();
        case SiriusPackage.DMODEL:
            return createDModel();
        case SiriusPackage.BASIC_LABEL_STYLE:
            return createBasicLabelStyle();
        case SiriusPackage.BEGIN_LABEL_STYLE:
            return createBeginLabelStyle();
        case SiriusPackage.CENTER_LABEL_STYLE:
            return createCenterLabelStyle();
        case SiriusPackage.END_LABEL_STYLE:
            return createEndLabelStyle();
        case SiriusPackage.BRACKET_EDGE_STYLE:
            return createBracketEdgeStyle();
        case SiriusPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY:
            return createComputedStyleDescriptionRegistry();
        case SiriusPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT:
            return (EObject) createDiagramElementMapping2ModelElement();
        case SiriusPackage.MODEL_ELEMENT2_VIEW_VARIABLE:
            return (EObject) createModelElement2ViewVariable();
        case SiriusPackage.VIEW_VARIABLE2_CONTAINER_VARIABLE:
            return (EObject) createViewVariable2ContainerVariable();
        case SiriusPackage.CONTAINER_VARIABLE2_STYLE_DESCRIPTION:
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
        case SiriusPackage.CONTAINER_LAYOUT:
            return createContainerLayoutFromString(eDataType, initialValue);
        case SiriusPackage.LABEL_POSITION:
            return createLabelPositionFromString(eDataType, initialValue);
        case SiriusPackage.CONTAINER_SHAPE:
            return createContainerShapeFromString(eDataType, initialValue);
        case SiriusPackage.BACKGROUND_STYLE:
            return createBackgroundStyleFromString(eDataType, initialValue);
        case SiriusPackage.BUNDLED_IMAGE_SHAPE:
            return createBundledImageShapeFromString(eDataType, initialValue);
        case SiriusPackage.LINE_STYLE:
            return createLineStyleFromString(eDataType, initialValue);
        case SiriusPackage.EDGE_ARROWS:
            return createEdgeArrowsFromString(eDataType, initialValue);
        case SiriusPackage.EDGE_ROUTING:
            return createEdgeRoutingFromString(eDataType, initialValue);
        case SiriusPackage.ALIGNMENT_KIND:
            return createAlignmentKindFromString(eDataType, initialValue);
        case SiriusPackage.FONT_FORMAT:
            return createFontFormatFromString(eDataType, initialValue);
        case SiriusPackage.RESIZE_KIND:
            return createResizeKindFromString(eDataType, initialValue);
        case SiriusPackage.LABEL_ALIGNMENT:
            return createLabelAlignmentFromString(eDataType, initialValue);
        case SiriusPackage.ARRANGE_CONSTRAINT:
            return createArrangeConstraintFromString(eDataType, initialValue);
        case SiriusPackage.SYNC_STATUS:
            return createSyncStatusFromString(eDataType, initialValue);
        case SiriusPackage.EXTENDED_PACKAGE:
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
        case SiriusPackage.CONTAINER_LAYOUT:
            return convertContainerLayoutToString(eDataType, instanceValue);
        case SiriusPackage.LABEL_POSITION:
            return convertLabelPositionToString(eDataType, instanceValue);
        case SiriusPackage.CONTAINER_SHAPE:
            return convertContainerShapeToString(eDataType, instanceValue);
        case SiriusPackage.BACKGROUND_STYLE:
            return convertBackgroundStyleToString(eDataType, instanceValue);
        case SiriusPackage.BUNDLED_IMAGE_SHAPE:
            return convertBundledImageShapeToString(eDataType, instanceValue);
        case SiriusPackage.LINE_STYLE:
            return convertLineStyleToString(eDataType, instanceValue);
        case SiriusPackage.EDGE_ARROWS:
            return convertEdgeArrowsToString(eDataType, instanceValue);
        case SiriusPackage.EDGE_ROUTING:
            return convertEdgeRoutingToString(eDataType, instanceValue);
        case SiriusPackage.ALIGNMENT_KIND:
            return convertAlignmentKindToString(eDataType, instanceValue);
        case SiriusPackage.FONT_FORMAT:
            return convertFontFormatToString(eDataType, instanceValue);
        case SiriusPackage.RESIZE_KIND:
            return convertResizeKindToString(eDataType, instanceValue);
        case SiriusPackage.LABEL_ALIGNMENT:
            return convertLabelAlignmentToString(eDataType, instanceValue);
        case SiriusPackage.ARRANGE_CONSTRAINT:
            return convertArrangeConstraintToString(eDataType, instanceValue);
        case SiriusPackage.SYNC_STATUS:
            return convertSyncStatusToString(eDataType, instanceValue);
        case SiriusPackage.EXTENDED_PACKAGE:
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
    public SiriusPackage getSiriusPackage() {
        return (SiriusPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static SiriusPackage getPackage() {
        return SiriusPackage.eINSTANCE;
    }

} // SiriusFactoryImpl
