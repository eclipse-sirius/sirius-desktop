/*******************************************************************************
 * Copyright (c) 2012, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.SiriusTabbarExtensionContributionFactory.TabbarActionContributionItem;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Contribution item for {@link HideDDiagramElementLabelAction} that provides a
 * selection change listener to {@link org.eclipse.ui.ISelectionService}.
 * 
 * @author fbarbin
 * 
 */
public class HideDDiagramElementLabelActionContributionItem extends TabbarActionContributionItem implements ISelectionListener {

    /**
     * Can edit property according to permission authority.
     */
    private boolean canEdit;

    /**
     * constructor.
     * 
     * @param part
     *            the current part.
     */
    public HideDDiagramElementLabelActionContributionItem(final IWorkbenchPart part) {
        super(new HideDDiagramElementLabelAction(SiriusDiagramActionBarContributor.HIDE_LABEL), part);
        canEdit = true;
    }

    /**
     * Set canEdit attribute.
     * 
     * @param canEdit
     *            can edit value.
     */
    public void setCanEdit(boolean canEdit) {
        this.canEdit = canEdit;

    }

    @Override
    public void update(String propertyName) {
        updateCanEdit();

        boolean isEnabled = false;
        ISelection selection = representationPart.getSite().getPage().getSelection();
        if (selection instanceof IStructuredSelection) {
            isEnabled = HideDDiagramElementLabelAction.isEnabled(((IStructuredSelection) selection).toList());
        }
        getAction().setEnabled(isEnabled && canEdit);
        super.update(propertyName);
    }

    /**
     * Updates the can edit property by asking to permission authority.
     */
    private void updateCanEdit() {
        boolean canEditInstance = true;
        if (representationPart instanceof DDiagramEditor) {
            final DDiagramEditor editor = (DDiagramEditor) representationPart;

            if (editor != null && editor.getRepresentation() != null) {
                final DDiagram editorDiagram = (DDiagram) editor.getRepresentation();
                Resource sessionResource = editor.getSession().getSessionResource();
                if (sessionResource != null) {
                    IPermissionAuthority permissionAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(sessionResource.getResourceSet());
                    canEditInstance = permissionAuthority.canEditInstance(editorDiagram);
                }
            }
        }
        setCanEdit(canEditInstance);
    }
}
