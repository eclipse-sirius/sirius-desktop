/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.sirius.properties.PropertiesPackage;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.AbstractExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceOverrideDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 *
 * @generated
 */
public class PropertiesExtWidgetsReferencePackageImpl extends EPackageImpl implements PropertiesExtWidgetsReferencePackage {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass abstractExtReferenceDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass extReferenceDescriptionEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass extReferenceWidgetStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass extReferenceWidgetConditionalStyleEClass = null;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private EClass extReferenceOverrideDescriptionEClass = null;

    /**
     * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
     * EPackage.Registry} by the package package URI value.
     * <p>
     * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
     * performs initialization of the package, or returns the registered package, if one already exists. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see org.eclipse.emf.ecore.EPackage.Registry
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage#eNS_URI
     * @see #init()
     * @generated
     */
    private PropertiesExtWidgetsReferencePackageImpl() {
        super(PropertiesExtWidgetsReferencePackage.eNS_URI, PropertiesExtWidgetsReferenceFactory.eINSTANCE);
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
     * This method is used to initialize {@link PropertiesExtWidgetsReferencePackage#eINSTANCE} when that field is
     * accessed. Clients should not invoke it directly. Instead, they should simply access that field to obtain the
     * package. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #eNS_URI
     * @see #createPackageContents()
     * @see #initializePackageContents()
     * @generated
     */
    public static PropertiesExtWidgetsReferencePackage init() {
        if (PropertiesExtWidgetsReferencePackageImpl.isInited) {
            return (PropertiesExtWidgetsReferencePackage) EPackage.Registry.INSTANCE.getEPackage(PropertiesExtWidgetsReferencePackage.eNS_URI);
        }

        // Obtain or create and register package
        PropertiesExtWidgetsReferencePackageImpl thePropertiesExtWidgetsReferencePackage = (PropertiesExtWidgetsReferencePackageImpl) (EPackage.Registry.INSTANCE
                .get(PropertiesExtWidgetsReferencePackage.eNS_URI) instanceof PropertiesExtWidgetsReferencePackageImpl ? EPackage.Registry.INSTANCE.get(PropertiesExtWidgetsReferencePackage.eNS_URI)
                        : new PropertiesExtWidgetsReferencePackageImpl());

        PropertiesExtWidgetsReferencePackageImpl.isInited = true;

        // Initialize simple dependencies
        PropertiesPackage.eINSTANCE.eClass();

        // Create package meta-data objects
        thePropertiesExtWidgetsReferencePackage.createPackageContents();

        // Initialize created meta-data
        thePropertiesExtWidgetsReferencePackage.initializePackageContents();

        // Mark meta-data to indicate it can't be changed
        thePropertiesExtWidgetsReferencePackage.freeze();

        // Update the registry and return the package
        EPackage.Registry.INSTANCE.put(PropertiesExtWidgetsReferencePackage.eNS_URI, thePropertiesExtWidgetsReferencePackage);
        return thePropertiesExtWidgetsReferencePackage;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getAbstractExtReferenceDescription() {
        return abstractExtReferenceDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractExtReferenceDescription_ReferenceNameExpression() {
        return (EAttribute) abstractExtReferenceDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getAbstractExtReferenceDescription_ReferenceOwnerExpression() {
        return (EAttribute) abstractExtReferenceDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractExtReferenceDescription_Style() {
        return (EReference) abstractExtReferenceDescriptionEClass.getEStructuralFeatures().get(2);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractExtReferenceDescription_ConditionalStyles() {
        return (EReference) abstractExtReferenceDescriptionEClass.getEStructuralFeatures().get(3);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getAbstractExtReferenceDescription_Extends() {
        return (EReference) abstractExtReferenceDescriptionEClass.getEStructuralFeatures().get(4);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExtReferenceDescription() {
        return extReferenceDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExtReferenceWidgetStyle() {
        return extReferenceWidgetStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExtReferenceWidgetConditionalStyle() {
        return extReferenceWidgetConditionalStyleEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExtReferenceWidgetConditionalStyle_Style() {
        return (EReference) extReferenceWidgetConditionalStyleEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EClass getExtReferenceOverrideDescription() {
        return extReferenceOverrideDescriptionEClass;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EReference getExtReferenceOverrideDescription_Overrides() {
        return (EReference) extReferenceOverrideDescriptionEClass.getEStructuralFeatures().get(0);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public EAttribute getExtReferenceOverrideDescription_FilterConditionalStylesFromOverriddenExtReferenceExpression() {
        return (EAttribute) extReferenceOverrideDescriptionEClass.getEStructuralFeatures().get(1);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertiesExtWidgetsReferenceFactory getPropertiesExtWidgetsReferenceFactory() {
        return (PropertiesExtWidgetsReferenceFactory) getEFactoryInstance();
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
        abstractExtReferenceDescriptionEClass = createEClass(PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION);
        createEAttribute(abstractExtReferenceDescriptionEClass, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION);
        createEAttribute(abstractExtReferenceDescriptionEClass, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION);
        createEReference(abstractExtReferenceDescriptionEClass, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__STYLE);
        createEReference(abstractExtReferenceDescriptionEClass, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__CONDITIONAL_STYLES);
        createEReference(abstractExtReferenceDescriptionEClass, PropertiesExtWidgetsReferencePackage.ABSTRACT_EXT_REFERENCE_DESCRIPTION__EXTENDS);

        extReferenceDescriptionEClass = createEClass(PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION);

        extReferenceWidgetStyleEClass = createEClass(PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_STYLE);

        extReferenceWidgetConditionalStyleEClass = createEClass(PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE);
        createEReference(extReferenceWidgetConditionalStyleEClass, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE__STYLE);

        extReferenceOverrideDescriptionEClass = createEClass(PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION);
        createEReference(extReferenceOverrideDescriptionEClass, PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__OVERRIDES);
        createEAttribute(extReferenceOverrideDescriptionEClass,
                PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_OVERRIDE_DESCRIPTION__FILTER_CONDITIONAL_STYLES_FROM_OVERRIDDEN_EXT_REFERENCE_EXPRESSION);
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
        setName(PropertiesExtWidgetsReferencePackage.eNAME);
        setNsPrefix(PropertiesExtWidgetsReferencePackage.eNS_PREFIX);
        setNsURI(PropertiesExtWidgetsReferencePackage.eNS_URI);

        // Obtain other dependent packages
        PropertiesPackage thePropertiesPackage = (PropertiesPackage) EPackage.Registry.INSTANCE.getEPackage(PropertiesPackage.eNS_URI);
        DescriptionPackage theDescriptionPackage = (DescriptionPackage) EPackage.Registry.INSTANCE.getEPackage(DescriptionPackage.eNS_URI);

        // Create type parameters

        // Set bounds for type parameters

        // Add supertypes to classes
        abstractExtReferenceDescriptionEClass.getESuperTypes().add(thePropertiesPackage.getAbstractWidgetDescription());
        extReferenceDescriptionEClass.getESuperTypes().add(this.getAbstractExtReferenceDescription());
        extReferenceDescriptionEClass.getESuperTypes().add(thePropertiesPackage.getWidgetDescription());
        extReferenceWidgetStyleEClass.getESuperTypes().add(thePropertiesPackage.getWidgetStyle());
        extReferenceWidgetConditionalStyleEClass.getESuperTypes().add(thePropertiesPackage.getWidgetConditionalStyle());
        extReferenceOverrideDescriptionEClass.getESuperTypes().add(this.getAbstractExtReferenceDescription());
        extReferenceOverrideDescriptionEClass.getESuperTypes().add(thePropertiesPackage.getAbstractOverrideDescription());

        // Initialize classes and features; add operations and parameters
        initEClass(abstractExtReferenceDescriptionEClass, AbstractExtReferenceDescription.class, "AbstractExtReferenceDescription", EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEAttribute(getAbstractExtReferenceDescription_ReferenceNameExpression(), theDescriptionPackage.getInterpretedExpression(), "referenceNameExpression", null, 1, 1, //$NON-NLS-1$
                AbstractExtReferenceDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getAbstractExtReferenceDescription_ReferenceOwnerExpression(), theDescriptionPackage.getInterpretedExpression(), "referenceOwnerExpression", null, 0, 1, //$NON-NLS-1$
                AbstractExtReferenceDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractExtReferenceDescription_Style(), this.getExtReferenceWidgetStyle(), null, "style", null, 0, 1, AbstractExtReferenceDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractExtReferenceDescription_ConditionalStyles(), this.getExtReferenceWidgetConditionalStyle(), null, "conditionalStyles", null, 0, -1, //$NON-NLS-1$
                AbstractExtReferenceDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES,
                !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEReference(getAbstractExtReferenceDescription_Extends(), this.getExtReferenceDescription(), null, "extends", null, 0, 1, AbstractExtReferenceDescription.class, !EPackageImpl.IS_TRANSIENT, //$NON-NLS-1$
                !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE, EPackageImpl.IS_UNIQUE,
                !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(extReferenceDescriptionEClass, ExtReferenceDescription.class, "ExtReferenceDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(extReferenceWidgetStyleEClass, ExtReferenceWidgetStyle.class, "ExtReferenceWidgetStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);

        initEClass(extReferenceWidgetConditionalStyleEClass, ExtReferenceWidgetConditionalStyle.class, "ExtReferenceWidgetConditionalStyle", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExtReferenceWidgetConditionalStyle_Style(), this.getExtReferenceWidgetStyle(), null, "style", null, 0, 1, ExtReferenceWidgetConditionalStyle.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, EPackageImpl.IS_COMPOSITE, !EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        initEClass(extReferenceOverrideDescriptionEClass, ExtReferenceOverrideDescription.class, "ExtReferenceOverrideDescription", !EPackageImpl.IS_ABSTRACT, !EPackageImpl.IS_INTERFACE, //$NON-NLS-1$
                EPackageImpl.IS_GENERATED_INSTANCE_CLASS);
        initEReference(getExtReferenceOverrideDescription_Overrides(), this.getExtReferenceDescription(), null, "overrides", null, 0, 1, ExtReferenceOverrideDescription.class, //$NON-NLS-1$
                !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_COMPOSITE, EPackageImpl.IS_RESOLVE_PROXIES, !EPackageImpl.IS_UNSETTABLE,
                EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);
        initEAttribute(getExtReferenceOverrideDescription_FilterConditionalStylesFromOverriddenExtReferenceExpression(), theDescriptionPackage.getInterpretedExpression(),
                "filterConditionalStylesFromOverriddenExtReferenceExpression", null, 0, 1, ExtReferenceOverrideDescription.class, !EPackageImpl.IS_TRANSIENT, !EPackageImpl.IS_VOLATILE, //$NON-NLS-1$
                EPackageImpl.IS_CHANGEABLE, !EPackageImpl.IS_UNSETTABLE, !EPackageImpl.IS_ID, EPackageImpl.IS_UNIQUE, !EPackageImpl.IS_DERIVED, EPackageImpl.IS_ORDERED);

        // Create resource
        createResource(PropertiesExtWidgetsReferencePackage.eNS_URI);
    }

} // PropertiesExtWidgetsReferencePackageImpl
