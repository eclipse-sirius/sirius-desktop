/*******************************************************************************
 * Copyright (c) 2008, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.draw2d.FigureCanvas;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.actions.ActionIds;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.gmf.runtime.diagram.ui.editparts.GraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.figures.ResizableCompartmentFigure;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.DDiagramQuery;
import org.eclipse.sirius.diagram.business.api.query.ToolSectionQuery;
import org.eclipse.sirius.diagram.description.Layer;
import org.eclipse.sirius.diagram.description.tool.ToolSection;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.view.RootLayoutData;
import org.eclipse.sirius.diagram.ui.edit.api.part.ISiriusEditPart;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.api.draw2d.ui.figures.FigureUtilities;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenuItem;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

/**
 * This menu contribution add a menu in the DDiagram diagram editor for each PopupMenu elements.
 * 
 * @author mporhel
 * 
 */
public class PopupMenuContribution implements IContributionItemProvider {
    /**
     * There is no real group in select menu of tabbar. To allow addition of actions after the 3 existing actions, the
     * specifier can use this group name.
     */
    private static final String DEFAULT_TABBAR_GROUP = "selectGroup"; //$NON-NLS-1$

    /**
     * True if this instance is called to contribute to tabbar, false otherwise. In case of tabbar contribution, only
     * "Popup menu" with a tabbar path are considered.
     */
    private boolean contributionToTabbar;

    /**
     * Default constructor.
     */
    public PopupMenuContribution() {
        // Do nothing.
    }

    /**
     * Constructor to specify the contribution mode.
     * 
     * @param contributionToTabbar
     *            True if this instance is called to contribute to tabbar, false otherwise
     */
    public PopupMenuContribution(boolean contributionToTabbar) {
        this.contributionToTabbar = contributionToTabbar;
    }

