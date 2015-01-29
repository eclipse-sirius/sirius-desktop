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
package org.eclipse.sirius.tests.swtbot.modelexplorer;

import org.eclipse.sirius.tests.support.api.PluginVersionCompatibility;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckNbVisibleElementsInTree;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.osgi.framework.Version;

/**
 * A SWTBot test for checking the filter section of the Model Explorer view (See
 * VP-3037).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class ModelExplorerFilterTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String VIEWPOINT_NAME = "Ticket_2298";

    private static final String MODEL_FILE = "My.ecore";

    private static final String SESSION_FILE = "My.aird";

    private static final String VSM_FILE = "ticket2298.odesign";

    private static final String IMAGE_FILE = "Thing.gif";

    private static final String DATA_UNIT_DIR = "data/unit/edgeCreation/";

    private static final String REPRESENTATION_DESC_2298_NAME = "Diag2298";

    private UIResource sessionAirdResource;

    SWTBot modelExplorerViewBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL_FILE, SESSION_FILE, VSM_FILE, IMAGE_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource, true);
        
        SWTBotView modelExplorerView = bot.viewById(IModelExplorerView.ID);
        modelExplorerView.setFocus();
        modelExplorerViewBot = modelExplorerView.bot();
    }

    /**
     * Test the filter function of the Model Explorer view.
     */
    public void testTheFilterArea() {
        boolean afterEclipse3_5 = true;
        if (new PluginVersionCompatibility("org.eclipse.ui.navigator").compareTo(new Version("3.4.2.M20100120-0800")) <= 0) {
            afterEclipse3_5 = false;
        }
        // Check that the filter area is here.
        try {
            modelExplorerViewBot.text();
        } catch (WidgetNotFoundException e) {
            fail("A text area should be displayed in the Model Explorer view to filter this view.");
        }
        bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 1, "The model explorer should display only the project at starting."));
        // Check that nothing is displayed if there is no match
        expandIfNeeded(!afterEclipse3_5);
        modelExplorerViewBot.text().setText("noMatch");
        bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 0, "The model explorer should display nothing if the filter does not match with anything."));
        // Check that the contents of the representations file is filtered
        expandIfNeeded(!afterEclipse3_5);
        modelExplorerViewBot.text().setText(VIEWPOINT_NAME);
        bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 4, "The model explorer should display all emements needed to access the category \"" + VIEWPOINT_NAME
                + "\" (project/representationFile/Folder representationPerCategory/category)."));
        // Check that the contents of the representations file AND the semantic
        // model are filtered
        expandIfNeeded(!afterEclipse3_5);
        modelExplorerViewBot.text().setText(REPRESENTATION_DESC_2298_NAME);
        bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 9, "The model explorer should display all emements needed to access the diagram \"" + REPRESENTATION_DESC_2298_NAME
                + "\" (in representationsPerCategory and in semantic model)."));
        expandIfNeeded(true);
        // Check that nothing is filtered if there is no filter
        modelExplorerViewBot.text().setText("");
        bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 18, "The model explorer should display a normal view if there is no filter."));
    }

    /**
     * For Eclipse 3.5 (and before), we need a expand all on treeViewer because
     * in Eclipse 3.5, the filter is only applied on expanded elements.
     * 
     * @param expandNeeded
     *            true if the expandAll is needed
     */
    protected void expandIfNeeded(boolean expandNeeded) {
        if (expandNeeded) {
            // Check that nothing is filtered if there is no filter
            modelExplorerViewBot.text().setText("");
            bot.waitUntil(new CheckNbVisibleElementsInTree(modelExplorerViewBot.tree(), 1, "The model explorer should display a normal view if there is no filter."));
            // Expand all (because in Eclipse 3.5, the filter only applied on
            // expand elements
            modelExplorerViewBot.tree().expandNode(getProjectName(), true);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        modelExplorerViewBot.text().setText("");

        sessionAirdResource = null;
        modelExplorerViewBot = null;

        super.tearDown();
    }
}
