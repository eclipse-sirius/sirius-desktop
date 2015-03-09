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
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.SiriusPlugin;

import com.google.common.base.Preconditions;
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
    }

    void initialize(IProgressMonitor monitor) {
        /*
         * Resolves all models needed by the session because GMF installs a
         * CrossReferencerAdapter that resolves the resource set.
         */
        DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        dAnalysisRefresher = new DAnalysisRefresher(session);
        Collection<DAnalysis> analyses = session.allAnalyses();
        // First resolve all VSM resources used for Sirius to ignore VSM
        // resources and VSM linked resources (as viewpoint:/environment
        // resource) as new semantic element
        dAnalysisRefresher.resolveAllVSMResources(analyses);
        // Resolve all semantic resources from {@link DAnalysis.getModels()}
        dAnalysisRefresher.resolveAllSemanticResourcesFromModels(analyses);
        // Then resolve all resources (to automatically add new semantic
        // resources)
        List<Resource> resourcesBeforeLoadOfSession = Lists.newArrayList(session.getTransactionalEditingDomain().getResourceSet().getResources());
        dAnalysisRefresher.forceLoadingOfEveryLinkedResource();
        monitor.worked(10);

        // Add the unknown resources to the semantic resources of this
        // session.
        dAnalysisRefresher.addAutomaticallyLoadedResourcesToSemanticResources(resourcesBeforeLoadOfSession);
        monitor.worked(1);
        session.setSynchronizeStatusofEveryResource();
        monitor.worked(1);

        DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
        // Look for controlled resources after load of every linked
        // resources.
        handlePossibleControlledResources();
        monitor.worked(1);
        dAnalysisRefresher.init();
    }

    void discoverAutomaticallyLoadedSemanticResources(List<Resource> allResources) {
        // Add the unknown resources to the semantic resources of this session.
        if (dAnalysisRefresher != null) {
            dAnalysisRefresher.addAutomaticallyLoadedResourcesToSemanticResources(allResources);
        }
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
            semanticResourcesUpdater = new SemanticResourcesUpdater(session, semanticResources);
            RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new SemanticResourceGetter(session);
            try {
                TransactionUtil.runExclusive(session.getTransactionalEditingDomain(), semanticResourcesGetter);
            } catch (InterruptedException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while accessing semantic resources"));
            }
            ((CopyOnWriteArrayList<Resource>) semanticResources).addAllAbsent(semanticResourcesGetter.getResult());
        }
        return Collections.unmodifiableCollection(semanticResources);
    }

    void handlePossibleControlledResources() {
        // Detect actual controlled resources.
        if (controlledResourcesDetector != null) {
            controlledResourcesDetector.init();
        }
        // Reset semanticResources to have getSemanticResources() ignores
        // controlledResources which are computed only at this step
        if (semanticResourcesUpdater != null) {
            semanticResourcesUpdater.dispose();
            semanticResourcesUpdater = null;
        }
        semanticResources = null;
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
}
