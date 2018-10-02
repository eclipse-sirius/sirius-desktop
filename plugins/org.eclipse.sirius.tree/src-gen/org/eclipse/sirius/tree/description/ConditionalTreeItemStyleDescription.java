/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.description;

import org.eclipse.sirius.viewpoint.description.ConditionalStyleDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Conditional Tree Item Style
 * Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription#getStyle <em>Style</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.tree.description.DescriptionPackage#getConditionalTreeItemStyleDescription()
 * @model
 * @generated
 */
public interface ConditionalTreeItemStyleDescription extends ConditionalStyleDescription {

    /**
     * Returns the value of the '<em><b>Style</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Style</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Style</em>' containment reference.
     * @see #setStyle(TreeItemStyleDescription)
     * @see org.eclipse.sirius.tree.description.DescriptionPackage#getConditionalTreeItemStyleDescription_Style()
     * @model containment="true"
     * @generated
     */
    TreeItemStyleDescription getStyle();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.tree.description.ConditionalTreeItemStyleDescription#getStyle
     * <em>Style</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Style</em>' containment reference.
     * @see #getStyle()
     * @generated
     */
    void setStyle(TreeItemStyleDescription value);
} // ConditionalTreeItemStyleDescription
