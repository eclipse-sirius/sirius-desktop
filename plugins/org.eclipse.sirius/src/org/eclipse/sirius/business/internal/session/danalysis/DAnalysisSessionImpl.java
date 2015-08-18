/*******************************************************************************
 * Copyright (c) 2013, 2015 THALES GLOBAL SERVICES, Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.transaction.util.ValidateEditSupport;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.SavingPolicy;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.business.api.session.SessionListener;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.session.SessionService;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelector;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSelectorService;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionHelper;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSessionService;
import org.eclipse.sirius.business.internal.resource.ResourceModifiedFieldUpdater;
import org.eclipse.sirius.business.internal.session.IsModifiedSavingPolicy;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.business.internal.session.RepresentationNameListener;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.api.util.ECrossReferenceAdapterWithUnproxyCapability;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.tools.internal.interpreter.ODesignGenericInterpreter;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationContainer;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A session which store data in {@link DAnalysis} references.
 * 
 * @author cbrun
 */
public class DAnalysisSessionImpl extends DAnalysisSessionEObjectImpl implements Session, DAnalysisSession, ResourceSyncClient {
    /** The custom saving policy the session should use. */
    private SavingPolicy savingPolicy;

    /** The {@link TransactionalEditingDomain} associated to this Session. */
    private TransactionalEditingDomain transactionalEditingDomain;

    /** The main Session Resource. */
    private Resource sessionResource;

    /** The {@link DAnalysis} of the main session resource (*.aird). */
    private DAnalysis mainDAnalysis;

    private SessionResourcesTracker tracker = new SessionResourcesTracker(this);

    // Session's configuration

    private final Saver saver;

    private ReloadingPolicy reloadingPolicy;

    private IResourceCollector currentResourceCollector;

    private SessionVSMUpdater vsmUpdater = new SessionVSMUpdater(this);

    private final SessionResourcesSynchronizer resourcesSynchronizer = new SessionResourcesSynchronizer(this);

    // Generic services offered by the session

    /**
     * The event broker suitable for identifying local or remote atomic changes.
     */
    private SessionEventBroker broker;

    private SessionService services;

    private ECrossReferenceAdapterWithUnproxyCapability crossReferencer;

    private IInterpreter interpreter;

    private final ListenerList listeners = new ListenerList();

    private int lastNotification = -1;

    // Default listeners

    /** The listener suitable for refresh the opened viewpoint editors. */
    private RefreshEditorsPrecommitListener refreshEditorsListeners;

    private RepresentationsChangeAdapter representationsChangeAdapter;

    private RepresentationNameListener representationNameListener;

    /**
     * Create a new session.
     * 
     * @param mainDAnalysis
     *            the analysis keeping the session data.
     */
    public DAnalysisSessionImpl(DAnalysis mainDAnalysis) {
        Preconditions.checkNotNull(mainDAnalysis);
        this.sessionResource = mainDAnalysis.eResource();
        Preconditions.checkNotNull(this.sessionResource, "A session must be inside a resource.");
        this.transactionalEditingDomain = Preconditions.checkNotNull(TransactionUtil.getEditingDomain(mainDAnalysis), "A session must be associated to an EditingDomain");
        this.mainDAnalysis = mainDAnalysis;
        this.saver = new Saver(this);
        this.interpreter = new ODesignGenericInterpreter();
        this.representationsChangeAdapter = new RepresentationsChangeAdapter(this);
        super.getAnalyses().add(mainDAnalysis);
        super.getResources().add(sessionResource);
        setAnalysisSelector(DAnalysisSelectorService.getSelector(this));
        setResourceCollector(new LocalResourceCollector(getTransactionalEditingDomain().getResourceSet()));
        setDeferSaveToPostCommit(true);
        setSaveInExclusiveTransaction(true);

    }

    // *******************
    // Session interpreter
    // *******************

    @Override
    public IInterpreter getInterpreter() {
        if (this.crossReferencer == null) {
            this.interpreter.setCrossReferencer(getSemanticCrossReferencer());
        }
        return this.interpreter;
    }

    /**
     * Configure the interpreter attached to this session.
     */
    void configureInterpreter() {
        // Calculate paths of the activated representation description files.
        List<String> filePaths = new ArrayList<String>();
        for (Viewpoint vp : getSelectedViewpointsSpecificToGeneric()) {
            Resource vpResource = vp.eResource();
            if (vpResource != null) {
                filePaths.add(vpResource.getURI().toPlatformString(true));
            }
        }
        // Setting the FILES property is actually interpreted as adding new
        // files for historical reasons, so reset it to null first before
        // setting the new value.
        this.interpreter.setProperty(IInterpreter.FILES, null);
        this.interpreter.setProperty(IInterpreter.FILES, filePaths);
        InterpreterRegistry.prepareImportsFromSession(this.interpreter, this);
        this.interpreter.setCrossReferencer(getSemanticCrossReferencer());
    }

    // *******************
    // Cross-referencer
    // *******************

    @Override
    public ECrossReferenceAdapterWithUnproxyCapability getSemanticCrossReferencer() {
        if (crossReferencer == null) {
            crossReferencer = createSemanticCrossReferencer();
            if (interpreter != null) {
                interpreter.setCrossReferencer(crossReferencer);
            }
        }
        return crossReferencer;
    }

    /**
     * Create the semantic cross referencer.
     * 
     * @return a new cross referencer adapter
     */
    protected ECrossReferenceAdapterWithUnproxyCapability createSemanticCrossReferencer() {
        return new SessionLazyCrossReferencer(this);
    }

