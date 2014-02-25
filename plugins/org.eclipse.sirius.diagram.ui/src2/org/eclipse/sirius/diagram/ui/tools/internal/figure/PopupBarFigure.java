/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.figure;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.RoundedRectangle;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;

//TODO comment
/**
 * A figure ...
 * 
 * @author mchauvin
 */
public class PopupBarFigure extends RoundedRectangle {

    private static final int SIZE = 30;

    private static final int WIDTH_OFFSET = 5;

    private List<IFigure> elements = new ArrayList<IFigure>();

    private IFigure parent;

    /**
     * Construct a new instance.
     * 
     * @param parent
     *            the parent figure
     */
    public PopupBarFigure(final IFigure parent) {
        super();
        this.parent = parent;
        setCornerDimensions(new Dimension(5, 5));
        setLineWidth(1);
        setLocation(parent.getBounds().getCopy().getTopLeft());
        setSize(SIZE, SIZE);
        this.parent.add(this);
    }

    /**
     * Add to menu the given entry.
     * 
     * @param entry
     *            the entry to add
     */
    public void addToMenu(final IFigure entry) {
        elements.add(entry);

        setSize((SIZE + WIDTH_OFFSET) * elements.size(), SIZE);
        for (final IFigure elem : elements) {
            elem.setSize(new Dimension(SIZE, SIZE));
            if (elements.indexOf(elem) > 0) {
                elem.setLocation(new Point(getBounds().getTopLeft().x + elem.getSize().width + WIDTH_OFFSET, getBounds().getTopLeft().y));
            } else {
                elem.setLocation(new Point(getBounds().getTopLeft().x, getBounds().getTopLeft().y));
            }
            this.add(elem);
        }

    }

}
