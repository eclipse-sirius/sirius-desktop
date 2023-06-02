/*******************************************************************************
 * Copyright (c) 2021, 2023 THALES GLOBAL SERVICES.
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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.Edge;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.AbstractDEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.util.EditPartQuery;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.finder.widgets.SWTBotView;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.exceptions.WidgetNotFoundException;
import org.eclipse.swtbot.swt.finder.utils.SWTBotPreferences;
import org.eclipse.swtbot.swt.finder.waits.Conditions;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTree;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTreeItem;
import org.junit.Assert;

/**
 * Test class dedicated to the show/hide contextual actions concerning edge labels. In this test this action can be
 * triggered on edge labels, edges themselves and a collection of the two. They are triggered from the diagram
 * contextual action, from the tabbar, from the outline and from the Show/Hide popup.
 *
 * @author <a href="mailto:steve.monnier@obeosoft.ca">Steve Monnier</a>
 */
public class HideRevealEdgeLabelsTest extends AbstractHideRevealDiagramElementsLabelTest {

    private static final String OUTLINE = "Outline"; //$NON-NLS-1$

    private static final String SHOW_HIDE_LABEL_TOOLTIP = "Show/Hide"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME_1LABEL = "577162-1label"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME_1LABEL = "new " + REPRESENTATION_NAME_1LABEL; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME_2LABELS = "577162-2labels"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME_2LABELS = "new " + REPRESENTATION_NAME_2LABELS; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME_3LABELS = "577162-3labels"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME_3LABELS = "new " + REPRESENTATION_NAME_3LABELS; //$NON-NLS-1$

    private static final String NODE_NAMED_C1 = "C1"; //$NON-NLS-1$

    private static final String NODE_NAMED_C2 = "C2"; //$NON-NLS-1$

    private static final String EDGE_NAMED_REFTOC1 = "refToC1"; //$NON-NLS-1$

    private static final String EDGE_NAMED_REFTOC2 = "refToC2"; //$NON-NLS-1$

    private static final String EDGE_REFTOC1_BEGIN_LABEL = "From C2"; //$NON-NLS-1$

    private static final String EDGE_REFTOC2_BEGIN_LABEL = "From C1"; //$NON-NLS-1$

    private static final String EDGE_REFTOC1_END_LABEL = "To C1"; //$NON-NLS-1$

    private static final String MODEL = "577162.ecore"; //$NON-NLS-1$

    private static final String SESSION_FILE = "representations.aird"; //$NON-NLS-1$

    private static final String VSM_FILE = "577162.odesign"; //$NON-NLS-1$

    private static final String DATA_UNIT_DIR = "data/unit/tools/hide-reveal/577162/"; //$NON-NLS-1$

    private static final String SEMANTIC_ROOT_NAME = "577162"; //$NON-NLS-1$

    private EPackage semanticRoot;

    private EClass eClassC1;

    private EClass eClassC2;

    private EReference eReferenceRefToC1;

