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
package org.eclipse.sirius.tests.unit.diagram.refresh;

import java.util.Map;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.internal.refresh.GMFHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.AbstractMappingImport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.google.common.collect.Maps;

/**
 * Test the behavior of the MappingUpdateVisitor's cache on layer (de)activation
 * when a same semantic element is used by several mapping import hierarchy. In
 * the previous implementation the cache key was this semantic element only
 * causing refresh issues in actual mapping hierarchy look up.
 * 
 * The existing diagram elements should not be deleted/recreated on layer
 * (de)activation but their actual mapping should be updated.
 * 
 * Then their location (GFM and Draw2D) should conserved.
 * 
 * See VP-3777
 * 
 * @author mporhel
 */
@RunWith(JUnit4.class)
public class MultiMappingImportChainsWithSameSemanticTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/VP-3777/3777.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/VP-3777/3777.odesign";

    private static final String REPRESENTATIONS_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/refresh/VP-3777/3777.aird";

    private static final String REPRESENTATION_DESC_NAME = "3777";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATIONS_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).iterator().next();
        DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @After
    public void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }

    /**
     * Test the behavior of the MappingUpdateVisitorCache on layer
     * (de)activation when a same semantic element is used by several mapping
     * import hierarchy.
     * 
     * See VP-3777
     */
    @Test
    public void testMappingUpdateVisitorCacheAndLocationKeepedOnDiagram() {
        assertEquals("The default layer only shoud be activated.", 1, diagram.getActivatedLayers().size());
        assertEquals("The test data should present only 4 diagram elements.", 4, diagram.getOwnedDiagramElements().size());

        Map<DDiagramElement, Rectangle> gmfBounds = Maps.newHashMap();
        Map<DDiagramElement, Rectangle> partBounds = Maps.newHashMap();

        // Register the current bounds of the DDiagramElements
        for (DDiagramElement dde : diagram.getOwnedDiagramElements()) {
            assertFalse(dde.getDiagramElementMapping() instanceof AbstractMappingImport);

            Node gmfNode = getGmfNode(dde);
            gmfBounds.put(dde, GMFHelper.getBounds(gmfNode));

            IGraphicalEditPart editPart = getEditPart(dde);
            partBounds.put(dde, editPart.getFigure().getBounds().getCopy());
        }

        activateLayer(diagram, "test");
        TestsUtil.synchronizationWithUIThread();
        // The test layer activation trigger the import mappings activation.
        checkElementAndBoundsConservation(gmfBounds, partBounds, true);

        deactivateLayer(diagram, "test");
        TestsUtil.synchronizationWithUIThread();

        // The test layer deactivation trigger the root mappings activation.
        checkElementAndBoundsConservation(gmfBounds, partBounds, false);

        activateLayer(diagram, "test");
        TestsUtil.synchronizationWithUIThread();

        // The test layer activation trigger the import mappings activation.
        checkElementAndBoundsConservation(gmfBounds, partBounds, true);

        deactivateLayer(diagram, "test");
        TestsUtil.synchronizationWithUIThread();

        // The test layer deactivation trigger the root mappings activation.
        checkElementAndBoundsConservation(gmfBounds, partBounds, false);
    }

    private void checkElementAndBoundsConservation(Map<DDiagramElement, Rectangle> expectedGmfBounds, Map<DDiagramElement, Rectangle> expectedPartBounds, boolean importMapping) {
        for (DDiagramElement dde : diagram.getOwnedDiagramElements()) {
            // Check that the actual mapping of the diagram element corresponds
            // to the expected state.
            DiagramElementMapping mapping = dde.getDiagramElementMapping();
            assertEquals(importMapping, mapping instanceof AbstractMappingImport);

            // Check that the actual mapping of the diagram element corresponds
            // to the expected state.
            DiagramDescription description = dde.getParentDiagram().getDescription();
            Layer expectedMappingDeclarationLayer = importMapping ? description.getAdditionalLayers().get(0) : description.getDefaultLayer();
            assertEquals(expectedMappingDeclarationLayer, mapping.eContainer());

            Node gmfNode = getGmfNode(dde);
            IGraphicalEditPart editPart = getEditPart(dde);
            // Assert that the current DDiagramElement is already known and was
            // not created by the last refresh.
            assertTrue(expectedGmfBounds.containsKey(dde));
            assertTrue(expectedPartBounds.containsKey(dde));

            // Check the GMF location conservation
            Rectangle gmfExpectedBounds = expectedGmfBounds.get(dde);
            Rectangle currentGmfBounds = GMFHelper.getBounds(gmfNode);
            assertEquals(gmfExpectedBounds.getLocation(), currentGmfBounds.getLocation());

            // Check the figure location conservation
            Rectangle partExpectedBounds = expectedPartBounds.get(dde);
            Rectangle currentPartBounds = editPart.getFigure().getBounds();
            assertEquals(partExpectedBounds.getLocation(), currentPartBounds.getLocation());

            // For container check the bounds conservation. For Nodes, the
            // styles changes the size but the location has already been tested.
            if (dde instanceof DDiagramElementContainer) {
                assertEquals(gmfExpectedBounds, currentGmfBounds);
                assertEquals(partExpectedBounds, currentPartBounds);
            }
        }
    }
}
