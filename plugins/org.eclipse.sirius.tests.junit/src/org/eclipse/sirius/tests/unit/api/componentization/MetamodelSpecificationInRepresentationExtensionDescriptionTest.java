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
package org.eclipse.sirius.tests.unit.api.componentization;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.collect.Sets;

public class MetamodelSpecificationInRepresentationExtensionDescriptionTest extends SiriusDiagramTestCase implements MetamodelSpecificationInRepresentationExtensionDescriptionModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ViewpointRegistry.getInstance().registerFromPlugin(MODELER_PATH);

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        initViewpoint(INTERACTIONS_VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(PARTICIPANTS_DESC_NAME, semanticModel);

        assertNotNull(diagram);
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testDisplayMetamodelExtensionElements() throws Exception {
        refresh(diagram);
        assertEquals(2, diagram.getOwnedDiagramElements().size());

        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            assertTrue(element instanceof DNodeContainer);
            assertTrue(((DNodeContainer) element).getOwnedDiagramElements().isEmpty());
        }

        activateViewpoint(ECORE_VIEWPOINT_NAME);
        activateLayer(diagram, "L2");

        refresh(diagram);
        assertEquals(2, diagram.getOwnedDiagramElements().size());

        for (final DDiagramElement element : diagram.getOwnedDiagramElements()) {
            assertTrue(element instanceof DNodeContainer);
            assertEquals(1, ((DNodeContainer) element).getOwnedDiagramElements().size());
        }
    }

    public void testCreateExtensionElements() throws Exception {
        EObject instance = accessor.createInstance("EClass");
        assertNotNull(instance);
    }

    public void testExtensionElementsPackageIsProvided() {
        activateViewpoint(ECORE_VIEWPOINT_NAME);
        Collection<MetamodelDescriptor> descriptors = MetamodelDescriptorManager.INSTANCE.provides(Sets.newHashSet(getSirius(ECORE_VIEWPOINT_NAME)));
        for (final MetamodelDescriptor descriptor : descriptors) {
            if (descriptor instanceof EcoreMetamodelDescriptor) {
                EPackage ePackage = ((EcoreMetamodelDescriptor) descriptor).resolve();
                if (EcorePackage.eINSTANCE.equals(ePackage))
                    return;
            }
        }
        fail();

    }

    private Viewpoint getSirius(String name) {
        for (final Viewpoint viewpoint : session.getSelectedViewpoints(false)) {
            if (name.equals(viewpoint.getName()))
                return viewpoint;
        }
        throw new IllegalArgumentException();
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
