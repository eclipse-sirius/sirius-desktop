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
package org.eclipse.sirius.tests.swtbot.canonicalrefresh;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.sirius.business.api.preferences.SiriusPreferencesKeys;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramNodeEditPart;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UILocalSession;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.ItemEnabledCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.VoidResult;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotShell;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;

/**
 * .
 */
public class PreCommitCanonicalRefreshTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String WIZARD_TITLE = "Selection Wizard";

    private static final String REPRESENTATION_NAME_BORDERED_NODE_ON_NODE = "doremi2253-borderedNodeOnNode";

    private static final String MODEL = "canonical_refresh01.ecore";

    private static final String SESSION_FILE = "canonical_refresh01.aird";

    private static final String VSM_FILE = "canonical_refresh01.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/canonicalrefresh/test01/";

    private static final String FILE_DIR = "/";

    // Point somewhere in diagram
    private static final Point SOMEWHERE_IN_DIAGRAM = new Point(322, 223);

    UILocalSession localSession;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
    }

    private void insertPackageP2(final SWTBotSiriusDiagramEditor editor) {
        UIThreadRunnable.asyncExec(new VoidResult() {
            public void run() {
                editor.activateTool("Package insertion");
                editor.click(SOMEWHERE_IN_DIAGRAM.x, SOMEWHERE_IN_DIAGRAM.y);
            }
        });

        bot.waitUntilWidgetAppears(Conditions.shellIsActive(WIZARD_TITLE));

        SWTBotShell wizard = bot.shell(WIZARD_TITLE);
        wizard.setFocus();

        SWTBot shellBot = new SWTBot(wizard.widget);

        SWTBotTree tree = shellBot.tree();

        // Select last package
        tree.select(tree.rowCount() - 2);

        SWTBotButton button = shellBot.button("Finish");
        shellBot.waitUntil(new ItemEnabledCondition(button));
        button.click();
    }

    /**
     * Activate the auto refresh mode.
     */
    protected void enableAutoRefresh() {
        bot.menu("Window").menu("Preferences").click();
        bot.waitUntil(Conditions.shellIsActive("Preferences"));
        bot.tree().getTreeItem("Sirius").expand().select();
        bot.checkBox("Automatic Refresh").select();
        bot.button("OK").click();
    }

    /**
     * Disable the auto refresh mode.
     */
    protected void disableAutoRefresh() {
        bot.menu("Window").menu("Preferences").click();
        bot.waitUntil(Conditions.shellIsActive("Preferences"));
        bot.tree().getTreeItem("Sirius").expand().select();
        bot.checkBox("Automatic Refresh").deselect();
        bot.button("OK").click();
    }

    /**
     * Ensures that the pre-commit Canonical Synchronization works when adding
     * new DNodes and DEdge in the diagram. <b> Impacted Synchronizers : </b>
     * DDiagramCanonicalSynchronizer * @throws Exception
     */
    public void _testSimpleDiagramSync() {
        changeSiriusPreference(SiriusPreferencesKeys.PREF_AUTO_REFRESH.name(), true);

        final SWTBotSiriusDiagramEditor editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_BORDERED_NODE_ON_NODE,
                REPRESENTATION_NAME_BORDERED_NODE_ON_NODE, DDiagram.class);

        insertPackageP2(editor);
        SWTBotGefEditPart packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();

        // Get the created node
        packageP2 = editor.getEditPart("p2", AbstractDiagramNodeEditPart.class);
        packageP2.select();

    }

}
