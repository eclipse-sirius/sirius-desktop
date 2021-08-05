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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
import org.eclipse.sirius.diagram.EdgeStyle;
import org.eclipse.sirius.diagram.Ellipse;
import org.eclipse.sirius.diagram.EndLabelStyle;
import org.eclipse.sirius.diagram.FilterVariableHistory;
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
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DDiagramSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DEdgeSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeContainerSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListElementSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeListSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DNodeSpec;
import org.eclipse.sirius.diagram.business.internal.metamodel.spec.DSemanticDiagramSpec;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class DiagramFactoryImpl extends EFactoryImpl implements DiagramFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
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
        case DiagramPackage.DRAG_AND_DROP_TARGET:
            return createDragAndDropTarget();
        case DiagramPackage.TYPED_VARIABLE_VALUE:
            return createTypedVariableValue();
        case DiagramPackage.EOBJECT_VARIABLE_VALUE:
            return createEObjectVariableValue();
        default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
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
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
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
            throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DDiagram createDDiagram() {
        DDiagramImpl dDiagram = new DDiagramSpec();
        return dDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DSemanticDiagram createDSemanticDiagram() {
        DSemanticDiagramImpl dSemanticDiagram = new DSemanticDiagramSpec();
        return dSemanticDiagram;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HideFilter createHideFilter() {
        HideFilterImpl hideFilter = new HideFilterImpl();
        return hideFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public HideLabelFilter createHideLabelFilter() {
        HideLabelFilterImpl hideLabelFilter = new HideLabelFilterImpl();
        return hideLabelFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FoldingPointFilter createFoldingPointFilter() {
        FoldingPointFilterImpl foldingPointFilter = new FoldingPointFilterImpl();
        return foldingPointFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FoldingFilter createFoldingFilter() {
        FoldingFilterImpl foldingFilter = new FoldingFilterImpl();
        return foldingFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AppliedCompositeFilters createAppliedCompositeFilters() {
        AppliedCompositeFiltersImpl appliedCompositeFilters = new AppliedCompositeFiltersImpl();
        return appliedCompositeFilters;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public AbsoluteBoundsFilter createAbsoluteBoundsFilter() {
        AbsoluteBoundsFilterImpl absoluteBoundsFilter = new AbsoluteBoundsFilterImpl();
        return absoluteBoundsFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DNode createDNode() {
        DNodeImpl dNode = new DNodeSpec();
        return dNode;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DNodeContainer createDNodeContainer() {
        DNodeContainerImpl dNodeContainer = new DNodeContainerSpec();
        return dNodeContainer;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DNodeList createDNodeList() {
        DNodeListImpl dNodeList = new DNodeListSpec();
        return dNodeList;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DNodeListElement createDNodeListElement() {
        DNodeListElementImpl dNodeListElement = new DNodeListElementSpec();
        return dNodeListElement;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @not-generated
     */
    @Override
    public DEdge createDEdge() {
        DEdgeImpl dEdge = new DEdgeSpec();
        return dEdge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Dot createDot() {
        DotImpl dot = new DotImpl();
        return dot;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GaugeSection createGaugeSection() {
        GaugeSectionImpl gaugeSection = new GaugeSectionImpl();
        return gaugeSection;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FlatContainerStyle createFlatContainerStyle() {
        FlatContainerStyleImpl flatContainerStyle = new FlatContainerStyleImpl();
        return flatContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ShapeContainerStyle createShapeContainerStyle() {
        ShapeContainerStyleImpl shapeContainerStyle = new ShapeContainerStyleImpl();
        return shapeContainerStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Square createSquare() {
        SquareImpl square = new SquareImpl();
        return square;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Ellipse createEllipse() {
        EllipseImpl ellipse = new EllipseImpl();
        return ellipse;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Lozenge createLozenge() {
        LozengeImpl lozenge = new LozengeImpl();
        return lozenge;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BundledImage createBundledImage() {
        BundledImageImpl bundledImage = new BundledImageImpl();
        return bundledImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public WorkspaceImage createWorkspaceImage() {
        WorkspaceImageImpl workspaceImage = new WorkspaceImageImpl();
        return workspaceImage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CustomStyle createCustomStyle() {
        CustomStyleImpl customStyle = new CustomStyleImpl();
        return customStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EdgeStyle createEdgeStyle() {
        EdgeStyleImpl edgeStyle = new EdgeStyleImpl();
        return edgeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public GaugeCompositeStyle createGaugeCompositeStyle() {
        GaugeCompositeStyleImpl gaugeCompositeStyle = new GaugeCompositeStyleImpl();
        return gaugeCompositeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BorderedStyle createBorderedStyle() {
        BorderedStyleImpl borderedStyle = new BorderedStyleImpl();
        return borderedStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public Note createNote() {
        NoteImpl note = new NoteImpl();
        return note;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public FilterVariableHistory createFilterVariableHistory() {
        FilterVariableHistoryImpl filterVariableHistory = new FilterVariableHistoryImpl();
        return filterVariableHistory;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CollapseFilter createCollapseFilter() {
        CollapseFilterImpl collapseFilter = new CollapseFilterImpl();
        return collapseFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public IndirectlyCollapseFilter createIndirectlyCollapseFilter() {
        IndirectlyCollapseFilterImpl indirectlyCollapseFilter = new IndirectlyCollapseFilterImpl();
        return indirectlyCollapseFilter;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BeginLabelStyle createBeginLabelStyle() {
        BeginLabelStyleImpl beginLabelStyle = new BeginLabelStyleImpl();
        return beginLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public CenterLabelStyle createCenterLabelStyle() {
        CenterLabelStyleImpl centerLabelStyle = new CenterLabelStyleImpl();
        return centerLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EndLabelStyle createEndLabelStyle() {
        EndLabelStyleImpl endLabelStyle = new EndLabelStyleImpl();
        return endLabelStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public BracketEdgeStyle createBracketEdgeStyle() {
        BracketEdgeStyleImpl bracketEdgeStyle = new BracketEdgeStyleImpl();
        return bracketEdgeStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ComputedStyleDescriptionRegistry createComputedStyleDescriptionRegistry() {
        ComputedStyleDescriptionRegistryImpl computedStyleDescriptionRegistry = new ComputedStyleDescriptionRegistryImpl();
        return computedStyleDescriptionRegistry;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DragAndDropTarget createDragAndDropTarget() {
        DragAndDropTargetImpl dragAndDropTarget = new DragAndDropTargetImpl();
        return dragAndDropTarget;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public TypedVariableValue createTypedVariableValue() {
        TypedVariableValueImpl typedVariableValue = new TypedVariableValueImpl();
        return typedVariableValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EObjectVariableValue createEObjectVariableValue() {
        EObjectVariableValueImpl eObjectVariableValue = new EObjectVariableValueImpl();
        return eObjectVariableValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public ContainerLayout createContainerLayoutFromString(EDataType eDataType, String initialValue) {
        ContainerLayout result = ContainerLayout.get(initialValue);
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
        if (result == null) {
            throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }
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
    @Override
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
