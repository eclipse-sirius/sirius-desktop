/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.editor.tools.internal.editor.EditorCustomizationManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.ui.IEditorPart;

import com.google.common.collect.Iterables;

/**
 * Abstract class to dynamicaly build treeview menus.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractMenuBuilder {
    private static final String EDIT = "edit";

    /**
     * child action for the advanced menu.
     */
    protected Collection advancedChildActions;

    /**
     * descriptors for the menu action.
     */
    protected Collection descriptors;

    /**
     * menu manager for the advanced menu.
     */
    protected IMenuManager myMenuManager;

    /**
     * Create a new builder.
     */
    public AbstractMenuBuilder() {
        super();
        descriptors = new LinkedHashSet();
        getMenu();
    }

    /**
     * return the menu child descriptors.
     * 
     * @return the menu child descriptors
     */
    public Collection getMyDescriptors() {
        return descriptors;
    }

    /**
     * return the menu manager.
     * 
     * @return the menu manager.
     */
    protected Option<IMenuManager> getMenu() {
        return Options.newSome(myMenuManager);
    }

    private void createMenuManager() {
        myMenuManager = new MenuManager(getLabel());
        // Force an update because Eclipse hides empty menus now.
        //
        myMenuManager.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(IMenuManager menuManager) {
                menuManager.updateAll(true);
            }
        });
    }

    /**
     * return the menu label.
     * 
     * @return the menu label.
     */
    public abstract String getLabel();

    /**
     * Attache the menu to its parent.
     * 
     * @param parent
     *            parent to attach to.
     */
    public void attach(final IMenuManager parent) {
        // parent.add(getMenu());
    }

    /**
     * Update the menu considering the new descriptor and the current selection.
     * 
     * @param newChildDescriptors
     *            new descriptors.
     * @param selection
     *            current selection.
     * @param editor
     *            current editor.
     */
    public void update(final Collection newChildDescriptors, final ISelection selection, final IEditorPart editor) {
        depopulate();
        advancedChildActions = generateCreateChildActions(filter(newChildDescriptors), selection, editor);
    }

    private Collection filter(final Collection newChildDescriptors) {
        for (final Object object : newChildDescriptors) {
            if (object instanceof CommandParameter) {
                if (!isDeprecated((CommandParameter) object) && isMine((CommandParameter) object)) {
                    descriptors.add(object);
                }
            } else {
                descriptors.add(object);
            }
        }
        return descriptors;
    }

    /**
     * return true if the command parameter is valid for the current builder.
     * 
     * @param object
     *            a command parameter to create new childs.
     * @return true if the command parameter is valid for the current builder.
     */
    protected abstract boolean isMine(CommandParameter object);

    /**
     * return true if the command parameter is deprecated and should be hidden.
     * 
     * @param param
     *            a command parameter
     * @return true if the command parameter is deprecated and should be hidden
     */
    protected boolean isDeprecated(final CommandParameter param) {
        return (param.getEStructuralFeature() != null && isDeprecated(param.getEStructuralFeature())) || (param.getValue() instanceof EObject && isDeprecated(((EObject) param.getValue()).eClass()));
    }

    private boolean isDeprecated(final EModelElement owner) {
        return isGlobalyDisabled(owner) || EditorCustomizationManager.getInstance().isHidden(owner);
    }

    private boolean isGlobalyDisabled(EModelElement owner) {
        return owner == DescriptionPackage.eINSTANCE.getRepresentationTemplate_OwnedRepresentations();
    }

    /**
     * depopulate the menu.
     */
    protected void depopulate() {
        descriptors = new LinkedHashSet();
        if (myMenuManager != null) {
            depopulateManager(myMenuManager, advancedChildActions);
        }
    }

    /**
     * This removes from the specified <code>manager</code> all
     * {@link org.eclipse.jface.action.ActionContributionItem}s based on the
     * {@link org.eclipse.jface.action.IAction}s contained in the
     * <code>actions</code> collection.
     * 
     * @param manager
     *            the manager to update.
     * @param actions
     *            the actions to remove from the manager.
     */
    protected void depopulateManager(final IContributionManager manager, final Collection actions) {
        if (actions != null) {
            final IContributionItem[] items = manager.getItems();
            for (IContributionItem item : items) {
                // Look into SubContributionItems
                //
                IContributionItem contributionItem = item;
                while (contributionItem instanceof SubContributionItem) {
                    contributionItem = ((SubContributionItem) contributionItem).getInnerItem();
                }

                // Delete the ActionContributionItems with matching action.
                //
                if (contributionItem instanceof ActionContributionItem) {
                    final IAction action = ((ActionContributionItem) contributionItem).getAction();
                    if (actions.contains(action)) {
                        manager.remove(contributionItem);
                    }
                }
            }
        }
    }

    /**
     * Generate the create child actions and return them.
     * 
     * @param actionDescriptors
     *            descriptor to create the actions from.
     * @param selection
     *            the current selection.
     * @param editor
     *            the current editor.
     * @return the list of actions.
     */
    protected Collection generateCreateChildActions(final Collection actionDescriptors, final ISelection selection, final IEditorPart editor) {
        final Collection actions = new ArrayList();
        if (actionDescriptors != null) {
            for (final Object actionDescriptor : actionDescriptors) {
                actions.add(new CreateChildAction(editor, selection, actionDescriptor));
            }
        }
        return actions;
    }

    /**
     * populate the menu.
     */
    public void populateMenu() {
        if (getMenu().some()) {
            populateManager(getMenu().get(), advancedChildActions, null);
        }
    }

    /**
     * depopulate the menu.
     */
    public void depopulateMenu() {
        if (getMenu().some()) {
            depopulateManager(getMenu().get(), advancedChildActions);
        }
    }

    /**
     * populate the menumanager with the given actions.
     * 
     * @param manager
     *            manager to populate.
     * @param actions
     *            actions to populate.
     * @param contributionID
     *            the Id for the contribution.
     */
    protected void populateManager(final IContributionManager manager, final Collection actions, final String contributionID) {
        if (actions != null) {
            for (final IAction action : Iterables.filter(actions, IAction.class)) {
                if (contributionID != null) {
                    manager.insertBefore(contributionID, action);
                } else {
                    manager.add(action);
                }
            }
        }
        manager.update(true);
    }

    /**
     * Inserts this menu before the EDIT item.
     * 
     * @param parent
     *            the parent in which to inset this menu.
     */
    public void insertBeforeInContainer(IMenuManager parent) {
        createMenuManager();
        populateMenu();
        parent.insertBefore(EDIT, myMenuManager);
    }

    /**
     * Inserts this menu after the EDIT item.
     * 
     * @param parent
     *            the parent in which to inset this menu.
     */
    public void insertAfterInContainer(IMenuManager parent) {
        createMenuManager();
        populateMenu();
        parent.insertAfter(EDIT, myMenuManager);
    }

}
