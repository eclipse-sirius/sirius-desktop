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
 * <em><b>Label Position</b></em>', and utility methods for working with them.
 * <!-- end-user-doc --> <!-- begin-model-doc --> Position of the label. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getLabelPosition()
 * @model
 * @generated
 */
public enum LabelPosition implements Enumerator {
    /**
     * The '<em><b>Border</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #BORDER
     * @generated
     * @ordered
     */
    BORDER_LITERAL(0, "border", "border"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Node</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #NODE
     * @generated
     * @ordered
     */
    NODE_LITERAL(1, "node", "node"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Border</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The label is on border. <!--
     * end-model-doc -->
     *
     * @see #BORDER_LITERAL
     * @model name="border"
     * @generated
     * @ordered
     */
    public static final int BORDER = 0;

    /**
     * The '<em><b>Node</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> The label is on the node. <!--
     * end-model-doc -->
     *
     * @see #NODE_LITERAL
     * @model name="node"
     * @generated
     * @ordered
     */
    public static final int NODE = 1;

    /**
     * An array of all the '<em><b>Label Position</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final LabelPosition[] VALUES_ARRAY = new LabelPosition[] { BORDER_LITERAL, NODE_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Label Position</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<LabelPosition> VALUES = Collections.unmodifiableList(Arrays.asList(LabelPosition.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Label Position</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LabelPosition get(String literal) {
        for (LabelPosition result : LabelPosition.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Label Position</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LabelPosition getByName(String name) {
        for (LabelPosition result : LabelPosition.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Label Position</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LabelPosition get(int value) {
        switch (value) {
        case BORDER:
            return BORDER_LITERAL;
        case NODE:
            return NODE_LITERAL;
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
    private LabelPosition(int value, String name, String literal) {
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

} // LabelPosition
