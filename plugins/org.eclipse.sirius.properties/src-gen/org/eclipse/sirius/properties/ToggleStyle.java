/**
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *
 */
package org.eclipse.sirius.properties;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Toggle Style</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getToggleStyle()
 * @model
 * @generated
 */
public enum ToggleStyle implements Enumerator {
    /**
     * The '<em><b>TWISTIE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #TWISTIE_VALUE
     * @generated
     * @ordered
     */
    TWISTIE(0, "TWISTIE", "TWISTIE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TREE NODE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #TREE_NODE_VALUE
     * @generated
     * @ordered
     */
    TREE_NODE(1, "TREE_NODE", "TREE_NODE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NONE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(2, "NONE", "NONE"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TWISTIE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TWISTIE</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TWISTIE
     * @model
     * @generated
     * @ordered
     */
    public static final int TWISTIE_VALUE = 0;

    /**
     * The '<em><b>TREE NODE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TREE NODE</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TREE_NODE
     * @model
     * @generated
     * @ordered
     */
    public static final int TREE_NODE_VALUE = 1;

    /**
     * The '<em><b>NONE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear, there really should be more of a description
     * here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NONE
     * @model
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 2;

    /**
     * An array of all the '<em><b>Toggle Style</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ToggleStyle[] VALUES_ARRAY = new ToggleStyle[] { TWISTIE, TREE_NODE, NONE, };

    /**
     * A public read-only list of all the '<em><b>Toggle Style</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static final List<ToggleStyle> VALUES = Collections.unmodifiableList(Arrays.asList(ToggleStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Toggle Style</b></em>' literal with the specified literal value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ToggleStyle get(String literal) {
        for (ToggleStyle result : ToggleStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Toggle Style</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ToggleStyle getByName(String name) {
        for (ToggleStyle result : ToggleStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Toggle Style</b></em>' literal with the specified integer value. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ToggleStyle get(int value) {
        switch (value) {
        case TWISTIE_VALUE:
            return TWISTIE;
        case TREE_NODE_VALUE:
            return TREE_NODE;
        case NONE_VALUE:
            return NONE;
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
    private ToggleStyle(int value, String name, String literal) {
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

} // ToggleStyle
