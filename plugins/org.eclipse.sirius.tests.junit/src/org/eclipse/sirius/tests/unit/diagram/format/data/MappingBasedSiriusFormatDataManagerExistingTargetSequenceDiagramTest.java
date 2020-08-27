/*******************************************************************************
 * Copyright (c) 2020 Obeo.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.MappingBasedSiriusFormatManagerFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased.MappingBasedTestConfiguration;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager}. Inspired from
 * {@link SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest}.
 * 
 * @author adieumegard
 */
@RunWith(value = Parameterized.class)
public class MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

    protected static final boolean MB_SEQ_REGENERATE_TEST_DATA = false;

    protected static final boolean MB_SEQ_GENERATE_IMAGES_TEST_DATA = false;

    @SuppressWarnings("hiding")
    protected static final String DATA_PATH = "data/sequence/unit/layout/mappingbased/";

    @SuppressWarnings("hiding")
    protected static final String SEMANTIC_MODEL_NAME = "fixture.interactions";

    @SuppressWarnings("hiding")
    protected static final String SEMANTIC_TARGET_MODEL_NAME = "targetfixture.interactions";

    @SuppressWarnings("hiding")
    protected static final String SESSION_MODEL_NAME = "fixture.aird";

    protected static final String TYPES_MODEL_NAME = "types.ecore";

    @SuppressWarnings("hiding")
    protected static final String MODELER_NAME = "../../variablesAccess/variablesAccess.odesign";

    @Override
    @Before
    public void setUp() throws Exception {

        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, getDataPath(), TYPES_MODEL_NAME);
        super.setUp();
    }

    @Override
    protected String getSessionModelName() {
        return SESSION_MODEL_NAME;
    }

    @Override
    protected String getSemanticModelName() {
        return SEMANTIC_MODEL_NAME;
    }

    @Override
    protected String getSemanticTargetModelName() {
        return SEMANTIC_TARGET_MODEL_NAME;
    }

    @Override
    protected String getModelerName() {
        return MODELER_NAME;
    }

    @Override
    protected String getDataPath() {
        return DATA_PATH;
    }

    protected static final Predicate<Diagram> ONLY_RAW_DIAGRAM = new Predicate<Diagram>() {

        @Override
        public boolean test(final Diagram input) {
            return input.raw;
        }
    };

    protected static final Diagram MB_SEQ_BASIC_COMBINED = new Diagram("Basic Combined Fragment Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_COMBINED_RAW = new Diagram("Basic Combined Fragment Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_COMBINED = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_COMBINED, MB_SEQ_BASIC_COMBINED_RAW);

    protected static final Diagram MB_SEQ_BASIC_EXECUTION = new Diagram("Basic Executions Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_EXECUTION_RAW = new Diagram("Basic Executions Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_EXECUTION = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_EXECUTION, MB_SEQ_BASIC_EXECUTION_RAW);

    protected static final Diagram MB_SEQ_BASIC_INTERACTION_USE = new Diagram("Basic Interaction Use Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_INTERACTION_USE_RAW = new Diagram("Basic Interaction Use Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_INTERACTION_USE = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_INTERACTION_USE, MB_SEQ_BASIC_INTERACTION_USE_RAW);

    protected static final Diagram MB_SEQ_BASIC_LOST_MESSAGE_END = new Diagram("Basic Lost Message End Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_LOST_MESSAGE_END_RAW = new Diagram("Basic Lost Message End Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_LOST_MESSAGE_END = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_LOST_MESSAGE_END,
            MB_SEQ_BASIC_LOST_MESSAGE_END_RAW);

    protected static final Diagram MB_SEQ_BASIC_MESSAGES = new Diagram("Basic Messages Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_MESSAGES_RAW = new Diagram("Basic Messages Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_MESSAGES = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_MESSAGES, MB_SEQ_BASIC_MESSAGES_RAW);

    protected static final Diagram MB_SEQ_BASIC_OBSERVATION = new Diagram("Basic Observation Diagram", 0, 0);

    protected static final Diagram MB_SEQ_BASIC_OBSERVATION_RAW = new Diagram("Basic Observation Diagram Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_BASIC_OBSERVATION = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_OBSERVATION, MB_SEQ_BASIC_OBSERVATION_RAW);

    protected static final Diagram MB_SEQ_COMPLEX = new Diagram("Complex", 0, 0);

    protected static final Diagram MB_SEQ_COMPLEX_RAW = new Diagram("Complex Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_COMPLEX = new Representation("Sequence Diagram on Interaction", MB_SEQ_COMPLEX, MB_SEQ_COMPLEX_RAW);

    protected static final Diagram MB_SEQ_COMPLEX_WITH_CF = new Diagram("Complex with CF", 0, 0);

    protected static final Diagram MB_SEQ_COMPLEX_WITH_CF_RAW = new Diagram("Complex with CF Raw", 0, 0, true);

    protected static final Representation MB_SEQ_REPRES_COMPLEX_WITH_CF = new Representation("Sequence Diagram on Interaction", MB_SEQ_COMPLEX_WITH_CF, MB_SEQ_COMPLEX_WITH_CF_RAW);

    protected static final Representation[] MB_SEQ_ALL_REPRESENTATIONS = { MB_SEQ_REPRES_BASIC_COMBINED, MB_SEQ_REPRES_BASIC_EXECUTION, MB_SEQ_REPRES_BASIC_INTERACTION_USE,
            MB_SEQ_REPRES_BASIC_LOST_MESSAGE_END, MB_SEQ_REPRES_BASIC_MESSAGES, MB_SEQ_REPRES_BASIC_OBSERVATION, MB_SEQ_REPRES_COMPLEX, MB_SEQ_REPRES_COMPLEX_WITH_CF };

    @Parameters
    public static Collection<Object[]> data() {
        // We could use @Theories and @Datapoints but the theory stops as soon
        // as there is a failure. With parameters, we have feedback for all
        // scenarii.
        Collection<Object[]> data = new ArrayList<>();
        for (Representation copyRep : MB_SEQ_ALL_REPRESENTATIONS) {
            data.add(new Object[] { copyRep });
        }
        return data;
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses all the source model elements.
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getFullTestConfiguration(EObject root) {
        Map<String, String> full_map = new HashMap<String, String>();
        String sourceRootID = MappingBasedTestConfiguration.getID(root);
        String targetRootID = MappingBasedTestConfiguration.getID(root, "target");
        full_map.put(sourceRootID, targetRootID);

        root.eAllContents().forEachRemaining(element -> {
            String sourceID = MappingBasedTestConfiguration.getID(element);
            String targetID = MappingBasedTestConfiguration.getID(element, "target");
            full_map.put(sourceID, targetID);
        });

        return new MappingBasedTestConfiguration(semanticModel, semanticTargetModel, full_map, targetRootID, "full");
    }

    /**
     * Constructor for parameterized test.
     * 
     * @param representationToCopyFormat
     *            a representation description
     * @param representationToPasteFormat
     *            a representation description
     * @param diagramToCopyFormatZoomData
     *            the zoom data to set for format copy in the current scenario
     * @param diagramToPasteFormatZoomData
     *            the zoom data to set for format application in the current scenario
     * @throws Exception
     */
    public MappingBasedSiriusFormatDataManagerExistingTargetSequenceDiagramTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedFormatDataOnRawDiagramsFull() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }
        StringBuilder differences = new StringBuilder();
        for (final Diagram diagramToPasteFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM).collect(Collectors.toList())) {
            for (final Diagram diagramToCopyFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM.negate()).collect(Collectors.toList())) {

                Configuration configuration = ConfigurationFactory.buildConfiguration();

                applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, configuration, differences);
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    protected void applyPredefinedFormatDataOnRawDiagrams(Diagram diagramToCopyFormat, Diagram diagramToPasteFormat, Configuration configuration, StringBuilder differences) throws Exception {

        List<DRepresentationDescriptor> allDDiagramDescriptors = getRepresentationDescriptors(representationToCopyFormat.name, session).stream().collect(Collectors.toList());
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();

        // Search for diagram to copy format from
        dRepresentationDescriptorToFind.setName(diagramToCopyFormat.name);
        Collections.sort(allDDiagramDescriptors, USING_NAME);

        final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Source Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        final DDiagram dDiagram = (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();
        Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(session, dDiagram);

        // Search for diagram to copy format to
        dRepresentationDescriptorToFind.setName(diagramToPasteFormat.name);
        final int searchTarget = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Target Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", searchTarget > -1);

        final DDiagram targetdDiagram = (DDiagram) allDDiagramDescriptors.get(searchTarget).getRepresentation();
        Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(session, targetdDiagram);

        if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
            final DiagramEditPart sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();
            final DiagramEditPart targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();

            final EObject semanticElement = sourceDiagramEditPart.resolveSemanticElement();
            EObject rootDiagramElement = null;
            if (semanticElement instanceof DSemanticDiagram) {
                rootDiagramElement = ((DSemanticDiagram) semanticElement).getTarget();
            } else if (semanticElement instanceof DDiagramElement) {
                rootDiagramElement = ((DDiagramElement) semanticElement).getTarget();
            }
            MappingBasedTestConfiguration semanticTargetFullTestConfiguration = getFullTestConfiguration(rootDiagramElement);

            Map<EObject, EObject> map = semanticTargetFullTestConfiguration.getObjectsMap();

            // Store format data from initial diagram
            final MappingBasedSiriusFormatDataManager originalManager = new MappingBasedSiriusFormatDataManager(map);
            originalManager.storeFormatData(sourceDiagramEditPart);

            try {
                final MappingBasedSiriusFormatDataManager newManager = new MappingBasedSiriusFormatDataManager(map);

                // mb_seq_ed => mapping_based_sequence_existing_diagram
                final String diagramMappingName = "mb_seq_ed_from___" + encodeDiagramName(diagramToCopyFormat) + "___to___" + encodeDiagramName(diagramToPasteFormat) + "__"
                        + semanticTargetFullTestConfiguration.getName();

                final RecordingCommand command = new RecordingCommand(targetDiagramEditPart.getEditingDomain()) {
                    @Override
                    protected void doExecute() {

                        if (MB_SEQ_GENERATE_IMAGES_TEST_DATA) {
                            exportDiagramToTempFolder(diagramMappingName + "_from_raw", targetdDiagram);
                        }

                        // Update diagram, but transaction will be rollbacked
                        DDiagram newDiagram = MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnDiagram(session, (DDiagram) getDRepresentation(sourceDiagramEditPart), map, session,
                                (DDiagram) getDRepresentation(targetDiagramEditPart), false);

                        if (MB_SEQ_GENERATE_IMAGES_TEST_DATA) {
                            exportDiagramToTempFolder(diagramMappingName + "_from", dDiagram);
                            exportDiagramToTempFolder(diagramMappingName + "_to", newDiagram);
                        }

                        newManager.storeFormatData(targetDiagramEditPart);

                    }
                };

                try {
                    // Force rollback of transaction to let raw diagram
                    // unchanged
                    targetDiagramEditPart.getEditingDomain().addResourceSetListener(ROLLBACK_LISTENER);
                    targetDiagramEditPart.getEditingDomain().getCommandStack().execute(command);
                } finally {
                    targetDiagramEditPart.getEditingDomain().removeResourceSetListener(ROLLBACK_LISTENER);
                }

                final String partialPath = diagramMappingName + XMI_EXTENSION;

                // Enable this if you want to generate referenced files
                if (MB_SEQ_REGENERATE_TEST_DATA) {
                    final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                    saveDiagram(newManager.getRootNodeFormatData().values(), path);
                }

                String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
                String message = "between diagram ";
                message += diagramToCopyFormat.name;
                message += " and raw diagram " + diagramToPasteFormat.name;
                FormatDifference<?> foundDifference = loadAndCompare(diagramToPasteFormat, fullPath, newManager, configuration);
                if (foundDifference != null) {
                    differences.append("\n. " + message + foundDifference);
                }
                TestsUtil.synchronizationWithUIThread();

            } finally {
                closeRawDiagram(diagramToPasteFormat);
            }
        }
    }

    @Override
    protected String getPlatformRelatedFullXmiDataPath() {
        return PLUGIN_PATH + getPlatformRelatedXmiDataPath();
    }

    @Override
    protected String getPlatformRelatedXmiDataPath() {
        return getPlatformRelatedDataPath() + XMI_FOLDER;
    }

    @Override
    protected String getPlatformRelatedDataPath() {
        String path = getDataPath();
        String platformVersion = Platform.getBundle("org.eclipse.core.runtime").getHeaders().get("Bundle-Version");
        if (platformVersion.startsWith("3.3") || platformVersion.startsWith("3.4") || platformVersion.startsWith("3.5")) {
            path = getDataPath() + "3.5/";
        } else if (platformVersion.startsWith("3.6")) {
            path = getDataPath() + "3.6/";
        }
        return path;
    }
}
