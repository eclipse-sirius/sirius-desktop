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

import java.util.Collection;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.delete.IDeleteHook;
import org.eclipse.sirius.business.internal.helper.delete.DeleteHookDescriptorRegistry;
import org.eclipse.sirius.business.internal.helper.delete.IDeleteHookDescriptor;
import org.eclipse.sirius.business.internal.helper.delete.StandaloneDeleteHookDescriptor;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.description.tool.DeleteHook;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeContainerEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeListEditPart;
import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.swt.SWT;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;

/**
 * Test that {@link DeleteHook} plugged on Delete tool is called with all delete means (from tabbar, from Edit->Delete,
 * from Ctrl-X, ...), (See VP-2091).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DeleteHookTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String PATH = "data/unit/delete/VP-2091/";

    private static final String SEMANTIC_RESOURCE_NAME = "VP-2091.ecore";

    private static final String SESSION_RESOURCE_NAME = "VP-2091.aird";

    private static final String MODELER_RESOURCE_NAME = "VP-2091.odesign";

    private static final String REPRESENTATION_NAME = "VP-2091_Diagram";

    private static final String REPRESENTATION_INSTANCE_NAME = "new " + REPRESENTATION_NAME;

    private static final String DUMMY_DELETE_HOOK_FOR_ECLASS_ID = "org.eclipse.sirius.tests.swtbot.VP-2091.EClass";

    private static final String DUMMY_DELETE_HOOK_FOR_EPACKAGE_ID = "org.eclipse.sirius.tests.swtbot.VP-2091.EPackage";

    private UILocalSession localSession;

    /** the diagram */
    protected UIDiagramRepresentation diagram;

    /** Bot for the DiagramEditPart */
    protected SWTBotGefEditPart diagramEditPartBot;

    private SWTBotGefEditPart eClassBot;

    private SWTBotGefEditPart ePackageBot;

    private DSemanticDiagram dSemanticDiagram;

    private DDiagramElement dDiagramElementOfEClassToDelete;

    private DDiagramElement dDiagramElementOfEPackageToDelete;

    private IDeleteHookDescriptor dummyDeleteOfEClassHookDescriptor;

    private IDeleteHookDescriptor dummyDeleteOfEPackageHookDescriptor;

    private DummyDeleteOfEClassHook dummyOfEClassDeleteHook;

    private DummyDeleteOfEPackageHook dummyOfEPackageDeleteHook;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SEMANTIC_RESOURCE_NAME, getProjectName() + "/" + SEMANTIC_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + MODELER_RESOURCE_NAME, getProjectName() + "/" + MODELER_RESOURCE_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, PATH + SESSION_RESOURCE_NAME, getProjectName() + "/" + SESSION_RESOURCE_NAME);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_NAME);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        SWTBotUtils.waitAllUiEvents();

        diagramEditPartBot = editor.rootEditPart().children().get(0);
        eClassBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeListEditPart.class)).get(0);
        ePackageBot = diagramEditPartBot.descendants(IsInstanceOf.instanceOf(DNodeContainerEditPart.class)).get(0);
        dSemanticDiagram = (DSemanticDiagram) ((IGraphicalEditPart) diagramEditPartBot.part()).resolveSemanticElement();
        dDiagramElementOfEClassToDelete = dSemanticDiagram.getOwnedDiagramElements().get(0);
        dDiagramElementOfEPackageToDelete = dSemanticDiagram.getOwnedDiagramElements().get(1);

        dummyOfEClassDeleteHook = new DummyDeleteOfEClassHook();
        dummyDeleteOfEClassHookDescriptor = new StandaloneDeleteHookDescriptor(DUMMY_DELETE_HOOK_FOR_ECLASS_ID, dummyOfEClassDeleteHook);
        DeleteHookDescriptorRegistry.addExtension(dummyDeleteOfEClassHookDescriptor);

        dummyOfEPackageDeleteHook = new DummyDeleteOfEPackageHook();
        dummyDeleteOfEPackageHookDescriptor = new StandaloneDeleteHookDescriptor(DUMMY_DELETE_HOOK_FOR_EPACKAGE_ID, dummyOfEPackageDeleteHook);
        DeleteHookDescriptorRegistry.addExtension(dummyDeleteOfEPackageHookDescriptor);

    }

    /**
     * Test {@link IDeleteHook} contribution use on Edit->Delete.
     */
    public void testDeleteHookOnEditDelete() {
        eClassBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithEditDeleteMenu();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Delete action", dummyOfEClassDeleteHook.isCalledCorrectly());
        assertNull("The EClass should be deleted", dDiagramElementOfEClassToDelete.eContainer());
        assertEquals("The EClass should be deleted", 1, dSemanticDiagram.getOwnedDiagramElements().size());
        assertTrue("The EClass should be deleted", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Test {@link IDeleteHook} contribution use on Edit->Cut.
     */
    public void testDeleteHookOnEditCut() {
        eClassBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithEditCutMenu();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Cut action", dummyOfEClassDeleteHook.isCalledCorrectly());
        assertNull("The EClass should be deleted", dDiagramElementOfEClassToDelete.eContainer());
        assertEquals("The EClass should be deleted", 1, dSemanticDiagram.getOwnedDiagramElements().size());
        assertTrue("The EClass should be deleted", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Test {@link IDeleteHook} contribution use on Ctrl+X.
     */
    public void testDeleteHookOnCtrlX() {
        eClassBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithCtrlXShortcut();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Cut action", dummyOfEClassDeleteHook.isCalledCorrectly());
        assertNull("The EClass should be deleted", dDiagramElementOfEClassToDelete.eContainer());
        assertEquals("The EClass should be deleted", 1, dSemanticDiagram.getOwnedDiagramElements().size());
        assertTrue("The EClass should be deleted", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Test {@link IDeleteHook} contribution use on Delete from model tabbar button.
     */
    public void testDeleteHookOnDeleteFromModelTabbarButton() {
        eClassBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteFromModelWithTabbarButton();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Delete from model tabbar action", dummyOfEClassDeleteHook.isCalledCorrectly());
        assertNull("The EClass should be deleted", dDiagramElementOfEClassToDelete.eContainer());
        assertEquals("The EClass should be deleted", 1, dSemanticDiagram.getOwnedDiagramElements().size());
        assertTrue("The EClass should be deleted", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Test {@link IDeleteHook} contribution cancel use on Edit->Delete.
     */
    public void testDeleteHookOnEditDeleteCancel() {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException: Could not find node with text:
             * VP-2091_Viewpoint at org.eclipse.swtbot .swt.finder.widgets.SWTBotTreeItem.getNodes(SWTBotTreeItem
             * .java:334) at org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem .getNode(SWTBotTreeItem.java:308) at
             * org.eclipse.swtbot.swt.finder .widgets.SWTBotTreeItem.getNode(SWTBotTreeItem.java:346) at org.eclipse
             * .swtbot.swt.finder.widgets.SWTBotTreeItem.expandNode( SWTBotTreeItem .java:283) at
             * org.eclipse.sirius.tests.swtbot.support.api.business
             * .sessionbrowser.AbstractUIElementWithNextTreeItem.getNextNode( AbstractUIElementWithNextTreeItem.java:42)
             * at org.eclipse.sirius.tests .swtbot.support.api.business.sessionbrowser
             * .UILSCategoryBrowser.selectViewpoint(UILSCategoryBrowser.java:40) at
             * org.eclipse.sirius.tests.swtbot.DeleteHookTests.
             * onSetUpAfterOpeningDesignerPerspective(DeleteHookTests.java:105) at
             * org.eclipse.sirius.tests.swtbot.support.api. AbstractSiriusSwtBotGefTestCase
             * .setUp(AbstractSiriusSwtBotGefTestCase.java:289)
             */
            return;
        }
        ePackageBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithEditDeleteMenu();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Delete action", dummyOfEPackageDeleteHook.isCalledCorrectly());
        assertNotNull("the deletion should be canceled", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Test {@link IDeleteHook} contribution cancel use on Edit->Cut.
     */
    public void testDeleteHookOnEditCutCancel() {
        ePackageBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithEditCutMenu();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Cut action", dummyOfEPackageDeleteHook.isCalledCorrectly());
        assertNotNull("The EPackage should be deleted", dDiagramElementOfEPackageToDelete.eContainer());
    }

    /**
     * Test {@link IDeleteHook} contribution cancel use on Ctrl+X.
     */
    public void testDeleteHookOnCtrlXCancel() {
        ePackageBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteWithCtrlXShortcut();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Edit->Cut action", dummyOfEPackageDeleteHook.isCalledCorrectly());
        assertNotNull("The EPackage should be deleted", dDiagramElementOfEPackageToDelete.eContainer());
    }

    /**
     * Test {@link IDeleteHook} contribution cancel use on Delete from model tabbar button.
     */
    public void testDeleteHookOnDeleteFromModelTabbarButtonCancel() {
        ePackageBot.select();
        SWTBotUtils.waitAllUiEvents();
        deleteFromModelWithTabbarButton();
        SWTBotUtils.waitAllUiEvents();
        assertTrue("the contributed IDeleteHook is not called from the Delete from model tabbar action", dummyOfEPackageDeleteHook.isCalledCorrectly());
        assertNotNull("the deletion should be canceled", dSemanticDiagram.getOwnedDiagramElements().contains(dDiagramElementOfEPackageToDelete));
    }

    /**
     * Delete by clicking on "Edit->Delete".
     */
    private void deleteWithEditDeleteMenu() {
        SWTBotMenu deleteMenu = SWTBotSiriusHelper.menu(bot, "Edit").menu("Delete");
        if (deleteMenu.isEnabled()) {
            deleteMenu.click();
        }
    }

    /**
     * Delete by clicking on "Edit->Cut".
     */
    private void deleteWithEditCutMenu() {
        SWTBotMenu cutMenu = SWTBotSiriusHelper.menu(bot, "Edit").menu("Cut");
        if (cutMenu.isEnabled()) {
            cutMenu.click();
        }
    }

    /**
     * Delete with Ctrl+X shortcut.
     */
    private void deleteWithCtrlXShortcut() {
        bot.activeEditor();

        String savedKeyboardLayout = SWTBotPreferences.KEYBOARD_LAYOUT;
        SWTBotPreferences.KEYBOARD_LAYOUT = "EN_US";
        editor.getCanvas().pressShortcut(SWT.CTRL, 'x');
        SWTBotPreferences.KEYBOARD_LAYOUT = savedKeyboardLayout;
        SWTBotUtils.waitAllUiEvents();
    }

    /**
     * Delete from model.
     */
    private void deleteFromModelWithTabbarButton() {
        SWTBotToolbarButton deleteFromModelButtonBot = editor.bot().toolbarButtonWithTooltip("Delete from Model");
        deleteFromModelButtonBot.click();
    }

    @Override
    protected void tearDown() throws Exception {
        localSession = null;
        diagram = null;
        diagramEditPartBot = null;
        eClassBot = null;
        ePackageBot = null;
        dSemanticDiagram = null;
        dDiagramElementOfEClassToDelete = null;
        dDiagramElementOfEPackageToDelete = null;
        DeleteHookDescriptorRegistry.removeExtension(dummyDeleteOfEClassHookDescriptor.getId());
        DeleteHookDescriptorRegistry.removeExtension(dummyDeleteOfEPackageHookDescriptor.getId());
        dummyOfEClassDeleteHook = null;
        dummyDeleteOfEClassHookDescriptor = null;
        dummyOfEPackageDeleteHook = null;
        dummyDeleteOfEPackageHookDescriptor = null;
        super.tearDown();
    }

    class DummyDeleteOfEClassHook implements IDeleteHook {

        private boolean calledCorrectly = false;

        public boolean isCalledCorrectly() {
            return calledCorrectly;
        }

        @Override
        public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections, Map<String, Object> parameters) {
            calledCorrectly = selections.contains(dDiagramElementOfEClassToDelete);
            return Status.OK_STATUS;
        }

    }

    class DummyDeleteOfEPackageHook implements IDeleteHook {

        private boolean calledCorrectly = false;

        public boolean isCalledCorrectly() {
            return calledCorrectly;
        }

        @Override
        public IStatus beforeDeleteCommandExecution(Collection<DSemanticDecorator> selections, Map<String, Object> parameters) {
            calledCorrectly = selections.contains(dDiagramElementOfEPackageToDelete);
            return Status.CANCEL_STATUS;
        }

    }
}
