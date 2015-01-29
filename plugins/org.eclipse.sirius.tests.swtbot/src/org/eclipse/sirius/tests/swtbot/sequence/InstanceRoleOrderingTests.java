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

import java.io.IOException;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.emf.common.util.URI;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.common.tools.internal.resource.ResourceSyncClientNotifier;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.SequenceDDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.InstanceRole;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.util.EditPartsHelper;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.sample.interactions.Interaction;
import org.eclipse.sirius.sample.interactions.Participant;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

import com.google.common.collect.Iterables;

/**
 * Test InstanceRole orderings in different cases. - Reordering instance role in
 * diagram must reorder instance in other diagram - Reordering instance role in
 * diagram must reorder instance role in Semantic model - Reordering instance
 * role in semantic model must reordering instance role in any impacted sequence
 * diagram.
 * 
 * @author jdupont
 */
public class InstanceRoleOrderingTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "reorder/instanceRole/";

    private static final String REPRESENTATION_NAME = "Sequence Diagram on Lifelines";

    private static final String REPRESENTATION_NAME2 = "Sequence Diagram on Lifelines2";

    private static final String REPRESENTATION_NAME3 = "Sequence Diagram on Lifelines3";

    private static final String MODEL = "lifelines.interactions";

    private static final String SESSION_FILE = "lifelines.aird";

    private static final String TYPES_FILE = "types.ecore";

    private SWTBotGefEditPart instanceRoleEditPartABot;

    private SWTBotGefEditPart instanceRoleEditPartBBot;

    private SWTBotGefEditPart instanceRoleEditPartCBot;

    private Rectangle instanceRoleEditPartABounds;

    private Rectangle instanceRoleEditPartBBounds;

    private Rectangle instanceRoleEditPartCBounds;

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

        initBotsAndBounds(editor);
    }

    /**
     * Test InstanceRole orderings. Put instance role A after B. Check that
     * instance role A was after B in representation and in semantic model.
     */
    public void testSimpleInstanceRoleReorderingByDragAndDrop() {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is a-b-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartABot, instanceRoleEditPartBBot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_A, LIFELINE_B, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "a", "b", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "a", "b", "c");

        /*
         * Move liflienes to graphically change their order.
         */
        moveLifelines_A_and_C();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");
    }

    private void moveLifelines_A_and_C() {
        // drag LIFELINE_C to (250,0) delta
        editor.drag(instanceRoleEditPartCBot, instanceRoleEditPartCBounds.x + 250, origin.y);
        // Drag LIFELINE_A to (300,0) delta, Lifeline A forth between Lifeline B
        // and Lifeline C
        editor.drag(instanceRoleEditPartABot, instanceRoleEditPartABounds.x + 300, origin.y);
        // Assert instance role reorder graphically
    }

    /**
     * Test instanceRole orderings when many representations exist. Put instance
     * role A after B. Check that instance role A was after B in many
     * representations and in semantic model.
     */
    public void testInstanceRoleReorderingAtDiagramOpening() {
        // Reorder lifelines in a first diagram
        testSimpleInstanceRoleReorderingByDragAndDrop();
        localSession.save();

        /*
         * Open an existing diagram and check that lifelines are reordered
         * during opening.
         */
        editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_NAME2, DDiagram.class);

        // To be able to check that ordering is done only for the opened
        // diagram.
        assertTrue("Layout should graphically reorder InstanceRoles during opening", localSession.isDirty());
        localSession.save();

        // Re-Initialize bots and bounds for second editor.
        initBotsAndBounds(editor);
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");

        /*
         * Open an other existing diagram and check that lifelines are reordered
         * during opening.
         */
        editor = openDiagram(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_NAME3, DDiagram.class);

        // Check that ordering is done only for the opened diagram.
        assertTrue("Layout should graphically reorder InstanceRoles during opening", localSession.isDirty());
        localSession.save();

        // Re-Initialize bots and bounds for third editor.
        initBotsAndBounds(editor);
        sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");
    }

    /**
     * Open 3 diagrams, reorder instance Roles in one representation and check
     * that the others representations have the reorder. Check too, that the
     * semantic model are reordered.
     */
    public void testInstanceRoleReorderingOnOtherOpenedDiagram() {
        // Open 2 existing diagrams. There are 3 diagrams opened.
        SWTBotSiriusDiagramEditor editor1 = editor;
        SWTBotSiriusDiagramEditor editor2 = openDiagram(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_NAME2, DDiagram.class);
        SWTBotSiriusDiagramEditor editor3 = openDiagram(localSession.getOpenedSession(), getRepresentationId(), REPRESENTATION_NAME3, DDiagram.class);

        assertEquals("", editor3.getReference(), bot.activeEditor().getReference());
        editor = editor3;
        initBotsAndBounds(editor3);
        // Reorder lifelines in a second diagram
        testSimpleInstanceRoleReorderingByDragAndDrop();
        editor3.close();

        assertEquals("", editor2.getReference(), bot.activeEditor().getReference());
        editor = editor2;
        initBotsAndBounds(editor2);
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");

        editor2.close();

        // check the last opened diagram.
        // It should be the first opened editor
        assertEquals("", editor1.getReference(), bot.activeEditor().getReference());
        editor = editor1;
        initBotsAndBounds(editor);
        sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");
    }

    /**
     * Test creation of new Instance Role in existing representation.
     */
    public void testInstanceRoleIndexOnCreation() {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();
        // Create participant between b and c, named d
        createParticipant("d", instanceRoleEditPartBBounds.right() + 10, instanceRoleEditPartBBounds.y);

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is a-b-d-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartABot, instanceRoleEditPartBBot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_A, LIFELINE_B, "d : ", LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "a", "b", "d", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "a", "b", "d", "c");
    }

    private void checkInstanceRolePartsGraphicalOrder(SWTBotGefEditPart... instanceRoles) {
        int globalX = 0;
        for (SWTBotGefEditPart part : instanceRoles) {
            Rectangle bounds = editor.getBounds(part);
            String name = ((IDiagramElementEditPart) part.part().getParent()).resolveDiagramElement().getName();
            assertTrue("The part " + name + " is not at the expected index on the diagram.", bounds.x > globalX);
            globalX = bounds.right();
        }
    }

    private void checkInstanceRoleGraphicalOrdering(SequenceDiagram sequenceDiagram, String... roleNames) {
        List<InstanceRole> sortedInstanceRole = sequenceDiagram.getSortedInstanceRole();

        assertEquals("The number of instance role is not the expected one.", roleNames.length, sortedInstanceRole.size());
        for (int i = 0; i < roleNames.length; i++) {
            assertEquals("Instance role " + i + " should be " + roleNames[i], roleNames[i], sortedInstanceRole.get(i).getName());
        }
    }

    /**
     * Test modification of ordered instance role in semantic model. Check after
     * that the representation have modification.
     * 
     * @throws Exception
     *             thrown in case of failure
     */
    public void testInstanceRoleReorderingFromSemanticChange() throws Exception {
        SequenceDiagram sequenceDiagram = EditPartsHelper.getSequenceDiagram(instanceRoleEditPartABot.part());
        SequenceDDiagram sequenceDDiagram = sequenceDiagram.getSequenceDDiagram();

        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartABot, instanceRoleEditPartBBot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_A, LIFELINE_B, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "a", "b", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "a", "b", "c");

        // Modify order of instance role in semantic model
        final Interaction myInteraction = loadSemanticModel(TEMP_PROJECT_NAME + "/" + MODEL);
        // Automatic and silent reload
        SessionManager.INSTANCE.getSession(myInteraction).setReloadingPolicy(new ReloadingPolicyImpl(new NoUICallback()));
        myInteraction.getParticipants().move(0, 1);
        try {
            // Save semantic model
            myInteraction.eResource().save(null);
        } catch (IOException e) {
            fail("resource can not be saved");
        }
        Job.getJobManager().join(ResourceSyncClientNotifier.FAMILY, new NullProgressMonitor());

        // Check auto reordering.
        // reinit bots and bounds : some edit parts could have switched their
        // targets before layout (no business id/xmi id in interaction files).
        initBotsAndBounds(editor);
        // Check that the Sequence orderings are consistent.
        validateOrdering();

        // Check that the order of instance role is b-a-c every where.
        checkInstanceRolePartsGraphicalOrder(instanceRoleEditPartBBot, instanceRoleEditPartABot, instanceRoleEditPartCBot);
        checkInstanceRoleGraphicalOrdering(sequenceDiagram, LIFELINE_B, LIFELINE_A, LIFELINE_C);
        checkInstanceRoleSemanticOrdering(sequenceDiagram, "b", "a", "c");
        checkParticipantsOrderInInteractionModel(((Interaction) sequenceDDiagram.getTarget()).getParticipants(), "b", "a", "c");
    }

    private void checkInstanceRoleSemanticOrdering(SequenceDiagram sequenceDiagram, String... particpantNames) {
        Iterable<Participant> semanticOrdering = Iterables.filter(sequenceDiagram.getSequenceDDiagram().getInstanceRoleSemanticOrdering().getSemanticInstanceRoles(), Participant.class);

        assertEquals("The number of instance role is not the expected one.", particpantNames.length, Iterables.size(semanticOrdering));
        for (int i = 0; i < particpantNames.length; i++) {
            assertEquals("The participant " + i + " in the semantic ordering should be " + particpantNames[i], particpantNames[i], Iterables.get(semanticOrdering, i).getName());
        }
    }

    private void checkParticipantsOrderInInteractionModel(List<Participant> participants, String... participantNames) {
        assertEquals("The number of instance role is not the expected one.", participantNames.length, participants.size());
        for (int i = 0; i < participantNames.length; i++) {
            assertEquals("The participant " + i + " in the semantic ordering should be " + participantNames[i], participantNames[i], participants.get(i).getName());
        }
    }

    private void initBotsAndBounds(SWTBotSiriusDiagramEditor swtBotEditor) {
        instanceRoleEditPartABot = swtBotEditor.getEditPart(LIFELINE_A);
        instanceRoleEditPartBBot = swtBotEditor.getEditPart(LIFELINE_B);
        instanceRoleEditPartCBot = swtBotEditor.getEditPart(LIFELINE_C);

        instanceRoleEditPartABounds = swtBotEditor.getBounds(instanceRoleEditPartABot);
        instanceRoleEditPartBBounds = swtBotEditor.getBounds(instanceRoleEditPartBBot);
        instanceRoleEditPartCBounds = swtBotEditor.getBounds(instanceRoleEditPartCBot);
    }

    private Interaction loadSemanticModel(final String semanticModelPath) throws Exception {
        Interaction result;
        /* Load the semantic model in the editing domain resource set */
        try {
            result = (Interaction) ModelUtils.load(URI.createPlatformPluginURI(semanticModelPath, true), localSession.getOpenedSession().getTransactionalEditingDomain().getResourceSet());
        } catch (final IOException e) {
            result = (Interaction) ModelUtils.load(URI.createPlatformResourceURI(semanticModelPath, true), localSession.getOpenedSession().getTransactionalEditingDomain().getResourceSet());
        }
        Assert.assertNotNull(INIT_ERROR_MSG, result);
        return result;
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
