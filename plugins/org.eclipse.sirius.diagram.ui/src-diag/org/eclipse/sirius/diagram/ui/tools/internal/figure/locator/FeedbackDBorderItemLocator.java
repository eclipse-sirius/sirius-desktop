/*******************************************************************************
 * Copyright (c) 2013, 2023 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.internal.figure.locator;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.figures.BorderedNodeFigure;
import org.eclipse.sirius.diagram.ui.tools.api.figure.locator.DBorderItemLocator;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
    protected List<IFigure> getBrotherFigures(IFigure targetBorderItem) {
        IFigure parentFigure = getParentFigure();
        if (parentFigure instanceof BorderedNodeFigure) {
            parentFigure = ((BorderedNodeFigure) parentFigure).getBorderItemContainer();
        }
        @SuppressWarnings("unchecked")
        Iterable<? extends IFigure> brotherFigures = Iterables.filter(parentFigure.getChildren(), Predicates.and(Predicates.instanceOf(IFigure.class), Predicates.not(Predicates.equalTo(targetBorderItem))));
        return Lists.newArrayList(brotherFigures);
    }

    /**
     * Determine if the the given rectangle conflicts with the position of
     * <code>figuresToCheck</code>.
     * 
     * @param recommendedRect
     *            The desired bounds
     * @param figuresToCheck
     *            Other figures to check if they conflict the
     *            <code>recommendedRect</code>
     * @param portsFiguresToIgnore
     *            the ports figures to ignore, even if they are in
     *            <code>figuresToCheck</code>
     * @return the optional Rectangle of the border item that is in conflict
     *         with the given bordered node (none option if no conflict)
     */
    protected Option<Rectangle> conflicts(final Rectangle recommendedRect, List<IFigure> figuresToCheck, final Collection<IFigure> portsFiguresToIgnore) {
        final ListIterator<IFigure> iterator = figuresToCheck.listIterator();
        while (iterator.hasNext()) {
            final IFigure borderItem = iterator.next();
            if (!portsFiguresToIgnore.contains(borderItem)) {
                if (borderItem.isVisible()) {
                    final Rectangle rect = new Rectangle(borderItem.getBounds());
                    if (!(portsFiguresToIgnore.contains(borderItem)) && rect.intersects(recommendedRect)) {
                        return Options.newSome(rect);
                    }
                }
            }
        }
        return Options.newNone();
    }
}
