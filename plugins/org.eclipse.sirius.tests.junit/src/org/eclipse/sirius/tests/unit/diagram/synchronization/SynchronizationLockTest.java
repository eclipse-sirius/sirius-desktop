/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.diagram.synchronization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.sirius.diagram.AbstractDNode;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeContainer;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.tools.internal.preferences.SiriusDiagramInternalPreferencesKeys;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.preferences.SiriusUIPreferencesKeys;
import org.eclipse.ui.IEditorPart;

public class SynchronizationLockTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/synchronization/trac2065.ecore";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/synchronization/trac2065.odesign";

    private static final String VIEWPOINT_NAME = "trac2065";

    private static final String REPRESENTATION_DESC_NAME1 = "Diagram (All Create=true)";

    private static final String REPRESENTATION_DESC_NAME2 = "Diagram (First Level Create=false, Sub Create=true, Edges Create=true)";

    private static final String REPRESENTATION_DESC_NAME3 = "Diagram (First Level Create=true, Sub Create=false, Edges Create=true)";

    private static final String REPRESENTATION_DESC_NAME4 = "Diagram (First Level Create=false, Sub Create=true, Edges Create=false)";

    private DDiagram diagram;

    private IEditorPart editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_RELOAD_ON_LAST_EDITOR_CLOSE.name(), false);
        changeSiriusUIPreference(SiriusUIPreferencesKeys.PREF_SAVE_WHEN_NO_EDITOR.name(), false);
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Test method. 7
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAllTrueSynchroMode() throws Exception {

        // Synchronization mode = true
        // All mappings with create=true
        // No mapping with Synchronization Lock
        // Create diagram

        // => Result: All the elements (and sub-elements + edges) have to be
        // displayed during the creation of the diagram.

        diagram = createDiagram(REPRESENTATION_DESC_NAME1);

        assertTrue("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAllTrueSynchroMode();

    }

    private void checkAllTrueSynchroMode() {
        assertEquals("All the elements have to be visible", 36, getVisibleElements(diagram.getDiagramElements()).size());
    }

    /**
     * Test method. 8
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAllTrueSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = true
        // All mappings with create=true
        // Node, List Container, Container, Border node mappings with
        // Synchronization Lock
        // Create diagram

        // => Result: Same result than testAllTrueSynchroMode
        // (All the elements (and sub-elements + edges) have to be displayed
        // during the creation of the diagram)

        switchSynchronizationLockOnAllKindsMappings1Root(true, REPRESENTATION_DESC_NAME1);

        testAllTrueSynchroMode();

    }

    /**
     * Test method. 3
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAllTrueNotSynchroMode() throws Exception {

        // Synchronization mode = false
        // All mappings with create=true
        // No mapping with Synchronization Lock
        // Create diagram

        // => Result: No element has to be displayed during the creation of the
        // diagram.

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME1);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAllTrueNotSynchroMode();

    }

    private void checkAllTrueNotSynchroMode() {
        assertEquals("No element has to be visible", 0, getVisibleElements(diagram.getDiagramElements()).size());
    }

    /**
     * Test method. 4
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAllTrueNotSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = false
        // All mappings with create=true
        // Node, List Container, Container, Border node mappings with
        // Synchronization Lock
        // Create diagram

        // => Result: Only the elements with synchronization lock have to be
        // displayed during the creation of the diagram.
        // (n1*, n2*, lc2*, c1*, n5*, n6*, bn1, bn2, bn3, bn5)

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        switchSynchronizationLockOnAllKindsMappings1Root(true, REPRESENTATION_DESC_NAME1);

        diagram = createDiagram(REPRESENTATION_DESC_NAME1);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAllTrueNotSynchroModeWithMappingsInSynchroLock();

    }

    private void checkAllTrueNotSynchroModeWithMappingsInSynchroLock() {
        assertEquals("Only mappings in Synchro lock have to be displayed", 10, getVisibleElements(diagram.getDiagramElements()).size());
        assertEquals("Only mappings in Synchro lock have to be displayed", 6, getVisibleElements(diagram.getOwnedDiagramElements()).size());

        DDiagramElement container = getDiagramElementsFromLabel(diagram, "c1*").get(0);
        assertTrue("The Container has not to own elements", container instanceof DNodeContainer && getVisibleElements(((DNodeContainer) container).getOwnedDiagramElements()).size() == 0);
        assertTrue("The Container has to own bordered nodes", container instanceof DNodeContainer && getVisibleElements(((DNodeContainer) container).getOwnedBorderedNodes()).size() == 3);

        DDiagramElement listContainer = getDiagramElementsFromLabel(diagram, "lc2*").get(0);
        assertTrue("The ListContainer has not to own elements", listContainer instanceof DNodeList && getVisibleElements(((DNodeList) listContainer).getOwnedElements()).size() == 0);

        DDiagramElement node = getDiagramElementsFromLabel(diagram, "n1*").get(0);
        assertTrue("The node has to own bordered nodes", node instanceof DNode && getVisibleElements(((DNode) node).getOwnedBorderedNodes()).size() == 1);
    }

    /**
     * Test method. 5
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRootElementsFalseSynchroMode() throws Exception {

        // Synchronization mode = true
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Create diagram

        // => Result: No element has to be displayed during the creation of the
        // diagram (like testAllTrueNotSynchroMode)

        diagram = createDiagram(REPRESENTATION_DESC_NAME2);

        assertTrue("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAllTrueNotSynchroMode();

    }

    /**
     * Test method. 6
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRootElementsFalseSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = true
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // Node, List Container, Container, Border node mappings with
        // Synchronization Lock
        // Create diagram

        // => Result: Same result than testRootElementsFalseSynchroMode.
        // (No element has to be displayed during the creation of the diagram
        // (like testAllTrueNotSynchroMode))

        switchSynchronizationLockOnAllKindsMappings2Root(true, REPRESENTATION_DESC_NAME2);

        testRootElementsFalseSynchroMode();

    }

    /**
     * Test method. 1
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRootElementsFalseNotSynchroMode() throws Exception {

        // Synchronization mode = false
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Create diagram

        // => Result: Same result than testRootElementsFalseSynchroMode.
        // (No element has to be displayed during the creation of the diagram
        // (like testAllTrueNotSynchroMode))

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME2);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAllTrueNotSynchroMode();

    }

    /**
     * Test method. 2
     * 
     * @throws Exception
     *             Test error.
     */
    public void testRootElementsFalseNotSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = true
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // Node, List Container, Container, Border node mappings with
        // Synchronization Lock
        // Create diagram

        // => Result: Same result than testRootElementsFalseSynchroMode.
        // (No element has to be displayed during the creation of the diagram
        // (like testAllTrueNotSynchroMode))

        switchSynchronizationLockOnAllKindsMappings2Root(true, REPRESENTATION_DESC_NAME2);

        testRootElementsFalseNotSynchroMode();

    }

    /**
     * Test method. A
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseSynchroMode() throws Exception {

        // Synchronization mode = true
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: The root elements have to be displayed + its contents and
        // edges.

        // Checkings in comments have to replace the other ones (or just to be
        // uncommented) when the trac #2047 will be integrated.

        diagram = createDiagram(REPRESENTATION_DESC_NAME2);

        checkAddRootElementsFalseSynchroMode(true);

    }

    private void checkAddRootElementsFalseSynchroMode(boolean edges) {
        EPackage root = (EPackage) semanticModel;

        EObject n1 = root.getEClassifier("n1*");
        dropSemantic(n1, diagram, null);
        checkNumberOfBorderedNodes(diagram, n1, 1);

        EObject n2 = root.getEClassifier("n2*");
        dropSemantic(n2, diagram, null);
        if (edges)
            checkNumberOfEdges(diagram, n2, 1, true);
        else
            checkNumberOfEdges(diagram, n2, 0, true);

        EObject lc2 = root.getEClassifier("lc2*");
        dropSemantic(lc2, diagram, null);
        checkNumberOfListElements(diagram, lc2, 3);
        if (edges)
            checkNumberOfEdges(diagram, lc2, 1, true);
        else
            checkNumberOfEdges(diagram, lc2, 0, true);

        EObject c1 = root.getEClassifier("c1*");
        dropSemantic(c1, diagram, null);
        checkNumberOfBorderedNodes(diagram, c1, 3);
        checkNumberOfNodes(diagram, c1, 6);
        // checkNumberOfContainers(diagram, c1, 1);

        if (edges) {
            checkNumberOfEdges(diagram, c1, 2, false);
            // checkNumberOfInternalEdges(diagram, c1, 7);
            checkNumberOfInternalEdges(diagram, c1, 4);
            // checkNumberOfOutgoingEdgesInside(diagram, c1, 3);
            checkNumberOfOutgoingEdgesInside(diagram, c1, 2);
            checkNumberOfIncomingEdgesInside(diagram, c1, 1);
        } else {
            checkNumberOfEdges(diagram, c1, 0, false);
            checkNumberOfInternalEdges(diagram, c1, 0);
            checkNumberOfOutgoingEdgesInside(diagram, c1, 0);
            checkNumberOfIncomingEdgesInside(diagram, c1, 0);
        }
    }

    /**
     * Test method. B
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = true
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: Same result than testAddRootElementsFalseSynchroMode.
        // (The root elements have to be displayed + its contents and edges)

        switchSynchronizationLockOnAllKindsMappings2Sub(true, REPRESENTATION_DESC_NAME2);

        testAddRootElementsFalseSynchroMode();

    }

    /**
     * Test method. C
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseNotSynchroMode() throws Exception {

        // Synchronization mode = false
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: The root elements have to be displayed but not its
        // contents neither the edges

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME2);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAddRootElementsFalseNotSynchroMode();

    }

    /**
     * Test method. D
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseNotSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = false
        // All root mappings with create=false, sub mapping and edges with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: The root elements have to be displayed and its contents
        // but not the edges

        switchSynchronizationLockOnAllKindsMappings2Sub(true, REPRESENTATION_DESC_NAME2);

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME2);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAddRootElementsFalseNotSynchroModeWithMappingsInSynchroLock();

    }

    /**
     * Test method. E
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddSubElementsFalseSynchroMode() throws Exception {

        // Synchronization mode = true
        // All sub mappings with create=false, root mappings and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Add the sub elements on the container elements: bn1, n3, n4, bn4, n7,
        // c3, bn2, bn5

        // => Result: The sub elements have to be displayed and the edges

        // Checkings in comments have to replace the other ones (or just to be
        // uncommented) when the trac #2047 will be integrated.

        diagram = createDiagram(REPRESENTATION_DESC_NAME3);

        assertTrue("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAddSubElementsFalseSynchroMode();

    }

    /**
     * Test method. F
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddSubElementsFalseSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = true
        // All sub mappings with create=false, root mappings and edges with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the sub elements on the container elements: bn1, n3, n4, bn4, n7,
        // c3, bn2, bn5

        // => Result: Same result than testAddRootElementsFalseSynchroMode.
        // (The sub elements have to be displayed and the edges)

        switchSynchronizationLockOnAllKindsMappings3Sub(true, REPRESENTATION_DESC_NAME3);

        testAddSubElementsFalseSynchroMode();

    }

    /**
     * Test method. G
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddSubElementsFalseNotSynchroMode() throws Exception {

        // Synchronization mode = false
        // All sub mappings with create=false, root mappings and edges with
        // create=true
        // No mapping with Synchronization Lock
        // Add the root elements and the sub elements on the container elements:
        // bn1, n3, n4, bn4, n7, c3, bn2, bn5

        // => Result: The sub elements have to be displayed but not the edges.

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME3);

        assertFalse("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAddSubElementsFalseNotSynchroMode(false);

    }

    /**
     * Test method. H
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddSubElementsFalseNotSynchroModeWithMappingsInSynchroLock() throws Exception {

        // Synchronization mode = false
        // All sub mappings with create=false, root mappings and edges with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the root elements and the sub elements on the container elements:
        // bn1, n3, n4, bn4, n7, c3, bn2, bn5

        // => Result: Same result than testAddSubElementsFalseNotSynchroMode.
        // (The sub elements have to be displayed but not the edges)

        switchSynchronizationLockOnAllKindsMappings3Sub(true, REPRESENTATION_DESC_NAME3);

        // testAddSubElementsFalseNotSynchroMode();

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME3);

        assertFalse("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAddSubElementsFalseNotSynchroMode(true);

    }

    /**
     * Test method. M
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddSubElementsFalseNotSynchroModeWithEdgesInSynchroLock() throws Exception {

        // Synchronization mode = false
        // All sub mappings with create=false, root mappings and edges with
        // create=true
        // Edge mapping between nodes and bordered nodes with Synchronization
        // Lock
        // Add the root elements and the sub elements on the container elements:
        // bn1, n3, n4, bn4, n7, c3, bn2, bn5

        // => Result: Same result than testAddSubElementsFalseNotSynchroMode but
        // with edges between bn and n.
        // (The sub elements have to be displayed and only the edges between
        // node and bordered nodes)

        switchSynchronizationLockOnAllKindsMappings3Edges(true, REPRESENTATION_DESC_NAME3);

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME3);

        assertFalse("This DDiagram should be synchronized.", diagram.isSynchronized());

        checkAddSubElementsFalseNotSynchroModeWithEdgesInSynchroLock();

    }

    /**
     * Test method. I
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseSynchroModeEdgesFalse() throws Exception {

        // Synchronization mode = true
        // All root mappings and edges with create=false, sub mapping with
        // create=true
        // No mapping with Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: The root elements have to be displayed + its contents but
        // not edges.

        diagram = createDiagram(REPRESENTATION_DESC_NAME4);

        checkAddRootElementsFalseSynchroMode(false);

    }

    /**
     * Test method. J
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseSynchroModeWithMappingsInSynchroLockEdgesFalse() throws Exception {

        // Synchronization mode = true
        // All root mappings and edges with create=false, sub mapping with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: Same result than
        // testAddRootElementsFalseSynchroModeEdgesFalse
        // (The root elements have to be displayed + its contents but not edges)

        switchSynchronizationLockOnAllKindsMappings4Sub(true, REPRESENTATION_DESC_NAME4);

        testAddRootElementsFalseSynchroMode();

    }

    /**
     * Test method. K
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseNotSynchroModeEdgesFalse() throws Exception {

        // Synchronization mode = false
        // All root mappings and edges with create=false, sub mapping with
        // create=true
        // No mapping with Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: The root elements have to be displayed but not its
        // contents neither the edges

        changeDiagramPreference(SiriusDiagramInternalPreferencesKeys.PREF_SYNCHRONIZE_DIAGRAM_ON_CREATION.name(), false);

        diagram = createDiagram(REPRESENTATION_DESC_NAME4);

        assertFalse("This DDiagram should not be synchronized.", diagram.isSynchronized());

        checkAddRootElementsFalseNotSynchroMode();

    }

    /**
     * Test method. L
     * 
     * @throws Exception
     *             Test error.
     */
    public void testAddRootElementsFalseNotSynchroModeWithMappingsInSynchroLockEdgesFalse() throws Exception {

        // Synchronization mode = false
        // All root mappings and edges with create=false, sub mapping with
        // create=true
        // Border node mappings, Sub node and container mapping with
        // Synchronization Lock
        // Add the root elements on the empty diagram: n1*, n2*, lc2*, c1*

        // => Result: Same result than
        // testAddRootElementsFalseSynchroModeEdgesFalse
        // (The root elements have to be displayed + its contents but not edges)

        switchSynchronizationLockOnAllKindsMappings4Sub(true, REPRESENTATION_DESC_NAME4);

        testAddRootElementsFalseSynchroModeEdgesFalse();

    }

    private void checkAddSubElementsFalseNotSynchroModeWithEdgesInSynchroLock() {

        EPackage root = (EPackage) semanticModel;

        assertEquals("No element has to be visible", 0, getVisibleElements(diagram.getDiagramElements()).size());

        EObject n1 = root.getEClassifier("n1*");
        EObject n2 = root.getEClassifier("n2*");
        EObject lc2 = root.getEClassifier("lc2*");
        EObject c1 = root.getEClassifier("c1*");
        EObject n5 = root.getEClassifier("n5*");
        EObject n6 = root.getEClassifier("n6*");

        dropSemantic(n1, diagram, null);
        dropSemantic(n2, diagram, null);
        dropSemantic(lc2, diagram, null);
        dropSemantic(c1, diagram, null);
        dropSemantic(n5, diagram, null);
        dropSemantic(n6, diagram, null);

        DNodeContainer containerC1 = getFirstRepresentationElement(diagram, c1, DNodeContainer.class);

        EObject bn1 = root.getEClassifier("bn1");
        dropSemantic(bn1, containerC1, null);
        checkNumberOfIncomingEdgesInside(diagram, c1, 1);
        checkNumberOfInternalEdges(diagram, c1, 0);

        EObject n3 = root.getEClassifier("n3");
        dropSemantic(n3, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 1);

        EObject n4 = root.getEClassifier("n4");
        dropSemantic(n4, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 1);
        checkNumberOfIncomingEdgesInside(diagram, c1, 1);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 0);

        EObject bn4 = root.getEClassifier("bn4");
        DNode containerN3 = getFirstRepresentationElement(diagram, n3, DNode.class);
        dropSemantic(bn4, containerN3, null);
        checkNumberOfInternalEdges(diagram, c1, 1);
        checkNumberOfIncomingEdgesInside(diagram, c1, 2);

        EObject bn2 = root.getEClassifier("bn2");
        dropSemantic(bn2, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 2);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 1);

        EObject bn5 = root.getEClassifier("bn5");
        dropSemantic(bn5, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 2);

    }

    private void checkAddSubElementsFalseSynchroMode() {

        EPackage root = (EPackage) semanticModel;

        // assertEquals("All the root elements and edges have to be visible",
        // 10, getVisibleElements(diagram.getDiagramElements()).size());
        assertEquals("All the root elements and edges have to be visible", 13, getVisibleElements(diagram.getDiagramElements()).size());

        EObject c1 = root.getEClassifier("c1*");
        DNodeContainer containerC1 = getFirstRepresentationElement(diagram, c1, DNodeContainer.class);

        EObject bn1 = root.getEClassifier("bn1");
        dropSemantic(bn1, containerC1, null);
        TestsUtil.synchronizationWithUIThread();
        checkNumberOfBorderedNodes(diagram, c1, 1);
        checkNumberOfNodes(diagram, c1, 1);
        // checkNumberOfContainers(diagram, c1, 0);
        checkNumberOfContainers(diagram, c1, 1);
        // checkNumberOfIncomingEdgesInside(diagram, c1, 1);
        checkNumberOfIncomingEdgesInside(diagram, c1, 2);
        checkNumberOfInternalEdges(diagram, c1, 0);

        EObject n3 = root.getEClassifier("n3");
        dropSemantic(n3, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 1);

        EObject n4 = root.getEClassifier("n4");
        dropSemantic(n4, containerC1, null);
        // checkNumberOfInternalEdges(diagram, c1, 2);
        checkNumberOfInternalEdges(diagram, c1, 3);
        // checkNumberOfIncomingEdgesInside(diagram, c1, 2);
        checkNumberOfIncomingEdgesInside(diagram, c1, 3);
        // checkNumberOfOutgoingEdgesInside(diagram, c1, 1);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 2);

        EObject bn4 = root.getEClassifier("bn4");
        DNode containerN3 = getFirstRepresentationElement(diagram, n3, DNode.class);
        dropSemantic(bn4, containerN3, null);
        // checkNumberOfInternalEdges(diagram, c1, 3);
        checkNumberOfInternalEdges(diagram, c1, 4);
        // checkNumberOfIncomingEdgesInside(diagram, c1, 3);
        checkNumberOfIncomingEdgesInside(diagram, c1, 4);

        EObject n7 = root.getEClassifier("n7");
        EObject lc2 = root.getEClassifier("lc2*");
        DNodeList containerLc2 = getFirstRepresentationElement(diagram, lc2, DNodeList.class);
        dropSemantic(n7, containerLc2, null);
        checkAddedElement(n7);

        // EObject c3 = root.getEClassifier("c3");
        // dropSemantic(c3, containerC1, null);
        // checkNumberOfInternalEdges(diagram, c1, 6);
        // checkNumberOfIncomingEdgesInside(diagram, c1, 4);
        // checkNumberOfOutgoingEdgesInside(diagram, c1, 2);

        EObject bn2 = root.getEClassifier("bn2");
        dropSemantic(bn2, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 6);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 4);

        EObject bn5 = root.getEClassifier("bn5");
        dropSemantic(bn5, containerC1, null);
        checkNumberOfInternalEdges(diagram, c1, 7);

    }

    private void checkAddSubElementsFalseNotSynchroMode(boolean withSynchroLock) {

        // withSynchroLock has to be deleted when the trac #2047 will be
        // integrated (-> !withSynchroLock)

        EPackage root = (EPackage) semanticModel;

        assertEquals("No element has to be visible", 0, getVisibleElements(diagram.getDiagramElements()).size());

        EObject n1 = root.getEClassifier("n1*");
        EObject n2 = root.getEClassifier("n2*");
        EObject lc2 = root.getEClassifier("lc2*");
        EObject c1 = root.getEClassifier("c1*");
        EObject n5 = root.getEClassifier("n5*");
        EObject n6 = root.getEClassifier("n6*");

        dropSemantic(n1, diagram, null);
        dropSemantic(n2, diagram, null);
        dropSemantic(lc2, diagram, null);
        dropSemantic(c1, diagram, null);
        dropSemantic(n5, diagram, null);
        dropSemantic(n6, diagram, null);

        if (!withSynchroLock) {
            assertEquals("All the root elements have to be visible without edges", 6, getVisibleElements(diagram.getDiagramElements()).size());
        } else {
            assertEquals("All the root elements have to be visible without edges", 7, getVisibleElements(diagram.getDiagramElements()).size());
        }

        DNodeContainer containerC1 = getFirstRepresentationElement(diagram, c1, DNodeContainer.class);

        EObject bn1 = root.getEClassifier("bn1");
        dropSemantic(bn1, containerC1, null);
        checkNumberOfBorderedNodes(diagram, c1, 1);
        checkNumberOfNodes(diagram, c1, 1);
        if (!withSynchroLock) {
            checkNumberOfContainers(diagram, c1, 0);
        } else {
            checkNumberOfContainers(diagram, c1, 1);
        }
        checkNumberOfIncomingEdgesInside(diagram, c1, 0);
        checkNumberOfInternalEdges(diagram, c1, 0);

        EObject n3 = root.getEClassifier("n3");
        dropSemantic(n3, containerC1, null);
        checkAddedElement(n3);
        checkNumberOfInternalEdges(diagram, c1, 0);

        EObject n4 = root.getEClassifier("n4");
        dropSemantic(n4, containerC1, null);
        checkAddedElement(n4);
        checkNumberOfInternalEdges(diagram, c1, 0);
        checkNumberOfIncomingEdgesInside(diagram, c1, 0);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 0);

        EObject bn4 = root.getEClassifier("bn4");
        DNode containerN3 = getFirstRepresentationElement(diagram, n3, DNode.class);
        dropSemantic(bn4, containerN3, null);
        checkAddedElement(bn4);
        checkNumberOfInternalEdges(diagram, c1, 0);
        checkNumberOfIncomingEdgesInside(diagram, c1, 0);

        EObject n7 = root.getEClassifier("n7");
        DNodeList containerLc2 = getFirstRepresentationElement(diagram, lc2, DNodeList.class);
        dropSemantic(n7, containerLc2, null);
        checkAddedElement(n7);

        if (!withSynchroLock) {
            EObject c3 = root.getEClassifier("c3");
            dropSemantic(c3, containerC1, null);
            checkAddedElement(c3);
            checkNumberOfInternalEdges(diagram, c1, 0);
            checkNumberOfIncomingEdgesInside(diagram, c1, 0);
            checkNumberOfOutgoingEdgesInside(diagram, c1, 0);
        }

        EObject bn2 = root.getEClassifier("bn2");
        dropSemantic(bn2, containerC1, null);
        checkAddedElement(bn2);
        checkNumberOfInternalEdges(diagram, c1, 0);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 0);

        EObject bn5 = root.getEClassifier("bn5");
        dropSemantic(bn5, containerC1, null);
        checkAddedElement(bn5);
        checkNumberOfInternalEdges(diagram, c1, 0);

    }

    private void checkAddRootElementsFalseNotSynchroMode() {

        EPackage root = (EPackage) semanticModel;

        EObject n1 = root.getEClassifier("n1*");
        dropSemantic(n1, diagram, null);
        checkNumberOfBorderedNodes(diagram, n1, 0);

        EObject n2 = root.getEClassifier("n2*");
        dropSemantic(n2, diagram, null);
        checkNumberOfEdges(diagram, n2, 0, true);

        EObject lc2 = root.getEClassifier("lc2*");
        dropSemantic(lc2, diagram, null);
        checkNumberOfListElements(diagram, lc2, 0);
        checkNumberOfEdges(diagram, lc2, 0, true);

        EObject c1 = root.getEClassifier("c1*");
        dropSemantic(c1, diagram, null);
        checkNumberOfBorderedNodes(diagram, c1, 0);
        checkNumberOfNodes(diagram, c1, 0);
        checkNumberOfContainers(diagram, c1, 0);

        checkNumberOfEdges(diagram, c1, 0, false);
        checkNumberOfInternalEdges(diagram, c1, 0);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 0);
        checkNumberOfIncomingEdgesInside(diagram, c1, 0);
    }

    private void checkAddRootElementsFalseNotSynchroModeWithMappingsInSynchroLock() {

        EPackage root = (EPackage) semanticModel;

        EObject n1 = root.getEClassifier("n1*");
        dropSemantic(n1, diagram, null);
        checkNumberOfBorderedNodes(diagram, n1, 1);

        EObject n2 = root.getEClassifier("n2*");
        dropSemantic(n2, diagram, null);
        checkNumberOfEdges(diagram, n2, 0, true);

        EObject lc2 = root.getEClassifier("lc2*");
        dropSemantic(lc2, diagram, null);
        checkNumberOfListElements(diagram, lc2, 3);
        checkNumberOfEdges(diagram, lc2, 0, true);

        EObject c1 = root.getEClassifier("c1*");
        dropSemantic(c1, diagram, null);
        checkNumberOfBorderedNodes(diagram, c1, 3);
        checkNumberOfNodes(diagram, c1, 6);
        // checkNumberOfContainers(diagram, c1, 1);

        checkNumberOfEdges(diagram, c1, 0, false);
        checkNumberOfInternalEdges(diagram, c1, 0);
        checkNumberOfOutgoingEdgesInside(diagram, c1, 0);
        checkNumberOfIncomingEdgesInside(diagram, c1, 0);
    }

    private void checkAddedElement(DDiagramElement element) {
        assertTrue("The dropped element is not visible", element.isVisible());
    }

    private void checkAddedElement(EObject element) {
        DDiagramElement elt = getFirstRepresentationElement(diagram, element, AbstractDNode.class);
        checkAddedElement(elt);
    }

    private void checkNumberOfBorderedNodes(DDiagram diagram, EObject element, int expectedNumber) {
        List<DNode> borderNodes = new ArrayList<DNode>();
        DDiagramElement elt = getFirstRepresentationElement(diagram, element, AbstractDNode.class);
        if (elt instanceof AbstractDNode) {
            borderNodes.addAll(((AbstractDNode) elt).getOwnedBorderedNodes());
        }
        checkAddedElement(elt);
        assertTrue("The element has to own border nodes", getVisibleElements(borderNodes).size() == expectedNumber);
    }

    private void checkNumberOfNodes(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeContainer elt = getFirstRepresentationElement(diagram, element, DNodeContainer.class);
        checkAddedElement(elt);
        assertTrue("The container has to own nodes", getVisibleElements(elt.getNodes()).size() == expectedNumber);
    }

    private void checkNumberOfContainers(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeContainer elt = getFirstRepresentationElement(diagram, element, DNodeContainer.class);
        checkAddedElement(elt);
        assertTrue("The container has to own containers", getVisibleElements(elt.getContainers()).size() == expectedNumber);
    }

    private void checkNumberOfEdges(DDiagram diagram, EObject element, int expectedNumber, boolean incoming) {
        List<DEdge> edges = new ArrayList<DEdge>();
        DDiagramElement elt = getFirstRepresentationElement(diagram, element, AbstractDNode.class);
        if (elt instanceof DNode) {
            edges.addAll(getEdges((DNode) elt, incoming));
        } else if (elt instanceof DNodeList) {
            edges.addAll(getEdges((DNodeList) elt, incoming));
        } else if (elt instanceof DNodeContainer) {
            edges.addAll(getEdges((DNodeContainer) elt, incoming));
        }
        checkAddedElement(elt);
        assertTrue("The number of edges is not good", getVisibleElements(edges).size() == expectedNumber);
    }

    private List<DEdge> getEdges(DNode node, boolean incoming) {
        if (incoming) {
            return node.getIncomingEdges();
        } else {
            return node.getOutgoingEdges();
        }
    }

    private List<DEdge> getEdges(DNodeList node, boolean incoming) {
        if (incoming) {
            return node.getIncomingEdges();
        } else {
            return node.getOutgoingEdges();
        }
    }

    private List<DEdge> getEdges(DNodeContainer node, boolean incoming) {
        if (incoming) {
            return node.getIncomingEdges();
        } else {
            return node.getOutgoingEdges();
        }
    }

    private void checkNumberOfListElements(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeList containerList = getFirstRepresentationElement(diagram, element, DNodeList.class);
        checkAddedElement(containerList);
        assertTrue("The list container has to be own elements", getVisibleElements(containerList.getOwnedElements()).size() == expectedNumber);
    }

    private void checkNumberOfInternalEdges(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeContainer container = getFirstRepresentationElement(diagram, element, DNodeContainer.class);
        List<DDiagramElement> contents = getAllVisibleElementsContent(container);
        assertEquals("Number of internal edges incorrect", getNumberOfInternalEdges(contents), expectedNumber);
    }

    private void checkNumberOfIncomingEdgesInside(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeContainer container = getFirstRepresentationElement(diagram, element, DNodeContainer.class);
        List<DDiagramElement> contents = getAllVisibleElementsContent(container);
        assertEquals("Number of incoming edges inside incorrect", getNumberOfIncomingEdges(contents), expectedNumber);
    }

    private void checkNumberOfOutgoingEdgesInside(DDiagram diagram, EObject element, int expectedNumber) {
        DNodeContainer container = getFirstRepresentationElement(diagram, element, DNodeContainer.class);
        List<DDiagramElement> contents = getAllVisibleElementsContent(container);
        assertEquals("Number of outgoing edges from inside incorrect", getNumberOfOutgoingEdges(contents), expectedNumber);
    }

    private void switchSynchronizationLockOnAllKindsMappings1Root(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default1"), "Nodes (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default1"), "Containers (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default1"), "List containers (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default1"), "Border nodes (create=true)"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void switchSynchronizationLockOnAllKindsMappings2Root(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Nodes (create=false)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Containers (create=false)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "List containers (create=false)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Border nodes (create=true)"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void switchSynchronizationLockOnAllKindsMappings2Sub(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Border nodes (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Nodes in containers (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default2"), "Containers in containers (create=true)"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void switchSynchronizationLockOnAllKindsMappings3Sub(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default3"), "Border nodes (create=false)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default3"), "Nodes in containers (create=false)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default3"), "Containers in containers (create=false)"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void switchSynchronizationLockOnAllKindsMappings4Sub(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default4"), "Border nodes (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default4"), "Nodes in containers (create=true)"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default4"), "Containers in containers (create=true)"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void switchSynchronizationLockOnAllKindsMappings3Edges(final boolean option, String representationDescName) {

        DDiagram defaultDiagram = (DDiagram) createRepresentation(representationDescName, semanticModel);

        List<DiagramElementMapping> mappings = new ArrayList<DiagramElementMapping>();
        mappings.add(getMapping(getLayer(defaultDiagram, "Default3"), "Nodes -> Border nodes"));
        mappings.add(getMapping(getLayer(defaultDiagram, "Default3"), "Border nodes -> Nodes"));

        switchSynchronizationLock(mappings.iterator(), option);

    }

    private void initSynchronizationLockOnAllMappings(final boolean option) {
        DDiagram defaultDiagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME1, semanticModel);
        switchSynchronizationLock(getAllMappings(getLayer(defaultDiagram, "Default1")).iterator(), option);
    }

    private void switchSynchronizationLock(final Iterator<DiagramElementMapping> mappings, final boolean option) {

        session.getTransactionalEditingDomain().getCommandStack().execute(new RecordingCommand(session.getTransactionalEditingDomain()) {
            /** {@inheritDoc} */
            @Override
            protected void doExecute() {

                while (mappings.hasNext()) {
                    mappings.next().setSynchronizationLock(option);
                }

            }

        });

    }

    private DiagramElementMapping getMapping(Layer layer, String name) {
        Iterator<EObject> it = layer.eAllContents();
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof DiagramElementMapping && ((DiagramElementMapping) obj).getName().equals(name))
                return (DiagramElementMapping) obj;
        }
        return null;
    }

    private List<DiagramElementMapping> getAllMappings(Layer layer) {
        List<DiagramElementMapping> result = new ArrayList<DiagramElementMapping>();

        Iterator<EObject> it = layer.eResource().getAllContents();
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof DiagramElementMapping)
                result.add((DiagramElementMapping) obj);
        }
        return result;
    }

    private List<DDiagramElement> getVisibleElements(List<? extends DDiagramElement> elements) {
        List<DDiagramElement> result = new ArrayList<DDiagramElement>();
        Iterator<? extends DDiagramElement> it = elements.iterator();
        while (it.hasNext()) {
            DDiagramElement element = it.next();
            if (element.isVisible())
                result.add(element);
        }
        return result;
    }

    private DDiagram createDiagram(String diagramName) {
        DDiagram diagram = (DDiagram) createRepresentation(diagramName, semanticModel);
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        return diagram;
    }

    private List<DDiagramElement> getAllVisibleElementsContent(DNodeContainer c) {
        List<DDiagramElement> contents = new ArrayList<DDiagramElement>();
        Iterator<EObject> it = c.eAllContents();
        while (it.hasNext()) {
            EObject obj = it.next();
            if (obj instanceof DDiagramElement && ((DDiagramElement) obj).isVisible()) {
                contents.add((DDiagramElement) obj);
            }
        }
        return contents;
    }

    private int getNumberOfInternalEdges(List<DDiagramElement> internalElements) {
        int nbInternalEdges = 0;
        Iterator<DDiagramElement> it = internalElements.iterator();
        while (it.hasNext()) {
            DDiagramElement element = it.next();
            if (element instanceof DNode) {
                DNode node = (DNode) element;
                nbInternalEdges += getNumberOfInternalEdges(node.getOutgoingEdges(), internalElements);
            } else if (element instanceof DNodeContainer) {
                DNodeContainer container = (DNodeContainer) element;
                nbInternalEdges += getNumberOfInternalEdges(container.getOutgoingEdges(), internalElements);
            }
        }
        return nbInternalEdges;
    }

    private int getNumberOfInternalEdges(List<DEdge> edges, List<DDiagramElement> internalElements) {
        int nbInternalEdges = 0;
        Iterator<?> it = getVisibleElements(edges).iterator();
        while (it.hasNext()) {
            DEdge edge = (DEdge) it.next();
            if (internalElements.contains(edge.getTargetNode())) {
                nbInternalEdges++;
            }
        }
        return nbInternalEdges;
    }

    private int getNumberOfIncomingEdges(List<DDiagramElement> internalElements) {
        int nbExternalEdges = 0;
        Iterator<DDiagramElement> it = internalElements.iterator();
        while (it.hasNext()) {
            DDiagramElement element = it.next();
            if (element instanceof DNode) {
                DNode node = (DNode) element;
                nbExternalEdges += getNumberOfExternalEdges(node.getIncomingEdges(), internalElements, true);
            } else if (element instanceof DNodeContainer) {
                DNodeContainer container = (DNodeContainer) element;
                nbExternalEdges += getNumberOfExternalEdges(container.getIncomingEdges(), internalElements, true);
            }
        }
        return nbExternalEdges;
    }

    private int getNumberOfOutgoingEdges(List<DDiagramElement> internalElements) {
        int nbExternalEdges = 0;
        Iterator<DDiagramElement> it = internalElements.iterator();
        while (it.hasNext()) {
            DDiagramElement element = it.next();
            if (element instanceof DNode) {
                DNode node = (DNode) element;
                nbExternalEdges += getNumberOfExternalEdges(node.getOutgoingEdges(), internalElements, false);
            } else if (element instanceof DNodeContainer) {
                DNodeContainer container = (DNodeContainer) element;
                nbExternalEdges += getNumberOfExternalEdges(container.getOutgoingEdges(), internalElements, false);
            }
        }
        return nbExternalEdges;
    }

    private int getNumberOfExternalEdges(List<DEdge> edges, List<DDiagramElement> internalElements, boolean incoming) {
        int nbExternalEdges = 0;
        Iterator<?> it = getVisibleElements(edges).iterator();
        while (it.hasNext()) {
            DEdge edge = (DEdge) it.next();
            if (incoming) {
                if (!internalElements.contains(edge.getSourceNode())) {
                    nbExternalEdges++;
                }
            } else {
                if (!internalElements.contains(edge.getTargetNode())) {
                    nbExternalEdges++;
                }
            }
        }
        return nbExternalEdges;
    }

    @Override
    protected void tearDown() throws Exception {
        initSynchronizationLockOnAllMappings(false);
        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }
}
