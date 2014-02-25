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
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;

/**
 * Basic class to describe common behavior for revealing elements.
 * 
 * @author dlecan
 */
public abstract class AbstractRevealElementEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return req.getType() != null && req.getType().equals(getCurrentType());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (request.getType() != null && request.getType().equals(getCurrentType())) {
            if (this.getHost() instanceof IGraphicalEditPart) {

                final IGraphicalEditPart gmfEditPart = (IGraphicalEditPart) this.getHost();
                final EObject element = gmfEditPart.resolveSemanticElement();
                final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(element);
                if (element instanceof DDiagram) {
                    final DDiagram diagram = (DDiagram) element;

                    final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                    final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);

                    final CompoundCommand cc = new CompoundCommand();
                    final org.eclipse.emf.common.command.Command cmd = buildCommand(request, diagram, emfCommandFactory);
                    cc.append(cmd);

                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    return new ICommandProxy(new GMFCommandWrapper(editingDomain, cc));
                }
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (request.getType() != null && request.getType().equals(getCurrentType())) {
            return this.getHost();
        }
        return super.getTargetEditPart(request);
    }

    /**
     * Get the current type of the request.
     * 
     * @return The curernt type of the request.
     */
    protected abstract String getCurrentType();

    /**
     * Build the command.
     * 
     * @param request
     *            The request.
     * @param diagram
     *            The current diagram.
     * @param emfCommandFactory
     *            The EMF command factory.
     * @return The command.
     */
    protected abstract org.eclipse.emf.common.command.Command buildCommand(Request request, DDiagram diagram, IDiagramCommandFactory emfCommandFactory);

}
