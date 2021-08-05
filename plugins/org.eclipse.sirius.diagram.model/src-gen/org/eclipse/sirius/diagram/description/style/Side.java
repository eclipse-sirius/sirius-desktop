/**
 * Copyright (c) 2007, 2013 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.diagram.description.style;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Side</b></em>', and utility
 * methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.style.StylePackage#getSide()
 * @model
 * @generated
 */
public enum Side implements Enumerator {
    /**
     * The '<em><b>WEST</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #WEST_VALUE
     * @generated
     * @ordered
     */
    WEST(0, "WEST", "WEST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SOUTH</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #SOUTH_VALUE
     * @generated
     * @ordered
     */
    SOUTH(1, "SOUTH", "SOUTH"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>EAST</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #EAST_VALUE
     * @generated
     * @ordered
     */
    EAST(2, "EAST", "EAST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NORTH</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NORTH_VALUE
     * @generated
     * @ordered
     */
    NORTH(3, "NORTH", "NORTH"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>WEST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>WEST</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #WEST
     * @model
     * @generated
     * @ordered
     */
    public static final int WEST_VALUE = 0;

    /**
     * The '<em><b>SOUTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOUTH</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SOUTH
     * @model
     * @generated
     * @ordered
     */
    public static final int SOUTH_VALUE = 1;

    /**
     * The '<em><b>EAST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EAST</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #EAST
     * @model
     * @generated
     * @ordered
     */
    public static final int EAST_VALUE = 2;

    /**
     * The '<em><b>NORTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NORTH</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NORTH
     * @model
     * @generated
     * @ordered
     */
    public static final int NORTH_VALUE = 3;

    /**
     * An array of all the '<em><b>Side</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final Side[] VALUES_ARRAY = new Side[] { WEST, SOUTH, EAST, NORTH, };

    /**
     * A public read-only list of all the '<em><b>Side</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    public static final List<Side> VALUES = Collections.unmodifiableList(Arrays.asList(Side.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Side</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static Side get(String literal) {
        for (Side result : Side.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Side</b></em>' literal with the specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static Side getByName(String name) {
        for (Side result : Side.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Side</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static Side get(int value) {
        switch (value) {
        case WEST_VALUE:
            return WEST;
        case SOUTH_VALUE:
            return SOUTH;
        case EAST_VALUE:
            return EAST;
        case NORTH_VALUE:
            return NORTH;
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
     * Only this class can construct instances. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private Side(int value, String name, String literal) {
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
     * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // Side
