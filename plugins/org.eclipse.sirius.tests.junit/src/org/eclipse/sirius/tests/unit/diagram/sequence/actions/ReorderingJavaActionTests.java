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
package org.eclipse.sirius.tests.unit.diagram.sequence.actions;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.business.api.action.MoveElementInListAction;

import junit.framework.TestCase;

/**
 * Tests.
 * 
 * @author pcdavid
 * 
 */
public class ReorderingJavaActionTests extends TestCase {
    private EList<EObject> list;

    /**
     * Swap the position of two elements in a list wiht only these elements.
     */
    public void testSwapElementsInPair() {
        initList(2);
        EObject first = list.get(0);
        EObject second = list.get(1);
        moveElementAfter(first, second);
        assertListOrder(second, first);
    }

    /**
     * Test.
     */
    public void testMoveLastElementToSecond() {
        initList(3);
        EObject first = list.get(0);
        EObject second = list.get(1);
        EObject third = list.get(2);
        moveElementAfter(third, first);
        assertListOrder(first, third, second);
    }

    /**
     * Test.
     */
    public void testMoveFirstElementToFront() {
        initList(2);
        EObject first = list.get(0);
        EObject second = list.get(1);
        moveElementAfter(first, null);
        assertListOrder(first, second);
    }

    /**
     * Test.
     */
    public void testDirectMoveInternalElement() {
        initList(4);
        EObject a = list.get(0);
        EObject b = list.get(1);
        EObject c = list.get(2);
        EObject d = list.get(3);
        list.move(1, c);
        assertListOrder(a, c, b, d);
    }

    private void assertListOrder(EObject... elements) {
        assertEquals(elements.length, list.size());
        for (int i = 0; i < elements.length; i++) {
            assertSame(elements[i], list.get(i));
        }
    }

    private void moveElementAfter(EObject first, EObject second) {
        new MoveElementInListAction().moveElementAfter(first, second, list);
    }

    private void initList(int n) {
        list = new BasicEList<EObject>();
        for (int i = 0; i < n; i++) {
            list.add(EcoreFactory.eINSTANCE.createEObject());
        }
    }
}
