/**
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.tests.sample.scxml;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Binding Datatype</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * The binding type in use for the SCXML document.
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getBindingDatatype()
 * @model extendedMetaData="name='Binding.datatype'"
 * @generated
 */
public enum BindingDatatype implements Enumerator {
    /**
     * The '<em><b>Early</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #EARLY_VALUE
     * @generated
     * @ordered
     */
    EARLY(0, "early", "early"),

    /**
     * The '<em><b>Late</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #LATE_VALUE
     * @generated
     * @ordered
     */
    LATE(1, "late", "late");

    /**
     * The '<em><b>Early</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Early</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #EARLY
     * @model name="early"
     * @generated
     * @ordered
     */
    public static final int EARLY_VALUE = 0;

    /**
     * The '<em><b>Late</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Late</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LATE
     * @model name="late"
     * @generated
     * @ordered
     */
    public static final int LATE_VALUE = 1;

    /**
     * An array of all the '<em><b>Binding Datatype</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final BindingDatatype[] VALUES_ARRAY = new BindingDatatype[] { EARLY, LATE, };

    /**
     * A public read-only list of all the '<em><b>Binding Datatype</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<BindingDatatype> VALUES = Collections.unmodifiableList(Arrays.asList(BindingDatatype.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Binding Datatype</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BindingDatatype get(String literal) {
        for (BindingDatatype result : BindingDatatype.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Binding Datatype</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BindingDatatype getByName(String name) {
        for (BindingDatatype result : BindingDatatype.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Binding Datatype</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BindingDatatype get(int value) {
        switch (value) {
        case EARLY_VALUE:
            return EARLY;
        case LATE_VALUE:
            return LATE;
        }
        return null;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final int value;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String name;

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private final String literal;

    /**
     * Only this class can construct instances. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    private BindingDatatype(int value, String name, String literal) {
        this.value = value;
        this.name = name;
        this.literal = literal;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String getLiteral() {
        return literal;
    }

    /**
     * Returns the literal value of the enumerator, which is its string
     * representation. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // BindingDatatype
