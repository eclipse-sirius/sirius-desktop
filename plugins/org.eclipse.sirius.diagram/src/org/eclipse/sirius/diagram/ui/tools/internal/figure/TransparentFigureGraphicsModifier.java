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

import org.eclipse.draw2d.Graphics;
import org.eclipse.sirius.diagram.ui.tools.api.figure.ITransparentFigure;

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
