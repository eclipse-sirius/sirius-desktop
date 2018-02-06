/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
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

    private Viewpoint viewpoint;

    private Object parent1 = A_STRING;

    private ViewpointItem item;

    private ViewpointItem item2;

    private ViewpointItem item3;

    private Viewpoint viewpoint2;

    private ViewpointItemImpl item4;

    private String representationsFilePath = "/org.eclipse.sirius.tests.junit/data/unit/refresh/sessionWith2SemanticModels/My.aird";

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
        URI sessionResourceURI = URI.createPlatformPluginURI(representationsFilePath, true);
        final Session session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        final Session session2 = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());

        item = new ViewpointItemImpl(session, viewpoint, parent1);
        item2 = new ViewpointItemImpl(session, viewpoint, parent1);
        item3 = new ViewpointItemImpl(session, viewpoint2, parent1);
        item4 = new ViewpointItemImpl(session2, viewpoint2, parent1);

    }

    /**
     * hashCode tests.
     */
    public void testHashCode() {
        assertTrue(item.hashCode() == item2.hashCode());
        assertFalse(item.hashCode() == item3.hashCode());
        assertFalse(item2.hashCode() == item3.hashCode());
        assertFalse(item4.hashCode() == item3.hashCode());
    }

    /**
     * equals tests.
     */
    public void testEqualsObject() {
        assertEquals(item, item2);
        assertNotEquals(item, item3);
        assertNotEquals(item2, item3);
        assertNotEquals(item3, item4);
    }

}
