/**
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Obeo - Initial API and implementation
 */
package org.eclipse.sirius.tests.swtbot.support.api.condition;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * A Condition to test if a SWTBotGefEditPart's view has resized.
 * 
 * @author edugueperoux
 */
public class CheckEditPartResized extends DefaultCondition {

    private final GraphicalEditPart graphicalEditPart;

    private final Dimension initialSize;

    /**
     * Default Constructor.
     * 
     * @param editPartBot
     *            bot to check if resized
     */
    public CheckEditPartResized(SWTBotGefEditPart editPartBot) {
        this.graphicalEditPart = (GraphicalEditPart) editPartBot.part();
        this.initialSize = graphicalEditPart.getFigure().getBounds().getSize().getCopy();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean test() throws Exception {
        return !initialSize.equals(graphicalEditPart.getFigure().getBounds().getSize());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFailureMessage() {
        return "Failed to find " + graphicalEditPart.resolveSemanticElement() + " resized";
    }

}
