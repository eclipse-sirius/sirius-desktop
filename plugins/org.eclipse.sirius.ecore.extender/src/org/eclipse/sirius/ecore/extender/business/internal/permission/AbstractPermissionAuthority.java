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
package org.eclipse.sirius.ecore.extender.business.internal.permission;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;

import com.google.common.collect.Lists;

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
    protected List<IAuthorityListener> listeners = new ArrayList<IAuthorityListener>();

    /** the locked objects. */
    protected WeakHashMap<EObject, Object> lockedObjects = new WeakHashMap<EObject, Object>();

    /**
     * Check if an object is locked or not.
     * 
     * @param eObject
     *            the object to check
     * @return <code>true</code> if is locked, <code>false</code> otherwise
     */
    protected boolean isLocked(final EObject eObject) {
        return lockedObjects.containsKey(eObject);
    }

    /**
     * Store as locked instance and notify.
     * 
     * @param eObject
     *            the locked instance
     */
    protected void storeAsLockedAndNotify(final EObject eObject) {
        lockedObjects.put(eObject, null);
        final Iterator<IAuthorityListener> iterator = Lists.newArrayList(listeners).iterator();
        while (iterator.hasNext()) {
            final IAuthorityListener listener = iterator.next();
            listener.notifyIsLocked(eObject);
        }
    }

    /**
     * Release and notify.
     * 
     * @param eObject
     *            the locked instance
     */
    protected void releaseFromLockedAndNotify(final EObject eObject) {
        lockedObjects.remove(eObject);
        final Iterator<IAuthorityListener> iterator = Lists.newArrayList(listeners).iterator();
        while (iterator.hasNext()) {
            final IAuthorityListener listener = iterator.next();
            listener.notifyIsReleased(eObject);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#addAuthorityListener(org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener)
     */
    public void addAuthorityListener(final IAuthorityListener listener) {
        // The same listener cannot be added multiple times
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#dispose(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public void dispose(final ResourceSet set) {
        if (set == null) {
            listeners.clear();
            lockedObjects.clear();
        } else {
            // we may clear the map
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#init(org.eclipse.emf.ecore.resource.ResourceSet)
     */
    public void init(final ResourceSet set) {
        if (set != null) {
            // we may create a map for each resource set
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#removeAuthorityListener(org.eclipse.sirius.ecore.extender.business.api.permission.IAuthorityListener)
     */
    public void removeAuthorityListener(final IAuthorityListener listener) {
        listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#setListening(boolean)
     */
    public void setListening(final boolean shouldListen) {
        listen = shouldListen;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#isChanged(org.eclipse.emf.ecore.EObject)
     */
    public boolean isChanged(final EObject instance) {
        return false;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority#isNewInstance(org.eclipse.emf.ecore.EObject)
     */
    public boolean isNewInstance(final EObject instance) {
        return false;
    }

}
