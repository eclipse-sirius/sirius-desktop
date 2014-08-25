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

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if the message bounds have been resized.
 * 
 * @author hmarchadour
 */
public class CheckSequenceMessageEditPartResized extends DefaultCondition {

    private final SequenceMessageEditPart sequenceMessageEditPart;

    private final Dimension initialSize;

    /**
     * Default Constructor
     * 
     * @param editPartBot
     *            bot to check if resized
     */
    public CheckSequenceMessageEditPartResized(SWTBotGefConnectionEditPart editPartBot) {
        this((SequenceMessageEditPart) editPartBot.part());
    }

    /**
     * Default Constructor
     * 
     * @param sequenceMessageEditPart
     *            SequenceMessageEditPart to check if resized
     */
    public CheckSequenceMessageEditPartResized(SequenceMessageEditPart sequenceMessageEditPart) {
        this.sequenceMessageEditPart = sequenceMessageEditPart;
        this.initialSize = sequenceMessageEditPart.getFigure().getBounds().getSize().getCopy();
    }

    @Override
    public boolean test() throws Exception {
    	return !initialSize.equals(sequenceMessageEditPart.getFigure().getBounds().getSize());
    }

    @Override
    public String getFailureMessage() {
        return "Failed to find " + sequenceMessageEditPart.resolveSemanticElement() + " resized";
    }

}
