/*******************************************************************************
 * Copyright (c) 2010, 2013 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.sequence.business.internal.query;

import java.util.Objects;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElement;

import com.google.common.collect.Iterables;

/**
 * General queries on {@link ISequenceElement}s.
 * 
 * @author pcdavid
 */
public class ISequenceElementQuery {
    /**
     * The event to query.
     */
    protected final ISequenceElement event;

    /**
     * Constructor.
     * 
     * @param event
     *            the event to query.
     */
    public ISequenceElementQuery(ISequenceElement event) {
        this.event = Objects.requireNonNull(event);
    }

    /**
     * Check the presence of the absolute bounds flag.
     * 
     * @return true if the element is flagged.
     */
    public boolean hasAbsoluteBoundsFlag() {
        if (event.getNotationView() != null && event.getNotationView().getElement() instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) event.getNotationView().getElement();
            return !Iterables.isEmpty(Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class));
        }
        return false;
    }

    /**
     * Return the flagged absolute bounds, ie the last known logical bounds.
     * 
     * @return the flagged absolute bounds.
     */
    public Rectangle getFlaggedAbsoluteBounds() {
        Rectangle bounds = new Rectangle();
        if (event.getNotationView() != null && event.getNotationView().getElement() instanceof DDiagramElement) {
            DDiagramElement dde = (DDiagramElement) event.getNotationView().getElement();
            Iterable<AbsoluteBoundsFilter> flags = Iterables.filter(dde.getGraphicalFilters(), AbsoluteBoundsFilter.class);
            if (!Iterables.isEmpty(flags)) {
                AbsoluteBoundsFilter flag = flags.iterator().next();
                bounds.x = safeGetInt(flag.getX());
                bounds.y = safeGetInt(flag.getY());
                bounds.width = safeGetInt(flag.getWidth());
                bounds.height = safeGetInt(flag.getHeight());
            }
        }
        return bounds;
    }

    private int safeGetInt(Integer i) {
        return i == null ? 0 : i;
    }
}
