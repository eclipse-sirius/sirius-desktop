/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.edit.api.part.AbstractDDiagramEditPart;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.TabbarContributionFactory;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Fill the toolbar when a diagram is selected.
 * 
 * @author mchauvin
 */
public class TabbarFillerWithContributions extends AbstractTabbarFiller {

    private static final String ARRANGE_SELECTION = "org.eclipse.sirius.diagram.ui.tabbar.arrangeselection"; //$NON-NLS-1$

    private static final String REFRESH = "org.eclipse.sirius.diagram.ui.tabbar.refresh"; //$NON-NLS-1$

    private static final String LAYER_FILTER = "org.eclipse.sirius.diagram.ui.tabbar.layerfilter"; //$NON-NLS-1$

    private static final String HIDE_PIN = "org.eclipse.sirius.diagram.ui.tabbar.hidepin"; //$NON-NLS-1$

    private static final String PAST = "org.eclipse.sirius.diagram.ui.tabbar.past"; //$NON-NLS-1$

    private static final String HIDE_DELETE = "org.eclipse.sirius.diagram.ui.tabbar.hidedelete"; //$NON-NLS-1$

    private static final String ZOOM = "org.eclipse.sirius.diagram.ui.tabbar.zoom"; //$NON-NLS-1$

    private static final String EXPORT = "org.eclipse.sirius.diagram.ui.tabbar.export"; //$NON-NLS-1$

    private static final String FONT = "org.eclipse.sirius.diagram.ui.tabbar.font"; //$NON-NLS-1$

    private static final String STYLE = "org.eclipse.sirius.diagram.ui.tabbar.style"; //$NON-NLS-1$

    private static final String SIZE = "org.eclipse.sirius.diagram.ui.tabbar.size"; //$NON-NLS-1$

    private static final String MODES = "org.eclipse.sirius.diagram.ui.tabbar.modes"; //$NON-NLS-1$

    private List<IContributionItem> dynamicContributions = new ArrayList<IContributionItem>();

    private ArrayList<IContributionItem> diagramContributionItems = new ArrayList<>();

    private ArrayList<IContributionItem> diagramElementContributionItems = new ArrayList<>();

    private TabbarContributionFactory contributionFactory = new TabbarContributionFactory();

    /**
     * Default Constructor.
     * 
     * @param manager
     *            the {@link ToolBarManager}.
     * @param page
     *            the current {@link IWorkbenchPage}.
     */
    public TabbarFillerWithContributions(ToolBarManager manager, IWorkbenchPage page) {
        super(manager, page);
    }

    @Override
    protected void doFill() {

        // Add default contributions
        configureGroupSeparators();
        addDiagramContributionItems();
        addDiagramElementContributionItems();

        // The visibility of static contributions are done before calling
        // addTabbarContributions() to have a correct index when
        // org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.AbstractMenuContributionItem.TabbarContributionItem.fill(ToolBar,
        // int) is called
        refreshItemsVisibility(null);

        // Add dynamic contributions
        ArrayList<IContributionItem> currentContributions = new ArrayList<IContributionItem>(Arrays.asList(manager.getItems()));
        addTabbarContributions();
        dynamicContributions = new ArrayList<IContributionItem>(Arrays.asList(manager.getItems()));
        dynamicContributions.removeAll(currentContributions);

        update(null);
    }

    /**
     * Updates the tabbar according to the given selection.
     * 
     * @param iSelection
     *            the current selection.
     */
    @Override
    public void update(ISelection iSelection) {
        refreshItemsVisibility(iSelection);

        manager.update(true);
    }

    /**
     * Refresh the visibility of items according to the given selection.
     * 
     * @param selection
     *            the current selection.
     */
    protected void refreshItemsVisibility(ISelection selection) {
        List<IContributionItem> existingItems = Arrays.asList(manager.getItems());
        for (IContributionItem current : existingItems) {
            if (!dynamicContributions.contains(current) && !current.isSeparator()) {
                current.setVisible(false);
            }
        }

        List<IContributionItem> contributionItems = getContributionItems(selection);
        for (IContributionItem item : contributionItems) {
            if (existingItems.contains(item)) {
                item.setVisible(true);
            }
        }
    }

    private List<IContributionItem> getContributionItems(ISelection selection) {
        List<IContributionItem> contributedItems = diagramContributionItems;
        if (selection instanceof IStructuredSelection) {
            Object firstElement = ((IStructuredSelection) selection).getFirstElement();
            if (!(firstElement instanceof AbstractDDiagramEditPart)) {
                contributedItems = diagramElementContributionItems;
            }
        }
        return contributedItems;
    }

    private void configureGroupSeparators() {
        initSeparator(ARRANGE_SELECTION);
        initSeparator(REFRESH);
        initSeparator(LAYER_FILTER);
        initSeparator(HIDE_PIN);
        initSeparator(MODES);
        initSeparator(PAST);
        initSeparator(HIDE_DELETE);
        initSeparator(ZOOM);
        initSeparator(EXPORT);
        initSeparator(FONT);
        initSeparator(STYLE);
        initSeparator(SIZE);
    }

