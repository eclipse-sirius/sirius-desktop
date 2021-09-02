/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.Collections;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.TransactionImpl;
import org.eclipse.gmf.runtime.diagram.ui.actions.CheckedPropertyAction;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.ModesMenuManager;
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
public class LayoutingModeSwitchingAction extends CheckedPropertyAction {

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
     * The {@link DDiagramEditor} containing the tabbar containing this action.
     */
    private DDiagramEditor editor;

    /**
     * The {@link ModesMenuManager} handling this action.
     */
    private ModesMenuManager modesMenuManager;

    /**
     * Default constructor.
     * 
     * @param page
     *            The workbench part associated with this action
     * @param editor
     *            The {@link DDiagramEditor} containing the tabbar containing this action.
     * @param editorDiagram
     *            the {@link DDiagram} on witch the layouting mode should be switched
     * @param modesMenuManager
     *            The {@link ModesMenuManager} handling this action.
     */
    public LayoutingModeSwitchingAction(IWorkbenchPage page, DDiagramEditor editor, DDiagram editorDiagram, ModesMenuManager modesMenuManager) {
        super(page, ActionIds.SWITCH_LAYOUTING_MODE, Messages.ChangeEditModeAction_ChangePropertyValueRequest_label, ActionIds.SWITCH_LAYOUTING_MODE);
        setWorkbenchPart(page.getActivePart());
        setId(ActionIds.SWITCH_LAYOUTING_MODE);
        this.ddiagram = editorDiagram;
        setImageDescriptor(ACTIVATE_LAYOUTING_MODE_IMAGE_DESCRIPTOR);
        setTextAndStatusAccordingToLayoutingMode();
        setToolTipText(Messages.EditModeAction_Label);
        this.editor = editor;
        this.modesMenuManager = modesMenuManager;
    }

    @Override
    protected boolean calculateChecked() {
        return ddiagram != null && ddiagram.isIsInLayoutingMode();
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) {
        TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();
        if (editingDomain != null) {

            // We don't use a command stack because we don't want the mode update to be undone
            TransactionImpl t = new TransactionImpl(editingDomain, false, Collections.EMPTY_MAP);
            try {
                t.start();
                this.ddiagram.setIsInLayoutingMode(true);
                this.ddiagram.setIsInShowingMode(false);
                t.commit();
            } catch (RollbackException | InterruptedException e) {
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, Messages.ChangeEditModeAction_ChangeFailure, e));
            }

        }
        modesMenuManager.refresh();
        setTextAndStatusAccordingToLayoutingMode();
    }

    @Override
    protected boolean calculateEnabled() {
        return ddiagram != null && editor != null;
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
     * @see org.eclipse.gmf.runtime.diagram.ui.actions.DiagramAction#dispose()
     */
    @Override
    public void dispose() {
        ddiagram = null;
        modesMenuManager = null;
        editor = null;
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

}
