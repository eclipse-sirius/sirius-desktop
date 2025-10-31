/*******************************************************************************
 * Copyright (c) 2010, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.SiriusFormatDataManagerForSemanticElements;
import org.eclipse.sirius.diagram.ui.tools.internal.format.AdvancedSiriusFormatDataManager;
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

/**
 * Test class for {@link SiriusFormatDataManagerForSemanticElements}.
 * 
 * @author dlecan
 */
@RunWith(value = Parameterized.class)
public class SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest extends AbstractSiriusFormatDataManagerForSemanticElementsTest {

    private final Representation representationToCopyFormat;

    private final Representation representationToPasteFormat;

    private final double[] diagramToCopyFormatZoomData;

    private final double[] diagramToPasteFormatZoomData;

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
        Collection<Object[]> data = new ArrayList<>();
        for (Representation copyRep : ALL_REPRESENTATIONS) {
            for (Representation pasteRep : ALL_REPRESENTATIONS) {
                for (double[] copyZoomData : ALL_ZOOM_DATA) {
                    for (double[] pasteZoomData : ALL_ZOOM_DATA) {
                        // Test only if one or the other zoom level is 1
                        // (IDENTITY) to reduce length of test
                        if (copyZoomData[0] == IDENTITY_ZOOM_LEVEL || pasteZoomData[0] == IDENTITY_ZOOM_LEVEL) {
                            data.add(new Object[] { copyRep, copyZoomData, pasteRep, pasteZoomData });
                        }
                    }
                }
            }
        }
        return data;
    }

    /**
     * Constructor for parameterized test.
     * 
     * * @param representationToCopyFormat a representation description
     * 
     * @param representationToPasteFormat
     *            a representation description
     * @param diagramToCopyFormatZoomData
     *            the zoom data to set for format copy in the current scenario
     * @param diagramToPasteFormatZoomData
     *            the zoom data to set for format application in the current
     *            scenario
     * @throws Exception
     */
    public SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest(Representation representationToCopyFormat, double[] diagramToCopyFormatZoomData,
            Representation representationToPasteFormat, double[] diagramToPasteFormatZoomData) throws Exception {
        this.representationToCopyFormat = representationToCopyFormat;
        this.representationToPasteFormat = representationToPasteFormat;
        this.diagramToCopyFormatZoomData = diagramToCopyFormatZoomData;
        this.diagramToPasteFormatZoomData = diagramToPasteFormatZoomData;
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedFormatDataOnRawDiagramsWithZoom() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }
        StringBuilder differences = new StringBuilder();
        for (final Diagram diagramToPasteFormat : Collections2.filter(representationToPasteFormat.diagrams, ONLY_RAW_DIAGRAM)) {
            for (final Diagram diagramToCopyFormat : openAllDiagramsInRepresentation(representationToCopyFormat, true)) {
                    Configuration configuration = ConfigurationFactory.buildConfiguration();

                    configuration.getEdgeConfiguration().setDistanceAroundPointsOfEdgeBendpointsList(Math.max(diagramToCopyFormatZoomData[1], diagramToPasteFormatZoomData[1]));

                    applyPredefinedFormatDataOnRawDiagramsWithZoom(diagramToCopyFormat, diagramToPasteFormat, diagramToCopyFormatZoomData[0], diagramToPasteFormatZoomData[0], configuration,
                            differences);
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    protected void applyPredefinedFormatDataOnRawDiagramsWithZoom(Diagram diagramToCopyFormat, Diagram diagramToPasteFormat, double diagramToCopyFormatZoomLevel, double diagramToPasteFormatZoomLevel,
            Configuration configuration, StringBuilder differences) throws Exception {

        final DiagramEditPart diagramEditPart = diagramToCopyFormat.diagramEditPart;

        // Store format data from initial diagram
        final AdvancedSiriusFormatDataManager originalManager = new SiriusFormatDataManagerForSemanticElements();
        originalManager.storeFormatData(diagramEditPart);

        try {
            openRawDiagram(diagramToPasteFormat);

            // Apply it on destination diagram, where all nodes and edges are
            // located around (0,0) (but not exactly)
            final DiagramEditPart destinationDiagram = diagramToPasteFormat.diagramEditPart;

            changeZoomLevel(diagramToCopyFormat, diagramToCopyFormatZoomLevel);
            changeZoomLevel(diagramToPasteFormat, diagramToPasteFormatZoomLevel);

            final AdvancedSiriusFormatDataManager newManager = new SiriusFormatDataManagerForSemanticElements();

            final RecordingCommand command = new RecordingCommand(destinationDiagram.getEditingDomain()) {
                @Override
                protected void doExecute() {
                    // Update diagram, but transaction will be
                    // rollbacked
                    originalManager.applyFormat(destinationDiagram);
                    newManager.storeFormatData(destinationDiagram);
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

            final String partialPath = "from___" + encodeDiagramName(diagramToCopyFormat) + "___to___" + encodeDiagramName(diagramToPasteFormat) + XMI_EXTENSION;

            // Enable this if you want to generate referenced files
            if (REGENERATE_TEST_DATA) {
                final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                saveDiagram(newManager.getRootNodeFormatData().values(), path);
            }

            String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
            String message = "between diagram ";
            message += diagramToCopyFormat.name + " (zoom level: " + diagramToCopyFormatZoomLevel + ")";
            message += " and raw diagram " + diagramToPasteFormat.name + " (zoom level: " + diagramToPasteFormatZoomLevel + ")";
            FormatDifference<?> foundDifference = loadAndCompare(diagramToPasteFormat, fullPath, newManager, configuration);
            if (foundDifference != null) {
                differences.append("\n. " + message + foundDifference);
            }

        } finally {
            closeRawDiagram(diagramToPasteFormat);
        }
    }
}
