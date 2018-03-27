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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Abstract Container Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.AbstractContainerDescription#getControls <em>Controls</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractContainerDescription#getLayout <em>Layout</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractContainerDescription#getExtends <em>Extends</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.AbstractContainerDescription#getFilterControlsFromExtendedContainerExpression
 * <em>Filter Controls From Extended Container Expression</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractContainerDescription()
 * @model abstract="true"
 * @generated
 */
public interface AbstractContainerDescription extends AbstractControlDescription {
    /**
     * Returns the value of the '<em><b>Controls</b></em>' containment reference list. The list contents are of type
     * {@link org.eclipse.sirius.properties.ControlDescription}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Controls</em>' containment reference list isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Controls</em>' containment reference list.
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractContainerDescription_Controls()
     * @model containment="true"
     * @generated
     */
    EList<ControlDescription> getControls();

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(LayoutDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractContainerDescription_Layout()
     * @model containment="true"
     * @generated
     */
    LayoutDescription getLayout();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractContainerDescription#getLayout
     * <em>Layout</em>}' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(LayoutDescription value);

    /**
     * Returns the value of the '<em><b>Extends</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Extends</em>' reference isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Extends</em>' reference.
     * @see #setExtends(ContainerDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractContainerDescription_Extends()
     * @model keys="name"
     * @generated
     */
    ContainerDescription getExtends();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.AbstractContainerDescription#getExtends
     * <em>Extends</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Extends</em>' reference.
     * @see #getExtends()
     * @generated
     */
    void setExtends(ContainerDescription value);

    /**
     * Returns the value of the '<em><b>Filter Controls From Extended Container Expression</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Filter Controls From Extended Container Expression</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Filter Controls From Extended Container Expression</em>' attribute.
     * @see #setFilterControlsFromExtendedContainerExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getAbstractContainerDescription_FilterControlsFromExtendedContainerExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getFilterControlsFromExtendedContainerExpression();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.properties.AbstractContainerDescription#getFilterControlsFromExtendedContainerExpression
     * <em>Filter Controls From Extended Container Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Filter Controls From Extended Container Expression</em>' attribute.
     * @see #getFilterControlsFromExtendedContainerExpression()
     * @generated
     */
    void setFilterControlsFromExtendedContainerExpression(String value);

} // AbstractContainerDescription
