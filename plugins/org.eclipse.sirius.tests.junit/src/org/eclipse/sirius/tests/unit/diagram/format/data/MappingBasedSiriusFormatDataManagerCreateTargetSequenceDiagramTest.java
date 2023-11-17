/*******************************************************************************
 * Copyright (c) 2020, 2021 Obeo.
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.MappingBasedSiriusFormatManagerFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
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
public class MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

    protected static final boolean MB_SEQ_REGENERATE_TEST_DATA = false;

    protected static final boolean MB_SEQ_GENERATE_IMAGES_TEST_DATA = false;

    @SuppressWarnings("hiding")
    protected static final String DATA_PATH = "data/sequence/unit/layout/mappingbased/";

    @SuppressWarnings("hiding")
    protected static final String SEMANTIC_MODEL_NAME = "fixture.interactions";

    @SuppressWarnings("hiding")
    protected static final String SEMANTIC_TARGET_MODEL_NAME = "targetfixture.interactions";

    protected static final String TYPES_MODEL_NAME = "types.ecore";

    @SuppressWarnings("hiding")
    protected static final String SESSION_MODEL_NAME = "fixture.aird";

    @SuppressWarnings("hiding")
    protected static final String MODELER_NAME = "../../variablesAccess/variablesAccess.odesign";

    public MappingBasedSiriusFormatDataManagerCreateTargetSequenceDiagramTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    @Override
    @Before
    public void setUp() throws Exception {
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

    protected static final Diagram MB_SEQ_BASIC_COMBINED = new Diagram("Basic Combined Fragment Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_COMBINED = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_COMBINED);

    protected static final Diagram MB_SEQ_BASIC_EXECUTION = new Diagram("Basic Executions Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_EXECUTION = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_EXECUTION);

    protected static final Diagram MB_SEQ_BASIC_INTERACTION_USE = new Diagram("Basic Interaction Use Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_INTERACTION_USE = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_INTERACTION_USE);

    protected static final Diagram MB_SEQ_BASIC_LOST_MESSAGE_END = new Diagram("Basic Lost Message End Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_LOST_MESSAGE_END = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_LOST_MESSAGE_END);

    protected static final Diagram MB_SEQ_BASIC_MESSAGES = new Diagram("Basic Messages Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_MESSAGES = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_MESSAGES);

    protected static final Diagram MB_SEQ_BASIC_OBSERVATION = new Diagram("Basic Observation Diagram", 0, 0);

    protected static final Representation MB_SEQ_REPRES_BASIC_OBSERVATION = new Representation("Sequence Diagram on Interaction", MB_SEQ_BASIC_OBSERVATION);

    protected static final Diagram MB_SEQ_BASIC_OBSERVATION_NOTES = new Diagram("Basic Observation Diagram with notes", 0, 0);

    protected static final RepresentationWithNotes MB_SEQ_REPRES_BASIC_OBSERVATION_NOTES = new RepresentationWithNotes("Sequence Diagram on Interaction", MB_SEQ_BASIC_OBSERVATION_NOTES);

    protected static final Diagram MB_SEQ_COMPLEX = new Diagram("Complex", 0, 0);

    protected static final Representation MB_SEQ_REPRES_COMPLEX = new Representation("Sequence Diagram on Interaction", MB_SEQ_COMPLEX);

    protected static final Diagram MB_SEQ_COMPLEX_WITH_CF = new Diagram("Complex with CF", 0, 0);

    protected static final Representation MB_SEQ_REPRES_COMPLEX_WITH_CF = new Representation("Sequence Diagram on Interaction", MB_SEQ_COMPLEX_WITH_CF);

    protected static final Representation[] MB_SEQ_ALL_REPRESENTATIONS = { MB_SEQ_REPRES_BASIC_COMBINED, MB_SEQ_REPRES_BASIC_EXECUTION, MB_SEQ_REPRES_BASIC_INTERACTION_USE,
            MB_SEQ_REPRES_BASIC_LOST_MESSAGE_END, MB_SEQ_REPRES_BASIC_MESSAGES, MB_SEQ_REPRES_BASIC_OBSERVATION, MB_SEQ_REPRES_COMPLEX, MB_SEQ_REPRES_COMPLEX_WITH_CF,
            MB_SEQ_REPRES_BASIC_OBSERVATION_NOTES };

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
     * Test method, generate new diagram with mapping = all elements
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedFormatDataOnNewDiagramFull() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        StringBuilder differences = new StringBuilder();

        List<DRepresentationDescriptor> allDDiagramDescriptors = getRepresentationDescriptors(representationToCopyFormat.name, session).stream().collect(Collectors.toList());
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        dRepresentationDescriptorToFind.setName(representationToCopyFormat.diagrams.get(0).name);
        Collections.sort(allDDiagramDescriptors, USING_NAME);
        final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        final DDiagram dDiagram = (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();
        Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(session, dDiagram);

        if (!sourceDiagramEditParts.isEmpty()) {
            DiagramEditPart sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();
            final EObject semanticElement = sourceDiagramEditPart.resolveSemanticElement();
            EObject rootDiagramElement = null;
            if (semanticElement instanceof DSemanticDiagram) {
                rootDiagramElement = ((DSemanticDiagram) semanticElement).getTarget();
            } else if (semanticElement instanceof DDiagramElement) {
                rootDiagramElement = ((DDiagramElement) semanticElement).getTarget();
            }
            MappingBasedTestConfiguration semanticTargetFullTestConfiguration = getFullTestConfiguration(rootDiagramElement);

            Configuration configuration = ConfigurationFactory.buildConfiguration();

            if (representationToCopyFormat instanceof RepresentationWithNotes) {
                doTestOnNewDiagram(differences, semanticTargetFullTestConfiguration, configuration, sourceDiagramEditPart, dDiagram, true);
            } else {
                doTestOnNewDiagram(differences, semanticTargetFullTestConfiguration, configuration, sourceDiagramEditPart, dDiagram, false);
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    private void doTestOnNewDiagram(StringBuilder differences, MappingBasedTestConfiguration explicitMappingTestConfiguration, Configuration configuration, DiagramEditPart sourceDiagramEditPart,
            DDiagram dDiagram, boolean includeNotes) {

        final MappingBasedSiriusFormatDataManager originalManager = new MappingBasedSiriusFormatDataManager(explicitMappingTestConfiguration.getObjectsMap());
        originalManager.storeFormatData(sourceDiagramEditPart);

        final MappingBasedSiriusFormatDataManager newManager = new MappingBasedSiriusFormatDataManager(explicitMappingTestConfiguration.getObjectsMap());
        final String newDiagramName = dDiagram.getName() + " " + explicitMappingTestConfiguration.getName() + " New" + (includeNotes ? " notes" : "");

        if (MB_SEQ_GENERATE_IMAGES_TEST_DATA) {
            exportDiagramToTempFolder(newDiagramName + "_from", dDiagram);
        }
        Collection<DiagramEditPart> targetDiagramEditParts = null;
        try {
            final RecordingCommand command = new RecordingCommand(sourceDiagramEditPart.getEditingDomain()) {
                DDiagram newDiagram;

                @Override
                protected void doExecute() {
                    newDiagram = MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnNewDiagram(session, dDiagram, explicitMappingTestConfiguration.getObjectsMap(), getTargetSession(),
                            newDiagramName, explicitMappingTestConfiguration.getTargetRoot(), includeNotes);
                }

                @Override
                public Collection<?> getResult() {
                    return Collections.singleton(newDiagram);
                }
            };

            getTargetSession().getTransactionalEditingDomain().getCommandStack().execute(command);
            // Let the post commit listeners make the draw2d changes
            EclipseUIUtil.synchronizeWithUIThread();
            DDiagram newDiagram = (DDiagram) command.getResult().stream().findFirst().get();
            targetDiagramEditParts = getDiagramEditPart(getTargetSession(), newDiagram);
            assertTrue(!targetDiagramEditParts.isEmpty());
            // Store the format data
            DiagramEditPart targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();
            newManager.storeFormatData(targetDiagramEditPart);
            if (MB_SEQ_GENERATE_IMAGES_TEST_DATA) {
                exportDiagramToTempFolder(newDiagramName + "_to", newDiagram);
            }
            // undo the command to let the session "unchanged"
            sourceDiagramEditPart.getEditingDomain().getCommandStack().undo();

            final String diagramToCopyFormatName = representationToCopyFormat.diagrams.get(0).name;
            final String partialPath = "from___" + encodeDiagramName(diagramToCopyFormatName) + "___to___" + encodeDiagramName(newDiagramName) + XMI_EXTENSION;

            try {
                // Enable this if you want to generate referenced files
                if (MB_SEQ_REGENERATE_TEST_DATA) {
                    final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                    saveDiagramFiltered(path, explicitMappingTestConfiguration, newManager);
                }

                String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
                String message = "between diagram ";
                message += diagramToCopyFormatName + " and diagram " + newDiagramName;
                FormatDifference<?> foundDifference = loadAndCompareFiltered(fullPath, newManager, configuration, explicitMappingTestConfiguration);
                if (foundDifference != null) {
                    differences.append("\n. " + message + foundDifference);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }
        } finally {
            cleanAndDispose(targetDiagramEditParts);
        }

    }

    protected Session getTargetSession() {
        return session;
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
        return getDataPath();
    }
}
