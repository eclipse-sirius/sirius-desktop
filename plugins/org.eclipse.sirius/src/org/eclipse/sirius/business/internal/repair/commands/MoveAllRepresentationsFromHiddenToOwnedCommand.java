/*******************************************************************************
 * Copyright (c) 2007, 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.repair.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;

/**
 * Default constructor.
 * 
 * @author esteban
 */
public class MoveAllRepresentationsFromHiddenToOwnedCommand extends IdentityCommand {

    private DRepresentationContainer view;

    /**
     * Default constructor.
     * 
     * @param view
     *            {@link DRepresentationContainer}
     */
    public MoveAllRepresentationsFromHiddenToOwnedCommand(DRepresentationContainer view) {
        super();
        this.view = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute() {
        final List<DRepresentation> hiddenReps = new ArrayList<DRepresentation>(view.getHiddenRepresentations());

        for (final DRepresentation rep : hiddenReps) {
            view.getOwnedRepresentations().add(rep);
        }
        view = null;
    }

    /**
     * Overridden to avoid the CommandStack to keep a reference to this command.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canUndo() {
        return false;
    }

}
