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
package org.eclipse.sirius.tests.swtbot.editor.vsm;

import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotVSMEditor;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Test border size computation expression in VSM.
 * 
 * @author <a href="mailto:belqassim.djafer@obeo.fr">Belqassim Djafer</a>
 */
public class BorderSizeComputationExpressionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String VSM = "borderSizeComputationExpression.odesign";

    private static final String VIEWPOINT_NAME = "borderSizeComputationExpression";

    private static final String ODESIGN = "platform:/resource/DesignerTestProject/" + VSM;

    private static final String DATA_UNIT_DIR = "data/unit/borderSizeComputationExpression/";

    private static final String GROUP = "My";

    private static final String GENERAL = "General";

    private static final String DIAGRAM = "Diagram";

    private static final String DEFAULT = "Default";

    private static final String PROPERTIES = "Properties";

    private static final String BORDER = "Border";

    private static final String DEFAULT_NODE_OR_IMAGE_BORDER_SIZE = "0";

    private static final String DEFAULT_CONTAINER_BORDER_SIZE = "1";

    private SWTBotText labelText;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, VSM);
    }

    /**
     * For each style mapping, Check that the Border Size Computation Expression
     * can not be empty.
     */
    public void testBorderSizeComputationExpression() {
        // Open VSM
        SWTBotVSMEditor odesignEditor = openViewpointSpecificationModel(VSM);
        // expands the tree
        SWTBotTreeItem tree = odesignEditor.bot().tree().expandNode(ODESIGN).expandNode(GROUP).expandNode(VIEWPOINT_NAME).expandNode(DIAGRAM);
        testBorderSizeComputationExpression(tree, "BasicShape", "Basic Shape black square", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Diamond", "Diamond gray", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Dot", "Dot gray", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Ellipse", "Ellipse gray", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Gauge", "Gauge", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Note", "Note yellow", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "SquareGray", "Square gray", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "ImageNode", "Workspace Image", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Gradient", "Gradient white to light_gray", DEFAULT_CONTAINER_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "Parallelogram", "Parallelogram light_gray", DEFAULT_CONTAINER_BORDER_SIZE);
        testBorderSizeComputationExpression(tree, "ImageContainer", "Workspace Image", DEFAULT_NODE_OR_IMAGE_BORDER_SIZE);
    }

    private void testBorderSizeComputationExpression(SWTBotTreeItem tree, String node, String style, String expectedDefaultSize) {
        tree.expandNode(DEFAULT).expandNode(node).expandNode(style).select();
        // set the focus on the Properties view
        bot.viewByTitle(PROPERTIES).setFocus();
        // set the focus on the Border tab
        SWTBotSiriusHelper.selectPropertyTabItem(BORDER);
        // get the label expression
        labelText = bot.viewByTitle(PROPERTIES).bot().text(0);
        // focus on label field
        labelText.setFocus();
        // Set Border Size Computation Expression to empty
        labelText.setText("");
        // set the focus on the General tab
        SWTBotSiriusHelper.selectPropertyTabItem(GENERAL);
        // set the focus on the Border tab
        SWTBotSiriusHelper.selectPropertyTabItem(BORDER);
        assertEquals("Border Size Computation Expression should not be empty for " + style, expectedDefaultSize, labelText.getText());
    }

}
