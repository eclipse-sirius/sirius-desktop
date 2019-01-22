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
package org.eclipse.sirius.tests.unit.common.interpreter.variable;

import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.api.interpreter.VariableType;
import org.eclipse.sirius.common.tools.internal.interpreter.VariableInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.VariableProposalProvider;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

import junit.framework.TestCase;

/**
 * A Test case for the {@link VariableProposalProvider}.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class VariableProposalProviderTests extends TestCase {

    private IInterpreter interpreter;

    private IProposalProvider proposalProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new VariableInterpreter();
        proposalProvider = new VariableProposalProvider();
    }

    public void testVariableProposalProviderNewEmptyExpression() {
        ContentProposal contentProposal = proposalProvider.getNewEmtpyExpression();
        assertNotNull("VariableProposalProvider.getNewEmtpyExpression() should not return null", contentProposal);
        assertEquals("The proposal should be the variable interpreter prefix", VariableInterpreter.PREFIX, contentProposal.getProposal());
    }

    public void testVariableProposalProviderWithoutVariables() {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);

        ContentContext context = new ContentContext(VariableInterpreter.PREFIX, VariableInterpreter.PREFIX.length(), interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be only one proposal", 1, proposals.size());
        ContentProposal contentProposal = proposals.get(0);
        assertEquals("The proposal should be the self variable", VariableInterpreter.SELF_VARIABLE_NAME, contentProposal.getProposal());
    }

    public void testVariableProposalProviderWithVariables() {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        String varExampleName = "varExampleName";
        String varExampleValue = "varExampleValue";
        interpreterContext.getVariables().put(varExampleName, VariableType.fromString(varExampleValue));

        ContentContext context = new ContentContext(VariableInterpreter.PREFIX, VariableInterpreter.PREFIX.length(), interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be only two proposals", 2, proposals.size());
        ContentProposal contentProposal1 = proposals.get(0);
        ContentProposal contentProposal2 = proposals.get(1);
        assertEquals("The proposal should be the self variable", VariableInterpreter.SELF_VARIABLE_NAME, contentProposal1.getProposal());
        assertEquals("The proposal should be the varExampleName variable", varExampleName, contentProposal2.getProposal());
    }

    /**
     * Check that if the user begins to write something (in this test, the 2
     * first character of VariableInterpreter.SELF_VARIABLE_NAME), the proposals
     * are filtered. In this test, only "self variable" should be returned.
     */
    public void testVariableProposalProviderWithVariablesWithPrefix() {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);
        String varExampleName = "varExampleName";
        String varExampleValue = "varExampleValue";
        interpreterContext.getVariables().put(varExampleName, VariableType.fromString(varExampleValue));

        ContentContext context = new ContentContext(VariableInterpreter.PREFIX + VariableInterpreter.SELF_VARIABLE_NAME.substring(0, 2), VariableInterpreter.PREFIX.length() + 2, interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be only two proposals", 1, proposals.size());
        ContentProposal contentProposal1 = proposals.get(0);
        assertEquals("The proposal should be the self variable", VariableInterpreter.SELF_VARIABLE_NAME, contentProposal1.getProposal());
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        proposalProvider = null;
        super.tearDown();
    }
}
