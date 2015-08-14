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
 * <em><b>Boolean Datatype</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * Boolean: true or false only
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getBooleanDatatype()
 * @model extendedMetaData="name='Boolean.datatype'"
 * @generated
 */
public enum BooleanDatatype implements Enumerator {
    /**
     * The '<em><b>True</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #TRUE_VALUE
     * @generated
     * @ordered
     */
    TRUE(0, "true", "true"),

    /**
     * The '<em><b>False</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #FALSE_VALUE
     * @generated
     * @ordered
     */
    FALSE(1, "false", "false");

    /**
     * The '<em><b>True</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>True</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TRUE
     * @model name="true"
     * @generated
     * @ordered
     */
    public static final int TRUE_VALUE = 0;

    /**
     * The '<em><b>False</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>False</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #FALSE
     * @model name="false"
     * @generated
     * @ordered
     */
    public static final int FALSE_VALUE = 1;

    /**
     * An array of all the '<em><b>Boolean Datatype</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final BooleanDatatype[] VALUES_ARRAY = new BooleanDatatype[] { TRUE, FALSE, };

    /**
     * A public read-only list of all the '<em><b>Boolean Datatype</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<BooleanDatatype> VALUES = Collections.unmodifiableList(Arrays.asList(BooleanDatatype.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Boolean Datatype</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BooleanDatatype get(String literal) {
        for (BooleanDatatype result : BooleanDatatype.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Boolean Datatype</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BooleanDatatype getByName(String name) {
        for (BooleanDatatype result : BooleanDatatype.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Boolean Datatype</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BooleanDatatype get(int value) {
        switch (value) {
        case TRUE_VALUE:
            return TRUE;
        case FALSE_VALUE:
            return FALSE;
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
    private BooleanDatatype(int value, String name, String literal) {
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

} // BooleanDatatype
