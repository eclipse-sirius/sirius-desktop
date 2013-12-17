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
package org.eclipse.sirius.diagram.ui.tools.internal.figure.locator;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;

/**
 * A specific bordered item locator for feedback when moving a border node.
 * 
 * @author fbarbin
 */
public class FeedbackDBorderItemLocator extends DBorderItemLocator {

    /**
     * Default constructor.
     * 
     * @param parentFigure
     *            the feedback target figure.
     */
    public FeedbackDBorderItemLocator(IFigure parentFigure) {
        super(parentFigure);
    }

    @Override
    protected Option<Rectangle> conflicts(Point recommendedLocation, IFigure targetBorderItem, Collection<IFigure> portsFiguresToIgnore) {
        final Rectangle recommendedRect = new Rectangle(recommendedLocation, getSize(targetBorderItem));
        IFigure parentFigure = getParentFigure();
        if (parentFigure instanceof BorderedNodeFigure) {
            parentFigure = ((BorderedNodeFigure) parentFigure).getBorderItemContainer();
        }
        final List borderItems = parentFigure.getChildren();
        final ListIterator iterator = borderItems.listIterator();
        while (iterator.hasNext()) {
            final IFigure borderItem = (IFigure) iterator.next();
            if (!portsFiguresToIgnore.contains(borderItem)) {
                if (borderItem.isVisible()) {
                    final Rectangle rect = new Rectangle(borderItem.getBounds());
                    if (!(portsFiguresToIgnore.contains(borderItem)) && borderItem != targetBorderItem && rect.intersects(recommendedRect)) {
                        return Options.newSome(rect);
                    }
                }
            }
        }
        return Options.newNone();
    }

}
