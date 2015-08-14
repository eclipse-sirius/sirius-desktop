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
 * <em><b>Transition Type Datatype</b></em>', and utility methods for working
 * with them. <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * The type of the transition i.e. internal or external.
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getTransitionTypeDatatype()
 * @model extendedMetaData="name='TransitionType.datatype'"
 * @generated
 */
public enum TransitionTypeDatatype implements Enumerator {
    /**
     * The '<em><b>Internal</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #INTERNAL_VALUE
     * @generated
     * @ordered
     */
    INTERNAL(0, "internal", "internal"),

    /**
     * The '<em><b>External</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #EXTERNAL_VALUE
     * @generated
     * @ordered
     */
    EXTERNAL(1, "external", "external");

    /**
     * The '<em><b>Internal</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Internal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #INTERNAL
     * @model name="internal"
     * @generated
     * @ordered
     */
    public static final int INTERNAL_VALUE = 0;

    /**
     * The '<em><b>External</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>External</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #EXTERNAL
     * @model name="external"
     * @generated
     * @ordered
     */
    public static final int EXTERNAL_VALUE = 1;

    /**
     * An array of all the '<em><b>Transition Type Datatype</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final TransitionTypeDatatype[] VALUES_ARRAY = new TransitionTypeDatatype[] { INTERNAL, EXTERNAL, };

    /**
     * A public read-only list of all the '
     * <em><b>Transition Type Datatype</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<TransitionTypeDatatype> VALUES = Collections.unmodifiableList(Arrays.asList(TransitionTypeDatatype.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Transition Type Datatype</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static TransitionTypeDatatype get(String literal) {
        for (TransitionTypeDatatype result : TransitionTypeDatatype.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Transition Type Datatype</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static TransitionTypeDatatype getByName(String name) {
        for (TransitionTypeDatatype result : TransitionTypeDatatype.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Transition Type Datatype</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static TransitionTypeDatatype get(int value) {
        switch (value) {
        case INTERNAL_VALUE:
            return INTERNAL;
        case EXTERNAL_VALUE:
            return EXTERNAL;
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
    private TransitionTypeDatatype(int value, String name, String literal) {
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

} // TransitionTypeDatatype
