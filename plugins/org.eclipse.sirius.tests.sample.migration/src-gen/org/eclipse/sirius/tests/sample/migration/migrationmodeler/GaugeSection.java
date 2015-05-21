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
package org.eclipse.sirius.tests.sample.migration.migrationmodeler;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Gauge Section</b></em>'. <!-- end-user-doc -->
 *
 * <!-- begin-model-doc --> The gauge section represents one gauge of a
 * GaugeCompositeStyle. <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMin
 * <em>Min</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMax
 * <em>Max</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getValue
 * <em>Value</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getLabel
 * <em>Label</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getBackgroundColor
 * <em>Background Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getForegroundColor
 * <em>Foreground Color</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection()
 * @model
 * @generated
 */
public interface GaugeSection extends EObject {
    /**
     * Returns the value of the '<em><b>Min</b></em>' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc --> The min
     * value of the gauge. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Min</em>' attribute.
     * @see #setMin(Integer)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_Min()
     * @model
     * @generated
     */
    Integer getMin();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMin
     * <em>Min</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_Max()
     * @model
     * @generated
     */
    Integer getMax();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getMax
     * <em>Max</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_Value()
     * @model
     * @generated
     */
    Integer getValue();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getValue
     * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_Label()
     * @model
     * @generated
     */
    String getLabel();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getLabel
     * <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
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
     * @see #setBackgroundColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_BackgroundColor()
     * @model containment="true"
     * @generated
     */
    Color getBackgroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getBackgroundColor
     * <em>Background Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Background Color</em>' containment
     *            reference.
     * @see #getBackgroundColor()
     * @generated
     */
    void setBackgroundColor(Color value);

    /**
     * Returns the value of the '<em><b>Foreground Color</b></em>' containment
     * reference. <!-- begin-user-doc --> <!-- end-user-doc --> <!--
     * begin-model-doc --> The foreground color. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Foreground Color</em>' containment
     *         reference.
     * @see #setForegroundColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getGaugeSection_ForegroundColor()
     * @model containment="true"
     * @generated
     */
    Color getForegroundColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.GaugeSection#getForegroundColor
     * <em>Foreground Color</em>}' containment reference. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Foreground Color</em>' containment
     *            reference.
     * @see #getForegroundColor()
     * @generated
     */
    void setForegroundColor(Color value);

} // GaugeSection
