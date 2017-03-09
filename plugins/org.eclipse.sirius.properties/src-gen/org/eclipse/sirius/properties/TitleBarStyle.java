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
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '<em><b>Title Bar Style</b></em>', and
 * utility methods for working with them. <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.properties.PropertiesPackage#getTitleBarStyle()
 * @model
 * @generated
 */
public enum TitleBarStyle implements Enumerator {
    /**
     * The '<em><b>TITLE BAR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #TITLE_BAR_VALUE
     * @generated
     * @ordered
     */
    TITLE_BAR(0, "TITLE_BAR", "TITLE_BAR"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>SHORT TITLE BAR</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #SHORT_TITLE_BAR_VALUE
     * @generated
     * @ordered
     */
    SHORT_TITLE_BAR(1, "SHORT_TITLE_BAR", "SHORT_TITLE_BAR"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>NO TITLE</b></em>' literal object. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #NO_TITLE_VALUE
     * @generated
     * @ordered
     */
    NO_TITLE(2, "NO_TITLE", "NO_TITLE"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>TITLE BAR</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>TITLE BAR</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #TITLE_BAR
     * @model
     * @generated
     * @ordered
     */
    public static final int TITLE_BAR_VALUE = 0;

    /**
     * The '<em><b>SHORT TITLE BAR</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>SHORT TITLE BAR</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #SHORT_TITLE_BAR
     * @model
     * @generated
     * @ordered
     */
    public static final int SHORT_TITLE_BAR_VALUE = 1;

    /**
     * The '<em><b>NO TITLE</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>NO TITLE</b></em>' literal object isn't clear, there really should be more of a
     * description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NO_TITLE
     * @model
     * @generated
     * @ordered
     */
    public static final int NO_TITLE_VALUE = 2;

    /**
     * An array of all the '<em><b>Title Bar Style</b></em>' enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final TitleBarStyle[] VALUES_ARRAY = new TitleBarStyle[] { TITLE_BAR, SHORT_TITLE_BAR, NO_TITLE, };

    /**
     * A public read-only list of all the '<em><b>Title Bar Style</b></em>' enumerators. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @generated
     */
    public static final List<TitleBarStyle> VALUES = Collections.unmodifiableList(Arrays.asList(TitleBarStyle.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Title Bar Style</b></em>' literal with the specified literal value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TitleBarStyle get(String literal) {
        for (TitleBarStyle result : TitleBarStyle.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Title Bar Style</b></em>' literal with the specified name. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TitleBarStyle getByName(String name) {
        for (TitleBarStyle result : TitleBarStyle.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Title Bar Style</b></em>' literal with the specified integer value. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static TitleBarStyle get(int value) {
        switch (value) {
        case TITLE_BAR_VALUE:
            return TITLE_BAR;
        case SHORT_TITLE_BAR_VALUE:
            return SHORT_TITLE_BAR;
        case NO_TITLE_VALUE:
            return NO_TITLE;
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
    private TitleBarStyle(int value, String name, String literal) {
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

} // TitleBarStyle
