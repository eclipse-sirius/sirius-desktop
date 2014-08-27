/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.contribution;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.eclipse.sirius.ext.base.Option;

/**
 * JUnit assertions for testing {@link Option}.
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class OptionAssert {
    public static <T> void assertHasNoValue(Option<T> opt) {
        assertNotNull("Option object should not be null.", opt);
        assertFalse("Option object should represent no value", opt.some());
    }

    public static <T> void assertHasSomeValue(Option<T> opt) {
        assertNotNull("Option object should not be null.", opt);
        assertTrue("Option object should represent a value", opt.some());
    }

    public static <T> void assertHasSomeNonNullValue(Option<T> opt) {
        assertHasSomeNonNullValue(opt);
        assertNotNull(opt.get());
    }

    public static <T> void assertHasExactValue(T expectedValue, Option<T> actualOption) {
        assertHasSomeValue(actualOption);
        assertSame(expectedValue, actualOption.get());
    }
}
