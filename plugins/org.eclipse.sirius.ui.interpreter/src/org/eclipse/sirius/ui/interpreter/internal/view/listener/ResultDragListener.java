/*******************************************************************************
 * Copyright (c) 2011, 2025 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.interpreter.internal.view.listener;

import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.events.TypedEvent;

/**
 * This listener will be registered against the "Result" TreeViewer in order to allow drag operations on that viewer.
 * 
 * @author <a href="mailto:laurent.goubet@obeo.fr">Laurent Goubet</a>
 */
public class ResultDragListener extends DragSourceAdapter {
    /**
     * TypedEvent.time is an unsigned integer while we need a signed long.
     * 
     * @see TypedEvent#time
     */
    private static final long TIME_MASK = 0xFFFFFFFFL;

    /** Keeps a reference towards the viewer against which this listener is registered. */
    private TreeViewer viewer;

    /**
     * Creates a new drag listener for the given <code>viewer</code>.
     * 
     * @param viewer
     *            The viewer against which this drag listener is registered.
     */
    public ResultDragListener(TreeViewer viewer) {
        this.viewer = viewer;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragFinished(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragFinished(DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(null);
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(0);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragSetData(DragSourceEvent event) {
        if (LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType)) {
            event.data = LocalSelectionTransfer.getTransfer().getSelection();
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.dnd.DragSourceAdapter#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
     */
    @Override
    public void dragStart(DragSourceEvent event) {
        LocalSelectionTransfer.getTransfer().setSelection(viewer.getSelection());
        LocalSelectionTransfer.getTransfer().setSelectionSetTime(event.time & TIME_MASK);
        event.doit = true;
    }
}
