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
package org.eclipse.sirius.tests.unit.diagram.layout.data;

import java.util.Collection;
import java.util.Map;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.layoutdata.EdgeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.NodeLayoutData;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.EdgeLayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.NodeLayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SemanticEdgeLayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SemanticNodeLayoutDataKey;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;
import org.eclipse.sirius.viewpoint.DRepresentationElement;

/**
 * Test class for {@link SiriusLayoutDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
public class SiriusLayoutDataManagerForSemanticElementsTest extends AbstractSiriusLayoutDataManagerForSemanticElementsTest {

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testBasicStoreLayout() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            final AdvancedSiriusLayoutDataManager manager = new SiriusLayoutDataManagerForSemanticElements();
            manager.storeLayoutData(diagram);

            // Enable this to save referenced data
            if (REGENERATE_TEST_DATA) {
                saveDiagram(diag, manager.getRootNodeLayoutData().values());
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
    public void testCheckNumberOfStoredLayouts() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagram = diag.diagramEditPart;

            final AdvancedSiriusLayoutDataManager manager = new SiriusLayoutDataManagerForSemanticElements();
            manager.storeLayoutData(diagram);

            final Map<? extends NodeLayoutDataKey, NodeLayoutData> nodeLayoutData = manager.getNodeLayoutData();
            final Map<? extends EdgeLayoutDataKey, EdgeLayoutData> edgeLayoutData = manager.getEdgeLayoutData();

            assertEquals("Number of expected node layout data is wrong for diagram " + diag.name, diag.numberOfNodeLayoutData, nodeLayoutData.size());
            assertEquals("Number of expected edge layout data is wrong for diagram " + diag.name, diag.numberOfEdgeLayoutData, edgeLayoutData.size());
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testCheckConsistantStoreLayout() throws Exception {
        for (final Diagram diag : getAndOpenAllDiagrams()) {
            final DiagramEditPart diagramEditPart = diag.diagramEditPart;

            final AdvancedSiriusLayoutDataManager manager = new SiriusLayoutDataManagerForSemanticElements();
            manager.storeLayoutData(diagramEditPart);
            final Collection<? extends NodeLayoutData> firstNodeValues = manager.getRootNodeLayoutData().values();
            final Collection<EdgeLayoutData> firstEdgeValues = manager.getEdgeLayoutData().values();

            for (int i = 0; i < ITERATIONS; i++) {
                final AdvancedSiriusLayoutDataManager otherManager = new SiriusLayoutDataManagerForSemanticElements();
                otherManager.storeLayoutData(diagramEditPart);
                final Collection<? extends NodeLayoutData> secondNodeValues = otherManager.getRootNodeLayoutData().values();
                final Collection<EdgeLayoutData> secondEdgeValues = otherManager.getEdgeLayoutData().values();

                final boolean haveSameLayout = LayoutHelper.INSTANCE.haveSameLayout(firstNodeValues, secondNodeValues, ConfigurationFactory.buildConfiguration());
                assertTrue("All node layouts should be the same for diagram " + diag.name, haveSameLayout);

                assertTrue("All edge layouts should be the same for diagram " + diag.name,
                        LayoutHelper.INSTANCE.haveSameLayout(firstEdgeValues, secondEdgeValues, ConfigurationFactory.buildConfiguration()));
            }
        }
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreNodeLayoutData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart editPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) editPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "Container p1", name);

        final AdvancedSiriusLayoutDataManager manager = new SiriusLayoutDataManagerForSemanticElements();
        manager.storeLayoutData(editPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticNodeLayoutDataKey dataKey = new SemanticNodeLayoutDataKey(element.getTarget());
        final NodeLayoutData nodeLayoutData = manager.getNodeLayoutData().get(dataKey);

        assertEquals("Wrong width", 653, nodeLayoutData.getWidth());
        assertEquals("Wrong height", 173, nodeLayoutData.getHeight());

        assertFalse("Node layout data sould have children", nodeLayoutData.getChildren().isEmpty());

    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testStoreEdgeLayoutData() throws Exception {
        openAllDiagramsInRepresentation(DIAG_TYPE2_MYPACKAGE.parent);

        final IGraphicalEditPart p1EditPart = (IGraphicalEditPart) DIAG_TYPE2_MYPACKAGE.diagramEditPart.getChildren().get(0);
        final IGraphicalEditPart edgeEditPart = (IGraphicalEditPart) ((IGraphicalEditPart) ((IGraphicalEditPart) p1EditPart.getChildren().get(1)).getChildren().get(0)).getSourceConnections().get(0);

        final DRepresentationElement element = (DRepresentationElement) ((View) edgeEditPart.getModel()).getElement();
        final String name = element.getName();
        assertEquals("Wrong edit part", "aC1-2", name);

        final AdvancedSiriusLayoutDataManager manager = new SiriusLayoutDataManagerForSemanticElements();
        manager.storeLayoutData(edgeEditPart);
        assertTrue("Manager should contain data for diagram " + DIAG_TYPE2_MYPACKAGE.name, manager.containsData());

        final SemanticEdgeLayoutDataKey dataKey = new SemanticEdgeLayoutDataKey(element.getTarget());
        final EdgeLayoutData edgeLayoutData = manager.getEdgeLayoutData().get(dataKey);

        assertEquals("Wrong point list", 4, edgeLayoutData.getPointList().size());
    }
}
