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
 * <em><b>Centering Style</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.description.DescriptionPackage#getCenteringStyle()
 * @model
 * @generated
 */
public enum CenteringStyle implements Enumerator {
    /**
     * The '<em><b>None</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #NONE_VALUE
     * @generated
     * @ordered
     */
    NONE(0, "None", "None"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Both</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #BOTH_VALUE
     * @generated
     * @ordered
     */
    BOTH(1, "Both", "Both"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Source</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #SOURCE_VALUE
     * @generated
     * @ordered
     */
    SOURCE(2, "Source", "Source"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Target</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #TARGET_VALUE
     * @generated
     * @ordered
     */
    TARGET(3, "Target", "Target"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>None</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>None</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NONE
     * @model name="None"
     * @generated
     * @ordered
     */
    public static final int NONE_VALUE = 0;

    /**
     * The '<em><b>Both</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Both</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #BOTH
     * @model name="Both"
     * @generated
     * @ordered
     */
    public static final int BOTH_VALUE = 1;

    /**
     * The '<em><b>Source</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Source</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SOURCE
     * @model name="Source"
     * @generated
     * @ordered
     */
    public static final int SOURCE_VALUE = 2;

    /**
     * The '<em><b>Target</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Target</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TARGET
     * @model name="Target"
     * @generated
     * @ordered
     */
    public static final int TARGET_VALUE = 3;

    /**
     * An array of all the '<em><b>Centering Style</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final CenteringStyle[] VALUES_ARRAY = new CenteringStyle[] { NONE, BOTH, SOURCE, TARGET, };

    /**
     * A public read-only list of all the '<em><b>Centering Style</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<CenteringStyle> VALUES = Collections.unmodifiableList(Arrays.asList(CenteringStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Centering Style</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static CenteringStyle get(String literal) {
        for (CenteringStyle result : CenteringStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Centering Style</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static CenteringStyle getByName(String name) {
        for (CenteringStyle result : CenteringStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Centering Style</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static CenteringStyle get(int value) {
        switch (value) {
        case NONE_VALUE:
            return NONE;
        case BOTH_VALUE:
            return BOTH;
        case SOURCE_VALUE:
            return SOURCE;
        case TARGET_VALUE:
            return TARGET;
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
    private CenteringStyle(int value, String name, String literal) {
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

} // CenteringStyle
