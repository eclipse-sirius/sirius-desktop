/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.layoutdata;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Point</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> Represents a point (x, y) in 2-dimensional space.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.Point#getX <em>X</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.layoutdata.Point#getY <em>Y</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getPoint()
 * @model
 * @generated
 */
public interface Point extends EObject {
    /**
     * Returns the value of the '<em><b>X</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Represents the x coordinate. <!-- end-model-doc -->
     *
     * @return the value of the '<em>X</em>' attribute.
     * @see #setX(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getPoint_X()
     * @model
     * @generated
     */
    int getX();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point#getX <em>X</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>X</em>' attribute.
     * @see #getX()
     * @generated
     */
    void setX(int value);

    /**
     * Returns the value of the '<em><b>Y</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Represents the y coordinate. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Y</em>' attribute.
     * @see #setY(int)
     * @see org.eclipse.sirius.diagram.layoutdata.LayoutdataPackage#getPoint_Y()
     * @model
     * @generated
     */
    int getY();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.layoutdata.Point#getY <em>Y</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Y</em>' attribute.
     * @see #getY()
     * @generated
     */
    void setY(int value);

} // Point
