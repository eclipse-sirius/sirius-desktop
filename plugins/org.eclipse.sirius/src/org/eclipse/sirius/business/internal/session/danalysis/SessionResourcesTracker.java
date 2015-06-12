/*******************************************************************************
 * Copyright (c) 2015 Obeo.
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
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.emf.EReferencePredicate;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * This class is responsible for keeping track of which resources in the
 * ResourceSet correspond to what kind of model (semantic,
 * session/analysis/representation, description...).
 * 
 * @author pcdavid
 */
class SessionResourcesTracker {
    /**
     * The session for which we keep track of resources.
     */
    private DAnalysisSessionImpl session;

    /** The semantic resources collection. */
    private Collection<Resource> semanticResources;

    /** The semantic resources collection updater. */
    private SemanticResourcesUpdater semanticResourcesUpdater;

    private ControlledResourcesDetector controlledResourcesDetector;

    private DAnalysisRefresher dAnalysisRefresher;

    /**
     * Creates a new tracker for the specified session.
     * 
     * @param session
     *            the session to track.
     */
    SessionResourcesTracker(DAnalysisSessionImpl session) {
        this.session = Preconditions.checkNotNull(session);
        this.controlledResourcesDetector = new ControlledResourcesDetector(session);
        dAnalysisRefresher = new DAnalysisRefresher(session);
    }

    void initialize(IProgressMonitor monitor) {
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        Collection<DAnalysis> analyses = session.allAnalyses();
        // First resolve all VSM resources used for Sirius to ignore VSM
        // resources and VSM linked resources (as viewpoint:/environment
        // resource) as new semantic element
        resolveAllVSMResources(analyses);
        // Resolve all semantic resources from {@link DAnalysis.getModels()}
        resolveAllSemanticResourcesFromModels(analyses);
        // Then resolve all resources (to automatically add new semantic
        // resources)
        List<Resource> resourcesBeforeLoadOfSession = Lists.newArrayList(session.getTransactionalEditingDomain().getResourceSet().getResources());
        forceLoadingOfEveryLinkedResource();
        monitor.worked(10);

        // Add the unknown resources to the semantic resources of this
        // session.
        manageAutomaticallyLoadedResources(session, resourcesBeforeLoadOfSession);
        monitor.worked(1);

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        // Look for controlled resources after load of every linked
        // resources.
        // Detect actual controlled resources.
        ControlledResourcesDetector.refreshControlledResources(session);
        if (controlledResourcesDetector != null) {
            controlledResourcesDetector.initialize();
        }
        // Reset semanticResources to have getSemanticResources() ignores
        // controlledResources which are computed only at this step
        if (semanticResourcesUpdater != null) {
            semanticResourcesUpdater.dispose();
            semanticResourcesUpdater = null;
        }
        semanticResources = null;
        monitor.worked(1);
        dAnalysisRefresher.initialize();
    }

    void addAdaptersOnAnalysis(final DAnalysis analysis) {
        if (semanticResourcesUpdater != null && !analysis.eAdapters().contains(semanticResourcesUpdater)) {
            analysis.eAdapters().add(semanticResourcesUpdater);
        }
    }

    void removeAdaptersOnAnalysis(final DAnalysis analysis) {
        if (semanticResourcesUpdater != null && analysis.eAdapters().contains(semanticResourcesUpdater)) {
            analysis.eAdapters().remove(semanticResourcesUpdater);
        }
    }

    Collection<Resource> getSemanticResources() {
        if (semanticResources == null) {
            semanticResources = new CopyOnWriteArrayList<Resource>();
            if (semanticResourcesUpdater == null) {
                semanticResourcesUpdater = new SemanticResourcesUpdater(session);
            }
            semanticResourcesUpdater.setSemanticResources(semanticResources);

            RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new RunnableWithResult.Impl<Collection<Resource>>() {
                @Override
                public void run() {
                    setResult(SemanticResourceGetter.collectTopLevelSemanticResources(session));
                }
            };
            try {
                TransactionUtil.runExclusive(session.getTransactionalEditingDomain(), semanticResourcesGetter);
            } catch (InterruptedException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while accessing semantic resources"));
            }
            ((CopyOnWriteArrayList<Resource>) semanticResources).addAllAbsent(semanticResourcesGetter.getResult());
        }
        return Collections.unmodifiableCollection(semanticResources);
    }

    /**
     * Allow semanticResources to be recomputed when calling
     * <code>getSemanticResources()</code>.
     */
    void setSemanticResourcesNotUptodate() {
        semanticResources.clear();
        semanticResources = null;
    }

    /**
     * Resolve all resources of the resource set of this session. Some
     * references are ignored (derived features, containment/container
     * references).
     */
    private void forceLoadingOfEveryLinkedResource() {
        ModelUtils.resolveAll(session.getTransactionalEditingDomain().getResourceSet(), new EReferencePredicate() {
            @Override
            public boolean apply(EReference input) {
                // Do not resolve derived features.
                // Do not resolve containment/container references : they are
                // already resolved by the model structural analysis course.
                return !input.isDerived() && !input.isContainer() && !input.isContainment();
            }
        });
    }

