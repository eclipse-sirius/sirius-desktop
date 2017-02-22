/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Maps;

/**
 * This class is responsible for providing an ID used during the refresh to
 * match candidates and existing elements. We used to use EcoreUtil.getURI() but
 * it was not performing well enough.
 * 
 * The ID is associated with the element through the eAdapters mechanism and as
 * such is stable as long as the element is there.
 * 
 * @author cbrun
 * @since 3.0
 * 
 */
public final class RefreshIdsHolder {

    private static Integer lastID = 0;

    private Map<EObject, Integer> knownObjects = Maps.newHashMap();

    /**
     * Return the element Id if there is one, create a new one if it's not
     * already here.
     * 
     * @param any
     *            any EObject.
     * @return the existing Id or the new one.
     */
    public Integer getOrCreateID(final EObject any) {
        Integer id = knownObjects.get(any);
        if (id == null) {
            id = lastID++;
            knownObjects.put(any, id);
        }
        return id;
    }

    /**
     * clear the internal state.
     */
    public void dispose() {
        this.knownObjects.clear();
    }

    /**
     * Return the IDs holderif there is one, create a new one if it's not
     * already here.
     * 
     * Note that the holder is associated to the given EObject through the
     * EAdapter mechanism and will clear its state when the eAdapter is removed
     * from the EObject (during Resource.unload() for instance).
     * 
     * @param any
     *            any EObject serving as delimiting the context of what is bein
     *            refreshed (a representation for instance.
     * @return the existing Holder or the new one.
     */
    public static RefreshIdsHolder getOrCreateHolder(final EObject any) {
        RefreshIdsHolder factory = RefreshIdsHolder.findHolder(any);
        if (factory == null) {
            factory = new RefreshIdsHolder();
            any.eAdapters().add(new StateFullIDFactoryAdapter(factory));
        }
        return factory;
    }

    private static RefreshIdsHolder findHolder(final EObject any) {
        Iterator<Adapter> it = any.eAdapters().iterator();
        while (it.hasNext()) {
            Adapter adapter = it.next();
            if (adapter instanceof StateFullIDFactoryAdapter) {
                return ((StateFullIDFactoryAdapter) adapter).getID();
            }
        }
        return null;
    }

}

/**
 * Adapter used to keep track of the ID.
 * 
 * @author cbrun
 * 
 */
class StateFullIDFactoryAdapter extends AdapterImpl {

    private final RefreshIdsHolder id;

    /**
     * Create the adapter.
     * 
     * @param id
     *            element ID.
     */
    StateFullIDFactoryAdapter(final RefreshIdsHolder id) {
        this.id = id;
    }

    public RefreshIdsHolder getID() {
        return id;
    }

    @Override
    public void notifyChanged(Notification msg) {
        super.notifyChanged(msg);
        if (msg.getEventType() == Notification.REMOVING_ADAPTER && msg.getOldValue() == this) {
            getID().dispose();
        }
    }

}
