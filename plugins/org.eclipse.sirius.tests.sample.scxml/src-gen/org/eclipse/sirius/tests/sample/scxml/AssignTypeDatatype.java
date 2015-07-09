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
 * <em><b>Assign Type Datatype</b></em>', and utility methods for working with
 * them. <!-- end-user-doc --> <!-- begin-model-doc -->
 *
 * The assign type that allows for precise manipulation of the datamodel
 * location. Types are: replacechildren (default), firstchild, lastchild,
 * previoussibling, nextsibling, replace, delete, addattribute
 *
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.scxml.ScxmlPackage#getAssignTypeDatatype()
 * @model extendedMetaData="name='AssignType.datatype'"
 * @generated
 */
public enum AssignTypeDatatype implements Enumerator {
    /**
     * The '<em><b>Replacechildren</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #REPLACECHILDREN_VALUE
     * @generated
     * @ordered
     */
    REPLACECHILDREN(0, "replacechildren", "replacechildren"),

    /**
     * The '<em><b>Firstchild</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #FIRSTCHILD_VALUE
     * @generated
     * @ordered
     */
    FIRSTCHILD(1, "firstchild", "firstchild"),

    /**
     * The '<em><b>Lastchild</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #LASTCHILD_VALUE
     * @generated
     * @ordered
     */
    LASTCHILD(2, "lastchild", "lastchild"),

    /**
     * The '<em><b>Previoussibling</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #PREVIOUSSIBLING_VALUE
     * @generated
     * @ordered
     */
    PREVIOUSSIBLING(3, "previoussibling", "previoussibling"),

    /**
     * The '<em><b>Nextsibling</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #NEXTSIBLING_VALUE
     * @generated
     * @ordered
     */
    NEXTSIBLING(4, "nextsibling", "nextsibling"),

    /**
     * The '<em><b>Replace</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #REPLACE_VALUE
     * @generated
     * @ordered
     */
    REPLACE(5, "replace", "replace"),

    /**
     * The '<em><b>Delete</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #DELETE_VALUE
     * @generated
     * @ordered
     */
    DELETE(6, "delete", "delete"),

    /**
     * The '<em><b>Addattribute</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #ADDATTRIBUTE_VALUE
     * @generated
     * @ordered
     */
    ADDATTRIBUTE(7, "addattribute", "addattribute");

    /**
     * The '<em><b>Replacechildren</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Replacechildren</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #REPLACECHILDREN
     * @model name="replacechildren"
     * @generated
     * @ordered
     */
    public static final int REPLACECHILDREN_VALUE = 0;

    /**
     * The '<em><b>Firstchild</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Firstchild</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #FIRSTCHILD
     * @model name="firstchild"
     * @generated
     * @ordered
     */
    public static final int FIRSTCHILD_VALUE = 1;

    /**
     * The '<em><b>Lastchild</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Lastchild</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LASTCHILD
     * @model name="lastchild"
     * @generated
     * @ordered
     */
    public static final int LASTCHILD_VALUE = 2;

    /**
     * The '<em><b>Previoussibling</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Previoussibling</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #PREVIOUSSIBLING
     * @model name="previoussibling"
     * @generated
     * @ordered
     */
    public static final int PREVIOUSSIBLING_VALUE = 3;

    /**
     * The '<em><b>Nextsibling</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Nextsibling</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NEXTSIBLING
     * @model name="nextsibling"
     * @generated
     * @ordered
     */
    public static final int NEXTSIBLING_VALUE = 4;

    /**
     * The '<em><b>Replace</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Replace</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #REPLACE
     * @model name="replace"
     * @generated
     * @ordered
     */
    public static final int REPLACE_VALUE = 5;

    /**
     * The '<em><b>Delete</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Delete</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DELETE
     * @model name="delete"
     * @generated
     * @ordered
     */
    public static final int DELETE_VALUE = 6;

    /**
     * The '<em><b>Addattribute</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Addattribute</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #ADDATTRIBUTE
     * @model name="addattribute"
     * @generated
     * @ordered
     */
    public static final int ADDATTRIBUTE_VALUE = 7;

    /**
     * An array of all the '<em><b>Assign Type Datatype</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final AssignTypeDatatype[] VALUES_ARRAY = new AssignTypeDatatype[] { REPLACECHILDREN, FIRSTCHILD, LASTCHILD, PREVIOUSSIBLING, NEXTSIBLING, REPLACE, DELETE, ADDATTRIBUTE, };

    /**
     * A public read-only list of all the '<em><b>Assign Type Datatype</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<AssignTypeDatatype> VALUES = Collections.unmodifiableList(Arrays.asList(AssignTypeDatatype.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Assign Type Datatype</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static AssignTypeDatatype get(String literal) {
        for (AssignTypeDatatype result : AssignTypeDatatype.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Assign Type Datatype</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static AssignTypeDatatype getByName(String name) {
        for (AssignTypeDatatype result : AssignTypeDatatype.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Assign Type Datatype</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static AssignTypeDatatype get(int value) {
        switch (value) {
        case REPLACECHILDREN_VALUE:
            return REPLACECHILDREN;
        case FIRSTCHILD_VALUE:
            return FIRSTCHILD;
        case LASTCHILD_VALUE:
            return LASTCHILD;
        case PREVIOUSSIBLING_VALUE:
            return PREVIOUSSIBLING;
        case NEXTSIBLING_VALUE:
            return NEXTSIBLING;
        case REPLACE_VALUE:
            return REPLACE;
        case DELETE_VALUE:
            return DELETE;
        case ADDATTRIBUTE_VALUE:
            return ADDATTRIBUTE;
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
    private AssignTypeDatatype(int value, String name, String literal) {
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

} // AssignTypeDatatype
