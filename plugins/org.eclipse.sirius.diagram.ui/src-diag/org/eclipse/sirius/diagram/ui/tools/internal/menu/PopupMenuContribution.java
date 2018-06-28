/*******************************************************************************
 * Copyright (c) 2008, 2018 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

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
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
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
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.viewpoint.DMappingBased;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractToolDescription;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.GroupMenu;
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
     * VM argument name to activate the POC of bugzilla 529992.
     */
    public static final String POPUP_MENU_IMPROVEMENT_ID = "org.eclipse.sirius.experimental.popupMenuImprovement"; //$NON-NLS-1$

    /**
     * Constant used for POC of bugzilla 529992 to separate group and menu id in path.>
     */
    private static final String PATH_SEPARATOR = "/"; //$NON-NLS-1$

    /**
     * Constant used for POC of bugzilla 529992 to insert action in a specific group or in an existing menu. The path is
     * relative to the current menu. It is used in documentation field of an operation action with this kind of format:
     * <UL>
     * <LI><code>path=/mySpecificGroupId</code> to add it in the group mySpecificGroupId of the current menu</LI>
     * <LI><code>path=myMenu/mySpecificGroupId</code> to add it in the group mySpecificGroupId of the sub menu myMenu of
     * the current menu</LI>
     * </UL>
     */
    private static final String PATH_PREFIX = "path"; //$NON-NLS-1$

    /**
     * Constant used for POC of bugzilla 529992 to insert a group declared as a "Popup Menu" in a specific existing
     * dropdown menu of tabbar. Currently only "Select" dropdown menu car be used ("tabbarPath=Select").
     */
    private static final String TABBAR_PATH_PREFIX = "tabbarPath"; //$NON-NLS-1$

    /**
     * Constant used for POC of bugzilla 529992 to define a group in a Popup Menu. It is used in documentation field of
     * a Popup Menu with this kind of format:<BR/>
     * <code>group=mySpecificGroupId</code>
     */
    private static final String GROUP_PREFIX = "group"; //$NON-NLS-1$

    /**
     * Constant used for POC of bugzilla 529992 to define the root of the menu. It allows to define a Popup Menu only to
     * define a group.
     */
    private static final String ROOT_MENU_ID = "/"; //$NON-NLS-1$

    /**
     * Field used only in case of POC of bugzilla 529992.<BR/>
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
     */
    public static void contributeToPopupMenuProgrammatically(final IMenuManager menu, final IWorkbenchPart part) {
        new PopupMenuContribution(true).contributeToPopupMenu(menu, part);
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

                    if (!Boolean.getBoolean(POPUP_MENU_IMPROVEMENT_ID)) {
                        contributeToPopupMenuNotImproved(menu, emfCommandFactory, activatedPopupMenus, activatedOperationActionMenus, activatedExternalJavaActionMenus, domain, primarySelection,
                                selectedViews, relativeCursorLocationToPrimarySelection);
                    } else {
                        contributeToPopupMenuImproved(menu, emfCommandFactory, session, activatedGroups, activatedPopupMenus, activatedOperationActionMenus, activatedExternalJavaActionMenus, domain,
                                primarySelection, selectedViews, relativeCursorLocationToPrimarySelection);
                    }
                }
            } else {
                // no focused edit part
            }
        }
    }

    // CHECKSTYLE:OFF
    private void contributeToPopupMenuNotImproved(final IMenuManager menu, final IDiagramCommandFactory emfCommandFactory, final EList<PopupMenu> activatedPopupMenus,
            final EList<OperationAction> activatedOperationActionMenus, final EList<ExternalJavaAction> activatedExternalJavaActionMenus, EditDomain domain, EditPart primarySelection,
            final Collection<DSemanticDecorator> selectedViews, Point relativeCursorLocationToPrimarySelection) {
        // CHECKSTYLE:ON
        for (final PopupMenu popMenu : new LinkedHashSet<>(activatedPopupMenus)) {
            Option<? extends IContributionItem> contributionItem = buildContributionItemToAdd(domain, selectedViews, popMenu, emfCommandFactory, primarySelection,
                    relativeCursorLocationToPrimarySelection);
            if (contributionItem.some()) {
                menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, contributionItem.get());
            }
        }
        for (final OperationAction operationAction : new LinkedHashSet<>(activatedOperationActionMenus)) {
            Option<IAction> action = buildActionToAdd(domain, selectedViews, operationAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
            if (action.some()) {
                menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
            }
        }
        for (final ExternalJavaAction externalJavaAction : new LinkedHashSet<>(activatedExternalJavaActionMenus)) {
            Option<IAction> action = buildActionToAdd(domain, selectedViews, externalJavaAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
            if (action.some()) {
                menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
            }
        }
    }

    // CHECKSTYLE:OFF
    private void contributeToPopupMenuImproved(final IMenuManager menu, final IDiagramCommandFactory emfCommandFactory, final Session session, final EList<GroupMenu> activatedGroups,
            final EList<PopupMenu> activatedPopupMenus, final EList<OperationAction> activatedOperationActionMenus, final EList<ExternalJavaAction> activatedExternalJavaActionMenus, EditDomain domain,
            EditPart primarySelection, final Collection<DSemanticDecorator> selectedViews, Point relativeCursorLocationToPrimarySelection) {
        // CHECKSTYLE:ON
        for (final PopupMenu popMenu : new LinkedHashSet<>(activatedPopupMenus)) {
            Option<? extends IContributionItem> contributionItem = buildContributionItemToAdd(menu, domain, selectedViews, popMenu, emfCommandFactory, primarySelection,
                    relativeCursorLocationToPrimarySelection);
            addSubMenuInMenu(menu, popMenu.getDocumentation(), contributionItem);
        }
        for (final OperationAction operationAction : new LinkedHashSet<>(activatedOperationActionMenus)) {
            Optional<IAction> action = buildActionToAdd(domain, selectedViews, operationAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection,
                    session.getInterpreter());
            addActionInMenu(menu, operationAction.getDocumentation(), action);
        }
        for (final ExternalJavaAction externalJavaAction : new LinkedHashSet<>(activatedExternalJavaActionMenus)) {
            Optional<IAction> action = buildActionToAdd(domain, selectedViews, externalJavaAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection,
                    session.getInterpreter());
            addActionInMenu(menu, externalJavaAction.getDocumentation(), action);
        }
        for (final GroupMenu group : new LinkedHashSet<>(activatedGroups)) {
            addGroup(menu, domain, selectedViews, group, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
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
        final FigureCanvas control = (FigureCanvas) primarySelection.getRoot().getViewer().getControl();
        final org.eclipse.swt.graphics.Point screenRelativeSWTPoint = control.toControl(cursorLocation);
        EditPart editPartUnderMouse = primarySelection.getRoot().getViewer().findObjectAtExcluding(new Point(screenRelativeSWTPoint.x, screenRelativeSWTPoint.y), Collections.EMPTY_LIST);

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

    /**
     * Builds contents of popup menus from active layers.
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
    private Option<? extends IContributionItem> buildContributionItemToAdd(final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final PopupMenu popupMenu,
            final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation) {

        final EObject semantic = selectedViews.iterator().next().getTarget();
        final MenuManager subMenu = new MenuManager(MessageTranslator.INSTANCE.getMessage(popupMenu, new IdentifiedElementQuery(popupMenu).getLabel()), popupMenu.getName().toLowerCase());
        final EList<MenuItemDescription> activatedAction = popupMenu.getMenuItemDescription();

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
            final Iterator<MenuItemDescription> mIterator = activatedAction.iterator();
            while (mIterator.hasNext()) {
                final MenuItemDescription menuItemDescription = mIterator.next();
                if (interpreter != null && preconditionHolds(menuItemDescription, selectedViews, semantic, interpreter)) {
                    final IAction action = buildMenuItemAction(menuItemDescription, selectedViews, domain, emfCommandFactory, interpreter, primarySelection, currentMouseLocation);
                    if (action != null) {
                        subMenu.add(action);
                    }
                }
            }
        } else {
            subMenu.removeAll();
        }
        // finalize
        if (subMenu.getSize() > 0) {
            subMenu.setVisible(true);
            return Options.newSome(subMenu);
        } else {
            return Options.newNone();
        }
    }

    private Boolean preconditionHolds(MenuItemDescription menuItemDescription, Collection<DSemanticDecorator> selectedViews, EObject semantic, IInterpreter interpreter) {
        final Boolean precondition;
        if (StringUtil.isEmpty(menuItemDescription.getPrecondition())) {
            precondition = true;
        } else {
            if (menuItemDescription instanceof OperationAction) {
                interpreter.setVariable(((OperationAction) menuItemDescription).getView().getName(), selectedViews);
            }
            try {
                precondition = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, menuItemDescription, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            } finally {
                if (menuItemDescription instanceof OperationAction) {
                    interpreter.unSetVariable(((OperationAction) menuItemDescription).getView().getName());
                }
            }
        }
        return precondition;
    }

    /**
     * Builds contents of popup menus from menuItemDescription.
     * 
     * @param domain
     *            the edit domain used for execution
     * @param selectedViews
     *            the semantic viewpoint element under focus.
     */
    private Option<IAction> buildActionToAdd(final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final MenuItemDescription menuItemDescription,
            final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation) {
        final EObject semantic = selectedViews.iterator().next().getTarget();
        IInterpreter interpreter = null;
        if (semantic != null && semantic.eResource() != null) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
        } else if (semantic instanceof DMappingBased) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(((DMappingBased) semantic).getMapping());
        }
        if (interpreter != null && preconditionHolds(menuItemDescription, selectedViews, semantic, interpreter)) {
            final IAction action = buildMenuItemAction(menuItemDescription, selectedViews, domain, emfCommandFactory, interpreter, primarySelection, currentMouseLocation);
            if (action != null) {
                return Options.newSome(action);
            }
        }
        return Options.newNone();
    }

    /**
     * 
     * Builds actions of popup menus from active layers.
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
    private IAction buildMenuItemAction(final MenuItemDescription menuItemDescription, final Collection<DSemanticDecorator> selectedViews, final EditDomain domain,
            final IDiagramCommandFactory emfCommandFactory, final IInterpreter interpreter, EditPart primarySelection, Point currentMouseLocation) {
        IAction result = null;
        if (menuItemDescription instanceof ExternalJavaAction) {
            final ExternalJavaAction javaAction = (ExternalJavaAction) menuItemDescription;
            if (javaAction.getId() != null && !"".equalsIgnoreCase(javaAction.getId())) { //$NON-NLS-1$
                result = buildJavaAction(javaAction, selectedViews, domain, emfCommandFactory, interpreter, currentMouseLocation);
            }
        } else if (menuItemDescription instanceof OperationAction) {
            final OperationAction operation = (OperationAction) menuItemDescription;
            if (operation.getInitialOperation().getFirstModelOperations() != null) {
                result = buildOperationAction(operation, selectedViews, domain, emfCommandFactory, primarySelection, currentMouseLocation);
            }
        }
        return result;
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
     * Add the menu, if it is a new one, in the <code>parentMenu</code>.<BR/>
     * Method added in case of POC of bug 529992.
     * 
     * @param parentMenu
     *            The menu in which to add the new menu.
     * @param documentationOptions
     *            Used only for POC of bug 529992
     * @param contributionItem
     *            The new menu
     */
    private void addSubMenuInMenu(final IMenuManager parentMenu, final String documentationOptions, Option<? extends IContributionItem> contributionItem) {
        if (contributionItem.some()) {
            if (!(parentMenu.equals(contributionItem)) && parentMenu.find(contributionItem.get().getId()) == null) {
                // It is not an already existing menu, add it to the corresponding group
                try {
                    parentMenu.appendToGroup(getGroupId(getPath(documentationOptions)), contributionItem.get());
                } catch (IllegalArgumentException e) {
                    // Group not found, use additions as fallback
                    parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, contributionItem.get());
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
     * @param action
     *            The new action
     */
    private void addActionInGroup(final IMenuManager parentMenu, final String groupId, Optional<IAction> action) {
        if (action.isPresent()) {
            try {
                parentMenu.appendToGroup(groupId, action.get());
            } catch (IllegalArgumentException e) {
                // Group not found, use additions as fallback
                parentMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
            }
        }
    }

    /**
     * Add the action in the <code>parentMenu</code>.<BR/>
     * Method added in case of POC of bug 529992.
     * 
     * @param parentMenu
     *            The menu in which to add the new action.
     * @param documentationOptions
     *            Used only for POC of bug 529992
     * @param action
     *            The new action
     */
    private void addActionInMenu(final IMenuManager parentMenu, final String documentationOptions, Optional<IAction> action) {
        if (action.isPresent()) {
            Optional<String> optionalPath = getPath(documentationOptions);
            IMenuManager insertMenu = getMenuManager(parentMenu, optionalPath);
            try {
                insertMenu.appendToGroup(getGroupId(optionalPath), action.get());
            } catch (IllegalArgumentException e) {
                // Group not found, use additions as fallback
                insertMenu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
            }
        }
    }

    private Optional<String> getPropertyValueFromString(String documentationOptions, String key) {
        try {
            Properties docProps = new Properties();
            StringReader docReader = new StringReader(documentationOptions);
            try {
                docProps.load(docReader);
            } finally {
                docReader.close();
            }
            String value = docProps.getProperty(key);
            if (value != null) {
                return Optional.of(value);
            }
        } catch (IOException e) {
            // Do nothing
        }
        return Optional.empty();
    }

    private Optional<String> getTabbarPath(String documentationOptions) {
        return getPropertyValueFromString(documentationOptions, TABBAR_PATH_PREFIX);
    }

    private Optional<String> getPath(String documentationOptions) {
        return getPropertyValueFromString(documentationOptions, PATH_PREFIX);
    }

    private Optional<String> getGroupIdToCreate(String documentationOptions) {
        return getPropertyValueFromString(documentationOptions, GROUP_PREFIX);
    }

    private IMenuManager getMenuManager(IMenuManager menu, Optional<String> optionalPath) {
        IMenuManager result = menu;
        if (optionalPath.isPresent()) {
            String[] pathSegments = optionalPath.get().split(PATH_SEPARATOR);
            for (int i = 0; i < pathSegments.length - 1; i++) {
                IMenuManager foundMenu = result.findMenuUsingPath(pathSegments[i]);
                if (foundMenu == null) {
                    break;
                }
                result = foundMenu;
            }
        }
        return result;
    }

    /**
     * Return the groupId of the <code>optionalPath</code>. "additions" is returned if no groupId is found in the
     * path.<BR/>
     * Examples of path with corresponding groupID:
     * <UL>
     * <LI>path="myGroupId" --> groupId ==> "myGroupId"</LI>
     * <LI>path="/myGroupId" --> groupId ==> ""</LI>
     * <LI>path="/myMenuId/myGroupId" --> groupId ==> "myGroupId"</LI>
     * <LI>path="/myMenuId1/myMenuId2/myMenuId3/myGroupId" --> groupId ==> "myGroupId"</LI>
     * </UL>
     * 
     * @param optionalPath
     *            The optional path from which to retreive the groupID
     * @return the groupId of the <code>optionalPath</code>. "additions" is returned if no groupId is found in the path.
     */
    private String getGroupId(Optional<String> optionalPath) {
        String groupId = IWorkbenchActionConstants.MB_ADDITIONS;
        if (optionalPath.isPresent()) {
            if (!optionalPath.get().contains(PATH_SEPARATOR) && optionalPath.get().length() > 0) {
                groupId = optionalPath.get();
            } else {
                String[] pathSegments = optionalPath.get().split(PATH_SEPARATOR);
                if (pathSegments.length > 1) {
                    groupId = pathSegments[pathSegments.length - 1];
                }
            }
        }
        return groupId;
    }

    /**
     * Builds contents of popup menus from active layers.<BR/>
     * Method added in case of POC of bug 529992 (initially a copy of
     * {@link #buildContributionItemToAdd(EditDomain, Collection, PopupMenu, IDiagramCommandFactory, EditPart, Point)}.
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
    private Option<? extends IContributionItem> buildContributionItemToAdd(IMenuManager parentMenu, final EditDomain domain, final Collection<DSemanticDecorator> selectedViews,
            final PopupMenu popupMenu, final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation) {
        final EObject semantic = selectedViews.iterator().next().getTarget();
        // Search if a menu with the same id already exists. In this case, we reuse it.
        String menuId = popupMenu.getName();
        Optional<String> tabbarPath = getTabbarPath(popupMenu.getDocumentation());
        Option<? extends IContributionItem> result;
        if (contributionToTabbar && !tabbarPath.isPresent()) {
            // For tabbar contribution, ignore contribution without tabbar path.
            result = Options.newNone();
        } else {
            IMenuManager subMenu;
            if (ROOT_MENU_ID.equals(menuId)) {
                subMenu = parentMenu;
            } else if (tabbarPath.isPresent() && tabbarPath.get().equals(parentMenu.getId())) {
                subMenu = parentMenu;
            } else {
                subMenu = parentMenu.findMenuUsingPath(menuId);
                if (subMenu == null) {
                    subMenu = new MenuManager(MessageTranslator.INSTANCE.getMessage(popupMenu, new IdentifiedElementQuery(popupMenu).getLabel()), menuId);
                    // Add a default "additions" group
                    subMenu.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
                }
            }

            // Add a separator group if asked
            Optional<String> optionalGroupId = getGroupIdToCreate(popupMenu.getDocumentation());
            if (optionalGroupId.isPresent()) {
                Separator separator = new Separator(optionalGroupId.get());
                subMenu.add(separator);
            }

            final EList<MenuItemDescription> activatedActions = popupMenu.getMenuItemDescription();

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
                final Iterator<MenuItemDescription> mIterator = activatedActions.iterator();
                while (mIterator.hasNext()) {
                    final MenuItemDescription menuItemDescription = mIterator.next();
                    Optional<IAction> optionalAction = buildActionToAdd(domain, selectedViews, menuItemDescription, emfCommandFactory, primarySelection, currentMouseLocation, interpreter);
                    addActionInMenu(subMenu, menuItemDescription.getDocumentation(), optionalAction);
                }
            } else {
                subMenu.removeAll();
            }
            // finalize
            if (!(parentMenu.equals(subMenu)) && subMenu instanceof ContributionManager && ((ContributionManager) subMenu).getSize() > 0) {
                subMenu.setVisible(true);
                result = Options.newSome(subMenu);
            } else {
                result = Options.newNone();
            }
        }
        return result;
    }

    /**
     * Builds contents of popup menus from active layers.<BR/>
     * Method added in case of POC of bug 529992 (initially a copy of
     * {@link #buildContributionItemToAdd(EditDomain, Collection, PopupMenu, IDiagramCommandFactory, EditPart, Point)}.
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
    private void addGroup(IMenuManager parentMenu, final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final GroupMenu group, final IDiagramCommandFactory emfCommandFactory,
            EditPart primarySelection, Point currentMouseLocation) {
        final EObject semantic = selectedViews.iterator().next().getTarget();
        String locationURI = group.getLocationURI();
        try {
            LocationURI locationURIQuery = new LocationURI(locationURI);
            IMenuManager menuContainingNewGroup = null;
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

            if (menuContainingNewGroup != null) {
                // Add the separator group in the desired menu
                Separator separator = new Separator(group.getName());
                menuContainingNewGroup.add(separator);
                // separator.setVisible(true);
                // TODO: Add new popupMenu if any

                // Add actions in the group
                final EList<MenuItemDescription> activatedActions = group.getItemDescriptions();

                IInterpreter interpreter = null;
                if (semantic != null && semantic.eResource() != null) {
                    interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
                } else if (semantic instanceof DMappingBased) {
                    interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(((DMappingBased) semantic).getMapping());
                }

                final Boolean isMenuPreconditionValidated;
                if (StringUtil.isEmpty(group.getPrecondition())) {
                    isMenuPreconditionValidated = true;
                } else {
                    isMenuPreconditionValidated = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, group, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
                }

                if (isMenuPreconditionValidated) {
                    final Iterator<MenuItemDescription> mIterator = activatedActions.iterator();
                    while (mIterator.hasNext()) {
                        final MenuItemDescription menuItemDescription = mIterator.next();
                        Optional<IAction> optionalAction = buildActionToAdd(domain, selectedViews, menuItemDescription, emfCommandFactory, primarySelection, currentMouseLocation, interpreter);
                        addActionInGroup(menuContainingNewGroup, group.getName(), optionalAction);
                    }
                }
            }
        } catch (URISyntaxException e) {
            DiagramUIPlugin.getPlugin().log(new Status(IStatus.WARNING, DiagramUIPlugin.ID, IStatus.OK, MessageFormat.format(Messages.Group_Not_Displayed, group.getName(), e.getMessage()), e));
        }

    }

    /**
     * Builds contents of popup menus from menuItemDescription.<BR/>
     * Method added in case of POC of bug 529992 (initially a copy of
     * {@link #buildActionToAdd(EditDomain, Collection, MenuItemDescription, IDiagramCommandFactory, EditPart, Point, IInterpreter)}.
     * 
     * @param domain
     *            the edit domain used for execution
     * @param selectedViews
     *            the semantic viewpoint element under focus.
     */
    private Optional<IAction> buildActionToAdd(final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final MenuItemDescription menuItemDescription,
            final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation, IInterpreter interpreter) {
        final EObject semantic = selectedViews.iterator().next().getTarget();
        if (interpreter != null && preconditionHolds(menuItemDescription, selectedViews, semantic, interpreter)) {
            final IAction action = buildMenuItemAction(menuItemDescription, selectedViews, domain, emfCommandFactory, interpreter, primarySelection, currentMouseLocation);
            if (action != null) {
                return Optional.of(action);
            }
        }
        return Optional.empty();
    }
}
