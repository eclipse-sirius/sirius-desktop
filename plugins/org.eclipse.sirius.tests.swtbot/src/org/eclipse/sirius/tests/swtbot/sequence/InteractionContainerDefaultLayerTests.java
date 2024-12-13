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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InteractionContainer;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Check Interaction Container size depending on sequence diagram element. This test uses a custom sequence VSM
 * interaction. The Interaction Container mapping is in the default layer, so it is always displayed.
 * 
 * @author smonnier
 */
public class InteractionContainerDefaultLayerTests extends AbstractSequenceDiagramTestCase {

    protected static final String VIEWPOINT_NAME = "Interactions_local_vsm";

    private static final String PATH = DATA_UNIT_DIR + "interactionContainer/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram2 on Lifelines";

    private static final String REPRESENTATION_DESCRIPTION_NAME = "Sequence Diagram2 on Interaction";

    private static final String MODEL = "lifelines.interactions";

    private static final String SESSION_FILE = "representations.aird";

    private static final String TYPES_FILE = "types.ecore";

    private static final String VSM_FILE = "interaction.odesign";

    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartCBot;

    private Rectangle instanceRoleEditPartABounds;

    private Rectangle instanceRoleEditPartBBounds;

    private Rectangle instanceRoleEditPartCBounds;

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

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        super.onSetUpBeforeClosingWelcomePage();
        if (getVsmFile() != null) {
            copyFileToTestProject(Activator.PLUGIN_ID, getPath(), getVsmFile());
        }
    }

    /**
     * Check that on lifeline move and change position, the interaction container bounds also move accordingly.
     */
    public void testInteractionResizeOnInstanceRolePositionChange() {
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
     * @return the vsmFile
     */
    public static String getVsmFile() {
        return VSM_FILE;
    }

    /**
     * @return the representationDescriptionName
     */
    public static String getRepresentationDescriptionName() {
        return REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String getRepresentationId() {
        return REPRESENTATION_DESCRIPTION_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Option<String> getDRepresentationName() {
        return Options.newSome(REPRESENTATION_NAME);
    }
}
