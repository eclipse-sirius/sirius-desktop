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
package org.eclipse.sirius.diagram.description.tool;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Reconnection Kind</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.tool.ToolPackage#getReconnectionKind()
 * @model
 * @generated
 */
public enum ReconnectionKind implements Enumerator {
    /**
     * The '<em><b>RECONNECT TARGET</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #RECONNECT_TARGET
     * @generated
     * @ordered
     */
    RECONNECT_TARGET_LITERAL(0, "RECONNECT_TARGET", "RECONNECT_TARGET"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>RECONNECT SOURCE</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #RECONNECT_SOURCE
     * @generated
     * @ordered
     */
    RECONNECT_SOURCE_LITERAL(1, "RECONNECT_SOURCE", "RECONNECT_SOURCE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>RECONNECT BOTH</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #RECONNECT_BOTH
     * @generated
     * @ordered
     */
    RECONNECT_BOTH_LITERAL(2, "RECONNECT_BOTH", "RECONNECT_BOTH"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>RECONNECT TARGET</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>RECONNECT TARGET</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #RECONNECT_TARGET_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RECONNECT_TARGET = 0;

    /**
     * The '<em><b>RECONNECT SOURCE</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>RECONNECT SOURCE</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #RECONNECT_SOURCE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RECONNECT_SOURCE = 1;

    /**
     * The '<em><b>RECONNECT BOTH</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>RECONNECT BOTH</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #RECONNECT_BOTH_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int RECONNECT_BOTH = 2;

    /**
     * An array of all the '<em><b>Reconnection Kind</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ReconnectionKind[] VALUES_ARRAY = new ReconnectionKind[] { RECONNECT_TARGET_LITERAL, RECONNECT_SOURCE_LITERAL, RECONNECT_BOTH_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Reconnection Kind</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<ReconnectionKind> VALUES = Collections.unmodifiableList(Arrays.asList(ReconnectionKind.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Reconnection Kind</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ReconnectionKind get(String literal) {
        for (ReconnectionKind result : ReconnectionKind.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reconnection Kind</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ReconnectionKind getByName(String name) {
        for (ReconnectionKind result : ReconnectionKind.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Reconnection Kind</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ReconnectionKind get(int value) {
        switch (value) {
        case RECONNECT_TARGET:
            return RECONNECT_TARGET_LITERAL;
        case RECONNECT_SOURCE:
            return RECONNECT_SOURCE_LITERAL;
        case RECONNECT_BOTH:
            return RECONNECT_BOTH_LITERAL;
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
    private ReconnectionKind(int value, String name, String literal) {
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

} // ReconnectionKind
