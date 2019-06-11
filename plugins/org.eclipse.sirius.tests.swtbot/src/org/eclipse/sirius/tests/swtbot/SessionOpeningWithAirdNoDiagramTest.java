/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * 
 * @author smonnier
 */
public class SessionOpeningWithAirdNoDiagramTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String NOTE_MESSAGE = "You should use the Model Explorer View and the Design Perspective to open aird files.";

    private static final String MODEL = "tc587.ecore";

    private static final String SESSION_FILE_NO_DIAGRAM = "tc587_no_diagram.aird";

    private static final String SESSION_FILE_NO_VIEWPOINT = "tc587_no_viewpoint.aird";

    private static final String DATA_UNIT_DIR = "data/unit/session/noDiagram_noSirius/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE_NO_DIAGRAM, SESSION_FILE_NO_VIEWPOINT);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        designerPerspectives.openPerspective("Java");
    }

    private void openSessionNoDiagram() {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_NO_DIAGRAM);
        mouseDoubleClickOnResource(sessionAirdResource);
    }

    private void openSessionNoSirius() {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE_NO_VIEWPOINT);
        mouseDoubleClickOnResource(sessionAirdResource);
    }

    /**
     * Test method. Opens an aird file with a selected viewpoint but no created
     * diagram.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testOpenSessionNoDiagram() throws Exception {
        openSessionNoDiagram();
        assertEditorIsNotError("editor did not opened correctly", bot.activeEditor());
        assertEquals("The active diagram or its title is not the expected one.", "diagram", bot.activeEditor().getTitle());

        SWTBotGefEditor gefEditor = bot.gefEditor("diagram");
        SWTBotGefEditPart noteEditPart = gefEditor.getEditPart(NOTE_MESSAGE);
        assertNotNull("The expected note has not been found.", noteEditPart);
    }

    /**
     * Test method. Opens an aird file without any selected viewpoint (and
     * therefore no diagram).
     * 
     * @throws Exception
     *             Test error.
     */
    public void testOpenSessionNoSirius() throws Exception {
        openSessionNoSirius();
        assertEditorIsNotError("editor did not opened correctly", bot.activeEditor());
        assertEquals("The active diagram or its title is not the expected one.", "diagram", bot.activeEditor().getTitle());

        SWTBotGefEditor gefEditor = bot.gefEditor("diagram");
        SWTBotGefEditPart noteEditPart = gefEditor.getEditPart(NOTE_MESSAGE);
        assertNotNull("The expected note has not been found.", noteEditPart);
    }

    /**
     * Return ui tree item corresponding to a UI resource in a project.
     * 
     * @param uiResource
     *            UI resource to select.
     * @return UI tree item.
     */
    private SWTBotTreeItem getUIItemFromResource(final UIResource uiResource) {

        SWTBotTreeItem treeItem = getProjectTreeItem();

        for (final String node : uiResource.getNodePath()) {
            treeItem = treeItem.expandNode(node);
        }
        treeItem = treeItem.expandNode(uiResource.getName());

        treeItem.setFocus();
        treeItem.select();

        return treeItem;
    }

    /**
     * Double-click on a resource.
     * 
     * @param uiResource
     *            Resource where to double-click.
     */
    private void mouseDoubleClickOnResource(final UIResource uiResource) {
        final SWTBotTreeItem treeItem = getUIItemFromResource(uiResource);
        treeItem.doubleClick();
    }

    private SWTBotTreeItem getProjectTreeItem() {
        final SWTBot projectExplorerBot = bot.viewByTitle("Package Explorer").bot();

        final SWTBotTree projectExplorerTree = projectExplorerBot.tree();
        projectExplorerTree.expandNode(designerProject.getName());
        SWTBotTreeItem treeItem = projectExplorerTree.getTreeItem(designerProject.getName());
        treeItem.expand();

        treeItem = projectExplorerTree.getTreeItem(designerProject.getName());
        return treeItem;
    }

}
