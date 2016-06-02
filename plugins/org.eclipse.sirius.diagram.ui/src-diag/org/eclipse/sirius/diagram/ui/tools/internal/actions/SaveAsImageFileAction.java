/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.tools.internal.actions.export.AbstractExportRepresentationsAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.ui.PlatformUI;

/**
 * Action to save current diagram as an image file.
 *
 * @author dlecan
 */
public class SaveAsImageFileAction extends AbstractExportRepresentationsAction {

    /**
     * Default constructor.
     */
    public SaveAsImageFileAction() {
        super(Messages.SaveAsImageFileAction_label, DiagramUIPlugin.Implementation.getBundledImageDescriptor("icons/screenshot.gif")); //$NON-NLS-1$

        /* set the id */
        setId(ActionIds.COPY_TO_IMAGE);
    }

    @Override
    protected Collection<DRepresentationDescriptor> getRepresentationToExport() {
        DRepresentation dRepresentationToExport = null;
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Object selectedObject : structuredSelection.toList()) {
                if (selectedObject instanceof IDDiagramEditPart) {
                    IDDiagramEditPart dDiagramEditPart = (IDDiagramEditPart) selectedObject;
                    Option<DDiagram> dDiagramOption = dDiagramEditPart.resolveDDiagram();
                    if (dDiagramOption.some()) {
                        dRepresentationToExport = dDiagramOption.get();
                        break;
                    }
                } else if (selectedObject instanceof IDiagramElementEditPart) {
                    IDiagramElementEditPart ddePart = (IDiagramElementEditPart) selectedObject;
                    DDiagramElement dde = ddePart.resolveDiagramElement();
                    if (dde != null) {
                        dRepresentationToExport = dde.getParentDiagram();
                        if (dRepresentationToExport != null) {
                            break;
                        }
                    }

                }
            }
        }

        if (dRepresentationToExport != null) {
            return Collections.<DRepresentationDescriptor> singleton(new DRepresentationQuery(dRepresentationToExport).getRepresentationDescriptor());
        }
        return Collections.<DRepresentationDescriptor> emptyList();
    }

    @Override
    protected Session getSession(DRepresentationDescriptor repDescriptor) {
        Session session = null;
        if (repDescriptor != null) {
            EObjectQuery eObjectQuery = new EObjectQuery(repDescriptor);
            session = eObjectQuery.getSession();
        }
        return session;
    }
}
