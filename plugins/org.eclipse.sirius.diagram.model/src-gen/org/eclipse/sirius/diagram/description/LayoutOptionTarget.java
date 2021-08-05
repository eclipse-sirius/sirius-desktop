/**
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 */
package org.eclipse.sirius.diagram.description;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Layout Option Target</b></em>',
 * and utility methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getLayoutOptionTarget()
 * @model
 * @generated
 */
public enum LayoutOptionTarget implements Enumerator {
    /**
     * The '<em><b>PARENT</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #PARENT_VALUE
     * @generated
     * @ordered
     */
    PARENT(0, "PARENT", "PARENT"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NODE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NODE_VALUE
     * @generated
     * @ordered
     */
    NODE(1, "NODE", "NODE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>EDGE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #EDGE_VALUE
     * @generated
     * @ordered
     */
    EDGE(2, "EDGE", "EDGE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PORTS</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #PORTS_VALUE
     * @generated
     * @ordered
     */
    PORTS(3, "PORTS", "PORTS"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>LABEL</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #LABEL_VALUE
     * @generated
     * @ordered
     */
    LABEL(4, "LABEL", "LABEL"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>PARENT</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PARENT</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #PARENT
     * @model
     * @generated
     * @ordered
     */
    public static final int PARENT_VALUE = 0;

    /**
     * The '<em><b>NODE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NODE</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NODE
     * @model
     * @generated
     * @ordered
     */
    public static final int NODE_VALUE = 1;

    /**
     * The '<em><b>EDGE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>EDGE</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #EDGE
     * @model
     * @generated
     * @ordered
     */
    public static final int EDGE_VALUE = 2;

    /**
     * The '<em><b>PORTS</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>PORTS</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #PORTS
     * @model
     * @generated
     * @ordered
     */
    public static final int PORTS_VALUE = 3;

    /**
     * The '<em><b>LABEL</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>LABEL</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LABEL
     * @model
     * @generated
     * @ordered
     */
    public static final int LABEL_VALUE = 4;

    /**
     * An array of all the '<em><b>Layout Option Target</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc
     * -->
     *
     * @generated
     */
    private static final LayoutOptionTarget[] VALUES_ARRAY = new LayoutOptionTarget[] { PARENT, NODE, EDGE, PORTS, LABEL, };

    /**
     * A public read-only list of all the '<em><b>Layout Option Target</b></em>' enumerators. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<LayoutOptionTarget> VALUES = Collections.unmodifiableList(Arrays.asList(LayoutOptionTarget.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Layout Option Target</b></em>' literal with the specified literal value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutOptionTarget get(String literal) {
        for (LayoutOptionTarget result : LayoutOptionTarget.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Option Target</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutOptionTarget getByName(String name) {
        for (LayoutOptionTarget result : LayoutOptionTarget.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Layout Option Target</b></em>' literal with the specified integer value. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static LayoutOptionTarget get(int value) {
        switch (value) {
        case PARENT_VALUE:
            return PARENT;
        case NODE_VALUE:
            return NODE;
        case EDGE_VALUE:
            return EDGE;
        case PORTS_VALUE:
            return PORTS;
        case LABEL_VALUE:
            return LABEL;
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
    private LayoutOptionTarget(int value, String name, String literal) {
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

} // LayoutOptionTarget
