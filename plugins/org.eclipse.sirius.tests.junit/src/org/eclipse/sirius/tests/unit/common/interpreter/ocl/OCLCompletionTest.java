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
package org.eclipse.sirius.tests.unit.common.interpreter.ocl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.ocl.business.internal.interpreter.OclInterpreter;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.tests.support.api.PluginVersionCompatibility;
import org.osgi.framework.Version;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Ensures that the OCL interpreter provides correct completion (e.g. in the
 * Interpreter View).
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class OCLCompletionTest extends TestCase {

    private OclInterpreter interpreter;

    private IProposalProvider proposalProvider;

    /**
     * A function that, once applied, returns all the proposals according to a
     * given {@link ContentInstanceContext}.
     */
    private Function<ContentInstanceContext, List<ContentProposal>> proposalInstanceFunction = new Function<ContentInstanceContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentInstanceContext input) {
            return proposalProvider.getProposals(interpreter, input);
        }
    };

    /**
     * A function that returns the expected proposal from an EOperation (in OCL,
     * we simply use the {@link EOperation}'s name.
     */
    private Function<EOperation, String> getSignature = new Function<EOperation, String>() {
        public String apply(EOperation from) {
            return from.getName();
        }
    };

    /**
     * 
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#setUp()
     */
    protected void setUp() throws Exception {
        super.setUp();

        // The OCL Interpreter is its own proposal provider (as it implements
        // IProposalProvider)
        interpreter = new OclInterpreter();
        proposalProvider = interpreter;
    };

    /**
     * 
     * {@inheritDoc}
     * 
     * @see junit.framework.TestCase#tearDown()
     */
    @Override
    protected void tearDown() throws Exception {
        interpreter.dispose();
        interpreter = null;
        proposalProvider = null;

        super.tearDown();
    }

    /**
     * Tests that OCL provides no proposal when context is null or contains no
     * object.
     */
    public void testOCLProposalsOnNullContext() {
        ContentContext cc = null;
        assertTrue(proposalProvider.getProposals(interpreter, cc).isEmpty());

        ContentInstanceContext cic = null;
        assertTrue(proposalProvider.getProposals(interpreter, cic).isEmpty());

        cic = new ContentInstanceContext(null, OclInterpreter.OCL_DISCRIMINANT, OclInterpreter.OCL_DISCRIMINANT.length());
        assertTrue(proposalProvider.getProposals(interpreter, cic).isEmpty());
    }

    /**
     * Tests invalid completion use cases (before 'ocl:', with invalid cursor
     * location...).
     */
    public void testOCLNoCompletion() {
        final EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        Function<Integer, ContentInstanceContext> createEmptyExpressionContextWithCursor = new Function<Integer, ContentInstanceContext>() {
            public ContentInstanceContext apply(Integer input) {
                return new ContentInstanceContext(c, OclInterpreter.OCL_DISCRIMINANT, input);
            }
        };

        // No completion before ocl:
        ContentInstanceContext ctx = createEmptyExpressionContextWithCursor.apply(0);
        List<ContentProposal> contentProposals = proposalInstanceFunction.apply(ctx);
        assertTrue("No completion should be proposed when cursor is before ocl:.", contentProposals.isEmpty());

        // No completion for invalid cursor position
        ctx = createEmptyExpressionContextWithCursor.apply(5);
        contentProposals = proposalInstanceFunction.apply(ctx);
        assertTrue("No completion should be proposed for an invalid cursor position.", contentProposals.isEmpty());

        // No completion for invalid cursor position
        ctx = createEmptyExpressionContextWithCursor.apply(-1);
        contentProposals = proposalInstanceFunction.apply(ctx);
        assertTrue("No completion should be proposed for an invalid cursor position.", contentProposals.isEmpty());
    }

    /**
     * Tests completion on an EClass.
     */
    public void testOCLCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentInstanceContext cic = new ContentInstanceContext(c, OclInterpreter.OCL_DISCRIMINANT, 0);

        // No completion before ocl:
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cic);
        assertTrue(contentProposals.isEmpty());

        // Check completion on 'ocl:'
        cic = new ContentInstanceContext(c, OclInterpreter.OCL_DISCRIMINANT, OclInterpreter.OCL_DISCRIMINANT.length());
        contentProposals = proposalProvider.getProposals(interpreter, cic);
        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("self");
        checkCompletionProposal(c.eClass(), contentProposals, vars);

        // Check completion on 'ocl:self.' (should be the same except for 'self'
        cic = new ContentInstanceContext(c, OclInterpreter.OCL_DISCRIMINANT + "self.", 9);
        vars.remove("self");
        contentProposals = proposalProvider.getProposals(interpreter, cic);
        checkCompletionProposal(c.eClass(), contentProposals, vars);
    }

    /**
     * Tests completion on another metamodel (here on a DNode).
     * 
     */
    public void testOCLCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentInstanceContext cic = new ContentInstanceContext(dNode, OclInterpreter.OCL_DISCRIMINANT, 0);

        // No completion before ocl:
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cic);
        assertTrue(contentProposals.isEmpty());

        // Check completion on 'ocl:'
        cic = new ContentInstanceContext(dNode, OclInterpreter.OCL_DISCRIMINANT, OclInterpreter.OCL_DISCRIMINANT.length());
        contentProposals = proposalProvider.getProposals(interpreter, cic);
        Set<String> vars = Sets.newHashSet();
        vars.add("self");
        checkCompletionProposal(dNode.eClass(), contentProposals, vars);

        // Check completion on 'ocl:self.' (should be the same except for 'self'
        cic = new ContentInstanceContext(dNode, OclInterpreter.OCL_DISCRIMINANT + "self./", 9);
        contentProposals = proposalProvider.getProposals(interpreter, cic);

        checkCompletionProposal(dNode.eClass(), contentProposals, interpreter.getVariables().keySet());
    }

    /**
     * Tests completion for an EClass when proposal contains begining of
     * expressions.
     * 
     */
    public void testOCLCompletionWithProposalStart() {
        // Step 1: create context
        final EClass c = EcoreFactory.eINSTANCE.createEClass();
        Function<String, ContentInstanceContext> createContextFunction = new Function<String, ContentInstanceContext>() {
            public ContentInstanceContext apply(String input) {
                return new ContentInstanceContext(c, input, input.length());
            }
        };
        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("self");

        // Step 2: call completion on 'ocl:ab' (should return 'abstract')
        ContentInstanceContext ctx = createContextFunction.apply("ocl:ab");
        List<ContentProposal> contentProposals = proposalInstanceFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, "ab");

        // Step 3: call completion on 'ocl:self.eAll' (should return
        // eAllContents, eAllSuperTypes...)
        ctx = createContextFunction.apply("ocl:self.eAll");
        contentProposals = proposalInstanceFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, "eAll");

        // Step 4: call completion on 'ocl:self.ocl' (should return
        // oclAsType, oclAsSet...)
        ctx = createContextFunction.apply("ocl:self.ocl");
        contentProposals = proposalInstanceFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, "ocl");

        // Step 5: call completion on 'ocl:self.ePackage.eClass' (should return
        // 'eClassifiers')
        ctx = createContextFunction.apply("ocl:self.ePackage.eClass");
        contentProposals = proposalInstanceFunction.apply(ctx);
        checkCompletionProposal(EcorePackage.eINSTANCE.getEPackage(), contentProposals, vars, "eClass");

        // Step 6: call completion inside a select instruction (should return
        // 'changeable')
        createContextFunction = new Function<String, ContentInstanceContext>() {
            public ContentInstanceContext apply(String input) {
                return new ContentInstanceContext(c, input, input.length() - 1);
            }
        };
        ctx = createContextFunction.apply("ocl:self.eAttributes->select(at: EAttribute | at.chan");
        contentProposals = proposalInstanceFunction.apply(ctx);
        checkCompletionProposal(EcorePackage.eINSTANCE.getEAttribute(), contentProposals, vars, "chan");
    }

    /**
     * Ensures that the proposals returned by the OCL interpreter are correct.
     * 
     * @param eClass
     *            the {@link EClass} of the element on which completion has been
     *            called (used to get all operations & features that should be
     *            proposed)
     * @param contentProposals
     *            the proposals computed by the OCL interpreter
     * @param variables
     *            the variables that should be currently handled by the
     *            interpreter
     */
    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals, Set<String> variables) {
        checkCompletionProposal(eClass, contentProposals, variables, "");
    }

    /**
     * Ensures that the proposals returned by the OCL interpreter are correct.
     * 
     * @param eClass
     *            the {@link EClass} of the element on which completion has been
     *            called (used to get all operations & features that should be
     *            proposed)
     * @param contentProposals
     *            the proposals computed by the OCL interpreter
     * @param variables
     *            the variables that should be currently handled by the
     *            interpreter
     * @param proposalStart
     *            used to calculate the expected proposals. For instance, if the
     *            proposalStart is 'abstr', then we will only return variables,
     *            features & operations which name starts with 'abstr'
     */
    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals, Set<String> variables, final String proposalStart) {
        // Step 1: get proposals as Strings
        Collection<String> proposals = getProposalsAsString(contentProposals);

        // Step 2: create predicate used to calculate the expected proposals.
        // For instance, if the proposalStart is 'abstr', then we will only
        // return variables, features & operations which name starts with
        // 'abstr'
        Predicate<String> concerned = new Predicate<String>() {
            public boolean apply(String input) {
                return StringUtil.isEmpty(proposalStart) || input.startsWith(proposalStart);
            }
        };

        // Step 3: check that the computed proposals contain the expected
        // proposals
        StringBuilder errorMsg = new StringBuilder();
        checkVariables(variables, proposals, concerned, errorMsg);
        checkEStruturalFeatures(eClass, proposals, concerned, errorMsg);
        checkEOperations(eClass, proposals, concerned, errorMsg);
        checkStandardOCLOperations(proposals, concerned, errorMsg);
        assertTrue(errorMsg.toString(), 0 == errorMsg.length());

        // Step 4: check that there are no other proposals than the expected
        // ones
        proposals.remove("getEStructuralFeature");
        assertEquals("Too many proposals are displayed: " + proposals.toString(), 0, proposals.size());
    }

    /**
     * Ensures that the given proposal lists contain all the eOperations of the
     * given {@link EClass} starting with the expected prefix.
     * 
     * @param eClass
     *            the EClass on which completion is called
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @param concerned
     *            a predicated allowing to select only the elements starting
     *            with the expected prefix
     * @param errorMsg
     *            the errorMsg to use for listing missing proposals
     */
    private void checkEOperations(EClass eClass, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Step 1: get the EOperations contained in the given eClass
        Collection<EOperation> opToCheck = Lists.newArrayList();
        Collection<String> opNames = Sets.newHashSet();
        for (EOperation op : eClass.getEAllOperations()) {
            if (!(opNames.contains(op.getName()))) {
                opNames.add(op.getName());
                opToCheck.add(op);
            }
        }
        Iterable<String> expectedEOperationProposals = Iterables.transform(opToCheck, getSignature);

        // Step 2: filtering the EOperations according to proposal start
        expectedEOperationProposals = Iterables.filter(expectedEOperationProposals, concerned);

        // Step 3: ensure that proposals contain all the expected elements
        StringBuilder tmpMsg = lookForExpectedProposals(expectedEOperationProposals, proposals);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected operations are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensures that the given proposal lists contain all the eStructuralFeatures
     * of the given {@link EClass} starting with the expected prefix.
     * 
     * @param eClass
     *            the EClass on which completion is called
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @param concerned
     *            a predicated allowing to select only the elements starting
     *            with the expected prefix
     * @param errorMsg
     *            the errorMsg to use for listing missing proposals
     */
    private void checkEStruturalFeatures(EClass eClass, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Step 1: get all EClasses structural features
        Function<EStructuralFeature, String> getExpectedProposal = new Function<EStructuralFeature, String>() {
            public String apply(EStructuralFeature from) {
                return from.getName();
            }
        };
        Iterable<String> expectedStructuralFeatures = Iterables.transform(eClass.getEAllStructuralFeatures(), getExpectedProposal);

        // Step 2: filtering features according to proposal start
        expectedStructuralFeatures = Sets.newLinkedHashSet(Iterables.filter(expectedStructuralFeatures, concerned));

        // Step 3: ensure that proposals contain all the expected elements
        StringBuilder tmpMsg = lookForExpectedProposals(expectedStructuralFeatures, proposals);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected features are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensures that the given proposal lists contain all the given expected
     * variables.
     * 
     * @param variables
     *            the expected variables
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @param concerned
     *            a predicated allowing to select only the elements starting
     *            with the expected prefix
     * @param errorMsg
     *            the errorMsg to use for listing missing proposals
     */
    private void checkVariables(Set<String> variables, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Step 1: filtering variable according to proposal start
        Set<String> filteredVariables = Sets.newLinkedHashSet(Iterables.filter(variables, concerned));

        // Step 2: ensure that proposals list all the expected variables
        StringBuilder tmpMsg = lookForExpectedProposals(filteredVariables, proposals);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected variables are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensures that the given proposal lists contain all the standard OCL
     * operations (oclAsType...) and toString.
     * 
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @param concerned
     *            a predicated allowing to select only the elements starting
     *            with the expected prefix
     * @param errorMsg
     *            the errorMsg to use for listing missing proposals
     */
    private void checkStandardOCLOperations(Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Step 1: list all OCL operations & toString
        Collection<String> oclOperations = Lists.newArrayList("oclAsType", "oclIsInState", "oclIsInvalid", "oclIsKindOf", "oclIsTypeOf", "oclIsUndefined");
        // oclAsSet and toString are only added in version more recent than ocl
        // 3.0.2
        if (new PluginVersionCompatibility("org.eclipse.ocl").compareTo(new Version("3.0.2.R30x_v201101110610")) > 0) {
            oclOperations.add("oclAsSet");
            oclOperations.add("toString");
        }

        // Step 2: filter expected operations according to proposal start
        oclOperations = Sets.newLinkedHashSet(Iterables.filter(oclOperations, concerned));

        // Step 3: ensure that proposals list all those operations
        StringBuilder tmpMsg = lookForExpectedProposals(oclOperations, proposals);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected variables are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensures that the given expected proposals are all contained in the given
     * actual proposals.
     * 
     * @param expectedProposals
     *            the expected proposals
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @return the error message
     */
    private StringBuilder lookForExpectedProposals(Iterable<String> expectedProposals, Collection<String> proposals) {
        StringBuilder tmpMsg = new StringBuilder();

        for (String prop : expectedProposals) {
            if (proposals.contains(prop)) {
                proposals.remove(prop);
            } else {
                tmpMsg.append("\n * " + prop);
            }
        }

        return tmpMsg;
    }

    /**
     * Returns the given collection of {@link ContentProposal} as a list of
     * String containing the values of each proposal.
     * 
     * @param proposals
     *            the proposals to convert
     * @return a list of String containing the values of each proposal
     */
    private Collection<String> getProposalsAsString(List<ContentProposal> proposals) {
        return Lists.newArrayList(Iterables.transform(proposals, new Function<ContentProposal, String>() {
            public String apply(ContentProposal from) {
                return from.getProposal();
            }
        }));
    }
}
