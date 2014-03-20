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

import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.requests.RequestConstants;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Edit policy to launch behaviors on a given edit part.
 * 
 * @author ymortier
 */
public class LaunchBehaviorToolEditPolicy extends AbstractSiriusEditPolicy {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#understandsRequest(org.eclipse.gef.Request)
     */
    @Override
    public boolean understandsRequest(final Request req) {
        return req.getType() != null && req.getType().equals(RequestConstants.REQ_LAUNCH_RULE_TOOL) && this.getHost() instanceof DDiagramEditPart;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getTargetEditPart(org.eclipse.gef.Request)
     */
    @Override
    public EditPart getTargetEditPart(final Request request) {
        if (understandsRequest(request)) {
            return this.getHost();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editpolicies.AbstractEditPolicy#getCommand(org.eclipse.gef.Request)
     */
    @Override
    public Command getCommand(final Request request) {
        if (understandsRequest(request)) {
            final DDiagramEditor diagramEditor = (DDiagramEditor) this.getHost().getViewer().getProperty(DDiagramEditor.EDITOR_ID);
            if (diagramEditor != null) {
                final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(diagramEditor.getEditingDomain().getResourceSet());
                final Object adapter = diagramEditor.getAdapter(IDiagramCommandFactoryProvider.class);
                final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
                final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);
                final CompoundCommand result = new CompoundCommand();
                final Iterator<?> iterTools = getSirius().getDescription() != null ? getSirius().getActivateBehaviors().iterator() : Collections.EMPTY_LIST.iterator();
                final DSemanticDecorator rootObject = getFirstDecorateSemanticElement();
                while (iterTools.hasNext()) {
                    final Object tool = iterTools.next();
                    if (tool instanceof BehaviorTool) {
                        result.add(new ICommandProxy(new GMFCommandWrapper(this.getEditingDomain(), emfCommandFactory.buildLaunchRuleCommandFromTool(rootObject, (BehaviorTool) tool, false, true))));
                    }
                }
                return result;
            }
        }
        return super.getCommand(request);
    }
}
