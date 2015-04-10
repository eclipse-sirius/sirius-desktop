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
