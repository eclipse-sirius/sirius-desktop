/*******************************************************************************
 * Copyright (c) 2009, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.api.menu;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
import org.eclipse.sirius.business.internal.metamodel.helper.EClassHelper;
import org.eclipse.sirius.editor.editorPlugin.SiriusEditorPlugin;
import org.eclipse.sirius.editor.tools.internal.editor.EditorCustomizationManager;
import org.eclipse.sirius.editor.tools.internal.menu.CustomChildTextAdapter;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.ui.IEditorPart;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Abstract class to dynamically build treeview menus.
 * 
 * @author cbrun
 * 
 */
public abstract class AbstractMenuBuilder {
    /**
     * The priority of the New Conditionnal Style menu.
     */
    protected static final int CONDITIONAL_STYLE;

    /**
     * The priority of the New Customization menu.
     */
    protected static final int CUSTOMIZATION;

    /**
     * The priority of the New Diagram Element menu.
     */
    protected static final int DIAGRAM_ELEMENT;

    /**
     * The priority of the New Element Creation menu.
     */
    protected static final int ELEMENT_CREATION;

    /**
     * The priority of the New Element Edition menu.
     */
    protected static final int ELEMENT_EDITION;

    /**
     * The priority of the New Extension menu.
     */
    protected static final int EXTENSION;

    /**
     * The priority of the New Filter menu.
     */
    protected static final int FILTER;

    /**
     * The priority of the New Import menu.
     */
    protected static final int IMPORT;

    /**
     * The priority of the New Layout menu.
     */
    protected static final int LAYOUT;

    /**
     * The priority of the New Menu menu.
     */
    protected static final int MENU;

    /**
     * The priority of the New Navigation menu.
     */
    protected static final int NAVIGATION;

    /**
     * The priority of the New Operation menu.
     */
    protected static final int OPERATION;

    /**
     * The priority of the New Reorder menu.
     */
    protected static final int REORDER;

    /**
     * The priority of the New Representation menu.
     */
    protected static final int REPRESENTATION;

    /**
     * The priority of the New Representation Creation menu.
     */
    protected static final int REPRESENTATION_CREATION;

    /**
     * The priority of the New Simulation menu.
     */
    protected static final int SIMULATION;

    /**
     * The priority of the New Style menu.
     */
    protected static final int STYLE;

    /**
     * The priority of the New Table Element menu.
     */
    protected static final int TABLE_ELEMENT;

    /**
     * The priority of the New Template menu.
     */
    protected static final int TEMPLATE;

    /**
     * The priority of the New Tool menu.
     */
    protected static final int TOOL;

    /**
     * The priority of the New Tree Element menu.
     */
    protected static final int TREE_ELEMENT;

    /**
     * The priority of the New Validation menu.
     */
    protected static final int VALIDATION;

    /**
     * The priority of the New Variable menu.
     */
    protected static final int VARIABLE;

    /**
     * The priority of the New (Others) menu.
     */
    protected static final int OTHERS;

    /**
     * The priority of the Initialize menu.
     */
    protected static final int INITIALIZE;

    /**
     * The priority of the Refactor menu.
     */
    protected static final int REFACTOR;

    private static final String EDIT = "edit";

    private static final int DEFAULT_PRIORITY = 1000000000;

    static {
        ResourceLocator rl = SiriusEditorPlugin.INSTANCE;
        CONDITIONAL_STYLE = getPriority(rl, "ConditionalStylePriority");
        CUSTOMIZATION = getPriority(rl, "CustomizationPriority");
        DIAGRAM_ELEMENT = getPriority(rl, "DiagramElementPriority");
        ELEMENT_CREATION = getPriority(rl, "ElementCreationPriority");
        ELEMENT_EDITION = getPriority(rl, "ElementEditionPriority");
        EXTENSION = getPriority(rl, "ExtensionPriority");
        FILTER = getPriority(rl, "FilterPriority");
        IMPORT = getPriority(rl, "ImportPriority");
        LAYOUT = getPriority(rl, "LayoutPriority");
        MENU = getPriority(rl, "MenuPriority");
        NAVIGATION = getPriority(rl, "NavigationPriority");
        OPERATION = getPriority(rl, "OperationPriority");
        REORDER = getPriority(rl, "ReorderPriority");
        REPRESENTATION = getPriority(rl, "RepresentationPriority");
        REPRESENTATION_CREATION = getPriority(rl, "RepresentationCreationPriority");
        SIMULATION = getPriority(rl, "SimulationPriority");
        STYLE = getPriority(rl, "StylePriority");
        TABLE_ELEMENT = getPriority(rl, "TableElementPriority");
        TEMPLATE = getPriority(rl, "TemplatePriority");
        TOOL = getPriority(rl, "ToolPriority");
        TREE_ELEMENT = getPriority(rl, "TreeElementPriority");
        VALIDATION = getPriority(rl, "ValidationPriority");
        VARIABLE = getPriority(rl, "VariablePriority");
        OTHERS = getPriority(rl, "OthersPriority");
        INITIALIZE = getPriority(rl, "InitializePriority");
        REFACTOR = getPriority(rl, "RefactorPriority");
    }

    /**
     * Child actions for the advanced menu.
     */
    protected Collection advancedChildActions;

