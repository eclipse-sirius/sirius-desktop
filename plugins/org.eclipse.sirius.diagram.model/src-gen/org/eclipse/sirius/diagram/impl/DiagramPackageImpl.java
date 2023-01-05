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
package org.eclipse.sirius.diagram.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.AbstractDNode;
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
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.Dot;
import org.eclipse.sirius.diagram.DragAndDropTarget;
import org.eclipse.sirius.diagram.EObjectVariableValue;
import org.eclipse.sirius.diagram.EdgeArrows;
import org.eclipse.sirius.diagram.EdgeRouting;
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
import org.eclipse.sirius.diagram.LabelPosition;
import org.eclipse.sirius.diagram.LineStyle;
import org.eclipse.sirius.diagram.Lozenge;
import org.eclipse.sirius.diagram.NodeStyle;
import org.eclipse.sirius.diagram.Note;
import org.eclipse.sirius.diagram.ResizeKind;
import org.eclipse.sirius.diagram.ShapeContainerStyle;
import org.eclipse.sirius.diagram.Square;
import org.eclipse.sirius.diagram.TypedVariableValue;
import org.eclipse.sirius.diagram.VariableValue;
import org.eclipse.sirius.diagram.WorkspaceImage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.impl.StylePackageImpl;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class DiagramPackageImpl extends EPackageImpl implements DiagramPackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dSemanticDiagramEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dDiagramElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass graphicalFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hideFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hideLabelFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass foldingPointFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass foldingFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass appliedCompositeFiltersEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass absoluteBoundsFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractDNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dNodeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dDiagramElementContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dNodeContainerEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dNodeListEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dNodeListElementEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dEdgeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dotEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass gaugeSectionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass containerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass flatContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass shapeContainerStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass squareEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass ellipseEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass lozengeEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass bundledImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass workspaceImageEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeTargetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass gaugeCompositeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass borderedStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass noteEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass filterVariableHistoryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass collapseFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass indirectlyCollapseFilterEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass beginLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass centerLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass endLabelStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass bracketEdgeStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass computedStyleDescriptionRegistryEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dragAndDropTargetEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hideLabelCapabilityStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass variableValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass typedVariableValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass eObjectVariableValueEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum containerLayoutEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum labelPositionEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum containerShapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum backgroundStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum bundledImageShapeEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum lineStyleEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum edgeArrowsEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum edgeRoutingEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum alignmentKindEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum resizeKindEEnum = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum arrangeConstraintEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.DiagramPackage#eNS_URI
     * @see #init()
     * @generated
     */
    private DiagramPackageImpl() {
        super(DiagramPackage.eNS_URI, DiagramFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
     *
     * <p>
     * This method is used to initialize {@link DiagramPackage#eINSTANCE} when that field is accessed. Clients should
     * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static DiagramPackage init() {
        if (DiagramPackageImpl.isInited) {
            return (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        }

        // Obtain or create and register package
        Object registeredDiagramPackage = EPackage.Registry.INSTANCE.get(DiagramPackage.eNS_URI);
        DiagramPackageImpl theDiagramPackage = registeredDiagramPackage instanceof DiagramPackageImpl ? (DiagramPackageImpl) registeredDiagramPackage : new DiagramPackageImpl();

        DiagramPackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (registeredPackage instanceof DescriptionPackageImpl ? registeredPackage : DescriptionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        StylePackageImpl theStylePackage = (StylePackageImpl) (registeredPackage instanceof StylePackageImpl ? registeredPackage : StylePackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (registeredPackage instanceof ToolPackageImpl ? registeredPackage : ToolPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (registeredPackage instanceof FilterPackageImpl ? registeredPackage : FilterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (registeredPackage instanceof ConcernPackageImpl ? registeredPackage : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theDiagramPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theDiagramPackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(DiagramPackage.eNS_URI, theDiagramPackage);
        return theDiagramPackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDDiagram() {
        return dDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_OwnedDiagramElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_DiagramElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_Description() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_Edges() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_Nodes() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_NodeListElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_Containers() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_CurrentConcern() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_ActivatedFilters() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_ActivatedTransientLayers() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_AllFilters() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_ActivatedRules() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_ActivateBehaviors() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_FilterVariableHistory() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(13);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_ActivatedLayers() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(14);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagram_Synchronized() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(15);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagram_HiddenElements() {
        return (EReference) dDiagramEClass.getEStructuralFeatures().get(16);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagram_IsInLayoutingMode() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(17);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagram_IsInShowingMode() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(18);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagram_HeaderHeight() {
        return (EAttribute) dDiagramEClass.getEStructuralFeatures().get(19);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDSemanticDiagram() {
        return dSemanticDiagramEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDDiagramElement() {
        return dDiagramElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagramElement_Visible() {
        return (EAttribute) dDiagramElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagramElement_TooltipText() {
        return (EAttribute) dDiagramElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElement_ParentLayers() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElement_Decorations() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElement_TransientDecorations() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElement_DiagramElementMapping() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElement_GraphicalFilters() {
        return (EReference) dDiagramElementEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGraphicalFilter() {
        return graphicalFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHideFilter() {
        return hideFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHideLabelFilter() {
        return hideLabelFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHideLabelFilter_HiddenLabels() {
        return (EAttribute) hideLabelFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFoldingPointFilter() {
        return foldingPointFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFoldingFilter() {
        return foldingFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAppliedCompositeFilters() {
        return appliedCompositeFiltersEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAppliedCompositeFilters_CompositeFilterDescriptions() {
        return (EReference) appliedCompositeFiltersEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbsoluteBoundsFilter() {
        return absoluteBoundsFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbsoluteBoundsFilter_X() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbsoluteBoundsFilter_Y() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbsoluteBoundsFilter_Height() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbsoluteBoundsFilter_Width() {
        return (EAttribute) absoluteBoundsFilterEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractDNode() {
        return abstractDNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractDNode_OwnedBorderedNodes() {
        return (EReference) abstractDNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractDNode_ArrangeConstraints() {
        return (EAttribute) abstractDNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDNode() {
        return dNodeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDNode_Width() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDNode_Height() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNode_OwnedStyle() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDNode_LabelPosition() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDNode_ResizeKind() {
        return (EAttribute) dNodeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNode_OriginalStyle() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNode_ActualMapping() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNode_CandidatesMapping() {
        return (EReference) dNodeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDDiagramElementContainer() {
        return dDiagramElementContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_Nodes() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_Containers() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_Elements() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_OwnedStyle() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_OriginalStyle() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_ActualMapping() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDDiagramElementContainer_CandidatesMapping() {
        return (EReference) dDiagramElementContainerEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagramElementContainer_Width() {
        return (EAttribute) dDiagramElementContainerEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDDiagramElementContainer_Height() {
        return (EAttribute) dDiagramElementContainerEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDNodeContainer() {
        return dNodeContainerEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeContainer_OwnedDiagramElements() {
        return (EReference) dNodeContainerEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDNodeContainer_ChildrenPresentation() {
        return (EAttribute) dNodeContainerEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDNodeList() {
        return dNodeListEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeList_OwnedElements() {
        return (EReference) dNodeListEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDNodeListElement() {
        return dNodeListElementEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeListElement_OwnedStyle() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeListElement_OriginalStyle() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeListElement_ActualMapping() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDNodeListElement_CandidatesMapping() {
        return (EReference) dNodeListElementEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDEdge() {
        return dEdgeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_OwnedStyle() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_Size() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_SourceNode() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_TargetNode() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_ActualMapping() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_RoutingStyle() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_IsFold() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_IsMockEdge() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_OriginalStyle() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDEdge_Path() {
        return (EReference) dEdgeEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_ArrangeConstraints() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_BeginLabel() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDEdge_EndLabel() {
        return (EAttribute) dEdgeEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeStyle() {
        return nodeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyle_LabelPosition() {
        return (EAttribute) nodeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDot() {
        return dotEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDot_BackgroundColor() {
        return (EAttribute) dotEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDot_StrokeSizeComputationExpression() {
        return (EAttribute) dotEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGaugeSection() {
        return gaugeSectionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_Min() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_Max() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_Value() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_Label() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_BackgroundColor() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSection_ForegroundColor() {
        return (EAttribute) gaugeSectionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerStyle() {
        return containerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFlatContainerStyle() {
        return flatContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFlatContainerStyle_BackgroundStyle() {
        return (EAttribute) flatContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFlatContainerStyle_BackgroundColor() {
        return (EAttribute) flatContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFlatContainerStyle_ForegroundColor() {
        return (EAttribute) flatContainerStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getShapeContainerStyle() {
        return shapeContainerStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getShapeContainerStyle_Shape() {
        return (EAttribute) shapeContainerStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getShapeContainerStyle_BackgroundColor() {
        return (EAttribute) shapeContainerStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSquare() {
        return squareEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSquare_Width() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSquare_Height() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSquare_Color() {
        return (EAttribute) squareEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEllipse() {
        return ellipseEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEllipse_HorizontalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEllipse_VerticalDiameter() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEllipse_Color() {
        return (EAttribute) ellipseEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLozenge() {
        return lozengeEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLozenge_Width() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLozenge_Height() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLozenge_Color() {
        return (EAttribute) lozengeEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBundledImage() {
        return bundledImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBundledImage_Shape() {
        return (EAttribute) bundledImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBundledImage_Color() {
        return (EAttribute) bundledImageEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBundledImage_ProvidedShapeID() {
        return (EAttribute) bundledImageEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWorkspaceImage() {
        return workspaceImageEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWorkspaceImage_WorkspacePath() {
        return (EAttribute) workspaceImageEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomStyle() {
        return customStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomStyle_Id() {
        return (EAttribute) customStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeTarget() {
        return edgeTargetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeTarget_OutgoingEdges() {
        return (EReference) edgeTargetEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeTarget_IncomingEdges() {
        return (EReference) edgeTargetEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeStyle() {
        return edgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_StrokeColor() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_LineStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_SourceArrow() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_TargetArrow() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_FoldingStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_Size() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_RoutingStyle() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyle_BeginLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyle_CenterLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyle_EndLabelStyle() {
        return (EReference) edgeStyleEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyle_Centered() {
        return (EAttribute) edgeStyleEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGaugeCompositeStyle() {
        return gaugeCompositeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeCompositeStyle_Alignment() {
        return (EAttribute) gaugeCompositeStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGaugeCompositeStyle_Sections() {
        return (EReference) gaugeCompositeStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBorderedStyle() {
        return borderedStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyle_BorderSize() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyle_BorderSizeComputationExpression() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyle_BorderColor() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyle_BorderLineStyle() {
        return (EAttribute) borderedStyleEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNote() {
        return noteEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNote_Color() {
        return (EAttribute) noteEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFilterVariableHistory() {
        return filterVariableHistoryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFilterVariableHistory_OwnedValues() {
        return (EReference) filterVariableHistoryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCollapseFilter() {
        return collapseFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCollapseFilter_Width() {
        return (EAttribute) collapseFilterEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCollapseFilter_Height() {
        return (EAttribute) collapseFilterEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getIndirectlyCollapseFilter() {
        return indirectlyCollapseFilterEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBeginLabelStyle() {
        return beginLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCenterLabelStyle() {
        return centerLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEndLabelStyle() {
        return endLabelStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBracketEdgeStyle() {
        return bracketEdgeStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getComputedStyleDescriptionRegistry() {
        return computedStyleDescriptionRegistryEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getComputedStyleDescriptionRegistry_ComputedStyleDescriptions() {
        return (EReference) computedStyleDescriptionRegistryEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDragAndDropTarget() {
        return dragAndDropTargetEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHideLabelCapabilityStyle() {
        return hideLabelCapabilityStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHideLabelCapabilityStyle_HideLabelByDefault() {
        return (EAttribute) hideLabelCapabilityStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getVariableValue() {
        return variableValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getTypedVariableValue() {
        return typedVariableValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getTypedVariableValue_VariableDefinition() {
        return (EReference) typedVariableValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getTypedVariableValue_Value() {
        return (EAttribute) typedVariableValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEObjectVariableValue() {
        return eObjectVariableValueEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEObjectVariableValue_VariableDefinition() {
        return (EReference) eObjectVariableValueEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEObjectVariableValue_ModelElement() {
        return (EReference) eObjectVariableValueEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getContainerLayout() {
        return containerLayoutEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLabelPosition() {
        return labelPositionEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getContainerShape() {
        return containerShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getBackgroundStyle() {
        return backgroundStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getBundledImageShape() {
        return bundledImageShapeEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getLineStyle() {
        return lineStyleEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getEdgeArrows() {
        return edgeArrowsEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getEdgeRouting() {
        return edgeRoutingEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getAlignmentKind() {
        return alignmentKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getResizeKind() {
        return resizeKindEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getArrangeConstraint() {
        return arrangeConstraintEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public DiagramFactory getDiagramFactory() {
        return (DiagramFactory) getEFactoryInstance();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isCreated = false;

    /**
     * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
     * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void createPackageContents() {
        if (isCreated) {
            return;
        }
        isCreated = true;

        // Create classes and their features
        dDiagramEClass = createEClass(DiagramPackage.DDIAGRAM);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__OWNED_DIAGRAM_ELEMENTS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__DIAGRAM_ELEMENTS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__DESCRIPTION);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__EDGES);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__NODES);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__NODE_LIST_ELEMENTS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__CONTAINERS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__CURRENT_CONCERN);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ACTIVATED_TRANSIENT_LAYERS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ALL_FILTERS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ACTIVATED_RULES);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ACTIVATE_BEHAVIORS);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__FILTER_VARIABLE_HISTORY);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS);
        createEAttribute(dDiagramEClass, DiagramPackage.DDIAGRAM__SYNCHRONIZED);
        createEReference(dDiagramEClass, DiagramPackage.DDIAGRAM__HIDDEN_ELEMENTS);
        createEAttribute(dDiagramEClass, DiagramPackage.DDIAGRAM__IS_IN_LAYOUTING_MODE);
        createEAttribute(dDiagramEClass, DiagramPackage.DDIAGRAM__IS_IN_SHOWING_MODE);
        createEAttribute(dDiagramEClass, DiagramPackage.DDIAGRAM__HEADER_HEIGHT);

        dSemanticDiagramEClass = createEClass(DiagramPackage.DSEMANTIC_DIAGRAM);

        dDiagramElementEClass = createEClass(DiagramPackage.DDIAGRAM_ELEMENT);
        createEAttribute(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__VISIBLE);
        createEAttribute(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__TOOLTIP_TEXT);
        createEReference(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__PARENT_LAYERS);
        createEReference(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__DECORATIONS);
        createEReference(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__TRANSIENT_DECORATIONS);
        createEReference(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__DIAGRAM_ELEMENT_MAPPING);
        createEReference(dDiagramElementEClass, DiagramPackage.DDIAGRAM_ELEMENT__GRAPHICAL_FILTERS);

        graphicalFilterEClass = createEClass(DiagramPackage.GRAPHICAL_FILTER);

        hideFilterEClass = createEClass(DiagramPackage.HIDE_FILTER);

        hideLabelFilterEClass = createEClass(DiagramPackage.HIDE_LABEL_FILTER);
        createEAttribute(hideLabelFilterEClass, DiagramPackage.HIDE_LABEL_FILTER__HIDDEN_LABELS);

        foldingPointFilterEClass = createEClass(DiagramPackage.FOLDING_POINT_FILTER);

        foldingFilterEClass = createEClass(DiagramPackage.FOLDING_FILTER);

        appliedCompositeFiltersEClass = createEClass(DiagramPackage.APPLIED_COMPOSITE_FILTERS);
        createEReference(appliedCompositeFiltersEClass, DiagramPackage.APPLIED_COMPOSITE_FILTERS__COMPOSITE_FILTER_DESCRIPTIONS);

        absoluteBoundsFilterEClass = createEClass(DiagramPackage.ABSOLUTE_BOUNDS_FILTER);
        createEAttribute(absoluteBoundsFilterEClass, DiagramPackage.ABSOLUTE_BOUNDS_FILTER__X);
        createEAttribute(absoluteBoundsFilterEClass, DiagramPackage.ABSOLUTE_BOUNDS_FILTER__Y);
        createEAttribute(absoluteBoundsFilterEClass, DiagramPackage.ABSOLUTE_BOUNDS_FILTER__HEIGHT);
        createEAttribute(absoluteBoundsFilterEClass, DiagramPackage.ABSOLUTE_BOUNDS_FILTER__WIDTH);

        abstractDNodeEClass = createEClass(DiagramPackage.ABSTRACT_DNODE);
        createEReference(abstractDNodeEClass, DiagramPackage.ABSTRACT_DNODE__OWNED_BORDERED_NODES);
        createEAttribute(abstractDNodeEClass, DiagramPackage.ABSTRACT_DNODE__ARRANGE_CONSTRAINTS);

        dNodeEClass = createEClass(DiagramPackage.DNODE);
        createEAttribute(dNodeEClass, DiagramPackage.DNODE__WIDTH);
        createEAttribute(dNodeEClass, DiagramPackage.DNODE__HEIGHT);
        createEReference(dNodeEClass, DiagramPackage.DNODE__OWNED_STYLE);
        createEAttribute(dNodeEClass, DiagramPackage.DNODE__LABEL_POSITION);
        createEAttribute(dNodeEClass, DiagramPackage.DNODE__RESIZE_KIND);
        createEReference(dNodeEClass, DiagramPackage.DNODE__ORIGINAL_STYLE);
        createEReference(dNodeEClass, DiagramPackage.DNODE__ACTUAL_MAPPING);
        createEReference(dNodeEClass, DiagramPackage.DNODE__CANDIDATES_MAPPING);

        dDiagramElementContainerEClass = createEClass(DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__NODES);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CONTAINERS);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ELEMENTS);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__OWNED_STYLE);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ORIGINAL_STYLE);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__ACTUAL_MAPPING);
        createEReference(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__CANDIDATES_MAPPING);
        createEAttribute(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__WIDTH);
        createEAttribute(dDiagramElementContainerEClass, DiagramPackage.DDIAGRAM_ELEMENT_CONTAINER__HEIGHT);

        dNodeContainerEClass = createEClass(DiagramPackage.DNODE_CONTAINER);
        createEReference(dNodeContainerEClass, DiagramPackage.DNODE_CONTAINER__OWNED_DIAGRAM_ELEMENTS);
        createEAttribute(dNodeContainerEClass, DiagramPackage.DNODE_CONTAINER__CHILDREN_PRESENTATION);

        dNodeListEClass = createEClass(DiagramPackage.DNODE_LIST);
        createEReference(dNodeListEClass, DiagramPackage.DNODE_LIST__OWNED_ELEMENTS);

        dNodeListElementEClass = createEClass(DiagramPackage.DNODE_LIST_ELEMENT);
        createEReference(dNodeListElementEClass, DiagramPackage.DNODE_LIST_ELEMENT__OWNED_STYLE);
        createEReference(dNodeListElementEClass, DiagramPackage.DNODE_LIST_ELEMENT__ORIGINAL_STYLE);
        createEReference(dNodeListElementEClass, DiagramPackage.DNODE_LIST_ELEMENT__ACTUAL_MAPPING);
        createEReference(dNodeListElementEClass, DiagramPackage.DNODE_LIST_ELEMENT__CANDIDATES_MAPPING);

        dEdgeEClass = createEClass(DiagramPackage.DEDGE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__OWNED_STYLE);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__SIZE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__SOURCE_NODE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__TARGET_NODE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__ACTUAL_MAPPING);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__ROUTING_STYLE);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__IS_FOLD);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__IS_MOCK_EDGE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__ORIGINAL_STYLE);
        createEReference(dEdgeEClass, DiagramPackage.DEDGE__PATH);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__ARRANGE_CONSTRAINTS);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__BEGIN_LABEL);
        createEAttribute(dEdgeEClass, DiagramPackage.DEDGE__END_LABEL);

        nodeStyleEClass = createEClass(DiagramPackage.NODE_STYLE);
        createEAttribute(nodeStyleEClass, DiagramPackage.NODE_STYLE__LABEL_POSITION);

        dotEClass = createEClass(DiagramPackage.DOT);
        createEAttribute(dotEClass, DiagramPackage.DOT__STROKE_SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(dotEClass, DiagramPackage.DOT__BACKGROUND_COLOR);

        gaugeSectionEClass = createEClass(DiagramPackage.GAUGE_SECTION);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__MIN);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__MAX);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__VALUE);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__LABEL);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__BACKGROUND_COLOR);
        createEAttribute(gaugeSectionEClass, DiagramPackage.GAUGE_SECTION__FOREGROUND_COLOR);

        containerStyleEClass = createEClass(DiagramPackage.CONTAINER_STYLE);

        flatContainerStyleEClass = createEClass(DiagramPackage.FLAT_CONTAINER_STYLE);
        createEAttribute(flatContainerStyleEClass, DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_STYLE);
        createEAttribute(flatContainerStyleEClass, DiagramPackage.FLAT_CONTAINER_STYLE__BACKGROUND_COLOR);
        createEAttribute(flatContainerStyleEClass, DiagramPackage.FLAT_CONTAINER_STYLE__FOREGROUND_COLOR);

        shapeContainerStyleEClass = createEClass(DiagramPackage.SHAPE_CONTAINER_STYLE);
        createEAttribute(shapeContainerStyleEClass, DiagramPackage.SHAPE_CONTAINER_STYLE__SHAPE);
        createEAttribute(shapeContainerStyleEClass, DiagramPackage.SHAPE_CONTAINER_STYLE__BACKGROUND_COLOR);

        squareEClass = createEClass(DiagramPackage.SQUARE);
        createEAttribute(squareEClass, DiagramPackage.SQUARE__WIDTH);
        createEAttribute(squareEClass, DiagramPackage.SQUARE__HEIGHT);
        createEAttribute(squareEClass, DiagramPackage.SQUARE__COLOR);

        ellipseEClass = createEClass(DiagramPackage.ELLIPSE);
        createEAttribute(ellipseEClass, DiagramPackage.ELLIPSE__HORIZONTAL_DIAMETER);
        createEAttribute(ellipseEClass, DiagramPackage.ELLIPSE__VERTICAL_DIAMETER);
        createEAttribute(ellipseEClass, DiagramPackage.ELLIPSE__COLOR);

        lozengeEClass = createEClass(DiagramPackage.LOZENGE);
        createEAttribute(lozengeEClass, DiagramPackage.LOZENGE__WIDTH);
        createEAttribute(lozengeEClass, DiagramPackage.LOZENGE__HEIGHT);
        createEAttribute(lozengeEClass, DiagramPackage.LOZENGE__COLOR);

        bundledImageEClass = createEClass(DiagramPackage.BUNDLED_IMAGE);
        createEAttribute(bundledImageEClass, DiagramPackage.BUNDLED_IMAGE__SHAPE);
        createEAttribute(bundledImageEClass, DiagramPackage.BUNDLED_IMAGE__COLOR);
        createEAttribute(bundledImageEClass, DiagramPackage.BUNDLED_IMAGE__PROVIDED_SHAPE_ID);

        workspaceImageEClass = createEClass(DiagramPackage.WORKSPACE_IMAGE);
        createEAttribute(workspaceImageEClass, DiagramPackage.WORKSPACE_IMAGE__WORKSPACE_PATH);

        customStyleEClass = createEClass(DiagramPackage.CUSTOM_STYLE);
        createEAttribute(customStyleEClass, DiagramPackage.CUSTOM_STYLE__ID);

        edgeTargetEClass = createEClass(DiagramPackage.EDGE_TARGET);
        createEReference(edgeTargetEClass, DiagramPackage.EDGE_TARGET__OUTGOING_EDGES);
        createEReference(edgeTargetEClass, DiagramPackage.EDGE_TARGET__INCOMING_EDGES);

        edgeStyleEClass = createEClass(DiagramPackage.EDGE_STYLE);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__LINE_STYLE);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__SOURCE_ARROW);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__TARGET_ARROW);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__FOLDING_STYLE);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__SIZE);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__ROUTING_STYLE);
        createEReference(edgeStyleEClass, DiagramPackage.EDGE_STYLE__BEGIN_LABEL_STYLE);
        createEReference(edgeStyleEClass, DiagramPackage.EDGE_STYLE__CENTER_LABEL_STYLE);
        createEReference(edgeStyleEClass, DiagramPackage.EDGE_STYLE__END_LABEL_STYLE);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__CENTERED);
        createEAttribute(edgeStyleEClass, DiagramPackage.EDGE_STYLE__STROKE_COLOR);

        gaugeCompositeStyleEClass = createEClass(DiagramPackage.GAUGE_COMPOSITE_STYLE);
        createEAttribute(gaugeCompositeStyleEClass, DiagramPackage.GAUGE_COMPOSITE_STYLE__ALIGNMENT);
        createEReference(gaugeCompositeStyleEClass, DiagramPackage.GAUGE_COMPOSITE_STYLE__SECTIONS);

        borderedStyleEClass = createEClass(DiagramPackage.BORDERED_STYLE);
        createEAttribute(borderedStyleEClass, DiagramPackage.BORDERED_STYLE__BORDER_SIZE);
        createEAttribute(borderedStyleEClass, DiagramPackage.BORDERED_STYLE__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(borderedStyleEClass, DiagramPackage.BORDERED_STYLE__BORDER_COLOR);
        createEAttribute(borderedStyleEClass, DiagramPackage.BORDERED_STYLE__BORDER_LINE_STYLE);

        noteEClass = createEClass(DiagramPackage.NOTE);
        createEAttribute(noteEClass, DiagramPackage.NOTE__COLOR);

        filterVariableHistoryEClass = createEClass(DiagramPackage.FILTER_VARIABLE_HISTORY);
        createEReference(filterVariableHistoryEClass, DiagramPackage.FILTER_VARIABLE_HISTORY__OWNED_VALUES);

        collapseFilterEClass = createEClass(DiagramPackage.COLLAPSE_FILTER);
        createEAttribute(collapseFilterEClass, DiagramPackage.COLLAPSE_FILTER__WIDTH);
        createEAttribute(collapseFilterEClass, DiagramPackage.COLLAPSE_FILTER__HEIGHT);

        indirectlyCollapseFilterEClass = createEClass(DiagramPackage.INDIRECTLY_COLLAPSE_FILTER);

        beginLabelStyleEClass = createEClass(DiagramPackage.BEGIN_LABEL_STYLE);

        centerLabelStyleEClass = createEClass(DiagramPackage.CENTER_LABEL_STYLE);

        endLabelStyleEClass = createEClass(DiagramPackage.END_LABEL_STYLE);

        bracketEdgeStyleEClass = createEClass(DiagramPackage.BRACKET_EDGE_STYLE);

        computedStyleDescriptionRegistryEClass = createEClass(DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY);
        createEReference(computedStyleDescriptionRegistryEClass, DiagramPackage.COMPUTED_STYLE_DESCRIPTION_REGISTRY__COMPUTED_STYLE_DESCRIPTIONS);

        dragAndDropTargetEClass = createEClass(DiagramPackage.DRAG_AND_DROP_TARGET);

        hideLabelCapabilityStyleEClass = createEClass(DiagramPackage.HIDE_LABEL_CAPABILITY_STYLE);
        createEAttribute(hideLabelCapabilityStyleEClass, DiagramPackage.HIDE_LABEL_CAPABILITY_STYLE__HIDE_LABEL_BY_DEFAULT);

        variableValueEClass = createEClass(DiagramPackage.VARIABLE_VALUE);

        typedVariableValueEClass = createEClass(DiagramPackage.TYPED_VARIABLE_VALUE);
        createEReference(typedVariableValueEClass, DiagramPackage.TYPED_VARIABLE_VALUE__VARIABLE_DEFINITION);
        createEAttribute(typedVariableValueEClass, DiagramPackage.TYPED_VARIABLE_VALUE__VALUE);

        eObjectVariableValueEClass = createEClass(DiagramPackage.EOBJECT_VARIABLE_VALUE);
        createEReference(eObjectVariableValueEClass, DiagramPackage.EOBJECT_VARIABLE_VALUE__VARIABLE_DEFINITION);
        createEReference(eObjectVariableValueEClass, DiagramPackage.EOBJECT_VARIABLE_VALUE__MODEL_ELEMENT);

        // Create enums
        containerLayoutEEnum = createEEnum(DiagramPackage.CONTAINER_LAYOUT);
        labelPositionEEnum = createEEnum(DiagramPackage.LABEL_POSITION);
        containerShapeEEnum = createEEnum(DiagramPackage.CONTAINER_SHAPE);
        backgroundStyleEEnum = createEEnum(DiagramPackage.BACKGROUND_STYLE);
        bundledImageShapeEEnum = createEEnum(DiagramPackage.BUNDLED_IMAGE_SHAPE);
        lineStyleEEnum = createEEnum(DiagramPackage.LINE_STYLE);
        edgeArrowsEEnum = createEEnum(DiagramPackage.EDGE_ARROWS);
        edgeRoutingEEnum = createEEnum(DiagramPackage.EDGE_ROUTING);
        alignmentKindEEnum = createEEnum(DiagramPackage.ALIGNMENT_KIND);
        resizeKindEEnum = createEEnum(DiagramPackage.RESIZE_KIND);
        arrangeConstraintEEnum = createEEnum(DiagramPackage.ARRANGE_CONSTRAINT);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
     * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized) {
            return;
        }
        isInitialized = true;

        // Initialize package
        setName(DiagramPackage.eNAME);
        setNsPrefix(DiagramPackage.eNS_PREFIX);
        setNsURI(DiagramPackage.eNS_URI);

        // Obtain other dependent packages
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);
        ConcernPackage theConcernPackage = (ConcernPackage) EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        FilterPackage theFilterPackage = (FilterPackage) EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        ValidationPackage theValidationPackage = (ValidationPackage) EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        ToolPackage theToolPackage = (ToolPackage) EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.style.StylePackage theStylePackage_1 = (org.eclipse.sirius.viewpoint.description.style.StylePackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.style.StylePackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.tool.ToolPackage theToolPackage_1 = (org.eclipse.sirius.viewpoint.description.tool.ToolPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.tool.ToolPackage.eNS_URI);

        // Add subpackages
        getESubpackages().add(theDescriptionPackage);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        dDiagramEClass.getESuperTypes().add(theViewpointPackage.getDRepresentation());
        dDiagramEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dSemanticDiagramEClass.getESuperTypes().add(this.getDDiagram());
        dSemanticDiagramEClass.getESuperTypes().add(theViewpointPackage.getDSemanticDecorator());
        dDiagramElementEClass.getESuperTypes().add(theViewpointPackage.getDRepresentationElement());
        graphicalFilterEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        hideFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        hideLabelFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        foldingPointFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        foldingFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        appliedCompositeFiltersEClass.getESuperTypes().add(this.getGraphicalFilter());
        absoluteBoundsFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        abstractDNodeEClass.getESuperTypes().add(this.getDDiagramElement());
        dNodeEClass.getESuperTypes().add(this.getAbstractDNode());
        dNodeEClass.getESuperTypes().add(this.getEdgeTarget());
        dNodeEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getAbstractDNode());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getEdgeTarget());
        dDiagramElementContainerEClass.getESuperTypes().add(this.getDragAndDropTarget());
        dNodeContainerEClass.getESuperTypes().add(this.getDDiagramElementContainer());
        dNodeListEClass.getESuperTypes().add(this.getDDiagramElementContainer());
        dNodeListElementEClass.getESuperTypes().add(this.getAbstractDNode());
        dEdgeEClass.getESuperTypes().add(this.getDDiagramElement());
        dEdgeEClass.getESuperTypes().add(this.getEdgeTarget());
        nodeStyleEClass.getESuperTypes().add(theViewpointPackage.getLabelStyle());
        nodeStyleEClass.getESuperTypes().add(theViewpointPackage.getStyle());
        nodeStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        nodeStyleEClass.getESuperTypes().add(this.getHideLabelCapabilityStyle());
        dotEClass.getESuperTypes().add(this.getNodeStyle());
        gaugeSectionEClass.getESuperTypes().add(theViewpointPackage.getCustomizable());
        containerStyleEClass.getESuperTypes().add(theViewpointPackage.getLabelStyle());
        containerStyleEClass.getESuperTypes().add(theViewpointPackage.getStyle());
        containerStyleEClass.getESuperTypes().add(this.getBorderedStyle());
        containerStyleEClass.getESuperTypes().add(this.getHideLabelCapabilityStyle());
        flatContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        shapeContainerStyleEClass.getESuperTypes().add(this.getContainerStyle());
        squareEClass.getESuperTypes().add(this.getNodeStyle());
        ellipseEClass.getESuperTypes().add(this.getNodeStyle());
        lozengeEClass.getESuperTypes().add(this.getNodeStyle());
        bundledImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getNodeStyle());
        workspaceImageEClass.getESuperTypes().add(this.getContainerStyle());
        customStyleEClass.getESuperTypes().add(this.getNodeStyle());
        edgeTargetEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        edgeStyleEClass.getESuperTypes().add(theViewpointPackage.getStyle());
        gaugeCompositeStyleEClass.getESuperTypes().add(this.getNodeStyle());
        borderedStyleEClass.getESuperTypes().add(theViewpointPackage.getStyle());
        noteEClass.getESuperTypes().add(this.getNodeStyle());
        filterVariableHistoryEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        collapseFilterEClass.getESuperTypes().add(this.getGraphicalFilter());
        indirectlyCollapseFilterEClass.getESuperTypes().add(this.getCollapseFilter());
        beginLabelStyleEClass.getESuperTypes().add(theViewpointPackage.getBasicLabelStyle());
        centerLabelStyleEClass.getESuperTypes().add(theViewpointPackage.getBasicLabelStyle());
        endLabelStyleEClass.getESuperTypes().add(theViewpointPackage.getBasicLabelStyle());
        bracketEdgeStyleEClass.getESuperTypes().add(this.getEdgeStyle());
        computedStyleDescriptionRegistryEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        dragAndDropTargetEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        variableValueEClass.getESuperTypes().add(theViewpointPackage.getIdentifiedElement());
        typedVariableValueEClass.getESuperTypes().add(this.getVariableValue());
        eObjectVariableValueEClass.getESuperTypes().add(this.getVariableValue());

        // Initialize classes and features; add operations and parameters
        initEClass(dDiagramEClass, DDiagram.class, "DDiagram", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDDiagram_OwnedDiagramElements(), this.getDDiagramElement(), null, "ownedDiagramElements", null, 0, -1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_DiagramElements(), this.getDDiagramElement(), null, "diagramElements", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_Description(), theDescriptionPackage.getDiagramDescription(), null, "description", null, 0, 1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_Edges(), this.getDEdge(), null, "edges", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_Nodes(), this.getDNode(), null, "nodes", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_NodeListElements(), this.getDNodeListElement(), null, "nodeListElements", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_Containers(), this.getDDiagramElementContainer(), null, "containers", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_CurrentConcern(), theConcernPackage.getConcernDescription(), null, "currentConcern", null, 0, 1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_ActivatedFilters(), theFilterPackage.getFilterDescription(), null, "activatedFilters", null, 0, -1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_ActivatedTransientLayers(), theDescriptionPackage.getAdditionalLayer(), null, "activatedTransientLayers", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_AllFilters(), theFilterPackage.getFilterDescription(), null, "allFilters", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_ActivatedRules(), theValidationPackage.getValidationRule(), null, "activatedRules", null, 0, -1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        getDDiagram_ActivatedRules().getEKeys().add(theDescriptionPackage_1.getIdentifiedElement_Name());
        initEReference(getDDiagram_ActivateBehaviors(), theToolPackage.getBehaviorTool(), null, "activateBehaviors", null, 0, -1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_FilterVariableHistory(), this.getFilterVariableHistory(), null, "filterVariableHistory", null, 1, 1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_ActivatedLayers(), theDescriptionPackage.getLayer(), null, "activatedLayers", null, 0, -1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagram_Synchronized(), theEcorePackage.getEBoolean(), "synchronized", "true", 0, 1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagram_HiddenElements(), this.getDDiagramElement(), null, "hiddenElements", null, 0, -1, DDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagram_IsInLayoutingMode(), theEcorePackage.getEBoolean(), "isInLayoutingMode", null, 0, 1, DDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagram_IsInShowingMode(), theEcorePackage.getEBoolean(), "isInShowingMode", null, 0, 1, DDiagram.class, EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagram_HeaderHeight(), theEcorePackage.getEInt(), "headerHeight", "1", 0, 1, DDiagram.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dSemanticDiagramEClass, DSemanticDiagram.class, "DSemanticDiagram", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(dDiagramElementEClass, DDiagramElement.class, "DDiagramElement", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDDiagramElement_Visible(), theEcorePackage.getEBoolean(), "visible", "true", 0, 1, DDiagramElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagramElement_TooltipText(), theEcorePackage.getEString(), "tooltipText", null, 0, 1, DDiagramElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElement_ParentLayers(), theDescriptionPackage.getLayer(), null, "parentLayers", null, 0, -1, DDiagramElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElement_Decorations(), theViewpointPackage.getDecoration(), null, "decorations", null, 0, -1, DDiagramElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElement_TransientDecorations(), theViewpointPackage.getDecoration(), null, "transientDecorations", null, 0, -1, DDiagramElement.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElement_DiagramElementMapping(), theDescriptionPackage.getDiagramElementMapping(), null, "diagramElementMapping", null, 0, 1, DDiagramElement.class, //$NON-NLS-1$
                EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElement_GraphicalFilters(), this.getGraphicalFilter(), null, "graphicalFilters", null, 0, -1, DDiagramElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        addEOperation(dDiagramElementEClass, this.getDDiagram(), "getParentDiagram", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(graphicalFilterEClass, GraphicalFilter.class, "GraphicalFilter", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(hideFilterEClass, HideFilter.class, "HideFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(hideLabelFilterEClass, HideLabelFilter.class, "HideLabelFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getHideLabelFilter_HiddenLabels(), ecorePackage.getEInt(), "hiddenLabels", null, 0, -1, HideLabelFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(foldingPointFilterEClass, FoldingPointFilter.class, "FoldingPointFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(foldingFilterEClass, FoldingFilter.class, "FoldingFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(appliedCompositeFiltersEClass, AppliedCompositeFilters.class, "AppliedCompositeFilters", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getAppliedCompositeFilters_CompositeFilterDescriptions(), theFilterPackage.getCompositeFilterDescription(), null, "compositeFilterDescriptions", null, 0, -1, //$NON-NLS-1$
                AppliedCompositeFilters.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(absoluteBoundsFilterEClass, AbsoluteBoundsFilter.class, "AbsoluteBoundsFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getAbsoluteBoundsFilter_X(), theEcorePackage.getEIntegerObject(), "x", null, 0, 1, AbsoluteBoundsFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Y(), theEcorePackage.getEIntegerObject(), "y", null, 0, 1, AbsoluteBoundsFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, AbsoluteBoundsFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbsoluteBoundsFilter_Width(), theEcorePackage.getEIntegerObject(), "width", null, 0, 1, AbsoluteBoundsFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(abstractDNodeEClass, AbstractDNode.class, "AbstractDNode", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getAbstractDNode_OwnedBorderedNodes(), this.getDNode(), null, "ownedBorderedNodes", null, 0, -1, AbstractDNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractDNode_ArrangeConstraints(), this.getArrangeConstraint(), "arrangeConstraints", "KEEP_LOCATION", 0, -1, AbstractDNode.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dNodeEClass, DNode.class, "DNode", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDNode_Width(), theEcorePackage.getEIntegerObject(), "width", null, 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDNode_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDNode_OwnedStyle(), this.getNodeStyle(), null, "ownedStyle", null, 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDNode_LabelPosition(), this.getLabelPosition(), "labelPosition", null, 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDNode_ResizeKind(), this.getResizeKind(), "resizeKind", "NONE", 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDNode_OriginalStyle(), theViewpointPackage.getStyle(), null, "originalStyle", null, 0, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDNode_ActualMapping(), theDescriptionPackage.getNodeMapping(), null, "actualMapping", null, 1, 1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDNode_CandidatesMapping(), theDescriptionPackage.getNodeMapping(), null, "candidatesMapping", null, 0, -1, DNode.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dDiagramElementContainerEClass, DDiagramElementContainer.class, "DDiagramElementContainer", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDDiagramElementContainer_Nodes(), this.getDNode(), null, "nodes", null, 0, -1, DDiagramElementContainer.class, EPackageImpl.IS_TRANSIENT, EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_Containers(), this.getDDiagramElementContainer(), null, "containers", null, 0, -1, DDiagramElementContainer.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_Elements(), this.getDDiagramElement(), null, "elements", null, 0, -1, DDiagramElementContainer.class, EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                EPackageImpl.IS_VOLATILE, !EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_OwnedStyle(), this.getContainerStyle(), null, "ownedStyle", null, 0, 1, DDiagramElementContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_OriginalStyle(), theViewpointPackage.getStyle(), null, "originalStyle", null, 0, 1, DDiagramElementContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_ActualMapping(), theDescriptionPackage.getContainerMapping(), null, "actualMapping", null, 1, 1, DDiagramElementContainer.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDDiagramElementContainer_CandidatesMapping(), theDescriptionPackage.getContainerMapping(), null, "candidatesMapping", null, 0, -1, DDiagramElementContainer.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagramElementContainer_Width(), theEcorePackage.getEIntegerObject(), "width", null, 0, 1, DDiagramElementContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDDiagramElementContainer_Height(), theEcorePackage.getEIntegerObject(), "height", null, 0, 1, DDiagramElementContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        EOperation op = addEOperation(dDiagramElementContainerEClass, this.getDNode(), "getNodesFromMapping", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theDescriptionPackage.getNodeMapping(), "mapping", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(dDiagramElementContainerEClass, this.getDDiagramElementContainer(), "getContainersFromMapping", 0, -1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theDescriptionPackage.getContainerMapping(), "mapping", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(dNodeContainerEClass, DNodeContainer.class, "DNodeContainer", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDNodeContainer_OwnedDiagramElements(), this.getDDiagramElement(), null, "ownedDiagramElements", null, 0, -1, DNodeContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDNodeContainer_ChildrenPresentation(), this.getContainerLayout(), "childrenPresentation", "FreeForm", 1, 1, DNodeContainer.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dNodeListEClass, DNodeList.class, "DNodeList", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDNodeList_OwnedElements(), this.getDNodeListElement(), null, "ownedElements", null, 0, -1, DNodeList.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(dNodeListElementEClass, DNodeListElement.class, "DNodeListElement", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDNodeListElement_OwnedStyle(), this.getNodeStyle(), null, "ownedStyle", null, 0, 1, DNodeListElement.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDNodeListElement_OriginalStyle(), theViewpointPackage.getStyle(), null, "originalStyle", null, 0, 1, DNodeListElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDNodeListElement_ActualMapping(), theDescriptionPackage.getNodeMapping(), null, "actualMapping", null, 1, 1, DNodeListElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDNodeListElement_CandidatesMapping(), theDescriptionPackage.getNodeMapping(), null, "candidatesMapping", null, 0, -1, DNodeListElement.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dEdgeEClass, DEdge.class, "DEdge", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDEdge_OwnedStyle(), this.getEdgeStyle(), null, "ownedStyle", null, 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_Size(), theEcorePackage.getEIntegerObject(), "size", "1", 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDEdge_SourceNode(), this.getEdgeTarget(), this.getEdgeTarget_OutgoingEdges(), "sourceNode", null, 1, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDEdge_TargetNode(), this.getEdgeTarget(), this.getEdgeTarget_IncomingEdges(), "targetNode", null, 1, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDEdge_ActualMapping(), theDescriptionPackage.getIEdgeMapping(), null, "actualMapping", null, 1, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_RoutingStyle(), this.getEdgeRouting(), "routingStyle", "straight", 1, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_IsFold(), theEcorePackage.getEBoolean(), "isFold", null, 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_IsMockEdge(), theEcorePackage.getEBoolean(), "isMockEdge", null, 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getDEdge_OriginalStyle(), theViewpointPackage.getStyle(), null, "originalStyle", null, 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getDEdge_Path(), this.getEdgeTarget(), null, "path", null, 0, -1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_ArrangeConstraints(), this.getArrangeConstraint(), "arrangeConstraints", "KEEP_LOCATION", 0, -1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_BeginLabel(), theEcorePackage.getEString(), "beginLabel", "", 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDEdge_EndLabel(), theEcorePackage.getEString(), "endLabel", "", 0, 1, DEdge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(nodeStyleEClass, NodeStyle.class, "NodeStyle", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNodeStyle_LabelPosition(), this.getLabelPosition(), "labelPosition", null, 0, 1, NodeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dotEClass, Dot.class, "Dot", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getDot_StrokeSizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "strokeSizeComputationExpression", "2", 0, 1, Dot.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getDot_BackgroundColor(), theViewpointPackage.getRGBValues(), "backgroundColor", "136,136,136", 0, 1, Dot.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(gaugeSectionEClass, GaugeSection.class, "GaugeSection", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGaugeSection_Min(), theEcorePackage.getEIntegerObject(), "min", null, 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSection_Max(), theEcorePackage.getEIntegerObject(), "max", null, 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSection_Value(), theEcorePackage.getEIntegerObject(), "value", null, 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSection_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSection_BackgroundColor(), theViewpointPackage.getRGBValues(), "backgroundColor", "0,0,0", 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSection_ForegroundColor(), theViewpointPackage.getRGBValues(), "foregroundColor", "138,226,52", 0, 1, GaugeSection.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerStyleEClass, ContainerStyle.class, "ContainerStyle", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(flatContainerStyleEClass, FlatContainerStyle.class, "FlatContainerStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getFlatContainerStyle_BackgroundStyle(), this.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getFlatContainerStyle_BackgroundColor(), theViewpointPackage.getRGBValues(), "backgroundColor", "255,255,255", 0, 1, FlatContainerStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getFlatContainerStyle_ForegroundColor(), theViewpointPackage.getRGBValues(), "foregroundColor", "209,209,209", 0, 1, FlatContainerStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(shapeContainerStyleEClass, ShapeContainerStyle.class, "ShapeContainerStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getShapeContainerStyle_Shape(), this.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getShapeContainerStyle_BackgroundColor(), theViewpointPackage.getRGBValues(), "backgroundColor", "209,209,209", 0, 1, ShapeContainerStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(squareEClass, Square.class, "Square", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSquare_Width(), theEcorePackage.getEIntegerObject(), "width", "0", 0, 1, Square.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSquare_Height(), theEcorePackage.getEIntegerObject(), "height", "0", 0, 1, Square.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSquare_Color(), theViewpointPackage.getRGBValues(), "color", "136,136,136", 0, 1, Square.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(ellipseEClass, Ellipse.class, "Ellipse", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getEllipse_HorizontalDiameter(), theEcorePackage.getEIntegerObject(), "horizontalDiameter", "0", 0, 1, Ellipse.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEllipse_VerticalDiameter(), theEcorePackage.getEIntegerObject(), "verticalDiameter", "0", 0, 1, Ellipse.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEllipse_Color(), theViewpointPackage.getRGBValues(), "color", "136,136,136", 0, 1, Ellipse.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(lozengeEClass, Lozenge.class, "Lozenge", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getLozenge_Width(), theEcorePackage.getEIntegerObject(), "width", "0", 0, 1, Lozenge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLozenge_Height(), theEcorePackage.getEIntegerObject(), "height", "0", 0, 1, Lozenge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLozenge_Color(), theViewpointPackage.getRGBValues(), "color", "136,136,136", 0, 1, Lozenge.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(bundledImageEClass, BundledImage.class, "BundledImage", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBundledImage_Shape(), this.getBundledImageShape(), "shape", null, 1, 1, BundledImage.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBundledImage_Color(), theViewpointPackage.getRGBValues(), "color", "0,0,0", 0, 1, BundledImage.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBundledImage_ProvidedShapeID(), ecorePackage.getEString(), "providedShapeID", null, 0, 1, BundledImage.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(workspaceImageEClass, WorkspaceImage.class, "WorkspaceImage", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getWorkspaceImage_WorkspacePath(), theEcorePackage.getEString(), "workspacePath", null, 1, 1, WorkspaceImage.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customStyleEClass, CustomStyle.class, "CustomStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCustomStyle_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CustomStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(edgeTargetEClass, EdgeTarget.class, "EdgeTarget", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEdgeTarget_OutgoingEdges(), this.getDEdge(), this.getDEdge_SourceNode(), "outgoingEdges", null, 0, -1, EdgeTarget.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeTarget_IncomingEdges(), this.getDEdge(), this.getDEdge_TargetNode(), "incomingEdges", null, 0, -1, EdgeTarget.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(edgeStyleEClass, EdgeStyle.class, "EdgeStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getEdgeStyle_LineStyle(), this.getLineStyle(), "lineStyle", null, 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_SourceArrow(), this.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_TargetArrow(), this.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_FoldingStyle(), theDescriptionPackage.getFoldingStyle(), "foldingStyle", "NONE", 1, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_Size(), theEcorePackage.getEIntegerObject(), "size", "1", 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_RoutingStyle(), this.getEdgeRouting(), "routingStyle", "straight", 1, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyle_BeginLabelStyle(), this.getBeginLabelStyle(), null, "beginLabelStyle", null, 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyle_CenterLabelStyle(), this.getCenterLabelStyle(), null, "centerLabelStyle", null, 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyle_EndLabelStyle(), this.getEndLabelStyle(), null, "endLabelStyle", null, 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_Centered(), theDescriptionPackage.getCenteringStyle(), "centered", "None", 1, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyle_StrokeColor(), theViewpointPackage.getRGBValues(), "strokeColor", "136,136,136", 0, 1, EdgeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(gaugeCompositeStyleEClass, GaugeCompositeStyle.class, "GaugeCompositeStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getGaugeCompositeStyle_Alignment(), this.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGaugeCompositeStyle_Sections(), this.getGaugeSection(), null, "sections", null, 0, -1, GaugeCompositeStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(borderedStyleEClass, BorderedStyle.class, "BorderedStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getBorderedStyle_BorderSize(), theEcorePackage.getEIntegerObject(), "borderSize", "0", 1, 1, BorderedStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBorderedStyle_BorderSizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "borderSizeComputationExpression", "0", 0, 1, BorderedStyle.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getBorderedStyle_BorderColor(), theViewpointPackage.getRGBValues(), "borderColor", "0,0,0", 0, 1, BorderedStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBorderedStyle_BorderLineStyle(), this.getLineStyle(), "borderLineStyle", null, 0, 1, BorderedStyle.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(noteEClass, Note.class, "Note", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNote_Color(), theViewpointPackage.getRGBValues(), "color", "252,233,79", 0, 1, Note.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(filterVariableHistoryEClass, FilterVariableHistory.class, "FilterVariableHistory", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getFilterVariableHistory_OwnedValues(), this.getVariableValue(), null, "ownedValues", null, 0, -1, FilterVariableHistory.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(collapseFilterEClass, CollapseFilter.class, "CollapseFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getCollapseFilter_Width(), theEcorePackage.getEInt(), "width", null, 0, 1, CollapseFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getCollapseFilter_Height(), theEcorePackage.getEInt(), "height", null, 0, 1, CollapseFilter.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(indirectlyCollapseFilterEClass, IndirectlyCollapseFilter.class, "IndirectlyCollapseFilter", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(beginLabelStyleEClass, BeginLabelStyle.class, "BeginLabelStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(beginLabelStyleEClass, theStylePackage_1.getBasicLabelStyleDescription(), "getDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(beginLabelStyleEClass, null, "setDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theStylePackage_1.getBasicLabelStyleDescription(), "description", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(centerLabelStyleEClass, CenterLabelStyle.class, "CenterLabelStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(centerLabelStyleEClass, theStylePackage_1.getBasicLabelStyleDescription(), "getDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(centerLabelStyleEClass, null, "setDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theStylePackage_1.getBasicLabelStyleDescription(), "description", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(endLabelStyleEClass, EndLabelStyle.class, "EndLabelStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(endLabelStyleEClass, theStylePackage_1.getBasicLabelStyleDescription(), "getDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        op = addEOperation(endLabelStyleEClass, null, "setDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$
        addEParameter(op, theStylePackage_1.getBasicLabelStyleDescription(), "description", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(bracketEdgeStyleEClass, BracketEdgeStyle.class, "BracketEdgeStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(computedStyleDescriptionRegistryEClass, ComputedStyleDescriptionRegistry.class, "ComputedStyleDescriptionRegistry", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getComputedStyleDescriptionRegistry_ComputedStyleDescriptions(), theStylePackage_1.getStyleDescription(), null, "computedStyleDescriptions", null, 0, -1, //$NON-NLS-1$
                ComputedStyleDescriptionRegistry.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dragAndDropTargetEClass, DragAndDropTarget.class, "DragAndDropTarget", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        addEOperation(dragAndDropTargetEClass, theDescriptionPackage.getDragAndDropTargetDescription(), "getDragAndDropDescription", 0, 1, EPackageImpl.IS_UNIQUE, EPackageImpl.IS_ORDERED); //$NON-NLS-1$

        initEClass(hideLabelCapabilityStyleEClass, HideLabelCapabilityStyle.class, "HideLabelCapabilityStyle", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getHideLabelCapabilityStyle_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, HideLabelCapabilityStyle.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(variableValueEClass, VariableValue.class, "VariableValue", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

        initEClass(typedVariableValueEClass, TypedVariableValue.class, "TypedVariableValue", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getTypedVariableValue_VariableDefinition(), theDescriptionPackage_1.getTypedVariable(), null, "variableDefinition", null, 1, 1, TypedVariableValue.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getTypedVariableValue_Value(), theEcorePackage.getEString(), "value", null, 0, 1, TypedVariableValue.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(eObjectVariableValueEClass, EObjectVariableValue.class, "EObjectVariableValue", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEObjectVariableValue_VariableDefinition(), theToolPackage_1.getSelectModelElementVariable(), null, "variableDefinition", null, 1, 1, EObjectVariableValue.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEObjectVariableValue_ModelElement(), theEcorePackage.getEObject(), null, "modelElement", null, 1, 1, EObjectVariableValue.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(containerLayoutEEnum, ContainerLayout.class, "ContainerLayout"); //$NON-NLS-1$
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.FREE_FORM);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.LIST);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.HORIZONTAL_STACK);
        addEEnumLiteral(containerLayoutEEnum, ContainerLayout.VERTICAL_STACK);

        initEEnum(labelPositionEEnum, LabelPosition.class, "LabelPosition"); //$NON-NLS-1$
        addEEnumLiteral(labelPositionEEnum, LabelPosition.BORDER_LITERAL);
        addEEnumLiteral(labelPositionEEnum, LabelPosition.NODE_LITERAL);

        initEEnum(containerShapeEEnum, ContainerShape.class, "ContainerShape"); //$NON-NLS-1$
        addEEnumLiteral(containerShapeEEnum, ContainerShape.PARALLELOGRAM_LITERAL);

        initEEnum(backgroundStyleEEnum, BackgroundStyle.class, "BackgroundStyle"); //$NON-NLS-1$
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_LEFT_TO_RIGHT_LITERAL);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.LIQUID_LITERAL);
        addEEnumLiteral(backgroundStyleEEnum, BackgroundStyle.GRADIENT_TOP_TO_BOTTOM_LITERAL);

        initEEnum(bundledImageShapeEEnum, BundledImageShape.class, "BundledImageShape"); //$NON-NLS-1$
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.SQUARE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.STROKE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.TRIANGLE_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.DOT_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.RING_LITERAL);
        addEEnumLiteral(bundledImageShapeEEnum, BundledImageShape.PROVIDED_SHAPE_LITERAL);

        initEEnum(lineStyleEEnum, LineStyle.class, "LineStyle"); //$NON-NLS-1$
        addEEnumLiteral(lineStyleEEnum, LineStyle.SOLID_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASH_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DOT_LITERAL);
        addEEnumLiteral(lineStyleEEnum, LineStyle.DASH_DOT_LITERAL);

        initEEnum(edgeArrowsEEnum, EdgeArrows.class, "EdgeArrows"); //$NON-NLS-1$
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.NO_DECORATION_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.OUTPUT_FILL_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_FILL_CLOSED_ARROW_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.FILL_DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.CIRCLE_PLUS_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.DOT_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_DOT_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.DIAMOND_WITH_DOT_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.FILL_DIAMOND_WITH_DOT_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_DIAMOND_AND_DOT_LITERAL);
        addEEnumLiteral(edgeArrowsEEnum, EdgeArrows.INPUT_ARROW_WITH_FILL_DIAMOND_AND_DOT_LITERAL);

        initEEnum(edgeRoutingEEnum, EdgeRouting.class, "EdgeRouting"); //$NON-NLS-1$
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.STRAIGHT_LITERAL);
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.MANHATTAN_LITERAL);
        addEEnumLiteral(edgeRoutingEEnum, EdgeRouting.TREE_LITERAL);

        initEEnum(alignmentKindEEnum, AlignmentKind.class, "AlignmentKind"); //$NON-NLS-1$
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.VERTICAL_LITERAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.HORIZONTAL_LITERAL);
        addEEnumLiteral(alignmentKindEEnum, AlignmentKind.SQUARE_LITERAL);

        initEEnum(resizeKindEEnum, ResizeKind.class, "ResizeKind"); //$NON-NLS-1$
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NONE_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NSEW_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.NORTH_SOUTH_LITERAL);
        addEEnumLiteral(resizeKindEEnum, ResizeKind.EAST_WEST_LITERAL);

        initEEnum(arrangeConstraintEEnum, ArrangeConstraint.class, "ArrangeConstraint"); //$NON-NLS-1$
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_LOCATION);
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_SIZE);
        addEEnumLiteral(arrangeConstraintEEnum, ArrangeConstraint.KEEP_RATIO);

        // Create resource
        createResource(DiagramPackage.eNS_URI);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // Sirius
        createSiriusAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getDot_StrokeSizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getBorderedStyle_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables"; //$NON-NLS-1$
        addAnnotation(getDot_StrokeSizeComputationExpression(), source, new String[] {});
        addAnnotation(getBorderedStyle_BorderSizeComputationExpression(), source, new String[] {});
    }

    /**
     * Initializes the annotations for <b>Sirius</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createSiriusAnnotations() {
        String source = "Sirius"; //$NON-NLS-1$
        addAnnotation(edgeArrowsEEnum.getELiterals().get(0), source, new String[] { "imagePath", "icons/full/decorator/noDecoration.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(1), source, new String[] { "imagePath", "icons/full/decorator/outputArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(2), source, new String[] { "imagePath", "icons/full/decorator/inputArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(3), source, new String[] { "imagePath", "icons/full/decorator/outputClosedArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(4), source, new String[] { "imagePath", "icons/full/decorator/inputClosedArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(5), source, new String[] { "imagePath", "icons/full/decorator/outputFillClosedArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(6), source, new String[] { "imagePath", "icons/full/decorator/inputFillClosedArrow.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(7), source, new String[] { "imagePath", "icons/full/decorator/diamond.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(8), source, new String[] { "imagePath", "icons/full/decorator/fillDiamond.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(9), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithDiamond.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(10), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithFillDiamond.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(11), source, new String[] { "imagePath", "icons/full/decorator/circlePlus.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(12), source, new String[] { "imagePath", "icons/full/decorator/dot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(13), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithDot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(14), source, new String[] { "imagePath", "icons/full/decorator/diamondWithDot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(15), source, new String[] { "imagePath", "icons/full/decorator/fillDiamondWithDot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(16), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithDiamondAndDot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(edgeArrowsEEnum.getELiterals().get(17), source, new String[] { "imagePath", "icons/full/decorator/inputArrowWithFillDiamondAndDot.gif" //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

} // DiagramPackageImpl
