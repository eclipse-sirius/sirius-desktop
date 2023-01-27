/*******************************************************************************
 * Copyright (c) 2023 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tests.swtbot.layout;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.l10n.DiagramUIActionsMessages;
import org.eclipse.jface.bindings.keys.KeyStroke;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeBeginNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeEndNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DEdgeNameEditPart;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.DNodeEditPart;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.distribute.DistributeAction;
import org.eclipse.sirius.tests.swtbot.Activator;
import org.eclipse.sirius.tests.swtbot.support.api.AbstractSiriusSwtBotGefTestCase;
import org.eclipse.sirius.tests.swtbot.support.api.business.UIResource;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusDiagramEditor;
import org.eclipse.sirius.tests.swtbot.support.api.editor.SWTBotSiriusHelper;
import org.eclipse.sirius.tests.swtbot.support.utils.SWTBotUtils;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotMenu;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarDropDownButton;

/**
 * Test labels alignment and distribution.
 * 
 * @author Laurent Redor
 */
@SuppressWarnings("restriction")
public class EdgeLabelsAlignAndDistributeTests extends AbstractSiriusSwtBotGefTestCase {

    private static final String DATA_UNIT_DIR = "/data/unit/layout/alignAndDistributeEdgeLabels/"; //$NON-NLS-1$

    private static final String SEMANTIC_RESOURCE_FILENAME = "edgeLabelAlignTest.ecore"; //$NON-NLS-1$

    private static final String SESSION_RESOURCE_FILENAME = "edgeLabelAlignTest.aird"; //$NON-NLS-1$

    private static final String MODELER_RESOURCE_FILENAME = "edgeLabelAlignTest.odesign"; //$NON-NLS-1$

    private static final String REPRESENTATION_NAME = "Align3Labels"; //$NON-NLS-1$

    private static final String REPRESENTATION_INSTANCE_NAME = "myDiagram"; //$NON-NLS-1$

    private static final Collection<Integer> ALL_DISTRIBUTE_KIND = Arrays.asList(DistributeAction.GAPS_HORIZONTALLY, DistributeAction.CENTERS_HORIZONTALLY, DistributeAction.GAPS_VERTICALLY,
            DistributeAction.CENTERS_VERTICALLY);

    SWTBotGefEditPart firstEdge_BeginLabel;

    SWTBotGefEditPart firstEdge_CenterLabel;

    SWTBotGefEditPart firstEdge_EndLabel;

    SWTBotGefEditPart secondEdge_BeginLabel;

    SWTBotGefEditPart secondEdge_CenterLabel;

    SWTBotGefEditPart secondEdge_EndLabel;

    public class GetXFunction implements Function<Rectangle, Integer> {
        @Override
        public Integer apply(Rectangle bounds) {
            return bounds.x;
        }
    }