    /**
     * Descriptors for the menu action.
     */
    protected Collection descriptors;

    /**
     * Menu manager for the advanced menu.
     */
    protected IMenuManager myMenuManager;

    /**
     * Map associating type keys (as computed by EClassHelper.getPath()), with
     * the priority of the corresponding type.
     */
    private Map<String, Integer> priorityMap = Maps.newHashMap();

    /**
     * Create a new builder.
     */
    public AbstractMenuBuilder() {
        super();
        descriptors = new LinkedHashSet();
        getMenu();
    }

    private static int getPriority(ResourceLocator rl, String id) {
        try {
            return Integer.parseInt(rl.getString(id).trim());
        } catch (NumberFormatException nfe) {
        } catch (MissingResourceException mre) {
        }
        return 100000;
    }

    /**
     * Return the menu child descriptors.
     * 
     * @return the menu child descriptors
     */
    public Collection getMyDescriptors() {
        return descriptors;
    }

    /**
     * Return the menu manager.
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
     * Return the menu label.
     * 
     * @return the menu label.
     */
    public abstract String getLabel();

    /**
     * Return the priority of the menu.
     * 
     * @return the priority.
     */
    public abstract int getPriority();

    /**
     * Attach the menu to its parent.
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
     * Return true if the command parameter is valid for the current builder.
     * 
     * @param object
     *            a command parameter to create new childs.
     * @return true if the command parameter is valid for the current builder.
     */
    protected abstract boolean isMine(CommandParameter object);

    /**
     * Return true if the command parameter is deprecated and should be hidden.
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
     * Depopulate the menu.
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
                final CreateChildAction cca;
                if (actionDescriptor instanceof CommandParameter) {
                    cca = new CustomCreateChildAction(editor, selection, (CommandParameter) actionDescriptor);
                } else {
                    cca = new CreateChildAction(editor, selection, actionDescriptor);
                }
                actions.add(cca);
            }
        }
        return actions;
    }

    /**
     * Populate the menu.
     */
    public void populateMenu() {
        if (getMenu().some()) {
            populateManager(getMenu().get(), advancedChildActions, null);
        }
    }

    /**
     * Depopulate the menu.
     */
    public void depopulateMenu() {
        if (getMenu().some()) {
            depopulateManager(getMenu().get(), advancedChildActions);
        }
    }

    /**
     * Populate the menumanager with the given actions.
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
            List<IAction> sortedActions = Lists.newArrayList(Iterables.filter(actions, IAction.class));
            Comparator<IAction> comparator = new Comparator<IAction>() {
                @Override
                public int compare(IAction a1, IAction a2) {
                    int diff = getPriority(a1) - getPriority(a2);
                    if (diff == 0) {
                        // if both actions have no priority associated, or if
                        // they have the same priority, we compare the text
                        diff = Collator.getInstance().compare(a1.getText(), a2.getText());
                    }
                    return diff;
                }
            };
            Collections.sort(sortedActions, comparator);

            for (final IAction action : sortedActions) {
                if (contributionID != null) {
                    manager.insertBefore(contributionID, action);
                } else {
                    manager.add(action);
                }
            }
        }
        manager.update(true);
    }

    private int getPriority(IAction action) {
        if (action instanceof CustomCreateChildAction) {
            return getPriority(((CustomCreateChildAction) action).getCreatedElementType());
        } else {
            return AbstractMenuBuilder.DEFAULT_PRIORITY;
        }
    }

    private int getPriority(String key) {
        if (key != null && !priorityMap.containsKey(key)) {
            int priority = AbstractMenuBuilder.DEFAULT_PRIORITY;
            try {
                priority = Integer.parseInt(SiriusEditorPlugin.INSTANCE.getString(key).trim());
            } catch (MissingResourceException mre) {
            } catch (NumberFormatException nfe) {
            }
            priorityMap.put(key, priority);
        }
        return Objects.firstNonNull(priorityMap.get(key), DEFAULT_PRIORITY);
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

    /**
     * Custom sub-class of CreateChildAction which knows what kind of element it
     * will create when the descriptor is a CommandParameter.
     */
    private final class CustomCreateChildAction extends CreateChildAction {
        private final String key;
        private String customText;

        private CustomCreateChildAction(IEditorPart editorPart, ISelection selection, CommandParameter descriptor) {
            super(editorPart, selection, descriptor);
            if (descriptor.getValue() instanceof EObject) {
                key = EClassHelper.getPath(((EObject) descriptor.getValue()).eClass());
                EObject eObj = (EObject) descriptor.getValue();
                Adapter ccta = EcoreUtil.getAdapter(eObj.eAdapters(), CustomChildTextAdapter.class);
                if (ccta instanceof CustomChildTextAdapter) {
                    customText = ((CustomChildTextAdapter) ccta).getText();
                    eObj.eAdapters().remove(ccta);
                }
            } else {
                key = null;
            }
        }

        public String getCreatedElementType() {
            return this.key;
        }
        
        @Override
        public String getText() {
            if (customText != null) {
                return customText;
            } else {
                return super.getText();
            }
        }

        @Override
        public String toString() {
            return "CustomCreateChildAction[key = " + key + "]";
        }
    }
}
