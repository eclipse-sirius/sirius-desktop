/*******************************************************************************
 * Copyright (c) 2018, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

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
import org.eclipse.sirius.diagram.tools.api.DiagramPlugin;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.api.ui.actions.ActionIds;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.part.EditorActionBarContributor;

/**
 * This action deactivate the layouting and show/hide mode if they are activated to go back to standard edit mode.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class StandardModeAction extends CheckedPropertyAction {

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
     *            The {@link ModesMenuManager} handling this action.
     *
     * @param editor
     *            The workbench part associated with this action
     * @param editorDiagram
     *            the {@link DDiagram} on witch the showing mode should be switched
     * @param modesMenuManager
     *            The {@link ModesMenuManager} handling this action.
     */
    public StandardModeAction(IWorkbenchPage page, DDiagramEditor editor, DDiagram editorDiagram, ModesMenuManager modesMenuManager) {
        super(page, ActionIds.DEFAULT_MODE, Messages.ChangeEditModeAction_ChangePropertyValueRequest_label, ActionIds.DEFAULT_MODE);
        setWorkbenchPart(page.getActivePart());
        setId(ActionIds.DEFAULT_MODE);
        setToolTipText(Messages.EditModeAction_Label);
        this.ddiagram = editorDiagram;
        if (ddiagram != null) {
            setImageDescriptor(DEFAULT_MODE_IMAGE_DESCRIPTOR);
            setTextAndStatusAccordingToDefaultMode();
        }
        this.editor = editor;
        this.modesMenuManager = modesMenuManager;
    }

    @Override
    protected synchronized boolean calculateChecked() {
        return ddiagram != null && !ddiagram.isIsInShowingMode() && !ddiagram.isIsInLayoutingMode();
    }

    @Override
    protected synchronized void doRun(IProgressMonitor progressMonitor) {
        if (editor == null || ddiagram == null) {
            // Can happen if we've been disposed since calculateChecked/calculateEnabled has been called.
            return;
        }
        TransactionalEditingDomain editingDomain = (TransactionalEditingDomain) editor.getEditingDomain();
        if (editingDomain != null) {

            // We don't use a command stack because we don't want the mode update to be undone
            TransactionImpl t = new TransactionImpl(editingDomain, false, Collections.EMPTY_MAP);
            try {

                t.start();
                this.ddiagram.setIsInLayoutingMode(false);
                this.ddiagram.setIsInShowingMode(false);
                t.commit();
            } catch (RollbackException | InterruptedException e) {
                DiagramPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, DiagramPlugin.ID, Messages.ChangeEditModeAction_ChangeFailure, e));
            }

        }
        modesMenuManager.refresh();
        setTextAndStatusAccordingToDefaultMode();
    }

    @Override
    protected synchronized boolean calculateEnabled() {
        return ddiagram != null && editor != null;
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
    public synchronized void dispose() {
        ddiagram = null;
        modesMenuManager = null;
        editor = null;
        super.dispose();
    }

}
