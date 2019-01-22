/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.unit.common.interpreter.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.internal.interpreter.ServiceInterpreter;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.ServiceProposalProvider;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.ecore.design.service.EcoreSamplePlugin;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

import junit.framework.TestCase;

/**
 * A Test case for the {@link ServiceProposalProvider}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class ServiceProposalProviderTests extends TestCase {

    /**
     * Name of the variable added for some tests.
     */
    private static final String GETAWAY_VARIABLE = "getawayVariable";

    private IInterpreter interpreter;

    private IProposalProvider proposalProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new ServiceInterpreter();
        proposalProvider = new ServiceProposalProvider();
    }

    protected IInterpreterContext initInterpreterContext(boolean addServices, boolean addOneVariable) {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        if (addServices) {
            interpreter.setProperty(IInterpreter.FILES, Collections.singletonList("/" + EcoreSamplePlugin.PLUGIN_ID + "/description/ecore.odesign"));
            interpreter.addImport("org.eclipse.sirius.ecore.design.service.EcoreService");
        }
        if (addOneVariable) {
            // Add a variable in this interpreter
            EAttribute eAttribute = EcoreFactory.eINSTANCE.createEAttribute();
            eAttribute.setName("a1");
            interpreter.setVariable(GETAWAY_VARIABLE, eAttribute);
            interpreterContext.getVariables().put(GETAWAY_VARIABLE, VariableType.ANY_EOBJECT);
        }
        return interpreterContext;
    }

    public void testServiceProposalProviderNewEmptyExpression() {
        ContentProposal contentProposal = proposalProvider.getNewEmtpyExpression();
        assertNotNull("ServiceProposalProvider.getNewEmtpyExpression() should not return null", contentProposal);
        assertEquals("The proposal should be the service interpreter prefix", ServiceInterpreter.PREFIX, contentProposal.getProposal());
    }

    public void testServiceProposalProviderWithoutServices() {
        IInterpreterContext interpreterContext = initInterpreterContext(false, false);
        ContentContext context = new ContentContext(ServiceInterpreter.PREFIX, ServiceInterpreter.PREFIX.length(), interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be only one proposal (self)", 1, proposals.size());
        assertEquals("There should be only \"self\" proposal.", VariableInterpreter.SELF_VARIABLE_NAME + ServiceInterpreter.RECEIVER_SEPARATOR, proposals.get(0).getProposal());
    }

    public void testServiceProposalProviderWithServices() {
        IInterpreterContext interpreterContext = initInterpreterContext(true, true);
        ContentContext context = new ContentContext(ServiceInterpreter.PREFIX, ServiceInterpreter.PREFIX.length(), interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 11 proposals (9 services, self and getawayVariable variables", 11, proposals.size());
        List<String> proposalStrings = new ArrayList<String>();
        proposalStrings.add(proposals.get(0).getProposal());
        proposalStrings.add(proposals.get(1).getProposal());
        proposalStrings.add(proposals.get(2).getProposal());
        proposalStrings.add(proposals.get(3).getProposal());
        proposalStrings.add(proposals.get(4).getProposal());
        proposalStrings.add(proposals.get(5).getProposal());
        proposalStrings.add(proposals.get(6).getProposal());
        proposalStrings.add(proposals.get(7).getProposal());
        proposalStrings.add(proposals.get(8).getProposal());
        proposalStrings.add(proposals.get(9).getProposal());
        proposalStrings.add(proposals.get(10).getProposal());
        assertAllServices(proposalStrings);
        assertTrue("There should be a proposal for the variable self", proposalStrings.contains(VariableInterpreter.SELF_VARIABLE_NAME + ServiceInterpreter.RECEIVER_SEPARATOR));
        assertTrue("There should be a proposal for the variable getawayVariable", proposalStrings.contains(GETAWAY_VARIABLE + ServiceInterpreter.RECEIVER_SEPARATOR));
    }

    /**
     * Check that the list <code>proposalStrings</code> contains all expected
     * services.
     * 
     * @param proposalStrings
     *            The list to check.
     */
    private void assertAllServices(List<String> proposalStrings) {
        assertTrue("There should be a proposal for the service renderTooltip", proposalStrings.contains("renderTooltip()"));
        assertTrue("There should be a proposal for the service getAllAssociatedElements", proposalStrings.contains("getAllAssociatedElements()"));
        assertTrue("There should be a proposal for the service eResourceName", proposalStrings.contains("eResourceName()"));
        assertTrue("There should be a proposal for the service getRootContainer", proposalStrings.contains("getRootContainer()"));
        assertTrue("There should be a proposal for the service renderToolTip", proposalStrings.contains("renderTooltip()"));
        assertTrue("There should be a proposal for the service allRoots", proposalStrings.contains("allRoots()"));
        assertTrue("There should be a proposal for the service render", proposalStrings.contains("render()"));
        assertTrue("There should be a proposal for the service performEdit", proposalStrings.contains("performEdit(String)"));
        assertTrue("There should be a proposal for the service findTypeByName", proposalStrings.contains("findTypeByName(String)"));
    }

    /**
     * Check that if the user begins to write something (in this test, the "get"
     * prefix is used), the proposals are filtered. In this test, only 2
     * services and 1 variable should be returned.
     */
    public void testServiceProposalProviderWithServicesWithPrefix() {
        IInterpreterContext interpreterContext = initInterpreterContext(true, true);
        ContentContext context = new ContentContext(ServiceInterpreter.PREFIX + "get", ServiceInterpreter.PREFIX.length() + 3, interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 3 proposals", 3, proposals.size());
        List<String> proposalStrings = new ArrayList<String>();
        proposalStrings.add(proposals.get(0).getProposal());
        proposalStrings.add(proposals.get(1).getProposal());
        proposalStrings.add(proposals.get(2).getProposal());
        assertTrue("There should be a proposal for the service getAllAssociatedElements", proposalStrings.contains("getAllAssociatedElements()"));
        assertTrue("There should be a proposal for the service getRootContainer", proposalStrings.contains("getRootContainer()"));
        assertTrue("There should be a proposal for the variable getawayVariable", proposalStrings.contains(GETAWAY_VARIABLE + ServiceInterpreter.RECEIVER_SEPARATOR));
    }

    /**
     * Check that if the user begins to write a variables (in this test, the
     * "getawayVariable" is used), the proposals are filtered. In this test,
     * only 9 services.
     */
    public void testServiceProposalProviderWithServicesOnVariable() {
        IInterpreterContext interpreterContext = initInterpreterContext(true, true);
        ContentContext context = new ContentContext(ServiceInterpreter.PREFIX + GETAWAY_VARIABLE + ServiceInterpreter.RECEIVER_SEPARATOR, ServiceInterpreter.PREFIX.length() + 16, interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 9 proposals", 9, proposals.size());
        List<String> proposalStrings = new ArrayList<String>();
        proposalStrings.add(proposals.get(0).getProposal());
        proposalStrings.add(proposals.get(1).getProposal());
        proposalStrings.add(proposals.get(2).getProposal());
        proposalStrings.add(proposals.get(3).getProposal());
        proposalStrings.add(proposals.get(4).getProposal());
        proposalStrings.add(proposals.get(5).getProposal());
        proposalStrings.add(proposals.get(6).getProposal());
        proposalStrings.add(proposals.get(7).getProposal());
        proposalStrings.add(proposals.get(8).getProposal());
        assertAllServices(proposalStrings);
    }

    /**
     * Check that if the user begins to write something (in this test, the
     * "getawayVariable.get" prefix is used), the proposals are filtered. In
     * this test, only 2 services.
     */
    public void testServiceProposalProviderWithServicesOnVariableWithPrefix() {
        IInterpreterContext interpreterContext = initInterpreterContext(true, true);
        ContentContext context = new ContentContext(ServiceInterpreter.PREFIX + GETAWAY_VARIABLE + ServiceInterpreter.RECEIVER_SEPARATOR + "get", ServiceInterpreter.PREFIX.length() + 19,
                interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 2 proposals", 2, proposals.size());
        List<String> proposalStrings = new ArrayList<String>();
        proposalStrings.add(proposals.get(0).getProposal());
        proposalStrings.add(proposals.get(1).getProposal());
        assertTrue("There should be a proposal for the service getAllAssociatedElements", proposalStrings.contains("getAllAssociatedElements()"));
        assertTrue("There should be a proposal for the service getRootContainer", proposalStrings.contains("getRootContainer()"));
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        proposalProvider = null;
        super.tearDown();
    }
}
