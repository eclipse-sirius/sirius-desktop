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
package org.eclipse.sirius.tests.swtbot.suite;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

import org.eclipse.sirius.tests.swtbot.sequence.ActionDisabledOnExtendedMessagesTest;
import org.eclipse.sirius.tests.swtbot.sequence.ActionDisabledOnSequenceDiagramTest;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragments2Tests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsAndElementsCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsAndMessagesCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsDisabledTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsOperandCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsOperandOverlapTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsSingleClickCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsTwoClickCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.CombinedFragmentsWithInclusionCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.ComplexInOutCombinedFragmentTest;
import org.eclipse.sirius.tests.swtbot.sequence.CreateMessageOnCollapsedExecutionTest;
import org.eclipse.sirius.tests.swtbot.sequence.CreateMessageTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionDeletionWithCFChildrenTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionLinkedMessageReconnectionTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMessageReconnectionTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMove2Tests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMove3Tests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMove4Tests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMove5Tests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMove6Tests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionMoveTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionReconnectionTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionSelectionEditPolicyTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExecutionTests;
import org.eclipse.sirius.tests.swtbot.sequence.ExternalModificationsTests;
import org.eclipse.sirius.tests.swtbot.sequence.FrameMoveWithExpansionTest;
import org.eclipse.sirius.tests.swtbot.sequence.FrameReparentTest;
import org.eclipse.sirius.tests.swtbot.sequence.HeaderSequenceDiagramTests;
import org.eclipse.sirius.tests.swtbot.sequence.InstanceRoleOrderingTests;
import org.eclipse.sirius.tests.swtbot.sequence.InstanceRoleResizableEditPolicyTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseCoverageTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseMoveDownTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseResizeTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseSingleClickCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseTests;
import org.eclipse.sirius.tests.swtbot.sequence.InteractionUseTwoClickCreationTests;
import org.eclipse.sirius.tests.swtbot.sequence.MessageExtensionTest;
import org.eclipse.sirius.tests.swtbot.sequence.NonAutoRefreshSequenceExecutionBasicAndReturnMessageTest;
import org.eclipse.sirius.tests.swtbot.sequence.NoteAttachmentTest;
import org.eclipse.sirius.tests.swtbot.sequence.ObservationPointTests;
import org.eclipse.sirius.tests.swtbot.sequence.PunctualStateTests;
import org.eclipse.sirius.tests.swtbot.sequence.RefreshLayoutNotExecutedOnNoSequenceChangesTests;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceArrangeLinkedBorderedNodesTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceBasicMessageTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceDestroyMessageMoveTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceDestroyMessageTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceDiagramDirtyTests;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceDiagramNoSnapTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceExecutionBasicAndReturnMessageTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceExecutionMessageToSelfReparentTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceExecutionMessageToSelfTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceLifelineTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceMessageToSelfTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceOpeningFilteredEventEndsTests;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceReorderTest;
import org.eclipse.sirius.tests.swtbot.sequence.SequenceReturnMessageTest;
import org.eclipse.sirius.tests.swtbot.sequence.StateBasicTests;
import org.eclipse.sirius.tests.swtbot.sequence.StateHierarchyTests;
import org.eclipse.sirius.tests.swtbot.sequence.StateNoMessageTests;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCall2Test;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCall3Test;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCallInOperandReorderTest;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCallMoveTest;
import org.eclipse.sirius.tests.swtbot.sequence.SyncCallTest;

/**
 * All SWTBot tests on sequence diagram.
 * 
 * @author smonnier
 */
public class SequenceSwtBotTestSuite extends TestCase {

    /**
     * Launches the test with the given arguments.
     * 
     * @param args
     *            Arguments of the testCase.
     */
    public static void main(final String[] args) {
        TestRunner.run(suite());
    }

