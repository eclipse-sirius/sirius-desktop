/*******************************************************************************
 * Copyright (c) 2014, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditor;
import org.eclipse.sirius.editor.tools.internal.presentation.CustomSiriusEditor;
import org.eclipse.sirius.tests.support.api.ImageComposer;
import org.eclipse.sirius.tests.support.api.ImageEquality;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.condition.CheckTreeItemEnabled;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test that the empty name validation error appears in the "problems" view.
 * 
 * @author <a href="mailto:jessy.mallet@obeo.fr">Jessy Mallet</a>
 */
public class ValidationEmptyNameTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String ERROR_NODE = "Errors (1 item)";

    private static final String VSM = "emptyName.odesign";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/vsmValidation/emptyNameVariable/";

    private static final String errorMessage = MessageFormat.format(Messages.VSMElementNameValidConstraint_invalidNameErrorMsg, "\"Group > emptyName > diagTest > Default > \"");

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * Test that the empty name validation error appears in the "problems" view
     * when the name of a diagram node is not filled.
     */
    public void testValidationErrorValue() {
        // Opened VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        bot.editorByTitle(VSM).setFocus();
        SWTBotTree tree = odesignEditor.bot().tree();
        SWTBotUtils.clickContextMenu(tree, "Validate");
        bot.button("OK").click();
        checkIconNodeInvalidate(odesignEditor, tree);
        assertTrue("Empty Name error does not appear in the problems view.", checkProblemValue());
    }

    /**
     * Check that the validation generate empty name error in the problem view.
     * 
     * @return Boolean
     */
    private Boolean checkProblemValue() {
        // accesses to problems view
        final SWTBotView problemViewBot = bot.viewByTitle("Problems");
        problemViewBot.setFocus();
        final SWTBotTree problemTree = problemViewBot.bot().tree();
        bot.waitUntil(new CheckTreeItemEnabled(problemTree.getTreeItem(ERROR_NODE)));
        SWTBotTreeItem item = problemTree.getTreeItem(ERROR_NODE).expand();
        for (String itemMessage : item.getNodes()) {
            if (errorMessage.equals(itemMessage)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check that a red cross appears on the icon Node Mapping to show problem
     * validation.
     * 
     * @param odesignEditor
     *            the odesign editor
     * @param tree
     *            the tree validated
     */
    private void checkIconNodeInvalidate(final SWTBotVSMEditor odesignEditor, SWTBotTree tree) {
        final SWTBotTreeItem treeItem = tree.getTreeItem(ODESIGN).getNode("Group").getNode("emptyName").getNode("diagTest").getNode("Default").getNode("Node").select();
        RunnableWithResult<Boolean> runnable = new RunnableWithResult.Impl<Boolean>() {
            @Override
            public void run() {
                treeItem.widget.getDisplay().syncExec(new Runnable() {
                    @Override
                    public void run() {
                        Image treeItemImageDisplay = treeItem.widget.getImage();
                        SiriusEditor treeEditor = (CustomSiriusEditor) odesignEditor.getReference().getEditor(false);
                        ImageComposer imageComposer = new ImageComposer();
                        Image treeItemImageExpected = imageComposer.getImageOfEditPlugin(treeEditor.getAdapterFactory(), (EObject) treeItem.widget.getData());
                        setResult(ImageEquality.areEqualImages(treeItemImageExpected, treeItemImageDisplay));
                    }
                });
            }
        };
        treeItem.widget.getDisplay().syncExec(runnable);
        assertTrue("A red cross must appear on the image of the node mapping.", runnable.getResult().booleanValue());
    }
}