    /**
     * Add the cross referencer (if exists and is not present) to the eAdapters
     * list of the given resource.
     * 
     * @param newResource
     *            the resource on which the semantic cross reference should be
     *            added.
     */
    protected void registerResourceInCrossReferencer(final Resource newResource) {
        if (crossReferencer != null) {
            if (!newResource.eAdapters().contains(crossReferencer)) {
                newResource.eAdapters().add(crossReferencer);
            }
        }
    }

    /**
     * Remove the cross referencer (if exists and is present) from the eAdapters
     * list of the given resource.
     * 
     * @param resource
     *            the resource from which the semantic cross reference should be
     *            removed.
     */
    protected void unregisterResourceInCrossReferencer(final Resource resource) {
        if (crossReferencer != null) {
            if (resource.eAdapters().contains(crossReferencer)) {
                resource.eAdapters().remove(crossReferencer);
            }
        }
    }

    /**
     * Disable & Remove all ECrossReferencerAdapter adapters.
     */
    protected void disableAndRemoveECrossReferenceAdapters() {
        ResourceSet resourceSet = getTransactionalEditingDomain().getResourceSet();

        // Disable resolution of proxy for SiriusCrossReferenceAdapter of
        // resourceSet
        List<Adapter> adaptersToRemove = new ArrayList<Adapter>();
        for (Adapter next : resourceSet.eAdapters()) {
            if (next instanceof SiriusCrossReferenceAdapter) {
                ((SiriusCrossReferenceAdapter) next).disableResolveProxy();
                adaptersToRemove.add(next);
            }
        }
        resourceSet.eAdapters().removeAll(adaptersToRemove);

        // disable resolveProxy capability before clearing adapters on resources
        for (Resource resource : resourceSet.getResources()) {
            disableCrossReferencerResolve(resource);
            resource.eAdapters().clear();
        }

        for (final DAnalysis analysis : Iterables.filter(allAnalyses(), Predicates.notNull())) {
            removeAdaptersOnAnalysis(analysis);
        }
    }

    // *******************
    // Analyses
    // *******************

    @Override
    public void addAnalysis(Resource analysisResource) {
        Preconditions.checkArgument(analysisResource.getContents().get(0) instanceof DAnalysis);
        DAnalysis analysis = (DAnalysis) analysisResource.getContents().get(0);
        super.getResources().add(analysisResource);
        super.getAnalyses().add(analysis);
        registerResourceInCrossReferencer(analysisResource);
        addAdaptersOnAnalysis(analysis);
        notifyListeners(SessionListener.REPRESENTATION_CHANGE);
        DViewOperations.on(this).updateSelectedViewpointsData(new NullProgressMonitor());
        configureInterpreter();
    }

    @Override
    public void removeAnalysis(Resource analysisResource) {
        Preconditions.checkArgument(analysisResource.getContents().get(0) instanceof DAnalysis);
        DAnalysis analysis = (DAnalysis) analysisResource.getContents().get(0);
        super.getResources().remove(analysisResource);
        super.getAnalyses().remove(analysis);
        for (DAnalysis potentialRef : allAnalyses()) {
            if (potentialRef.getReferencedAnalysis().contains(analysis)) {
                potentialRef.getReferencedAnalysis().remove(analysis);
            }
        }
        unregisterResourceInCrossReferencer(analysisResource);
        transactionalEditingDomain.getResourceSet().getResources().remove(analysisResource);
        removeAdaptersOnAnalysis(analysis);
        notifyListeners(SessionListener.REPRESENTATION_CHANGE);
    }

    @Override
    public void addReferencedAnalysis(final DAnalysis newAnalysis) {
        assert newAnalysis.eResource() != null;
        List<DAnalysis> sources = Lists.newArrayList();
        if (!super.getAnalyses().isEmpty()) {
            DAnalysis referencer = super.getAnalyses().iterator().next();
            if (referencer != null) {
                sources.add(referencer);
            }
        }
        addReferencedAnalysis(newAnalysis, sources);
    }

    @Override
    public void addReferencedAnalysis(final DAnalysis newAnalysis, final Collection<DAnalysis> referencers) {
        if (referencers != null && !referencers.isEmpty()) {
            // Install the cross referencer as soon as possible
            registerResourceInCrossReferencer(newAnalysis.eResource());

            for (DAnalysis referencer : referencers) {
                referencer.getReferencedAnalysis().add(newAnalysis);
            }

            addAdaptersOnAnalysis(newAnalysis);
            for (DAnalysis dAnalysis : new DAnalysisQuery(newAnalysis).getAllReferencedAnalyses()) {
                registerResourceInCrossReferencer(dAnalysis.eResource());
                addAdaptersOnAnalysis(dAnalysis);
            }

            notifyListeners(SessionListener.REPRESENTATION_CHANGE);
        } else {
            throw new IllegalStateException("Cant add a referenced analysis if no parent analysis exists");
        }
    }

    @Override
    public void removeReferencedAnalysis(final DAnalysis analysis) {
        assert analysis.eResource() != null;
        Collection<DAnalysis> referencers = Lists.newArrayList();
        for (DAnalysis potentialRef : allAnalyses()) {
            if (potentialRef.getReferencedAnalysis().contains(analysis)) {
                referencers.add(potentialRef);
            }
        }
        if (!referencers.isEmpty()) {
            for (DAnalysis referencer : referencers) {
                referencer.getReferencedAnalysis().remove(analysis);
            }
            removeAdaptersOnAnalysis(analysis);
            notifyListeners(SessionListener.REPRESENTATION_CHANGE);
        } else {
            throw new IllegalStateException("Cant remove a referenced analysis if no parent analysis exists");
        }
    }

