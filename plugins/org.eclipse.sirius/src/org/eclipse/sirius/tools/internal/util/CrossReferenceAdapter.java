/******************************************************************************
 * Copyright (c) 2006, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    IBM Corporation - initial API and implementation
 *    Mariot Chauvin <mariot.chauvin@obeo.fr> - Checkstylized
 ****************************************************************************/

package org.eclipse.sirius.tools.internal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EStructuralFeature.Setting;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.ECrossReferenceEList;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * This class comes from GMF. An adapter that maintains itself as an adapter for
 * all contained objects. It can be installed for an {@link EObject}, a
 * {@link Resource}, or a {@link ResourceSet}.
 * <p>
 * This adapter maintain information on inverse references, resource imports,
 * and resource exports.
 * 
 * author Christian Vogt (cvogt) author Christian W. Damus (cdamus)
 * 
 * @author mchauvin (added for checktyle)
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CrossReferenceAdapter extends ECrossReferenceAdapter {

    private static Map<EClass, List<EStructuralFeature>> eClassToChangeableFeatures = new HashMap<EClass, List<EStructuralFeature>>();

    private static List nullList = new ArrayList(1);

    private Map<Resource, Map<Resource, Counter>> imports = new HashMap<Resource, Map<Resource, Counter>>();

    private Map<Resource, Map<Resource, Counter>> exports = new HashMap<Resource, Map<Resource, Counter>>();

    private boolean resolve = true;

    /**
     * Initializes me.
     */
    public CrossReferenceAdapter() {
        this(true);
    }

    /**
     * Initializes me.
     * 
     * @param resolve
     *            flag to determine if the proxies need to be resolved
     */
    public CrossReferenceAdapter(final boolean resolve) {
        super();

        this.resolve = resolve;
    }

    /**
     * Updates imports and exports maps.
     * 
     * @param notification
     *            the event notification
     */
    @Override
    public void selfAdapt(final Notification notification) {
        super.selfAdapt(notification);
        final Object notifier = notification.getNotifier();
        final Object feature = notification.getFeature();

        // update import / export information when a resource
        // is unloaded or loaded
        if (notifier instanceof Resource) {
            if (notification.getFeatureID(Resource.class) == Resource.RESOURCE__IS_LOADED) {
                if (!notification.getNewBooleanValue()) {
                    deregisterReferences((Resource) notifier);
                } else {
                    final Iterator<EObject> iterContents = ((Resource) notifier).getContents().iterator();
                    while (iterContents.hasNext()) {
                        final EObject child = iterContents.next();
                        if (child != null) {
                            updateImportsAndExports((Resource) notifier, child, true);
                        }
                    }
                }
            }

            return;
        }

        // interested in maintaining import / export information
        // when the notifier is an EObject and the feature is a
        // non-containment EReference
        if (!(notifier instanceof EObject) || !(feature instanceof EReference)) {
            return;
        }

        final EReference reference = (EReference) feature;
        if (isImportExportCapable(reference, (EObject) notifier)) {

            EObject newValue;
            EObject oldValue;
            switch (notification.getEventType()) {
            case Notification.RESOLVE:
            case Notification.SET:
            case Notification.UNSET:
                oldValue = (EObject) notification.getOldValue();
                if (oldValue != null) {
                    deregisterReference(((EObject) notification.getNotifier()).eResource(), oldValue.eResource());
                }
                newValue = (EObject) notification.getNewValue();
                if (newValue != null) {
                    registerReference(((EObject) notification.getNotifier()).eResource(), newValue.eResource());
                }
                break;
            case Notification.ADD:
                newValue = (EObject) notification.getNewValue();
                if (newValue != null) {
                    registerReference(((EObject) notification.getNotifier()).eResource(), newValue.eResource());
                }
                break;
            case Notification.ADD_MANY:
                final Collection<EObject> newValues = (Collection<EObject>) notification.getNewValue();
                final Iterator<EObject> iterNewValues = newValues.iterator();
                while (iterNewValues.hasNext()) {
                    newValue = iterNewValues.next();
                    registerReference(((EObject) notification.getNotifier()).eResource(), newValue.eResource());
                }
                break;
            case Notification.REMOVE:
                oldValue = (EObject) notification.getOldValue();
                if (oldValue != null) {
                    deregisterReference(((EObject) notification.getNotifier()).eResource(), oldValue.eResource());
                }
                break;
            case Notification.REMOVE_MANY:
                final Collection<EObject> oldValues = (Collection<EObject>) notification.getOldValue();
                final Iterator<EObject> iterOldValues = oldValues.iterator();
                while (iterOldValues.hasNext()) {
                    oldValue = iterOldValues.next();
                    deregisterReference(((EObject) notification.getNotifier()).eResource(), oldValue.eResource());
                }
                break;
            default:
                break;
            }
        }
    }

    /**
     * Extends the superclass method to handle the removal cases of containment,
     * to tear down aggregate (resource-level) cross-references.
     * 
     * @param notification
     *            the notification.
     */
    @Override
    protected void handleContainment(final Notification notification) {
        super.handleContainment(notification);

        final Object notifier = notification.getNotifier();
        if (notifier instanceof ResourceSet) {
            // not interested in removal of resources from the resource set
            return;
        }
        Resource resource;
        switch (notification.getEventType()) {
        case Notification.ADD:
            final EObject newValue = (EObject) notification.getNewValue();

            if (newValue != null) {
                if (notifier instanceof Resource) {
                    resource = (Resource) notifier;
                } else {
                    resource = ((EObject) notification.getNotifier()).eResource();
                }

                // handle processing of the new value that has been added
                updateImportsAndExports(resource, newValue, true);
            }

            break;
        case Notification.ADD_MANY:
            if (notifier instanceof Resource) {
                resource = (Resource) notifier;
            } else {
                resource = ((EObject) notification.getNotifier()).eResource();
            }

            final Collection<EObject> newValues = (Collection<EObject>) notification.getNewValue();
            final Iterator<EObject> iterNewValues = newValues.iterator();
            while (iterNewValues.hasNext()) {
                final EObject next = iterNewValues.next();

                if (next != null) {
                    // handle processing of the new value that has been added
                    updateImportsAndExports(resource, next, true);
                }
            }
            break;

        case Notification.REMOVE:
            final EObject oldValue = (EObject) notification.getOldValue();

            if (oldValue != null) {

                if (notifier instanceof Resource) {
                    resource = (Resource) notifier;
                } else {
                    resource = ((EObject) notification.getNotifier()).eResource();
                }

                // handle processing of the old value that has been removed
                updateImportsAndExports(resource, oldValue, false);
            }
            break;
        case Notification.REMOVE_MANY:
            if (notifier instanceof Resource) {
                resource = (Resource) notifier;

                if (!resource.isLoaded()) {
                    // purge the resource from the imports/exports map
                    deregisterReferences(resource);
                    return;
                }
            } else {
                resource = ((EObject) notification.getNotifier()).eResource();
            }

            final Collection<EObject> oldValues = (Collection<EObject>) notification.getOldValue();
            final Iterator<EObject> iterOldValues = oldValues.iterator();
            while (iterOldValues.hasNext()) {
                final EObject next = iterOldValues.next();

                if (next != null) {
                    // handle processing of the old value that has been removed
                    updateImportsAndExports(resource, next, false);
                }
            }
            break;
        default:
            break;
        }
    }

    /**
     * Updates the imports and exports map for the specified eObject.
     * 
     * @param resource
     *            a resource
     * @param value
     *            the specified eObject
     * @param register
     *            boolean flag to indicate whether to register imports or
     *            unregister imports
     */
    public void updateImportsAndExports(final Resource resource, final EObject value, final boolean register) {
        final CrossReferenceAdapter adapter = CrossReferenceAdapter.getExistingCrossReferenceAdapter(value);

        if (register) {
            if (adapter != null) {
                // now, register incoming unidirectional references and
                // opposites
                final Iterator<EStructuralFeature.Setting> iter = adapter.getInverseReferences(value).iterator();
                while (iter.hasNext()) {
                    final EStructuralFeature.Setting next = iter.next();
                    final EReference ref = (EReference) next.getEStructuralFeature();
                    final EObject owner = next.getEObject();

                    if (isImportExportCapable(ref, owner)) {
                        registerReference(owner.eResource(), resource);
                    }
                }
            }
        } else {
            // deregister the outgoing references and incoming bidirectionals
            final EContentsEList.FeatureIterator<EObject> crossReferences = getOptimizedCrossReferenceIterator(value);

            while (crossReferences.hasNext()) {
                final EObject referent = crossReferences.next();

                if (referent != null) {
                    final EReference eReference = (EReference) crossReferences.feature();

                    if (isImportExportCapable(eReference, referent)) {
                        final Resource referencedResource = referent.eResource();
                        deregisterReference(resource, referencedResource);
                    }
                }
            }

            // now, deregister incoming unidirectional references and opposites
            if (adapter != null) {
                final Iterator<EStructuralFeature.Setting> iter = adapter.getInverseReferences(value).iterator();
                while (iter.hasNext()) {
                    final EStructuralFeature.Setting next = iter.next();
                    final EReference ref = (EReference) next.getEStructuralFeature();
                    final EObject owner = next.getEObject();

                    if (isImportExportCapable(ref, owner)) {
                        deregisterReference(owner.eResource(), resource);
                    }
                }
            }
        }

        // process contents
        if (adapter != null) {
            adapter.updateImportsAndExportsForContents(resource, value, register);
        }
    }

    /**
     * Updates the imports and exports map for the contents of the specified
     * eObject.
     * 
     * @param resource
     *            a resource
     * @param value
     *            the specified eObject
     * @param register
     *            boolean flag to indicate whether to register imports or
     *            unregister imports
     */
    public void updateImportsAndExportsForContents(final Resource resource, final EObject value, final boolean register) {
        // go through the children of the eObject
        final Iterator<EObject> i = resolve() ? value.eContents().iterator() : ((InternalEList<EObject>) value.eContents()).basicIterator();
        while (i.hasNext()) {
            updateImportsAndExports(resource, i.next(), register);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    @Override
    public void setTarget(final Notifier target) {
        super.setTarget(target);

        if (target instanceof EObject) {
            final EObject eObject = (EObject) target;
            final Resource resource = eObject.eResource();

            // register the outgoing references and incoming bidirectionals
            final EContentsEList.FeatureIterator<EObject> crossReferences = getOptimizedCrossReferenceIterator(eObject);

            while (crossReferences.hasNext()) {
                final EObject referent = crossReferences.next();

                if (referent != null) {
                    final EReference eReference = (EReference) crossReferences.feature();

                    if (isImportExportCapable(eReference, referent)) {
                        final Resource referencedResource = referent.eResource();
                        registerReference(resource, referencedResource);
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#unsetTarget(org.eclipse.emf.common.notify.Notifier)
     */
    @Override
    public void unsetTarget(final Notifier notifier) {
        super.unsetTarget(notifier);
        if (notifier instanceof Resource) {
            deregisterReferences((Resource) notifier);
        }
    }

    /**
     * Gets the imports of a resource.
     * 
     * @param referencer
     *            the resource to retrieve imports for
     * @return a Set of resource imports
     */
    public Set<Resource> getImports(final Resource referencer) {

        final Map<Resource, Counter> importsMap = getImportsMap(referencer);

        if (importsMap != null) {
            return Collections.unmodifiableSet(importsMap.keySet());
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Gets the exports of a resource.
     * 
     * @param referenced
     *            the resource to retrieve exports for
     * @return a Set of resource exports
     */
    public Set<Resource> getExports(final Resource referenced) {

        final Map<Resource, Counter> exportsMap = getExportsMap(referenced);

        if (exportsMap != null) {
            return Collections.unmodifiableSet(exportsMap.keySet());
        } else {
            return Collections.emptySet();
        }
    }

    /**
     * Returns the imports map of the given resource.
     * 
     * @param resource
     * @return imports map of the given resource
     */
    private Map<Resource, Counter> getImportsMap(final Resource resource) {
        return imports.get(resource);
    }

    /**
     * Returns the exports map of the given resource.
     * 
     * @param resource
     * @return exports map of the given resource
     */
    private Map<Resource, Counter> getExportsMap(final Resource resource) {
        return exports.get(resource);
    }

    /**
     * Registers a reference updating the imports and exports maps accordingly.
     * 
     * @param referencer
     *            the referencing resource
     * @param referenced
     *            the referenced resouce
     */
    private void registerReference(final Resource referencer, final Resource referenced) {

        if ((referencer != null) && (referenced != null) && (referencer != referenced)) {

            Map<Resource, Counter> importsMap = getImportsMap(referencer);

            if (importsMap == null) {
                importsMap = new HashMap<Resource, Counter>();
                imports.put(referencer, importsMap);
            }

            Counter importsCount = importsMap.get(referenced);

            if (importsCount == null) {

                importsCount = new Counter();
                importsMap.put(referenced, importsCount);

                importAdded(referencer, referenced);
            } else {
                importsCount.inc();
            }

            Map<Resource, Counter> exportsMap = getExportsMap(referenced);

            if (exportsMap == null) {
                exportsMap = new HashMap<Resource, Counter>();
                exports.put(referenced, exportsMap);
            }

            Counter exportsCount = exportsMap.get(referencer);

            if (exportsCount == null) {

                exportsCount = new Counter();
                exportsMap.put(referencer, exportsCount);

                exportAdded(referenced, referencer);
            } else {
                exportsCount.inc();
            }
        }
    }

    /**
     * Hook to be implemented by subclasses upon the establishment of a new
     * import of the <code>referenced</code> resource by the
     * <code>referencer</code>. This implementation does nothing; subclasses
     * need not call <code>super</code>.
     * 
     * @param referencer
     *            the referencing resource (doing the importing)
     * @param referenced
     *            the resource that it references
     */
    protected void importAdded(final Resource referencer, final Resource referenced) {
        // subclass hook
    }

    /**
     * Hook to be implemented by subclasses upon the elimination of an import of
     * the <code>referenced</code> resource by the <code>referencer</code>. This
     * implementation does nothing; subclasses need not call <code>super</code>.
     * 
     * @param referencer
     *            the formerly referencing resource (doing the importing)
     * @param referenced
     *            the resource that it had referenced
     */
    protected void importRemoved(final Resource referencer, final Resource referenced) {
        // subclass hook
    }

    /**
     * Hook to be implemented by subclasses upon the establishment of a new
     * export of the <code>referenced</code> resource to the
     * <code>referencer</code>. This implementation does nothing; subclasses
     * need not call <code>super</code>.
     * 
     * @param referenced
     *            the resource being referenced (doing the exporting)
     * @param referencer
     *            the referencing resource
     */
    protected void exportAdded(final Resource referenced, final Resource referencer) {
        // subclass hook
    }

    /**
     * Hook to be implemented by subclasses upon the elimination of an export of
     * the <code>referenced</code> resource to the <code>referencer</code>. This
     * implementation does nothing; subclasses need not call <code>super</code>.
     * 
     * @param referenced
     *            the resource formerly being referenced (doing the exporting)
     * @param referencer
     *            the formerly referencing resource
     */
    protected void exportRemoved(final Resource referenced, final Resource referencer) {
        // subclass hook
    }

    /**
     * Deregisters a reference updating the imports and exports maps
     * accordingly.
     * 
     * @param referencer
     *            the referencing resource
     * @param referenced
     *            the referenced resource
     */
    private void deregisterReference(final Resource referencer, final Resource referenced) {

        if ((referencer != null) && (referenced != null) && (referencer != referenced)) {

            final Map<Resource, Counter> importsMap = getImportsMap(referencer);

            if (importsMap != null) {

                final Counter importsCount = importsMap.get(referenced);

                if ((importsCount != null) && importsCount.dec()) {

                    importsMap.remove(referenced);

                    importRemoved(referencer, referenced);

                    if (importsMap.isEmpty()) {
                        imports.remove(referencer);
                    }
                }
            }

            final Map<Resource, Counter> exportsMap = getExportsMap(referenced);

            if (exportsMap != null) {

                final Counter exportsCount = exportsMap.get(referencer);

                if ((exportsCount != null) && exportsCount.dec()) {

                    exportsMap.remove(referencer);

                    exportRemoved(referenced, referencer);

                    if (exportsMap.isEmpty()) {
                        exports.remove(referenced);
                    }
                }
            }
        }
    }

    /**
     * Cleans up a resource from the imports and exports maps.
     * 
     * @param referencer
     *            the referencing resource
     */
    private void deregisterReferences(final Resource referencer) {

        final Object[] resImports = getImports(referencer).toArray();

        for (Object resImport : resImports) {

            final Resource referenced = (Resource) resImport;

            final Map<Resource, Counter> importsMap = getImportsMap(referencer);

            if (importsMap != null) {

                importsMap.remove(referenced);

                importRemoved(referencer, referenced);

                if (importsMap.isEmpty()) {
                    imports.remove(referencer);
                }
            }

            final Map<Resource, Counter> exportsMap = getExportsMap(referenced);

            if (exportsMap != null) {

                exportsMap.remove(referencer);

                exportRemoved(referenced, referencer);

                if (exportsMap.isEmpty()) {
                    exports.remove(referenced);
                }
            }
        }
    }

    /**
     * Returns a Set of EObjects that reference the given EObject. If an
     * EReference is specified, the scope of the search is limited only to that
     * EReference. To include all references specify a value of null. If an
     * EClass type is specified, the returned Set will only include those
     * referencers that match the given type. To include all types specify a
     * value of null.
     * 
     * @param referenced
     *            the referenced EObject
     * @param reference
     *            the reference to find referencers on, null for any reference
     * @param type
     *            the type of the referencers, use null for any type
     * @return a Set of referencers
     */
    public Set<EObject> getInverseReferencers(final EObject referenced, final EReference reference, final EClass type) {
        return getReferencers(getInverseReferences(referenced), reference, type);
    }

    /**
     * Like the {@link #getInverseReferencers(EObject, EReference, EClass)}
     * method, obtains referencing objects (optionally filtered by reference and
     * type), except that it additionally only considers references that are
     * {@linkplain EStructuralFeature#isChangeable() changeable} and can
     * {@linkplain EReference#isResolveProxies() reference other resources}.
     * 
     * @param referenced
     *            the referenced EObject
     * @param reference
     *            the reference to find referencers on, null for any reference
     * @param type
     *            the type of the referencers, use null for any type
     * @return a Set of referencers on potentially cross-resource references
     */
    public Set<EObject> getInverseReferencersCrossResource(final EObject referenced, final EReference reference, final EClass type) {
        return getReferencers(getInverseReferencesCrossResource(referenced), reference, type);
    }

    /**
     * Like the {@link #getInverseReferences(EObject)} method, obtains settings
     * implementing references to the specified object, except that it only
     * considers references that are
     * {@linkplain EStructuralFeature#isChangeable() changeable} and can
     * {@linkplain EReference#isResolveProxies() reference other resources}.
     * 
     * @param eObject
     *            the referenced EObject
     * 
     * @return a collection of {@link }s on potentially cross-resource references
     */
    public Collection<EStructuralFeature.Setting> getInverseReferencesCrossResource(final EObject eObject) {
        return getInverseReferencesCrossResource(eObject, !resolve());
    }

    /**
     * Returns a Set of EObjects that reference the given EObject through a uni
     * directional EReferences. If an EReference is specified, the scope of the
     * search is limited only to that EReference. To include all references
     * specify a value of null. If an EClass type is specified, the returned Set
     * will only include those referencers that match the given type. To include
     * all types specify a value of null.
     * 
     * @param referenced
     *            the referenced EObject
     * @param reference
     *            the reference to find referencers on, null for any reference
     * @param type
     *            the type of the referencers, use null for any type
     * @return a Set of referencers
     */
    public Set<EObject> getNonNavigableInverseReferencers(final EObject referenced, final EReference reference, final EClass type) {
        return getReferencers(getNonNavigableInverseReferences(referenced), reference, type);
    }

    /**
     * Extracts the EObjects from the EStructuralFeature.Setting references and
     * returns a filtered Set based on the given reference and type.
     * 
     * @param references
     *            a collection of EStructuralFeature.Setting
     * @param reference
     *            the reference to find referencers on, null for any reference
     * @param type
     *            the type of the referencers, use null for any type
     * @return a Set of referencers
     */
    private Set<EObject> getReferencers(final Collection<EStructuralFeature.Setting> references, final EReference reference, final EClass type) {
        final Set<EObject> set = new HashSet<EObject>();
        if (!references.isEmpty()) {
            final Iterator<Setting> iter = references.iterator();
            while (iter.hasNext()) {
                final Setting setting = iter.next();
                if (reference == null || reference == setting.getEStructuralFeature()) {
                    final EObject referencer = setting.getEObject();
                    if (referencer != null && (type == null || type.isInstance(referencer))) {
                        set.add(referencer);
                    }
                }
            }
        }
        return set;
    }

    /**
     * Searches the adapter list of the given Notifier for a
     * CrossReferenceAdapter. If not found, returns null.
     * 
     * @param notifier
     *            the notifier to search
     * @return the CrossReferenceAdapter if found, otherwise null
     */
    public static CrossReferenceAdapter getExistingCrossReferenceAdapter(final Notifier notifier) {
        if (notifier == null) {
            return null;
        }

        CrossReferenceAdapter crossAdapter = null;

        final List<Adapter> adapters = notifier.eAdapters();
        final int size = adapters.size();
        for (int i = 0; i < size; ++i) {
            final Adapter adapter = adapters.get(i);
            if (adapter instanceof CrossReferenceAdapter) {
                crossAdapter = (CrossReferenceAdapter) adapter;
                break;
            }
        }
        return crossAdapter;
    }

    /**
     * Obtains the cross-reference adapter for the specified resource set, if
     * necessary creating it and attaching it.
     * 
     * @param resourceSet
     *            the resource set
     * 
     * @return the resourceSet's cross-reference adapter
     */
    public static CrossReferenceAdapter getCrossReferenceAdapter(final ResourceSet resourceSet) {
        if (resourceSet == null) {
            return null;
        }

        CrossReferenceAdapter result = CrossReferenceAdapter.getExistingCrossReferenceAdapter(resourceSet);

        if (result == null) {
            result = new CrossReferenceAdapter();
            resourceSet.eAdapters().add(result);
        }

        return result;
    }

    /**
     * A mutable integer used to count number of object-level references between
     * two resources.
     * 
     * @author Christian W. Damus (cdamus)
     */
    private static final class Counter {
        private int value = 1;

        Counter() {
            super();
        }

        /**
         * Obtains my value.
         * 
         * @return my count
         */
        @SuppressWarnings("unused")
        int getValue() {
            return value;
        }

        /**
         * Increments me.
         */
        void inc() {
            value++;
        }

        /**
         * Decrements me.
         * 
         * @return <code>true</code> if I am now zero; <code>false</code>,
         *         otherwise
         */
        boolean dec() {
            return --value <= 0;
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#resolve()
     */
    @Override
    protected boolean resolve() {
        return this.resolve;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.util.ECrossReferenceAdapter#getInverseReferences(org.eclipse.emf.ecore.EObject,
     *      boolean)
     */
    @Override
    public Collection<EStructuralFeature.Setting> getInverseReferences(final EObject eObject, final boolean resolve1) {
        final Collection<EStructuralFeature.Setting> result = new ArrayList<EStructuralFeature.Setting>();

        if (resolve1) {
            resolveAll(eObject);
        }

        final EObject eContainer = eObject.eContainer();
        if (eContainer != null) {
            result.add(((InternalEObject) eContainer).eSetting(eObject.eContainmentFeature()));
        }

        final Collection<EStructuralFeature.Setting> nonNavigableInverseReferences = inverseCrossReferencer.get(eObject);
        if (nonNavigableInverseReferences != null) {
            result.addAll(nonNavigableInverseReferences);
        }
        final Iterator<EReference> i = eObject.eClass().getEAllReferences().iterator();
        while (i.hasNext()) {
            final EReference eReference = i.next();
            final EReference eOpposite = eReference.getEOpposite();
            if (eOpposite != null && !eReference.isContainer() && !eReference.isContainment() && eObject.eIsSet(eReference)) {
                if (FeatureMapUtil.isMany(eObject, eReference)) {
                    final Object collection = eObject.eGet(eReference);
                    final Iterator<InternalEObject> j = resolve() ? ((Collection) collection).iterator() : ((InternalEList) collection).basicIterator();
                    while (j.hasNext()) {
                        final InternalEObject referencingEObject = j.next();
                        result.add(referencingEObject.eSetting(eOpposite));
                    }
                } else {
                    // although the reference is set, the value could be null
                    final InternalEObject referencingEObject = (InternalEObject) eObject.eGet(eReference, resolve());
                    if (referencingEObject != null) {
                        result.add(referencingEObject.eSetting(eOpposite));
                    }
                }
            }
        }

        return result;
    }

    /**
     * Computes the references defined by the specified EClass that are
     * {@linkplain EStructuralFeature#isChangeable() changeable}.
     * 
     * @param eCls
     *            an EClass
     * @return a list of its {@link EReference}s that are changeable
     */
    private static List<EStructuralFeature> getCrossReferencesChangeableFeatures(final EClass eCls) {
        List<EStructuralFeature> features = eClassToChangeableFeatures.get(eCls);
        if (features == null) {
            features = nullList;
            final EStructuralFeature[] crossReferenceFeatures =

            ((EClassImpl.FeatureSubsetSupplier) eCls.getEAllStructuralFeatures()).crossReferences();
            if (crossReferenceFeatures != null) {
                features = new ArrayList<EStructuralFeature>(crossReferenceFeatures.length);
                for (final EStructuralFeature feature : crossReferenceFeatures) {
                    if (CrossReferenceAdapter.isMutable(feature)) {
                        features.add(feature);
                    }
                }
            }
            eClassToChangeableFeatures.put(eCls, features);
        }
        return features != nullList ? features : null;
    }

    /**
     * Queries whether a feature is mutable. A feature is considered mutable if
     * and only if it is changeable and it is either not derived or it is a
     * member of a feature map (though not itself a feature map).
     * 
     * @param feature
     *            the feature to test
     * 
     * @return <code>true</code> if the reference is mutable; <code>false</code>
     *         , otherwise
     */
    static boolean isMutable(final EStructuralFeature feature) {
        boolean result = feature.isChangeable();

        if (result) {
            if (feature.isDerived()) {
                // check whether it is a feature-map member that is not, itself,
                // a feature map
                final EStructuralFeature group = ExtendedMetaData.INSTANCE.getGroup(feature);

                result = (group != null) && !FeatureMapUtil.isFeatureMap(feature);
            }
        }

        return result;
    }

    /**
     * An iterator over the references defined by the specified EObject that are
     * {@linkplain EStructuralFeature#isChangeable() changeable}.
     * 
     * @param eObj
     *            an EObject
     * @return an iterator over its {@link EReference}s that are changeable
     */
    private EContentsEList.FeatureIterator<EObject> getOptimizedCrossReferenceIterator(final EObject eObj) {
        final List<EStructuralFeature> features = CrossReferenceAdapter.getCrossReferencesChangeableFeatures(eObj.eClass());
        if (features != null) {
            EContentsEList<EObject> list = null;
            if (features.size() > 0) {
                list = new ECrossReferenceEList<EObject>(eObj, features.toArray(new EStructuralFeature[features.size()])) {
                    // to get to the protected constructor
                };
            } else {
                list = ECrossReferenceEList.emptyCrossReferenceEList();
            }

            return (EContentsEList.FeatureIterator<EObject>) (resolve() ? list.iterator() : ((InternalEList<EObject>) list).basicIterator());
        }
        final EContentsEList<EObject> list = ECrossReferenceEList.emptyCrossReferenceEList();
        return (EContentsEList.FeatureIterator<EObject>) list.iterator();
    }

    /**
     * Like the {@link #getInverseReferences(EObject, boolean)} method, obtains
     * settings implementing references to the specified object, except that it
     * only considers references that are
     * {@linkplain EStructuralFeature#isChangeable() changeable} and can
     * {@linkplain EReference#isResolveProxies() reference other resources}.
     * 
     * @param eObject
     *            the referenced EObject
     * @param resolveProxies
     *            whether to resolve proxies or not
     * 
     * @return a collection of {@link EStructuralFeature.Setting}s on
     *         potentially cross-resource references
     */
    public Collection<EStructuralFeature.Setting> getInverseReferencesCrossResource(final EObject eObject, final boolean resolveProxies) {
        final Collection<EStructuralFeature.Setting> result = new ArrayList<EStructuralFeature.Setting>();

        if (resolveProxies) {
            resolveAll(eObject);
        }

        final EObject eContainer = eObject.eContainer();
        if (eContainer != null) {
            result.add(((InternalEObject) eContainer).eSetting(eObject.eContainmentFeature()));
        }

        final Collection<EStructuralFeature.Setting> nonNavigableInverseReferences = inverseCrossReferencer.get(eObject);
        if (nonNavigableInverseReferences != null) {
            result.addAll(nonNavigableInverseReferences);
        }
        final Iterator<EReference> i = eObject.eClass().getEAllReferences().iterator();
        while (i.hasNext()) {
            final EReference eReference = i.next();
            final EReference eOpposite = eReference.getEOpposite();

            if (eOpposite != null && isImportExportCapable(eReference, eObject) && eObject.eIsSet(eReference)) {
                if (FeatureMapUtil.isMany(eObject, eReference)) {
                    final Object collection = eObject.eGet(eReference);
                    final Iterator<InternalEObject> j = resolve() ? ((Collection<InternalEObject>) collection).iterator() : ((InternalEList<InternalEObject>) collection).basicIterator();
                    while (j.hasNext()) {
                        final InternalEObject referencingEObject = j.next();
                        result.add(referencingEObject.eSetting(eOpposite));
                    }
                } else {
                    // although the reference is set, the value could be null
                    final InternalEObject referencingEObject = (InternalEObject) eObject.eGet(eReference, resolve());
                    if (referencingEObject != null) {
                        result.add(referencingEObject.eSetting(eOpposite));
                    }
                }
            }
        }

        return result;
    }

    /**
     * Queries whether the specified reference, applied to the given owner
     * object, is capable of establishing a resource import or export by virtue
     * of being a mutable cross-resource reference.
     * <p>
     * A reference is considered to support resource imports and exports if all
     * of the following apply:
     * </p>
     * <ul>
     * <li>the reference is not a container or containment reference. Note that
     * this excludes cross-resource containment from registering as an
     * import/export dependency</li>
     * <li>the reference resolves proxies</li>
     * <li>the reference is changeable</li>
     * </ul>
     * 
     * @param reference
     *            a reference feature
     * @param owner
     *            an object under consideration that defines this reference.
     *            Subclasses may need to introspect the object or its EClass to
     *            further refine their criteria
     * 
     * @return <code>true</code> if this reference in the context of this owner
     *         should be counted for resource imports and exports; false,
     *         otherwise
     */
    protected boolean isImportExportCapable(final EReference reference, final EObject owner) {
        return !reference.isContainer() && !reference.isContainment() && reference.isResolveProxies() // can
                // be
                // cross-resource
                && reference.isChangeable(); // not computed
    }
}
