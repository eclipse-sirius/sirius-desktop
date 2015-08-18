/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.ui.tools.internal.commands.EMFCommandFactoryUI;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.AbstractToolAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.AbstractToolItemAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.CreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.CreateToolItemAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.DeleteTreeItemsAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.RefreshAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.TreePopupMenuContributionSupport;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.MenuHelper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * A menu listener which show or hide the menu according to :
 * <UL>
 * <LI>the current selection</LI>
 * <LI>and the tool precondition.
 * </UL>
 * 
 * @author <a href="mailto:nathalie.lepine@obeo.fr">Nathalie Lepine</a>
 * 
 */
public class DTreeMenuListener implements IMenuListener {

    private static final String MENU_OPEN_REPRESENTATION_ID = "popup.navigate"; //$NON-NLS-1$

    private static final String MENU_NEW_REPRESENTATION_ID = "popup.new"; //$NON-NLS-1$

    private static final String NEW_REPRESENTATION_GROUP_SEPARATOR = "newRepresentation"; //$NON-NLS-1$

    private static final String EXISTING_REPRESENTATION_GROUP_SEPARATOR = "existingRepresentation"; //$NON-NLS-1$

    private static final String PROPERTIES_SEPARATOR = "properties"; //$NON-NLS-1$

    private static final String LOCK_SEPARATOR = "lockGroup"; //$NON-NLS-1$

    private final AdapterFactory adapterFactory;

    private final DTree dTree;

    private final DTreeViewerManager treeViewManager;

    private RefreshAction refreshAction;

    private final DeleteTreeItemsAction deleteItemsAction;

    private Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions;

    private List<AbstractToolAction> createActionsForTree;

    /**
     * The support to use for handling contextual menu contributions.
     */
    private TreePopupMenuContributionSupport treePopupMenuContributionHandler;

