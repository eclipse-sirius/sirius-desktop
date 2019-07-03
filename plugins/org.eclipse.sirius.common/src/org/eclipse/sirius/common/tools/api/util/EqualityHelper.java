/*******************************************************************************
 * Copyright (c) 2009, 2018 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.common.tools.api.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.google.common.base.Objects;

/**
 * An helper to check EObject equality.</br>
 * It extends and override EcoreUtil.EqualityHelper so that equals methods ignore EAttribute that are ID=true.
 * 
 * @author mchauvin
 */
public final class EqualityHelper extends org.eclipse.emf.ecore.util.EcoreUtil.EqualityHelper {

    private static boolean enableUriFragmentCache;

    private static final Map<EObject, Record> E_URI_FRAGMENT_CACHE = new ConcurrentHashMap<>();

    @Override
    protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute attribute) {
        boolean isID = attribute.isID();
        return isID || super.haveEqualAttribute(eObject1, eObject2, attribute);
    }

    /**
     * Check if a Collection of EObjects contains an EObject, based on their resource and URI fragment.
     * 
     * @param collection
     *            the collection to watch
     * @param eObj
     *            the EObject to find
     * @return <code>true</code> if the collection contains the object, <code>false</code> otherwise
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
     * Remove from a Collection, an EObject, based on their resource and URI fragment.
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
     * Check if two EObject are the same, based on their resource and URI fragment.
     * 
     * @param eObj1
     *            the first EObject to compare
     * @param eObj2
     *            the second EObject to compare
     * @return <code>true</code> if they are equals, <code>false</code> otherwise. If the two objects are both
     *         <code>null</code> return <code>true</code>, otherwise if only one of them is null, return
     *         <code>false</code>
     */
    public static boolean areEquals(EObject eObj1, EObject eObj2) {
        if (Objects.equal(eObj1, eObj2)) {
            return true;
        }
        return haveSameURIFragment(eObj1, eObj2);
    }

    private static boolean haveSameURIFragment(EObject eObj1, EObject eObj2) {
        boolean result = false;
        if (sameType(eObj1, eObj2)) {

            EObject container1 = eObj1.eContainer();
            EObject container2 = eObj2.eContainer();
            if (container1 instanceof InternalEObject && container2 instanceof InternalEObject) {
                String eObj1Frag = getUriFragment((InternalEObject) container1, eObj1.eContainingFeature(), eObj1);
                String eObj2Frag = getUriFragment((InternalEObject) container2, eObj2.eContainingFeature(), eObj2);

                if (eObj1Frag != null && eObj2Frag != null && eObj1Frag.equals(eObj2Frag)) {
                    result = haveSameURIFragment(container1, container2);
                }
            } else if (container1 == null && container2 == null) {
                Resource res1 = eObj1.eResource();
                Resource res2 = eObj2.eResource();
                if (res1 != null && res2 != null) {
                    URI uriRes1 = res1.getURI();
                    URI uriRes2 = res2.getURI();
                    if ((uriRes1.isPlatformPlugin() && uriRes2.isPlatformPlugin()) || (uriRes1.isPlatformResource() && uriRes2.isPlatformResource())) {
                        result = uriRes1.equals(uriRes2);
                    }
                }
            } else {
                /*
                 * one of the containers is null.. no chance both objects are sharing the same URI.
                 */
            }

        }
        return result;
    }

    private static String getUriFragment(InternalEObject container, EStructuralFeature eContainingFeature, EObject eObj) {
        String uriFragment;

        if (enableUriFragmentCache) {
            Record record = E_URI_FRAGMENT_CACHE.get(eObj);
            if (record != null && record.matches(container, eContainingFeature)) {
                uriFragment = E_URI_FRAGMENT_CACHE.get(eObj).getUriFragment();
            } else {
                uriFragment = container.eURIFragmentSegment(eContainingFeature, eObj);
                E_URI_FRAGMENT_CACHE.put(eObj, new Record(uriFragment, container, eContainingFeature));
            }
        } else {
            uriFragment = container.eURIFragmentSegment(eContainingFeature, eObj);
        }
        return uriFragment;
    }

    private static boolean sameType(EObject eObj1, EObject eObj2) {
        return eObj1 != null && eObj2 != null && eObj1.getClass() == eObj2.getClass();
    }

    /**
     * Enable or disable the ability to cache the computed values. The cache is cleared when this method is called to
     * disable the cache.
     * 
     * @param enable
     *            <code>true</code> to allow this helper to put the computed values in a cache, <code>false</code>
     *            otherwise.
     */
    public static synchronized void setUriFragmentCacheEnabled(boolean enable) {
        enableUriFragmentCache = enable;

        if (!enable) {
            E_URI_FRAGMENT_CACHE.clear();
        }
    }

    private static class Record {

        private final String uriFragment;

        private final EObject eContainer;

        private final EStructuralFeature containingFeature;

        Record(String uriFragment, EObject container, EStructuralFeature containingFeature) {
            this.uriFragment = uriFragment;
            this.eContainer = container;
            this.containingFeature = containingFeature;
        }

        /**
         * Compare the given container and feature to the recorded ones.
         * 
         * @param container
         *            a potential container.
         * @param feature
         *            a potential containing feature.
         * @return <code>true</code> if the given container and feature are the same instances than the one referenced
         *         by the current Record, <code>false</code> otherwise.
         */
        public boolean matches(EObject container, EStructuralFeature feature) {
            return this.eContainer == container && this.containingFeature == feature;
        }

        public String getUriFragment() {
            return uriFragment;
        }
    }
}
