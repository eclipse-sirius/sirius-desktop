/*******************************************************************************
 * Copyright (c) 2024 THALES GLOBAL SERVICES.
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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.waits.ICondition;

/**
 * Check Interaction Container size depending on sequence diagram element.
 * 
 * @author smonnier
 */
public class InteractionContainerTests extends AbstractSequenceDiagramTestCase {

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
     * Check that on lifeline move, the interaction container bounds also move accordingly.
     */
    public void testInteractionResizeOnInstanceRoleMove() {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Activate the extension layer.
        ICondition done = new OperationDoneCondition();
        diagram.changeLayerActivation(INTERACTION_CONTAINER_LAYER);
        bot.waitUntil(done);

        // Check that the Interaction Container east bound correspond to the Instance Role C east bound + margin
        SWTBotGefEditPart interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        Rectangle interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + 50, interactionContainerBounds.getRight().x);

        /*
         * Move lifelines to graphically change their order.
         */
        moveLifelines_A_and_C();

        // Check that the Interaction Container east bound still correspond to the Instance Role C east bound + margin
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        interactionContainereditPart = editor.getEditPart("Lifelines", InteractionContainerEditPart.class);
        interactionContainerBounds = editor.getBounds(interactionContainereditPart);
        assertEquals("Interaction Container east bound is not where expected", instanceRoleEditPartCBounds.getRight().x + 50, interactionContainerBounds.getRight().x);
    }

    private void moveLifelines_A_and_C() {
        // drag LIFELINE_C to (250,0) delta
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.x + 250, origin.y);
        // Drag LIFELINE_A to (300,0) delta, Lifeline A forth between Lifeline B
        // and Lifeline C
        editor.drag(instanceRoleEditPartABot, instanceRoleEditPartABounds.x + 300, origin.y);
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
