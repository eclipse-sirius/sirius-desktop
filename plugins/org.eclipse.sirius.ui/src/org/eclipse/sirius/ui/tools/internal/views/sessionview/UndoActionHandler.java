/*******************************************************************************
 * Copyright (c) 2011 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.views.sessionview;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchMessages;

/**
 * <p>
 * UndoActionHandler provides common behavior for performing an undo, as well as
 * labelling and enabling the undo menu item. This class may be instantiated by
 * clients.
 * </p>
 * 
 * @since 3.1
 */
public final class UndoActionHandler extends OperationHistoryActionHandler {

    /**
     * Construct an action handler that handles the labelling and enabling of
     * the undo action for the specified undo context.
     * 
     * @param site
     *            the workbench part site that created the action.
     * @param designerSessionView
     *            the {@link DesignerSessionView} from which to gte the undo
     *            context
     */
    public UndoActionHandler(IWorkbenchPartSite site, DesignerSessionView designerSessionView) {
        super(site, designerSessionView);
        setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
        setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO_DISABLED));
    }

    @Override
    void flush() {
        getHistory().dispose(getUndoContext(), true, false, false);
    }

    @Override
    String getCommandString() {
        return WorkbenchMessages.Operations_undoCommand;
    }

    @Override
    String getTooltipString() {
        return WorkbenchMessages.Operations_undoTooltipCommand;
    }

    @Override
    String getSimpleCommandString() {
        return WorkbenchMessages.Workbench_undo;
    }

    @Override
    String getSimpleTooltipString() {
        return WorkbenchMessages.Workbench_undoToolTip;
    }

    @Override
    IUndoableOperation getOperation() {
        return getHistory().getUndoOperation(getUndoContext());

    }

    @Override
    IStatus runCommand(IProgressMonitor pm) throws ExecutionException {
        return getHistory().undo(getUndoContext(), pm, this);
    }

    @Override
    boolean shouldBeEnabled() {
        return getHistory().canUndo(getUndoContext());
    }
}
