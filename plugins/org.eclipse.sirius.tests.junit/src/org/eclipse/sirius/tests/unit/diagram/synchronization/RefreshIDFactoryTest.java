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
import org.eclipse.sirius.common.tools.api.util.RefreshIDFactory;
import org.eclipse.sirius.tests.support.api.EqualsHashCodeTestCase;

/**
 * Test class for {@link RefreshIDFactory}.
 * 
 * @author dlecan
 */
public class RefreshIDFactoryTest extends EqualsHashCodeTestCase {

    private static final EcoreFactory EF = EcoreFactory.eINSTANCE;

    private final EObject eObject;

    /**
     * Constructor.
     */
    public RefreshIDFactoryTest() {
        super();
        eObject = EF.createEObject();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createInstance() throws Exception {
        // Instanciate a new Integer in order to have a different instance
        return new Integer(RefreshIDFactory.getOrCreateID(eObject));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Object createNotEqualInstance() throws Exception {
        return RefreshIDFactory.getOrCreateID(EF.createEObject());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckSameInstance() throws Exception {
        assertSame(RefreshIDFactory.getOrCreateID(eObject), RefreshIDFactory.getOrCreateID(eObject));
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckNotSameInstance() throws Exception {
        assertNotSame(RefreshIDFactory.getOrCreateID(EF.createEObject()), RefreshIDFactory.getOrCreateID(eObject));
    }

}
