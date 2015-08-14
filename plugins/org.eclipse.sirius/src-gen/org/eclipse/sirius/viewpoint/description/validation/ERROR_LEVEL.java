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
package org.eclipse.sirius.viewpoint.description.validation;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc --> A representation of the literals of the enumeration '
 * <em><b>ERROR LEVEL</b></em>', and utility methods for working with them. <!--
 * end-user-doc --> <!-- begin-model-doc --> All levels of error for a
 * validation rule. <!-- end-model-doc -->
 *
 * @see org.eclipse.sirius.viewpoint.description.validation.ValidationPackage#getERROR_LEVEL()
 * @model
 * @generated
 */
public enum ERROR_LEVEL implements Enumerator {
    /**
     * The '<em><b>INFO</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #INFO
     * @generated
     * @ordered
     */
    INFO_LITERAL(0, "INFO", "INFO"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>WARNING</b></em>' literal object. <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * 
     * @see #WARNING
     * @generated
     * @ordered
     */
    WARNING_LITERAL(1, "WARNING", "WARNING"), //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>ERROR</b></em>' literal object. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @see #ERROR
     * @generated
     * @ordered
     */
    ERROR_LITERAL(3, "ERROR", "ERROR"); //$NON-NLS-1$ //$NON-NLS-2$

    /**
     * The '<em><b>INFO</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Info level. <!-- end-model-doc
     * -->
     *
     * @see #INFO_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int INFO = 0;

    /**
     * The '<em><b>WARNING</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Warning level. <!--
     * end-model-doc -->
     *
     * @see #WARNING_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int WARNING = 1;

    /**
     * The '<em><b>ERROR</b></em>' literal value. <!-- begin-user-doc --> <!--
     * end-user-doc --> <!-- begin-model-doc --> Error level. <!-- end-model-doc
     * -->
     *
     * @see #ERROR_LITERAL
     * @model
     * @generated
     * @ordered
     */
    public static final int ERROR = 3;

    /**
     * An array of all the '<em><b>ERROR LEVEL</b></em>' enumerators. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     *
     * @generated
     */
    private static final ERROR_LEVEL[] VALUES_ARRAY = new ERROR_LEVEL[] { INFO_LITERAL, WARNING_LITERAL, ERROR_LITERAL, };

    /**
     * A public read-only list of all the '<em><b>ERROR LEVEL</b></em>'
     * enumerators. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static final List<ERROR_LEVEL> VALUES = Collections.unmodifiableList(Arrays.asList(ERROR_LEVEL.VALUES_ARRAY));

    /**
     * Returns the '<em><b>ERROR LEVEL</b></em>' literal with the specified
     * literal value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ERROR_LEVEL get(String literal) {
        for (ERROR_LEVEL result : ERROR_LEVEL.VALUES_ARRAY) {
            if (result.toString().equals(literal)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>ERROR LEVEL</b></em>' literal with the specified
     * name. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ERROR_LEVEL getByName(String name) {
        for (ERROR_LEVEL result : ERROR_LEVEL.VALUES_ARRAY) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    /**
     * Returns the '<em><b>ERROR LEVEL</b></em>' literal with the specified
     * integer value. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public static ERROR_LEVEL get(int value) {
        switch (value) {
        case INFO:
            return INFO_LITERAL;
        case WARNING:
            return WARNING_LITERAL;
        case ERROR:
            return ERROR_LITERAL;
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
    private ERROR_LEVEL(int value, String name, String literal) {
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

} // ERROR_LEVEL
