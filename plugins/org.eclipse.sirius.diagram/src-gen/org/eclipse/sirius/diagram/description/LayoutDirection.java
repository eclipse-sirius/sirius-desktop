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
package org.eclipse.sirius.diagram.description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>Layout Direction</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayoutDirection()
 * @model
 * @generated
 */
public enum LayoutDirection implements Enumerator {
    /**
     * The '<em><b>Top To Bottom</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #TOP_TO_BOTTOM_VALUE
     * @generated
     * @ordered
     */
    TOP_TO_BOTTOM(0, "TopToBottom", "topToBottom"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Left To Right</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #LEFT_TO_RIGHT_VALUE
     * @generated
     * @ordered
     */
    LEFT_TO_RIGHT(1, "LeftToRight", "LeftToRight"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Bottom To Top</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #BOTTOM_TO_TOP_VALUE
     * @generated
     * @ordered
     */
    BOTTOM_TO_TOP(2, "BottomToTop", "bottomToTop"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Top To Bottom</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Top To Bottom</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TOP_TO_BOTTOM
     * @model name="TopToBottom" literal="topToBottom"
     * @generated
     * @ordered
     */
    public static final int TOP_TO_BOTTOM_VALUE = 0;

    /**
     * The '<em><b>Left To Right</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Left To Right</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LEFT_TO_RIGHT
     * @model name="LeftToRight"
     * @generated
     * @ordered
     */
    public static final int LEFT_TO_RIGHT_VALUE = 1;

    /**
     * The '<em><b>Bottom To Top</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Bottom To Top</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #BOTTOM_TO_TOP
     * @model name="BottomToTop" literal="bottomToTop"
     * @generated
     * @ordered
     */
    public static final int BOTTOM_TO_TOP_VALUE = 2;

    /**
     * An array of all the '<em><b>Layout Direction</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final LayoutDirection[] VALUES_ARRAY = new LayoutDirection[] { TOP_TO_BOTTOM, LEFT_TO_RIGHT, BOTTOM_TO_TOP, };

    /**
     * A public read-only list of all the '<em><b>Layout Direction</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<LayoutDirection> VALUES = Collections.unmodifiableList(Arrays.asList(LayoutDirection.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Layout Direction</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutDirection get(String literal) {
        for (LayoutDirection result : LayoutDirection.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Direction</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutDirection getByName(String name) {
        for (LayoutDirection result : LayoutDirection.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Direction</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutDirection get(int value) {
        switch (value) {
        case TOP_TO_BOTTOM_VALUE:
            return TOP_TO_BOTTOM;
        case LEFT_TO_RIGHT_VALUE:
            return LEFT_TO_RIGHT;
        case BOTTOM_TO_TOP_VALUE:
            return BOTTOM_TO_TOP;
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
    private LayoutDirection(int value, String name, String literal) {
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

} // LayoutDirection
