/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.SetLayoutingModeCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.SetShowingModeCommand;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * This action deactivate the layouting and show/hide mode if they are activated to go back to standard edit mode.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class StandardModeAction extends DiagramAction {

    /**
     * Icon used in the tabbar to allow end-user to activate the showing mode. It is also used in the editor's status
     * line to indicate that showing mode is active.
     */
    private static final ImageDescriptor DEFAULT_MODE_IMAGE_DESCRIPTOR = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DEFAULT_MODE);

    /**
     * The {@link DDiagram} on witch the showing mode should be switched.
     */
    private DDiagram ddiagram;

    /**
     * Default constructor.
     *
     * @param iWorkbenchPart
     *            The workbench part associated with this action
     * @param editorDiagram
     *            the {@link DDiagram} on witch the showing mode should be switched
     */
    public StandardModeAction(IWorkbenchPart iWorkbenchPart, DDiagram editorDiagram) {
        super(iWorkbenchPart);
        setId(ActionIds.DEFAULT_MODE);
        this.ddiagram = editorDiagram;
        if (ddiagram != null) {
            setImageDescriptor(DEFAULT_MODE_IMAGE_DESCRIPTOR);
            setTextAndStatusAccordingToDefaultMode();
        }
    }

    /**
     * Switches the text associated to this Action and updates this editor's statusLine.
     */
    private void setTextAndStatusAccordingToDefaultMode() {
        // Step 1 : updating action's text and image
        setText(Messages.DefaultModeAction_Label);

        // Step 2 : updating the editor's status bar
        IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
        if (activeEditor != null) {
            // Step 2.1 : trying to get the status line manager
            IEditorSite site = (IEditorSite) activeEditor.getSite();
            EditorActionBarContributor actionBarContributor = null;
            IStatusLineManager statusLineManager = null;
            if (site != null && site.getActionBarContributor() instanceof EditorActionBarContributor) {
                actionBarContributor = (EditorActionBarContributor) site.getActionBarContributor();
            }
            if (actionBarContributor != null && actionBarContributor.getActionBars() != null) {
                statusLineManager = actionBarContributor.getActionBars().getStatusLineManager();
            }

            // Step 2.2 : if statusLineManager can be found
            if (statusLineManager != null) {
                // we update it according to the DDiagram showing mode status
                String statusMessage = Messages.DefaultModeAction_statusOn;
                statusLineManager.setMessage(DiagramUIPlugin.getPlugin().getImage(DEFAULT_MODE_IMAGE_DESCRIPTOR), statusMessage);
            }
        }
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.jface.action.Action#getStyle()
     */
    @Override
    public int getStyle() {
        return AS_UNSPECIFIED;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#isSelectionListener()
     */
    @Override
    protected boolean isSelectionListener() {
        return false;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#getCommand()
     */
    @Override
    protected Command getCommand() {
        Command returnedCommand = UnexecutableCommand.INSTANCE;
        if (getWorkbenchPart() != null) {
            String commandLabel = ddiagram.isIsInShowingMode() ? Messages.SetShowingModeCommandAndUpdateActionImage_deactivateLabel : Messages.SetShowingModeCommandAndUpdateActionImage_activateLabel;
            returnedCommand = new ICommandProxy(new SetShowingModeCommand(TransactionUtil.getEditingDomain(ddiagram), ddiagram, commandLabel, false))
                    .chain(new ICommandProxy(new SetLayoutingModeCommand(TransactionUtil.getEditingDomain(ddiagram), ddiagram, false) {

                        @Override
                        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                            CommandResult doExecuteWithResult = super.doExecuteWithResult(monitor, info);
                            if (IStatus.OK == doExecuteWithResult.getStatus().getCode()) {
                                setTextAndStatusAccordingToDefaultMode();
                            }
                            return doExecuteWithResult;
                        }
                    }));
        }
        return returnedCommand;
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#dispose()
     */
    @Override
    public void dispose() {
        ddiagram = null;
        super.dispose();
    }

    /**
     *
     * {@inheritDoc}
     *
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#createTargetRequest()
     */
    @Override
    protected Request createTargetRequest() {
        return null;
    }

}
