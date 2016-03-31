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
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.sirius.diagram.ContainerShape;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Shape Container Style Description</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription#getShape
 * <em>Shape</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription#getBackgroundColor
 * <em>Background Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getShapeContainerStyleDescription()
 * @model
 * @generated
 */
public interface ShapeContainerStyleDescription extends ContainerStyleDescription, SizeComputationContainerStyleDescription {
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
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getShapeContainerStyleDescription_Shape()
     * @model required="true"
     * @generated
     */
    ContainerShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription#getShape
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
     * Returns the value of the '<em><b>Background Color</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The color to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' reference.
     * @see #setBackgroundColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getShapeContainerStyleDescription_BackgroundColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.ShapeContainerStyleDescription#getBackgroundColor
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
