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
 * <em><b>Container Shape</b></em>', and utility methods for working with them.
 * <!-- end-user-doc -->
 *
 * @see org.eclipse.sirius.diagram.DiagramPackage#getContainerShape()
 * @model
 * @generated
 */
public enum ContainerShape implements Enumerator {
    /**
     * The '<em><b>Parallelogram</b></em>' literal object. <!-- begin-user-doc
     * --> <!-- end-user-doc -->
     *
     * @see #PARALLELOGRAM
     * @generated
     * @ordered
     */
    PARALLELOGRAM_LITERAL(0, "parallelogram", "parallelogram"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>Parallelogram</b></em>' literal value. <!-- begin-user-doc
     * -->
     * <p>
     * If the meaning of '<em><b>Parallelogram</b></em>' literal object isn't
     * clear, there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
     *
     * @see #PARALLELOGRAM_LITERAL
     * @model name="parallelogram"
     * @generated
     * @ordered
     */
    public static final int PARALLELOGRAM = 0;

    /**
     * An array of all the '<em><b>Container Shape</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ContainerShape[] VALUES_ARRAY = new ContainerShape[] { PARALLELOGRAM_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>Container Shape</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    public static final List<ContainerShape> VALUES = Collections.unmodifiableList(Arrays.asList(ContainerShape.VALUES_ARRAY));

    /**
     * Returns the '<em><b>Container Shape</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param literal
     *            the literal.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerShape get(String literal) {
        for (ContainerShape result : ContainerShape.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Container Shape</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param name
     *            the name.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerShape getByName(String name) {
        for (ContainerShape result : ContainerShape.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>Container Shape</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     *
     * @param value
     *            the integer value.
     * @return the matching enumerator or <code>null</code>.
     * @generated
     */
    public static ContainerShape get(int value) {
        switch (value) {
        case PARALLELOGRAM:
            return PARALLELOGRAM_LITERAL;
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
    private ContainerShape(int value, String name, String literal) {
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

} // ContainerShape
