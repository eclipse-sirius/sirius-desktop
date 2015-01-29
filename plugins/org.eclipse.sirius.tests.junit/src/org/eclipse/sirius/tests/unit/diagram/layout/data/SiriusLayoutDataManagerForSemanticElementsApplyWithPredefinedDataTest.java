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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.LayoutHelper.LayoutDifference;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.layoutdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.AdvancedSiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.semantic.SiriusLayoutDataManagerForSemanticElements;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

/**
 * Test class for {@link SiriusLayoutDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
@RunWith(value = Parameterized.class)
public class SiriusLayoutDataManagerForSemanticElementsApplyWithPredefinedDataTest extends AbstractSiriusLayoutDataManagerForSemanticElementsTest {

    private final Representation representationToCopyLayout;

    private final Representation representationToPasteLayout;

    private final double[] diagramToCopyLayoutZoomData;

    private final double[] diagramToPasteLayoutZoomData;

    protected static final ResourceSetListenerImpl ROLLBACK_LISTENER = new ResourceSetListenerImpl() {
        @Override
        public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
            throw new RollbackException(new Status(IStatus.ERROR, SiriusTestsPlugin.PLUGIN_ID, "Don't want to change this diagram"));
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isAggregatePrecommitListener() {
            return true;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean isPrecommitOnly() {
            return true;
        }
    };

    protected static final Predicate<Diagram> ONLY_RAW_DIAGRAM = new Predicate<Diagram>() {
        public boolean apply(final Diagram input) {
            return input.raw;
        }
    };

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
        Collection<Object[]> data = Lists.newArrayList();
        for (Representation copyRep : ALL_REPRESENTATIONS) {
            for (Representation pasteRep : ALL_REPRESENTATIONS) {
                for (double[] copyZoomData : ALL_ZOOM_DATA) {
                    for (double[] pasteZoomData : ALL_ZOOM_DATA) {
                        data.add(new Object[] { copyRep, copyZoomData, pasteRep, pasteZoomData });
                    }
                }
            }
        }
        return data;
    }

    /**
     * Constructor for parameterized test.
     * 
     * * @param representationToCopyLayout a representation description
     * 
     * @param representationToPasteLayout
     *            a representation description
     * @param diagramToCopyLayoutZoomData
     *            the zoom data to set for layout copy in the current scenario
     * @param diagramToPasteLayoutZoomData
     *            the zoom data to set for layout application in the current
     *            scenario
     * @throws Exception
     */
    public SiriusLayoutDataManagerForSemanticElementsApplyWithPredefinedDataTest(Representation representationToCopyLayout, double[] diagramToCopyLayoutZoomData,
            Representation representationToPasteLayout, double[] diagramToPasteLayoutZoomData) throws Exception {
        this.representationToCopyLayout = representationToCopyLayout;
        this.representationToPasteLayout = representationToPasteLayout;
        this.diagramToCopyLayoutZoomData = diagramToCopyLayoutZoomData;
        this.diagramToPasteLayoutZoomData = diagramToPasteLayoutZoomData;
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedLayoutDataOnRawDiagramsWithZoom() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }
        StringBuilder differences = new StringBuilder();
        for (final Diagram diagramToPasteLayout : Collections2.filter(representationToPasteLayout.diagrams, ONLY_RAW_DIAGRAM)) {
            for (final Diagram diagramToCopyLayout : openAllDiagramsInRepresentation(representationToCopyLayout, true)) {
                // Test only if one or the other zoom level is 1
                // (IDENTITY) to reduce length of test
                if (diagramToCopyLayoutZoomData[0] == IDENTITY_ZOOM_LEVEL || diagramToPasteLayoutZoomData[0] == IDENTITY_ZOOM_LEVEL) {

                    Configuration configuration = ConfigurationFactory.buildConfiguration();

                    configuration.getEdgeConfiguration().setDistanceAroundPointsOfEdgeBendpointsList(Math.max(diagramToCopyLayoutZoomData[1], diagramToPasteLayoutZoomData[1]));

                    applyPredefinedLayoutDataOnRawDiagramsWithZoom(diagramToCopyLayout, diagramToPasteLayout, diagramToCopyLayoutZoomData[0], diagramToPasteLayoutZoomData[0], configuration,
                            differences);
                }
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    protected void applyPredefinedLayoutDataOnRawDiagramsWithZoom(Diagram diagramToCopyLayout, Diagram diagramToPasteLayout, double diagramToCopyLayoutZoomLevel, double diagramToPasteLayoutZoomLevel,
            Configuration configuration, StringBuilder differences) throws Exception {

        final DiagramEditPart diagramEditPart = diagramToCopyLayout.diagramEditPart;

        // Store layout data from initial diagram
        final AdvancedSiriusLayoutDataManager originalManager = new SiriusLayoutDataManagerForSemanticElements();
        originalManager.storeLayoutData(diagramEditPart);

        try {
            openRawDiagram(diagramToPasteLayout);

            // Apply it on destination diagram, where all nodes and edges are
            // located around (0,0) (but not exactly)
            final DiagramEditPart destinationDiagram = diagramToPasteLayout.diagramEditPart;

            changeZoomLevel(diagramToCopyLayout, diagramToCopyLayoutZoomLevel);
            changeZoomLevel(diagramToPasteLayout, diagramToPasteLayoutZoomLevel);

            final AdvancedSiriusLayoutDataManager newManager = new SiriusLayoutDataManagerForSemanticElements();

            final RecordingCommand command = new RecordingCommand(destinationDiagram.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    // Update diagram, but transaction will be
                    // rollbacked
                    originalManager.applyLayout(destinationDiagram);
                    newManager.storeLayoutData(destinationDiagram);
                }
            };

            try {
                // Force rollback of transaction to let raw diagram
                // unchanged
                destinationDiagram.getEditingDomain().addResourceSetListener(ROLLBACK_LISTENER);
                destinationDiagram.getEditingDomain().getCommandStack().execute(command);
            } finally {
                destinationDiagram.getEditingDomain().removeResourceSetListener(ROLLBACK_LISTENER);
            }

            final String partialPath = "from___" + encodeDiagramName(diagramToCopyLayout) + "___to___" + encodeDiagramName(diagramToPasteLayout) + XMI_EXTENSION;

            // Enable this if you want to generate referenced files
            if (REGENERATE_TEST_DATA) {
                final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                saveDiagram(newManager.getRootNodeLayoutData().values(), path);
            }

            String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
            String message = "between diagram ";
            message += diagramToCopyLayout.name + " (zoom level: " + diagramToCopyLayoutZoomLevel + ")";
            message += " and raw diagram " + diagramToPasteLayout.name + " (zoom level: " + diagramToPasteLayoutZoomLevel + ")";
            LayoutDifference<?> foundDifference = loadAndCompare(diagramToPasteLayout, fullPath, newManager, configuration);
            if (foundDifference != null) {
                differences.append("\n. " + message + foundDifference);
            }

        } finally {
            closeRawDiagram(diagramToPasteLayout);
        }
    }
}
