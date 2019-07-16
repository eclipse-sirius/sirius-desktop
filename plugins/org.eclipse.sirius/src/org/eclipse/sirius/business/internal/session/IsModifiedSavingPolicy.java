/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.session;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.session.AbstractSavingPolicy;
import org.eclipse.sirius.common.tools.api.resource.ResourceMigrationMarker;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * A saving policy implementation which use the isModified state of the resources and their inter-dependencies to
 * determine if a resource should be serialized again or not.
 * 
 * <b>This policy assume the isModified flag has been updated for each resource if any change happened in the contained
 * EObjects /!\</b>
 * 
 * <br>
 * What it saves is:
 * <ul>
 * <li>all the modified resources (isModified=true)</li>
 * <li>go through all the other resources and identify those which have references onto the modified ones : these should
 * be saved too.</li>
 * <li>retrieve the underlying file status for each resource and add the CONFLICTING or DELETED ones in the list of
 * resources to save.</li>
 * </ul>
 * 
 * 
 * @author cbrun
 * @since 1.0.0
 */
public class IsModifiedSavingPolicy extends AbstractSavingPolicy {

    /**
     * Check if a resource has been logically modified, meaning some of its objects have changes in some way.
     * 
     * @param resource
     *            the resource to check
     * @return <code>true</code> if the resource has changes to save, <code>false</code> otherwise
     */
    private Predicate<Resource> isModified = new Predicate<Resource>() {

        @Override
        public boolean apply(Resource resource) {
            /*
             * We assume the resource always is "tracking modification" but not using the Resource-specific
             * implementation. We rely on the fact that the Sirius runtime will set the isModified flag itself when a
             * change is done on the resource.
             */

            return resource.isModified() || ResourceMigrationMarker.hasMigrationMarker(resource);
        }
    };

    /**
     * Check if the resource underlying file is in a DELETED or CONFLICTING state.
     * 
     * @param resource
     *            the resource to check
     * @return <code>true</code> if the resource has changes to save, <code>false</code> otherwise
     */
    private Predicate<Resource> underlyingFileIsDeletedOrConflicting = new Predicate<Resource>() {

        @Override
        public boolean apply(Resource resource) {
            ResourceStatus resourceStatus = ResourceSetSync.getStatus(resource);
            return resourceStatus == ResourceStatus.DELETED || resourceStatus == ResourceStatus.CONFLICTING_DELETED;
        }
    };

    /**
     * Construct a new instance.
     * 
     * @param domain
     *            the editing domain to use
     */
    public IsModifiedSavingPolicy(TransactionalEditingDomain domain) {
        super(domain);
    }

    /**
     * Computes the set of resources to save. This is a safe approximation of the exact sub-set of resource in the scope
     * whose serialization has changed. Saving all the returned resources will produce the same result as saving all the
     * resources in the scope, but in the general case will save much less resources (and thus be faster).
     * <p>
     * It may save more resources than strictly needed. For example if resource A (not modified) contains references to
     * elements in resource B (modified), but the only references are to elements in B whose URI will not change. In
     * such a case we will save A anyway. More precise analyses would be possible but cost-prohibitive.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Collection<Resource> computeResourcesToSave(Set<Resource> scope, Map<?, ?> options, IProgressMonitor monitor) {

        final Map<Object, Object> mergedOptions = new HashMap<Object, Object>(getDefaultSaveOptions());
        if (options != null) {
            mergedOptions.putAll(options);
        }

        Set<Resource> saveable = Sets.newLinkedHashSet(Iterables.filter(scope, new Predicate<Resource>() {

            @Override
            public boolean apply(Resource resourcetoSave) {
                return !ResourceSetSync.isReadOnly(resourcetoSave) && !SiriusUtil.isModelerDescriptionFile(resourcetoSave);
            }

        }));

        /* We must save a resource if is has been logically modified ... */

        Set<Resource> logicallyModified = Sets.newLinkedHashSet(Iterables.filter(saveable, isModified));

