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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.description.ContainerMapping;
import org.eclipse.sirius.diagram.description.NodeMapping;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;

/**
 * Command changing a border node to a contained node.
 * 
 * @author cbrun
 * 
 */
public class BorderToContainedCommand extends AbstractUndoRecordingCommand {

    private final EObject elementToMove;

    /**
     * Create a new command.
     * 
     * @param set
     *            the current resourceSet.
     * @param elementToMove
     *            the element to move from border to contained.
     */
    public BorderToContainedCommand(final ResourceSet set, final EObject elementToMove) {
        super(set);
        this.elementToMove = elementToMove;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        final EObject parent = elementToMove.eContainer();
        if (parent instanceof ContainerMapping) {
            if (elementToMove instanceof NodeMapping) {
                ((ContainerMapping) parent).getSubNodeMappings().add((NodeMapping) elementToMove);
                BorderRefactoringAction.forceNotification((NodeMapping) elementToMove);
            }
        }

    }

    @Override
    protected String getText() {
        return "Move from Bordered Node to Contained Node";
    }

}
