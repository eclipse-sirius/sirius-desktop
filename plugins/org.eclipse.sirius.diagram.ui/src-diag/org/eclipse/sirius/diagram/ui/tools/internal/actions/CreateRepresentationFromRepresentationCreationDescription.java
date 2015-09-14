/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.InitializeLayoutCommand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ui.tools.api.actions.AbstractCreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

/**
 * Create a new Representation from a {@link RepresentationCreationDescription}.
 *
 * @author cbrun
 *
 */
public class CreateRepresentationFromRepresentationCreationDescription extends AbstractCreateRepresentationFromRepresentationCreationDescription {

    private final IGraphicalEditPart editPart;

    /**
     * Build the action.
     *
     * @param desc
     *            {@link RepresentationCreationDescription} to use.
     * @param target
     *            node on which the user requested the creation of a new
     *            representation.
     * @param editingDomain
     *            current {@link org.eclipse.emf.edit.domain.EditingDomain}.
     * @param curPart
     *            EditPart of the current
     *            {@link org.eclipse.sirius.viewpoint.DNode}.
     * @since 0.9.0
     */
    public CreateRepresentationFromRepresentationCreationDescription(final RepresentationCreationDescription desc, final DRepresentationElement target, final TransactionalEditingDomain editingDomain,
            final IGraphicalEditPart curPart) {
        super(desc, target, editingDomain, getDiagramCommandFactory(curPart, editingDomain));
        this.editPart = curPart;
    }

    @Override
    protected Option<DRepresentation> executeCreationCommand(Option<Command> initialOperationCommand, CreateRepresentationCommand createRepresentationCommand) {
        final CompositeTransactionalCommand compositeCommand = new CompositeTransactionalCommand(getEditingDomain(), Messages.CreateRepresentationFromRepresentationCreationDescription_cmdLabel);
        if (initialOperationCommand.some()) {
            compositeCommand.compose(new GMFCommandWrapper(getEditingDomain(), initialOperationCommand.get()));
        }
        InitializeLayoutCommand layoutCommand = null;
        DRepresentation createdRepresentation = null;
        compositeCommand.compose(new GMFCommandWrapper(getEditingDomain(), createRepresentationCommand));
        if (getTarget() != null) {
            layoutCommand = new InitializeLayoutCommand(getEditingDomain(), createRepresentationCommand, editPart);
            compositeCommand.compose(new GMFCommandWrapper(getEditingDomain(), layoutCommand));
        }
        editPart.getRoot().getViewer().getEditDomain().getCommandStack().execute(new ICommandProxy(compositeCommand));

        if (createRepresentationCommand.getCreatedRepresentation() != null) {
            createdRepresentation = createRepresentationCommand.getCreatedRepresentation();
        } else if (layoutCommand != null && layoutCommand.getLayoutedRepresentation() != null) {
            createdRepresentation = layoutCommand.getLayoutedRepresentation();
        }
        return Options.newSome(createdRepresentation);
    }

    /**
     * Returns the emf command factory.
     *
     * @param curPart
     *
     * @return the emf command factory.
     */
    private static IDiagramCommandFactory getDiagramCommandFactory(IGraphicalEditPart curPart, TransactionalEditingDomain editingDomain) {
        final DDiagramEditor diagramEditor = (DDiagramEditor) curPart.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
        if (diagramEditor == null) {
            return null;
        }
        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        final IDiagramCommandFactory diagramCommandFactory = diagramCmdFactoryProvider.getCommandFactory(editingDomain);
        return diagramCommandFactory;
    }
}
