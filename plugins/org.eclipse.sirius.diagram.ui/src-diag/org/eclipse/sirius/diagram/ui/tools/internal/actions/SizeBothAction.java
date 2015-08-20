/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramElementContainerEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.IWorkbenchPage;

import com.google.common.collect.Iterables;

/**
 * Sirius specifc size both action:
 * <UL>
 * <LI>disable the action on Region and Region container.</LI>
 * <LI>check authority permission in calculateEnabled()./LI>
 * </UL>
 * 
 * @author mporhel
 * 
 */
public class SizeBothAction extends org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction {

    /**
     * Creates the Make Same Size Both Action.
     * 
     * @param workbenchPage
     *            the workbench page.
     */
    public SizeBothAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);
    }

    @Override
    protected boolean calculateEnabled() {
        boolean enabled = super.calculateEnabled();
        if (enabled) {
            for (AbstractDiagramElementContainerEditPart container : Iterables.filter(getSelectedObjects(), AbstractDiagramElementContainerEditPart.class)) {
                if (container.isRegion() || (container instanceof AbstractDiagramContainerEditPart && ((AbstractDiagramContainerEditPart) container).isRegionContainer())) {
                    enabled = false;
                    break;
                }
            }
        }
        return enabled && canEditInstance();
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
