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
package org.eclipse.sirius.diagram;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration ' <em><b>Bundled Image Shape</b></em>',
 * and utility methods for working with them. <!-- end-user-doc --> <!-- begin-model-doc --> Default shapes. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getBundledImageShape()
 * @model
 * @generated
 */
public enum BundledImageShape implements Enumerator {
    /**
     * The '<em><b>Square</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #SQUARE
     * @generated
     * @ordered
     */
    SQUARE_LITERAL(0, "square", "square"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Stroke</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #STROKE
     * @generated
     * @ordered
     */
    STROKE_LITERAL(1, "stroke", "stroke"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Triangle</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #TRIANGLE
     * @generated
     * @ordered
     */
    TRIANGLE_LITERAL(3, "triangle", "triangle"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Dot</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #DOT
     * @generated
     * @ordered
     */
    DOT_LITERAL(4, "dot", "dot"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Ring</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #RING
     * @generated
     * @ordered
     */
    RING_LITERAL(5, "ring", "ring"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Provided Shape</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #PROVIDED_SHAPE
     * @generated
     * @ordered
     */
    PROVIDED_SHAPE_LITERAL(6, "providedShape", "providedShape"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Square</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Square</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SQUARE_LITERAL
     * @model name="square"
     * @generated
     * @ordered
     */
    public static final int SQUARE = 0;

    /**
     * The '<em><b>Stroke</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Stroke</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #STROKE_LITERAL
     * @model name="stroke"
     * @generated
     * @ordered
     */
    public static final int STROKE = 1;

    /**
     * The '<em><b>Triangle</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Triangle</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TRIANGLE_LITERAL
     * @model name="triangle"
     * @generated
     * @ordered
     */
    public static final int TRIANGLE = 3;

    /**
     * The '<em><b>Dot</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Dot</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #DOT_LITERAL
     * @model name="dot"
     * @generated
     * @ordered
     */
    public static final int DOT = 4;

    /**
     * The '<em><b>Ring</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Ring</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #RING_LITERAL
     * @model name="ring"
     * @generated
     * @ordered
     */
    public static final int RING = 5;

    /**
     * The '<em><b>Provided Shape</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Provided Shape</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #PROVIDED_SHAPE_LITERAL
     * @model name="providedShape"
     * @generated
     * @ordered
     */
    public static final int PROVIDED_SHAPE = 6;

    /**
     * An array of all the '<em><b>Bundled Image Shape</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    private static final BundledImageShape[] VALUES_ARRAY = new BundledImageShape[] { SQUARE_LITERAL, STROKE_LITERAL, TRIANGLE_LITERAL, DOT_LITERAL, RING_LITERAL, PROVIDED_SHAPE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Bundled Image Shape</b></em>' enumerators. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<BundledImageShape> VALUES = Collections.unmodifiableList(Arrays.asList(BundledImageShape.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the specified literal value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
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
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
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
     * Returns the '<em><b>Bundled Image Shape</b></em>' literal with the specified integer value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static BundledImageShape get(int value) {
        switch (value) {
        case SQUARE:
            return SQUARE_LITERAL;
        case STROKE:
            return STROKE_LITERAL;
        case TRIANGLE:
            return TRIANGLE_LITERAL;
        case DOT:
            return DOT_LITERAL;
        case RING:
            return RING_LITERAL;
        case PROVIDED_SHAPE:
            return PROVIDED_SHAPE_LITERAL;
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
     * Returns the literal value of the enumerator, which is its string representation. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    @Override
    public String toString() {
        return literal;
    }

} // BundledImageShape
