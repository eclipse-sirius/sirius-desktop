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
 * <em><b>Flat Container Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle
 * <em>Background Style</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getFlatContainerStyle()
 * @model
 * @generated
 */
public interface FlatContainerStyle extends ContainerStyle {
    /**
     * Returns the value of the '<em><b>Background Style</b></em>' attribute.
     * The literals are from the enumeration
     * {@link org.eclipse.sirius.diagram.BackgroundStyle}. <!-- begin-user-doc
     * --> <!-- end-user-doc --> <!-- begin-model-doc --> The background style.
     * <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @see #setBackgroundStyle(BackgroundStyle)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFlatContainerStyle_BackgroundStyle()
     * @model required="true"
     * @generated
     */
    BackgroundStyle getBackgroundStyle();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundStyle
     * <em>Background Style</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Style</em>' attribute.
     * @see org.eclipse.sirius.diagram.BackgroundStyle
     * @see #getBackgroundStyle()
     * @generated
     */
    void setBackgroundStyle(BackgroundStyle value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The background color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' containment
     *         reference.
     * @see #setBackgroundColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFlatContainerStyle_BackgroundColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getBackgroundColor
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' attribute.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(RGBValues value);

    /**
     * Returns the value of the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The foreground color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Foreground Color</em>' containment
     *         reference.
     * @see #setForegroundColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getFlatContainerStyle_ForegroundColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getForegroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.FlatContainerStyle#getForegroundColor
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Color</em>' attribute.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(RGBValues value);

} // FlatContainerStyle
