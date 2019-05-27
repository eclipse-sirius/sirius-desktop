/*******************************************************************************
 * Copyright (c) 2016, 2019 Obeo.
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

import org.eclipse.sirius.diagram.description.ConditionalContainerStyleDescription;
import org.eclipse.sirius.diagram.description.ConditionalNodeStyleDescription;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.ui.tools.api.views.modelexplorerview.IModelExplorerView;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotEditor;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.eclipse.ui.PlatformUI;

/**
 * Test Class to check the border nodes authorized sides feature.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class BorderNodeSidePropertySectionTest extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "data/unit/borderNodeSide/";

    private static final String MODEL = "borderNodeSide.ecore";

    private static final String SESSION_FILE = "borderNodeSide.aird";

    private static final String ODESIGN_FILE = "borderNodeSide.odesign";

    private static final String DEFAULT_LAYER_NAME = "Default";

    private SWTBotView propertiesBot;

    private SWTBotEditor odesignEditorBot;

    private SWTBotTreeItem viewpointItemBot;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, ODESIGN_FILE);
    }

    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        super.onSetUpAfterOpeningDesignerPerspective();

        SWTBotView projectExplorer = bot.viewById(IModelExplorerView.ID);
        projectExplorer.setFocus();
        SWTBot projectExplorerBot = projectExplorer.bot();
        projectExplorerBot.tree().expandNode(getProjectName()).expandNode(ODESIGN_FILE).doubleClick();

        odesignEditorBot = bot.activeEditor();
        odesignEditorBot.setFocus();
        viewpointItemBot = odesignEditorBot.bot().tree().expandNode("platform:/resource/" + getProjectName() + "/" + ODESIGN_FILE, true);
        viewpointItemBot.setFocus();
    }

    /**
     * Checks that the side property is visible or not for node with conditional style or not.
     * 
     * @param parentNodeOfTestedNode
     *            the parent node of the node we test the property visibility on.
     * @param testedNode
     *            the node we want to check the side property visibility on.
     * @param shouldBeVisible
     *            true if the side property must be visible on the given tested node.
     * @param conditionalStylePresent
     *            true if the given node should be under a conditional style.
     * @param failMessage
     *            the message in case the test fails.
     */
    private void checkSidePropertyVisibility(final SWTBotTreeItem parentNodeOfTestedNode, final SWTBotTreeItem testedNode, boolean shouldBeVisible, final boolean conditionalStylePresent,
            String failMessage) {
        testedNode.select();
        PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            @Override
            public void run() {
                Object data = parentNodeOfTestedNode.widget.getData();
                if (conditionalStylePresent) {
                    assertTrue(data instanceof ConditionalNodeStyleDescription || data instanceof ConditionalContainerStyleDescription);
                } else {
                    assertFalse(data instanceof ConditionalNodeStyleDescription || data instanceof ConditionalContainerStyleDescription);
                }
            }
        });
        propertiesBot = bot.viewByTitle("Properties");
        propertiesBot.setFocus();
        SWTBotSiriusHelper.selectPropertyTabItem("Advanced", propertiesBot.bot());
        if (shouldBeVisible) {
            try {
                propertiesBot.bot().checkBoxWithLabel("Authorized Sides:");
            } catch (WidgetNotFoundException e) {
                fail(failMessage);
            }
        } else {
            try {
                propertiesBot.bot().checkBoxWithLabel("Authorized Sides:");
                fail(failMessage);
            } catch (WidgetNotFoundException e) {
                // valid case
            }
        }

    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a border node in its parent are visible for a
     * conditional style
     */
    public void testSidePropertyVisibilityOnBorderedNodeWithConditionalStyle() {
        final SWTBotTreeItem westBorderedNodeContditionalStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(1).getNode(0).getNode(1);
        SWTBotTreeItem westBorderedNodeContditionalStyle = westBorderedNodeContditionalStyleParent.getNode(0);
        checkSidePropertyVisibility(westBorderedNodeContditionalStyleParent, westBorderedNodeContditionalStyle, true, true,
                "the checkbox to choose the authorized sides for bordered node style should be available.");
    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a border node in its parent are visible for a no
     * conditional style
     */
    public void testSidePropertyVisibilityOnBorderedNode() {
        final SWTBotTreeItem westBorderedNodeDefaultStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(1).getNode(0);
        SWTBotTreeItem westBorderedNodeDefaultStyle = westBorderedNodeDefaultStyleParent.getNode(0);
        checkSidePropertyVisibility(westBorderedNodeDefaultStyleParent, westBorderedNodeDefaultStyle, true, false,
                "the checkbox to choose the authorized sides for bordered node style should be available.");
    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a bordered node are not visible for a
     * WorkspaceImageImage no conditional style of a container.
     */
    public void testSidePropertyNoVisibilityOnContainerWithConditionalStyle() {
        final SWTBotTreeItem containerWithConditionalStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(1).getNode(6);
        SWTBotTreeItem containerWithConditionalStyle = containerWithConditionalStyleParent.getNode(0);
        checkSidePropertyVisibility(containerWithConditionalStyleParent, containerWithConditionalStyle, false, true,
                "the checkbox to choose the authorized sides for bordered node style should not be available for a container conditional style.");

    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a bordered node are not visible for a
     * WorkspaceImage conditional style of a container.
     */
    public void testSidePropertyNoVisibilityOnContainerWithNoConditionalStyle() {
        final SWTBotTreeItem containerWithNoConditionalStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(1);
        SWTBotTreeItem containerWithNoConditionalStyle = containerWithNoConditionalStyleParent.getNode(5);
        checkSidePropertyVisibility(containerWithNoConditionalStyleParent, containerWithNoConditionalStyle, false, false,
                "the checkbox to choose the authorized sides for bordered node style should not be available for a container style.");
    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a bordered node are not visible for a
     * WorkspaceImage no conditional style of a container.
     */
    public void testSidePropertyNoVisibilityOnSimpleNodeWithConditionalStyle() {
        final SWTBotTreeItem simpleNodeWithConditionalStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(0).getNode(1);
        SWTBotTreeItem simpleNodeWithConditionalStyle = simpleNodeWithConditionalStyleParent.getNode(0);
        checkSidePropertyVisibility(simpleNodeWithConditionalStyleParent, simpleNodeWithConditionalStyle, false, true,
                "the checkbox to choose the authorized sides for bordered node style should not be available for a simple node conditional style.");
    }

    /**
     * Tests that the checkbox used to specify the authorized sides of a bordered node are not visible for a
     * WorkspaceImage conditional style of a container.
     */
    public void testSidePropertyNoVisibilityOnSimpleNodeWithNoConditionalStyle() {
        final SWTBotTreeItem simpleNodeWithNoConditionalStyleParent = viewpointItemBot.getNode(0).getNode(0).getNode(0).getNode(DEFAULT_LAYER_NAME).getNode(0);
        SWTBotTreeItem simpleNodeWithNoConditionalStyle = simpleNodeWithNoConditionalStyleParent.getNode(0);
        checkSidePropertyVisibility(simpleNodeWithNoConditionalStyleParent, simpleNodeWithNoConditionalStyle, false, false,
                "the checkbox to choose the authorized sides for bordered node style should not be available for a simple node style.");
    }

    @Override
    protected void tearDown() throws Exception {
        odesignEditorBot.close();
        odesignEditorBot = null;
        propertiesBot = null;
        viewpointItemBot = null;
        super.tearDown();
    }

}
