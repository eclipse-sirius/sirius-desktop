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
package org.eclipse.sirius.viewpoint.description.style.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.audit.AuditPackage;
import org.eclipse.sirius.viewpoint.description.audit.impl.AuditPackageImpl;
import org.eclipse.sirius.viewpoint.description.impl.DescriptionPackageImpl;
import org.eclipse.sirius.viewpoint.description.style.BasicLabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.LabelBorderStyles;
import org.eclipse.sirius.viewpoint.description.style.LabelStyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleDescription;
import org.eclipse.sirius.viewpoint.description.style.StyleFactory;
import org.eclipse.sirius.viewpoint.description.style.StylePackage;
import org.eclipse.sirius.viewpoint.description.style.TooltipStyleDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.sirius.viewpoint.description.tool.impl.ToolPackageImpl;
import org.eclipse.sirius.viewpoint.description.validation.ValidationPackage;
import org.eclipse.sirius.viewpoint.description.validation.impl.ValidationPackageImpl;
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
    private EClass styleDescriptionEClass = null;

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
    private EClass tooltipStyleDescriptionEClass = null;

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
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#eNS_URI
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
        ToolPackageImpl theToolPackage = (ToolPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ToolPackage.eNS_URI) instanceof ToolPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ToolPackage.eNS_URI) : ToolPackage.eINSTANCE);
        ValidationPackageImpl theValidationPackage = (ValidationPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(ValidationPackage.eNS_URI) instanceof ValidationPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(ValidationPackage.eNS_URI) : ValidationPackage.eINSTANCE);
        AuditPackageImpl theAuditPackage = (AuditPackageImpl) (EPackage.Registry.INSTANCE.getEPackage(AuditPackage.eNS_URI) instanceof AuditPackageImpl ? EPackage.Registry.INSTANCE
                .getEPackage(AuditPackage.eNS_URI) : AuditPackage.eINSTANCE);

        // Create package meta-data objects
        theStylePackage.createPackageContents();
        theViewpointPackage.createPackageContents();
        theDescriptionPackage.createPackageContents();
        theToolPackage.createPackageContents();
        theValidationPackage.createPackageContents();
        theAuditPackage.createPackageContents();

        // Initialize created meta-data
        theStylePackage.initializePackageContents();
        theViewpointPackage.initializePackageContents();
        theDescriptionPackage.initializePackageContents();
        theToolPackage.initializePackageContents();
        theValidationPackage.initializePackageContents();
        theAuditPackage.initializePackageContents();

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

        tooltipStyleDescriptionEClass = createEClass(TOOLTIP_STYLE_DESCRIPTION);
        createEAttribute(tooltipStyleDescriptionEClass, TOOLTIP_STYLE_DESCRIPTION__TOOLTIP_EXPRESSION);
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
        ViewpointPackage theViewpointPackage = (ViewpointPackage) EPackage.Registry.INSTANCE.getEPackage(ViewpointPackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        labelStyleDescriptionEClass.getESuperTypes().add(this.getBasicLabelStyleDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(styleDescriptionEClass, StyleDescription.class, "StyleDescription", IS_ABSTRACT, IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

        initEClass(basicLabelStyleDescriptionEClass, BasicLabelStyleDescription.class, "BasicLabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getBasicLabelStyleDescription_LabelSize(), theEcorePackage.getEInt(), "labelSize", "8", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_LabelFormat(), theViewpointPackage.getFontFormat(), "labelFormat", "normal", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
                IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_ShowIcon(), ecorePackage.getEBoolean(), "showIcon", "true", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_LabelExpression(), theDescriptionPackage.getInterpretedExpression(), "labelExpression", "feature:name", 0, 1, BasicLabelStyleDescription.class,
                !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEReference(getBasicLabelStyleDescription_LabelColor(), theDescriptionPackage.getColorDescription(), null, "labelColor", null, 1, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
        initEAttribute(getBasicLabelStyleDescription_IconPath(), theEcorePackage.getEString(), "iconPath", "", 0, 1, BasicLabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
                !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        initEClass(labelStyleDescriptionEClass, LabelStyleDescription.class, "LabelStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getLabelStyleDescription_LabelAlignment(), theViewpointPackage.getLabelAlignment(), "labelAlignment", null, 0, 1, LabelStyleDescription.class, !IS_TRANSIENT, !IS_VOLATILE,
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

        initEClass(tooltipStyleDescriptionEClass, TooltipStyleDescription.class, "TooltipStyleDescription", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getTooltipStyleDescription_TooltipExpression(), theDescriptionPackage.getInterpretedExpression(), "tooltipExpression", "", 0, 1, TooltipStyleDescription.class, !IS_TRANSIENT,
                !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

        // Create annotations
        // http://www.eclipse.org/sirius/interpreted/expression/returnType
        createReturnTypeAnnotations();
        // http://www.eclipse.org/sirius/interpreted/expression/variables
        createVariablesAnnotations();
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
        addAnnotation(getBasicLabelStyleDescription_LabelExpression(), source, new String[] { "returnType", "a string." });
        addAnnotation(getTooltipStyleDescription_TooltipExpression(), source, new String[] { "returnType", "a string." });
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
        addAnnotation(getBasicLabelStyleDescription_LabelExpression(), source, new String[] { "diagram", "diagram.DDiagram | the current DSemanticDiagram.", "view",
                "diagram.DDiagramElement | the current view for which the label is calculated." });
        addAnnotation(getTooltipStyleDescription_TooltipExpression(), source, new String[] { "view", "viewpoint.DSemanticDecorator | the current view." });
    }

} // StylePackageImpl
