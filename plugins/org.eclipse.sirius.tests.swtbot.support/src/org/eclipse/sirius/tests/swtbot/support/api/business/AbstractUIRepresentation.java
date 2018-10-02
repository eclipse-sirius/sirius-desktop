/**
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES
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
package org.eclipse.sirius.tests.swtbot.support.api.business;

import org.eclipse.sirius.tests.swtbot.support.api.bot.SWTDesignerBot;
import org.eclipse.sirius.tests.swtbot.support.api.business.sessionbrowser.AbstractUIElementWithTreeItem;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotCommonHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.IEditorPart;

/**
 * Object to represent representations (diagrams, tables, ...).
 * 
 * @author dlecan
 * @param <E>
 *            Type of editor.
 */
public abstract class AbstractUIRepresentation<E extends SWTBotEditor> extends AbstractUIElementWithTreeItem {

    /**
     * Inner special SWTBot.
     */
    protected SWTDesignerBot designerBot = new SWTDesignerBot();

    private final String representationName;

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item.
     * 
     * @param representationName
     *            Representation name.
     */
    public AbstractUIRepresentation(final SWTBotTreeItem treeItem, final String representationName) {
        super(treeItem);
        this.representationName = representationName;
    }

    /**
     * Close current representation.
     */
    public void close() {
        SWTBotCommonHelper.closeCurrentEditor();
    }

    /**
     * Open current representation. Does nothing if current diagram was created
     * in this test session instead of being simply opened.
     */
    protected void doOpen() {

        if (getTreeItem() != null) {

            getTreeItem().select().setFocus();
            getTreeItem().doubleClick();
        }

        SWTBotUtils.waitProgressMonitorClose("Open representation");
    }

    /**
     * save current representation.
     */

    protected void doSave() {
        SWTBotCommonHelper.saveCurrentEditor();
    }

    /**
     * Delete this representation.
     */
    public void delete() {
        SWTBotUtils.clickContextMenu(getTreeItem(), "Delete");
    }

    /**
     * Returns the representationName.
     * 
     * @return The representationName.
     */
    protected String getRepresentationName() {
        return representationName;
    }

    /**
     * Get the associated {@link DRepresentation}.
     * 
     * @return the associated {@link DRepresentation}
     */
    public DRepresentation getDRepresentation() {
        DRepresentation dRepresentation = null;
        IEditorPart editorPart = getEditor().getReference().getEditor(false);
        if (editorPart instanceof DialectEditor) {
            DialectEditor dialectEditor = (DialectEditor) editorPart;
            dRepresentation = dialectEditor.getRepresentation();
        }
        return dRepresentation;
    }

    /**
     * Get Editor.
     * 
     * @return Editor.
     */
    public abstract E getEditor();
}
