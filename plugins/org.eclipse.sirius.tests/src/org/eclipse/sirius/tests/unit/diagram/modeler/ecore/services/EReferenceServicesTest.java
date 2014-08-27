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
package org.eclipse.sirius.tests.unit.diagram.modeler.ecore.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;
import org.eclipse.sirius.ecore.design.service.EReferenceServices;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Automated tests for the EReferenceServices class.
 * 
 * @author pcdavid
 */
public class EReferenceServicesTest {
    private static final String[] WHITESPACE = new String[] { " ", "\t", "  ", "", "  \t " };;

    private EReferenceServices service;

    private EClass a;

    private EClass b;

    private EReference ref;

    @BeforeClass
    public static void initializeEMF() {
        if (!EMFPlugin.IS_ECLIPSE_RUNNING) {
            @SuppressWarnings("unused")
            EPackage ecore = EcorePackage.eINSTANCE;
            Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("ecore", new EcoreResourceFactoryImpl());
        }
    }

    @Before
    public void createService() {
        service = new EReferenceServices();
        a = EcoreFactory.eINSTANCE.createEClass();
        a.setName("A");
        b = EcoreFactory.eINSTANCE.createEClass();
        b.setName("B");
        ref = EcoreFactory.eINSTANCE.createEReference();
    }

    @Test
    public void render_single_reference() {
        createReference(1, 1, "ref", true);
        assertEquals("[1..1] /ref", service.render(ref));

        createReference(1, 1, "ref", false);
        assertEquals("[1..1] ref", service.render(ref));
    }

    @Test
    public void render_optional_single_reference() {
        createReference(0, 1, "ref", true);
        assertEquals("[0..1] /ref", service.render(ref));

        createReference(0, 1, "ref", false);
        assertEquals("[0..1] ref", service.render(ref));
    }

    @Test
    public void render_multiple_referrence() {
        createReference(1, -1, "ref", true);
        assertEquals("[1..*] /ref", service.render(ref));

        createReference(1, -1, "ref", false);
        assertEquals("[1..*] ref", service.render(ref));
    }

    @Test
    public void render_optional_multiple_reference() {
        createReference(0, -1, "ref", true);
        assertEquals("[0..*] /ref", service.render(ref));

        createReference(0, -1, "ref", false);
        assertEquals("[0..*] ref", service.render(ref));
    }

    @Test
    public void render_anonymous_reference() {
        createReference(1, 1, "ref", true);
        ref.setName(null);
        assertEquals("[1..1]", service.render(ref));

        createReference(1, 1, "ref", false);
        ref.setName(null);
        assertEquals("[1..1]", service.render(ref));
    }

    @Test
    public void extract_cardinality() {
        assertEquals(null, service.extractCardinalityPart(""));
        assertEquals(null, service.extractCardinalityPart("   "));
        assertEquals(null, service.extractCardinalityPart(" name "));
        assertEquals(null, service.extractCardinalityPart(" /name "));

        assertEquals("1..1", service.extractCardinalityPart("[1..1]"));
        assertEquals("1..1", service.extractCardinalityPart(" [  1..1 ]   name"));

        assertEquals("1  ..*", service.extractCardinalityPart("[1  ..*  ]"));
        assertEquals("*", service.extractCardinalityPart(" [  * ]   name"));
        assertEquals("..*", service.extractCardinalityPart(" [ ..* ]   name"));
        assertEquals("1..", service.extractCardinalityPart(" [1..]   name"));

        String[] parts = ":".split(":x");
        assertEquals(1, parts.length);
    }

    @Test
    public void parse_invalid_cardinality() {
        assertCardinality(null, null, service.parseCardinality(""));
        assertCardinality(null, null, service.parseCardinality("[]"));
        assertCardinality(null, null, service.parseCardinality("[  ]"));
        assertCardinality(null, null, service.parseCardinality("[..]"));
        assertCardinality(null, null, service.parseCardinality("[ ..  ]"));
        assertCardinality(null, null, service.parseCardinality("[  . .  ]"));

        assertCardinality(null, null, service.parseCardinality("["));
        assertCardinality(null, null, service.parseCardinality("  [  "));
        assertCardinality(null, null, service.parseCardinality("[ .. "));
        assertCardinality(null, null, service.parseCardinality("  [..   "));

        assertCardinality(null, null, service.parseCardinality("]"));
        assertCardinality(null, null, service.parseCardinality("  ]  "));

        assertCardinality(null, null, service.parseCardinality("..]"));
        assertCardinality(null, null, service.parseCardinality(" .. ]  "));
    }