    /**
     * Default constructor.
     * 
     * @param tree
     *            The tree associates with this menu
     * @param treeViewManager
     *            The manager of the TreeView
     * @param mappingToCreateActions
     *            A map which associates {@link TreeMapping} with the
     *            corresponding list of {@link AbstractToolAction} (
     *            {@link org.eclipse.sirius.tree.ui.tools.internal.editor.action.CreateLineAction}
     *            or
     *            {@link org.eclipse.sirius.tree.ui.tools.internal.editor.action.CreateTargetColumnAction}
     *            )
     * @param createActionsForTree
     *            A list of the actions for create lines under the tree.
     */
    public DTreeMenuListener(final DTree tree, final DTreeViewerManager treeViewManager, final Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions,
            final List<AbstractToolAction> createActionsForTree) {
        super();
        adapterFactory = DialectUIManager.INSTANCE.createAdapterFactory();
        this.dTree = tree;
        this.treeViewManager = treeViewManager;
        this.treePopupMenuContributionHandler = new TreePopupMenuContributionSupport(this.treeViewManager.getEditingDomain(), this.treeViewManager.getTreeCommandFactory());
        this.deleteItemsAction = new DeleteTreeItemsAction(treeViewManager.getEditingDomain(), treeViewManager.getTreeCommandFactory());

        setMappingToCreateActions(mappingToCreateActions);
        setCreateActionsForTree(createActionsForTree);
        refreshAction = new RefreshAction((DTreeEditor) treeViewManager.getEditor());
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.jface.action.IMenuListener#menuAboutToShow(org.eclipse.jface.action.IMenuManager)
     */
    public void menuAboutToShow(final IMenuManager manager) {
        // Refresh the cached actions if needed
        treeViewManager.fillMenu();
        // Add Open representation menu
        addOpenRepresentationMenu(manager);
        // Add new representation menu
        addNewRepresentationMenu(manager);
        manager.add(new Separator());
        // Add viewpoint menus
        addRefreshMenu(manager);
        manager.add(new Separator());
        // Add line menus
        addTreeItemMenus(manager);
        manager.add(new Separator(DTreeMenuListener.LOCK_SEPARATOR));
        addTreeMenus(manager);
        // Add show properties view
        manager.add(new Separator(DTreeMenuListener.PROPERTIES_SEPARATOR));
        manager.add(new Separator());
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    /**
     * Add the tree menus if any line is selected.<BR>
     * 
     * @param manager
     *            the menu manager
     */
    private void addTreeMenus(final IMenuManager manager) {
        final Collection<DTreeItem> selectedItems = treeViewManager.getSelectedItems();
        if (selectedItems != null && !selectedItems.isEmpty()) {
            for (final AbstractToolAction abstractToolAction : getCreateActionsForTree()) {
                if (abstractToolAction instanceof CreateToolItemAction) {
                    final CreateToolItemAction createLineAction = (CreateToolItemAction) abstractToolAction;
                    if (createLineAction.canExecute()) {
                        manager.add(createLineAction);
                    }
                }
            }
            manager.add(new Separator());
        }
    }

    /**
     * Add the open sub menu and its actions if needed.
     * 
     * @param manager
     *            The menu manager
     */
    private void addOpenRepresentationMenu(final IMenuManager manager) {
        // Create a new sub-menu manager
        final MenuManager openMenuManager = new MenuManager("Open", DTreeMenuListener.MENU_OPEN_REPRESENTATION_ID);
        // Create the item to add to the main manager
        final SubContributionItem openMenuItem = new SubContributionItem(openMenuManager);
        manager.add(openMenuItem);
        // Add menus to open existing representations (created by
        // RepresentationCreationDescription)
        final Separator existingGroup = new Separator(DTreeMenuListener.EXISTING_REPRESENTATION_GROUP_SEPARATOR);
        openMenuManager.add(existingGroup);
        // Add menus to open existing representations (corresponding
        // to the RepresentationNavigationDescription)
        final Separator openRepresentationGroup = new Separator(MenuHelper.OPEN_REPRESENTATION_GROUP_SEPARATOR);
        openMenuManager.add(openRepresentationGroup);
        final Collection<DTreeItem> currentTreeElements = treeViewManager.getSelectedItems();
        if (currentTreeElements != null && currentTreeElements.size() == 1) {
            DTreeItem currentTreeElement = currentTreeElements.iterator().next();
            // Add actions to open existing representation
            createOpenAction(openMenuItem, currentTreeElement);
        } else {
            // Add actions to open existing representation
            createOpenAction(openMenuItem, dTree);
        }
    }

    /**
     * Add the new sub menu and its actions if needed.
     * 
     * @param manager
     *            The menu manager
     */
    private void addNewRepresentationMenu(final IMenuManager manager) {
        // Create a new sub-menu manager
        final MenuManager newMenuManager = new MenuManager("New", DTreeMenuListener.MENU_NEW_REPRESENTATION_ID);
        // Create the item to add to the main manager
        final SubContributionItem newMenuItem = new SubContributionItem(newMenuManager);
        manager.add(newMenuItem);
        // Add menus to create new representations (corresponding to
        // the RepresentationCreationDescription)
        final Separator createGroup = new Separator(DTreeMenuListener.NEW_REPRESENTATION_GROUP_SEPARATOR);
        newMenuManager.add(createGroup);
        final Collection<DTreeItem> currentTreeElements = treeViewManager.getSelectedItems();
        if (currentTreeElements != null && currentTreeElements.size() == 1) {
            DTreeItem currentTreeElement = currentTreeElements.iterator().next();
            // Add actions to navigate to new representation
            if (currentTreeElement != null) {
                createDetailsActions(currentTreeElement, newMenuItem);
            }
        }
    }

    /**
     * @param openMenuItem
     * @param semanticElement
     */
    private void createOpenAction(final SubContributionItem openItem, final DSemanticDecorator decorator) {
        final EObject semanticElement = decorator.getTarget();
        final Session session = SessionManager.INSTANCE.getSession(semanticElement);
        if (session != null) {
            final Collection<DRepresentation> otherRepresentations = DialectManager.INSTANCE.getRepresentations(semanticElement, session);
            for (final DRepresentation representation : otherRepresentations) {
                if (!EcoreUtil.equals(dTree, representation) && isFromActiveViewpoint(session, representation)) {
                    openItem.setVisible(true);
                    ((IMenuManager) openItem.getInnerItem()).appendToGroup(EXISTING_REPRESENTATION_GROUP_SEPARATOR, MenuHelper.buildOpenRepresentationAction(session, representation, adapterFactory));
                }
            }
            if (decorator instanceof DRepresentationElement) {
                if (buildOpenRepresentationsMenu((IMenuManager) openItem.getInnerItem(), (DRepresentationElement) decorator, session)) {
                    // if at least one navigable representation menu
                    // has been created, we have to make the navigate menu
                    // visible
                    openItem.setVisible(true);
                }
            }
        }
    }

    private boolean buildOpenRepresentationsMenu(final IMenuManager openMenu, final DRepresentationElement element, final Session session) {
        if (element.getMapping() != null) {

            for (final RepresentationNavigationDescription navDesc : element.getMapping().getNavigationDescriptions()) {
                boolean append = true;
                if (!isFromActiveViewpoint(session, navDesc.getRepresentationDescription())) {
                    append = false;
                }

                final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element.getTarget());

                final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
                variables.put(navDesc.getContainerVariable(), element.getTarget());
                variables.put(navDesc.getContainerViewVariable(), element);

                final InitInterpreterVariablesTask init = new InitInterpreterVariablesTask(variables, interpreter, new EMFCommandFactoryUI());
                init.execute();

                final String precondition = navDesc.getPrecondition();
                if (append && !StringUtil.isEmpty(precondition)) {
                    append = false;

                    try {
                        append = interpreter.evaluateBoolean(element.getTarget(), navDesc.getPrecondition());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                    }
                }

                if (append) {
                    // VP-2659 : we return true if at least one action has been
                    // added in the menu to make it visible
                    return MenuHelper.buildOpenRepresentationActions(openMenu, interpreter, navDesc, element, session, adapterFactory);
                }
            }
        }
        return false;
    }

    private void addRefreshMenu(final IMenuManager manager) {
        manager.add(refreshAction);
    }

    private void addTreeItemMenus(final IMenuManager manager) {
        final Collection<DTreeItem> selectedItems = treeViewManager.getSelectedItems();
        if (selectedItems != null && !selectedItems.isEmpty()) {
            manager.add(new Separator());

            // Expose popup menus if there is only one selected line.
            if (selectedItems.size() == 1) {
                DTreeItem firstSelectedItem = selectedItems.iterator().next();
                // VP-915 : handling users contributing throw PopupMenus tools
                treePopupMenuContributionHandler.contributeToPopupMenu(manager, firstSelectedItem);
            }

            addDeleteItemsAction(manager, selectedItems);

            // Expose create actions if there is only one selected line.
            if (selectedItems.size() == 1) {
                DTreeItem firstSelectedItem = selectedItems.iterator().next();
                addCreateActions(manager, firstSelectedItem);
            }

        }
    }

    private void addCreateActions(IMenuManager manager, DTreeItem singleSelectedItem) {
        if (singleSelectedItem.getActualMapping() != null) {
            final List<AbstractToolAction> createActions = getMappingToCreateActions().get(singleSelectedItem.getActualMapping());
            if (createActions != null && !createActions.isEmpty()) {
                for (final AbstractToolAction createAction : createActions) {
                    ((AbstractToolItemAction) createAction).setLine(singleSelectedItem);
                    if (createAction.canExecute()) {
                        manager.add(createAction);
                    }
                }
            }
        }

    }

    private void addDeleteItemsAction(IMenuManager manager, Collection<DTreeItem> selectedItems) {
        deleteItemsAction.setItems(selectedItems);
        if (deleteItemsAction.canExecute()) {
            manager.add(deleteItemsAction);
        }
    }

    private void createDetailsActions(final DTreeItem currentElement, final SubContributionItem newMenuItem) {
        if (currentElement.getMapping() != null) {
            final Session session = currentElement.getTarget() != null ? SessionManager.INSTANCE.getSession(currentElement.getTarget()) : null;
            if (session != null) {
                for (RepresentationCreationDescription desc : currentElement.getMapping().getDetailDescriptions()) {
                    boolean append = true;
                    if (!isFromActiveViewpoint(session, desc.getRepresentationDescription())) {
                        append = false;
                    }

                    final String precondition = desc.getPrecondition();
                    if (append && !StringUtil.isEmpty(precondition)) {
                        append = false;
                        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(currentElement.getTarget());
                        try {
                            append = interpreter.evaluateBoolean(currentElement.getTarget(), precondition);
                        } catch (final EvaluationException e) {
                            // do nothing
                        }
                    }
                    if (append) {
                        newMenuItem.setVisible(true);
                        ((IMenuManager) newMenuItem.getInnerItem()).appendToGroup(NEW_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc,
                                currentElement, treeViewManager.getEditingDomain(), treeViewManager.getTreeCommandFactory()));
                    }
                }
            }
        }
    }

