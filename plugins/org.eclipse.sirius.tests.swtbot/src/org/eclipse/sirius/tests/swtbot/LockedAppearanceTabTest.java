/*******************************************************************************
 * Copyright (c) 2014, 2019 Obeo.
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

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToggleButton;

/**
 * Ensure that when DDiagram is locked by using a permission authority all actions are disabled in the appearance tab of
 * the property view.
 * 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=444267
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public class LockedAppearanceTabTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String SELECTED_PACKAGE = "0";

    private static final String FONTS_AND_COLORS = "Fonts and Colors:";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Open the editor1
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);
    }

    /**
     * Check that actions in the appearance tab of the property view are enabled or disabled depending on the permission
     * authority.
     */
    public void testActionsEnablement() {
        // check that tested actions are enabled for the diagram
        selectDiagram();
        checkDiagramActionEnabled(true);

        // check that tested actions are enabled for the selected package
        selectPackageElement();
        editor.clickContextMenu("White"); // Modify to test "Reset style"
        checkSelectionActionEnabled(true);

        // activate the ReadOnlyPermission Authority on the representation
        DialectEditor dialectEditor = (DialectEditor) editor.getReference().getEditor(false);
        ((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(dialectEditor.getRepresentation())).activate();

        loseAndRetrieveTheFocus();

        // check that tested actions are disabled for the diagram
        selectDiagram();
        checkDiagramActionEnabled(false);

        // check that tested actions are disable for the selected package
        selectPackageElement();
        checkSelectionActionEnabled(false);
    }

    /**
     * Asserts that the widget is enabled or not depending on the parameter.
     * 
     * @param widget
     *            the current widget
     * @param enabled
     *            true if the widget should be enabled
     */
    private void assertEnabled(AbstractSWTBot<? extends Widget> widget, boolean enabled) {
        if (enabled) {
            assertEnabled(widget);
        } else {
            assertNotEnabled(widget);
        }
    }

    /**
     * Lose and retrieve the focus
     */
    private void loseAndRetrieveTheFocus() {
        // lose the focus (set focus to the session browser)
        localSession.getLocalSessionBrowser().getTreeItem().setFocus();
        SWTBotUtils.waitAllUiEvents();

        // retrieve the focus
        editor.setFocus();
        editor.click(300, 300);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Selects the package element named "0"
     */
    private void selectPackageElement() {
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, SELECTED_PACKAGE, AbstractDiagramContainerEditPart.class);
        editor.getEditPart(SELECTED_PACKAGE, AbstractDiagramContainerEditPart.class).select().click();
        bot.waitUntil(cs);

        // Wait for action status refresh
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Selects the diagram
     */
    private void selectDiagram() {
        SWTBotGefEditPart diagPart = editor.rootEditPart().children().iterator().next();
        IDDiagramEditPart part = (IDDiagramEditPart) diagPart.part();
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, part);
        editor.select(diagPart);
        bot.waitUntil(cs);
        editor.click(300, 300);
        // Wait for action status refresh
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Check that widgets should be enabled depending on the parameter. Theses widgets are available when the diagram is
     * selected.
     * 
     * @param enabled
     *            true if widgets should be enabled
     */
    private void checkDiagramActionEnabled(boolean enabled) {
        // 5 buttons: bold, italic, text color, line color, fill color
        // 2 combos: font type, font size
        checkActionEnabled(3, 2, 2, enabled);
    }

    /**
     * Check that widgets should be enabled depending on the parameter. Theses widgets are available when there is a
     * selection.
     * 
     * @param enabled
     *            true if widgets should be enabled
     */
    private void checkSelectionActionEnabled(boolean enabled) {
        // 9 buttons: bold, italic, text color, line color, fill color,
        // underline, strikethrough, style workspace image, reset style
        // 2 combos: font type, font size
        checkActionEnabled(5, 4, 2, enabled);
    }

    /**
     * Check that actions should be enabled depending on the parameter.
     * 
     * @param nbPushButtons
     *            number of push buttons to check
     * @param nbToogleButtons
     *            number of toggle buttons to check
     * @param nbCombos
     *            number of combos to check
     * @param enabled
     *            true if widgets should be enabled
     */
    private void checkActionEnabled(int nbPushButtons, int nbToogleButtons, int nbCombos, boolean enabled) {
        SWTBotView propertiesView = bot.viewByTitle("Properties");
        SWTBot propertiesBot = propertiesView.bot();
        propertiesView.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Appearance", propertiesView.bot());

        // Push buttons
        for (int i = 0; i < nbPushButtons; i++) {
            SWTBotButton button = propertiesBot.buttonInGroup(FONTS_AND_COLORS, i);
            assertEnabled(button, enabled);
        }

        // Toggle buttons
        for (int i = 0; i < nbToogleButtons; i++) {
            SWTBotToggleButton button = propertiesBot.toggleButtonInGroup(FONTS_AND_COLORS, i);
            assertEnabled(button, enabled);
        }

        // Combos
        for (int i = 0; i < nbCombos; i++) {
            SWTBotCCombo combo = propertiesBot.ccomboBoxInGroup(FONTS_AND_COLORS, i);
            assertEnabled(combo, enabled);
        }
    }
}
