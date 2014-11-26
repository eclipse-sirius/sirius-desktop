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
package org.eclipse.sirius.tests.unit.api.vsm.editor;

import junit.framework.TestCase;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.sirius.diagram.description.DescriptionFactory;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.EdgeCreationDescription;
import org.eclipse.sirius.diagram.description.tool.ToolFactory;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.ui.tools.api.assist.TextContentProposalProvider;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * Standalone test for {@link TextContentProposalProvider}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class TextContentProposalProviderTests extends TestCase {

    /**
     * Test that {@link TextContentProposalProvider} return the right proposal
     * {@link IContentProposal}.
     */
    public void testProposalsWithEmptyEPackageRegistry() {
        // Setup
        DiagramDescription diagramDescription = DescriptionFactory.eINSTANCE.createDiagramDescription();
        ToolSection createToolSection = ToolFactory.eINSTANCE.createToolSection();
        EdgeCreationDescription edgeCreationDescription = ToolFactory.eINSTANCE.createEdgeCreationDescription();
        diagramDescription.setToolSection(createToolSection);
        createToolSection.getOwnedTools().add(edgeCreationDescription);

        // Test
        TextContentProposalProvider textContentProposalProvider = new TextContentProposalProvider();
        textContentProposalProvider.initContext(edgeCreationDescription, ToolPackage.Literals.ABSTRACT_TOOL_DESCRIPTION__PRECONDITION);
        IContentProposal[] proposals = textContentProposalProvider.getProposals("[self.ea/]", 8);

        // Check
        String content = proposals[0].getContent();
        int cursorPosition = proposals[0].getCursorPosition();
        assertEquals("The proposal is wrong", content, "[self.eAllContents()/]");
        assertEquals("The cursor after auto-completion is incorrectly positionned.", cursorPosition, 20);
    }

}
