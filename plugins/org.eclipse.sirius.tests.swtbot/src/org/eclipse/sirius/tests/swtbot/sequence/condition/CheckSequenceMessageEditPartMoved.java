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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if the message bounds have been moved.
 * 
 * @author smonnier
 */
public class CheckSequenceMessageEditPartMoved extends DefaultCondition {

    private SequenceMessageEditPart sequenceMessageEditPart;

    private Point initialLocation;

    private Point translation;

    /**
     * Default Constructor
     * 
     * @param editPartBot
     *            bot to check if moved
     */
    public CheckSequenceMessageEditPartMoved(SWTBotGefConnectionEditPart editPartBot) {
        this((SequenceMessageEditPart) editPartBot.part());
    }

    /**
     * Default Constructor
     * 
     * @param sequenceMessageEditPart
     *            SequenceMessageEditPart to check if moved
     */
    public CheckSequenceMessageEditPartMoved(SequenceMessageEditPart sequenceMessageEditPart) {
        this.sequenceMessageEditPart = sequenceMessageEditPart;
        this.initialLocation = sequenceMessageEditPart.getFigure().getBounds().getLocation().getCopy();
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        if (this.translation != null) {
            return initialLocation.getTranslated(translation).equals(sequenceMessageEditPart.getFigure().getBounds().getLocation());
        } else {
            return !initialLocation.equals(sequenceMessageEditPart.getFigure().getBounds().getLocation());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return "Failed to find " + sequenceMessageEditPart.resolveSemanticElement() + " moved";
    }

    /**
     * Sets the value of translation to translation.
     * 
     * @param translation
     *            The translation to set.
     */
    public void setTranslation(Point translation) {
        this.translation = translation;
    }

}
