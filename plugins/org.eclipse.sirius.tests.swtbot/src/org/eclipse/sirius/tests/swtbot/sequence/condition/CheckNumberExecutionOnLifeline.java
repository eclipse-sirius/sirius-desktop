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

import static org.junit.Assert.assertTrue;

import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

/**
 * Class to check the number of execution on a lifeline.
 * 
 * @author smonnier
 */
public class CheckNumberExecutionOnLifeline extends DefaultCondition {
    private String lifelineLabel;

    private int numberOfExecutionOnLifeline;

    private SWTBotSiriusDiagramEditor editor;

    /**
     * Constructor.
     * 
     * @param lifelineLabel
     *            the label of the lifeline where the execution is located.
     * 
     * @param numberOfExecutionOnDiagram
     *            the number of the execution on the lifeline.
     */
    public CheckNumberExecutionOnLifeline(String lifelineLabel, int numberOfExecutionOnLifeline, SWTBotSiriusDiagramEditor editor) {
        this.lifelineLabel = lifelineLabel;
        this.numberOfExecutionOnLifeline = numberOfExecutionOnLifeline;
        this.editor = editor;
    }

    public boolean test() throws Exception {
        try {
            SWTBotGefEditPart editPart = editor.getEditPart(lifelineLabel);
            assertTrue("There is no graphical edit part named " + lifelineLabel, editPart.part() instanceof GraphicalEditPart);

            GraphicalEditPart part = (GraphicalEditPart) editPart.part().getParent();
            return EditPartsHelper.getAllExecutions((IGraphicalEditPart) part).size() == numberOfExecutionOnLifeline;
        } catch (WidgetNotFoundException e) {
            return false;
        }
    }

    public String getFailureMessage() {
        // TODO Auto-generated method stub
        return null;
    }

}
