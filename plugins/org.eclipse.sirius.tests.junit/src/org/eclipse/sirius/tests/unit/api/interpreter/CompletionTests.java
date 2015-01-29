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
package org.eclipse.sirius.tests.unit.api.interpreter;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.common.tools.api.contentassist.ContentContext;
import org.eclipse.sirius.common.tools.api.contentassist.ContentProposal;
import org.eclipse.sirius.common.tools.api.contentassist.IProposalProvider;
import org.eclipse.sirius.common.tools.api.interpreter.DefaultInterpreterContextFactory;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterContext;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.diagram.description.tool.NodeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.unit.diagram.modeler.ecore.EcoreModeler;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Tests on editing domain based on entities diagram of ecore modeler.
 * 
 * @author mchauvin
 */
public class CompletionTests extends SiriusDiagramTestCase implements EcoreModeler {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        genericSetUp(TEST_SEMANTIC_MODEL_PATH, MODELER_PATH);
        initViewpoint(DESIGN_VIEWPOINT_NAME);
        interpreter = session.getInterpreter();
        TestsUtil.emptyEventsFromUIThread();
    }

    public void testGetStructuralFeatures() {
        ContentContext context = getContext(null, null, "DDiagram:1.1.0", getContentContextText(), 2);

        assertTrue(interpreter instanceof IProposalProvider);

        Collection<ContentProposal> proposals = ((IProposalProvider) interpreter).getProposals(interpreter, context);
        for (final EStructuralFeature feature : DiagramPackage.eINSTANCE.getDDiagram().getEStructuralFeatures()) {
            assertContains(proposals, feature.getName());
        }
    }

    public void testGetContainerVariableOnToolPrecondition() throws Exception {
        NodeCreationDescription nodeCreationDescription = ToolFactory.eINSTANCE.createNodeCreationDescription();
        EAttribute precondition = ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition();

        ContentContext context = getContext(nodeCreationDescription, precondition, NodeCreationDescription.class.getName(), getContentContextText("c"), 2);

        assertTrue(interpreter instanceof IProposalProvider);

        Collection<ContentProposal> proposals = ((IProposalProvider) interpreter).getProposals(interpreter, context);
        assertContains(proposals, "container");
    }

    private ContentContext getContext(EObject element, EStructuralFeature feature, String domainClass, String text, int cursorPosition) {
        IInterpreterContext interContext = DefaultInterpreterContextFactory.createInterpreterContext(element, true, feature, Collections.singletonList(domainClass),
                Collections.<EPackage> emptyList(), Collections.<String, String> emptyMap(), Collections.<String> emptyList());

        ContentContext context = new ContentContext(text, cursorPosition, interContext);
        return context;
    }

    private String getContentContextText() {
        return getContentContextText("");
    }

    private String getContentContextText(String firstChars) {
        return "<%" + firstChars;
    }

    private void assertContains(Collection<ContentProposal> contentProposals, String name) {
        assertTrue(name + " could not be found", contains(contentProposals, name));
    }

    private boolean contains(Collection<ContentProposal> contentProposals, String name) {
        for (final ContentProposal contentProposal : contentProposals) {
            if (contentProposal.getProposal().equals(name))
                return true;
        }
        return false;
    }
}
