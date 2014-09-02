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
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.util.Collection;

import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * When a DDiagram is locked by using a permission authority some actions are
 * still available.
 * 
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=442761
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 * 
 */
public class LockedTabBarTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String FILTERS = "Filters";

    private static final String LAYERS = "Layers";

    private static final String PIN_UNPIN = "Pin/Unpin";

    private static final String SHOW_HIDE = "Show/Hide";

    private static final String ARRANGE_ALL = "Arrange All";

    private static final String ARRANGE_LINKED_BORDERED_NODES = "Arrange Linked Bordered Nodes";

    private static final String PIN_SELECTION = "Pin selected elements";

    private static final String UNPIN_SELECTION = "Unpin selected elements";

    private static final String HIDE_ELEMENT = "Hide element";

    private static final String SET_STYLE_TO_WORKSPACE_IMAGE = "Set style to workspace image";

    private static final String SELECTED_PACKAGE = "0";

    private UIResource sessionAirdResource;

    private UILocalSession localSession;

    private SWTBotSiriusDiagramEditor editor;

    private UIDiagramRepresentation diagram;

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
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), false);
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        // Activate Design viewpoint
        localSession.changeViewpointSelection(Sets.newHashSet("Design"), Sets.<String> newHashSet());

        // Open the entity diagram
        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint("Design").selectRepresentation("Entities").selectDiagramInstance("aaaa package entities").open();
        editor = diagram.getEditor();
    }

    /**
     * Check that tabbar actions are enabled or disabled depending on the
     * permission authority
     */
    public void testTabbarActionsEnablement() {
        // check that tested buttons are enabled for the diagram
        selectDiagram();
        checkEnabled(true);

        // check that tested buttons are enabled for the selected package
        selectPackageElement();
        checkEnabledWithSelectedElement(true);

        // activate the ReadOnlyPermission Authority on the representation
        ((ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(diagram.getDRepresentation())).activate();

        looseAndRetrieveTheFocus();

        // check that tested buttons are disabled for the diagram
        selectDiagram();
        checkEnabled(false);

        // check that tested buttons are disable for the selected package
        selectPackageElement();
        checkEnabledWithSelectedElement(false);
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
     * Loose and retrieve the focus
     */
    private void looseAndRetrieveTheFocus() {
        // loose the focus (set focus to the session brower)
        localSession.getLocalSessionBrowser().getTreeItem().setFocus();
        SWTBotUtils.waitAllUiEvents();

        // retrieve the focus
        editor.setFocus();
        editor.click(300, 300);
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Check that widgets should be enabled depending on the parameter. Theses
     * widgets are available when the diagram is selected.
     * 
     * @param enabled
     *            true if widgets should be enabled
     */
    private void checkEnabled(boolean enabled) {
        assertEnabled(bot.toolbarDropDownButtonWithTooltip(LAYERS), enabled);
        assertEnabled(bot.toolbarDropDownButtonWithTooltip(FILTERS), enabled);
        assertEnabled(bot.toolbarButtonWithTooltip(SHOW_HIDE), enabled);
        assertEnabled(bot.toolbarButtonWithTooltip(PIN_UNPIN), enabled);

        // "Arrange All" drop down button
        SWTBotToolbarDropDownButton arrangeAllMenu = bot.toolbarDropDownButtonWithTooltip(ARRANGE_ALL);
        for (SWTBotMenu item : arrangeAllMenu.menuItems(new AllItemsExcept(ARRANGE_LINKED_BORDERED_NODES))) {
            assertEnabled(item, enabled);
        }
        arrangeAllMenu.pressShortcut(Keystrokes.ESC);
    }

    /**
     * Check that widgets should be enabled depending on the parameter Theses
     * widgets are available when an element is selected.
     * 
     * @param enabled
     *            true if widgets should be enabled
     */
    private void checkEnabledWithSelectedElement(boolean enabled) {
        // "Unpin selected elements" is always disabled in this test
        assertEnabled(bot.toolbarButtonWithTooltip(UNPIN_SELECTION), false);

        assertEnabled(bot.toolbarButtonWithTooltip(PIN_SELECTION), enabled);
        assertEnabled(bot.toolbarButtonWithTooltip(HIDE_ELEMENT), enabled);
        assertEnabled(bot.toolbarButtonWithTooltip(SET_STYLE_TO_WORKSPACE_IMAGE), enabled);

        // "Arrange Selection" drop down button
        SWTBotToolbarDropDownButton arrangeSelectionMenu = bot.toolbarDropDownButton();
        for (SWTBotMenu item : arrangeSelectionMenu.menuItems(new AllItemsExcept())) {
            assertEnabled(item, enabled);
        }
        arrangeSelectionMenu.pressShortcut(Keystrokes.ESC);
    }

    /**
     * Selects the package element named "0"
     */
    private void selectPackageElement() {
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, SELECTED_PACKAGE, AbstractDiagramContainerEditPart.class);
        editor.getEditPart(SELECTED_PACKAGE, AbstractDiagramContainerEditPart.class).select();
        bot.waitUntil(cs);
        // Wait for tabbar refresh
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
        // Wait for tabbar refresh
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * A matcher that returns all {@link MenuItem}s except the ones having the
     * given tooltip.
     * 
     * @author alagarde
     * 
     */
    private static final class AllItemsExcept extends BaseMatcher<MenuItem> {

        private Collection<String> itemsNotToConsider;

        public AllItemsExcept(String... itemsNotToConsider) {
            this.itemsNotToConsider = Lists.newArrayList(itemsNotToConsider);
        }

        public boolean matches(Object item) {
            if (item instanceof MenuItem) {
                for (String itemNotToConsider : itemsNotToConsider) {
                    if (itemNotToConsider.equals(((MenuItem) item).getText())) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void describeTo(Description description) {

        }
    }

}
