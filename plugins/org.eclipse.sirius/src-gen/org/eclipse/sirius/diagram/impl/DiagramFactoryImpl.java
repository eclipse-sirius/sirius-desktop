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
package org.eclipse.sirius.diagram.impl;

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
import org.eclipse.sirius.business.internal.metamodel.spec.DSemanticDiagramSpec;
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
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.AlignmentKind;
import org.eclipse.sirius.diagram.AppliedCompositeFilters;
import org.eclipse.sirius.diagram.ArrangeConstraint;
import org.eclipse.sirius.diagram.BackgroundStyle;
import org.eclipse.sirius.diagram.BeginLabelStyle;
import org.eclipse.sirius.diagram.BorderedStyle;
import org.eclipse.sirius.diagram.BracketEdgeStyle;
import org.eclipse.sirius.diagram.BundledImage;
import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.diagram.CenterLabelStyle;
import org.eclipse.sirius.diagram.CollapseFilter;
import org.eclipse.sirius.diagram.ComputedStyleDescriptionRegistry;
import org.eclipse.sirius.diagram.ContainerLayout;
import org.eclipse.sirius.diagram.ContainerShape;
import org.eclipse.sirius.diagram.CustomStyle;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramLink;
import org.eclipse.sirius.diagram.DDiagramSet;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.FilterVariableHistory;
import org.eclipse.sirius.diagram.FilterVariableValue;
import org.eclipse.sirius.diagram.FlatContainerStyle;
import org.eclipse.sirius.diagram.FoldingFilter;
import org.eclipse.sirius.diagram.FoldingPointFilter;
import org.eclipse.sirius.diagram.GaugeCompositeStyle;
import org.eclipse.sirius.diagram.GaugeSection;
import org.eclipse.sirius.diagram.HideFilter;
import org.eclipse.sirius.diagram.HideLabelFilter;
import org.eclipse.sirius.diagram.IndirectlyCollapseFilter;
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class DiagramFactoryImpl extends EFactoryImpl implements DiagramFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public static DiagramFactory init() {
        try {
            DiagramFactory theDiagramFactory = (DiagramFactory) EPackage.Registry.INSTANCE.getEFactory(DiagramPackage.eNS_URI);
            if (theDiagramFactory != null) {
                return theDiagramFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new DiagramFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     */
    public DiagramFactoryImpl() {
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
        case DiagramPackage.DDIAGRAM:
            return createDDiagram();
        case DiagramPackage.DSEMANTIC_DIAGRAM:
            return createDSemanticDiagram();
        case DiagramPackage.HIDE_FILTER:
            return createHideFilter();
        case DiagramPackage.HIDE_LABEL_FILTER:
            return createHideLabelFilter();
        case DiagramPackage.FOLDING_POINT_FILTER:
            return createFoldingPointFilter();
        case DiagramPackage.FOLDING_FILTER:
            return createFoldingFilter();
        case DiagramPackage.APPLIED_COMPOSITE_FILTERS:
            return createAppliedCompositeFilters();
        case DiagramPackage.ABSOLUTE_BOUNDS_FILTER:
            return createAbsoluteBoundsFilter();
        case DiagramPackage.DDIAGRAM_LINK:
            return createDDiagramLink();
        case DiagramPackage.DNODE:
            return createDNode();
        case DiagramPackage.DNODE_CONTAINER:
            return createDNodeContainer();
        case DiagramPackage.DNODE_LIST:
            return createDNodeList();
        case DiagramPackage.DNODE_LIST_ELEMENT:
            return createDNodeListElement();
        case DiagramPackage.DEDGE:
            return createDEdge();
        case DiagramPackage.DDIAGRAM_SET:
            return createDDiagramSet();
        case DiagramPackage.DOT:
            return createDot();
        case DiagramPackage.GAUGE_SECTION:
            return createGaugeSection();
        case DiagramPackage.FLAT_CONTAINER_STYLE:
            return createFlatContainerStyle();
        case DiagramPackage.SHAPE_CONTAINER_STYLE:
            return createShapeContainerStyle();
        case DiagramPackage.SQUARE:
            return createSquare();
        case DiagramPackage.ELLIPSE:
            return createEllipse();
        case DiagramPackage.LOZENGE:
            return createLozenge();
        case DiagramPackage.BUNDLED_IMAGE:
            return createBundledImage();
        case DiagramPackage.WORKSPACE_IMAGE:
            return createWorkspaceImage();
        case DiagramPackage.CUSTOM_STYLE:
            return createCustomStyle();
        case DiagramPackage.EDGE_STYLE:
            return createEdgeStyle();
        case DiagramPackage.GAUGE_COMPOSITE_STYLE:
            return createGaugeCompositeStyle();
        case DiagramPackage.BORDERED_STYLE:
            return createBorderedStyle();
        case DiagramPackage.NOTE:
            return createNote();
        case DiagramPackage.FILTER_VARIABLE_HISTORY:
            return createFilterVariableHistory();
        case DiagramPackage.FILTER_VARIABLE_VALUE:
            return createFilterVariableValue();
        case DiagramPackage.COLLAPSE_FILTER:
            return createCollapseFilter();
        case DiagramPackage.INDIRECTLY_COLLAPSE_FILTER:
            return createIndirectlyCollapseFilter();
        case DiagramPackage.BEGIN_LABEL_STYLE:
            return createBeginLabelStyle();
        case DiagramPackage.CENTER_LABEL_STYLE:
            return createCenterLabelStyle();
        case DiagramPackage.END_LABEL_STYLE:
            return createEndLabelStyle();
        case DiagramPackage.BRACKET_EDGE_STYLE:
            return createBracketEdgeStyle();
        case DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY:
            return createComputedStyleDescriptionRegistry();
        case DiagramPackage.DIAGRAM_ELEMENT_MAPPING2_MODEL_ELEMENT:
            return (EObject) createDiagramElementMapping2ModelElement();
        case DiagramPackage.MODEL_ELEMENT2_VIEW_VARIABLE:
            return (EObject) createModelElement2ViewVariable();
        case DiagramPackage.VIEW_VARIABLE2_CONTAINER_VARIABLE:
            return (EObject) createViewVariable2ContainerVariable();
        case DiagramPackage.CONTAINER_VARIABLE2_STYLE_DESCRIPTION:
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
        case DiagramPackage.CONTAINER_LAYOUT:
            return createContainerLayoutFromString(eDataType, initialValue);
        case DiagramPackage.LABEL_POSITION:
            return createLabelPositionFromString(eDataType, initialValue);
        case DiagramPackage.CONTAINER_SHAPE:
            return createContainerShapeFromString(eDataType, initialValue);
        case DiagramPackage.BACKGROUND_STYLE:
            return createBackgroundStyleFromString(eDataType, initialValue);
        case DiagramPackage.BUNDLED_IMAGE_SHAPE:
            return createBundledImageShapeFromString(eDataType, initialValue);
        case DiagramPackage.LINE_STYLE:
            return createLineStyleFromString(eDataType, initialValue);
        case DiagramPackage.EDGE_ARROWS:
            return createEdgeArrowsFromString(eDataType, initialValue);
        case DiagramPackage.EDGE_ROUTING:
            return createEdgeRoutingFromString(eDataType, initialValue);
        case DiagramPackage.ALIGNMENT_KIND:
            return createAlignmentKindFromString(eDataType, initialValue);
        case DiagramPackage.RESIZE_KIND:
            return createResizeKindFromString(eDataType, initialValue);
        case DiagramPackage.ARRANGE_CONSTRAINT:
            return createArrangeConstraintFromString(eDataType, initialValue);
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
        case DiagramPackage.CONTAINER_LAYOUT:
            return convertContainerLayoutToString(eDataType, instanceValue);
        case DiagramPackage.LABEL_POSITION:
            return convertLabelPositionToString(eDataType, instanceValue);
        case DiagramPackage.CONTAINER_SHAPE:
            return convertContainerShapeToString(eDataType, instanceValue);
        case DiagramPackage.BACKGROUND_STYLE:
            return convertBackgroundStyleToString(eDataType, instanceValue);
        case DiagramPackage.BUNDLED_IMAGE_SHAPE:
            return convertBundledImageShapeToString(eDataType, instanceValue);
        case DiagramPackage.LINE_STYLE:
            return convertLineStyleToString(eDataType, instanceValue);
        case DiagramPackage.EDGE_ARROWS:
            return convertEdgeArrowsToString(eDataType, instanceValue);
        case DiagramPackage.EDGE_ROUTING:
            return convertEdgeRoutingToString(eDataType, instanceValue);
        case DiagramPackage.ALIGNMENT_KIND:
            return convertAlignmentKindToString(eDataType, instanceValue);
        case DiagramPackage.RESIZE_KIND:
            return convertResizeKindToString(eDataType, instanceValue);
        case DiagramPackage.ARRANGE_CONSTRAINT:
            return convertArrangeConstraintToString(eDataType, instanceValue);
        default:
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
        }
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
    public Square createSquare() {
        SquareImpl square = new SquareSpec();
        return square;
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
     * @not-generated
     */
    public EdgeStyle createEdgeStyle() {
        EdgeStyleImpl edgeStyle = new EdgeStyleSpec();
        return edgeStyle;
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
    public DiagramPackage getDiagramPackage() {
        return (DiagramPackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @deprecated
     * @generated
     */
    @Deprecated
    public static DiagramPackage getPackage() {
        return DiagramPackage.eINSTANCE;
    }

} // DiagramFactoryImpl
