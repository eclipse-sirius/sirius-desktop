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
 * <em><b>Container Layout</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getContainerLayout()
 * @model
 * @generated
 */
public enum ContainerLayout implements Enumerator {
    /**
     * The '<em><b>Free Form</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     *
     * @see #FREE_FORM_VALUE
     * @generated
     * @ordered
     */
    FREE_FORM(0, "FreeForm", "FreeForm"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>List</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     *
     * @see #LIST_VALUE
     * @generated
     * @ordered
     */
    LIST(1, "List", "List"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Horizontal Stack</b></em>' literal object. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @see #HORIZONTAL_STACK_VALUE
     * @generated
     * @ordered
     */
    HORIZONTAL_STACK(2, "HorizontalStack", "HorizontalStack"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Vertical Stack</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #VERTICAL_STACK_VALUE
     * @generated
     * @ordered
     */
    VERTICAL_STACK(3, "VerticalStack", "VerticalStack"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Free Form</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>Free Form</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #FREE_FORM
     * @model name="FreeForm"
     * @generated
     * @ordered
     */
    public static final int FREE_FORM_VALUE = 0;

    /**
     * The '<em><b>List</b></em>' literal value. <!-- begin-user-doc -->
     * <p>
     * If the meaning of '<em><b>List</b></em>' literal object isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #LIST
     * @model name="List"
     * @generated
     * @ordered
     */
    public static final int LIST_VALUE = 1;

    /**
     * The '<em><b>Horizontal Stack</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Horizontal Stack</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #HORIZONTAL_STACK
     * @model name="HorizontalStack"
     * @generated
     * @ordered
     */
    public static final int HORIZONTAL_STACK_VALUE = 2;

    /**
     * The '<em><b>Vertical Stack</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Vertical Stack</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #VERTICAL_STACK
     * @model name="VerticalStack"
     * @generated
     * @ordered
     */
    public static final int VERTICAL_STACK_VALUE = 3;

    /**
     * An array of all the '<em><b>Container Layout</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ContainerLayout[] VALUES_ARRAY = new ContainerLayout[] { FREE_FORM, LIST, HORIZONTAL_STACK, VERTICAL_STACK, };

    /**
     * A public read-only list of all the '<em><b>Container Layout</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<ContainerLayout> VALUES = Collections.unmodifiableList(Arrays.asList(ContainerLayout.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Container Layout</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerLayout get(String literal) {
        for (ContainerLayout result : ContainerLayout.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Container Layout</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerLayout getByName(String name) {
        for (ContainerLayout result : ContainerLayout.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Container Layout</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerLayout get(int value) {
        switch (value) {
        case FREE_FORM_VALUE:
            return FREE_FORM;
        case LIST_VALUE:
            return LIST;
        case HORIZONTAL_STACK_VALUE:
            return HORIZONTAL_STACK;
        case VERTICAL_STACK_VALUE:
            return VERTICAL_STACK;
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
    private ContainerLayout(int value, String name, String literal) {
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

} // ContainerLayout
