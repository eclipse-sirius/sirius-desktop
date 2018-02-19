/**
 * Copyright (c) 2007, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Generic Layout</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.GenericLayout#getID <em>ID</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.GenericLayout#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.GenericLayout#getLayoutOptions <em>Layout Options</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getGenericLayout()
 * @model
 * @generated
 */
public interface GenericLayout extends Layout {
    /**
     * Returns the value of the '<em><b>ID</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>ID</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>ID</em>' attribute.
     * @see #setID(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getGenericLayout_ID()
     * @model
     * @generated
     */
    String getID();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.GenericLayout#getID <em>ID</em>}' attribute.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>ID</em>' attribute.
     * @see #getID()
     * @generated
     */
    void setID(String value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getGenericLayout_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.GenericLayout#getLabel <em>Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Layout Options</b></em>' reference list. The list contents are of type
     * {@link org.eclipse.sirius.diagram.description.LayoutOption}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout Options</em>' reference list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layout Options</em>' reference list.
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getGenericLayout_LayoutOptions()
     * @model
     * @generated
     */
    EList<LayoutOption> getLayoutOptions();

} // GenericLayout
