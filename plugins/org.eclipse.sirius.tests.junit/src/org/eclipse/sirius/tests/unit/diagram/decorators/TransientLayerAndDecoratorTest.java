/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.decorators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramExtensionDescription;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.model.business.internal.helper.LayerModelHelper;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.OpenedSessionsCondition;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.description.DecorationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.ui.IEditorPart;
import org.junit.Assert;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test the transient layer and its decorators functionality.
 * 
 * @author smonnier
 */
public class TransientLayerAndDecoratorTest extends GenericTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/transientDecorators/transient_decorator.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/transientDecorators/decorator.odesign";

    private static final String MODELER_EXTENSION_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/transientDecorators/decorator_extension.odesign";

    private static final String DIAGRAM_DESCRIPTION_NAME = "Transient_Decorator";

    private static final String DIAGRAM_EXTENSION_DESCRIPTION_NAME = "Transient_Decorator_Extension";

    private static final String VIEWPOINT_NAME = "Transient_Decorator";

    private static final String VIEWPOINT_EXTENSION_NAME = "Transient_Decorator_Extension";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_LABEL = "Transient layer with mapping based decorator";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_NAME = "MBD_TransientLayer";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_EXTENSION_LABEL = "Transient layer with mapping based decorator - Extension";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_EXTENSION_NAME = "MBD_TransientLayer_ext";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_OPTIONAL_LABEL = "Transient layer with mapping based decorator - Default and Optional";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME = "MBD_TransientLayer_default_optional";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_LABEL = "Transient layer with mapping based decorator - Default and not Optional";

    private static final String TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_NAME = "MBD_TransientLayer_default_not_optional";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_LABEL = "Transient layer with semantic based decorator";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_NAME = "SBD_TransientLayer";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_EXTENSION_LABEL = "Transient layer with semantic based decorator - Extension";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_EXTENSION_NAME = "SBD_TransientLayer_ext";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_LABEL = "Transient layer with semantic based decorator - Default and Optional";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME = "SBD_TransientLayer_default_optional";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_LABEL = "Transient layer with semantic based decorator - Default and not Optional";

    private static final String TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_NAME = "SBD_TransientLayer_default_not_optional";

    private DDiagram dDiagram;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        String[] modelerDescriptionPaths = { MODELER_PATH, MODELER_PATH, MODELER_EXTENSION_PATH };
        genericSetUp(SEMANTIC_MODEL_PATH, Arrays.asList(modelerDescriptionPaths));
        initViewpoint(VIEWPOINT_NAME);

    }

    /**
     * Check that {@link MappingBasedDecoration} in a transient layer behave as expected:<br/>
     * - Activating a transient layer does not put the session to dirty state <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram and its project deactivates a transient layer<br/>
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
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerModelHelper.isTransientLayer(transientLayers.get(0)));
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerModelHelper.isTransientLayer(transientLayers.get(1)));
        Assert.assertEquals("There should be 2 transient layers", 6, transientLayers.size());
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
     * Check that {@link MappingBasedDecoration} in a transient layer behave as expected:<br/>
     * - Activating a transient layer does not put the session to dirty state <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram and its project deactivates a transient layer<br/>
     * 
     * @throws Exception
     */
    public void testMappingBasedDecorationFromDiagramExtension() throws Exception {
        initViewpoint(VIEWPOINT_EXTENSION_NAME);
        Assert.assertEquals("There should be 2 available viewpoint", 2, viewpoints.size());
        Iterator<Viewpoint> viewpointIterator = viewpoints.iterator();
        viewpointIterator.next();
        Viewpoint viewpoint_extension = viewpointIterator.next();
        Assert.assertEquals("The second viewpoint is not the expected one", VIEWPOINT_EXTENSION_NAME, viewpoint_extension.getName());
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        Assert.assertEquals("The viewpoint should have one diagram extension", 1, Iterables.size(viewpoint_extension.getOwnedRepresentationExtensions()));
        DiagramExtensionDescription diagramExtensionDescription = Iterables.getOnlyElement(Iterables.filter(viewpoint_extension.getOwnedRepresentationExtensions(), DiagramExtensionDescription.class));
        Assert.assertEquals("The diagram extension does not have the expected name", DIAGRAM_EXTENSION_DESCRIPTION_NAME, diagramExtensionDescription.getName());
        final EList<AdditionalLayer> transientLayers = diagramExtensionDescription.getLayers();
        Assert.assertEquals("There should be 2 transient layers", 2, transientLayers.size());
        Assert.assertEquals("The first transient layer has not the expected name", TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_EXTENSION_LABEL, transientLayers.get(0).getLabel());

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

        processTestOnTransientLayerDecoration(mbd, TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_EXTENSION_NAME);
    }

    /**
     * This test checks that a default layer is applied by default. As it is optional, it also checks that the layer can
     * be deactivated.
     * 
     */
    public void testMappingBasedDecoration_Default_Optional() {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
        AdditionalLayer transientLayer = transientLayers.get(2);
        Assert.assertEquals("The third transient layer has not the expected name", TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_OPTIONAL_LABEL, transientLayer.getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayer.getDecorationDescriptionsSet());
        final List<MappingBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), MappingBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

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

        Assert.assertTrue("A default transient layer was unexpectedly not among the activated transient layers of the diagram", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        deactivateLayer(dDiagram, TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME);

        Assert.assertFalse("The optional transient layer was not deactivated", dDiagram.getActivatedTransientLayers().contains(transientLayer));
    }

    /**
     * This test checks that a default layer is applied by default. As it is non optional, it also checks that the layer
     * cannot be deactivated.
     * 
     */
    public void testMappingBasedDecoration_Default_NotOptional() {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
        AdditionalLayer transientLayer = transientLayers.get(4);
        Assert.assertEquals("The third transient layer has not the expected name", TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_LABEL, transientLayer.getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayer.getDecorationDescriptionsSet());
        final List<MappingBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), MappingBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

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

        Assert.assertTrue("A default transient layer was unexpectedly not among the activated transient layers of the diagram", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        // Try to deactivate a transient non optional layer
        deactivateLayer(dDiagram, TRANSIENT_LAYER_MAPPING_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_NAME);

        Assert.assertTrue("The non optional transient layer was deactivated", dDiagram.getActivatedTransientLayers().contains(transientLayer));
    }

    /**
     * Check that {@link SemanticBasedDecoration} in a transient layer behave as expected:<br/>
     * - Activating a transient layer does not put the session to dirty state <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram and its project deactivates a transient layer<br/>
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
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerModelHelper.isTransientLayer(transientLayers.get(0)));
        Assert.assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, LayerModelHelper.isTransientLayer(transientLayers.get(1)));
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
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

    /**
     * Check that {@link SemanticBasedDecoration} in a transient layer behave as expected:<br/>
     * - Activating a transient layer does not put the session to dirty state <br/>
     * - Closing/Reopening a diagram does not deactivate a transient layer<br/>
     * - Closing/Reopening a diagram does not change layer activation status <br/>
     * - Closing/Reopening a diagram and its project deactivates a transient layer<br/>
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testSemanticBasedDecorationFromDiagramExtension() throws OperationCanceledException, InterruptedException {
        initViewpoint(VIEWPOINT_EXTENSION_NAME);
        Assert.assertEquals("There should be 2 available viewpoint", 2, viewpoints.size());
        Iterator<Viewpoint> viewpointIterator = viewpoints.iterator();
        viewpointIterator.next();
        Viewpoint viewpoint_extension = viewpointIterator.next();
        Assert.assertEquals("The second viewpoint is not the expected one", VIEWPOINT_EXTENSION_NAME, viewpoint_extension.getName());
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        Assert.assertEquals("The viewpoint should have one diagram extension", 1, Iterables.size(viewpoint_extension.getOwnedRepresentationExtensions()));
        DiagramExtensionDescription diagramExtensionDescription = Iterables.getOnlyElement(Iterables.filter(viewpoint_extension.getOwnedRepresentationExtensions(), DiagramExtensionDescription.class));
        Assert.assertEquals("The diagram extension does not have the expected name", DIAGRAM_EXTENSION_DESCRIPTION_NAME, diagramExtensionDescription.getName());
        final EList<AdditionalLayer> transientLayers = diagramExtensionDescription.getLayers();
        Assert.assertEquals("There should be 2 transient layers", 2, transientLayers.size());
        Assert.assertEquals("The second transient layer has not the expected name", TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_EXTENSION_LABEL, transientLayers.get(1).getLabel());

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

        processTestOnTransientLayerDecoration(sbd, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_EXTENSION_NAME);
    }

    /**
     * This test checks that a default layer is applied by default. </br>
     * - As it is optional, it also checks that the layer can be deactivated.</br>
     * - After closing and reopening the diagram, the transient layer activation should not change .</br>
     * - The transient layer application should not dirtyfy the diagram
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testSemanticBasedDecoration_Default_Optional() throws OperationCanceledException, InterruptedException {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
        AdditionalLayer transientLayer = transientLayers.get(3);
        Assert.assertEquals("The second transient layer has not the expected name", TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_LABEL, transientLayer.getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayer.getDecorationDescriptionsSet());
        final List<SemanticBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), SemanticBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

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

        Assert.assertTrue("A default transient layer was unexpectedly not among the activated transient layers of the diagram", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        deactivateLayer(dDiagram, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME);

        Assert.assertFalse("The optional transient layer was not deactivated", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        DialectUIManager.INSTANCE.closeEditor(editor, true);
        TestsUtil.synchronizationWithUIThread();
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());

        Assert.assertFalse("The optional transient layer that has been deactivated should still be deactivated after closing and reoping diagram",
                dDiagram.getActivatedTransientLayers().contains(transientLayer));
    }

    /**
     * This test checks that the transient layer activation or deactivation will not update the diagram making the
     * session dirty.
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testTransientLayerActivationInManualRefresh() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_REFRESH_ON_REPRESENTATION_OPENING.name(), false);

        // Initialization: Check that the transient layers and decorator are as
        // expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
        AdditionalLayer transientLayer = transientLayers.get(3);
        Assert.assertEquals("The second transient layer has not the expected name", TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_LABEL, transientLayer.getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayer.getDecorationDescriptionsSet());
        final List<SemanticBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), SemanticBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

        // Initialize the diagram
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                dDiagram = (DDiagram) DialectManager.INSTANCE.createRepresentation(DIAGRAM_DESCRIPTION_NAME, semanticModel, diagramDescription, session, new NullProgressMonitor());
                ((EPackage) semanticModel).getEClassifiers().add(EcoreFactory.eINSTANCE.createEClass());
            }
        });
        editor = DialectUIManager.INSTANCE.openEditor(session, dDiagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        session.save(new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        // check that there is only one DDiagramElement
        String badNumberOfElements = "The sirius refresh has occurred when changing transient layer activation status. Bad number of DDiagramElement in the diagram";
        Assert.assertEquals(badNumberOfElements, 1, dDiagram.getDiagramElements().size());
        Assert.assertTrue("A default transient layer was unexpectedly not among the activated transient layers of the diagram", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        // activate and deactivate transient layer and check that the sirius
        // refresh won't be done
        deactivateLayer(dDiagram, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME);

        Assert.assertFalse("The optional transient layer was not deactivated", dDiagram.getActivatedTransientLayers().contains(transientLayer));
        Assert.assertEquals(badNumberOfElements, 1, dDiagram.getDiagramElements().size());

        activateLayer(dDiagram, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_OPTIONAL_NAME);

        Assert.assertTrue("The optional transient layer was not activated", dDiagram.getActivatedTransientLayers().contains(transientLayer));
        Assert.assertEquals(badNumberOfElements, 1, dDiagram.getDiagramElements().size());
    }

    /**
     * 
     * This test checks that a default layer is applied by default. As it is non optional, it also checks that the layer
     * cannot be deactivated.
     * 
     * @throws OperationCanceledException
     * @throws InterruptedException
     */
    public void testSemanticBasedDecoration_Default_NotOptional() throws OperationCanceledException, InterruptedException {
        // Initialization: Check that the transient layers and mapping based
        // decorator are as expected
        final DiagramDescription diagramDescription = findDiagramDescription(DIAGRAM_DESCRIPTION_NAME);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        final EList<AdditionalLayer> transientLayers = diagramDescription.getAdditionalLayers();
        Assert.assertEquals("There should be 6 transient layers", 6, transientLayers.size());
        AdditionalLayer transientLayer = transientLayers.get(5);
        Assert.assertEquals("The second transient layer has not the expected name", TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_LABEL, transientLayer.getLabel());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, transientLayer.getDecorationDescriptionsSet());
        final List<SemanticBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(transientLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), SemanticBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, Iterables.size(decorationDescriptions));

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

        Assert.assertTrue("A default transient layer was unexpectedly not among the activated transient layers of the diagram", dDiagram.getActivatedTransientLayers().contains(transientLayer));

        deactivateLayer(dDiagram, TRANSIENT_LAYER_SEMANTIC_BASED_DECORATOR_DEFAULT_NOT_OPTIONAL_NAME);

        Assert.assertTrue("The non optional transient layer was deactivated", dDiagram.getActivatedTransientLayers().contains(transientLayer));
    }

    private void processTestOnTransientLayerDecoration(DecorationDescription mbd, String layerName) throws InterruptedException {
        // Activate transient layer with a mapping based decorator
        activateLayer(dDiagram, layerName);
        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(dDiagram.getOwnedDiagramElements());
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
        AdditionalLayer transientLayer = (AdditionalLayer) mbd.eContainer().eContainer();
        if (!transientLayer.isActiveByDefault()) {
            Assert.assertEquals("4 default transient layer should be active", 4, dDiagram.getActivatedTransientLayers().size());
            Assert.assertFalse("The parent layer should not be active, as it is not default", dDiagram.getActivatedTransientLayers().contains(mbd.eContainer().eContainer()));
        } else {
            Assert.assertEquals("5 default transient layer should be active", 5, dDiagram.getActivatedTransientLayers().size());
            Assert.assertTrue("The parent layer should be active, as it is default", dDiagram.getActivatedTransientLayers().contains(mbd.eContainer().eContainer()));
        }
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
            // 1 DecoratorDescription was activated, 4 are default
            assertEquals("We should have 5 decoration here", 5, diagramElement.getTransientDecorations().size());
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
            // 1 DecoratorDescription was activated, 4 are default
            assertEquals("We should have 5 decoration here", 5, diagramElement.getTransientDecorations().size());
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
