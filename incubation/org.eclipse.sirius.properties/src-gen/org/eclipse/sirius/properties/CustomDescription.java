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
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Custom Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.properties.CustomDescription#getCustomExpressions
 * <em>Custom Expressions</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.properties.CustomDescription#getCustomOperations
 * <em>Custom Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getCustomDescription()
 * @model
 * @generated
 */
public interface CustomDescription extends WidgetDescription {
    /**
     * Returns the value of the '<em><b>Custom Expressions</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.CustomExpression}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Expressions</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Custom Expressions</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCustomDescription_CustomExpressions()
     * @model containment="true"
     * @generated
     */
    EList<CustomExpression> getCustomExpressions();

    /**
     * Returns the value of the '<em><b>Custom Operations</b></em>' containment
     * reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.CustomOperation}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Custom Operations</em>' containment reference
     * list isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Custom Operations</em>' containment
     *         reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getCustomDescription_CustomOperations()
     * @model containment="true"
     * @generated
     */
    EList<CustomOperation> getCustomOperations();

} // CustomDescription
