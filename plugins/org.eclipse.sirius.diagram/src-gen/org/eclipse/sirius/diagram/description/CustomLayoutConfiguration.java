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
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Custom Layout Configuration</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLayoutOptions <em>Layout
 * Options</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getCustomLayoutConfiguration()
 * @model
 * @generated
 */
public interface CustomLayoutConfiguration extends Layout {
    /**
     * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Id</em>' attribute.
     * @see #setId(String)
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getCustomLayoutConfiguration_Id()
     * @model
     * @generated
     */
    String getId();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getId
     * <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Id</em>' attribute.
     * @see #getId()
     * @generated
     */
    void setId(String value);

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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getCustomLayoutConfiguration_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.CustomLayoutConfiguration#getLabel
     * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getCustomLayoutConfiguration_LayoutOptions()
     * @model
     * @generated
     */
    EList<LayoutOption> getLayoutOptions();

} // CustomLayoutConfiguration
