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
package org.eclipse.sirius.tests.unit.api.session;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.viewpoint.description.DescriptionFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import junit.framework.TestCase;

public class ViewpointSelectionTests extends TestCase {

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        /* init all */
        ViewpointSelection.getViewpoints("*");
    }

    public void testFileExtensionWithoutPattern() {
        if (Movida.isEnabled()) {
            // The new Movida registry does not implement the
            // registerFromWorkspace() API.
            return;
        }
        Set<Viewpoint> createdViewpoints = new HashSet<Viewpoint>();
        createdViewpoints.add(createViewpoint("lolita"));

        ViewpointRegistry.getInstance().registerFromWorkspace(createdViewpoints);

        Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints("lolita");
        assertFalse(viewpoints.isEmpty());
    }

    public void testMultipleFileExtension() {
        if (Movida.isEnabled()) {
            // The new Movida registry does not implement the
            // registerFromWorkspace() API.
            return;
        }
        Set<Viewpoint> createdViewpoints = new HashSet<Viewpoint>();
        createdViewpoints.add(createViewpoint("lolita tatata       tutu"));

        ViewpointRegistry.getInstance().registerFromWorkspace(createdViewpoints);

        Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints("lolita");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("tatata");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("tutu");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("toto");
        assertTrue(viewpoints.isEmpty());
    }

    public void testFileExtensionWithPattern() {
        if (Movida.isEnabled()) {
            // The new Movida registry does not implement the
            // registerFromWorkspace() API.
            return;
        }
        Set<Viewpoint> createdViewpoints = new HashSet<Viewpoint>();
        createdViewpoints.add(createViewpoint("*.treztattaaaazzz"));

        ViewpointRegistry.getInstance().registerFromWorkspace(createdViewpoints);

        Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints("treztattaaaazzz");
        assertFalse(viewpoints.isEmpty());
    }

    public void testMultipleFileExtensionWithPattern() {
        if (Movida.isEnabled()) {
            // The new Movida registry does not implement the
            // registerFromWorkspace() API.
            return;
        }
        Set<Viewpoint> createdViewpoints = new HashSet<Viewpoint>();
        createdViewpoints.add(createViewpoint("*.treztattaaaazzz toto *.tutu   tata  "));

        ViewpointRegistry.getInstance().registerFromWorkspace(createdViewpoints);

        Set<Viewpoint> viewpoints = ViewpointSelection.getViewpoints("treztattaaaazzz");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("toto");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("tutu");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("tata");
        assertFalse(viewpoints.isEmpty());
        assertEquals(1, viewpoints.size());
        
        viewpoints = ViewpointSelection.getViewpoints("titi");
        assertTrue(viewpoints.isEmpty());
    }

    private Viewpoint createViewpoint(String modelExtension) {
        Viewpoint vp = DescriptionFactory.eINSTANCE.createViewpoint();
        vp.setModelFileExtension(modelExtension);
        return vp;
    }

}
