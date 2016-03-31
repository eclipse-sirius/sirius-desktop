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

import org.eclipse.sirius.viewpoint.Customizable;
import org.eclipse.sirius.viewpoint.RGBValues;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Gauge Section</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The gauge section represents one gauge of a
 * GaugeCompositeStyle. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getMin <em>Min</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getMax <em>Max</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getValue <em>Value</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getLabel <em>Label</em>}
 * </li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>{@link org.eclipse.sirius.diagram.GaugeSection#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection()
 * @model
 * @generated
 */
public interface GaugeSection extends Customizable {
    /**
     * Returns the value of the '<em><b>Min</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The min
     * value of the gauge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Min</em>' attribute.
     * @see #setMin(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_Min()
     * @model
     * @generated
     */
    Integer getMin();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getMin <em>Min</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Min</em>' attribute.
     * @see #getMin()
     * @generated
     */
    void setMin(Integer value);

    /**
     * Returns the value of the '<em><b>Max</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The max
     * value of the gauge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Max</em>' attribute.
     * @see #setMax(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_Max()
     * @model
     * @generated
     */
    Integer getMax();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getMax <em>Max</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Max</em>' attribute.
     * @see #getMax()
     * @generated
     */
    void setMax(Integer value);

    /**
     * Returns the value of the '<em><b>Value</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * current value. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Value</em>' attribute.
     * @see #setValue(Integer)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_Value()
     * @model
     * @generated
     */
    Integer getValue();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getValue <em>Value</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Value</em>' attribute.
     * @see #getValue()
     * @generated
     */
    void setValue(Integer value);

    /**
     * Returns the value of the '<em><b>Label</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The
     * label of the gauge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label</em>' attribute.
     * @see #setLabel(String)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getLabel <em>Label</em>}'
     * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label</em>' attribute.
     * @see #getLabel()
     * @generated
     */
    void setLabel(String value);

    /**
     * Returns the value of the '<em><b>Background Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The background color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Background Color</em>' containment
     *         reference.
     * @see #setBackgroundColor(RGBValues)
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_BackgroundColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getBackgroundColor
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
     * @see org.eclipse.sirius.diagram.DiagramPackage#getGaugeSection_ForegroundColor()
     * @model containment="true" resolveProxies="true"
     * @generated
     */
    RGBValues getForegroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.diagram.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Color</em>' attribute.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(RGBValues value);

} // GaugeSection
