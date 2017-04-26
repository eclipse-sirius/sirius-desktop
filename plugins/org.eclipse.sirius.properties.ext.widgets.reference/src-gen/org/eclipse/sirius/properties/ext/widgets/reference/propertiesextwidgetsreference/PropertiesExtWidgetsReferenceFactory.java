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

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc --> The <b>Factory</b> for the model. It provides a create method for each non-abstract class of
 * the model. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.PropertiesExtWidgetsReferencePackage
 * @generated
 */
public interface PropertiesExtWidgetsReferenceFactory extends EFactory {
    /**
     * The singleton instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    PropertiesExtWidgetsReferenceFactory eINSTANCE = org.eclipse.sirius.properties.ext.widgets.reference.propertiesextwidgetsreference.impl.PropertiesExtWidgetsReferenceFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Ext Reference Description</em>'. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return a new object of class '<em>Ext Reference Description</em>'.
     * @generated
     */
    ExtReferenceDescription createExtReferenceDescription();

    /**
     * Returns a new object of class '<em>Ext Reference Widget Style</em>'. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @return a new object of class '<em>Ext Reference Widget Style</em>'.
     * @generated
     */
    ExtReferenceWidgetStyle createExtReferenceWidgetStyle();

    /**
     * Returns a new object of class '<em>Ext Reference Widget Conditional Style</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Ext Reference Widget Conditional Style</em>'.
     * @generated
     */
    ExtReferenceWidgetConditionalStyle createExtReferenceWidgetConditionalStyle();

    /**
     * Returns a new object of class '<em>Ext Reference Override Description</em>'. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @return a new object of class '<em>Ext Reference Override Description</em>'.
     * @generated
     */
    ExtReferenceOverrideDescription createExtReferenceOverrideDescription();

    /**
     * Returns the package supported by this factory. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @return the package supported by this factory.
     * @generated
     */
    PropertiesExtWidgetsReferencePackage getPropertiesExtWidgetsReferencePackage();

} // PropertiesExtWidgetsReferenceFactory
