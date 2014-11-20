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
package org.eclipse.sirius.tests.unit.common.interpreter.feature;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.tests.unit.common.interpreter.AbstractCompletionTestCase;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * Tests for the {@link FeatureInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class FeatureCompletionTests extends AbstractCompletionTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setInterpreterAndProposalProvider(new FeatureInterpreter(), new FeatureProposalProvider());
    };

    /**
     * Tests proposal based on "feature:" prefix.
     */
    public void testCompletionOnInterpreterPrefix() {
        ContentContext cc = createContentContext("f", 1, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);

        cc = createContentContext("feature", 7, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);
    }

    /**
     * Tests proposal based on "feature:" prefix.
     */
    public void testInstanceCompletionOnInterpreterPrefix() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        ContentInstanceContext cic = new ContentInstanceContext(c, "f", 1);
        assertCompletionMatchesEmptyExpression(cic, compoundProposalInstanceFunction);

        cic = new ContentInstanceContext(c, "feature", 7);
        assertCompletionMatchesEmptyExpression(cic, compoundProposalInstanceFunction);
    }

    /**
     * Tests that completion proposals.
     */
    public void testFeatureProposals() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        ContentContext cc = null;
        assertTrue(getProposals(cc).isEmpty());

        ContentInstanceContext cic = null;
        assertTrue(getProposals(cic).isEmpty());
    }

    /**
     * Tests emtpy expression proposal.
     */
    public void testCompletionEmtpyField() {
        ContentContext cc = createContentContext("", 0, "EClass");
        assertCompletionMatchesEmptyExpression(cc, proposalFunction);
    }

    /**
     * Tests emtpy expression proposal.
     */
    public void testInstanceCompletionEmtpyField() {
        ContentInstanceContext cic = new ContentInstanceContext(null, "", 0);
        assertCompletionMatchesEmptyExpression(cic, proposalInstanceFunction);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testNoCompletion() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        Function<Integer, ContentContext> createEmptyExpressionContextWithCursor = new Function<Integer, ContentContext>() {
            public ContentContext apply(Integer input) {
                return createContentContext("feature:", input, "EClass");
            }
        };
        doTestNoCompletion(createEmptyExpressionContextWithCursor, proposalFunction);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceNoCompletion() {
        final EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        Function<Integer, ContentInstanceContext> createEmptyExpressionContextWithCursor = new Function<Integer, ContentInstanceContext>() {
            public ContentInstanceContext apply(Integer input) {
                return new ContentInstanceContext(c, "feature:", input);
            }
        };
        doTestNoCompletion(createEmptyExpressionContextWithCursor, proposalInstanceFunction);
    }

    /**
     * @param createContextFunction
     *            function to give a context with "feature:" and the wanted
     *            cursor position.
     */
    private <T> void doTestNoCompletion(Function<Integer, T> createContextFunction, Function<T, List<ContentProposal>> getProposalFunction) {
        // No completion in between 'f' and ':' in 'feature:'
        for (int i = 0; i < 8; i++) {
            T ctx = createContextFunction.apply(i);
            List<ContentProposal> contentProposals = getProposalFunction.apply(ctx);
            assertTrue("No completion should be proposed when cursor is between 'f' and ':' in 'feature:' for i=" + i, contentProposals.isEmpty());
        }
    }

    /**
     * Tests completion for an EClass.
     */
    public void testCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentContext cc = createContentContext("feature:", 8, "EClass", EcorePackage.eINSTANCE);

        // implicit context
        List<ContentProposal> contentProposals = getProposals(cc);

        checkCompletionProposal(c.eClass(), contentProposals);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentInstanceContext cic = new ContentInstanceContext(c, "feature:", 8);

        // implicit context
        List<ContentProposal> contentProposals = getProposals(cic);

        checkCompletionProposal(c.eClass(), contentProposals);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentContext cc = createContentContext("feature:", 8, "DNode", DiagramPackage.eINSTANCE);
        List<ContentProposal> contentProposals = getProposals(cc);

        checkCompletionProposal(dNode.eClass(), contentProposals);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentInstanceContext cic = new ContentInstanceContext(dNode, "feature:", 8);

        List<ContentProposal> contentProposals = getProposals(cic);

        checkCompletionProposal(dNode.eClass(), contentProposals);
    }

    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals) {
        Collection<String> proposals = extractProposal(contentProposals);

        StringBuilder errorMsg = new StringBuilder();

        Predicate<String> concerned = new Predicate<String>() {
            public boolean apply(String input) {
                return true;
            }
        };

        checkEStruturalFeatures(eClass, proposals, concerned, errorMsg);

        assertTrue(errorMsg.toString(), 0 == errorMsg.length());
    }
}
