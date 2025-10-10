/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.acceleo.ui.interpreter.internal.view.actions;

import org.eclipse.acceleo.ui.interpreter.internal.IInterpreterConstants;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterImages;
import org.eclipse.acceleo.ui.interpreter.internal.InterpreterMessages;
import org.eclipse.acceleo.ui.interpreter.view.InterpreterView;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;

/**
 * This action will allow us to link the current language interpreter with the currently focused editor.
 * <p>
 * It is up to the language to determine what to do with the editor, and some might very well ignore it altogether.
 * </p>
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class LinkWithEditorContextAction extends Action {
    /** The tooltip we'll show for this action. */
    private static final String DEFAULT_TOOLTIP_TEXT = InterpreterMessages.getString("intepreter.action.link.default.tooltip"); //$NON-NLS-1$

    /** Keeps a reference to the interpreter view. */
    private InterpreterView view;

    /**
     * Instantiates the Link with editor toggle given the interpreter view it should operate on.
     * 
     * @param view
     *            The interpreter view it should operate on.
     */
    public LinkWithEditorContextAction(InterpreterView view) {
        super(null, IAction.AS_CHECK_BOX);
        setToolTipText(DEFAULT_TOOLTIP_TEXT);
        setImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_ICON));
        setDisabledImageDescriptor(InterpreterImages.getImageDescriptor(IInterpreterConstants.LINK_WITH_EDITOR_CONTEXT_ACTION_DISABLED_ICON));
        this.view = view;
    }

    /**
     * Changes the tooltip of this action to reflect that it is now linked with the given editor (or that the link has
     * been disabled).
     * 
     * @param editorPart
     *            The editor we are currently linked with. May be <code>null</code>.
     */
    public void changeTooltip(IEditorPart editorPart) {
        if (editorPart != null) {
            final IEditorInput input = editorPart.getEditorInput();
            final IFile file = (IFile) Platform.getAdapterManager().getAdapter(input, IFile.class);
            if (file != null) {
                setToolTipText(InterpreterMessages.getString("intepreter.action.link.active.tooltip", //$NON-NLS-1$
                        file.getName()));
            }
        } else {
            setToolTipText(DEFAULT_TOOLTIP_TEXT);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.Action#run()
     */
    @Override
    public void run() {
        if (view != null) {
            view.linkWithEditorContext();
        }
    }
}
