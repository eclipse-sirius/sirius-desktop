/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.api.componentization;

import java.util.Arrays;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DView;

/**
 * Test opening a diagram corresponding to an extension of an extension of a
 * diagram description
 * 
 * @author jdupont
 * 
 */
public class DiagramExtensionDescriptionTest extends SiriusDiagramTestCase implements DiagramExtensionDescriptionModeler {

    private DDiagram diagram;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ViewpointRegistry.getInstance().registerFromPlugin(MODELER_PATH);
        String[] modelerDescriptionPaths = { MODELER_PATH, MODELER_EXTENSION1_PATH, MODELER_EXTENSION2_PATH };
        genericSetUp(SEMANTIC_MODEL_PATH, Arrays.asList(modelerDescriptionPaths));
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Test opening a diagram corresponding to an extension of an extension of a
     * diagram description
     * 
     * @throws Exception
     */
    public void testOpeningDiagramWith2Extension() throws Exception {
        initViewpoint(DESCRIPTION_EXTENSION_UN_VIEWPOINT_NAME);
        initViewpoint(DESCRIPTION_VIEWPOINT_NAME);
        initViewpoint(DESCRIPTION_EXTENSION_DEUX_VIEWPOINT_NAME);

        diagram = (DDiagram) getRepresentations(DIAGRAM_DESCRIPTION).toArray()[0];
        assertNotNull(diagram);
        session.addSelectedView((DView) new DRepresentationQuery(diagram).getRepresentationDescriptor().eContainer(), new NullProgressMonitor());

        TestsUtil.emptyEventsFromUIThread();

        EPackage ePackage = (EPackage) semanticModel;
        diagram.getDescription().getAdditionalLayers();
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.getSelectedViews();
        final EClass eClass = (EClass) ePackage.getEClassifiers().get(0);
        final IGraphicalEditPart parent = getEditPart(getFirstDiagramElement(diagram, eClass));
        assertNotNull("Problem during the opening of the editor of a diagram extension (no container edit part instance found)", parent);
    }

    @Override
    protected void tearDown() throws Exception {
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
