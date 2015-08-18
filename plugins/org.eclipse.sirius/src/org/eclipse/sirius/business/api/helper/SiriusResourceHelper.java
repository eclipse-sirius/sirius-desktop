/*******************************************************************************
 * Copyright (c) 2008, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.api.helper;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

import com.google.common.base.Objects;

/**
 * An helper uses to manage the Sirius resource (between the {@link ResourceSet}
 * of the
 * {@link org.eclipse.sirius.business.api.componentization.ViewpointRegistry
 * registry} and the {@link ResourceSet} of the editing domaine (used by session
 * and editors).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @since 0.9.0
 */
public final class SiriusResourceHelper {

    /**
     * Avoid instantiation.
     */
    private SiriusResourceHelper() {
    }

    /**
     * Check that two resources have the same {@link URI}
     * 
     * @param referenceResource
     *            the reference resource
     * @param ressource
     *            the resource to compare
     * @return <code>false</code> if one of the resource is <code>null</code>
     *         and if the uris are not equals, <code>true</code> otherwise
     */
    private static boolean sameURIs(final Resource referenceResource, final Resource ressource) {
        if (referenceResource != null && ressource != null) {
            final URI referenceURI = referenceResource.getURI();
            if (referenceURI != null) {
                return referenceURI.equals(ressource.getURI());
            }
        }
        return false;
    }

    /**
     * Create the views corresponding to the viewpoints of the
     * {@link org.eclipse.sirius.business.api.componentization.ViewpointRegistry
     * registry}. Each resource containing a viewpoint in the registry is load
     * in the resourceSet a the editing domain (by using the URI) and then the
     * view is search by the name.
     * 
     * @param session
     *            The current session
     * @param registryViewpoints
     *            The viewpoints of the registry
     * @param editingDomainResourceSet
     *            The resource set of the editingDomain (use by session and
     *            editors)
     * @param semanticElement
     *            One of the semantic element of the session
     */
    public static void createViews(final Session session, final List<Viewpoint> registryViewpoints, final ResourceSet editingDomainResourceSet, final EObject semanticElement) {
        for (Viewpoint registryViewpoint : registryViewpoints) {
            // Get the resource for this viewpoint in the resourceSet of the
            // editingDomain
            final Resource editingDomainResource = editingDomainResourceSet.getResource(registryViewpoint.eResource().getURI(), true);
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                // Search the corresponding viewpoint in the resource of
                // the editingDomain resourceSet
                final Viewpoint editingDomainViewpoint = SiriusUtil.findViewpoint(editingDomainResource, registryViewpoint.getName());
                if (editingDomainViewpoint != null) {
                    session.createView(editingDomainViewpoint, Collections.singleton(semanticElement), new NullProgressMonitor());
                }
            } catch (final IOException e1) {
                /* do not log anything */
            }
        }
    }

    /**
     * Search the corresponding viewpoint in the session (in the resourceSet of
     * editingDomain). If it was not found, we add the resource (with the URI of
     * the resource in the registry) in the resourceSet of editingDomain.
     * Incidentally, if we detect Resource which can not be load (case of
     * odesign being renamed), we remove these Resource of the
     * editingDomainResourceSet.
     * 
     * @param session
     *            The current session
     * @param registryViewpoint
     *            The viewpoints of the registry
     * @return The corresponding viewpoint (in the resourceSet of the
     *         editingDomain)
     */
    public static Viewpoint getCorrespondingViewpoint(final Session session, final Viewpoint registryViewpoint) {
        Viewpoint editingDomainViewpoint = null;
        Resource registryViewpointResource = null;

        /*
         * Search the corresponding viewpoint in the resources of the
         * resourceSet of the editingDomain
         */
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        for (final Iterator<Resource> iterator = domain.getResourceSet().getResources().iterator(); iterator.hasNext() && editingDomainViewpoint == null; /**/) {
            final Resource editingDomainResource = iterator.next();
            if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false")) //$NON-NLS-1$ //$NON-NLS-2$
                    && !SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(editingDomainResource.getURI().fileExtension())) {
                continue;
            }
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                /* check URI and then viewpoint name */
                registryViewpointResource = registryViewpoint.eResource();
                if (SiriusResourceHelper.sameURIs(registryViewpointResource, editingDomainResource)) {
                    editingDomainViewpoint = SiriusUtil.findViewpoint(editingDomainResource, registryViewpoint.getName());
                }
            } catch (final Resource.IOWrappedException resException) {
                /*
                 * There is a problem with the loading of this resource so we
                 * remove it from the resourceSet (case of the odesign rename)
                 */
                iterator.remove();
            } catch (final IOException e1) {
                /* do not log anything */
            }
        }
        if (editingDomainViewpoint == null) {
            /*
             * The corresponding viewpoint is not in a resource of the
             * editingDomain resourceSet. This can occur if the file
             * corresponding to the odesign resource does not exist. So we try
             * to add the corresponding viewpoint in the editingDomain
             * resourceSet.
             */
            if (registryViewpointResource == null) {
                registryViewpointResource = registryViewpoint.eResource();
            }
            final Resource editingDomainResource = domain.getResourceSet().getResource(registryViewpointResource.getURI(), true);
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                /*
                 * Search the corresponding viewpoint in the resource of the
                 * editingDomain resourceSet
                 */
                editingDomainViewpoint = SiriusUtil.findViewpoint(editingDomainResource, registryViewpoint.getName());
            } catch (final IOException e1) {
                /* do not log anything */
            }
        }
        return editingDomainViewpoint;
    }

    /**
     * Check if the view for this viewpoint exists in this session.
     * 
     * @param session
     *            The current session
     * @param editingDomainViewpoint
     *            A viewpoint from the editingDomainResourceSet
     * @return true if a view for this viewpoint exists, false otherwise
     */
    public static boolean isViewExistForSirius(final Session session, final Viewpoint editingDomainViewpoint) {
        DAnalysis mainDAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        for (DView view : mainDAnalysis.getOwnedViews()) {
            if (view.getViewpoint() != null && view.getViewpoint().equals(editingDomainViewpoint)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the viewpoint instance with the specified logical URI inside the
     * editing domain of the session, optionally loading it if it is not
     * already.
     * 
     * @param session
     *            the session in which to look for.
     * @param uri
     *            the logical URI of the viewpoint to locate.
     * @param loadOnDemand
     *            whether or not to try to load the resource providing the
     *            specified viewpoint if it is not already loaded in the
     *            session's editing domain.
     * @return a Sirius instance from the session's editing domain with the
     *         specified logical URI, if it could be found or loaded.
     */
    public static Option<Viewpoint> getCorrespondingViewpoint(Session session, URI uri, boolean loadOnDemand) {
        ViewpointRegistry registry = (ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance();
        Option<URI> providerURI = registry.getProvider(uri);
        if (providerURI.some()) {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            Resource editingDomainResource = domain.getResourceSet().getResource(providerURI.get(), loadOnDemand);
            if (editingDomainResource != null) {
                for (Viewpoint vp : registry.getSiriusResourceHandler().collectViewpointDefinitions(editingDomainResource)) {
                    Option<URI> vpURI = new ViewpointQuery(vp).getViewpointURI();
                    if (vpURI.some() && Objects.equal(vpURI.get(), uri)) {
                        return Options.newSome(vp);
                    }
                }
            }
        }
        return Options.newNone();
    }
}
