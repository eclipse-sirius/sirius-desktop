/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES.
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

import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartIsDisplayed;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckEditPartMoved;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckToolIsActivated;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tests.swtbot.support.utils.dnd.DndUtil;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.junit.SWTBotJunit4ClassRunner;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test class which checks correct refresh after view creation with selection
 * wizard or DnD Tool.
 * 
 * @author dlecan, smonnier
 */
@RunWith(SWTBotJunit4ClassRunner.class)
public class RefreshAfterViewCreationTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String WIZARD_TITLE = "Selection Wizard";

    private static final String PANE_BASED_WIZARD_TITLE = "Pane Based Selection Wizard";

    private static final String REPRESENTATION_INSTANCE_NAME_2026 = "new TC2026_Container";

    private static final String REPRESENTATION_NAME_2026 = "TC2026_Container";

    private static final String REPRESENTATION_NAME_BORDERED_NODE_ON_NODE = "doremi2253-borderedNodeOnNode";

    private static final String REPRESENTATION_NAME_BORDERED_NODE_ON_NODE_IN_CONTAINER = "doremi2253-borderedNodeOnNodeInContainer";

    private static final String REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER = "doremi2253-borderedNodeOnNode";

    private static final String REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER_IN_CONTAINER = "doremi2253-borderedNodeOnNodeInContainer";

    private static final String REPRESENTATION_NAME_NODE = "doremi2253-Node";

    private static final String REPRESENTATION_NAME_CONTAINER = "doremi2253-Container";

    private static final String REPRESENTATION_NAME_LIST = "doremi2253-ContainerList";

    private static final String REPRESENTATION_NAME_NODE_IN_CONTAINER = "doremi2253-NodeInContainer";

    private static final String REPRESENTATION_NAME_CONTAINER_IN_CONTAINER = "doremi2253-ContainerInContainer";

    private static final String REPRESENTATION_NAME_LIST_IN_CONTAINER = "doremi2253-ContainerListInContainer";

    private static final String MODEL = "edgeRefreshAfterViewCreation.ecore";

    private static final String SESSION_FILE = "edgeRefreshAfterViewCreation.aird";

    private static final String VSM_FILE = "edgeRefreshAfterViewCreation.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/refresh/";

    private static final String FILE_DIR = "/";

    private static final String CMD_DELETE_FROM_DIAGRAM = "Delete from Diagram";

    private static final String CMD_INSERT_PACKAGE = "InsertPackage";

    private static final String CMD_INSERT_CLASS = "InsertClass";

    private static final String UNSYNCHRO_CONTEXTUAL_MENU_NAME = "Unsynchronized";

    private static final String REFRESH_CONTEXTUAL_MENU_NAME = "Refresh";

    // Point somewhere in diagram
    private static final Point SOMEWHERE_IN_DIAGRAM = new Point(322, 223);

    UILocalSession localSession;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Override
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * @param editor
     */
    private void insertPackageP2(final SWTBotSiriusDiagramEditor editor) {
        applyOneClicTool("Package insertion", SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        SWTBotShell wizard = bot.shell(WIZARD_TITLE);
        wizard.setFocus();

        SWTBot shellBot = new SWTBot(wizard.widget);

        SWTBotTree tree = shellBot.tree();

        // Select last package
        tree.select(tree.rowCount() - 1);

        SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }

    /**
     * @param editor
     */
    private void insertClassC2(final SWTBotSiriusDiagramEditor editor) {
        applyOneClicTool("Class insertion", SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        SWTBotShell wizard = bot.shell(WIZARD_TITLE);
        wizard.setFocus();

        SWTBot shellBot = new SWTBot(wizard.widget);

        // Select last class
        SWTBotTree tree = shellBot.tree();
        tree.select(tree.rowCount() - 1);

        SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }

    private void applyOneClicTool(String toolName, int xLocation, int yLocation) {
        ICondition done = new CheckToolIsActivated(editor, toolName);
        editor.activateTool(toolName);
        bot.waitUntil(done);
        editor.click(xLocation, yLocation);
    }

    /**
     * @param editor
     */
    private void insertClassC2PaneBased(final SWTBotSiriusDiagramEditor editor) {
        applyOneClicTool("Class insertion Pane", SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(PANE_BASED_WIZARD_TITLE));

        SWTBotShell wizard = bot.shell(PANE_BASED_WIZARD_TITLE);
        wizard.setFocus();

        SWTBot shellBot = new SWTBot(wizard.widget);

        // Select last class
        SWTBotTree tree = shellBot.tree();
        tree.select(tree.rowCount() - 1);

        SWTBotButton addButton = shellBot.button("Add");
        shellBot.waitUntil(new ItemEnabledCondition(addButton));
        addButton.click();

        SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testSelectionPackageInWizardAndCheckIfEdgeIsCreated() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_2026, REPRESENTATION_INSTANCE_NAME_2026, DDiagram.class, true, true);

        applyOneClicTool("Create Packages", SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        SWTBotShell wizard = bot.shell(WIZARD_TITLE);
        wizard.setFocus();

        SWTBot shellBot = new SWTBot(wizard.widget);

        SWTBotTree tree = shellBot.tree();

        int[] indexesSelection = new int[tree.rowCount()];
        for (int i = 0; i < tree.rowCount(); i++) {
            indexesSelection[i] = i;
        }
        // Selection all packages
        tree.select(indexesSelection);

        SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();

        // Use parent because getEditPart returns label edit part for a bordered
        // node
        SWTBotGefEditPart source = editor.getEditPart("EC1").parent();
        SWTBotGefEditPart target = editor.getEditPart("EC2").parent();

        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);

        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * that have an incoming or an outgoing edge. <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnNodeCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_NODE, REPRESENTATION_NAME_BORDERED_NODE_ON_NODE, DDiagram.class,
                true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_PACKAGE).click();
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_PACKAGE).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * that have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnNodeCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_NODE, REPRESENTATION_NAME_BORDERED_NODE_ON_NODE, DDiagram.class,
                true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * in a container. The bordered node have an incoming or an outgoing edge.
     * <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnNodeInContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_NODE_IN_CONTAINER,
                REPRESENTATION_NAME_BORDERED_NODE_ON_NODE_IN_CONTAINER, DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_PACKAGE).click();
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_PACKAGE).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the class C2
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        source.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The class C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * in a container. The bordered node have an incoming or an outgoing edge.
     * <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnNodeInContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_NODE_IN_CONTAINER,
                REPRESENTATION_NAME_BORDERED_NODE_ON_NODE_IN_CONTAINER, DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the class C2
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        source.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The class C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a node that
     * have an incoming or an outgoing edge. <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnNodeCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE, REPRESENTATION_NAME_NODE, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a node that
     * have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnNodeCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE, REPRESENTATION_NAME_NODE, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    private void selectAndRefreshEditor() {
        ICondition done = new OperationDoneCondition();
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Refresh the diagram
        editor.clickContextMenu(REFRESH_CONTEXTUAL_MENU_NAME);

        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(done);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Test if the edge appear graphically after an insertion of a container
     * that have an incoming or an outgoing edge. <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER, REPRESENTATION_NAME_CONTAINER, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a container
     * that have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER, REPRESENTATION_NAME_CONTAINER, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a list that
     * have an incoming or an outgoing edge. <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnListCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_LIST, REPRESENTATION_NAME_LIST, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramListEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a list that
     * have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnListCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_LIST, REPRESENTATION_NAME_LIST, DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a node in a
     * container. The node have an incoming or an outgoing edge.<BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnNodeInContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE_IN_CONTAINER, REPRESENTATION_NAME_NODE_IN_CONTAINER, DDiagram.class, true,
                true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a node in a
     * container. The node have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnNodeInContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE_IN_CONTAINER, REPRESENTATION_NAME_NODE_IN_CONTAINER, DDiagram.class, true,
                true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());
        selectAndRefreshEditor();
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test that adding a node in a container, using a selection wizard, will
     * place the node at the expected position. The node should not be shifted
     * in the container as much as this container is far from diagram origin
     * (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testNodeCreationInContainerPositionStabilityUsingSelectionWizardTool() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE_IN_CONTAINER, REPRESENTATION_NAME_NODE_IN_CONTAINER, DDiagram.class, true,
                true);

        editor.maximize();

        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            insertClassC2(editor);

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test that adding a node in a container, using a paned based selection
     * wizard, will place the node at the expected position. The node should not
     * be shifted in the container as much as this container is far from diagram
     * origin (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testNodeCreationInContainerPositionStabilityUsingPaneBasedSelectionWizardTool() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE_IN_CONTAINER, REPRESENTATION_NAME_NODE_IN_CONTAINER, DDiagram.class, true,
                true);

        maximizeEditor(editor);

        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            insertClassC2PaneBased(editor);

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test that adding a node in a container, using drag and drop, will place
     * the node at the expected position. The node should not be shifted in the
     * container as much as this container is far from diagram origin (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testNodeCreationInContainerPositionStabilityUsingDragAndDropFromTreeview() throws Exception {
        Assume.assumeFalse("Drag and drop from View does not work with Xvnc", DndUtil.isUsingXvnc());
        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_NODE_IN_CONTAINER,
                REPRESENTATION_NAME_NODE_IN_CONTAINER, DDiagram.class, true, true);

        editor.maximize();

        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            restoreEditor(editor);

            final UIResource modelUIResource = new UIResource(designerProject, MODEL);

            // Drag and drop from treeview
            SWTBotTreeItem nodeToDrag = localSession.getSemanticResourceNode(modelUIResource).expand().expandNode("root").expandNode("usecaseNodes").expandNode("p1").getNode("C2 -> C1");
            nodeToDrag.select();

            nodeToDrag.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10));

            // Wait that dropped element appears in diagram
            SWTBotUtils.waitAllUiEvents();
            bot.waitUntil(new CheckEditPartIsDisplayed("C2", editor));

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test if the edge appear graphically after an insertion of a container in
     * a container. The container have an incoming or an outgoing edge.<BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnContainerInContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER_IN_CONTAINER, REPRESENTATION_NAME_CONTAINER_IN_CONTAINER,
                DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test that adding a node in a container, using a selection wizard, will
     * place the node at the expected position. The node should not be shifted
     * in the container as much as this container is far from diagram origin
     * (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testContainerCreationInContainerPositionStabilityUsingSelectionWizardTool() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER_IN_CONTAINER, REPRESENTATION_NAME_CONTAINER_IN_CONTAINER,
                DDiagram.class, true, true);

        editor.maximize();
        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            insertClassC2(editor);

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test that adding a node in a container, using a paned based selection
     * wizard, will place the node at the expected position. The node should not
     * be shifted in the container as much as this container is far from diagram
     * origin (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testContainerCreationInContainerPositionStabilityUsingPaneBasedSelectionWizardTool() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER_IN_CONTAINER, REPRESENTATION_NAME_CONTAINER_IN_CONTAINER,
                DDiagram.class, true, true);

        editor.maximize();

        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            insertClassC2PaneBased(editor);

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test that adding a node in a container, using drag and drop, will place
     * the node at the expected position. The node should not be shifted in the
     * container as much as this container is far from diagram origin (VP-890).
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testContainerCreationInContainerPositionStabilityUsingDragAndDropTool() throws Exception {
        Assume.assumeFalse("Drag and drop from View does not work with Xvnc", DndUtil.isUsingXvnc());
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER_IN_CONTAINER, REPRESENTATION_NAME_CONTAINER_IN_CONTAINER,
                DDiagram.class, true, true);

        editor.maximize();

        try {
            SWTBotGefEditPart p1Bot = editor.getEditPart("p1");
            Rectangle p1Bounds = editor.getBounds(p1Bot);

            CheckEditPartMoved checkMoved = new CheckEditPartMoved(p1Bot);
            editor.drag(p1Bounds.getCenter(), p1Bounds.getCenter().getTranslated(100, 100));
            bot.waitUntil(checkMoved);

            restoreEditor(editor);

            final UIResource modelUIResource = new UIResource(designerProject, MODEL);

            // Drag and drop from treeview
            SWTBotTreeItem nodeToDrag = localSession.getSemanticResourceNode(modelUIResource).expand().expandNode("root").expandNode("usecaseNodes").expandNode("p1").getNode("C2 -> C1");
            nodeToDrag.select();

            nodeToDrag.dragAndDrop(editor.getCanvas(), new org.eclipse.swt.graphics.Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10));

            // Wait that dropped element appears in diagram
            SWTBotUtils.waitAllUiEvents();
            bot.waitUntil(new CheckEditPartIsDisplayed("C2", editor));

            SWTBotGefEditPart c2Bot = editor.getEditPart("C2").parent();
            Point c2Location = editor.getBounds(c2Bot).getLocation();
            Point expectedC2Location = new Point(SOMEWHERE_IN_DIAGRAM.x + 10, SOMEWHERE_IN_DIAGRAM.y + 10);

            // Assert.assertEquals(p1Bounds.getTranslated(100, 100),
            // editor.getBounds(p1Bot));
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).x, editor.getBounds(p1Bot).x, 4);
            Assert.assertEquals(p1Bounds.getTranslated(100, 100).y, editor.getBounds(p1Bot).y, 4);
            Assert.assertEquals(expectedC2Location.x, c2Location.x, 1);
            Assert.assertEquals(expectedC2Location.y, c2Location.y, 1);
        } finally {
            editor.restore();
        }
    }

    /**
     * Test if the edge appear graphically after an insertion of a container in
     * a container. The container have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnContainerInContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_CONTAINER_IN_CONTAINER, REPRESENTATION_NAME_CONTAINER_IN_CONTAINER,
                DDiagram.class, true, true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramContainerEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramContainerEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a list in a
     * container. The list have an incoming or an outgoing edge.<BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnListInContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_LIST_IN_CONTAINER, REPRESENTATION_NAME_LIST_IN_CONTAINER, DDiagram.class, true,
                true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_CLASS).click();
        try {
            editor.getEditPart("C2", AbstractDiagramListEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_CLASS).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a list in a
     * container. The list have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnListInContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_LIST_IN_CONTAINER, REPRESENTATION_NAME_LIST_IN_CONTAINER, DDiagram.class, true,
                true);

        insertClassC2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramListEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramListEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());
        selectAndRefreshEditor();
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * that have an incoming or an outgoing edge. <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER, REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER,
                DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_PACKAGE).click();
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_PACKAGE).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * that have an incoming or an outgoing edge. <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER, REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER,
                DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());
        selectAndRefreshEditor();
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * in a container. The bordered node have an incoming or an outgoing edge.
     * <BR>
     * This method checks also the Undo and the Redo of this functionality.<BR>
     * This method checks that feature in autorefresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnContainerInContainerCreationWithAutoRefresh() throws Exception {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER_IN_CONTAINER,
                REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER_IN_CONTAINER, DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Undo the last insertion
        bot.menu("Edit").menu("Undo " + CMD_INSERT_PACKAGE).click();
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The insertion was canceled so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Redo the last insertion
        bot.menu("Edit").menu("Redo " + CMD_INSERT_PACKAGE).click();
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the class C2
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        source.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The class C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }

    /**
     * Test if the edge appear graphically after an insertion of a bordered node
     * in a container. The bordered node have an incoming or an outgoing edge.
     * <BR>
     * This method checks that feature in manual refresh mode.
     * 
     * @throws Exception
     *             Test error.
     */
    @Test
    public void testEdgeCreationOnBorderedNodeOnContainerInContainerCreationWithoutAutoRefresh() throws Exception {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER_IN_CONTAINER,
                REPRESENTATION_NAME_BORDERED_NODE_ON_CONTAINER_IN_CONTAINER, DDiagram.class, true, true);

        insertPackageP2(editor);

        // Get the source and the target of the edge
        SWTBotGefEditPart source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        SWTBotGefEditPart target = editor.getEditPart("C1", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        List<SWTBotGefConnectionEditPart> connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());

        selectAndRefreshEditor();

        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());

        // Select the diagram itself
        editor.select(editor.mainEditPart());
        // Unsynchronized the diagram
        editor.clickContextMenu(UNSYNCHRO_CONTEXTUAL_MENU_NAME);
        // Delete the class C2
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        source.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The class C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());
        selectAndRefreshEditor();
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
        // Delete the package p2
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();
        editor.clickContextMenu(CMD_DELETE_FROM_DIAGRAM);
        try {
            editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
            fail("The package that contains C2 has been removed from the diagram so there shouldn't be an edit part for the class C2.");
        } catch (WidgetNotFoundException e) {
            // Do nothing : This exception is attempted
        }
        // Insert the package p2 (without class C2 because of unsynchronization)
        insertPackageP2(editor);
        // Insert the class C2
        insertClassC2(editor);
        // Get the source of the edge
        source = editor.getEditPart("C2", AbstractDiagramBorderNodeEditPart.class);
        // Get the attempted connection
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 0, connectionEditPart.size());
        selectAndRefreshEditor();
        connectionEditPart = editor.getConnectionEditPart(source, target);
        assertEquals("Wrong number of edges", 1, connectionEditPart.size());
    }
}
