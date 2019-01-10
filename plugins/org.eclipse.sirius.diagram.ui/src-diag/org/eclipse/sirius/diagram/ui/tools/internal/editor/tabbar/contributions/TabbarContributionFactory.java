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
package org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions;

import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.AutoSizeAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.FontDialogAction;
import org.eclipse.gmf.runtime.diagram.ui.actions.internal.FontStyleAction;
import org.eclipse.gmf.runtime.diagram.ui.internal.l10n.DiagramUIPluginImages;
import org.eclipse.gmf.runtime.diagram.ui.l10n.DiagramUIMessages;
import org.eclipse.gmf.runtime.diagram.ui.parts.IDiagramWorkbenchPart;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramActionBarContributor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.action.ConcernComboContributionItem;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SaveAsImageFileAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SelectHiddenElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SelectPinnedElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SiriusAutoSizeAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SiriusCopyAppearancePropertiesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.SizeBothAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.delete.DeleteFromModelWithHookAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.layout.CopyFormatAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.refresh.RefreshDiagramAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.ResetStylePropertiesToDefaultValuesAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.style.SetStyleToWorkspaceImageAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.HideDDiagramElementLabelAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.TabbarRevealElementsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.actions.visibility.TabbarRevealLabelsAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.FiltersContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.LayersContribution;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.DiagramActionContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarAlignMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarArrangeMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarColorPropertyContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarDistributeMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarPasteFormatMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarPinElementsEclipseAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarRouterMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarSelectMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarStraightenToMenuManager;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarUnpinElementsEclipseAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomInAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.TabbarZoomOutAction;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.actions.ZoomContributionItem;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.tabbar.contributions.SiriusTabbarExtensionContributionFactory.TabbarActionContributionItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IWorkbenchPartSite;

/**
 * Factory for tabbar {@link IContributionItem}.
 * 
 * @author lfasani
 */
@SuppressWarnings("restriction")
public class TabbarContributionFactory {

    private static final String HIDE_ELEMENT_ID = "org.eclipse.sirius.diagram.ui.tabbar.hideelement"; //$NON-NLS-1$

    /**
     * Creates the Automatic Layout contribution item. Organized in a drop-down menu, this item is used to trigger an
     * automatic layout of the elements on the diagram.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createArrangeMenuManager(IDiagramWorkbenchPart part) {
        TabbarArrangeMenuManager arrangeMenu = new TabbarArrangeMenuManager(part);
        arrangeMenu.setVisible(true);
        return arrangeMenu;
    }

    /**
     * Creates the Select menu manager contribution item. Organized in a drop-down menu, can be used to select groups of
     * diagram elements in a single operation.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createSelectMenuManager() {
        TabbarSelectMenuManager selectMenu = new TabbarSelectMenuManager();
        selectMenu.setVisible(true);
        return selectMenu;
    }

    /**
     * Creates the Alignment contribution item. This menu contains several operations which can be used to align several
     * graphical elements in various ways.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createAlignMenuManager() {
        TabbarAlignMenuManager alignMenu = new TabbarAlignMenuManager();
        alignMenu.setVisible(true);
        return alignMenu;
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
    public IContributionItem createLayerContribution(IDiagramWorkbenchPart part, ToolBarManager manager) {
        LayersContribution layersContribution = new LayersContribution();
        ContributionItem layerItem = layersContribution.createContributionItem(manager);
        layersContribution.setPart(part);
        return layerItem;
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
    public IContributionItem createFilterContribution(IDiagramWorkbenchPart part, ToolBarManager manager) {
        FiltersContributionItem filtersContribution = new FiltersContributionItem();
        ContributionItem filterItem = filtersContribution.createContributionItem(manager);
        filtersContribution.setPart(part);
        return filterItem;
    }

    /**
     * Creates the Concern Selection contribution item, if needed.
     * 
     * @param part
     *            the diagram workbench part.
     * @return the {@link IContributionItem} or null of it is not needed.
     */
    public IContributionItem createConcernContribution(IDiagramWorkbenchPart part) {
        if (part instanceof DDiagramEditor) {
            final DDiagram editorDiagram = (DDiagram) ((DDiagramEditor) part).getRepresentation();
            DiagramDescription description = null;
            if (editorDiagram != null) {
                description = editorDiagram.getDescription();
            }

            if (description != null) {
                IWorkbenchPartSite site = part.getSite();
                if (site != null) {
                    return createConcernItem(editorDiagram, description, site);
                }
            }
        }
        return null;
    }

    private IContributionItem createConcernItem(final DDiagram diagram, final DiagramDescription description, IWorkbenchPartSite site) {
        if (description.getConcerns() != null && !description.getConcerns().getOwnedConcernDescriptions().isEmpty() && description.getConcerns().getOwnedConcernDescriptions().size() != 1) {
            ConcernComboContributionItem item = new ConcernComboContributionItem(site.getPage(), ""); //$NON-NLS-1$
            if (diagram != null) {
                item.setDiagram(diagram);
            }
            return item;
        }
        return null;
    }

