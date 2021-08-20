/******************************************************************************
 * Copyright (c) 2002, 2021 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation 
 *    Obeo - Improvements and bug fixes
 ****************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.actions;

import java.util.List;

import org.eclipse.gef.Request;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.internal.requests.ApplyAppearancePropertiesRequest;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IWorkbenchPage;

/**
 * A copy of CopyAppearancePropertiesAction GMF action that uses the last
 * selected element as base (instead of the first one) to copy the appearance
 * properties.
 * 
 * @author Florian Barbin
 *
 */
@SuppressWarnings("restriction")
public class SiriusCopyAppearancePropertiesAction extends DiagramAction {

    /**
     * Default constructor.
     * 
     * @param workbenchPage
     *            the current workbench page.
     */
    public SiriusCopyAppearancePropertiesAction(IWorkbenchPage workbenchPage) {
        super(workbenchPage);

        setId(ActionIds.ACTION_SIRIUS_COPY_APPEARANCE_PROPERTIES);
        setText(DiagramUIActionsMessages.CopyAppearancePropertiesAction_text);
        setToolTipText(Messages.SiriusCopyAppearancePropertiesAction_tooltipMessage);

        setImageDescriptor(DiagramUIActionsPluginImages.DESC_COPY_APPEARANCE);
        setDisabledImageDescriptor(DiagramUIActionsPluginImages.DESC_COPY_APPEARANCE_DISABLED);
        setHoverImageDescriptor(DiagramUIActionsPluginImages.DESC_COPY_APPEARANCE);
    }

    @Override
    protected boolean isSelectionListener() {
        return true;
    }

    @Override
    protected Request createTargetRequest() {
        return new ApplyAppearancePropertiesRequest();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected void updateTargetRequest() {
        ApplyAppearancePropertiesRequest request = (ApplyAppearancePropertiesRequest) getTargetRequest();
        List set = super.createOperationSet();
        if (!set.isEmpty()) {
            IGraphicalEditPart editPart = (IGraphicalEditPart) set.get(set.size() - 1);
            request.setViewToCopyFrom(editPart.getNotationView());
        }
        super.updateTargetRequest();
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected List createOperationSet() {
        List operationSet = super.createOperationSet();
        if (!operationSet.isEmpty())
            operationSet.remove(operationSet.size() - 1);
        return operationSet;
    }

}