    @Override
    protected void onSetUpBeforeClosingWelcomePage() throws Exception {
        copyFileToTestProject(Activator.PLUGIN_ID, DATA_UNIT_DIR, SEMANTIC_RESOURCE_FILENAME, SESSION_RESOURCE_FILENAME, MODELER_RESOURCE_FILENAME);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onSetUpAfterOpeningDesignerPerspective() throws Exception {
        final UIResource sessionAirdResource = new UIResource(designerProject, "/", SESSION_RESOURCE_FILENAME); //$NON-NLS-1$
        localSession = designerPerspective.openSessionFromFile(sessionAirdResource);

        editor = (SWTBotSiriusDiagramEditor) openRepresentation(localSession.getOpenedSession(), REPRESENTATION_NAME, REPRESENTATION_INSTANCE_NAME, DDiagram.class);

        initEditor();

        firstEdge_BeginLabel = editor.getEditPart("startc03toC01", DEdgeBeginNameEditPart.class); //$NON-NLS-1$
        firstEdge_CenterLabel = editor.getEditPart("c03toC01", DEdgeNameEditPart.class); //$NON-NLS-1$
        firstEdge_EndLabel = editor.getEditPart("endc03toC01", DEdgeEndNameEditPart.class); //$NON-NLS-1$
        secondEdge_BeginLabel = editor.getEditPart("startc02toC01", DEdgeBeginNameEditPart.class); //$NON-NLS-1$
        secondEdge_CenterLabel = editor.getEditPart("c02toC01", DEdgeNameEditPart.class); //$NON-NLS-1$
        secondEdge_EndLabel = editor.getEditPart("endc02toC01", DEdgeEndNameEditPart.class); //$NON-NLS-1$
    }

    private void initEditor() {
        if (editor != null) {
            editor.setSnapToGrid(false);
            editor.setFocus();
        }
    }

    public void testAlignLeftOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignLeft, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).x);
    }

    public void testAlignCenterOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignCenter, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getCenter().x);
    }

    public void testAlignRightOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignRight, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getRight().x);
    }

    public void testAlignTopOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignTop, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).y);
    }

    public void testAlignMiddleOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignMiddle, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getCenter().y);
    }

    public void testAlignBottomOnLabelsOfSameEdge() {
        testAlignActionsOnLabelsOfSameEdge(DiagramUIActionsMessages.AlignBottom, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getBottom().y);
    }

    public void testAlignLeftOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignLeft, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).x);
    }

    public void testAlignCenterOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignCenter, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getCenter().x);
    }

    public void testAlignRightOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignRight, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getRight().x);
    }

    public void testAlignTopOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignTop, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).y);
    }

    public void testAlignMiddleOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignMiddle, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getCenter().y);
    }

    public void testAlignBottomOnLabelsOfSeveralEdges() {
        testAlignActionsOnLabelsOfSeveralEdges(DiagramUIActionsMessages.AlignBottom, swtBotGefEditPart -> editor.getAbsoluteBounds(swtBotGefEditPart).getBottom().y);
    }

    public void testDistributeGapsHorizontallyOnLabelsOfSameEdge() {
        testDistributeActionsOnLabelsOfSameEdge(DistributeAction.getLabel(DistributeAction.GAPS_HORIZONTALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).x - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getRight().x);
    }

    public void testDistributeCentersHorizontallyOnLabelsOfSameEdge() {
        testDistributeActionsOnLabelsOfSameEdge(DistributeAction.getLabel(DistributeAction.CENTERS_HORIZONTALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).getCenter().x - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getCenter().x);
    }

    public void testDistributeGapsVerticallyOnLabelsOfSameEdge() {
        testDistributeActionsOnLabelsOfSameEdge(DistributeAction.getLabel(DistributeAction.GAPS_VERTICALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).y - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getBottom().y);
    }

    public void testDistributeCentersVerticallyOnLabelsOfSameEdge() {
        testDistributeActionsOnLabelsOfSameEdge(DistributeAction.getLabel(DistributeAction.CENTERS_VERTICALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).getCenter().y - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getCenter().y);
    }

    public void testDistributeGapsHorizontallyOnLabelsOfSeveralEdges() {
        testDistributeActionsOnLabelsOfSeveralEdges(DistributeAction.getLabel(DistributeAction.GAPS_HORIZONTALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).x - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getRight().x);
    }

    public void testDistributeCentersHorizontallyOnLabelsOfSeveralEdges() {
        testDistributeActionsOnLabelsOfSeveralEdges(DistributeAction.getLabel(DistributeAction.CENTERS_HORIZONTALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).getCenter().x - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getCenter().x);
    }

    public void testDistributeGapsVerticallyOnLabelsOfSeveralEdges() {
        testDistributeActionsOnLabelsOfSeveralEdges(DistributeAction.getLabel(DistributeAction.GAPS_VERTICALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).y - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getBottom().y);
    }

    public void testDistributeCentersVerticallyOnLabelsOfSeveralEdges() {
        testDistributeActionsOnLabelsOfSeveralEdges(DistributeAction.getLabel(DistributeAction.CENTERS_VERTICALLY, false),
                swtBotGefEditParts -> editor.getAbsoluteBounds(swtBotGefEditParts.get(1)).getCenter().y - editor.getAbsoluteBounds(swtBotGefEditParts.get(0)).getCenter().y);
    }

    /**
     * Check that actions are available in Eclipse menus.</BR>
     */
    public void testActionsFromEclipseMenu() {
         final SWTBotMenu menuDistribute = bot.menu("&Diagram").menu("&Distribute"); //$NON-NLS-1$ //$NON-NLS-2$
         // Distribute actions of Eclipse menu are visible if 3 labels are selected
         testDistributeEclipseMenu(menuDistribute, firstEdge_BeginLabel, firstEdge_CenterLabel, firstEdge_EndLabel);

         // Align actions of Eclipse menu are visible if 2 labels are selected
         testAlignEclipseMenu(firstEdge_BeginLabel, firstEdge_CenterLabel);
    }

    /**
     * Check that actions are available in tabbar.</BR>
     * This method also checks the disablement conditions.
     */
    // TODO: Fix this test : The test is OK when launched alone, but KO with all the tests of this class. The drop-down
    // menu didn't closed/collapse correctly. This is an SWTBot problem (not linked with the feature itself).
    public void _testActionsStatusFromTabbar() {
        // final SWTBotMenu menuDistribute = bot.menu("&Diagram").menu("&Distribute"); //$NON-NLS-1$ //$NON-NLS-2$
        // Distribute actions of tabbar are disabled if only 2 labels are selected
        testDistributeActionsStatusFromTabbar(false, firstEdge_BeginLabel, firstEdge_CenterLabel);
        // Distribute actions of tabbar are disabled if the selection contains a mix of labels and nodes
        SWTBotGefEditPart nodeC01EditPart = editor.getEditPart("C01", DNodeEditPart.class); //$NON-NLS-1$
        testDistributeActionsStatusFromTabbar(false, firstEdge_BeginLabel, firstEdge_CenterLabel, nodeC01EditPart);
        // Other disablement conditions for distribute actions are already checked in {@link
        // org.eclipse.sirius.tests.swtbot.DistributeActionTests}.
        // Distribute actions of tabbar are enabled if 3 labels are selected
        testDistributeActionsStatusFromTabbar(true, firstEdge_BeginLabel, firstEdge_CenterLabel, firstEdge_EndLabel);
        // Align actions of tabbar are disabled if only 1 label is selected
        testAlignActionsStatusFromTabbar(false, firstEdge_BeginLabel);
        // Align actions of tabbar are disabled if the selection contains a mix of labels and nodes
        testAlignActionsStatusFromTabbar(false, firstEdge_BeginLabel, nodeC01EditPart);
        // Align actions of tabbar are enabled if 2 labels are selected
        testAlignActionsStatusFromTabbar(true, firstEdge_BeginLabel, firstEdge_CenterLabel);

    }

    private void testAlignActionsOnLabelsOfSameEdge(String actionLabel, Function<SWTBotGefEditPart, Integer> getLocationFunction) {
        int expectedXLocation = getLocationFunction.apply(firstEdge_CenterLabel);
        editor.select(firstEdge_BeginLabel, firstEdge_CenterLabel);
        editor.clickContextMenu(actionLabel);
        assertEquals("The center label moved. It should not as it is the reference element of the selection, ie the last selected.", expectedXLocation, //$NON-NLS-1$
                getLocationFunction.apply(firstEdge_CenterLabel).intValue());
        assertEquals("The begin label is not at the expected location. It should be aligned to the center label.", expectedXLocation, //$NON-NLS-1$
                getLocationFunction.apply(firstEdge_BeginLabel).intValue());
    }

    private void testAlignActionsOnLabelsOfSeveralEdges(String actionLabel, Function<SWTBotGefEditPart, Integer> getLocationFunction) {
        int expectedXLocation = getLocationFunction.apply(secondEdge_CenterLabel);
        editor.select(firstEdge_BeginLabel, firstEdge_CenterLabel, secondEdge_CenterLabel);
        editor.clickContextMenu(actionLabel);
        assertEquals("The center label, of the second edge, moved. It should not as it is the reference element of the selection, ie the last selected.", expectedXLocation, //$NON-NLS-1$
                getLocationFunction.apply(secondEdge_CenterLabel).intValue());
        assertEquals("The begin label, of the first edge, is not at the expected location. It should be aligned to the center label of the second edge.", expectedXLocation, //$NON-NLS-1$
                getLocationFunction.apply(firstEdge_BeginLabel).intValue());
        assertEquals("The center label, of the first edge, is not at the expected location. It should be aligned to the center label of the second edge.", expectedXLocation, //$NON-NLS-1$
                getLocationFunction.apply(firstEdge_CenterLabel).intValue());
    }

    private void testDistributeActionsOnLabelsOfSameEdge(String actionLabel, Function<List<SWTBotGefEditPart>, Integer> getLocationFunction) {
        editor.select(secondEdge_BeginLabel, secondEdge_CenterLabel, secondEdge_EndLabel);
        editor.clickContextMenu(actionLabel);
        // A delta of 1 pixel is authorized because of the possible rounded value of the gap
        assertEquals("The gap between begin label and center label is not the same than the gap bewteen center label and end label.", //$NON-NLS-1$
                getLocationFunction.apply(Arrays.asList(secondEdge_BeginLabel, secondEdge_CenterLabel)), getLocationFunction.apply(Arrays.asList(secondEdge_CenterLabel, secondEdge_EndLabel)), 1);
    }

    private void testDistributeActionsOnLabelsOfSeveralEdges(String actionLabel, Function<List<SWTBotGefEditPart>, Integer> getLocationFunction) {
        editor.select(secondEdge_BeginLabel, secondEdge_CenterLabel, secondEdge_EndLabel, firstEdge_EndLabel);
        editor.clickContextMenu(actionLabel);
        Integer expected = getLocationFunction.apply(Arrays.asList(secondEdge_EndLabel, firstEdge_EndLabel));
        // A delta of 1 pixel is authorized because of the possible rounded value of the gap
        assertEquals("The gap between begin label and center label of second edge is not the same than the gap bewteen end label of first edge and end label of second edge.", //$NON-NLS-1$
                expected, getLocationFunction.apply(Arrays.asList(secondEdge_BeginLabel, secondEdge_CenterLabel)), 1);
        assertEquals("The gap between center label and end label of second edge is not the same than the gap bewteen end label of first edge and end label of second edge.", //$NON-NLS-1$
                expected, getLocationFunction.apply(Arrays.asList(secondEdge_CenterLabel, secondEdge_EndLabel)), 1);
    }

    /**
     * Ensure that the distribute actions, of the Eclipse menu, are visible for the current selected elements.</BR>
     * The status enabled or not is not checked because of SwtBot problems.
     * 
     * @param menuDistribute
     *            distribute actions menu
     * @param labelEditPartsToDistribute
     *            the label edit parts to distribute
     */
    private void testDistributeEclipseMenu(SWTBotMenu menuDistribute, SWTBotGefEditPart... labelEditPartsToDistribute) {
        editor.select(labelEditPartsToDistribute);
        for (int distributeKind : ALL_DISTRIBUTE_KIND) {
            String labelMenu = DistributeAction.getLabel(distributeKind, false);
            final SWTBotMenu distributeActionMenu = menuDistribute.menu(labelMenu);
            assertTrue("Distribute action \"" + labelMenu + "\" is not visible with current selection.", distributeActionMenu.isVisible()); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Ensure that the align actions, of the Eclipse menu, are visible for the current selected elements.</BR>
     * The status enabled or not is not checked because of SwtBot problems.
     * 
     * @param labelEditPartsToAlign
     *            the label edit parts to align
     */
    private void testAlignEclipseMenu(SWTBotGefEditPart... labelEditPartsToAlign) {
        editor.select(labelEditPartsToAlign);
        // bot.waitUntil(new CheckSelectedCondition(editor, labelEditPartsToAlign));
        SWTBotUtils.waitAllUiEvents();
        // editor.setFocus();
        Collection<String> allAlignActionsLabels = Arrays.asList(DiagramUIActionsMessages.AlignLeftToolbarAction_Tooltip, DiagramUIActionsMessages.AlignCenterToolbarAction_Tooltip,
                DiagramUIActionsMessages.AlignRightToolbarAction_Tooltip, DiagramUIActionsMessages.AlignTopToolbarAction_Tooltip, DiagramUIActionsMessages.AlignMiddleToolbarAction_Tooltip,
                DiagramUIActionsMessages.AlignBottomToolbarAction_Tooltip);

        for (String alignActionLabel : allAlignActionsLabels) {
            SWTBotMenu menuAlign = SWTBotSiriusHelper.menu(editor.bot(), "&Diagram").menu(DiagramUIActionsMessages.AlignActionMenu_AlignDropDownTooltip); //$NON-NLS-1$
            final SWTBotMenu alignActionMenu = menuAlign.menu(alignActionLabel);
            assertTrue("Align action \"" + alignActionLabel + "\" is not visible with current selection.", alignActionMenu.isVisible()); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * Ensure that the distribute actions, of the tabbar, are enabled or disabled for the current selected elements.
     * 
     * @param enabled
     *            The expected status for the actions
     * @param labelEditPartsToAlign
     *            the label edit parts to distribute
     * 
     */
    private void testDistributeActionsStatusFromTabbar(boolean enabled, SWTBotGefEditPart... labelEditPartsToAlign) {
        editor.select(labelEditPartsToAlign);
        SWTBotUtils.waitAllUiEvents();
        for (int distributeKind : ALL_DISTRIBUTE_KIND) {
            String labelMenu = DistributeAction.getLabel(distributeKind, true);
            SWTBotToolbarDropDownButton distributeMenu = editor.bot().toolbarDropDownButtonWithTooltip(Messages.DistributeAction_distributeGapsHorizontallyTooltip);
            try {
                SWTBotMenu menuItem = distributeMenu.menuItem(labelMenu);
                if (enabled) {
                    assertTrue("Distribute action \"" + labelMenu + "\" should be enabled with current selection.", menuItem.isEnabled()); //$NON-NLS-1$ //$NON-NLS-2$
                } else {
                    assertFalse("Distribute action \"" + labelMenu + "\" should be disabled with current selection.", menuItem.isEnabled()); //$NON-NLS-1$ //$NON-NLS-2$
                }
            } finally {
                // The lines bellow have been added to close the drowDown menu
                try {
                    distributeMenu.pressShortcut(KeyStroke.getInstance("ESC")); //$NON-NLS-1$
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                SWTBotUtils.waitAllUiEvents();
            }
        }
    }

    /**
     * Ensure that the align actions, of the tabbar, are enabled or disabled for the current selected elements.
     * 
     * @param enabled
     *            The expected status for the actions
     * @param labelEditPartsToAlign
     *            the label edit parts to align
     */
    private void testAlignActionsStatusFromTabbar(boolean enabled, SWTBotGefEditPart... labelEditPartsToAlign) {
        editor.select(labelEditPartsToAlign);
        SWTBotUtils.waitAllUiEvents();

        Collection<String> allAlignActionsLabels = Arrays.asList(DiagramUIActionsMessages.AlignLeftToolbarAction_Tooltip, DiagramUIActionsMessages.AlignCenterToolbarAction_Tooltip,
                DiagramUIActionsMessages.AlignRightToolbarAction_Tooltip, DiagramUIActionsMessages.AlignTopToolbarAction_Tooltip, DiagramUIActionsMessages.AlignMiddleToolbarAction_Tooltip,
                DiagramUIActionsMessages.AlignBottomToolbarAction_Tooltip);
        for (String alignActionLabel : allAlignActionsLabels) {
            SWTBotToolbarDropDownButton alignMenu = editor.bot().toolbarDropDownButtonWithTooltip(DiagramUIActionsMessages.AlignLeftToolbarAction_Tooltip);
            SWTBotMenu menuItem = alignMenu.menuItem(alignActionLabel);
            if (enabled) {
                assertTrue("Align action \"" + alignActionLabel + "\" should be enabled with current selection.", menuItem.isEnabled()); //$NON-NLS-1$ //$NON-NLS-2$
            } else {
                assertFalse("Align action \"" + alignActionLabel + "\" should be enabled with current selection.", menuItem.isEnabled()); //$NON-NLS-1$ //$NON-NLS-2$
            }
            // The lines bellow have been added to close the drowDown menu
            try {
                alignMenu.pressShortcut(KeyStroke.getInstance("ESC")); //$NON-NLS-1$
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            SWTBotUtils.waitAllUiEvents();
        }

    }
}
