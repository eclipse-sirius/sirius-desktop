/*******************************************************************************
 * Copyright (c) 2020, 2023 Obeo.
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
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Shape;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.FormatHelper.FormatDifference;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.Configuration;
import org.eclipse.sirius.diagram.formatdata.tools.api.util.configuration.ConfigurationFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.MappingBasedSiriusFormatManagerFactory;
import org.eclipse.sirius.diagram.ui.tools.api.format.semantic.MappingBasedSiriusFormatDataManager;
import org.eclipse.sirius.diagram.ui.tools.api.util.GMFNotationHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.format.data.manager.mappingbased.MappingBasedTestConfiguration;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
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
public class MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

    protected static final boolean MB_REGENERATE_TEST_DATA = false;

    protected static final boolean MB_GENERATE_IMAGES_TEST_DATA = false;

    protected static final Predicate<Diagram> ONLY_RAW_DIAGRAM = new Predicate<Diagram>() {

        @Override
        public boolean test(final Diagram input) {
            return input.raw;
        }
    };

    protected static final Diagram MB_DIAG_TYPE8_MYPACKAGE = new Diagram("DiagType8 of MyPackage", 16, 0);

    protected static final Diagram MB_DIAG_TYPE8_P2 = new Diagram("DiagType8 of p2", 3, 0);

    protected static final Diagram MB_DIAG_TYPE8_RAW = new Diagram("DiagType8 Raw of targetMyPackage", 16, 0, true);

    protected static final Representation MB_REPRES_TYPE8 = new Representation("DiagType8", MB_DIAG_TYPE8_MYPACKAGE, MB_DIAG_TYPE8_P2, MB_DIAG_TYPE8_RAW);

    protected static final Diagram MB_DIAG_TYPE8UNSYNC_MYPACKAGE = new Diagram("DiagType8_unsync of MyPackage", 16, 0);

    protected static final Diagram MB_DIAG_TYPE8UNSYNC_RAW = new Diagram("DiagType8_unsync Raw of targetMyPackage", 16, 0, true);

    protected static final Representation MB_REPRES_TYPE8UNSYNC = new Representation("DiagType8_unsync", MB_DIAG_TYPE8UNSYNC_MYPACKAGE, MB_DIAG_TYPE8UNSYNC_RAW);

    protected static final Diagram MB_DIAG_TYPE8UNSYNCBN_MYPACKAGE = new Diagram("DiagType8_unsyncBN of MyPackage", 12, 0);

    protected static final Diagram MB_DIAG_TYPE8UNSYNCBN_RAW = new Diagram("DiagType8_unsyncBN Raw of targetMyPackage", 11, 0, true);

    protected static final Representation MB_REPRES_TYPE8UNSYNCBN = new Representation("DiagType8_unsync_onlyBN", MB_DIAG_TYPE8UNSYNCBN_MYPACKAGE, MB_DIAG_TYPE8UNSYNCBN_RAW);

    protected static final Diagram MB_DIAG_TYPE10_MYPACKAGE = new Diagram("DiagType10 of MyPackage", 16, 2);

    protected static final Diagram MB_DIAG_TYPE10_RAW = new Diagram("DiagType10 Raw of targetMyPackage", 16, 2, true);

    protected static final Representation MB_REPRES_TYPE10 = new Representation("DiagType10", MB_DIAG_TYPE10_MYPACKAGE, MB_DIAG_TYPE10_RAW);

    protected static final Diagram MB_DIAG_TYPE2_NOTES_MYPACKAGE = new Diagram("DiagType2 with notes of MyPackage", 16, 2);

    protected static final Diagram MB_DIAG_TYPE2_RAW = new Diagram("DiagType2 Raw of targetMyPackage", 16, 2, true);

    protected static final RepresentationWithNotes MB_REPRES_NOTES_TYPE2 = new RepresentationWithNotes("DiagType2", MB_DIAG_TYPE2_NOTES_MYPACKAGE, MB_DIAG_TYPE2_RAW);

    protected static final Representation[] MB_ALL_REPRESENTATIONS = { MB_REPRES_TYPE8, MB_REPRES_TYPE8UNSYNC, MB_REPRES_TYPE8UNSYNCBN, MB_REPRES_TYPE10, MB_REPRES_NOTES_TYPE2 };

    @Parameters
    public static Collection<Object[]> data() {
        // We could use @Theories and @Datapoints but the theory stops as soon
        // as there is a failure. With parameters, we have feedback for all
        // scenarii.
        Collection<Object[]> data = new ArrayList<>();
        for (Representation copyRep : MB_ALL_REPRESENTATIONS) {
            data.add(new Object[] { copyRep });
        }
        return data;
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
    public MappingBasedSiriusFormatDataManagerExistingTargetDiagramTest(Representation representationToCopyFormat) throws Exception {
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
        MappingBasedTestConfiguration semanticTargetFullTestConfiguration = getFullTestConfiguration();
        for (final Diagram diagramToPasteFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM).collect(Collectors.toList())) {
            for (final Diagram diagramToCopyFormat : representationToCopyFormat.diagrams.stream().filter(ONLY_RAW_DIAGRAM.negate()).collect(Collectors.toList())) {

                Configuration configuration = ConfigurationFactory.buildConfiguration();

                differences.append(applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, configuration, semanticTargetFullTestConfiguration));
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

                differences.append(applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, configuration, semanticTargetSubsetMapConfiguration));
            }
        }

        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    protected StringBuilder applyPredefinedFormatDataOnRawDiagrams(Diagram diagramToCopyFormat, Diagram diagramToPasteFormat, Configuration configuration,
            MappingBasedTestConfiguration mapTestConfiguration) throws Exception {
        StringBuilder differences = new StringBuilder();
        List<DRepresentationDescriptor> allDDiagramDescriptors = getRepresentationDescriptors(representationToCopyFormat.name, session).stream().collect(Collectors.toList());
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        dRepresentationDescriptorToFind.setName(diagramToCopyFormat.name);
        Collections.sort(allDDiagramDescriptors, USING_NAME);
        final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Source Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        final DDiagram dDiagram = (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();
        Collection<DiagramEditPart> sourceDiagramEditParts = getDiagramEditPart(session, dDiagram);

        dRepresentationDescriptorToFind.setName(diagramToPasteFormat.name);
        final int searchTarget = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Target Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", searchTarget > -1);

        final DDiagram targetdDiagram = (DDiagram) allDDiagramDescriptors.get(searchTarget).getRepresentation();
        Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(session, targetdDiagram);

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
                        DDiagram newDiagram = MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnDiagram(session, (DDiagram) getDRepresentation(sourceDiagramEditPart), map, session,
                                (DDiagram) getDRepresentation(targetDiagramEditPart), true);
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

                if (MB_DIAG_TYPE2_NOTES_MYPACKAGE.name.equals(diagramToCopyFormat.name)) {
                    // "Hard coded" checks for Notes, Texts and Representation links, that are not handled by
                    // MappingBasedSiriusFormatDataManager so associated location and style are not stored in file to
                    // compare with.
                    differences.append(checkPureGraphicalElements(sourceDiagramEditPart, targetDiagramEditPart, map));
                    // Additional checks (not really mandatory)
                    Collection<Shape> sourceNotes = GMFNotationHelper.getNotes(sourceDiagramEditPart.getDiagramView());
                    Collection<Shape> targetNotes = GMFNotationHelper.getNotes(targetDiagramEditPart.getDiagramView());
                    // There are 2 additional notes in target diagram (Two notes named "Nice" without note attachment)
                    assertEquals("The number of Notes is wrong.", sourceNotes.size() + 2, targetNotes.size());
                    Collection<Shape> sourceTexts = GMFNotationHelper.getTextNotes(sourceDiagramEditPart.getDiagramView());
                    Collection<Shape> targetTexts = GMFNotationHelper.getTextNotes(targetDiagramEditPart.getDiagramView());
                    // 4 Texts in source and target diagrams
                    assertEquals("The number of Texts in source diagram is wrong.", 4, sourceTexts.size());
                    assertEquals("The number of Texts is wrong.", sourceTexts.size(), targetTexts.size());
                }
                // undo the command to let raw diagram unchanged
                targetDiagramEditPart.getEditingDomain().getCommandStack().undo();

                final String partialPath = diagramMappingName + XMI_EXTENSION;

                // Enable this if you want to generate referenced files
                if (MB_REGENERATE_TEST_DATA) {
                    final String path = getPlatformRelatedXmiDataPath() + RAW_FOLDER + partialPath;
                    saveDiagramFiltered(path, mapTestConfiguration, newManager);
                }

                String fullPath = getPlatformRelatedFullXmiDataPath() + RAW_FOLDER + partialPath;
                FormatDifference<?> foundDifference = loadAndCompareFiltered(fullPath, newManager, configuration, mapTestConfiguration);
                if (foundDifference != null) {
                    differences.append("\n     ." + foundDifference);
                }
                TestsUtil.synchronizationWithUIThread();

            } finally {
                cleanAndDispose(sourceDiagramEditParts);
                cleanAndDispose(targetDiagramEditParts);
                closeRawDiagram(diagramToPasteFormat);
            }
        }
        if (!differences.isEmpty()) {
            differences.insert(0, String.format("\n. between diagram %s and raw diagram %s", diagramToCopyFormat.name, diagramToPasteFormat.name)); //$NON-NLS-1$
        }
        return differences;
    }
}
