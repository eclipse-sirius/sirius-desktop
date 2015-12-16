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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.aql;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.acceleo.aql.business.internal.AQLSiriusInterpreter;
import org.eclipse.sirius.common.acceleo.aql.ide.proposal.AQLProposalProvider;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposalWithReplacement;
import org.eclipse.sirius.tests.unit.common.interpreter.AbstractCompletionTestCase;

/**
 * Test for the completion provided by the {@link AQLSiriusInterpreter}.
 * 
 * @author <a href="mailto:stephane.begaudeau@obeo.fr">Stephane Begaudeau</a>
 */
public class AcceleoQueryLanguageCompletionTests extends AbstractCompletionTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.setInterpreterAndProposalProvider(new AQLSiriusInterpreter(), new AQLProposalProvider());
    }

    /**
     * Test the empty aql expression proposal.
     */
    public void testEmptyAQLProposal() {
        final ContentProposal emptyExpressionProposal = this.concreteProposalProvider.getNewEmtpyExpression();
        assertEquals("aql:", emptyExpressionProposal.getProposal());
    }

    /**
     * Test the completion without any replacement.
     */
    public void testAQLProposalWithoutReplacement() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("FirstEClass");

        ContentInstanceContext cic = new ContentInstanceContext(c, "aql:self.", 9);
        List<ContentProposal> proposals = this.getProposals(cic);

        for (ContentProposal contentProposal : proposals) {
            if (contentProposal instanceof ContentProposalWithReplacement) {
                ContentProposalWithReplacement proposalWithReplacement = (ContentProposalWithReplacement) contentProposal;
                assertEquals(0, proposalWithReplacement.getReplacementLength());
                assertEquals(9, proposalWithReplacement.getReplacementOffset());
            }
        }
    }

    /**
     * Test the completion with replacement of a part of the expression before
     * the cursor.
     */
    public void testAQLProposalWithPreviousReplacement() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("FirstEClass");

        ContentInstanceContext cic = new ContentInstanceContext(c, "aql:self.na", 11);
        List<ContentProposal> proposals = this.getProposals(cic);

        assertEquals(1, proposals.size());

        if (proposals.get(0) instanceof ContentProposalWithReplacement) {
            ContentProposalWithReplacement proposalWithReplacement = (ContentProposalWithReplacement) proposals.get(0);
            assertEquals("name", proposalWithReplacement.getProposal());
            assertEquals(2, proposalWithReplacement.getReplacementLength());
            assertEquals(9, proposalWithReplacement.getReplacementOffset());
        }
    }

    /**
     * Test the completion with replacement of a part of the expression after
     * the cursor.
     */
    public void testAQLWithAfterReplacement() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("FirstEClass");

        ContentInstanceContext cic = new ContentInstanceContext(c, "aql:self.nana", "aql:self.na".length());
        List<ContentProposal> proposals = this.getProposals(cic);

        assertEquals(1, proposals.size());

        if (proposals.get(0) instanceof ContentProposalWithReplacement) {
            // The proposal should be to insert "name" right after the dot and replacing the first "na".
            ContentProposalWithReplacement proposalWithReplacement = (ContentProposalWithReplacement) proposals.get(0);
            assertEquals("name", proposalWithReplacement.getProposal());
            assertEquals("name".length(), proposalWithReplacement.getReplacementLength());
            assertEquals("aql:self.".length(), proposalWithReplacement.getReplacementOffset());
        }
    }

    /**
     * Test the completion with replacement of a part of the expression before
     * and after the cursor.
     */
    public void testAQLWithBeforeAndAfterReplacement() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("FirstEClass");

        ContentInstanceContext cic = new ContentInstanceContext(c, "aql:self.nam", 11);
        List<ContentProposal> proposals = this.getProposals(cic);

        assertEquals(1, proposals.size());

        if (proposals.get(0) instanceof ContentProposalWithReplacement) {
            ContentProposalWithReplacement proposalWithReplacement = (ContentProposalWithReplacement) proposals.get(0);
            assertEquals("name", proposalWithReplacement.getProposal());
            assertEquals(3, proposalWithReplacement.getReplacementLength());
            assertEquals(9, proposalWithReplacement.getReplacementOffset());
        }
    }
}
