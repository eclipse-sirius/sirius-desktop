/*******************************************************************************
 * Copyright (c) 2017, 2018 THALES GLOBAL SERVICES and others.
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
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.business.api.diagramtype.DiagramTypeDescriptorRegistry;
import org.eclipse.sirius.diagram.business.api.diagramtype.IDiagramTypeDescriptor;
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
 * An action that switches the showing mode of the given {@link DDiagram}.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class VisibilityModeSwitchingAction extends CheckedPropertyAction {

    /**
     * Icon used in the tabbar to allow end-user to activate the showing mode. It is also used in the editor's status
     * line to indicate that showing mode is active.
     */
    private static final ImageDescriptor ACTIVATE_SHOW_HIDE_MODE_IMAGE_DESCRIPTOR = DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.SHOWING_MODE_ACTIVE_ICON);

    /**
     * The {@link DDiagram} on witch the showing mode should be switched.
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
     *
     * @param editor
     *            The {@link DDiagramEditor} containing the tabbar containing this action.
     * @param editorDiagram
     *            the {@link DDiagram} on witch the showing mode should be switched
     * @param modesMenuManager
     *            The {@link ModesMenuManager} handling this action.
     */
    public VisibilityModeSwitchingAction(IWorkbenchPage page, DDiagramEditor editor, DDiagram editorDiagram, ModesMenuManager modesMenuManager) {
        super(page, ActionIds.SWITCH_SHOWING_MODE, Messages.ChangeEditModeAction_ChangePropertyValueRequest_label, ActionIds.SWITCH_SHOWING_MODE);
        setWorkbenchPart(page.getActivePart());
        setId(ActionIds.SWITCH_SHOWING_MODE);
        setToolTipText(Messages.EditModeAction_Label);
        this.ddiagram = editorDiagram;
        if (ddiagram != null) {
            setImageDescriptor(ACTIVATE_SHOW_HIDE_MODE_IMAGE_DESCRIPTOR);
            setTextAndStatusAccordingToShowingMode();
        }
        this.editor = editor;
        this.modesMenuManager = modesMenuManager;
    }

    /**
     * Switches the text associated to this Action according to the current ShowingMode status (activated or not) and
     * updates this editor's statusLine.
     */
    private void setTextAndStatusAccordingToShowingMode() {
        // Step 1 : updating action's text and image
        setText(Messages.ShowingModeSwitchingAction_label);

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
                if (this.ddiagram != null && this.ddiagram.isIsInShowingMode()) {
                    String statusMessage = Messages.ShowingModeSwitchingAction_statusOn;
                    statusLineManager.setMessage(DiagramUIPlugin.getPlugin().getImage(ACTIVATE_SHOW_HIDE_MODE_IMAGE_DESCRIPTOR), statusMessage);
                } else {
                    statusLineManager.setMessage(""); //$NON-NLS-1$
                }
            }
        }
    }

    @Override
    public int getStyle() {
        return AS_CHECK_BOX;
    }

    @Override
    protected boolean isSelectionListener() {
        return false;
    }

    @Override
    protected boolean calculateEnabled() {
        return ddiagram != null && editor != null;
    }

    @Override
    protected void doRun(IProgressMonitor progressMonitor) {
        TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();
        if (editingDomain != null) {

            // We don't use a command stack because we don't want the mode update to be undone
            TransactionImpl t = new TransactionImpl(editingDomain, false, Collections.EMPTY_MAP);
            try {
                t.start();
                this.ddiagram.setIsInLayoutingMode(false);
                this.ddiagram.setIsInShowingMode(true);
                t.commit();
            } catch (RollbackException | InterruptedException e) {
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, Messages.ChangeEditModeAction_ChangeFailure, e));
            }

        }
        modesMenuManager.refresh();
        setTextAndStatusAccordingToShowingMode();
    }

    @Override
    protected boolean calculateChecked() {
        return ddiagram != null && ddiagram.isIsInShowingMode();
    }

    @Override
    public void dispose() {
        ddiagram = null;
        modesMenuManager = null;
        editor = null;
        super.dispose();
    }

    /**
     * Indicates if the given ddiagram is allowing visibility mode.
     *
     * @param diagram
     *            the diagram to inspect
     * @return true if the given ddiagram is allowing visibility mode, false otherwise.
     */
    public static boolean diagramAllowsVisibilityMode(DDiagram diagram) {

        // If an aird has been opened from the Package Explorer View, then we
        // return false as no diagram is associated to this editor
        if (diagram == null || diagram.getDescription() == null) {
            return false;
        }

        // If diagram is not null, we search for a possible
        // DiagramDescriptionProvider handling this type of diagram
        boolean diagramAllowsVisibilityMode = true;
        for (final IDiagramTypeDescriptor diagramTypeDescriptor : DiagramTypeDescriptorRegistry.getInstance().getAllDiagramTypeDescriptors()) {
            if (diagramTypeDescriptor.getDiagramDescriptionProvider().handles(diagram.getDescription().eClass().getEPackage())) {
                // This DiagramDescriptionProvider may forbid layouting mode
                diagramAllowsVisibilityMode = diagramAllowsVisibilityMode && diagramTypeDescriptor.getDiagramDescriptionProvider().allowsVisibilityModeActivation();
                break;
            }
        }
        // default return value is true (for basic DDiagram that are not handled
        // by any DiagramDescriptionProvider
        return diagramAllowsVisibilityMode;

    }

}
