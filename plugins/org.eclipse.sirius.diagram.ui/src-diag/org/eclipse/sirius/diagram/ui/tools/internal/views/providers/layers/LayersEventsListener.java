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

import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;

/**
 * A listener to update the viewer.
 * 
 * @author mchauvin
 */
public class LayersEventsListener implements SessionListener {

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

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.session.SessionListener#notify(int)
     */
    public void notify(final int changeKind) {
        switch (changeKind) {
        case SessionListener.SELECTED_VIEWS_CHANGE_KIND:
        case SessionListener.VSM_UPDATED:
            updateViewer();
            break;
        default:
            break;
        }

    }

    private void updateViewer() {
        EclipseUIUtil.displayAsyncExec(new Runnable() {
            public void run() {
                if (viewer != null && !viewer.getControl().isDisposed()) {
                    viewer.refresh();
                }
            }
        });
    }
}
