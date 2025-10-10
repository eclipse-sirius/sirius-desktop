/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.sirius.ui.interpreter.internal.view.InterpreterView;
import org.eclipse.sirius.ui.interpreter.internal.view.actions.LinkWithEditorContextAction;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IWorkbenchPartReference;

/**
 * This implementation of a part listener will allow us to determine at all times whether the "work in editor context"
 * action should be enabled.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class InterpreterEditorPartListener implements IPartListener2 {
    private LinkWithEditorContextAction linkWithEditorContextAction;

    private InterpreterView interpreterView;

    /**
     * Constructor.
     *
     * @param linkWithEditorContextAction
     * @param interpreterView
     */
    public InterpreterEditorPartListener(LinkWithEditorContextAction linkWithEditorContextAction, InterpreterView interpreterView) {
        this.linkWithEditorContextAction = linkWithEditorContextAction;
        this.interpreterView = interpreterView;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partActivated(IWorkbenchPartReference partRef) {
        // If the toggle is checked, defer enablement computing till we uncheck it
        if (!linkWithEditorContextAction.isChecked() && partRef instanceof IEditorReference) {
            linkWithEditorContextAction.setEnabled(interpreterView.getCurrentLanguageInterpreter().canLinkWithEditor(((IEditorReference) partRef).getEditor(false)));
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partClosed(IWorkbenchPartReference partRef) {
        // If the toggle is checked, defer enablement computing till we uncheck it
        if (!linkWithEditorContextAction.isChecked() && partRef instanceof IEditorReference && interpreterView.getSite().getPage() != null) {
            final IEditorPart editorPart = interpreterView.getSite().getPage().getActiveEditor();
            if (editorPart == null) {
                linkWithEditorContextAction.setEnabled(false);
            } else {
                linkWithEditorContextAction.setEnabled(interpreterView.getCurrentLanguageInterpreter().canLinkWithEditor(editorPart));
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partDeactivated(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partHidden(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partInputChanged(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partOpened(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partVisible(IWorkbenchPartReference partRef) {
        // No need to react to this event
    }
}
