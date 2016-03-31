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
 * <em><b>Arrange Constraint</b></em>', and utility methods for working with
 * them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getArrangeConstraint()
 * @model
 * @generated
 */
public enum ArrangeConstraint implements Enumerator {
    /**
     * The '<em><b>KEEP LOCATION</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #KEEP_LOCATION_VALUE
     * @generated
     * @ordered
     */
    KEEP_LOCATION(0, "KEEP_LOCATION", "KEEP_LOCATION"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>KEEP SIZE</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #KEEP_SIZE_VALUE
     * @generated
     * @ordered
     */
    KEEP_SIZE(1, "KEEP_SIZE", "KEEP_SIZE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>KEEP RATIO</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #KEEP_RATIO_VALUE
     * @generated
     * @ordered
     */
    KEEP_RATIO(2, "KEEP_RATIO", "KEEP_RATIO"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>KEEP LOCATION</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>KEEP LOCATION</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #KEEP_LOCATION
     * @model
     * @generated
     * @ordered
     */
    public static final int KEEP_LOCATION_VALUE = 0;

    /**
     * The '<em><b>KEEP SIZE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>KEEP SIZE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #KEEP_SIZE
     * @model
     * @generated
     * @ordered
     */
    public static final int KEEP_SIZE_VALUE = 1;

    /**
     * The '<em><b>KEEP RATIO</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>KEEP RATIO</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #KEEP_RATIO
     * @model
     * @generated
     * @ordered
     */
    public static final int KEEP_RATIO_VALUE = 2;

    /**
     * An array of all the '<em><b>Arrange Constraint</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ArrangeConstraint[] VALUES_ARRAY = new ArrangeConstraint[] { KEEP_LOCATION, KEEP_SIZE, KEEP_RATIO, };

    /**
     * A public read-only list of all the '<em><b>Arrange Constraint</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<ArrangeConstraint> VALUES = Collections.unmodifiableList(Arrays.asList(ArrangeConstraint.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Arrange Constraint</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ArrangeConstraint get(String literal) {
        for (ArrangeConstraint result : ArrangeConstraint.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Arrange Constraint</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ArrangeConstraint getByName(String name) {
        for (ArrangeConstraint result : ArrangeConstraint.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Arrange Constraint</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ArrangeConstraint get(int value) {
        switch (value) {
        case KEEP_LOCATION_VALUE:
            return KEEP_LOCATION;
        case KEEP_SIZE_VALUE:
            return KEEP_SIZE;
        case KEEP_RATIO_VALUE:
            return KEEP_RATIO;
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
    private ArrangeConstraint(int value, String name, String literal) {
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

} // ArrangeConstraint
