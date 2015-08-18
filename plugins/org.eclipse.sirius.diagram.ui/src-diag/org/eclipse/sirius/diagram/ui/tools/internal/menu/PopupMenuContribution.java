/*******************************************************************************
 * Copyright (c) 2008, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

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
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DSemanticDiagram;
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
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.PopupMenu;
import org.eclipse.sirius.viewpoint.description.tool.ToolEntry;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

;

/**
 * This menu contribution add a menu in the DDiagram diagram editor for each
 * PopupMenu elements.
 * 
 * @author mporhel
 * 
 */
public class PopupMenuContribution implements IContributionItemProvider {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#contributeToActionBars(org.eclipse.ui.IActionBars,
     *      org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    public void contributeToActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#contributeToPopupMenu(org.eclipse.jface.action.IMenuManager,
     *      org.eclipse.ui.IWorkbenchPart)
     */
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
                final EList<Layer> layers = designerDiag.getActivatedLayers();

                final Session session = SessionManager.INSTANCE.getSession(designerDiag.getTarget());

                final EList<ToolSection> sections = new BasicEList<ToolSection>();
                final EList<PopupMenu> activatedPopupMenus = new BasicEList<PopupMenu>();
                final EList<OperationAction> activatedOperationActionMenus = new BasicEList<OperationAction>();
                final EList<ExternalJavaAction> activatedExternalJavaActionMenus = new BasicEList<ExternalJavaAction>();

                final EList<AbstractToolDescription> tools = designerDiag.getDescription().getReusedTools();
                computeReusedTools(tools, activatedPopupMenus, activatedExternalJavaActionMenus, activatedOperationActionMenus);
                sections.addAll(new ToolSectionQuery(designerDiag.getDescription().getToolSection()).getAllSections());

                for (final Layer layer : layers) {
                    for (final ToolSection ts : layer.getToolSections()) {
                        sections.addAll(new ToolSectionQuery(ts).getAllSections());
                    }
                }

                final Iterator<ToolSection> lIterator = sections.iterator();
                while (lIterator.hasNext()) {
                    final ToolSection toolSection = lIterator.next();
                    activatedPopupMenus.addAll(toolSection.getPopupMenus());
                    ToolSectionQuery toolSectionQuery = new ToolSectionQuery(toolSection);
                    activatedOperationActionMenus.addAll(toolSectionQuery.getOperationActions(session));
                    activatedExternalJavaActionMenus.addAll(toolSectionQuery.getExternalJavaActions(session));
                    computeReusedTools(toolSection.getReusedTools(), activatedPopupMenus, activatedExternalJavaActionMenus, activatedOperationActionMenus);
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

                // TODOCBR check cardinality
                if (domain != null && selectedViews.size() > 0) {

                    Point relativeCursorLocationToPrimarySelection = getCurrentLocation(primarySelection);

                    for (final PopupMenu popMenu : new HashSet<PopupMenu>(activatedPopupMenus)) {
                        Option<? extends IContributionItem> contributionItem = buildContributionItemToAdd(domain, selectedViews, popMenu, emfCommandFactory, primarySelection,
                                relativeCursorLocationToPrimarySelection);
                        if (contributionItem.some()) {
                            menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, contributionItem.get());
                        }
                    }
                    for (final OperationAction operationAction : new HashSet<OperationAction>(activatedOperationActionMenus)) {
                        Option<IAction> action = buildActionToAdd(domain, selectedViews, operationAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
                        if (action.some()) {
                            menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
                        }
                    }
                    for (final ExternalJavaAction externalJavaAction : new HashSet<ExternalJavaAction>(activatedExternalJavaActionMenus)) {
                        Option<IAction> action = buildActionToAdd(domain, selectedViews, externalJavaAction, emfCommandFactory, primarySelection, relativeCursorLocationToPrimarySelection);
                        if (action.some()) {
                            menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, action.get());
                        }
                    }
                }
            } else {
                // no focused edit part
            }
        }
    }

    /**
     * Computes the location where the element must be created: a location
     * relative to the primary edit part.
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

    private void computeReusedTools(final EList<? extends ToolEntry> reusedTools, final EList<PopupMenu> activatedPopupMenus, final EList<ExternalJavaAction> activatedExternalJavaActionMenus,
            final EList<OperationAction> activatedOperationActionMenus) {
        for (ToolEntry abstractToolDescription : reusedTools) {
            if (abstractToolDescription instanceof PopupMenu) {
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
     *            The mouse location (used if some elements are created with
     *            contextual action)
     * @return an optional IContributionItem.
     */
    private Option<? extends IContributionItem> buildContributionItemToAdd(final EditDomain domain, final Collection<DSemanticDecorator> selectedViews, final PopupMenu popupMenu,
            final IDiagramCommandFactory emfCommandFactory, EditPart primarySelection, Point currentMouseLocation) {

        final EObject semantic = selectedViews.iterator().next().getTarget();
        final MenuManager subMenu = new MenuManager(new IdentifiedElementQuery(popupMenu).getLabel(), popupMenu.getName().toLowerCase());
        final EList<MenuItemDescription> activatedAction = popupMenu.getMenuItemDescription();

        IInterpreter interpreter = null;
        if (semantic != null && semantic.eResource() != null) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(semantic);
        } else if (semantic instanceof DMappingBased) {
            interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(((DMappingBased) semantic).getMapping());
        }

        final Boolean menuPrecondition;
        if (StringUtil.isEmpty(popupMenu.getPrecondition())) {
            menuPrecondition = true;
        } else {
            menuPrecondition = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(semantic, popupMenu, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
        }

        if (menuPrecondition) {
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
        cc.add(new org.eclipse.gef.commands.Command("Store mouse location") {
            @Override
            public void execute() {
                SiriusLayoutDataManager.INSTANCE.addData(new RootLayoutData(primarySelection, currentMouseLocation.getCopy(), new Dimension(-1, -1)));
            }
        });
        cc.add(new ICommandProxy(new GMFCommandWrapper(transactionalEditingDomain, command)));
        ImageDescriptor imageDescriptor = null;
        if (operationAction.getIcon() != null && !"".equals(operationAction.getIcon())) //$NON-NLS-1$
            imageDescriptor = DiagramUIPlugin.Implementation.findImageDescriptor(operationAction.getIcon());
        return new Action(new IdentifiedElementQuery(operationAction).getLabel(), imageDescriptor) {
            public void run() {
                super.run();
                domain.getCommandStack().execute(cc);
            }
        };

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#disposeContributions(org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    public void disposeContributions(final IWorkbenchPartDescriptor arg0) {

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider#updateActionBars(org.eclipse.ui.IActionBars,
     *      org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor)
     */
    public void updateActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#addProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void addProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#provides(org.eclipse.gmf.runtime.common.core.service.IOperation)
     */
    public boolean provides(final IOperation arg0) {
        // Always provide
        return true;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.gmf.runtime.common.core.service.IProvider#removeProviderChangeListener(org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener)
     */
    public void removeProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

}
