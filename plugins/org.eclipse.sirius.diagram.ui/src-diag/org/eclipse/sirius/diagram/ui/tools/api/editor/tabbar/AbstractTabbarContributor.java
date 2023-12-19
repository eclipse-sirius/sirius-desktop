/*******************************************************************************
 * Copyright (c) 2015, 2024 Obeo.
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
package org.eclipse.sirius.diagram.ui.tools.api.editor.tabbar;

import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.TabbarContributionFactory;

/**
 * Abstract implementation of {@link ITabbarContributor}. Inherit from this class allows creating one or several
 * contribution items of the default Sirius tabbar.
 * 
 * @author Florian Barbin
 *
 */
public abstract class AbstractTabbarContributor implements ITabbarContributor {

    private TabbarContributionFactory contributorFactory = new TabbarContributionFactory();

    /**
     * Creates the Automatic Layout contribution item. Organized in a drop-down menu, this item is used to trigger an
     * automatic layout of the elements on the diagram.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createArrangeMenuManager(IDiagramWorkbenchPart part) {
        return contributorFactory.createArrangeMenuManager(part);
    }

    /**
     * Creates the Select menu manager contribution item. Organized in a drop-down menu, can be used to select groups of
     * diagram elements in a single operation.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createSelectMenuManager() {
        return contributorFactory.createSelectMenuManager();
    }

    /**
     * Creates the Alignment contribution item. This menu contains several operations which can be used to align several
     * graphical elements in various ways.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createAlignMenuManager() {
        return contributorFactory.createAlignMenuManager();
    }

    /**
     * Creates the Layer Selection contribution item.
     * 
     * @param part
     *            the diagram workbench part.
     * @param manager
     *            the toolbar manager.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createLayerContribution(IDiagramWorkbenchPart part, ToolBarManager manager) {
        return contributorFactory.createLayerContribution(part, manager);
    }

    /**
     * Creates the Filter Selection contribution item.
     * 
     * @param part
     *            the diagram workbench part.
     * @param manager
     *            the toolbar manager.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createFilterContribution(IDiagramWorkbenchPart part, ToolBarManager manager) {
        return contributorFactory.createFilterContribution(part, manager);
    }

    /**
     * Creates the Concern Selection contribution item, if needed.
     * 
     * @param part
     *            the diagram workbench part.
     * @return the {@link IContributionItem} or null of it is not needed.
     */
    protected IContributionItem createConcernContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createConcernContribution(part);
    }

    /**
     * Creates the Select Hidden Elements contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createSelectHiddenElementsContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createSelectHiddenElementsContribution(part);
    }

    /**
     * Creates the Hide Element contribution item. This button hides all the selected elements from view.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createHideElementContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createHideElementContribution(part);
    }

    /**
     * Creates the Show Element contribution item. This button shows all the selected elements from view.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createShowElementContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createShowElementContribution(part);
    }

    /**
     * Creates the Hide Element Label contribution item. This button hides the label of the selected elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected ActionContributionItem createHideElementLabelContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createHideElementLabelContribution(part);
    }

    /**
     * Creates the Show Element Label contribution item. This button shows the label of the selected elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @param hideElementContributionItem
     *            the opposite hide contribution item. Can be null.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createShowElementLabelContribution(IDiagramWorkbenchPart part, ActionContributionItem hideElementContributionItem) {
        return contributorFactory.createShowElementLabelContribution(part, hideElementContributionItem);
    }

    /**
     * Creates the Delete From Diagram contribution item. This action removes the selected graphical element from the
     * diagram, but does not delete the corresponding semantic elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createDeleteFromDiagramContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createDeleteFromDiagramContribution(part);
    }

    /**
     * Creates the Delete From Model contribution item. This action removes both the selected graphical element and the
     * corresponding semantic elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createDeleteFromModelContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createDeleteFromModelContribution(part);
    }

    /**
     * Creates the Select Pinned elements contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createSelectPinnedElementsContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createSelectPinnedElementsContribution(part);
    }

    /**
     * Creates the Pin element contribution item. Toggle the pin state of the selected elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createPinElementContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createPinElementContribution(part);
    }

    /**
     * Creates the Zoom combo contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createZoomContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createZoomContribution(part);
    }

    /**
     * Creates the Zoom-in contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createZoomInContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createZoomInContribution(part);
    }

    /**
     * Creates the Zoom-out contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createZoomOutContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createZoomOutContribution(part);
    }

    /**
     * Creates the Font Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createFontColorContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createFontColorContribution(part);
    }

    /**
     * Creates the Bold font contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createBoldFontStyleContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createBoldFontStyleContribution(part);
    }

    /**
     * Creates the Italic font contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createItalicFontStyleContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createItalicFontStyleContribution(part);
    }

    /**
     * Creates the Font dialog contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createFontDialogContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createFontDialogContribution(part);
    }

    /**
     * Creates the Fill Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createFillColorContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createFillColorContribution(part);
    }

    /**
     * Creates the Line Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createLineColorPropertyContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createLineColorPropertyContribution(part);
    }

    /**
     * Creates the Cancel Custom Style contribution item. The Cancel Custom Style button resets all the style attributes
     * of an element to its default values and un-marks it as customized.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createResetStylePropertyContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createResetStylePropertyContribution(part);
    }

    /**
     * Creates the set style to workspace image contribution item. The Workspace image button can be used to replace the
     * graphical representation of an element by an image that you can select from anywhere in your Eclipse workspace.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createSetStyleToWorkspaceImageContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createSetStyleToWorkspaceImageContribution(part);
    }

    /**
     * Creates the Distribute menu contribution item. This menu contains several actions which can be used to distribute
     * the selected diagram elements horizontally/vertically with a same space between their centers or their bounds.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createDistributeContribution() {
        return contributorFactory.createDistributeContribution();
    }

    /**
     * Creates the Export As Image contribution item.This button can be used to export the current diagram as an image
     * file stored on disk.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createSaveAsImageContributionItem() {
        return contributorFactory.createSaveAsImageContributionItem();
    }

    /**
     * Creates the Mode Menu Manager contribution item. This drop down menu contains three actions:
     * <ul>
     * <li>Standard mode: Deactivate the layouting and visibility mode if they are activated.</li>
     * <li>Visibility mode: this action shows diagram elements made invisible by user and allows to change the
     * visibility status with double click on diagram elements.</li>
     * <li>Layouting Mode: This button enables a special "layout mode", in which some operations are prevented from
     * having an effect on the semantic model.</li>
     * </ul>
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized or if the diagram
     *         doesn't allow the layouting mode.
     */
    protected IContributionItem createModeMenuManagerContributionItem(IDiagramWorkbenchPart part) {
        return contributorFactory.createModesMenuManager((DDiagram) part.getDiagram().getElement());
    }

    /**
     * Creates the Router contribution item. This action allows changing the line style of edges (Rectilinear, Oblique,
     * Tree)
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createRouterContribution() {
        return contributorFactory.createRouterContribution();
    }

    /**
     * Creates the copy format contribution item. This tool can be used to duplicate the format of some diagram elements
     * from this diagram into another.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     * @deprecated since 4.1.0 Use {@link #createCopyFormatContribution(IDiagramWorkbenchPart)} instead.
     */
    @Deprecated
    protected IContributionItem createCopyLayoutContribution(IDiagramWorkbenchPart part) {
        return createCopyFormatContribution(part);
    }

    /**
     * Creates the copy format contribution item. This tool can be used to duplicate the format of some diagram elements
     * from this diagram into another.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createCopyFormatContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createCopyFormatContribution(part);
    }

    /**
     * Creates the Paste Format contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     * @deprecated since 4.1.0 Use {@link #createPasteFormatContribution(IDiagramWorkbenchPart)} instead.
     */
    @Deprecated
    protected IContributionItem createPasteLayoutContribution(IDiagramWorkbenchPart part) {
        return createPasteFormatContribution(part);
    }

    /**
     * Creates the Paste Format contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createPasteFormatContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createPasteFormatContribution(part);
    }

    /**
     * Creates the Refresh action contribution item. This operation, which can also be invoked with the F5 keyboard
     * shortcut, will force an update of the diagram's content according to the latest version of the underlying
     * semantic model.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createRefreshContribution() {
        return contributorFactory.createRefreshContribution();
    }

    /**
     * Creates the Make Same Size contribution item. When multiple elements are selected, clicking on this tool will
     * resize all of them to have the same size (both width and height).
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createSizeBothContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createSizeBothContribution(part);
    }

    /**
     * Creates the Auto-Size contribution item. This button marks the selected elements as auto-sized.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    protected IContributionItem createAutoSizeContribution(IDiagramWorkbenchPart part) {
        return contributorFactory.createAutoSizeContribution(part);
    }

    /**
     * Creates the straighten to contribution item. This menu contains several operations which can be used to
     * straighten to different positions edges.
     * 
     * @return the {@link IContributionItem}.
     */
    protected IContributionItem createStraightenContribution() {
        return contributorFactory.createStraightenContribution();
    }
}
