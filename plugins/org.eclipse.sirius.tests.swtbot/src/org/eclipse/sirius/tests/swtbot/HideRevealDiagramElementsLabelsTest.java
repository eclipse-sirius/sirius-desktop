/*******************************************************************************
 * Copyright (c) 2010, 2024 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tests.swtbot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramContainerEditPart;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDiagramEdgeEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusTextEditPart;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckTreeItemFontFormat;
import org.eclipse.sirius.tests.swtbot.support.api.condition.OperationDoneCondition;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemExpanded;
import org.eclipse.sirius.tests.swtbot.support.api.condition.TreeItemSelected;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.sirius.viewpoint.FontFormat;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.ICondition;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * Tests ensuring that hide/reveal Labels located on the border of a
 * Node/Bordered Node works correctly.
 * 
 * The hide/reveal action will be tested :
 * <ul>
 * <li>From Outline, Context menu and Tabbar</li>
 * <li>By selecting the Label, the Node that contains it or the Bordered Node
 * that Contains it.</li>
 * <li>Reveal actions will only be called from Outline, please see
 * DiagramElementSelectionDialogWithLabelInBorderTest for the test of the
 * Show/Hide wizard on labels.
 * </ul>
 * 
 * @author alagarde
 */
public class HideRevealDiagramElementsLabelsTest extends AbstractHideRevealDiagramElementsLabelTest {

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (right-click on node).
     */
    public void testHideLabelWithTabbarOnNode() {

        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME).parent();

