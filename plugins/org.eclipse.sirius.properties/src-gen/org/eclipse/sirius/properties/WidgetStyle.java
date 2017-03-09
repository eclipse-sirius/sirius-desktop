/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Widget Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression <em>Label Font Name
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression <em>Label Font Size
 * Expression</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor <em>Label Background Color</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor <em>Label Foreground Color</em>}</li>
 * <li>{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontFormat <em>Label Font Format</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle()
 * @model
 * @generated
 */
public interface WidgetStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Label Font Name Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Font Name Expression</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Font Name Expression</em>' attribute.
     * @see #setLabelFontNameExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle_LabelFontNameExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getLabelFontNameExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontNameExpression <em>Label Font
     * Name Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Font Name Expression</em>' attribute.
     * @see #getLabelFontNameExpression()
     * @generated
     */
    void setLabelFontNameExpression(String value);

    /**
     * Returns the value of the '<em><b>Label Font Size Expression</b></em>' attribute. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Font Size Expression</em>' attribute isn't clear, there really should be more of
     * a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Font Size Expression</em>' attribute.
     * @see #setLabelFontSizeExpression(String)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle_LabelFontSizeExpression()
     * @model dataType="org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     * @generated
     */
    String getLabelFontSizeExpression();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelFontSizeExpression <em>Label Font
     * Size Expression</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Font Size Expression</em>' attribute.
     * @see #getLabelFontSizeExpression()
     * @generated
     */
    void setLabelFontSizeExpression(String value);

    /**
     * Returns the value of the '<em><b>Label Background Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Background Color</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Background Color</em>' reference.
     * @see #setLabelBackgroundColor(ColorDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle_LabelBackgroundColor()
     * @model
     * @generated
     */
    ColorDescription getLabelBackgroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelBackgroundColor <em>Label
     * Background Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Background Color</em>' reference.
     * @see #getLabelBackgroundColor()
     * @generated
     */
    void setLabelBackgroundColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Label Foreground Color</b></em>' reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Foreground Color</em>' reference isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Foreground Color</em>' reference.
     * @see #setLabelForegroundColor(ColorDescription)
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle_LabelForegroundColor()
     * @model
     * @generated
     */
    ColorDescription getLabelForegroundColor();

    /**
     * Sets the value of the '{@link org.eclipse.sirius.properties.WidgetStyle#getLabelForegroundColor <em>Label
     * Foreground Color</em>}' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Foreground Color</em>' reference.
     * @see #getLabelForegroundColor()
     * @generated
     */
    void setLabelForegroundColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Label Font Format</b></em>' attribute list. The list contents are of type
     * {@link org.eclipse.sirius.viewpoint.FontFormat}. The literals are from the enumeration
     * {@link org.eclipse.sirius.viewpoint.FontFormat}. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Font Format</em>' attribute list isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Font Format</em>' attribute list.
     * @see org.eclipse.sirius.viewpoint.FontFormat
     * @see org.eclipse.sirius.properties.PropertiesPackage#getWidgetStyle_LabelFontFormat()
     * @model upper="4"
     * @generated
     */
    EList<FontFormat> getLabelFontFormat();

} // WidgetStyle
