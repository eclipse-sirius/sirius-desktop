/*******************************************************************************
 * Copyright (c) 2008, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;
import org.eclipse.sirius.ui.tools.api.actions.AbstractCreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
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
     * @param tableCommandFactory
     *            The {@link ITableCommandFactory}.
     */
    public CreateRepresentationFromRepresentationCreationDescription(RepresentationCreationDescription desc, DRepresentationElement target, TransactionalEditingDomain editingDomain,
            ITableCommandFactory tableCommandFactory) {
        super(desc, target, editingDomain, tableCommandFactory);
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
