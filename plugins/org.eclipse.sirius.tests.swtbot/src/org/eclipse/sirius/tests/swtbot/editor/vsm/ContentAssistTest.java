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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * Test to use the content assist on a text area.
 * 
 * @author smonnier
 */
public class ContentAssistTest extends AbstractContentAssistTest {

    private static final String NODE_NAME = "EPackage";

    private static final String GROUP_NAME = "tc_viewpoint_742";

    private static final String REPRESENTATION_NAME = "tc_viewpoint_742";

    private static final String VIEWPOINT_NAME = "tc_viewpoint_742";

    private static final String VSM_FILE = "tc_viewpoint_742.odesign";

    private static final String DATA_UNIT_DIR = "data/unit/contentAssist/";

    /**
     * Current diagram.
     */
    protected UIDiagramRepresentation diagram;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM_FILE);

    }

    /**
     * Test method for ticket VP-742 about content assist that delete the newly
     * added text after unfocus/refocus.
     * 
     * @throws Exception
     *             Test error.
     */
    public void testContentAssist() throws Exception {
        // Open odesign file
        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();

        // Select a node mapping
        SWTBotVSMEditor activeEditor = SWTBotVSMHelper.getVSMEditorContainingName(VSM_FILE);
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP_NAME).expandNode(VIEWPOINT_NAME).expandNode(REPRESENTATION_NAME)
                .select(NODE_NAME);

        try {
            // Change the semantic candidate expression
            SWTBotView propertiesBot = bot.viewByTitle("Properties");
            propertiesBot.setFocus();
            final SWTBotText semanticCandidateExpressionText = propertiesBot.bot().text("feature:eContents");
            semanticCandidateExpressionText.setFocus();
            semanticCandidateExpressionText.setText("aql:self.aa");
            // Ensure that
            // org.eclipse.sirius.editor.editorPlugin.SiriusEditor.SiriusEditor().new
            // CommandStackListener() {...}.commandStackChanged(EventObject) is
            // called before continue
            SWTBotUtils.waitAllUiEvents();
            // Unfocus the semantic candidate expression (to validate it)
            final SWTBotText semanticElementText = propertiesBot.bot().text("EPackage");
            semanticElementText.setFocus();
            // Ensure that
            // org.eclipse.sirius.editor.editorPlugin.SiriusEditor.SiriusEditor().new
            // CommandStackListener() {...}.commandStackChanged(EventObject) is
            // called before continue
            SWTBotUtils.waitAllUiEvents();

            // Focus back on the semantic candidate expression and modify its
            // content
            semanticCandidateExpressionText.setFocus();
            semanticCandidateExpressionText.setText("aql:");
            // Ensure that
            // org.eclipse.sirius.editor.editorPlugin.SiriusEditor.SiriusEditor().new
            // CommandStackListener() {...}.commandStackChanged(EventObject) is
            // called before continue
            semanticElementText.setFocus();
            SWTBotUtils.waitAllUiEvents();

            semanticCandidateExpressionText.setFocus();
            String initialText = semanticCandidateExpressionText.getText();

            // Use of content assist
            String contentAssistProposalText = selectContentAssistProposal(semanticCandidateExpressionText, 4, 4);
            String expectedCompletion = contentAssistProposalText.split(" String")[0].replaceAll(" ", "");
            assertEquals("The content of Semantic Candidate Expression after content assist use is not as expected", initialText + expectedCompletion, semanticCandidateExpressionText.getText());
        } finally {
            activeEditor.close();
        }
    }
}
