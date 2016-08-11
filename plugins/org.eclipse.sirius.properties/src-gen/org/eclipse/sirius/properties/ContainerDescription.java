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
 * <em><b>Container Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.ContainerDescription#getControls
 * <em>Controls</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.ContainerDescription#getLayout
 * <em>Layout</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription()
 * @model
 * @generated
 */
public interface ContainerDescription extends ControlDescription {
    /**
     * Returns the value of the '<em><b>Controls</b></em>' containment reference
     * list. The list contents are of type
     * {@link org.eclipse.sirius.properties.ControlDescription}. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controls</em>' containment reference list
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controls</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription_Controls()
     * @model containment="true"
     * @generated
     */
    EList<ControlDescription> getControls();

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(LayoutDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getContainerDescription_Layout()
     * @model containment="true"
     * @generated
     */
    LayoutDescription getLayout();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.properties.ContainerDescription#getLayout
     * <em>Layout</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(LayoutDescription value);

} // ContainerDescription
