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

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
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

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager}. Inspired from
 * {@link SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest}.
 * 
 * @author adieumegard
 */
@RunWith(value = Parameterized.class)
public class MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest extends MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest {


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
    public MappingBasedSiriusFormatDataManagerExistingTargetDiagramDiffSessionTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    protected Session targetSession;

    protected EObject targetSemanticModel;

    protected EObject targetSemanticTargetModel;

    /** Name of the file containing the session target mapped elements */
    protected static final String SESSION_TARGET_MODEL_NAME = "My2.aird";

    @Override
    @Before
    public void setUp() throws Exception {

        genericSetUp(getSemanticTargetModelPaths(), getModelerPathAsList(), getSessionTargetModelPath());

        targetSession = session;
        targetSemanticModel = semanticModel;
        targetSemanticTargetModel = getModelFromPath(getSemanticTargetModelPaths().get(0), targetSession);

        super.setUp();
    }

    protected List<String> getSemanticTargetModelPaths() {
        return Collections.singletonList(PLUGIN_PATH + getPlatformRelatedDataPath() + getSemanticTargetModelName());
    }

    protected String getSessionTargetModelPath() {
        return PLUGIN_PATH + getPlatformRelatedDataPath() + SESSION_TARGET_MODEL_NAME;
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
        MappingBasedTestConfiguration semanticTargetFullTestConfiguration = getFullTestConfiguration();
        for (final Diagram diagramToPasteFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM).collect(Collectors.toList())) {
            for (final Diagram diagramToCopyFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM.negate()).collect(Collectors.toList())) {

                Configuration configuration = ConfigurationFactory.buildConfiguration();

                applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, configuration, differences, semanticTargetFullTestConfiguration);
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedFormatDataOnRawDiagramsSubset() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }
        StringBuilder differences = new StringBuilder();
        MappingBasedTestConfiguration semanticTargetSubsetMapConfiguration = getSubsetTestConfiguration();
        for (final Diagram diagramToPasteFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM).collect(Collectors.toList())) {
            for (final Diagram diagramToCopyFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM.negate()).collect(Collectors.toList())) {

                Configuration configuration = ConfigurationFactory.buildConfiguration();

                applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, configuration, differences, semanticTargetSubsetMapConfiguration);
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses all the source model elements.
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getFullTestConfiguration() {
        Map<String, String> full_map = new HashMap<String, String>();
        full_map.put("//p1", "//targetp1");
        full_map.put("//p1/C1-1", "//targetp1/targetC1-1");
        full_map.put("//p1/C1-1/aC1-2", "//targetp1/targetC1-1/targetaC1-2");
        full_map.put("//p1/C1-1/aC1-2/@eGenericType", "//targetp1/targetC1-1/targetaC1-2/@eGenericType");
        full_map.put("//p1/C1-1/aC1-1-1", "//targetp1/targetC1-1/targetaC1-1-1");
        full_map.put("//p1/C1-1/aC1-1-1/@eGenericType", "//targetp1/targetC1-1/targetaC1-1-1/@eGenericType");
        full_map.put("//p1/C1-2", "//targetp1/targetC1-2");
        full_map.put("//p1/C1-3", "//targetp1/targetC1-3");
        full_map.put("//p1/C1-3/m1", "//targetp1/targetC1-3/targetm1");
        full_map.put("//p1/p1-1", "//targetp1/targetp1-1");
        full_map.put("//p1/p1-1/C1-1-1", "//targetp1/targetp1-1/targetC1-1-1");
        full_map.put("//p1/p1-1/C1-1-1/m1", "//targetp1/targetp1-1/targetC1-1-1/targetm1");
        full_map.put("//p1/p1-1/C1-1-2", "//targetp1/targetp1-1/targetC1-1-2");
        full_map.put("//p1/p1-2", "//targetp1/targetp1-2");
        full_map.put("//p1/p1-2/C1-2-1", "//targetp1/targetp1-2/targetC1-2-1");
        full_map.put("//p1/p1-3", "//targetp1/targetp1-3");
        full_map.put("//p2", "//targetp2");
        full_map.put("//p2/p2-1", "//targetp2/targetp2-1");
        full_map.put("//p2/p2-1/new%20EClass%201", "//targetp2/targetp2-1/targetnew%20EClass%201");
        full_map.put("//p2/p2-1/new%20EClass%201/m1", "//targetp2/targetp2-1/targetnew%20EClass%201/targetm1");
        full_map.put("//p2/p2-2", "//targetp2/targetp2-2");
        full_map.put("//p3", "//targetp3");
        full_map.put("//p4", "//targetp4");

        return new MappingBasedTestConfiguration(semanticModel, targetSemanticTargetModel, full_map, null, "full");
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses a subset of the source model elements.
     * 
     * @return
     */
    @Override
    protected MappingBasedTestConfiguration getSubsetTestConfiguration() {
        Map<String, String> subset_map = new HashMap<String, String>();
        subset_map.put("//p1", "//targetp1");
        subset_map.put("//p1/C1-1", "//targetp1/targetC1-1");
        subset_map.put("//p1/C1-1/aC1-1-1", "//targetp1/targetC1-1/targetaC1-1-1");
        subset_map.put("//p1/C1-1/aC1-1-1/@eGenericType", "//targetp1/targetC1-1/targetaC1-1-1/@eGenericType");
        subset_map.put("//p1/C1-2", "//targetp1/targetC1-2");
        subset_map.put("//p1/p1-1", "//targetp1/targetp1-1");
        subset_map.put("//p1/p1-1/C1-1-1", "//targetp1/targetp1-1/targetC1-1-1");
        subset_map.put("//p1/p1-1/C1-1-1/m1", "//targetp1/targetp1-1/targetC1-1-1/targetm1");
        subset_map.put("//p2", "//targetp2");
        subset_map.put("//p2/p2-1", "//targetp2/targetp2-1");
        subset_map.put("//p2/p2-1/new%20EClass%201", "//targetp2/targetp2-1/targetnew%20EClass%201");
        subset_map.put("//p2/p2-2", "//targetp2/targetp2-2");
        subset_map.put("//p4", "//targetp4");

        return new MappingBasedTestConfiguration(semanticModel, targetSemanticTargetModel, subset_map, null, "subset");
    }
    protected void applyPredefinedFormatDataOnRawDiagrams(Diagram diagramToCopyFormat, Diagram diagramToPasteFormat, Configuration configuration, StringBuilder differences,
            MappingBasedTestConfiguration mapTestConfiguration) throws Exception {

        List<DRepresentationDescriptor> allSourceDDiagramDescriptors = getRepresentationDescriptors(representationToCopyFormat.name, session).stream().collect(Collectors.toList());
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        dRepresentationDescriptorToFind.setName(diagramToCopyFormat.name);
        Collections.sort(allSourceDDiagramDescriptors, USING_NAME);
        final int search = Collections.binarySearch(allSourceDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Source Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        final DDiagram dDiagram = (DDiagram) allSourceDDiagramDescriptors.get(search).getRepresentation();
        Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(session, dDiagram);

        List<DRepresentationDescriptor> allTargetDDiagramDescriptors = getRepresentationDescriptors(representationToCopyFormat.name, targetSession).stream().collect(Collectors.toList());
        dRepresentationDescriptorToFind.setName(diagramToPasteFormat.name);
        final int searchTarget = Collections.binarySearch(allTargetDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Target Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", searchTarget > -1);

        final DDiagram targetdDiagram = (DDiagram) allTargetDDiagramDescriptors.get(searchTarget).getRepresentation();
        Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(targetSession, targetdDiagram);

        if (!sourceDiagramEditParts.isEmpty() && !targetDiagramEditParts.isEmpty()) {
            final DiagramEditPart sourceDiagramEditPart = sourceDiagramEditParts.stream().findFirst().get();
            final DiagramEditPart targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();

            Map<EObject, EObject> map = mapTestConfiguration.getObjectsMap();

            // Store format data from initial diagram
            final MappingBasedSiriusFormatDataManager originalManager = new MappingBasedSiriusFormatDataManager(map);
            originalManager.storeFormatData(sourceDiagramEditPart);

            try {
                final MappingBasedSiriusFormatDataManager newManager = new MappingBasedSiriusFormatDataManager(map);

                // mb_ed => mapping_based_existing_diagram
                final String diagramMappingName = "mb_ed_from___" + encodeDiagramName(diagramToCopyFormat) + "___to___" + encodeDiagramName(diagramToPasteFormat) + "__"
                        + mapTestConfiguration.getName();

                final RecordingCommand command = new RecordingCommand(targetDiagramEditPart.getEditingDomain()) {
                    @Override
                    protected void doExecute() {
                        if (MB_GENERATE_IMAGES_TEST_DATA) {
                            exportDiagramToTempFolder(diagramMappingName + "_from_raw", targetdDiagram);
                        }
                        // Update diagram, but transaction will be rollbacked
                        DDiagram newDiagram = MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnDiagram(session, (DDiagram) getDRepresentation(sourceDiagramEditPart), map,
                                targetSession,
                                (DDiagram) getDRepresentation(targetDiagramEditPart), false);

                        if (MB_GENERATE_IMAGES_TEST_DATA) {
                            exportDiagramToTempFolder(diagramMappingName + "_from", dDiagram);
                            exportDiagramToTempFolder(diagramMappingName + "_to", newDiagram);
                        }
                    }
                };

                targetDiagramEditPart.getEditingDomain().getCommandStack().execute(command);
                // Let the post commit listeners make the draw2d changes
                EclipseUIUtil.synchronizeWithUIThread();
                // Store the format data
                newManager.storeFormatData(targetDiagramEditPart);
                // undo the command to let raw diagram unchanged
                targetDiagramEditPart.getEditingDomain().getCommandStack().undo();

                final String partialPath = diagramMappingName + XMI_EXTENSION;

                // Enable this if you want to generate referenced files
                if (MB_REGENERATE_TEST_DATA) {
                    final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                    saveDiagramFiltered(path, mapTestConfiguration, newManager);
                }

                String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
                String message = "between diagram ";
                message += diagramToCopyFormat.name;
                message += " and raw diagram " + diagramToPasteFormat.name;
                FormatDifference<?> foundDifference = loadAndCompareFiltered(fullPath, newManager, configuration, mapTestConfiguration);
                if (foundDifference != null) {
                    differences.append("\n. " + message + foundDifference);
                }
                TestsUtil.synchronizationWithUIThread();

            } finally {
                cleanAndDispose(sourceDiagramEditParts);
                cleanAndDispose(targetDiagramEditParts);
                closeRawDiagram(diagramToPasteFormat);
            }
        }
    }
}
