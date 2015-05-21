/*******************************************************************************
 * Copyright (c) 2007-2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.sample.migration.migrationmodeler;

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
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBackgroundStyle()
 * @model
 * @generated
 */
public enum BackgroundStyle implements Enumerator {
    /**
     * The '<em><b>Gradient Left To Right</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #GRADIENT_LEFT_TO_RIGHT_VALUE
     * @generated
     * @ordered
     */
    GRADIENT_LEFT_TO_RIGHT(0, "GradientLeftToRight", "GradientLeftToRight"),

    /**
     * The '<em><b>Liquid</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #LIQUID_VALUE
     * @generated
     * @ordered
     */
    LIQUID(1, "Liquid", "Liquid"), /**
     * The '
     * <em><b>Gradient Top To Bottom</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #GRADIENT_TOP_TO_BOTTOM_VALUE
     * @generated
     * @ordered
     */
    GRADIENT_TOP_TO_BOTTOM(3, "GradientTopToBottom", "GradientTopToBottom");

    /**
     * The '<em><b>Gradient Left To Right</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Gradient Left To Right</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #GRADIENT_LEFT_TO_RIGHT
     * @model name="gradientLeftToRight"
     * @generated
     * @ordered
     */
    public static final int GRADIENT_LEFT_TO_RIGHT_VALUE = 0;

    /**
     * The '<em><b>Liquid</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Liquid style. <!--
     * end-model-doc -->
     *
     * @see #LIQUID
     * @model name="Liquid"
     * @generated
     * @ordered
     */
    public static final int LIQUID_VALUE = 1;

    /**
     * The '<em><b>Gradient Top To Bottom</b></em>' literal value. <!--
     * begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Gradient Top To Bottom</b></em>' literal object
     * isn't clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #GRADIENT_TOP_TO_BOTTOM
     * @model name="gradientTopToBottom"
     * @generated
     * @ordered
     */
    public static final int GRADIENT_TOP_TO_BOTTOM_VALUE = 3;

    /**
     * An array of all the '<em><b>Background Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final BackgroundStyle[] VALUES_ARRAY = new BackgroundStyle[] { GRADIENT_LEFT_TO_RIGHT, LIQUID, GRADIENT_TOP_TO_BOTTOM, };

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
     * @generated
     */
    public static BackgroundStyle get(int value) {
        switch (value) {
        case GRADIENT_LEFT_TO_RIGHT_VALUE:
            return GRADIENT_LEFT_TO_RIGHT;
        case LIQUID_VALUE:
            return LIQUID;
        case GRADIENT_TOP_TO_BOTTOM_VALUE:
            return GRADIENT_TOP_TO_BOTTOM;
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
