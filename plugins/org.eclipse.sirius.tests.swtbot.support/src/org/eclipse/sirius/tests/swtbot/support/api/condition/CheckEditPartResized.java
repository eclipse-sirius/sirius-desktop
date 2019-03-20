/**
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES
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
        this((GraphicalEditPart) editPartBot.part());
    }

    /**
     * Default Constructor.
     * 
     * @param editPart
     *            {@link GraphicalEditPart} to check if resized
     */
    public CheckEditPartResized(GraphicalEditPart editPart) {
        this.graphicalEditPart = editPart;
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
        return "The graphical element " + graphicalEditPart.resolveSemanticElement() + " has not been resized";
    }

}
