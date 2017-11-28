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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.util.CartesianProduct;
import org.eclipse.sirius.common.tools.api.util.EObjectCouple;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;

import junit.framework.TestCase;

/**
 * Cartesian product test case.
 * 
 * @author mchauvin
 */
public class CartesianProductTestCase extends TestCase {

    private static final EClass A = EcoreFactory.eINSTANCE.createEClass();

    private static final EClass B = EcoreFactory.eINSTANCE.createEClass();

    private static final EClass C = EcoreFactory.eINSTANCE.createEClass();

    private static final EClass D = EcoreFactory.eINSTANCE.createEClass();

    public void testBaseProduct() {
        A.setName("A");
        B.setName("B");
        C.setName("C");
        D.setName("D");

        final Set<EClass> collection1 = new HashSet<EClass>();
        collection1.add(A);
        collection1.add(B);
        final Set<EClass> collection2 = new HashSet<EClass>();
        collection2.add(C);
        collection2.add(D);

        RefreshIdsHolder ids = new RefreshIdsHolder();

        CartesianProduct cartesianProduct = new CartesianProduct(collection1, collection2, ids);

        // the result should be the following
        List<EObjectCouple> expectedResult = new ArrayList<EObjectCouple>();
        expectedResult.add(new EObjectCouple(A, C, ids));
        expectedResult.add(new EObjectCouple(A, D, ids));
        expectedResult.add(new EObjectCouple(B, C, ids));
        expectedResult.add(new EObjectCouple(B, D, ids));

        List<EObjectCouple> result = new ArrayList<EObjectCouple>();

        final Iterator<EObjectCouple> it = cartesianProduct.iterator();
        while (it.hasNext()) {
            EObjectCouple couple = it.next();
            result.add(couple);
        }
        assertTrue(areEquals(expectedResult, result));
    }

    private static boolean areEquals(final Collection<EObjectCouple> collection1, final Collection<EObjectCouple> collection2) {
        if (collection1.size() != collection2.size())
            return false;

        for (final EObjectCouple couple1 : collection1) {
            if (!contains(couple1, collection2))
                return false;
        }
        return true;
    }

    private static boolean contains(final EObjectCouple obj1, final Collection<EObjectCouple> collection) {

        for (final EObjectCouple obj2 : collection) {
            if (areEquals(obj1, obj2))
                return true;
        }
        return false;
    }

    private static boolean areEquals(final EObjectCouple obj1, final EObjectCouple obj2) {
        if (obj1 == obj2)
            return true;

        return obj1.getObj1().equals(obj2.getObj1()) && obj1.getObj2().equals(obj2.getObj2());
    }

}
