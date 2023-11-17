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

import java.text.MessageFormat;
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
import org.eclipse.sirius.diagram.ui.provider.Messages;
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
public class MappingBasedSiriusFormatDataManagerCallCheckSequenceTest extends AbstractMappingBasedSiriusFormatDataManagerTest {

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

    @Parameters
    public static Collection<Object[]> data() {
        // We could use @Theories and @Datapoints but the theory stops as soon
        // as there is a failure. With parameters, we have feedback for all
        // scenarii.
        Collection<Object[]> data = new ArrayList<>();
        data.add(new Object[] { MB_SEQ_REPRES_BASIC_COMBINED });
        return data;
    }

    /**
     * Compute configuration for source to target EObjects mapping. Uses all the source model elements and removes one
     * of them
     * 
     * @return
     */
    protected MappingBasedTestConfiguration getFaultyTestConfiguration(EObject root) {
        Map<String, String> full_map = new HashMap<String, String>();
        String sourceRootID = MappingBasedTestConfiguration.getID(root);
        String targetRootID = MappingBasedTestConfiguration.getID(root, "target");
        full_map.put(sourceRootID, targetRootID);

        root.eAllContents().forEachRemaining(element -> {
            String sourceID = MappingBasedTestConfiguration.getID(element);
            String targetID = MappingBasedTestConfiguration.getID(element, "target");
            full_map.put(sourceID, targetID);
        });

        String toRemove = full_map.keySet().stream().filter(key -> !key.equals(sourceRootID)).findFirst().get();
        full_map.remove(toRemove);

        return new MappingBasedTestConfiguration(semanticModel, semanticTargetModel, full_map, "", "fullFaulty");
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
    public MappingBasedSiriusFormatDataManagerCallCheckSequenceTest(Representation representationToCopyFormat) throws Exception {
        super(representationToCopyFormat);
    }

    @Override
    @Before
    public void setUp() throws Exception {
        copyFilesToTestProject(SiriusTestsPlugin.PLUGIN_ID, getDataPath(), TYPES_MODEL_NAME);
        super.setUp();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testCallCheckMapCompleteness() throws Exception {
        if (TestsUtil.shouldSkipLongTests()) {
            return;
        }

        final DDiagram diagramToCopyFormat = getDiagram(MB_SEQ_REPRES_BASIC_COMBINED, MB_SEQ_BASIC_COMBINED);
        final DDiagram diagramToPasteFormat = getDiagram(MB_SEQ_REPRES_BASIC_COMBINED, MB_SEQ_BASIC_COMBINED_RAW);

        Collection<DiagramEditPart> diagramToCopyEditParts = getDiagramEditPart(session, diagramToCopyFormat);
        assertTrue(!diagramToCopyEditParts.isEmpty());
        final DiagramEditPart diagramToCopyEditPart = diagramToCopyEditParts.stream().findFirst().get();
        final EObject semanticElement = diagramToCopyEditPart.resolveSemanticElement();
        EObject rootDiagramElement = null;
        if (semanticElement instanceof DSemanticDiagram) {
            rootDiagramElement = ((DSemanticDiagram) semanticElement).getTarget();
        } else if (semanticElement instanceof DDiagramElement) {
            rootDiagramElement = ((DDiagramElement) semanticElement).getTarget();
        }
        try {
            applyPredefinedFormatDataOnRawDiagrams(diagramToCopyFormat, diagramToPasteFormat, getFaultyTestConfiguration(rootDiagramElement));
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(), MessageFormat.format(Messages.MappingBasedSiriusFormatManagerFactory_ErrorMappingfunctionIncompleteOnSequenceDiagram, diagramToCopyFormat));
        } finally {
            cleanAndDispose(diagramToCopyEditParts);
        }
    }

    private DDiagram getDiagram(Representation rep, Diagram diag) {

        List<DRepresentationDescriptor> allDDiagramDescriptors = getRepresentationDescriptors(rep.name, session).stream().collect(Collectors.toList());
        Collections.sort(allDDiagramDescriptors, USING_NAME);
        DRepresentationDescriptor dRepresentationDescriptorToFind = ViewpointFactory.eINSTANCE.createDRepresentationDescriptor();
        dRepresentationDescriptorToFind.setName(diag.name);
        final int search = Collections.binarySearch(allDDiagramDescriptors, dRepresentationDescriptorToFind, USING_NAME);

        assertTrue("Source Diagram " + dRepresentationDescriptorToFind.getName() + " is not found in representation", search > -1);

        return (DDiagram) allDDiagramDescriptors.get(search).getRepresentation();
    }

    protected void applyPredefinedFormatDataOnRawDiagrams(DDiagram diagramToCopyFormat, DDiagram diagramToPasteFormat, MappingBasedTestConfiguration mapTestConfiguration) throws Exception {

        Map<EObject, EObject> map = mapTestConfiguration.getObjectsMap();

        final RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnDiagram(session, diagramToCopyFormat, map, session, diagramToPasteFormat, false);
            }
        };
        // Execute the command to see if there is a problem
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Undo the command to let the session, more or less, in its previous state
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
    }

    protected void applyPredefinedFormatDataOnNewDiagram(DDiagram diagramToCopyFormat, MappingBasedTestConfiguration mapTestConfiguration, String diagramName, EObject targetDiagramRoot)
            throws Exception {

        Map<EObject, EObject> map = mapTestConfiguration.getObjectsMap();

        final RecordingCommand command = new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                MappingBasedSiriusFormatManagerFactory.getInstance().applyFormatOnNewDiagram(session, diagramToCopyFormat, map, session, diagramName, targetDiagramRoot, false);
            }
        };

        // Execute the command to see if there is a problem
        session.getTransactionalEditingDomain().getCommandStack().execute(command);
        // Undo the command to let the session, more or less, in its previous state
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
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