    private void initSeparator(String name) {
        IContributionItem contributionItem = new Separator(name);
        manager.add(contributionItem);
    }

    private void addDiagramContributionItems() {
        addContributionItem(diagramContributionItems, ARRANGE_SELECTION, contributionFactory.createSelectMenuManager());
        addContributionItem(diagramContributionItems, ARRANGE_SELECTION, contributionFactory.createArrangeMenuManager(part));

        addContributionItem(diagramContributionItems, REFRESH, contributionFactory.createRefreshContribution());

        addContributionItem(diagramContributionItems, LAYER_FILTER, contributionFactory.createConcernContribution(part));
        addContributionItem(diagramContributionItems, LAYER_FILTER, contributionFactory.createFilterContribution(part, manager));
        addContributionItem(diagramContributionItems, LAYER_FILTER, contributionFactory.createLayerContribution(part, manager));

        addContributionItem(diagramContributionItems, HIDE_PIN, contributionFactory.createSelectPinnedElementsContribution(part));
        addContributionItem(diagramContributionItems, HIDE_PIN, contributionFactory.createSelectHiddenElementsContribution(part));
        addContributionItem(diagramContributionItems, MODES, contributionFactory.createModesMenuManager((DDiagram) part.getDiagram().getElement()));

        addContributionItem(diagramContributionItems, PAST, contributionFactory.createPasteFormatContribution(part));

        addContributionItem(diagramContributionItems, ZOOM, contributionFactory.createZoomContribution(part));
        addContributionItem(diagramContributionItems, ZOOM, contributionFactory.createZoomOutContribution(part));
        addContributionItem(diagramContributionItems, ZOOM, contributionFactory.createZoomInContribution(part));

        addContributionItem(diagramContributionItems, EXPORT, contributionFactory.createSaveAsImageContributionItem());
    }

    private void addContributionItem(Collection<IContributionItem> contributionItems, String separatorName, IContributionItem contributionItem) {
        if (contributionItem != null) {
            contributionItems.add(contributionItem);
            manager.insertAfter(separatorName, contributionItem);
        }
    }

    private void addDiagramElementContributionItems() {
        addContributionItem(diagramElementContributionItems, ARRANGE_SELECTION, contributionFactory.createStraightenContribution());
        addContributionItem(diagramElementContributionItems, ARRANGE_SELECTION, contributionFactory.createDistributeContribution());
        addContributionItem(diagramElementContributionItems, ARRANGE_SELECTION, contributionFactory.createAlignMenuManager());
        addContributionItem(diagramElementContributionItems, ARRANGE_SELECTION, contributionFactory.createArrangeMenuManager(part));

        IContributionItem pinElementContributionItem = contributionFactory.createPinElementContribution(part);
        addContributionItem(diagramElementContributionItems, HIDE_PIN, contributionFactory.createUnPinElementContribution(part, pinElementContributionItem));
        addContributionItem(diagramElementContributionItems, HIDE_PIN, pinElementContributionItem);
        addContributionItem(diagramElementContributionItems, MODES, contributionFactory.createModesMenuManager((DDiagram) part.getDiagram().getElement()));
        addContributionItem(diagramElementContributionItems, PAST, contributionFactory.createCopyFormatContribution(part));

        addContributionItem(diagramElementContributionItems, HIDE_DELETE, contributionFactory.createDeleteFromModelContribution(part));
        addContributionItem(diagramElementContributionItems, HIDE_DELETE, contributionFactory.createDeleteFromDiagramContribution(part));

        ActionContributionItem hideElementLabelContribution = contributionFactory.createHideElementLabelContribution(part);
        addContributionItem(diagramElementContributionItems, HIDE_DELETE, hideElementLabelContribution);
        addContributionItem(diagramElementContributionItems, HIDE_DELETE, contributionFactory.createShowElementLabelContribution(part, hideElementLabelContribution));
        addContributionItem(diagramElementContributionItems, HIDE_DELETE, contributionFactory.createHideElementContribution(part));
        addContributionItem(diagramElementContributionItems, HIDE_DELETE, contributionFactory.createShowElementContribution(part));

        addContributionItem(diagramElementContributionItems, FONT, contributionFactory.createFontDialogContribution(part));
        addContributionItem(diagramElementContributionItems, FONT, contributionFactory.createFontColorContribution(part));
        addContributionItem(diagramElementContributionItems, FONT, contributionFactory.createItalicFontStyleContribution(part));
        addContributionItem(diagramElementContributionItems, FONT, contributionFactory.createBoldFontStyleContribution(part));

        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createCopyAppearancePropertiesContribution(part));
        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createResetStylePropertyContribution(part));
        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createSetStyleToWorkspaceImageContribution(part));
        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createRouterContribution());
        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createLineColorPropertyContribution(part));
        addContributionItem(diagramElementContributionItems, STYLE, contributionFactory.createFillColorContribution(part));

        addContributionItem(diagramElementContributionItems, SIZE, contributionFactory.createAutoSizeContribution(part));
        addContributionItem(diagramElementContributionItems, SIZE, contributionFactory.createSizeBothContribution(part));

    }

    @Override
    public void dispose() {
        releaseTabbarContributions();
        super.dispose();
    }

}
