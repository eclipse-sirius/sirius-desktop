/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 *
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
