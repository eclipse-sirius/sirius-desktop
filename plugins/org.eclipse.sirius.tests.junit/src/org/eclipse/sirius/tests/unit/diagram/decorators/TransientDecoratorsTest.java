/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.decorators;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.LayerHelper;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.OpenedSessionsCondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test the transient layer and its decorators functionality.
 * 
 * @author smonnier
 */
public class TransientDecoratorsTest extends GenericTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/transientDecorators/transient_decorator.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/transientDecorators/decorator.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Transient_Decorator";

    private static final String VIEWPOINT_NAME = "Transient_Decorator";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_LABEL = "Transient layer with mapping based decorator";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_NAME = "MBD_TransientLayer";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_LABEL = "Transient layer with semantic based decorator";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_NAME = "SBD_TransientLayer";

    private DDiagram dDiagram;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);

    }

    /**
     * Check that {@link MappingBasedDecoration} in a transient layer behave as
     * expected:<br/>
     * - Activating a transient layer does not put the session to dirty state
     * <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram and its project deactivates a transient
     * layer<br/>
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testMappingBasedDecoration() throws OperationCanceledException, InterruptedException {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerHelper.isTransientLayer(transientLayers.get(0)));
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerHelper.isTransientLayer(transientLayers.get(1)));
        Assert.assertEquals("There should be 2 transient layers", 2, transientLayers.size());
        Assert.assertEquals("The first transient layer has not the expected name", TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_LABEL, transientLayers.get(0).getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayers.get(0).getDecorationDescriptionsSet());
        final List<MappingBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayers.get(0).getDecorationDescriptionsSet().getDecorationDescriptions(), MappingBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

        MappingBasedDecoration mbd = decorationDescriptions.get(0);

        // Initialize the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                dDiagram = (DDiagram) DialectManager.INSTANCE.createRepresentation(DIAGRAM_DESCRIPTION_NAME, semanticModel, diagramDescription, session, new NullProgressMonitor());
            }
        });
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());

        processTestOnTransientLayerDecoration(mbd, TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_NAME);
    }

    /**
     * Check that {@link SemanticBasedDecoration} in a transient layer behave as
     * expected:<br/>
     * - Activating a transient layer does not put the session to dirty state
     * <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram and its project deactivates a transient
     * layer<br/>
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testSemanticBasedDecoration() throws OperationCanceledException, InterruptedException {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final List<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerHelper.isTransientLayer(transientLayers.get(0)));
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerHelper.isTransientLayer(transientLayers.get(1)));
        Assert.assertEquals("There should be 2 transient layers", 2, transientLayers.size());
        Assert.assertEquals("The second transient layer has not the expected name", TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_LABEL, transientLayers.get(1).getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayers.get(1).getDecorationDescriptionsSet());
        final List<SemanticBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayers.get(1).getDecorationDescriptionsSet().getDecorationDescriptions(), SemanticBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

        SemanticBasedDecoration sbd = decorationDescriptions.get(0);

        // Initialize the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                dDiagram = (DDiagram) DialectManager.INSTANCE.createRepresentation(DIAGRAM_DESCRIPTION_NAME, semanticModel, diagramDescription, session, new NullProgressMonitor());
            }
        });
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());

        processTestOnTransientLayerDecoration(sbd, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_NAME);
    }

    private void processTestOnTransientLayerDecoration(DecorationDescription mbd, String layerName) throws InterruptedException {
        // Activate transient layer with a mapping based decorator
        activateLayer(dDiagram, layerName);
        final List<DDiagramElement> elements = Lists.newArrayList(dDiagram.getOwnedDiagramElements());
        checkTransientDecoration(elements, mbd);
        Assert.assertEquals("The session should be still be in sync when applying a transient layer", SessionStatus.SYNC, session.getStatus());

        // Check that closing and reopening the diagram does not change the
        // transient layer activation
        DialectUIManager.INSTANCE.closeEditor(editor, true);
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        checkTransientDecoration(elements, mbd);

        // Close session and reopen representation to check that the transient
        // layer is not activated and its decoration not displayed
        URI uri = session.getSessionResource().getURI();
        closeSession(session);
        TestsUtil.waitUntil(new OpenedSessionsCondition(0));
        createSession(uri);
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());
        session.open(new NullProgressMonitor());
        TestsUtil.waitUntil(new OpenedSessionsCondition(1));
        dDiagram = (DDiagram) ((DAnalysis) session.getSessionResource().getContents().get(0)).getOwnedViews().get(0).getOwnedRepresentationDescriptors().get(0).getRepresentation();
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());

        // Check that the transient layer is not active and that there is no
        // displayed decorator
        Assert.assertEquals("Only one layer should be active", 1, dDiagram.getActivatedLayers().size());
        Assert.assertEquals("Only the default layer should be active", "Default", dDiagram.getActivatedLayers().get(0).getName());
        Assert.assertEquals("No transient layer should be active", 0, dDiagram.getActivatedTransientLayers().size());
        checkNoTransientDecoration(dDiagram.getOwnedDiagramElements());
    }

    private void checkTransientDecoration(final List<DDiagramElement> elements, final DecorationDescription decorationDescription) {
        if (decorationDescription instanceof MappingBasedDecoration) {
            checkTransientMappingBasedDecoration(elements, (MappingBasedDecoration) decorationDescription);
        } else if (decorationDescription instanceof SemanticBasedDecoration) {
            checkTransientSemanticBasedDecoration(elements, (SemanticBasedDecoration) decorationDescription);
        } else {
            fail("Unhandled DecorationDescription");
        }
    }

    private void checkTransientMappingBasedDecoration(final List<? extends DDiagramElement> elements, final MappingBasedDecoration decorationDescription) {
        for (final DDiagramElement diagramElement : elements) {
            checkTransientMappingBasedDecoration(diagramElement, decorationDescription);
        }
    }

    private void checkTransientMappingBasedDecoration(final DDiagramElement diagramElement, final MappingBasedDecoration decorationDescription) {
        if (decorationDescription.getMappings().contains(diagramElement.getMapping())) {
            // Sample data does not describe mapping with several decorations
            assertEquals("We should have 1 decoration here", 1, diagramElement.getTransientDecorations().size());
        } else {
            for (Decoration decoration : diagramElement.getTransientDecorations()) {
                assertNotSame("We should have this decoration here", decorationDescription, decoration.getDescription());
            }
        }
    }

    private void checkTransientSemanticBasedDecoration(final List<DDiagramElement> elements, final SemanticBasedDecoration decorationDescription) {
        for (final DDiagramElement diagramElement : elements) {
            checkTransientSemanticBasedDecoration(diagramElement, decorationDescription);
        }
    }

    private void checkTransientSemanticBasedDecoration(final DDiagramElement diagramElement, final SemanticBasedDecoration decorationDescription) {
        if (accessor.eInstanceOf(diagramElement.getTarget(), decorationDescription.getDomainClass())) {
            assertEquals("We should have 1 decoration here", 1, diagramElement.getTransientDecorations().size());
        } else {
            assertEquals("We should have no decoration here", 0, diagramElement.getTransientDecorations().size());
        }
    }

    private void checkNoTransientDecoration(final List<DDiagramElement> elements) {
        for (final DDiagramElement diagramElement : elements) {
            checkNoTransientDecoration(diagramElement);
        }
    }

    private void checkNoTransientDecoration(final DDiagramElement diagramElement) {
        assertEquals("We should have no decoration here", 0, diagramElement.getTransientDecorations().size());
    }

}
