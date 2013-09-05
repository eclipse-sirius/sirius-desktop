/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.editor.tabbar;

import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.CopyAppearancePropertiesAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.FontDialogAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.FontStyleAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.SizeBothAction;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

import org.eclipse.sirius.DDiagram;
import org.eclipse.sirius.description.DiagramDescription;
import org.eclipse.sirius.diagram.ImagesPath;
import org.eclipse.sirius.diagram.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.part.SiriusDiagramEditorPlugin;
import org.eclipse.sirius.diagram.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.diagram.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.tools.internal.actions.SelectPinnedElementsAction;
import org.eclipse.sirius.diagram.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.tools.internal.actions.layout.CopyLayoutAction;
import org.eclipse.sirius.diagram.tools.internal.actions.layout.PasteLayoutAction;
import org.eclipse.sirius.diagram.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.sirius.diagram.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.HideDDiagramElementLabelActionContributionItem;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarAlignMenuManager;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarArrangeMenuManager;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarColorPropertyContributionItem;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarPinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarRouterMenuManager;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarSelectMenuManager;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarUnpinElementsEclipseAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarZoomAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarZoomInAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.actions.TabbarZoomOutAction;
import org.eclipse.sirius.diagram.tools.internal.editor.tabbar.contributions.SiriusTabbarExtensionContributionFactory.TabbarActionContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.action.ConcernComboContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.action.DeleteFromDiagramContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.action.SetStyleToWorkspaceImageContributionItem;

/**
 * Directly fill the tabbar. This filler does not allows extension and does not
 * use the {@link org.eclipse.ui.menus.IMenuService} to populate the tabbar
 * (toolbar contribution support is broken on Juno and Kepler).
 * 
 * @author mporhel
 */
public class TabbarFillerWithoutContributions extends AbstractTabbarFiller {

    private IPartListener zoomManagerLinker;

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

