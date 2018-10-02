/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.bot.description;

import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusPropertiesView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * This class helps to manipulate a Diagram Description element in a odesign
 * editor.
 * 
 * @author amartin
 */
public class DiagramDescriptionBot extends AbstractOdesignTreeItemBot {

    /**
     * The constructor.
     * 
     * @param bot
     *            the bot.
     * @param treeItem
     *            the treItem to manipulate.
     * @param propertiesView
     *            the properties view to edit the properties of the viewpoint
     *            element.
     * 
     */
    public DiagramDescriptionBot(SWTBot bot, SWTBotTreeItem treeItem, SiriusPropertiesView propertiesView) {
        super(bot, treeItem, propertiesView);
    }

}
