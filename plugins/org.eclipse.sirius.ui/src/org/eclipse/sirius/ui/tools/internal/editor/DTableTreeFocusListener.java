/*******************************************************************************
 * Copyright (c) 2007, 2008, 2020 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.actions.ActionFactory;

/**
 * A focusListener which disabled the EMF copy/cut/paste/delete actions.
 *
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class DTableTreeFocusListener implements FocusListener {
    /**
     * Action to disabled the default action.
     *
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    private static final class DisabledAction extends Action {

        /**
         * The default constructor.
         */
        DisabledAction() {
            setEnabled(false);
        }
    }

    private final AbstractDTreeEditor tableEditor;

    private final IAction emfCutAction;

    private final IAction emfCopyAction;

    private final IAction emfPasteAction;

    private final IAction emfDeleteAction;

    private final IAction disabledAction;

    /**
     * The default constructor.
     *
     * @param tableEditor
     *            The current table editor
     * @param tree
     *            The current tree
     */
    public DTableTreeFocusListener(final AbstractDTreeEditor tableEditor, final Tree tree) {
        this.tableEditor = tableEditor;
        emfCutAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.CUT.getId());
        emfCopyAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.COPY.getId());
        emfPasteAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.PASTE.getId());
        emfDeleteAction = tableEditor.getActionBars().getGlobalActionHandler(ActionFactory.DELETE.getId());
        disabledAction = new DisabledAction();
    }

    /**
     * We reset the actions with the EMF actions.<BR>
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.events.FocusListener#focusLost(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusLost(FocusEvent e) {
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), emfCutAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), emfCopyAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), emfPasteAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.DELETE.getId(), emfDeleteAction);
        tableEditor.getActionBars().updateActionBars();
    }

    /**
     * We override the EMF actions with the standard action.<BR>
     * {@inheritDoc}
     *
     * @see org.eclipse.swt.events.FocusListener#focusGained(org.eclipse.swt.events.FocusEvent)
     */
    @Override
    public void focusGained(FocusEvent e) {
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), disabledAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), disabledAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), disabledAction);
        tableEditor.getActionBars().setGlobalActionHandler(ActionFactory.DELETE.getId(), disabledAction);
        tableEditor.getActionBars().updateActionBars();
    }
}
