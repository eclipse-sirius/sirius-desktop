/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.properties.tools.internal.menu;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.viewpoint.description.Group;
import org.eclipse.ui.IEditorPart;

/**
 * Action for inlining the default rules in the current VSM.
 * 
 * @author mbats
 * 
 */
public class ImportingDefaultPropertiesViewDescriptionAction extends StaticSelectionCommandAction {

    /**
     * A field indicating if the selection used to construct the action is
     * valid.
     */
    private boolean isSelectionValid;

    /**
     * Create a new importing action.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public ImportingDefaultPropertiesViewDescriptionAction(final IEditorPart editor, final ISelection selection) {
        super(editor);
        isSelectionValid = true;
        configureAction(selection);
    }

    @Override
    public String getText() {
        if (command != null) {
            return command.getLabel();
        } else {
            return super.getText();
        }
    }

    @Override
    protected Command createActionCommand(final EditingDomain editingDomain, final Collection<?> selection) {

        final Collection<EObject> selected = new ArrayList<EObject>();
        for (final Object object : selection) {
            if (object instanceof EObject) {
                selected.add((EObject) object);
            }
        }
        return buildActionCommand(editingDomain, selected);
    }

    /**
     * Build the command for the importing action.
     * 
     * @param editingDomain
     *            the current editing domain.
     * @param selection
     *            the selected eobjects
     * @return the builded action command.
     */
    protected Command buildActionCommand(final EditingDomain editingDomain, final Collection<EObject> selection) {
        Command result = UnexecutableCommand.INSTANCE;
        this.isSelectionValid = false;
        if (selection.size() == 1) {
            final EObject selectedEObject = selection.iterator().next();
            if (selectedEObject instanceof Group) {
                this.isSelectionValid = true;
                result = new ImportingDefaultPropertiesViewDescriptionCommand(editingDomain.getResourceSet(), (Group) selectedEObject);
            }
        }
        return result;
    }

    /**
     * <p>
     * Indicates if the selection used to build the action is valid.
     * </p>
     * 
     * <p>
     * This information will be used by the
     * {@link ImportingMenu}
     * to decide whether this action should be added to the menu or not. <br/>
     * 4 cases can happen :
     * <ul>
     * <li>isSelectionValid && isEnabled -> the Action is shown an can be
     * executed</li>
     * <li>isSelectionValid && !(isEnabled) -> the Action is shown an cannot be
     * executed</li>
     * <li>!(isSelectionValid) && isEnabled -> this case cannot happen</li>
     * <li>!(isSelectionValid) && !(isEnabled) -> the Action hidden (not added
     * to the refactor menu)</li>
     * </ul>
     * </p>
     * 
     * @return <code>true</code> if the selection is valid.
     */
    public boolean isSelectionValid() {
        return this.isSelectionValid;
    }
}
