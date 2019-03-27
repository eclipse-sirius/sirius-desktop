/*******************************************************************************
 * Copyright (c) 2008, 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.action;

import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.diagram.ui.parts.DiagramEditor;
import org.eclipse.sirius.common.tools.api.find.AbstractFindLabelEngine;
import org.eclipse.sirius.common.ui.tools.api.find.AbstractFindLabelDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * An implementation of the AbstractFindLabelDialog that use the "reveal" method when the "next" button is used.
 * 
 * @author glefur
 */
public class RevealFindLabelDialog extends AbstractFindLabelDialog {
    private final DiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param parentShell
     *            the parent shell
     * @param engine
     *            the find label engine
     * @param currentEditor
     *            the current editor
     */
    public RevealFindLabelDialog(final Shell parentShell, final AbstractFindLabelEngine engine, final DiagramEditor currentEditor) {
        super(parentShell, engine);
        this.editor = currentEditor;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.common.ui.tools.api.find.AbstractFindLabelDialog#selectElement(java.lang.Object)
     */
    @Override
    protected void selectElement(final Object selection) {
        EditPart editPart = (EditPart) selection;
        if (!editPart.isSelectable()) {
            // As container labels are not selectable, we must select its parent
            editPart = getFirstSelectableParentEditPart(editPart);
            assert editPart != null;
        }
        editor.getDiagramGraphicalViewer().select(editPart);
        editor.getDiagramGraphicalViewer().reveal(editPart);
    }

    private EditPart getFirstSelectableParentEditPart(EditPart editPart) {
        EditPart returnedEditPart = editPart.getParent();
        if (returnedEditPart != null && !returnedEditPart.isSelectable()) {
            return getFirstSelectableParentEditPart(returnedEditPart);
        }
        return returnedEditPart;
    }
}
