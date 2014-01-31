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
package org.eclipse.sirius.diagram.description.style.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.concern.ConcernPackage;
import org.eclipse.sirius.diagram.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.diagram.description.filter.FilterPackage;
import org.eclipse.sirius.diagram.description.filter.impl.FilterPackageImpl;
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
import org.eclipse.sirius.diagram.description.style.LozengeNodeDescription;
import org.eclipse.sirius.diagram.description.style.NodeStyleDescription;
import org.eclipse.sirius.diagram.description.style.NoteDescription;
import org.eclipse.sirius.diagram.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SizeComputationContainerStyleDescription;
import org.eclipse.sirius.diagram.description.style.SquareDescription;
import org.eclipse.sirius.diagram.description.style.StyleFactory;
import org.eclipse.sirius.diagram.description.style.StylePackage;
import org.eclipse.sirius.diagram.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.diagram.impl.DiagramPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.impl.ViewpointPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
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
     * Creates an instance of the model <b>Package</b>, registered with
     * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
     * package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory
     * method {@link #init init()}, which also performs initialization of the
     * package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private StylePackageImpl() {
        super(eNS_URI, StyleFactory.eINSTANCE);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private static boolean isInited = false;

    /**
     * Creates, registers, and initializes the <b>Package</b> for this model,
     * and for any others upon which it depends.
     * 
     * <p>
     * This method is used to initialize {@link StylePackage#eINSTANCE} when
     * that field is accessed. Clients should not invoke it directly. Instead,
     * they should simply access that field to obtain the package. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static StylePackage init() {
        if (isInited)
            return (StylePackage) EPackage.Registry.INSTANCE.getEPackage(StylePackage.eNS_URI);

        // Obtain or create and register package
        StylePackageImpl theStylePackage = (StylePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof StylePackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new StylePackageImpl());

        isInited = true;

        // Initialize simple dependencies
        EcorePackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        ViewpointPackageImpl theViewpointPackage = (ViewpointPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI) instanceof ViewpointPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ViewpointPackage.eNS_URI) : ViewpointPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl theStylePackage_1 = (org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.style.StylePackage.eNS_URI) instanceof org.eclipse.sirius.viewpoint.description.style.impl.StylePackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.style.StylePackage.eNS_URI) : org.eclipse.sirius.viewpoint.description.style.StylePackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl theValidationPackage = (org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eNS_URI) instanceof org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eNS_URI) : org.eclipse.sirius.viewpoint.description.validation.ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        DiagramPackageImpl theDiagramPackage = (DiagramPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI) instanceof DiagramPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DiagramPackage.eNS_URI) : DiagramPackage.eINSTANCE);
        org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) instanceof org.eclipse.sirius.diagram.description.impl.DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI) : org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE);
        org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl theToolPackage_1 = (org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl) (EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.tool.ToolPackage.eNS_URI) instanceof org.eclipse.sirius.diagram.description.tool.impl.ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.tool.ToolPackage.eNS_URI) : org.eclipse.sirius.diagram.description.tool.ToolPackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theStylePackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theStylePackage_1.createPackageContents();
        theToolPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theDiagramPackage.createPackageContents();
        theDescriptionPackage_1.createPackageContents();
        theToolPackage_1.createPackageContents();
        theFilterPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theStylePackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theStylePackage_1.initializePackageContents();
        theToolPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
        theDiagramPackage.initializePackageContents();
        theDescriptionPackage_1.initializePackageContents();
        theToolPackage_1.initializePackageContents();
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
    public EClass getBorderedStyleDescription() {
        return borderedStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBorderedStyleDescription_BorderSizeComputationExpression() {
        return (EAttribute) borderedStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBorderedStyleDescription_BorderColor() {
        return (EReference) borderedStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNodeStyleDescription() {
        return nodeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyleDescription_SizeComputationExpression() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyleDescription_LabelPosition() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyleDescription_HideLabelByDefault() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getNodeStyleDescription_ResizeKind() {
        return (EAttribute) nodeStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCustomStyleDescription() {
        return customStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getCustomStyleDescription_Id() {
        return (EAttribute) customStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSquareDescription() {
        return squareDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquareDescription_Width() {
        return (EAttribute) squareDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSquareDescription_Height() {
        return (EAttribute) squareDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getSquareDescription_Color() {
        return (EReference) squareDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLozengeNodeDescription() {
        return lozengeNodeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozengeNodeDescription_WidthComputationExpression() {
        return (EAttribute) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLozengeNodeDescription_HeightComputationExpression() {
        return (EAttribute) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLozengeNodeDescription_Color() {
        return (EReference) lozengeNodeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEllipseNodeDescription() {
        return ellipseNodeDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEllipseNodeDescription_Color() {
        return (EReference) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipseNodeDescription_HorizontalDiameterComputationExpression() {
        return (EAttribute) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEllipseNodeDescription_VerticalDiameterComputationExpression() {
        return (EAttribute) ellipseNodeDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBundledImageDescription() {
        return bundledImageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBundledImageDescription_Shape() {
        return (EAttribute) bundledImageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBundledImageDescription_Color() {
        return (EReference) bundledImageDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getNoteDescription() {
        return noteDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getNoteDescription_Color() {
        return (EReference) noteDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getDotDescription() {
        return dotDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getDotDescription_BackgroundColor() {
        return (EReference) dotDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getDotDescription_StrokeSizeComputationExpression() {
        return (EAttribute) dotDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGaugeCompositeStyleDescription() {
        return gaugeCompositeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeCompositeStyleDescription_Alignment() {
        return (EAttribute) gaugeCompositeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeCompositeStyleDescription_Sections() {
        return (EReference) gaugeCompositeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getGaugeSectionDescription() {
        return gaugeSectionDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSectionDescription_MinValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSectionDescription_MaxValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSectionDescription_ValueExpression() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSectionDescription_BackgroundColor() {
        return (EReference) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getGaugeSectionDescription_ForegroundColor() {
        return (EReference) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getGaugeSectionDescription_Label() {
        return (EAttribute) gaugeSectionDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getSizeComputationContainerStyleDescription() {
        return sizeComputationContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSizeComputationContainerStyleDescription_WidthComputationExpression() {
        return (EAttribute) sizeComputationContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getSizeComputationContainerStyleDescription_HeightComputationExpression() {
        return (EAttribute) sizeComputationContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getRoundedCornerStyleDescription() {
        return roundedCornerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRoundedCornerStyleDescription_ArcWidth() {
        return (EAttribute) roundedCornerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getRoundedCornerStyleDescription_ArcHeight() {
        return (EAttribute) roundedCornerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getContainerStyleDescription() {
        return containerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getContainerStyleDescription_RoundedCorner() {
        return (EAttribute) containerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getFlatContainerStyleDescription() {
        return flatContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getFlatContainerStyleDescription_BackgroundStyle() {
        return (EAttribute) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyleDescription_BackgroundColor() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyleDescription_ForegroundColor() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getFlatContainerStyleDescription_LabelBorderStyle() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getShapeContainerStyleDescription() {
        return shapeContainerStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getShapeContainerStyleDescription_Shape() {
        return (EAttribute) shapeContainerStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getShapeContainerStyleDescription_BackgroundColor() {
        return (EReference) shapeContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getWorkspaceImageDescription() {
        return workspaceImageDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getWorkspaceImageDescription_WorkspacePath() {
        return (EAttribute) workspaceImageDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEdgeStyleDescription() {
        return edgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyleDescription_StrokeColor() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_LineStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_SourceArrow() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_TargetArrow() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_SizeComputationExpression() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_RoutingStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getEdgeStyleDescription_FoldingStyle() {
        return (EAttribute) edgeStyleDescriptionEClass.getEStructuralFeatures().get(6);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyleDescription_BeginLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(7);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyleDescription_CenterLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(8);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getEdgeStyleDescription_EndLabelStyleDescription() {
        return (EReference) edgeStyleDescriptionEClass.getEStructuralFeatures().get(9);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBeginLabelStyleDescription() {
        return beginLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getCenterLabelStyleDescription() {
        return centerLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getEndLabelStyleDescription() {
        return endLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getBracketEdgeStyleDescription() {
        return bracketEdgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
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
     * Creates the meta-model objects for the package. This method is guarded to
     * have no affect on any invocation but its first. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    public void createPackageContents() {
        if (isCreated)
            return;
        isCreated = true;

        // Create classes and their features
        borderedStyleDescriptionEClass = createEClass(BORDERED_STYLE_DESCRIPTION);
        createEAttribute(borderedStyleDescriptionEClass, BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(borderedStyleDescriptionEClass, BORDERED_STYLE_DESCRIPTION__BORDER_COLOR);

        nodeStyleDescriptionEClass = createEClass(NODE_STYLE_DESCRIPTION);
        createEAttribute(nodeStyleDescriptionEClass, NODE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(nodeStyleDescriptionEClass, NODE_STYLE_DESCRIPTION__LABEL_POSITION);
        createEAttribute(nodeStyleDescriptionEClass, NODE_STYLE_DESCRIPTION__HIDE_LABEL_BY_DEFAULT);
        createEAttribute(nodeStyleDescriptionEClass, NODE_STYLE_DESCRIPTION__RESIZE_KIND);

        customStyleDescriptionEClass = createEClass(CUSTOM_STYLE_DESCRIPTION);
        createEAttribute(customStyleDescriptionEClass, CUSTOM_STYLE_DESCRIPTION__ID);

        squareDescriptionEClass = createEClass(SQUARE_DESCRIPTION);
        createEAttribute(squareDescriptionEClass, SQUARE_DESCRIPTION__WIDTH);
        createEAttribute(squareDescriptionEClass, SQUARE_DESCRIPTION__HEIGHT);
        createEReference(squareDescriptionEClass, SQUARE_DESCRIPTION__COLOR);

        lozengeNodeDescriptionEClass = createEClass(LOZENGE_NODE_DESCRIPTION);
        createEAttribute(lozengeNodeDescriptionEClass, LOZENGE_NODE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION);
        createEAttribute(lozengeNodeDescriptionEClass, LOZENGE_NODE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION);
        createEReference(lozengeNodeDescriptionEClass, LOZENGE_NODE_DESCRIPTION__COLOR);

        ellipseNodeDescriptionEClass = createEClass(ELLIPSE_NODE_DESCRIPTION);
        createEReference(ellipseNodeDescriptionEClass, ELLIPSE_NODE_DESCRIPTION__COLOR);
        createEAttribute(ellipseNodeDescriptionEClass, ELLIPSE_NODE_DESCRIPTION__HORIZONTAL_DIAMETER_COMPUTATION_EXPRESSION);
        createEAttribute(ellipseNodeDescriptionEClass, ELLIPSE_NODE_DESCRIPTION__VERTICAL_DIAMETER_COMPUTATION_EXPRESSION);

        bundledImageDescriptionEClass = createEClass(BUNDLED_IMAGE_DESCRIPTION);
        createEAttribute(bundledImageDescriptionEClass, BUNDLED_IMAGE_DESCRIPTION__SHAPE);
        createEReference(bundledImageDescriptionEClass, BUNDLED_IMAGE_DESCRIPTION__COLOR);

        noteDescriptionEClass = createEClass(NOTE_DESCRIPTION);
        createEReference(noteDescriptionEClass, NOTE_DESCRIPTION__COLOR);

        dotDescriptionEClass = createEClass(DOT_DESCRIPTION);
        createEReference(dotDescriptionEClass, DOT_DESCRIPTION__BACKGROUND_COLOR);
        createEAttribute(dotDescriptionEClass, DOT_DESCRIPTION__STROKE_SIZE_COMPUTATION_EXPRESSION);

        gaugeCompositeStyleDescriptionEClass = createEClass(GAUGE_COMPOSITE_STYLE_DESCRIPTION);
        createEAttribute(gaugeCompositeStyleDescriptionEClass, GAUGE_COMPOSITE_STYLE_DESCRIPTION__ALIGNMENT);
        createEReference(gaugeCompositeStyleDescriptionEClass, GAUGE_COMPOSITE_STYLE_DESCRIPTION__SECTIONS);

        gaugeSectionDescriptionEClass = createEClass(GAUGE_SECTION_DESCRIPTION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR);
        createEReference(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__LABEL);

        sizeComputationContainerStyleDescriptionEClass = createEClass(SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(sizeComputationContainerStyleDescriptionEClass, SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__WIDTH_COMPUTATION_EXPRESSION);
        createEAttribute(sizeComputationContainerStyleDescriptionEClass, SIZE_COMPUTATION_CONTAINER_STYLE_DESCRIPTION__HEIGHT_COMPUTATION_EXPRESSION);

        roundedCornerStyleDescriptionEClass = createEClass(ROUNDED_CORNER_STYLE_DESCRIPTION);
        createEAttribute(roundedCornerStyleDescriptionEClass, ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH);
        createEAttribute(roundedCornerStyleDescriptionEClass, ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT);

        containerStyleDescriptionEClass = createEClass(CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(containerStyleDescriptionEClass, CONTAINER_STYLE_DESCRIPTION__ROUNDED_CORNER);

        flatContainerStyleDescriptionEClass = createEClass(FLAT_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(flatContainerStyleDescriptionEClass, FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_STYLE);
        createEReference(flatContainerStyleDescriptionEClass, FLAT_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR);
        createEReference(flatContainerStyleDescriptionEClass, FLAT_CONTAINER_STYLE_DESCRIPTION__FOREGROUND_COLOR);
        createEReference(flatContainerStyleDescriptionEClass, FLAT_CONTAINER_STYLE_DESCRIPTION__LABEL_BORDER_STYLE);

        shapeContainerStyleDescriptionEClass = createEClass(SHAPE_CONTAINER_STYLE_DESCRIPTION);
        createEAttribute(shapeContainerStyleDescriptionEClass, SHAPE_CONTAINER_STYLE_DESCRIPTION__SHAPE);
        createEReference(shapeContainerStyleDescriptionEClass, SHAPE_CONTAINER_STYLE_DESCRIPTION__BACKGROUND_COLOR);

        workspaceImageDescriptionEClass = createEClass(WORKSPACE_IMAGE_DESCRIPTION);
        createEAttribute(workspaceImageDescriptionEClass, WORKSPACE_IMAGE_DESCRIPTION__WORKSPACE_PATH);

        edgeStyleDescriptionEClass = createEClass(EDGE_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__STROKE_COLOR);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__LINE_STYLE);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__SOURCE_ARROW);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__TARGET_ARROW);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__SIZE_COMPUTATION_EXPRESSION);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__ROUTING_STYLE);
        createEAttribute(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__FOLDING_STYLE);
        createEReference(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__BEGIN_LABEL_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__CENTER_LABEL_STYLE_DESCRIPTION);
        createEReference(edgeStyleDescriptionEClass, EDGE_STYLE_DESCRIPTION__END_LABEL_STYLE_DESCRIPTION);

        beginLabelStyleDescriptionEClass = createEClass(BEGIN_LABEL_STYLE_DESCRIPTION);

        centerLabelStyleDescriptionEClass = createEClass(CENTER_LABEL_STYLE_DESCRIPTION);

        endLabelStyleDescriptionEClass = createEClass(END_LABEL_STYLE_DESCRIPTION);

        bracketEdgeStyleDescriptionEClass = createEClass(BRACKET_EDGE_STYLE_DESCRIPTION);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private boolean isInitialized = false;

    /**
     * Complete the initialization of the package and its meta-model. This
     * method is guarded to have no affect on any invocation but its first. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void initializePackageContents() {
        if (isInitialized)
            return;
        isInitialized = true;

        // Initialize package
        setName(eNAME);
        setNsPrefix(eNS_PREFIX);
        setNsURI(eNS_URI);

        // Obtain other dependent packages
        org.eclipse.sirius.viewpoint.description.style.StylePackage theStylePackage_1 = (org.eclipse.sirius.viewpoint.description.style.StylePackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.viewpoint.description.style.StylePackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        DiagramPackage theDiagramPackage = (DiagramPackage) EPackage.Registry.INSTANCE.getEPackage(DiagramPackage.eNS_URI);
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        org.eclipse.sirius.diagram.description.DescriptionPackage theDescriptionPackage_1 = (org.eclipse.sirius.diagram.description.DescriptionPackage) EPackage.Registry.INSTANCE
                .getEPackage(org.eclipse.sirius.diagram.description.DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        borderedStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getBorderedStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getLabelStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(theStylePackage_1.getTooltipStyleDescription());
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
        initEClass(borderedStyleDescriptionEClass, BorderedStyleDescription.class, "BorderedStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBorderedStyleDescription_BorderSizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "borderSizeComputationExpression", "0", 0, 1,
                BorderedStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBorderedStyleDescription_BorderColor(), theDescriptionPackage.getColorDescription(), null, "borderColor", null, 1, 1, BorderedStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeStyleDescriptionEClass, NodeStyleDescription.class, "NodeStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNodeStyleDescription_SizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "sizeComputationExpression", "3", 0, 1, NodeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_LabelPosition(), theDiagramPackage.getLabelPosition(), "labelPosition", "border", 0, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_ResizeKind(), theDiagramPackage.getResizeKind(), "resizeKind", "NONE", 1, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(customStyleDescriptionEClass, CustomStyleDescription.class, "CustomStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getCustomStyleDescription_Id(), theEcorePackage.getEString(), "id", null, 0, 1, CustomStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(squareDescriptionEClass, SquareDescription.class, "SquareDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSquareDescription_Width(), ecorePackage.getEIntegerObject(), "width", "0", 0, 1, SquareDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
                IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSquareDescription_Height(), ecorePackage.getEIntegerObject(), "height", "0", 0, 1, SquareDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
                !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getSquareDescription_Color(), theDescriptionPackage.getColorDescription(), null, "color", null, 1, 1, SquareDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(lozengeNodeDescriptionEClass, LozengeNodeDescription.class, "LozengeNodeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLozengeNodeDescription_WidthComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "widthComputationExpression", null, 0, 1,
                LozengeNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLozengeNodeDescription_HeightComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "heightComputationExpression", null, 0, 1,
                LozengeNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getLozengeNodeDescription_Color(), theDescriptionPackage.getColorDescription(), null, "color", null, 1, 1, LozengeNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(ellipseNodeDescriptionEClass, EllipseNodeDescription.class, "EllipseNodeDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEllipseNodeDescription_Color(), theDescriptionPackage.getColorDescription(), null, "color", null, 1, 1, EllipseNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "horizontalDiameterComputationExpression", "0", 0, 1,
                EllipseNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEllipseNodeDescription_VerticalDiameterComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "verticalDiameterComputationExpression", "0", 0, 1,
                EllipseNodeDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(bundledImageDescriptionEClass, BundledImageDescription.class, "BundledImageDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBundledImageDescription_Shape(), theDiagramPackage.getBundledImageShape(), "shape", null, 1, 1, BundledImageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBundledImageDescription_Color(), theDescriptionPackage.getColorDescription(), null, "color", null, 1, 1, BundledImageDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(noteDescriptionEClass, NoteDescription.class, "NoteDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getNoteDescription_Color(), theDescriptionPackage.getColorDescription(), null, "color", null, 1, 1, NoteDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(dotDescriptionEClass, DotDescription.class, "DotDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getDotDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, DotDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getDotDescription_StrokeSizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "strokeSizeComputationExpression", "2", 0, 1, DotDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeCompositeStyleDescriptionEClass, GaugeCompositeStyleDescription.class, "GaugeCompositeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeCompositeStyleDescription_Alignment(), theDiagramPackage.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeCompositeStyleDescription_Sections(), this.getGaugeSectionDescription(), null, "sections", null, 0, -1, GaugeCompositeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeSectionDescriptionEClass, GaugeSectionDescription.class, "GaugeSectionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeSectionDescription_MinValueExpression(), theDescriptionPackage.getInterpretedExpression(), "minValueExpression", "0", 0, 1, GaugeSectionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_MaxValueExpression(), theDescriptionPackage.getInterpretedExpression(), "maxValueExpression", "0", 0, 1, GaugeSectionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", "0", 0, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSectionDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSectionDescription_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 1, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(sizeComputationContainerStyleDescriptionEClass, SizeComputationContainerStyleDescription.class, "SizeComputationContainerStyleDescription", IS_ABSTRACT, !IS_INTERFACE,
                IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getSizeComputationContainerStyleDescription_WidthComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "widthComputationExpression", "-1", 0, 1,
                SizeComputationContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getSizeComputationContainerStyleDescription_HeightComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "heightComputationExpression", "-1", 0, 1,
                SizeComputationContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(roundedCornerStyleDescriptionEClass, RoundedCornerStyleDescription.class, "RoundedCornerStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoundedCornerStyleDescription_ArcWidth(), theEcorePackage.getEIntegerObject(), "arcWidth", "1", 0, 1, RoundedCornerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRoundedCornerStyleDescription_ArcHeight(), theEcorePackage.getEIntegerObject(), "arcHeight", "1", 0, 1, RoundedCornerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerStyleDescriptionEClass, ContainerStyleDescription.class, "ContainerStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerStyleDescription_RoundedCorner(), theEcorePackage.getEBoolean(), "roundedCorner", null, 0, 1, ContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flatContainerStyleDescriptionEClass, FlatContainerStyleDescription.class, "FlatContainerStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlatContainerStyleDescription_BackgroundStyle(), theDiagramPackage.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, FlatContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 1, 1, FlatContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_LabelBorderStyle(), theStylePackage_1.getLabelBorderStyleDescription(), null, "labelBorderStyle", null, 0, 1,
                FlatContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(shapeContainerStyleDescriptionEClass, ShapeContainerStyleDescription.class, "ShapeContainerStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeContainerStyleDescription_Shape(), theDiagramPackage.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getShapeContainerStyleDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, ShapeContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(workspaceImageDescriptionEClass, WorkspaceImageDescription.class, "WorkspaceImageDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWorkspaceImageDescription_WorkspacePath(), theEcorePackage.getEString(), "workspacePath", null, 1, 1, WorkspaceImageDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeStyleDescriptionEClass, EdgeStyleDescription.class, "EdgeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeStyleDescription_StrokeColor(), theDescriptionPackage.getColorDescription(), null, "strokeColor", null, 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_LineStyle(), theDiagramPackage.getLineStyle(), "lineStyle", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SourceArrow(), theDiagramPackage.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_TargetArrow(), theDiagramPackage.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "sizeComputationExpression", "<%eContents().nSize%>", 1, 1,
                EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_RoutingStyle(), theDiagramPackage.getEdgeRouting(), "routingStyle", "straight", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_FoldingStyle(), theDescriptionPackage_1.getFoldingStyle(), "foldingStyle", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_BeginLabelStyleDescription(), this.getBeginLabelStyleDescription(), null, "beginLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_CenterLabelStyleDescription(), this.getCenterLabelStyleDescription(), null, "centerLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_EndLabelStyleDescription(), this.getEndLabelStyleDescription(), null, "endLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(beginLabelStyleDescriptionEClass, BeginLabelStyleDescription.class, "BeginLabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(centerLabelStyleDescriptionEClass, CenterLabelStyleDescription.class, "CenterLabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(endLabelStyleDescriptionEClass, EndLabelStyleDescription.class, "EndLabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(bracketEdgeStyleDescriptionEClass, BracketEdgeStyleDescription.class, "BracketEdgeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
        // TagValues
        createTagValuesAnnotations();
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/returnType</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createReturnTypeAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/returnType";
        addAnnotation(getBorderedStyleDescription_BorderSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getNodeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getLozengeNodeDescription_WidthComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getLozengeNodeDescription_HeightComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEllipseNodeDescription_VerticalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getGaugeSectionDescription_MinValueExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getGaugeSectionDescription_MaxValueExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getGaugeSectionDescription_ValueExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getSizeComputationContainerStyleDescription_WidthComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getSizeComputationContainerStyleDescription_HeightComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." });
    }

    /**
     * Initializes the annotations for
     * <b>http://www.eclipse.org/sirius/interpreted/expression/variables</b>.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createVariablesAnnotations() {
        String source = "http://www.eclipse.org/sirius/interpreted/expression/variables";
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
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DSemanticDiagram.", "view",
                "diagram.DEdge | the current edge view for which the size is calculated.", "sourceView", "diagram.EdgeTarget | the source view of the current edge.", "targetView",
                "diagram.EdgeTarget | the target view of the current edge." });
    }

    /**
     * Initializes the annotations for <b>TagValues</b>. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @generated
     */
    protected void createTagValuesAnnotations() {
        String source = "TagValues";
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] {});
    }

} // StylePackageImpl
