/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.outline;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.IQuickOutlineCallback;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;

/**
 * The VSM outline callback.
 * 
 * @author bgrouhan
 */
public class VSMOutlineCallback implements IQuickOutlineCallback {

    private CustomSiriusEditor editorPart;

    /**
     * The constructor.
     * 
     * @param siriusEditor
     *            the Sirius editor.
     */
    public VSMOutlineCallback(CustomSiriusEditor siriusEditor) {
        this.editorPart = siriusEditor;
    }

    @Override
    public void select(Object selectedElement) {
        if (selectedElement != null) {
            ISelection selection = new StructuredSelection(selectedElement);
            editorPart.getEditorSite().getSelectionProvider().setSelection(selection);
            editorPart.handleContentOutlineSelection(selection);
        }
    }
}
