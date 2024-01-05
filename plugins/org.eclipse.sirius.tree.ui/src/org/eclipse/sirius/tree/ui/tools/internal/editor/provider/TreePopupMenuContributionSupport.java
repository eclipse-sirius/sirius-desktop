/*******************************************************************************
 * Copyright (c) 2009, 2024 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.tree.ui.tools.internal.editor.provider;

import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.MessageTranslator;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.MetaClassNotFoundException;
import org.eclipse.sirius.tools.api.SiriusPlugin;
import org.eclipse.sirius.tools.api.ui.ExternalJavaActionProvider;
import org.eclipse.sirius.tools.api.ui.IExternalJavaAction;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.business.api.command.ITreeCommandFactory;
import org.eclipse.sirius.tree.business.api.query.TreePopupMenuQuery;
import org.eclipse.sirius.tree.description.TreePopupMenu;
import org.eclipse.sirius.tree.ui.provider.TreeUIPlugin;
import org.eclipse.sirius.tree.ui.tools.internal.editor.DTreeViewerManager;
import org.eclipse.sirius.viewpoint.description.tool.CreateInstance;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaAction;
import org.eclipse.sirius.viewpoint.description.tool.ExternalJavaActionCall;
import org.eclipse.sirius.viewpoint.description.tool.MenuItemDescription;
import org.eclipse.sirius.viewpoint.description.tool.OperationAction;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

/**
 * A Class that will populate a {@link DTreeItem}'s contextual menu using all the
 * {@link org.eclipse.sirius.viewpoint.description.tool.PopupMenu}s defined with its associate
 * {@link org.eclipse.sirius.tree.description.TreeItemMapping}.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public final class TreePopupMenuContributionSupport {

    /**
     * The editing domain.
     */
    protected TransactionalEditingDomain domain;

    /**
     * The factory to use to create tree-related commands.
     */
    protected ITreeCommandFactory treeCommandFactory;

    /**
     * Creates a new TreePopupMenuContributionSupport.
     * 
     * @param transactionalEditingDomain
     *            the domain to use for executing contributed actions
     * @param treeCommandFactory
     *            the command factory to use
     */
    public TreePopupMenuContributionSupport(TransactionalEditingDomain transactionalEditingDomain, ITreeCommandFactory treeCommandFactory) {
        this.domain = transactionalEditingDomain;
        this.treeCommandFactory = treeCommandFactory;
    }

    /**
     * Adds all menus and actions defined for the given {@link DTreeItem} to the given contextual menu.
     * 
     * @param menu
     *            the contextual menu about to be shown
     * @param selectedItem
     *            the selected {@link DTreeItem} from which getting the additional menus and actions
     */
    public void contributeToPopupMenu(final IMenuManager menu, DTreeItem selectedItem) {
        EList<TreePopupMenu> popupMenus = selectedItem.getActualMapping().getPopupMenus();
        IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(selectedItem.getTarget());
        boolean haveCreatedAtLeastOnePopupMenu = false;
        if (interpreter == null || popupMenus.isEmpty()) {
            return;
        }

        // For each defined popupMenu
        for (TreePopupMenu popupMenu : popupMenus) {
            // If the defined popupMenu is applicable (i.e. has a "true"
            // precondition)
            final Boolean menuPrecondition;
            if (StringUtil.isEmpty(popupMenu.getPrecondition())) {
                menuPrecondition = true;
            } else {
                menuPrecondition = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(selectedItem.getTarget(), popupMenu,
                        ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            }

            if (menuPrecondition) {

                // We create a subMenu
                final MenuManager subMenu = new MenuManager(MessageTranslator.INSTANCE.getMessage(popupMenu, new IdentifiedElementQuery(popupMenu).getLabel()),
                        new IdentifiedElementQuery(popupMenu).getLabel().toLowerCase());
                // and populate it with all menu contributions contained it this
                // popupMenu
                buildActionsFromTreePopupMenu(subMenu, selectedItem, popupMenu, interpreter);

                // If at least one action has been added to the subMenu, we make
                // it visible
                if (subMenu.getSize() > 0) {
                    haveCreatedAtLeastOnePopupMenu = true;
                    subMenu.setParent(menu);
                    menu.add(subMenu);
                    subMenu.setVisible(true);
                }
            }
        }
        if (haveCreatedAtLeastOnePopupMenu) {
            menu.add(new Separator());
        }

    }

    /**
     * Builds all the Actions specified in the given popupMenu and adds them to the given subMenu
     * 
     * @param subMenu
     *            the subMenu in which create the actions
     * @param selectedItem
     *            the {@link DTreeItem} from which the contextual menu is computed
     * @param popupMenu
     *            the popupMenu containing the Actions to create
     * @param domain
     */
    private void buildActionsFromTreePopupMenu(IMenuManager subMenu, DTreeItem selectedItem, TreePopupMenu popupMenu, IInterpreter interpreter) {
        // For each menu contribution associated to the given popupMenu
        for (MenuItemDescription candidateMenuItem : new TreePopupMenuQuery(popupMenu).getMenuItems()) {
            // If the menu item is applicable (i.e. has a "true"
            // precondition)
            final Boolean menuItemPrecondition;
            if (StringUtil.isEmpty(candidateMenuItem.getPrecondition())) {
                menuItemPrecondition = true;
            } else {
                menuItemPrecondition = RuntimeLoggerManager.INSTANCE.decorate(interpreter).evaluateBoolean(selectedItem.getTarget(), candidateMenuItem,
                        ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition());
            }

            if (menuItemPrecondition) {
                // We build a jface Action from this menu item
                final IAction action = buildActionFromMenuItemDescription(candidateMenuItem, selectedItem);
                if (action != null) {
                    subMenu.add(action);
                }
            }
        }
    }

    /**
     * Builds a JFace Action from the given {@link MenuItemDescription}, which target is the given selected
     * {@link DTreeItem}.
     * 
     * @param candidateMenuItem
     *            the {@link MenuItemDescription} to create an Action from
     * @param selectedItem
     *            the selected {@link DTreeItem}
     * @return a JFace Action created from the given {@link MenuItemDescription}
     */
    private IAction buildActionFromMenuItemDescription(MenuItemDescription candidateMenuItem, DTreeItem selectedItem) {
        IAction result = null;
        // The Menu Item Description can be :
        // An External Java Action
        if (candidateMenuItem instanceof ExternalJavaAction) {
            final ExternalJavaAction javaAction = (ExternalJavaAction) candidateMenuItem;
            if (!StringUtil.isEmpty(javaAction.getId())) {
                result = buildJavaAction(javaAction, selectedItem, new IdentifiedElementQuery(javaAction).getLabel(), javaAction.getIcon());
            }
        } else {
            // An External Java Action call
            if (candidateMenuItem instanceof ExternalJavaActionCall) {
                final ExternalJavaAction javaAction = ((ExternalJavaActionCall) candidateMenuItem).getAction();
                if (javaAction != null && !StringUtil.isEmpty(javaAction.getId())) {
                    String iconPath = ((ExternalJavaActionCall) candidateMenuItem).getIcon();
                    if (StringUtil.isEmpty(iconPath)) {
                        iconPath = javaAction.getIcon();
                    }
                    result = buildJavaAction(javaAction, selectedItem, new IdentifiedElementQuery(candidateMenuItem).getLabel(), iconPath);
                }
                // An Operation Action
            } else {
                if (candidateMenuItem instanceof OperationAction) {
                    final OperationAction operation = (OperationAction) candidateMenuItem;
                    if (operation.getInitialOperation().getFirstModelOperations() != null) {
                        result = buildOperationAction(operation, selectedItem);
                    }
                }
            }
        }
        return result;
    }

    /**
     * Builds a JFace Action from the given {@link ExternalJavaAction}, which target is the given selected
     * {@link DTreeItem}.
     * 
     * @param javaActionItem
     *            the ExternalJavaAction action to create an Action from
     * @param selectedItem
     *            the selected {@link DTreeItem}
     * @param nameOfTheAction
     *            the name to associate with the created action
     * @param iconPath
     *            the path of the icon to associate with the created action
     * @return a JFace Action created from the given {@link ExternalJavaAction}
     */
    private IAction buildJavaAction(ExternalJavaAction javaActionItem, DTreeItem selectedItem, String nameOfTheAction, String iconPath) {
        // Step 1 : getting the java Action
        final IExternalJavaAction javaAction = ExternalJavaActionProvider.INSTANCE.getJavaActionById(javaActionItem.getId());

        if (javaAction != null) {
            // Step 2 : building the command from this java Action
            final Command command = this.treeCommandFactory.buildJavaActionFromTool(javaActionItem, selectedItem, javaAction);

            if (command.canExecute()) {
                // Step 3 : getting the icon to associate with the action to
                // create
                ImageDescriptor imageDescriptor = null;
                if (!StringUtil.isEmpty(iconPath)) {
                    imageDescriptor = TreeUIPlugin.findImageDescriptor(iconPath);
                }
                // Step 4 : creating an action that will call the builded
                // command
                return new Action(nameOfTheAction, imageDescriptor) {

                    @Override
                    public void run() {
                        super.run();
                        domain.getCommandStack().execute(command);
                    }
                };
            }
        }
        return null;
    }

    /**
     * Builds a JFace Action from the given {@link OperationAction}, which target is the given selected
     * {@link DTreeItem}.
     * 
     * @param operationAction
     *            the operation action to create an Action from
     * @param selectedItem
     *            the selected {@link DTreeItem}
     * @return a JFace Action created from the given {@link OperationAction}
     */
    private IAction buildOperationAction(final OperationAction operationAction, DTreeItem selectedItem) {
        // Step 1 : getting the icon to associate with the action to create
        ImageDescriptor imageDescriptor = null;
        if (!StringUtil.isEmpty(operationAction.getIcon())) {
            imageDescriptor = TreeUIPlugin.findImageDescriptor(operationAction.getIcon());
        } else {
            // no icon is specified, let's provide a semantic icon as a default
            // if we can find a create instance task.
            imageDescriptor = TreePopupMenuContributionSupport.findImageDescriptor(operationAction);
        }

        // Step 2 : building the command from this OperationAction
        final Command operationActionCommand = treeCommandFactory.buildOperationActionFromTool(operationAction, selectedItem);

        if (operationActionCommand.canExecute()) {
            // Step 3 : creating an action that will call the builded
            // command
            return new Action(MessageTranslator.INSTANCE.getMessage(operationAction, new IdentifiedElementQuery(operationAction).getLabel()), imageDescriptor) {
                @Override
                public void run() {
                    super.run();
                    domain.getCommandStack().execute(operationActionCommand);
                }
            };
        }
        return null;
    }

    private static ImageDescriptor findImageDescriptor(OperationAction createTool) {

        ImageDescriptor descriptor = DTreeViewerManager.getImageRegistry().getDescriptor(DTreeViewerManager.CREATE_TREE_ITEM_IMG);
        EObject created = null;

        Iterator<EObject> iter = createTool.eAllContents();
        while (created == null && iter.hasNext()) {
            EObject current = iter.next();
            if (current instanceof CreateInstance) {
                created = TreePopupMenuContributionSupport.tryToInstanciateType(createTool, created, ((CreateInstance) current).getTypeName());
            }
        }

        if (created != null) {
            final IItemLabelProvider labelProvider = (IItemLabelProvider) TreeUIPlugin.getPlugin().getItemProvidersAdapterFactory().adapt(created, IItemLabelProvider.class);
            if (labelProvider != null) {
                ImageDescriptor semanticDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(created));
                if (semanticDescriptor != null) {
                    descriptor = semanticDescriptor;
                }

            }
        }

        return descriptor;
    }

    private static EObject tryToInstanciateType(OperationAction createTool, EObject created, String map) {
        EObject result = created;
        if (!StringUtil.isEmpty(map)) {
            try {
                final EObject anInstance = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(createTool).createInstance(map);
                result = anInstance;
            } catch (final MetaClassNotFoundException e) {
                /*
                 * silent catch, it's just a bit more magic, if we're able to retrieve the right type then we'll do.
                 */
            }

        }
        return result;
    }
}
