/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.RelativeBendpoints;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.description.EdgeMappingImport;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * Tests ensuring that repair work as expected when dealing with MappingImports.
 * 
 * @author alagarde
 * 
 */
public class RepairWithMappingImportsTest extends AbstractRepairMigrateTest {

    private static final String PATH = "/data/unit/repair/RepairWithMappingImport/";

    private static final String SEMANTIC_RESOURCE_NAME = "RepairWithMappingImport.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "RepairWithMappingImport.aird";

    private static final String VSM_RESOURCE_NAME = "RepairWithMappingImport.odesign";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + REPRESENTATIONS_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + VSM_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME);
    }

    /**
     * Test that the repair process restore only customizations and leave the
     * refresh update the non customized features. Test also that the repair
     * process doesn't reset a WorkspaceImage set to display a background image.
     * 
     * @throws Exception
     */
    public void testRepairOnStyleCustomizations() throws Exception {
        // Step 1: Launch a repair
        runRepairProcess(REPRESENTATIONS_RESOURCE_NAME);

        // Step 2: Open session and check that edges bendpoints and mapping have
        // not been modified
        genericSetUp("/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + VSM_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/"
                + REPRESENTATIONS_RESOURCE_NAME);
        DDiagram dDiagram = (DDiagram) getRepresentations("Diagram", semanticModel).iterator().next();

        DEdge dEdge = Iterables.filter(dDiagram.getOwnedDiagramElements(), DEdge.class).iterator().next();
        Edge gmfEdge = null;
        for (Setting setting : session.getSemanticCrossReferencer().getInverseReferences(dEdge)) {
            if (setting.getEObject() instanceof Edge) {
                gmfEdge = (Edge) setting.getEObject();
            }
        }
        assertNotNull("There should be a GMF Edge on the diagram", gmfEdge);
        assertEquals("Wrong number of bendpoints, repair should have kept the Edge appearance", 4, ((RelativeBendpoints) gmfEdge.getBendpoints()).getPoints().size());
        assertTrue("Wrong edge mapping, repair should have kept the Edge appearance", dEdge.getActualMapping() instanceof EdgeMappingImport);

        // Step 3: Check the dirty status of the editor
        IEditorPart diagramEditor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertEquals("At diagram opening after a repair, the session shouldn't be dirty", SessionStatus.SYNC, session.getStatus());

        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        TestsUtil.synchronizationWithUIThread();
    }
}
