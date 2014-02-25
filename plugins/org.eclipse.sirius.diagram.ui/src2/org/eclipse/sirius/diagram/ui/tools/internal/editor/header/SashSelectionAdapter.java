/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.header;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * A SelectionAdapter to resize the header composite when sash is moved.
 * 
 * @author lredor
 * 
 */
public class SashSelectionAdapter extends SelectionAdapter {
    DDiagram dDiagram;

    DiagramHeaderComposite compositeToResize;

    /**
     * Default constructor.
     * 
     * @param compositeToResize
     *            The composite to resize (the top part of the
     *            {@link org.eclipse.swt.widgets.Sash}).
     * @param dDiagram
     *            The {@link DDiagram} that stores the number of lines of the
     *            header after a resizing.
     */
    public SashSelectionAdapter(DiagramHeaderComposite compositeToResize, DDiagram dDiagram) {
        super();
        this.compositeToResize = compositeToResize;
        this.dDiagram = dDiagram;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     * 
     * @see org.eclipse.swt.widgets.Sash#addSelectionListener(org.eclipse.swt.events.SelectionListener)
     */
    public void widgetSelected(SelectionEvent event) {
        // We modify the height of the widget only when it is a drag event (@see
        // Sash#addSelectionListener(org.eclipse.swt.events.SelectionListener)).
        if (event.detail == SWT.DRAG) {
            // Compute the new height of the headerSection according to the move
            // of the sash.
            int newHeight = event.y - compositeToResize.getBounds().y;
            if (newHeight > DiagramHeaderComposite.getDiagramHeaderLineHeight()) {
                // Adapt the newHeight to the
                // DEFAULT_HEADER_INSTANCE_ROLE_HEIGHT.
                // It should be a multiple of this constant.
                int div = newHeight / DiagramHeaderComposite.getDiagramHeaderLineHeight();
                int mod = newHeight % DiagramHeaderComposite.getDiagramHeaderLineHeight();
                if (mod > (DiagramHeaderComposite.getDiagramHeaderLineHeight() / 2)) {
                    div += 1;
                }
                newHeight = DiagramHeaderComposite.getDiagramHeaderLineHeight() * div;
            } else {
                // The height should be at least the default.
                newHeight = DiagramHeaderComposite.getDiagramHeaderLineHeight();
            }
            compositeToResize.setHeaderHeight(newHeight);
        }
    }
}
