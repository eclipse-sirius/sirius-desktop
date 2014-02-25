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
package org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring.border;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.diagram.description.AbstractNodeMapping;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.diagram.editor.tools.internal.menu.refactoring.EdgeMappingRefactoringAction;
import org.eclipse.sirius.editor.tools.api.menu.AbstractEObjectRefactoringAction;
import org.eclipse.ui.IEditorPart;

/**
 * Action changing a node and changing it as a border node.
 * 
 * @author cbrun
 * 
 */
public class BorderRefactoringAction extends AbstractEObjectRefactoringAction {
    private static final String TEXT_IF_DISABLE = "Move to Contained/Bordered Node";

    /**
     * Create the action.
     * 
     * @param editor
     *            the current editor.
     * @param selection
     *            the current selection.
     */
    public BorderRefactoringAction(final IEditorPart editor, final ISelection selection) {
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
            final EObject elementToMove = selection.iterator().next();
            if (elementToMove instanceof AbstractNodeMapping) {
                setSelectionValid(true);
                setTextIfDisable(TEXT_IF_DISABLE);
                if (isInBorder(elementToMove) && mightHaveContainedNodes(elementToMove.eContainer())) {
                    result = new BorderToContainedCommand(arg0.getResourceSet(), elementToMove);
                } else if (!isInBorder(elementToMove) && mightHaveBorderedNodes(elementToMove.eContainer())) {
                    result = new ContainedToBorderCommand(arg0.getResourceSet(), elementToMove);
                }
            }
        }
        return result;
    }

    private boolean mightHaveBorderedNodes(final EObject container) {
        return container instanceof AbstractNodeMapping;
    }

    private boolean mightHaveContainedNodes(final EObject container) {
        return container instanceof ContainerMapping;
    }

    private boolean isInBorder(final EObject elementToMove) {
        return elementToMove.eContainingFeature() == DescriptionPackage.eINSTANCE.getAbstractNodeMapping_BorderedNodeMappings();
    }

    /**
     * Force a notification on a node mapping to be refresh (label and image).
     * 
     * @param elementToMove
     *            the node mapping to be notified.
     */
    public static void forceNotification(NodeMapping elementToMove) {
        String name = elementToMove.getName();
        elementToMove.setName("_refactoring");
        elementToMove.setName(name);
        EdgeMappingRefactoringAction.refreshSelection(elementToMove);
    }
}
