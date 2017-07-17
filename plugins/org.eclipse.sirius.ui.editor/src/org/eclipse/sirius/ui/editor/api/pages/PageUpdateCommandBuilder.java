/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.editor.api.pages;

import org.eclipse.sirius.ui.editor.SessionEditor;
import org.eclipse.sirius.ui.editor.api.pages.PageProviderRegistry.PositioningKind;
import org.eclipse.sirius.ui.editor.internal.pages.RemovePageCommand;
import org.eclipse.sirius.ui.editor.internal.pages.RenameTabLabelCommand;
import org.eclipse.sirius.ui.editor.internal.pages.ReorderPageCommand;

/**
 * Provides commands for {@link AbstractSessionEditorPage} pages to tell owner
 * editor to update it.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class PageUpdateCommandBuilder {
    /**
     * Component providing commands to execute from a {@link SessionEditor}.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public class PageUpdateCommand {
        /**
         * This command remove the page providing it from its editor.
         */
        RemovePageCommand removeCommand;

        /**
         * This command rename the page's tab of its editor.
         */
        RenameTabLabelCommand renameCommand;

        /**
         * This command reorder the page in its editor to a new location.
         */
        ReorderPageCommand reorderCommand;

        /**
         * Returns the command to remove the page providing it from its editor.
         * 
         * @return the command to remove the page providing it from its editor.
         */
        public RemovePageCommand getRemoveCommand() {
            return removeCommand;
        }

        /**
         * Returns the command to rename the page's tab of its editor.
         * 
         * @return the command to rename the page's tab of its editor.
         */
        public RenameTabLabelCommand getRenameCommand() {
            return renameCommand;
        }

        /**
         * Returns the command to reorder the page in its editor to a new
         * location.
         * 
         * @return the command to reorder the page in its editor to a new
         *         location.
         */
        public ReorderPageCommand getReorderCommand() {
            return reorderCommand;
        }

        /**
         * Sets the command to rename the page's tab of its editor.
         * 
         * @param renameCommand
         *            the command to rename the page's tab of its editor.
         */
        public void setRenameCommand(RenameTabLabelCommand renameCommand) {
            this.renameCommand = renameCommand;
        }

        /**
         * Sets the command to remove the page providing it from its editor.
         * 
         * @param removePageCommand
         *            the command to remove the page providing it from its
         *            editor.
         */
        public void setRemoveCommand(RemovePageCommand removePageCommand) {
            this.removeCommand = removePageCommand;
        }

        /**
         * Sets the command to reorder the page in its editor to a new location.
         * 
         * @param reorderCommand
         *            the command to reorder the page in its editor to a new
         *            location.
         */
        public void setReorderCommand(ReorderPageCommand reorderCommand) {
            this.reorderCommand = reorderCommand;
        }
    }

    /**
     * Component providing commands to execute from a {@link SessionEditor}.
     */
    PageUpdateCommand pageUpdateCommand;

    /**
     * Initialize the commands builder.
     */
    public PageUpdateCommandBuilder() {
        pageUpdateCommand = new PageUpdateCommand();
    }

    /**
     * Creates a command to rename the page's tab with the given label.
     * 
     * @param newLabel
     *            the new tab label.
     * @return the command builder.
     */
    public PageUpdateCommandBuilder renameTab(String newLabel) {
        pageUpdateCommand.setRenameCommand(new RenameTabLabelCommand(newLabel));
        return this;
    }

    /**
     * Creates a command to remove the page from its editor.
     * 
     * @return the command builder.
     */
    public PageUpdateCommandBuilder removePage() {
        pageUpdateCommand.setRemoveCommand(new RemovePageCommand());
        return this;
    }

    /**
     * Creates a command to reorder the page regarding the given location
     * information.
     * 
     * @param positioningKind
     *            the kind of positioning to achieve.
     * @param targetPageId
     *            the target page id to place this page relatively to.
     * @return the command builder.
     */
    public PageUpdateCommandBuilder reorderPage(PositioningKind positioningKind, String targetPageId) {
        pageUpdateCommand.setReorderCommand(new ReorderPageCommand(positioningKind, targetPageId));
        return this;
    }

    /**
     * Build the command containing all commands to execute.
     * 
     * @return the command containing all commands to execute.
     */
    public PageUpdateCommand build() {
        return pageUpdateCommand;
    }
}