    /**
     * Add the gerrit part of the Sequence tests to the specified suite.
     * 
     * @param suite
     *            the suite into which to add the tests.
     */
    public static void addGerritPart(TestSuite suite) {
        suite.addTestSuite(InstanceRoleResizableEditPolicyTests.class);
        suite.addTestSuite(SequenceReorderTest.class);
        suite.addTestSuite(SequenceDiagramDirtyTests.class);
        suite.addTestSuite(SequenceOpeningFilteredEventEndsTests.class);
        suite.addTestSuite(SequenceDiagramNoSnapTest.class);
        suite.addTestSuite(FrameMoveWithExpansionTest.class);
        suite.addTestSuite(SequenceLifelineTest.class);
        suite.addTestSuite(SequenceBasicMessageTest.class);
        suite.addTestSuite(MessageExtensionTest.class);
        suite.addTestSuite(SequenceExecutionBasicAndReturnMessageTest.class);
        suite.addTestSuite(SequenceMessageToSelfTest.class);
        suite.addTestSuite(CreateMessageTests.class);
        suite.addTestSuite(ExecutionTests.class);
        suite.addTestSuite(ExecutionMoveTests.class);
        suite.addTestSuite(ExecutionMove2Tests.class);
        suite.addTestSuite(ExecutionMove3Tests.class);
        suite.addTestSuite(ExecutionMove4Tests.class);
        suite.addTestSuite(ExecutionMove5Tests.class);
        suite.addTestSuite(ExecutionDeletionWithCFChildrenTests.class);
        suite.addTestSuite(ExecutionReconnectionTests.class);
        suite.addTestSuite(ExecutionMessageReconnectionTests.class);
        suite.addTestSuite(ExecutionLinkedMessageReconnectionTests.class);
        suite.addTestSuite(ExternalModificationsTests.class);
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the test.
     * 
     * @return The test suite containing all the tests
     */
    public static Test suite() {
        final TestSuite suite = new TestSuite("SwtBot tests on sequence diagram");

        addGerritPart(suite);

        suite.addTestSuite(SequenceReturnMessageTest.class);
        suite.addTestSuite(SyncCallTest.class);
        suite.addTestSuite(SyncCallMoveTest.class);
        suite.addTestSuite(SyncCall2Test.class);
        suite.addTestSuite(SyncCall3Test.class);
        suite.addTestSuite(SyncCallInOperandReorderTest.class);
        suite.addTestSuite(CreateMessageOnCollapsedExecutionTest.class);
        suite.addTestSuite(SequenceExecutionMessageToSelfTest.class);
        suite.addTestSuite(SequenceDestroyMessageTest.class);
        suite.addTestSuite(NoteAttachmentTest.class);
        suite.addTestSuite(SequenceArrangeLinkedBorderedNodesTest.class);
        suite.addTestSuite(InteractionUseSingleClickCreationTests.class);
        suite.addTestSuite(InteractionUseTwoClickCreationTests.class);
        suite.addTestSuite(InteractionUseCoverageTests.class);
        suite.addTestSuite(InteractionUseTests.class);
        suite.addTestSuite(InteractionUseMoveDownTests.class);
        suite.addTestSuite(InteractionUseResizeTests.class);
        suite.addTestSuite(CombinedFragmentsSingleClickCreationTests.class);
        suite.addTestSuite(CombinedFragmentsTwoClickCreationTests.class);
        suite.addTestSuite(CombinedFragmentsWithInclusionCreationTests.class);
        suite.addTestSuite(CombinedFragmentsAndElementsCreationTests.class);
        suite.addTestSuite(CombinedFragmentsAndMessagesCreationTests.class);
        suite.addTestSuite(CombinedFragmentsTests.class);
        suite.addTestSuite(CombinedFragmentsOperandCreationTests.class);
        suite.addTestSuite(CombinedFragmentsOperandOverlapTests.class);
        suite.addTestSuite(StateBasicTests.class);
        suite.addTestSuite(PunctualStateTests.class);
        suite.addTestSuite(RefreshLayoutNotExecutedOnNoSequenceChangesTests.class);
        suite.addTestSuite(StateNoMessageTests.class);
        suite.addTestSuite(ComplexInOutCombinedFragmentTest.class);
        suite.addTestSuite(ObservationPointTests.class);
        suite.addTestSuite(ActionDisabledOnSequenceDiagramTest.class);
        suite.addTestSuite(ActionDisabledOnExtendedMessagesTest.class);
        suite.addTestSuite(FrameReparentTest.class);
        suite.addTestSuite(InstanceRoleOrderingTests.class);

        suite.addTestSuite(HeaderSequenceDiagramTests.class);

        return suite;
    }

    /**
     * Creates the {@link junit.framework.TestSuite TestSuite} for all the
     * disabled test.
     * 
     * @return The test suite containing all the disabled tests.
     */
    public static Test disabledSuite() {
        final TestSuite suite = new TestSuite("Sequence Disabled SwtBot tests");

        suite.addTestSuite(SequenceDestroyMessageMoveTest.class);
        suite.addTestSuite(SequenceExecutionMessageToSelfReparentTest.class);
        suite.addTestSuite(ExecutionSelectionEditPolicyTests.class);
        suite.addTestSuite(ExecutionMove6Tests.class);

        suite.addTestSuite(StateHierarchyTests.class);

        // Non auto refresh undo redo after delete with reparent/reconnect fails
        suite.addTestSuite(NonAutoRefreshSequenceExecutionBasicAndReturnMessageTest.class);

        suite.addTestSuite(CombinedFragmentsDisabledTests.class);
        suite.addTestSuite(CombinedFragments2Tests.class);
        return suite;
    }

}
