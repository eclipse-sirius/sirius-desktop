/*******************************************************************************
 * Copyright (c) 2008, 2015 THALES GLOBAL SERVICES.
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
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.util.DescriptionResourceImpl;

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
     * Create a new empty {@link Session} instance from the sessionModelURI.
     * 
     * @param sessionResourceURI
     *            the location URI of the new {@link Session} to create
     * @return the newly created {@link Session}
     * 
     * @throws CoreException
     *             exception when session resource creation failed
     */
    public Session createSession(URI sessionResourceURI) throws CoreException {
        return createSession(sessionResourceURI, new NullProgressMonitor());
    }

    @Override
    public Session createSession(final URI sessionResourceURI, IProgressMonitor monitor) throws CoreException {
        ResourceSet set = ResourceSetFactory.createFactory().createResourceSet(sessionResourceURI);
        final TransactionalEditingDomain transactionalEditingDomain = EditingDomainFactoryService.INSTANCE.getEditingDomainFactory().createEditingDomain(set);

        // Configure the resource set, its is done here and not before the
        // editing domain creation which could provide its own resource set.
        set = transactionalEditingDomain.getResourceSet();
        if (Movida.isEnabled()) {
            set.setURIConverter(new ViewpointURIConverter((ViewpointRegistry) org.eclipse.sirius.business.api.componentization.ViewpointRegistry.getInstance()));
        }
        if (set instanceof ResourceSetImpl) {
            ResourceSetImpl resourceSetImpl = (ResourceSetImpl) set;
            new ResourceSetImpl.MappedResourceLocator(resourceSetImpl);
        }

        set.getLoadOptions().put(DescriptionResourceImpl.OPTION_USE_URI_FRAGMENT_AS_ID, true);

        boolean alreadyExistingResource = set.getURIConverter().exists(sessionResourceURI, null);
        Session session = null;
        if (alreadyExistingResource) {
            session = loadSessionModelResource(sessionResourceURI, transactionalEditingDomain, monitor);
        } else {
            session = createSessionResource(sessionResourceURI, transactionalEditingDomain, monitor);
        }
        return session;
    }

    private Session loadSessionModelResource(URI sessionResourceURI, TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        // Make ResourceSet aware of resource loading with progress monitor
        ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));

        Session session = null;
        try {
            monitor.beginTask(Messages.SessionFactoryImpl_sessionLoadingMsg, 4);
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
            throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, Messages.SessionFactoryImpl_loadingError, e));
        } finally {
            monitor.done();
            ResourceSetUtil.resetProgressMonitor(resourceSet);
        }
        return session;
    }

    private Session createSessionResource(final URI sessionResourceURI, final TransactionalEditingDomain transactionalEditingDomain, IProgressMonitor monitor) throws CoreException {
        Session session = null;
        try {
            monitor.beginTask(Messages.SessionFactoryImpl_sessionCreation, 2);
            Resource sessionModelResource = new ResourceSetImpl().createResource(sessionResourceURI);
            DAnalysis analysis = ViewpointFactory.eINSTANCE.createDAnalysis();
            sessionModelResource.getContents().add(analysis);
            try {
                sessionModelResource.save(Collections.emptyMap());
            } catch (IOException e) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, Messages.SessionFactoryImpl_creationFailedErrorMsg, e));
            }
            monitor.worked(1);
            // Now load it from the TED
            sessionModelResource = transactionalEditingDomain.getResourceSet().getResource(sessionResourceURI, true);
            if (sessionModelResource.getContents().isEmpty()) {
                throw new CoreException(new Status(IStatus.ERROR, SiriusPlugin.ID, Messages.SessionFactoryImpl_EmptyContentErrorMsg));
            }
            analysis = (DAnalysis) sessionModelResource.getContents().get(0);
            session = new DAnalysisSessionImpl(analysis);
            monitor.worked(1);
        } finally {
            monitor.done();
        }
        return session;
    }

    @Override
    public Session createDefaultSession(URI sessionResourceURI) throws CoreException {
        return createSession(sessionResourceURI, new NullProgressMonitor());
    }
}
