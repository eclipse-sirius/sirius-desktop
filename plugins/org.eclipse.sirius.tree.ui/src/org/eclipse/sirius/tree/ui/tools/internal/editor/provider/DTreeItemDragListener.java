/*******************************************************************************
 * Copyright (c) 2009, 2011 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;

/**
 * .
 * 
 * @author alagarde
 */
public class DTreeItemDragListener extends DragSourceAdapter implements DragSourceListener {

    private ISelectionProvider selectionProvider;

    /**
     * Construct a new instance.
     * 
     * @param selectionProvider
     *            the selection provider
     */
    public DTreeItemDragListener(ISelectionProvider selectionProvider) {
        this.selectionProvider = selectionProvider;
    }

    @Override
    public void dragFinished(DragSourceEvent event) {
    }

    @Override
    public void dragSetData(DragSourceEvent event) {
        // We set the data of the drag to all selected DTreeItem (if any)
        ISelection selection = this.selectionProvider.getSelection();
        if (selection instanceof IStructuredSelection) {
            Set<DTreeItem> dragData = new LinkedHashSet<>();
            Iterator<Object> selectionIterator = ((IStructuredSelection) selection).iterator();
            while (selectionIterator.hasNext()) {
                Object selectedElement = selectionIterator.next();
                if (selectedElement instanceof DTreeItem) {
                    dragData.add((DTreeItem) selectedElement);
                }
            }
            LocalSelectionTransfer.getTransfer().setSelection(this.selectionProvider.getSelection());
            event.data = dragData;
        }
    }

    @Override
    public void dragStart(DragSourceEvent event) {
        event.doit = !this.selectionProvider.getSelection().isEmpty();
    }
}
