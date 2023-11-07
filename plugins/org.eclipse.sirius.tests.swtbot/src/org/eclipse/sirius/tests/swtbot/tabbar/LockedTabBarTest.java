/*******************************************************************************
 * Copyright (c) 2010, 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.tabbar;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.ExtensionPointTabbarContributorProvider;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ExtenderConstants;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.permission.DefaultPermissionProvider;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionProviderDescriptor;
import org.eclipse.sirius.ecore.extender.business.internal.permission.PermissionService;
import org.eclipse.sirius.ecore.extender.business.internal.permission.ReadOnlyPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.internal.permission.descriptors.StandalonePermissionProviderDescriptor;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.keyboard.Keystrokes;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * Ensure that when DDiagram is locked by using a permission authority all
 * actions are disabled in the tabbar.
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

    private static final String ARRANGE_SELECTION = "Arrange Selection";

    private static final String ARRANGE_LINKED_BORDER_NODES = "Arrange Linked Border Nodes";

    private static final String PIN_SELECTION = "Pin selected elements";

    private static final String UNPIN_SELECTION = "Unpin selected elements";

    private static final String HIDE_ELEMENT = "Hide element";

    private static final String SET_STYLE_TO_WORKSPACE_IMAGE = "Set style to workspace image";

    private static final String DELETE_FROM_MODEL = "Delete from Model";

    private static final String SELECTED_PACKAGE = "0";

    private PermissionProviderDescriptor permissionProviderDescriptor;

    private static final String PLUGIN_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><?eclipse version=\"3.4\"?><plugin><extension id=\"customTabbar\" point=\""
            + ExtensionPointTabbarContributorProvider.EXTENSION_ID
            + "\"><tabbarContributor class=\"org.eclipse.sirius.tests.swtbot.tabbar.TabbarContributorSample\"></tabbarContributor></extension></plugin>";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        initCustomPermissionAuthority();

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    /**
     * Check that tabbar actions are enabled or disabled depending on the
     * permission authority
     */
    public void testTabbarActionsEnablement() {
        testTabbarActionsEnablement(false);
    }

    /**
     * Check that tabbar actions are enabled or disabled depending on the
     * permission authority
     */
    public void testTabbarActionsEnablementWithCustomTabbar() {
        testTabbarActionsEnablement(true);
    }

    /**
     * Check that tabbar actions are enabled or disabled for the diagram
     * depending on the permission authority
     */
    public void testTabbarActionsEnablementForDiagram() {
        testTabbarActionsEnablementForDiagram(false);
    }

    /**
     * Check that tabbar actions are enabled or disabled for the diagram
     * depending on the permission authority
     */
    public void testTabbarActionsEnablementForDiagramWithCustomTabbar() {
        testTabbarActionsEnablementForDiagram(true);
    }

    /**
     * Check that tabbar actions are enabled or disabled for a selection
     * depending on the permission authority
     */
    public void testTabbarActionsEnablementForSelection() {
        testTabbarActionsEnablementForSelection(false);
    }

    /**
     * Check that tabbar actions are enabled or disabled for a selection
     * depending on the permission authority
     */
    public void testTabbarActionsEnablementForSelectionWithCustomTabbar() {
        testTabbarActionsEnablementForSelection(true);
    }

    private void testTabbarActionsEnablement(boolean customTabbar) {
        if (customTabbar) {
            registerExtension();
        }
        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);

        // check that tested buttons are enabled for the diagram
        selectDiagram();
        checkEnabled(true);

        // check that tested buttons are enabled for the selected package
        selectPackageElement();
        checkEnabledWithSelectedElement(true);

        // lock the diagram
        lockDiagram();

        // check that tested buttons are disabled for the diagram
        selectDiagram();
        checkEnabled(false);

        // check that tested buttons are disable for the selected package
        selectPackageElement();
        checkEnabledWithSelectedElement(false);
    }

    /**
     * Check that tabbar actions are enabled or disabled for the diagram
     * depending on the permission authority
     */
    private void testTabbarActionsEnablementForDiagram(boolean customTabbar) {
        if (customTabbar) {
            registerExtension();
        }

        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);

        selectDiagram();

        // check that tested buttons are enabled for the diagram
        checkEnabled(true);

        // lock the diagram
        lockDiagram();

        // check that tested buttons are disabled for the diagram
        checkEnabled(false);
    }

    /**
     * Check that tabbar actions are enabled or disabled for a selection
     * depending on the permission authority
     */
    private void testTabbarActionsEnablementForSelection(boolean customTabbar) {
        if (customTabbar) {
            registerExtension();
        }
        // Open the editor
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "aaaa package entities", DDiagram.class);

        selectPackageElement();

        // check that tested buttons are enabled for the selected package
        checkEnabledWithSelectedElement(true);

        // lock the diagram
        lockDiagram();

        // check that tested buttons are disable for the selected package
        checkEnabledWithSelectedElement(false);
    }

    /**
     * Lock the diagram
     */
    private void lockDiagram() {
        // activate the ReadOnlyPermission Authority on the representation
        DialectEditor dialectEditor = (DialectEditor) editor.getReference().getEditor(false);
        DRepresentation representation = dialectEditor.getRepresentation();
        ReadOnlyPermissionAuthority permissionAuthority = (ReadOnlyPermissionAuthority) PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation);
        permissionAuthority.activate();
        permissionAuthority.notifyLock(Collections.singleton(representation));
    }

    /**
     * Init Sirius with a {@link ReadOnlyPermissionAuthority}.
     */
    private void initCustomPermissionAuthority() {
        ReadOnlyPermissionAuthority readOnlyPermissionAuthority = new ReadOnlyPermissionAuthority();
        IPermissionProvider permissionProvider = new DefaultPermissionProvider(readOnlyPermissionAuthority);
        permissionProviderDescriptor = new StandalonePermissionProviderDescriptor("org.eclipse.sirius.tree.tests.forbiddenPermissionAuthorityProvider", ExtenderConstants.HIGHEST_PRIORITY,
                permissionProvider);
        PermissionService.addExtension(permissionProviderDescriptor);
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
        for (SWTBotMenu item : arrangeAllMenu.menuItems(new AllItemsExcept(ARRANGE_LINKED_BORDER_NODES))) {
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
        assertEnabled(bot.toolbarButtonWithTooltip(DELETE_FROM_MODEL), enabled);
        assertEnabled(bot.toolbarButtonWithTooltip(SET_STYLE_TO_WORKSPACE_IMAGE), enabled);

        // "Arrange Selection" drop down button
        SWTBotToolbarDropDownButton arrangeSelectionMenu = bot.toolbarDropDownButtonWithTooltip(ARRANGE_SELECTION);
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

    @Override
    protected void tearDown() throws Exception {
        PermissionService.removeExtension(permissionProviderDescriptor);
        removeExtension();
        permissionProviderDescriptor = null;
        editor = null;
        localSession = null;
        sessionAirdResource = null;
        super.tearDown();
    }

    /**
     * A matcher that returns all {@link MenuItem}s except the ones having the
     * given tooltip.
     * 
     * @author alagarde
     */
    private static final class AllItemsExcept extends BaseMatcher<MenuItem> {

        private Collection<String> itemsNotToConsider;

        public AllItemsExcept(String... itemsNotToConsider) {
            this.itemsNotToConsider = new ArrayList<>(Arrays.asList(itemsNotToConsider));
        }

        @Override
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

        @Override
        public void describeTo(Description description) {

        }
    }

    /**
     * Installs dynamically the tabbar extension.
     */
    private void registerExtension() {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        IContributor contributor = ContributorFactoryOSGi.createContributor(Activator.getDefault().getBundle());
        extensionRegistry.addContribution(new ByteArrayInputStream(PLUGIN_XML.getBytes()), contributor, false, null, null, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
    }

    /**
     * Remove the installed extension (if it exists).
     */
    private void removeExtension() {
        IExtensionRegistry extensionRegistry = Platform.getExtensionRegistry();
        IExtension extension = extensionRegistry.getExtension(ExtensionPointTabbarContributorProvider.EXTENSION_ID, Activator.PLUGIN_ID + ".customTabbar");
        if (extension != null) {
            extensionRegistry.removeExtension(extension, ((ExtensionRegistry) extensionRegistry).getTemporaryUserToken());
        }
    }
}
