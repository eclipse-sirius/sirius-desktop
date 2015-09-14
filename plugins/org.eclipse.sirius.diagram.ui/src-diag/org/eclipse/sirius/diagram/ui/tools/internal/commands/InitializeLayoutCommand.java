/*******************************************************************************
 * Copyright (c) 2010, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.commands;

import java.util.Collection;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.layout.LayoutUtils;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.collect.Lists;

/**
 * Specific command to initialize layout after diagram creation from diagram
 * creation tool description.
 *
 * @author mporhel
 */
public class InitializeLayoutCommand extends RecordingCommand {

    private final CreateRepresentationCommand command;

    private final IGraphicalEditPart editPart;

    private DRepresentation layoutedRepresentation;

    /**
     * Constructor.
     *
     * @param domain
     *            the editing domain.
     * @param editPart
     *            the source editpart.
     * @param command
     *            the creation command.
     */
    public InitializeLayoutCommand(final TransactionalEditingDomain domain, CreateRepresentationCommand command, IGraphicalEditPart editPart) {
        super(domain, Messages.InitializeLayoutCommand_label);
        this.command = command;
        this.editPart = editPart;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        layoutedRepresentation = command.getCreatedRepresentation();
        if (layoutedRepresentation != null && editPart != null) {
            LayoutUtils.initializeDiagramLayout(editPart.getNotationView().getDiagram(), layoutedRepresentation);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<?> getResult() {
        return Lists.newArrayList(layoutedRepresentation);
    }

    /**
     * Get the layouted representation.
     *
     * @return the layouted representation
     */
    public DRepresentation getLayoutedRepresentation() {
        return layoutedRepresentation;
    }
}
