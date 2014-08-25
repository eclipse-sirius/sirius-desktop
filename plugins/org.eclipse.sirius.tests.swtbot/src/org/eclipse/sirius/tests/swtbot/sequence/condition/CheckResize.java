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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Checks if the bounds have been resized.
 * 
 * @author smonnier
 */
public class CheckResize extends DefaultCondition {

    private Rectangle initialBounds;

    private String labelOfEditPart;

    private Point expectedTranslation;

    private final SWTBotSiriusDiagramEditor editor;

    private int indexOfExecution = Integer.MIN_VALUE;

    private Dimension expectedResize;

    /**
     * Constructor for resize from top : resize delta == - translation delta
     * 
     * @param bot
     *            Bot.
     */
    public CheckResize(String labelOfEditPart, Point expectedTranslation, SWTBotSiriusDiagramEditor editor) {
        this.labelOfEditPart = labelOfEditPart;
        this.expectedTranslation = expectedTranslation;
        this.editor = editor;
        this.initialBounds = getBounds(labelOfEditPart).getCopy();
    }

    /**
     * Constructor for resize from top : resize delta == - translation delta
     * 
     * @param bot
     *            Bot.
     */
    public CheckResize(String labelOfLifelineEditPart, int indexOfExecution, Point expectedTranslation, SWTBotSiriusDiagramEditor editor) {
        this.labelOfEditPart = labelOfLifelineEditPart;
        this.expectedTranslation = expectedTranslation;
        this.editor = editor;
        this.indexOfExecution = indexOfExecution;
        this.initialBounds = getBounds(labelOfLifelineEditPart).getCopy();
    }

    /**
     * Constructor.
     * 
     * @param bot
     *            Bot.
     */
    public CheckResize(String labelOfLifelineEditPart, int indexOfExecution, Dimension expectedResize, SWTBotSiriusDiagramEditor editor) {
        this.labelOfEditPart = labelOfLifelineEditPart;
        this.expectedResize = expectedResize;
        this.editor = editor;
        this.indexOfExecution = indexOfExecution;
        this.initialBounds = getBounds(labelOfLifelineEditPart).getCopy();
    }

    /**
     * {@inheritDoc}
     */
    public boolean test() throws Exception {
        Dimension expectedEffect = new Dimension();
        if (expectedTranslation != null) {
            expectedEffect = new Dimension(-expectedTranslation.x, -expectedTranslation.y);
        } else if (expectedResize != null) {
            expectedEffect = expectedResize.getCopy();
        }

        return getBounds(labelOfEditPart).width == initialBounds.width + expectedEffect.width && getBounds(labelOfEditPart).height == initialBounds.height + expectedEffect.height;
    }

    private Rectangle getBounds(final String editPartName) {
        final SWTBotGefEditPart editPartBot = editor.getEditPart(editPartName);
        assertNotNull("No part named '" + editPartName + "' found.", editPartBot);
        final EditPart rawPart = editPartBot.part().getParent();
        assertTrue("Part named '" + editPartName + "' is not an IGraphicalEditPart.", rawPart instanceof IGraphicalEditPart);
        IGraphicalEditPart part = (IGraphicalEditPart) rawPart;
        if (part instanceof InstanceRoleEditPart && indexOfExecution >= 0) {
            part = getExecutionEditPart(editPartName, indexOfExecution);
        }
        return part.getFigure().getBounds().getCopy();
    }

    /**
     * Get the execution edit part.
     * @param lifelineLabel
     *            name of the lifeline
     * @param index
     *            position of the execution on the lifeline
     * @return the execution edit part.
     */
    public ExecutionEditPart getExecutionEditPart(String lifelineLabel, int index) {
        SWTBotGefEditPart editPart = editor.getEditPart(lifelineLabel);
        assertTrue("There is no graphical edit part named " + lifelineLabel, editPart.part() instanceof GraphicalEditPart);

        GraphicalEditPart part = (GraphicalEditPart) editPart.part().getParent();
        List<ExecutionEditPart> allExecutions = EditPartsHelper.getAllExecutions((IGraphicalEditPart) part);

        return allExecutions.get(index);
    }

    /**
     * {@inheritDoc}
     */
    public String getFailureMessage() {
        return null;
    }
}
