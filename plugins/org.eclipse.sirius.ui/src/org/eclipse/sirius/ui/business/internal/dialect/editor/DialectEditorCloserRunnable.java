/*******************************************************************************
 * Copyright (c) 2009, 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.business.internal.dialect.editor;

import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;

/**
 * {@link Runnable} to close a {@link DialectEditor}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorCloserRunnable implements Runnable {

    private IEditingSession editingSession;

    private DialectEditor dialectEditor;

    /**
     * Default constructor.
     * 
     * @param editingSession
     *            the {@link IEditingSession} to close the {@link DialectEditor}
     * @param dialectEditor
     *            the {@link DialectEditor} to close
     */
    public DialectEditorCloserRunnable(IEditingSession editingSession, DialectEditor dialectEditor) {
        this.editingSession = editingSession;
        this.dialectEditor = dialectEditor;
    }

    @Override
    public void run() {
        if (dialectEditor != null && editingSession.getEditors().contains(dialectEditor)) {
            // We open a dialog informing the user that the
            // representation or its target has been deleted, and close
            // the editor
            if (dialectEditor.getSite() != null && dialectEditor.getSite().getShell() != null) {
                dialectEditor.getDialogFactory().editorWillBeClosedInformationDialog(dialectEditor.getSite().getShell());
            }
            editingSession.closeEditors(false, dialectEditor);
        }
    }

}
