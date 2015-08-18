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
package org.eclipse.sirius.common.ui.tools.api.navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.sirius.common.tools.api.constant.CommonPreferencesConstants;
import org.eclipse.sirius.common.tools.api.util.TreeItemWrapper;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.common.ui.tools.internal.preference.DynamicConfigurationHelper;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;

/**
 * An implementation of ITreeContentProvider that surround another
 * ITreeContentProvider to group its children with an intermediary tree level.
 * 
 * This class depends of three preferences that force a plugin execution context
 * to work properly. these preferences can be edited by the user directly in the
 * Eclipse UI preference in the Sirius page.
 * 
 * Actually, we haven't any listener on the preference. An user need to refresh
 * manually the GroupingContentProvider if he updates a preference.
 * 
 * For details about related preferences:
 * 
 * @see CommonPreferencesConstants.PREF_GROUP_ENABLE
 * @see CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE
 * @see CommonPreferencesConstants.PREF_GROUP_SIZE
 * @see CommonPreferencesConstants.PREF_GROUP_TRIGGER
 * 
 */
public class GroupingContentProvider implements ITreeContentProvider {

    /**
     * Encapsulates the configurable parameters of the grouping content
     * provider, and keeps it in sync with possible changes in the preferences.
     */
    private static class Configuration extends DynamicConfigurationHelper {
        int groupSize;

        int groupTrigger;

        boolean groupEnabled;

        boolean groupByContainingFeature;

        Configuration(IPreferenceStore store) {
            super(store);
            bindInt(CommonPreferencesConstants.PREF_GROUP_SIZE, "groupSize"); //$NON-NLS-1$
            bindInt(CommonPreferencesConstants.PREF_GROUP_TRIGGER, "groupTrigger"); //$NON-NLS-1$
            bindBoolean(CommonPreferencesConstants.PREF_GROUP_ENABLE, "groupEnabled"); //$NON-NLS-1$
            bindBoolean(CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE, "groupByContainingFeature"); //$NON-NLS-1$
        }
    }

    private final ITreeContentProvider delegateTreeContentProvider;

    private final Configuration config;

    /**
     * The constructor that required another ITreeContentProvider to surround.
     * 
     * @param delegateTreeContentProvider
     *            The delegated tree content provider
     */
    public GroupingContentProvider(ITreeContentProvider delegateTreeContentProvider) {
        this.delegateTreeContentProvider = delegateTreeContentProvider;
        this.config = new Configuration(SiriusTransPlugin.getPlugin().getPreferenceStore());
    }

    /**
     * Group size getter. The size of children that can be contained in each
     * intermediary tree level.
     * 
     * @see CommonPreferencesConstants.PREF_GROUP_SIZE
     * @return the group size
     */
    public int getGroupSize() {
        return config.groupSize;
    }

    /**
     * Group enable getter. Check if the GroupingContentProvider is enable.
     * 
     * @see CommonPreferencesConstants.PREF_GROUP_ENABLE
     * @return true if the group is enable
     */
    public boolean isGroupEnabled() {
        return config.groupEnabled;
    }

    /**
     * Group by containing feature getter. Check if the GroupByContainingFeature
     * is enable. This value changes the implementation used for the group
     * children. If it set at true, the grouping strategy use the containing
     * feature instead of the basic hierarchy.
     * 
     * @see CommonPreferencesConstants.PREF_GROUP_BY_CONTAINING_FEATURE
     * @return true if the group is enable
     */
    public boolean isGroupByContainingFeature() {
        return config.groupByContainingFeature;
    }

    /**
     * Trigger size getter. The trigger size of children that triggers the
     * display of intermediary tree levels.
     * 
     * @see CommonPreferencesConstants.PREF_GROUP_TRIGGER
     * @return the trigger size if groupSize >= groupTrigger, else the group
     *         size.
     */
    public int getTriggerSize() {
        if (config.groupTrigger >= config.groupSize) {
            return config.groupTrigger;
        } else {
            return config.groupSize;
        }
    }

    @Override
    public void dispose() {
        config.dispose();
        delegateTreeContentProvider.dispose();
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        delegateTreeContentProvider.inputChanged(viewer, oldInput, newInput);

    }

