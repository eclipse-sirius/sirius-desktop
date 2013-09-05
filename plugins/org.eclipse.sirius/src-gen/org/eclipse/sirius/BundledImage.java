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
package org.eclipse.sirius;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Bundled Image</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc --> The bundled image style allows to use the default
 * images provide by the Sirius editor. <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.eclipse.sirius.BundledImage#getShape <em>Shape</em>}</li>
 * <li>{@link org.eclipse.sirius.BundledImage#getColor <em>Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.SiriusPackage#getBundledImage()
 * @model
 * @generated
 */
public interface BundledImage extends NodeStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

    /**
     * Returns the value of the '<em><b>Shape</b></em>' attribute. The literals
     * are from the enumeration {@link org.eclipse.sirius.BundledImageShape}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Shape</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The shape to use. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.BundledImageShape
     * @see #setShape(BundledImageShape)
     * @see org.eclipse.sirius.SiriusPackage#getBundledImage_Shape()
     * @model required="true"
     * @generated
     */
    BundledImageShape getShape();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.BundledImage#getShape <em>Shape</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Shape</em>' attribute.
     * @see org.eclipse.sirius.BundledImageShape
     * @see #getShape()
     * @generated
     */
    void setShape(BundledImageShape value);

    /**
     * Returns the value of the '<em><b>Color</b></em>' attribute. The literals
     * are from the enumeration {@link org.eclipse.sirius.BundledImageColor}.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Color</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The color to use. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Color</em>' attribute.
     * @see org.eclipse.sirius.BundledImageColor
     * @see #setColor(BundledImageColor)
     * @see org.eclipse.sirius.SiriusPackage#getBundledImage_Color()
     * @model required="true"
     * @generated
     */
    RGBValues getColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.BundledImage#getColor <em>Color</em>}'
     * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Color</em>' containment reference.
     * @see #getColor()
     * @generated
     */
    void setColor(RGBValues value);

} // BundledImage
