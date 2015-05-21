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
 * <em><b>Font Format</b></em>', and utility methods for working with them. <!--
 * end-user-doc --> <!-- begin-model-doc --> The format of the font. <!--
 * end-model-doc -->
 *
 * @see org.eclipse.sirius.tests.sample.migration.migrationmodeler.MigrationmodelerPackage#getFontFormat()
 * @model
 * @generated
 */
public enum FontFormat implements Enumerator {
    /**
     * The '<em><b>Normal</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #NORMAL_VALUE
     * @generated
     * @ordered
     */
    NORMAL(0, "normal", "normal"),

    /**
     * The '<em><b>Italic</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #ITALIC_VALUE
     * @generated
     * @ordered
     */
    ITALIC(1, "italic", "italic"),

    /**
     * The '<em><b>Bold</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #BOLD_VALUE
     * @generated
     * @ordered
     */
    BOLD(2, "bold", "bold"),

    /**
     * The '<em><b>Bold italic</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #BOLD_ITALIC_VALUE
     * @generated
     * @ordered
     */
    BOLD_ITALIC(3, "bold_italic", "bold_italic");

    /**
     * The '<em><b>Normal</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Normal</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #NORMAL
     * @model name="normal"
     * @generated
     * @ordered
     */
    public static final int NORMAL_VALUE = 0;

    /**
     * The '<em><b>Italic</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Italic</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #ITALIC
     * @model name="italic"
     * @generated
     * @ordered
     */
    public static final int ITALIC_VALUE = 1;

    /**
     * The '<em><b>Bold</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Bold</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #BOLD
     * @model name="bold"
     * @generated
     * @ordered
     */
    public static final int BOLD_VALUE = 2;

    /**
     * The '<em><b>Bold italic</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Bold italic</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #BOLD_ITALIC
     * @model name="bold_italic"
     * @generated
     * @ordered
     */
    public static final int BOLD_ITALIC_VALUE = 3;

    /**
     * An array of all the '<em><b>Font Format</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final FontFormat[] VALUES_ARRAY = new FontFormat[] { NORMAL, ITALIC, BOLD, BOLD_ITALIC, };

    /**
     * A public read-only list of all the '<em><b>Font Format</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<FontFormat> VALUES = Collections.unmodifiableList(Arrays.asList(FontFormat.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Font Format</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static FontFormat get(String literal) {
        for (FontFormat result : FontFormat.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Font Format</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static FontFormat getByName(String name) {
        for (FontFormat result : FontFormat.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Font Format</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static FontFormat get(int value) {
        switch (value) {
        case NORMAL_VALUE:
            return NORMAL;
        case ITALIC_VALUE:
            return ITALIC;
        case BOLD_VALUE:
            return BOLD;
        case BOLD_ITALIC_VALUE:
            return BOLD_ITALIC;
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
    private FontFormat(int value, String name, String literal) {
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

} // FontFormat
