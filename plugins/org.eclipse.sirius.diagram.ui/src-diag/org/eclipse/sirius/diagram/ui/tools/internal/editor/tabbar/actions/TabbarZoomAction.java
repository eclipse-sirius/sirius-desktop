/*******************************************************************************
 * Copyright (c) 2012, 2025 THALES GLOBAL SERVICES and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.draw2d.zoom.ZoomListener;
import org.eclipse.gef.Disposable;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * TabbarZoomAction implementation that let set zoom manager later.
 * 
 * @author fbarbin
 */
public abstract class TabbarZoomAction extends Action implements ZoomListener, Disposable {

    /**
     * The ZoomManager used to zoom in or out.
     */
    protected ZoomManager zoomManager;
    
    /**
     * Constructor.
     * 
     * @param text
     *            the action's text, or <code>null</code> if there is no text
     * @param image
     *            the action's image, or <code>null</code> if there is no image
     */
    public TabbarZoomAction(String text, ImageDescriptor image) {
        super(text, image);
    }

    /**
     * remove listener on zoom manager.
     * 
     * @see org.eclipse.gef.Disposable#dispose()
     */
    public void dispose() {
        if (zoomManager != null) {
            zoomManager.removeZoomListener(this);
            zoomManager = null;
        }
    }

    /**
     * Set the zoom manager and add this zoom action as listener on it.
     * 
     * @param zm
     *            the ZoomManager used to zoom in or out.
     */
    public void setZoomManager(ZoomManager zm) {
        if (zoomManager == zm)
            return;
        if (zoomManager != null)
            zoomManager.removeZoomListener(this);

        zoomManager = zm;

        if (zoomManager != null)
            zoomManager.addZoomListener(this);
    }

}
