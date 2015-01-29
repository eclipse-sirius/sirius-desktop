/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.diagram.refresh;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.Request;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.notation.Node;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DNodeList;
import org.eclipse.sirius.diagram.EdgeTarget;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * Test for issue #1934 "ModelAccessor.eDelete does only a remove".
 * 
 * See also #1931.
 * 
 * @author pcdavid
 */
public class DanglingReferencesTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/modelers/ecore/test.ecore";

    private static final String MODELER_PATH = "/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign";

    private static final String VIEWPOINT_NAME = "Design";

    private static final String REPRESENTATION_DESC_NAME = "Entities";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        cleanWorkspace();
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME);
    }

    /**
     * This test creates two EClasses "new EClass 1" (A) and "new EClass 2" (B)
     * and a reference (and thus an edge) from A to B. After deleting B and
     * doing a Refresh, A's outgoingEdges should be empty. Previous to the
     * correction of #1934, A still had a dangling reference to the old DEdge,
     * even though it was not part of the diagram and not visible.
     * 
     * Corresponds to ticket #1934, and scenario #2 in ticket #1931.
     */
    public void testDeleteSemanticElementCorrespondingToNode() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), false);

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
        assertNotNull("The editor did not open ! ", editorPart);

        final EPackage ePackage = (EPackage) semanticModel;
        assertTrue("The semantic model is not empty before the tool application", ePackage.getEClassifiers().isEmpty());
        // Create a first class
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        assertEquals("The class was not created or more elements were created", 1, ePackage.getEClassifiers().size());
        assertTrue("The class has not the right instance type", ePackage.getEClassifiers().get(0) instanceof EClass);
        final EClass eClass1 = (EClass) ePackage.getEClassifiers().get(0);
        assertEquals("The class has not the right name", "NewEClass1", eClass1.getName());

        DNodeList firstClassDiagramElement = (DNodeList) getFirstDiagramElement(diagram, eClass1);
        assertNotNull("The first class has no corresponding diagramElement", firstClassDiagramElement);
        final Node firstClassNode = getGmfNode(firstClassDiagramElement);
        assertNotNull("The first class has no corresponding GMF node", firstClassNode);
        // Create a second class
        assertTrue(applyNodeCreationTool("Class", diagram, diagram));
        assertEquals("The second class was not created or more elements were created", 2, ePackage.getEClassifiers().size());
        assertTrue("The second class has not the right instance type", ePackage.getEClassifiers().get(0) instanceof EClass);
        final EClass eClass2 = (EClass) ePackage.getEClassifiers().get(1);
        assertEquals("The second class has not the right name", "NewEClass2", eClass2.getName());

        DDiagramElement secondClassDiagramElement = getFirstDiagramElement(diagram, eClass2);
        assertNotNull("The second class has no corresponding diagramElement", secondClassDiagramElement);
        IGraphicalEditPart secondClassEditPart = getEditPart(secondClassDiagramElement);
        assertTrue("The editMode of the second class must be enabled.", secondClassEditPart.isEditModeEnabled());
        Node secondClassNode = getGmfNode(secondClassDiagramElement);
        assertNotNull("The second class has no corresponding GMF node", secondClassNode);
        // Create a reference between the two classes.
        applyEdgeCreationTool("Reference", diagram, (EdgeTarget) firstClassDiagramElement, (EdgeTarget) secondClassDiagramElement);
        assertEquals("The operation was not created or more elements were created", 1, eClass1.getEReferences().size());
        final EReference eReference = eClass1.getEReferences().get(0);
        assertEquals("The reference has not the right name", "newEReference1", eReference.getName());
        assertEquals("The reference has not the right type", eClass2, eReference.getEType());

        assertEquals("The first class has no outgoing edge to the second one.", 1, firstClassDiagramElement.getOutgoingEdges().size());

        // Delete the second class
        applyDeletionTool(secondClassDiagramElement);
        editorPart.setFocus();

        // Launch refresh
        final Request refreshRequest = new GroupRequest(RequestConstants.REQ_REFRESH_VIEWPOINT);
        ((DiagramEditor) editorPart).getDiagramEditPart().performRequest(refreshRequest);

        // The second class shouldn't have a representation.
        secondClassDiagramElement = getFirstDiagramElement(diagram, eClass2);
        assertNull("The second class has a corresponding diagramElement", secondClassDiagramElement);

        firstClassDiagramElement = (DNodeList) getFirstDiagramElement(diagram, eClass1);
        assertEquals("The first class has a dangling reference to the oblsolete outgoing edge to the second one.", 0, firstClassDiagramElement.getOutgoingEdges().size());

        // Close of the editor
        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    @Override
    protected void tearDown() throws Exception {
        diagram = null;
        super.tearDown();
    }
}
