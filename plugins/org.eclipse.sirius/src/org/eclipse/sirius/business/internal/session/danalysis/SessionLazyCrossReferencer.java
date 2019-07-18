/*******************************************************************************
 * Copyright (c) 2014, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.eclipse.sirius.common.tools.internal.util.FastInverseCrossReferencesList;
import org.eclipse.sirius.viewpoint.DRepresentation;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * A LazyCrossReferencer for the session. {@link LazyCrossReferencer#initialize()} is overridden in order to only add
 * the adapter at the first use.
 * 
 * @author <a href="mailto:mickael.lanoe@obeo.fr">Mickael LANOE</a>
 *
 */
public class SessionLazyCrossReferencer extends SiriusCrossReferenceAdapter {
    /**
     * Flag to know if the LazyCrossReferencer has been initialized.
     */
    protected boolean initialized;

    private DAnalysisSessionImpl session;

    private Predicate<EObject> eObjectToBeIgnoredPredicate = obj -> false;

    /**
     * InverseCrossReferencer to allow access to {@link #removeProxies(URI)} in '@link InternalCrossReferencer}.
     * 
     * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
     */
    protected class LocalInverseCrossReferencer extends InverseCrossReferencer {
        private static final long serialVersionUID = 1L;

        @Override
        protected List<EObject> removeProxies(URI uri) {
            return super.removeProxies(uri);
        }

        /**
         * Check if the proxyMap is null or not. Since change in ECrossReferenceAdapter (bugzilla 400891), the proxyMap
         * is no longer used if the resolve() method returns true. In this case, we must iterate on all crossReferences
         * to retrieve corresponding proxies.
         * 
         * @return true if the proxy map is null.
         */
        public boolean isNullMapProxy() {
            return proxyMap == null;
        }

        /**
         * Get all proxy {@link EObject EObjects} that have URI corresponding to the <code>resourceURI</code>. Warning:
         * this map is computed at each call and can be costly.
         * 
         * @param resourceURI
         *            The URI of the resource for which we want to get the proxy EObjects.
         * @return map of proxies with the URI as key.
         */
        public Map<URI, List<EObject>> getProxiesOf(URI resourceURI) {
            Map<URI, List<EObject>> result = new HashMap<>();
            if (resourceURI != null) {
                for (Iterator<EObject> iterator = keySet().iterator(); iterator.hasNext(); /* */) {
                    EObject eObject = iterator.next();
                    if (eObject.eIsProxy()) {
                        URI keyURI = EcoreUtil.getURI(eObject);
                        if (resourceURI.equals(keyURI.trimFragment())) {
                            List<EObject> correspondingEObjects = result.get(keyURI);
                            if (correspondingEObjects == null) {
                                correspondingEObjects = new ArrayList<>();
                            }
                            correspondingEObjects.add(eObject);
                            result.put(keyURI, correspondingEObjects);
                        }
                    }
                }
            }
            return result;
        }

        @Override
        protected Collection<EStructuralFeature.Setting> newCollection() {
            return new FastInverseCrossReferencesList(() -> !SessionLazyCrossReferencer.this.settingTargets || SessionLazyCrossReferencer.this.resolve());
        }
    }

    /**
     * Construct from an opened session.
     * 
     * @param session
     *            an opened session
     */
    public SessionLazyCrossReferencer(DAnalysisSessionImpl session) {
        this.session = session;
    }

    @Override
    protected InverseCrossReferencer createInverseCrossReferencer() {
        return new LocalInverseCrossReferencer();
    }

    /**
     * Look at all EObjects of the specified resource and resolve proxy cross reference that reference these
     * EObjects.<BR>
     * A part of {@link #resolveAll(EObject)} has been duplicated to avoid the time consumption of accessing to
     * resourceURI for each objects of the same resource.
     * 
     * @param resource
     *            Each cross reference pointing to a proxy of this <code>resource</code> will be resolved.
     */
    public void resolveProxyCrossReferences(Resource resource) {
        if (initialized) {
            // The resolution of proxy is called only is the cross-referencer
            // has already been initialized.

            if (resource != null) {
                URI resourceURI = resource.getURI();
                if (resourceURI != null) {
                    ResourceSet resourceSet = resource.getResourceSet();
                    if (resourceSet != null) {
                        resourceURI = resourceSet.getURIConverter().normalize(resourceURI);
                    }
                }
                final Iterator<EObject> it = resource.getAllContents();
                // Since change in ECrossReferenceAdapter (bugzilla 400891),
                // the proxyMap is no longer used if the resolve() method
                // returns true. In this case, we must iterate on all
                // crossReferences to retrieve corresponding proxies.
                if (((LocalInverseCrossReferencer) inverseCrossReferencer).isNullMapProxy()) {
                    // Get proxies of this resource
                    Map<URI, List<EObject>> proxies = ((LocalInverseCrossReferencer) inverseCrossReferencer).getProxiesOf(resourceURI);
                    // Iterate on all EObject of the resource to find proxy cross
                    // references and resolve them.
                    while (it.hasNext()) {
                        EObject eObject = it.next();
                        URI eObjectURI = getURI(eObject, resource, resourceURI);
                        resolveProxyCrossReferences(eObject, resource, proxies.get(eObjectURI));
                    }
                } else {
                    // Code used for Juno
                    while (it.hasNext()) {
                        EObject eObject = it.next();
                        URI eObjectURI = getURI(eObject, resource, resourceURI);
                        List<EObject> proxies = ((LocalInverseCrossReferencer) inverseCrossReferencer).removeProxies(eObjectURI);
                        resolveProxyCrossReferences(eObject, resource, proxies);
                    }
                }
            }
        }
    }