        checkLabelIsVisible(NODE_WITH_LABEL_NAME);
        editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the tabbar doesn't propose user to hide label
        // anymore
        boolean hideLabelTabbarFound = true;
        editor.select(editPart);

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarButton button = null;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            button = editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelTabbarFound = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The tabbar shouldn't allow user to hide label of " + NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelTabbarFound && button.isEnabled());
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (button in tabbar with edge and optional invalid element
     * selected).
     */
    private void testHideLabelWithTabbarOnEdge(boolean selectInvalidElement) {

        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(EDGE_WITH_LABEL_NAME).parent();

        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
        if (selectInvalidElement) {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart, getInvalidElement())));
        } else {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        }
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : ensure that the tabbar doesn't propose user to hide label
        // anymore
        boolean hideLabelTabbarFound = true;
        editor.select(editPart);

        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarButton button = null;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            button = editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelTabbarFound = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The tabbar shouldn't allow user to hide label of " + EDGE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelTabbarFound && button.isEnabled());
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (button in tabbar with edge).
     */
    public void testHideLabelWithTabbarOnEdge() {
        testHideLabelWithTabbarOnEdge(false);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (button in tabbar with edge and an invalid element selected).
     */
    public void testHideLabelWithTabbarOnEdgeWithInvalidSelection() {
        testHideLabelWithTabbarOnEdge(true);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (right-click on a bordered node).
     */
    public void testHideLabelWithTabbarOnBorderedNode() {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(BORDERED_NODE_WITH_LABEL_NAME).parent();

        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);
        editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the tabbar doesn't propose user to hide label
        // anymore
        boolean hideLabelTabbarFound = true;
        editor.select(editPart);
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarButton button = null;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            button = editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelTabbarFound = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The tabbar shouldn't allow user to hide label of " + BORDERED_NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelTabbarFound && button.isEnabled());
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * tabbar (right-click on label).
     */
    public void testHideLabelWithTabbarOnLabel() {

        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart parentEditPart = editPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);
        editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the tabbar doesn't propose user to hide label
        // anymore
        boolean hideLabelTabbarFound = true;
        editor.select(parentEditPart);
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarButton button = null;
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            button = editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelTabbarFound = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The tabbar shouldn't allow user to hide label of " + NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelTabbarFound && button.isEnabled());
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Node).
     */
    private void testHideLabelWithContextMenuOnNode(boolean selectInvalidElement) {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME).parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);
        if (selectInvalidElement) {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart, getInvalidElement())));
        } else {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        }
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the context menu doesn't propose user to hide
        // label anymore
        boolean hideLabelContextMenuActionFound = true;
        editor.select(editPart);
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelContextMenuActionFound = false;
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelContextMenuActionFound);
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Node).
     */
    public void testHideLabelWithContextMenuOnNode() {
        testHideLabelWithContextMenuOnNode(false);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Node and an invalid element).
     */
    public void testHideLabelWithContextMenuOnNodeWithInvalidSelection() {
        testHideLabelWithContextMenuOnNode(true);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Edge).
     */
    private void testHideLabelWithContextMenuOnEdge(boolean selectInvalidElement) {
        // Step 0 : Create list of elements to select
        SWTBotGefEditPart editPart = editor.getEditPart(EDGE_WITH_LABEL_NAME).parent();
        HashSet<SWTBotGefEditPart> elementsToSelect;
        if (selectInvalidElement) {
            elementsToSelect = new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart, getInvalidElement()));
        } else {
            elementsToSelect = new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart));
        }
        // Step 1 : hide the label and check that it is correctly hidden
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
        editor.select(elementsToSelect);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : ensure that the context menu doesn't propose user to hide
        // label anymore
        boolean hideLabelContextMenuActionFound = true;
        editor.select(elementsToSelect);
        try {
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelContextMenuActionFound = false;
        } finally {
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + EDGE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelContextMenuActionFound);
        }

        // Step 3 : reveal the label and check that it is correctly reveal
        editor.clickContextMenu(REVEAL_LABEL_TOOLTIP);

        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Edge).
     */
    public void testHideLabelWithContextMenuOnEdge() {
        testHideLabelWithContextMenuOnEdge(false);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Edge and an invalid element).
     */
    public void testHideLabelWithContextMenuOnEdgeWithInvalidSelection() {
        testHideLabelWithContextMenuOnEdge(true);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Bordered Node).
     */
    private void testHideLabelWithContextMenuOnBorderedNode(boolean selectInvalidElement) {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(BORDERED_NODE_WITH_LABEL_NAME).parent();
        checkLabelIsVisible(BORDERED_NODE_WITH_LABEL_NAME);
        if (selectInvalidElement) {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart, getInvalidElement(), editor.getEditPart("Text", SiriusTextEditPart.class), editor.getEditPart("Note", SiriusNoteEditPart.class))));
        } else {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        }
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        checkLabelIsHidden(BORDERED_NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the context menu doesn't propose user to hide
        // label anymore
        boolean hideLabelContextMenuActionFound = true;
        editor.select(editPart);
        try {
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelContextMenuActionFound = false;
        } finally {
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + BORDERED_NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelContextMenuActionFound);
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Bordered Node).
     */
    public void testHideLabelWithContextMenuOnBorderedNode() {
        testHideLabelWithContextMenuOnBorderedNode(false);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the Bordered Node and an invalid element).
     */
    public void testHideLabelWithContextMenuOnBorderedNodeWithInvalidSelection() {
        testHideLabelWithContextMenuOnBorderedNode(true);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the label).
     */
    private void testHideLabelWithContextMenuOnLabel(boolean selectInvalidElement) {
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart parentEditPart = editPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);
        if (selectInvalidElement) {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart, getInvalidElement())));
        } else {
            editor.select(new HashSet<SWTBotGefEditPart>(Arrays.asList(editPart)));
        }
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : ensure that the context menu doesn't propose user to hide
        // label anymore
        boolean hideLabelContextMenuActionFound = true;
        editor.select(parentEditPart);
        try {
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelContextMenuActionFound = false;
        } finally {
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + NODE_WITH_LABEL_NAME + " (as it is already hidden)", hideLabelContextMenuActionFound);
        }
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the label).
     */
    public void testHideLabelWithContextMenuOnLabel() {
        testHideLabelWithContextMenuOnLabel(false);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * context menu (right-click on the label and an invalid element).
     */
    public void testHideLabelWithContextMenuOnLabelWithInvalidSelection() {
        testHideLabelWithContextMenuOnLabel(true);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * the outline on Node.
     * 
     * @throws Exception
     *             if a recursive exception occurs
     */
    public void testHideAndRevealLabelWithOutlineOnNode() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            // Can cause timeouts.
            return;
        }
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart parentEditPart = editPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);

        final SWTBotView view = getAndExpandOutlineView(true);

        // Step 1.2 : open the context menu the element to hide and select the
        // hide element action
        SWTBotTreeItem labelItem = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).getNode(NODE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).getNode(NODE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(labelItem));
        setShownImage(labelItem);
        SWTBotTreeItem nodeItem = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(nodeItem));

        checkOutlineIsCorrectlyDecorated(labelItem, false);
        nodeItem.contextMenu(HIDE_LABEL_TOOLTIP).click();

        // Step 1.3 : ensure node is hidden
        checkLabelIsHidden(NODE_WITH_LABEL_NAME);
        checkOutlineIsCorrectlyDecorated(labelItem, true);

        // Step 2 : ensure that the outline doesn't propose user to hide
        // label anymore
        boolean outlineAllowToHideLabel = true;
        boolean outlineAllowToRevealLabel = false;

        editor.select(parentEditPart);
        SWTBotUtils.waitAllUiEvents();
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            nodeItem.click().contextMenu(HIDE_LABEL_TOOLTIP).click();
        } catch (RuntimeException e) {
            outlineAllowToHideLabel = false;
            try {
                nodeItem.click().contextMenu(REVEAL_LABEL_TOOLTIP).click();
                outlineAllowToRevealLabel = true;
            } catch (WidgetNotFoundException wnfe) {
                outlineAllowToRevealLabel = false;
            }
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + editPart.part(), outlineAllowToHideLabel);
            Assert.assertTrue("The context menu should allow user to show label of " + editPart.part(), outlineAllowToRevealLabel);

            // Step 4 : we ensure that label has been correctly revealed
            checkLabelIsVisible(NODE_WITH_LABEL_NAME);
            checkOutlineIsCorrectlyDecorated(labelItem, false);
        }

    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * the outline on Edge.
     * 
     * @throws Exception
     *             if a recursive exception occurs
     */
    public void testHideAndRevealLabelWithOutlineOnEdge() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            // Causes timeout.
            return;
        }
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(EDGE_WITH_LABEL_NAME);
        SWTBotGefEditPart parentEditPart = editPart.parent();
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

        final SWTBotView view = getAndExpandOutlineView(true);

        // Step 1.2 : open the context menu the element to hide and select the
        // hide element action
        SWTBotTreeItem labelItem = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").getNode(EDGE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").getNode(EDGE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(labelItem));
        setShownImage(labelItem);
        SWTBotTreeItem nodeItem = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(nodeItem));
        checkOutlineIsCorrectlyDecorated(labelItem, false);
        nodeItem.contextMenu(HIDE_LABEL_TOOLTIP).click();

        // Step 1.3 : ensure node is hidden
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);
        checkOutlineIsCorrectlyDecorated(labelItem, true);

        // Step 2 : ensure that the outline doesn't propose user to hide
        // label anymore
        boolean outlineAllowToHideLabel = true;
        boolean outlineAllowToRevealLabel = false;

        editor.select(parentEditPart);
        SWTBotUtils.waitAllUiEvents();
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            nodeItem.click().contextMenu(HIDE_LABEL_TOOLTIP).click();
        } catch (RuntimeException e) {
            outlineAllowToHideLabel = false;
            try {
                nodeItem.click().contextMenu(REVEAL_LABEL_TOOLTIP).click();
                outlineAllowToRevealLabel = true;
            } catch (WidgetNotFoundException wnfe) {
                outlineAllowToRevealLabel = false;
            }
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + editPart.part(), outlineAllowToHideLabel);
            Assert.assertTrue("The context menu should allow user to show label of " + editPart.part(), outlineAllowToRevealLabel);

            // Step 4 : we ensure that label has been correctly revealed
            checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
            checkOutlineIsCorrectlyDecorated(labelItem, false);
        }

    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from
     * the outline on a Label.
     * 
     * @throws Exception
     *             if a recursive exception occurs
     */
    public void testHideAndRevealLabelWithOutlineOnLabel() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        // Step 1 : hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart parentEditPart = editPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);

        // Step 1.1 : open the outline view and get its SWTBotView
        final SWTBotView view = getAndExpandOutlineView(true);

        // Step 1.2 : open the context menu the element to hide and select the
        // hide element action
        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).getNode(NODE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).getNode(NODE_WITH_LABEL_NAME + " " + LABEL_SUFFIX_IN_OUTLINE).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        setShownImage(item);
        checkOutlineIsCorrectlyDecorated(item, false);
        item.contextMenu(HIDE_LABEL_TOOLTIP).click();

        // Step 1.3 : ensure node is hidden
        checkLabelIsHidden(NODE_WITH_LABEL_NAME);
        checkOutlineIsCorrectlyDecorated(item, true);

        // Step 3 : ensure that the outline doesn't propose user to hide
        // label anymore
        boolean outlineAllowToHideLabel = true;
        boolean outlineAllowToRevealLabel = false;

        editor.select(parentEditPart);
        SWTBotUtils.waitAllUiEvents();
        try {
            item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME + LABEL_SUFFIX_IN_OUTLINE).click();
            view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME + LABEL_SUFFIX_IN_OUTLINE).select();
            SWTBotUtils.waitAllUiEvents();
            bot.waitUntil(new TreeItemSelected(item));
            item.contextMenu(HIDE_LABEL_TOOLTIP).click();
        } catch (WidgetNotFoundException e) {
            outlineAllowToHideLabel = false;
            try {
                item.contextMenu(REVEAL_LABEL_TOOLTIP).click();
                outlineAllowToRevealLabel = true;
            } catch (WidgetNotFoundException wnfe) {
                outlineAllowToRevealLabel = false;
            }
        } finally {
            Assert.assertFalse("The context menu shouldn't allow user to hide label of " + editPart.part(), outlineAllowToHideLabel);
            Assert.assertTrue("The context menu should allow user to show label of " + editPart.part(), outlineAllowToRevealLabel);

            // Step 4 : we ensure that label has been correctly revealed
            checkLabelIsVisible(NODE_WITH_LABEL_NAME);
            checkOutlineIsCorrectlyDecorated(item, false);
        }

    }

    /**
     * 
     * <ul>
     * <li>Hide a label of a node --> The label disappears</li>
     * <li>Hide the node --> The node disappears</li>
     * <li>Reveal the node --> The node appears without the label</li>
     * <li>Reveal the label of the node --> The label appears</li>
     * <li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     * 
     */
    public void testRevealEdgeAfterHideLabelWithOutline() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        // Step 1 : hide the label (using Context menu) and check that it is
        // correctly hidden
        SWTBotGefEditPart labelEditPart = editor.getEditPart(EDGE_WITH_LABEL_NAME);
        SWTBotGefEditPart nodeEditPart = labelEditPart.parent();
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

        editor.select(nodeEditPart);
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        ICondition done = new OperationDoneCondition();
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();
        bot.waitUntil(done);

        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : now we hide the node (using the context menu)
        editor.select(nodeEditPart);
        done = new OperationDoneCondition();
        editor.clickContextMenu(HIDE_ELEMENT_TOOLTIP);
        bot.waitUntil(done);
        checkEdgeIsHidden(nodeEditPart);

        // Step 3 : reveal the node (using outline)
        // The label must still be hidden
        // Step 3.1 : open the outline view and get its SWTBotView
        final SWTBotView view = getAndExpandOutlineView(false);

        // Step 3.2 : open the context menu the element to reveal and select the
        // reveal element action
        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        SWTBotUtils.clickContextMenu(item, REVEAL_ELEMENT_TOOLTIP);
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 4 : reveal the label (using outline)
        item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        done = new OperationDoneCondition();
        SWTBotUtils.clickContextMenu(item, REVEAL_LABEL_TOOLTIP);
        bot.waitUntil(done);
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
    }

    /**
     * 
     * <ul>
     * <li>Hide a label of a node --> The label disappears</li>
     * <li>Hide the node --> The node disappears</li>
     * <li>Reveal the node --> The node appears without the label</li>
     * <li>Reveal the label of the node --> The label appears</li>
     * <li>
     * </ul>
     * 
     * @throws Exception
     *             if a reflective exception occurred
     * 
     */
    public void testRevealNodeAfterHideLabelWithOutline() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            /*
             * org.eclipse.swtbot.swt.finder.widgets.TimeoutException: Timeout
             * after: 10000 ms.: tree item with text myEnum is not selected at
             * org
             * .eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil(SWTBotFactory
             * .java:407) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory.waitUntil
             * (SWTBotFactory.java:381) at
             * org.eclipse.swtbot.swt.finder.SWTBotFactory
             * .waitUntil(SWTBotFactory.java:369) at
             * org.eclipse.sirius.tests.swtbot
             * .HideRevealDiagramElementsLabelsTest
             * .testRevealNodeAfterHideLabelWithOutline
             * (HideRevealDiagramElementsLabelsTest.java:569)
             */
            return;
        }
        // Step 1 : hide the label (using Context menu) and check that it is
        // correctly hidden
        SWTBotGefEditPart labelEditPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart nodeEditPart = labelEditPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);

        editor.select(nodeEditPart);
        // Wait all UI events to ensure that the tabbar is correctly refreshed.
        SWTBotUtils.waitAllUiEvents();
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : now we hide the node (using the context menu)
        editor.select(nodeEditPart);
        editor.clickContextMenu(HIDE_ELEMENT_TOOLTIP);
        checkNodeIsHidden(nodeEditPart);

        // Step 3 : reveal the node (using outline)
        // The label must still be hidden
        // Step 3.1 : open the outline view and get its SWTBotView
        final SWTBotView view = getAndExpandOutlineView(false);

        // Step 3.2 : open the context menu the element to reveal and select the
        // reveal element action
        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        item.contextMenu(REVEAL_ELEMENT_TOOLTIP).click();
        checkLabelIsHidden(NODE_WITH_LABEL_NAME);
        checkNodeIsVisible(nodeEditPart);
        bot.waitUntil(new CheckTreeItemFontFormat(item, new ArrayList<FontFormat>()));

        // Step 4 : reveal the label (using outline)
        item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).select();
        SWTBotUtils.waitAllUiEvents();
        TreeItemExpanded treeItemExpanded = new TreeItemExpanded(item, NODE_WITH_LABEL_NAME);
        item.expand();
        bot.waitUntil(treeItemExpanded);

        // Validates that the FontFormat of the TreeItem changes when the
        // visibility changes
        List<FontFormat> widgetFormat = SWTBotUtils.getWidgetFormat(item.getNode(NODE_WITH_LABEL_NAME + " label").widget);
        assertEquals(FontFormat.ITALIC, widgetFormat.get(0).getValue());
        SWTBotUtils.clickContextMenu(item, REVEAL_LABEL_TOOLTIP);

        bot.waitUntil(new CheckTreeItemFontFormat(item, new ArrayList<FontFormat>()));

        checkLabelIsVisible(NODE_WITH_LABEL_NAME);
    }

    /**
     * 
     * <ul>
     * <li>Hide a label of a node --> The label disappears</li>
     * <li>Hide the node --> The node disappears</li>
     * <li>Reveal the label of the node --> The node is still hidden</li>
     * <li>Reveal the node --> The node appears with the label</li>
     * 
     * <li>
     * </ul>
     * * @throws Exception if a reflective exception occurred
     */
    public void testRevealNodeAfterHideAndRevealLabelWithOutline() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }
        // Step 1 : hide the label (using Outline) and check that it is
        // correctly hidden
        SWTBotGefEditPart labelEditPart = editor.getEditPart(NODE_WITH_LABEL_NAME);
        SWTBotGefEditPart nodeEditPart = labelEditPart.parent();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);

        editor.select(nodeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkLabelIsHidden(NODE_WITH_LABEL_NAME);

        // Step 2 : now he hide the node (using the context menu)
        editor.select(nodeEditPart);
        editor.clickContextMenu(HIDE_ELEMENT_TOOLTIP);

        checkNodeIsHidden(nodeEditPart);

        // Step 3 : reveal the label (using outline)
        // Node must still be hidden
        // Step 3.1 : open the outline view and get its SWTBotView
        final SWTBotView view = getAndExpandOutlineView(true);

        // Step 3.2 : open the context menu the label to reveal and select the
        // reveal label action
        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        item.contextMenu(REVEAL_LABEL_TOOLTIP).click();
        checkNodeIsHidden(nodeEditPart);

        // Step 4 : reveal the node (using outline)
        // The label must now be visible
        item = view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).click();
        view.bot().tree().getTreeItem("p").getNode(NODE_WITH_LABEL_NAME).select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        item.contextMenu(REVEAL_ELEMENT_TOOLTIP).click();
        checkLabelIsVisible(NODE_WITH_LABEL_NAME);

    }

    /**
     * 
     * <ul>
     * <li>Hide a label of an edge --> The label disappears</li>
     * <li>Hide the edge --> The edge disappears</li>
     * <li>Reveal the label of the edge --> The edge is still hidden</li>
     * <li>Reveal the edge --> The edge appears with the label</li>
     * 
     * <li>
     * </ul>
     * * @throws Exception if a reflective exception occurred
     */
    public void testRevealEdgeAfterHideAndRevealLabelWithOutline() throws Exception {
        if (TestsUtil.shouldSkipUnreliableTests()) {
            return;
        }

        // Step 1 : hide the label (using the context menu) and check that it is
        // correctly hidden
        SWTBotGefEditPart edgeEditPart = editor.getEditPart(EDGE_WITH_LABEL_NAME, AbstractDiagramEdgeEditPart.class);
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);

        editor.select(edgeEditPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        checkEdgeLabelIsHidden(EDGE_WITH_LABEL_NAME);

        // Step 2 : now we hide the edge (using the context menu)
        editor.select(edgeEditPart);
        editor.clickContextMenu(HIDE_ELEMENT_TOOLTIP);

        checkEdgeIsHidden(edgeEditPart);

        // Step 3 : reveal the label (using outline)
        // Edge must still be hidden
        // Step 3.1 : open the outline view and get its SWTBotView
        final SWTBotView view = getAndExpandOutlineView(true);

        // Step 3.2 : open the context menu the label to reveal and select the
        // reveal label action
        SWTBotTreeItem item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        item.contextMenu(REVEAL_LABEL_TOOLTIP).click();
        checkEdgeIsHidden(edgeEditPart);

        // Step 4 : reveal the node (using outline)
        // The label must now be visible
        item = view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").click();
        view.bot().tree().getTreeItem("p").getNode(EDGE_WITH_LABEL_NAME + " : B").select();
        SWTBotUtils.waitAllUiEvents();
        bot.waitUntil(new TreeItemSelected(item));
        item.contextMenu(REVEAL_ELEMENT_TOOLTIP).click();
        checkEdgeLabelIsVisible(EDGE_WITH_LABEL_NAME);
    }

    private SWTBotGefEditPart getInvalidElement() {
        // Get the p1 package
        SWTBotGefEditPart p1EditPart = editor.getEditPart("p1", AbstractDiagramContainerEditPart.class);
        // Get the last border node (that is the one without label).
        SWTBotGefEditPart borderNodeWithoutLabel = p1EditPart.children().get(p1EditPart.children().size() - 1);
        return borderNodeWithoutLabel;
    }
}
