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
package org.eclipse.sirius.tests.unit.diagram.views.session;

import static org.junit.Assert.assertNotEquals;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.ui.tools.api.views.common.item.ViewpointItem;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import junit.framework.TestCase;

/**
 * Test class.
 * 
 * @author dlecan
 */
public class ViewpointItemTest extends TestCase {

    private static final String A_STRING = "a string";

    private static final String ANOTHER_STRING = "another string";

    private static final String A_NAME = "a name";

    private Viewpoint viewpoint;

    private Object parent1 = A_STRING;

    private Object parent2 = ANOTHER_STRING;

    private ViewpointItem item;

    private ViewpointItem item2;

    private ViewpointItem item3;

    private Viewpoint viewpoint2;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        Resource newResource = new XMIResourceImpl(URI.createURI("uri1"));
        viewpoint = DescriptionFactory.eINSTANCE.createViewpoint();
        viewpoint2 = DescriptionFactory.eINSTANCE.createViewpoint();

        newResource.getContents().add(viewpoint);
        newResource.getContents().add(viewpoint2);

        item = new ViewpointItemImpl(null, viewpoint, parent1);
        item2 = new ViewpointItemImpl(null, viewpoint, parent1);
        item3 = new ViewpointItemImpl(null, viewpoint2, parent1);

    }

    /**
     * hashCode tests.
     */
    public void testHashCode() {
        assertTrue(item.hashCode() == item2.hashCode());
        assertFalse(item.hashCode() == item3.hashCode());
        assertFalse(item2.hashCode() == item3.hashCode());
    }

    /**
     * equals tests.
     */
    public void testEqualsObject() {
        assertEquals(item, item2);
        assertNotEquals(item, item3);
        assertNotEquals(item2, item3);

    }

}
