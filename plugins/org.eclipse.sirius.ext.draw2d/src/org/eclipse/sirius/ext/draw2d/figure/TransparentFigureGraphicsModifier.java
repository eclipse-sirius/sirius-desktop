/*******************************************************************************
 * Copyright (c) 2010, 2018 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ext.draw2d.figure;

import org.eclipse.draw2d.Graphics;

/**
 * Specific handler to configure {@link Graphics} regarding the current
 * {@link ITransparentFigure}.
 * 
 * @author mporhel
 */
public class TransparentFigureGraphicsModifier {

    private ITransparentFigure figure;

    private Graphics graphics;

    /**
     * Constructor.
     * 
     * @param figure
     *            the current figure.
     * @param graphics
     *            the current graphics
     */
    public TransparentFigureGraphicsModifier(ITransparentFigure figure, Graphics graphics) {
        this.figure = figure;
        this.graphics = graphics;
    }

    /**
     * Pushes the current state of the current graphics onto a stack. Enable
     * transparency if figure's transparent mode is enabled.
     */
    public void pushState() {
        if (graphics != null && figure != null && figure.isTransparent()) {
            graphics.pushState();
            graphics.setAlpha(figure.getSiriusAlpha());
        }
    }

    /**
     * Pops the previous state of the graphics off the stack.
     */
    public void popState() {
        if (graphics != null && figure != null && figure.isTransparent()) {
            graphics.popState();
        }
    }
}