        /*
         * ... or it references a resource which has been modified (in which case the URIs to the referenced elements in
         * these resource *may* havechanged)...
         */
        Set<Resource> dependOnLogicallyModified = new LinkedHashSet<>();
        if (logicallyModified.size() > 0) {
            Iterables.addAll(dependOnLogicallyModified, Iterables.filter(Sets.difference(saveable, logicallyModified), new ResourceHasReferenceTo(isModified)));
        }

        Predicate<Resource> exists = new Predicate<Resource>() {
            private URIConverter defaultConverter;

            @Override
            public boolean apply(Resource resourcetoSave) {
                ResourceSet rs = resourcetoSave.getResourceSet();
                URIConverter uriConverter = rs == null ? getDefaultURIConverter() : rs.getURIConverter();
                return uriConverter.exists(resourcetoSave.getURI(), mergedOptions);
            }

            private URIConverter getDefaultURIConverter() {
                if (defaultConverter == null) {
                    defaultConverter = new ResourceSetImpl().getURIConverter();
                }
                return defaultConverter;
            }
        };
        Set<Resource> underlyingFileDoesNotExist = Sets.newLinkedHashSet(Iterables.filter(saveable, Predicates.not(exists)));
        Set<Resource> isConflictingOrDeleted = Sets.newLinkedHashSet(Iterables.filter(saveable, underlyingFileIsDeletedOrConflicting));
        /*
         * or the underlying file is out of date and must be recreated/updated to match the version in memory.
         */
        Set<Resource> toSave = new LinkedHashSet<>();
        for (Resource resource : Sets.union(logicallyModified, dependOnLogicallyModified)) {
            if (hasDifferentSerialization(resource, mergedOptions)) {
                toSave.add(resource);
            } else {
                ResourceMigrationMarker.clearMigrationMarker(resource);
            }
        }

        Iterables.addAll(toSave, Sets.union(underlyingFileDoesNotExist, isConflictingOrDeleted));
        /*
         * if we have something to save which has no different serialization then something is fishy...
         */
        return toSave;
    }

    private static class ResourceHasReferenceTo implements Predicate<Resource> {
        private final Predicate<Resource> modifiedResources;

        ResourceHasReferenceTo(Predicate<Resource> logicallyModifieds) {
            this.modifiedResources = logicallyModifieds;
        }

        @Override
        public boolean apply(Resource resource) {
            Predicate<EObject> hasOuterRef = new EObjectHasReferencesTo(modifiedResources);
            return Iterators.any(EcoreUtil.<EObject> getAllProperContents(resource, false), hasOuterRef);
        }
    }

    private static class EObjectHasReferencesTo implements Predicate<EObject> {
        private final Predicate<Resource> modifiedResources;

        EObjectHasReferencesTo(Predicate<Resource> logicallyModifieds) {
            this.modifiedResources = logicallyModifieds;
        }

        @Override
        public boolean apply(EObject source) {
            if (!source.eIsProxy()) {
                /*
                 * We could process the references in an order which gives us the highest chance to hit a success sooner
                 * and avoid, for instance, computing derived references unless strictly necessary.
                 */
                for (EReference ref : source.eClass().getEAllReferences()) {
                    /*
                     * we should not go on containment references as we are already in a getProperContent iteration. We
                     * are only interested in references which will impact the serialization (hence ignoring
                     * isTransient).
                     */
                    if (!ref.isTransient() && !ref.isContainment()) {
                        for (EObject target : getReferencedEObjects(source, ref)) {
                            final Resource targetResource = target.eResource();
                            if (!target.eIsProxy() && targetResource != null && modifiedResources.apply(targetResource)) {
                                return true;
                            }
                        }
                    }
                }
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        protected Collection<EObject> getReferencedEObjects(EObject source, EReference ref) {
            Object val = source.eGet(ref, false);
            Collection<EObject> values = Collections.emptySet();
            if (ref.isMany() && val instanceof Collection<?>) {
                values = (Collection<EObject>) val;
            } else if (val instanceof EObject) {
                values = Collections.singleton((EObject) val);
            }
            return values;
        }
    }

}
