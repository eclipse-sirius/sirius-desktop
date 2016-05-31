/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.Messages;

/**
 * Specific command to rename the given representation.
 * 
 * @author mporhel
 * 
 */
public class RenameRepresentationCommand extends RecordingCommand {

    private DRepresentationDescriptor representationDescriptor;

    private String newName;

    /**
     * Specific command to rename the given representation.
     * 
     * @param transDomain
     *            the editing domain.
     * @param repDescriptor
     *            the descriptor of the representation to rename.
     * @param name
     *            the new name.
     */
    public RenameRepresentationCommand(TransactionalEditingDomain transDomain, DRepresentationDescriptor repDescriptor, String name) {
        super(transDomain, Messages.RenameRepresentationCommand_label);
        this.representationDescriptor = repDescriptor;
        this.newName = name;
    }

    @Override
    public boolean canExecute() {
        return representationDescriptor.getRepresentation() == null ? false : super.canExecute();
    }

    @Override
    protected void doExecute() {
        if (representationDescriptor != null) {
            representationDescriptor.setName(newName);
            representationDescriptor.getRepresentation().setName(newName);
        }
    }

}
