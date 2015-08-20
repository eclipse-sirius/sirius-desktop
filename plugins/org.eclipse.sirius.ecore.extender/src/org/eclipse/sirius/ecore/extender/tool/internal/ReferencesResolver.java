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
package org.eclipse.sirius.ecore.extender.tool.internal;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.internal.Messages;
import org.eclipse.sirius.ext.emf.EReferencePredicate;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * Class responsible for resolving some references.
 * 
 * @author cbrun
 * 
 */
public class ReferencesResolver {

    private Predicate<EReference> filter;

    private ResourceSet set;

    /**
     * Create a new references resolver.
     * 
     * @param filter
     *            filter telling which references to resolve.
     * @param set
     *            resource set to resolve.
     */
    public ReferencesResolver(ResourceSet set, final EReferencePredicate filter) {
        this.filter = new Predicate<EReference>() {
            @Override
            public boolean apply(EReference input) {
                return filter.apply(input);
            }
        };
        this.set = set;
    }

    /**
     * Resolve the references.
     * 
     * @param monitor
     *            monitor to track progress.
     */
    public void resolve(IProgressMonitor monitor) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(set);
        final List<Resource> cachedIdsResources = ReferencesResolver.prepareResolveAll(set, authority);
        monitor.beginTask(Messages.ReferencesResolver_resolveNonContainedReferencesTask, set.getResources().size());
        for (Resource res : Lists.newArrayList(set.getResources())) {
            doResolveAll(res);
            monitor.worked(1);
        }
        monitor.done();
        ReferencesResolver.unprepareResolveAll(authority, cachedIdsResources);
    }

    /**
     * Visits all proxies in the resource and tries to resolve them.
     * 
     * @param resource
     *            the objects to visit.
     * @param filter
     *            the filter telling which references to resolve.
     */
    private void doResolveAll(Resource resource) {
        Iterator<EObject> i = resource.getAllContents();
        while (i.hasNext()) {
            EObject eObject = i.next();
            resolveCrossReferences(eObject);
        }
    }

    private void resolveCrossReferences(EObject eObject) {
        Iterator<EReference> it = Iterators.filter(eObject.eClass().getEAllReferences().iterator(), filter);
        while (it.hasNext()) {
            eObject.eGet(it.next());
        }
    }

    /**
     * Prepare the whole resolving by setting accelerating ID resolving maps.
     * 
     * @param set
     *            the resource set to resolve.
     * @param authority
     *            the permission authority.
     * @return the list of resources with a cached ID map.
     */
    public static List<Resource> prepareResolveAll(final ResourceSet set, final IPermissionAuthority authority) {
        if (authority != null) {
            authority.setListening(false);
        }
        final List<Resource> cachedIdsResources = new LinkedList<Resource>();
        Iterator<Resource> iterResources = set.getResources().iterator();
        while (iterResources.hasNext()) {
            final Resource resource = iterResources.next();
            if (resource instanceof ResourceImpl && ((ResourceImpl) resource).getIntrinsicIDToEObjectMap() == null) {
                ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
                cachedIdsResources.add(resource);
            }
        }
        return cachedIdsResources;
    }

    /**
     * Reinit resources changed having a chached ID map.
     * 
     * @param authority
     *            the permission authority.
     * @param cachedIdsResources
     *            the cached ID resources.
     */
    public static void unprepareResolveAll(final IPermissionAuthority authority, final List<Resource> cachedIdsResources) {
        Iterator<Resource> iterResourcesBis = cachedIdsResources.iterator();
        while (iterResourcesBis.hasNext()) {
            final Resource resource = iterResourcesBis.next();
            if (resource instanceof ResourceImpl && ((ResourceImpl) resource).getIntrinsicIDToEObjectMap() != null) {
                ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(null);
            }
        }
        if (authority != null) {
            authority.setListening(true);
        }
    }

}
