/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.dialect.command;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Specific command to rename the given representation.
 * 
 * @author mporhel
 * 
 */
public class RenameRepresentationCommand extends RecordingCommand {

    private DRepresentation representation;

    private String newName;

    /**
     * Specific command to rename the given representation.
     * 
     * @param transDomain
     *            the editing domain.
     * @param selection
     *            the representation to rename.
     * @param name
     *            the new name.
     */
    public RenameRepresentationCommand(TransactionalEditingDomain transDomain, DRepresentation selection, String name) {
        super(transDomain, Messages.RenameRepresentationCommand_label);
        this.representation = selection;
        this.newName = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        if (representation != null) {
            representation.setName(newName);
        }
    }

}
