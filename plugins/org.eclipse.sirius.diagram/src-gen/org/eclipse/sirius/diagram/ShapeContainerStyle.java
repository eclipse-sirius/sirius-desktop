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
package org.eclipse.sirius.diagram;

import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Shape Container Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.ShapeContainerStyle#getShape
 * <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getShapeContainerStyle()
 * @model
 * @generated
 */
public interface ShapeContainerStyle extends ContainerStyle {
    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.diagram.ContainerShape}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Shape</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @see #setShape(ContainerShape)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getShapeContainerStyle_Shape()
     * @model required="true"
     * @generated
     */
    ContainerShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle#getShape
     * <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.diagram.ContainerShape
     * @see #getShape()
     * @generated
     */
    void setShape(ContainerShape value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Color</em>' containment reference
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The background color. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' attribute.
     * @see #setBackgroundColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getShapeContainerStyle_BackgroundColor()
     * @model dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.ShapeContainerStyle#getBackgroundColor
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' attribute.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(RGBValues value);

} // ShapeContainerStyle
