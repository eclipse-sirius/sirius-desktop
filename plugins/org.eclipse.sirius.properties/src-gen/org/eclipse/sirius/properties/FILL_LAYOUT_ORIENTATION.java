/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>FILL LAYOUT ORIENTATION</b></em>
 * ', and utility methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getFILL_LAYOUT_ORIENTATION()
 * @model
 * @generated
 */
public enum FILL_LAYOUT_ORIENTATION implements Enumerator {
    /**
     * The '<em><b>VERTICAL</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #VERTICAL_VALUE
     * @generated
     * @ordered
     */
    VERTICAL(0, "VERTICAL", "VERTICAL"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>HORIZONTAL</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #HORIZONTAL_VALUE
     * @generated
     * @ordered
     */
    HORIZONTAL(0, "HORIZONTAL", "HORIZONTAL"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>VERTICAL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>VERTICAL</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #VERTICAL
     * @model
     * @generated
     * @ordered
     */
    public static final int VERTICAL_VALUE = 0;

    /**
     * The '<em><b>HORIZONTAL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>HORIZONTAL</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #HORIZONTAL
     * @model
     * @generated
     * @ordered
     */
    public static final int HORIZONTAL_VALUE = 0;

    /**
     * An array of all the '<em><b>FILL LAYOUT ORIENTATION</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    private static final FILL_LAYOUT_ORIENTATION[] VALUES_ARRAY = new FILL_LAYOUT_ORIENTATION[] { VERTICAL, HORIZONTAL, };

    /**
     * A public read-only list of all the '<em><b>FILL LAYOUT ORIENTATION</b></em>' enumerators. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<FILL_LAYOUT_ORIENTATION> VALUES = Collections.unmodifiableList(Arrays.asList(FILL_LAYOUT_ORIENTATION.VALUES_ARRAY));

    /**
     * Returns the '<em><b>FILL LAYOUT ORIENTATION</b></em>' literal with the specified literal value. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FILL_LAYOUT_ORIENTATION get(String literal) {
        for (FILL_LAYOUT_ORIENTATION result : FILL_LAYOUT_ORIENTATION.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>FILL LAYOUT ORIENTATION</b></em>' literal with the specified name. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FILL_LAYOUT_ORIENTATION getByName(String name) {
        for (FILL_LAYOUT_ORIENTATION result : FILL_LAYOUT_ORIENTATION.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>FILL LAYOUT ORIENTATION</b></em>' literal with the specified integer value. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FILL_LAYOUT_ORIENTATION get(int value) {
        switch (value) {
        case VERTICAL_VALUE:
            return VERTICAL;
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
    private FILL_LAYOUT_ORIENTATION(int value, String name, String literal) {
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

} // FILL_LAYOUT_ORIENTATION
