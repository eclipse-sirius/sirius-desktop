/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.handler;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.swt.widgets.Event;

/**
 * A Sirius mouse wheel handler to zoom around the mouse location. This class is
 * a copy of {@link org.eclipse.gef.MouseWheelZoomHandler} to correctly handle
 * SiriusAnimatableZoomManager.
 * 
 * @author fbarbin
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class SiriusMouseWheelZoomHandler implements MouseWheelHandler {
    /**
     * The Singleton instance.
     */
    public static final SiriusMouseWheelZoomHandler SINGLETON = new SiriusMouseWheelZoomHandler();

    /**
     * Zooms the given viewer.
     * 
     * @see MouseWheelHandler#handleMouseWheel(Event, EditPartViewer)
     */
    @Override
    public void handleMouseWheel(Event event, EditPartViewer viewer) {

        ZoomManager zoomMgr = (ZoomManager) viewer.getProperty(ZoomManager.class.toString());
        if (zoomMgr != null) {
            if (event.count > 0) {
                zoomIn(zoomMgr, event);
            } else {
                zoomOut(zoomMgr, event);
            }
            event.doit = false;
        }
    }

    private void zoomOut(ZoomManager zoomMgr, Event event) {
        if (zoomMgr instanceof SiriusAnimatableZoomManager) {
            ((SiriusAnimatableZoomManager) zoomMgr).zoomTo(zoomMgr.getPreviousZoomLevel(), new Point(event.x, event.y));
        } else {
            zoomMgr.zoomOut();
        }

    }

    private void zoomIn(ZoomManager zoomMgr, Event event) {
        if (zoomMgr instanceof SiriusAnimatableZoomManager) {
            ((SiriusAnimatableZoomManager) zoomMgr).zoomTo(zoomMgr.getNextZoomLevel(), new Point(event.x, event.y));
        } else {
            zoomMgr.zoomIn();
        }

    }

}
