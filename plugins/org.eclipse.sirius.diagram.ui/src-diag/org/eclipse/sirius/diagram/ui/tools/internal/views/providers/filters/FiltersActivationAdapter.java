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
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.filters;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.filter.FilterDescription;

/**
 * An adapter to listen layer activation change.
 * 
 * @author mchauvin
 */
public class FiltersActivationAdapter extends AdapterImpl {

    /** The structured viewer to update. */
    private StructuredViewer viewer;

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

    private void update(final DDiagram diagram, final FilterDescription filter, final boolean activate) {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                if (viewer != null) {
                    viewer.update(filter, null);
                }
            }
        });
    }

    private void update(final DDiagram notifier, final Collection<FilterDescription> filters, final boolean b) {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                if (viewer != null && filters != null) {
                    for (FilterDescription filter : filters) {
                        viewer.update(filter, null);
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
            if (featureID == DiagramPackage.DDIAGRAM__ACTIVATED_FILTERS) {

                switch (msg.getEventType()) {

                case Notification.ADD:
                    final FilterDescription filterToAdd = (FilterDescription) msg.getNewValue();
                    update((DDiagram) notifier, filterToAdd, true);
                    break;
                case Notification.ADD_MANY:
                    @SuppressWarnings("unchecked")
                    final Collection<FilterDescription> filtersToAdd = (Collection<FilterDescription>) msg.getNewValue();
                    update((DDiagram) notifier, filtersToAdd, true);
                    break;
                case Notification.REMOVE:
                    final FilterDescription filterToRemove = (FilterDescription) msg.getOldValue();
                    update((DDiagram) notifier, filterToRemove, false);
                    break;
                case Notification.REMOVE_MANY:
                    @SuppressWarnings("unchecked")
                    final Collection<FilterDescription> filtersToRemove = (Collection<FilterDescription>) msg.getOldValue();
                    update((DDiagram) notifier, filtersToRemove, false);
                    break;
                default:
                    break;
                }
            }
        }
    }
}
