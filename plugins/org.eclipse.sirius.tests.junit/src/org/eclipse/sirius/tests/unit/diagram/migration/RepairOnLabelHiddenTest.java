/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.migration;

import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;

import com.google.common.collect.Iterators;

/**
 * Test that the repair process restore only customizations and leave the
 * refresh update the non customized features.
 * 
 * See VP-3894.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class RepairOnLabelHiddenTest extends AbstractRepairMigrateTest {

    private static final String PATH = "/data/unit/repair/labelHidden/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-3894.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "VP-3894.aird";

    private static final String MODELER_RESOURCE_NAME = "VP-3894.odesign";

    private static final String DIAGRAM_NAME = "new VP-3894_Diagram";

    private static final String DIAGRAM_BIS_NAME = "new VP-3894_DiagramBis";

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + SEMANTIC_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + REPRESENTATIONS_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(SiriusTestsPlugin.PLUGIN_ID, PATH + "/" + MODELER_RESOURCE_NAME, "/" + TEMPORARY_PROJECT_NAME + "/" + MODELER_RESOURCE_NAME);

        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
    }

    /**
     * Test that the repair process restore label hidden status.
     * 
     * @throws Exception
     */
    public void testRepairOnLabelHidden() throws Exception {
        // Launch a repair
        runRepairProcess(REPRESENTATIONS_RESOURCE_NAME);

        // Check the fontFormat and the foregroundColor
        URI representationsResourceURI = URI.createPlatformResourceURI("/" + TEMPORARY_PROJECT_NAME + "/" + REPRESENTATIONS_RESOURCE_NAME, true);
        ResourceSet resourceSet = new ResourceSetImpl();
        Resource representationsResource = resourceSet.getResource(representationsResourceURI, true);

        // Check first diagram
        Iterator<DDiagram> filter = Iterators.filter(representationsResource.getAllContents(), DDiagram.class);
        assertTrue("It miss a DDiagram in the aird resource", filter.hasNext());
        DDiagram dDiagram = filter.next();
        assertEquals(DIAGRAM_NAME, new DRepresentationQuery(dDiagram).getRepresentationDescriptor().getName());
        assertEquals("The DDiagram " + DIAGRAM_NAME + " after repair should always contains 12 DDiagramElements", 12, Iterators.size(Iterators.filter(dDiagram.eAllContents(), DDiagramElement.class)));

        DDiagramElement dDiagramElementOfP1 = dDiagram.getOwnedDiagramElements().get(3);
        DDiagramElement dDiagramElementOfP2 = dDiagram.getOwnedDiagramElements().get(4);
        DDiagramElement dDiagramElementOfC1 = dDiagram.getOwnedDiagramElements().get(0);
        DDiagramElement dDiagramElementOfC3 = dDiagram.getOwnedDiagramElements().get(1);
        DNode dNodeOfA1 = ((DNode) dDiagramElementOfC1).getOwnedBorderedNodes().get(0);
        DDiagramElement dDiagramElementOfDatatype = dDiagram.getOwnedDiagramElements().get(2);
        DEdge dEdgeA1 = dDiagram.getEdges().get(1);
        DEdge dEdgeA2 = dDiagram.getEdges().get(2);
        DEdge dEdgeC1 = dDiagram.getEdges().get(0);

        assertFalse("The label of p1 should be keeped visible after a repair, as the hide label is not supported on container", new DDiagramElementQuery(dDiagramElementOfP1).isLabelHidden());
        assertFalse("The label of p2 should be keeped visible after a repair, as the hide label is not supported on container", new DDiagramElementQuery(dDiagramElementOfP2).isLabelHidden());
        assertTrue("The label of c1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC1).isLabelHidden());
        assertTrue("The label of c3 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC3).isLabelHidden());
        assertFalse("The label of a1 should be keeped visible after a repair", new DDiagramElementQuery(dNodeOfA1).isLabelHidden());
        assertTrue("The label of Datatype should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfDatatype).isLabelHidden());
        assertTrue("The label of the edge a1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA1).isLabelHidden());
        assertTrue("The label of the edge a2 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA2).isLabelHidden());
        assertTrue("The label of the edge c1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeC1).isLabelHidden());

        assertFalse("The node p1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfP1).isHidden());
        assertFalse("The node p2 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfP2).isHidden());
        assertFalse("The node c1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC1).isHidden());
        assertFalse("The node c3 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC3).isHidden());
        assertFalse("The node a1 should be keeped visible after a repair", new DDiagramElementQuery(dNodeOfA1).isHidden());
        assertFalse("The node Datatype should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfDatatype).isHidden());
        assertFalse("The edge a1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA1).isHidden());
        assertFalse("The edge a2 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA2).isHidden());
        assertFalse("The edge c1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeC1).isHidden());

        // Check second diagram
        assertTrue("It miss a DDiagram in the aird resource", filter.hasNext());
        DDiagram dDiagramBis = filter.next();
        assertEquals(DIAGRAM_BIS_NAME, new DRepresentationQuery(dDiagramBis).getRepresentationDescriptor().getName());
        assertEquals("The DDiagram " + DIAGRAM_BIS_NAME + " after repair should always contains 12 DDiagramElements", 12,
                Iterators.size(Iterators.filter(dDiagramBis.eAllContents(), DDiagramElement.class)));

        dDiagramElementOfP1 = dDiagramBis.getOwnedDiagramElements().get(3);
        dDiagramElementOfP2 = dDiagramBis.getOwnedDiagramElements().get(4);
        dDiagramElementOfC1 = dDiagramBis.getOwnedDiagramElements().get(0);
        dDiagramElementOfC3 = dDiagramBis.getOwnedDiagramElements().get(1);
        dNodeOfA1 = ((DNode) dDiagramElementOfC1).getOwnedBorderedNodes().get(0);
        dDiagramElementOfDatatype = dDiagramBis.getOwnedDiagramElements().get(2);
        dEdgeA1 = dDiagramBis.getEdges().get(1);
        dEdgeA2 = dDiagramBis.getEdges().get(2);
        dEdgeC1 = dDiagramBis.getEdges().get(0);

        assertFalse("The label of p1 should be keeped visible after a repair, as the hide label is not supported on container", new DDiagramElementQuery(dDiagramElementOfP1).isLabelHidden());
        assertFalse("The label of p2 should be keeped visible after a repair, as the hide label is not supported on container", new DDiagramElementQuery(dDiagramElementOfP2).isLabelHidden());
        assertTrue("The label of c1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC1).isLabelHidden());
        assertTrue("The label of c3 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC3).isLabelHidden());
        assertTrue("The label of a1 should be keeped hidden after a repair", new DDiagramElementQuery(dNodeOfA1).isLabelHidden());
        assertTrue("The label of Datatype should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfDatatype).isLabelHidden());
        assertTrue("The label of the edge a1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA1).isLabelHidden());
        assertTrue("The label of the edge a2 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA2).isLabelHidden());
        assertTrue("The label of the edge c1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeC1).isLabelHidden());

        assertFalse("The node p1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfP1).isHidden());
        assertFalse("The node p2 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfP2).isHidden());
        assertFalse("The node c1 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC1).isHidden());
        assertFalse("The node c3 should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfC3).isHidden());
        assertFalse("The node a1 should be keeped visible after a repair", new DDiagramElementQuery(dNodeOfA1).isHidden());
        assertFalse("The node Datatype should be keeped hidden after a repair", new DDiagramElementQuery(dDiagramElementOfDatatype).isHidden());
        assertFalse("The edge a1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA1).isHidden());
        assertFalse("The edge a2 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeA2).isHidden());
        assertFalse("The edge c1 should be keeped hidden after a repair", new DDiagramElementQuery(dEdgeC1).isHidden());

    }
}
