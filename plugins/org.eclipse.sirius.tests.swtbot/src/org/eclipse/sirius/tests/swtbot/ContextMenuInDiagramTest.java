/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.ext.gmf.runtime.editparts.GraphicalHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;

/**
 * Tests that right clicking on some EditPart will make them selected.
 * 
 * @author lfasani
 */
public class ContextMenuInDiagramTest extends AbstractSiriusSwtBotGefTestCase {
    private static final String FILE_DIR = "/";

    private static final String DIAGRAM_INSTANCE_NAME = "new TestClassDiagram";

    private static final String DIAGRAM_DESCRIPTION = "TestClassDiagram";

    private static final String SEQUENCE_DIAGRAM_INSTANCE_NAME = "Sequence Diagram";

    private static final String SEQUENCE_DIAGRAM_DESCRIPTION = "Sequence Diagram on Interaction";

    private static final String MODEL_FILE = "contextMenuTest.ecore";

    private static final String MODEL_FILE_2 = "contextMenuTest.interactions";

    private static final String SESSION_FILE = "representations.aird";

    private static final String VSM_FILE = "contextMenu.odesign";

    private static final String DATA_UNIT_DIR = "/data/unit/contextMenu/";

    private SWTBotSiriusDiagramEditor editor;

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, MODEL_FILE_2, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);

    }

    private void openDiagram(String descriptionName, String instanceName) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), descriptionName, instanceName, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();
        editor.setFocus();
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Tests that right clicking on some EditPart will make them selected.
     * <ul>
     * <li>DNode2EditPart (border nodes on a node)</li>
     * <li>DNode4EditPart (border nodes on a container)</li>
     * </ul>
     */
    public void testContextualMenuBehaviorOnDiagramElement() {
        openDiagram(DIAGRAM_DESCRIPTION, DIAGRAM_INSTANCE_NAME);

        checkSelectionAfterRightClickOn("DNode2EditPartattr1");
        checkSelectionAfterRightClickOn("DNode4EditPartattr1");
    }

    /**
     * Tests that right clicking on some EditPart in a sequence diagram will make them selected.
     * <ul>
     * <li>StateEditPart</li>
     * <li>CombinedFragmentCompartmentEditPart</li>
     * <li>OperandCompartmentEditPart</li>
     * </ul>
     */
    public void testContextualMenuBehaviorOnSequenceDiagramElement() {
        openDiagram(SEQUENCE_DIAGRAM_DESCRIPTION, SEQUENCE_DIAGRAM_INSTANCE_NAME);

        // Check the CombinedFragmentCompartmentEditPart
        checkSelectionAfterRightClickOn("alt.1");

        // Check the StateEditPArt
        checkSelectionAfterRightClickOn("s1");

        // Check the OperandCompartmentEditPart
        checkSelectionAfterRightClickOn("[condition1]");
    }

    /**
     * Right click on the edit part and check that it is selected.
     * 
     * @param string
     *            name of the edit part
     */
    private void checkSelectionAfterRightClickOn(String editPartName) {
        IGraphicalEditPart editPart = (IGraphicalEditPart) editor.getEditPart(editPartName).part().getParent();
        rightClickOn(editPart);
        checkSelectedPart(editPart);

        // try to clean the contextual menu
        editor.click(new Point(0, 0));
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Right click on the given editPart
     */
    private void rightClickOn(IGraphicalEditPart editPart) {
        Point top = GraphicalHelper.getAbsoluteBoundsIn100Percent(editPart).getTop();
        editor.clickContextMenu(new Point(top.x, top.y + 4));
    }

    /**
     * Check that the given edit part is selected.
     */
    private void checkSelectedPart(EditPart expectedSelectedEditPart) {
        EditPart selectedEditPart = (EditPart) ((IStructuredSelection) editor.getSelection()).getFirstElement();
        assertEquals("Wrong selected object after right clicking on it", expectedSelectedEditPart, selectedEditPart);
    }
}
