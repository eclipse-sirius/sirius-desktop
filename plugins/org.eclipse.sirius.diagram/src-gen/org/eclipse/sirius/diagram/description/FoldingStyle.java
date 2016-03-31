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
 * <em><b>Folding Style</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getFoldingStyle()
 * @model
 * @generated
 */
public enum FoldingStyle implements Enumerator {
    /**
     * The '<em><b>NONE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #NONE
     * @generated
     * @ordered
     */
    NONE_LITERAL(0, "NONE", "NONE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SOURCE</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #SOURCE
     * @generated
     * @ordered
     */
    SOURCE_LITERAL(1, "SOURCE", "SOURCE"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TARGET</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #TARGET
     * @generated
     * @ordered
     */
    TARGET_LITERAL(2, "TARGET", "TARGET"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NONE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NONE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NONE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int NONE = 0;

    /**
     * The '<em><b>SOURCE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SOURCE</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SOURCE_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int SOURCE = 1;

    /**
     * The '<em><b>TARGET</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TARGET</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TARGET_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int TARGET = 2;

    /**
     * An array of all the '<em><b>Folding Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final FoldingStyle[] VALUES_ARRAY = new FoldingStyle[] { NONE_LITERAL, SOURCE_LITERAL, TARGET_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Folding Style</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<FoldingStyle> VALUES = Collections.unmodifiableList(Arrays.asList(FoldingStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Folding Style</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FoldingStyle get(String literal) {
        for (FoldingStyle result : FoldingStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Folding Style</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FoldingStyle getByName(String name) {
        for (FoldingStyle result : FoldingStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Folding Style</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static FoldingStyle get(int value) {
        switch (value) {
        case NONE:
            return NONE_LITERAL;
        case SOURCE:
            return SOURCE_LITERAL;
        case TARGET:
            return TARGET_LITERAL;
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
    private FoldingStyle(int value, String name, String literal) {
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

} // FoldingStyle
