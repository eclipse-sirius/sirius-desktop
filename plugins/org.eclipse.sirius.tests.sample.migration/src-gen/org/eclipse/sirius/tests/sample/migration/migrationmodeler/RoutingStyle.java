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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Routing Style</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getRoutingStyle()
 * @model
 * @generated
 */
public enum RoutingStyle implements Enumerator {
    /**
     * The '<em><b>Straight</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #STRAIGHT_VALUE
     * @generated
     * @ordered
     */
    STRAIGHT(0, "Straight", "Straight"),

    /**
     * The '<em><b>Manhattan</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #MANHATTAN_VALUE
     * @generated
     * @ordered
     */
    MANHATTAN(1, "Manhattan", "Manhattan"),

    /**
     * The '<em><b>Tree</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #TREE_VALUE
     * @generated
     * @ordered
     */
    TREE(2, "Tree", "Tree");

    /**
     * The '<em><b>Straight</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Straight</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #STRAIGHT
     * @model name="Straight"
     * @generated
     * @ordered
     */
    public static final int STRAIGHT_VALUE = 0;

    /**
     * The '<em><b>Manhattan</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Manhattan</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #MANHATTAN
     * @model name="Manhattan"
     * @generated
     * @ordered
     */
    public static final int MANHATTAN_VALUE = 1;

    /**
     * The '<em><b>Tree</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Tree</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TREE
     * @model name="Tree"
     * @generated
     * @ordered
     */
    public static final int TREE_VALUE = 2;

    /**
     * An array of all the '<em><b>Routing Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final RoutingStyle[] VALUES_ARRAY = new RoutingStyle[] { STRAIGHT, MANHATTAN, TREE, };

    /**
     * A public read-only list of all the '<em><b>Routing Style</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<RoutingStyle> VALUES = Collections.unmodifiableList(Arrays.asList(RoutingStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Routing Style</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static RoutingStyle get(String literal) {
        for (RoutingStyle result : RoutingStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Routing Style</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static RoutingStyle getByName(String name) {
        for (RoutingStyle result : RoutingStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Routing Style</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static RoutingStyle get(int value) {
        switch (value) {
        case STRAIGHT_VALUE:
            return STRAIGHT;
        case MANHATTAN_VALUE:
            return MANHATTAN;
        case TREE_VALUE:
            return TREE;
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
    private RoutingStyle(int value, String name, String literal) {
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

} // RoutingStyle
