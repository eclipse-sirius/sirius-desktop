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
 * This class helps to manipulate a Sirius element in a odesign editor.
 * 
 * @author amartin
 */
public class SiriusBot extends AbstractOdesignTreeItemBot {
    /**
     * The constructor.
     * 
     * @param bot
     *            the bot.
     * @param treeItem
     *            the treeItem to manipulate.
     * @param propertiesView
     *            the properties view to edit the properties of the viewpoint
     *            element.
     */
    public SiriusBot(SWTBot bot, SWTBotTreeItem treeItem, SiriusPropertiesView propertiesView) {
        super(bot, treeItem, propertiesView);
    }

    /**
     * A function who create a diagram in a odesign.
     * 
     * @param diagramName
     *            the name of the diagram to create.
     * @return the SWT diagram created.
     */
    public DiagramDescriptionBot createDiagramDescription(String diagramName) {
        treeItem.contextMenu("New Child").menu("Diagram Description");
        propertiesView.setName(diagramName);
        treeItem.setFocus();
        return new DiagramDescriptionBot(bot, treeItem.getNode(diagramName), propertiesView);
    }

}
