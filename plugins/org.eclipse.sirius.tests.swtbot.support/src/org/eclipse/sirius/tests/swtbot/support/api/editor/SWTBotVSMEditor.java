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
package org.eclipse.sirius.tests.swtbot.support.api.editor;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.tests.swtbot.support.api.bot.description.GroupBot;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusPropertiesView;
import org.eclipse.swtbot.eclipse.finder.SWTWorkbenchBot;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.PartInitException;

/**
 * 
 * Help to manipulate an odesign editor.
 * 
 * @author amartin
 * 
 */
public class SWTBotVSMEditor extends SWTBotEditor {

    // private UIResource sessionResource;
    private IPath odesignPath;

    private SiriusPropertiesView propertiesView;

    /**
     * The constructor.
     * 
     * @param reference
     *            the editor reference.
     * @param bot
     *            the bot.
     * @param sessionResource
     *            the URI of the session.
     * @param propertiesView
     *            the properties view.
     * @throws WidgetNotFoundException
     *             the error.
     */
    public SWTBotVSMEditor(IEditorReference reference, SWTWorkbenchBot bot, UIResource sessionResource, SiriusPropertiesView propertiesView) throws WidgetNotFoundException {
        super(reference, bot);
        // this.sessionResource = sessionResource;
        this.propertiesView = propertiesView;
    }

    /**
     * Construct a new instance.
     * 
     * @param reference
     *            the editor reference
     * @param bot
     *            the workbench bot
     * @throws WidgetNotFoundException
     *             if an exception occurs
     */
    public SWTBotVSMEditor(final IEditorReference reference, final SWTWorkbenchBot bot) throws WidgetNotFoundException {
        super(reference, bot);
        try {
            IEditorInput input = reference.getEditorInput();
            odesignPath = getPath(input);
        } catch (PartInitException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private IPath getPath(IEditorInput input) {
        IPath path = null;
        if (input instanceof IFileEditorInput) {
            path = ((IFileEditorInput) input).getFile().getFullPath();
        } else if (input instanceof IPathEditorInput) {
            path = ((IPathEditorInput) input).getPath();
        }
        return path;
    }

    /**
     * Get Group of session tree odesign.
     * 
     * @return Root of session tree.
     */
    public GroupBot getGroup() {

        SWTBotTree tree = bot.treeWithLabel("Resource Set");

        final URI diagramURI = URI.createPlatformResourceURI(odesignPath.toString(), true);

        tree.setFocus();

        // return new GroupBot(bot, new SWTBotTreeItem(tree.widget.getItem(0))
        // .getNode(0).expand(), propertiesView);

        final SWTBotTreeItem treeItem = tree.getTreeItem(diagramURI.toString());

        bot.sleep(250);
        return new GroupBot(bot, treeItem.getNode(0).expand(), propertiesView);
    }

}
