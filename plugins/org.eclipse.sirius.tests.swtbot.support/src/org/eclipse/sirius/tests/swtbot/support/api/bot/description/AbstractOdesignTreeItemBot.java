/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.bot.description;

import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusPropertiesView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Mother class for manipulating an VSM element in the VSM editor.
 * 
 * @author amartin
 */
public class AbstractOdesignTreeItemBot {
    SWTBotTreeItem treeItem;

    SWTBot bot;

    SiriusPropertiesView propertiesView;

    /**
     * The constructor.
     * 
     * @param bot
     *            The current bot.
     * @param treeItem
     *            the SWTBotTreeItem of the tree.
     * @param propertiesView
     *            the properties view to use in order edit the element.
     */
    public AbstractOdesignTreeItemBot(SWTBot bot, SWTBotTreeItem treeItem, SiriusPropertiesView propertiesView) {
        this.treeItem = treeItem;
        this.bot = bot;
        this.propertiesView = propertiesView;
    }

}
