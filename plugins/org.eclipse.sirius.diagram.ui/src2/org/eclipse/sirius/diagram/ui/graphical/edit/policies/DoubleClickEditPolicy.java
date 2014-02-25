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
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramElementMapping;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;

/**
 * An edit policy which launch an action after double-clicking an edit part.
 * 
 * @author smonnier
 * 
 */
public class DoubleClickEditPolicy extends OpenEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.editpolicies.OpenEditPolicy#getOpenCommand(org.eclipse.gef.Request)
     */
    @Override
    protected Command getOpenCommand(Request request) {
        EditPart editPart = this.getHost();
        Object model = editPart.getModel();
        if (model instanceof View) {
            EObject element = ((View) model).getElement();
            if (element instanceof DDiagramElement) {
                final DDiagramElement ddiagramElement = (DDiagramElement) element;
                DiagramElementMapping diagramElementMapping = ddiagramElement.getDiagramElementMapping();
                if (diagramElementMapping.getDoubleClickDescription() != null) {
                    final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(element);
                    final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
                    final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                    final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                    final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
                    final org.eclipse.emf.common.command.Command cmd = emfCommandFactory.buildDoubleClickOnElementCommandFromTool(ddiagramElement, diagramElementMapping.getDoubleClickDescription());
                    final TransactionalEditingDomain editingDomain = ((IGraphicalEditPart) getHost()).getEditingDomain();
                    return new ICommandProxy(new GMFCommandWrapper(editingDomain, cmd));
                }
            }
        }
        return null;
    }

}