    /**
     * Return all valid(i.e. not null) owned and referenced analyses.
     * 
     * @return all valid(i.e. not null) owned and referenced analyses.
     */
    public Collection<DAnalysis> allAnalyses() {
        Collection<DAnalysis> analysisAndReferenced = Sets.newLinkedHashSet();
        for (DAnalysis analysis : Lists.newArrayList(super.getAnalyses())) {
            /* analysis could be null */
            if (analysis != null) {
                analysisAndReferenced.add(analysis);
                addAllReferencedAnalyses(analysisAndReferenced, analysis);
            }
        }
        return analysisAndReferenced;
    }

    Collection<Resource> getAllSemanticResources() {
        Collection<Resource> semanticResources = new LinkedHashSet<Resource>(this.getSemanticResources());
        semanticResources.addAll(this.getControlledResources());
        return semanticResources;
    }

    private void addAllReferencedAnalyses(final Collection<DAnalysis> analysisAndReferenced, final DAnalysis analysis) {
        for (DAnalysis referenced : Sets.newLinkedHashSet(analysis.getReferencedAnalysis())) {
            if (!analysisAndReferenced.contains(referenced) && referenced.eResource() != null) {
                analysisAndReferenced.add(referenced);
                addAllReferencedAnalyses(analysisAndReferenced, referenced);
            }
        }
    }

    DAnalysis getMainAnalysis() {
        return this.mainDAnalysis;
    }

