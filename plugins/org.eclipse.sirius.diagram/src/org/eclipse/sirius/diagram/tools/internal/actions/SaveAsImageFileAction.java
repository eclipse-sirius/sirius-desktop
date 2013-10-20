/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.actions;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.diagram.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.ui.tools.internal.actions.export.AbstractExportRepresentationsAction;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DDiagramElement;

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
        super("Export diagram as image", SiriusDiagramEditorPlugin.getBundledImageDescriptor("icons/screenshot.gif"));

        /* set the id */
        setId(ActionIds.COPY_TO_IMAGE);
    }

    @Override
    protected Collection<DDiagram> getDDiagramsToExport() {
        DDiagram dDDiagramToExport = null;
        ISelection selection = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
        if (selection instanceof IStructuredSelection) {
            IStructuredSelection structuredSelection = (IStructuredSelection) selection;
            for (Object selectedObject : structuredSelection.toList()) {
                if (selectedObject instanceof IDDiagramEditPart) {
                    IDDiagramEditPart dDiagramEditPart = (IDDiagramEditPart) selectedObject;
                    Option<DDiagram> dDiagramOption = dDiagramEditPart.resolveDDiagram();
                    if (dDiagramOption.some()) {
                        dDDiagramToExport = dDiagramOption.get();
                        break;
                    }
                } else if (selectedObject instanceof IDiagramElementEditPart) {
                    IDiagramElementEditPart ddePart = (IDiagramElementEditPart) selectedObject;
                    DDiagramElement dde = ddePart.resolveDiagramElement();
                    if (dde != null) {
                        dDDiagramToExport = dde.getParentDiagram();
                        if (dDDiagramToExport != null) {
                            break;
                        }
                    }

                }
            }
        }
        return dDDiagramToExport == null ? Collections.<DDiagram> emptyList() : Collections.singleton(dDDiagramToExport);
    }

    @Override
    protected Session getSession(DDiagram diagram) {
        Session session = null;
        if (diagram != null) {
            EObjectQuery eObjectQuery = new EObjectQuery(diagram);
            session = eObjectQuery.getSession();
        }
        return session;
    }
}
