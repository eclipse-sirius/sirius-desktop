/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.table.metamodel.table.DTable;
import org.eclipse.sirius.table.metamodel.table.provider.Messages;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.google.common.collect.Lists;

/**
 * Common behaviors for hide/reveal actions. It opens a selection dialog.
 *
 * @author dlecan
 * @param <T>
 *            Current type. May be
 *            {@link org.eclipse.sirius.table.metamodel.table.DLine} or
 *            {@link org.eclipse.sirius.table.metamodel.table.DColumn}
 */
public abstract class AbstractHideRevealAction<T extends EObject> extends AbstractTransactionalTableAction {

    /**
     * Creates a new action with the given image.
     *
     * @param dTable
     *            {@link DTable} to use
     * @param text
     *            the string used as the text for the action, or
     *            <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     * @param editingDomain
     *            The transactional editing domain
     * @param tableCommandFactory
     *            The EMF command factory
     */
    public AbstractHideRevealAction(final DTable dTable, final String text, final ImageDescriptor image, final TransactionalEditingDomain editingDomain,
            final ITableCommandFactory tableCommandFactory) {
        super(dTable, text, image, editingDomain, tableCommandFactory);
    }

    @Override
    public void run() {
        final Collection<T> visibleElements = getInitialVisibleElements();

        final SelectionDialog dlg = createSelectionDialog();
        dlg.setBlockOnOpen(true);
        dlg.setTitle(getTitle());
        dlg.setMessage(getMessage());
        dlg.setInitialSelections(visibleElements.toArray());
        dlg.setHelpAvailable(false);
        dlg.open();

        // User wants to continue his action
        if (Window.OK == dlg.getReturnCode() && dlg.getResult() != null) {
            List<Object> newVisibles = Lists.newArrayList(dlg.getResult());
            CompoundCommand compoundCommand = new CompoundCommand(MessageFormat.format(Messages.Action_setValues, getSetVisibleMethodName()));
            for (T element : getAllElements()) {
                boolean visible = newVisibles.contains(element);
                // XOR operator
                // a ^ b => true if a and b are true or false
                // simultaneously

                // Here, we want to update visibility only if it has
                // changed
                if (visible ^ isVisibleElement(element)) {
                    compoundCommand.append(getTableCommandFactory().buildSetValue(element, getSetVisibleMethodName(), visible));
                }
            }
            getEditingDomain().getCommandStack().execute(compoundCommand);
        }
    }

    /**
     * Get the method name of "setVisible" method.
     *
     * @return The method name.
     */
    protected abstract String getSetVisibleMethodName();

    /**
     * Get initial visible elements.
     *
     * @return The initial visible elements.
     */
    protected abstract Collection<T> getInitialVisibleElements();

    /**
     * Get dialog title name.
     *
     * @return Elements name.
     */
    protected abstract String getTitle();

    /**
     * Get dialog message.
     *
     * @return Elements name.
     */
    protected abstract String getMessage();

    /**
     * Get all current elements.
     *
     * @return All current elements.
     */
    protected abstract List<T> getAllElements();

    /**
     * Create selection dialog.
     *
     * @return Selection dialog.
     */
    protected abstract SelectionDialog createSelectionDialog();

    /**
     * Check if an element is visible.
     *
     * @param element
     *            Element to check.
     * @return <code>true</code> if element is visible, <code>false</code>
     *         otherwise.
     */
    protected abstract boolean isVisibleElement(T element);

}
