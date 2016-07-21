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
package org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.sirius.properties.PropertiesPackage;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each operation of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferenceFactory
 * @model kind="package"
 * @generated
 */
public interface PropertiesExtWidgetsReferencePackage extends EPackage {
    /**
     * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNAME = "propertiesextwidgetsreference"; //$NON-NLS-1$

    /**
     * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_URI = "http://www.eclipse.org/sirius/properties/1.0.0/ext/widgets/reference"; //$NON-NLS-1$

    /**
     * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    String eNS_PREFIX = "properties-ext-widgets-reference"; //$NON-NLS-1$

    /**
     * The singleton instance of the package. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    PropertiesExtWidgetsReferencePackage eINSTANCE = org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl.init();

    /**
     * The meta object id for the '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
     * @generated
     */
    int EXT_REFERENCE_DESCRIPTION = 0;

    /**
     * The feature id for the '<em><b>Identifier</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__IDENTIFIER = PropertiesPackage.WIDGET_DESCRIPTION__IDENTIFIER;

    /**
     * The feature id for the '<em><b>Label Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__LABEL_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__LABEL_EXPRESSION;

    /**
     * The feature id for the '<em><b>Help Expression</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__HELP_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__HELP_EXPRESSION;

    /**
     * The feature id for the '<em><b>Is Enabled Expression</b></em>' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__IS_ENABLED_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION__IS_ENABLED_EXPRESSION;

    /**
     * The feature id for the '<em><b>Reference Name Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 0;

    /**
     * The feature id for the '<em><b>Reference Owner Expression</b></em>'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 1;

    /**
     * The number of structural features of the '
     * <em>Ext Reference Description</em>' class. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     * @ordered
     */
    int EXT_REFERENCE_DESCRIPTION_FEATURE_COUNT = PropertiesPackage.WIDGET_DESCRIPTION_FEATURE_COUNT + 2;

    /**
     * Returns the meta object for class '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * <em>Ext Reference Description</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for class '<em>Ext Reference Description</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription
     * @generated
     */
    EClass getExtReferenceDescription();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression
     * <em>Reference Name Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Reference Name Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceNameExpression()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EAttribute getExtReferenceDescription_ReferenceNameExpression();

    /**
     * Returns the meta object for the attribute '
     * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression
     * <em>Reference Owner Expression</em>}'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return the meta object for the attribute '
     *         <em>Reference Owner Expression</em>'.
     * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.ExtReferenceDescription#getReferenceOwnerExpression()
     * @see #getExtReferenceDescription()
     * @generated
     */
    EAttribute getExtReferenceDescription_ReferenceOwnerExpression();

    /**
     * Returns the factory that creates the instances of the model. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the factory that creates the instances of the model.
     * @generated
     */
    PropertiesExtWidgetsReferenceFactory getPropertiesExtWidgetsReferenceFactory();

    /**
     * <!-- begin-user-doc --> Defines literals for the meta objects that
     * represent
     * <ul>
     * <li>each class,</li>
     * <li>each feature of each class,</li>
     * <li>each operation of each class,</li>
     * <li>each enum,</li>
     * <li>and each data type</li>
     * </ul>
     * <!-- end-user-doc -->
     *
     * @generated
     */
    interface Literals {
        /**
         * The meta object literal for the '
         * {@link org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * <em>Ext Reference Description</em>}' class. <!-- begin-user-doc -->
         * <!-- end-user-doc -->
         *
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.ExtReferenceDescriptionImpl
         * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferencePackageImpl#getExtReferenceDescription()
         * @generated
         */
        EClass EXT_REFERENCE_DESCRIPTION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription();

        /**
         * The meta object literal for the '
         * <em><b>Reference Name Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXT_REFERENCE_DESCRIPTION__REFERENCE_NAME_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_ReferenceNameExpression();

        /**
         * The meta object literal for the '
         * <em><b>Reference Owner Expression</b></em>' attribute feature. <!--
         * begin-user-doc --> <!-- end-user-doc -->
         *
         * @generated
         */
        EAttribute EXT_REFERENCE_DESCRIPTION__REFERENCE_OWNER_EXPRESSION = PropertiesExtWidgetsReferencePackage.eINSTANCE.getExtReferenceDescription_ReferenceOwnerExpression();

    }

} // PropertiesExtWidgetsReferencePackage
