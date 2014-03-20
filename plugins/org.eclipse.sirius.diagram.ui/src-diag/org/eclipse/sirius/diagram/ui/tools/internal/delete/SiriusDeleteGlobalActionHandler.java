/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.delete;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.ui.action.global.GlobalActionId;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionContext;
import org.eclipse.gmf.runtime.common.ui.services.action.global.IGlobalActionHandler;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.diagram.ui.providers.DiagramGlobalActionHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;

/**
 * A {@link IGlobalActionHandler} to manage the delete action.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class SiriusDeleteGlobalActionHandler extends DiagramGlobalActionHandler implements IGlobalActionHandler {

    /**
     * Overridden to tells that we handle only delete action.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean canHandle(IGlobalActionContext cntxt) {
        boolean canHandle = false;
        /* Check if the active part is a IDiagramWorkbenchPart */
        /* Check if the selection is a structured selection */
        IWorkbenchPart part = cntxt.getActivePart();
        if (part instanceof IDiagramWorkbenchPart && cntxt.getSelection() instanceof IStructuredSelection) {

            /* Check the action id */
            String actionId = cntxt.getActionId();
            if (GlobalActionId.DELETE.equals(actionId)) {
                canHandle = getCommand(cntxt) != null;
            }
        }

        return canHandle;
    }

    @Override
    public ICommand getCommand(IGlobalActionContext cntxt) {
        ICommand deleteCommand = super.getCommand(cntxt);
        if (deleteCommand != null && deleteCommand.canExecute()) {
            TransactionalEditingDomain domain = getEditingDomain(cntxt);
            deleteCommand = new DeleteWrapperHookExecutorCommand(domain, computeSelections(cntxt), deleteCommand);
        }
        return deleteCommand;
    }

    private TransactionalEditingDomain getEditingDomain(IGlobalActionContext cntxt) {
        IWorkbenchPart part = cntxt.getActivePart();
        TransactionalEditingDomain result = null;

        IEditingDomainProvider provider = (IEditingDomainProvider) part.getAdapter(IEditingDomainProvider.class);

        if (provider != null) {
            EditingDomain domain = provider.getEditingDomain();

            if (domain instanceof TransactionalEditingDomain) {
                result = (TransactionalEditingDomain) domain;
            }
        }

        return result;
    }

    private Collection<DSemanticDecorator> computeSelections(IGlobalActionContext globalActionContext) {

        final Collection<DSemanticDecorator> diagramElements = new ArrayList<DSemanticDecorator>();
        ISelection selection = globalActionContext.getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            Iterator<?> iterator = structuredSelection.iterator();
            while (iterator.hasNext()) {
                Object selectedObject = iterator.next();
                if (selectedObject instanceof IGraphicalEditPart) {
                    final IGraphicalEditPart gEditPart = (IGraphicalEditPart) selectedObject;
                    final EObject element = gEditPart.resolveSemanticElement();
                    if (element instanceof DSemanticDecorator) {
                        diagramElements.add((DSemanticDecorator) element);
                    }
                }
            }
        }
        return diagramElements;
    }

}
