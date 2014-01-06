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
package org.eclipse.sirius.tree.ui.tools.internal.editor.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.ui.tools.api.actions.AbstractCreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 */
public class CreateRepresentationFromRepresentationCreationDescription extends AbstractCreateRepresentationFromRepresentationCreationDescription {

    /**
     * Build the action.
     * 
     * @param desc
     *            {@link RepresentationCreationDescription} to use.
     * @param target
     *            element on which the user requested the creation of a new
     *            representation.
     * @param editingDomain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     * @param treeCommandFactory
     *            The {@link ITreeCommandFactory}.
     */
    public CreateRepresentationFromRepresentationCreationDescription(RepresentationCreationDescription desc, DRepresentationElement target, TransactionalEditingDomain editingDomain,
            ITreeCommandFactory treeCommandFactory) {
        super(desc, target, editingDomain, treeCommandFactory);
    }

    @Override
    protected Option<DRepresentation> executeCreationCommand(Option<Command> initialOperationCommand, CreateRepresentationCommand createRepresentationCommand) {
        final CompoundCommand compoundCommand = new CompoundCommand();
        if (initialOperationCommand.some()) {
            compoundCommand.append(initialOperationCommand.get());
        }
        compoundCommand.append(createRepresentationCommand);
        getEditingDomain().getCommandStack().execute(compoundCommand);
        return Options.newSome(createRepresentationCommand.getCreatedRepresentation());
    }
}
