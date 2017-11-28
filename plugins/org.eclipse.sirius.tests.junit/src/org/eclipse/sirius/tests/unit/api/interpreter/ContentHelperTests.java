/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.interpreter;

import org.eclipse.sirius.common.tools.internal.assist.ContentContextHelper;

import junit.framework.TestCase;

/**
 * Tests for the completion proposal start computation.
 * 
 * @author mporhel
 * 
 */
public class ContentHelperTests extends TestCase {

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testJavaIdentifiers() {

        assertFalse(Character.isJavaIdentifierStart('<'));
        assertFalse(Character.isJavaIdentifierStart('>'));
        assertFalse(Character.isJavaIdentifierStart('%'));
        assertFalse(Character.isJavaIdentifierStart('['));
        assertFalse(Character.isJavaIdentifierStart('/'));
        assertFalse(Character.isJavaIdentifierStart(']'));
        assertFalse(Character.isJavaIdentifierStart('('));
        assertFalse(Character.isJavaIdentifierStart(')'));
        assertFalse(Character.isJavaIdentifierStart('.'));
        assertFalse(Character.isJavaIdentifierStart(' '));
        assertFalse(Character.isJavaIdentifierStart('\r'));
        assertFalse(Character.isJavaIdentifierStart('\n'));
        assertFalse(Character.isJavaIdentifierStart('!'));
        assertFalse(Character.isJavaIdentifierStart(';'));
        assertFalse(Character.isJavaIdentifierStart('='));
        assertFalse(Character.isJavaIdentifierStart('/'));
        assertFalse(Character.isJavaIdentifierStart('-'));
        assertFalse(Character.isJavaIdentifierStart('+'));

        assertFalse(Character.isJavaIdentifierPart('<'));
        assertFalse(Character.isJavaIdentifierPart('>'));
        assertFalse(Character.isJavaIdentifierPart('%'));
        assertFalse(Character.isJavaIdentifierPart('['));
        assertFalse(Character.isJavaIdentifierPart('/'));
        assertFalse(Character.isJavaIdentifierPart(']'));
        assertFalse(Character.isJavaIdentifierPart('('));
        assertFalse(Character.isJavaIdentifierPart(')'));
        assertFalse(Character.isJavaIdentifierPart('.'));
        assertFalse(Character.isJavaIdentifierPart(' '));
        assertFalse(Character.isJavaIdentifierPart('\r'));
        assertFalse(Character.isJavaIdentifierPart('\n'));
        assertFalse(Character.isJavaIdentifierPart('!'));
        assertFalse(Character.isJavaIdentifierPart(';'));
        assertFalse(Character.isJavaIdentifierPart('='));
        assertFalse(Character.isJavaIdentifierPart('/'));
        assertFalse(Character.isJavaIdentifierPart('-'));
        assertFalse(Character.isJavaIdentifierPart('+'));

        assertTrue(Character.isJavaIdentifierPart('_'));
        assertTrue(Character.isJavaIdentifierStart('_'));
        assertTrue(Character.isJavaIdentifierPart('5'));
        assertFalse(Character.isJavaIdentifierStart('5'));

        assertTrue(Character.isJavaIdentifierPart('a'));
        assertTrue(Character.isJavaIdentifierStart('a'));

        assertTrue(Character.isJavaIdentifierPart('A'));
        assertTrue(Character.isJavaIdentifierStart('A'));

        assertTrue(Character.isJavaIdentifierPart('$'));
        assertTrue(Character.isJavaIdentifierStart('$'));
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testContextHelperAndMTL() {

        ContentContextHelper cch = new ContentContextHelper("[/]", 0, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("[/]", 1, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("[self.name./]", 11, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("[self.name/]", 10, "");
        assertEquals("name", cch.getProposalStart());

        cch = new ContentContextHelper("[self.nam/]", 9, "");
        assertEquals("nam", cch.getProposalStart());

        cch = new ContentContextHelper("[self.na/]", 8, "");
        assertEquals("na", cch.getProposalStart());

        cch = new ContentContextHelper("[self.n/]", 7, "");
        assertEquals("n", cch.getProposalStart());
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testContextHelperAndOCL() {

        ContentContextHelper cch = new ContentContextHelper("ocl:", 4, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("ocl:sel", 7, "");
        assertEquals("sel", cch.getProposalStart());
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testContextHelperStartDetection() {

        ContentContextHelper cch = new ContentContextHelper("aaaa.aaaa(", 10, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("aaaa.aaaa()", 11, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("aaaa.aaaa().", 12, "");
        assertEquals("", cch.getProposalStart());

        cch = new ContentContextHelper("aaaa.aaa_9", 10, "");
        assertEquals("aaa_9", cch.getProposalStart());

        cch = new ContentContextHelper("-> a", 4, "");
        assertEquals("a", cch.getProposalStart());
    }
}