    /**
     * Creates the Select Hidden Elements contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createSelectHiddenElementsContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            SelectHiddenElementsAction selectHiddenElementsAction = new SelectHiddenElementsAction(site.getPage(), part);
            return new DiagramActionContributionItem(selectHiddenElementsAction);
        }
        return null;
    }

    /**
     * Creates the Hide Element contribution item. This button hides all the selected elements from view.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createHideElementContribution(IDiagramWorkbenchPart part) {
        HideDDiagramElementAction hideDDiagramElementAction = new HideDDiagramElementAction(SiriusDiagramActionBarContributor.HIDE_ELEMENT);
        hideDDiagramElementAction.setActionPart(part);
        TabbarActionContributionItem tabbarActionContributionItem = new TabbarActionContributionItem(hideDDiagramElementAction, part);
        tabbarActionContributionItem.setId(HIDE_ELEMENT_ID);
        return tabbarActionContributionItem;
    }

    /**
     * Creates the Show Element contribution item. This button reveals all the selected elements from view. (Relevant
     * only in visibility mode)
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createShowElementContribution(IDiagramWorkbenchPart part) {
        TabbarRevealElementsAction tabbarRevealElementsAction = new TabbarRevealElementsAction(SiriusDiagramActionBarContributor.SHOW_ELEMENT);
        tabbarRevealElementsAction.setActionPart(part);
        TabbarActionContributionItem tabbarActionContributionItem = new TabbarActionContributionItem(tabbarRevealElementsAction, part);
        return tabbarActionContributionItem;
    }

    /**
     * Creates the Hide Element Label contribution item. This button hides the label of the selected elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public ActionContributionItem createHideElementLabelContribution(IDiagramWorkbenchPart part) {
        HideDDiagramElementLabelAction hideDDiagramElementAction = new HideDDiagramElementLabelAction(SiriusDiagramActionBarContributor.HIDE_LABEL);
        TabbarActionContributionItem tabbarActionContributionItem = new TabbarActionContributionItem(hideDDiagramElementAction, part);
        return tabbarActionContributionItem;
    }

    /**
     * Creates the Show Element Label contribution item. This button reveals the label of the selected elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @param oppositeHideLabel
     *            The Hide Label Contribution Item. Useful to bind the hide and show label actions to mark them as
     *            visible when clicking on one of them.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createShowElementLabelContribution(IDiagramWorkbenchPart part, ActionContributionItem oppositeHideLabel) {
        IAction action = oppositeHideLabel.getAction();
        TabbarRevealLabelsAction revealOutlineLabelsAction = new TabbarRevealLabelsAction(SiriusDiagramActionBarContributor.SHOW_LABEL);
        if (action instanceof HideDDiagramElementLabelAction) {
            revealOutlineLabelsAction.setOppositeHideAction((HideDDiagramElementLabelAction) action);
            ((HideDDiagramElementLabelAction) action).setOppositeRevealAction(revealOutlineLabelsAction);
        }
        TabbarActionContributionItem tabbarActionContributionItem = new TabbarActionContributionItem(revealOutlineLabelsAction, part);
        return tabbarActionContributionItem;
    }

    /**
     * Creates the Delete From Diagram contribution item. This action removes the selected graphical element from the
     * diagram, but does not delete the corresponding semantic elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createDeleteFromDiagramContribution(IDiagramWorkbenchPart part) {
        DeleteFromDiagramAction deleteFromDiagramAction = new DeleteFromDiagramAction(DiagramUIMessages.DiagramEditor_Delete_from_Diagram, SiriusDiagramActionBarContributor.DELETE_FROM_DIAGRAM,
                ActionIds.ACTION_DELETE_FROM_DIAGRAM, DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.DELETE_FROM_DIAGRAM_ICON));
        return new TabbarActionContributionItem(deleteFromDiagramAction, part);
    }

    /**
     * Creates the Delete From Model contribution item. This action removes both the selected graphical element and the
     * corresponding semantic elements.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createDeleteFromModelContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            DeleteFromModelWithHookAction deleteFromModelAction = new DeleteFromModelWithHookAction(site.getPage(), part);
            deleteFromModelAction.init();
            return new DiagramActionContributionItem(deleteFromModelAction);
        }
        return null;
    }

    /**
     * Creates the Select Pinned elements contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createSelectPinnedElementsContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            SelectPinnedElementsAction pinnedElementsAction = new SelectPinnedElementsAction(site.getPage(), part);

            return new DiagramActionContributionItem(pinnedElementsAction);
        }
        return null;
    }

    /**
     * Creates the Pin element contribution item. Mark all the selected elements as pinned.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createPinElementContribution(IDiagramWorkbenchPart part) {
        TabbarPinElementsEclipseAction pinAction = new TabbarPinElementsEclipseAction();
        return new TabbarActionContributionItem(pinAction, part);
    }

    /**
     * Creates the Unpin element contribution item. Mark all the selected elements as un-pinned.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @param pinElementContributionItem
     *            the opposite pin contribution item. Can be null.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createUnPinElementContribution(IDiagramWorkbenchPart part, IContributionItem pinElementContributionItem) {

        TabbarUnpinElementsEclipseAction unpinAction = new TabbarUnpinElementsEclipseAction();
        if (pinElementContributionItem instanceof TabbarActionContributionItem) {
            IAction pinAction = ((TabbarActionContributionItem) pinElementContributionItem).getAction();
            if (pinAction instanceof TabbarPinElementsEclipseAction) {
                ((TabbarPinElementsEclipseAction) pinAction).setOppositePinAction(unpinAction);
            }
            unpinAction.setOppositePinAction(pinAction);
        }
        return new TabbarActionContributionItem(unpinAction, part);
    }

    /**
     * Creates the Zoom combo contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createZoomContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            ZoomComboContributionItem zoomItem = new ZoomComboContributionItem(site.getPage()) {

                @Override
                public void fill(ToolBar parent, int index) {
                    super.fill(parent, index);
                    ToolItem addedItem = parent.getItem(parent.getItemCount() - 1);
                    addedItem.setToolTipText(DiagramUIMessages.ZoomActionMenu_ZoomLabel);
                }

            };
            return zoomItem;
        }
        return null;
    }

    /**
     * Creates the Zoom-in contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createZoomInContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            TabbarZoomAction zoomIn = new TabbarZoomInAction();
            zoomIn.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_IN);
            zoomIn.setText(DiagramUIMessages.ZoomAction_ZoomIn);
            return new ZoomContributionItem(zoomIn, site.getPage());
        }
        return null;
    }

    /**
     * Creates the Zoom-out contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createZoomOutContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            TabbarZoomAction zoomOut = new TabbarZoomOutAction();
            zoomOut.setImageDescriptor(DiagramUIPluginImages.DESC_ZOOM_OUT);
            zoomOut.setText(DiagramUIMessages.ZoomAction_ZoomOut);
            return new ZoomContributionItem(zoomOut, site.getPage());
        }
        return null;
    }

    /**
     * Creates the Font Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createFontColorContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            TabbarColorPropertyContributionItem fontColorMenu = TabbarColorPropertyContributionItem.createFontColorContributionItem(site.getPage());
            fontColorMenu.setActionWorkbenchPart(part);
            return fontColorMenu;
        }
        return null;
    }

    /**
     * Creates the Bold font contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createBoldFontStyleContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            FontStyleAction fontStyleBoldAction = FontStyleAction.createBoldFontStyleAction(site.getPage());
            return new ActionContributionItem(fontStyleBoldAction);
        }
        return null;
    }

    /**
     * Creates the Italic font contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createItalicFontStyleContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            FontStyleAction fontStyleItalicAction = FontStyleAction.createItalicFontStyleAction(site.getPage());
            return new ActionContributionItem(fontStyleItalicAction);
        }
        return null;
    }

    /**
     * Creates the Font dialog contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createFontDialogContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            IAction fontDialogAction = new FontDialogAction(site.getPage());
            fontDialogAction.setImageDescriptor(DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.FONT_WIZARD));
            return new ActionContributionItem(fontDialogAction);
        }
        return null;
    }

    /**
     * Creates the Fill Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createFillColorContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            TabbarColorPropertyContributionItem fillColorMenu = TabbarColorPropertyContributionItem.createFillColorContributionItem(site.getPage());
            fillColorMenu.setActionWorkbenchPart(part);
            return fillColorMenu;
        }
        return null;
    }

    /**
     * Creates the Line Color contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createLineColorPropertyContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            TabbarColorPropertyContributionItem lineColorMenu = TabbarColorPropertyContributionItem.createLineColorContributionItem(site.getPage());
            lineColorMenu.setActionWorkbenchPart(part);
            return lineColorMenu;
        }
        return null;
    }

    /**
     * Creates the Cancel Custom Style contribution item. The Cancel Custom Style button resets all the style attributes
     * of an element to its default values and un-marks it as customized.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createResetStylePropertyContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            ResetStylePropertiesToDefaultValuesAction resetStylePropertiesToDefaultValuesAction = new ResetStylePropertiesToDefaultValuesAction(site.getPage());
            return new TabbarActionContributionItem(resetStylePropertiesToDefaultValuesAction, part);
        }
        return null;
    }

    /**
     * Creates the set style to workspace image contribution item. The Workspace image button can be used to replace the
     * graphical representation of an element by an image that you can select from anywhere in your Eclipse workspace.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createSetStyleToWorkspaceImageContribution(IDiagramWorkbenchPart part) {
        return new TabbarActionContributionItem(new SetStyleToWorkspaceImageAction(), part);
    }

    /**
     * Creates the Distribute menu contribution item. This menu contains several actions which can be used to distribute
     * the selected diagram elements horizontally/vertically with a same space between their centers or their bounds.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createDistributeContribution() {
        TabbarDistributeMenuManager distributeMenu = new TabbarDistributeMenuManager();
        return distributeMenu;
    }

    /**
     * Creates the Export As Image contribution item.This button can be used to export the current diagram as an image
     * file stored on disk.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createSaveAsImageContributionItem() {
        SaveAsImageFileAction saveAsImageFileAction = new SaveAsImageFileAction();
        return new ActionContributionItem(saveAsImageFileAction);
    }

    /**
     * Creates the {@link ModesMenuManager} allowing to choose a diagram editor edit mode.
     * 
     * @param editorDiagram
     *            the diagram from which the menu is made available.
     * @return the {@link ModesMenuManager}.
     */
    public IContributionItem createModesMenuManager(DDiagram editorDiagram) {
        ModesMenuManager modesMenu = null;
        modesMenu = new ModesMenuManager(EclipseUIUtil.getActivePage(), editorDiagram);
        modesMenu.setVisible(true);
        return modesMenu;
    }

