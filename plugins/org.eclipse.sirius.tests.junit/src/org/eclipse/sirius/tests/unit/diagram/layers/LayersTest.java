/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.layers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.query.DRepresentationDescriptorInternalHelper;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.business.internal.sync.DDiagramSynchronizer;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.tests.support.api.DiagramComponentizationTestSupport;
import org.eclipse.sirius.tools.api.interpreter.InterpreterUtil;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.uml2.uml.NamedElement;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

public class LayersTest extends AbtsractLayerTests {

    /*
     * -> The union of all mappings on all activated layers are taken into
     * account to compute the element the diagram should display -> In the case
     * of a mapping import, the more specific mapping takes precedence over the
     * others. The priority will be computed according to the following rules
     * (in order of priority): The last mapping of the hierarchy (sample :
     * NodeMappingImport "AAA" imports NodeMapping "AA" which in turn imports
     * NodeMapping "A" : the NodeMappingImport "AAA" has the priority, then it
     * will be the NodeMappingImport "AA" and finally the NodeMapping "A")
     * 
     * If two XxxMappingImport have the same priority, a mapping is arbitrary
     * chosen. A validation rule will be added to check conflicts between
     * mappings. -> The elements to display on a diagram are computed in this
     * order:
     * 
     * For each mapping: - The more specific mapping (see previous rule) -
     * compute elements to display and add diagram elements; update actual
     * mapping - for each mapping from the importedMapping tree: -- compute
     * elements minus the elements already displayed by the more specific
     * mappings and add diagram elements; -- update candidateMappings
     * 
     * The elements displayed by a mapping are computed according to the
     * expressions: - semanticCandidatesExpression: expression that returns all
     * semantic elements susceptible to be added to the diagram. This is
     * optional; if not filled, the candidates are the tree of semantic elements
     * owned by the semantic element of the graphical container. -
     * preconditionExpression: expression that filters the semantic candidates
     * out.
     */

    // test LayerHelper -> construction of the tree
    // test LayeHelpeer -> browsing the tree

    private static final String SEMANTIC_MODEL_PATH = "/data/unit/layers/football.uml";

    private static final String MODELER_PATH = "/data/unit/layers/football.odesign";

    private static final String TEST_CLASS_DIAGRAM = "Test class diagram";

    private static final String VIEWPOINT_NAME = "Football with UML2 (Modeler test for layers)";

    @Override
    protected void init() throws Exception {
        genericSetUp(PLUGIN + SEMANTIC_MODEL_PATH, PLUGIN + MODELER_PATH);
        final Viewpoint vp = getViewpointFromName(VIEWPOINT_NAME, session);
        activateViewpoint(vp.getName());
    }

    public void testNodeMappingImportsWithoutSemanticElements() {
        final DiagramDescription classDiag = findDiagramDescription("NodeMappingImport without semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer oldGenerationLayer = findLayer(classDiag, "Old generation");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, oldGenerationLayer);
        final Layer newGenerationLayer = findLayer(classDiag, "New generation");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, newGenerationLayer);

        final NodeMapping defaultMapping = defaultLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final NodeMapping firstMappingImport = oldGenerationLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final NodeMapping secondMappingImport = newGenerationLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        diagram = getRefreshedDiagram();
        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        activateLayer(oldGenerationLayer);
        diagram = getRefreshedDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, firstMappingImport);

