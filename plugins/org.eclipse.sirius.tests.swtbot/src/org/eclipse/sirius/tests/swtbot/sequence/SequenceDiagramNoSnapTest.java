/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Collections;

import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCheckBox;

/**
 * Test class for snap options management on Sequence diagrams.
 * 
 * @author mporhel
 */
public class SequenceDiagramNoSnapTest extends AbstractDefaultModelSequenceTests {

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSnapOptionsDisabledOnSequenceDiagram() throws Exception {
        assertFalse("Snap to grid should be deactivated on Sequence editor opening.", editor.isSnapToGrid());
        assertFalse("Snap to shapes should be deactivated on Sequence editor opening.", editor.isSnapToShape());

        editor.setSnapToGrid(true);
        assertFalse("It should not be possible to activate snap to grid on a Sequence diagram.", editor.isSnapToGrid());

        editor.setSnapToShape(true);
        assertFalse("It should not be possible to activate snap to shapes on a Sequence diagram.", editor.isSnapToShape());

        editor.click(5, 5);
        SWTBotUtils.waitAllUiEvents();
        SWTBotView propertyView = bot.viewByTitle("Properties");
        propertyView.setFocus();
        SWTBot propertyViewBot = propertyView.bot();
        SWTBotSiriusHelper.selectPropertyTabItem("Rulers & Grid");

        SWTBotCheckBox snapToGridCheckBox = propertyViewBot.checkBox("Snap To Grid");
        assertFalse(snapToGridCheckBox.isChecked());
        snapToGridCheckBox.click();
        assertFalse("It should not be possible to activate snap to grid on a Sequence diagram.", editor.isSnapToGrid());
        assertFalse(snapToGridCheckBox.isChecked());

        SWTBotCheckBox snapToShapesCheckBox = propertyViewBot.checkBox("Snap To Shapes");
        assertFalse(snapToShapesCheckBox.isChecked());
        snapToShapesCheckBox.click();
        assertFalse("It should not be possible to activate snap to shapes on a Sequence diagram.", editor.isSnapToShape());
        assertFalse(snapToShapesCheckBox.isChecked());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSnapOptionsDisabledOnNewSequenceDiagram() throws Exception {
        editor.close();

        UIResource interactionModel = new UIResource(designerProject, FILE_DIR, getSemanticModel());
        UIDiagramRepresentation sequenceDiag = localSession.newDiagramRepresentation("Sequence Diagram on Lifelines").on(localSession.getSemanticResourceNode(interactionModel).getNode(0))
                .withDefaultName().ok();

        editor = sequenceDiag.getEditor();
        assertFalse("Snap to grid should be deactivated on the opening of a newly created Sequence diagram.", editor.isSnapToGrid());
        assertFalse("Snap to shapes should be deactivated on the opening of a newly created Sequence diagram.", editor.isSnapToShape());

        editor.setSnapToGrid(true);
        assertFalse("It should not be possible to activate snap to grid on a Sequence diagram.", editor.isSnapToGrid());

        editor.setSnapToShape(true);
        assertFalse("It should not be possible to activate snap to shapes on a Sequence diagram.", editor.isSnapToShape());
    }

    /**
     * Test method.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testSnapOptionsStillDisabledOnSequenceDiagramWhenChangedInStandardDiagram() throws Exception {
        ICondition done = new OperationDoneCondition();
        localSession.changeViewpointSelection(Collections.singleton("Design"), Collections.<String> emptySet());
        bot.waitUntil(done);
        SWTBotSiriusDiagramEditor ecoreEditor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), "Entities", "simple package entities", DDiagram.class, false, false);

        boolean snapToGridOnOpening = editor.isSnapToGrid();

        ecoreEditor.setSnapToGrid(!snapToGridOnOpening);
        assertEquals("It should possible to change snap to grid on a standard diagram.", !snapToGridOnOpening, ecoreEditor.isSnapToGrid());
        assertFalse("Change on other diagram should not activate snap to grid on a Sequence diagram.", editor.isSnapToGrid());
        ecoreEditor.setSnapToGrid(snapToGridOnOpening);
        assertEquals("It should not be possible to activate snap to grid on a Sequence diagram.", snapToGridOnOpening, ecoreEditor.isSnapToGrid());
        assertFalse("Change on other diagram should not activate snap to grid on a Sequence diagram.", editor.isSnapToGrid());

        boolean snapToShapeOnOpening = editor.isSnapToShape();
        ecoreEditor.setSnapToShape(!snapToShapeOnOpening);
        assertEquals("It should not be possible to activate snap to shapes on a Sequence diagram.", !snapToShapeOnOpening, ecoreEditor.isSnapToShape());
        assertFalse("It should not be possible to activate snap to shapes on a Sequence diagram.", editor.isSnapToShape());

        ecoreEditor.setSnapToShape(snapToShapeOnOpening);
        assertEquals("It should not be possible to activate snap to grid on a Sequence diagram.", snapToShapeOnOpening, ecoreEditor.isSnapToShape());
        assertFalse("Change on other diagram should not activate snap to grid on a Sequence diagram.", editor.isSnapToShape());
    }
}
