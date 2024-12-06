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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;

/**
 * A basic permission authority which will manage a list of listeners.
 * 
 * @author mchauvin
 */
public abstract class AbstractPermissionAuthority implements IPermissionAuthority {

    /** the listen boolean. */
    protected boolean listen;

    /** the authority listeners. */
    protected List<IAuthorityListener> listeners = new CopyOnWriteArrayList<IAuthorityListener>();

    /** the locked objects. */
    protected Set<Object> lockedObjects = Collections.newSetFromMap(new WeakHashMap<Object, Boolean>()); 

    /**
     * Check if an object is locked or not.
     * 
     * @param eObject
     *            the object to check
     * @return <code>true</code> if is locked, <code>false</code> otherwise
     */
    protected boolean isLocked(final EObject eObject) {
        return lockedObjects.contains(eObject);
    }

    /**
     * Store as locked instance and notify.
     * 
     * @param eObject
     *            the locked instance
     */
    protected void storeAsLockedAndNotify(final EObject eObject) {
        lockedObjects.add(eObject);
        for (IAuthorityListener listener : listeners) {
            listener.notifyIsLocked(eObject);
        }
    }

    @Override
    public List<EObject> getLockedObjects() {
        return lockedObjects.stream().filter(EObject.class::isInstance).map(EObject.class::cast).toList();
    }

    /**
     * Release and notify.
     * 
     * @param eObject
     *            the locked instance
     */
    protected void releaseFromLockedAndNotify(final EObject eObject) {
        lockedObjects.remove(eObject);
        for (IAuthorityListener listener : listeners) {
            listener.notifyIsReleased(eObject);
        }
    }

    @Override
    public void addAuthorityListener(final IAuthorityListener listener) {
        // The same listener cannot be added multiple times
        if (!listeners.contains(listener)) {
            listeners.add(Objects.requireNonNull(listener));
        }
    }

    @Override
    public void dispose(final ResourceSet set) {
        listeners.clear();
        lockedObjects.clear();
    }

    @Override
    public void init(final ResourceSet set) {
        if (set != null) {
            // we may create a map for each resource set
        }
    }

    @Override
    public void removeAuthorityListener(final IAuthorityListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void setListening(final boolean shouldListen) {
        listen = shouldListen;
    }

    @Override
    public boolean isChanged(final EObject instance) {
        return false;
    }

    @Override
    public boolean isNewInstance(final EObject instance) {
        return false;
    }
}
