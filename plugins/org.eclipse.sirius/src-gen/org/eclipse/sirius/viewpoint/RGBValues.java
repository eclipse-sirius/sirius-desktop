/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Obeo - initial API and implementation
 * 
 */
package org.eclipse.sirius.viewpoint;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>RGB Values</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.viewpoint.RGBValues#getRed <em>Red</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.RGBValues#getGreen <em>Green</em>}</li>
 * <li>{@link org.eclipse.sirius.viewpoint.RGBValues#getBlue <em>Blue</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getRGBValues()
 * @model
 * @generated
 */
public interface RGBValues extends EObject {
    /**
     * Returns the value of the '<em><b>Red</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Red</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Red</em>' attribute.
     * @see #setRed(int)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getRGBValues_Red()
     * @model required="true"
     * @generated
     */
    int getRed();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getRed <em>Red</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Red</em>' attribute.
     * @see #getRed()
     * @generated
     */
    void setRed(int value);

    /**
     * Returns the value of the '<em><b>Green</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Green</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Green</em>' attribute.
     * @see #setGreen(int)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getRGBValues_Green()
     * @model required="true"
     * @generated
     */
    int getGreen();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getGreen <em>Green</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Green</em>' attribute.
     * @see #getGreen()
     * @generated
     */
    void setGreen(int value);

    /**
     * Returns the value of the '<em><b>Blue</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Blue</em>' attribute isn't clear, there really
     * should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Blue</em>' attribute.
     * @see #setBlue(int)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getRGBValues_Blue()
     * @model required="true"
     * @generated
     */
    int getBlue();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.RGBValues#getBlue <em>Blue</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Blue</em>' attribute.
     * @see #getBlue()
     * @generated
     */
    void setBlue(int value);

} // RGBValues
