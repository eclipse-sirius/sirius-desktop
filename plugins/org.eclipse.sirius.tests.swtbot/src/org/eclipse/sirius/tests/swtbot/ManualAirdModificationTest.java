/*******************************************************************************
 * Copyright (c) 2017, 2022 Obeo.
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

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;
import java.util.stream.Stream;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.IntResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests behavior of table and tree editors when the aird is manually modified
 * whereas editors are already opened.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ManualAirdModificationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/refresh/manualAirdModification/";

    private static final String SEMANTIC_RESOURCE_NAME = "manualAird.ecore";

    private static final String REPRESENTATIONS_RESOURCE_NAME = "manualAird.aird";

    private static final String MODELER_RESOURCE_NAME = "manualAird.odesign";

    private SWTBotTree tableTree;

    private SWTBotEditor tableEditor;

    private SWTBotEditor treeEditor;

    private Session session;

    private URI airdResourceUri;

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        System.setProperty("org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey", "true"); //$NON-NLS-1$ //$NON-NLS-2$

        sessionAirdResource = new UIResource(designerProject, "/", REPRESENTATIONS_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        session = localSession.getOpenedSession();

        tableEditor = openRepresentation(localSession.getOpenedSession(), "manualAirdTable", "manualAirdTable", DTable.class);
        tableTree = tableEditor.bot().tree();

        treeEditor = openRepresentation(localSession.getOpenedSession(), "manualAirdTree", "manualAirdTree", DTree.class);

        airdResourceUri = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(sessionAirdResource.getFullPath())).getRawLocationURI();

    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, PATH, SEMANTIC_RESOURCE_NAME, REPRESENTATIONS_RESOURCE_NAME, MODELER_RESOURCE_NAME);
    }

    @Override
    protected void tearDown() throws Exception {
        System.setProperty("org.eclipse.sirius.ui.restoreBehaviorEnablingDirectEditOnAlphanumericKey", "false"); //$NON-NLS-1$ //$NON-NLS-2$
        super.tearDown();
    }

    /**
     * Test that modifications made outside of the session does not break
     * current opened table and tree editors.
     * 
     * @throws IOException
     *             if a problem occurs when modifying representations file
     *             outside of the session.
     * @throws CoreException
     *             if a problem occurs when refreshing workspace project.
     */
    public void testManualAirdModificationBehavior() throws IOException, CoreException {
        // check original header column width
        assertEquals("The original table header column width is not the expected one.", (Integer) 59, syncExec(new IntResult() {
            @Override
            public Integer run() {
                return tableTree.widget.getColumns()[0].getWidth();
            }
        }));
        // We replace the header column width from 59 to 100 in the aird.
        modifyRepresentationsFileOutsideOfTheSession("headerColumnWidth=\"59\"", "headerColumnWidth=\"100\"");

        // refresh workspace projects
        ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName()).refreshLocal(IResource.DEPTH_INFINITE, new NullProgressMonitor());

        SWTBotUtils.waitAllUiEvents();
        tableEditor.show();
        SWTBotUtils.waitAllUiEvents();

        // check original header column width
        assertEquals("The table header column width has not been updated after modification of representations file outside of the session.", (Integer) 100, syncExec(new IntResult() {
            @Override
            public Integer run() {
                return tableTree.widget.getColumns()[0].getWidth();
            }
        }));

        assertEquals("The session should not be dirty.", SessionStatus.SYNC, session.getStatus());

        // We check property view is still linked to table selection
        changeNameByUsingPropertyView(tableEditor, "C3", "C4");
        tableEditor.setFocus();
        assertEquals("The session should be dirty.", SessionStatus.DIRTY, session.getStatus());

        // We check direct edit tool works for the table
        directEditLabel(tableEditor, "C2", 'E');
        treeEditor.show();
        try {
            treeEditor.bot().tree().getTreeItem("C4");
        } catch (WidgetNotFoundException e) {
            fail("Tree cell has not been updated grapically.");
        }
        try {
            treeEditor.bot().tree().getTreeItem("E");
        } catch (WidgetNotFoundException e) {
            fail("Tree cell has not been updated grapically.");
        }
        session.save(new NullProgressMonitor());
        assertEquals("The session should not be dirty.", SessionStatus.SYNC, session.getStatus());

        // We check property view is still linked to tree selection
        changeNameByUsingPropertyView(treeEditor, "C1", "C7");
        treeEditor.setFocus();
        assertEquals("The session should be dirty.", SessionStatus.DIRTY, session.getStatus());

        // We check direct edit tool works for the tree
        directEditLabel(treeEditor, "E", 'F');
        tableEditor.show();
        treeEditor.show();

        try {
            treeEditor.bot().tree().getTreeItem("F");
        } catch (WidgetNotFoundException e) {
            fail("Tree cell has not been updated grapically.");
        }
        try {
            treeEditor.bot().tree().getTreeItem("C7");
        } catch (WidgetNotFoundException e) {
            fail("Tree cell has not been updated grapically.");
        }
    }

    /**
     * Replace the value in the aird matching the given reg exp oldValue by the
     * new value.
     * 
     * @param newValue
     *            the value used to replace mathing value in the aird.
     * @param oldValue
     *            the reg exp used to find content in aird that will be replaced
     *            by the new value.
     * @throws IOException
     *             if any problem occurs when modifying the aird.
     * 
     */
    protected void modifyRepresentationsFileOutsideOfTheSession(String oldValue, String newValue) throws IOException {
        java.nio.file.Path representationsFilePath = Paths.get(airdResourceUri);
        // Read the content of the file
        Optional<String> newContent = Optional.empty();
        try (BufferedReader br = Files.newBufferedReader(representationsFilePath)) {
            // Replace the content
            Stream<String> newContentStream = br.lines().map(line -> line.replace(oldValue, newValue));
            newContent = newContentStream.reduce((concatenatedLines, lineToConcat) -> concatenatedLines.concat(lineToConcat));
        }
        if (newContent.isPresent()) {
            // Write the new content
            try (BufferedWriter bw = Files.newBufferedWriter(representationsFilePath, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
                bw.write(newContent.get());
            }
        }
    }

    /**
     * Direct edit the text cell with the given old value and replace the
     * content with the given char.
     * 
     * @param editor
     *            editor containing the text cell to edit.
     * @param oldValue
     *            the value that should be changed.
     * @param newChar
     *            the new value.
     */
    protected void directEditLabel(SWTBotEditor editor, String oldValue, char newChar) {
        SWTBotTreeItem tableItem = editor.bot().tree().getTreeItem(oldValue);
        tableItem.select();
        tableItem.click(2);
        SWTBotUtils.directEditWithKeyboard(tableTree.widget, newChar + "");
    }

    /**
     * Change the value of the cell text containing the old value to the new one
     * by clicking on it on the given editor and modifying it using the
     * properties view. This tests that properties views are linked to editor
     * selections.
     * 
     * @param editor
     *            editor used to do the modification
     * @param oldValue
     *            the EClass name old value to update
     * @param newValue
     *            the EClass name new value.
     */
    protected void changeNameByUsingPropertyView(SWTBotEditor editor, String oldValue, String newValue) {
        editor.bot().tree().select(oldValue);
        SWTBotUtils.waitAllUiEvents();
        bot.viewByTitle("Properties").setFocus();
        SWTBot propertiesBot = bot.viewByTitle("Properties").bot();
        try {
            SWTBotTreeItem treeItem = propertiesBot.tree().getTreeItem(oldValue).getNode("Name");
            treeItem.select();
            treeItem.click();
            SWTBotText propertyText = propertiesBot.text(oldValue);
            propertyText.setText(newValue);
        } catch (WidgetNotFoundException e) {
            fail("The properties view is not linked anymore to editor selection.");
        }
    }

}
