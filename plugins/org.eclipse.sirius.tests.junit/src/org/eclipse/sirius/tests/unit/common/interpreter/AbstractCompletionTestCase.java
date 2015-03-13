/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.interpreter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.CompoundInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.DefaultInterpreterContextFactory;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.TypeName;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Abstract base class for completion test cases.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 */

public class AbstractCompletionTestCase extends TestCase {
    /**
     * Concrete interpreter to test. Must be initialized by calling
     * {@link AbstractCompletionTestCase#setInterpreterAndProposalProvider()} in
     * a method that overrides {@link TestCase#setUp()}
     */
    protected IInterpreter concreteInterpreter;

    /**
     * Concrete proposal provider to test. Must be initialized by calling
     * {@link AbstractCompletionTestCase#setInterpreterAndProposalProvider()} in
     * a method that overrides {@link TestCase#setUp()}
     */
    protected IProposalProvider concreteProposalProvider;

    /**
     * A proposal function for the current provider that takes a
     * {@link ContentContext}
     */
    protected Function<ContentContext, List<ContentProposal>> proposalFunction = new Function<ContentContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentContext input) {
            return getProposals(input);
        }
    };

    /**
     * A proposal function for the current provider that takes a
     * {@link ContentInstanceContext}
     */
    protected Function<ContentInstanceContext, List<ContentProposal>> proposalInstanceFunction = new Function<ContentInstanceContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentInstanceContext input) {
            return getProposals(input);
        }
    };

    /**
     * A proposal function for {@link CompoundInterpreter} that takes a
     * {@link ContentContext}
     */
    protected Function<ContentContext, List<ContentProposal>> compoundProposalFunction = new Function<ContentContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentContext input) {
            return CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, input);
        }
    };

    /**
     * A proposal function for {@link CompoundInterpreter} that takes a
     * {@link ContentInstanceContext}
     */
    protected Function<ContentInstanceContext, List<ContentProposal>> compoundProposalInstanceFunction = new Function<ContentInstanceContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentInstanceContext input) {
            return CompoundInterpreter.INSTANCE.getProposals(CompoundInterpreter.INSTANCE, input);
        }
    };

    @Override
    protected void tearDown() throws Exception {
        if (concreteInterpreter != null) {
            concreteInterpreter.dispose();
        }
        concreteInterpreter = null;
        concreteProposalProvider = null;
        super.tearDown();
    }

    /**
     * Set the interpreter and the proposal provider.
     * 
     * @param interpreter
     *            the interpreter to use in the test
     * @param proposalProvider
     *            the proposal provider to use in the test
     */
    protected void setInterpreterAndProposalProvider(IInterpreter interpreter, IProposalProvider proposalProvider) {
        this.concreteInterpreter = interpreter;
        this.concreteProposalProvider = proposalProvider;
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param context
     *            the context
     * @return content proposal list
     */
    protected List<ContentProposal> getProposals(ContentContext context) {
        return concreteProposalProvider.getProposals(concreteInterpreter, context);
    }

    /**
     * Evaluates the content proposals for a given expression and returns the
     * result as a list.
     * 
     * @param context
     *            the context
     * @return content proposal list
     */
    protected List<ContentProposal> getProposals(ContentInstanceContext context) {
        return concreteProposalProvider.getProposals(concreteInterpreter, context);
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param eClass
     *            target domain class
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, String eClass) {
        return createContentContext(expression, cursorPosition, eClass, Collections.<String, VariableType> emptyMap());
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param element
     *            the concerned element
     * @param eClass
     *            target domain class
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, EObject element, String eClass) {
        return createContentContext(expression, cursorPosition, element, eClass, null, Collections.<String, VariableType> emptyMap(), Collections.<String> emptyList());
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param eClass
     *            target domain class
     * @param variables
     *            declared variables
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, String eClass, Map<String, VariableType> variables) {
        return createContentContext(expression, cursorPosition, eClass, null, variables, Collections.<String> emptyList());
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param eClass
     *            target domain class
     * @param ePackage
     *            available EPackage
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, String eClass, EPackage ePackage) {
        return createContentContext(expression, cursorPosition, eClass, ePackage, Collections.<String, VariableType> emptyMap(), Collections.<String> emptyList());
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param element
     *            the concerned element
     * @param eClass
     *            target domain class
     * @param ePackage
     *            available EPackage
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, EObject element, String eClass, EPackage ePackage) {
        return createContentContext(expression, cursorPosition, element, eClass, ePackage, Collections.<String, VariableType> emptyMap(), Collections.<String> emptyList());
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param eClass
     *            target domain class
     * @param ePackage
     *            available EPackage
     * @param variables
     *            the defined variables
     * @param dependencies
     *            the list of available dependencies
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, String eClass, EPackage ePackage, Map<String, VariableType> variables, Collection<String> dependencies) {
        return createContentContext(expression, cursorPosition, null, eClass, ePackage, variables, dependencies);
    }

    /**
     * Create a {@link ContentContext}.
     * 
     * @param expression
     *            expression of the context
     * @param cursorPosition
     *            cursor position in the expression
     * @param element
     *            the concerned element
     * @param eClass
     *            target domain class
     * @param ePackage
     *            available EPackage
     * @param variables
     *            the defined variables
     * @param dependencies
     *            the list of available dependencies
     * @return a new {@link ContentContext}
     */
    protected ContentContext createContentContext(String expression, int cursorPosition, EObject element, String eClass, EPackage ePackage, Map<String, VariableType> variables,
            Collection<String> dependencies) {
        Collection<EPackage> pList = ePackage == null ? Collections.<EPackage> emptyList() : Collections.singletonList(ePackage);
        return new ContentContext(expression, cursorPosition, DefaultInterpreterContextFactory.createInterpreterContext(element, true, null, VariableType.fromString(eClass), pList, variables,
                dependencies));
    }

    /**
     * Get proposals from content proposals.
     * 
     * @param proposals
     *            content proposals
     * @return collection of proposal string
     */
    protected Collection<String> extractProposal(List<ContentProposal> proposals) {
        return Lists.newArrayList(Iterables.transform(proposals, new Function<ContentProposal, String>() {
            public String apply(ContentProposal from) {
                return from.getProposal();
            }
        }));
    }

    /**
     * Ensure that proposal collection contains expected proposals.
     * 
     * @param expectedProposals
     *            expected proposals
     * @param proposals
     *            proposals to check
     * @param concerned
     *            predicate to filter expected proposals
     * @return error message or empty string
     */
    protected StringBuilder lookForExpectedProposals(Iterable<String> expectedProposals, Collection<String> proposals, Predicate<String> concerned) {
        StringBuilder tmpMsg = new StringBuilder();

        for (String prop : Iterables.filter(expectedProposals, concerned)) {
            if (proposals.contains(prop)) {
                proposals.remove(prop);
            } else {
                tmpMsg.append("\n * " + prop);
            }
        }

        return tmpMsg;
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
    protected void checkVariables(Set<String> expectedVariables, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Variables
        StringBuilder tmpMsg = lookForExpectedProposals(expectedVariables, proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected variables are not present in completion proposals:");
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
    protected void checkEStruturalFeatures(EClass eClass, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        Function<EStructuralFeature, String> getExpectedProposal = new Function<EStructuralFeature, String>() {
            public String apply(EStructuralFeature from) {
                return from.getName();
            }
        };

        // EStructuralfeatures
        StringBuilder tmpMsg = lookForExpectedProposals(Iterables.transform(eClass.getEAllStructuralFeatures(), getExpectedProposal), proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected features are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensures that the given proposal lists contain all the eOperations of the
     * given {@link EClass} starting with the expected prefix.
     * 
     * @param eClass
     *            the EClass on which completion is called
     * @param implicitContext
     *            implicit context
     * @param proposals
     *            the proposals computed by the OCL interpreter
     * @param concerned
     *            a predicated allowing to select only the elements starting
     *            with the expected prefix
     * @param signature
     *            function class to get operation signature
     * @param errorMsg
     *            the errorMsg to use for listing missing proposals
     */
    protected void checkEOperations(EClass eClass, boolean implicitContext, Collection<String> proposals, Predicate<String> concerned, Function<EOperation, String> signature, StringBuilder errorMsg) {
        // EOperations
        Collection<EOperation> opToCheck = Lists.newArrayList();
        Collection<String> opNames = Sets.newHashSet();
        for (EOperation op : eClass.getEAllOperations()) {
            if (!(implicitContext && opNames.contains(op.getName()))) {
                opNames.add(op.getName());
                opToCheck.add(op);
            }
        }

        StringBuilder tmpMsg = lookForExpectedProposals(Iterables.transform(opToCheck, signature), proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected operations are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    /**
     * Ensure that the completion matches the interpreter empty expression
     * 
     * @param context
     *            context to used
     * @param getProposalFunction
     *            function class to call to have proposals
     */
    protected <T> void assertCompletionMatchesEmptyExpression(T context, Function<T, List<ContentProposal>> getProposalFunction) {
        List<ContentProposal> proposals = getProposalFunction.apply(context);
        final ContentProposal emptyExpressionProposal = concreteProposalProvider.getNewEmtpyExpression();

        assertEquals(1, proposals.size());
        ContentProposal proposal = proposals.get(0);

        // ContentProposal.equals() does not test the display and the cursor
        // position.
        assertEquals(emptyExpressionProposal.getProposal(), proposal.getProposal());
        assertEquals(emptyExpressionProposal.getDisplay(), proposal.getDisplay());
        assertEquals(emptyExpressionProposal.getCursorPosition(), proposal.getCursorPosition());
    }
}