    @Test
    public void parse_complete_cardinality() {
        assertCardinality(0, 0, service.parseCardinality("[0..0]"));
        assertCardinality(0, 0, service.parseCardinality("[ 0 .. 0  ]"));
        assertCardinality(0, 1, service.parseCardinality("[0.. 1]"));
        assertCardinality(0, 1, service.parseCardinality("[0..1]"));
        assertCardinality(0, 1, service.parseCardinality(" [ 0  ..1]"));
        assertCardinality(0, 2, service.parseCardinality("[0..2]"));
        assertCardinality(0, 2, service.parseCardinality("[ 0 ..  2]"));
        assertCardinality(0, -1, service.parseCardinality("[0..*]"));
        assertCardinality(0, -1, service.parseCardinality("[0..* ]"));
        assertCardinality(0, -1, service.parseCardinality("[0..  * ] "));

        assertCardinality(1, 0, service.parseCardinality("[1..0]"));
        assertCardinality(1, 1, service.parseCardinality("[1..1]"));
        assertCardinality(1, 2, service.parseCardinality("[1..2]"));
        assertCardinality(1, -1, service.parseCardinality("[1..*]"));

        assertCardinality(-1, 0, service.parseCardinality("[*..0]"));
        assertCardinality(-1, 1, service.parseCardinality("[*..1]"));
        assertCardinality(-1, 2, service.parseCardinality("[*..2]"));
        assertCardinality(-1, -1, service.parseCardinality("[*..*]"));
    }

    @Test
    public void parse_partial_cardinality() {
        assertCardinality(1, null, service.parseCardinality("[1..]"));
        assertCardinality(1, null, service.parseCardinality("[ 1 ..]"));
        assertCardinality(1, null, service.parseCardinality("[1..  ]   "));
        assertCardinality(null, 1, service.parseCardinality("[..1]"));
        assertCardinality(null, 1, service.parseCardinality("[\t ..1 ]"));
        assertCardinality(null, 1, service.parseCardinality("[..1]"));
        assertCardinality(null, 1, service.parseCardinality("[  ..1 ]"));

        assertCardinality(-1, null, service.parseCardinality("[*..]"));
        assertCardinality(-1, null, service.parseCardinality(" [ *..]"));
        assertCardinality(-1, null, service.parseCardinality("[*  ..]"));
        assertCardinality(-1, null, service.parseCardinality("[*  .. ] "));
        assertCardinality(-1, null, service.parseCardinality("[*.. ] "));
        assertCardinality(null, -1, service.parseCardinality("[..*]"));
        assertCardinality(null, -1, service.parseCardinality("[ .. * ] "));
        assertCardinality(null, -1, service.parseCardinality("[ .. * ]"));
        assertCardinality(null, -1, service.parseCardinality("[..* ]"));
        assertCardinality(null, -1, service.parseCardinality("  [ .. *] "));

        assertCardinality(2, null, service.parseCardinality("[2..]"));
        assertCardinality(null, 2, service.parseCardinality("[..2]"));
    }

