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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.filters;

import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;

/**
 * The content provider.
 * 
 * @author mchauvin
 */
public class FiltersContentProvider implements IStructuredContentProvider {

    /** The EMF adapter */
    private FiltersActivationAdapter filtersActivationAdapter;

    /** The diagram workbench part */
    private IDiagramWorkbenchPart diagramPart;

    /**
     * Create a new content providers to display layers.
     * 
     * @param adapter
     *            the layer activation adapter
     * @param part
     *            the part responsible of the diagram access
     */
    public FiltersContentProvider(final FiltersActivationAdapter adapter, final IDiagramWorkbenchPart part) {
        filtersActivationAdapter = adapter;
        diagramPart = part;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof DiagramDescription) {
            final DiagramDescription diagramDesc = (DiagramDescription) inputElement;
            return diagramDesc.getFilters().toArray();
        }
        return Collections.EMPTY_LIST.toArray();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#dispose()
     */
    public void dispose() {
        if (diagramPart != null) {
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                removeListenerFrom((View) obj);
            }
            diagramPart = null;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        if (oldInput != null) {
            filtersActivationAdapter.setViewer(viewer);
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                removeListenerFrom((View) obj);
            }
        }
        if (newInput != null) {
            filtersActivationAdapter.setViewer(viewer);
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                addListenerTo((View) obj);
            }
        }
    }

    private void removeListenerFrom(final View oldInput) {
        final EObject element = oldInput.getElement();
        if (element instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) element;
            diagram.eAdapters().remove(filtersActivationAdapter);
        }
    }

    private void addListenerTo(final View newInput) {
        final EObject element = newInput.getElement();
        if (element instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) element;
            diagram.eAdapters().add(filtersActivationAdapter);
        }
    }
}