    /**
     * Get URI of this <code>eObject</code>.
     * 
     * @param eObject
     *            Concerned {@link EObject}
     * @param resource
     *            Resource containing the <code>eObject</code>
     * @param resourceURI
     *            URI of <code>resource</code>
     * 
     * @return the URI of this <code>eObject</code>.
     */
    private URI getURI(EObject eObject, Resource resource, URI resourceURI) {
        URI eObjectURI;
        if (resourceURI != null) {
            eObjectURI = resourceURI.appendFragment(resource.getURIFragment(eObject));
        } else {
            eObjectURI = URI.createHierarchicalURI(null, null, resource.getURIFragment(eObject));
        }
        return eObjectURI;
    }

    /**
     * Resolve the proxy cross references of the current <code>eObject</code>.
     * 
     * @param eObject
     *            Current EObject (not a proxy)
     * @param resource
     *            Resource containing the <code>eObject</code>
     * @param proxies
     *            Corresponding proxies of eObject
     */
    private void resolveProxyCrossReferences(EObject eObject, Resource resource, List<EObject> proxies) {
        if (proxies != null) {
            for (int i = 0, size = proxies.size(); i < size; ++i) {
                EObject proxy = proxies.get(i);
                for (EStructuralFeature.Setting setting : getInverseReferences(proxy, false)) {
                    resolveProxy(resource, eObject, proxy, setting);
                }
            }
        }
    }

    @Override
    public void dump() {
        if (!initialized) {
            initialize();
        }
        super.dump();
    }

    /**
     * Set a predicate to know if inverse references has to be searched for a given EObject.</br>
     * This can be useful to prevent the resolution of some references of the given EObject.
     * 
     * @param predicate
     *            the predicate
     */
    public void setEObjectToBeIgnored(Predicate<EObject> predicate) {
        this.eObjectToBeIgnoredPredicate = predicate;
    }

