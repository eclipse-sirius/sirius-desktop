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
package org.eclipse.sirius.tests.swtbot;

import java.text.MessageFormat;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.Tabbar;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.LocationURI;
import org.eclipse.sirius.diagram.ui.tools.internal.menu.PopupMenuContribution;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.ui.PlatformUI;

/**
 * Tests for the popup menu tool.
 * 
 * @author cnotot
 */
public class PopupMenuTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String REPRESENTATION_INSTANCE_NAME = "new vp939";

    private static final String REPRESENTATION_NAME = "vp939";

    private static final String MODEL = "vp939.ecore";

    private static final String SESSION_FILE = "vp939.aird";

    private static final String VSM_FILE = "vp939.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/popupMenu/";

    private static final String FILE_DIR = "/";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open the diagram and gather the initial bounds of all the bordered nodes.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnPackage() throws Exception {

        // Select a diagram element named "sub package"
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The menu should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnPackage() throws Exception {

        // Select a diagram element named "ref"
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The menu should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnPackage() throws Exception {

        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myAction3.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The menu should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            fail("The action myAction1.1 of the menu myMenu1 should exist");
        }
        try {
            editor.clickContextMenu("myAction1.2");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The action myAction1.2 of the menu myMenu1 should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The menu should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myAction3.2");
            fail("The action myAction3.2 of the menu myMenu3 should not exist");
        } catch (WidgetNotFoundException e) {
        }
        try {
            editor.clickContextMenu("myAction3.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The action myAction3.1 of the menu myMenu3 should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu1OnAttribute() throws Exception {
        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction1.2");
        } catch (WidgetNotFoundException e) {
            fail("The action myAction1.2 of the menu myMenu1 should exist");
        }
        try {
            editor.clickContextMenu("myAction1.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The action myAction1.1 of the menu myMenu1 should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu2OnAttribute() throws Exception {

        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction2.1");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The menu should not exist");
    }

    /**
     * @throws Exception
     *             e
     */
    public void testMenu3OnAttribute() throws Exception {

        editor.click(editor.getEditPart("att"));
        editor.select(editor.getEditPart("att"));
        try {
            editor.clickContextMenu("myAction3.1");
            fail("The action myAction3.1 of the menu myMenu3 should not exist");
        } catch (WidgetNotFoundException e) {
        }
        try {
            editor.clickContextMenu("myAction3.2");
        } catch (WidgetNotFoundException e) {
            return;
        }
        fail("The action myAction3.2 of the menu myMenu3 should not exist");
    }

    /**
     * Test if the menu or actions are accessible with a precondition Also test if the variable $views is accessible
     * only from Operation action and not in external action or action call.
     * 
     * @throws Exception
     *             e
     */
    public void testMenuWithWrongVariable() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));

        // the popup menu has a precondition with $views
        try {
            editor.clickContextMenu("myMenuWithWrongVariable");
            fail("The action myActionWithViews of the menu myMenu3 should not exist");
        } catch (WidgetNotFoundException e) {
        }

        // the ExternalJavaAction has a precondition with $views
        try {
            editor.clickContextMenu("myExternalJavaActionWithWrongVariableAction");
            fail("The action myExternalJavaActionWithWrongVariableAction should not exist");
        } catch (WidgetNotFoundException e) {
        }

        // the ExternalJavaActionCall has a precondition with $views
        try {
            editor.clickContextMenu("myExternalJavaActionCallWithWrongVariableAction");
            fail("The action myExternalJavaActionCallWithWrongVariableAction should not exist");
        } catch (WidgetNotFoundException e) {
        }

    }

    /**
     * Test if the menu or actions are accessible with a precondition in the operation action Also test if the variable
     * $views is accessible only from Operation action.
     * 
     * @throws Exception
     *             e
     */
    public void testMenu4() throws Exception {
        SWTBotGefEditPart editPart = editor.getEditPart("Class");
        SWTBotGefEditPart editPart2 = editor.getEditPart("att");

        // the action is visible if there are more than one edit part selected
        testMenuWith2Selections(editPart, editPart2);
        testMenuWith1Selection(editPart);
        testMenuWith2Selections(editPart, editPart2);
        testMenuWith1Selection(editPart);
    }

    /**
     * Test that a warning is logged for a group with a blank location.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testWarningForGroupWithBlankLocationOnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        boolean previousWarningCatchActiveStatus = isWarningCatchActive();
        warnings.clear();
        setWarningCatchActive(true);
        try {
            try {
                editor.clickContextMenu("myActionInGroupWithBlankLocationURI");
            } catch (WidgetNotFoundException e) {
                // Expected, check that a warning has been displayed
                String expectedMessage = MessageFormat.format(Messages.Group_Not_Displayed, "groupWithBlankLocationURI",
                        MessageFormat.format(Messages.LocationURI_ParsePb_Blank, LocationURI.MENU_SCHEME, LocationURI.TABBAR_SCHEME)) + ": ";
                if (doesAWarningOccurs()) {
                    if (warnings.values().size() == 1) {
                        String message = warnings.values().iterator().next().getMessage();
                        assertEquals("The warning concerning the group with blank locationUri should use a specific message.", expectedMessage, message);
                    } else {
                        fail("One warning is expected concerning the group with blank locationUri, but was " + warnings.values().size() + ".");
                    }
                } else {
                    fail("One warning is expected concerning the group with blank locationUri, but was 0.");
                }
            }
        } finally {
            setWarningCatchActive(previousWarningCatchActiveStatus);
            warnings.clear();
        }
    }

    /**
     * Test that a warning is logged for a group with a not empty location contained in a popup menu.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testWarningForGroupWithLocationInPopupMenuOnPackage() throws Exception {

        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        boolean previousWarningCatchActiveStatus = isWarningCatchActive();
        warnings.clear();
        setWarningCatchActive(true);
        try {
            try {
                editor.clickContextMenu("myActionInGroupInPopupMenuWithLocationURI");
            } catch (WidgetNotFoundException e) {
                // Expected, check that a warning has been displayed
                String expectedMessage = MessageFormat.format(Messages.Group_Not_Displayed, "groupInPopupMenuWithLocationURI",
                        MessageFormat.format(org.eclipse.sirius.viewpoint.Messages.Constraint_validNullLocationURIForGroupInPopupMenuConstraint_message, "aNotBlankLocationURI"));
                if (doesAWarningOccurs()) {
                    if (warnings.values().size() == 1) {
                        String message = warnings.values().iterator().next().getMessage();
                        assertEquals("The warning concerning the group with blank locationUri should use a specific message.", expectedMessage, message);
                    } else {
                        fail("One warning is expected concerning the group with locationUri in a popup menu, but was " + warnings.values().size() + ".");
                    }
                } else {
                    fail("One warning is expected concerning the group with locationUri in a popup menu, but was 0.");
                }
            }
        } finally {
            setWarningCatchActive(previousWarningCatchActiveStatus);
            warnings.clear();
        }
    }

    /**
     * Test that action in a new group in an existing menu is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInExistingMenuOnClass() throws Exception {
        // TODO: There is no check that the action is really in the right menu. So clickContextMenu must be
        // replaced/completed by a new method
        // org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils.isMenuEnabled(Display, Control, String) with a
        // "qualifiedName" instead of a simple label.
        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myActionInNavigateMenu");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionInNavigateMenu\" of the new group \"siriusGroupInNavigateMenu\" of the existing menu \"Navigate\" should exist");
        }
    }

    /**
     * Test that action in a new group in an existing menu is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInAPopupMenuOnPackage() throws Exception {
        // TODO: There is no check that the action is really in the right menu. So clickContextMenu must be
        // replaced/completed by a new method
        // org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils.isMenuEnabled(Display, Control, String) with a
        // "qualifiedName" instead of a simple label.
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myActionInGroupInPopupMenu");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionInGroupInPopupMenu\" of the new group \"groupInPopupMenu\" of the new menu \"myMenuContainingGroups\" should exist");
        }
    }

    /**
     * Test that action in a new group in the New menu is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInTheNewMenuOnPackage() throws Exception {
        // TODO: There is no check that the action is really in the right menu. So clickContextMenu must be
        // replaced/completed by a new method
        // org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils.isMenuEnabled(Display, Control, String) with a
        // "qualifiedName" instead of a simple label.
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myActionInGroupInTheNewMenu");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionInGroupInTheNewMenu\" of the new group \"groupInTheNewMenu\" of the \"New\" menu should exist");
        }
    }

    /**
     * Test that action in a new group in the Open menu is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInTheOpenMenuOnPackage() throws Exception {
        // TODO: There is no check that the action is really in the right menu. So clickContextMenu must be
        // replaced/completed by a new method
        // org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils.isMenuEnabled(Display, Control, String) with a
        // "qualifiedName" instead of a simple label.
        editor.click(editor.getEditPart("sub package"));
        editor.select(editor.getSelectableEditPart("sub package"));
        try {
            editor.clickContextMenu("myActionInGroupInTheOpenMenu");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionInGroupInTheOpenMenu\" of the new group \"groupInTheOpenMenu\" of the \"Open\" menu should exist");
        }
    }

    /**
     * Test that action in a new group in the default menu (locationURI without menu id) is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInDefaultMenuOnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myActionInGroupWithoutMenuId");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionInGroupWithoutMenuId\" of the new group \"menuGroupWithoutMenuId\" of the root menu should exist");
        }
    }

    /**
     * Test that action in a new group in the default menu (locationURI without menu id) is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInANewGroupInAMenuContributedByVSMOnClass() throws Exception {

        editor.click(editor.getEditPart("Class"));
        editor.select(editor.getEditPart("Class"));
        try {
            editor.clickContextMenu("myActionG1.1");
        } catch (WidgetNotFoundException e) {
            fail("The action \"myActionG1.1\" of the new group \"myGroup1\" of the menu \\\"myMenu1\\\" should exist");
        }
    }

    /**
     * Test that an action in a new group in the Select menu of tabbar is accessible.
     * 
     * @throws Exception
     *             In case of unexpected problem
     */
    public void testActionInNewGroupInTabbar() throws Exception {
        assertEquals("Only the action contributed by the VSM must be available in mock Select menu of tabbar (+ the separator)", 2, getNbElementsInSelectMenuOfTabbar(editor));
    }

    private int getNbElementsInSelectMenuOfTabbar(SWTBotSiriusDiagramEditor editor) {
        final DDiagramEditorImpl edit = (DDiagramEditorImpl) editor.getReference().getEditor(false);
        Tabbar tabbar = edit.getTabbar();
        Option<Object> toolbarOption = ReflectionHelper.getFieldValueWithoutException(tabbar, "toolBar");
        final ToolBar toolBar = (ToolBar) toolbarOption.get();

        RunnableWithResult<Integer> result = new RunnableWithResult<Integer>() {
            int result = 0;

            @Override
            public void run() {
                ToolItem selecAllMenuItem = toolBar.getItem(1);
                assertEquals("The second item must be the \"Select\" menu", DiagramUIActionsMessages.SelectAllAction_toolbar_SelectAll, selecAllMenuItem.getToolTipText());
                // It is not possible to check tabbar menu by Swtbot so we use a mock menu and the "API" to complete the
                // menu with the contents of the VSM
                IMenuManager mockMenuManager = new MenuManager(null, ActionIds.MENU_SELECT);
                // Add potential select all actions contributed in VSM
                PopupMenuContribution.contributeToPopupMenuProgrammatically(mockMenuManager, edit, true);
                result = mockMenuManager.getItems().length;
                mockMenuManager.dispose();
            }

            @Override
            public Integer getResult() {
                return result;
            }

            @Override
            public void setStatus(IStatus status) {

            }

            @Override
            public IStatus getStatus() {
                return null;
            }
        };
        PlatformUI.getWorkbench().getDisplay().syncExec(result);
        int count = result.getResult();
        return count;
    }

    /**
     * With one edit part selected the action is not visible.
     * 
     * @param editPart
     */
    private void testMenuWith1Selection(SWTBotGefEditPart editPart) {
        editor.select(editPart);
        try {
            editor.clickContextMenu("actionMenu4");
            fail("The action actionMenu4 of the menu4 should not exist");
        } catch (WidgetNotFoundException e) {
        }
    }

    /**
     * With two edit parts selected the action is visible.
     * 
     * @param editPart
     * @param editPart2
     */
    private void testMenuWith2Selections(SWTBotGefEditPart editPart, SWTBotGefEditPart editPart2) {
        editor.select(editPart, editPart2);

        try {
            editor.clickContextMenu("actionMenu4");
        } catch (WidgetNotFoundException e) {
            fail("The action actionMenu4 of the menu myMenu4 should exist");
        }
    }

    /**
     * Test undo/redo of the PopupMenu action.
     * 
     */
    public void testUndoRedoMenuAction() {
        SWTBotGefEditPart editPart = editor.getEditPart("Class");
        SWTBotGefEditPart editPart2 = editor.getEditPart("att");
        editor.select(editPart, editPart2);
        try {
            editor.clickContextMenu("actionMenu4");
        } catch (WidgetNotFoundException e) {
            fail("The action actionMenu4 of the menu myMenu4 should exist");
        }
        try {
            undo("actionMenu4");
        } catch (WidgetNotFoundException e) {
            fail("The action actionMenu4 of the menu myMenu4 should be canceled");
        }
        try {
            redo("actionMenu4");
        } catch (WidgetNotFoundException e) {
            fail("The action actionMenu4 of the menu myMenu4 should be restored");
        }
    }
}
