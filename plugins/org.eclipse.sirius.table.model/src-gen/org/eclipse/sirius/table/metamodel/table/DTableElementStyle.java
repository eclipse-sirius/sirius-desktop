/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.metamodel.table;

import org.eclipse.emf.common.util.EList;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.IdentifiedElement;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> A representation of the model object ' <em><b>DTable Element Style</b></em>'. <!--
 * end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelSize <em>Label Size</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelFormat <em>Label Format</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultForegroundStyle <em>Default
 * Foreground Style</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultBackgroundStyle <em>Default
 * Background Style</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getForegroundColor <em>Foreground
 * Color</em>}</li>
 * <li>{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getBackgroundColor <em>Background
 * Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle()
 * @model
 * @generated
 */
public interface DTableElementStyle extends IdentifiedElement {
    /**
     * Returns the value of the '<em><b>Label Size</b></em>' attribute. The default value is <code>"8"</code>. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The font size. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Size</em>' attribute.
     * @see #setLabelSize(int)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_LabelSize()
     * @model default="8"
     * @generated
     */
    int getLabelSize();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getLabelSize <em>Label
     * Size</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Size</em>' attribute.
     * @see #getLabelSize()
     * @generated
     */
    void setLabelSize(int value);

    /**
     * Returns the value of the '<em><b>Label Format</b></em>' attribute. The default value is <code>"normal"</code>.
     * The literals are from the enumeration {@link org.eclipse.sirius.viewpoint.FontFormat}. <!-- begin-user-doc -->
     * <!-- end-user-doc --> <!-- begin-model-doc --> The font format. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Format</em>' attribute.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see #setLabelFormat(FontFormat)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_LabelFormat()
     * @model default="normal"
     * @generated
     */
    EList<FontFormat> getLabelFormat();

    /**
     * Returns the value of the '<em><b>Foreground Color</b></em>' attribute. The default value is <code>"0,0,0"</code>.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Foreground Color</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Foreground Color</em>' attribute.
     * @see #setForegroundColor(RGBValues)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_ForegroundColor()
     * @model default="0,0,0" dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getForegroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getForegroundColor
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Color</em>' attribute.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(RGBValues value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' attribute. The default value is
     * <code>"255,255,255"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Background Color</em>' containment reference isn't clear, there really should be more
     * of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Background Color</em>' attribute.
     * @see #setBackgroundColor(RGBValues)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_BackgroundColor()
     * @model default="255,255,255" dataType="org.eclipse.sirius.viewpoint.RGBValues"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#getBackgroundColor
     * <em>Background Color</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' attribute.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(RGBValues value);

    /**
     * Returns the value of the '<em><b>Default Foreground Style</b></em>' attribute. The default value is
     * <code>"false"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Determine if the
     * foreground part of the style is computed from a conditional style or not (default style). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Default Foreground Style</em>' attribute.
     * @see #setDefaultForegroundStyle(boolean)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_DefaultForegroundStyle()
     * @model default="false"
     * @generated
     */
    boolean isDefaultForegroundStyle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultForegroundStyle <em>Default
     * Foreground Style</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Foreground Style</em>' attribute.
     * @see #isDefaultForegroundStyle()
     * @generated
     */
    void setDefaultForegroundStyle(boolean value);

    /**
     * Returns the value of the '<em><b>Default Background Style</b></em>' attribute. The default value is
     * <code>"false"</code>. <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> Determine if the
     * background part of the style is computed from a conditional style or not (default style). <!-- end-model-doc -->
     *
     * @return the value of the '<em>Default Background Style</em>' attribute.
     * @see #setDefaultBackgroundStyle(boolean)
     * @see org.eclipse.sirius.table.metamodel.table.TablePackage#getDTableElementStyle_DefaultBackgroundStyle()
     * @model default="false"
     * @generated
     */
    boolean isDefaultBackgroundStyle();

    /**
     * Sets the value of the
     * '{@link org.eclipse.sirius.table.metamodel.table.DTableElementStyle#isDefaultBackgroundStyle <em>Default
     * Background Style</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Default Background Style</em>' attribute.
     * @see #isDefaultBackgroundStyle()
     * @generated
     */
    void setDefaultBackgroundStyle(boolean value);

} // DTableElementStyle
