/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.tree;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITreeRepresentation;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.tree.description.TreeItemCreationTool;
import org.eclipse.sirius.tree.description.TreeItemMapping;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.SetValue;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that context menu on tree is refreshed when VSM changes. Test VP-2270.
 * 
 * 
 * @author jdupont
 */
public class ContextMenuTreeTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "2270";

    private static final String VSM_FILE = "2270.odesign";

    private static final String SESSION_FILE = "2270.aird";

    private static final String ECORE_FILE = "2270.ecore";

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/tree/contextMenuTree/vp-2270/";

    private static final String REPRESENTATION_TREE_NAME = "Tree";

    private static final String REPRESENTATION_TREE_INSTANCE_NAME = "new Tree";

    private static final String NODE_CLASS = "Class";

    private static final String GROUP = "VP-2270 Group";

    private static final String CLASS1 = "new EClass 1";

    private static final String CREATION_TOOL = "createTool2270";

    /**
     * Current table.
     */
    protected UITreeRepresentation tree;

    /**
     * Test that the contextual menu representation tree is refreshed after
     * modify VSM.
     */
    public void testRefreshContextMenuAfterModifyVsm() {
        // open tree representation
        openTreeRepresentation();
        // Check the contextual menu tree representation
        checkContextualMenuTreeRepresentation();
    // Check the create toolbar
    checkCreateToolBar();
        // Open VSM
        openVSM();
        // Modify VSM, add a new creation tool
        addNewCreationToolToVSM();
        // Check the contextual menu table representation refreshed
        checkContextualMenuMenuTreeRepresentationRefreshed();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, getFilesUsedForTest());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Invokes {@link Result#run()} synchronously on the UI thread.
     * 
     * @param <T>
     *            return type
     * 
     * @param toExecute
     *            the object to be invoked in the UI thread.
     * @return the boolean returned by toExecute
     */
    protected <T> T syncExec(Result<T> toExecute) {
        return UIThreadRunnable.syncExec(toExecute);
    }

    /**
     * Return files used in the current test.
     */
    private String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE, ECORE_FILE, SESSION_FILE };
    }

    private void checkContextualMenuMenuTreeRepresentationRefreshed() {
        tree.getTree().setFocus();
        tree.getTree().getTreeItem(CLASS1).select().contextMenu("my create tool");
        tree.getTree().getTreeItem(CLASS1).select().contextMenu(CREATION_TOOL);
    }

    /**
     * Check that the creation tools are accessible on contextual menu
     */
    private void checkContextualMenuTreeRepresentation() {
        // Reduce timeout
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        tree.getTree().getTreeItem(CLASS1).select().contextMenu("my create tool");
        try {
            SWTBotPreferences.TIMEOUT = 500;
            tree.getTree().getTreeItem(CLASS1).select().contextMenu(CREATION_TOOL);
            fail("This tool should not be present");

        } catch (WidgetNotFoundException e) {
            // DO NOTHINGS, it's the good behavior
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }

    }

  private void checkCreateToolBar() {
    // Check the default item of the dropDown Toolbar
    SWTBotToolbarDropDownButton myDropdown = bot.toolbarDropDownButtonWithTooltip("createTool1");

    // code below doesn't work because menuItem are never built

    // try {
    // // this builds the menu adding Action on it but at that time, menuItem
    // // are not built
    // myDropdown.menuItems(null);
    // } catch (Exception e) {
    // // allow UIThread to initialize what is necessary to built menuItem
    // SWTBotUtils.waitAllUiEvents();
    // }
    //
    // // click on second menu item
    // SWTBotMenu showMenuItem = myDropdown.menuItem("line");
    // showMenuItem.click();
    // // Check that the default item of the dropDown Toolbar is the last used
    // myDropdown = bot.toolbarDropDownButtonWithTooltip("line");

  }

    private void addNewCreationToolToVSM() {
        SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();
        // Expand vsm tree
        SWTBotTreeItem treeClass = activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP).expandNode(VIEWPOINT_NAME)
                .expandNode(REPRESENTATION_TREE_NAME).expandNode(NODE_CLASS).select();
        SWTBotUtils.clickContextMenu(treeClass, "Create");
        TreeItem creationTool = activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP).expandNode(VIEWPOINT_NAME)
                .expandNode(REPRESENTATION_TREE_NAME).expandNode(NODE_CLASS).expandNode("Create").widget;
        // Create new creation tool
        TreeItemCreationTool tool = (TreeItemCreationTool) getData(creationTool);
        tool.getMapping().add((TreeItemMapping) tool.eContainer());
        tool.setName(CREATION_TOOL);
        tool.setFirstModelOperation(ToolFactory.eINSTANCE.createChangeContext());
        tool.setForceRefresh(true);
        // Add change context operation
        ChangeContext changeContext = (ChangeContext) tool.getFirstModelOperation();
        changeContext.setBrowseExpression("var:container");
        changeContext.getSubModelOperations().add(ToolFactory.eINSTANCE.createCreateInstance());
        // Add create instance operation
        CreateInstance createInstance = (CreateInstance) changeContext.getSubModelOperations().get(0);
        createInstance.setReferenceName("eClassifiers");
        createInstance.setTypeName("EClass");
        createInstance.setVariableName("instance");
        createInstance.getSubModelOperations().add(ToolFactory.eINSTANCE.createSetValue());
        // Add set value operation
        SetValue setValue = (SetValue) createInstance.getSubModelOperations().get(0);
        setValue.setFeatureName("name");
        setValue.setValueExpression("NewEClass");

        activeEditor.save();

    }

    /**
     * Open the VSM
     */
    private void openVSM() {
        SWTBotView projectExplorer = bot.viewByTitle("Model Explorer");
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();
    }

    /**
     * Open the table representation
     */
    private void openTreeRepresentation() {
        tree = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TREE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TREE_INSTANCE_NAME, UITreeRepresentation.class).open();
    }

    private Object getData(final TreeItem widget) {
        return syncExec(new Result<Object>() {
            @Override
            public Object run() {
                return widget.getData();
            }
        });
    }

}
