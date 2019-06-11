/*******************************************************************************
 * Copyright (c) 2017 Obeo
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIDiagramRepresentation;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMHelper;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;

/**
 * This class verifies that feature name of various tool's operations and column mapping have completion.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class FeatureAssistTest extends AbstractContentAssistTest {

    private static final String CHANGE_CONTEXT_NODE = "Change Context aql:self";

    private static final String LABEL_EDIT_NODE = "Label Edit";

    private static final String COLUMN_MAPPING_NODE = "stubLooseFocus";

    private static final String GROUP_NAME = "featureTest";

    private static final String TABLE_REPRESENTATION_NAME = "featureTestTable";

    private static final String TREE_REPRESENTATION_NAME = "featureTestTree";

    private static final String VIEWPOINT_NAME = "featureTest";

    private static final String VSM_FILE = "featureTest.odesign";

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
     * Test feature completion for SetValueFeatureName.
     * 
     */
    public void testFeatureCompletionForSetValueFeatureName() {
        testFeatureCompletion("Set stub", "stubLooseFocusChange", "eAnnotations", new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });
    }

    /**
     * Test feature completion for UnsetFeatureName.
     * 
     */
    public void testFeatureCompletionForUnsetFeatureName() {
        testFeatureCompletion("Unset stub", "stubLooseFocusChange", "eAnnotations", new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });
    }

    /**
     * Test feature completion for MoveElementFeatureName.
     * 
     */
    public void testFeatureCompletionForMoveElementFeatureName() {
        testFeatureCompletion("Move stub", "stubLooseFocusChange", "eAnnotations", new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });
    }

    /**
     * Test feature completion for CreateInstanceReferenceName.
     * 
     */
    public void testFeatureCompletionForCreateInstanceFeatureName() {
        testFeatureCompletion("Create Instance ecore::EPackage", "stubLooseFocusChange", "ePackage",
                new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });
    }

    /**
     * Tests feature completion for CreateInstanceReferenceName. Also tests that if the feature is set, the type name
     * proposal is restricted to consistent types.
     * 
     */
    public void testTypeCompletionWithCreateInstanceFeatureNameSet() {
        testFeatureCompletion(false, "stub", "Create Instance ecore::EPackage", "stubLooseFocusChange", "ePackage",
                new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });

        // If the feature is ePackage, only ecore::EPackage should be proposed.
        testFeatureCompletion(true, "ecore::EPackage", "Create Instance ecore::EPackage", "stubLooseFocusChange", "ecore::EPackage",
                new String[] { TABLE_REPRESENTATION_NAME, COLUMN_MAPPING_NODE, LABEL_EDIT_NODE, CHANGE_CONTEXT_NODE });
    }

    /**
     * Test feature completion for FeatureColumnMappingFeatureName.
     * 
     */
    public void testFeatureCompletionForFeatureColumnMappingFeatureName() {
        testFeatureCompletion(COLUMN_MAPPING_NODE, "stubLooseFocusChange", "eAnnotations", new String[] { TABLE_REPRESENTATION_NAME });
    }

    /**
     * Test feature completion for FeatureChangeListenerFeatureName.
     */
    public void testFeatureCompletionForFeatureChangeListenerFeatureName() {
        testFeatureCompletion("Feature Change Listener stub", "ecore::EPackage", "eAnnotations", new String[] { TREE_REPRESENTATION_NAME, "Drop Tool", "Filter" });
    }

    private void testFeatureCompletion(String operationToSelect, String firstTextToSet, String expectedResult, String[] nodesToExpend) {
        testFeatureCompletion(true, "stub", operationToSelect, firstTextToSet, expectedResult, nodesToExpend);
    }

    private void testFeatureCompletion(boolean closeEditor, String fieldToTest, String operationToSelect, String firstTextToSet, String expectedResult, String[] nodesToExpend) {
        // Open odesign file
        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(VSM_FILE).doubleClick();

        // Select a node mapping
        SWTBotVSMEditor activeEditor = SWTBotVSMHelper.getVSMEditorContainingName(VSM_FILE);
        activeEditor.setFocus();
        activeEditor.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + VSM_FILE).expandNode(GROUP_NAME).expandNode(VIEWPOINT_NAME).expandNode(nodesToExpend)
                .select(operationToSelect);

        try {
            // Change the semantic candidate expression
            SWTBotView propertiesBot = bot.viewByTitle("Properties");
            propertiesBot.setFocus();
            final SWTBotText semanticCandidateExpressionText = propertiesBot.bot().text(fieldToTest);
            semanticCandidateExpressionText.setFocus();
            semanticCandidateExpressionText.setText("");

            // Unfocus the semantic candidate expression (to validate it) and
            // add text to another text area
            final SWTBotText semanticElementText = propertiesBot.bot().text("stubLooseFocus");
            semanticElementText.setFocus();
            semanticElementText.setText(firstTextToSet);

            // Focus back on the semantic candidate expression and modify its
            // content
            semanticCandidateExpressionText.setFocus();
            semanticCandidateExpressionText.setText("");
            // Unfocus the semantic candidate expression (to validate it) and
            // focus it again
            semanticElementText.setFocus();
            semanticCandidateExpressionText.setFocus();

            // Use of content assist
            selectContentAssistProposal(semanticCandidateExpressionText, 0, 0);
            assertEquals("The content of Semantic Candidate Expression after content assist use is not as expected", expectedResult, semanticCandidateExpressionText.getText());

            // If the the editor is kept opened for the next test, we set back the field value:
            if (!closeEditor) {
                semanticElementText.setFocus();
                semanticElementText.setText("stubLooseFocus");
            }

        } finally {
            if (closeEditor) {
                activeEditor.close();
            }
        }
    }
}