    /**
     * Creates the Router contribution item. This action allows changing the line style of edges (Rectilinear, Oblique,
     * Tree)
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createRouterContribution() {
        TabbarRouterMenuManager routerMenu = new TabbarRouterMenuManager();
        routerMenu.setVisible(true);
        return routerMenu;
    }

    /**
     * Creates the Apply Style contribution item. Use this button to reproduce the visual style of an element onto
     * others.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createCopyAppearancePropertiesContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            SiriusCopyAppearancePropertiesAction copyAppearancePropertiesAction = new SiriusCopyAppearancePropertiesAction(site.getPage());
            return new ActionContributionItem(copyAppearancePropertiesAction);
        }
        return null;
    }

    /**
     * Creates the copy format contribution item. This tool can be used to duplicate the format of some diagram elements
     * from this diagram into another.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createCopyFormatContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            CopyFormatAction copyLayoutAction = new CopyFormatAction(site.getPage(), part);
            return new ActionContributionItem(copyLayoutAction);
        }
        return null;
    }

    /**
     * Creates the Paste Format contribution item.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createPasteFormatContribution(IDiagramWorkbenchPart part) {
        TabbarPasteFormatMenuManager pasteFormatMenu = new TabbarPasteFormatMenuManager();
        pasteFormatMenu.setVisible(true);
        return pasteFormatMenu;
    }

    /**
     * Creates the Refresh action contribution item. This operation, which can also be invoked with the F5 keyboard
     * shortcut, will force an update of the diagram's content according to the latest version of the underlying
     * semantic model.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createRefreshContribution() {
        RefreshDiagramAction action = new RefreshDiagramAction(SiriusDiagramActionBarContributor.REFRESH_DIAGRAM,
                DiagramUIPlugin.Implementation.getBundledImageDescriptor(DiagramImagesPath.REFRESH_IMG));
        return new ActionContributionItem(action);
    }

    /**
     * Creates the Make Same Size contribution item. When multiple elements are selected, clicking on this tool will
     * resize all of them to have the same size (both width and height).
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createSizeBothContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            SizeBothAction sizeBothAction = new SizeBothAction(site.getPage());
            sizeBothAction.init();
            return new ActionContributionItem(sizeBothAction);
        }
        return null;
    }

    /**
     * Creates the Auto-Size contribution item. This button marks the selected elements as auto-sized.
     * 
     * @param part
     *            the current IDiagramWorkbenchPart.
     * @return the {@link IContributionItem} or null if the workbench part is being initialized.
     */
    public IContributionItem createAutoSizeContribution(IDiagramWorkbenchPart part) {
        IWorkbenchPartSite site = part.getSite();
        if (site != null) {
            AutoSizeAction autoSizeAction = new SiriusAutoSizeAction(site.getPage());
            return new ActionContributionItem(autoSizeAction);
        }
        return null;
    }

    /**
     * Creates the straighten to contribution item. This menu contains several operations which can be used to
     * straighten to different positions edges.
     * 
     * @return the {@link IContributionItem}.
     */
    public IContributionItem createStraightenContribution() {
        TabbarStraightenToMenuManager straightenToMenuManager = new TabbarStraightenToMenuManager();
        straightenToMenuManager.setVisible(true);
        return straightenToMenuManager;
    }

}
