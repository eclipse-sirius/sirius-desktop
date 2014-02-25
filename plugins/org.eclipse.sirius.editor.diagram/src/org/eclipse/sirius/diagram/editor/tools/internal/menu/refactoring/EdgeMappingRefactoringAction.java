/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * Action changing a node and changing it as a border node.
 * 
 * @author nlepine
 * 
 */
public class EdgeMappingRefactoringAction extends AbstractEObjectRefactoringAction {
    private static final String TEXT_IF_DISABLE = "Move to Element-Based/Relation-Based Edge";

    /**
     * Create the action.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public EdgeMappingRefactoringAction(final IEditorPart editor, final ISelection selection) {
        super(editor, selection);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Command buildActionCommand(final EditingDomain arg0, final Collection<EObject> selection) {
        Command result = UnexecutableCommand.INSTANCE;
        setSelectionValid(false);
        if (selection.size() == 1) {
            final EObject elementToUpdate = selection.iterator().next();
            if (elementToUpdate instanceof EdgeMapping) {
                setSelectionValid(true);
                setTextIfDisable(TEXT_IF_DISABLE);
                result = new EdgeMappingDomainBasedCommand(arg0.getResourceSet(), elementToUpdate);
            }
        }
        return result;
    }

    /**
     * Force a notification on a node mapping to be refresh (label and image).
     * 
     * @param elementToUpdate
     *            the node mapping to be notified.
     */
    public static void forceNotification(EdgeMapping elementToUpdate) {
        String name = elementToUpdate.getName();
        elementToUpdate.setName("_refactoring");
        elementToUpdate.setName(name);
        EdgeMappingRefactoringAction.refreshSelection(elementToUpdate);
    }

    /**
     * Refreshes the current selection by using the selection provider of the
     * current active site.
     * 
     * @param elementMapping
     *            the element Mapping that must be refreshed.
     */
    public static void refreshSelection(DiagramElementMapping elementMapping) {
        Option<ISelectionProvider> activeSiteSelectionProvider = EdgeMappingRefactoringAction.getActiveSiteSelectionProvider();
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
