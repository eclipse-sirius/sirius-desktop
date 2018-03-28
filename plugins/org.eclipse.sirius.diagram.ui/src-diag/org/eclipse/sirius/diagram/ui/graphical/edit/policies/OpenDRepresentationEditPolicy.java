/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenDiagramEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.ui.tools.internal.editor.NavigateToCommand;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;

/**
 * This edit policy handles 'Open' requests on link note edit parts by opening the target representation in a new
 * editor.
 */
public class OpenDRepresentationEditPolicy extends OpenDiagramEditPolicy {

    @Override
    protected Command getOpenCommand(Request request) {
        EditPart targetEditPart = getTargetEditPart(request);
        if (targetEditPart instanceof IGraphicalEditPart) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) targetEditPart;
            View view = editPart.getNotationView();
            if (view != null) {
                EObject element = ViewUtil.resolveSemanticElement(view);
                if (element instanceof DRepresentationDescriptor) {
                    DRepresentationDescriptor descriptor = (DRepresentationDescriptor) element;
                    Session session = SessionManager.INSTANCE.getExistingSession(EcoreUtil.getRootContainer(descriptor).eResource().getURI());
                    return new ICommandProxy(new GMFCommandWrapper(session.getTransactionalEditingDomain(), new NavigateToCommand(session, descriptor.getRepresentation())));
                }
            }
        }
        return null;
    }

}
