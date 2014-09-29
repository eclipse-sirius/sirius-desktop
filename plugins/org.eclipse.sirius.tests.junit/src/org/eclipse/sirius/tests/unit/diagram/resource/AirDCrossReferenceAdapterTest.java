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
package org.eclipse.sirius.tests.unit.diagram.resource;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.business.internal.resource.parser.AirDResourceImpl;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * Test the use of AirDCrossReferenceAdapter.
 * 
 * @author smonnier
 * 
 */
public class AirDCrossReferenceAdapterTest extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(PACKAGES_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
    }

    /**
     * .
     * 
     * @throws Exception
     *             If problem
     */
    public void testAirDCrossReferencerAdapter() throws Exception {

        DDiagram diagram = (DDiagram) createRepresentation(ENTITIES_DESC_NAME, semanticModel);
        /* IEditorPart editor = */DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        ResourceSet resourceSet = semanticModel.eResource().getResourceSet();

        for (Resource resource : resourceSet.getResources()) {
            if (resource instanceof AirDResourceImpl) {
                assertTrue("This resource should have an AirDCrossReferenceAdapter : " + resource.getURI().path(), shouldContainAirDCrossReferencer(resource));
            } else {
                assertFalse("This resource should not have an AirDCrossReferenceAdapter : " + resource.getURI().path(), shouldContainAirDCrossReferencer(resource));
            }
        }

        EPackage ePackage = (EPackage) semanticModel;
        final DDiagramElement elementToDelete = getFirstDiagramElement(diagram, ePackage.getEClassifiers().get(0));
        delete(getEditPart(elementToDelete));

        for (Resource resource : resourceSet.getResources()) {
            if (resource instanceof AirDResourceImpl) {
                assertTrue("This resource should have an AirDCrossReferenceAdapter : " + resource.getURI().path(), shouldContainAirDCrossReferencer(resource));
            } else {
                assertFalse("This resource should not have an AirDCrossReferenceAdapter : " + resource.getURI().path(), shouldContainAirDCrossReferencer(resource));
            }
        }
    }

    private boolean shouldContainAirDCrossReferencer(Resource resource) {
        return Iterables.any(resource.eAdapters(), new Predicate<Adapter>() {

            public boolean apply(Adapter input) {
                return input instanceof AirDCrossReferenceAdapter;
            }
        });
    }
}
