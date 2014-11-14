/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DRefreshable;

/**
 * Edit policy that can return a refresh command on the Request
 * {@link RequestConstants#REQ_REFRESH_VIEWPOINT}.
 * 
 * @author ymortier
 */
public class RefreshSiriusElementEditPolicy extends AbstractEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return req.getType() != null && req.getType().equals(RequestConstants.REQ_REFRESH_VIEWPOINT);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_REFRESH_VIEWPOINT)) {
            if (this.getHost() instanceof IGraphicalEditPart && getHost().isActive()) {
                final IGraphicalEditPart gmfEditPart = (IGraphicalEditPart) this.getHost();
                final EObject element = gmfEditPart.resolveSemanticElement();
                if (element instanceof DRefreshable) {
                    final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                    final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(element);
                    final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
                    final DRefreshable refreshable = (DRefreshable) element;
                    final org.eclipse.emf.common.command.Command cmd = emfCommandFactory.buildRefreshCommand(refreshable);
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    return new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
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
        if (request.getType() != null && request.getType().equals(RequestConstants.REQ_REFRESH_VIEWPOINT)) {
            return this.getHost();
        }
        return super.getTargetEditPart(request);
    }
}