    /**
     * Resolve all VSM resources, and VSM linked resources (as
     * viewpoint:/environment resource), used through Sirius in
     * <code>allAnalysis</code>.
     * 
     * @param allAnalysis
     *            The analysis of this session (owned analysis or referenced
     *            analysis by this session).
     */
    private void resolveAllVSMResources(Collection<DAnalysis> allAnalysis) {
        List<Resource> resolvedResources = Lists.newArrayList();
        for (DAnalysis dAnalysis : allAnalysis) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                if (dView.getViewpoint() != null) {
                    Resource vsmResource = dView.getViewpoint().eResource();
                    if (vsmResource != null && !resolvedResources.contains(vsmResource)) {
                        ModelUtils.resolveAll(vsmResource, true);
                        resolvedResources.add(vsmResource);
                    }
                }
            }
        }
    }

    /**
     * Resolve all semantic resources.
     * 
     * @param allAnalysis
     *            The analysis of this session
     */
    private void resolveAllSemanticResourcesFromModels(Collection<DAnalysis> allAnalysis) {
        for (DAnalysis dAnalysis : allAnalysis) {
            for (EObject model : dAnalysis.getModels()) {
                EcoreUtil.resolve(model, dAnalysis);
            }
        }
    }

    /**
     * Check the resources in the resourceSet. Detect new resources and add them
     * to the session as new semantic resources or referenced session resources.<BR>
     * <BR>
     * New semantic resources are :
     * <UL>
     * <LI>Resources that are not in the <code>knownResources</code> list</LI>
     * <LI>Resources that are not in the semantic resources of this session</LI>
     * <LI>Resources that are not in the referenced representations files
     * resources of this session</LI>
     * <LI>Resources that are not the Sirius environment resource</LI>
     * </UL>
     * <BR>
     * New referenced session resources are :
     * <UL>
     * <LI>Resources that are not in the <code>knownResources</code> list</LI>
     * <LI>Resources that are in the referenced representations files resources
     * of this session (the list is computed from the allAnalyses() result)</LI>
     * </UL>
     * 
     * @param knownResources
     *            List of resources that is already loaded before the resolveAll
     *            of the representations file load.
     */
    static void manageAutomaticallyLoadedResources(final DAnalysisSessionImpl session, List<Resource> knownResources) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        List<Resource> resourcesAfterLoadOfSession = Lists.newArrayList(domain.getResourceSet().getResources());
        // Remove the known resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), knownResources);

        if (resourcesAfterLoadOfSession.isEmpty()) {
            return;
        }

        Set<Resource> referencedSessionResources = session.getReferencedSessionResources();
        Collection<Resource> newReferencedSessionResources = Lists.newArrayList(Iterables.filter(resourcesAfterLoadOfSession, Predicates.in(referencedSessionResources)));
        if (!newReferencedSessionResources.isEmpty()) {
            for (Resource newReferencedSessionResource : newReferencedSessionResources) {
                session.registerResourceInCrossReferencer(newReferencedSessionResource);
                for (DAnalysis refAnalysis : Iterables.filter(newReferencedSessionResource.getContents(), DAnalysis.class)) {
                    session.addAdaptersOnAnalysis(refAnalysis);
                }
            }
        }

        // Remove the known semantic resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), session.getSemanticResources());
        // Remove the known referenced representations file resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), referencedSessionResources);

        final Iterable<Resource> newSemanticResourcesIterator = Iterables.filter(resourcesAfterLoadOfSession, new Predicate<Resource>() {
            public boolean apply(Resource resource) {
                // Remove empty resource and the Sirius environment
                return !resource.getContents().isEmpty() && !(new URIQuery(resource.getURI()).isEnvironmentURI());
            }
        });
        if (!Iterables.isEmpty(newSemanticResourcesIterator)) {
            domain.getCommandStack().execute(new RecordingCommand(domain, "Add referenced semantic resources") {
                @Override
                protected void doExecute() {
                    for (Resource resource : newSemanticResourcesIterator) {
                        session.addSemanticResource(resource.getURI(), new NullProgressMonitor());
                    }
                }
            });
        }
    }

    void dispose() {
        session = null;
        if (dAnalysisRefresher != null) {
            dAnalysisRefresher.dispose();
            dAnalysisRefresher = null;
        }
        if (controlledResourcesDetector != null) {
            controlledResourcesDetector.dispose();
            controlledResourcesDetector = null;
        }
        if (semanticResourcesUpdater != null) {
            semanticResourcesUpdater.dispose();
            semanticResourcesUpdater = null;
        }
        if (semanticResources != null) {
            semanticResources.clear();
        }
    }

    void detectControlledResources() {
        ControlledResourcesDetector.refreshControlledResources(session);
    }
}
