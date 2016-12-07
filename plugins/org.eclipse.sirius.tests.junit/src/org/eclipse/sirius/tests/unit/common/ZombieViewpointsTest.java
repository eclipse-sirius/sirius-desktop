/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusTestCase;
import org.eclipse.sirius.tools.api.command.ICommandFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

/**
 * Test that once a VSM has been removed from the workspace, the Viewpoints it
 * defines are correctly removed and forgotten, and can not reappear later.
 * 
 * @see https://bugs.eclipse.org/bugs/show_bug.cgi?id=475594
 * @author pcdavid
 */
public class ZombieViewpointsTest extends SiriusTestCase {

    private static final String WKS_PATH = "/sample/bug.odesign";

    private static final URI WKS_URI = URI.createPlatformResourceURI(ZombieViewpointsTest.WKS_PATH, true);

    @Override
    protected ICommandFactory getCommandFactory() {
        return null;
    }

    public void testViewpointRemovedFromWorkspaceDoNotReappear() throws Exception {
        EclipseTestsSupportHelper helper = EclipseTestsSupportHelper.INSTANCE;
        Set<Viewpoint> initialVPs = Sets.newHashSet(ViewpointRegistry.getInstance().getViewpoints());
        Set<URI> initialRegistryResources = snapshotRegistryResources();

        // Copy the first version of "/sample/bug.odesign" and check its
        // contribution to the Viewpoint registry
        helper.createProject("sample");
        helper.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/zombies/bug-1.odesign", ZombieViewpointsTest.WKS_PATH, true);
        Set<Viewpoint> v1VPs = Sets.newHashSet(ViewpointRegistry.getInstance().getViewpoints());
        assertTrue("Initially available Viewpoints should still all be visible", v1VPs.containsAll(initialVPs));
        Set<Viewpoint> v1Contributions = Sets.difference(v1VPs, initialVPs);
        assertEquals("The first version of the workspace VSM should contribute only 1 new Viewpoint", 1, v1Contributions.size());
        Viewpoint v1 = v1Contributions.iterator().next();
        assertTrue("The first version of the VSM should contribute Viewpoint 'A'", !v1.eIsProxy() && "A".equals(v1.getName()) && ZombieViewpointsTest.WKS_URI.equals(v1.eResource().getURI()));
        helper.deleteProject("sample");
        assertTrue("The viewpoints contributed by the first version of the VSM should not be visible once the VSM has been deleted", !ViewpointRegistry.getInstance().getViewpoints().contains(v1));
        assertTrue("The previously contributed viewpoint should be unloaded and removed from the registry", v1.eIsProxy() && v1.eResource() == null);

        // Copy the second version of "/sample/bug.odesign" and check the
        // Viewpoint registry's state
        helper.createProject("sample");
        helper.copyFile(SiriusTestsPlugin.PLUGIN_ID, "/data/unit/zombies/bug-2.odesign", ZombieViewpointsTest.WKS_PATH, true);
        Set<Viewpoint> v2VPs = Sets.newHashSet(ViewpointRegistry.getInstance().getViewpoints());
        assertTrue("Initially available Viewpoints should still all be visible", v2VPs.containsAll(initialVPs));
        Set<Viewpoint> v2Contributions = Sets.difference(v2VPs, initialVPs);
        assertEquals("The second version of the workspace VSM should contribute only 1 new Viewpoint", 1, v2Contributions.size());
        Viewpoint v2 = v2Contributions.iterator().next();
        assertTrue("The second version of the VSM should contribute Viewpoint 'B'", !v2.eIsProxy() && "B".equals(v2.getName()) && ZombieViewpointsTest.WKS_URI.equals(v2.eResource().getURI()));
        assertTrue("The previously contributed viewpoint should be unloaded and removed from the registry", v1.eIsProxy() && v1.eResource() == null);
        helper.deleteProject("sample");

        // Check that after removing the test fixtures, the registry is back to
        // its initial state
        assertEquals("The Viewpoint registry should be back to its initial states at the end of the test", initialRegistryResources, snapshotRegistryResources());
    }

    private HashSet<URI> snapshotRegistryResources() {
        ResourceSet resourceSet = ViewpointRegistry.getInstance().getViewpoints().iterator().next().eResource().getResourceSet();
        return Sets.newHashSet(Iterables.transform(resourceSet.getResources(), new Function<Resource, URI>() {
            @Override
            public URI apply(Resource res) {
                return res.getURI();
            };
        }));
    }
}
