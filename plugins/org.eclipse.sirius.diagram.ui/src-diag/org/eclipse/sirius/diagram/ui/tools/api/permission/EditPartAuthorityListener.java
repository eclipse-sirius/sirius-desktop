/*******************************************************************************
 * Copyright (c) 2007, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.api.permission;

import java.util.Collection;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.RootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramRootEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.DecorationEditPolicy;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.EditPolicyRoles;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DDiagramEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.figure.DiagramSemanticElementLockedNotificationFigure;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.LockStatus;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.ext.jface.viewers.IToolTipProvider;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;

/**
 * Listener disabling /enabling edit parts when their locking status is changing.
 * 
 * @author cbrun
 * 
 */
public class EditPartAuthorityListener implements IAuthorityListener {

    private IGraphicalEditPart part;

    /**
     * Create the listener.
     * 
     * @param part
     *            the edit part on which to listen
     */
    public EditPartAuthorityListener(final IGraphicalEditPart part) {
        this.part = part;
    }

    @Override
    public void notifyIsLocked(final EObject instance) {
        if (shouldRefresh(instance))
            refreshEditMode();
    }

    @Override
    public void notifyIsReleased(final EObject instance) {
        if (shouldRefresh(instance))
            refreshEditMode();
    }

    @Override
    public void notifyIsLocked(Collection<EObject> instances) {
        for (final EObject eObject : instances) {
            if (shouldRefresh(eObject)) {
                refreshEditMode();
                return;
            }
        }
    }

    @Override
    public void notifyIsReleased(Collection<EObject> instances) {
        for (final EObject eObject : instances) {
            if (shouldRefresh(eObject)) {
                refreshEditMode();
                return;
            }
        }
    }

    private boolean shouldRefresh(EObject instance) {
        // Do not call part.resolveSemanticElement() if the part is not active
        boolean shouldRefresh = false;
        if (instance != null && part.isActive()) {
            EObject element = part.resolveSemanticElement();
            if (element instanceof DSemanticDecorator) {
                DSemanticDecorator semanticDecorator = (DSemanticDecorator) element;
                EObject target = semanticDecorator.getTarget();

                boolean isConcerningEditPart = instance.equals(target) || instance.equals(semanticDecorator);
                boolean isConcerningDiagramEditPart = target != null && instance instanceof DDiagram && semanticDecorator instanceof DDiagramElement
                        && instance.equals(((DDiagramElement) semanticDecorator).getParentDiagram());
                shouldRefresh = isConcerningEditPart || isConcerningDiagramEditPart;
            }
        }
        return shouldRefresh;
    }

