/*******************************************************************************
 * Copyright (c) 2013 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.Collection;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.base.Function;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.business.api.componentization.SiriusRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.SiriusQuery;
import org.eclipse.sirius.business.internal.movida.VSMResolver;
import org.eclipse.sirius.business.internal.movida.SiriusResourceOperations;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.description.Viewpoint;

/**
 * Helper class to extract Movida-related code from {@link DAnalysisSessionImpl}
 * to reduce its code size.
 * 
 * @author pcdavid
 */
final class MovidaSupport {
    private DAnalysisSessionImpl session;
    private Set<URI> vsmResources;
    
    MovidaSupport(DAnalysisSessionImpl session) {
        this.session = session;
    }
    
    void updatePhysicalVSMResourceURIs(Collection<Viewpoint> selectedSiriuss) {
        Set<URI> selected = Sets.newHashSet(Iterables.filter(Iterables.transform(selectedSiriuss, new Function<Viewpoint, URI>() {
            public URI apply(Viewpoint from) {
                return new SiriusQuery(from).getSiriusURI().get();
            }
        }), Predicates.notNull()));
        vsmResources = new VSMResolver((org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry) SiriusRegistry.getInstance()).resolve(selected);
    }

    void registryChanged(final org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry registry, Set<URI> removed, Set<URI> added, Set<URI> changed) {
        TransactionalEditingDomain transactionalEditingDomain = session.getTransactionalEditingDomain();
        Set<URI> selected = Sets.newHashSet(Iterables.transform(session.getSelectedSiriuss(false), new Function<Viewpoint, URI>() {
            public URI apply(Viewpoint from) {
                return new SiriusQuery(from).getSiriusURI().get();
            }
        }));
        final SetView<URI> unavailable = Sets.intersection(selected, removed);
        if (!unavailable.isEmpty()) {
            deselectMissingSiriuss(unavailable);
        }
        Set<URI> resourcesLoaded = vsmResources;
        Set<URI> resourcesRequired = new VSMResolver(registry).resolve(selected);
        Set<URI> resourcesChanged = Sets.newHashSet(Iterables.transform(Sets.intersection(changed, selected), new Function<URI, URI>() {
            public URI apply(URI logicalURI) {
                return registry.getProvider(logicalURI).get();
            };
        }));
        for (URI uri : Sets.difference(resourcesLoaded, resourcesRequired)) {
            // The resource was loaded but is not required anymore.
            // Unload & remove.
            Resource vsm = transactionalEditingDomain.getResourceSet().getResource(uri, false);
            new SiriusResourceOperations(vsm).unloadAndResetProxyURIs();
            transactionalEditingDomain.getResourceSet().getResources().remove(vsm);
        }
        for (URI uri : resourcesChanged) {
            // The resource is loaded and still required, but its content
            // has changed: unload it.
            Resource vsm = transactionalEditingDomain.getResourceSet().getResource(uri, false);
            if (vsm != null) {
                new SiriusResourceOperations(vsm).unloadAndResetProxyURIs();
            }
        }
        for (URI uri : Sets.difference(resourcesRequired, resourcesLoaded)) {
            transactionalEditingDomain.getResourceSet().getResource(uri, true);
        }
        for (DAnalysis analysis : session.allAnalyses()) {
            EcoreUtil.resolveAll(analysis);
        }
        vsmResources = resourcesRequired;
        transactionalEditingDomain.getCommandStack().execute(new RecordingCommand(transactionalEditingDomain) {
            @Override
            protected void doExecute() {
                for (DView view : session.getSelectedViews()) {
                    for (DRepresentation representation : view.getAllRepresentations()) {
                        DialectManager.INSTANCE.refreshEffectiveRepresentationDescription(representation, new NullProgressMonitor());
                    }
                }
            }
        });
    }

    void deselectMissingSiriuss(final SetView<URI> unavailable) {
        TransactionalEditingDomain ted = session.getTransactionalEditingDomain();
        ted.getCommandStack().execute(new RecordingCommand(ted) {
            @Override
            protected void doExecute() {
                for (final Viewpoint viewpoint : session.getSelectedSiriuss(false)) {
                    Option<URI> uri = new SiriusQuery(viewpoint).getSiriusURI();
                    if (uri.some() && unavailable.contains(uri.get())) {
                        session.unselectSirius(viewpoint);
                        DialectManager.INSTANCE.updateRepresentationsExtendedBy(session, viewpoint, false);
                    }
                }
            }
        });
    }

}
