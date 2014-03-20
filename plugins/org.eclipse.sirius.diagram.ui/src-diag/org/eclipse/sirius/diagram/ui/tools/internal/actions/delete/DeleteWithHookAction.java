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
package org.eclipse.sirius.diagram.ui.tools.internal.actions.delete;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;
import org.eclipse.gmf.runtime.diagram.core.util.ViewUtil;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.actions.PromptingDeleteAction;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.business.api.helper.delete.DeleteHookHelper;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Sets;

/**
 * Delete Action originating via keyboard using the 'Delete' hot/shortcut key.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public class DeleteWithHookAction extends PromptingDeleteAction {

    /**
     * Constructs a <code>PromptingDeleteAction</code> using the specified part.
     * 
     * @param part
     *            The part for this action
     */
    public DeleteWithHookAction(final IWorkbenchPart part) {
        super(part);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.diagram.ui.internal.actions.PromptingDeleteAction#createCommand(java.util.List)
     */
    @Override
    public Command createCommand(final List objects) {
        /*
         * in super class there is a check on a GMF preference, we may need to
         * override
         */
        return super.createCommand(objects);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.ui.actions.WorkbenchPartAction#execute(org.eclipse.gef.commands.Command)
     */
    @Override
    protected void execute(final Command command) {
        if (command == null || !command.canExecute()) {
            return;
        }

        final DeleteHookHelper deleteHookHelper = new DeleteHookHelper(computeSelections());
        if (deleteHookHelper.checkDeleteHook()) {
            super.execute(command);
        }
    }

    private Collection<DSemanticDecorator> computeSelections() {

        final Set<DSemanticDecorator> diagramElements = Sets.newLinkedHashSet();

        for (final Object selectedObject : getSelectedObjects()) {
            if (selectedObject instanceof IGraphicalEditPart) {
                final IGraphicalEditPart gEditPart = (IGraphicalEditPart) selectedObject;
                final View view = (View) gEditPart.getModel();
                final EObject element = ViewUtil.resolveSemanticElement(view);
                if (element instanceof DSemanticDecorator) {
                    diagramElements.add((DSemanticDecorator) element);
                }
            }
        }
        return diagramElements;
    }

}
