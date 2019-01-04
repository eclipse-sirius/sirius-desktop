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
package org.eclipse.sirius.tests.unit.diagram.format.data;

import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.requests.RequestConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListName2EditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListNameEditPart;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.IContainerLabelOffsets;
import org.eclipse.sirius.tests.SiriusTestsPlugin;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.PlatformUI;

/**
 * Test the position of the container and list label
 * 
 * @author nlepine
 */
public class LabelPositionOnContainerAndListTest extends SiriusDiagramTestCase {

    private static final String C1_IMAGE = "c1Image";

    private static final String C1_SHAPE = "c1Shape";

    private static final String C2 = "c2";

    private static final String C1 = "c1";

    private static final String P2 = "p2";

    private static final String NEW_E_CLASS_4_IMAGE = "new EClass 4Image";

    private static final String NEW_E_CLASS_3_SHAPE = "new EClass 3Shape";

    private static final String NEW_E_CLASS_2 = "new EClass 2";

    private static final String NEW_PACKAGE_2 = "new Package 2";

    private static final String SEMANTIC_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/2352/2352.ecore";

    private static final String REPRESENTATION_MODEL_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/2352/2352.aird";

    private static final String MODELER_PATH = "/" + SiriusTestsPlugin.PLUGIN_ID + "/data/unit/layout/2352/2352.odesign";

    private static final String REPRESENTATION_DESC_NAME = "2352";

    private DDiagram diagram;

    private DiagramEditor editor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        TestsUtil.emptyEventsFromUIThread();

        genericSetUp(SEMANTIC_MODEL_PATH, MODELER_PATH, REPRESENTATION_MODEL_PATH);

        diagram = (DDiagram) getRepresentations(REPRESENTATION_DESC_NAME).toArray()[0];
        assertNotNull(diagram);

        editor = (DiagramEditor) DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();

    }

    /**
     * Test the label position : the aird contains the old values of the label bounds Test at the diagram opening, the
     * label has taken the new bounds
     * 
     * @throws Exception
     */
    public void testLabelPositionOnDiagramOpening() throws Exception {
        // check new position : y + constant
        checkContainerLabel(P2);
        checkListLabel(C1);
        checkList2Label(C2);
        checkContainerLabel(C1_SHAPE);
        checkContainerLabel(C1_IMAGE);
    }

    /**
     * Test the label position on diagram element creation
     * 
     * @throws Exception
     */
    public void testLabelPositionOnCreation() throws Exception {

        assertTrue("Class creation on diagram failed", applyNodeCreationTool("Class with flat style", diagram, diagram));
        moveEditPart(NEW_E_CLASS_2, new Point(10, 100));

        assertTrue("Class creation on diagram failed", applyNodeCreationTool("Class with shape style", diagram, diagram));
        moveEditPart(NEW_E_CLASS_3_SHAPE, new Point(50, 100));

        assertTrue("Class creation on diagram failed", applyNodeCreationTool("Class with image style", diagram, diagram));
        moveEditPart(NEW_E_CLASS_4_IMAGE, new Point(100, 100));

        assertTrue("Package creation on diagram failed", applyNodeCreationTool("Package", diagram, diagram));
        moveEditPart(NEW_PACKAGE_2, new Point(150, 100));

        // check new position : y + constant
        checkContainerLabel(NEW_PACKAGE_2);
        checkListLabel(NEW_E_CLASS_2);
        checkContainerLabel(NEW_E_CLASS_3_SHAPE);
        checkContainerLabel(NEW_E_CLASS_4_IMAGE);
    }

    /**
     * Move th edit part.
     * 
     * @param label
     *            the edit part label.
     * @param point
     *            delta to move.
     * 
     */
    private void moveEditPart(String label, Point point) {
        IGraphicalEditPart editPart = getEditPart(label);
        ChangeBoundsRequest request = new ChangeBoundsRequest();
        request.setMoveDelta(point);
        request.setLocation(point);
        request.setType(RequestConstants.REQ_MOVE);
        Command command = editPart.getCommand(request);
        executeCommand(editor, command);
    }

    private void executeCommand(final IDiagramWorkbenchPart part, final org.eclipse.gef.commands.Command command) {
        final EditDomain ed = part.getDiagramGraphicalViewer().getEditDomain();
        ed.getCommandStack().execute(command);
        synchronizationWithUIThread();
    }

    /**
     * Force UIThread to execute all runnables on its stack.
     */
    private void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
        } ;
    }

    /**
     * Check if the label bound = bounds parent + offset
     */
    private void checkContainerLabel(String label) {
        IGraphicalEditPart editPart = getEditPart(label);
        IGraphicalEditPart nameEditPart = getContainerNameEditPart(editPart);
        assertNotNull(nameEditPart);
        Rectangle bounds = getLabelBoundsFromParent(nameEditPart);
        assertEquals(bounds.y, nameEditPart.getFigure().getBounds().y);
    }

    /**
     * Check if the label bound = bounds parent + offset
     */
    private void checkListLabel(String label) {
        IGraphicalEditPart editPart = getEditPart(label);
        IGraphicalEditPart nameEditPart = getListNameEditPart(editPart);
        assertNotNull(nameEditPart);
        Rectangle bounds = getLabelBoundsFromParent(nameEditPart);
        assertEquals(bounds.y, nameEditPart.getFigure().getBounds().y);
    }

    /**
     * Check if the label bound = bounds parent + offset
     */
    private void checkList2Label(String label) {
        IGraphicalEditPart editPart = getEditPart(label);
        IGraphicalEditPart nameEditPart = getListName2EditPart(editPart);
        assertNotNull(nameEditPart);
        Rectangle bounds = getLabelBoundsFromParent(nameEditPart);
        assertEquals(bounds.y, nameEditPart.getFigure().getBounds().y);
    }

    /**
     * @param editPart
     * @return if the label bound = bounds parent + offset
     */
    private Rectangle getLabelBoundsFromParent(IGraphicalEditPart editPart) {
        assertNotNull(editPart.getParent());
        assertTrue(editPart.getParent() instanceof GraphicalEditPart);
        Rectangle bounds = ((GraphicalEditPart) editPart.getParent()).getFigure().getBounds().getCopy();
        bounds.y += IContainerLabelOffsets.LABEL_OFFSET;
        return bounds;
    }

    /**
     * @param label
     * @return the edit part corresponding to the label
     */
    private IGraphicalEditPart getEditPart(String label) {
        List<DDiagramElement> diagramElementsFromLabel = getDiagramElementsFromLabel(diagram, label);
        assertFalse(diagramElementsFromLabel.isEmpty());
        IGraphicalEditPart editPart = getEditPart(diagramElementsFromLabel.get(0), editor);
        assertNotNull(editPart);
        return editPart;
    }

    private IGraphicalEditPart getListNameEditPart(final IGraphicalEditPart parent) {
        for (final EditPart child : (List<EditPart>) parent.getChildren()) {
            if (child instanceof DNodeListNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    private IGraphicalEditPart getListName2EditPart(final IGraphicalEditPart parent) {
        for (final EditPart child : (List<EditPart>) parent.getChildren()) {
            if (child instanceof DNodeListName2EditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    private IGraphicalEditPart getContainerNameEditPart(final IGraphicalEditPart parent) {
        for (final EditPart child : (List<EditPart>) parent.getChildren()) {
            if (child instanceof DNodeContainerNameEditPart)
                return (IGraphicalEditPart) child;
        }
        return null;
    }

    @Override
    protected void tearDown() throws Exception {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        super.tearDown();
    }

}
