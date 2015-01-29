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

import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry.ViewpointComparator;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

public class SiriusComparatorTests extends TestCase {

    private static final String SOURCE_LOCATION = "/data/unit/comparator/";

    private static final String VSM_1 = "vp1.odesign";

    private static final String VSM_2 = "vp2.odesign";

    private static final String TEMPORARY_PROJECT_NAME = "DesignerTestProject";

    private static final String TEMPORARY_PROJECT_NAME_2 = TEMPORARY_PROJECT_NAME + "2";

    private Viewpoint vp1_P1_resource;

    private Viewpoint vp2_P1_resource;

    private Viewpoint vp2_P2_resource;

    private Viewpoint vp1_plugin;

    private Viewpoint vp2_plugin;

    private ViewpointComparator comp;

    private Set<Viewpoint> registeredInTest = null;

    public void testIdentity() {
        if (Movida.isEnabled()) {
            return;
        }
        assertEquals(0, comp.compare(vp1_P1_resource, vp1_P1_resource));
        assertEquals(0, comp.compare(vp2_P1_resource, vp2_P1_resource));
        assertEquals(0, comp.compare(vp2_P2_resource, vp2_P2_resource));

        assertEquals(0, comp.compare(vp1_plugin, vp1_plugin));
        assertEquals(0, comp.compare(vp2_plugin, vp2_plugin));
    }

    public void testSameLabelDifferentProjects() {
        if (Movida.isEnabled()) {
            return;
        }
        int comp1 = comp.compare(vp1_P1_resource, vp2_P2_resource);
        int comp2 = comp.compare(vp2_P2_resource, vp1_P1_resource);

        assertEquals("Comparator is not stable", comp1, -comp2);
        assertTrue("project1/vp1.odesign < project2/vp2.odesign", comp1 < 0);
        assertTrue("project1/vp1.odesign < project2/vp2.odesign", comp2 > 0);
    }

    public void testSameLabelDifferentResourceNameAndProjects() {
        if (Movida.isEnabled()) {
            return;
        }
        int comp1 = comp.compare(vp2_P1_resource, vp2_P2_resource);
        int comp2 = comp.compare(vp2_P2_resource, vp2_P1_resource);

        assertEquals("Comparator is not stable", comp1, -comp2);
        assertTrue("project1/vp2.odesign < project2/vp2.odesign", comp1 < 0);
        assertTrue("project1/vp2.odesign < project2/vp2.odesign", comp2 > 0);
    }

    public void testSameLabelSameProject() {
        if (Movida.isEnabled()) {
            return;
        }
        int comp1 = comp.compare(vp1_P1_resource, vp2_P1_resource);
        int comp2 = comp.compare(vp2_P1_resource, vp1_P1_resource);

        assertEquals("Comparator is not stable", comp1, -comp2);
        assertTrue("project1/vp1.odesign < project1/vp2.odesign", comp1 < 0);
        assertTrue("project1/vp1.odesign < project1/vp2.odesign", comp2 > 0);
    }

    public void testSameLabelSamePlugin() {
        if (Movida.isEnabled()) {
            return;
        }
        int comp1 = comp.compare(vp1_plugin, vp2_plugin);
        int comp2 = comp.compare(vp2_plugin, vp1_plugin);

        assertEquals("Comparator is not stable", comp1, -comp2);
        assertTrue("plugin/../vp1.odesign < plugin/../vp2.odesign", comp1 < 0);
        assertTrue("plugin/../vp1.odesign < plugin/../vp2.odesign", comp2 > 0);
    }

    public void testSameLabelDifferentLocation() {
        if (Movida.isEnabled()) {
            return;
        }
        int comp1 = comp.compare(vp1_P1_resource, vp1_plugin);
        int comp2 = comp.compare(vp1_plugin, vp1_P1_resource);

        assertEquals("Comparator is not stable", comp1, -comp2);
        assertTrue("project1/vp1.odesign < plugin/../vp1.odesign", comp1 < 0);
        assertTrue("project1/vp1.odesign < plugin/../vp1.odesign", comp2 > 0);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        comp = new ViewpointRegistry.ViewpointComparator();
        if (Movida.isEnabled()) {
            return;
        }
        registeredInTest = Sets.newHashSet();

        initProject1();
        initProject2();
        initFromPlugins();

    }

    private void initProject1() {
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME);

        String pluginPath = SOURCE_LOCATION + VSM_1;
        String wksPath = TEMPORARY_PROJECT_NAME + "/" + VSM_1;
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);

        pluginPath = SOURCE_LOCATION + VSM_2;
        wksPath = TEMPORARY_PROJECT_NAME + "/" + VSM_2;
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);

        vp1_P1_resource = getFirstSirius(TEMPORARY_PROJECT_NAME + "/", VSM_1, false);
        vp2_P1_resource = getFirstSirius(TEMPORARY_PROJECT_NAME + "/", VSM_2, false);

    }

    private void initProject2() {
        EclipseTestsSupportHelper.INSTANCE.createProject(TEMPORARY_PROJECT_NAME_2);

        String pluginPath = SOURCE_LOCATION + VSM_2;
        String wksPath = TEMPORARY_PROJECT_NAME_2 + "/" + VSM_2;
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, pluginPath, wksPath);

        vp2_P2_resource = getFirstSirius(TEMPORARY_PROJECT_NAME_2 + "/", VSM_2, false);
    }

    private void initFromPlugins() {
        vp1_plugin = getFirstSirius(SiriusTestsPlugin.PLUGIN_ID + SOURCE_LOCATION, VSM_1, true);
        vp2_plugin = getFirstSirius(SiriusTestsPlugin.PLUGIN_ID + SOURCE_LOCATION, VSM_2, true);

    }

    private Viewpoint getFirstSirius(String location, String vsm1, boolean plugin) {
        String path = location + vsm1;

        Viewpoint firstVP = null;
        if (plugin) {
            Set<Viewpoint> registered = ViewpointRegistry.getInstance().registerFromPlugin(path);
            registeredInTest.addAll(registered);
            assertFalse(registered.isEmpty());
            firstVP = registered.iterator().next();
        } else {
            URI uri = URI.createPlatformResourceURI(path, true);
            // Wkp listener
            for (Viewpoint vp : ViewpointRegistry.getInstance().getViewpoints()) {
                URI registeredUri = vp.eResource().getURI();
                if (registeredUri.equals(uri)) {
                    firstVP = vp;
                    break;
                }
            }
        }
        return firstVP;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        if (Movida.isEnabled()) {
            return;
        }

        vp1_P1_resource = null;
        vp2_P1_resource = null;
        vp2_P2_resource = null;

        vp1_plugin = null;
        vp2_plugin = null;

        for (Viewpoint vp : registeredInTest) {
            ViewpointRegistry.getInstance().disposeFromPlugin(vp);
        }
        registeredInTest.clear();
        registeredInTest = null;

        comp = null;
    }
}
