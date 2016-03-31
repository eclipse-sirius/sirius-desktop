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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Edge Routing</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc --> Routing style of edges. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getEdgeRouting()
 * @model
 * @generated
 */
public enum EdgeRouting implements Enumerator {
    /**
     * The '<em><b>Straight</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #STRAIGHT
     * @generated
     * @ordered
     */
    STRAIGHT_LITERAL(0, "straight", "straight"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Manhattan</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #MANHATTAN
     * @generated
     * @ordered
     */
    MANHATTAN_LITERAL(1, "manhattan", "manhattan"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Tree</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #TREE
     * @generated
     * @ordered
     */
    TREE_LITERAL(2, "tree", "tree"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Straight</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Straight</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #STRAIGHT_LITERAL
     * @model name="straight"
     * @generated
     * @ordered
     */
    public static final int STRAIGHT = 0;

    /**
     * The '<em><b>Manhattan</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Manhattan</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #MANHATTAN_LITERAL
     * @model name="manhattan"
     * @generated
     * @ordered
     */
    public static final int MANHATTAN = 1;

    /**
     * The '<em><b>Tree</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Tree</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TREE_LITERAL
     * @model name="tree"
     * @generated
     * @ordered
     */
    public static final int TREE = 2;

    /**
     * An array of all the '<em><b>Edge Routing</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final EdgeRouting[] VALUES_ARRAY = new EdgeRouting[] { STRAIGHT_LITERAL, MANHATTAN_LITERAL, TREE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Edge Routing</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<EdgeRouting> VALUES = Collections.unmodifiableList(Arrays.asList(EdgeRouting.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Edge Routing</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeRouting get(String literal) {
        for (EdgeRouting result : EdgeRouting.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Routing</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeRouting getByName(String name) {
        for (EdgeRouting result : EdgeRouting.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Edge Routing</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static EdgeRouting get(int value) {
        switch (value) {
        case STRAIGHT:
            return STRAIGHT_LITERAL;
        case MANHATTAN:
            return MANHATTAN_LITERAL;
        case TREE:
            return TREE_LITERAL;
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
    private EdgeRouting(int value, String name, String literal) {
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

} // EdgeRouting
