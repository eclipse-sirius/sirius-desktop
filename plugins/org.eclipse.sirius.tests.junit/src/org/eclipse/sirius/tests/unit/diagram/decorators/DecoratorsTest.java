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
package org.eclipse.sirius.tests.unit.diagram.decorators;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gmf.runtime.diagram.core.preferences.PreferencesHint;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecorator;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.dialect.command.RefreshRepresentationsCommand;
import org.eclipse.sirius.business.api.query.DViewQuery;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.metamodel.helper.model.LayerModelHelper;
import org.eclipse.sirius.diagram.business.internal.metamodel.operations.model.DDiagramSpecOperations;
import org.eclipse.sirius.diagram.description.AdditionalLayer;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.MappingBasedDecoration;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractBorderedDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.part.DiagramEditPartService;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.SiriusDecoratorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.decoration.SiriusGenericDecorator;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tests.unit.diagram.GenericTestCase;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Decoration;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.GenericDecorationDescription;
import org.eclipse.sirius.viewpoint.description.SemanticBasedDecoration;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.uml2.uml.NamedElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Test creation od decorators via Designer Decorations.
 * 
 * @author mporhel
 */
public class DecoratorsTest extends GenericTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/sprint.uml";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/decorators/sprint.odesign";

    private static final String TEST_CLASS_DIAGRAM = "Test class diagram";

    private static Method getDecorators;

    static {
        try {
            getDecorators = DecorationEditPolicy.class.getDeclaredMethod("getDecorators");
            getDecorators.setAccessible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void setLayerVisibility(final DDiagram diagram, final Layer layer, final boolean visible) {

        boolean transientLayer = LayerModelHelper.isTransientLayer(layer);
        EList<Layer> activatedLayers = diagram.getActivatedLayers();
        EList<AdditionalLayer> activatedTransientLayers = diagram.getActivatedTransientLayers();

        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        domain.getCommandStack().execute(new RecordingCommand(domain) {

            @Override
            protected void doExecute() {
                if (visible) {
                    if (transientLayer) {
                        activatedTransientLayers.add((AdditionalLayer) layer);
                    } else {
                        activatedLayers.add(layer);
                    }
                } else {
                    if (transientLayer) {
                        activatedTransientLayers.remove(layer);
                    } else {
                        activatedLayers.remove(layer);
                    }
                }
            }
        });
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);

        final Viewpoint vp = getViewpointFromName("Sprint with UML2 (Modeler test for decorators)");
        activateViewpoint(vp.getName());

    }

    public void testWithoutDecorationsSet() {
        final DiagramDescription classDiag = findDiagramDescription("WithoutDecorationsSet");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        getRefreshedDiagram();
    }

    public void testWithoutDecorationDescription() {
        final DiagramDescription classDiag = findDiagramDescription("WithoutDecorationdescription");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 0, defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().size());

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        getRefreshedDiagram();
    }

    public void testMappingBasedDecoration() {
        final DiagramDescription classDiag = findDiagramDescription("MappingBasedDecoration");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final List<MappingBasedDecoration> decorationDescriptions = Lists
                .newArrayList(Iterables.filter(defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions(), MappingBasedDecoration.class));
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, Iterables.size(decorationDescriptions));

        MappingBasedDecoration mbd = decorationDescriptions.get(0);
        MappingBasedDecoration mbdc = decorationDescriptions.get(1);
        MappingBasedDecoration mbdle = decorationDescriptions.get(2);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, mbd.getMappings().size());
        DiagramElementMapping mapping = mbd.getMappings().get(0);
        assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, mapping instanceof NodeMapping && mapping.eContainer() instanceof Layer);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, mbdc.getMappings().size());
        mapping = mbdc.getMappings().get(0);
        assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, mapping instanceof ContainerMapping && mapping.eContainer() instanceof Layer);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, mbdle.getMappings().size());
        mapping = mbdle.getMappings().get(0);
        assertTrue(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, mapping instanceof NodeMapping && mapping.eContainer() instanceof ContainerMapping);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        final DDiagram diagram = getRefreshedDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 6 diagram elements here .", 6, elements.size());
        assertEquals("We should have 5 nodes here .", 5, diagram.getNodes().size());
        assertEquals("We should have 1 container/list here .", 1, diagram.getContainers().size());
        EList<DNodeListElement> listElements = ((DNodeList) diagram.getContainers().get(0)).getOwnedElements();
        assertEquals("We should have 2 list elements here .", 2, listElements.size());

        checkDecoration(elements, mbd);
        checkDecoration(elements, mbdc);
        checkDecoration(elements, mbdle);

        /*
         * The decoration are computed by the ddiagram synchronizer even if there is no check on list element edit parts
         * which are IDiagramNameEditpart: DescribedDecoratorProvider currently provide decorations only for
         * IDiagramElementEditPart whch does not inherits from IDiagramNameEditPart.
         */
        checkDecoration(listElements, mbd);
        checkDecoration(listElements, mbdc);
        checkDecoration(listElements, mbdle);

    }

    public void testSemanticBasedDecoration() {
        final DiagramDescription classDiag = findDiagramDescription("SemanticBasedDecoration");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final SemanticBasedDecoration decorationDescription = (SemanticBasedDecoration) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);
        assertFalse(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, StringUtil.isEmpty(decorationDescription.getDomainClass()));

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        final DDiagram diagram = getRefreshedDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 8 elements here .", 8, elements.size());
        checkDecoration(elements, decorationDescription);

        DNodeList list = (DNodeList) diagram.getContainers().get(0);
        assertEquals("We should have 5 list elements here .", 5, list.getElements().size());
        checkDecoration(list.getElements(), decorationDescription);
    }

    public void testGenericDecoration() {
        final DiagramDescription classDiag = findDiagramDescription("GenericDecoration");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final GenericDecorationDescription decorationDescription = (GenericDecorationDescription) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        final DDiagram diagram = getRefreshedDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 8 elements here .", 8, elements.size());
        checkDecoration(elements);

        DNodeList list = (DNodeList) diagram.getContainers().get(0);
        assertEquals("We should have 5 list elements here .", 5, list.getElements().size());
        checkDecoration(list.getElements());
    }

    public void testMappingBasedDecorationLayerDeactivation() {
        final DiagramDescription classDiag = findDiagramDescription("MappingBasedDecorationLayer");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 2, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);

        assertNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer.getDecorationDescriptionsSet());
        final MappingBasedDecoration decorationDescription = (MappingBasedDecoration) firstLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, decorationDescription.getMappings().size());

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = getRefreshedDiagram();

        List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 5 nodes here .", 5, elements.size());

        checkNoDecoration(elements);

        setLayerVisibility(diagram, firstLayer, true);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 5 nodes here .", 5, elements.size());
        checkDecoration(elements, decorationDescription);

        setLayerVisibility(diagram, firstLayer, false);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 5 nodes here .", 5, elements.size());
        checkNoDecoration(elements);
    }

    public void testSemanticBasedDecorationLayerDeactivation() {
        final DiagramDescription classDiag = findDiagramDescription("SemanticBasedDecorationLayer");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 2, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);

        assertNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer.getDecorationDescriptionsSet());
        final SemanticBasedDecoration decorationDescription = (SemanticBasedDecoration) firstLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);
        assertFalse(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, StringUtil.isEmpty(decorationDescription.getDomainClass()));

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = getRefreshedDiagram();

        List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 7 nodes here .", 7, elements.size());

        SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(elements.get(0).getTarget());

        checkNoDecoration(elements);

        setLayerVisibility(diagram, firstLayer, true);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 7 nodes here .", 7, elements.size());
        checkDecoration(elements, decorationDescription);

        setLayerVisibility(diagram, firstLayer, false);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 7 nodes here .", 7, elements.size());
        checkNoDecoration(elements);
    }

    public void testGenericDecorationLayerDeactivation() {
        final DiagramDescription classDiag = findDiagramDescription("GenericDecorationLayer");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 2, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);

        assertNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer.getDecorationDescriptionsSet());
        final GenericDecorationDescription decorationDescription = (GenericDecorationDescription) firstLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        DDiagram diagram = getRefreshedDiagram();

        List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 7 nodes here .", 7, elements.size());

        checkNoDecoration(elements);

        setLayerVisibility(diagram, firstLayer, true);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 7 nodes here .", 7, elements.size());
        checkDecoration(elements);

        setLayerVisibility(diagram, firstLayer, false);
        diagram = getRefreshedDiagram();
        elements = diagram.getOwnedDiagramElements();
        assertEquals("We should have 7 nodes here .", 7, elements.size());
        checkNoDecoration(elements);
    }

    private void checkDecoration(final List<? extends DDiagramElement> elements, final MappingBasedDecoration decorationDescription) {
        for (final DDiagramElement diagramElement : elements) {
            checkDecoration(diagramElement, decorationDescription);
        }
    }

    private void checkDecoration(final DDiagramElement diagramElement, final MappingBasedDecoration decorationDescription) {
        if (decorationDescription.getMappings().contains(diagramElement.getMapping())) {
            // Sample data does not describe mapping with several decorations
            assertEquals("We should have 1 decoration here", 1, diagramElement.getDecorations().size());
        } else {
            for (Decoration decoration : diagramElement.getDecorations()) {
                assertNotSame("We should have this decoration here", decorationDescription, decoration.getDescription());
            }
        }
    }

    private void checkDecoration(final List<DDiagramElement> elements, final SemanticBasedDecoration decorationDescription) {
        for (final DDiagramElement diagramElement : elements) {
            checkDecoration(diagramElement, decorationDescription);
        }
    }

    private void checkDecoration(final DDiagramElement diagramElement, final SemanticBasedDecoration decorationDescription) {
        if (accessor.eInstanceOf(diagramElement.getTarget(), decorationDescription.getDomainClass())) {
            assertEquals("We should have 1 decoration here", 1, diagramElement.getDecorations().size());
        } else {
            assertEquals("We should have no decoration here", 0, diagramElement.getDecorations().size());
        }
    }

    private void checkDecoration(final List<DDiagramElement> elements) {
        for (final DDiagramElement diagramElement : elements) {
            checkDecoration(diagramElement);
        }
    }

    private void checkDecoration(final DDiagramElement diagramElement) {
        int ndDecos = diagramElement.getDecorations().size() + diagramElement.getTransientDecorations().size();
        if (diagramElement.getTarget() instanceof NamedElement && !((NamedElement) diagramElement.getTarget()).getName().startsWith("false")) {
            assertEquals("We should have 1 decoration here", 1, ndDecos);
        } else {
            assertEquals("We should have no decoration here", 0, ndDecos);
        }
    }

    private void checkNoDecoration(final List<DDiagramElement> elements) {
        for (final DDiagramElement diagramElement : elements) {
            checkNoDecoration(diagramElement);
        }
    }

    private void checkNoDecoration(final DDiagramElement diagramElement) {
        assertEquals("We should have no decoration here", 0, diagramElement.getDecorations().size());
    }

    /*
     * No check on list element edit parts which are IDiagramNameEditpart: DescribedDecoratorProvider currently provide
     * decorations only for IDiagramElementEditPart whch does not inherits from IDiagramNameEditPart.
     */
    public void testMappingBasedDecorationEditPart() throws Exception {
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 8, viewpoints.iterator().next().getOwnedRepresentations().size());
        final DSemanticDiagram diagram = getDiagramFromDescriptionName("MappingBasedDecoration");

        doInitSemanticDiagram(diagram);

        final Layer defaultLayer = diagram.getDescription().getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final MappingBasedDecoration decorationDescription = (MappingBasedDecoration) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, decorationDescription.getMappings().size());

        NodeMapping nodeMapping = defaultLayer.getNodeMappings().get(0);
        List<? extends DDiagramElement> elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        assertEquals("We should have 3 nodes here", 3, elements.size());

        nodeMapping = defaultLayer.getNodeMappings().get(1);
        elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        assertEquals("We should have 2 nodes here", 2, elements.size());

        ContainerMapping containerMapping = defaultLayer.getContainerMappings().get(0);
        elements = DDiagramSpecOperations.getContainersFromMapping(diagram, containerMapping);
        assertEquals("We should have 1 container here", 1, elements.size());

        final Shell shell = new Shell();
        final DiagramEditPart diagramEditPart = doInitGmfDiagram(shell, diagram);

        for (final Object editPart : diagramEditPart.getChildren()) {
            if (editPart instanceof AbstractBorderedDiagramElementEditPart) {
                final AbstractBorderedDiagramElementEditPart nodeEditPart = (AbstractBorderedDiagramElementEditPart) editPart;
                final DDiagramElement diagramElement = nodeEditPart.resolveDiagramElement();
                final DecorationEditPolicy policy = (DecorationEditPolicy) nodeEditPart.getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
                final Map<Object, IDecorator> decorators = getDecorators(policy);
                if (decorationDescription.getMappings().contains(diagramElement.getMapping())) {
                    assertEquals("We should have 1 decoration", 1, diagramElement.getDecorations().size());
                    assertEquals("We should have 1 decorator", 1, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                } else {
                    assertEquals("We should have 0 decoration", 0, diagramElement.getDecorations().size());
                    assertEquals("We should have 0 decorator", 0, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                }

            }
        }

        doCleanupGmfDiagram(shell, diagramEditPart);
    }

    private Map<Object, IDecorator> getDecorators(final DecorationEditPolicy policy) throws Exception {
        return (Map<Object, IDecorator>) getDecorators.invoke(policy);
    }

    public void testSemanticBasedDecorationEditPart() throws Exception {
        // doInitSession();
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 8, viewpoints.iterator().next().getOwnedRepresentations().size());
        final DSemanticDiagram diagram = getDiagramFromDescriptionName("SemanticBasedDecoration");

        doInitSemanticDiagram(diagram);

        final Layer defaultLayer = diagram.getDescription().getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final SemanticBasedDecoration decorationDescription = (SemanticBasedDecoration) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);
        assertFalse(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, StringUtil.isEmpty(decorationDescription.getDomainClass()));

        NodeMapping nodeMapping = defaultLayer.getNodeMappings().get(0);
        List<DNode> elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        nodeMapping = defaultLayer.getNodeMappings().get(1);
        elements.addAll(DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping));
        assertEquals("We should have 5 nodes here", 5, elements.size());

        nodeMapping = defaultLayer.getNodeMappings().get(2);
        elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        assertEquals("We should have 2 nodes here", 2, elements.size());

        final Shell shell = new Shell();
        final DiagramEditPart diagramEditPart = doInitGmfDiagram(shell, diagram);

        for (final Object editPart : diagramEditPart.getChildren()) {
            if (editPart instanceof AbstractDiagramNodeEditPart) {
                final AbstractDiagramNodeEditPart nodeEditPart = (AbstractDiagramNodeEditPart) editPart;
                final DDiagramElement diagramElement = nodeEditPart.resolveDiagramElement();
                final DecorationEditPolicy policy = (DecorationEditPolicy) nodeEditPart.getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
                final Map<Object, IDecorator> decorators = getDecorators(policy);
                if (accessor.eInstanceOf(diagramElement.getTarget(), decorationDescription.getDomainClass())) {
                    assertEquals("We should have 1 decoration", 1, diagramElement.getDecorations().size());
                    assertEquals("We should have 1 decorator", 1, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                } else {
                    assertEquals("We should have 0 decoration", 0, diagramElement.getDecorations().size());
                    assertEquals("We should have 0 decorator", 0, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                }
            }
        }

        doCleanupGmfDiagram(shell, diagramEditPart);
        doCleanupSession();
    }

    public void testGenericDecorationEditPart() throws Exception {
        // doInitSession();
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 8, viewpoints.iterator().next().getOwnedRepresentations().size());
        final DSemanticDiagram diagram = getDiagramFromDescriptionName("GenericDecoration");

        doInitSemanticDiagram(diagram);

        final Layer defaultLayer = diagram.getDescription().getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer.getDecorationDescriptionsSet());
        final GenericDecorationDescription decorationDescription = (GenericDecorationDescription) defaultLayer.getDecorationDescriptionsSet().getDecorationDescriptions().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, decorationDescription);

        NodeMapping nodeMapping = defaultLayer.getNodeMappings().get(0);
        List<DNode> elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        nodeMapping = defaultLayer.getNodeMappings().get(1);
        elements.addAll(DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping));
        assertEquals("We should have 5 nodes here", 5, elements.size());

        nodeMapping = defaultLayer.getNodeMappings().get(2);
        elements = DDiagramSpecOperations.getNodesFromMapping(diagram, nodeMapping);
        assertEquals("We should have 2 nodes here", 2, elements.size());

        final Shell shell = new Shell();
        final DiagramEditPart diagramEditPart = doInitGmfDiagram(shell, diagram);

        for (final Object editPart : diagramEditPart.getChildren()) {
            if (editPart instanceof AbstractDiagramNodeEditPart) {
                final AbstractDiagramNodeEditPart nodeEditPart = (AbstractDiagramNodeEditPart) editPart;
                final DDiagramElement diagramElement = nodeEditPart.resolveDiagramElement();
                final DecorationEditPolicy policy = (DecorationEditPolicy) nodeEditPart.getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
                final Map<Object, IDecorator> decorators = getDecorators(policy);
                if (diagramElement.getTarget() instanceof NamedElement && !((NamedElement) diagramElement.getTarget()).getName().startsWith("false")) {
                    assertEquals("We should have 1 decoration", 1, diagramElement.getDecorations().size());
                    assertEquals("We should have 1 decorator", 1, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                } else {
                    assertEquals("We should have 0 decoration", 0, diagramElement.getDecorations().size());
                    assertEquals("We should have 0 decorator", 0, ((SiriusGenericDecorator) decorators.get(SiriusDecoratorProvider.KEY)).getDecorations().size());
                }
            }
        }

        doCleanupGmfDiagram(shell, diagramEditPart);
        doCleanupSession();
    }

    // Cannot return null
    private DSemanticDiagram getDiagramFromDescriptionName(final String name) {
        Iterable<DSemanticDiagram> matchingDescriptions = Collections.emptySet();
        for (final DView dView : session.getOwnedViews()) {

            Iterable<DSemanticDiagram> allDSemanticDiagrams = Iterables.filter(new DViewQuery(dView).getLoadedRepresentations(), DSemanticDiagram.class);
            matchingDescriptions = Iterables.filter(allDSemanticDiagrams, new Predicate<DSemanticDiagram>() {
                @Override
                public boolean apply(DSemanticDiagram input) {
                    return name.equals(input.getDescription().getName());
                }
            });

            if (!Iterables.isEmpty(matchingDescriptions)) {
                break;
            }
        }

        DSemanticDiagram result = null;

        int size = Iterables.size(matchingDescriptions);
        if (size == 0) {
            fail("Description name '" + name + "' was not found. " + getDebugInfo());
        } else if (size > 1) {
            fail("Too many descriptions named '" + name + "' were found: " + size + ". " + getDebugInfo());
        } else {
            // size = 1
            result = Iterables.get(matchingDescriptions, 0);
        }
        return result;
    }

    private String getDebugInfo() {
        StringBuilder sb = new StringBuilder("Debug info:\n");

        for (final DView dView : session.getOwnedViews()) {
            sb.append("DView: ");
            sb.append(dView);
            sb.append("\n");
            for (DRepresentationDescriptor rep : new DViewQuery(dView).getLoadedRepresentationsDescriptors()) {
                sb.append("\t");
                sb.append("DRepresentation: ");
                sb.append(rep.getName());
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    private void doInitSemanticDiagram(final DSemanticDiagram diagram) {
        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
        domain.getCommandStack().execute(new RefreshRepresentationsCommand(domain, defaultProgress, diagram));
    }

    private DiagramEditPart doInitGmfDiagram(final Shell shell, final DSemanticDiagram diagram) {
        final Diagram gmfDiag = ViewService.createDiagram(diagram, "Sirius", new PreferencesHint("Sirius"));
        final TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(diagram);
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(domain) {
            /**
             * 
             * {@inheritDoc}
             */
            @Override
            protected void doExecute() {
                diagram.eResource().getContents().add(gmfDiag);
            }

        });
        final DiagramEditPartService tool = new DiagramEditPartService();
        final DiagramEditPart diagramEditPart = tool.createDiagramEditPart(gmfDiag, shell, new PreferencesHint("DView"));
        return diagramEditPart;
    }

    private void doCleanupGmfDiagram(final Shell shell, final DiagramEditPart diagramEditPart) {
        if (diagramEditPart != null) {
            diagramEditPart.deactivate();
        }
        if (shell != null) {
            Display.getCurrent().asyncExec(new Runnable() {
                @Override
                public void run() {
                    shell.dispose();
                }
            });
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
