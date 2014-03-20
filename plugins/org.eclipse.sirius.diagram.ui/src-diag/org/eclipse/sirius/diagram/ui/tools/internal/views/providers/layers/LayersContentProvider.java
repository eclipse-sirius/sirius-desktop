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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.Layer;

/**
 * The content provider.
 * 
 * @author mchauvin
 */
public class LayersContentProvider implements IStructuredContentProvider {

    /** The EMF adapter */
    private final LayersActivationAdapter layerActivationAdapter;

    /** The Session listener */
    private final LayersEventsListener eventListener;

    /** The diagram workbench part */
    private IDiagramWorkbenchPart diagramPart;

    /** the diagram session */
    private Session session;

    /**
     * Create a new content providers to display layers.
     * 
     * @param adapter
     *            the layer activation adapter
     * @param listener
     *            the session listener
     * @param part
     *            the part responsible of the diagram access
     */
    public LayersContentProvider(final LayersActivationAdapter adapter, final LayersEventsListener listener, final IDiagramWorkbenchPart part) {
        layerActivationAdapter = adapter;
        eventListener = listener;
        diagramPart = part;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public Object[] getElements(final Object inputElement) {
        if (inputElement instanceof DDiagram) {
            final DiagramDescription diagramDesc = ((DDiagram) inputElement).getDescription();
            final Collection<Layer> allLayers = new DiagramComponentizationManager().getAllLayers(session.getSelectedViewpoints(false), diagramDesc);
            final Collection<Layer> layers = new ArrayList<Layer>();
            for (final Layer layer : allLayers) {
                if (layer != null && layer != diagramDesc.getDefaultLayer()) {
                    layers.add(layer);
                }
            }
            return layers.toArray();
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
        if (session != null) {
            session = null;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        layerActivationAdapter.setViewer(viewer);
        eventListener.setViewer(viewer);

        if (oldInput != null) {
            final DiagramEditPart diaEditPart = diagramPart.getDiagramEditPart();
            final Object obj = diaEditPart.getModel();
            if (obj instanceof View) {
                removeListenerFrom((View) obj);
            }
        }
        if (newInput != null) {
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
            diagram.eAdapters().remove(layerActivationAdapter);
            removeListenerToSession();
        }
    }

    private void addListenerTo(final View newInput) {
        final EObject element = newInput.getElement();
        if (element instanceof DDiagram) {
            final DDiagram diagram = (DDiagram) element;
            diagram.eAdapters().add(layerActivationAdapter);
            if (diagram instanceof DSemanticDiagram) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
                addListenerToSession();
            }
        }
    }

    private void addListenerToSession() {
        if (session != null) {
            session.addListener(eventListener);
        }
    }

    private void removeListenerToSession() {
        if (session != null) {
            session.removeListener(eventListener);
        }
    }
}
