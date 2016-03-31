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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.viewpoint.description.ColorDescription;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Gauge Section Description</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The gauge section represents one gauge of a
 * GaugeCompositeStyle. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getMinValueExpression
 * <em>Min Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getMaxValueExpression
 * <em>Max Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getValueExpression
 * <em>Value Expression</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getLabel
 * <em>Label</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription()
 * @model
 * @generated
 */
public interface GaugeSectionDescription extends EObject {
    /**
     * Returns the value of the '<em><b>Min Value Expression</b></em>'
     * attribute. The default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Min Value Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Min Value Expression</em>' attribute.
     * @see #setMinValueExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_MinValueExpression()
     * @model default="0" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getMinValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getMinValueExpression
     * <em>Min Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Min Value Expression</em>'
     *            attribute.
     * @see #getMinValueExpression()
     * @generated
     */
    void setMinValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Max Value Expression</b></em>'
     * attribute. The default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Max Value Expression</em>' attribute isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Max Value Expression</em>' attribute.
     * @see #setMaxValueExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_MaxValueExpression()
     * @model default="0" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getMaxValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getMaxValueExpression
     * <em>Max Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Max Value Expression</em>'
     *            attribute.
     * @see #getMaxValueExpression()
     * @generated
     */
    void setMaxValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Value Expression</b></em>' attribute.
     * The default value is <code>"0"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Value Expression</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Value Expression</em>' attribute.
     * @see #setValueExpression(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_ValueExpression()
     * @model default="0" dataType=
     *        "org.eclipse.sirius.viewpoint.description.InterpretedExpression"
     *        annotation=
     *        "http://www.eclipse.org/sirius/interpreted/expression/returnType returnType='an integer.'"
     * @generated
     */
    String getValueExpression();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getValueExpression
     * <em>Value Expression</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value Expression</em>' attribute.
     * @see #getValueExpression()
     * @generated
     */
    void setValueExpression(String value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The background color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' reference.
     * @see #setBackgroundColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_BackgroundColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getBackgroundColor
     * <em>Background Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Foreground Color</b></em>' reference.
     * <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The foreground color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Foreground Color</em>' reference.
     * @see #setForegroundColor(ColorDescription)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_ForegroundColor()
     * @model required="true"
     * @generated
     */
    ColorDescription getForegroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getForegroundColor
     * <em>Foreground Color</em>}' reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Color</em>' reference.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(ColorDescription value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * label of the gauge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.diagram.description.style.StylePackage#getGaugeSectionDescription_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.description.style.GaugeSectionDescription#getLabel
     * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

} // GaugeSectionDescription
