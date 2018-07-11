/*******************************************************************************
 * Copyright (c) 2011, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
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
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * An action that switches the LayoutingMode of the given {@link DDiagram}.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public class LayoutingModeSwitchingAction extends DiagramAction {

    /**
     * Icon used in the tabbar to allow end-user to activate layouting mode. It is also used in the editor's status line
     * to indicate that LayoutingMode is active.
     */
    private static final ImageDescriptor ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.LAYOUTING_MODE_ACTIVE_ICON);

    /**
     * The {@link DDiagram} on witch the layouting mode should be switched.
     */
    private DDiagram ddiagram;

    /**
     * Default constructor.
     *
     * @param iWorkbenchPart
     *            The workbench part associated with this action
     * @param editorDiagram
     *            the {@link DDiagram} on witch the layouting mode should be switched
     */
    public LayoutingModeSwitchingAction(IWorkbenchPart iWorkbenchPart, DDiagram editorDiagram) {
        super(iWorkbenchPart);
        setId(ActionIds.SWITCH_LAYOUTING_MODE);
        this.ddiagram = editorDiagram;
        setImageDescriptor(ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR);
        setTextAndStatusAccordingToLayoutingMode();
    }

    /**
     * Switches the text associated to this Action according to the current LayoutMode status (activated or not) and
     * updates this editor's statusLine.
     */
    private void setTextAndStatusAccordingToLayoutingMode() {
        // Step 1 : updating action's text and image
        setText(Messages.LayoutingModeSwitchingAction_label);

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
                // we update it according to the DDiagram layouting mode status
                if (this.ddiagram != null && this.ddiagram.isIsInLayoutingMode()) {
                    String statusMessage = Messages.LayoutingModeSwitchingAction_statusOn;
                    statusLineManager.setMessage(DiagramUIPlugin.getPlugin().getImage(ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR), statusMessage);
                } else {
                    statusLineManager.setMessage(""); //$NON-NLS-1$
                }
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
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(ddiagram);
        if (editingDomain != null) {
            setTextAndStatusAccordingToLayoutingMode();
            String commandLabel = ddiagram.isIsInShowingMode() ? Messages.SetShowingModeCommandAndUpdateActionImage_deactivateLabel : Messages.SetShowingModeCommandAndUpdateActionImage_activateLabel;
            returnedCommand = new ICommandProxy(new SetShowingModeCommand(TransactionUtil.getEditingDomain(ddiagram), ddiagram, commandLabel, false))
                    .chain(new ICommandProxy(new SetLayoutingModeCommand(editingDomain, ddiagram, true) {

                        @Override
                        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
                            CommandResult doExecuteWithResult = super.doExecuteWithResult(monitor, info);
                            if (IStatus.OK == doExecuteWithResult.getStatus().getCode()) {
                                setTextAndStatusAccordingToLayoutingMode();
                            }
                            return doExecuteWithResult;

                        }

                    }));
            if (ddiagram.isIsInLayoutingMode()) {
                setText(Messages.SetLayoutingModeCommandAndUpdateActionImage_deactivateLabel);
            }
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
     * Indicates if the given ddiagram is allowing layouting mode.
     *
     * @param diagram
     *            the diagram to inspect
     * @return true if the given ddiagram is allowing layouting mode, false otherwise
     */
    public static boolean diagramAllowsLayoutingMode(DDiagram diagram) {
        // If an aird has been opened from the Package Explorer View, then we
        // return false as no diagram is associated to this editor
        if (diagram == null || diagram.getDescription() == null) {
            return false;
        }

        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram
        boolean diagramAllowsLayoutingMode = true;
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagram.getDescription().eClass().getEPackage())) {
                // This DiagramDescriptionProvider may forbid layouting mode
                diagramAllowsLayoutingMode = diagramAllowsLayoutingMode && diagramTypeDescriptor.getDiagramDescriptionProvider().allowsLayoutingModeActivation();
                break;
            }
        }
        // default return value is true (for basic DDiagram that are not handled
        // by any DiagramDescriptionProvider
        return diagramAllowsLayoutingMode;
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