    /**
     * Enables or disables the edit mode according to the {@link IPermissionAuthority} informations and refreshes
     * decorators (launches the refresh asynchronously or synchronously in the UI Thread according to the
     * {@link SiriusDiagramUiPreferencesKeys#PREF_REFRESH_DECORATORS_SYNCHRONOUSLY} preference).
     */
    public void refreshEditMode() {
        // Step 1: check if the current editor is null (means it has just been
        // closed)
        final Option<DDiagramEditor> diagramEditorIfAny = getDDiagramEditor();
        if (diagramEditorIfAny.some()) {
            final DDiagramEditor diagramEditor = diagramEditorIfAny.get();
            final EObject semanticElement = part.resolveSemanticElement();
            final IPermissionAuthority auth = diagramEditor.getPermissionAuthority();
            final boolean enableEditMode = auth.canEditInstance(semanticElement);

            // Step 2: launch the refresh synchronously or not according to
            // preferences
            boolean refreshShouldBePerformedSynchronously = DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiPreferencesKeys.PREF_REFRESH_DECORATORS_SYNCHRONOUSLY.name());
            // If refresh should be performed synchronously, we directly launch
            // the refresh
            if (refreshShouldBePerformedSynchronously) {
                doRefreshEditMode(enableEditMode, diagramEditor, semanticElement);
            } else {
                // Otherwise, we launch it asynchronously
                EclipseUIUtil.displayAsyncExec(new Runnable() {

                    @Override
                    public void run() {
                        doRefreshEditMode(enableEditMode, diagramEditor, semanticElement);
                    }
                });
            }
        }
    }

    /**
     * Refreshes the edit mode and decorators.
     * 
     * @param enableEditMode
     *            if true, then enables edit mode; disables edit mode otherwise
     * @param diagramEditor
     *            the current diagramEditor to refresh
     * @param semanticElement
     *            the semantic element associated to the current edit part
     */
    protected void doRefreshEditMode(boolean enableEditMode, DDiagramEditor diagramEditor, final EObject semanticElement) {
        // Step 1: enable/disable edit mode according to the permissions on the
        // semantic element
        if (!enableEditMode || !isTargetValid(semanticElement)) {
            part.disableEditMode();
        } else {
            part.enableEditMode();
        }

        // Step 2: refresh decorators through the decoration edit policy
        final DecorationEditPolicy decorationEditPolicy = (DecorationEditPolicy) part.getEditPolicy(EditPolicyRoles.DECORATION_ROLE);
        if (decorationEditPolicy != null && part.getParent() != null && part.getRoot() != null) {
            decorationEditPolicy.refresh();
        }

        // Step 3: specific decorator for the root semantic element
        doRefreshRootSemanticElementEditMode(diagramEditor, semanticElement);

    }

    /**
     * Refreshes the lock decoration for the semantic element associated to the diagram root (by displaying a red/green
     * padlock on the editor background).
     * 
     * @param diagramEditor
     *            the current diagramEditor to refresh
     * @param semanticElement
     *            the semantic element associated to the current edit part
     */
    private void doRefreshRootSemanticElementEditMode(DDiagramEditor diagramEditor, final EObject semanticElement) {
        if (part instanceof DDiagramEditPart && semanticElement instanceof DSemanticDecorator) {
            final DDiagramEditPart ddep = (DDiagramEditPart) part;
            RootEditPart rootEditPart = ddep.getRoot();
            if (rootEditPart instanceof DiagramRootEditPart) {
                try {
                    EObject target = ((DSemanticDecorator) semanticElement).getTarget();
                    LockStatus lockStatus = diagramEditor.getPermissionAuthority().getLockStatus(target);
                    switch (lockStatus) {
                    case LOCKED_BY_ME:
                        DiagramSemanticElementLockedNotificationFigure.createNotification((DiagramRootEditPart) rootEditPart, LockStatus.LOCKED_BY_ME);
                        break;
                    case LOCKED_BY_OTHER:
                        String tooltip = ""; //$NON-NLS-1$
                        IToolTipProvider tooltipProvider = Platform.getAdapterManager().getAdapter(semanticElement, IToolTipProvider.class);
                        if (tooltipProvider != null) {
                            tooltip = tooltipProvider.getToolTipText(target);
                        }
                        DiagramSemanticElementLockedNotificationFigure.createNotification((DiagramRootEditPart) rootEditPart, "", tooltip, LockStatus.LOCKED_BY_OTHER); //$NON-NLS-1$
                        break;
                    case NOT_LOCKED:
                    default:
                        DiagramSemanticElementLockedNotificationFigure.removeNotification((DiagramRootEditPart) rootEditPart);
                        break;
                    }
                } catch (IllegalStateException e) {
                    // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
                }
            }
        }
    }

    /**
     * Check if the target is not null for the DDiagramElement.
     * 
     * @param semanticElement
     *            The semantic element to check
     * @return true if the target value is valid, false otherwise
     */
    private boolean isTargetValid(final EObject semanticElement) {
        boolean result = false;
        try {

            if (semanticElement instanceof DDiagramElement) {
                final EObject target = ((DDiagramElement) semanticElement).getTarget();
                result = target != null && target.eResource() != null;
            } else {
                result = true;
            }
        } catch (IllegalStateException e) {
            // Nothing to log here, this can happen if the resource is not accessible anymore (distant resource).
            result = false;
        }
        return result;
    }

    /**
     * Check if an editPart is locked.
     * 
     * @return true if the edit part is locked, false otherwise.
     */
    public boolean isLocked() {
        if (part.getParent() != null && part.getRoot() != null) {
            final DDiagramEditor diagramEditor = (DDiagramEditor) part.getViewer().getProperty(DDiagramEditor.EDITOR_ID);
            if (diagramEditor != null) {
                final IPermissionAuthority auth = diagramEditor.getPermissionAuthority();
                return !auth.canEditInstance(part.resolveSemanticElement());
            }
        }
        return false;

    }

    /**
     * Returns the DDiagramEditor associated to the current edit part (if any).
     */
    private Option<DDiagramEditor> getDDiagramEditor() {
        Option<DDiagramEditor> diagramEditor = Options.newNone();
        try {
            diagramEditor = Options.newSome((DDiagramEditor) part.getViewer().getProperty(DDiagramEditor.EDITOR_ID));
        } catch (NullPointerException e) {
            // nothing to do, method will return Options.newNone()
        }
        return diagramEditor;
    }
}
