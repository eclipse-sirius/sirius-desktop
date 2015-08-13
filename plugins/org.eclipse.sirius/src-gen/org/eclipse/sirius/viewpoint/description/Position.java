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
package org.eclipse.sirius.viewpoint.description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Position</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 * 
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getPosition()
 * @model
 * @generated
 */
public enum Position implements Enumerator {
    /**
     * The '<em><b>NORTH</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #NORTH
     * @generated
     * @ordered
     */
    NORTH_LITERAL(0, "NORTH", "NORTH"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>WEST</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #WEST
     * @generated
     * @ordered
     */
    WEST_LITERAL(1, "WEST", "WEST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SOUTH</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #SOUTH
     * @generated
     * @ordered
     */
    SOUTH_LITERAL(2, "SOUTH", "SOUTH"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>EAST</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #EAST
     * @generated
     * @ordered
     */
    EAST_LITERAL(3, "EAST", "EAST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NORTH WEST</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #NORTH_WEST
     * @generated
     * @ordered
     */
    NORTH_WEST_LITERAL(4, "NORTH_WEST", "NORTH_WEST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NORTH EAST</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #NORTH_EAST
     * @generated
     * @ordered
     */
    NORTH_EAST_LITERAL(5, "NORTH_EAST", "NORTH_EAST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SOUTH WEST</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #SOUTH_WEST
     * @generated
     * @ordered
     */
    SOUTH_WEST_LITERAL(6, "SOUTH_WEST", "SOUTH_WEST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SOUTH EAST</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #SOUTH_EAST
     * @generated
     * @ordered
     */
    SOUTH_EAST_LITERAL(7, "SOUTH_EAST", "SOUTH_EAST"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>CENTER</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #CENTER
     * @generated
     * @ordered
     */
    CENTER_LITERAL(8, "CENTER", "CENTER"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NORTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NORTH</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NORTH_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NORTH = 0;

    /**
     * The '<em><b>WEST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>WEST</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #WEST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int WEST = 1;

    /**
     * The '<em><b>SOUTH</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOUTH</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SOUTH_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SOUTH = 2;

    /**
     * The '<em><b>EAST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EAST</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #EAST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int EAST = 3;

    /**
     * The '<em><b>NORTH WEST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NORTH WEST</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NORTH_WEST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NORTH_WEST = 4;

    /**
     * The '<em><b>NORTH EAST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NORTH EAST</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #NORTH_EAST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NORTH_EAST = 5;

    /**
     * The '<em><b>SOUTH WEST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOUTH WEST</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SOUTH_WEST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SOUTH_WEST = 6;

    /**
     * The '<em><b>SOUTH EAST</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOUTH EAST</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #SOUTH_EAST_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SOUTH_EAST = 7;

    /**
     * The '<em><b>CENTER</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>CENTER</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     * 
     * @see #CENTER_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int CENTER = 8;

    /**
     * An array of all the '<em><b>Position</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final Position[] VALUES_ARRAY = new Position[] { NORTH_LITERAL, WEST_LITERAL, SOUTH_LITERAL, EAST_LITERAL, NORTH_WEST_LITERAL, NORTH_EAST_LITERAL, SOUTH_WEST_LITERAL,
        SOUTH_EAST_LITERAL, CENTER_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Position</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<Position> VALUES = Collections.unmodifiableList(Arrays.asList(Position.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Position</b></em>' literal with the specified literal
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Position get(String literal) {
        for (Position result : Position.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Position</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Position getByName(String name) {
        for (Position result : Position.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Position</b></em>' literal with the specified integer
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static Position get(int value) {
        switch (value) {
        case NORTH:
            return NORTH_LITERAL;
        case WEST:
            return WEST_LITERAL;
        case SOUTH:
            return SOUTH_LITERAL;
        case EAST:
            return EAST_LITERAL;
        case NORTH_WEST:
            return NORTH_WEST_LITERAL;
        case NORTH_EAST:
            return NORTH_EAST_LITERAL;
        case SOUTH_WEST:
            return SOUTH_WEST_LITERAL;
        case SOUTH_EAST:
            return SOUTH_EAST_LITERAL;
        case CENTER:
            return CENTER_LITERAL;
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
    private Position(int value, String name, String literal) {
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

} // Position
