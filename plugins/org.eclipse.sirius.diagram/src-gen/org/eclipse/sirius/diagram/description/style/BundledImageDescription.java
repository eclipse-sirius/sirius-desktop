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
package org.eclipse.sirius.diagram.description.style;

import org.eclipse.sirius.diagram.BundledImageShape;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>Bundled Image Description</b></em>'. <!--
 * end-user-doc -->
 *
 * <!-- begin-model-doc --> The bundled image style allows to use the default images provide by the Viewpoint editor.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getShape <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getColor <em>Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getProvidedShapeID <em>Provided Shape
 * ID</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBundledImageDescription()
 * @model
 * @generated
 */
public interface BundledImageDescription extends NodeStyleDescription {
    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.BundledImageShape}. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The shape to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @see #setShape(BundledImageShape)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBundledImageDescription_Shape()
     * @model required="true"
     * @generated
     */
    BundledImageShape getShape();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getShape
     * <em>Shape</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.diagram.BundledImageShape
     * @see #getShape()
     * @generated
     */
    void setShape(BundledImageShape value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The color to use. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Color</em>' reference.
     * @see #setColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBundledImageDescription_Color()
     * @model required="true"
     * @generated
     */
    ColorDescription getColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getColor
     * <em>Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Color</em>' reference.
     * @see #getColor()
     * @generated
     */
    void setColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Provided Shape ID</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Provided Shape ID</em>' attribute isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Provided Shape ID</em>' attribute.
     * @see #setProvidedShapeID(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getBundledImageDescription_ProvidedShapeID()
     * @model
     * @generated
     */
    String getProvidedShapeID();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.diagram.description.style.BundledImageDescription#getProvidedShapeID <em>Provided
     * Shape ID</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Provided Shape ID</em>' attribute.
     * @see #getProvidedShapeID()
     * @generated
     */
    void setProvidedShapeID(String value);

} // BundledImageDescription
