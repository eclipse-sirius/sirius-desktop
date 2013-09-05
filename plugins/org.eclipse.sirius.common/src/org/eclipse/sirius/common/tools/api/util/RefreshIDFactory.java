/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * This class is responsible for providing an ID used during the refresh to
 * match candidates and existing elements. We used to use EcoreUtil.getURI() but
 * it was not performing well enough.
 * 
 * The ID is associated with the element through the eAdapters mechanism and as
 * such is stable as long as the element is there.
 * 
 * @author cbrun
 * 
 */
public final class RefreshIDFactory {

    private static Integer lastID = 0;

    private RefreshIDFactory() {

    }

    /**
     * Return the element Id if there is one, create a new one if it's not
     * already here.
     * 
     * @param any
     *            any EObject.
     * @return the existing Id or the new one.
     */
    public static Integer getOrCreateID(final EObject any) {
        Integer id = RefreshIDFactory.findID(any);
        if (id == null) {
            id = lastID++;
            any.eAdapters().add(new IDAdapter(id));
        }
        return id;
    }

    private static Integer findID(final EObject any) {
        for (final Adapter adapter : any.eAdapters()) {
            if (adapter instanceof IDAdapter) {
                return ((IDAdapter) adapter).getID();
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
class IDAdapter extends AdapterImpl {

    private final Integer id;

    /**
     * Create the adapter.
     * 
     * @param id
     *            element ID.
     */
    public IDAdapter(final Integer id) {
        this.id = id;
    }

    public Integer getID() {
        return id;
    }

}
