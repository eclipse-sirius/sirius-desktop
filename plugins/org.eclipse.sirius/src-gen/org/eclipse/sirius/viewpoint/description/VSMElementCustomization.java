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
package org.eclipse.sirius.viewpoint.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>VSM Element Customization</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getPredicateExpression <em>Predicate
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getFeatureCustomizations <em>Feature
 * Customizations</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomization()
 * @model
 * @generated
 */
public interface VSMElementCustomization extends IVSMElementCustomization {
    /**
     * Returns the value of the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Predicate Expression</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Predicate Expression</em>' attribute.
     * @see #setPredicateExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomization_PredicateExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean result.
     *        True to enable the customization, false to disabled it. True by default.'"
     *        annotation="http://www.eclipse.org/sirius/interpreted/expression/variables view='ecore.EObject | the
     *        current view.' container='ecore.EObject | the semantic container.'"
     * @generated
     */
    String getPredicateExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.VSMElementCustomization#getPredicateExpression <em>Predicate
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predicate Expression</em>' attribute.
     * @see #getPredicateExpression()
     * @generated
     */
    void setPredicateExpression(String value);

    /**
     * Returns the value of the '<em><b>Feature Customizations</b></em>' containment reference list. The list contents
     * are of type {@link org.eclipse.sirius.viewpoint.description.EStructuralFeatureCustomization}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Feature Customizations</em>' containment reference list isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Feature Customizations</em>' containment reference list.
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getVSMElementCustomization_FeatureCustomizations()
     * @model containment="true" resolveProxies="true" required="true"
     * @generated
     */
    EList<EStructuralFeatureCustomization> getFeatureCustomizations();

} // VSMElementCustomization
