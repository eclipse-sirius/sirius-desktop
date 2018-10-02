/**
 * Copyright (c) 2016 Obeo.
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
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Edit Support</b></em>'. <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getEditSupport()
 * @model
 * @generated
 */
public interface EditSupport extends EObject {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    Object getImage();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    String getText();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model featureRequired="true"
     * @generated
     */
    Object getText(EStructuralFeature feature);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    String getTabName();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model featureRequired="true"
     * @generated
     */
    EList<Object> getChoiceOfValues(EStructuralFeature feature);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model required="true" eStructuralFeatureRequired="true"
     * @generated
     */
    boolean isMultiline(EStructuralFeature eStructuralFeature);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model required="true" eStructuralFeatureRequired="true"
     * @generated
     */
    String getDescription(EStructuralFeature eStructuralFeature);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model kind="operation"
     * @generated
     */
    EList<EStructuralFeature> getEStructuralFeatures();

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model featureRequired="true" newValueRequired="true"
     * @generated
     */
    Object setValue(EStructuralFeature feature, Object newValue);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model eStructuralFeatureRequired="true"
     * @generated
     */
    boolean needsTextWidget(EStructuralFeature eStructuralFeature);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model eStructuralFeatureRequired="true"
     * @generated
     */
    boolean needsCheckboxWidget(EStructuralFeature eStructuralFeature);

} // EditSupport
