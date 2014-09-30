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
package org.eclipse.sirius.tests.unit.api.interpreter.crossReferencer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.acceleo.common.utils.IAcceleoCrossReferenceProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoSiriusCrossReferencerProvider;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;

/**
 * VP-3321 : Bad Acceleo CrossReferencer behaviour in an Ecore Modeling Project
 * 
 * Cross referencer for projects with no modeling project nature don't have to
 * use the session cross referencer.
 * 
 * @author nlepine
 * 
 */
public class AcceleoCrossReferencerTest extends SiriusDiagramTestCase implements EcoreModeler {

    private static final String CROSS_REFERENCER_TEST_ECORE = "/org.eclipse.sirius.tests.junit/data/unit/interpreter/crossReferencer/test.ecore";

    private static final String PROJECT = "project";

    private static final String PROJECT_TEST_ECORE = "/project/test.ecore";

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        final List<String> modelerPathes = new ArrayList<String>();
        modelerPathes.add(MODELER_PATH);
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, modelerPathes);
    }

    /**
     * @throws Exception
     */
    public void testAcceleoCrossReferencer() throws Exception {
        // create a project which is not a modeling project and add it a model
        // resource
        EclipseTestsSupportHelper.INSTANCE.createProject(PROJECT);
        EclipseTestsSupportHelper.INSTANCE.copyFile(CROSS_REFERENCER_TEST_ECORE, "project/test.ecore");

        // create a resource set with the model
        ResourceSet resourceSet = new ResourceSetImpl();
        EObject semanticModelFromProject = ModelUtils.load(URI.createPlatformResourceURI(PROJECT_TEST_ECORE, true), resourceSet);

        // check the IAcceleoCrossReferenceProvider : it must be null,
        // semanticModelFromProject does not belong to the modeling project
        // resource set
        checkCrossReferenceProviderFromProject(semanticModelFromProject);

        // create a diagram in the modeling project : the
        // AcceleoSiriusCrossReferencerProvider must be created and
        // registered in the Platform adapters
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        getRepresentations(ENTITIES_DESC_NAME);

        // check the IAcceleoCrossReferenceProvider : it must be an
        // AcceleoSiriusCrossReferencerProvider, semantic model is in the
        // modeling project
        checkCrossReferenceProviderFromModelingProject();

        // re check the IAcceleoCrossReferenceProvider : it must be null,
        // semanticModelFromProject does not belong to the modeling project
        // resource set
        checkCrossReferenceProviderFromProject(semanticModelFromProject);
    }

    /**
     * check the IAcceleoCrossReferenceProvider : it must be an
     * AcceleoSiriusCrossReferencerProvider, semantic model is in the
     * modeling project
     */
    private void checkCrossReferenceProviderFromModelingProject() {
        Object adapter = Platform.getAdapterManager().getAdapter(semanticModel, IAcceleoCrossReferenceProvider.class);
        assertTrue("the AcceleoCrossReferenceProvider must be an insatance of AcceleoSiriusCrossReferencerProvider, the adapted object is in the modeling project resource set",
                adapter instanceof AcceleoSiriusCrossReferencerProvider);
    }

    /**
     * check the IAcceleoCrossReferenceProvider : it must be null,
     * semanticModelFromProject does not belong to the modeling project resource
     * set
     * 
     * @param semanticModelFromProject
     */
    private void checkCrossReferenceProviderFromProject(EObject semanticModelFromProject) {
        Object adapter = Platform.getAdapterManager().getAdapter(semanticModelFromProject, IAcceleoCrossReferenceProvider.class);
        assertNull("the AcceleoCrossReferenceProvider must be null, the adapted object is not in the modeling project resource set", adapter);
    }

}
