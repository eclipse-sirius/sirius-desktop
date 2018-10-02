/*******************************************************************************
 * Copyright (c) 2017 Obeo.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ecore.EMFUtil;

import junit.framework.TestCase;

/**
 * Unit tests for {@link TypeName}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class TypeNameTest extends TestCase {

    /**
     * Test the method {@link TypeName#fromString(String)}. Check that
     * {@link TypeName} with {@link EMFUtil#QUALIFIED_NAME_SEPARATOR} and spaces
     * are normalized when created a new {@link TypeName} from it.
     */
    public void testFromString() {
        TypeName typeName = TypeName.fromString(" ecore::EJavaObject ");
        assertEquals("The type name should have been normalized. ", "ecore.EJavaObject", typeName.getCompleteName());

        typeName = TypeName.fromString(" ecore::EObject ");
        assertEquals("The type name should have been normalized. ", "ecore.EObject", typeName.getCompleteName());

        typeName = TypeName.fromString(" ecore::EPackage ");
        assertEquals("The type name should have been normalized. ", "ecore.EPackage", typeName.getCompleteName());
    }
}