        deactivateLayer(oldGenerationLayer);
        activateLayer(newGenerationLayer);
        diagram = getRefreshedDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, secondMappingImport);
    }

    public void testNodeMappingImportsWithSemanticElements() {
        final DiagramDescription classDiag = findDiagramDescription("NodeMappingImport with semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);

        final NodeMapping defaultMapping = defaultLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final NodeMapping firstMappingImport = firstLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final NodeMapping secondMappingImport = secondLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        setLayerVisibility(diagram, firstLayer, true);
        diagram = getRefreshedDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkSemanticMappingImport(diagram, elementsAfter, defaultMapping, firstMappingImport);

        setLayerVisibility(diagram, firstLayer, false);
        setLayerVisibility(diagram, secondLayer, true);
        diagram = getRefreshedDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);

        checkSemanticMappingImport(diagram, elementsAfter, defaultMapping, secondMappingImport);
    }

    public void testNodeMappingImportsWithSemanticElementsAndHideMapping() {
        final DiagramDescription classDiag = findDiagramDescription("NodeMappingImport with semantic elements and hide sub mappings");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);

        final NodeMapping defaultMapping = defaultLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final NodeMapping firstMappingImport = firstLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final NodeMapping secondMappingImport = secondLayer.getNodeMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        activateLayer(firstLayer);
        refreshDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());

        DDiagramElement zidaneElement = findByTargetName(elementsAfter, "Zidane");
        DDiagramElement makeleleElement = findByTargetName(elementsAfter, "Makelele");
        DDiagramElement benzemaElement = findByTargetName(elementsAfter, "Benzema");
        assertFalse("Makele element should not be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, makeleleElement));
        assertFalse("benzema element should not be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, benzemaElement));
        assertTrue("Zidane should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, zidaneElement));
        List<DDiagramElement> visible = new ArrayList<DDiagramElement>(1);
        visible.add(zidaneElement);
        checkMapping(visible, firstMappingImport);

        setLayerVisibility(diagram, firstLayer, false);
        setLayerVisibility(diagram, secondLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        zidaneElement = findByTargetName(elementsAfter, "Zidane");
        makeleleElement = findByTargetName(elementsAfter, "Makelele");
        benzemaElement = findByTargetName(elementsAfter, "Benzema");
        assertFalse("Zidane element not should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, zidaneElement));
        assertFalse("Makelele element should not be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, makeleleElement));
        assertTrue("Benzema should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, benzemaElement));
        visible = new ArrayList<DDiagramElement>(1);
        visible.add(benzemaElement);
        checkMapping(visible, secondMappingImport);
    }

    public void testNodeMappingVisibility() {
        final DiagramDescription classDiag = findDiagramDescription("NodeMapping only");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));

        setLayerVisibility(diagram, findLayer(classDiag, "Makelele"), true);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("We should have 2 node here .", 2, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getSecondElement()));

        setLayerVisibility(diagram, findLayer(classDiag, "Makelele"), false);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("We should have 2 node here one visible and one hidden", 2, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));
        assertFalse("The second element should be hidden", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getSecondElement()));
    }

    public void testContainerMappingImportsWithoutSemanticElements() {
        final DiagramDescription classDiag = findDiagramDescription("ContainerMappingImport without semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());

        selectSirius(getViewpointFromName("Annexe"), session);

        final DiagramDescription annexeDiag = findDiagramDescription("annexeDiag");

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, DiagramComponentizationTestSupport.getAllLayers(session, annexeDiag).size());

        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);

        final ContainerMapping defaultMapping = defaultLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final ContainerMapping firstMappingImport = firstLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final ContainerMapping secondMappingImport = secondLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        setLayerVisibility(diagram, firstLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, firstMappingImport);

        setLayerVisibility(diagram, firstLayer, false);
        setLayerVisibility(diagram, secondLayer, true);
        refreshDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, secondMappingImport);
    }

    private void selectSirius(final Viewpoint selectedSirius, final Session session) {
        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            @Override
            protected void doExecute() {
                selectionCallback.selectViewpoint(selectedSirius, session, new NullProgressMonitor());
            }
        });
    }

    public void testReuseContainerMappingImportsWithoutSemanticElements() {
        final DiagramDescription classDiag = findDiagramDescription("Reuse ContainerMappingImport without semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());

        selectSirius(getViewpointFromName("Annexe"), session);

        final DiagramDescription annexeDiag = findDiagramDescription("annexeDiag");

        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 1, DiagramComponentizationTestSupport.getAllLayers(session, annexeDiag).size());

        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);

        final ContainerMapping defaultMapping = (ContainerMapping) defaultLayer.getReusedMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final ContainerMapping firstMappingImport = (ContainerMapping) firstLayer.getReusedMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final ContainerMapping secondMappingImport = (ContainerMapping) secondLayer.getReusedMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        setLayerVisibility(diagram, firstLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, firstMappingImport);

        setLayerVisibility(diagram, firstLayer, false);
        setLayerVisibility(diagram, secondLayer, true);
        refreshDiagram();
        refreshVisibility(diagram);
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, secondMappingImport);
    }

    public void testContainerMappingImportsWithoutSemanticElementsAndExtension() {
        final DiagramDescription classDiag = findDiagramDescription("ContainerMappingImport without semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());

        selectSirius(getViewpointFromName("Annexe"), session);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 4, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());

        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);
        final Layer extensionLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(3);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, extensionLayer);

        final ContainerMapping defaultMapping = defaultLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final ContainerMapping firstMappingImport = firstLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final ContainerMapping secondMappingImport = secondLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);
        final ContainerMapping extensionMappingImport = extensionLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, extensionMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        activateLayer(firstLayer);
        refreshDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, firstMappingImport);

        deactivateLayer(firstLayer);
        activateLayer(secondLayer);
        refreshDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkMapping(elementsAfter, secondMappingImport);

        deactivateLayer(secondLayer);
        activateLayer(extensionLayer);
        refreshDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        /* 3 for the all default layer and 6 for the extension layer */
        assertEquals("We should have 9 nodes here .", 9, elementsAfter.size());

    }

    public void testContainerMappingImportsWithSemanticElements() {
        final DiagramDescription classDiag = findDiagramDescription("ContainerMappingImport with semantic elements");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        final Layer defaultLayer = classDiag.getDefaultLayer();
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultLayer);
        assertEquals(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, 3, DiagramComponentizationTestSupport.getAllLayers(session, classDiag).size());
        final Layer firstLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(1);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstLayer);
        final Layer secondLayer = DiagramComponentizationTestSupport.getAllLayers(session, classDiag).get(2);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondLayer);

        final ContainerMapping defaultMapping = defaultLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, defaultMapping);
        final ContainerMapping firstMappingImport = firstLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, firstMappingImport);
        final ContainerMapping secondMappingImport = secondLayer.getContainerMappings().get(0);
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, secondMappingImport);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();

        final List<DDiagramElement> elements = new ArrayList<DDiagramElement>(diagram.getOwnedDiagramElements());
        assertEquals("We should have 3 nodes here .", 3, elements.size());

        activateLayer(firstLayer);
        refreshDiagram();
        refreshVisibility(diagram);
        List<DDiagramElement> elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);
        checkSemanticMappingImport(diagram, elementsAfter, defaultMapping, firstMappingImport);

        deactivateLayer(firstLayer);
        activateLayer(secondLayer);
        refreshDiagram();
        elementsAfter = diagram.getOwnedDiagramElements();
        assertEquals("We should have 3 nodes here .", 3, elementsAfter.size());
        compareElements(elements, elementsAfter);

        checkSemanticMappingImport(diagram, elementsAfter, defaultMapping, secondMappingImport);
    }

    private void checkSemanticMappingImport(final DDiagram diagram, final List<DDiagramElement> elements, final AbstractNodeMapping defaultMapping, final AbstractNodeMapping mappingImport) {
        Collection<EObject> semanticCandidates = Collections.emptySet();

        // The interpreter.
        final IInterpreter interpreter = InterpreterUtil.getInterpreter(diagram);
        try {
            if (diagram instanceof DSemanticDiagram)
                semanticCandidates = interpreter.evaluateCollection(((DSemanticDiagram) diagram).getTarget(), mappingImport.getSemanticCandidatesExpression());

        } catch (final EvaluationException e) {
            // do nothing
        }
        for (final DDiagramElement element : elements) {
            if (semanticCandidates.contains(element.getTarget())) {
                assertTrue("the element does not have the right node mapping", EcoreUtil.equals(element.getMapping(), mappingImport));
            } else {
                assertTrue("the element does not have the right node mapping", EcoreUtil.equals(element.getMapping(), defaultMapping));
            }
        }

    }

    public void testContainerMappingVisibility() {
        final DiagramDescription classDiag = findDiagramDescription("ContainerMapping only");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, classDiag);

        initSynchronizer(classDiag, TEST_CLASS_DIAGRAM);
        refreshDiagram();
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());
        setLayerVisibility(diagram, DiagramComponentizationTestSupport.getAllLayers(session, diagram.getDescription()).get(1), true);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("We should have 2 node here .", 2, getNumberOfElementsInDiagram());
        setLayerVisibility(diagram, DiagramComponentizationTestSupport.getAllLayers(session, diagram.getDescription()).get(1), false);
        refreshDiagram();
        refreshVisibility(diagram);
        assertEquals("We should have 2 node here .", 2, getNumberOfElementsInDiagram());
    }

    public void testNodeMappingVisibilityWithCloseAndReopenSession() throws Exception {

        final DiagramDescription diagramDescription = findDiagramDescription("NodeMapping only");
        assertNotNull(THE_UNIT_TEST_DATA_SEEMS_INCORRECT, diagramDescription);

        initSynchronizer(diagramDescription, TEST_CLASS_DIAGRAM);
        refreshDiagram();
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {

            @Override
            protected void doExecute() {
                DRepresentationDescriptorInternalHelper.createDRepresentationDescriptor(sync.getDiagram(), (DAnalysisSessionImpl) session, semanticModel.eResource(), diagramDescription.getName());
            }
        });

        /* save close and reload the session */
        saveCloseAndReloadSession();

        semanticModel = session.getSemanticResources().iterator().next().getContents().iterator().next();

        viewpoints.addAll(loadGroup(PLUGIN + MODELER_PATH));

        final DiagramDescription diagramDescriptionAfterReload = findDiagramDescription("NodeMapping only");

        assertTrue(diagramDescription != diagramDescriptionAfterReload);

        // Get the new diagram instance
        List<DRepresentation> representations = new ArrayList<DRepresentation>(getRepresentations(diagramDescriptionAfterReload.getName(), semanticModel));
        assertEquals(2, representations.size());

        diagram = null;
        for (DRepresentation dRepresentation : representations) {
            if (new DRepresentationQuery(dRepresentation).getRepresentationDescriptor().getName().equalsIgnoreCase(TEST_CLASS_DIAGRAM)) {
                diagram = (DDiagram) dRepresentation;
                break;
            }
        }

        assertNotNull("Diagram " + TEST_CLASS_DIAGRAM + " not found after reload!", diagram);

        /* set the synchronizer diagram description instance to the new one */
        reinitSynchronizer(diagramDescriptionAfterReload);
        /* launch a refresh with the old instance to build the mapping tables */
        refreshDiagram();
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());

        /* set the synchronizer diagram description instance to the old one */
        reinitSynchronizer(diagramDescription);

        /* refresh the diagram and test that we do not create extra nodes */
        refreshDiagram();
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));

        refreshDiagram();
        assertEquals("We should have 1 node here .", 1, getNumberOfElementsInDiagram());
        assertTrue("The first element should be visible", DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, getFirstElement()));
    }

    protected Collection<Viewpoint> loadGroup(final String modelerDescriptionPath) throws Exception {
        Group group = (Group) ModelUtils.load(URI.createPlatformPluginURI(modelerDescriptionPath, true), session.getTransactionalEditingDomain().getResourceSet());
        return group.getOwnedViewpoints();
    }

    private DDiagramElement findByTargetName(final List<DDiagramElement> list, final String targetName) {
        DDiagramElement found = Iterables.find(list, new Predicate<DDiagramElement>() {
            @Override
            public boolean apply(DDiagramElement input) {
                return input.getTarget() instanceof NamedElement && targetName.equals(((NamedElement) input.getTarget()).getName());
            }
        });
        assertNotNull("'" + targetName + "' element not found", found);
        return found;
    }

    private void activateLayer(Layer layer) {
        setLayerVisibility(diagram, layer, true);
    }

    private void deactivateLayer(Layer layer) {
        setLayerVisibility(diagram, layer, false);
    }

    private static void checkMapping(final List<DDiagramElement> elements, final AbstractNodeMapping firstMappingImport) {
        for (final DDiagramElement element : elements) {
            assertTrue("the element does not have the right node mapping", EcoreUtil.equals(element.getMapping(), firstMappingImport));
        }
    }

    private DDiagramElement getSecondElement() {
        return diagram.getOwnedDiagramElements().get(1);
    }

    protected void reinitSynchronizer(final DiagramDescription description) {
        sync = new DDiagramSynchronizer(interpreter, description, accessor);
        sync.setDiagram((DSemanticDiagram) diagram);
    }

    private void saveCloseAndReloadSession() throws Exception {

        URI sessionResourceURI = session.getSessionResource().getURI();

        /* save session -> useful to invalidate mappings table cache */
        session.save(new NullProgressMonitor());

        /* close session */
        closeSession(session);
        viewpoints.clear();

        /* reload session */
        session = SessionFactory.INSTANCE.createSession(sessionResourceURI, new NullProgressMonitor());
        assertNotNull(session);
        session.open(new NullProgressMonitor());
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
