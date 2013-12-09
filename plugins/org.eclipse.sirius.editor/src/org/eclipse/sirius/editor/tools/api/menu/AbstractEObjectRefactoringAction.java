/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.ui.action.StaticSelectionCommandAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DiagramElementMapping;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Action for a refactoring concerning an EObject.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractEObjectRefactoringAction extends StaticSelectionCommandAction {

    /**
     * A field indicating if the selection used to construct the action is
     * valid.
     */
    private boolean isSelectionValid;

    /**
     * The text associated to this Action if if it has a valid selection but
     * isn't enabled.
     */
    private String textIfDisable;

    /**
     * Create a new refactoring action.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public AbstractEObjectRefactoringAction(final IEditorPart editor, final ISelection selection) {
        super(editor);
        isSelectionValid = true;
        configureAction(selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getText() {
        if (command != null) {
            return isEnabled() ? command.getLabel() : getTextIfDisable();
        } else {
            return super.getText();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command createActionCommand(final EditingDomain arg0, final Collection<?> arg1) {

        final Collection<EObject> selected = new ArrayList<EObject>();
        for (final Object object : arg1) {
            if (object instanceof EObject) {
                selected.add((EObject) object);
            }
        }
        return buildActionCommand(arg0, selected);
    }

    /**
     * build the command for the refactoring action.
     * 
     * @param arg0
     *            the current editing domain.
     * @param selection
     *            the selected eobjects.
     * @return the builded action command.
     */
    protected abstract Command buildActionCommand(EditingDomain arg0, Collection<EObject> selection);

    /**
     * <p>
     * Indicates if the selection used to build the action is valid.
     * </p>
     * 
     * <p>
     * This information will be used by the
     * {@link org.eclipse.sirius.editor.tools.internal.menu.refactoring.RefactoringMenu}
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

    /**
     * Sets a value that indicates if the selection used to build the action is
     * valid.
     * 
     * @param valid
     *            the new value.
     */
    protected void setSelectionValid(boolean valid) {
        this.isSelectionValid = valid;
    }

    /**
     * Returns the text associated to this Action if it has a valid selection
     * but isn't enabled.
     * 
     * @return the text associated to this Action if it has a valid selection
     *         but isn't enabled
     */
    protected String getTextIfDisable() {
        return this.textIfDisable;
    }

    /**
     * Sets the text associated to this Action if if it has a valid selection
     * but isn't enabled. This text should still explain the expected behavior
     * of this action.
     * 
     * @param textForInvalidSelection
     *            the text associated to this Action if it has a valid selection
     *            but isn't enabled
     */
    protected void setTextIfDisable(String textForInvalidSelection) {
        this.textIfDisable = textForInvalidSelection;
    }

    /**
     * Refreshes the current selection by using the selection provider of the
     * current active site.
     * 
     * @param elementMapping
     *            the element Mapping that must be refreshed.
     */
    protected static void refreshSelection(DiagramElementMapping elementMapping) {
        Option<ISelectionProvider> activeSiteSelectionProvider = AbstractEObjectRefactoringAction.getActiveSiteSelectionProvider();
        if (activeSiteSelectionProvider.some()) {
            ISelectionProvider selectionProvider = activeSiteSelectionProvider.get();
            if (selectionProvider.getSelection() instanceof TreeSelection) {
                TreeSelection newSelection = new TreeSelection(((TreeSelection) selectionProvider.getSelection()).getPathsFor(elementMapping));
                selectionProvider.setSelection(newSelection);
            }
        }
    }

    /**
     * Returns the selectionProvider of the current Active Site.
     * 
     * @return the selectionProvider of the current Active Site
     */
    private static Option<ISelectionProvider> getActiveSiteSelectionProvider() {
        Option<IWorkbenchPartSite> site = getSite();
        if (site.some()) {
            ISelectionProvider selectionProvider = site.get().getSelectionProvider();
            if (selectionProvider != null) {
                return Options.newSome(selectionProvider);
            }
        }
        return Options.newNone();
    }

    private static Option<IWorkbenchPartSite> getSite() {
        IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (activeWorkbenchWindow != null) {
            IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();
            if (activePage != null) {
                IWorkbenchPart activePart = activePage.getActivePart();
                if (activePart != null) {
                    return Options.fromNullable(activePart.getSite());
                }
            }
        }
        return Options.newNone();
    }
}
