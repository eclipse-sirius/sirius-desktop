/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
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
import java.util.Optional;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.common.tools.api.util.ReflectionHelper;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramListEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNameEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.ExtensionPointTabbarContributorProvider;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.Tabbar;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckSelectedCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.ui.PlatformUI;

/**
 * Test case to check the tabbar extension point. See
 * https://bugs.eclipse.org/bugs/show_bug.cgi?id=481573
 * 
 * @author Florian Barbin
 */
@SuppressWarnings("restriction")
public class TabbarContributorExtensionTest extends AbstractSiriusSwtBotGefTestCase {

    // We don't need a specific diagram so we reuse an existing one.
    private static final String REPRESENTATION_INSTANCE_NAME = "aaaa package entities";

    private static final String REPRESENTATION_NAME = "Entities";

    private static final String MODEL = "tc2216.ecore";

    private static final String SESSION_FILE = "tc2216.aird";

    private static final String VSM_FILE = "tc2216.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/portPositionStability/tc-2216/";

    private static final String FILE_DIR = "/";

    private static final String PLUGIN_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><?eclipse version=\"3.4\"?><plugin><extension id=\"customTabbar\" point=\""
            + ExtensionPointTabbarContributorProvider.EXTENSION_ID
            + "\"><tabbarContributor class=\"org.eclipse.sirius.tests.swtbot.tabbar.TabbarContributorSample\"></tabbarContributor></extension></plugin>";

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {

        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

    }

    /**
     * Test that the default tabbar is properly displayed when no contributor is
     * provided.
     */
    public void testTabbarCountWithoutContributor() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        selectDiagramElement();
        SWTBotUtils.waitAllUiEvents();
        int count = getTabbarItemsCount();
        assertEquals("Unexpected number of tabbar contribution items", 32, count);
        selectDiagram();
        SWTBotUtils.waitAllUiEvents();
        count = getTabbarItemsCount();
        assertEquals("Unexpected number of tabbar contribution items", 21, count);
        selectEdge();
        SWTBotUtils.waitAllUiEvents();
        count = getTabbarItemsCount();
        assertEquals("Unexpected number of tabbar contribution items", 32, count);
    }

    /**
     * Tests that the custom tabbar is properly displayed when it is installed
     * via extension point.
     */
    public void testCustomTabbarContributor() {
        registerExtension();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);
        selectDiagramElement();
        SWTBotUtils.waitAllUiEvents();
        int count = getTabbarItemsCount();
        assertEquals("The custom tabbar should be registered", 25, count);
        selectDiagram();
        SWTBotUtils.waitAllUiEvents();
        count = getTabbarItemsCount();
        assertEquals("The custom tabbar should be registered", 14, count);
        selectEdge();
        SWTBotUtils.waitAllUiEvents();
        count = getTabbarItemsCount();
        assertEquals("The custom tabbar should be registered", 0, count);
    }

    private int getTabbarItemsCount() {
        DDiagramEditorImpl edit = (DDiagramEditorImpl) editor.getReference().getEditor(false);
        Tabbar tabbar = edit.getTabbar();
        Optional<Object> toolbarOption = ReflectionHelper.getFieldValueWithoutException(tabbar, "toolBar");
        final ToolBar toolBar = (ToolBar) toolbarOption.get();

        RunnableWithResult<Integer> result = new RunnableWithResult<Integer>() {
            int result = 0;

            @Override
            public void run() {
                result = Integer.valueOf(toolBar.getItemCount());
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

    private void selectDiagramElement() {
        // Select a diagram element named "loooooooooooong name is very long"
        editor.reveal("loooooooooooong name is very long");
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, "loooooooooooong name is very long", AbstractDiagramListEditPart.class);
        editor.click("loooooooooooong name is very long");
        bot.waitUntil(cs);
    }

    private void selectEdge() {
        // Select the edge named "[0..1] ref0"
        editor.reveal("[0..1] ref0");
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, "[0..1] ref0", AbstractDiagramNameEditPart.class);
        editor.click("[0..1] ref0");
        bot.waitUntil(cs);
    }

    private void selectDiagram() {
        SWTBotGefEditPart diagPart = editor.rootEditPart().children().iterator().next();
        IDDiagramEditPart part = (IDDiagramEditPart) diagPart.part();
        CheckSelectedCondition cs = new CheckSelectedCondition(editor, part);
        editor.select(diagPart);
        bot.waitUntil(cs);
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

    @Override
    protected void tearDown() throws Exception {
        removeExtension();
        super.tearDown();
    }

}
