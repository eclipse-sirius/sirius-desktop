/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence.condition;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if the lifeline bounds have been resized.
 * 
 * @author smonnier
 */
public class CheckLifelineResize extends DefaultCondition {

    private Rectangle initialBounds;

    private LifelineEditPart lifelineEditPart;

    private Point expectedTranslation;

    /**
     * Constructor.
     * 
     * @param lifelineEditPart
     *            the edit part that we are waiting for its resize.
     * @param expectedTranslation
     *            the expected translation
     */
    public CheckLifelineResize(LifelineEditPart lifelineEditPart, Point expectedTranslation) {
        this.initialBounds = lifelineEditPart.getFigure().getBounds().getCopy();
        this.lifelineEditPart = lifelineEditPart;
        this.expectedTranslation = expectedTranslation;
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        return lifelineEditPart.getFigure().getBounds().width == initialBounds.width + expectedTranslation.x
                && lifelineEditPart.getFigure().getBounds().height == initialBounds.height + expectedTranslation.y;
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return null;
    }
}