    /**
     * Tests whether a representation description belongs to a viewpoint which
     * is currently active in the session.
     * 
     * @param session
     *            the current session.
     * @param representationDescription
     *            the representation description to check.
     * @return <code>true</code> if the representation description belongs to a
     *         viewpoint which is currently active in the session.
     */
    private boolean isFromActiveViewpoint(final Session session, final RepresentationDescription representationDescription) {
        final Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(representationDescription);
        return vp != null && session.getSelectedViewpoints(false).contains(vp);
    }

    /**
     * Tests whether a representation belongs to a viewpoint which is currently
     * active in the session.
     * 
     * @param session
     *            the current session.
     * @param representation
     *            the representation to check.
     * @return <code>true</code> if the representation belongs to a viewpoint
     *         which is currently active in the session.
     */
    private boolean isFromActiveViewpoint(final Session session, final DRepresentation representation) {
        final RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
        return isFromActiveViewpoint(session, description);
    }

    protected Map<TreeMapping, List<AbstractToolAction>> getMappingToCreateActions() {
        return mappingToCreateActions;
    }

    public void setMappingToCreateActions(final Map<TreeMapping, List<AbstractToolAction>> mappingToCreateActions) {
        this.mappingToCreateActions = mappingToCreateActions;
    }

    protected List<AbstractToolAction> getCreateActionsForTree() {
        return createActionsForTree;
    }

    public void setCreateActionsForTree(final List<AbstractToolAction> createActionsForTable) {
        this.createActionsForTree = createActionsForTable;
    }
}