    @Override
    public void addAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.registerAnalysis(analysis);
        }
        if (tracker != null) {
            tracker.addAdaptersOnAnalysis(analysis);
        }
    }

    @Override
    public void removeAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.unregisterAnalysis(analysis);
        }
        if (tracker != null) {
            tracker.removeAdaptersOnAnalysis(analysis);
        }
    }

    @Override
    public void moveRepresentation(final DAnalysis newContainer, final DRepresentation representation) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation.eContainer());
        IProgressMonitor pm = new NullProgressMonitor();
        if (!authority.canDeleteInstance(representation)) {
            throw new LockedInstanceException(representation);
        }
        final EObject semantic;
        if (representation.eContainer() instanceof DRepresentationContainer && !((DRepresentationContainer) representation.eContainer()).getModels().isEmpty()) {
            semantic = ((DRepresentationContainer) representation.eContainer()).getModels().iterator().next();
        } else {
            semantic = null;
        }
        Viewpoint viewpoint = ((DView) representation.eContainer()).getViewpoint();
        DView receiver = findViewForRepresentation(viewpoint, newContainer);
        if (receiver == null) {
            final IPermissionAuthority analysisAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(newContainer);
            if (analysisAuthority.canCreateIn(newContainer)) {
                createView(viewpoint, Lists.newArrayList(semantic), false, pm);
                receiver = findViewForRepresentation(viewpoint, newContainer);
            } else {
                throw new LockedInstanceException(newContainer);
            }
        }
        final IPermissionAuthority receiverAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(receiver);
        if (receiverAuthority.canCreateIn(receiver)) {
            receiver.getOwnedRepresentations().add(representation);
            // Add all semantic root elements pointed by the target of all
            // DSemanticDecorator of this representation (except of this root is
            // a root of a referencedAnalysis)
            if (receiver.eContainer() instanceof DAnalysis) {
                DAnalysisSessionHelper.updateModelsReferences((DAnalysis) receiver.eContainer(), Iterators.filter(representation.eAllContents(), DSemanticDecorator.class));
            }
        } else {
            throw new LockedInstanceException(receiver);
        }
        for (EObject object : getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, representation)) {
            getServices().putCustomData(CustomDataConstants.GMF_DIAGRAMS, representation, object);
        }
    }

    private static DView findViewForRepresentation(Viewpoint vp, DAnalysis analysis) {
        for (final DView view : analysis.getOwnedViews()) {
            if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), vp)) {
                return view;
            }
        }
        return null;
    }

    // *******************
    // Semantic Resources
    // *******************

    /**
     * Find all the resources referenced by any object from the specified
     * resource. Ignore "http" resources.
     * 
     * @param res
     *            the resource to start from.
     * @return all the resources referenced by elements from res, except "http"
     *         resources.
     */
    protected Collection<Resource> collectAllReferencedResources(Resource res) {
        Collection<Resource> result = Collections.emptyList();
        if (currentResourceCollector != null) {
            result = currentResourceCollector.getAllReferencedResources(res);
        }
        return result;
    }

    /**
     * Find all the resources referencing by any object from the specified
     * resource. Ignore "http" resources.
     * 
     * @param res
     *            the resource to start from.
     * @return all the resources referencing by elements from res, except "http"
     *         resources.
     */
    protected Collection<Resource> collectAllReferencingResources(Resource res) {
        Collection<Resource> result = Collections.emptyList();
        if (currentResourceCollector != null) {
            result = currentResourceCollector.getAllReferencingResources(res);
        }
        return result;
    }

    @Override
    public synchronized void addSemanticResource(URI semanticModelURI, IProgressMonitor monitor) {
        if (semanticModelURI != null) {
            if (new FileQuery(semanticModelURI.fileExtension()).isSessionResourceFile()) {
                throw new IllegalArgumentException("A representation file cannot be added as semantic resource.");
            }
            monitor.beginTask("Semantic resource addition : " + semanticModelURI.lastSegment(), 3);
            ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
            // Make ResourceSet aware of resource loading with progress
            // monitor
            ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));
            try {
                monitor.beginTask("Semantic resource addition : " + semanticModelURI.lastSegment(), 3);

                Resource newSemanticResource = resourceSet.getResource(semanticModelURI, false);
                if (newSemanticResource != null && newSemanticResource.getContents().isEmpty()) {
                    // The resource is probably loaded (created) with a proxy
                    // resolution. Indeed, in case of proxy, the method
                    // eResolveProxy causes a creation of an empty resource in
                    // the resourceSet.
                    // So we must unload it before load it again.
                    // resourceSet.
                    newSemanticResource.unload();
                }
                monitor.worked(1);
                newSemanticResource = resourceSet.getResource(semanticModelURI, true);
                // If it is a new resource, register it with all its referenced
                // resources as semantic models.
                if (!getSemanticResources().contains(newSemanticResource)) {
                    doAddSemanticResource(newSemanticResource, resourceSet);
                    for (Resource res : collectAllReferencedResources(newSemanticResource)) {
                        doAddSemanticResource(res, resourceSet);
                    }
                }
            } finally {
                monitor.done();
                ResourceSetUtil.resetProgressMonitor(resourceSet);
            }
        }
    }

    /**
     * Registers the specified resource as a semantic resource.
     * 
     * @param newResource
     *            the semantic resource.
     * @param set
     *            the ResourceSet in which it should be added if needed.
     */
    protected void doAddSemanticResource(final Resource newResource, final ResourceSet set) {
        if (new ResourceQuery(newResource).isRepresentationsResource()) {
            throw new IllegalArgumentException("A representation file cannot be added as semantic resource.");
        }
        if (newResource.getResourceSet() != set) {
            set.getResources().add(newResource);
        }
        if (newResource.getContents().size() > 0) {
            notifyNewMetamodels(newResource);
            for (final DAnalysis analysis : this.allAnalyses()) {
                analysis.getSemanticResources().add(new ResourceDescriptor(newResource.getURI()));
            }
        }

        ControlledResourcesDetector.refreshControlledResources(this);

        registerResourceInCrossReferencer(newResource);
    }

    private void notifyNewMetamodels(final Resource newResource) {
        if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false"))) { //$NON-NLS-1$ //$NON-NLS-2$
            return;
        }
        final Collection<EPackage> collectedMetamodels = collectMetamodels(newResource.getAllContents());
        final ModelAccessor accessor = getModelAccessor();
        if (accessor != null) {
            final Collection<EcoreMetamodelDescriptor> descriptors = new ArrayList<EcoreMetamodelDescriptor>();
            for (final EPackage package1 : collectedMetamodels) {
                descriptors.add(new EcoreMetamodelDescriptor(package1));
            }
            accessor.activateMetamodels(descriptors);
        }
    }

    private Collection<EPackage> collectMetamodels(final TreeIterator<EObject> allContents) {
        final Collection<EPackage> result = new LinkedHashSet<EPackage>();
        while (allContents.hasNext()) {
            result.add(allContents.next().eClass().getEPackage());
        }
        return result;
    }

    /**
     * Allow semanticResources to be recomputed when calling
     * <code>getSemanticResources()</code>.
     */
    void setSemanticResourcesNotUptodate() {
        tracker.setSemanticResourcesNotUptodate();
    }

    @Override
    public Collection<Resource> getSemanticResources() {
        if (tracker != null) {
            return tracker.getSemanticResources();
        } else {
            return Collections.emptyList();
        }
    }

    @Override
    public void removeSemanticResource(Resource semanticResource, IProgressMonitor monitor, boolean removeReferencingResources) {
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        if (removeReferencingResources) {
            for (final Resource res : collectAllReferencingResources(semanticResource)) {
                doRemoveSemanticResource(res, resourceSet);
            }
        }
        doRemoveSemanticResource(semanticResource, resourceSet);
    }

    /**
     * Unregisters the resource from the list of semantic resources.
     * 
     * @param res
     *            the semantic resource to unregister.
     * @param set
     *            the resourceset from which to remove it.
     */
    protected void doRemoveSemanticResource(final Resource res, final ResourceSet set) {
        set.getResources().remove(res);

        // update models in aird resource
        for (final DAnalysis analysis : this.allAnalyses()) {
            analysis.getSemanticResources().remove(new ResourceDescriptor(res.getURI()));
        }

        unregisterResourceInCrossReferencer(res);
        if (!isFromPackageRegistry(set, res)) {
            disableCrossReferencerResolve(res);
            res.unload();
            enableCrossReferencerResolve(res);
        }

        ControlledResourcesDetector.refreshControlledResources(this);
    }

    void discoverAutomaticallyLoadedResources(List<Resource> allResources) {
        SessionResourcesTracker.manageAutomaticallyLoadedResources(this, allResources);
    }

    // *******************
    // Session Resources
    // *******************
    @Override
    public Resource getSessionResource() {
        return sessionResource;
    }

    @Override
    public Set<Resource> getReferencedSessionResources() {
        return getSessionResources(false);
    }

    @Override
    public Set<Resource> getAllSessionResources() {
        return getSessionResources(true);
    }

    private Set<Resource> getSessionResources(boolean includeMainResource) {
        List<Resource> result = Lists.newArrayList();
        for (DAnalysis analysis : allAnalyses()) {
            Resource res = analysis.eResource();
            if (res != null && (includeMainResource || res != sessionResource)) {
                result.add(res);
            }
        }
        return ImmutableSet.copyOf(result);
    }

    // *******************
    // Saving and Synchronization
    // *******************
    /**
     * Sets the Synchronization status of every resources of this Session's
     * resourceSet to SYNC or changed regarding their modified status.
     */
    protected void setSynchronizeStatusofEveryResource() {
        setSynchronizeStatusofEveryResource(transactionalEditingDomain.getResourceSet().getResources());
    }

    /**
     * Sets the Synchronization status of considered resources of this Session's
     * resourceSet to SYNC or changed regarding their modified status.
     * 
     * Should only be called from setSynchronizeStatusofEveryResource method
     * (and overriding ones).
     * 
     * @param resourcesToConsider
     *            the resources to consider.
     */
    protected final void setSynchronizeStatusofEveryResource(Iterable<Resource> resourcesToConsider) {
        ResourceSetSync rsSetSync = ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain);
        Collection<ResourceSyncClient.ResourceStatusChange> changes = Lists.newArrayList();
        for (Resource resource : Sets.newHashSet(resourcesToConsider)) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(resource);
            ResourceStatus newStatus = resource.isModified() ? ResourceStatus.CHANGED : ResourceStatus.SYNC;
            changes.add(new ResourceSyncClient.ResourceStatusChange(resource, newStatus, oldStatus));
        }
        rsSetSync.statusesChanged(changes);
    }

    @Override
    public final void save(IProgressMonitor monitor) {
        save((Map<?, ?>) null, monitor);
    }

    @Override
    public void save(Map<?, ?> options, IProgressMonitor monitor) {
        saver.save(options, monitor);
    }

    /**
     * Performs the save immediately.
     * 
     * @param options
     *            the options to use to save the resources.
     * @param monitor
     *            the monitor to use to report progress.
     * @param runExclusive
     *            whether or not to execute the saving in an exclusive
     *            transaction.
     */
    protected void doSave(final Map<?, ?> options, final IProgressMonitor monitor, boolean runExclusive) {
        try {
            monitor.beginTask("Session saving", 3);
            final Collection<Resource> allResources = Lists.newArrayList();
            allResources.addAll(getAllSessionResources());
            Collection<Resource> semanticResourcesCollection = getSemanticResources();
            allResources.addAll(semanticResourcesCollection);
            allResources.addAll(getControlledResources());
            monitor.worked(1);
            RunnableWithResult<Collection<Resource>> save = new RunnableWithResult.Impl<Collection<Resource>>() {
                @Override
                public void run() {
                    Collection<Resource> savedResources = getSavingPolicy().save(allResources, options, new SubProgressMonitor(monitor, 7));
                    setResult(savedResources);
                }
            };
            if (runExclusive && !saver.domainDisposed.get()) {
                /*
                 * launching the save itself in a read-only transaction will
                 * block any other operation on the model which could come in
                 * the meantime.
                 */
                getTransactionalEditingDomain().runExclusive(save);
            } else {
                save.run();
            }

            Collection<Resource> savedResources = save.getResult();
            if (savedResources != null) {
                CommandStack commandStack = transactionalEditingDomain.getCommandStack();
                if (commandStack instanceof BasicCommandStack) {
                    ((BasicCommandStack) commandStack).saveIsDone();
                }
                if (allResourcesAreInSync()) {
                    notifyListeners(SessionListener.SYNC);
                } else {
                    notifyListeners(SessionListener.DIRTY);
                }
                monitor.worked(1);
            }
        } catch (InterruptedException e) {
            SiriusPlugin.getDefault().warning("save interrupted", e);
        } finally {
            monitor.done();
        }
    }

    @Override
    public void statusChanged(Resource resource, ResourceStatus oldStatus, ResourceStatus newStatus) {
        resourcesSynchronizer.statusChanged(resource, oldStatus, newStatus);
    }

    @Override
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        resourcesSynchronizer.statusesChanged(changes);
    }

    /**
     * Tells if the specified resource is one of
     * {@link Session#getSemanticResources()},
     * {@link Session#getAllSessionResources()} or
     * DAnalysisSessionEObject#getControlledResources().
     * 
     * @param resource
     *            the specified {@link Resource}
     * @param resources
     *            the session resources
     * @return true if the specified {@link Resource} is one of the
     *         {@link Session}, false otherwise
     */
    protected boolean isResourceOfSession(Resource resource, Iterable<Resource> resources) {
        for (Resource input : resources) {
            if (resource == input || (input.getURI() != null && Objects.equal(resource.getURI(), input.getURI()))) {
                return true;
            }
        }
        return false;
    }

    void sessionResourceReloaded(final Resource newSessionResource) {
        // sessionResource's contents before reload can be proxy
        // then need to be reassigned with reloaded
        // sessionResource reference.
        sessionResource = newSessionResource;
        mainDAnalysis = (DAnalysis) sessionResource.getContents().get(0);
    }

    @Override
    public void setReloadingPolicy(ReloadingPolicy reloadingPolicy) {
        this.reloadingPolicy = reloadingPolicy;
    }

    @Override
    public ReloadingPolicy getReloadingPolicy() {
        return reloadingPolicy != null ? reloadingPolicy : new ReloadingPolicyImpl(new NoUICallback());
    }

    @Override
    public void setSavingPolicy(SavingPolicy savingPolicy) {
        this.savingPolicy = savingPolicy;
    }

    /**
     * Returns the custom saving policy the session should use ; if no
     * SavingPolicy has been defined, creates a default one.<br/>
     * Subclasses can override this method to define a new default Saving
     * Policy.
     * 
     * @return the custom saving policy the session should use
     */
    @Override
    public SavingPolicy getSavingPolicy() {
        return savingPolicy != null ? savingPolicy : new IsModifiedSavingPolicy(transactionalEditingDomain);
    }

    /**
     * Indicates whether all resources (semantic and Danalysises) of this
     * Session are whether {@link ResourceStatus#SYNC} or
     * {@link ResourceStatus#READONLY}.
     * 
     * @return true if all resources (semantic and Danalysises) of this Session
     *         are whether {@link ResourceStatus#SYNC} or
     *         {@link ResourceStatus#READONLY}, false otherwise
     */
    protected boolean allResourcesAreInSync() {
        return resourcesSynchronizer.allResourcesAreInSync();
    }

    /**
     * Indicates whether considered resources are whether
     * {@link ResourceStatus#SYNC} or {@link ResourceStatus#READONLY}.
     * 
     * @param resourcesToConsider
     *            the resources to inspect.
     * @return true if all considered are whether {@link ResourceStatus#SYNC} or
     *         {@link ResourceStatus#READONLY}, false otherwise
     */
    protected final boolean checkResourcesAreInSync(Iterable<? extends Resource> resourcesToConsider) {
        return resourcesSynchronizer.checkResourcesAreInSync(resourcesToConsider);
    }

    @Override
    public SessionStatus getStatus() {
        if (allResourcesAreInSync()) {
            return SessionStatus.SYNC;
        } else {
            return SessionStatus.DIRTY;
        }
    }

    public void setDeferSaveToPostCommit(boolean deferSaveOnPostCommit) {
        this.saver.deferSaveToPostCommit = deferSaveOnPostCommit;
    }

    public boolean isDeferSaveToPostCommit() {
        return this.saver.deferSaveToPostCommit;
    }

    /**
     * Set to true to do saving in a read-only EMF Transaction, false otherwise.
     * Note that if the {@link SavingPolicy} execute some EMF Command, this must
     * be at false.
     * 
     * @param saveInExclusiveTransaction
     *            specify if the saving is done in a read-only transaction
     */
    public void setSaveInExclusiveTransaction(boolean saveInExclusiveTransaction) {
        this.saver.saveInExclusiveTransaction = saveInExclusiveTransaction;
    }

    public boolean isSaveInExclusiveTransaction() {
        return this.saver.saveInExclusiveTransaction;
    }

    // *******************
    // Model Accessor
    // *******************

    private void initializeAccessor() {
        ModelAccessor accessor = getModelAccessor();
        if (accessor != null) {
            accessor.init(transactionalEditingDomain.getResourceSet());
        }
    }

    @Override
    public ModelAccessor getModelAccessor() {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(transactionalEditingDomain.getResourceSet());
    }

    // *******************
    // Events, Triggers and Listeners
    // *******************
    /**
     * This method allows adding {@code ModelChangeTrigger} to the current
     * session {@link SessionEventBroker}. This method is called during the
     * opening of the Session, before setting the open attribute to true and
     * before launching the SessionListener.OPENED notifications.
     */
    protected void initLocalTriggers() {
        Predicate<Notification> danglingRemovalPredicate = Predicates.or(DanglingRefRemovalTrigger.IS_DETACHMENT, DanglingRefRemovalTrigger.IS_ATTACHMENT);
        DanglingRefRemovalTrigger danglingRemovalTrigger = new DanglingRefRemovalTrigger(this);
        getEventBroker().addLocalTrigger(SessionEventBrokerImpl.asFilter(danglingRemovalPredicate), danglingRemovalTrigger);

        addRefreshEditorsListener();
        /*
         * Make sure these adapters are added after the rest, and in particular
         * after the semantic cross-referencer, so that they can rely on an
         * up-to-date cross-referencer when invoked.
         */
        for (DAnalysis analysis : allAnalyses()) {
            addAdaptersOnAnalysis(analysis);
        }
    }

    @Override
    public void addListener(final SessionListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(final SessionListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notify all the registered listeners of the specified event.
     * 
     * @param notification
     *            the event to notify the listeners of.
     */
    public void notifyListeners(final int notification) {
        if (notification == SessionListener.REPRESENTATION_CHANGE || notification == SessionListener.SELECTED_VIEWS_CHANGE_KIND || notification == SessionListener.VSM_UPDATED
                || notification != lastNotification) {
            for (final SessionListener listener : Iterables.filter(Lists.newArrayList(listeners.getListeners()), SessionListener.class)) {
                listener.notify(notification);
            }
            lastNotification = notification;
        }
    }

    @Override
    public void notifyControlledModel(final Resource newControlled) {
        // Set the already controlled resource to modified because they can
        // reference the new resource.
        for (final Resource controlledResource : super.getControlledResources()) {
            controlledResource.setModified(true);
        }
        super.getControlledResources().add(newControlled);
        notifyListeners(SessionListener.SEMANTIC_CHANGE);
    }

    @Override
    public void notifyUnControlledModel(final EObject uncontrolled, final Resource resource) {
        if (resource.getContents().size() == 0) {
            super.getControlledResources().remove(resource);
        }
        notifyListeners(SessionListener.SEMANTIC_CHANGE);
    }

    @Override
    public SessionEventBroker getEventBroker() {
        if (broker == null) {
            broker = new SessionEventBrokerImpl(transactionalEditingDomain);
        }
        return broker;
    }

    /**
     * Add the refresh editors preCommit listener to the editingDomain.
     */
    protected void addRefreshEditorsListener() {
        if (refreshEditorsListeners == null) {
            refreshEditorsListeners = new RefreshEditorsPrecommitListener(transactionalEditingDomain);
            getEventBroker().addLocalTrigger(RefreshEditorsPrecommitListener.IS_IMPACTING, refreshEditorsListeners);
            this.addListener(refreshEditorsListeners);
        }
    }

    @Override
    public RefreshEditorsPrecommitListener getRefreshEditorsListener() {
        return refreshEditorsListeners;
    }

    // *******************
    // Session Configuration
    // *******************

    @Override
    public void setAnalysisSelector(final DAnalysisSelector selector) {
        if (this.getServices() instanceof DAnalysisSessionService) {
            ((DAnalysisSessionService) this.getServices()).setAnalysisSelector(selector);
        }
    }

    public void setResourceCollector(IResourceCollector collector) {
        this.currentResourceCollector = collector;
    }

    // *******************
    // Viewpoint Selection and DView Management
    // *******************
    @Override
    public Collection<Viewpoint> getSelectedViewpoints(boolean includeReferencedAnalysis) {
        return DViewOperations.on(this).getSelectedViewpoints(includeReferencedAnalysis);
    }

    /**
     * Get the selected viewpoints sorted form more specifis to generics.
     * 
     * @return a collection of selected viewpoints for this session.
     */
    public Collection<Viewpoint> getSelectedViewpointsSpecificToGeneric() {
        return DViewOperations.on(this).getSelectedViewpointsSpecificToGeneric();
    }

    @Override
    public Collection<DView> getSelectedViews() {
        return DViewOperations.on(this).getSelectedViews(allAnalyses());
    }

    @Override
    public void addSelectedView(DView view, IProgressMonitor monitor) throws IllegalArgumentException {
        DViewOperations.on(this).addSelectedView(view, monitor);
    }

    @Override
    public void removeSelectedView(final DView view, IProgressMonitor monitor) {
        DViewOperations.on(this).removeSelectedView(view, monitor);
    }

    @Override
    public Collection<DView> getOwnedViews() {
        return DViewOperations.on(this).getOwnedViews();
    }

    @Override
    public void createView(Viewpoint viewpoint, Collection<EObject> semantics, IProgressMonitor monitor) {
        createView(viewpoint, semantics, true, monitor);
    }

    @Override
    public void createView(final Viewpoint viewpoint, final Collection<EObject> semantics, final boolean createNewRepresentations, IProgressMonitor monitor) {
        DViewOperations.on(this).createView(viewpoint, semantics, createNewRepresentations, monitor);
    }

    // *******************
    // Session opening and closing
    // *******************

    @Override
    public void open(IProgressMonitor monitor) {
        try {
            monitor.beginTask("Open session", 33);
            SessionManager.INSTANCE.add(this);
            monitor.worked(1);
            notifyListeners(SessionListener.OPENING);
            monitor.worked(1);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_SESSION_KEY);

            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).registerClient(resourcesSynchronizer);
            monitor.worked(1);
            this.representationNameListener = new RepresentationNameListener(this);
            monitor.worked(1);

            tracker.initialize(monitor);
            monitor.worked(1);

            setSynchronizeStatusofEveryResource();
            monitor.worked(1);

            configureInterpreter();
            monitor.worked(1);

            initializeAccessor();
            monitor.worked(1);

            ViewpointRegistry.getInstance().addListener(this.vsmUpdater);
            // Setup ResourceModifiedFieldUpdater
            TransactionalEditingDomain.DefaultOptions options = TransactionUtil.getAdapter(getTransactionalEditingDomain(), TransactionalEditingDomain.DefaultOptions.class);
            if (options != null) {
                Object value = options.getDefaultTransactionOptions().get(Transaction.OPTION_VALIDATE_EDIT);
                ValidateEditSupport delegate = null;
                if (value instanceof ValidateEditSupport) {
                    delegate = (ValidateEditSupport) value;
                }
                if (!(delegate instanceof ResourceModifiedFieldUpdater) && getTransactionalEditingDomain() instanceof InternalTransactionalEditingDomain) {
                    InternalTransactionalEditingDomain internalDomain = (InternalTransactionalEditingDomain) getTransactionalEditingDomain();
                    new ResourceModifiedFieldUpdater(internalDomain, delegate);
                }
            }

            DViewOperations.on(this).updateSelectedViewpointsData(new SubProgressMonitor(monitor, 10));
            initLocalTriggers();

            super.setOpen(true);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_SESSION_KEY);
            notifyListeners(SessionListener.OPENED);
            monitor.worked(1);
        } catch (OperationCanceledException e) {
            super.setOpen(true);
            close(new SubProgressMonitor(monitor, 10));
            throw e;
        } finally {
            monitor.done();
        }
    }

    @Override
    public void close(IProgressMonitor monitor) {
        if (!isOpen()) {
            return;
        }
        ViewpointRegistry.getInstance().removeListener(this.vsmUpdater);
        this.vsmUpdater = null;
        notifyListeners(SessionListener.CLOSING);
        disableAndRemoveECrossReferenceAdapters();

        removeListener(getRefreshEditorsListener());
        refreshEditorsListeners = null;
        reloadingPolicy = null;
        savingPolicy = null;
        if (interpreter != null) {
            interpreter.dispose();
        }
        ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).unregisterClient(resourcesSynchronizer);
        // We do not reset resourcesSynchronizer to null as some of the session
        // methods which are delegated to it must still be availble when the
        // session is closed. It does not hold to any state or resource so
        // that's OK.
        ResourceSetSync.uninstallResourceSetSync(transactionalEditingDomain);
        super.setOpen(false);
        /*
         * Let's clear the cross referencer of the VSM resource if it's still
         * there (added by the updateSelectedViewpointsData).
         */
        ResourceSet resourceSet = getTransactionalEditingDomain().getResourceSet();

        if (currentResourceCollector != null) {
            currentResourceCollector.dispose();
            currentResourceCollector = null;
        }
        interpreter = null;
        representationNameListener.dispose();
        representationNameListener = null;
        representationsChangeAdapter = null;
        // dispose the SessionEventBroker
        if (broker != null) {
            broker.dispose();
            broker = null;
        }
        flushOperations(transactionalEditingDomain);
        // Unload all referenced resources
        unloadAllResources();
        // To remove remaining resource like environment:/viewpoint
        for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
            resource.unload();
            resourceSet.getResources().remove(resource);
        }
        // Notify that the session is closed.
        notifyListeners(SessionListener.CLOSED);
        SessionManager.INSTANCE.remove(this);
        if (tracker != null) {
            tracker.dispose();
            tracker = null;
        }
        crossReferencer = null;
        saver.dispose();

        transactionalEditingDomain.dispose();
        doDisposePermissionAuthority(resourceSet);
        transactionalEditingDomain = null;
        getActivatedViewpoints().clear();
        services = null;
        sessionResource = null;
        mainDAnalysis = null;
    }

    /**
     * Disable {@link SiriusCrossReferenceAdapter} resolveProxy capability on
     * resource and all its contents
     * 
     * @param resource
     *            the resource
     */
    void disableCrossReferencerResolve(Resource resource) {
        // Disable resolveProxy for SiriusCrossreferencerAdapter.
        // SiriusCrossreferencerAdapter on EObject are also on resource,
        // consequently we manage only the resource itself.
        for (Adapter adapter : resource.eAdapters()) {
            if (adapter instanceof SiriusCrossReferenceAdapter) {
                ((SiriusCrossReferenceAdapter) adapter).disableResolveProxy();
            }
        }
    }

    /**
     * Enable {@link SiriusCrossReferenceAdapter} resolveProxy capability on
     * resource and all its contents
     * 
     * @param resource
     *            the resource
     */
    void enableCrossReferencerResolve(Resource resource) {
        // Enable resolveProxy for SiriusCrossreferencerAdapter.
        // SiriusCrossreferencerAdapter on EObject are also on resource,
        // consequently we manage only the resource itself.
        for (Adapter adapter : resource.eAdapters()) {
            if (adapter instanceof SiriusCrossReferenceAdapter) {
                ((SiriusCrossReferenceAdapter) adapter).enableResolveProxy();
            }
        }
    }

    private static void flushOperations(TransactionalEditingDomain ted) {
        CommandStack commandStack = ted.getCommandStack();
        ResourceSet resourceSet = ted.getResourceSet();
        if (commandStack != null) {
            // Remove also ResourceUndoContext to have operations really
            // removed from IOperationHistory
            // This must be done before commandStack.flush(); to have
            // the Undo/RedoActionHandler update the actions
            if (commandStack instanceof IWorkspaceCommandStack) {
                IWorkspaceCommandStack workspaceCommandStack = (IWorkspaceCommandStack) commandStack;
                for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
                    workspaceCommandStack.getOperationHistory().dispose(new ResourceUndoContext(ted, resource), true, true, true);
                }
            }
            commandStack.flush();
        }
    }

    /**
     * Disposes the permission authority corresponding to this session (if any
     * found).
     * 
     * @param resourceSet
     *            the resourceSet associated to the current session (given in
     *            parameter as the EditingDomain may already have been disposed)
     */
    protected void doDisposePermissionAuthority(ResourceSet resourceSet) {
        PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resourceSet).dispose(resourceSet);
    }

    /**
     * Method called at {@link Session#close(IProgressMonitor)} to unload all
     * referenced {@link Resource}s.
     */
    private void unloadAllResources() {
        ResourceSet rs = transactionalEditingDomain.getResourceSet();
        for (Resource resource : getAllSessionResources()) {
            resource.unload();
            rs.getResources().remove(resource);
        }
        for (Resource res : Iterables.concat(super.getResources(), getSemanticResources(), super.getControlledResources())) {
            // Don't try to unload metamodel resources.
            try {
                if (!isFromPackageRegistry(rs, res)) {
                    res.unload();
                }
            } catch (final IllegalStateException e) {
                // we might have an exception unloading a resource already
                // unaccessible
                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while unloading an unaccessible resource:\n" + e.getMessage(), e));
            }
            rs.getResources().remove(res);
        }
        super.getAnalyses().clear();
        super.getResources().clear();
        super.getControlledResources().clear();
    }

    private boolean isFromPackageRegistry(ResourceSet rset, Resource resource) {
        URI uri = resource.getURI();
        return uri != null && rset.getPackageRegistry().getEPackage(uri.toString()) != null;
    }

    // *******************
    // Basic Session services
    // *******************

    @Override
    public TransactionalEditingDomain getTransactionalEditingDomain() {
        return transactionalEditingDomain;
    }

    @Override
    public SessionService getServices() {
        if (services == null) {
            services = new DAnalysisSessionServicesImpl(this);
        }
        return services;
    }

    @Override
    public String getID() {
        return sessionResource.getURI().toString();
    }

    @Override
    public String toString() {
        return "Local Session: " + getID();
    }

    /**
     * Get collection of available {@link DRepresentationContainer} for the
     * {@link RepresentationDescription}.
     * 
     * @param representationDescription
     *            the representation description.
     * @return available representation containers
     */
    public Collection<DRepresentationContainer> getAvailableRepresentationContainers(RepresentationDescription representationDescription) {
        final Viewpoint viewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
        Collection<DAnalysis> allAnalysis = allAnalyses();

        final List<DRepresentationContainer> containers = new ArrayList<DRepresentationContainer>();

        for (DAnalysis analysis : allAnalysis) {
            DRepresentationContainer container = null;

            for (final DView view : analysis.getOwnedViews()) {
                if (view instanceof DRepresentationContainer && viewpoint == view.getViewpoint() && view.eContainer() instanceof DAnalysis) {
                    container = (DRepresentationContainer) view;
                    break;
                }
            } // for

            if (container != null) {
                containers.add(container);
            }
        }

        return containers;
    }
}
