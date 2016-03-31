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
 * <em><b>Background Style</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc --> Style of the background of a
 * container. <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getBackgroundStyle()
 * @model
 * @generated
 */
public enum BackgroundStyle implements Enumerator {
    /**
     * The '<em><b>Gradient Left To Right</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #GRADIENT_LEFT_TO_RIGHT
     * @generated
     * @ordered
     */
    GRADIENT_LEFT_TO_RIGHT_LITERAL(0, "GradientLeftToRight", "GradientLeftToRight"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Liquid</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #LIQUID
     * @generated
     * @ordered
     */
    LIQUID_LITERAL(1, "Liquid", "Liquid"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Gradient Top To Bottom</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #GRADIENT_TOP_TO_BOTTOM
     * @generated
     * @ordered
     */
    GRADIENT_TOP_TO_BOTTOM_LITERAL(3, "GradientTopToBottom", "GradientTopToBottom"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Gradient Left To Right</b></em>' literal value. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Gradient style. <!-- end-model-doc -->
     *
     * @see #GRADIENT_LEFT_TO_RIGHT_LITERAL
     * @model name="GradientLeftToRight"
     * @generated
     * @ordered
     */
    public static final int GRADIENT_LEFT_TO_RIGHT = 0;

    /**
     * The '<em><b>Liquid</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Liquid style. <!--
     * end-model-doc -->
     *
     * @see #LIQUID_LITERAL
     * @model name="Liquid"
     * @generated
     * @ordered
     */
    public static final int LIQUID = 1;

    /**
     * The '<em><b>Gradient Top To Bottom</b></em>' literal value. <!--
     * begin-user-doc --> <!-- end-user-doc --> <!-- begin-model-doc -->
     * Gradient style. <!-- end-model-doc -->
     *
     * @see #GRADIENT_TOP_TO_BOTTOM_LITERAL
     * @model name="GradientTopToBottom"
     * @generated
     * @ordered
     */
    public static final int GRADIENT_TOP_TO_BOTTOM = 3;

    /**
     * An array of all the '<em><b>Background Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final BackgroundStyle[] VALUES_ARRAY = new BackgroundStyle[] { GRADIENT_LEFT_TO_RIGHT_LITERAL, LIQUID_LITERAL, GRADIENT_TOP_TO_BOTTOM_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Background Style</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<BackgroundStyle> VALUES = Collections.unmodifiableList(Arrays.asList(BackgroundStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Background Style</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BackgroundStyle get(String literal) {
        for (BackgroundStyle result : BackgroundStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Background Style</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BackgroundStyle getByName(String name) {
        for (BackgroundStyle result : BackgroundStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Background Style</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BackgroundStyle get(int value) {
        switch (value) {
        case GRADIENT_LEFT_TO_RIGHT:
            return GRADIENT_LEFT_TO_RIGHT_LITERAL;
        case LIQUID:
            return LIQUID_LITERAL;
        case GRADIENT_TOP_TO_BOTTOM:
            return GRADIENT_TOP_TO_BOTTOM_LITERAL;
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
    private BackgroundStyle(int value, String name, String literal) {
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

} // BackgroundStyle
