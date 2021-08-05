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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.diagram.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.BorderedStyleDescription;
import org.eclipse.sirius.diagram.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.BundledImageDescription;
import org.eclipse.sirius.diagram.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.ContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.CustomStyleDescription;
import org.eclipse.sirius.diagram.description.style.DotDescription;
import org.eclipse.sirius.diagram.description.style.EdgeStyleDescription;
import org.eclipse.sirius.diagram.description.style.EllipseNodeDescription;
import org.eclipse.sirius.diagram.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.diagram.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.diagram.description.style.GaugeSectionDescription;
import org.eclipse.sirius.diagram.description.style.HideLabelCapabilityStyleDescription;
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.Side;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.description.tool.ToolPackage;
import org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class StylePackageImpl extends EPackageImpl implements StylePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass borderedStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass nodeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass customStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass squareDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass lozengeNodeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass ellipseNodeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass bundledImageDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass noteDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass dotDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass gaugeCompositeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass gaugeSectionDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass sizeComputationContainerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass roundedCornerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass containerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass flatContainerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass shapeContainerStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass workspaceImageDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass edgeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass beginLabelStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass centerLabelStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass endLabelStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass bracketEdgeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass hideLabelCapabilityStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EEnum sideEEnum = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private StylePackageImpl() {
        super(StylePackage.eNS_URI, StyleFactory.eINSTANCE);
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
     * This method is used to initialize {@link StylePackage#eINSTANCE} when that field is accessed. Clients should not
     * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static StylePackage init() {
        if (StylePackageImpl.isInited) {
            return (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);
        }

        // Obtain or create and register package
        Object registeredStylePackage = EPackage.Registry.INSTANCE.get(StylePackage.eNS_URI);
        StylePackageImpl theStylePackage = registeredStylePackage instanceof StylePackageImpl ? (StylePackageImpl) registeredStylePackage : new StylePackageImpl();

        StylePackageImpl.isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();
        ViewpointPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        Object registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (registeredPackage instanceof DiagramPackageImpl ? registeredPackage : DiagramPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (registeredPackage instanceof DescriptionPackageImpl ? registeredPackage : DescriptionPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (registeredPackage instanceof ToolPackageImpl ? registeredPackage : ToolPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (registeredPackage instanceof FilterPackageImpl ? registeredPackage : FilterPackage.eINSTANCE);
        registeredPackage = EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (registeredPackage instanceof ConcernPackageImpl ? registeredPackage : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theStylePackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theStylePackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theConcernPackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        theStylePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(StylePackage.eNS_URI, theStylePackage);
        return theStylePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBorderedStyleDescription() {
        return borderedStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyleDescription_BorderSizeComputationExpression() {
        return (EAttribute) borderedStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBorderedStyleDescription_BorderColor() {
        return (EReference) borderedStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBorderedStyleDescription_BorderLineStyle() {
        return (EAttribute) borderedStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNodeStyleDescription() {
        return nodeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyleDescription_SizeComputationExpression() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyleDescription_LabelPosition() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyleDescription_ResizeKind() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getNodeStyleDescription_ForbiddenSides() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCustomStyleDescription() {
        return customStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getCustomStyleDescription_Id() {
        return (EAttribute) customStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSquareDescription() {
        return squareDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSquareDescription_Width() {
        return (EAttribute) squareDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSquareDescription_Height() {
        return (EAttribute) squareDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getSquareDescription_Color() {
        return (EReference) squareDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getLozengeNodeDescription() {
        return lozengeNodeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLozengeNodeDescription_WidthComputationExpression() {
        return (EAttribute) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getLozengeNodeDescription_HeightComputationExpression() {
        return (EAttribute) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getLozengeNodeDescription_Color() {
        return (EReference) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEllipseNodeDescription() {
        return ellipseNodeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEllipseNodeDescription_Color() {
        return (EReference) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEllipseNodeDescription_HorizontalDiameterComputationExpression() {
        return (EAttribute) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEllipseNodeDescription_VerticalDiameterComputationExpression() {
        return (EAttribute) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBundledImageDescription() {
        return bundledImageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBundledImageDescription_Shape() {
        return (EAttribute) bundledImageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getBundledImageDescription_Color() {
        return (EReference) bundledImageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getBundledImageDescription_ProvidedShapeID() {
        return (EAttribute) bundledImageDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getNoteDescription() {
        return noteDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getNoteDescription_Color() {
        return (EReference) noteDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getDotDescription() {
        return dotDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getDotDescription_BackgroundColor() {
        return (EReference) dotDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getDotDescription_StrokeSizeComputationExpression() {
        return (EAttribute) dotDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGaugeCompositeStyleDescription() {
        return gaugeCompositeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeCompositeStyleDescription_Alignment() {
        return (EAttribute) gaugeCompositeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGaugeCompositeStyleDescription_Sections() {
        return (EReference) gaugeCompositeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getGaugeSectionDescription() {
        return gaugeSectionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSectionDescription_MinValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSectionDescription_MaxValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSectionDescription_ValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGaugeSectionDescription_BackgroundColor() {
        return (EReference) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getGaugeSectionDescription_ForegroundColor() {
        return (EReference) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getGaugeSectionDescription_Label() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getSizeComputationContainerStyleDescription() {
        return sizeComputationContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSizeComputationContainerStyleDescription_WidthComputationExpression() {
        return (EAttribute) sizeComputationContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getSizeComputationContainerStyleDescription_HeightComputationExpression() {
        return (EAttribute) sizeComputationContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getRoundedCornerStyleDescription() {
        return roundedCornerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRoundedCornerStyleDescription_ArcWidth() {
        return (EAttribute) roundedCornerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getRoundedCornerStyleDescription_ArcHeight() {
        return (EAttribute) roundedCornerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getContainerStyleDescription() {
        return containerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getContainerStyleDescription_RoundedCorner() {
        return (EAttribute) containerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getFlatContainerStyleDescription() {
        return flatContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getFlatContainerStyleDescription_BackgroundStyle() {
        return (EAttribute) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFlatContainerStyleDescription_BackgroundColor() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFlatContainerStyleDescription_ForegroundColor() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getFlatContainerStyleDescription_LabelBorderStyle() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getShapeContainerStyleDescription() {
        return shapeContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getShapeContainerStyleDescription_Shape() {
        return (EAttribute) shapeContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getShapeContainerStyleDescription_BackgroundColor() {
        return (EReference) shapeContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getWorkspaceImageDescription() {
        return workspaceImageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getWorkspaceImageDescription_WorkspacePath() {
        return (EAttribute) workspaceImageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEdgeStyleDescription() {
        return edgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_StrokeColor() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_LineStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_SourceArrow() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_TargetArrow() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_SizeComputationExpression() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_RoutingStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_FoldingStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_BeginLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_CenterLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_EndLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getEdgeStyleDescription_EndsCentering() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(10);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_CenteredSourceMappings() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(11);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getEdgeStyleDescription_CenteredTargetMappings() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(12);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBeginLabelStyleDescription() {
        return beginLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getCenterLabelStyleDescription() {
        return centerLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getEndLabelStyleDescription() {
        return endLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getBracketEdgeStyleDescription() {
        return bracketEdgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getHideLabelCapabilityStyleDescription() {
        return hideLabelCapabilityStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getHideLabelCapabilityStyleDescription_HideLabelByDefault() {
        return (EAttribute) hideLabelCapabilityStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EEnum getSide() {
        return sideEEnum;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public StyleFactory getStyleFactory() {
        return (StyleFactory) getEFactoryInstance();
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
        borderedStyleDescriptionEClass = createEClass(StylePackage.BORDERED_STYLE_DESCRIPTION);
        createEAttribute(borderedStyleDescriptionEClass, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(borderedStyleDescriptionEClass, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_COLOR);
        createEAttribute(borderedStyleDescriptionEClass, StylePackage.BORDERED_STYLE_DESCRIPTION__BORDER_LINE_STYLE);

        nodeStyleDescriptionEClass = createEClass(StylePackage.NODE_STYLE_DESCRIPTION);
        createEAttribute(nodeStyleDescriptionEClass, StylePackage.NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(nodeStyleDescriptionEClass, StylePackage.NODE_STYLE_DESCRIPTION__LABEL_POSITION);
        createEAttribute(nodeStyleDescriptionEClass, StylePackage.NODE_STYLE_DESCRIPTION__RESIZE_KIND);
        createEAttribute(nodeStyleDescriptionEClass, StylePackage.NODE_STYLE_DESCRIPTION__FORBIDDEN_SIDES);

        customStyleDescriptionEClass = createEClass(StylePackage.CUSTOM_STYLE_DESCRIPTION);
        createEAttribute(customStyleDescriptionEClass, StylePackage.CUSTOM_STYLE_DESCRIPTION__ID);

        squareDescriptionEClass = createEClass(StylePackage.SQUARE_DESCRIPTION);
        createEAttribute(squareDescriptionEClass, StylePackage.SQUARE_DESCRIPTION__WIDTH);
        createEAttribute(squareDescriptionEClass, StylePackage.SQUARE_DESCRIPTION__HEIGHT);
        createEReference(squareDescriptionEClass, StylePackage.SQUARE_DESCRIPTION__COLOR);

        lozengeNodeDescriptionEClass = createEClass(StylePackage.LOZENGE_NODE_DESCRIPTION);
        createEAttribute(lozengeNodeDescriptionEClass, StylePackage.LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION);
        createEAttribute(lozengeNodeDescriptionEClass, StylePackage.LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION);
        createEReference(lozengeNodeDescriptionEClass, StylePackage.LOZENGE_NODE_DESCRIPTION__COLOR);

        ellipseNodeDescriptionEClass = createEClass(StylePackage.ELLIPSE_NODE_DESCRIPTION);
        createEReference(ellipseNodeDescriptionEClass, StylePackage.ELLIPSE_NODE_DESCRIPTION__COLOR);
        createEAttribute(ellipseNodeDescriptionEClass, StylePackage.ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION);
        createEAttribute(ellipseNodeDescriptionEClass, StylePackage.ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION);

        bundledImageDescriptionEClass = createEClass(StylePackage.BUNDLED_IMAGE_DESCRIPTION);
        createEAttribute(bundledImageDescriptionEClass, StylePackage.BUNDLED_IMAGE_DESCRIPTION__SHAPE);
        createEReference(bundledImageDescriptionEClass, StylePackage.BUNDLED_IMAGE_DESCRIPTION__COLOR);
        createEAttribute(bundledImageDescriptionEClass, StylePackage.BUNDLED_IMAGE_DESCRIPTION__PROVIDED_SHAPE_ID);

        noteDescriptionEClass = createEClass(StylePackage.NOTE_DESCRIPTION);
        createEReference(noteDescriptionEClass, StylePackage.NOTE_DESCRIPTION__COLOR);

        dotDescriptionEClass = createEClass(StylePackage.DOT_DESCRIPTION);
        createEReference(dotDescriptionEClass, StylePackage.DOT_DESCRIPTION__BACKGROUND_COLOR);
        createEAttribute(dotDescriptionEClass, StylePackage.DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION);

        gaugeCompositeStyleDescriptionEClass = createEClass(StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION);
        createEAttribute(gaugeCompositeStyleDescriptionEClass, StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT);
        createEReference(gaugeCompositeStyleDescriptionEClass, StylePackage.GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS);

        gaugeSectionDescriptionEClass = createEClass(StylePackage.GAUGE_SECTION_DESCRIPTION);
        createEAttribute(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR);
        createEReference(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR);
        createEAttribute(gaugeSectionDescriptionEClass, StylePackage.GAUGE_SECTION_DESCRIPTION__LABEL);

        sizeComputationContainerStyleDescriptionEClass = createEClass(StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(sizeComputationContainerStyleDescriptionEClass, StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION);
        createEAttribute(sizeComputationContainerStyleDescriptionEClass, StylePackage.SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION);

        roundedCornerStyleDescriptionEClass = createEClass(StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION);
        createEAttribute(roundedCornerStyleDescriptionEClass, StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH);
        createEAttribute(roundedCornerStyleDescriptionEClass, StylePackage.ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT);

        containerStyleDescriptionEClass = createEClass(StylePackage.CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(containerStyleDescriptionEClass, StylePackage.CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER);

        flatContainerStyleDescriptionEClass = createEClass(StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(flatContainerStyleDescriptionEClass, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE);
        createEReference(flatContainerStyleDescriptionEClass, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR);
        createEReference(flatContainerStyleDescriptionEClass, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR);
        createEReference(flatContainerStyleDescriptionEClass, StylePackage.FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE);

        shapeContainerStyleDescriptionEClass = createEClass(StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(shapeContainerStyleDescriptionEClass, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE);
        createEReference(shapeContainerStyleDescriptionEClass, StylePackage.SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        workspaceImageDescriptionEClass = createEClass(StylePackage.WORKSPACE_IMAGE_DESCRIPTION);
        createEAttribute(workspaceImageDescriptionEClass, StylePackage.WORKSPACE_IMAGE_DESCRIPTION__WORKSPACE_PATH);

        edgeStyleDescriptionEClass = createEClass(StylePackage.EDGE_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__STROKE_COLOR);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__LINE_STYLE);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__SOURCE_ARROW);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__TARGET_ARROW);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__ROUTING_STYLE);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__FOLDING_STYLE);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION);
        createEAttribute(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__ENDS_CENTERING);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_SOURCE_MAPPINGS);
        createEReference(edgeStyleDescriptionEClass, StylePackage.EDGE_STYLE_DESCRIPTION__CENTERED_TARGET_MAPPINGS);

        beginLabelStyleDescriptionEClass = createEClass(StylePackage.BEGIN_LABEL_STYLE_DESCRIPTION);

        centerLabelStyleDescriptionEClass = createEClass(StylePackage.CENTER_LABEL_STYLE_DESCRIPTION);

        endLabelStyleDescriptionEClass = createEClass(StylePackage.END_LABEL_STYLE_DESCRIPTION);

        bracketEdgeStyleDescriptionEClass = createEClass(StylePackage.BRACKET_EDGE_STYLE_DESCRIPTION);

        hideLabelCapabilityStyleDescriptionEClass = createEClass(StylePackage.HIDE_LABEL_CAPABILITY_STYLE_DESCRIPTION);
        createEAttribute(hideLabelCapabilityStyleDescriptionEClass, StylePackage.HIDE_LABEL_CAPABILITY_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT);

        // Create enums
        sideEEnum = createEEnum(StylePackage.SIDE);
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
        setName(StylePackage.eNAME);
        setNsPrefix(StylePackage.eNS_PREFIX);
        setNsURI(StylePackage.eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.viewpoint.description.style.StylePackage theStylePackage_1 = (org.eclipse.sirius.viewpoint.description.style.StylePackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.style.StylePackage.eNS_URI);
        org.eclipse.sirius.viewpoint.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.viewpoint.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.DescriptionPackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        borderedStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getBorderedStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getLabelStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getTooltipStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getHideLabelCapabilityStyleDescription());
        customStyleDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        squareDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        lozengeNodeDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        ellipseNodeDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        bundledImageDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        noteDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        dotDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        gaugeCompositeStyleDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        roundedCornerStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getRoundedCornerStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getBorderedStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getLabelStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getTooltipStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getHideLabelCapabilityStyleDescription());
        flatContainerStyleDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        flatContainerStyleDescriptionEClass.getESuperTypes().add(this.getSizeComputationContainerStyleDescription());
        shapeContainerStyleDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        shapeContainerStyleDescriptionEClass.getESuperTypes().add(this.getSizeComputationContainerStyleDescription());
        workspaceImageDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        workspaceImageDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        edgeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        beginLabelStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getBasicLabelStyleDescription());
        centerLabelStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getBasicLabelStyleDescription());
        endLabelStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getBasicLabelStyleDescription());
        bracketEdgeStyleDescriptionEClass.getESuperTypes().add(this.getEdgeStyleDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(borderedStyleDescriptionEClass, BorderedStyleDescription.class, "BorderedStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBorderedStyleDescription_BorderSizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "borderSizeComputationExpression", "0", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                BorderedStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getBorderedStyleDescription_BorderColor(), theDescriptionPackage_1.getColorDescription(), null, "borderColor", null, 1, 1, BorderedStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBorderedStyleDescription_BorderLineStyle(), theDiagramPackage.getLineStyle(), "borderLineStyle", "solid", 0, 1, BorderedStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(nodeStyleDescriptionEClass, NodeStyleDescription.class, "NodeStyleDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getNodeStyleDescription_SizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sizeComputationExpression", "3", 0, 1, NodeStyleDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getNodeStyleDescription_LabelPosition(), theDiagramPackage.getLabelPosition(), "labelPosition", "border", 0, 1, NodeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getNodeStyleDescription_ResizeKind(), theDiagramPackage.getResizeKind(), "resizeKind", "NONE", 1, 1, NodeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getNodeStyleDescription_ForbiddenSides(), this.getSide(), "forbiddenSides", null, 0, -1, NodeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(customStyleDescriptionEClass, CustomStyleDescription.class, "CustomStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomStyleDescription_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CustomStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(squareDescriptionEClass, SquareDescription.class, "SquareDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEAttribute(getSquareDescription_Width(), theEcorePackage.getEIntegerObject(), "width", "0", 0, 1, SquareDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSquareDescription_Height(), theEcorePackage.getEIntegerObject(), "height", "0", 0, 1, SquareDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$//$NON-NLS-2$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getSquareDescription_Color(), theDescriptionPackage_1.getColorDescription(), null, "color", null, 1, 1, SquareDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(lozengeNodeDescriptionEClass, LozengeNodeDescription.class, "LozengeNodeDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLozengeNodeDescription_WidthComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "widthComputationExpression", null, 0, 1, //$NON-NLS-1$
                LozengeNodeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getLozengeNodeDescription_HeightComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "heightComputationExpression", null, 0, 1, //$NON-NLS-1$
                LozengeNodeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getLozengeNodeDescription_Color(), theDescriptionPackage_1.getColorDescription(), null, "color", null, 1, 1, LozengeNodeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(ellipseNodeDescriptionEClass, EllipseNodeDescription.class, "EllipseNodeDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEllipseNodeDescription_Color(), theDescriptionPackage_1.getColorDescription(), null, "color", null, 1, 1, EllipseNodeDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "horizontalDiameterComputationExpression", "0", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                EllipseNodeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEllipseNodeDescription_VerticalDiameterComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "verticalDiameterComputationExpression", "0", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                EllipseNodeDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(bundledImageDescriptionEClass, BundledImageDescription.class, "BundledImageDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBundledImageDescription_Shape(), theDiagramPackage.getBundledImageShape(), "shape", null, 1, 1, BundledImageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getBundledImageDescription_Color(), theDescriptionPackage_1.getColorDescription(), null, "color", null, 1, 1, BundledImageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getBundledImageDescription_ProvidedShapeID(), ecorePackage.getEString(), "providedShapeID", null, 0, 1, BundledImageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(noteDescriptionEClass, NoteDescription.class, "NoteDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getNoteDescription_Color(), theDescriptionPackage_1.getColorDescription(), null, "color", null, 1, 1, NoteDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(dotDescriptionEClass, DotDescription.class, "DotDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getDotDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, DotDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getDotDescription_StrokeSizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "strokeSizeComputationExpression", "2", 0, 1, DotDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        initEClass(gaugeCompositeStyleDescriptionEClass, GaugeCompositeStyleDescription.class, "GaugeCompositeStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeCompositeStyleDescription_Alignment(), theDiagramPackage.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyleDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGaugeCompositeStyleDescription_Sections(), this.getGaugeSectionDescription(), null, "sections", null, 0, -1, GaugeCompositeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(gaugeSectionDescriptionEClass, GaugeSectionDescription.class, "GaugeSectionDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeSectionDescription_MinValueExpression(), theDescriptionPackage_1.getInterpretedExpression(), "minValueExpression", "0", 0, 1, GaugeSectionDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_MaxValueExpression(), theDescriptionPackage_1.getInterpretedExpression(), "maxValueExpression", "0", 0, 1, GaugeSectionDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_ValueExpression(), theDescriptionPackage_1.getInterpretedExpression(), "valueExpression", "0", 0, 1, GaugeSectionDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getGaugeSectionDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, GaugeSectionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getGaugeSectionDescription_ForegroundColor(), theDescriptionPackage_1.getColorDescription(), null, "foregroundColor", null, 1, 1, GaugeSectionDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSectionDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(sizeComputationContainerStyleDescriptionEClass, SizeComputationContainerStyleDescription.class, "SizeComputationContainerStyleDescription", EPackageImpl.IS_ABSTRACT, //$NON-NLS-1$
                !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSizeComputationContainerStyleDescription_WidthComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "widthComputationExpression", "-1", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                SizeComputationContainerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getSizeComputationContainerStyleDescription_HeightComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "heightComputationExpression", "-1", 0, 1, //$NON-NLS-1$//$NON-NLS-2$
                SizeComputationContainerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(roundedCornerStyleDescriptionEClass, RoundedCornerStyleDescription.class, "RoundedCornerStyleDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoundedCornerStyleDescription_ArcWidth(), theEcorePackage.getEIntegerObject(), "arcWidth", "10", 0, 1, RoundedCornerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getRoundedCornerStyleDescription_ArcHeight(), theEcorePackage.getEIntegerObject(), "arcHeight", "10", 0, 1, RoundedCornerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(containerStyleDescriptionEClass, ContainerStyleDescription.class, "ContainerStyleDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerStyleDescription_RoundedCorner(), theEcorePackage.getEBoolean(), "roundedCorner", null, 0, 1, ContainerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(flatContainerStyleDescriptionEClass, FlatContainerStyleDescription.class, "FlatContainerStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlatContainerStyleDescription_BackgroundStyle(), theDiagramPackage.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, FlatContainerStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_ForegroundColor(), theDescriptionPackage_1.getColorDescription(), null, "foregroundColor", null, 1, 1, FlatContainerStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_LabelBorderStyle(), theStylePackage_1.getLabelBorderStyleDescription(), null, "labelBorderStyle", null, 0, 1, //$NON-NLS-1$
                FlatContainerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(shapeContainerStyleDescriptionEClass, ShapeContainerStyleDescription.class, "ShapeContainerStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeContainerStyleDescription_Shape(), theDiagramPackage.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getShapeContainerStyleDescription_BackgroundColor(), theDescriptionPackage_1.getColorDescription(), null, "backgroundColor", null, 1, 1, ShapeContainerStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(workspaceImageDescriptionEClass, WorkspaceImageDescription.class, "WorkspaceImageDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWorkspaceImageDescription_WorkspacePath(), theDescriptionPackage_1.getImagePath(), "workspacePath", null, 1, 1, WorkspaceImageDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(edgeStyleDescriptionEClass, EdgeStyleDescription.class, "EdgeStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, EPackageImpl.IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
        initEReference(getEdgeStyleDescription_StrokeColor(), theDescriptionPackage_1.getColorDescription(), null, "strokeColor", null, 1, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_LineStyle(), theDiagramPackage.getLineStyle(), "lineStyle", null, 0, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SourceArrow(), theDiagramPackage.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_TargetArrow(), theDiagramPackage.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SizeComputationExpression(), theDescriptionPackage_1.getInterpretedExpression(), "sizeComputationExpression", "1", 1, 1, EdgeStyleDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_RoutingStyle(), theDiagramPackage.getEdgeRouting(), "routingStyle", "straight", 1, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_FoldingStyle(), theDescriptionPackage.getFoldingStyle(), "foldingStyle", null, 0, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyleDescription_BeginLabelStyleDescription(), this.getBeginLabelStyleDescription(), null, "beginLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyleDescription_CenterLabelStyleDescription(), this.getCenterLabelStyleDescription(), null, "centerLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyleDescription_EndLabelStyleDescription(), this.getEndLabelStyleDescription(), null, "endLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_EndsCentering(), theDescriptionPackage.getCenteringStyle(), "endsCentering", "None", 0, 1, EdgeStyleDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyleDescription_CenteredSourceMappings(), theDescriptionPackage.getDiagramElementMapping(), null, "centeredSourceMappings", null, 0, -1, EdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getEdgeStyleDescription_CenteredTargetMappings(), theDescriptionPackage.getDiagramElementMapping(), null, "centeredTargetMappings", null, 0, -1, EdgeStyleDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(beginLabelStyleDescriptionEClass, BeginLabelStyleDescription.class, "BeginLabelStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(centerLabelStyleDescriptionEClass, CenterLabelStyleDescription.class, "CenterLabelStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(endLabelStyleDescriptionEClass, EndLabelStyleDescription.class, "EndLabelStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(bracketEdgeStyleDescriptionEClass, BracketEdgeStyleDescription.class, "BracketEdgeStyleDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(hideLabelCapabilityStyleDescriptionEClass, HideLabelCapabilityStyleDescription.class, "HideLabelCapabilityStyleDescription", EPackageImpl.IS_ABSTRACT, EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getHideLabelCapabilityStyleDescription_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, HideLabelCapabilityStyleDescription.class, //$NON-NLS-1$//$NON-NLS-2$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED,
                EPackageImpl.IS_ORDERED);

        // Initialize enums and add enum literals
        initEEnum(sideEEnum, Side.class, "Side"); //$NON-NLS-1$
        addEEnumLiteral(sideEEnum, Side.WEST);
        addEEnumLiteral(sideEEnum, Side.SOUTH);
        addEEnumLiteral(sideEEnum, Side.EAST);
        addEEnumLiteral(sideEEnum, Side.NORTH);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // TagValues
        createTagValuesAnnotations();
    }

    /**
     * Initializes the annotations for <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType"; //$NON-NLS-1$
        addAnnotation(getBorderedStyleDescription_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getNodeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLozengeNodeDescription_WidthComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getLozengeNodeDescription_HeightComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEllipseNodeDescription_VerticalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getGaugeSectionDescription_MinValueExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getGaugeSectionDescription_MaxValueExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getGaugeSectionDescription_ValueExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSizeComputationContainerStyleDescription_WidthComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getSizeComputationContainerStyleDescription_HeightComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
        });
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." //$NON-NLS-1$ //$NON-NLS-2$
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
        addAnnotation(getBorderedStyleDescription_BorderSizeComputationExpression(), source, new String[] {});
        addAnnotation(getNodeStyleDescription_SizeComputationExpression(), source, new String[] {});
        addAnnotation(getLozengeNodeDescription_WidthComputationExpression(), source, new String[] {});
        addAnnotation(getLozengeNodeDescription_HeightComputationExpression(), source, new String[] {});
        addAnnotation(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), source, new String[] {});
        addAnnotation(getEllipseNodeDescription_VerticalDiameterComputationExpression(), source, new String[] {});
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] {});
        addAnnotation(getGaugeSectionDescription_MinValueExpression(), source, new String[] {});
        addAnnotation(getGaugeSectionDescription_MaxValueExpression(), source, new String[] {});
        addAnnotation(getGaugeSectionDescription_ValueExpression(), source, new String[] {});
        addAnnotation(getSizeComputationContainerStyleDescription_WidthComputationExpression(), source, new String[] {});
        addAnnotation(getSizeComputationContainerStyleDescription_HeightComputationExpression(), source, new String[] {});
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] { "diagram", "diagram.DSemanticDiagram | the current DSemanticDiagram.", //$NON-NLS-1$ //$NON-NLS-2$
                "view", "diagram.DEdge | the current edge view for which the size is calculated.", //$NON-NLS-1$ //$NON-NLS-2$
                "sourceView", "diagram.EdgeTarget | the source view of the current edge.", //$NON-NLS-1$ //$NON-NLS-2$
                "targetView", "diagram.EdgeTarget | the target view of the current edge." //$NON-NLS-1$ //$NON-NLS-2$
        });
    }

    /**
     * Initializes the annotations for <b>TagValues</b>. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    protected void createTagValuesAnnotations() {
        String source = "TagValues"; //$NON-NLS-1$
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] {});
    }

} // StylePackageImpl