    @Override
    public void contributeToActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * Method to do the same as {@link #contributeToPopupMenu(IMenuManager, IWorkbenchPart)} but out of the extension
     * point context where it is originally used. Useful to use this kind of contribution in tabbar for example.
     * 
     * @param menu
     *            The target popup menu manager
     * @param part
     *            The context workbench part
     * @param contributeToTabbar
     *            true if the contribution is for tabbar, false if it is for the contextual menu.
     */
    public static void contributeToPopupMenuProgrammatically(final IMenuManager menu, final IWorkbenchPart part, final boolean contributeToTabbar) {
        new PopupMenuContribution(contributeToTabbar).contributeToPopupMenu(menu, part);
    }

    @Override
    public void contributeToPopupMenu(final IMenuManager menu, final IWorkbenchPart part) {
        if (part instanceof SiriusDiagramEditor) {

            final SiriusDiagramEditor diagrampart = (SiriusDiagramEditor) part;

            final Object adapter = diagrampart.getAdapter(IDiagramCommandFactoryProvider.class);
            if (adapter == null) {
                return;
            }
            final TransactionalEditingDomain transactionalEditingDomain = diagrampart.getEditingDomain();
            final IDiagramCommandFactoryProvider cmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
            final IDiagramCommandFactory emfCommandFactory = cmdFactoryProvider.getCommandFactory(transactionalEditingDomain);

            final EObject element = diagrampart.getDiagramEditPart().resolveSemanticElement();
            if (element instanceof DSemanticDiagram) {
                final DSemanticDiagram designerDiag = (DSemanticDiagram) element;
                final List<Layer> layers = new DDiagramQuery(designerDiag).getAllActivatedLayers();

                final Session session = SessionManager.INSTANCE.getSession(designerDiag.getTarget());

                final EList<ToolSection> sections = new BasicEList<ToolSection>();
                final EList<GroupMenu> activatedGroups = new BasicEList<GroupMenu>();
                final EList<PopupMenu> activatedPopupMenus = new BasicEList<PopupMenu>();
                final EList<OperationAction> activatedOperationActionMenus = new BasicEList<OperationAction>();
                final EList<ExternalJavaAction> activatedExternalJavaActionMenus = new BasicEList<ExternalJavaAction>();

                final EList<AbstractToolDescription> tools = designerDiag.getDescription().getReusedTools();
                computeReusedTools(tools, activatedGroups, activatedPopupMenus, activatedExternalJavaActionMenus, activatedOperationActionMenus);
                sections.addAll(new ToolSectionQuery(designerDiag.getDescription().getToolSection()).getAllSections());

                for (final Layer layer : layers) {
                    for (final ToolSection ts : layer.getToolSections()) {
                        sections.addAll(new ToolSectionQuery(ts).getAllSections());
                    }
                }

                final Iterator<ToolSection> lIterator = sections.iterator();
                while (lIterator.hasNext()) {
                    final ToolSection toolSection = lIterator.next();
                    activatedGroups.addAll(toolSection.getGroups());
                    activatedPopupMenus.addAll(toolSection.getPopupMenus());
                    ToolSectionQuery toolSectionQuery = new ToolSectionQuery(toolSection);
                    activatedOperationActionMenus.addAll(toolSectionQuery.getOperationActions(session));
                    activatedExternalJavaActionMenus.addAll(toolSectionQuery.getExternalJavaActions(session));
                    computeReusedTools(toolSection.getReusedTools(), activatedGroups, activatedPopupMenus, activatedExternalJavaActionMenus, activatedOperationActionMenus);
                }
                EditDomain domain = null;
                EditPart primarySelection = null;

                final Collection<DSemanticDecorator> selectedViews = new ArrayList<DSemanticDecorator>(diagrampart.getDiagramGraphicalViewer().getSelectedEditParts().size());
                for (final EditPart editpart : (Iterable<EditPart>) diagrampart.getDiagramGraphicalViewer().getSelectedEditParts()) {
                    if (EditPart.SELECTED_PRIMARY == editpart.getSelected()) {
                        primarySelection = editpart;
                    }
                    domain = editpart.getViewer().getEditDomain();
                    if (editpart instanceof IGraphicalEditPart && editpart instanceof ISiriusEditPart) {
                        final IGraphicalEditPart curPart = (IGraphicalEditPart) editpart;
                        final EObject designerObj = curPart.resolveSemanticElement();
                        if (designerObj instanceof DSemanticDecorator) {
                            selectedViews.add((DSemanticDecorator) designerObj);
                        }
                    }
                }
                // No selected edit parts : diagram is selected
                if (diagrampart.getDiagramGraphicalViewer().getSelectedEditParts().size() == 0) {
                    domain = diagrampart.getDiagramEditPart().getViewer().getEditDomain();
                    selectedViews.add(designerDiag);
                    primarySelection = diagrampart.getDiagramEditPart();
                }
                if (domain != null && selectedViews.size() > 0) {

                    Point relativeCursorLocationToPrimarySelection = getCurrentLocation(primarySelection);

                    contributeToPopupMenu(menu, emfCommandFactory, session, activatedGroups, activatedPopupMenus, activatedOperationActionMenus, activatedExternalJavaActionMenus, domain,
                            primarySelection, selectedViews, relativeCursorLocationToPrimarySelection);
                }

            } else {
                // no focused edit part
            }
        }
    }

    // CHECKSTYLE:OFF
    private void contributeToPopupMenu(final IMenuManager menu, final IDiagramCommandFactory emfCommandFactory, final Session session, final EList<GroupMenu> activatedGroups,
            final EList<PopupMenu> activatedPopupMenus, final EList<OperationAction> activatedOperationActionMenus, final EList<ExternalJavaAction> activatedExternalJavaActionMenus, EditDomain domain,
            EditPart primarySelection, final Collection<DSemanticDecorator> selectedViews, Point relativeCursorLocationToPrimarySelection) {
        // CHECKSTYLE:ON
        if (!contributionToTabbar) {
            for (final PopupMenu popMenu : new LinkedHashSet<>(activatedPopupMenus)) {
                addPopupMenu(popMenu, menu, null, domain, selectedViews, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
            }
        }
        for (final GroupMenu group : new LinkedHashSet<>(activatedGroups)) {
            addItemToMenu(group, menu, null, selectedViews, domain, emfCommandFactory, session.getInterpreter(), primarySelection, relativeCursorLocationToPrimarySelection);
        }
        if (!contributionToTabbar) {
            for (final OperationAction operationAction : new LinkedHashSet<>(activatedOperationActionMenus)) {
                addItemToMenu(operationAction, menu, null, selectedViews, domain, emfCommandFactory, session.getInterpreter(), primarySelection, relativeCursorLocationToPrimarySelection);

            }
            for (final ExternalJavaAction externalJavaAction : new LinkedHashSet<>(activatedExternalJavaActionMenus)) {
                addItemToMenu(externalJavaAction, menu, null, selectedViews, domain, emfCommandFactory, session.getInterpreter(), primarySelection, relativeCursorLocationToPrimarySelection);
            }
        }
    }

    /**
     * Computes the location where the element must be created: a location relative to the primary edit part.
     * 
     * @param primarySelection
     *            The first selected edit part
     * @return the location where the element must be created.
     */
    protected Point getCurrentLocation(EditPart primarySelection) {
        org.eclipse.swt.graphics.Point cursorLocation = PlatformUI.getWorkbench().getDisplay().getCursorLocation();
        EditPartViewer viewer = primarySelection.getRoot().getViewer();
        final FigureCanvas control = (FigureCanvas) viewer.getControl();
        final org.eclipse.swt.graphics.Point screenRelativeSWTPoint = control.toControl(cursorLocation);
        EditPart editPartUnderMouse = viewer.findObjectAtExcluding(new Point(screenRelativeSWTPoint.x, screenRelativeSWTPoint.y), Collections.EMPTY_LIST);

        Point currentLocation = new Point(screenRelativeSWTPoint.x, screenRelativeSWTPoint.y);
        if (editPartUnderMouse instanceof GraphicalEditPart) {
            // Translate to relative parent coordinates
            final IFigure fig = ((GraphicalEditPart) editPartUnderMouse).getFigure();
            fig.translateToRelative(currentLocation);
            // Remove the origin parent coordinates
            final Point containerLocation = fig.getBounds().getLocation();

            currentLocation = new Point(currentLocation.x - Math.max(0, containerLocation.x), currentLocation.y - Math.max(0, containerLocation.y));
            // Eventually "scroll" the coordinates if there is scroll bar on
            // parent
            if (fig instanceof ResizableCompartmentFigure) {
                final Point scrollOffset = ((ResizableCompartmentFigure) fig).getScrollPane().getViewport().getViewLocation();
                final Point shiftFromMarginOffset = FigureUtilities.getShiftFromMarginOffset((ResizableCompartmentFigure) fig, false, editPartUnderMouse);
                currentLocation = new Point(currentLocation.x + scrollOffset.x - shiftFromMarginOffset.x, currentLocation.y + scrollOffset.y - shiftFromMarginOffset.y);
            }
        }

        return currentLocation;
    }

    private void computeReusedTools(final EList<? extends ToolEntry> reusedTools, final EList<GroupMenu> activatedGroups, final EList<PopupMenu> activatedPopupMenus,
            final EList<ExternalJavaAction> activatedExternalJavaActionMenus, final EList<OperationAction> activatedOperationActionMenus) {
        for (ToolEntry abstractToolDescription : reusedTools) {
            if (abstractToolDescription instanceof GroupMenu) {
                activatedGroups.add((GroupMenu) abstractToolDescription);
            } else if (abstractToolDescription instanceof PopupMenu) {
                activatedPopupMenus.add((PopupMenu) abstractToolDescription);
            } else if (abstractToolDescription instanceof OperationAction) {
                activatedOperationActionMenus.add((OperationAction) abstractToolDescription);
            } else if (abstractToolDescription instanceof ExternalJavaAction) {
                activatedExternalJavaActionMenus.add((ExternalJavaAction) abstractToolDescription);
            }
        }
    }

    private Boolean preconditionHolds(AbstractToolDescription menuOrGroupItem, Collection<DSemanticDecorator> selectedViews, EObject semantic, IInterpreter interpreter) {
        final Boolean precondition;
        if (StringUtil.isEmpty(menuOrGroupItem.getPrecondition())) {
            precondition = true;
        } else {
            if (menuOrGroupItem instanceof OperationAction) {
                interpreter.setVariable(((OperationAction) menuOrGroupItem).getView().getName(), selectedViews);
            }
            try {
                precondition = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, menuOrGroupItem, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            } finally {
                if (menuOrGroupItem instanceof OperationAction) {
                    interpreter.unSetVariable(((OperationAction) menuOrGroupItem).getView().getName());
                }
            }
        }
        return precondition;
    }

    /**
     * Add a group or an action in the <code>parentMenu</code>
     * 
     * @param menuItemDescription
     *            the menu item which must receive an action
     * @param designerDiag
     *            the current diagram
     * @param selectedViews
     *            the semantic viewpoint element under focus.
     * @param domain
     *            the edit domain used for command execution
     * @param emfCommandFactory
     */
    // CHECKSTYLE:OFF
    private void addItemToMenu(final AbstractToolDescription menuOrGroupItem, IMenuManager parentMenu, final String groupId, final Collection<DSemanticDecorator> selectedViews,
            final EditDomain domain, final IDiagramCommandFactory emfCommandFactory, final IInterpreter interpreter, EditPart primarySelection, Point currentMouseLocation) {
        // CHECKSTYLE:ON
        final EObject semantic = selectedViews.iterator().next().getTarget();
        if (interpreter != null && preconditionHolds(menuOrGroupItem, selectedViews, semantic, interpreter)) {
            if (menuOrGroupItem instanceof GroupMenu) {
                addGroup((GroupMenu) menuOrGroupItem, parentMenu, domain, selectedViews, emfCommandFactory, primarySelection, currentMouseLocation);
            } else if (menuOrGroupItem instanceof PopupMenu) {
                addPopupMenu((PopupMenu) menuOrGroupItem, parentMenu, groupId, domain, selectedViews, emfCommandFactory, primarySelection, currentMouseLocation);
            } else if (menuOrGroupItem instanceof ExternalJavaAction) {
                final ExternalJavaAction javaAction = (ExternalJavaAction) menuOrGroupItem;
                if (javaAction.getId() != null && !"".equalsIgnoreCase(javaAction.getId())) { //$NON-NLS-1$
                    IAction action = buildJavaAction(javaAction, selectedViews, domain, emfCommandFactory, interpreter, currentMouseLocation);
                    if (action != null) {
                        addActionInGroup(parentMenu, groupId, action);
                    }
                }
            } else if (menuOrGroupItem instanceof OperationAction) {
                final OperationAction operation = (OperationAction) menuOrGroupItem;
                if (operation.getInitialOperation().getFirstModelOperations() != null) {
                    IAction action = buildOperationAction(operation, selectedViews, domain, emfCommandFactory, primarySelection, currentMouseLocation);
                    if (action != null) {
                        addActionInGroup(parentMenu, groupId, action);
                    }
                }
            }
        }
    }

    /**
     * 
     * Builds java actions of popup menus from active layers.
     * 
     * @param menuItemDescription
     *            the menu item which must receive an action
     * @param designerDiag
     *            the current diagram
     * @param designerObj
     *            the semantic viewpoint element under focus.
     * @param domain
     *            the edit domain used for execution
     * @param emfCommandFactory
     */
    private IAction buildJavaAction(final ExternalJavaAction javaActionMenuItem, final Collection<DSemanticDecorator> selectedViews, final EditDomain domain,
            final IDiagramCommandFactory emfCommandFactory, final IInterpreter interpreter, Point currentMouseLocation) {
        final IExternalJavaAction javaAction = ExternalJavaActionProvider.INSTANCE.getJavaActionById(javaActionMenuItem.getId());
        IAction result = null;
        ImageDescriptor imageDescriptor = null;
        if (javaAction != null) {
            final Command command = emfCommandFactory.buildJavaActionFromTool(javaActionMenuItem, selectedViews, javaAction);
            final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(selectedViews.iterator().next());
            final GMFCommandWrapper gefCommandWrapper = new GMFCommandWrapper(transactionalEditingDomain, command);
            if (command.canExecute()) {
                if (javaActionMenuItem.getIcon() != null && !"".equals(javaActionMenuItem.getIcon())) { //$NON-NLS-1$
                    imageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor(javaActionMenuItem.getIcon());
                }
                result = new Action(new IdentifiedElementQuery(javaActionMenuItem).getLabel(), imageDescriptor) {

                    @Override
                    public void run() {
                        super.run();
                        domain.getCommandStack().execute(new ICommandProxy(gefCommandWrapper));
                    }
                };
            }
        }
        return result;
    }

    /**
     * 
     * Builds operation actions of popup menus from active layers.
     * 
     * @param menuItemDescription
     *            the menu item which must receive an action
     * @param designerDiag
     *            the current diagram
     * @param designerObj
     *            the semantic viewpoint element under focus.
     * @param domain
     *            the edit domain used for execution
     * @param emfCommandFactory
     */
    private IAction buildOperationAction(final OperationAction operationAction, final Collection<DSemanticDecorator> selectedViews, final EditDomain domain,
            final IDiagramCommandFactory emfCommandFactory, final EditPart primarySelection, final Point currentMouseLocation) {
        final TransactionalEditingDomain transactionalEditingDomain = TransactionUtil.getEditingDomain(selectedViews.iterator().next());
        final Command command = emfCommandFactory.buildOperationActionFromTool(operationAction, selectedViews);
        final CompoundCommand cc = new CompoundCommand(command.getLabel());
        cc.add(new org.eclipse.gef.commands.Command(Messages.PopupMenuContribution_storeMouseLocationCmdLabel) {
            @Override
            public void execute() {
                SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(primarySelection, currentMouseLocation.getCopy(), new Dimension(-1, -1)));
            }
        });
        cc.add(new ICommandProxy(new GMFCommandWrapper(transactionalEditingDomain, command)));
        ImageDescriptor imageDescriptor = null;
        if (operationAction.getIcon() != null && !"".equals(operationAction.getIcon())) { //$NON-NLS-1$
            imageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor(operationAction.getIcon());
        }
        return new Action(MessageTranslator.INSTANCE.getMessage(operationAction, new IdentifiedElementQuery(operationAction).getLabel()), imageDescriptor) {
            @Override
            public void run() {
                super.run();
                domain.getCommandStack().execute(cc);
            }
        };

    }

    @Override
    public void disposeContributions(final IWorkbenchPartDescriptor arg0) {

    }

    @Override
    public void updateActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    @Override
    public void addProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    @Override
    public boolean provides(final IOperation arg0) {
        // Always provide
        return true;
    }

    @Override
    public void removeProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    /**
     * Add the action in the group <code>groupId</code> of the menu <code>parentMenu</code>.
     * 
     * @param parentMenu
     *            The menu in which to add the new action.
     * @param groupId
     *            The group of the menu in which to add the new action.
     * @param action
     *            The new action
     */
    private void addActionInGroup(final IMenuManager parentMenu, final String groupId, IAction action) {
        if (contributionToTabbar && DEFAULT_TABBAR_GROUP.equals(groupId)) {
            parentMenu.insertAfter(ActionIds.ACTION_TOOLBAR_SELECT_ALL_SHAPES, action);
        } else {
            try {
                parentMenu.appendToGroup(groupId, action);
            } catch (IllegalArgumentException e) {
                // Group not found, use additions as fallback
                try {
                    parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action);
                } catch (IllegalArgumentException e2) {
                    // If the group additions does not exist, we create it.
                    parentMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
                    parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action);
                }
            }
        }
    }

    /**
     * Add the action in the group <code>groupId</code> of the menu <code>parentMenu</code>.
     * 
     * @param parentMenu
     *            The menu in which to add the new action.
     * @param groupId
     *            The group of the menu in which to add the new action.
     * @param menu
     *            The new menu
     */
    private void addMenuInGroup(final IMenuManager parentMenu, final String groupId, IContributionItem menu) {
        try {
            parentMenu.appendToGroup(groupId, menu);
        } catch (IllegalArgumentException e) {
            // Group not found, use additions as fallback
            try {
                parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
            } catch (IllegalArgumentException e2) {
                // If the group additions does not exist, we create it.
                parentMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
                parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, menu);
            }
        }
    }

    /**
     * Builds content of popup menus from active layers and add it in the "additions" group of the current
     * <code>parentMenu</code>.<BR/>
     * 
     * @param popupMenu
     *            The description of the PopupMenu to add
     * @param parentMenu
     *            The menu in which to add the PopupMenu and its content.
     * @param domain
     *            the edit domain used for execution of the actions contained in this PopupMenu
     * @param selectedViews
     *            the DRepresentationElement under focus.
     * @param emfCommandFactory
     *            The factory used to build the command corresponding to the actions contained in this PopupMenu
     * @param primarySelection
     *            The first selected edit part
     * @param currentMouseLocation
     *            The mouse location (used if some elements are created with contextual action)
     */
    // CHECKSTYLE:OFF
    private void addPopupMenu(PopupMenu popupMenu, IMenuManager parentMenu, String groupId, final EditDomain domain, final Collection<DSemanticDecorator> selectedViews,
            final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation) {
        // CHECKSTYLE:ON
        final EObject semantic = selectedViews.iterator().next().getTarget();

        final EList<MenuItemDescription> activatedGroupsOrActions = popupMenu.getMenuItemDescription();

        IInterpreter interpreter = null;
        if (semantic != null && semantic.eResource() != null) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
        } else if (semantic instanceof DMappingBased) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(((DMappingBased) semantic).getMapping());
        }

        final Boolean isMenuPreconditionValidated;
        if (StringUtil.isEmpty(popupMenu.getPrecondition())) {
            isMenuPreconditionValidated = true;
        } else {
            isMenuPreconditionValidated = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, popupMenu, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }

        if (isMenuPreconditionValidated) {
            // Search if a menu with the same id already exists. In this case, we reuse it.
            String menuId = popupMenu.getName();
            IMenuManager subMenu = parentMenu.findMenuUsingPath(menuId);
            if (subMenu == null) {
                subMenu = new MenuManager(MessageTranslator.INSTANCE.getMessage(popupMenu, new IdentifiedElementQuery(popupMenu).getLabel()), menuId);
                // Add a default "additions" group
                subMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
            }

            final Iterator<MenuItemDescription> mIterator = activatedGroupsOrActions.iterator();
            while (mIterator.hasNext()) {
                final MenuItemDescription menuItemDescription = mIterator.next();
                addItemToMenu(menuItemDescription, subMenu, null, selectedViews, domain, emfCommandFactory, interpreter, primarySelection, currentMouseLocation);
            }
            // finalize
            if (!(parentMenu.equals(subMenu)) && subMenu instanceof ContributionManager && ((ContributionManager) subMenu).getSize() > 0) {
                subMenu.setVisible(true);
                addMenuInGroup(parentMenu, groupId, subMenu);
            }
        }
    }

    /**
     * Builds contents of popup menus from active layers.<BR/>
     * 
     * @param domain
     *            the edit domain used for execution
     * @param selectedViews
     *            the semantic viewpoint element under focus.
     * @param emfCommandFactory
     * @param primarySelection
     *            The first selected edit part
     * @param currentMouseLocation
     *            The mouse location (used if some elements are created with contextual action)
     * @return an optional IContributionItem.
     */
    private void addGroup(final GroupMenu group, IMenuManager parentMenu, final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final IDiagramCommandFactory emfCommandFactory,
            EditPart primarySelection, Point currentMouseLocation) {
        final EObject semantic = selectedViews.iterator().next().getTarget();
        IMenuManager menuContainingNewGroup = null;
        String locationURI = group.getLocationURI();
        if (group.eContainer() instanceof PopupMenu) {
            if (locationURI != null && !locationURI.isEmpty()) {
                // The location URI must be null/empty when it is located in a PopupMenu
                DiagramUIPlugin.getPlugin()
                        .log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, IStatus.OK,
                                MessageFormat.format(Messages.Group_Not_Displayed, group.getName(),
                                        MessageFormat.format(org.eclipse.sirius.viewpoint.Messages.Constraint_validNullLocationURIForGroupInPopupMenuConstraint_message, group.getLocationURI())),
                                null));
            } else {
                menuContainingNewGroup = parentMenu;
            }
        } else {
            try {
                LocationURI locationURIQuery = new LocationURI(locationURI);
                if (!contributionToTabbar && locationURIQuery.getMenuId().isPresent()) {
                    String menuId = locationURIQuery.getMenuId().get();
                    if (LocationURI.ROOT_MENU_ID.equals(menuId)) {
                        menuContainingNewGroup = parentMenu;
                    } else {
                        menuContainingNewGroup = parentMenu.findMenuUsingPath(menuId);
                        if (menuContainingNewGroup == null) {
                            DiagramUIPlugin.getPlugin().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, IStatus.OK,
                                    MessageFormat.format(Messages.Group_Not_Displayed, group.getName(), MessageFormat.format(Messages.Group_No_Menu_ID, menuId)), null));
                        }
                    }
                } else if (contributionToTabbar && locationURIQuery.getTabbarId().isPresent()) {
                    if (locationURIQuery.getTabbarId().get().equals(parentMenu.getId())) {
                        menuContainingNewGroup = parentMenu;
                    }
                }
            } catch (URISyntaxException e) {
                DiagramUIPlugin.getPlugin().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, IStatus.OK, MessageFormat.format(Messages.Group_Not_Displayed, group.getName(), e.getMessage()), e));
            }
        }

        if (menuContainingNewGroup != null) {
            // Search if the group already exist (reuse it if it is the case).
            IContributionItem existingSeparator = menuContainingNewGroup.findUsingPath(group.getName());
            if (!(existingSeparator instanceof Separator) && !(contributionToTabbar && DEFAULT_TABBAR_GROUP.equals(group.getName()))) {
                // Add a new separator group in the desired menu
                menuContainingNewGroup.add(new Separator(group.getName()));
            }

            // Add actions or menus in the group
            final EList<GroupMenuItem> activatedMenusOrActions = group.getItemDescriptions();

            IInterpreter interpreter = null;
            if (semantic != null && semantic.eResource() != null) {
                interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
            } else if (semantic instanceof DMappingBased) {
                interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(((DMappingBased) semantic).getMapping());
            }

            final Boolean isGroupPreconditionValidated;
            if (StringUtil.isEmpty(group.getPrecondition())) {
                isGroupPreconditionValidated = true;
            } else {
                isGroupPreconditionValidated = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, group, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            }

            if (isGroupPreconditionValidated) {
                final Iterator<GroupMenuItem> mIterator = activatedMenusOrActions.iterator();
                while (mIterator.hasNext()) {
                    final GroupMenuItem menuItemDescription = mIterator.next();
                    addItemToMenu(menuItemDescription, menuContainingNewGroup, group.getName(), selectedViews, domain, emfCommandFactory, interpreter, primarySelection, currentMouseLocation);
                }
            }
        }

    }
}
