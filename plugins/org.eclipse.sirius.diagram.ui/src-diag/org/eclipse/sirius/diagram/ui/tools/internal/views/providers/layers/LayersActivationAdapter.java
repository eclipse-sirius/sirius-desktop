/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.layers;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.ui.tools.api.graphical.edit.palette.PaletteManager;

/**
 * An adapter to listen layer activation change.
 * 
 * @author mchauvin
 */
public class LayersActivationAdapter extends AdapterImpl {

    /** The structured viewer to update. */
    private StructuredViewer viewer;

    /** The palette manager to update. */
    private PaletteManager paletteManager;

    /**
     * Set the viewer.
     * 
     * @param viewer
     *            the viewer to update when the model change
     */
    public void setViewer(final Viewer viewer) {
        if (viewer instanceof StructuredViewer) {
            this.viewer = (StructuredViewer) viewer;
        }
    }

    /**
     * Set the palette manager.
     * 
     * @param paletteManager
     *            the palette manager to update when the model change
     */
    public void setPaletteManager(final PaletteManager paletteManager) {
        this.paletteManager = paletteManager;
    }

    private void update(final DDiagram diagram, final Layer layer, final boolean activate) {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                if (viewer != null) {
                    viewer.update(layer, null);
                }

                if (paletteManager != null) {
                    /* refresh the palette */
                    if (activate) {
                        paletteManager.showLayer(layer);
                    } else {
                        paletteManager.hideLayer(layer);
                    }
                }

            }
        });
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.common.notify.impl.AdapterImpl#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    @Override
    public void notifyChanged(final Notification msg) {
        final Object notifier = msg.getNotifier();
        if (notifier instanceof DDiagram) {
            final int featureID = msg.getFeatureID(DDiagram.class);
            if (featureID == DiagramPackage.DDIAGRAM__ACTIVATED_LAYERS) {

                switch (msg.getEventType()) {

                case Notification.ADD:
                    final Layer layerToAdd = (Layer) msg.getNewValue();
                    update((DDiagram) notifier, layerToAdd, true);
                    break;
                case Notification.REMOVE:
                    final Layer layerToRemove = (Layer) msg.getOldValue();
                    update((DDiagram) notifier, layerToRemove, false);
                    break;
                default:
                    break;
                }
            }
        }
    }
}
