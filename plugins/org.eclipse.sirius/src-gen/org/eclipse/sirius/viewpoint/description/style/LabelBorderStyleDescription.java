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
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Label Border Style Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> The style of the border of a container label. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerHeight <em>Corner
 * Height</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerWidth <em>Corner
 * Width</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyleDescription()
 * @model
 * @generated
 */
public interface LabelBorderStyleDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyleDescription_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

    /**
     * Returns the value of the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Name</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Name</em>' attribute.
     * @see #setName(String)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyleDescription_Name()
     * @model
     * @generated
     */
    String getName();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getName
     * <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Name</em>' attribute.
     * @see #getName()
     * @generated
     */
    void setName(String value);

    /**
     * Returns the value of the '<em><b>Corner Height</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Corner Height</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Corner Height</em>' attribute.
     * @see #setCornerHeight(int)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyleDescription_CornerHeight()
     * @model
     * @generated
     */
    int getCornerHeight();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerHeight <em>Corner
     * Height</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Corner Height</em>' attribute.
     * @see #getCornerHeight()
     * @generated
     */
    void setCornerHeight(int value);

    /**
     * Returns the value of the '<em><b>Corner Width</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Corner Width</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Corner Width</em>' attribute.
     * @see #setCornerWidth(int)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getLabelBorderStyleDescription_CornerWidth()
     * @model
     * @generated
     */
    int getCornerWidth();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.viewpoint.description.style.LabelBorderStyleDescription#getCornerWidth <em>Corner
     * Width</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Corner Width</em>' attribute.
     * @see #getCornerWidth()
     * @generated
     */
    void setCornerWidth(int value);

} // LabelBorderStyleDescription
