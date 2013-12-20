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
package org.eclipse.sirius.editor.tools.internal.menu.refactoring;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.ui.IEditorPart;

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
        AbstractEObjectRefactoringAction.refreshSelection(elementToUpdate);
    }
}
