/*******************************************************************************
 * Copyright (c) 2007, 2022 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.ecore.extender.business.api.permission;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

/**
 * A permission authority is able to say whether the software has the right, or not, to set/unset add or remove
 * attributes.
 * 
 * @author cbrun
 * 
 */
public interface IPermissionAuthority {
    /**
     * return true if the software may edit the data, false otherwise.
     * 
     * @param eObj
     *            instance to edit.
     * @param featureName
     *            name of the feature to edit.
     * @return true if the software may edit the data, false otherwise.
     */
    boolean canEditFeature(EObject eObj, String featureName);

    /**
     * return true if the software may edit the data, false otherwise.
     * 
     * @param eObj
     *            instance to edit.
     * @return true if the software may edit the data, false otherwise.
     */
    boolean canEditInstance(EObject eObj);

    /**
     * Check whether an element can have new children or not.
     * 
     * @param eObj
     *            any instance.
     * @return true if an instance may be aggregated to the given one, false otherwise.
     */
    boolean canCreateIn(EObject eObj);

    /**
     * return true if the given instance is a newly created one.
     * 
     * @param instance
     *            any instance.
     * @return true if the given instance is a newly created one.
     */
    boolean isNewInstance(EObject instance);

    /**
     * return true if the given instance has been changed.
     * 
     * @param instance
     *            any instance.
     * @return true if the given instance has been changed.
     */
    boolean isChanged(EObject instance);

    /**
     * Called when an instance has been changed.
     * 
     * @param instance
     *            changed instance.
     */
    void notifyInstanceChange(EObject instance);

    /**
     * Called when an instance has been created.
     * 
     * @param instance
     *            new instance.
     */
    void notifyNewInstanceCreation(EObject instance);

    /**
     * Called when an instance has been deleted.
     * 
     * @param instance
     *            deleted instance.
     */
    void notifyInstanceDeletion(EObject instance);

    /**
     * initialize the permission authority.
     * 
     * @param set
     *            the model.
     */
    void init(ResourceSet set);

    /**
     * Clear the permission authority.
     * 
     * @param set
     *            the model.
     */
    void dispose(ResourceSet set);

    /**
     * Add a new listener on the current authority.
     * 
     * @param listener
     *            new listener.
     */
    void addAuthorityListener(IAuthorityListener listener);

    /**
     * Remove an existing listener from the current authority.
     * 
     * @param listener
     *            listener to remove.
     */
    void removeAuthorityListener(IAuthorityListener listener);

    /**
     * Tell whether the authority should report issues through Exceptions.
     * 
     * @param report
     *            if true then the permission authority will complain when a locked element has been changed.
     */
    void setReportIssues(boolean report);

    /**
     * Tell whether the authority should listen the model changes and notify listeners accordingly or not.
     * 
     * @param shouldListen
     *            true if the authority should listen for changes, false otherwise.
     */
    void setListening(boolean shouldListen);

    /**
     * Tell whether the given instance may be deleted or not. An instance can be deleted if the following conditions are
     * satisfied:
     * <ul>
     * <li>The parent modification is authorized. (The deletion will modify the containing feature)</li>
     * <li>The element itself can be deleted.</li>
     * <li>The children can be deleted.</li>
     * </ul>
     * 
     * @param target
     *            any instance.
     * @return true if the instance can be deleted.
     */
    boolean canDeleteInstance(EObject target);

    /**
     * Notify the lock of given elements.
     * 
     * @param elements
     *            the locked elements
     */
    void notifyLock(Collection<? extends EObject> elements);

    /**
     * Notify the unlock of given elements.
     * 
     * @param elements
     *            the unlocked elements
     */
    void notifyUnlock(Collection<? extends EObject> elements);

    /**
     * Get the {@link LockStatus} of the <code>element</code> {@link EObject}.
     * 
     * @param element
     *            the {@link EObject} for which we want {@link LockStatus}
     * 
     * @return the {@link LockStatus} of <code>element</code>
     */
    LockStatus getLockStatus(EObject element);

    /**
     * Get the locked objects.
     * 
     * @return an unmodifiable list of the locked objects.
     */
    List<EObject> getLockedObjects();

    /**
     * Check if an {@link EObject} is frozen.
     * 
     * @param eObject
     *            the {@link EObject} to check if frozen.
     * @return if the {@link EObject} is frozen or not.
     */
    default boolean isFrozen(EObject eObject) {
        return false;
    };
}
