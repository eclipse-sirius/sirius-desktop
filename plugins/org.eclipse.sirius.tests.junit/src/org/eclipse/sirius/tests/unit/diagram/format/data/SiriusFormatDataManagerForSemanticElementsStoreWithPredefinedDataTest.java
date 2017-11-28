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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.format.semantic.SiriusFormatDataManagerForSemanticElements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test class for {@link SiriusFormatDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
@RunWith(value = Parameterized.class)
public class SiriusFormatDataManagerForSemanticElementsStoreWithPredefinedDataTest extends AbstractSiriusFormatDataManagerForSemanticElementsTest {

    private final Representation representation;

    private final double[] zoomData;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Parameters
    public static Collection<Object[]> data() {
        // We could use @Theories and @Datapoints but the theory stops as soon
        // as there is a failure. With parameters, we have feedback for all
        // scenarii.
        Collection<Object[]> data = new ArrayList<>();
        for (Representation rep : ALL_REPRESENTATIONS) {
            for (double[] zoomData : ALL_ZOOM_DATA) {
                data.add(new Object[] { rep, zoomData });
            }
        }
        return data;
    }

    /**
     * Constructor for parameterized test.
     * 
     * @param representation
     *            a representation description
     * @param zoomData
     *            the zoom data to set for the current scenario
     * @throws Exception
     */
    public SiriusFormatDataManagerForSemanticElementsStoreWithPredefinedDataTest(Representation representation, double[] zoomData) throws Exception {
        this.representation = representation;
        this.zoomData = zoomData;
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testStoreFormatAgainstPredefinedData() throws Exception {
        StringBuilder differences = new StringBuilder();
        double zoomLevel = zoomData[0];
        double distance = zoomData[1];

        for (final Diagram diag : openAllDiagramsInRepresentation(representation)) {
            changeZoomLevel(diag, zoomLevel);

            Configuration configuration = ConfigurationFactory.buildConfiguration();
            configuration.getEdgeConfiguration().setDistanceAroundPointsOfEdgeBendpointsList(distance);

            // Store format data from diagram
            final DiagramEditPart diagramEditPart = diag.diagramEditPart;
            final AdvancedSiriusFormatDataManager manager = new SiriusFormatDataManagerForSemanticElements();
            manager.storeFormatData(diagramEditPart);

            // Enable this if you want to generate referenced files
            if (REGENERATE_TEST_DATA) {
                final String path = getPlatformRelatedXmiDataPath() + encodeDiagramName(diag) + XMI_EXTENSION;
                saveDiagram(manager.getRootNodeFormatData().values(), path);
            }

            String path = getPlatformRelatedFullXmiDataPath() + encodeDiagramName(diag) + XMI_EXTENSION;
            FormatDifference<?> foundDifference = loadAndCompare(diag, path, manager, configuration);
            if (foundDifference != null) {
                differences.append("\n. in the diagram " + diag.name + " (zoom level: " + zoomLevel + ")" + foundDifference);
            }
        }

        assertTrue("Found differences for representation named " + representation.name + ", zoom " + zoomLevel + " and distance around bendpoints " + distance + ": \n" + differences,
                differences.length() == 0);
    }
}
