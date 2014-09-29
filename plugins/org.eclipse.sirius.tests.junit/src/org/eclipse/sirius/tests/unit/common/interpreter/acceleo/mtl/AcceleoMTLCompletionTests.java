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
package org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.AcceleoMTLInterpreter;
import org.eclipse.sirius.common.acceleo.mtl.business.internal.interpreter.DynamicAcceleoModule;
import org.eclipse.sirius.common.acceleo.mtl.ide.AcceleoProposalProvider;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentInstanceContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.DefaultInterpreterContextFactory;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DiagramFactory;
import org.eclipse.sirius.diagram.DiagramPackage;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import org.eclipse.sirius.tests.SiriusTestsPlugin;

/**
 * Tests for the {@link AcceleoMTLInterpreter} utility class.
 * 
 * @author mporhel
 * 
 */
public class AcceleoMTLCompletionTests extends TestCase {

    private IInterpreter interpreter;

    private IProposalProvider proposalProvider;

    private Function<ContentContext, List<ContentProposal>> proposalFunction = new Function<ContentContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentContext input) {
            IProposalProvider proposalProvider = new AcceleoProposalProvider();
            return proposalProvider.getProposals(interpreter, input);
        }
    };

    private Function<ContentInstanceContext, List<ContentProposal>> proposalInstanceFunction = new Function<ContentInstanceContext, List<ContentProposal>>() {
        public List<ContentProposal> apply(ContentInstanceContext input) {
            IProposalProvider proposalProvider = new AcceleoProposalProvider();
            return proposalProvider.getProposals(interpreter, input);
        }
    };

    private static final String IMPORT = "org::eclipse::sirius::tests::unit::common::interpreter::acceleo::mtl::AcceleoMtlInterpreterTestModule";

    private static final String SERVICE = "org.eclipse.sirius.tests.unit.common.interpreter.acceleo.mtl.BasicService";

    private Function<EOperation, String> getSignature = new Function<EOperation, String>() {
        public String apply(EOperation from) {
            return getProposalSignature(from);
        }
    };

    protected void setUp() throws Exception {
        super.setUp();

        interpreter = new AcceleoMTLInterpreter();
        proposalProvider = new AcceleoProposalProvider();
    };

    @Override
    protected void tearDown() throws Exception {
        interpreter.dispose();
        interpreter = null;

        proposalProvider = null;

        super.tearDown();
    }

    /**
     * Tests that completion proposals.
     */
    public void testAcceleoMtlProposals() {
        EClass eClass = EcoreFactory.eINSTANCE.createEClass();
        eClass.setName("c");

        ContentContext cc = null;
        assertTrue(proposalProvider.getProposals(interpreter, cc).isEmpty());

        ContentInstanceContext cic = null;
        assertTrue(proposalProvider.getProposals(interpreter, cic).isEmpty());

        interpreter.dispose();
    }

    /**
     * Tests emtpy mtl expression proposal.
     */
    public void testAcceleoMTLCompletionEmtpyField() {
        ContentContext cc = createContentContext("", 0, "EClass");

        doTestAcceleoMTLCompletionEmtpyField(cc, proposalFunction);
    }

    /**
     * Tests emtpy mtl expression proposal.
     */
    public void testAcceleoMTLInstanceCompletionEmtpyField() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        ContentInstanceContext cic = new ContentInstanceContext(c, "", 0);

        doTestAcceleoMTLCompletionEmtpyField(cic, proposalInstanceFunction);
    }

    private <T> void doTestAcceleoMTLCompletionEmtpyField(T context, Function<T, List<ContentProposal>> getProposalFunction) {
        List<ContentProposal> proposals = getProposalFunction.apply(context);

        assertEquals(1, proposals.size());
        assertEquals("[/]", proposals.get(0).getProposal());
        assertEquals("[/]", proposals.get(0).getDisplay());
        assertEquals(1, proposals.get(0).getCursorPosition());

        ContentProposal newEmtpyExpression = proposalProvider.getNewEmtpyExpression();
        assertEquals("[/]", newEmtpyExpression.getProposal());
        assertEquals("[/]", newEmtpyExpression.getDisplay());
        assertEquals(1, newEmtpyExpression.getCursorPosition());
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLNoCompletion() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        Function<Integer, ContentContext> createEmptyExpressionContextWithCursor = new Function<Integer, ContentContext>() {
            public ContentContext apply(Integer input) {
                return createContentContext("[/]", input, "EClass");
            }
        };
        doTestAcceleoMTLNoCompletion(createEmptyExpressionContextWithCursor, proposalFunction);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceNoCompletion() {
        final EClass c = EcoreFactory.eINSTANCE.createEClass();
        c.setName("c1");

        Function<Integer, ContentInstanceContext> createEmptyExpressionContextWithCursor = new Function<Integer, ContentInstanceContext>() {
            public ContentInstanceContext apply(Integer input) {
                return new ContentInstanceContext(c, "[/]", input);
            }
        };
        doTestAcceleoMTLNoCompletion(createEmptyExpressionContextWithCursor, proposalInstanceFunction);
    }

    /**
     * @param createContextFunction
     *            function to give a context with [/] and the wanted cursor
     *            position.
     */
    private <T> void doTestAcceleoMTLNoCompletion(Function<Integer, T> createContextFunction, Function<T, List<ContentProposal>> getProposalFunction) {

        // No completion before [
        T ctx = createContextFunction.apply(0);
        List<ContentProposal> contentProposals = getProposalFunction.apply(ctx);
        // VP-3353
        // assertTrue("No completion should be proposed when cursor is before [/].",
        // contentProposals.isEmpty());

        // No completion in /]
        ctx = createContextFunction.apply(2);
        contentProposals = getProposalFunction.apply(ctx);
        assertTrue("No completion should be proposed when cursor is betwen / and ] in [/].", contentProposals.isEmpty());

        // No completion after /]
        ctx = createContextFunction.apply(3);
        contentProposals = getProposalFunction.apply(ctx);
        assertTrue("No completion should be proposed when cursor is after [/].", contentProposals.isEmpty());

        // No completion for invalid cursor position
        ctx = createContextFunction.apply(4);
        // VP-3353
        // contentProposals = getProposalFunction.apply(ctx);
        // assertTrue("No completion should be proposed for an invalid cursor position.",
        // contentProposals.isEmpty());

        // No completion for invalid cursor position
        ctx = createContextFunction.apply(-1);
        // VP-3353
        // contentProposals = getProposalFunction.apply(ctx);
        // assertTrue("No completion should be proposed for an invalid cursor position.",
        // contentProposals.isEmpty());

    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentContext cc = createContentContext("[/]", 0, "EClass");

        // No completion before [
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cc);
        // assertTrue(contentProposals.isEmpty());

        // implicit context
        cc = createContentContext("[/]", 1, "EClass");
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        checkCompletionProposal(c.eClass(), contentProposals, vars, true);

        //
        cc = createContentContext("[self./]", 6, "EClass");
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        checkCompletionProposal(c.eClass(), contentProposals, interpreter.getVariables().keySet(), false);

        // qualified name
        cc = createContentContext("[self./]", 6, "ecore.EClass");
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        checkCompletionProposal(c.eClass(), contentProposals, interpreter.getVariables().keySet(), false);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceCompletionOnEcore() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();
        ContentInstanceContext cic = new ContentInstanceContext(c, "[/]", 0);

        // No completion before [
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cic);
        // assertTrue(contentProposals.isEmpty());

        cic = new ContentInstanceContext(c, "[/]", 1);
        contentProposals = proposalProvider.getProposals(interpreter, cic);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        checkCompletionProposal(c.eClass(), contentProposals, vars, true);

        //
        cic = new ContentInstanceContext(c, "[self./]", 6);
        contentProposals = proposalProvider.getProposals(interpreter, cic);
        checkCompletionProposal(c.eClass(), contentProposals, interpreter.getVariables().keySet(), false);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLCompletionOnOtherM2() {

        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentContext cc = createContentContext("[/]", 0, "DNode", DiagramPackage.eINSTANCE, Collections.<String, String> emptyMap(), Collections.<String> emptyList());

        // No completion before [
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cc);
        // assertTrue(contentProposals.isEmpty());

        cc = createContentContext("[/]", 1, "DNode", DiagramPackage.eINSTANCE, Collections.<String, String> emptyMap(), Collections.<String> emptyList());
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        Set<String> vars = Sets.newHashSet();
        vars.add("thisEObject");
        vars.add("self");

        checkCompletionProposal(dNode.eClass(), contentProposals, vars, true);

        cc = createContentContext("[self./]", 6, "DNode", DiagramPackage.eINSTANCE, Collections.<String, String> emptyMap(), Collections.<String> emptyList());
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        checkCompletionProposal(dNode.eClass(), contentProposals, interpreter.getVariables().keySet(), false);

        cc = createContentContext("[self./]", 6, "diagram.DNode", DiagramPackage.eINSTANCE, Collections.<String, String> emptyMap(), Collections.<String> emptyList());
        contentProposals = proposalProvider.getProposals(interpreter, cc);

        checkCompletionProposal(dNode.eClass(), contentProposals, interpreter.getVariables().keySet(), false);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceCompletionOnOtherM2() {
        DNode dNode = DiagramFactory.eINSTANCE.createDNode();
        ContentInstanceContext cic = new ContentInstanceContext(dNode, "[/]", 0);

        // No completion before [
        List<ContentProposal> contentProposals = proposalProvider.getProposals(interpreter, cic);
        // assertTrue(contentProposals.isEmpty());

        cic = new ContentInstanceContext(dNode, "[/]", 1);
        contentProposals = proposalProvider.getProposals(interpreter, cic);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        checkCompletionProposal(dNode.eClass(), contentProposals, vars, true);

        //
        cic = new ContentInstanceContext(dNode, "[self./]", 6);
        contentProposals = proposalProvider.getProposals(interpreter, cic);

        checkCompletionProposal(dNode.eClass(), contentProposals, interpreter.getVariables().keySet(), false);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLCompletionWithVariables() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();

        String varName = "maClasse";
        String varType = c.eClass().getName();
        ContentContext c1 = createContentContext("[/]", 1, "EClass", Collections.singletonMap(varName, varType));
        ContentContext c2 = createContentContext("[self.name.concat()/]", 18, "EClass", Collections.singletonMap(varName, varType));

        doTestAcceleoMTLCompletionWithVariables(c, varName, c1, c2, proposalFunction);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceCompletionWithVariables() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();

        ContentInstanceContext c1 = new ContentInstanceContext(c, "[/]", 1);
        ContentInstanceContext c2 = new ContentInstanceContext(c, "[self.name.concat()/]", 18);

        doTestAcceleoMTLCompletionWithVariables(c, "maClasse", c1, c2, proposalInstanceFunction);
    }

    /**
     * @param context1
     *            "[/]", cursor : 1
     * @param context2
     *            "[self.name.concat()/]", cursor : 18
     */
    private <T> void doTestAcceleoMTLCompletionWithVariables(EClass c, String varName, T context1, T context2, Function<T, List<ContentProposal>> getProposalFunction) {
        interpreter.setVariable(varName, c);
        assertEquals("Variables was not declared", 1, interpreter.getVariables().size());

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        List<ContentProposal> contentProposals = getProposalFunction.apply(context1);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true);

        contentProposals = getProposalFunction.apply(context2);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLCompletionWithDependencies() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();

        Collection<String> dependencies = Lists.newArrayList(IMPORT, SERVICE);
        ContentContext c1 = createContentContext("[/]", 1, "EClass", EcorePackage.eINSTANCE, Collections.<String, String> emptyMap(), dependencies);
        ContentContext c2 = createContentContext("[self./]", 6, "EClass", EcorePackage.eINSTANCE, Collections.<String, String> emptyMap(), dependencies);
        ContentContext c3 = createContentContext("[self.name.concat()/]", 18, "EClass");

        doTestAcceleoMTLCompletionWithDependencies(c, c1, c2, c3, proposalFunction, dependencies);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceCompletionWithDependencies() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();

        Collection<String> dependencies = Lists.newArrayList(IMPORT, SERVICE);

        ContentInstanceContext c1 = new ContentInstanceContext(c, "[/]", 1);
        ContentInstanceContext c2 = new ContentInstanceContext(c, "[self./]", 6);
        ContentInstanceContext c3 = new ContentInstanceContext(c, "[self.name.concat()/]", 18);

        doTestAcceleoMTLCompletionWithDependencies(c, c1, c2, c3, proposalInstanceFunction, dependencies);
    }

    /**
     * @param context1
     *            "[/]", cursor : 1
     * @param context2
     *            "[self./]", cursor : 6
     * @param context3
     *            "[self.name.concat()/]", cursor : 18
     */
    private <T> void doTestAcceleoMTLCompletionWithDependencies(EClass c, T context1, T context2, T context3, Function<T, List<ContentProposal>> getProposalFunction, Collection<String> dependencies) {
        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        for (String dep : dependencies) {
            interpreter.addImport(dep);
        }

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        List<ContentProposal> contentProposals = getProposalFunction.apply(context1);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true, dependencies, "");

        contentProposals = getProposalFunction.apply(context2);
        checkCompletionProposal(c.eClass(), contentProposals, Collections.<String> emptySet(), false, dependencies, "");

        contentProposals = getProposalFunction.apply(context3);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true, dependencies, "");
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLCompletionWithProposalStart() {
        EClass c = EcoreFactory.eINSTANCE.createEClass();

        CreateContextWithCursorPosition<ContentContext> createContext = new CreateContextWithCursorPosition<ContentContext>() {
            public ContentContext apply(String input) {
                return createContentContext(input, input.indexOf('/') - i, "EClass");
            }
        };

        doTestAcceleoMTLCompletionWithProposalStart(c, createContext, proposalFunction);
    }

    /**
     * Tests completion for an EClass.
     * 
     * There is a bug in Acceleo completion without context:
     * getEStructuralFeature(name) is not proposed.
     */
    public void testAcceleoMTLInstanceCompletionWithProposalStart() {
        final EClass c = EcoreFactory.eINSTANCE.createEClass();

        CreateContextWithCursorPosition<ContentInstanceContext> createContext = new CreateContextWithCursorPosition<ContentInstanceContext>() {
            public ContentInstanceContext apply(String input) {
                return new ContentInstanceContext(c, input, getCursorPosition(input));
            }
        };

        doTestAcceleoMTLCompletionWithProposalStart(c, createContext, proposalInstanceFunction);
    }

    /**
     * @param createContextFunction
     *            function to give a context with [/] and the wanted cursor
     *            position.
     */
    private <T> void doTestAcceleoMTLCompletionWithProposalStart(EClass c, CreateContextWithCursorPosition<T> createContextFunction, Function<T, List<ContentProposal>> getProposalFunction) {
        List<String> mockVsms = new ArrayList<String>();
        mockVsms.add(SiriusTestsPlugin.PLUGIN_ID);
        interpreter.setProperty(IInterpreter.FILES, mockVsms);
        interpreter.addImport(IMPORT);

        Set<String> vars = Sets.newHashSet();
        vars.addAll(interpreter.getVariables().keySet());
        vars.add("thisEObject");
        vars.add("self");

        T ctx = createContextFunction.apply("[ab/]");
        List<ContentProposal> contentProposals = getProposalFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true, Collections.singleton(IMPORT), "ab");

        ctx = createContextFunction.apply("[dyna/]");
        contentProposals = getProposalFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, true, Collections.singleton(IMPORT), "dyna");

        ctx = createContextFunction.apply("[self.eAll/]");
        contentProposals = getProposalFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, Collections.<String> emptySet(), false, Collections.singleton(IMPORT), "eAll");

        createContextFunction.setDelta(1);
        ctx = createContextFunction.apply("[self.name.concat(thisEObject.nam)/]");
        contentProposals = getProposalFunction.apply(ctx);
        checkCompletionProposal(c.eClass(), contentProposals, vars, false, Collections.singleton(IMPORT), "nam");
    }

    private ContentContext createContentContext(String expression, int cursorPostion, String eClass) {
        return createContentContext(expression, cursorPostion, eClass, Collections.<String, String> emptyMap());
    }

    private ContentContext createContentContext(String expression, int cursorPostion, String eClass, Map<String, String> variables) {
        return createContentContext(expression, cursorPostion, eClass, null, variables, Collections.<String> emptyList());
    }

    private ContentContext createContentContext(String expression, int cursorPostion, String eClass, EPackage ePackage, Map<String, String> variables, Collection<String> dependencies) {
        Collection<EPackage> pList = ePackage == null ? Collections.<EPackage> emptyList() : Collections.singletonList(ePackage);
        return new ContentContext(expression, cursorPostion, DefaultInterpreterContextFactory.createInterpreterContext(null, true, null, Collections.singletonList(eClass), pList, variables,
                dependencies));
    }

    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals, Set<String> variables, boolean ignoreOperationNotFound) {
        checkCompletionProposal(eClass, contentProposals, variables, ignoreOperationNotFound, Collections.<String> emptyList(), "");
    }

    /*
     * There is a bug in Acceleo completion without context: doublons are not
     * minimized without checking parameters. For an EClass,
     * getEStructuralFeature(name) is not proposed.
     */
    private void checkCompletionProposal(EClass eClass, List<ContentProposal> contentProposals, Set<String> variables, boolean implicitContext, Collection<String> dependencies,
            final String proposalStart) {
        // assertFalse(contentProposals.isEmpty());
        Collection<String> proposals = extractProposal(contentProposals);
        Predicate<String> concerned = new Predicate<String>() {
            public boolean apply(String input) {
                return StringUtil.isEmpty(proposalStart) || input.startsWith(proposalStart);
            }
        };

        StringBuilder errorMsg = new StringBuilder();

        checkVariables(variables, proposals, concerned, errorMsg);

        checkEStruturalFeatures(eClass, proposals, concerned, errorMsg);

        checkEOperations(eClass, implicitContext, proposals, concerned, errorMsg);

        checkDependencies(implicitContext, dependencies, proposals, concerned, errorMsg);

        checkDynamicQueries(proposals, errorMsg);

        assertTrue(errorMsg.toString(), 0 == errorMsg.length());
    }

    private void checkDynamicQueries(Collection<String> proposals, StringBuilder errorMsg) {
        StringBuilder tmpMsg = new StringBuilder();

        for (String prop : proposals) {
            if (prop.startsWith(DynamicAcceleoModule.getDynamicModuleQueryPrefix())) {
                tmpMsg.append("\n * " + prop);
            }
        }

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nThe dynamic queries should not be present in the complection proposals:");
            errorMsg.append(tmpMsg.toString());
        }

    }

    private void checkDependencies(boolean implicitContext, Collection<String> dependencies, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Dependencies
        StringBuilder tmpMsg = lookForExpectedProposals(getDependenciesProposals(dependencies, implicitContext), proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected dependencies are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    private StringBuilder lookForExpectedProposals(Iterable<String> expectedProposals, Collection<String> proposals, Predicate<String> concerned) {
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

    private void checkEOperations(EClass eClass, boolean implicitContext, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // EOperations
        Collection<EOperation> opToCheck = Lists.newArrayList();
        Collection<String> opNames = Sets.newHashSet();
        for (EOperation op : eClass.getEAllOperations()) {
            if (!(implicitContext && opNames.contains(op.getName()))) {
                opNames.add(op.getName());
                opToCheck.add(op);
            }
        }

        StringBuilder tmpMsg = lookForExpectedProposals(Iterables.transform(opToCheck, getSignature), proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected operations are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    private void checkEStruturalFeatures(EClass eClass, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
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

    private void checkVariables(Set<String> variables, Collection<String> proposals, Predicate<String> concerned, StringBuilder errorMsg) {
        // Variables
        StringBuilder tmpMsg = lookForExpectedProposals(variables, proposals, concerned);

        if (tmpMsg.length() != 0) {
            tmpMsg.insert(0, "\nSome expected variables are not present in completion proposals:");
            errorMsg.append(tmpMsg.toString());
        }
    }

    private String getProposalSignature(EOperation op) {
        String opSig = op.getName() + "(";
        if (op.getEParameters().size() > 1) {
            String comas = "";
            for (int i = 1; i < op.getEParameters().size(); i++) {
                comas = comas + ", ";
            }
            opSig = opSig + comas;
        }
        opSig = opSig + ")";
        return opSig;
    }

    private Collection<String> getDependenciesProposals(Collection<String> dependencies, boolean implicitContext) {
        Collection<String> services = Lists.newArrayList();
        if (dependencies == null) {
            return services;
        }

        if (dependencies.contains(IMPORT)) {
            services.add("getImportedName()");
            services.add("getImportedQueryName()");
            services.add("getName()");
            services.add("eContentsQuery()");
            services.add("getNameQuery()");
            services.add("isAbstractQuery()");
            services.add("selfImportedQuery()");
            services.add("selfQuery()");
        }

        if (dependencies.contains(SERVICE)) {
            services.add("sampleService()");
        }

        return services;

    }

    private Collection<String> extractProposal(List<ContentProposal> proposals) {
        return Lists.newArrayList(Iterables.transform(proposals, new Function<ContentProposal, String>() {
            public String apply(ContentProposal from) {
                return from.getProposal();
            }
        }));
    }

    private abstract class CreateContextWithCursorPosition<T> implements Function<String, T> {
        int i = 0;

        public void setDelta(int i) {
            this.i = i;
        }

        /**
         * Compute the cursor position : the index of '/' in the expression
         * minus the delta (default value is 0).
         * 
         * @param input
         *            the expression
         * @return the cursor position
         */
        protected int getCursorPosition(String input) {
            return input.indexOf('/') - i;
        }
    }
}
