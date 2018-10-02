/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ui.tools.internal.views.common.navigator.adapters;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;

/**
 * A simple drag target adapter for {@link LocalSelectionTransfer}.
 * 
 * @author mchauvin
 */
public class ModelDragTargetAdapter extends DragSourceAdapter implements TransferDragSourceListener {

    private final ISelectionProvider provider;

    /**
     * Construct a new drag target adapter from the selection provider given as
     * parameter.
     * 
     * @param provider
     *            the selection provider
     */
    public ModelDragTargetAdapter(ISelectionProvider provider) {
        this.provider = provider;
    }

    @Override
    public Transfer getTransfer() {
        return LocalSelectionTransfer.getTransfer();
    }

    @Override
    public void dragStart(DragSourceEvent event) {
        final ISelection selection = provider.getSelection();
        LocalSelectionTransfer.getTransfer().setSelection(selection);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & 0xFFFFFFFFL);
        event.doit = true;
    }

    @Override
    public void dragSetData(DragSourceEvent event) {
        event.data = LocalSelectionTransfer.getTransfer().getSelection();
    }

    @Override
    public void dragFinished(DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(null);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
    }
}
