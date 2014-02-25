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
 * Command changing a contained element to a bordered one.
 * 
 * @author cbrun
 * 
 */
public class ContainedToBorderCommand extends AbstractUndoRecordingCommand {

    private final EObject elementToMove;

    private final EObject parent;

    /**
     * Create a new command.
     * 
     * @param set
     *            the current resourceSet.
     * @param elementToMove
     *            the element to move from contained to border.
     */
    public ContainedToBorderCommand(final ResourceSet set, final EObject elementToMove) {
        super(set);
        this.elementToMove = elementToMove;
        this.parent = elementToMove.eContainer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        final ContainerMapping parentMapping = (ContainerMapping) parent;
        parentMapping.getBorderedNodeMappings().add((NodeMapping) elementToMove);
        BorderRefactoringAction.forceNotification((NodeMapping) elementToMove);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean canExecute() {
        return super.canExecute() && parent instanceof ContainerMapping && elementToMove instanceof NodeMapping;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getText() {
        return "Move from Contained Node to Bordered Node";
    }

}