    @Override
    public Collection<Setting> getInverseReferences(final EObject object, final boolean resolve) {
        if (!eObjectToBeIgnoredPredicate.test(object)) {
            if (!initialized) {
                initialize();
            }
            return super.getInverseReferences(object, resolve);
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Setting> getInverseReferences(final EObject object) {
        if (!eObjectToBeIgnoredPredicate.test(object)) {
            if (!initialized) {
                initialize();
            }
            return super.getInverseReferences(object);
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Setting> getNonNavigableInverseReferences(final EObject object, final boolean resolve) {
        if (!eObjectToBeIgnoredPredicate.test(object)) {
            if (!initialized) {
                initialize();
            }
            return super.getNonNavigableInverseReferences(object, resolve);
        }
        return Collections.emptyList();
    }

    @Override
    public Collection<Setting> getNonNavigableInverseReferences(final EObject object) {
        if (!eObjectToBeIgnoredPredicate.test(object)) {
            if (!initialized) {
                initialize();
            }
            return super.getNonNavigableInverseReferences(object);
        }
        return Collections.emptyList();
    }

    @Override
    public Notifier getTarget() {
        if (!initialized) {
            initialize();
        }
        return super.getTarget();
    }

    @Override
    public boolean isAdapterForType(final Object type) {
        if (!initialized) {
            initialize();
        }
        return super.isAdapterForType(type);
    }

    @Override
    public void notifyChanged(final Notification notification) {
        if (!initialized) {
            initialize();
        }
        super.notifyChanged(notification);
    }

    @Override
    public void setTarget(final Notifier target) {
        if (!initialized) {
            initialize();
        }
        super.setTarget(target);
    }

    @Override
    public void unsetTarget(final Notifier target) {
        if (!initialized) {
            initialize();
        }
        super.unsetTarget(target);
    }

    @Override
    protected void handleContainment(Notification notification) {
        deregisterDeletedElements(notification);

        super.handleContainment(notification);
    }

    /**
     * This method removes the current cross referencer adapter from adapters of removed elements. The removeAdapter
     * method propagate the removal to all contents of its parameter.
     * 
     * @param notification
     *            a containment notification
     */
    protected void deregisterDeletedElements(Notification notification) {
        switch (notification.getEventType()) {

        case Notification.UNSET:
        case Notification.SET:
        case Notification.REMOVE:
            Object oldValue = notification.getOldValue();
            if (oldValue instanceof Notifier) {
                removeAdapterIfNecessary(notification, (Notifier) oldValue);
            }
            break;

        case Notification.REMOVE_MANY:
            for (Notifier oldVal : Iterables.filter((Collection<?>) notification.getOldValue(), Notifier.class)) {
                removeAdapterIfNecessary(notification, oldVal);
            }
            break;

        default:
            break;
        }
    }

    /**
     * This method does not remove the adapter from the notification old value in two cases:
     * <ul>
     * <li>if its new container is already set and also has the adapter</li>
     * <li>if the element is now a root of a resource which has the adapter</li>
     * </ul>
     * 
     * @param notification
     *            a containment notification
     * @param oldValue
     *            notification old value on which the adapter may be removed
     */
    private void removeAdapterIfNecessary(Notification notification, Notifier oldValue) {
        boolean toRemove = true;

        if (oldValue instanceof EObject) {
            EObject removedEObject = (EObject) oldValue;

            Notifier currentContainer = removedEObject.eContainer();
            if (currentContainer == null) {
                currentContainer = removedEObject.eResource();
            }

            if (currentContainer != null && currentContainer != notification.getNotifier() && currentContainer.eAdapters().contains(this)) {
                toRemove = false;
            }
        }

        if (toRemove) {
            removeAdapter(oldValue);
        }
    }

    /**
     * Returns the session associated to this cross-reference.
     * 
     * @return the session.
     */
    public DAnalysisSessionImpl getSession() {
        return session;
    }

    /**
     * Adapts all resources of the session with this cross referencer.
     */
    protected void initialize() {
        initialized = true;

        Collection<Resource> semanticResources = session.getSemanticResources();
        EList<Resource> controlledResources = session.getControlledResources();
        Set<Resource> allSessionResources = session.getAllSessionResources();
        Collection<Resource> srmFiles = session.getSrmResources();

        Iterable<Resource> resources = Iterables.concat(semanticResources, controlledResources, allSessionResources);
        for (Resource resource : resources) {
            List<Adapter> adapters = resource.eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }
        for (Resource resource : srmFiles) {
            // Since https://bugs.eclipse.org/bugs/show_bug.cgi?id=471106 fix in EMF (initially fixed in Sirius by
            // https://git.eclipse.org/r/#/c/50654/) some feature can be indexed twice by the crossreferencer. To avoid
            // that behavior we perform a resolve all before installing the XRef.
            EcoreUtil.resolveAll(resource);
            List<Adapter> adapters = resource.eAdapters();
            // add only if it was not added between creation and
            // initialization
            if (!adapters.contains(this)) {
                adapters.add(this);
            }
        }
    }

    @Override
    protected void selfAdapt(Notification notification) {
        if (isTopLevelRepresentationRemoval(notification)) {
            if (!unloadedResources.contains(notification.getNotifier())) {
                handleContainment(notification);
            }
        }
        super.selfAdapt(notification);
    }

    /**
     * Say if a {@link DRepresentation} is being removed from the resource.
     * 
     * @param notification
     *            the notification
     * @return true is the DRepresentation is being removed.
     */
    public static boolean isTopLevelRepresentationRemoval(Notification notification) {
        Object notifier = notification.getNotifier();
        boolean isResourceContentChange = notifier instanceof Resource && notification.getFeatureID(Resource.class) == Resource.RESOURCE__CONTENTS;
        final boolean isRepresentationRemoval;
        if (notification.getEventType() == Notification.REMOVE && notification.getOldValue() instanceof DRepresentation) {
            isRepresentationRemoval = true;
        } else if (notification.getEventType() == Notification.REMOVE_MANY) {
            Collection<?> removed = (Collection<?>) notification.getOldValue();
            isRepresentationRemoval = Iterables.all(removed, Predicates.instanceOf(DRepresentation.class));
        } else {
            isRepresentationRemoval = false;
        }
        return isResourceContentChange && isRepresentationRemoval;
    }
}
