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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.util.RefreshIdsHolder;
import org.eclipse.sirius.tests.support.api.EqualsHashCodeTestCase;

/**
 * Test class for {@link RefreshIdsHolder}.
 * 
 * @author dlecan
 */
public class RefreshIdsHolderTest extends EqualsHashCodeTestCase {

    private static final EcoreFactory EF = EcoreFactory.eINSTANCE;

    private final EObject eObject;

    private RefreshIdsHolder ids;
    
    /**
     * Constructor.
     */
    public RefreshIdsHolderTest() {
        super();
        eObject = EF.createEObject();
        ids = new RefreshIdsHolder();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createInstance() throws Exception {
        // Instanciate a new Integer in order to have a different instance
        return new Integer(ids.getOrCreateID(eObject));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createNotEqualInstance() throws Exception {
        return ids.getOrCreateID(EF.createEObject());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckSameInstance() throws Exception {
        assertSame(ids.getOrCreateID(eObject), ids.getOrCreateID(eObject));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckNotSameInstance() throws Exception {
        assertNotSame(ids.getOrCreateID(EF.createEObject()), ids.getOrCreateID(eObject));
    }

}
