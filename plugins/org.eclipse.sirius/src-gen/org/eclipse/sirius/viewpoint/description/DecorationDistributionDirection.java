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
package org.eclipse.sirius.viewpoint.description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Decoration Distribution
 * Direction</b></em>', and utility methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.DescriptionPackage#getDecorationDistributionDirection()
 * @model
 * @generated
 */
public enum DecorationDistributionDirection implements Enumerator {
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
     * An array of all the '<em><b>Decoration Distribution Direction</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    private static final DecorationDistributionDirection[] VALUES_ARRAY = new DecorationDistributionDirection[] { VERTICAL, HORIZONTAL, };

    /**
     * A public read-only list of all the ' <em><b>Decoration Distribution Direction</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<DecorationDistributionDirection> VALUES = Collections.unmodifiableList(Arrays.asList(DecorationDistributionDirection.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Decoration Distribution Direction</b></em>' literal with the specified literal value. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DecorationDistributionDirection get(String literal) {
        for (DecorationDistributionDirection result : DecorationDistributionDirection.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Decoration Distribution Direction</b></em>' literal with the specified name. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DecorationDistributionDirection getByName(String name) {
        for (DecorationDistributionDirection result : DecorationDistributionDirection.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Decoration Distribution Direction</b></em>' literal with the specified integer value. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static DecorationDistributionDirection get(int value) {
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
    private DecorationDistributionDirection(int value, String name, String literal) {
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

} // DecorationDistributionDirection
