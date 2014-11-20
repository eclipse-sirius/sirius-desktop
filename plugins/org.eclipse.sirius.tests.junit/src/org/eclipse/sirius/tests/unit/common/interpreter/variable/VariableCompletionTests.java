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
package org.eclipse.sirius.tests.unit.common.interpreter.variable;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.VariableProposalProvider;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.tests.unit.common.interpreter.AbstractCompletionTestCase;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Tests for the {@link VariableInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class VariableCompletionTests extends AbstractCompletionTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setInterpreterAndProposalProvider(new VariableInterpreter(), new VariableProposalProvider());
    };

    /**
     * Tests proposal based on "var:" prefix.
     */
    public void testCompletionOnInterpreterPrefix() {

        ContentContext cc = createContentContext("v", 1, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);

        cc = createContentContext("var", 3, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);
    }

    /**
     * Tests proposal based on "var:" prefix.
     */
    public void testInstanceCompletionOnInterpreterPrefix() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        ContentInstanceContext cic = new ContentInstanceContext(c, "v", 1);
        assertCompletionMatchesEmptyExpression(cic, compoundProposalInstanceFunction);

        cic = new ContentInstanceContext(c, "var", 3);
        assertCompletionMatchesEmptyExpression(cic, compoundProposalInstanceFunction);
    }

    /**
     * Tests that completion proposals.
     */
    public void testProposals() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        ContentContext cc = null;
        assertTrue(getProposals(cc).isEmpty());

        ContentInstanceContext cic = null;
        assertTrue(getProposals(cic).isEmpty());
    }

    /**
     * Tests empty expression proposal.
     */
    public void testCompletionEmtpyField() {
        ContentContext cc = createContentContext("", 0, "EClass");
        assertCompletionMatchesEmptyExpression(cc, proposalFunction);
    }

    /**
     * Tests empty expression proposal.
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
                return createContentContext("var:", input, "EClass");
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
                return new ContentInstanceContext(c, "var:", input);
            }
        };
        doTestNoCompletion(createEmptyExpressionContextWithCursor, proposalInstanceFunction);
    }

    /**
     * @param createContextFunction
     *            function to give a context with "var:" and the wanted cursor
     *            position.
     */
    private <T> void doTestNoCompletion(Function<Integer, T> createContextFunction, Function<T, List<ContentProposal>> getProposalFunction) {
        // No completion in between 'v' and ':' in 'var:'
        for (int i = 0; i < 4; i++) {
            T ctx = createContextFunction.apply(i);
            List<ContentProposal> contentProposals = getProposalFunction.apply(ctx);
            assertTrue("No completion should be proposed when cursor is between 'v' and ':' in 'var:' for i=" + i, contentProposals.isEmpty());
        }
    }

    /**
     * Tests completion for an EClass.
     */
    public void testCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentContext cc = createContentContext("var:", 4, "EClass");

        // implicit context
        List<ContentProposal> contentProposals = getProposals(cc);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(concreteInterpreter.getVariables().keySet());
        vars.add("self");

        checkCompletionProposal(c.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentInstanceContext cic = new ContentInstanceContext(c, "var:", 4);

        List<ContentProposal> contentProposals = getProposals(cic);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(concreteInterpreter.getVariables().keySet());
        vars.add("self");

        checkCompletionProposal(c.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testCompletionOnOtherM2() {

        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentContext cc = createContentContext("var:", 4, "DNode", DiagramPackage.eINSTANCE);

        List<ContentProposal> contentProposals = getProposals(cc);

        Set<String> vars = Sets.newHashSet();
        vars.add("self");

        checkCompletionProposal(dNode.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentInstanceContext cic = new ContentInstanceContext(dNode, "var:", 4);
        List<ContentProposal> contentProposals = getProposals(cic);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(concreteInterpreter.getVariables().keySet());
        vars.add("self");

        checkCompletionProposal(dNode.eClass(), contentProposals, vars);
    }

    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals, Set<String> variables) {
        Collection<String> proposals = extractProposal(contentProposals);
        StringBuilder errorMsg = new StringBuilder();

        Predicate<String> concerned = new Predicate<String>() {
            public boolean apply(String input) {
                return true;
            }
        };

        checkVariables(variables, proposals, concerned, errorMsg);
        assertTrue(errorMsg.toString(), 0 == errorMsg.length());
    }
}
