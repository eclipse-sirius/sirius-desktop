/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot.propertypage;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.gmf.runtime.common.ui.dialogs.PropertiesDialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.view.DesignerViews;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.internal.session.SiriusSessionDetailsPropertyPage;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.PreferencesUtil;

/**
 * Tests to check the Sirius Session Details property page.
 * 
 * @author lfasani
 */
public class SiriusSessionDetailsPropertyPageTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/propertypage/";

    private static final String MODEL = "My.ecore";

    private static final String MODEL2 = "My.interactions";

    private static final String SESSION_FILE = "representations.aird";

    private static final String REPRESENTATION_DECRIPTION_NAME = "Entities";

    private static final String REPRESENTATION_NAME = "root package entities";

    private Session session;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, MODEL2, SESSION_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, "/", SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        closeOutline();
        session = localSession.getOpenedSession();
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(session, REPRESENTATION_DECRIPTION_NAME, REPRESENTATION_NAME, DDiagram.class);
    }

    @Override
    protected void tearDown() throws Exception {
        // Reopen outline
        new DesignerViews(bot).openOutlineView();
        SWTBotUtils.waitAllUiEvents();
        super.tearDown();
    }

    /**
     * Tests the content of {@link SiriusSessionDetailsPropertyPage}
     */
    public void testSiriusSessionDetailsPage() {
        // The page is called from its contribution instead of the contextual menu via
        // SWTBot because most of the time SWTBot wrongly focuses on the properties view instead.
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(designerProject.getName());
        IFile file = project.getFile(localSession.getRepresentationsFileTreeItem().getText());
        IPreferenceNode[] propertiesContributors = PreferencesUtil.propertiesContributorsFor(file);
        IPreferenceNode siriusSessionDetailsPropertyPage = null;
        for (IPreferenceNode iPreferenceNode : propertiesContributors) {
            if (iPreferenceNode.getId().equals("org.eclipse.sirius.ui.SiriusSessionDetailsPropertyPage")) {
                siriusSessionDetailsPropertyPage = iPreferenceNode;
            }
        }
        // Check that the page is available
        assertNotNull("The Sirius Session Details property page has not been found", siriusSessionDetailsPropertyPage);
        PreferenceManager preferenceManager = new PreferenceManager();
        preferenceManager.addToRoot(siriusSessionDetailsPropertyPage);

        // --------------------
        // Property page test
        // Open the page in a Properties Dialog
        UIThreadRunnable.asyncExec(new VoidResult() {
            @Override
            public void run() {
                final Shell activeShell = PlatformUI.getWorkbench().getDisplay().getActiveShell();
                PropertiesDialog propertiesDialog = new PropertiesDialog(activeShell, preferenceManager);
                propertiesDialog.create();
                propertiesDialog.open();
            }
        });
        bot.waitUntil(Conditions.shellIsActive("Properties"));

        // Check the content of the Repository Information page
        SWTBot shellBot = bot.shell("Properties").bot();
        SWTBotTreeItem treeItem = shellBot.tree().getTreeItem("Sirius Session Details");
        treeItem.select();
        shellBot.waitUntil(new TreeItemSelected(treeItem));

        bot.waitUntil(new DefaultCondition() {
            @Override
            public boolean test() throws Exception {
                return shellBot.text(0).getText().contains("*** Resources");
            }

            @Override
            public String getFailureMessage() {
                return "Expected message not diplayed";
            }
        });
        String text = shellBot.text(0).getText();
        text = text.replaceFirst(".*\n", "");
        text = text.replaceAll("[0-9]* bytes", "x bytes");
        assertEquals("Bad session details", getExpectedSessionDetails(), text);

        shellBot.button("Close").click();
        Session session = localSession.getOpenedSession();
        session.close(new NullProgressMonitor());
    }

    private String getExpectedSessionDetails() {
        String expectedReport = """

                *** Dependencies

                General projects dependencies (direct and transitive) (0)

                Image projects dependencies (direct) (0)

                *** Resources

                Session Resources (1)
                  platform:/resource/DesignerTestProject/representations.aird - 227 elements - x bytes

                Semantic Resources (2)
                  platform:/resource/DesignerTestProject/My.ecore - 7 elements - x bytes
                  platform:/resource/DesignerTestProject/My.interactions - 2 elements - x bytes

                *** Viewpoints

                Active viewpoints (2)
                  Interactions loaded from resource platform:/plugin/org.eclipse.sirius.sample.interactions.design/description/interaction.odesign
                  Design loaded from resource platform:/plugin/org.eclipse.sirius.sample.ecore.design/description/ecore.odesign

                Inactive viewpoints (0)

                *** Representations

                All representations: 6
                  Diagram: 4
                  Sequence: 1
                  EditionTable: 1
                  CrossTable: 0
                  Tree: 0

                Loaded representations:  4
                Representation elements in loaded representations:  18

                Loaded representations containing elements with no semantic target (red cross decorator) (1)
                  Classes in root package - uid: _KjzRUGu6EeucgZBzLx9xaw
                Tip: The next (manual or automatic) refresh  will remove those elements. The red cross is displayed when the represented element has been detached or deleted from the model.

                Invalid representations (3)
                  no target package entities BROKEN NoTarget - uid: _XlP0gAj6Ee2OwOZgulCHPg
                  root package entities BROKEN NoRepPath - uid: _TMvtwAj7Ee2OwOZgulCHPg
                  root package entities BROKEN NoRep - uid: _Uf3ihgj7Ee2OwOZgulCHPg
                Information: A representation is invalid when:
                  - it has no target represented element or its target element has been detached or deleted
                  - it can not be reached because it has been deleted or because of a technical issue

                Representation descriptors details (6)
                  root package entities - uid: _8t7_oGu3EeuuXbLvG4gakA - description: Entities - viewpoint: Design - repPath: platform:/resource/DesignerTestProject/representations.aird#_8tukQGu3EeuuXbLvG4gakA -> {eClass: ecore::EPackage, name: root}  [Loaded][Diagram]
                  Classes in root package - uid: _KjzRUGu6EeucgZBzLx9xaw - description: Classes - viewpoint: Design - repPath: platform:/resource/DesignerTestProject/representations.aird#_Kh3-sGu6EeucgZBzLx9xaw -> {eClass: ecore::EPackage, name: root}  [Loaded][EditionTable]
                  no target package entities BROKEN NoTarget - uid: _XlP0gAj6Ee2OwOZgulCHPg - description: Entities - viewpoint: Design - repPath: platform:/resource/DesignerTestProject/representations.aird#_XlNYQAj6Ee2OwOZgulCHPg -> null  [Invalid][Loaded][Diagram]
                  root package entities BROKEN NoRepPath - uid: _TMvtwAj7Ee2OwOZgulCHPg - description: Entities - viewpoint: Design - repPath: null -> {eClass: ecore::EPackage, name: root}  [Invalid][Diagram]
                  root package entities BROKEN NoRep - uid: _Uf3ihgj7Ee2OwOZgulCHPg - description: Entities - viewpoint: Design - repPath: platform:/resource/DesignerTestProject/representations.aird#_Uf2UYAj7Ee2OwOZgulCHPg -> {eClass: ecore::EPackage, name: root}  [Invalid][Diagram]
                  Sequence Diagram on rootInteraction - uid: _3xGxUDagEe6u54Qv7g9TQA - description: Sequence Diagram on Interaction - viewpoint: Interactions - repPath: platform:/resource/DesignerTestProject/representations.aird#_3xFjMDagEe6u54Qv7g9TQA -> {eClass: interactions::Interaction, name: rootInteraction}  [Loaded][Sequence]

                Representations opened in an editor (1)
                  root package entities - uid: _8t7_oGu3EeuuXbLvG4gakA
                 """;
        return expectedReport;
    }
}
