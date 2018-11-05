/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

/**
 * A helper to forcibly clear all the fields of a test case to avoid retaining
 * too many objects and resources in memory during a long test suite.
 * 
 * @author pcdavid
 */
public class TestCaseCleaner {
    private static final Class<?> ROOT_CLASS = TestCase.class;

    private final TestCase target;

    /**
     * Constructor.
     * 
     * @param target
     *            the test case instance to clean.
     */
    public TestCaseCleaner(TestCase target) {
        this.target = target;
    }

    /**
     * Clears (i.e. sets to null) all non-primitive and non-final fields of the
     * target test case except those which are declared in ROOT_CLASS.
     * 
     * @throws IllegalAccessException
     *             if if was not possible to access to some of the field by
     *             reflection.
     */
    public void clearAllFields() throws IllegalAccessException {
        for (Class<?> current = target.getClass(); current != TestCaseCleaner.ROOT_CLASS; current = current.getSuperclass()) {
            clearFieldsFrom(current);
        }
    }

    private void clearFieldsFrom(Class<?> klass) {
        for (Field field : klass.getDeclaredFields()) {
            boolean isReference = !field.getType().isPrimitive();
            try {
                field.setAccessible(true);
                boolean isSet = field.get(target) != null;
                if (isReference && isSet) {
                    clearField(field);
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                // Do nothing
            }
        }
    }

    private void clearField(Field field) throws IllegalAccessException {
        boolean isFinal = Modifier.isFinal(field.getModifiers());
        if (!isFinal) {
            field.set(target, null);
        }
    }
}
