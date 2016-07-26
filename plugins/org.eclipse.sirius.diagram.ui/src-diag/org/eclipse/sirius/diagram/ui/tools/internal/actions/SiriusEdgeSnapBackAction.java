/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Action that will permit a user to snap back all existing and non empty labels
 * of a connection back to its original position relative to the connection.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
@SuppressWarnings("restriction")
public class SiriusEdgeSnapBackAction extends DiagramAction {

    /**
     * Initialize action.
     * 
     * @param page
     *            the {@link IWorkbenchPage} where the action takes place.
     */
    public SiriusEdgeSnapBackAction(IWorkbenchPage page) {
        super(page);
    }

    @Override
    public void init() {
        super.init();
        setText(Messages.SiriusEdgeSnapBackAction_EdgeSnapBackLabel);
        setId(ActionIds.EDGE_SNAP_BACK);
        setToolTipText(Messages.SiriusEdgeSnapBackAction_EdgeSnapBackActionToolTipText);
        setImageDescriptor(DiagramUIActionsPluginImages.DESC_SNAPBACK);
        setHoverImageDescriptor(getImageDescriptor());
    }

    @Override
    protected Request createTargetRequest() {
        return new Request(ActionIds.EDGE_SNAP_BACK);
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    @Override
    protected boolean isOperationHistoryListener() {
        return true;
    }

}