    private EReference eReferenceRefToC2;

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        // We change the timeOut of SWTBot, as we often search for widget that
        // doesn't exist
        previousPollDelay = System.getProperty(SWTBotPreferences.KEY_DEFAULT_POLL_DELAY);
        System.setProperty(SWTBotPreferences.KEY_DEFAULT_POLL_DELAY, "0"); //$NON-NLS-1$

        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, MODEL, SESSION_FILE, VSM_FILE);
    }

    /**
     * Open only the session. As tests uses 3 different diagrams, they will open the required ones.
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        sessionAirdResource = new UIResource(designerProject, FILE_DIR, SESSION_FILE);
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);
        semanticRoot = (EPackage) localSession.getOpenedSession().getSemanticResources().iterator().next().getContents().get(0);
        eClassC1 = (EClass) semanticRoot.getEClassifier(NODE_NAMED_C1);
        eReferenceRefToC2 = eClassC1.getEReferences().get(0);
        eClassC2 = (EClass) semanticRoot.getEClassifier(NODE_NAMED_C2);
        eReferenceRefToC1 = eClassC2.getEReferences().get(0);
    }

    private void openRepresentation1Label() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_1LABEL, REPRESENTATION_INSTANCE_NAME_1LABEL, DDiagram.class);
    }

    private void openRepresentation2Labels() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_2LABELS, REPRESENTATION_INSTANCE_NAME_2LABELS, DDiagram.class);
    }

    private void openRepresentation3Labels() {
        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME_3LABELS, REPRESENTATION_INSTANCE_NAME_3LABELS, DDiagram.class);
    }

    /**
     * Activates the Show/Hide mode using the tabbar button.
     */
    protected void activateShowHideModeUsingTabbar() {
        SWTBotGefEditPart editPart = editor.getSWTBotGefViewer().mainEditPart();
        editPart.click();
        SWTBotUtils.waitAllUiEvents();
        SWTBotToolbarDropDownButton toolbarDropDownButton = editor.bot().toolbarDropDownButtonWithTooltip(Messages.EditModeAction_Label);
        SWTBotUtils.waitAllUiEvents();
        toolbarDropDownButton.menuItem(Messages.ShowingModeSwitchingAction_label).click();
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithContextMenu_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeWithContextMenu(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithContextMenu_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeWithContextMenu(EDGE_REFTOC1_BEGIN_LABEL);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeWithContextMenu_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeWithContextMenu(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from tabbar action. Tested on a diagram
     * where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithTabbarAction_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeWithTabbarAction(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from tabbar action. Tested on a diagram
     * where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithTabbarAction_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeWithTabbarAction(EDGE_REFTOC1_BEGIN_LABEL);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from tabbar action. Tested on a diagram
     * where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeWithTabbarAction_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeWithTabbarAction(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from outline (treeview mode). Tested on a
     * diagram where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithOutlineView_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from outline (treeview mode). Tested on a
     * diagram where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithOutlineView_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from outline (treeview mode). Tested on a
     * diagram where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeWithOutlineView_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithWizard_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeWithWizard_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeWithWizard_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithWizard_3Labels() {
        openRepresentation3Labels();
        testHideRevealLabelsOnEdgeOneByOneWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithWizard_2Labels() {
        openRepresentation2Labels();
        testHideRevealLabelsOnEdgeOneByOneWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard. Tested on a diagram where edges
     * have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithWizard_1Label() {
        openRepresentation1Label();
        testHideRevealLabelsOnEdgeOneByOneWithWizard(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from "Visibility mode". Tested on a diagram
     * where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from "Visibility mode". Tested on a diagram
     * where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode(EDGE_REFTOC1_BEGIN_LABEL);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from "Visibility mode". Tested on a diagram
     * where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * All edges labels are hidden one by one. Tested on a diagram where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_NAMED_REFTOC1);
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_NAMED_REFTOC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * All edges labels are hidden one by one. Tested on a diagram where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_REFTOC1_BEGIN_LABEL);
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_REFTOC2_BEGIN_LABEL);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * All edges labels are hidden one by one. Tested on a diagram where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_NAMED_REFTOC1);
        testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(EDGE_NAMED_REFTOC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction(EDGE_REFTOC1_BEGIN_LABEL);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction(EDGE_NAMED_REFTOC1);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 3 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView_3Labels() {
        openRepresentation3Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 2 labels.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView_2Labels() {
        openRepresentation2Labels();
        testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     * Tested on a diagram where edges have 1 label.
     */
    public void testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView_1Label() {
        openRepresentation1Label();
        testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView(eReferenceRefToC2);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when the selection contains an edge and a label of a
     * different edge. Tested on a diagram where edges have 3 labels.
     */
    public void testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge_3Labels() {
        openRepresentation3Labels();
        List<String> remainingLabels = new ArrayList<String>();
        remainingLabels.add(EDGE_REFTOC1_BEGIN_LABEL);
        remainingLabels.add(EDGE_REFTOC1_END_LABEL);
        testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge(EDGE_NAMED_REFTOC1, remainingLabels);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when the selection contains an edge and a label of a
     * different edge. Tested on a diagram where edges have 2 labels.
     */
    public void testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge_2Labels() {
        openRepresentation2Labels();
        List<String> remainingLabels = new ArrayList<String>();
        remainingLabels.add(EDGE_REFTOC1_END_LABEL);
        testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge(EDGE_REFTOC1_BEGIN_LABEL, remainingLabels);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when the selection contains an edge and a label of a
     * different edge. Tested on a diagram where edges have 1 label.
     */
    public void testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge_1Labels() {
        openRepresentation1Label();
        List<String> remainingLabels = new ArrayList<String>();
        testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge(EDGE_NAMED_REFTOC1, remainingLabels);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     */
    private void testHideRevealAllLabelsOnEdgeWithContextMenu(String edgeLabel) {
        // Select the edge, hide all labels and check that they are correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(edgeLabel).parent();
        checkAllEdgeLabelsAreVisible(edgeLabel);
        editor.select(editPart);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        checkAllEdgeLabelsAreHidden(edgeLabel);

        // Check that the context menu doesn't propose the user to hide labels anymore
        boolean hideLabelContextMenuActionFound = true;
        try {
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
        } catch (WidgetNotFoundException e) {
            hideLabelContextMenuActionFound = false;
        } finally {
            Assert.assertFalse("The context menu shouldn't allow user to hide labels of " + edgeLabel + " (as it is already hidden)", hideLabelContextMenuActionFound); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // Reveal the labels and check that they are correctly revealed
        editor.clickContextMenu(REVEAL_LABEL_TOOLTIP);
        checkAllEdgeLabelsAreVisible(edgeLabel);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from context menu (right-click on the Edge).
     */
    private void testHideRevealAllLabelsOnEdgeOneByOneWithContextMenu(String edgeLabel) {
        // Select the edge, hide all labels and check that they are correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(edgeLabel).parent();
        checkAllEdgeLabelsAreVisible(edgeLabel);
        for (SWTBotGefEditPart labelEditPart : visibleLabelsEditPart(editPart)) {
            editor.select(labelEditPart);
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
            checkEdgeLabelIsHidden(((AbstractDEdgeNameEditPart) labelEditPart.part()).getLabelText());
        }

        // Check all labels are hidden
        checkAllEdgeLabelsAreHidden(edgeLabel);

        // Check that, once the parent edge is selected, the "Hide label" action is not available
        editor.select(editPart);
        final long oldTimeout = SWTBotPreferences.TIMEOUT;
        try {
            SWTBotPreferences.TIMEOUT = 1000;
            editor.clickContextMenu(HIDE_LABEL_TOOLTIP);
            fail("The hide action should not be available, as all labels are hidden"); //$NON-NLS-1$
        } catch (WidgetNotFoundException e) {
            // OK: it is expected to catch this exception
        } finally {
            SWTBotPreferences.TIMEOUT = oldTimeout;
        }

        // Reveal the labels and check that they are correctly revealed
        editor.clickContextMenu(REVEAL_LABEL_TOOLTIP);
        checkAllEdgeLabelsAreVisible(edgeLabel);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from tabbar (button "Hide Label" in tabbar).
     */
    private void testHideRevealAllLabelsOnEdgeWithTabbarAction(String edgeLabel) {

        // Hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(edgeLabel).parent();

        checkAllEdgeLabelsAreVisible(edgeLabel);
        editor.select(editPart);
        editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();

        checkAllEdgeLabelsAreHidden(edgeLabel);

        // Ensure that the tabbar doesn't propose user to hide label
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
            Assert.assertFalse("The tabbar shouldn't allow user to hide label of " + edgeLabel + " (as it is already hidden)", hideLabelTabbarFound && button.isEnabled()); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // Reveal the labels and check that they are correctly revealed
        editor.bot().toolbarButtonWithTooltip(REVEAL_LABEL_TOOLTIP).click();
        checkAllEdgeLabelsAreVisible(edgeLabel);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from tabbar (button "Hide Label" in tabbar).
     */
    private void testHideRevealAllLabelsOnEdgeOneByOneWithTabbarAction(String edgeLabel) {

        // Hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = editor.getEditPart(edgeLabel).parent();

        checkAllEdgeLabelsAreVisible(edgeLabel);
        for (SWTBotGefEditPart labelEditPart : visibleLabelsEditPart(editPart)) {
            editor.select(labelEditPart);
            editor.bot().toolbarButtonWithTooltip(HIDE_LABEL_TOOLTIP).click();
            checkEdgeLabelIsHidden(((AbstractDEdgeNameEditPart) labelEditPart.part()).getLabelText());
        }
        checkAllEdgeLabelsAreHidden(edgeLabel);

        // Reveal the labels and check that they are correctly revealed
        editor.select(editPart);
        editor.bot().toolbarButtonWithTooltip(REVEAL_LABEL_TOOLTIP).click();
        checkAllEdgeLabelsAreVisible(edgeLabel);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from outline (Treeview mode).
     */
    private void testHideRevealAllLabelsOnEdgeWithOutlineView(EReference eReference) {
        String edgeLabel = eReference.getName();
        String eReferenceTreeLabel = edgeLabel + " : " + eReference.getEType().getName(); //$NON-NLS-1$

        // hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = getSWTBotGefEditPart(eReference);

        checkAllEdgeLabelsAreVisible(editPart);
        editor.select(editPart);

        SWTBotTreeItem outlineTreeRoot = activateOutlineTreeViewMode();
        outlineTreeRoot.getNode(eReferenceTreeLabel).contextMenu(HIDE_LABEL_TOOLTIP).click();

        checkAllEdgeLabelsAreHidden(editPart);

        // Reveal the labels and check that they are correctly revealed
        outlineTreeRoot.getNode(eReferenceTreeLabel).contextMenu(REVEAL_LABEL_TOOLTIP).click();
        checkAllEdgeLabelsAreVisible(editPart);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from outline (Treeview mode).
     */
    private void testHideRevealAllLabelsOnEdgeOneByOneWithOutlineView(EReference eReference) {
        String edgeLabel = eReference.getName();
        String eReferenceTreeLabel = edgeLabel + " : " + eReference.getEType().getName(); //$NON-NLS-1$

        // hide the label and check that it is correctly hidden
        SWTBotGefEditPart editPart = getSWTBotGefEditPart(eReference);
        checkAllEdgeLabelsAreVisible(editPart);

        SWTBotTreeItem outlineTreeRoot = activateOutlineTreeViewMode();
        SWTBotTreeItem referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
        // Hide the labels and check that they are correctly hidden
        for (SWTBotTreeItem edgeLabelTreeItem : referenceTreeItem.getItems()) {
            // Select the item and wait that all Ui Events are done.
            edgeLabelTreeItem.select();
            // Need to sleep: waitAllUiEvents doesn't work here (and already in select method)
            bot.sleep(200);
            // Check it exists on diagram, then hide it from outline and check it is hidden on diagram
            checkEdgeLabelIsVisible(edgeLabelTreeItem.getText().replace(" label", "")); //$NON-NLS-1$ //$NON-NLS-2$
            edgeLabelTreeItem.contextMenu(HIDE_LABEL_TOOLTIP).click();
            checkEdgeLabelIsHidden(edgeLabelTreeItem.getText().replace(" label", "")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        checkAllEdgeLabelsAreHidden(editPart);

        // Reveal the labels and check that they are correctly revealed
        for (SWTBotTreeItem edgeLabelTreeItem : referenceTreeItem.getItems()) {
            // Select the item and wait that all Ui Events are done.
            edgeLabelTreeItem.select();
            SWTBotUtils.waitAllUiEvents();
            // Check it is hidden on diagram, then reveal it from outline and check it is shown on diagram
            checkEdgeLabelIsHidden(edgeLabelTreeItem.getText().replace(" label", "")); //$NON-NLS-1$ //$NON-NLS-2$
            edgeLabelTreeItem.contextMenu(REVEAL_LABEL_TOOLTIP).click();
            checkEdgeLabelIsVisible(edgeLabelTreeItem.getText().replace(" label", "")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        checkAllEdgeLabelsAreVisible(editPart);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard (button Hide/Reveal in tabbar).
     */
    private void testHideRevealAllLabelsOnEdgeWithWizard(EReference eReference) {
        String edgeLabel = eReference.getName();
        String eReferenceTreeLabel = edgeLabel + " : " + eReference.getEType().getName(); //$NON-NLS-1$

        SWTBotGefEditPart editPart = getSWTBotGefEditPart(eReference);
        checkAllEdgeLabelsAreVisible(editPart);

        SWTBotTree outlineTreeRoot = getWizardTree();
        SWTBotTreeItem referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
        // Hide the labels and check that they are correctly hidden
        for (SWTBotTreeItem edgeLabelTreeItem : referenceTreeItem.getItems()) {
            assertTrue("The label " + edgeLabelTreeItem.getText() + " should be checked", edgeLabelTreeItem.isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
            edgeLabelTreeItem.uncheck();
        }
        bot.button("OK").click(); //$NON-NLS-1$

        checkAllEdgeLabelsAreHidden(editPart);


        outlineTreeRoot = getWizardTree();
        referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
        // Reveal the labels and check that they are correctly visible
        for (SWTBotTreeItem edgeLabelTreeItem : referenceTreeItem.getItems()) {
            assertFalse("The label " + edgeLabelTreeItem.getText() + " should not be checked", edgeLabelTreeItem.isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
            edgeLabelTreeItem.check();
        }
        bot.button("OK").click(); //$NON-NLS-1$
        checkAllEdgeLabelsAreVisible(editPart);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard (button Hide/Reveal in tabbar).
     * Here it is called label by label.
     */
    private void testHideRevealLabelsOnEdgeOneByOneWithWizard(EReference eReference) {
        String edgeLabel = eReference.getName();
        String eReferenceTreeLabel = edgeLabel + " : " + eReference.getEType().getName(); //$NON-NLS-1$

        SWTBotGefEditPart editPart = getSWTBotGefEditPart(eReference);
        checkAllEdgeLabelsAreVisible(editPart);

        SWTBotTree outlineTreeRoot = getWizardTree();
        SWTBotTreeItem referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
        int items = referenceTreeItem.getItems().length;
        int position = 0;
        // Hide the labels and check that they are correctly hidden
        while (position < items) {
            SWTBotTreeItem edgeLabelTreeItem = referenceTreeItem.getItems()[position];
            outlineTreeRoot.expandNode(eReferenceTreeLabel);
            String edgeLabelText = edgeLabelTreeItem.getText().replace(" label", ""); //$NON-NLS-1$ //$NON-NLS-2$
            assertTrue("The label " + edgeLabelTreeItem.getText() + " should be checked", edgeLabelTreeItem.isChecked());  //$NON-NLS-1$//$NON-NLS-2$
            edgeLabelTreeItem.uncheck();
            bot.button("OK").click(); //$NON-NLS-1$
            checkEdgeLabelIsHidden(edgeLabelText);
            position++;
            if (position < items) {
                // There are other labels to hide so we need to open the wizard again for the next loop
                outlineTreeRoot = getWizardTree();
                referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
            }
        }

        checkAllEdgeLabelsAreHidden(editPart);

        outlineTreeRoot = getWizardTree();
        referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
        // Reveal the labels and check that they are correctly visible
        position = 0;
        while (position < items) {
            SWTBotTreeItem edgeLabelTreeItem = referenceTreeItem.getItems()[position];
            outlineTreeRoot.expandNode(eReferenceTreeLabel);
            String edgeLabelText = edgeLabelTreeItem.getText().replace(" label", ""); //$NON-NLS-1$ //$NON-NLS-2$
            assertFalse("The label " + edgeLabelTreeItem.getText() + " should not be checked", edgeLabelTreeItem.isChecked()); //$NON-NLS-1$ //$NON-NLS-2$
            edgeLabelTreeItem.check();
            bot.button("OK").click(); //$NON-NLS-1$
            checkEdgeLabelIsVisible(edgeLabelText);
            position++;
            if (position < items) {
                // There are other labels to hide so we need to open the wizard again for the next loop
                outlineTreeRoot = getWizardTree();
                referenceTreeItem = outlineTreeRoot.expandNode(eReferenceTreeLabel);
            }
        }
        checkAllEdgeLabelsAreVisible(editPart);
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly when called from wizard (button Hide/Reveal in tabbar).
     */
    private void testHideRevealAllLabelsOnEdgeOneByOneWithVisibilityMode(String edgeLabel) {
        SWTBotGefEditPart editPart = editor.getEditPart(edgeLabel).parent();
        DEdge dEdge = (DEdge) ((View) editPart.part().getModel()).getElement();
        checkAllEdgeLabelsAreVisible(editPart);
        // Switch to visibility mode and double click on the edge not a label
        activateShowHideModeUsingTabbar();

        // Hide the labels by double clicking on them and check that they are correctly hidden
        for (SWTBotGefEditPart labelEditPart : visibleLabelsEditPart(editPart)) {
            editor.doubleClick(labelEditPart);
            SWTBotUtils.waitAllUiEvents();
            assertTrue("The label " + ((AbstractDEdgeNameEditPart) labelEditPart.part()).getLabelText() + " should be hidden", checkEdgeLabelEditPartHidden(dEdge, labelEditPart)); //$NON-NLS-1$ //$NON-NLS-2$
        }

        // Reveal the labels by double clicking on them and check that they are correctly revealed
        for (SWTBotGefEditPart labelEditPart : visibleLabelsEditPart(editPart)) {
            editor.doubleClick(labelEditPart);
            SWTBotUtils.waitAllUiEvents();
            assertFalse("The label " + ((AbstractDEdgeNameEditPart) labelEditPart.part()).getLabelText() + " should not be hidden", checkEdgeLabelEditPartHidden(dEdge, labelEditPart)); //$NON-NLS-1$ //$NON-NLS-2$
        }
        checkAllEdgeLabelsAreVisible(editPart);
    }

    /**
     * Check that a label is hidden independently from the visibility mode status. Instead of checking the edit part
     * display status, this method check for the HideLabelFilter in the DEdge.
     *
     * @param dEdge
     *            the {@link DEdge} to check
     * @param labelEditPart
     *            the current {@link SWTBotGefEditPart} wrapping the {@link AbstractDEdgeNameEditPart} to check.
     * @return if the label is hidden.
     */
    private boolean checkEdgeLabelEditPartHidden(DEdge dEdge, SWTBotGefEditPart labelEditPart) {
        return new DDiagramElementQuery(dEdge).isLabelHidden(new EditPartQuery((AbstractDEdgeNameEditPart) labelEditPart.part()).getVisualID());
    }

    /**
     * Ensures that the Hide and Reveal Actions works correctly with a selection of an edge and a label of a different
     * edge.<br/>
     * The test select the edge refToC2 and the label (from refToC1) given by the argument edgeLabel. <br/>
     * It will perform the hide label on this selection and check that only the selected labels (from refToC2 and the
     * one from refToC1) are hidden.
     */
    private void testHideRevealWithSelectionOfEdgeAndLabelDifferentEdge(String edgeLabel, List<String> expectedLabelToBeStillDisplayed) {
        // Select the edge refToC2 and the given label of the other edge, hide labels
        SWTBotGefEditPart editPartREFTOC1 = getSWTBotGefEditPart(eReferenceRefToC1);
        SWTBotGefEditPart editPartREFTOC2 = getSWTBotGefEditPart(eReferenceRefToC2);
        SWTBotGefEditPart editPartLabel = editor.getEditPart(edgeLabel);
        checkAllEdgeLabelsAreVisible(editPartREFTOC1);
        checkAllEdgeLabelsAreVisible(editPartREFTOC2);
        editor.select(editPartREFTOC2, editPartLabel);
        editor.clickContextMenu(HIDE_LABEL_TOOLTIP);

        // Check that they are correctly hidden
        checkAllEdgeLabelsAreHidden(editPartREFTOC2);
        checkEdgeLabelIsHidden(edgeLabel);
        for (String displayedLabel : expectedLabelToBeStillDisplayed) {
            checkEdgeLabelIsVisible(displayedLabel);
        }

        // Reveal the labels and check that they are correctly revealed
        editor.select(editPartREFTOC1, editPartREFTOC2);
        editor.clickContextMenu(REVEAL_LABEL_TOOLTIP);
        checkAllEdgeLabelsAreVisible(editPartREFTOC1);
        checkAllEdgeLabelsAreVisible(editPartREFTOC2);
    }

    private SWTBotGefEditPart getSWTBotGefEditPart(EReference eReference) {
        try {
            return editor.getEditPart(eReference.getName()).parent();
        } catch (WidgetNotFoundException e) {
            SWTBotGefEditPart sourceEditPart = editor.getEditPart(((EClass) eReference.eContainer()).getName()).parent();
            SWTBotGefEditPart targetEditPart = editor.getEditPart(eReference.getEType().getName()).parent();
            return editor.getConnectionEditPart(sourceEditPart, targetEditPart).get(0);
        }
    }

    private SWTBotTreeItem activateOutlineTreeViewMode() {
        SWTBotView outlineTitle = bot.viewByTitle(OUTLINE);
        List<SWTBotToolbarButton> toolbarButtons = outlineTitle.getToolbarButtons();
        for (SWTBotToolbarButton swtBotToolbarButton : toolbarButtons) {
            if (swtBotToolbarButton.getToolTipText().equals(OUTLINE)) {
                swtBotToolbarButton.click();
                return outlineTitle.bot().tree().expandNode(SEMANTIC_ROOT_NAME);
            }
        }
        fail("Unable to set the Outline view in tree mode"); //$NON-NLS-1$
        return null;
    }

    private SWTBotTree getWizardTree() {
        editor.bot().toolbarButtonWithTooltip(SHOW_HIDE_LABEL_TOOLTIP).click();
        bot.waitUntilWidgetAppears(Conditions.shellIsActive("Diagram elements visibility")); //$NON-NLS-1$
        return bot.tree();
    }

    /**
     * Ensures that all labels from the edge with the given name are visible.
     *
     * @param label
     *            the label to search
     */
    private void checkAllEdgeLabelsAreVisible(String label) {
        SWTBotGefEditPart parent = editor.getEditPart(label).parent();
        checkAllEdgeLabelsAreVisible(parent);
    }

    /**
     * Ensures that all labels from the edge with the given name are visible.
     *
     * @param label
     *            the label to search
     */
    private void checkAllEdgeLabelsAreVisible(SWTBotGefEditPart parent) {
        for (SWTBotGefEditPart labelSWTBotGefEditPart : parent.children()) {
            EditPart part = labelSWTBotGefEditPart.part();
            if (part instanceof AbstractDEdgeNameEditPart && !((AbstractDEdgeNameEditPart) part).getLabelText().isBlank()) {
                checkEdgeLabelIsVisible(((AbstractDEdgeNameEditPart) part).getLabelText());
            }
        }
    }

    private List<SWTBotGefEditPart> visibleLabelsEditPart(SWTBotGefEditPart parent) {
        // Check in mappings what label should be visible
        DEdge dEdgeSpec = (DEdge) ((Edge) parent.part().getModel()).getElement();
        EdgeMapping actualMapping = (EdgeMapping) dEdgeSpec.getActualMapping();
        List<SWTBotGefEditPart> visibleLabelEditPart = new ArrayList<SWTBotGefEditPart>();
        for (SWTBotGefEditPart labelSWTBotGefEditPart : parent.children()) {
            EditPart part = labelSWTBotGefEditPart.part();
            if (part instanceof DEdgeNameEditPart && actualMapping.getStyle().getCenterLabelStyleDescription() != null) {
                visibleLabelEditPart.add(labelSWTBotGefEditPart);
            } else if (part instanceof DEdgeBeginNameEditPart && actualMapping.getStyle().getBeginLabelStyleDescription() != null) {
                visibleLabelEditPart.add(labelSWTBotGefEditPart);
            } else if (part instanceof DEdgeEndNameEditPart && actualMapping.getStyle().getEndLabelStyleDescription() != null) {
                visibleLabelEditPart.add(labelSWTBotGefEditPart);
            }
        }
        return visibleLabelEditPart;
    }

    /**
     * Ensures that no label from the edge with the given name is visible.
     *
     * @param label
     *            the label to search
     */
    private void checkAllEdgeLabelsAreHidden(String label) {
        SWTBotGefEditPart parent = editor.getEditPart(label).parent();
        checkAllEdgeLabelsAreHidden(parent);
    }

    /**
     * Ensures that no label from the edge with the given name is visible.
     *
     * @param label
     *            the label to search
     */
    private void checkAllEdgeLabelsAreHidden(SWTBotGefEditPart parent) {
        for (SWTBotGefEditPart labelSWTBotGefEditPart : parent.children()) {
            if (labelSWTBotGefEditPart.part() instanceof AbstractDEdgeNameEditPart && !((AbstractDEdgeNameEditPart) labelSWTBotGefEditPart.part()).getLabelText().isEmpty()) {
                AbstractDEdgeNameEditPart abstractDEdgeNameEditPart = (AbstractDEdgeNameEditPart) labelSWTBotGefEditPart.part();
                checkEdgeLabelIsHidden(abstractDEdgeNameEditPart.getLabelText());
            }
        }
    }
}
