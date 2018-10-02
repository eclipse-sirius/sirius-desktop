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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Conditional Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription#getPredicateExpression <em>Predicate
 * Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getConditionalStyleDescription()
 * @model abstract="true"
 * @generated
 */
public interface ConditionalStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Predicate Expression</b></em>' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> This expression will get evaluated and if it returns true the contained
     * style will be choosen. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Predicate Expression</em>' attribute.
     * @see #setPredicateExpression(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getConditionalStyleDescription_PredicateExpression()
     * @model dataType= "org.eclipse.sirius.viewpoint.description.InterpretedExpression" required="true" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='a boolean.'" annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/variables view='ecore.EObject | the current view.'
     *        container='ecore.EObject | the semantic container.'"
     * @generated
     */
    String getPredicateExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription#getPredicateExpression <em>Predicate
     * Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Predicate Expression</em>' attribute.
     * @see #getPredicateExpression()
     * @generated
     */
    void setPredicateExpression(String value);

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @model
     * @generated
     */
    @Deprecated
    boolean checkPredicate(EObject modelElement, EObject viewVariable, EObject containerVariable);

} // ConditionalStyleDescription
