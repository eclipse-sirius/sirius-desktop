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
package org.eclipse.sirius.tests.swtbot.table;

import static org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable.syncExec;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.table.metamodel.table.description.CreateLineTool;
import org.eclipse.sirius.table.metamodel.table.description.DescriptionFactory;
import org.eclipse.sirius.table.metamodel.table.description.TableDescription;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.business.UITableRepresentation;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.sirius.viewpoint.description.tool.ChangeContext;
import org.eclipse.sirius.viewpoint.description.tool.ToolFactory;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that context menu on table is refreshed when VSM changes. Test VP-2270.
 * 
 * 
 * @author jdupont
 */
public class ContextMenuTableTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "2270";

    private static final String VSM_FILE = "2270.odesign";

    private static final String SESSION_FILE = "2270.aird";

    private static final String ECORE_FILE = "2270.ecore";

    private static final String FILE_DIR = "/";

    private static final String DATA_UNIT_DIR = "data/unit/table/contextMenuTable/vp-2270/";

    private static final String REPRESENTATION_TABLE_NAME = "Table";

    private static final String REPRESENTATION_TABLE_INSTANCE_NAME = "new Table";

    private static final String CLASS1 = "new EClass 1";

    private static final String CREATION_TOOL = "createLine2270";

    /**
     * Current table.
     */
    protected UITableRepresentation table;

    /**
     * Test that the contextual menu representation table is refreshed after
     * modify VSM.
     */
  public void testRefreshContextMenuAfterModifyVsm() {
    // open table representation
    openTableRepresentation();
    // Check the contextual menu table representation
    checkContextualMenuTableRepresentation();
    // Check the create line and column toolbar
    checkCreateLineColumnToolBar();
    // Open VSM
    openVSM();
    // Modify VSM, add a new creation tool
    addNewCreationToolToVSM();
    // Check the contextual menu table representation refreshed
    checkContextualMenuMenuTableRepresentationRefreshed();
  }

  private void checkCreateLineColumnToolBar() {
    // Check the default item of the dropDown Toolbar
    SWTBotToolbarDropDownButton myDropdown = bot.toolbarDropDownButtonWithTooltip("Create Class");

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
     * Return files used in the current test.
     */
    private String[] getFilesUsedForTest() {
        return new String[] { VSM_FILE, ECORE_FILE, SESSION_FILE };
    }

    private void checkContextualMenuMenuTableRepresentationRefreshed() {
        table.getTable().setFocus();
        table.getTable().getTreeItem(CLASS1).select().contextMenu("Create Class");
        table.getTable().getTreeItem(CLASS1).select().contextMenu("line");
        table.getTable().getTreeItem(CLASS1).select().contextMenu(CREATION_TOOL);
    }

    /**
     * Check that the creation tools are accessible on contextual menu
     */
    private void checkContextualMenuTableRepresentation() {
        // Reduce timeout
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
    table.getTable().getTreeItem(CLASS1).select().contextMenu("Create Class");
        table.getTable().getTreeItem(CLASS1).select().contextMenu("line");
        try {
            SWTBotPreferences.TIMEOUT = 500;
            table.getTable().getTreeItem(CLASS1).select().contextMenu(CREATION_TOOL);
            fail("This tool should not be present");

        } catch (WidgetNotFoundException tme) {
            // DO NOTHINGS, it's the good behavior
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }

    }

    private void addNewCreationToolToVSM() {
        final SWTBotEditor activeEditor = bot.activeEditor();
        activeEditor.setFocus();

        Group group = syncExec(new Result<Group>() {
            @Override
            public Group run() {
                SWTBotTree tree = activeEditor.bot().tree();
                SWTBotTreeItem[] allItems = tree.getAllItems();
                Resource vsmResource = (Resource) allItems[0].widget.getData();
                return (Group) vsmResource.getContents().get(0);
            }
        });

        final TableDescription tableDesc = (TableDescription) group.getOwnedViewpoints().get(0).getOwnedRepresentations().get(0);

        // Add Mock Creation Line tool
        final CreateLineTool tool = DescriptionFactory.eINSTANCE.createCreateLineTool();
        tool.setMapping(tableDesc.getAllLineMappings().get(0));
        tool.setName(CREATION_TOOL);

        // Add change context
        ChangeContext changeContext = ToolFactory.eINSTANCE.createChangeContext();
        changeContext.setBrowseExpression("var:container");
        tool.setFirstModelOperation(changeContext);

        // A complete tool is not needed : it must contains a model operation.
        EditingDomain ed = AdapterFactoryEditingDomain.getEditingDomainFor(tableDesc);
        ed.getCommandStack().execute(new AbstractCommand() {

            @Override
            public void execute() {
                tableDesc.getOwnedCreateLine().add(tool);
            }

            @Override
            public boolean canExecute() {
                return true;
            }

            @Override
            public void redo() {
            }
        });
        assertTrue("Tool creation should have modified the VSM.", activeEditor.isDirty());
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
    private void openTableRepresentation() {
        table = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(REPRESENTATION_TABLE_NAME)
                .selectRepresentationInstance(REPRESENTATION_TABLE_INSTANCE_NAME, UITableRepresentation.class).open();
    }

    class SelectionCondition<T> implements ICondition {
        private final ISelectionProvider selectionProvider;

        private final Class<T> selectionType;

        public SelectionCondition(ISelectionProvider selProv, Class<T> selectionType) {
            this.selectionProvider = selProv;
            this.selectionType = selectionType;
        }

        @Override
        public boolean test() throws Exception {
            IStructuredSelection selection = (IStructuredSelection) selectionProvider.getSelection();
            return selection.getFirstElement() != null && selectionType.isInstance(selection.getFirstElement());

        }

        @Override
        public void init(SWTBot bot) {
        }

        @Override
        public String getFailureMessage() {
            return "Exprected element was not selected.";
        }
    }

}
