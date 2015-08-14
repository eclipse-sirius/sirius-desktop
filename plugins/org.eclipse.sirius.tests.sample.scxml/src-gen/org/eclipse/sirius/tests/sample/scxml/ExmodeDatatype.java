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
 * <em><b>Exmode Datatype</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * Describes the processor execution mode for this document, being either "lax"
 * or "strict".
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getExmodeDatatype()
 * @model extendedMetaData="name='Exmode.datatype'"
 * @generated
 */
public enum ExmodeDatatype implements Enumerator {
    /**
     * The '<em><b>Lax</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #LAX_VALUE
     * @generated
     * @ordered
     */
    LAX(0, "lax", "lax"),

    /**
     * The '<em><b>Strict</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #STRICT_VALUE
     * @generated
     * @ordered
     */
    STRICT(1, "strict", "strict");

    /**
     * The '<em><b>Lax</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Lax</b></em>' literal object isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LAX
     * @model name="lax"
     * @generated
     * @ordered
     */
    public static final int LAX_VALUE = 0;

    /**
     * The '<em><b>Strict</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Strict</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #STRICT
     * @model name="strict"
     * @generated
     * @ordered
     */
    public static final int STRICT_VALUE = 1;

    /**
     * An array of all the '<em><b>Exmode Datatype</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ExmodeDatatype[] VALUES_ARRAY = new ExmodeDatatype[] { LAX, STRICT, };

    /**
     * A public read-only list of all the '<em><b>Exmode Datatype</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<ExmodeDatatype> VALUES = Collections.unmodifiableList(Arrays.asList(ExmodeDatatype.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Exmode Datatype</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static ExmodeDatatype get(String literal) {
        for (ExmodeDatatype result : ExmodeDatatype.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Exmode Datatype</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static ExmodeDatatype getByName(String name) {
        for (ExmodeDatatype result : ExmodeDatatype.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Exmode Datatype</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static ExmodeDatatype get(int value) {
        switch (value) {
        case LAX_VALUE:
            return LAX;
        case STRICT_VALUE:
            return STRICT;
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
    private ExmodeDatatype(int value, String name, String literal) {
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

} // ExmodeDatatype
