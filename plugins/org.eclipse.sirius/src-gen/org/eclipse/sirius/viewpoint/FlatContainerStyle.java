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
package org.eclipse.sirius.viewpoint;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Flat Container Style</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getFlatContainerStyle()
 * @model
 * @generated
 */
public interface FlatContainerStyle extends ContainerStyle {
    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    String copyright = "Copyright (c) 2007-2013 THALES GLOBAL SERVICES\n All rights reserved.\n\n Contributors:\n     Obeo - Initial API and implementation";

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
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getFlatContainerStyle_BackgroundColor()
     * @model default="gray"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Background Color</em>' containment
     *            reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(RGBValues value);

    /**
     * Returns the value of the '<em><b>Background Style</b></em>' attribute.
     * The literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.BackgroundStyle}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The background style.
     * <!-- end-model-doc -->
     * 
     * @return the value of the '<em>Background Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.BackgroundStyle
     * @see #setBackgroundStyle(BackgroundStyle)
     * @see org.eclipse.sirius.viewpoint.SiriusPackage#getFlatContainerStyle_BackgroundStyle()
     * @model required="true"
     * @generated
     */
    BackgroundStyle getBackgroundStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Background Style</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.BackgroundStyle
     * @see #getBackgroundStyle()
     * @generated
     */
    void setBackgroundStyle(BackgroundStyle value);

    /**
     * Returns the value of the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreground Color</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc --> <!-- begin-model-doc --> The foreground color. <!--
     * end-model-doc -->
     * 
     * @return the value of the '<em>Foreground Color</em>' containment
     *         reference.
     * @see #setForegroundColor(RGBValues)
     * @see org.eclipse.sirius.viewpoint.ViewpointPackage#getFlatContainerStyle_ForegroundColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getForegroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.viewpoint.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     * 
     * @param value
     *            the new value of the '<em>Foreground Color</em>' containment
     *            reference.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(RGBValues value);

} // FlatContainerStyle
