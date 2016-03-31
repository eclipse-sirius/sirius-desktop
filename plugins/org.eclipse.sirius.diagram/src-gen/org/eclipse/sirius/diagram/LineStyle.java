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
 * <em><b>Line Style</b></em>', and utility methods for working with them. <!--
 * end-user-doc --> <!-- begin-model-doc --> Style of a connection. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getLineStyle()
 * @model
 * @generated
 */
public enum LineStyle implements Enumerator {
    /**
     * The '<em><b>Solid</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #SOLID
     * @generated
     * @ordered
     */
    SOLID_LITERAL(0, "solid", "solid"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Dash</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #DASH
     * @generated
     * @ordered
     */
    DASH_LITERAL(1, "dash", "dash"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Dot</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #DOT
     * @generated
     * @ordered
     */
    DOT_LITERAL(2, "dot", "dot"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Dash dot</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #DASH_DOT
     * @generated
     * @ordered
     */
    DASH_DOT_LITERAL(3, "dash_dot", "dash_dot"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Solid</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Solid</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SOLID_LITERAL
     * @model name="solid"
     * @generated
     * @ordered
     */
    public static final int SOLID = 0;

    /**
     * The '<em><b>Dash</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dash</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DASH_LITERAL
     * @model name="dash"
     * @generated
     * @ordered
     */
    public static final int DASH = 1;

    /**
     * The '<em><b>Dot</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dot</b></em>' literal object isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DOT_LITERAL
     * @model name="dot"
     * @generated
     * @ordered
     */
    public static final int DOT = 2;

    /**
     * The '<em><b>Dash dot</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dash dot</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DASH_DOT_LITERAL
     * @model name="dash_dot"
     * @generated
     * @ordered
     */
    public static final int DASH_DOT = 3;

    /**
     * An array of all the '<em><b>Line Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final LineStyle[] VALUES_ARRAY = new LineStyle[] { SOLID_LITERAL, DASH_LITERAL, DOT_LITERAL, DASH_DOT_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Line Style</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<LineStyle> VALUES = Collections.unmodifiableList(Arrays.asList(LineStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineStyle get(String literal) {
        for (LineStyle result : LineStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified name.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineStyle getByName(String name) {
        for (LineStyle result : LineStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Line Style</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LineStyle get(int value) {
        switch (value) {
        case SOLID:
            return SOLID_LITERAL;
        case DASH:
            return DASH_LITERAL;
        case DOT:
            return DOT_LITERAL;
        case DASH_DOT:
            return DASH_DOT_LITERAL;
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
    private LineStyle(int value, String name, String literal) {
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

} // LineStyle
