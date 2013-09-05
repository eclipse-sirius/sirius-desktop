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
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * An helper to check EObject equality.
 * 
 * @author mchauvin
 */
public final class EqualityHelper {

    private EqualityHelper() {
    }

    /**
     * Check if a Collection of EObjects contains an EObject, based on their
     * resource and URI fragment.
     * 
     * @param collection
     *            the collection to watch
     * @param eObj
     *            the EObject to find
     * @return <code>true</code> if the collection contains the object,
     *         <code>false</code> otherwise
     */
    public static boolean contains(final Collection<? extends EObject> collection, final EObject eObj) {
        for (final EObject object : collection) {
            if (EqualityHelper.areEquals(object, eObj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Remove from a Collection, an EObject, based on their resource and URI
     * fragment.
     * 
     * @param collection
     *            the collection
     * @param eObj
     *            the EObject to remove
     */
    public static void remove(final Collection<? extends EObject> collection, final EObject eObj) {
        final Iterator<? extends EObject> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final EObject next = iterator.next();
            if (EqualityHelper.areEquals(next, eObj)) {
                iterator.remove();
            }
        }
    }

    /**
     * Check if two EObject are the same, based on their resource and URI
     * fragment.
     * 
     * @param eObj1
     *            the first EObject to compare
     * @param eObj2
     *            the second EObject to compare
     * @return <code>true</code> if they are equals, <code>false</code>
     *         otherwise. If the two objects are both <code>null</code> return
     *         <code>true</code>, otherwise if only one of them is null, return
     *         <code>false</code>
     */
    public static boolean areEquals(final EObject eObj1, final EObject eObj2) {

        if (eObj1 == eObj2 || (eObj1 != null && eObj1.equals(eObj2))) {
            return true;
        }

        boolean equal = false;

        if (eObj1 != null && eObj2 != null && eObj1.getClass() == eObj2.getClass()) {
            final Resource res1 = eObj1.eResource();
            final Resource res2 = eObj2.eResource();
            if (res1 != null && res2 != null) {
                final URI uriRes1 = res1.getURI();
                final URI uriRes2 = res2.getURI();
                if ((uriRes1.isPlatformPlugin() && uriRes2.isPlatformPlugin()) || (uriRes1.isPlatformResource() && uriRes2.isPlatformResource())) {
                    /*
                     * return false if uris are not the same for performance,
                     * because getURIFFragment cost is high.
                     */
                    if (!uriRes1.equals(uriRes2)) {
                        equal = false;
                    } else {
                        equal = res1.getURIFragment(eObj1).equals(res2.getURIFragment(eObj2));
                    }
                }
            }
        }
        return equal;
    }

}
