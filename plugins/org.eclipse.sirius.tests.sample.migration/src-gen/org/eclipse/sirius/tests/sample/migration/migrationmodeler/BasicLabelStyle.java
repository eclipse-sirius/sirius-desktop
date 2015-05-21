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
 * <em><b>Basic Label Style</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelSize
 * <em>Label Size</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelFormat
 * <em>Label Format</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#isShowIcon
 * <em>Show Icon</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelColor
 * <em>Label Color</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getIconPath
 * <em>Icon Path</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle()
 * @model
 * @generated
 */
public interface BasicLabelStyle extends EObject {
    /**
     * Returns the value of the '<em><b>Label Size</b></em>' attribute. The
     * default value is <code>"8"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The font size. <!--
     * end-model-doc -->
     *
     * @return the value of the '<em>Label Size</em>' attribute.
     * @see #setLabelSize(int)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle_LabelSize()
     * @model default="8"
     * @generated
     */
    int getLabelSize();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelSize
     * <em>Label Size</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Size</em>' attribute.
     * @see #getLabelSize()
     * @generated
     */
    void setLabelSize(int value);

    /**
     * Returns the value of the '<em><b>Label Format</b></em>' attribute. The
     * default value is <code>"normal"</code>. The literals are from the
     * enumeration
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat}
     * . <!-- begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * The font format. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Label Format</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * @see #setLabelFormat(FontFormat)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle_LabelFormat()
     * @model default="normal"
     * @generated
     */
    FontFormat getLabelFormat();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelFormat
     * <em>Label Format</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Format</em>' attribute.
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.FontFormat
     * @see #getLabelFormat()
     * @generated
     */
    void setLabelFormat(FontFormat value);

    /**
     * Returns the value of the '<em><b>Show Icon</b></em>' attribute. The
     * default value is <code>"true"</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> True, if the icon shoud be
     * dispayed on the element. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Show Icon</em>' attribute.
     * @see #setShowIcon(boolean)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle_ShowIcon()
     * @model default="true"
     * @generated
     */
    boolean isShowIcon();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#isShowIcon
     * <em>Show Icon</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Show Icon</em>' attribute.
     * @see #isShowIcon()
     * @generated
     */
    void setShowIcon(boolean value);

    /**
     * Returns the value of the '<em><b>Label Color</b></em>' containment
     * reference. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Label Color</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Label Color</em>' containment reference.
     * @see #setLabelColor(Color)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle_LabelColor()
     * @model containment="true"
     * @generated
     */
    Color getLabelColor();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getLabelColor
     * <em>Label Color</em>}' containment reference. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Label Color</em>' containment
     *            reference.
     * @see #getLabelColor()
     * @generated
     */
    void setLabelColor(Color value);

    /**
     * Returns the value of the '<em><b>Icon Path</b></em>' attribute. The
     * default value is <code>""</code>. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The path of the icon to display
     * on the element. If unset, the icon corresponding to the semantic element
     * will be displayed. <!-- end-model-doc -->
     *
     * @return the value of the '<em>Icon Path</em>' attribute.
     * @see #setIconPath(String)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBasicLabelStyle_IconPath()
     * @model default=""
     * @generated
     */
    String getIconPath();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.BasicLabelStyle#getIconPath
     * <em>Icon Path</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Icon Path</em>' attribute.
     * @see #getIconPath()
     * @generated
     */
    void setIconPath(String value);

} // BasicLabelStyle
