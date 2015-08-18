/*******************************************************************************
 * Copyright (c) 2013, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar;

import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.CopyAppearancePropertiesAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.ui.tools.api.action.ConcernComboContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SelectPinnedElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.HideDDiagramElementLabelActionContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarAlignMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarArrangeMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarColorPropertyContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarSelectMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.SiriusTabbarExtensionContributionFactory.TabbarActionContributionItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPage;

/**
 * Directly fill the tabbar. This filler does not allows extension and does not
 * use the {@link org.eclipse.ui.menus.IMenuService} to populate the tabbar
 * (toolbar contribution support is broken on Juno and Kepler).
 * 
 * @author mporhel
 */
@SuppressWarnings("restriction")
public class TabbarFillerWithoutContributions extends AbstractTabbarFiller {

    /**
     * Construct a new instance.
     * 
     * @param manager
     *            the toolbar manager
     * @param page
     *            the workbench page
     */
    public TabbarFillerWithoutContributions(ToolBarManager manager, IWorkbenchPage page) {
        super(manager, page);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarFiller#doFill()
     */
    protected void doFill() {
        createSelectArrangeAlignActions();

        manager.add(new Separator());

        createLayerFilterActions();

        manager.add(new Separator());

        createHideAndDeleteActions();

        createZoomActions();

        createExtraDiagramActions();

        manager.add(new Separator());

        createStyleActions();

        manager.add(new Separator());

        createSizeActions();
    }

    private void createSelectArrangeAlignActions() {
        TabbarArrangeMenuManager arrangeMenu = new TabbarArrangeMenuManager(part);
        arrangeMenu.setVisible(true);
        manager.add(arrangeMenu);

        TabbarSelectMenuManager selectMenu = new TabbarSelectMenuManager();
        selectMenu.setVisible(true);
        manager.add(selectMenu);

        TabbarAlignMenuManager alignMenu = new TabbarAlignMenuManager();
        alignMenu.setVisible(true);
        manager.add(alignMenu);
    }

    private void createLayerFilterActions() {
        LayersContribution layersContribution = new LayersContribution();
        ContributionItem layerItem = layersContribution.createContributionItem(manager);
        layersContribution.setPart(part);
        manager.add(layerItem);

        FiltersContributionItem filtersContribution = new FiltersContributionItem();
        ContributionItem filterItem = filtersContribution.createContributionItem(manager);
        filtersContribution.setPart(part);
        manager.add(filterItem);

        // Concern ?
        if (part instanceof DDiagramEditor) {
            final DDiagram diagram = (DDiagram) ((DDiagramEditor) part).getRepresentation();
            if (diagram != null) {
                DiagramDescription description = diagram.getDescription();
                if (description != null && description.getConcerns() != null && description.getConcerns().getOwnedConcernDescriptions().size() > 1) {
                    ConcernComboContributionItem item = new ConcernComboContributionItem(page, ""); //$NON-NLS-1$
                    item.setDiagram(diagram);
                    manager.add(item);
                }
            }
        }
    }

    private void createHideAndDeleteActions() {
        SelectHiddenElementsAction selectHiddenElementsAction = new SelectHiddenElementsAction(page, part);
        manager.add(selectHiddenElementsAction);

        HideDDiagramElementAction hideDDiagramElementAction = new HideDDiagramElementAction(SiriusDiagramActionBarContributor.HIDE_ELEMENT);
        hideDDiagramElementAction.setActionPart(part);
        manager.add(new TabbarActionContributionItem(hideDDiagramElementAction, part));

        HideDDiagramElementLabelActionContributionItem contributionItem = new HideDDiagramElementLabelActionContributionItem(part);
        manager.add(contributionItem);

        SelectPinnedElementsAction pinnedElementsAction = new SelectPinnedElementsAction(page, part);
        manager.add(pinnedElementsAction);
    }

    private void createZoomActions() {
        ZoomComboContributionItem zoomItem = new ZoomComboContributionItem(page) {

            @Override
            public void fill(ToolBar parent, int index) {
                super.fill(parent, index);
                ToolItem addedItem = parent.getItem(parent.getItemCount() - 1);
                addedItem.setToolTipText(DiagramUIMessages.ZoomActionMenu_ZoomLabel);
            }

        };
        manager.add(zoomItem);
    }

    private void createExtraDiagramActions() {
        if (part instanceof DDiagramEditor) {
            DDiagram editorDiagram = (DDiagram) ((DDiagramEditor) part).getRepresentation();
            if (LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(editorDiagram)) {
                LayoutingModeSwitchingAction layoutingModeSwitchingAction = new LayoutingModeSwitchingAction(page, editorDiagram);
                manager.add(new TabbarActionContributionItem(layoutingModeSwitchingAction));
            }
        }
    }

    private void createStyleActions() {
        TabbarColorPropertyContributionItem fontColorMenu = TabbarColorPropertyContributionItem.createFontColorContributionItem(page);
        fontColorMenu.setActionWorkbenchPart(part);
        manager.add(fontColorMenu);

        TabbarColorPropertyContributionItem fillColorMenu = TabbarColorPropertyContributionItem.createFillColorContributionItem(page);
        fillColorMenu.setActionWorkbenchPart(part);
        manager.add(fillColorMenu);

        TabbarColorPropertyContributionItem lineColorMenu = TabbarColorPropertyContributionItem.createLineColorContributionItem(page);
        lineColorMenu.setActionWorkbenchPart(part);
        manager.add(lineColorMenu);

        ResetStylePropertiesToDefaultValuesAction resetStylePropertiesToDefaultValuesAction = new ResetStylePropertiesToDefaultValuesAction(page);
        manager.add(new TabbarActionContributionItem(resetStylePropertiesToDefaultValuesAction, part));

        CopyAppearancePropertiesAction copyAppearancePropertiesAction = new CopyAppearancePropertiesAction(page);
        manager.add(copyAppearancePropertiesAction);
    }

    private void createSizeActions() {
        SizeBothAction sizeBothAction = new SizeBothAction(page);
        sizeBothAction.init();
        manager.add(sizeBothAction);

        AutoSizeAction autoSizeAction = new AutoSizeAction(page);
        manager.add(autoSizeAction);
    }
}
