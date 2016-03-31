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
 * <em><b>Alignment Kind</b></em>', and utility methods for working with them.
 * <p>
 * There are three kinds of alignment :
 * <ul>
 * <li><b>HORIZONTAL</b> : All the gauges are aligned in a horizontal way.</li>
 * <li><b>VERTICAL</b> : All the gauge are aligned in a vertical way.</li>
 * <li><b>SQUARE</b> : All the gauges are positionned in a square way.</li>
 * </ul>
 * </p>
 * <p>
 * Samples with three gauges :
 * <ul>
 * <li>VERTICAL :</li>
 *
 * <pre>
 * &lt;code&gt;
 *    G
 *    G
 *    G
 * &lt;/code&gt;
 * </pre>
 *
 * <li>HORIZONTAL :</li>
 *
 * <pre>
 * &lt;code&gt;
 * GGG
 * &lt;/code&gt;
 * </pre>
 *
 * <li>SQUARE :</li>
 *
 * <pre>
 * &lt;code&gt;
 *    GG
 *    G
 * &lt;/code&gt;
 * </pre>
 *
 * </ul>
 * </p>
 * <!-- end-user-doc --> <!-- begin-model-doc --> Possible alignments of gauges.
 * <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getAlignmentKind()
 * @model
 * @generated
 */
public enum AlignmentKind implements Enumerator {
    /**
     * The '<em><b>VERTICAL</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #VERTICAL
     * @generated
     * @ordered
     */
    VERTICAL_LITERAL(0, "VERTICAL", "VERTICAL"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>HORIZONTAL</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #HORIZONTAL
     * @generated
     * @ordered
     */
    HORIZONTAL_LITERAL(1, "HORIZONTAL", "HORIZONTAL"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SQUARE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #SQUARE
     * @generated
     * @ordered
     */
    SQUARE_LITERAL(2, "SQUARE", "SQUARE"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>VERTICAL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>VERTICAL</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #VERTICAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int VERTICAL = 0;

    /**
     * The '<em><b>HORIZONTAL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>HORIZONTAL</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #HORIZONTAL_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int HORIZONTAL = 1;

    /**
     * The '<em><b>SQUARE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SQUARE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SQUARE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SQUARE = 2;

    /**
     * An array of all the '<em><b>Alignment Kind</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final AlignmentKind[] VALUES_ARRAY = new AlignmentKind[] { VERTICAL_LITERAL, HORIZONTAL_LITERAL, SQUARE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Alignment Kind</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<AlignmentKind> VALUES = Collections.unmodifiableList(Arrays.asList(AlignmentKind.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Alignment Kind</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static AlignmentKind get(String literal) {
        for (AlignmentKind result : AlignmentKind.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Alignment Kind</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static AlignmentKind getByName(String name) {
        for (AlignmentKind result : AlignmentKind.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Alignment Kind</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static AlignmentKind get(int value) {
        switch (value) {
        case VERTICAL:
            return VERTICAL_LITERAL;
        case HORIZONTAL:
            return HORIZONTAL_LITERAL;
        case SQUARE:
            return SQUARE_LITERAL;
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
    private AlignmentKind(int value, String name, String literal) {
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

} // AlignmentKind
