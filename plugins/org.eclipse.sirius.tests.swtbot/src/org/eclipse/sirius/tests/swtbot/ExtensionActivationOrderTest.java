/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.sirius.tests.support.api.EclipseTestsSupportHelper;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.dialog.ViewpointSelectionDialog;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTableItem;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This tests for a bug (VP-2529) when a viewpoint which contains a representation extension is enabled before the
 * viewpoint which contains the extended representation: if the extension references the base viewpoint through
 * "viewpoint:/" URIs, the base VSM was loaded through that logical URI instead of a concrete "platform:/" one, which
 * causes problems in other parts of Sirius.
 * 
 * See VP-2529
 * 
 * @author pierre-charles.david@obeo.fr
 */
public class ExtensionActivationOrderTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String FIXTURE_ROOT = "/data/unit/componentization";

    private static final String BASE_PROJECT_NAME = "vp2529.design";

    private static final String BASE_VSM_NAME = "base.odesign";

    private static final String EXT_A_PROJECT_NAME = "vp2529.design.ext.a";

    private static final String EXT_A_VSM_NAME = "ext_a.odesign";

    private static final String EXT_B_PROJECT_NAME = "vp2529.design.ext.b";

    private static final String EXT_B_VSM_NAME = "ext_b.odesign";

    private static final String DIAGRAM_NAME = "Base Diagram";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // The project containing the base viewpoint
        EclipseTestsSupportHelper.INSTANCE.createProject(BASE_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, FIXTURE_ROOT + "/" + BASE_PROJECT_NAME + "/" + BASE_VSM_NAME, "/" + BASE_PROJECT_NAME + "/" + BASE_VSM_NAME);
        // The two projects containing the extensions
        EclipseTestsSupportHelper.INSTANCE.createProject(EXT_A_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, FIXTURE_ROOT + "/" + EXT_A_PROJECT_NAME + "/" + EXT_A_VSM_NAME, "/" + EXT_A_PROJECT_NAME + "/" + EXT_A_VSM_NAME);
        EclipseTestsSupportHelper.INSTANCE.createProject(EXT_B_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, FIXTURE_ROOT + "/" + EXT_B_PROJECT_NAME + "/" + EXT_B_VSM_NAME, "/" + EXT_B_PROJECT_NAME + "/" + EXT_B_VSM_NAME);
        // Create the test project
        EclipseTestsSupportHelper.INSTANCE.copyFile(Activator.PLUGIN_ID, FIXTURE_ROOT + "/example.ecore", "/" + getProjectName() + "/example.ecore");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        // Remove projects created during setup
        EclipseTestsSupportHelper.INSTANCE.deleteProject(EXT_B_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(EXT_A_PROJECT_NAME);
        EclipseTestsSupportHelper.INSTANCE.deleteProject(BASE_PROJECT_NAME);
        super.tearDown();
    }

    /**
     * Part of VP-2529 was that when the user selects the base viewpoint and all its extensions, the viewpoints were
     * enabled in a pseudo-random order, and some orders (extensions before base) cause problems.
     */
    public void _testEnableBaseAndExtensions() {
        /*
         * The bug is not systematic, it depends on the unpredictable order in which the selected viewpoints are
         * actually enabled, so we try several times.
         */
        for (int i = 0; i < 5; i++) {
            UIResource semanticResource = new UIResource(designerProject, "example.ecore");
            UILocalSession session = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticResource).fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                    .selectNoViewpoint();

            session.changeViewpointSelection(new HashSet<String>(Arrays.asList("Base", "Extension_A", "Extension_B")), Collections.<String> emptySet());
            SWTBotTreeItem mainPackage = session.getSemanticResourceNode(semanticResource).getNode("package");
            UIDiagramRepresentation diagram = session.newDiagramRepresentation("new " + DIAGRAM_NAME, DIAGRAM_NAME).on(mainPackage).withDefaultName().ok();
            /*
             * The next line fails (with an NPE) if the bug in VP-2529 is present, as the pseudo-editor which was opened
             * shows an error message instead of a real diagram.
             */
            diagram.getEditor();
            session.closeAndDiscardChanges();
            designerProject.deleteResource(new UIResource(designerProject, "example.aird"));
        }
    }

    /**
     * In order to ensure that all the dependencies of a viewpoint are enabled when a viewpoint is enabled, a new
     * validation UI has been added to the viewpoints selection dialog. Test that when we try to enable just
     * Extension_A, the UI pops up and refuses to finish the action until we have also selected Base.
     */
    public void testEnableOnlyExtension() {
        UIResource semanticResource = new UIResource(designerProject, "example.ecore");
        UILocalSession session = designerPerspective.openSessionCreationWizardFromSemanticResource(semanticResource).fromAlreadySelectedSemanticResource().withDefaultSessionName().finish()
                .selectNoViewpoint();
        Set<String> viewpointToSelect = Collections.singleton("Extension_A");

        /*
         * We can not use session.changeSiriusSelection() directly, so the following code is copied and adapted from
         * there.
         */
        SWTBotTreeItem rootTreeItem = session.getRootSessionTreeItem().select();
        SWTBotUtils.clickContextMenu(rootTreeItem, ViewpointSelectionDialog.VIEWPOINT_DIALOG_NAME);

        bot.waitUntil(Conditions.shellIsActive(ViewpointSelectionDialog.VIEWPOINT_DIALOG_NAME));
        SWTBot botSiriusSelection = bot.shell(ViewpointSelectionDialog.VIEWPOINT_DIALOG_NAME).bot();

        if (viewpointToSelect != null) {
            Set<String> allSiriusNames = viewpointToSelect;
            Map<String, Boolean> viewpointSelection = new HashMap<>();
            for (String vpName : allSiriusNames) {
                viewpointSelection.put(vpName, viewpointToSelect.contains(vpName));
            }
            if (!viewpointSelection.isEmpty()) {
                for (int rowPosition = 0; rowPosition < botSiriusSelection.table().rowCount(); rowPosition++) {

                    final SWTBotTableItem item = botSiriusSelection.table().getTableItem(rowPosition);
                    final String text = item.getText(0);

                    if (viewpointSelection.containsKey(text)) {
                        item.check();
                        viewpointSelection.remove(text);
                    }
                }
                final SWTBotButton okButton = botSiriusSelection.button("OK");

                /*
                 * There is an invalid selection ("Extension_A" requires "Base"). Check that the 'OK' button is invalid.
                 */
                botSiriusSelection.waitUntil(new DefaultCondition() {

                    @Override
                    public String getFailureMessage() {
                        return "OK button is enabled";
                    }

                    @Override
                    public boolean test() throws Exception {
                        return !okButton.isEnabled();
                    }

                });
            }
        }

        /*
         * Check that the error message exists on the dialog. Throws a WidgetNotFoundException if the message does not
         * exist.
         */
        botSiriusSelection.text(" Extension_A requires: Base");

        /*
         * We got the "error" message, now we complete the selection normally and try to create a diagram.
         */
        bot.button("Cancel").click();
        session.changeViewpointSelection(new HashSet<String>(Arrays.asList("Base", "Extension_A")), Collections.<String> emptySet());
        SWTBotTreeItem mainPackage = session.getSemanticResourceNode(semanticResource).getNode("package");
        UIDiagramRepresentation diagram = session.newDiagramRepresentation("new " + DIAGRAM_NAME, DIAGRAM_NAME).on(mainPackage).withDefaultName().ok();

        /*
         * The next line fails (with an NPE) if the bug in VP-2529 is present, as the pseudo-editor which was opened
         * shows an error message instead of a real diagram.
         */
        diagram.getEditor();
        session.closeAndDiscardChanges();
    }
}
