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

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.ecore.design.service.EcoreSamplePlugin;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Sirius uri mapping test.
 * 
 * @author mporhel
 */
public class SiriusUriTests extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String ECORE_MODELER_VIEWPOINT_PATH = "viewpoint:/org.eclipse.sirius.sample.ecore.design/Design";

    private static final String ECORE_MODELER_PHYSICAL_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String ECORE_MODELER_EXTENSION_PHYSICAL_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore_extension.odesign";

    private static final String OTHER_MODELER_CUSTOM_RELATIVE_PATH = "/data/unit/viewpoint_uri/ecoreToReuse.odesign";

    private static final String RESOURCE = "platform:/resource";

    private static final String PLUGIN = "platform:/plugin";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp();

    }

    public void testSiriusUriToPluginUri() {

        URI ecoreModelerSiriusURI = URI.createURI(ECORE_MODELER_VIEWPOINT_PATH);
        URI ecoreModelerRealURI = session.getTransactionalEditingDomain().getResourceSet().getURIConverter().normalize(ecoreModelerSiriusURI);

        assertEquals(PLUGIN + ECORE_MODELER_PHYSICAL_PATH, ecoreModelerRealURI.toString());

        Resource modelerResource = session.getTransactionalEditingDomain().getResourceSet().getResource(ecoreModelerSiriusURI, true);
        Group group = (Group)modelerResource.getContents().get(0);
        Set<Viewpoint> viewpoints = new HashSet<Viewpoint>(group.getOwnedViewpoints());//SiriusRegistry.getInstance().register(ecoreModelerSiriusURI);
        DiagramDescription desc = getEntitiesDiagramDescription(viewpoints);
        assertTrue(!desc.getDefaultLayer().getContainerMappings().isEmpty());
        assertTrue(desc.getDefaultLayer().getReusedMappings().isEmpty());
    }

    private DiagramDescription getEntitiesDiagramDescription(Set<Viewpoint> viewpoints) {
        final String entities = "Entities";
        DiagramDescription result = null;

        loop: for (Viewpoint vp : viewpoints) {
            for (RepresentationDescription desc : vp.getOwnedRepresentations()) {
                if (desc instanceof DiagramDescription && entities.equals(desc.getName())) {
                    result = (DiagramDescription) desc;
                    break loop;
                }
            }
        }

        assertNotNull("No found " + entities + " diagram description", result);
        return result;
    }

    public void testSiriusUriToWorkspaceUri() {
        EclipseTestsSupportHelper.INSTANCE.createProject(EcoreSamplePlugin.PLUGIN_ID);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, OTHER_MODELER_CUSTOM_RELATIVE_PATH, ECORE_MODELER_PHYSICAL_PATH);

        URI ecoreModelerSiriusURI = URI.createURI(ECORE_MODELER_VIEWPOINT_PATH);
        URI ecoreModelerRealURI = session.getTransactionalEditingDomain().getResourceSet().getURIConverter().normalize(ecoreModelerSiriusURI);

        // viewpoint in plugins and workpspace check that viewpoint uri mapping
        // points to workspace
        assertEquals(RESOURCE + ECORE_MODELER_PHYSICAL_PATH, ecoreModelerRealURI.toString());

        Resource modelerResource = session.getTransactionalEditingDomain().getResourceSet().getResource(ecoreModelerSiriusURI, true);
        Group group = (Group)modelerResource.getContents().get(0);
        Set<Viewpoint> viewpoints = new HashSet<Viewpoint>(group.getOwnedViewpoints());// SiriusRegistry.getInstance().register(ecoreModelerRealURI);
        
        DiagramDescription desc = getEntitiesDiagramDescription(viewpoints);
        assertTrue(desc.getDefaultLayer().getContainerMappings().isEmpty());
        assertTrue(!desc.getDefaultLayer().getReusedMappings().isEmpty());

        DiagramElementMapping reusedMapping = desc.getDefaultLayer().getReusedMappings().get(0);
        URI reusedMappingSiriusUri = reusedMapping.eResource().getURI();

        assertTrue(reusedMappingSiriusUri.toString().startsWith("viewpoint:/"));
        URI reusedMappingResourceUri = session.getTransactionalEditingDomain().getResourceSet().getURIConverter().normalize(reusedMappingSiriusUri);
        assertEquals(PLUGIN + ECORE_MODELER_EXTENSION_PHYSICAL_PATH, reusedMappingResourceUri.toString());
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
