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
package org.eclipse.sirius.tests.swtbot.support.api.business;

import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Object to manage tree.
 * 
 * @author jdupont
 */
public class UITreeRepresentation extends AbstractUIRepresentation<SWTBotEditor> {

    /**
     * Constructor.
     * 
     * @param treeItem
     *            Tree item.
     * 
     * @param representationName
     *            Representation name.
     */
    public UITreeRepresentation(final SWTBotTreeItem treeItem, final String representationName) {
        super(treeItem, representationName);
    }

    /**
     * Open current representation. Does nothing if current tree was created in
     * this test session instead of being simply opened.
     * 
     * @return Current representation.
     */
    public UITreeRepresentation open() {
        super.doOpen();
        return this;
    }

    /**
     * save current representation.
     * 
     * @return Current representation.
     */
    public UITreeRepresentation save() {
        super.doSave();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SWTBotEditor getEditor() {
        final SWTBotEditor editorByTitle = designerBot.editorByTitle(getRepresentationName());
        // As of 2009-12-18, SWTBotEditor#setFocus() doesn't do anything
        editorByTitle.show();
        return editorByTitle;
    }

    /**
     * Get tree.
     * 
     * @return Current tree.
     */
    public SWTBotTree getTree() {
        return getEditorBot().tree();
    }

    /**
     * Get editor bot.
     * 
     * @return Editor bot.
     */
    public SWTBot getEditorBot() {
        return getEditor().bot();
    }

}
