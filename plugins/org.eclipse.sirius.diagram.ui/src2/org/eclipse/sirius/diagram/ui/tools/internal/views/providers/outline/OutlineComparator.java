/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.views.providers.outline;

import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;

/**
 * A comparator for the outline tree viewer.
 * 
 * @author Mariot Chauvin (mchauvin)
 */
public class OutlineComparator extends ViewerComparator {

    private static final int VISIBLE_ELEMENT = 0;

    private static final int HIDDEN_ELEMENT = 2;

    private static final int OTHER_NON_VISIBLE_ELEMENT = 4;

    private static final int VIEW_NODE = 0;

    private static final int VIEW_EDGE = 1;

    private static final int OTHER = 64;

    /**
     * Constructor.
     */
    public OutlineComparator() {
        super();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ViewerComparator#category(java.lang.Object)
     */
    @Override
    public int category(final Object element) {

        int value = 0;
        if (element instanceof DDiagramElement) {
            if (((DDiagramElement) element).isVisible()) {
                value += VISIBLE_ELEMENT;
            } else if (new DDiagramElementQuery((DDiagramElement) element).isHidden()) {
                value += HIDDEN_ELEMENT;
            } else {
                value += OTHER_NON_VISIBLE_ELEMENT;
            }

            if (element instanceof DEdge) {
                value += VIEW_EDGE;
            } else {
                value += VIEW_NODE;
            }

        } else {
            value += OTHER;
        }
        return value;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.viewers.ViewerComparator#isSorterProperty(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public boolean isSorterProperty(final Object element, final String property) {
        return false;
    }
}
