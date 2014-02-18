/**
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.widget;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.RunnableWithResult.Impl;
import org.eclipse.swt.graphics.Color;

/**
 * A {@link RunnableWithResult} to get a {@link IFigure#getBackgroundColor()}
 * from a {@link IFigure}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class BackgroundColorFigureGetter extends Impl<Color> implements RunnableWithResult<Color> {

    private final IFigure figure;

    /**
     * Default constructor.
     * 
     * @param figure
     *            the {@link IFigure} for which get the
     *            {@link IFigure#getBackgroundColor()}
     */
    public BackgroundColorFigureGetter(IFigure figure) {
        this.figure = figure;
    }

    /**
     * Overridden to get the {@link IFigure#getBackgroundColor()} corresponding
     * to the specified {@link IFigure}.
     * 
     * {@inheritDoc}
     */
    @Override
    public void run() {
        setResult(figure.getBackgroundColor());
    }
}
