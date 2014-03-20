/*******************************************************************************
 * Copyright (c) 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions;

import org.eclipse.gef.internal.GEFMessages;
import org.eclipse.gef.internal.InternalImages;
import org.eclipse.gef.ui.actions.GEFActionConstants;

/**
 * SiriusZoomInAction extension to set zoom manager once part is active.
 * 
 * @author fbarbin
 */
public class TabbarZoomInAction extends TabbarZoomAction {

    /**
     * Creates a new zoom in action.
     */
    public TabbarZoomInAction() {
        super(GEFMessages.ZoomIn_Label, InternalImages.DESC_ZOOM_IN);
        setToolTipText(GEFMessages.ZoomIn_Tooltip);
        setId(GEFActionConstants.ZOOM_IN);
        setActionDefinitionId(GEFActionConstants.ZOOM_IN);

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IAction#run()
     */
    public void run() {
        if (zoomManager != null)
            zoomManager.zoomIn();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gef.editparts.ZoomListener#zoomChanged(double)
     */
    public void zoomChanged(double zoom) {
        setEnabled(zoomManager.canZoomIn());
    }

}
