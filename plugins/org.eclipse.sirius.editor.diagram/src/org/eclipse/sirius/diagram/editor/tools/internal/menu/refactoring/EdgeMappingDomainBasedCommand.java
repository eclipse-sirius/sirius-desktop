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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.editor.tools.api.menu.AbstractUndoRecordingCommand;

/**
 * Command changing an edge mapping using or not domain based.
 * 
 * @author nlepine
 * 
 */
public class EdgeMappingDomainBasedCommand extends AbstractUndoRecordingCommand {

    private final EObject elementToUpdate;

    /**
     * Create a new command.
     * 
     * @param set
     *            the current resourceSet.
     * @param elementToUpdate
     *            the element to move from border to contained.
     */
    public EdgeMappingDomainBasedCommand(final ResourceSet set, final EObject elementToUpdate) {
        super(set);
        this.elementToUpdate = elementToUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (elementToUpdate instanceof EdgeMapping) {
            ((EdgeMapping) elementToUpdate).setUseDomainElement(!((EdgeMapping) elementToUpdate).isUseDomainElement());
            EdgeMappingRefactoringAction.forceNotification((EdgeMapping) elementToUpdate);
        }
    }

    @Override
    protected String getText() {
        if (elementToUpdate instanceof EdgeMapping && ((EdgeMapping) elementToUpdate).isUseDomainElement()) {
            return "Move to Relation Based Edge";
        }
        return "Move to Element Based Edge";
    }

}
