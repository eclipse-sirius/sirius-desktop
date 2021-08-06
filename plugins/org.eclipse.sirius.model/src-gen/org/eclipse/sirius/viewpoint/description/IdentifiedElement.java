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
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Identified Element</b></em>'. <!-- end-user-doc
 * -->
 *
 * <!-- begin-model-doc --> All elements which are identified by its name (id) and which have a label to display it to
 * the end-user. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getLabel <em>Label</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getIdentifiedElement()
 * @model
 * @generated
 */
public interface IdentifiedElement extends EObject {
    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. The default value is <code>""</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The identifier of this element. Must be unique.
     * Changing this identifier will break existing user models which reference the old identifier. <!-- end-model-doc
     * -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getIdentifiedElement_Name()
     * @model default="" required="true"
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getName <em>Name</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The label used to display this viewpoint to the end-user. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getIdentifiedElement_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.IdentifiedElement#getLabel
     * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

} // IdentifiedElement
