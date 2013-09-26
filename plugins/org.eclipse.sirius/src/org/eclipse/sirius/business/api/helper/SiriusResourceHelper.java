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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.base.Objects;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.common.tools.api.util.Options;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * An helper uses to manage the Sirius resource (between the
 * {@link ResourceSet} of the
 * {@link org.eclipse.sirius.business.api.componentization.SiriusRegistry
 * registry} and the {@link ResourceSet} of the editing domaine (used by session
 * and editors).
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 * @since 2.2
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
     * {@link org.eclipse.sirius.business.api.componentization.SiriusRegistry
     * registry}. Each resource containing a viewpoint in the registry is load
     * in the resourceSet a the editing domain (by using the URI) and then the
     * view is search by the name.
     * 
     * @param session
     *            The current session
     * @param registrySiriuss
     *            The viewpoints of the registry
     * @param editingDomainResourceSet
     *            The resource set of the editingDomain (use by session and
     *            editors)
     * @param semanticElement
     *            One of the semantic element of the session
     */
    public static void createViews(final Session session, final List<Viewpoint> registrySiriuss, final ResourceSet editingDomainResourceSet, final EObject semanticElement) {
        for (Viewpoint registrySirius : registrySiriuss) {
            // Get the resource for this viewpoint in the resourceSet of the
            // editingDomain
            final Resource editingDomainResource = editingDomainResourceSet.getResource(registrySirius.eResource().getURI(), true);
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                // Search the corresponding viewpoint in the resource of
                // the editingDomain resourceSet
                final Viewpoint editingDomainSirius = SiriusUtil.findSirius(editingDomainResource, registrySirius.getName());
                if (editingDomainSirius != null) {
                    session.createView(editingDomainSirius, Collections.singleton(semanticElement));
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
     * @param registrySirius
     *            The viewpoints of the registry
     * @return The corresponding viewpoint (in the resourceSet of the
     *         editingDomain)
     */
    public static Viewpoint getCorrespondingSirius(final Session session, final Viewpoint registrySirius) {
        Viewpoint editingDomainSirius = null;

        /*
         * Search the corresponding viewpoint in the resources of the
         * resourceSet of the editingDomain
         */
        final TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        for (final Iterator<Resource> iterator = domain.getResourceSet().getResources().iterator(); iterator.hasNext() && editingDomainSirius == null; /**/) {
            final Resource editingDomainResource = iterator.next();
            if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false")) && !SiriusUtil.DESCRIPTION_MODEL_EXTENSION.equals(editingDomainResource.getURI().fileExtension())) {
                continue;
            }
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                /* check URI and then viewpoint name */
                if (SiriusResourceHelper.sameURIs(registrySirius.eResource(), editingDomainResource)) {
                    editingDomainSirius = SiriusUtil.findSirius(editingDomainResource, registrySirius.getName());
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
        if (editingDomainSirius == null) {
            /*
             * The corresponding viewpoint is not in a resource of the
             * editingDomain resourceSet. This can occur if the file
             * corresponding to the odesign resource does not exist. So we try
             * to add the corresponding viewpoint in the editingDomain
             * resourceSet.
             */

            final Resource editingDomainResource = domain.getResourceSet().getResource(registrySirius.eResource().getURI(), true);
            try {
                editingDomainResource.load(Collections.EMPTY_MAP);
                /*
                 * Search the corresponding viewpoint in the resource of the
                 * editingDomain resourceSet
                 */
                editingDomainSirius = SiriusUtil.findSirius(editingDomainResource, registrySirius.getName());
            } catch (final IOException e1) {
                /* do not log anything */
            }
        }
        return editingDomainSirius;
    }

    /**
     * Check if the view for this viewpoint exists in this session.
     * 
     * @param session
     *            The current session
     * @param editingDomainSirius
     *            A viewpoint from the editingDomainResourceSet
     * @return true if a view for this viewpoint exists, false otherwise
     */
    public static boolean isViewExistForSirius(final Session session, final Viewpoint editingDomainSirius) {
        DAnalysis mainDAnalysis = (DAnalysis) session.getSessionResource().getContents().get(0);
        for (DView view : mainDAnalysis.getOwnedViews()) {
            if (view.getViewpoint() != null && view.getViewpoint().equals(editingDomainSirius)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Finds the Sirius instance with the specified logical URI inside the
     * editing domain of the session, optionally loading it if it is not
     * already.
     * 
     * @param session
     *            the session in which to look for.
     * @param uri
     *            the logical URI of the Sirius to locate.
     * @param loadOnDemand
     *            whether or not to try to load the resource providing the
     *            specified viewpoint if it is not already loaded in the
     *            session's editing domain.
     * @return a Sirius instance from the session's editing domain with the
     *         specified logical URI, if it could be found or loaded.
     */
    public static Option<Viewpoint> getCorrespondingSirius(Session session, URI uri, boolean loadOnDemand) {
        SiriusRegistry registry = (SiriusRegistry) org.eclipse.sirius.business.api.componentization.SiriusRegistry.getInstance();
        Option<URI> providerURI = registry.getProvider(uri);
        if (providerURI.some()) {
            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            Resource editingDomainResource = domain.getResourceSet().getResource(providerURI.get(), loadOnDemand);
            if (editingDomainResource != null) {
                for (Viewpoint vp : registry.getSiriusResourceHandler().collectSiriusDefinitions(editingDomainResource)) {
                    Option<URI> vpURI = new SiriusQuery(vp).getSiriusURI();
                    if (vpURI.some() && Objects.equal(vpURI.get(), uri)) {
                        return Options.newSome(vp);
                    }
                }
            }
        }
        return Options.newNone();
    }
}
