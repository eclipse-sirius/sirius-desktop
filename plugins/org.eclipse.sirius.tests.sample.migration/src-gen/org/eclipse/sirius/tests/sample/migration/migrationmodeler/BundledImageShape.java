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
 * <em><b>Bundled Image Shape</b></em>', and utility methods for working with
 * them. <!-- end-user-doc --> <!-- begin-model-doc --> Default shapes. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getBundledImageShape()
 * @model
 * @generated
 */
public enum BundledImageShape implements Enumerator {
    /**
     * The '<em><b>Square</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #SQUARE_VALUE
     * @generated
     * @ordered
     */
    SQUARE(0, "square", "square"),

    /**
     * The '<em><b>Stroke</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #STROKE_VALUE
     * @generated
     * @ordered
     */
    STROKE(1, "stroke", "stroke"),

    /**
     * The '<em><b>Triangle</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #TRIANGLE_VALUE
     * @generated
     * @ordered
     */
    TRIANGLE(3, "triangle", "triangle"),

    /**
     * The '<em><b>Dot</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #DOT_VALUE
     * @generated
     * @ordered
     */
    DOT(4, "dot", "dot"),

    /**
     * The '<em><b>Ring</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #RING_VALUE
     * @generated
     * @ordered
     */
    RING(5, "ring", "ring");

    /**
     * The '<em><b>Square</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Square</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SQUARE
     * @model name="square"
     * @generated
     * @ordered
     */
    public static final int SQUARE_VALUE = 0;

    /**
     * The '<em><b>Stroke</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Stroke</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #STROKE
     * @model name="stroke"
     * @generated
     * @ordered
     */
    public static final int STROKE_VALUE = 1;

    /**
     * The '<em><b>Triangle</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Triangle</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TRIANGLE
     * @model name="triangle"
     * @generated
     * @ordered
     */
    public static final int TRIANGLE_VALUE = 3;

    /**
     * The '<em><b>Dot</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dot</b></em>' literal object isn't clear, there
     * really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DOT
     * @model name="dot"
     * @generated
     * @ordered
     */
    public static final int DOT_VALUE = 4;

    /**
     * The '<em><b>Ring</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ring</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #RING
     * @model name="ring"
     * @generated
     * @ordered
     */
    public static final int RING_VALUE = 5;

    /**
     * An array of all the '<em><b>Bundled Image Shape</b></em>' enumerators.
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final BundledImageShape[] VALUES_ARRAY = new BundledImageShape[] { SQUARE, STROKE, TRIANGLE, DOT, RING, };

    /**
     * A public read-only list of all the '<em><b>Bundled Image Shape</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<BundledImageShape> VALUES = Collections.unmodifiableList(Arrays.asList(BundledImageShape.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the
     * specified literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BundledImageShape get(String literal) {
        for (BundledImageShape result : BundledImageShape.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the
     * specified name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BundledImageShape getByName(String name) {
        for (BundledImageShape result : BundledImageShape.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the
     * specified integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static BundledImageShape get(int value) {
        switch (value) {
        case SQUARE_VALUE:
            return SQUARE;
        case STROKE_VALUE:
            return STROKE;
        case TRIANGLE_VALUE:
            return TRIANGLE;
        case DOT_VALUE:
            return DOT;
        case RING_VALUE:
            return RING;
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
    private BundledImageShape(int value, String name, String literal) {
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

} // BundledImageShape
