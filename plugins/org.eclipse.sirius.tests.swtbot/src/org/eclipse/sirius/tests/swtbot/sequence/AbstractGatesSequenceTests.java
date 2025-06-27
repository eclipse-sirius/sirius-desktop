/*******************************************************************************
 * Copyright (c) 2025 CEA LIST.
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
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InstanceRoleEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionContainerEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.InteractionUseEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.CombinedFragment;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.InteractionUse;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

/**
 * Abstract test class for gates.
 * 
 * @author smonnier
 */
public abstract class AbstractGatesSequenceTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "gates/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Interaction Use tests";

    private static final String MODEL = "gates.interactions";

    private static final String SESSION_FILE = "gates.aird";

    private static final String TYPES_FILE = "types.ecore";

    /** Main part */
    protected SWTBotGefEditPart sequenceDiagramBot;

    /** Main interaction */
    protected Interaction interaction;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartABot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartBBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartCBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartDBot;

    /** InstanceRoles */
    protected SWTBotGefEditPart instanceRoleEditPartEBot;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartA;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartB;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartC;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartD;

    /** InstanceRoles */
    protected InstanceRoleEditPart instanceRoleEditPartE;

    /** Participants */
    protected Participant participantA;

    /** Participants */
    protected Participant participantB;

    /** Participants */
    protected Participant participantC;

    /** Participants */
    protected Participant participantD;

    /** Participants */
    protected Participant participantE;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartABounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartBBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartCBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartDBounds;

    /** InstanceRoles bounds */
    protected Rectangle instanceRoleEditPartEBounds;

    /** IUs */
    protected SWTBotGefEditPart firstInteractionUseBot;

    /** IUs parts */
    protected InteractionUseEditPart firstInteractionEditPart;

    /** IUs */
    protected InteractionUse firstInteractionUse;

    /** IUs bounds */
    protected Rectangle firstInteractionUseBounds;

    /** CFs */
    protected SWTBotGefEditPart firstCombinedFragmentBot;

    /** CFs parts */
    protected CombinedFragmentEditPart firstCombinedFragmentEditPart;

    /** CFs */
    protected CombinedFragment firstCombinedFragment;

    /** CFs bounds */
    protected Rectangle firstCombinedFragmentBounds;

    /** ICs */
    protected SWTBotGefEditPart firstInteractionContainerBot;

    /** ICs parts */
    protected InteractionContainerEditPart firstInteractionContainerEditPart;

    /** ICs */
    protected Interaction firstInteractionContainer;

    /** ICs bounds */
    protected Rectangle firstInteractionContainerBounds;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        editor.reveal(LIFELINE_A);

        // Arrange All
        arrangeAll();
        maximizeEditor(editor);

        // InstanceRoles
        instanceRoleEditPartABot = editor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = editor.getEditPart(LIFELINE_B);
        instanceRoleEditPartCBot = editor.getEditPart(LIFELINE_C);
        instanceRoleEditPartDBot = editor.getEditPart(LIFELINE_D);
        instanceRoleEditPartEBot = editor.getEditPart(LIFELINE_E);

        instanceRoleEditPartA = (InstanceRoleEditPart) instanceRoleEditPartABot.parent().part();
        instanceRoleEditPartB = (InstanceRoleEditPart) instanceRoleEditPartBBot.parent().part();
        instanceRoleEditPartC = (InstanceRoleEditPart) instanceRoleEditPartCBot.parent().part();
        instanceRoleEditPartD = (InstanceRoleEditPart) instanceRoleEditPartDBot.parent().part();
        instanceRoleEditPartE = (InstanceRoleEditPart) instanceRoleEditPartEBot.parent().part();

        participantA = (Participant) instanceRoleEditPartA.resolveTargetSemanticElement();
        participantB = (Participant) instanceRoleEditPartB.resolveTargetSemanticElement();
        participantC = (Participant) instanceRoleEditPartC.resolveTargetSemanticElement();
        participantD = (Participant) instanceRoleEditPartD.resolveTargetSemanticElement();
        participantE = (Participant) instanceRoleEditPartE.resolveTargetSemanticElement();

        instanceRoleEditPartABounds = editor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = editor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = editor.getBounds(instanceRoleEditPartCBot);
        instanceRoleEditPartDBounds = editor.getBounds(instanceRoleEditPartDBot);
        instanceRoleEditPartEBounds = editor.getBounds(instanceRoleEditPartEBot);

        sequenceDiagramBot = instanceRoleEditPartABot.parent().parent();
        interaction = (Interaction) participantA.eContainer();

        // IUs
        firstInteractionUseBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionUseEditPart.class)).get(0);
        firstInteractionEditPart = (InteractionUseEditPart) firstInteractionUseBot.part();
        firstInteractionUse = (InteractionUse) firstInteractionEditPart.resolveTargetSemanticElement();
        firstInteractionUseBounds = editor.getBounds(firstInteractionUseBot);

        // CFs
        firstCombinedFragmentBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(CombinedFragmentEditPart.class)).get(0);
        firstCombinedFragmentEditPart = (CombinedFragmentEditPart) firstCombinedFragmentBot.part();
        firstCombinedFragment = (CombinedFragment) firstCombinedFragmentEditPart.resolveTargetSemanticElement();
        firstCombinedFragmentBounds = editor.getBounds(firstCombinedFragmentBot);

        // ICs
        firstInteractionContainerBot = sequenceDiagramBot.descendants(IsInstanceOf.instanceOf(InteractionContainerEditPart.class)).get(0);
        firstInteractionContainerEditPart = (InteractionContainerEditPart) firstInteractionContainerBot.part();
        firstInteractionContainer = (Interaction) firstInteractionContainerEditPart.resolveTargetSemanticElement();
        firstInteractionContainerBounds = editor.getBounds(firstInteractionContainerBot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {

        // Main part
        sequenceDiagramBot = null;

        interaction = null;

        // InstanceRoles
        instanceRoleEditPartABot = null;
        instanceRoleEditPartBBot = null;
        instanceRoleEditPartCBot = null;
        instanceRoleEditPartDBot = null;
        instanceRoleEditPartEBot = null;

        instanceRoleEditPartA = null;
        instanceRoleEditPartB = null;
        instanceRoleEditPartC = null;
        instanceRoleEditPartD = null;
        instanceRoleEditPartE = null;

        participantA = null;
        participantB = null;
        participantC = null;
        participantD = null;
        participantE = null;

        instanceRoleEditPartABounds = null;
        instanceRoleEditPartBBounds = null;
        instanceRoleEditPartCBounds = null;
        instanceRoleEditPartDBounds = null;
        instanceRoleEditPartEBounds = null;

        // IUs
        firstInteractionUseBot = null;
        firstInteractionEditPart = null;
        firstInteractionUse = null;
        firstInteractionUseBounds = null;

        // CFs
        firstCombinedFragmentBot = null;
        firstCombinedFragmentEditPart = null;
        firstCombinedFragment = null;
        firstCombinedFragmentBounds = null;

        super.tearDown();
    }

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

}
