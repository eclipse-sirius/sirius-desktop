/*******************************************************************************
 * Copyright (c) 2007, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;

/**
 * An edit policy to hide diagram elements.
 * 
 * @author ymortier
 */
public class HideSiriusElementEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return req.getType() != null && req.getType().equals(RequestConstants.REQ_HIDE_ELEMENT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_HIDE_ELEMENT)) {
            if (this.getHost() instanceof IGraphicalEditPart) {
                final IGraphicalEditPart gmfEditPart = (IGraphicalEditPart) this.getHost();
                final EObject element = gmfEditPart.resolveSemanticElement();
                if (element instanceof DDiagramElement) {
                    if (this.getHost().getParent() != null && this.getHost().getRoot() != null) {
                        final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                        final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                        final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                        final DDiagramElement diagramElement = (DDiagramElement) element;

                        return getHideCommand(diagramElement, cmdFactoryProvider);

                    }

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
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_HIDE_ELEMENT)) {
            return this.getHost();
        }
        return super.getTargetEditPart(request);
    }

    private Command getHideCommand(final DDiagramElement diagramElement, final IDiagramCommandFactoryProvider cmdFactoryProvider) {
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagramElement);
        final IDiagramCommandFactory diagramCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
        final CompoundCommand result = new CompoundCommand();

        Command command = null;

        if (diagramElement.isVisible()) {
            final Set<EObject> elementsToHide = new HashSet<EObject>();
            if (getHost() instanceof GraphicalEditPart && !(getHost() instanceof ConnectionEditPart)) {
                final GraphicalEditPart graphEp = (GraphicalEditPart) getHost();
                Iterator<?> it = graphEp.getSourceConnections().iterator();
                while (it.hasNext()) {
                    final Object obj = it.next();
                    if (obj instanceof ConnectionEditPart) {
                        final EObject connectionElement = ((ConnectionEditPart) obj).resolveSemanticElement();
                        if (connectionElement != null) {
                            elementsToHide.add(connectionElement);
                        }
                    }
                }
                it = graphEp.getTargetConnections().iterator();
                while (it.hasNext()) {
                    final Object obj = it.next();
                    if (obj instanceof ConnectionEditPart) {
                        final EObject connectionElement = ((ConnectionEditPart) obj).resolveSemanticElement();
                        if (connectionElement != null) {
                            elementsToHide.add(connectionElement);
                        }
                    }
                }

            }
            elementsToHide.add(diagramElement);
            final org.eclipse.emf.common.command.Command cmd = diagramCommandFactory.buildHideCommand(elementsToHide);
            result.append(cmd);
            final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
            command = new ICommandProxy(new GMFCommandWrapper(editingDomain, result));
        }
        return command;
    }

}
