/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.graphical.edit.policies;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.ChangeBoundsRequest;
import org.eclipse.gmf.runtime.emf.commands.core.command.CompositeTransactionalCommand;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

/**
 * A {@link AirResizableEditPolicy} able to handle containers which are both a
 * Region and a RegionContainer.
 * 
 * @author mporhel
 */
public class RegionRegionContainerResizableEditPolicy extends RegionResizableEditPolicy {

    private final RegionContainerResizableEditPolicy regionContainerPolicy = new RegionContainerResizableEditPolicy();

    @Override
    public void setHost(EditPart host) {
        super.setHost(host);
        regionContainerPolicy.setHost(host);
    }

    @Override
    protected void completeResizeCommand(CompositeTransactionalCommand ctc, ChangeBoundsRequest request) {
        super.completeResizeCommand(ctc, request);
        regionContainerPolicy.completeResizeCommand(ctc, request);
    }

    /**
     * Let the region container policy create the adjust children command.
     */
    @Override
    protected Option<ChangeBoundsRequest> getAdjustChildrenRequest(ChangeBoundsRequest request) {
        return Options.newNone();
    }

    @Override
    protected Command getAutoSizeCommand(Request request) {
        Command autoSizeCommand = super.getAutoSizeCommand(request);
        autoSizeCommand = regionContainerPolicy.getRegionContainerAutoSizeCommand(request, autoSizeCommand);
        return autoSizeCommand;
    }

    @Override
    protected void addCollapseHandle(List createSelectionHandles) {
        // Do nothing as the collapse is not supported yet for recursive
        // regions.
    }
}
