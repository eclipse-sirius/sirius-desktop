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
 * <em><b>Abstract Representation</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getMappingId
 * <em>Mapping Id</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getLayout
 * <em>Layout</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isDisplayed
 * <em>Displayed</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isHidden
 * <em>Hidden</em>}</li>
 * <li>
 * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isPinned
 * <em>Pinned</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation()
 * @model abstract="true"
 * @generated
 */
public interface AbstractRepresentation extends EObject {
    /**
     * Returns the value of the '<em><b>Mapping Id</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Mapping Id</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Mapping Id</em>' attribute.
     * @see #setMappingId(String)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation_MappingId()
     * @model required="true"
     * @generated
     */
    String getMappingId();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getMappingId
     * <em>Mapping Id</em>}' attribute. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Mapping Id</em>' attribute.
     * @see #getMappingId()
     * @generated
     */
    void setMappingId(String value);

    /**
     * Returns the value of the '<em><b>Layout</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Layout</em>' containment reference isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Layout</em>' containment reference.
     * @see #setLayout(Layout)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation_Layout()
     * @model containment="true"
     * @generated
     */
    Layout getLayout();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#getLayout
     * <em>Layout</em>}' containment reference. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the new value of the '<em>Layout</em>' containment reference.
     * @see #getLayout()
     * @generated
     */
    void setLayout(Layout value);

    /**
     * Returns the value of the '<em><b>Displayed</b></em>' attribute. The
     * default value is <code>"true"</code>. <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Displayed</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Displayed</em>' attribute.
     * @see #setDisplayed(boolean)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation_Displayed()
     * @model default="true"
     * @generated
     */
    boolean isDisplayed();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isDisplayed
     * <em>Displayed</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Displayed</em>' attribute.
     * @see #isDisplayed()
     * @generated
     */
    void setDisplayed(boolean value);

    /**
     * Returns the value of the '<em><b>Hidden</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Hidden</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Hidden</em>' attribute.
     * @see #setHidden(boolean)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation_Hidden()
     * @model
     * @generated
     */
    boolean isHidden();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isHidden
     * <em>Hidden</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Hidden</em>' attribute.
     * @see #isHidden()
     * @generated
     */
    void setHidden(boolean value);

    /**
     * Returns the value of the '<em><b>Pinned</b></em>' attribute. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Pinned</em>' attribute isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @return the value of the '<em>Pinned</em>' attribute.
     * @see #setPinned(boolean)
     * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getAbstractRepresentation_Pinned()
     * @model
     * @generated
     */
    boolean isPinned();

    /**
     * Sets the value of the '
     * {@link org.eclipse.sirius.tests.sample.migration.migrationmodeler.AbstractRepresentation#isPinned
     * <em>Pinned</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @param value
     *            the new value of the '<em>Pinned</em>' attribute.
     * @see #isPinned()
     * @generated
     */
    void setPinned(boolean value);

} // AbstractRepresentation
