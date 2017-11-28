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
package org.eclipse.sirius.tests.unit.common;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.ext.emf.Contents;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import junit.framework.TestCase;

/**
 * Tests for the {@link Contents} utility class.
 * 
 * @author pcdavid
 */
public class ContentsTests extends TestCase {
    /**
     * Tests that a null EObject produces a non-null but empty iterable.
     */
    public void testNullProducesEmptyIterable() {
        Iterable<EObject> contents = Contents.of(null);
        assertNotNull(contents);
        assertTrue(Iterables.isEmpty(contents));
    }

    /**
     * Tests that an EObject with no contents produces an empty iterable.
     */
    public void testEmptyEObjectProducesEmptyIterable() {
        EObject obj = EcoreFactory.eINSTANCE.createEObject();
        Iterable<EObject> contents = Contents.of(obj);
        assertNotNull(contents);
        assertTrue(Iterables.isEmpty(contents));
    }

    /**
     * Tests that an EObject with a single child produces a singleton iterable.
     */
    public void testIterationOnEObjectWithASingleChild() {
        EPackage parent = EcoreFactory.eINSTANCE.createEPackage();
        EClass child = EcoreFactory.eINSTANCE.createEClass();
        parent.getEClassifiers().add(child);
        Iterable<EObject> contents = Contents.of(parent);
        assertNotNull(contents);
        List<EObject> elements = Lists.newArrayList(contents);
        assertNotNull(elements);
        assertEquals(1, elements.size());
        assertSame(child, elements.get(0));
    }

    /**
     * Tests that an EObject with several children produces an iterable on these
     * children, in the correct order.
     */
    public void testIterationOnEObjectWithSeveralChildren() {
        final int n = 10;
        EPackage parent = EcoreFactory.eINSTANCE.createEPackage();
        List<EObject> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            EClass child = EcoreFactory.eINSTANCE.createEClass();
            parent.getEClassifiers().add(child);
            children.add(child);
        }
        List<EObject> result = Lists.newArrayList(Contents.of(parent));
        assertEquals(children.size(), result.size());
        for (int i = 0; i < n; i++) {
            assertSame(children.get(i), result.get(i));
        }
    }

    /**
     * Tests that several iterables produced on the same EObject produce exactly
     * the same result.
     */
    public void testSuccessiveCallsProducesSameResult() {
        final int n = 10;
        EPackage parent = EcoreFactory.eINSTANCE.createEPackage();
        for (int i = 0; i < n; i++) {
            EClass child = EcoreFactory.eINSTANCE.createEClass();
            parent.getEClassifiers().add(child);
        }
        List<EObject> result1 = Lists.newArrayList(Contents.of(parent));
        List<EObject> result2 = Lists.newArrayList(Contents.of(parent));
        assertEquals(result1.size(), result2.size());
        for (int i = 0; i < n; i++) {
            assertSame(result1.get(i), result2.get(i));
        }
    }

    /**
     * Tests that Contents produces an Iterable which also takes EAnnotations
     * into account.
     */
    public void testContentsIterableIncludesEAnnoations() {
        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        EAnnotation ann = EcoreFactory.eINSTANCE.createEAnnotation();
        pkg.getEAnnotations().add(ann);
        List<EObject> elements = Lists.newArrayList(Contents.of(pkg));
        assertEquals(1, elements.size());
        assertSame(ann, elements.get(0));
    }

    /**
     * Tests that contents only iterates on direct children and does not include
     * other descendants recursively.
     */
    public void testContentsIterablesIsNotRecursive() {
        EPackage pkg = EcoreFactory.eINSTANCE.createEPackage();
        EClass klass = EcoreFactory.eINSTANCE.createEClass();
        pkg.getEClassifiers().add(klass);
        klass.getEStructuralFeatures().add(EcoreFactory.eINSTANCE.createEAttribute());
        List<EObject> elements = Lists.newArrayList(Contents.of(pkg));
        assertEquals(1, elements.size());
        assertSame(klass, elements.get(0));
    }
}
