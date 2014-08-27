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
package org.eclipse.sirius.tests.swtbot;

import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramBorderNodeEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.tools.api.preferences.SiriusDiagramUiPreferencesKeys;
import org.eclipse.sirius.tests.swtbot.support.api.view.SiriusOutlineView;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.waits.Conditions;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;

/**
 * Tests ensuring that hide/reveal Labels located on the border of a
 * Node/Bordered Node works correctly.
 * 
 * In this Test Scenario, we will have to use the old UI as we are using
 * filters. see {@link HideRevealDiagramElementsLabelsTest}
 * 
 * @author alagarde
 */
public class HideRevealDiagramElementsLabelsTestWithOldUI extends AbstractHideRevealDiagramElementsLabelTest {

    /**
     * Name of the filter to apply in this test.
     */
    private static final String FILTER_NAME = "port collapse";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        changeDiagramUIPreference(SiriusDiagramUiPreferencesKeys.PREF_OLD_UI.name(), true);
        super.onSetUpAfterOpeningDesignerPerspective();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * <ul>
     * <li>Hide a label of a node --> The label disappears</li>
     * <li>Activate a filter that hides the node --> The node disappears</li>
     * <li>Deactivate the filter --> The node appears without the label</li>
     * <li>Reveal the label of the node --> The label appears</li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     */
    public void testHideRevealLabelWithNodeFilering() throws Exception {

        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart nodeEditPart = editor.getEditPart(BORDERED_NODE_WITH_LABEL_NAME, AbstractDiagramBorderNodeEditPart.class);

        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);

        editor.select(nodeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // Step 2 : activate a filter that hides the node
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkNodeIsHidden(nodeEditPart);

        // Step 3 : deactivate the filter
        outlineView.filters().activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // Step 4 : reveal the label
        final SWTBotView view = getAndExpandOutlineView(false);

        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode("p1").expand().getNode(BORDERED_NODE_WITH_LABEL_NAME).click();
        item.contextMenu(REVEAL_LABEL_TOOLTIP).click();

        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);
    }

    /**
     * <ul>
     * <li>Hide a label of a node --> The label disappears</li>
     * <li>Activate a filter that hides the node --> The node disappears</li>
     * <li>Reveal the label of the node --> The label must still be hidden</li>
     * <li>Deactivate the filter --> The node appears with the label</li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     */
    public void testHideRevealLabelWithNodeFilering2() throws Exception {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart nodeEditPart = editor.getEditPart(BORDERED_NODE_WITH_LABEL_NAME, AbstractDiagramBorderNodeEditPart.class);

        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);

        editor.select(nodeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // deselect
        editor.click(0, 0);

        // Step 2 : activate a filter that hides the node
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkNodeIsHidden(nodeEditPart);

        // Step 3 : reveal the label
        editor.setFocus();

        bot.toolbarButtonWithTooltip("Show/Hide").click();
        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Diagram elements visibility"));
        bot.text().setText("*A label");
        bot.tree().getTreeItem("p1").getNode("A").getNode("A label").select().toggleCheck();

        bot.button("OK").click();

        checkNodeIsHidden(nodeEditPart);
        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // Step 4 : deactivate the filter
        outlineView.filters().activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);

    }

    /**
     * <ul>
     * <li>Hide a label of an edge --> The label disappears</li>
     * <li>Activate a filter that hides the edge --> The edge disappears</li>
     * <li>Deactivate the filter --> The edge appears without the label</li>
     * <li>Reveal the label of the edge --> The label appears</li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     */
    public void testHideRevealEdgeLabelWithNodeFilering() throws Exception {

        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart edgeEditPart = editor.getEditPart(EDGE_WITH_LABEL_NAME, AbstractDiagramEdgeEditPart.class);

        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

        editor.select(edgeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : activate a filter that hides the node
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkEdgeIsHidden(edgeEditPart);

        // Step 3 : deactivate the filter
        outlineView.filters().activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 4 : reveal the label
        final SWTBotView view = getAndExpandOutlineView(false);

        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        item.contextMenu(REVEAL_LABEL_TOOLTIP).click();

        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
    }

    /**
     * <ul>
     * <li>Hide a label of a edge --> The label disappears</li>
     * <li>Activate a filter that hides the edge --> The edge disappears</li>
     * <li>Reveal the label of the edge --> The label must still be hidden</li>
     * <li>Deactivate the filter --> The edge appears with the label</li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     */
    public void testHideRevealEdgeLabelWithNodeFilering2() throws Exception {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart edgeEditPart = editor.getEditPart(EDGE_WITH_LABEL_NAME, AbstractDiagramEdgeEditPart.class);

        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

        editor.select(edgeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : activate a filter that hides the node
        final SiriusOutlineView outlineView = designerViews.openOutlineView();
        outlineView.layers();
        SWTBotUtils.waitAllUiEvents();
        outlineView.filters();
        SWTBotUtils.waitAllUiEvents();
        outlineView.activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkEdgeIsHidden(edgeEditPart);

        // Step 3 : reveal the label
        final SWTBotView view = getAndExpandOutlineView(false);

        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        item.contextMenu(REVEAL_LABEL_TOOLTIP).click();

        checkEdgeIsHidden(edgeEditPart);

        // Step 4 : deactivate the filter
        outlineView.filters().activateFilter(FILTER_NAME);
        SWTBotUtils.waitAllUiEvents();
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

    }
}
