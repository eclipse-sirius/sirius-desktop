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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.AbsoluteBoundsFilter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceElementAccessor;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.ISequenceEvent;
import org.eclipse.sirius.diagram.sequence.business.internal.elements.SequenceDiagram;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ISequenceEventEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.LifelineEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.SequenceMessageEditPart;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.unit.diagram.sequence.InteractionsConstants;
import org.eclipse.swtbot.eclipse.gef.finder.matchers.IsInstanceOf;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefConnectionEditPart;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Test class for external modification and improved layout.
 * 
 * @author mporhel
 */
public class ExternalModificationsTests extends AbstractSequenceDiagramTestCase {

    private static final String PATH = DATA_UNIT_DIR + "external_changes/vp-1424/";

    private static final String MODEL = "vp-1424.interactions";

    private static final String SESSION_FILE = "vp-1424.aird";

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
        return null;
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
        return Options.newNone();
    }

    /**
     * A sub execution is moved on its lifeline
     * 
     * Note the serialization execution="//@ownedInteractions.0/@executions.0"/,
     * reference points to the xth element of a list. If we reorder them in
     * interaction without having the aird opened, the layout will not be able
     * get the best result.
     */
    public void testExecutionReparent() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        openDiagram("reparent");

        assertEquals(SessionStatus.DIRTY, session.getStatus());

        String lifeline = "newParticipant2 : ";
        SWTBotGefEditPart IRBot = editor.getEditPart(lifeline).parent();

        // Refreshed
        List<SWTBotGefEditPart> descendants = IRBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        assertEquals(2, descendants.size());
        sortEvents(descendants);

        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(0).part(), new Rectangle(0, 143, 0, 12), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(1).part(), new Rectangle(0, 160, 0, 237), false);

        assertNoAbsoluteBoundsSpecificFlags();
    }

    /**
     * The second execution is included in the first.
     */
    public void testExecutionReparent2() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        openDiagram("reparent2");

        assertEquals(SessionStatus.DIRTY, session.getStatus());

        String lifeline = "newParticipant2 : ";
        SWTBotGefEditPart IRBot = editor.getEditPart(lifeline).parent();

        // Refreshed
        List<SWTBotGefEditPart> descendants = IRBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        assertEquals(2, descendants.size());
        sortEvents(descendants);

        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(0).part(), new Rectangle(0, 118, 0, 200), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(1).part(), new Rectangle(0, 292, 0, 21), false);

        assertNoAbsoluteBoundsSpecificFlags();
    }

    public void _testExecutionAdds() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        openDiagram("executions");

        assertEquals(SessionStatus.DIRTY, session.getStatus());

        String lifeline = "tt : ";
        SWTBotGefEditPart IRBot = editor.getEditPart(lifeline).parent();

        // Refreshed
        List<SWTBotGefEditPart> descendants = IRBot.descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        assertEquals(6, descendants.size());
        sortEvents(descendants);

        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(0).part(), new Rectangle(0, 110, 0, 50), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(1).part(), new Rectangle(0, 165, 0, 50), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(2).part(), new Rectangle(0, 405, 0, 270), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(3).part(), new Rectangle(0, 440, 0, 70), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(4).part(), new Rectangle(0, 515, 0, 50), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) descendants.get(5).part(), new Rectangle(0, 635, 0, 30), false);

        assertNoAbsoluteBoundsSpecificFlags();
    }

    private void assertNoAbsoluteBoundsSpecificFlags() {
        Diagram diag = (Diagram) editor.mainEditPart().part().getModel();
        Option<SequenceDiagram> sequenceDiagram = ISequenceElementAccessor.getSequenceDiagram(diag);
        assertTrue(sequenceDiagram.some());
        Collection<AbsoluteBoundsFilter> flags = Lists.newArrayList(Iterators.filter(sequenceDiagram.get().getSequenceDDiagram().eAllContents(), AbsoluteBoundsFilter.class));
        assertFalse(flags.isEmpty());
        for (AbsoluteBoundsFilter flag : flags) {
            Integer specificFlagValue1 = Integer.valueOf(-1);
            Integer specificFlagValue2 = Integer.valueOf(-2);
            assertFalse(specificFlagValue1.equals(flag.getX()) || specificFlagValue2.equals(flag.getX()));
            assertFalse(specificFlagValue1.equals(flag.getWidth()) || specificFlagValue2.equals(flag.getWidth()));
        }
    }

    public void testMessageReconnect() {
        Session session = localSession.getOpenedSession();
        assertEquals(SessionStatus.SYNC, session.getStatus());

        openDiagram("messages");

        assertEquals(SessionStatus.DIRTY, session.getStatus());

        // Refreshed
        List<SWTBotGefEditPart> lifelines = editor.mainEditPart().descendants(IsInstanceOf.instanceOf(LifelineEditPart.class));
        assertEquals(2, lifelines.size());

        List<SWTBotGefEditPart> executions = editor.mainEditPart().descendants(IsInstanceOf.instanceOf(ExecutionEditPart.class));
        assertEquals(2, executions.size());
        sortEvents(executions);

        List<SWTBotGefConnectionEditPart> messages = new ArrayList<>();
        for (SWTBotGefEditPart seqNodes : Iterables.concat(lifelines, executions)) {
            messages.addAll(seqNodes.sourceConnections());
        }
        assertEquals(2, messages.size());
        sortEvents(messages);

        assertMessageVerticalPosition((SequenceMessageEditPart) messages.get(0).part(), 150);
        assertMessageVerticalPosition((SequenceMessageEditPart) messages.get(1).part(), 210);

        assertExecutionHasValidLogicalBounds((ExecutionEditPart) executions.get(0).part(), new Rectangle(0, 130, 0, 40), false);
        assertExecutionHasValidLogicalBounds((ExecutionEditPart) executions.get(1).part(), new Rectangle(0, 215, 0, 40), false);

        assertNoAbsoluteBoundsSpecificFlags();
    }

    private void sortEvents(List<? extends SWTBotGefEditPart> descendants) {
        Collections.sort(descendants, Ordering.natural().onResultOf(new Function<SWTBotGefEditPart, Integer>() {
            /**
             * {@inheritDoc}
             */
            public Integer apply(SWTBotGefEditPart from) {
                if (from.part() instanceof ISequenceEventEditPart) {
                    ISequenceEvent ise = ((ISequenceEventEditPart) from.part()).getISequenceEvent();
                    return ise.getVerticalRange().getLowerBound();
                }
                return -1;
            }
        }));
    }

    private void openDiagram(String diagramToCheck) {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), getRepresentationId(), diagramToCheck, DDiagram.class);
        initEditor();

        maximizeEditor(editor);
    }

}
