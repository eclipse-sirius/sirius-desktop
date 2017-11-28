/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.provider;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.sirius.table.ui.tools.internal.editor.AbstractDTableEditor;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.ui.actions.ActionFactory;

/**
 * A focusListener which replaces the EMF copy/cut/paste actions with standard
 * actions.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableCellEditorFocusListener implements FocusListener {
    /**
     * Action to cut the content of the cell in the clipboard.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static class StandardCutAction extends Action {
        TextCellEditor textCellEditor;

        /**
         * The default constructor.
         * 
         * @param textCellEditor
         *            The current cell editor
         */
        StandardCutAction(final TextCellEditor textCellEditor) {
            this.textCellEditor = textCellEditor;
        }

        @Override
        public void run() {
            textCellEditor.performCut();
        }
    }

    /**
     * Action to copy the content of the cell in the clipboard.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static class StandardCopyAction extends Action {
        TextCellEditor textCellEditor;

        /**
         * The default constructor.
         * 
         * @param textCellEditor
         *            The current cell editor
         */
        StandardCopyAction(final TextCellEditor textCellEditor) {
            this.textCellEditor = textCellEditor;
        }

        @Override
        public void run() {
            textCellEditor.performCopy();
        }
    }

    /**
     * Action to paste the content of the cell in the clipboard.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static class StandardPasteAction extends Action {
        TextCellEditor textCellEditor;

        /**
         * The default constructor.
         * 
         * @param textCellEditor
         *            The current cell editor
         */
        StandardPasteAction(final TextCellEditor textCellEditor) {
            this.textCellEditor = textCellEditor;
        }

        @Override
        public void run() {
            textCellEditor.performPaste();
        }
    }

    AbstractDTableEditor tableEditor;

    TextCellEditor textCellEditor;

    IAction emfCutAction;

    IAction emfCopyAction;

    IAction emfPasteAction;

    IAction standardCut;

    IAction standardCopy;

    IAction standardPaste;

    /**
     * The default constructor.
     * 
     * @param tableEditor
     *            The current table editor
     * @param textCellEditor
     *            The current cellEditor
     */
    public DTableCellEditorFocusListener(final AbstractDTableEditor tableEditor, final TextCellEditor textCellEditor) {
        this.tableEditor = tableEditor;
        this.textCellEditor = textCellEditor;
        emfCutAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.CUT.getId());
        emfCopyAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.COPY.getId());
        emfPasteAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.PASTE.getId());

        standardCut = new StandardCutAction(textCellEditor);
        standardCopy = new StandardCopyAction(textCellEditor);
        standardPaste = new StandardPasteAction(textCellEditor);
    }

    /**
     * We reset the actions with the EMF actions.<BR> {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    public void focusLost(final FocusEvent e) {
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), emfCutAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), emfCopyAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), emfPasteAction);
        tableEditor.getActionBars().updateActionBars();
    }

    /**
     * We override the EMF actions with the standard action.<BR> {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    public void focusGained(final FocusEvent e) {
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), standardCut);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), standardCopy);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), standardPaste);
        tableEditor.getActionBars().updateActionBars();
    }
}
