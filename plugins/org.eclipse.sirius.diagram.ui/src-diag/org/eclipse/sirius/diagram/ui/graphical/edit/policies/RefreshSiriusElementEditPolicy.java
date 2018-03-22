/*******************************************************************************
 * Copyright (c) 2007, 2018 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DRefreshable;

/**
 * Edit policy that can return a refresh command on the Request {@link RequestConstants#REQ_REFRESH_VIEWPOINT}.
 * 
 * @author ymortier
 */
public class RefreshSiriusElementEditPolicy extends AbstractEditPolicy {

    @Override
    public boolean understandsRequest(Request req) {
        return req != null && RequestConstants.REQ_REFRESH_VIEWPOINT.equals(req.getType());
    }

    @Override
    public Command getCommand(Request request) {
        if (understandsRequest(request)) {
            EditPart host = this.getHost();
            if (host instanceof IGraphicalEditPart && host.isActive()) {
                final IGraphicalEditPart gmfEditPart = (IGraphicalEditPart) host;
                final EObject element = gmfEditPart.resolveSemanticElement();
                if (element instanceof DRefreshable) {
                    DDiagramEditor diagramEditor = (DDiagramEditor) host.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    IDiagramCommandFactoryProvider cmdFactoryProvider = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    TransactionalEditingDomain ted = TransactionUtil.getEditingDomain(element);
                    org.eclipse.emf.common.command.Command cmd = cmdFactoryProvider.getCommandFactory(ted).buildRefreshCommand((DRefreshable) element);
                    return new ICommandProxy(new GMFCommandWrapper(ted, cmd));
                }
            }
        }
        return null;
    }

    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (understandsRequest(request)) {
            return this.getHost();
        }
        return super.getTargetEditPart(request);
    }
}
