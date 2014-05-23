/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/

package org.eclipse.sirius.business.internal.session;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.AbstractSavingPolicy;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

/**
 * A saving policy implementation which use the isModified state of the
 * resources and their inter-dependencies to determine if a resource should be
 * serialized again or not.
 * 
 * <b>This policy assume the isModified flag has been updated for each resource
 * if any change happened in the contained EObjects /!\</b>
 * 
 * <br>
 * What it saves is:
 * <ul>
 * <li>all the modified resources (isModified=true)</li>
 * <li>go through all the other resources and identify those which have
 * references onto the modified ones : these should be saved too.</li>
 * <li>retrieve the underlying file status for each resource and add the
 * CONFLICTING or DELETED ones in the list of resources to save.</li>
 * </ul>
 * 
 * 
 * @author cbrun
 * @since 1.0.0
 */
public class IsModifiedSavingPolicy extends AbstractSavingPolicy {

    /**
     * Check if a resource has been logically modified, meaning some of its
     * objects have changes in some way.
     * 
     * @param resource
     *            the resource to check
     * @return <code>true</code> if the resource has changes to save,
     *         <code>false</code> otherwise
     */
    private Predicate<Resource> isModified = new Predicate<Resource>() {

        @Override
        public boolean apply(Resource resource) {
            /*
             * We assume the resource always is "tracking modification" but not
             * using the Resource-specific implementation. We rely on the fact
             * that the Sirius runtime will set the isModified flag itself when
             * a change is done on the resource.
             */
            return resource.isLoaded() && resource.isModified();
        }
    };

    /**
     * Check if the resource underlying file is in a DELETED or CONFLICTING
     * state.
     * 
     * @param resource
     *            the resource to check
     * @return <code>true</code> if the resource has changes to save,
     *         <code>false</code> otherwise
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
     * Computes the set of resources to save. This is a safe approximation of
     * the exact sub-set of resource in the scope whose serialization has
     * changed. Saving all the returned resources will produce the same result
     * as saving all the resources in the scope, but in the general case will
     * save much less resources (and thus be faster).
     * <p>
     * It may save more resources than strictly needed. For example if resource
     * A (not modified) contains references to elements in resource B
     * (modified), but the only references are to elements in B whose URI will
     * not change. In such a case we will save A anyway. More precise analyses
     * would be possible but cost-prohibitive.
     * <p>
     * {@inheritDoc}
     */
    @Override
    public Collection<Resource> computeResourcesToSave(Set<Resource> scope, Map<?, ?> options, IProgressMonitor monitor) {
        // We must save a resource if...
        Set<Resource> resourcesToSave = Sets.newLinkedHashSet();
        // it has been explicitly modified (or marked as such)...
        Iterables.addAll(resourcesToSave, Iterables.filter(scope, isModified));
        // or it references a resource which has been modified (in which case
        // the URIs to the referenced elements in these resource *may* have
        // changed)...
        Iterables.addAll(resourcesToSave, Iterables.filter(scope, new ResourceHasReferenceTo(Predicates.and(Predicates.in(scope), isModified))));
        // or the underlying file is out of date and must be recreated/updated
        // to match the version in memory.
        Iterables.addAll(resourcesToSave, Iterables.filter(scope, underlyingFileIsDeletedOrConflicting));
        return ImmutableSet.copyOf(resourcesToSave);
    }

    private static class ResourceHasReferenceTo implements Predicate<Resource> {
        private final Predicate<Resource> modifiedResources;

        public ResourceHasReferenceTo(Predicate<Resource> logicallyModifieds) {
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

        public EObjectHasReferencesTo(Predicate<Resource> logicallyModifieds) {
            this.modifiedResources = logicallyModifieds;
        }

        @Override
        public boolean apply(EObject source) {
            /*
             * We could process the references in an order which gives us the
             * highest chance to hit a success sooner and avoid, for instance,
             * computing derived references unless strictly necessary.
             */
            for (EReference ref : source.eClass().getEAllReferences()) {
                if (!ref.isTransient()) {
                    for (EObject target : getReferencedEObjects(source, ref)) {
                        if (!target.eIsProxy() && modifiedResources.apply(target.eResource())) {
                            return true;
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
