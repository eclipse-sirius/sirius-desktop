/*******************************************************************************
 * Copyright (c) 2010, 2012 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.ui.tools.internal.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.tree.DTree;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.description.TreeMapping;
import org.eclipse.sirius.tree.ui.tools.internal.command.EMFCommandFactoryUI;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.AbstractToolAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.AbstractToolItemAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.CreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.CreateToolItemAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.DeleteTreeItemsAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.OpenRepresentationAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.actions.RefreshAction;
import org.eclipse.sirius.tree.ui.tools.internal.editor.provider.TreePopupMenuContributionSupport;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

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

    private static final String MENU_NAVIGATE_ID = "popup.navigate";

    private static final String NEW_REPRESENTATION_GROUP_SEPARATOR = "newRepresentation";

    private static final String EXISTING_REPRESENTATION_GROUP_SEPARATOR = "existingRepresentation";

    private static final String NAVIGATE_REPRESENTATION_GROUP_SEPARATOR = "navigateRepresentationGroup";

    private static final String PROPERTIES_SEPARATOR = "properties";

    private static final String LOCK_SEPARATOR = "lockGroup";

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
        // Add line menus
        addTreeItemMenus(manager);
        // Add navigate menus
        addNavigateMenu(manager);
        manager.add(new Separator());
        // Add viewpoint menus
        addRefreshMenu(manager);
        manager.add(new Separator());
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
     * Add the navigate sub menu and its actions if needed.
     * 
     * @param manager
     *            The menu manager
     */
    private void addNavigateMenu(final IMenuManager manager) {
        // Create a new sub-menu manager
        final MenuManager navigateMenuManager = new MenuManager("Navigate", DTreeMenuListener.MENU_NAVIGATE_ID);
        // Create the item to add to the main manager
        final SubContributionItem navigateMenuItem = new SubContributionItem(navigateMenuManager);
        manager.add(navigateMenuItem);
        // Add menus to navigate through existing representations (created by
        // RepresentationCreationDescription)
        final Separator existingGroup = new Separator(DTreeMenuListener.EXISTING_REPRESENTATION_GROUP_SEPARATOR);
        navigateMenuManager.add(existingGroup);
        // Add menus to navigate through existing representations (corresponding
        // to the RepresentationNavigationDescription)
        final Separator navigateRepresentationGroup = new Separator(NAVIGATE_REPRESENTATION_GROUP_SEPARATOR);
        navigateMenuManager.add(navigateRepresentationGroup);
        // Add menus to navigate through new representations (corresponding to
        // the RepresentationCreationDescription)
        final Separator createGroup = new Separator(DTreeMenuListener.NEW_REPRESENTATION_GROUP_SEPARATOR);
        navigateMenuManager.add(createGroup);
        final Collection<DTreeItem> currentTreeElements = treeViewManager.getSelectedItems();
        if (currentTreeElements != null && currentTreeElements.size() == 1) {
            DTreeItem currentTreeElement = currentTreeElements.iterator().next();
            // Add actions to navigate to existing representation
            createNavigationAction(navigateMenuItem, currentTreeElement);
            // Add actions to navigate to new representation
            if (currentTreeElement != null) {
                createDetailsActions(currentTreeElement, navigateMenuItem);
            }
        } else {
            // Add actions to navigate to existing representation
            createNavigationAction(navigateMenuItem, dTree);
        }
    }

    /**
     * @param navigateMenuItem
     * @param semanticElement
     */
    private void createNavigationAction(final SubContributionItem navigate, final DSemanticDecorator decorator) {
        final EObject semanticElement = decorator.getTarget();
        final Session session = SessionManager.INSTANCE.getSession(semanticElement);
        if (session != null) {
            final Collection<DRepresentation> otherRepresentations = DialectManager.INSTANCE.getRepresentations(semanticElement, session);
            for (final DRepresentation representation : otherRepresentations) {
                navigate.setVisible(true);
                ((IMenuManager) navigate.getInnerItem()).appendToGroup(EXISTING_REPRESENTATION_GROUP_SEPARATOR, buildOpenRepresentationAction(session, representation));
            }
            if (decorator instanceof DRepresentationElement) {
                if (buildNavigableRepresentationsMenu((IMenuManager) navigate.getInnerItem(), decorator, session)) {
                    // if at least one navigable representation menu
                    // has been created, we have to make the navigate menu
                    // visible
                    navigate.setVisible(true);
                }
            }
        }
    }

    private boolean buildNavigableRepresentationsMenu(final IMenuManager navigate, final EObject designerObj, final Session session) {
        final DRepresentationElement element = (DRepresentationElement) designerObj;
        if (element.getMapping() != null) {

            for (final RepresentationNavigationDescription navDesc : element.getMapping().getNavigationDescriptions()) {
                final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element.getTarget());

                final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
                variables.put(navDesc.getContainerVariable(), element.getTarget());
                variables.put(navDesc.getContainerViewVariable(), element);

                final InitInterpreterVariablesTask init = new InitInterpreterVariablesTask(variables, interpreter, new EMFCommandFactoryUI());
                init.execute();

                boolean precondition = true;
                if (!StringUtil.isEmpty(navDesc.getPrecondition())) {
                    try {
                        precondition = interpreter.evaluateBoolean(element.getTarget(), navDesc.getPrecondition());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                    }
                }

                if (precondition) {
                    // VP-2659 : we return true if at least one action has been
                    // added in the menu to make it visible
                    return buildOpenRepresentationActions(navigate, interpreter, navDesc, element, session);
                }
            }
        }
        return false;
    }

    private boolean buildOpenRepresentationActions(final IMenuManager navigate, final IInterpreter interpreter, final RepresentationNavigationDescription navDesc,
            final DRepresentationElement element, final Session session) {
        boolean atLeastOneRepresentationActionsWasCreated = false;
        Collection<EObject> candidates;
        if (!StringUtil.isEmpty(navDesc.getBrowseExpression())) {
            final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
            candidates = safeInterpreter.evaluateCollection(element.getTarget(), navDesc, ToolPackage.eINSTANCE.getRepresentationNavigationDescription_BrowseExpression());
        } else {
            candidates = new ArrayList<EObject>();
            final Iterator<EObject> it = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element.getTarget()).eAllContents(element.getTarget());
            while (it.hasNext()) {
                candidates.add(it.next());
            }
        }
        final Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(navDesc.getRepresentationDescription(), session);
        for (final DRepresentation representation : representations) {
            if (representation instanceof DSemanticDecorator && candidates.contains(((DSemanticDecorator) representation).getTarget())) {
                interpreter.setVariable(navDesc.getRepresentationNameVariable().getName(), representation.getName());
                String label = new StringBuffer("Open ").append(navDesc.getName()).append(" : ").append(representation.getName()).toString();
                if (!StringUtil.isEmpty(navDesc.getNavigationNameExpression())) {
                    try {
                        label = interpreter.evaluateString(element.getTarget(), navDesc.getNavigationNameExpression());
                    } catch (final EvaluationException e) {
                        RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getRepresentationNavigationDescription_NavigationNameExpression(), e);
                    }
                }
                navigate.appendToGroup(NAVIGATE_REPRESENTATION_GROUP_SEPARATOR, buildOpenRepresentationAction(session, representation, label));
                atLeastOneRepresentationActionsWasCreated = true;
            }
        }
        return atLeastOneRepresentationActionsWasCreated;
    }

    private IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation) {
        String representationName = representation.getName();
        if (StringUtil.isEmpty(representationName)) {
            representationName = "(unnamed)";
            if (representation instanceof DDiagram) {
                representationName += " " + new IdentifiedElementQuery(((DDiagram) representation).getDescription()).getLabel();
            }
        }
        return buildOpenRepresentationAction(session, representation, "Open " + representationName);
    }

    private IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation, final String label) {

        ImageDescriptor imageDescriptor = null;
        final IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(representation, IItemLabelProvider.class);
        if (labelProvider != null) {
            imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(representation));
        }
        return new OpenRepresentationAction(label, imageDescriptor, representation, session);

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

    private void createDetailsActions(final DTreeItem currentElement, final SubContributionItem navigate) {
        if (currentElement.getActualMapping() != null) {
            final Iterator<RepresentationCreationDescription> it = currentElement.getActualMapping().getDetailDescriptions().iterator();
            while (it.hasNext()) {
                final RepresentationCreationDescription desc = it.next();
                final String precondition = desc.getPrecondition();
                boolean append = true;
                if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
                    append = false;
                    final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(currentElement.getTarget());
                    try {
                        append = interpreter.evaluateBoolean(currentElement.getTarget(), precondition);
                    } catch (final EvaluationException e) {
                        // do nothing
                    }
                }
                if (append) {
                    navigate.setVisible(true);
                    ((IMenuManager) navigate.getInnerItem()).appendToGroup(NEW_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc, currentElement,
                            treeViewManager.getEditingDomain(), treeViewManager.getTreeCommandFactory()));
                }
            }
        }
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
