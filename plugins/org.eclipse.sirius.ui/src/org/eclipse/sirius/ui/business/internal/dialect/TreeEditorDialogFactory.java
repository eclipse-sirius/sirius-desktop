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
package org.eclipse.sirius.ui.business.internal.dialect;

import java.text.MessageFormat;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.sirius.ui.business.api.dialect.DefaultDialectEditorDialogFactory;
import org.eclipse.sirius.ui.tools.internal.editor.AbstractDTreeEditor;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.ui.PlatformUI;

/**
 * Dialog factory for AbstractDTreeEditors.
 *
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 *
 */
public class TreeEditorDialogFactory extends DefaultDialectEditorDialogFactory {
    private AbstractDTreeEditor editor;

    /**
     * Default constructor.
     *
     * @param abstractDTreeEditor
     *            the editor associated to this
     *            {@link org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory}
     *            .
     */
    public TreeEditorDialogFactory(AbstractDTreeEditor abstractDTreeEditor) {
        this.editor = abstractDTreeEditor;
    }

    @Override
    public void informUserOfEvent(int severity, String message) {
        if ((PlatformUI.getWorkbench() != null) && (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null)) {
            MessageDialog.openWarning(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), MessageFormat.format(Messages.TreeEditorDialogFactory_error, editor.getTitle()), message);
        }
    }
}
