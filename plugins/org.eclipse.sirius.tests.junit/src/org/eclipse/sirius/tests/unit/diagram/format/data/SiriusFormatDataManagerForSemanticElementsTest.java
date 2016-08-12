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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.Collection;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.formatdata.EdgeFormatData;
import org.eclipse.sirius.diagram.formatdata.NodeFormatData;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.EdgeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.NodeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticEdgeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SemanticNodeFormatDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SiriusFormatDataManagerForSemanticElements;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * Test class for {@link SiriusFormatDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
public class SiriusFormatDataManagerForSemanticElementsTest extends AbstractSiriusFormatDataManagerForSemanticElementsTest {

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBasicStoreFormat() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
            manager.storeFormatData(diagram);

            // Enable this to save referenced data
            if (REGENERATE_TEST_DATA) {
                saveDiagram(diag, manager.getRootNodeFormatData().values());
            }

            assertTrue("Manager should contain data for diagram " + diag.name, manager.containsData());
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckNumberOfStoredFormats() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
            manager.storeFormatData(diagram);

            final Map<? extends NodeFormatDataKey, NodeFormatData> nodeFormatData = manager.getNodeFormatData();
            final Map<? extends EdgeFormatDataKey, EdgeFormatData> edgeFormatData = manager.getEdgeFormatData();

            assertEquals("Number of expected node format data is wrong for diagram " + diag.name, diag.numberOfNodeFormatData, nodeFormatData.size());
            assertEquals("Number of expected edge format data is wrong for diagram " + diag.name, diag.numberOfEdgeFormatData, edgeFormatData.size());
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckConsistantStoreFormat() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagramEditPart = diag.diagramEditPart;

            final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
            manager.storeFormatData(diagramEditPart);
            final Collection<? extends NodeFormatData> firstNodeValues = manager.getRootNodeFormatData().values();
            final Collection<EdgeFormatData> firstEdgeValues = manager.getEdgeFormatData().values();

            for (int i = 0; i < ITERATIONS; i++) {
                final AdvancedSiriusFormatDataManager otherManager = new SiriusFormatDataManagerForSemanticElements();
                otherManager.storeFormatData(diagramEditPart);
                final Collection<? extends NodeFormatData> secondNodeValues = otherManager.getRootNodeFormatData().values();
                final Collection<EdgeFormatData> secondEdgeValues = otherManager.getEdgeFormatData().values();

                final boolean haveSameFormat = FormatHelper.INSTANCE.haveSameLayout(firstNodeValues, secondNodeValues, ConfigurationFactory.buildConfiguration());
                assertTrue("All node formats should be the same for diagram " + diag.name, haveSameFormat);

                assertTrue("All edge formats should be the same for diagram " + diag.name,
                        FormatHelper.INSTANCE.haveSameLayout(firstEdgeValues, secondEdgeValues, ConfigurationFactory.buildConfiguration()));
            }
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreNodeFormatData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart editPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) editPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "Container p1", name);

        final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
        manager.storeFormatData(editPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticNodeFormatDataKey dataKey = new SemanticNodeFormatDataKey(element.getTarget());
        final NodeFormatData nodeFormatData = manager.getNodeFormatData().get(dataKey);

        assertEquals("Wrong width", 653, nodeFormatData.getWidth());
        assertEquals("Wrong height", 173, nodeFormatData.getHeight());

        assertFalse("Node format data sould have children", nodeFormatData.getChildren().isEmpty());

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreEdgeFormatData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart p1EditPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);
        final IGraphicalEditPart edgeEditPart = (IGraphicalEditPart) ((IGraphicalEditPart) ((IGraphicalEditPart) p1EditPart.getChildren().get(1)).getChildren().get(0)).getSourceConnections().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) edgeEditPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "aC1-2", name);

        final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
        manager.storeFormatData(edgeEditPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticEdgeFormatDataKey dataKey = new SemanticEdgeFormatDataKey(element.getTarget());
        final EdgeFormatData edgeFormatData = manager.getEdgeFormatData().get(dataKey);

        assertEquals("Wrong point list", 4, edgeFormatData.getPointList().size());
    }
}
