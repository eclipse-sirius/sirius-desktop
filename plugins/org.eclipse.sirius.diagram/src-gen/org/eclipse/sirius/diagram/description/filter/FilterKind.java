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
package org.eclipse.sirius.diagram.description.filter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Kind</b></em>', and utility methods for working with them. <!--
 * end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.filter.FilterPackage#getFilterKind()
 * @model
 * @generated
 */
public enum FilterKind implements Enumerator {
    /**
     * The '<em><b>HIDE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #HIDE
     * @generated
     * @ordered
     */
    HIDE_LITERAL(0, "HIDE", "HIDE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>COLLAPSE</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #COLLAPSE
     * @generated
     * @ordered
     */
    COLLAPSE_LITERAL(1, "COLLAPSE", "COLLAPSE"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>HIDE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>HIDE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #HIDE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int HIDE = 0;

    /**
     * The '<em><b>COLLAPSE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>COLLAPSE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #COLLAPSE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int COLLAPSE = 1;

    /**
     * An array of all the '<em><b>Kind</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final FilterKind[] VALUES_ARRAY = new FilterKind[] { HIDE_LITERAL, COLLAPSE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Kind</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<FilterKind> VALUES = Collections.unmodifiableList(Arrays.asList(FilterKind.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Kind</b></em>' literal with the specified literal
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FilterKind get(String literal) {
        for (FilterKind result : FilterKind.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Kind</b></em>' literal with the specified name. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static FilterKind getByName(String name) {
        for (FilterKind result : FilterKind.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Kind</b></em>' literal with the specified integer
     * value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FilterKind get(int value) {
        switch (value) {
        case HIDE:
            return HIDE_LITERAL;
        case COLLAPSE:
            return COLLAPSE_LITERAL;
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
    private FilterKind(int value, String name, String literal) {
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

} // FilterKind
