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
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Abstract test class for states.
 * 
 * @author smonnier
 */
public abstract class AbstractStatesSequenceTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "states/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Sample #1";

    private static final String MODEL = "states.interactions";

    private static final String SESSION_FILE = "states.aird";

    private static final String TYPES_FILE = "types.ecore";

    // State
    protected SWTBotGefEditPart stateS1Bot;

    protected SWTBotGefEditPart stateS2Bot;

    protected Rectangle stateS1ScreenBounds;

    protected Rectangle stateS2ScreenBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    protected String getSemanticModel() {
        return MODEL;
    }

    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    protected String getSessionModel() {
        return SESSION_FILE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return InteractionsConstants.SEQUENCE_DIAGRAM_REPRESENTATION_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }

    /**
     * create the first state on lifeline A
     * 
     * @param yScreenStateS1
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS1OnLifelineA(int yScreenStateS1, double zoom) {

        // Reveal A to scroll to the left
        editor.reveal(LIFELINE_A);

        // Calculate the X position of the center of lifelines A and B
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);
        // Creation of an state
        Option<SWTBotGefEditPart> newStateOption = createStateWithResult(lifelineAPosition, yScreenStateS1);
        Assert.assertTrue(newStateOption.some());

        stateS1Bot = newStateOption.get();

        // Validates the position of the state
        stateS1ScreenBounds = assertStateHasValidScreenBounds(stateS1Bot, new Rectangle(0, yScreenStateS1, LayoutConstants.DEFAULT_EXECUTION_WIDTH,
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
        Assert.assertEquals(lifelineAPosition, stateS1ScreenBounds.getCenter().x);

        // Test Creation of a state on a state is not possible
        int yExecA1 = stateS1ScreenBounds.getCenter().y;
        Option<SWTBotGefEditPart> noStateOption = createStateWithResult(lifelineAPosition, yExecA1);
        Assert.assertFalse(noStateOption.some());
    }

    /**
     * create a state on lifeline B
     * 
     * @param yScreenStateS1
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS2OnLifelineB(int yScreenStateS1, double zoom) {
        int lifelineBPosition = getLifelineScreenX(LIFELINE_B);

        // Creation of an state on lifeline B
        Option<SWTBotGefEditPart> newStateOption = createStateWithResult(lifelineBPosition, yScreenStateS1);
        Assert.assertTrue(newStateOption.some());

        stateS2Bot = newStateOption.get();

        stateS2ScreenBounds = assertStateHasValidScreenBounds(stateS2Bot, new Rectangle(0, yScreenStateS1, (int) (LayoutConstants.DEFAULT_EXECUTION_WIDTH * zoom),
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }

    /**
     * create the second state on lifeline A
     * 
     * @param yScreenStateS2
     *            vertical position
     * @param zoom
     *            amount of zoom
     */
    protected void doCreateS2OnLifelineA(int yScreenStateS2, double zoom) {
        int lifelineAPosition = getLifelineScreenX(LIFELINE_A);

        // Creation of an state on lifeline B
        Option<SWTBotGefEditPart> newStateOption = createStateWithResult(lifelineAPosition, yScreenStateS2);
        Assert.assertTrue(newStateOption.some());

        stateS2Bot = newStateOption.get();

        stateS2ScreenBounds = assertStateHasValidScreenBounds(stateS2Bot, new Rectangle(0, yScreenStateS2, (int) (LayoutConstants.DEFAULT_EXECUTION_WIDTH * zoom),
                (int) (LayoutConstants.DEFAULT_EXECUTION_HEIGHT * zoom)), false);
    }
}
