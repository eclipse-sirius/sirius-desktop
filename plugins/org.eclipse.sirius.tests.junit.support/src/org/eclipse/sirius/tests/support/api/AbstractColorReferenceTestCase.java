/**
 * Copyright (c) 2012, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved.   This program and the accompanying materials
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.junit.Assert;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import junit.framework.TestCase;

/**
 * Test cardinality and initialization of Color references.
 * 
 * @author mporhel
 */
public abstract class AbstractColorReferenceTestCase extends TestCase {

    private Collection<EReference> colorReferences;

    private Collection<EClass> classesWithColorReferences;

    // Specific color references : non required and without initalization.
    private Collection<EReference> specialColorRefs = new ArrayList<>();

    private EPackage basePackage;

    private final Predicate<EReference> isColorReference = new Predicate<EReference>() {
        @Override
        public boolean apply(EReference input) {
            return !input.isContainment() && DescriptionPackage.eINSTANCE.getColorDescription().isSuperTypeOf(input.getEReferenceType());
        }
    };

    public EPackage getBasePackage() {
        return basePackage;
    }

    public void setBasePackage(EPackage basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        colorReferences = new LinkedHashSet<>();
        classesWithColorReferences = new LinkedHashSet<>();
        Assert.assertNotNull("Base package should not be null.", basePackage);
        lookForColorReferences(basePackage);
        specialColorRefs = Arrays.asList(org.eclipse.sirius.diagram.description.DescriptionPackage.eINSTANCE
                .getDiagramDescription_BackgroundColor());
    }

    private void lookForColorReferences(EPackage pkg) {
        for (EClass eclass : Iterables.filter(pkg.getEClassifiers(), EClass.class)) {
            lookForColorReferences(eclass);
        }
        for (EPackage subPkg : pkg.getESubpackages()) {
            lookForColorReferences(subPkg);
        }
    }

    private void lookForColorReferences(EClass eclass) {
        Iterable<EReference> allColorReferences = Iterables.filter(eclass.getEAllReferences(), isColorReference);
        Iterables.addAll(colorReferences, allColorReferences);
        if (!Iterables.isEmpty(allColorReferences) && !eclass.isAbstract() && !eclass.isInterface()) {
            classesWithColorReferences.add(eclass);
        }
    }

    /**
     * Test that all color references are required.
     */
    public void testColorReferencesCardinality() {
        List<EReference> referencesWithWrongCardinality = new ArrayList<>();

        Predicate<EReference> shouldBeRequired = new Predicate<EReference>() {
            @Override
            public boolean apply(EReference input) {
                return !input.isRequired() && !specialColorRefs.contains(input);
            }
        };

        Iterables.addAll(referencesWithWrongCardinality, Iterables.filter(colorReferences, shouldBeRequired));
        Assert.assertTrue(getMessage(referencesWithWrongCardinality), referencesWithWrongCardinality.isEmpty());
    }

    /**
     * Test that all color references are set by the factory.
     */
    public void testColorReferencesInitialization() {
        StringBuilder sb = new StringBuilder();
        for (EClass clazz : classesWithColorReferences) {
            EObject created = EcoreUtil.create(clazz);
            for (EReference colorRef : Iterables.filter(clazz.getEAllReferences(), isColorReference)) {
                if ((!created.eIsSet(colorRef) || created.eGet(colorRef) == null) && !specialColorRefs.contains(colorRef)) {
                    sb.append(" . " + clazz.getName() + "#" + colorRef.getName() + "\n");
                }
            }
        }
        TestCase.assertTrue("Some color references need initialization:\n" + sb.toString(), sb.length() == 0);
    }

    private String getMessage(List<EReference> referencesWithWrongCardinality) {
        StringBuilder sb = new StringBuilder();
        sb.append(referencesWithWrongCardinality.size());
        sb.append(" color references should be required, please modify their cardinality:");
        for (EReference ref : referencesWithWrongCardinality) {
            sb.append("\n . ");
            sb.append(ref.eResource().getURIFragment(ref));
        }
        return sb.toString();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        basePackage = null;
        colorReferences.clear();
        classesWithColorReferences.clear();
    }
}