    @Test
    public void edit_reference_change_name() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                createReference(1, 1, "test", false);
                service.performEdit(ref, prefix + "newName" + suffix);
                assertEquals("newName", ref.getName());
                assertEquals(1, ref.getLowerBound());
                assertEquals(1, ref.getUpperBound());
                assertFalse(ref.isDerived());
            }
        }
    }

    @Test
    public void edit_reference_change_name_set_derived() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                for (String sep : WHITESPACE) {
                    createReference(1, 1, "test", false);
                    service.performEdit(ref, prefix + "/" + sep + "newName" + suffix);
                    assertEquals("newName", ref.getName());
                    assertEquals(1, ref.getLowerBound());
                    assertEquals(1, ref.getUpperBound());
                    assertTrue(ref.isDerived());
                }
            }
        }
    }

    @Test
    public void edit_reference_change_name_set_not_derived() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                createReference(1, 1, "test", true);
                service.performEdit(ref, prefix + "newName" + suffix);
                assertEquals("newName", ref.getName());
                assertEquals(1, ref.getLowerBound());
                assertEquals(1, ref.getUpperBound());
                assertFalse(ref.isDerived());
            }
        }
    }

    @Test
    public void edit_reference_change_cardinality() {
        createReference(0, 0, "test", false);
        service.performEdit(ref, "[0..*]test");
        assertEquals("test", ref.getName());
        assertFalse(ref.isDerived());
        assertEquals(0, ref.getLowerBound());
        assertEquals(-1, ref.getUpperBound());

        createReference(0, 0, "test", false);
        service.performEdit(ref, "[0..*] test");
        assertEquals("test", ref.getName());
        assertFalse(ref.isDerived());
        assertEquals(0, ref.getLowerBound());
        assertEquals(-1, ref.getUpperBound());

        createReference(0, 0, "test", false);
        service.performEdit(ref, " [ 1..*] test ");
        assertEquals("test", ref.getName());
        assertFalse(ref.isDerived());
        assertEquals(1, ref.getLowerBound());
        assertEquals(-1, ref.getUpperBound());

        createReference(0, 0, "test", false);
        service.performEdit(ref, " [ 1..] test ");
        assertEquals("test", ref.getName());
        assertFalse(ref.isDerived());
        assertEquals(1, ref.getLowerBound());
        assertEquals(0, ref.getUpperBound());

        createReference(0, 0, "test", false);
        service.performEdit(ref, " [ ..3] test ");
        assertEquals("test", ref.getName());
        assertFalse(ref.isDerived());
        assertEquals(0, ref.getLowerBound());
        assertEquals(3, ref.getUpperBound());
    }

    @Test
    public void edit_reference_lower_bound_shortcut_0() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                createReference(1, 1, "test", false);
                service.performEdit(ref, prefix + "0" + suffix);
                assertEquals("test", ref.getName());
                assertEquals(0, ref.getLowerBound());
                assertEquals(1, ref.getUpperBound());
                assertFalse(ref.isDerived());
            }
        }
    }

    @Test
    public void edit_reference_lower_bound_shortcut_1() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                createReference(0, 1, "test", false);
                service.performEdit(ref, prefix + "1" + suffix);
                assertEquals("test", ref.getName());
                assertEquals(1, ref.getLowerBound());
                assertEquals(1, ref.getUpperBound());
                assertFalse(ref.isDerived());
            }
        }
    }

    @Test
    public void edit_reference_upper_bound_shortcut() {
        for (String prefix : WHITESPACE) {
            for (String suffix : WHITESPACE) {
                createReference(0, 1, "test", false);
                service.performEdit(ref, prefix + "*" + suffix);
                assertEquals("test", ref.getName());
                assertEquals(0, ref.getLowerBound());
                assertEquals(-1, ref.getUpperBound());
                assertFalse(ref.isDerived());
            }
        }
    }

    private void assertCardinality(Integer lower, Integer upper, List<Integer> card) {
        assertTrue(card == null || card.size() == 2);
        if (lower == null && upper == null) {
            assertTrue(card == null || (card.get(0) == null && card.get(1) == null));
        } else {
            assertEquals(lower, card.get(0));
            assertEquals(upper, card.get(1));
        }
    }

    private void createReference(int min, int max, String name, boolean derived) {
        a.getEStructuralFeatures().clear();
        ref = EcoreFactory.eINSTANCE.createEReference();
        ref.setName(name);
        ref.setLowerBound(min);
        ref.setUpperBound(max);
        a.getEStructuralFeatures().add(ref);
        ref.setEType(b);
        ref.setDerived(derived);
    }
}
