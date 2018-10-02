/**
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * A condition to wait until the project dependencies item is diplayed.
 * 
 * @author <a href="mailto:maxime.porhel@obeo.fr">Maxime Porhel</a>
 */
public class ProjectDependenciesItemDisplayed extends DefaultCondition implements ICondition {

    private final SWTBot modelExplorerViewBot;

    private final String projectName;

    /**
     * Constructor.
     * 
     * @param modelExplorerViewBot
     *            the view bot
     * @param projectName
     *            the project to check
     */
    public ProjectDependenciesItemDisplayed(SWTBot modelExplorerViewBot, String projectName) {
        this.modelExplorerViewBot = modelExplorerViewBot;
        this.projectName = projectName;
    }

    @Override
    public boolean test() throws Exception {
        SWTBotTree tree = modelExplorerViewBot.tree();
        SWTBotTreeItem swtBotTreeItem = tree.expandNode(projectName);
        SWTBotTreeItem firstNode = swtBotTreeItem.getNode(0);
        return "Project Dependencies".equals(firstNode.getText());
    }

    @Override
    public String getFailureMessage() {
        return "The project dependencies item was not diplayed for " + projectName;
    }
}
