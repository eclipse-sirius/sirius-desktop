/*******************************************************************************
 * Copyright (c) 2010, 2017 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.unit.common.interpreter.feature;

import java.util.List;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.common.tools.internal.interpreter.FeatureInterpreter;
import org.eclipse.sirius.common.ui.tools.internal.interpreter.FeatureProposalProvider;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DescriptionPackage;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.tests.unit.common.interpreter.AbstractCompletionTestCase;
import org.eclipse.sirius.tools.internal.interpreter.SiriusInterpreterContextFactory;

/**
 * Tests for the {@link FeatureProposalProvider} .
 * 
 * @author mporhel
 * 
 */
public class FeatureProposalProviderTests extends AbstractCompletionTestCase {

    private IInterpreter interpreter;

    private IProposalProvider proposalProvider;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        interpreter = new FeatureInterpreter();
        proposalProvider = new FeatureProposalProvider();
    }

    public void testFeatureProposalProviderNewEmptyExpression() {
        ContentProposal contentProposal = proposalProvider.getNewEmtpyExpression();
        assertNotNull("FeatureProposalProvider.getNewEmtpyExpression() should not return null", contentProposal);
        assertEquals("The proposal should be the feature interpreter prefix", FeatureInterpreter.PREFIX, contentProposal.getProposal());
    }

    public void testFeatureProposalProvider() {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);

        ContentContext context = new ContentContext("", 0, interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 13 proposals", 8, proposals.size());
        int i = 0;
        ContentProposal contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS.getName() + " feature", EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.ENAMED_ELEMENT__NAME.getName() + " feature", EcorePackage.Literals.ENAMED_ELEMENT__NAME.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__NS_URI.getName() + " feature", EcorePackage.Literals.EPACKAGE__NS_URI.getName(), contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__NS_PREFIX.getName() + " feature", EcorePackage.Literals.EPACKAGE__NS_PREFIX.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__EFACTORY_INSTANCE.getName() + " feature", EcorePackage.Literals.EPACKAGE__EFACTORY_INSTANCE.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__ECLASSIFIERS.getName() + " feature", EcorePackage.Literals.EPACKAGE__ECLASSIFIERS.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i++);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__ESUBPACKAGES.getName() + " feature", EcorePackage.Literals.EPACKAGE__ESUBPACKAGES.getName(),
                contentProposal.getProposal());
        contentProposal = proposals.get(i);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE.getName() + " feature", EcorePackage.Literals.EPACKAGE__ESUPER_PACKAGE.getName(),
                contentProposal.getProposal());
    }

    /**
     * Check that if the user begins to write something (in this test, the "nam"
     * characters), the proposals are filtered. In this test, only "name"
     * feature (EcorePackage.Literals.ENAMED_ELEMENT__NAME.getName()) should be
     * returned.
     */
    public void testFeatureProposalProviderWithPrefix() {
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        diagramDescription.setDomainClass(EcorePackage.eNAME + "." + EcorePackage.Literals.EPACKAGE.getName());
        IInterpreterContext interpreterContext = SiriusInterpreterContextFactory.createInterpreterContext(diagramDescription, DescriptionPackage.Literals.DIAGRAM_DESCRIPTION__PRECONDITION_EXPRESSION);

        ContentContext context = new ContentContext(EcorePackage.Literals.ENAMED_ELEMENT__NAME.getName().substring(0, 3), 3, interpreterContext);
        List<ContentProposal> proposals = proposalProvider.getProposals(interpreter, context);
        assertNotNull("proposals should not be null", proposals);
        assertEquals("There should be 1 proposals", 1, proposals.size());
        ContentProposal contentProposal = proposals.get(0);
        assertEquals("The proposal should be the " + EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS.getName() + " feature", EcorePackage.Literals.ENAMED_ELEMENT__NAME.getName(),
                contentProposal.getProposal());
    }

    @Override
    protected void tearDown() throws Exception {
        interpreter = null;
        proposalProvider = null;
        super.tearDown();
    }
}
