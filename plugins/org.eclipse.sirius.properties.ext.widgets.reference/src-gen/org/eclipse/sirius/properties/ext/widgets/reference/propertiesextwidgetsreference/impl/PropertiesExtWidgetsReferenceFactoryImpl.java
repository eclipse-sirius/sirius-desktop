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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetConditionalStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceWidgetStyle;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory;
import org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 *
 * @generated
 */
public class PropertiesExtWidgetsReferenceFactoryImpl extends EFactoryImpl implements PropertiesExtWidgetsReferenceFactory {
    /**
     * Creates the default factory implementation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static PropertiesExtWidgetsReferenceFactory init() {
        try {
            PropertiesExtWidgetsReferenceFactory thePropertiesExtWidgetsReferenceFactory = (PropertiesExtWidgetsReferenceFactory) EPackage.Registry.INSTANCE
                    .getEFactory(PropertiesExtWidgetsReferencePackage.eNS_URI);
            if (thePropertiesExtWidgetsReferenceFactory != null) {
                return thePropertiesExtWidgetsReferenceFactory;
            }
        } catch (Exception exception) {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new PropertiesExtWidgetsReferenceFactoryImpl();
    }

    /**
     * Creates an instance of the factory. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public PropertiesExtWidgetsReferenceFactoryImpl() {
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
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_DESCRIPTION:
            return createExtReferenceDescription();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_STYLE:
            return createExtReferenceWidgetStyle();
        case PropertiesExtWidgetsReferencePackage.EXT_REFERENCE_WIDGET_CONDITIONAL_STYLE:
            return createExtReferenceWidgetConditionalStyle();
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
    public ExtReferenceDescription createExtReferenceDescription() {
        ExtReferenceDescriptionImpl extReferenceDescription = new ExtReferenceDescriptionImpl();
        return extReferenceDescription;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExtReferenceWidgetStyle createExtReferenceWidgetStyle() {
        ExtReferenceWidgetStyleImpl extReferenceWidgetStyle = new ExtReferenceWidgetStyleImpl();
        return extReferenceWidgetStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public ExtReferenceWidgetConditionalStyle createExtReferenceWidgetConditionalStyle() {
        ExtReferenceWidgetConditionalStyleImpl extReferenceWidgetConditionalStyle = new ExtReferenceWidgetConditionalStyleImpl();
        return extReferenceWidgetConditionalStyle;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public PropertiesExtWidgetsReferencePackage getPropertiesExtWidgetsReferencePackage() {
        return (PropertiesExtWidgetsReferencePackage) getEPackage();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @deprecated
     * @generated
     */
    @Deprecated
    public static PropertiesExtWidgetsReferencePackage getPackage() {
        return PropertiesExtWidgetsReferencePackage.eINSTANCE;
    }

} // PropertiesExtWidgetsReferenceFactoryImpl
