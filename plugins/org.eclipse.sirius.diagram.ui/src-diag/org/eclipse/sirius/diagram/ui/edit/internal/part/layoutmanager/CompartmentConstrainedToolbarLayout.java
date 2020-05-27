/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part.layoutmanager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout;
import org.eclipse.sirius.ext.gmf.runtime.gef.ui.figures.SiriusWrapLabel;

/**
 * Layout manager specific for compartment.
 * 
 * @author lfasani
 */
public class CompartmentConstrainedToolbarLayout extends ConstrainedToolbarLayout {
    /*
     * (non-Javadoc) Partly copied from super class. The change is about the way the prefSize is computed.
     * @see
     * org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout#calculatePreferredSize(org.eclipse.draw2d.
     * IFigure, int, int)
     */
    @SuppressWarnings({ "deprecation" })
    @Override
    protected Dimension calculatePreferredSize(IFigure container, int wHint, int hHint) {
        Insets insets = container.getInsets();
        if (!container.isVisible())
            return new Dimension(insets.getWidth(), insets.getHeight());
        if (isHorizontal()) {
            // CHECKSTYLE:OFF
            wHint = -1;
            if (hHint >= 0)
                hHint = Math.max(0, hHint - insets.getHeight());
        } else {
            hHint = -1;
            if (wHint >= 0)
                wHint = Math.max(0, wHint - insets.getWidth());
            // CHECKSTYLE:ON
        }

        // The preferred size of the compartment is computed :
        // * width: it is the preferred width of the region(s)
        // * height : with whint=width previously computed, it is the preferred height of (the compartment label +
        // the region)
        List<IFigure> children = getChildren(container);
        //@formatter:off
        List<IFigure> childrenWithoutLabel = getChildren(container).stream()
                .filter(figure -> !(figure instanceof SiriusWrapLabel))
                .collect(Collectors.toList());
        //@formatter:on
        Dimension prefSize = calculateChildrenSize(childrenWithoutLabel, wHint, hHint, true);
        prefSize = calculateChildrenSize(children, prefSize.width, hHint, true);
        // Do a second pass, if necessary
        if (wHint >= 0 && prefSize.width > wHint) {
            prefSize = calculateChildrenSize(childrenWithoutLabel, prefSize.width, hHint, true);
        } else if (hHint >= 0 && prefSize.height > hHint) {
            prefSize = calculateChildrenSize(childrenWithoutLabel, wHint, prefSize.height, true);
        }

        prefSize.height += Math.max(0, children.size() - 1) * spacing;
        return transposer.t(prefSize).expand(insets.getWidth(), insets.getHeight()).union(getBorderPreferredSize(container));
    }

    /**
     * Copied from org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout.calculateChildrenSize(List, int,
     * int, boolean)
     */
    private Dimension calculateChildrenSize(List<IFigure> children, int wHint, int hHint, boolean preferred) {
        Dimension childSize;
        IFigure child;
        int height = 0;
        int width = 0;
        for (int i = 0; i < children.size(); i++) {
            child = children.get(i);
            childSize = transposer.t(preferred ? child.getPreferredSize(wHint, hHint) : child.getMinimumSize(wHint, hHint));
            height += childSize.height;
            width = Math.max(width, childSize.width);
        }
        return new Dimension(width, height);
    }

    /**
     * Copied from org.eclipse.gmf.runtime.draw2d.ui.figures.ConstrainedToolbarLayout.getChildren(IFigure).
     */
    private List<IFigure> getChildren(IFigure container) {
        List<IFigure> children = new ArrayList<IFigure>(container.getChildren());
        if (getIgnoreInvisibleChildren()) {
            Iterator<IFigure> iter = children.iterator();
            while (iter.hasNext()) {
                IFigure f = iter.next();
                if (!f.isVisible())
                    iter.remove();
            }
        }
        if (isReversed())
            Collections.reverse(children);
        return children;
    }
}
