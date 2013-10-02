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
package org.eclipse.sirius.viewpoint.description.style;

import org.eclipse.sirius.viewpoint.ContainerShape;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Shape Container Style Description</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getShapeContainerStyleDescription()
 * @model
 * @generated
 */
public interface ShapeContainerStyleDescription extends ContainerStyleDescription, SizeComputationContainerStyleDescription {
    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals
     * are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.ContainerShape}. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of the '<em>Shape</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.ContainerShape
     * @see #setShape(ContainerShape)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getShapeContainerStyleDescription_Shape()
     * @model required="true"
     * @generated
     */
    ContainerShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getShape
     * <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.ContainerShape
     * @see #getShape()
     * @generated
     */
    void setShape(ContainerShape value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' attribute.
     * The default value is <code>"gray"</code>. The literals are from the
     * enumeration {@link org.eclipse.sirius.DefaultColors}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The background color.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Background Color</em>' attribute.
     * @see org.eclipse.sirius.DefaultColors
     * @see #setBackgroundColor(DefaultColors)
     * @see org.eclipse.sirius.viewpoint.description.style.StylePackage#getShapeContainerStyleDescription_BackgroundColor()
     * @model default="gray" required="true"
     * @generated
     */
    ColorDescription getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.description.style.ShapeContainerStyleDescription#getBackgroundColor
     * <em>Background Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Background Color</em>' reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(ColorDescription value);

} // ShapeContainerStyleDescription
