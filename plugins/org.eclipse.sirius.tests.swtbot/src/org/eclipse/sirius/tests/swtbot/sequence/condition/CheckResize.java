/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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

    private final SWTBotSiriusDiagramEditor editor;

    private int indexOfExecution = Integer.MIN_VALUE;

    Dimension expectedEffect;

    /**
     * Constructor for resize from top : resize delta == - translation delta
     * 
     * @param labelOfEditPart
     *            The label of the edit part that must be resized
     * @param expectedTranslation
     *            the resize delta
     * @param editor
     *            The editor containing the edit part that must be resized
     */
    public CheckResize(String labelOfEditPart, Point expectedTranslation, SWTBotSiriusDiagramEditor editor) {
        this.labelOfEditPart = labelOfEditPart;
        expectedEffect = new Dimension(-expectedTranslation.x, -expectedTranslation.y);
        this.editor = editor;
        this.initialBounds = getBounds(labelOfEditPart);
    }

    /**
     * Constructor for resizing an execution from top : resize delta == -
     * translation delta
     * 
     * @param labelOfLifelineEditPart
     *            The label of life line containing the edit part that must be
     *            resized
     * @param indexOfExecution
     *            The index of the execution on the life line
     * @param expectedTranslation
     *            the resize delta the resize delta
     * @param editor
     *            The editor containing the edit part that must be resized
     */
    public CheckResize(String labelOfLifelineEditPart, int indexOfExecution, Point expectedTranslation, SWTBotSiriusDiagramEditor editor) {
        this(labelOfLifelineEditPart, indexOfExecution, new Dimension(-expectedTranslation.x, -expectedTranslation.y), editor);
    }

    /**
     * Constructor for resizing an execution.
     * 
     * @param labelOfLifelineEditPart
     *            The label of life line containing the edit part that must be
     *            resized
     * @param indexOfExecution
     *            The index of the execution on the life line
     * @param expectedResize
     *            the resize delta
     * @param editor
     *            The editor containing the edit part that must be resized
     */
    public CheckResize(String labelOfLifelineEditPart, int indexOfExecution, Dimension expectedResize, SWTBotSiriusDiagramEditor editor) {
        this.labelOfEditPart = labelOfLifelineEditPart;
        expectedEffect = expectedResize;
        this.editor = editor;
        this.indexOfExecution = indexOfExecution;
        this.initialBounds = getBounds(labelOfLifelineEditPart);
    }

    @Override
    public boolean test() throws Exception {
        Rectangle currentBounds = getBounds(labelOfEditPart);
        return currentBounds.width == initialBounds.width + expectedEffect.width && currentBounds.height == initialBounds.height + expectedEffect.height;
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
     * 
     * @param lifelineLabel
     *            name of the life line
     * @param index
     *            position of the execution on the life line
     * @return the execution edit part.
     */
    public ExecutionEditPart getExecutionEditPart(String lifelineLabel, int index) {
        SWTBotGefEditPart editPart = editor.getEditPart(lifelineLabel);
        assertTrue("There is no graphical edit part named " + lifelineLabel, editPart.part() instanceof GraphicalEditPart);

        GraphicalEditPart part = (GraphicalEditPart) editPart.part().getParent();
        List<ExecutionEditPart> allExecutions = EditPartsHelper.getAllExecutions((IGraphicalEditPart) part);

        return allExecutions.get(index);
    }

    @Override
    public String getFailureMessage() {
        Rectangle currentBounds = getBounds(labelOfEditPart);
        return "The edit part \"" + labelOfEditPart + "\" with initial size <" + initialBounds.width + ", " + initialBounds.height + "> has not been resized as expected. expected <"
                + (initialBounds.width + expectedEffect.width) + ", "
                + (initialBounds.height + expectedEffect.height) + "> but was <" + currentBounds.width + ", " + currentBounds.height + ">";
    }
}
