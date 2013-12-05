/*******************************************************************************
 * Copyright (c) 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.factory.SessionFactory;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointRegistry;
import org.eclipse.sirius.business.internal.movida.registry.ViewpointURIConverter;
import org.eclipse.sirius.business.internal.session.danalysis.DAnalysisSessionImpl;
import org.eclipse.sirius.common.tools.api.editing.EditingDomainFactoryService;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetFactory;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;

/**
 * Default implementation of a session factory.
 * 
 * @author mchauvin
 */
public final class SessionFactoryImpl implements SessionFactory {

    /** Avoid instantiation. */
    private SessionFactoryImpl() {
    }

    /**
     * Default initialization of a {@link SessionFactoryImpl}.
     * 
     * @return a new instance of {@link SessionFactory}.
     */
    public static SessionFactory init() {
        SessionFactory sessionFactory = SessionFactoryService.INSTANCE.getSessionFactory();
        if (sessionFactory == null) {
            sessionFactory = new SessionFactoryImpl();
        }
        return sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    public Session createSession(URI sessionResourceURI) throws CoreException {
        return createSession(sessionResourceURI, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public Session createSession(final URI sessionResourceURI, IProgressMonitor monitor) throws CoreException {
        final ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(sessionResourceURI);

        final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(set);
        if (Movida.isEnabled()) {
            transactionalEditingDomain.getResourceSet()
                    .setURIConverter(new ViewpointURIConverter((ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance()));
        }
        boolean alreadyExistingResource = exists(sessionResourceURI, transactionalEditingDomain.getResourceSet());
        Session session = null;
        if (alreadyExistingResource) {
            session = loadSessionModelResource(sessionResourceURI, transactionalEditingDomain, monitor);
        } else {
            session = createSessionResource(sessionResourceURI, transactionalEditingDomain, monitor);
        }
        return session;
    }

    /**
     * Used to check if a Resource exists at this URI with EMF 2.3 since from
     * EMF 2.4 URIConverter.exists(URI) can be used.
     * 
     * @param sessionResourceURI
     *            the URI of the Resource to which checks existence
     * @param resourceSet
     * @return true if a Resource exists at this URI
     */
    private boolean exists(URI sessionResourceURI, ResourceSet resourceSet) {
        boolean exists = false;
        InputStream inputStream = null;
        try {
            inputStream = resourceSet.getURIConverter().createInputStream(sessionResourceURI);
            if (inputStream != null) {
                exists = true;
            }
        } catch (IOException e) {
            // Do nothing, we consider that the resource does not exist
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // Do nothing, we consider that the resource does not exist
                }
            }
        }
        return exists;
    }

    private Session loadSessionModelResource(URI sessionResourceURI, TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        // Make ResourceSet aware of resource loading with progress monitor
        ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));

        Session session = null;
        try {
            monitor.beginTask("Session loading", 4);
            // Get resource
            final Resource sessionModelResource = resourceSet.getResource(sessionResourceURI, true);
            if (sessionModelResource != null) {
                DAnalysis analysis = null;
                if (!sessionModelResource.getContents().isEmpty() && (sessionModelResource.getContents().get(0) instanceof DAnalysis)) {
                    analysis = (DAnalysis) sessionModelResource.getContents().get(0);
                    session = new DAnalysisSessionImpl(analysis);
                    monitor.worked(2);
                } else {
                    session = createSessionResource(sessionResourceURI, transactionalEditingDomain, new SubProgressMonitor(monitor, 2));
                }
            }
        } catch (WrappedException e) {
            throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "Error while loading representations file", e));
        } finally {
            monitor.done();
            ResourceSetUtil.resetProgressMonitor(resourceSet);
        }
        return session;
    }

    private Session createSessionResource(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
        Session session = null;
        try {
            monitor.beginTask("Session creation", 2);
            Resource sessionModelResource = new ResourceSetImpl().createResource(sessionResourceURI);
            DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
            sessionModelResource.getContents().add(analysis);
            try {
                sessionModelResource.save(Collections.emptyMap());
            } catch (IOException e) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed", e));
            }
            monitor.worked(1);
            // Now load it from the TED
            sessionModelResource = transactionalEditingDomain.getResourceSet().getResource(sessionResourceURI, true);
            if (sessionModelResource.getContents().isEmpty()) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, "session creation failed: the resource content is empty."));
            }
            analysis = (DAnalysis) sessionModelResource.getContents().get(0);
            session = new DAnalysisSessionImpl(analysis);
            monitor.worked(1);
        } finally {
            monitor.done();
        }
        return session;
    }

    /**
     * {@inheritDoc}
     */
    public Session createDefaultSession(URI sessionResourceURI) throws CoreException {
        return createSession(sessionResourceURI, new NullProgressMonitor());
    }
}
