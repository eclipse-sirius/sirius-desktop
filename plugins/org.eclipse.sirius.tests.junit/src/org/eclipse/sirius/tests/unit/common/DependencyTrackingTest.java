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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.ext.base.relations.DependencyTracker;
import org.eclipse.sirius.ext.base.relations.Relation;
import org.eclipse.sirius.ext.base.relations.TransitiveClosure;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * @author pierre-charles.david@obeo.fr
 */
public class DependencyTrackingTest {
    private final Relation<EClass> deps = new Relation<EClass>() {
        public Set<EClass> apply(EClass from) {
            Set<EClass> result = Sets.newHashSet();
            for (EReference ref : Iterables.filter(from.getEStructuralFeatures(), EReference.class)) {
                result.add((EClass) ref.getEType());
            }
            return result;
        }
    };

    private EcoreFactory factory = EcoreFactory.eINSTANCE;

    private Map<String, EClass> classes = Maps.newHashMap();

    @Before
    public void buildModel() {
        // Create a "tree"
        link("r", "a");
        link("a", "c");
        link("a", "d");
        link("d", "e");
        link("d", "f");
        link("r", "b");
        link("b", "g");
        link("g", "h");
        link("h", "i");
        link("i", "j");
        // Add some dependencies between the branches
        link("b", "f");
        link("h", "f");
        link("d", "r");
    }
    
    private EClass getClass(String className) {
        if (classes.containsKey(className)) {
            return classes.get(className);
        } else {
            EClass c = factory.createEClass();
            c.setName(className);
            classes.put(className, c);
            return c;
        }
    }
    
    private void link(String from, String to) {
        EReference ref = factory.createEReference();
        ref.setEType(getClass(to));
        getClass(from).getEStructuralFeatures().add(ref);
    }

    @Test(expected = IllegalArgumentException.class)
    public void can_not_ask_dependencies_of_untracked_element() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(deps);
        tracker.getDependencies(getClass("r"));
    }
    
    @Test
    public void direct_dependency() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(deps);
        tracker.add(getClass("r"));
        tracker.add(getClass("e"));
        assertHasDependency(tracker, "r", "a");
        assertEquals(0, tracker.getDependencies(getClass("e")).size());
    }
    
    @Test
    public void transitive_dependency() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(new TransitiveClosure<EClass>(deps));
        tracker.add(getClass("r"));
        assertHasDependency(tracker, "r", "a");
        assertHasDependency(tracker, "r", "b");
        assertHasDependency(tracker, "r", "c");
        assertHasDependency(tracker, "r", "d");
        assertHasDependency(tracker, "r", "e");
        assertHasDependency(tracker, "r", "f");
        assertHasDependency(tracker, "r", "g");
        assertHasDependency(tracker, "r", "h");
        assertHasDependency(tracker, "r", "i");
        
        tracker.add(getClass("b"));
        assertHasDependency(tracker, "b", "f");
        assertHasDependency(tracker, "b", "g");
        assertHasDependency(tracker, "b", "h");
        assertHasDependency(tracker, "b", "i");
        assertHasDependency(tracker, "b", "j");
        assertHasNotDependency(tracker, "b", "a");
        assertHasNotDependency(tracker, "b", "c");
        assertHasNotDependency(tracker, "b", "d");
        assertHasNotDependency(tracker, "b", "e");
    }
    
    @Test
    public void direct_reverse_dependency() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(deps);
        tracker.add(getClass("r"));
        assertHasReverseDependency(tracker, "a", "r");
        assertHasReverseDependency(tracker, "b", "r");
        assertHasNotReverseDependency(tracker, "r", "a");
        assertHasNotReverseDependency(tracker, "c", "r");
    }

    @Test
    public void transitive_reverse_dependency() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(new TransitiveClosure<EClass>(deps));
        tracker.add(getClass("r"));
        assertHasReverseDependency(tracker, "a", "r");
        assertHasReverseDependency(tracker, "b", "r");
        assertHasReverseDependency(tracker, "e", "r");
        assertHasReverseDependency(tracker, "f", "r");
        assertHasReverseDependency(tracker, "j", "r");
        
        // "h" is not a tracked element
        assertHasNotReverseDependency(tracker, "j", "h");
        tracker.add(getClass("h"));
        // once it is tracked, we see the reverse dependency
        assertHasReverseDependency(tracker, "j", "h");
    }
    
    @Test
    public void dynamic_relation() {
        DependencyTracker<EClass> tracker = new DependencyTracker<EClass>(deps);
        tracker.add(getClass("r"));
        assertHasNotDependency(tracker, "r", "d");
        assertHasNotReverseDependency(tracker, "d", "r");
        link("r", "d");
        assertHasNotDependency(tracker, "r", "d");
        assertHasNotReverseDependency(tracker, "d", "r");
        tracker.update(getClass("r"));
        assertHasDependency(tracker, "r", "d");
        assertHasReverseDependency(tracker, "d", "r");
    }

    private void assertHasDependency(DependencyTracker<EClass> tracker, String from, String to) {
        EClass fromClass = getClass(from);
        EClass toClass = getClass(to);
        Set<EClass> dependencies = tracker.getDependencies(fromClass);
        assertTrue(dependencies.contains(toClass));
    }
    
    private void assertHasNotDependency(DependencyTracker<EClass> tracker, String from, String to) {
        EClass fromClass = getClass(from);
        EClass toClass = getClass(to);
        Set<EClass> dependencies = tracker.getDependencies(fromClass);
        assertFalse(dependencies.contains(toClass));
    }
    
    private void assertHasReverseDependency(DependencyTracker<EClass> tracker, String from, String to) {
        EClass fromClass = getClass(from);
        EClass toClass = getClass(to);
        Set<EClass> dependencies = tracker.getReverseDependencies(fromClass);
        assertTrue(dependencies.contains(toClass));
    }
    
    private void assertHasNotReverseDependency(DependencyTracker<EClass> tracker, String from, String to) {
        EClass fromClass = getClass(from);
        EClass toClass = getClass(to);
        Set<EClass> dependencies = tracker.getReverseDependencies(fromClass);
        assertFalse(dependencies.contains(toClass));
    }

}
