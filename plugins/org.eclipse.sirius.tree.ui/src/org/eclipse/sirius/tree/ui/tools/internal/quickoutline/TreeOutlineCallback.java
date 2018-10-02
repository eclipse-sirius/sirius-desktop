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
package org.eclipse.sirius.tree.ui.tools.internal.quickoutline;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.common.ui.tools.api.dialog.quickoutline.IQuickOutlineCallback;
import org.eclipse.ui.IEditorPart;

/**
 * A tree specific {@link IQuickOutlineCallback}.
 * 
 * @author ymortier
 */
public class TreeOutlineCallback implements IQuickOutlineCallback {

    private IEditorPart editorPart;

    /**
     * Default constructor.
     * 
     * @param editorPart
     *            a {@link IEditorPart}
     */
    public TreeOutlineCallback(IEditorPart editorPart) {
        this.editorPart = editorPart;
    }

    @Override
    public void select(Object selectedElement) {
        ISelection selection = new StructuredSelection(selectedElement);
        editorPart.getEditorSite().getSelectionProvider().setSelection(selection);
    }

}
