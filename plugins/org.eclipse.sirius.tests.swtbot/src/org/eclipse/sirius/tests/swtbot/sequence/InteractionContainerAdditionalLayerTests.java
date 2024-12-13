/*******************************************************************************
 * Copyright (c) CEA.
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
package org.eclipse.sirius.tests.swtbot.sequence;

import java.util.Optional;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionContainer;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.EndOfLifeEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Check Interaction Container size depending on sequence diagram element. This test uses the default sequence VSM
 * interaction. The Interaction Container mapping is in an additional layer, not active by default.
 * 
 * @author smonnier
 */
public class InteractionContainerAdditionalLayerTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "reorder/instanceRole/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Lifelines";

    private static final String MODEL = "lifelines.interactions";

    private static final String SESSION_FILE = "lifelines.aird";

    private static final String TYPES_FILE = "types.ecore";

    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartCBot;

    private Rectangle instanceRoleEditPartABounds;

    private Rectangle instanceRoleEditPartBBounds;

    private Rectangle instanceRoleEditPartCBounds;

    private String INTERACTION_CONTAINER_LAYER = "Interaction Container";

    private UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        diagram = localSession.getLocalSessionBrowser().perCategory().selectViewpoint(VIEWPOINT_NAME).selectRepresentation(getRepresentationId())
                .selectRepresentationInstance(getDRepresentationName().get(), UIDiagramRepresentation.class);

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        initBotsAndBounds(editor);
    }

    /**
     * Check that on lifeline move and change position, the interaction container bounds also move accordingly.
     */
    public void testInteractionResizeOnInstanceRolePositionChange() {
        // Activate the extension layer.
        ICondition done = new OperationDoneCondition();
        diagram.changeLayerActivation(INTERACTION_CONTAINER_LAYER);
        bot.waitUntil(done);

        // Check that the Interaction Container east bound correspond to the Instance Role C east bound + margin
        SWTBotGefEditPart interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);

        /*
         * Move lifelines to graphically change their order.
         */
        // drag LIFELINE_C to (250,0) delta
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.x + 250, origin.y);
        // Drag LIFELINE_A to (300,0) delta, Lifeline A forth between Lifeline B
        // and Lifeline C
        editor.drag(instanceRoleEditPartABot, instanceRoleEditPartABounds.x + 300, origin.y);

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);
        assertEquals("Interaction Container west bound is not where expected", instanceRoleEditPartBBounds.getLeft().x - InteractionContainer.MARGIN, interactionContainerBounds.getLeft().x, 1);
    }

    /**
     * Check that when the last lifeline move back and forth, the interaction container bounds also move accordingly.
     */
    public void testInteractionResizeOnInstanceRoleMoveBackAndForth() {
        // Activate the extension layer.
        ICondition done = new OperationDoneCondition();
        diagram.changeLayerActivation(INTERACTION_CONTAINER_LAYER);
        bot.waitUntil(done);

        // Check that the Interaction Container east bound correspond to the Instance Role C east bound + margin
        SWTBotGefEditPart interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);

        /*
         * Move lifelines further on the right
         */
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.x + 250, origin.y);

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);

        /*
         * Move lifelines back on the left
         */
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.x - 100, origin.y);

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + InteractionContainer.MARGIN, interactionContainerBounds.getRight().x, 1);
    }

    /**
     * Check that when an execution moves down and resize the lifelines, the interaction container bounds also resizes
     * accordingly.
     */
    public void testInteractionResizeOnExecutionMove() {
        // Activate the extension layer.
        ICondition done = new OperationDoneCondition();
        diagram.changeLayerActivation(INTERACTION_CONTAINER_LAYER);
        bot.waitUntil(done);

        // Check that the Interaction Container south bound correspond to an End of Life south bound + margin
        SWTBotGefEditPart interactionContainerEditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainerEditPart);

        LifelineEditPart lifelineAEditPart = getLifelineEditPart(LIFELINE_A);
        SWTBotGefEditPart lifelineABotEditPart = getBotEditPart(lifelineAEditPart, LifelineEditPart.class);
        Rectangle lifelineABounds = editor.getAbsoluteBounds(lifelineABotEditPart);
        Optional<EndOfLifeEditPart> optionalEOLEditPart = lifelineAEditPart.getChildren().stream().filter(EndOfLifeEditPart.class::isInstance).map(EndOfLifeEditPart.class::cast).findFirst();
        assertTrue("Lifeline A should have and end of life at the bottom", optionalEOLEditPart.isPresent());
        SWTBotGefEditPart eolABotEditPart = getBotEditPart(optionalEOLEditPart.get(), EndOfLifeEditPart.class);
        Rectangle eolABounds = editor.getAbsoluteBounds(eolABotEditPart);
        assertEquals("Interaction Container south bound is not where expected", eolABounds.getBottom().y + InteractionContainer.MARGIN, interactionContainerBounds.getBottom().y, 1);

        // Create an execution on lifeline A
        editor.activateTool("Execution");
        editor.click(instanceRoleEditPartABounds.getCenter().x, 150);

        // Validates the position of the execution
        assertNotNull("The execution index 0 on lifeline " + LIFELINE_A + " has not been found", getExecutionScreenPosition(LIFELINE_A, 0));

        Point executionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        Dimension executionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();

        // Move the execution down at the limit of the lifeline so it will expend the lifeline and interaction container
        Point targetedLocation = lifelineABounds.getBottom().getTranslated(0, -10);
        editor.drag(executionScreenPosition, targetedLocation);
        executionScreenPosition = getExecutionScreenPosition(LIFELINE_A, 0).getCopy();
        executionDimension = getExecutionScreenDimension(LIFELINE_A, 0).getCopy();
        assertEquals("Execution has not been moved at the expected location", targetedLocation.y, executionScreenPosition.y, 1);

        // Check that the bottom of the lifeline has been expanded
        lifelineAEditPart = getLifelineEditPart(LIFELINE_A);
        lifelineABotEditPart = getBotEditPart(lifelineAEditPart, LifelineEditPart.class);
        lifelineABounds = editor.getAbsoluteBounds(lifelineABotEditPart);
        optionalEOLEditPart = lifelineAEditPart.getChildren().stream().filter(EndOfLifeEditPart.class::isInstance).map(EndOfLifeEditPart.class::cast).findFirst();
        eolABotEditPart = getBotEditPart(optionalEOLEditPart.get(), EndOfLifeEditPart.class);
        eolABounds = editor.getAbsoluteBounds(eolABotEditPart);
        assertEquals("The lifeline has not been expanded as expected", eolABounds.y,
                executionScreenPosition.y + executionDimension.height + LayoutConstants.INTERACTION_EXECUTION_MIN_HEIGHT_AFTER_LAYOUT, 1);

        // Check that the interaction container has been expanded as well
        interactionContainerEditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        assertEquals("Interaction Container south bound is not where expected", eolABounds.getBottom().y + InteractionContainer.MARGIN, interactionContainerBounds.getBottom().y, 1);

        // Move the Execution up
        targetedLocation = lifelineABounds.getTop().getTranslated(0, 50);
        editor.drag(executionScreenPosition, targetedLocation);

        // Move the End of life up to shorten the lifeline length by 10px
        targetedLocation = eolABounds.getCenter().getTranslated(0, -10);
        editor.drag(eolABounds.getCenter(), targetedLocation);
        lifelineAEditPart = getLifelineEditPart(LIFELINE_A);
        lifelineABotEditPart = getBotEditPart(lifelineAEditPart, LifelineEditPart.class);
        lifelineABounds = editor.getAbsoluteBounds(lifelineABotEditPart);
        optionalEOLEditPart = lifelineAEditPart.getChildren().stream().filter(EndOfLifeEditPart.class::isInstance).map(EndOfLifeEditPart.class::cast).findFirst();
        eolABotEditPart = getBotEditPart(optionalEOLEditPart.get(), EndOfLifeEditPart.class);
        eolABounds = editor.getAbsoluteBounds(eolABotEditPart);
        assertEquals("The lifeline has not been shorten as expected", eolABounds.getCenter().y, targetedLocation.y, 1);

        // Check that the interaction container has been shorten as well
        interactionContainerEditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainerEditPart);
        assertEquals("Interaction Container south bound is not where expected", eolABounds.getBottom().y + InteractionContainer.MARGIN, interactionContainerBounds.getBottom().y, 1);
    }

    private SWTBotGefEditPart getBotEditPart(AbstractGraphicalEditPart parentExec, final Class<? extends EditPart> expectedEditPartType) {
        return editor.getEditPart(parentExec.getFigure().getBounds().getCopy().getCenter(), expectedEditPartType);
    }

    private void initBotsAndBounds(SWTBotSiriusDiagramEditor swtBotEditor) {
        instanceRoleEditPartABot = swtBotEditor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = swtBotEditor.getEditPart(LIFELINE_B);
        instanceRoleEditPartCBot = swtBotEditor.getEditPart(LIFELINE_C);

        instanceRoleEditPartABounds = swtBotEditor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = swtBotEditor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = swtBotEditor.getBounds(instanceRoleEditPartCBot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        instanceRoleEditPartABot = null;
        instanceRoleEditPartBBot = null;
        instanceRoleEditPartCBot = null;

        instanceRoleEditPartABounds = null;
        instanceRoleEditPartBBounds = null;
        instanceRoleEditPartCBounds = null;

        super.tearDown();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getPath() {
        return PATH;
    }

    @Override
    protected String getSemanticModel() {
        return MODEL;
    }

    @Override
    protected String getTypesSemanticModel() {
        return TYPES_FILE;
    }

    @Override
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
}
