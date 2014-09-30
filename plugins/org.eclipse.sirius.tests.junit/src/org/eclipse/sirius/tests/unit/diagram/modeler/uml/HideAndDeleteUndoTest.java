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
package org.eclipse.sirius.tests.unit.diagram.modeler.uml;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.helper.display.DisplayServiceManager;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;
import org.eclipse.uml2.uml.Class;
import org.eclipse.uml2.uml.Model;
import org.eclipse.uml2.uml.PackageableElement;

/**
 * Tests that the undo command has the correct behavior for DeleteFromDiagram
 * and HideElement commands.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class HideAndDeleteUndoTest extends SiriusDiagramTestCase {

    private static final String SEMANTIC_MODEL_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/node/noderefresh.uml";

    private static final String MODELER_PATH = "/org.eclipse.sirius.tests.junit/data/unit/refresh/node/noderefresh.odesign";

    private static final String VIEWPOINT_NAME = "UML2";

    private static final String REPRESENTATION_DESC_NAME = "Node Class and Package Diagram with Ports";

    private DDiagram diagram;

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(VIEWPOINT_NAME);
        TestsUtil.emptyEventsFromUIThread();
    }

    /**
     * Corresponds to ticket #1784. Check that after one undo, all the delete
     * elements are reveal.
     */
    public void testUndoDeleteFromDiagram() {
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, model);
        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;
        final int nbDiagramElements = 5;
        assertEquals("Bad number of diagram elements.", nbDiagramElements, diagram.getOwnedDiagramElements().size());

        // Select 2 elements : Class2 and Class3
        final DDiagramElement class2DiagramElement = getFirstDiagramElement(diagram, class2);
        assertNotNull("The Class2 has no corresponding diagramElement", class2DiagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(class2DiagramElement);
        diagramEditor.getDiagramGraphicalViewer().setSelection(new StructuredSelection(elementEditPart));
        final DDiagramElement class3DiagramElement = getFirstDiagramElement(diagram, class3);
        assertNotNull("The Class3 has no corresponding diagramElement", class3DiagramElement);
        elementEditPart = getEditPart(class3DiagramElement);
        deleteFromDiagram(elementEditPart);
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Bad number of diagram elements.", nbDiagramElements - 2, diagram.getOwnedDiagramElements().size());

        // Call Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();
        assertEquals("Bad number of diagram elements.", nbDiagramElements, diagram.getOwnedDiagramElements().size());

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * @param diagram
     *            The diagram
     * @return
     */
    private int getNbVisibleDiagramElements(DDiagram diagram) {
        int nbVisibleDiagramElements = 0;
        for (DDiagramElement diagramElement : diagram.getOwnedDiagramElements()) {
            if (DisplayServiceManager.INSTANCE.getDisplayService().isDisplayed(diagram, diagramElement)) {
                nbVisibleDiagramElements++;
            }
        }
        return nbVisibleDiagramElements;
    }

    /**
     * Corresponds to ticket #1784. Check that after one undo, all the hide
     * elements are reveal.
     */
    public void testUndoHideElement() {
        // Get the desired package
        final Model model = (Model) semanticModel;
        assertNotNull("Corrupted input data", model);
        // Get the diagram for the root of this model.
        diagram = (DDiagram) createRepresentation(REPRESENTATION_DESC_NAME, model);
        // Get the class named "Class2"
        PackageableElement element = model.getPackagedElement("Class2");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class2 = (Class) element;

        // Get the class named "Class3"
        element = model.getPackagedElement("Class3");
        assertTrue("Corrupted input data", element instanceof Class);
        final Class class3 = (Class) element;

        // Open the editor (and refresh it)
        final IEditorPart editorPart = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

        assertTrue("We should have a DiagramDocumentEditor", editorPart instanceof DiagramDocumentEditor);
        final DiagramDocumentEditor diagramEditor = (DiagramDocumentEditor) editorPart;
        final int nbDiagramElements = 5;
        assertEquals("Bad number of diagram elements.", nbDiagramElements, diagram.getOwnedDiagramElements().size());
        assertEquals("Bad number of visible diagram elements.", nbDiagramElements, getNbVisibleDiagramElements(diagram));

        // Select 2 elements : Class2 and Class3
        final DDiagramElement class2DiagramElement = getFirstDiagramElement(diagram, class2);
        assertNotNull("The Class2 has no corresponding diagramElement", class2DiagramElement);
        IGraphicalEditPart elementEditPart = getEditPart(class2DiagramElement);
        diagramEditor.getDiagramGraphicalViewer().setSelection(new StructuredSelection(elementEditPart));
        final DDiagramElement class3DiagramElement = getFirstDiagramElement(diagram, class3);
        assertNotNull("The Class3 has no corresponding diagramElement", class3DiagramElement);
        elementEditPart = getEditPart(class3DiagramElement);
        diagramEditor.getDiagramGraphicalViewer().appendSelection(elementEditPart);

        final HideDDiagramElementAction actionDelegate = new HideDDiagramElementAction();

        final Action mockAction = new Action() {
        };

        actionDelegate.selectionChanged(mockAction, diagramEditor.getDiagramGraphicalViewer().getSelection());
        actionDelegate.run(mockAction);
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Bad number of diagram elements.", nbDiagramElements, diagram.getOwnedDiagramElements().size());
        assertEquals("Bad number of visible diagram elements.", (nbDiagramElements - 2), getNbVisibleDiagramElements(diagram));

        // Call Ctrl+Z
        session.getTransactionalEditingDomain().getCommandStack().undo();
        TestsUtil.synchronizationWithUIThread();

        assertEquals("Bad number of diagram elements.", nbDiagramElements, diagram.getOwnedDiagramElements().size());
        assertEquals("Bad number of visible diagram elements.", nbDiagramElements, getNbVisibleDiagramElements(diagram));

        DialectUIManager.INSTANCE.closeEditor(editorPart, false);
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    protected void tearDown() throws Exception {

        diagram = null;

        super.tearDown();
    }

}
