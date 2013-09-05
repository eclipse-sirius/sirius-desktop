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
package org.eclipse.sirius.description.style.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.description.DescriptionPackage;
import org.eclipse.sirius.description.audit.AuditPackage;
import org.eclipse.sirius.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.description.concern.ConcernPackage;
import org.eclipse.sirius.description.concern.impl.ConcernPackageImpl;
import org.eclipse.sirius.description.contribution.ContributionPackage;
import org.eclipse.sirius.description.filter.FilterPackage;
import org.eclipse.sirius.description.filter.impl.FilterPackageImpl;
import org.eclipse.sirius.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.description.style.BeginLabelStyleDescription;
import org.eclipse.sirius.description.style.BorderedStyleDescription;
import org.eclipse.sirius.description.style.BracketEdgeStyleDescription;
import org.eclipse.sirius.description.style.BundledImageDescription;
import org.eclipse.sirius.description.style.CenterLabelStyleDescription;
import org.eclipse.sirius.description.style.ContainerStyleDescription;
import org.eclipse.sirius.description.style.CustomStyleDescription;
import org.eclipse.sirius.description.style.DotDescription;
import org.eclipse.sirius.description.style.EdgeStyleDescription;
import org.eclipse.sirius.description.style.EllipseNodeDescription;
import org.eclipse.sirius.description.style.EndLabelStyleDescription;
import org.eclipse.sirius.description.style.FlatContainerStyleDescription;
import org.eclipse.sirius.description.style.GaugeCompositeStyleDescription;
import org.eclipse.sirius.description.style.GaugeSectionDescription;
import org.eclipse.sirius.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.description.style.LabelBorderStyles;
import org.eclipse.sirius.description.style.LabelStyleDescription;
import org.eclipse.sirius.description.style.LozengeNodeDescription;
import org.eclipse.sirius.description.style.NodeStyleDescription;
import org.eclipse.sirius.description.style.NoteDescription;
import org.eclipse.sirius.description.style.RoundedCornerStyleDescription;
import org.eclipse.sirius.description.style.ShapeContainerStyleDescription;
import org.eclipse.sirius.description.style.SquareDescription;
import org.eclipse.sirius.description.style.StyleDescription;
import org.eclipse.sirius.description.style.StyleFactory;
import org.eclipse.sirius.description.style.StylePackage;
import org.eclipse.sirius.description.style.TooltipStyleDescription;
import org.eclipse.sirius.description.style.WorkspaceImageDescription;
import org.eclipse.sirius.description.tool.ToolPackage;
import org.eclipse.sirius.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.description.validation.ValidationPackage;
import org.eclipse.sirius.description.validation.impl.ValidationPackageImpl;
import org.eclipse.sirius.impl.SiriusPackageImpl;

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
    public static final String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass styleDescriptionEClass = null;

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
    private EClass borderedStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass basicLabelStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelBorderStylesEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass labelBorderStyleDescriptionEClass = null;

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
    private EClass bracketEdgeStyleDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass tooltipStyleDescriptionEClass = null;

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
    private EClass ellipseNodeDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    private EClass lozengeNodeDescriptionEClass = null;

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
     * @see org.eclipse.sirius.description.style.StylePackage#eNS_URI
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
        ContributionPackage.eINSTANCE.eClass();

        // Obtain or create and register interdependencies
        SiriusPackageImpl theSiriusPackage = (SiriusPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(SiriusPackage.eNS_URI) instanceof SiriusPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(SiriusPackage.eNS_URI) : SiriusPackage.eINSTANCE);
        DescriptionPackageImpl theDescriptionPackage = (DescriptionPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI) instanceof DescriptionPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(DescriptionPackage.eNS_URI) : DescriptionPackage.eINSTANCE);
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        FilterPackageImpl theFilterPackage = (FilterPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(FilterPackage.eNS_URI) instanceof FilterPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(FilterPackage.eNS_URI) : FilterPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);
        ConcernPackageImpl theConcernPackage = (ConcernPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ConcernPackage.eNS_URI) instanceof ConcernPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ConcernPackage.eNS_URI) : ConcernPackage.eINSTANCE);

        // Create package meta-data objects
        theStylePackage.createPackageContents();
        theSiriusPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theFilterPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();
        theConcernPackage.createPackageContents();

        // Initialize created meta-data
        theStylePackage.initializePackageContents();
        theSiriusPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theFilterPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();
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
    public EClass getStyleDescription() {
        return styleDescriptionEClass;
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
    public EClass getBasicLabelStyleDescription() {
        return basicLabelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyleDescription_LabelSize() {
        return (EAttribute) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyleDescription_LabelFormat() {
        return (EAttribute) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyleDescription_ShowIcon() {
        return (EAttribute) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyleDescription_LabelExpression() {
        return (EAttribute) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getBasicLabelStyleDescription_LabelColor() {
        return (EReference) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getBasicLabelStyleDescription_IconPath() {
        return (EAttribute) basicLabelStyleDescriptionEClass.getEStructuralFeatures().get(5);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelStyleDescription() {
        return labelStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelStyleDescription_LabelAlignment() {
        return (EAttribute) labelStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelBorderStyles() {
        return labelBorderStylesEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EReference getLabelBorderStyles_LabelBorderStyleDescriptions() {
        return (EReference) labelBorderStylesEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getLabelBorderStyleDescription() {
        return labelBorderStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelBorderStyleDescription_Id() {
        return (EAttribute) labelBorderStyleDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelBorderStyleDescription_Name() {
        return (EAttribute) labelBorderStyleDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelBorderStyleDescription_CornerHeight() {
        return (EAttribute) labelBorderStyleDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getLabelBorderStyleDescription_CornerWidth() {
        return (EAttribute) labelBorderStyleDescriptionEClass.getEStructuralFeatures().get(3);
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
    public EReference getFlatContainerStyleDescription_BackgroundColor() {
        return (EReference) flatContainerStyleDescriptionEClass.getEStructuralFeatures().get(1);
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
    public EClass getBracketEdgeStyleDescription() {
        return bracketEdgeStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EClass getTooltipStyleDescription() {
        return tooltipStyleDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public EAttribute getTooltipStyleDescription_TooltipExpression() {
        return (EAttribute) tooltipStyleDescriptionEClass.getEStructuralFeatures().get(0);
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
        styleDescriptionEClass = createEClass(STYLE_DESCRIPTION);

        roundedCornerStyleDescriptionEClass = createEClass(ROUNDED_CORNER_STYLE_DESCRIPTION);
        createEAttribute(roundedCornerStyleDescriptionEClass, ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_WIDTH);
        createEAttribute(roundedCornerStyleDescriptionEClass, ROUNDED_CORNER_STYLE_DESCRIPTION__ARC_HEIGHT);

        borderedStyleDescriptionEClass = createEClass(BORDERED_STYLE_DESCRIPTION);
        createEAttribute(borderedStyleDescriptionEClass, BORDERED_STYLE_DESCRIPTION__BORDER_SIZE_COMPUTATION_EXPRESSION);
        createEReference(borderedStyleDescriptionEClass, BORDERED_STYLE_DESCRIPTION__BORDER_COLOR);

        basicLabelStyleDescriptionEClass = createEClass(BASIC_LABEL_STYLE_DESCRIPTION);
        createEAttribute(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__LABEL_SIZE);
        createEAttribute(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__LABEL_FORMAT);
        createEAttribute(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__SHOW_ICON);
        createEAttribute(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__LABEL_EXPRESSION);
        createEReference(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__LABEL_COLOR);
        createEAttribute(basicLabelStyleDescriptionEClass, BASIC_LABEL_STYLE_DESCRIPTION__ICON_PATH);

        labelStyleDescriptionEClass = createEClass(LABEL_STYLE_DESCRIPTION);
        createEAttribute(labelStyleDescriptionEClass, LABEL_STYLE_DESCRIPTION__LABEL_ALIGNMENT);

        labelBorderStylesEClass = createEClass(LABEL_BORDER_STYLES);
        createEReference(labelBorderStylesEClass, LABEL_BORDER_STYLES__LABEL_BORDER_STYLE_DESCRIPTIONS);

        labelBorderStyleDescriptionEClass = createEClass(LABEL_BORDER_STYLE_DESCRIPTION);
        createEAttribute(labelBorderStyleDescriptionEClass, LABEL_BORDER_STYLE_DESCRIPTION__ID);
        createEAttribute(labelBorderStyleDescriptionEClass, LABEL_BORDER_STYLE_DESCRIPTION__NAME);
        createEAttribute(labelBorderStyleDescriptionEClass, LABEL_BORDER_STYLE_DESCRIPTION__CORNER_HEIGHT);
        createEAttribute(labelBorderStyleDescriptionEClass, LABEL_BORDER_STYLE_DESCRIPTION__CORNER_WIDTH);

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

        tooltipStyleDescriptionEClass = createEClass(TOOLTIP_STYLE_DESCRIPTION);
        createEAttribute(tooltipStyleDescriptionEClass, TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION);

        gaugeSectionDescriptionEClass = createEClass(GAUGE_SECTION_DESCRIPTION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__MIN_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__MAX_VALUE_EXPRESSION);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__VALUE_EXPRESSION);
        createEReference(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__BACKGROUND_COLOR);
        createEReference(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__FOREGROUND_COLOR);
        createEAttribute(gaugeSectionDescriptionEClass, GAUGE_SECTION_DESCRIPTION__LABEL);

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
        EcorePackage theEcorePackage = (EcorePackage) EPackage.Registry.INSTANCE.getEPackage(EcorePackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);
        SiriusPackage theSiriusPackage = (SiriusPackage) EPackage.Registry.INSTANCE.getEPackage(SiriusPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        roundedCornerStyleDescriptionEClass.getESuperTypes().add(this.getStyleDescription());
        borderedStyleDescriptionEClass.getESuperTypes().add(this.getStyleDescription());
        labelStyleDescriptionEClass.getESuperTypes().add(this.getBasicLabelStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getBorderedStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getLabelStyleDescription());
        nodeStyleDescriptionEClass.getESuperTypes().add(this.getTooltipStyleDescription());
        customStyleDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        squareDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        lozengeNodeDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        ellipseNodeDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        bundledImageDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        noteDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        dotDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        gaugeCompositeStyleDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getRoundedCornerStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getBorderedStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getLabelStyleDescription());
        containerStyleDescriptionEClass.getESuperTypes().add(this.getTooltipStyleDescription());
        flatContainerStyleDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        shapeContainerStyleDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        workspaceImageDescriptionEClass.getESuperTypes().add(this.getNodeStyleDescription());
        workspaceImageDescriptionEClass.getESuperTypes().add(this.getContainerStyleDescription());
        edgeStyleDescriptionEClass.getESuperTypes().add(this.getStyleDescription());
        beginLabelStyleDescriptionEClass.getESuperTypes().add(this.getBasicLabelStyleDescription());
        centerLabelStyleDescriptionEClass.getESuperTypes().add(this.getBasicLabelStyleDescription());
        endLabelStyleDescriptionEClass.getESuperTypes().add(this.getBasicLabelStyleDescription());
        bracketEdgeStyleDescriptionEClass.getESuperTypes().add(this.getEdgeStyleDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(styleDescriptionEClass, StyleDescription.class, "StyleDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(roundedCornerStyleDescriptionEClass, RoundedCornerStyleDescription.class, "RoundedCornerStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getRoundedCornerStyleDescription_ArcWidth(), theEcorePackage.getEIntegerObject(), "arcWidth", "1", 0, 1, RoundedCornerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getRoundedCornerStyleDescription_ArcHeight(), theEcorePackage.getEIntegerObject(), "arcHeight", "1", 0, 1, RoundedCornerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(borderedStyleDescriptionEClass, BorderedStyleDescription.class, "BorderedStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBorderedStyleDescription_BorderSizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "borderSizeComputationExpression", "0", 0, 1,
                BorderedStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBorderedStyleDescription_BorderColor(), theDescriptionPackage.getColorDescription(), null, "borderColor", null, 1, 1, BorderedStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(basicLabelStyleDescriptionEClass, BasicLabelStyleDescription.class, "BasicLabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBasicLabelStyleDescription_LabelSize(), theEcorePackage.getEInt(), "labelSize", "8", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_LabelFormat(), theSiriusPackage.getFontFormat(), "labelFormat", "normal", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_ShowIcon(), ecorePackage.getEBoolean(), "showIcon", "true", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", "<%name%>", 0, 1, BasicLabelStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBasicLabelStyleDescription_LabelColor(), theDescriptionPackage.getColorDescription(), null, "labelColor", null, 1, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelStyleDescriptionEClass, LabelStyleDescription.class, "LabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelStyleDescription_LabelAlignment(), theSiriusPackage.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelBorderStylesEClass, LabelBorderStyles.class, "LabelBorderStyles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getLabelBorderStyles_LabelBorderStyleDescriptions(), this.getLabelBorderStyleDescription(), null, "labelBorderStyleDescriptions", null, 0, -1, LabelBorderStyles.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelBorderStyleDescriptionEClass, LabelBorderStyleDescription.class, "LabelBorderStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelBorderStyleDescription_Id(), theEcorePackage.getEString(), "id", null, 0, 1, LabelBorderStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelBorderStyleDescription_Name(), theEcorePackage.getEString(), "name", null, 0, 1, LabelBorderStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelBorderStyleDescription_CornerHeight(), theEcorePackage.getEInt(), "cornerHeight", null, 0, 1, LabelBorderStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getLabelBorderStyleDescription_CornerWidth(), theEcorePackage.getEInt(), "cornerWidth", null, 0, 1, LabelBorderStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(nodeStyleDescriptionEClass, NodeStyleDescription.class, "NodeStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getNodeStyleDescription_SizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "sizeComputationExpression", "3", 0, 1, NodeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_LabelPosition(), theSiriusPackage.getLabelPosition(), "labelPosition", "border", 0, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_HideLabelByDefault(), ecorePackage.getEBoolean(), "hideLabelByDefault", "false", 0, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getNodeStyleDescription_ResizeKind(), theSiriusPackage.getResizeKind(), "resizeKind", "NONE", 1, 1, NodeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
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
        initEAttribute(getBundledImageDescription_Shape(), theSiriusPackage.getBundledImageShape(), "shape", null, 1, 1, BundledImageDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
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
        initEAttribute(getGaugeCompositeStyleDescription_Alignment(), theSiriusPackage.getAlignmentKind(), "alignment", "SQUARE", 0, 1, GaugeCompositeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeCompositeStyleDescription_Sections(), this.getGaugeSectionDescription(), null, "sections", null, 0, -1, GaugeCompositeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(containerStyleDescriptionEClass, ContainerStyleDescription.class, "ContainerStyleDescription", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getContainerStyleDescription_RoundedCorner(), theEcorePackage.getEBoolean(), "roundedCorner", null, 0, 1, ContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(flatContainerStyleDescriptionEClass, FlatContainerStyleDescription.class, "FlatContainerStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getFlatContainerStyleDescription_BackgroundStyle(), theSiriusPackage.getBackgroundStyle(), "backgroundStyle", null, 1, 1, FlatContainerStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, FlatContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 1, 1, FlatContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getFlatContainerStyleDescription_LabelBorderStyle(), this.getLabelBorderStyleDescription(), null, "labelBorderStyle", null, 0, 1, FlatContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(shapeContainerStyleDescriptionEClass, ShapeContainerStyleDescription.class, "ShapeContainerStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getShapeContainerStyleDescription_Shape(), theSiriusPackage.getContainerShape(), "shape", null, 1, 1, ShapeContainerStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getShapeContainerStyleDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, ShapeContainerStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(workspaceImageDescriptionEClass, WorkspaceImageDescription.class, "WorkspaceImageDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getWorkspaceImageDescription_WorkspacePath(), theEcorePackage.getEString(), "workspacePath", null, 1, 1, WorkspaceImageDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(edgeStyleDescriptionEClass, EdgeStyleDescription.class, "EdgeStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEReference(getEdgeStyleDescription_StrokeColor(), theDescriptionPackage.getColorDescription(), null, "strokeColor", null, 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_LineStyle(), theSiriusPackage.getLineStyle(), "lineStyle", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SourceArrow(), theSiriusPackage.getEdgeArrows(), "sourceArrow", "NoDecoration", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_TargetArrow(), theSiriusPackage.getEdgeArrows(), "targetArrow", "InputArrow", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_SizeComputationExpression(), theDescriptionPackage.getInterpretedExpression(), "sizeComputationExpression", "<%eContents().nSize%>", 1, 1,
                EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_RoutingStyle(), theSiriusPackage.getEdgeRouting(), "routingStyle", "straight", 1, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getEdgeStyleDescription_FoldingStyle(), theDescriptionPackage.getFoldingStyle(), "foldingStyle", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_BeginLabelStyleDescription(), this.getBeginLabelStyleDescription(), null, "beginLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_CenterLabelStyleDescription(), this.getCenterLabelStyleDescription(), null, "centerLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getEdgeStyleDescription_EndLabelStyleDescription(), this.getEndLabelStyleDescription(), null, "endLabelStyleDescription", null, 0, 1, EdgeStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(tooltipStyleDescriptionEClass, TooltipStyleDescription.class, "TooltipStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTooltipStyleDescription_TooltipExpression(), theDescriptionPackage.getInterpretedExpression(), "tooltipExpression", "", 0, 1, TooltipStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(gaugeSectionDescriptionEClass, GaugeSectionDescription.class, "GaugeSectionDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getGaugeSectionDescription_MinValueExpression(), theDescriptionPackage.getInterpretedExpression(), "minValueExpression", "<%0%>", 0, 1, GaugeSectionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_MaxValueExpression(), theDescriptionPackage.getInterpretedExpression(), "maxValueExpression", "<%0%>", 0, 1, GaugeSectionDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_ValueExpression(), theDescriptionPackage.getInterpretedExpression(), "valueExpression", "<%0%>", 0, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSectionDescription_BackgroundColor(), theDescriptionPackage.getColorDescription(), null, "backgroundColor", null, 1, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getGaugeSectionDescription_ForegroundColor(), theDescriptionPackage.getColorDescription(), null, "foregroundColor", null, 1, 1, GaugeSectionDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getGaugeSectionDescription_Label(), theEcorePackage.getEString(), "label", null, 0, 1, GaugeSectionDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
        addAnnotation(getBasicLabelStyleDescription_LabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getNodeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getLozengeNodeDescription_WidthComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getLozengeNodeDescription_HeightComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEllipseNodeDescription_VerticalDiameterComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getTooltipStyleDescription_TooltipExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getGaugeSectionDescription_MinValueExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getGaugeSectionDescription_MaxValueExpression(), source, new String[] { "returnType", "an integer." });
        addAnnotation(getGaugeSectionDescription_ValueExpression(), source, new String[] { "returnType", "an integer." });
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
        addAnnotation(getBasicLabelStyleDescription_LabelExpression(), source, new String[] { "diagram", "viewpoint.DDiagram | the current DSemanticDiagram.", "view",
                "viewpoint.DDiagramElement | the current view for which the label is calculated." });
        addAnnotation(getNodeStyleDescription_SizeComputationExpression(), source, new String[] {});
        addAnnotation(getLozengeNodeDescription_WidthComputationExpression(), source, new String[] {});
        addAnnotation(getLozengeNodeDescription_HeightComputationExpression(), source, new String[] {});
        addAnnotation(getEllipseNodeDescription_HorizontalDiameterComputationExpression(), source, new String[] {});
        addAnnotation(getEllipseNodeDescription_VerticalDiameterComputationExpression(), source, new String[] {});
        addAnnotation(getDotDescription_StrokeSizeComputationExpression(), source, new String[] {});
        addAnnotation(getEdgeStyleDescription_SizeComputationExpression(), source, new String[] {});
        addAnnotation(getTooltipStyleDescription_TooltipExpression(), source, new String[] { "view", "viewpoint.DSemanticDecorator | the current view." });
        addAnnotation(getGaugeSectionDescription_MinValueExpression(), source, new String[] {});
        addAnnotation(getGaugeSectionDescription_MaxValueExpression(), source, new String[] {});
        addAnnotation(getGaugeSectionDescription_ValueExpression(), source, new String[] {});
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
