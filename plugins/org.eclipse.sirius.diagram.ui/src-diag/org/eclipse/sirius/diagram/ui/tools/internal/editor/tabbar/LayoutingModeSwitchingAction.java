/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.UnexecutableCommand;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * An action that switches the LayoutingMode of the given {@link DDiagram}.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public class LayoutingModeSwitchingAction extends DiagramAction {

    /**
     * Icon used in the tabbar to allow end-user to activate layouting mode. It
     * is also used in the editor's status line to indicate that LayoutingMode
     * is active.
     */
    private static final ImageDescriptor ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.LAYOUTING_MODE_ACTIVE_ICON);

    /**
     * The {@link DDiagram} on witch the layouting mode should be switched.
     */
    private DDiagram ddiagram;

    /**
     * Default constructor.
     *
     * @param workbenchPage
     *            The workbench page associated with this action
     * @param editorDiagram
     *            the {@link DDiagram} on witch the layouting mode should be
     *            switched
     */
    public LayoutingModeSwitchingAction(IWorkbenchPage workbenchPage, DDiagram editorDiagram) {
        super(workbenchPage);
        setId(ActionIds.SWITCH_LAYOUTING_MODE);
        this.ddiagram = editorDiagram;
        setImageDescriptor(ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR);
        setTextAndStatusAccordingToLayoutingMode();
    }

    /**
     * Switches the text associated to this Action according to the current
     * LayoutMode status (activated or not) and updates this editor's
     * statusLine.
     */
    private void setTextAndStatusAccordingToLayoutingMode() {
        // Step 1 : updating action's text and image
        if (this.ddiagram != null && this.ddiagram.isIsInLayoutingMode()) {
            setText(Messages.LayoutingModeSwitchingAction_deactivate);
            setChecked(true);
        } else {
            setText(Messages.LayoutingModeSwitchingAction_activate);
            setChecked(false);
        }

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
        return AS_CHECK_BOX;
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
        List<?> selectedObjects = getSelectedObjects();
        if (!selectedObjects.isEmpty()) {
            if (selectedObjects.iterator().next() instanceof IDDiagramEditPart) {
                IDDiagramEditPart diagramEP = (IDDiagramEditPart) selectedObjects.iterator().next();
                if (diagramEP.getModel() instanceof Diagram) {
                    Diagram diagramGMF = (Diagram) diagramEP.getModel();
                    if (diagramGMF.getElement() instanceof DDiagram) {
                        returnedCommand = getCommandForDDiagram((DDiagram) diagramGMF.getElement());
                    }
                }
            }
        }

        return returnedCommand;
    }

    /**
     * Returns a command that switches the LayoutingMode of the given
     * {@link DDiagram}.
     *
     * @param diagram
     *            the {@link DDiagram} on witch the layouting mode should be
     *            switched
     * @return a command that switches the LayoutingMode of the given
     *         {@link DDiagram}
     */
    private Command getCommandForDDiagram(DDiagram diagram) {
        Command returnedCommand = UnexecutableCommand.INSTANCE;
        TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(diagram);
        if (editingDomain != null) {
            returnedCommand = new ICommandProxy(new SetLayoutingModeCommandAndUpdateActionImage(editingDomain, diagram, !diagram.isIsInLayoutingMode()));
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
     * @return true if the given ddiagram is allowing layouting mode, false
     *         otherwise
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
     * A command that changes the LayoutingMode of the given {@link DDiagram}
     * and updates the image assocatied to the Action.
     *
     * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
     *
     */
    public class SetLayoutingModeCommandAndUpdateActionImage extends AbstractTransactionalCommand {

        /**
         * The {@link DDiagram} on witch the layouting mode should be switched.
         */
        private DDiagram diagram;

        /**
         * Indicates whether the Layouting Mode should be enabled.
         */
        private boolean layoutingModeShouldBeEnabled;

        /**
         * Constructor.
         *
         * @param editingDomain
         *            the editing domain
         * @param diagram
         *            the {@link DDiagram} on witch the layouting mode should be
         *            switched
         * @param layoutingModeShouldBeEnabled
         *            indicates whether the layouting mode should be enabled or
         *            disabled
         */
        public SetLayoutingModeCommandAndUpdateActionImage(TransactionalEditingDomain editingDomain, DDiagram diagram, boolean layoutingModeShouldBeEnabled) {
            super(editingDomain, Messages.SetLayoutingModeCommandAndUpdateActionImage_activateLabel, null);
            this.diagram = diagram;
            this.layoutingModeShouldBeEnabled = layoutingModeShouldBeEnabled;
            if (this.diagram.isIsInLayoutingMode()) {
                setText(Messages.SetLayoutingModeCommandAndUpdateActionImage_deactivateLabel);
            }
        }

        /**
         *
         * {@inheritDoc}
         *
         * @see org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand#doExecuteWithResult(org.eclipse.core.runtime.IProgressMonitor,
         *      org.eclipse.core.runtime.IAdaptable)
         */
        @Override
        protected CommandResult doExecuteWithResult(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
            CommandResult commandResult = CommandResult.newOKCommandResult();
            if (LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(diagram)) {
                this.diagram.setIsInLayoutingMode(layoutingModeShouldBeEnabled);
                setTextAndStatusAccordingToLayoutingMode();
            }
            return commandResult;
        }
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