    @Override
    public void dispose() {
        page.removePartListener(zoomManagerLinker);

        super.dispose();
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.tools.internal.editor.tabbar.AbstractTabbarFiller#doFill()
     */
    protected void doFill() {
        createSelectArrangeAlignActions();

        manager.add(new Separator());

        RefreshDiagramAction action = new RefreshDiagramAction(SiriusDiagramActionBarContributor.REFRESH_DIAGRAM, SiriusDiagramEditorPlugin.getBundledImageDescriptor(ImagesPath.REFRESH_IMG));
        manager.add(action);

        manager.add(new Separator());

        createLayerFilterActions();

        manager.add(new Separator());

        createPinActions();

        manager.add(new Separator());

        createCopyPasteLayoutActions();

        manager.add(new Separator());

        createHideAndDeleteActions();

        manager.add(new Separator());

        createZoomActions();

        manager.add(new Separator());

        createExtraDiagramActions();

        manager.add(new Separator());

        createFontActions();

        manager.add(new Separator());

        createStyleActions();

        manager.add(new Separator());

        createSizeActions();
    }

    private void createSelectArrangeAlignActions() {
        TabbarArrangeMenuManager arrangeMenu = new TabbarArrangeMenuManager(page, part);
        arrangeMenu.setVisible(true);
        manager.add(arrangeMenu);

        TabbarSelectMenuManager selectMenu = new TabbarSelectMenuManager(page);
        selectMenu.setVisible(true);
        manager.add(selectMenu);

        TabbarAlignMenuManager alignMenu = new TabbarAlignMenuManager(page);
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
                    ConcernComboContributionItem item = new ConcernComboContributionItem(page, "");
                    item.setDiagram(diagram);
                    manager.add(item);
                }
            }
        }
    }

    private void createPinActions() {
        SelectPinnedElementsAction pinnedElementsAction = new SelectPinnedElementsAction(page, part);
        manager.add(pinnedElementsAction);

        TabbarPinElementsEclipseAction pinAction = new TabbarPinElementsEclipseAction();
        TabbarUnpinElementsEclipseAction unpinAction = new TabbarUnpinElementsEclipseAction();
        pinAction.setOppositePinAction(unpinAction);
        unpinAction.setOppositePinAction(pinAction);
        manager.add(new TabbarActionContributionItem(pinAction, part));
        manager.add(new TabbarActionContributionItem(unpinAction, part));
    }

    private void createCopyPasteLayoutActions() {
        CopyLayoutAction copyLayoutAction = new CopyLayoutAction(page, part);
        manager.add(copyLayoutAction);

        PasteLayoutAction pasteLayoutAction = new PasteLayoutAction(page, part);
        manager.add(pasteLayoutAction);
    }

    private void createHideAndDeleteActions() {
        SelectHiddenElementsAction selectHiddenElementsAction = new SelectHiddenElementsAction(page, part);
        manager.add(selectHiddenElementsAction);

        HideDDiagramElementAction hideDDiagramElementAction = new HideDDiagramElementAction(SiriusDiagramActionBarContributor.HIDE_ELEMENT);
        hideDDiagramElementAction.setActionPart(part);
        manager.add(new TabbarActionContributionItem(hideDDiagramElementAction, part));

        HideDDiagramElementLabelActionContributionItem contributionItem = new HideDDiagramElementLabelActionContributionItem(part);
        manager.add(contributionItem);

        DeleteFromDiagramContributionItem deleteFromDiagram = new DeleteFromDiagramContributionItem(new DeleteFromDiagramAction(DiagramUIMessages.DiagramEditor_Delete_from_Diagram,
                SiriusDiagramActionBarContributor.DELETE_FROM_DIAGRAM, ActionIds.ACTION_DELETE_FROM_DIAGRAM,
                SiriusDiagramEditorPlugin.getBundledImageDescriptor(ImagesPath.DELETE_FROM_DIAGRAM_ICON)), page);
        deleteFromDiagram.setItemPart(part);
        manager.add(deleteFromDiagram);

        DeleteFromModelWithHookAction deleteFromModelAction = new DeleteFromModelWithHookAction(page, part);
        deleteFromModelAction.init();
        manager.add(deleteFromModelAction);
    }

    private void createZoomActions() {
        final TabbarZoomAction zoomIn = new TabbarZoomInAction();
        zoomIn.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_IN);
        zoomIn.setText(DiagramUIMessages.ZoomAction_ZoomIn);
        manager.add(zoomIn);

        final TabbarZoomAction zoomOut = new TabbarZoomOutAction();
        zoomOut.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_OUT);
        zoomIn.setText(DiagramUIMessages.ZoomAction_ZoomOut);
        manager.add(zoomOut);

        // During tabbar activation, Zoom mannager is not ready yet.
        zoomManagerLinker = new IPartListener() {
            public void partActivated(IWorkbenchPart p) {
                if (p instanceof DDiagramEditor && p == part) {
                    final ZoomManager zoomManager = (ZoomManager) p.getAdapter(ZoomManager.class);
                    zoomIn.setZoomManager(zoomManager);
                    zoomOut.setZoomManager(zoomManager);
                }
            }

            public void partBroughtToTop(IWorkbenchPart p) {
            }

            public void partClosed(IWorkbenchPart p) {
                page.removePartListener(this);
            }

            public void partDeactivated(IWorkbenchPart p) {
            }

            public void partOpened(IWorkbenchPart p) {
            }
        };
        page.addPartListener(zoomManagerLinker);

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
        SaveAsImageFileAction saveAsImageFileAction = new SaveAsImageFileAction();
        manager.add(saveAsImageFileAction);

        if (part instanceof DDiagramEditor) {
            DDiagram editorDiagram = (DDiagram) ((DDiagramEditor) part).getRepresentation();
            if (LayoutingModeSwitchingAction.diagramAllowsLayoutingMode(editorDiagram)) {
                LayoutingModeSwitchingAction layoutingModeSwitchingAction = new LayoutingModeSwitchingAction(page, editorDiagram);
                manager.add(new TabbarActionContributionItem(layoutingModeSwitchingAction));
            }
        }
    }

    private void createFontActions() {
        FontStyleAction fontStyleBoldAction = FontStyleAction.createBoldFontStyleAction(page);
        manager.add(fontStyleBoldAction);

        FontStyleAction fontStyleItalicAction = FontStyleAction.createItalicFontStyleAction(page);
        manager.add(fontStyleItalicAction);

        TabbarColorPropertyContributionItem fontColorMenu = TabbarColorPropertyContributionItem.createFontColorContributionItem(page);
        fontColorMenu.setActionWorkbenchPart(part);
        manager.add(fontColorMenu);

        IAction fontDialogAction = new FontDialogAction(page);
        fontDialogAction.setImageDescriptor(SiriusDiagramEditorPlugin.getBundledImageDescriptor(ImagesPath.FONT_WIZARD));
        manager.add(fontDialogAction);
    }

    private void createStyleActions() {
        TabbarColorPropertyContributionItem fillColorMenu = TabbarColorPropertyContributionItem.createFillColorContributionItem(page);
        fillColorMenu.setActionWorkbenchPart(part);
        manager.add(fillColorMenu);

        TabbarColorPropertyContributionItem lineColorMenu = TabbarColorPropertyContributionItem.createLineColorContributionItem(page);
        lineColorMenu.setActionWorkbenchPart(part);
        manager.add(lineColorMenu);

        MenuManager routerManager = new TabbarRouterMenuManager(page);
        manager.add(routerManager);

        manager.add(new SetStyleToWorkspaceImageContributionItem(new SetStyleToWorkspaceImageAction(), page, part));

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
