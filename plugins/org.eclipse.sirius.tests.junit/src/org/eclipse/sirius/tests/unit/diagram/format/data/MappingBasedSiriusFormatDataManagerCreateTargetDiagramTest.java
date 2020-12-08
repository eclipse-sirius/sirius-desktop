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

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
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
import org.junit.runners.Parameterized.Parameters;

/**
 * Test class for {@link MappingBasedSiriusFormatDataManager}. Inspired from
 * {@link SiriusFormatDataManagerForSemanticElementsApplyWithPredefinedDataTest}.
 * 
 * @author adieumegard
 */
@RunWith(value = Parameterized.class)
public class MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

    protected static final boolean MB_REGENERATE_TEST_DATA = false;

    protected static final boolean MB_GENERATE_IMAGES_TEST_DATA = false;

    protected static final Diagram MB_DIAG_TYPE2_MYPACKAGE = new Diagram("DiagType2 of MyPackage", 16, 2);

    protected static final Representation MB_REPRES_TYPE2 = new Representation("DiagType2", MB_DIAG_TYPE2_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE2_FILTERS_MYPACKAGE = new Diagram("DiagType2 filters and layers of MyPackage", 16, 2);

    protected static final Representation MB_REPRES_TYPE2_FILTERS = new Representation("DiagType2_filters_layers", MB_DIAG_TYPE2_FILTERS_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE2_NOTES_MYPACKAGE = new Diagram("DiagType2 with notes of MyPackage", 16, 2);

    protected static final RepresentationWithNotes MB_REPRES_NOTES_TYPE2 = new RepresentationWithNotes("DiagType2", MB_DIAG_TYPE2_NOTES_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE2UNSYNC_MYPACKAGE = new Diagram("DiagType2_unsync of MyPackage", 16, 2);

    protected static final Representation MB_REPRES_TYPE2UNSYNC = new Representation("DiagType2_unsync", MB_DIAG_TYPE2UNSYNC_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE2UNSYNC_EDGE_MYPACKAGE = new Diagram("DiagType2_unsync_edge of MyPackage", 16, 2);

    protected static final Representation MB_REPRES_TYPE2UNSYNC_EDGE = new Representation("DiagType2_unsync_edge", MB_DIAG_TYPE2UNSYNC_EDGE_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE8_MYPACKAGE = new Diagram("DiagType8 of MyPackage", 16, 0);

    protected static final Diagram MB_DIAG_TYPE8_P2 = new Diagram("DiagType8 of p2", 3, 0);

    protected static final Representation MB_REPRES_TYPE8 = new Representation("DiagType8", MB_DIAG_TYPE8_MYPACKAGE, MB_DIAG_TYPE8_P2);

    protected static final Diagram MB_DIAG_TYPE8UNSYNC_MYPACKAGE = new Diagram("DiagType8_unsync of MyPackage", 16, 0);

    protected static final Representation MB_REPRES_TYPE8UNSYNC = new Representation("DiagType8_unsync", MB_DIAG_TYPE8UNSYNC_MYPACKAGE);

    protected static final Diagram MB_DIAG_TYPE11_MYPACKAGE = new Diagram("DiagType11 of MyPackage", 16, 2);

    protected static final Representation MB_REPRES_TYPE11 = new Representation("DiagType11", MB_DIAG_TYPE11_MYPACKAGE);

    protected static final Representation[] MB_ALL_REPRESENTATIONS = {
            MB_REPRES_TYPE2, MB_REPRES_TYPE2_FILTERS, MB_REPRES_TYPE2UNSYNC, MB_REPRES_TYPE2UNSYNC_EDGE, MB_REPRES_TYPE8, MB_REPRES_TYPE8UNSYNC, MB_REPRES_NOTES_TYPE2,
            MB_REPRES_TYPE11 };

    public MappingBasedSiriusFormatDataManagerCreateTargetDiagramTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

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
        MappingBasedTestConfiguration semanticTargetFullTestConfiguration = getFullTestConfiguration();
        Configuration configuration = ConfigurationFactory.buildConfiguration();

        if (representationToCopyFormat instanceof RepresentationWithNotes) {
            doTestOnNewDiagram(differences, semanticTargetFullTestConfiguration, configuration, true);
        } else {
            doTestOnNewDiagram(differences, semanticTargetFullTestConfiguration, configuration, false);
        }
        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    /**
     * Test method, generate new diagram with mapping = subset of elements
     *
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testApplyPredefinedFormatDataOnNewDiagramSubset() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        StringBuilder differences = new StringBuilder();
        MappingBasedTestConfiguration semanticTargetSubsetMapConfiguration = getSubsetTestConfiguration();
        Configuration configuration = ConfigurationFactory.buildConfiguration();

        if (representationToCopyFormat instanceof RepresentationWithNotes) {
            doTestOnNewDiagram(differences, semanticTargetSubsetMapConfiguration, configuration, true);
        } else {
            doTestOnNewDiagram(differences, semanticTargetSubsetMapConfiguration, configuration, false);
        }
        assertTrue("Found differences : \n" + differences, differences.length() == 0);
    }

    private void doTestOnNewDiagram(StringBuilder differences, MappingBasedTestConfiguration explicitMappingTestConfiguration, Configuration configuration, boolean includeNotes) {

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

            final MappingBasedSiriusFormatDataManager originalManager = new MappingBasedSiriusFormatDataManager(explicitMappingTestConfiguration.getObjectsMap());
            originalManager.storeFormatData(sourceDiagramEditPart);

            final MappingBasedSiriusFormatDataManager newManager = new MappingBasedSiriusFormatDataManager(explicitMappingTestConfiguration.getObjectsMap());
            final String newDiagramName = dDiagram.getName() + " " + explicitMappingTestConfiguration.getName() + " New" + (includeNotes ? " notes" : "");

            if (MB_GENERATE_IMAGES_TEST_DATA) {
                exportDiagramToTempFolder(newDiagramName + "_from", dDiagram);
            }
            final RecordingCommand command = new RecordingCommand(sourceDiagramEditPart.getEditingDomain()) {
                private DDiagram newDiagram;

                @Override
                protected void doExecute() {
                    newDiagram = MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnNewDiagram(session, dDiagram, explicitMappingTestConfiguration.getObjectsMap(), session,
                            newDiagramName, explicitMappingTestConfiguration.getTargetRoot(), includeNotes);
                }

                @Override
                public Collection<?> getResult() {
                    return Collections.singleton(newDiagram);
                }
            };

            try {
                sourceDiagramEditPart.getEditingDomain().getCommandStack().execute(command);
                DDiagram newDiagram = (DDiagram) command.getResult().stream().findFirst().get();
                Collection<DiagramEditPart> targetDiagramEditParts = getDiagramEditPart(session, newDiagram);
                assertTrue(!targetDiagramEditParts.isEmpty());

                DiagramEditPart targetDiagramEditPart = targetDiagramEditParts.stream().findFirst().get();
                newManager.storeFormatData(targetDiagramEditPart);

                if (MB_GENERATE_IMAGES_TEST_DATA) {
                    exportDiagramToTempFolder(newDiagramName + "_to", newDiagram);
                }
            } finally {
                sourceDiagramEditPart.getEditingDomain().getCommandStack().undo();
            }

            final String diagramToCopyFormatName = representationToCopyFormat.diagrams.get(0).name;
            final String partialPath = "from___" + encodeDiagramName(diagramToCopyFormatName) + "___to___" + encodeDiagramName(newDiagramName) + XMI_EXTENSION;

            try {
                // Enable this if you want to generate referenced files
                if (MB_REGENERATE_TEST_DATA) {
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
                fail(e.getMessage());
            } finally {

            }
        }
    }
}
