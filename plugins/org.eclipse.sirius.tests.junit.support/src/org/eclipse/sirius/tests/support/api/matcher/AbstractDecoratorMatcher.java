/**
 * Copyright (c) 2010, 2017, 2023 THALES GLOBAL SERVICES
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.support.api.matcher;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gmf.runtime.diagram.ui.services.decorator.IDecoration;
import org.eclipse.gmf.runtime.draw2d.ui.internal.figures.ImageFigureEx;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.swt.graphics.Image;
import org.hamcrest.BaseMatcher;

/**
 * 
 * This class is used to check if a graphical element has a decorator.
 * 
 * @author amartin
 */
public abstract class AbstractDecoratorMatcher extends BaseMatcher<EditPart> {

    /**
     * return the image of the targeted decorator.
     * 
     * @return the image of te decorator
     */
    protected abstract Image getImage();

    @Override
    public boolean matches(final Object item) {
        if (item instanceof EditPart) {
            EditPart part = (EditPart) item;
            EditPartViewer viewer = part.getViewer();
            Map<IFigure, EditPart> mapDecorator = viewer.getVisualPartMap();

            for (final Entry<IFigure, EditPart> entry : mapDecorator.entrySet()) {
                final EditPart currentPart = entry.getValue();
                final IFigure visual = entry.getKey();
                if (currentPart.equals(part) && visual instanceof IDecoration) {
                    return findFigureWithImage(visual, getImage());
                }

            }
        }
        return false;
    }

    @SuppressWarnings("restriction")
    private boolean findFigureWithImage(IFigure figure, Image image) {
        if (figure instanceof ImageFigureEx) {
            if (ImageEquality.areEqualImages(((ImageFigureEx) figure).getImage(), image)) {
                return true;
            }
        }

        boolean imageFigureExFound = false;
        Iterator<? extends IFigure> it = figure.getChildren().iterator();
        while (it.hasNext() && !imageFigureExFound) {
            IFigure innerFigure = it.next();
            imageFigureExFound = findFigureWithImage(innerFigure, image);
        }

        return imageFigureExFound;
    }
}