    @Override
    public Object[] getElements(Object parentElement) {
        if (parentElement instanceof GroupingItem) {
            return getChildren(parentElement);
        } else {
            Object[] elements = delegateTreeContentProvider.getElements(parentElement);
            return groupChildren(parentElement, elements);
        }
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        if (parentElement instanceof GroupingItem) {
            return ((GroupingItem) parentElement).getChildren().toArray();
        } else {
            Object[] children = delegateTreeContentProvider.getChildren(parentElement);
            return groupChildren(parentElement, children);
        }
    }

    @Override
    public Object getParent(Object element) {
        if (element instanceof GroupingItem) {
            GroupingItem groupItem = (GroupingItem) element;
            return groupItem.getParent();
        } else {
            return delegateTreeContentProvider.getParent(element);
        }
    }

    @Override
    public boolean hasChildren(Object element) {
        return element instanceof GroupingItem || delegateTreeContentProvider.hasChildren(element);
    }

    /**
     * This function help to group children.
     * 
     * @param parent
     *            the parent
     * @param children
     *            children
     * @return grouped children
     */
    protected Object[] groupChildren(Object parent, Object[] children) {

        Object[] result = children;
        if (isGroupEnabled()) {
            if (isGroupByContainingFeature()) {
                result = groupChildrenByContainingFeature(parent, children);
            } else {
                result = defaultGroupChildren(parent, children);
            }
        }
        return result;
    }

    private Object[] groupChildrenByContainingFeature(Object parent, Object[] children) {
        LinkedListMultimap<Object, Object> childrenContainingMapping = buildChildrenContainerMapping(children);
        List<Object> result = new ArrayList<Object>();
        for (Object structuralFeature : childrenContainingMapping.keySet()) {
            int currentOffset = 0;
            List<Object> indexedChildren = childrenContainingMapping.get(structuralFeature);
            if (indexedChildren.size() > getTriggerSize()) {
                List<List<Object>> partition = Lists.partition(indexedChildren, config.groupSize);
                if (partition.size() > 0) {
                    if (partition.size() > 1) {
                        for (List<Object> partItem : partition) {
                            GroupingItem currentGroup;
                            if (structuralFeature instanceof EStructuralFeature) {
                                currentGroup = new GroupingItem(currentOffset, parent, new ArrayList<Object>(partItem), " " + ((EStructuralFeature) structuralFeature).getName()); //$NON-NLS-1$
                            } else {
                                currentGroup = new GroupingItem(currentOffset, parent, new ArrayList<Object>(partItem));
                            }
                            result.add(currentGroup);
                            currentOffset = currentOffset + partItem.size();
                        }
                    } else {
                        for (List<Object> partItem : partition) {
                            result.addAll(partItem);
                        }
                    }
                }
            } else {
                result.addAll(indexedChildren);
            }
        }
        return result.toArray();

    }

    private LinkedListMultimap<Object, Object> buildChildrenContainerMapping(Object[] children) {
        LinkedListMultimap<Object, Object> childrenContainingMapping = LinkedListMultimap.create();
        Object noContainingFeature = new Object();
        for (Object child : children) {
            Object containingFeature;
            if (child instanceof EObject) {
                containingFeature = ((EObject) child).eContainingFeature();
            } else if (child instanceof TreeItemWrapper) {
                Object wrappedObject = ((TreeItemWrapper) child).getWrappedObject();
                if (wrappedObject instanceof EObject) {
                    containingFeature = ((EObject) wrappedObject).eContainingFeature();
                } else {
                    containingFeature = noContainingFeature;
                }
            } else {
                containingFeature = noContainingFeature;
            }
            childrenContainingMapping.put(containingFeature, child);
        }
        return childrenContainingMapping;
    }

    private Object[] defaultGroupChildren(Object parent, Object[] children) {
        if (children.length > getTriggerSize()) {
            List<List<Object>> partition = Lists.partition(Arrays.asList(children), config.groupSize);
            Object[] result = new Object[partition.size()];

            int indexOfResult = 0;
            for (List<Object> indexedChildren : partition) {
                int currentOffset = indexOfResult * config.groupSize;
                GroupingItem currentGroup = new GroupingItem(currentOffset, parent, indexedChildren);
                result[indexOfResult] = currentGroup;
                indexOfResult++;
            }

            return result;
        } else {
            return children;
        }
    }

    /**
     * Returns the base TreeContentProvider used to delegate calls if no
     * grouping is needed.
     * 
     * @return the base TreeContentProvider used to delegate calls if no
     *         grouping is needed
     */
    public ITreeContentProvider getDelegateTreeContentProvider() {
        return delegateTreeContentProvider;
    }
}
