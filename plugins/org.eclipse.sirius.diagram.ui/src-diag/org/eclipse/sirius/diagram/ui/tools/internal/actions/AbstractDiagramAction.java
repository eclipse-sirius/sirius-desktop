/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Abstract diagram action. Override calculateEnabled() to check authority
 * permission.
 *
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */
public abstract class AbstractDiagramAction extends DiagramAction {

    /**
     * Constructs a new diagram action.
     *
     * @param workbenchPart
     *            The workbench part associated with this action
     */
    public AbstractDiagramAction(IWorkbenchPart workbenchPart) {
        super(workbenchPart);
    }

    /**
     * Constructs a new diagram action.
     *
     * @param workbenchPage
     *            The workbench Page associated with this action
     */
    public AbstractDiagramAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    /**
     * Calculate the enable state.
     * 
     * @return true to enable the action
     */
    @Override
    protected boolean calculateEnabled() {
        return canEditInstance() && super.calculateEnabled();
    }

    /**
     * Check authority permission.
     *
     * @return true if the action can be launched on the diagram
     */
    protected boolean canEditInstance() {
        boolean canEditInstance = true;
        if (getWorkbenchPart() instanceof DDiagramEditor && ((DDiagramEditor) getWorkbenchPart()).getRepresentation() instanceof DDiagram) {
            final DDiagramEditor editor = (DDiagramEditor) getWorkbenchPart();
            final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
            Resource sessionResource = editor.getSession().getSessionResource();
            if (sessionResource != null) {
                IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(sessionResource.getResourceSet());
                canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
            }
        }

        return canEditInstance;
    }
}
