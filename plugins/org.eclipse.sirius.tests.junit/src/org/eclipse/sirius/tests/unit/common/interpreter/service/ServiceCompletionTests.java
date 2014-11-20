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
package org.eclipse.sirius.tests.unit.common.interpreter.service;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.ServiceProposalProvider;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.tests.unit.common.interpreter.AbstractCompletionTestCase;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

/**
 * Tests for the {@link ServiceInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class ServiceCompletionTests extends AbstractCompletionTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setInterpreterAndProposalProvider(new ServiceInterpreter(), new ServiceProposalProvider());
    };

    /**
     * Tests proposal based on "service:" prefix.
     */
    public void testCompletionOnInterpreterPrefix() {
        ContentContext cc = createContentContext("s", 1, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);

        cc = createContentContext("service", 7, "EClass");
        assertCompletionMatchesEmptyExpression(cc, compoundProposalFunction);
    }

    /**
     * Tests proposal based on "service:" prefix.
     */
    public void testInstanceCompletionOnInterpreterPrefix() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        ContentInstanceContext cic = new ContentInstanceContext(c, "s", 1);
        assertCompletionMatchesEmptyExpression(cic, compoundProposalInstanceFunction);

        cic = new ContentInstanceContext(c, "service", 7);
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
    public void testCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentContext cc = createContentContext("service:", 8, c, "EClass");

        // implicit context
        List<ContentProposal> contentProposals = getProposals(cc);

        // ServiceProposalProvider add "." at the end of a variable
        Set<String> vars = Sets.newHashSet();
        for (String variable : concreteInterpreter.getVariables().keySet()) {
            vars.add(variable + ".");
        }
        vars.add("self.");

        checkCompletionProposal(c.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentInstanceContext cic = new ContentInstanceContext(c, "service:", 8);

        List<ContentProposal> contentProposals = getProposals(cic);

        // ServiceProposalProvider add "." at the end of a variable
        Set<String> vars = Sets.newHashSet();
        for (String variable : concreteInterpreter.getVariables().keySet()) {
            vars.add(variable + ".");
        }
        vars.add("self.");

        checkCompletionProposal(c.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testCompletionOnOtherM2() {

        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentContext cc = createContentContext("service:", 8, dNode, "DNode", DiagramPackage.eINSTANCE);

        List<ContentProposal> contentProposals = getProposals(cc);

        Set<String> vars = Sets.newHashSet();

        // ServiceProposalProvider add "." at the end of a variable
        vars.add("self.");

        checkCompletionProposal(dNode.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion for an EClass.
     */
    public void testInstanceCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentInstanceContext cic = new ContentInstanceContext(dNode, "service:", 8);
        List<ContentProposal> contentProposals = getProposals(cic);

        // ServiceProposalProvider add "." at the end of a variable
        Set<String> vars = Sets.newHashSet();
        for (String variable : concreteInterpreter.getVariables().keySet()) {
            vars.add(variable + ".");
        }
        vars.add("self.");

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
