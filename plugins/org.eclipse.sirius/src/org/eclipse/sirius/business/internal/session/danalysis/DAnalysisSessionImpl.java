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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.runtime.CoreException;
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
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.transaction.util.ValidateEditSupport;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
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
import org.eclipse.sirius.business.internal.metamodel.helper.ComponentizationHelper;
import org.eclipse.sirius.business.internal.migration.resource.ResourceFileExtensionPredicate;
import org.eclipse.sirius.business.internal.query.DAnalysisesInternalQuery;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
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
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
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
import org.eclipse.sirius.viewpoint.ViewpointFactory;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

/**
 * A session which store data in {@link DAnalysis} references.
 * 
 * @author cbrun
 */
public class DAnalysisSessionImpl extends DAnalysisSessionEObjectImpl implements Session, DAnalysisSession, ResourceSyncClient {
    /** The custom saving policy the session should use. */
    protected SavingPolicy savingPolicy;

    /** The {@link TransactionalEditingDomain} associated to this Session. */
    private TransactionalEditingDomain transactionalEditingDomain;

    // Session's state and helpers for its maintenance.
    // See also the following fields inherited from DAnalysisSessionEObject:
    // - resources: all the @*.aird@ resources in the session.
    // - controlledResources: all the semantic resources in the session which
    // are not in DASI.semanticResources and which are
    // contained/controled, directly or indirectly, by a semanticResource

    /** The main Session Resource. */
    private Resource sessionResource;

    /** The {@link DAnalysis} of the main session resource (*.aird). */
    private DAnalysis mainDAnalysis;

    /** The semantic resources collection. */
    private Collection<Resource> semanticResources;

    /** The semantic resources collection updater. */
    private SemanticResourcesUpdater semanticResourcesUpdater;

    private ControlledResourcesDetector controlledResourcesDetector;

    private DAnalysisRefresher dAnalysisRefresher;

    // Session's configuration

    private final Saver saver = new Saver(this);

    private ReloadingPolicy reloadingPolicy;

    private boolean disposeEditingDomainOnClose = true;

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

        this.interpreter = new ODesignGenericInterpreter();
        this.representationsChangeAdapter = new RepresentationsChangeAdapter(this);
        this.controlledResourcesDetector = new ControlledResourcesDetector(this);
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
    private void configureInterpreter() {
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
        // Disable resolution of proxy for AirDCrossReferenceAdapter of
        // session and for semanticCrossReferencer during the closing
        Adapter existingAirDCrossReferenceAdapter = EcoreUtil.getExistingAdapter(resourceSet, AirDCrossReferenceAdapter.class);
        AirDCrossReferenceAdapter airDCrossReferenceAdapter = null;
        if (existingAirDCrossReferenceAdapter instanceof AirDCrossReferenceAdapter) {
            airDCrossReferenceAdapter = (AirDCrossReferenceAdapter) existingAirDCrossReferenceAdapter;
            airDCrossReferenceAdapter.disableResolve();
            resourceSet.eAdapters().remove(airDCrossReferenceAdapter);
        }
        if (getSemanticCrossReferencer() instanceof LazyCrossReferencer) {
            ((LazyCrossReferencer) getSemanticCrossReferencer()).disableResolve();
        }
        // Let's clear the cross referencer if it's still there.
        for (final Resource res : getSemanticResources()) {
            unregisterResourceInCrossReferencer(res);
        }
        for (final DAnalysis analysis : Iterables.filter(allAnalyses(), Predicates.notNull())) {
            removeAdaptersOnAnalysis(analysis);
            Resource analysisResource = analysis.eResource();
            if (analysisResource != null) {
                unregisterResourceInCrossReferencer(analysisResource);
            }
        }
        Iterable<Resource> resources = Lists.newArrayList(resourceSet.getResources());
        for (Resource resource : Iterables.filter(resources, new ResourceFileExtensionPredicate(SiriusUtil.DESCRIPTION_MODEL_EXTENSION, false))) {
            unregisterResourceInCrossReferencer(resource);
        }
    }

    /**
     * Enable all ECrossReferencerAdapter adapters before the end of closing.
     */
    protected void reenableECrossReferenceAdaptersBeforeEndOfClosing() {
        ResourceSet resourceSet = getTransactionalEditingDomain().getResourceSet();
        Adapter existingAirDCrossReferenceAdapter = EcoreUtil.getExistingAdapter(resourceSet, AirDCrossReferenceAdapter.class);
        if (existingAirDCrossReferenceAdapter instanceof AirDCrossReferenceAdapter) {
            ((AirDCrossReferenceAdapter) existingAirDCrossReferenceAdapter).enableResolve();
        }
        if (getSemanticCrossReferencer() instanceof LazyCrossReferencer) {
            ((LazyCrossReferencer) getSemanticCrossReferencer()).enableResolve();
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
        updateSelectedViewpointsData(new NullProgressMonitor());
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
            for (DAnalysis referencer : referencers) {
                referencer.getReferencedAnalysis().add(newAnalysis);
            }
            addAdaptersOnAnalysis(newAnalysis);
            registerResourceInCrossReferencer(newAnalysis.eResource());
            for (DAnalysis dAnalysis : new DAnalysisQuery(newAnalysis).getAllReferencedAnalyses()) {
                addAdaptersOnAnalysis(dAnalysis);
                registerResourceInCrossReferencer(dAnalysis.eResource());
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
        return new DAnalysisesInternalQuery(super.getAnalyses()).getAllAnalyses();
    }

    @Override
    public void addAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.registerAnalysis(analysis);
        }
        if (semanticResourcesUpdater != null && !analysis.eAdapters().contains(semanticResourcesUpdater)) {
            analysis.eAdapters().add(semanticResourcesUpdater);
        }
    }

    @Override
    public void removeAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.unregisterAnalysis(analysis);
        }
        if (semanticResourcesUpdater != null && analysis.eAdapters().contains(semanticResourcesUpdater)) {
            analysis.eAdapters().remove(semanticResourcesUpdater);
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
        DView receiver = findViewForRepresentation(representation, newContainer);
        if (receiver == null) {
            final IPermissionAuthority analysisAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(newContainer);
            if (analysisAuthority.canCreateIn(newContainer)) {
                createView(getViewpoint(representation), Lists.newArrayList(semantic), false, pm);
                receiver = findViewForRepresentation(representation, newContainer);
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
        transferCustomData(this, representation);
    }

    /**
     * Transfers the custom data of the origin session for the given associated
     * instance into this session and for the same instance.
     * 
     * @param origin
     *            the original session.
     * @param associatedInstance
     *            the associated instance.
     */
    private void transferCustomData(final Session origin, final EObject associatedInstance) {
        // Diagram should be move.
        final Collection<EObject> diagrams = origin.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, associatedInstance);
        for (final EObject object : diagrams) {
            this.getServices().putCustomData(CustomDataConstants.GMF_DIAGRAMS, associatedInstance, object);
        }
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
            final EObject root = newResource.getContents().get(0);
            for (final DAnalysis analysis : this.allAnalyses()) {
                analysis.getModels().add(root);
            }
        }
        registerResourceInCrossReferencer(newResource);
    }

    private void notifyNewMetamodels(final Resource newResource) {
        if (Boolean.valueOf(System.getProperty("org.eclipse.sirius.enableUnsafeOptimisations", "false"))) {
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

    @Override
    public Collection<Resource> getSemanticResources() {
        if (semanticResources == null) {
            semanticResources = new CopyOnWriteArrayList<Resource>();
            semanticResourcesUpdater = new SemanticResourcesUpdater(this, semanticResources);
            RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new SemanticResourceGetter(this);
            try {
                TransactionUtil.runExclusive(getTransactionalEditingDomain(), semanticResourcesGetter);
            } catch (InterruptedException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while accessing semantic resources"));
            }
            ((CopyOnWriteArrayList<Resource>) semanticResources).addAllAbsent(semanticResourcesGetter.getResult());
        }
        return Collections.unmodifiableCollection(semanticResources);
    }

    @Override
    public void removeSemanticResource(Resource semanticResource, IProgressMonitor monitor) {
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        for (final Resource res : collectAllReferencingResources(semanticResource)) {
            doRemoveSemanticResource(res, resourceSet);
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
        if (res.getContents().size() > 0) {
            final EObject root = res.getContents().get(0);
            for (final DAnalysis analysis : this.allAnalyses()) {
                analysis.getModels().remove(root);
            }
        }
        unregisterResourceInCrossReferencer(res);
        if (couldBeUnload(set, res)) {
            res.unload();
        }
        set.getResources().remove(res);
    }

    void discoverAutomaticallyLoadedSemanticResources(List<Resource> allResources) {
        // Add the unknown resources to the semantic resources of this
        // session.
        if (dAnalysisRefresher != null) {
            dAnalysisRefresher.addAutomaticallyLoadedResourcesToSemanticResources(allResources);
        }
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
     * @return the result of the save operation.
     */
    protected IStatus doSave(final Map<?, ?> options, final IProgressMonitor monitor, boolean runExclusive) {
        IStatus status = Status.OK_STATUS;

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
                    try {
                        Collection<Resource> savedResources = getSavingPolicy().save(allResources, options, new SubProgressMonitor(monitor, 7));
                        setResult(savedResources);
                        setStatus(Status.OK_STATUS);
                        // CHECKSTYLE:OFF
                    } catch (Throwable e) {
                        // CHECKSTYLE:ON
                        Status status = new Status(IStatus.ERROR, SiriusPlugin.ID, "Error while saving the session", e);
                        setStatus(status);
                        SiriusPlugin.getDefault().error("Save failed", new CoreException(status));
                    }
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
            if (savedResources == null) {
                // If the savedResources list is null, something went wrong and
                // has already been logged.
                status = save.getStatus();
            } else {
                boolean semanticSave = false;
                for (final Resource resource : savedResources) {
                    if (semanticResourcesCollection.contains(resource) || getControlledResources().contains(resource)) {
                        semanticSave = true;
                    }
                }
                if (semanticSave) {
                    notifyListeners(SessionListener.SEMANTIC_CHANGE);
                }
                monitor.worked(1);
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

        return status;
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
        return Iterables.any(resources, new ResourceSyncClientNotificationFilter(resource));
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

    /**
     * Configure this session so that when it is closed, it disposes the
     * associated editing domain or not. Normally, each session has its own
     * editing domain, which is disposed when the session is closed, but the
     * historical behavior was to share the same editing domain for all session.
     * Applications which have not been updated and still used shared editing
     * domains should set this flag to <code>false</code> to avoid problems.
     * 
     * @param disposeOnClose
     *            whether or not the editing domain used by this session should
     *            be disposed when the session is closed.
     */
    public void setDisposeEditingDomainOnClose(boolean disposeOnClose) {
        this.disposeEditingDomainOnClose = disposeOnClose;
    }

    /**
     * Tests whether this session will dispose its editing domain when it is
     * closed.
     * 
     * @return <code>true</code> if this session will dispose its editing domain
     *         when closed.
     */
    public boolean getDisposeEditingDomainOnClose() {
        return disposeEditingDomainOnClose;
    }

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
        SortedSet<Viewpoint> result = new TreeSet<Viewpoint>(new ViewpointRegistry.ViewpointComparator());
        Collection<DAnalysis> scope = includeReferencedAnalysis ? allAnalyses() : Collections.singleton(mainDAnalysis);
        for (DView view : getSelectedViews(scope)) {
            Viewpoint viewpoint = view.getViewpoint();
            if (viewpoint != null && !viewpoint.eIsProxy()) {
                result.add(viewpoint);
            }
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get the selected viewpoints sorted form more specifis to generics.
     * 
     * @return a collection of selected viewpoints for this session.
     */
    public Collection<Viewpoint> getSelectedViewpointsSpecificToGeneric() {
        Collection<Viewpoint> viewpoints = getSelectedViewpoints(false);
        // Then orders specific to generic
        final List<Viewpoint> orderedViewpoints = new ArrayList<Viewpoint>(viewpoints.size());
        for (final Viewpoint viewpoint : viewpoints) {
            int insertPosition = orderedViewpoints.size();
            for (final Viewpoint viewpoint2 : orderedViewpoints) {
                if (ComponentizationHelper.isExtendedBy(viewpoint, viewpoint2)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2);
                } else if (ComponentizationHelper.isExtendedBy(viewpoint2, viewpoint)) {
                    insertPosition = orderedViewpoints.indexOf(viewpoint2) + 1;
                }
            }
            orderedViewpoints.add(insertPosition, viewpoint);
        }
        return Collections.unmodifiableCollection(orderedViewpoints);
    }

    @Override
    public Collection<DView> getSelectedViews() {
        return getSelectedViews(allAnalyses());
    }

    private Collection<DView> getSelectedViews(Iterable<DAnalysis> analyses) {
        Collection<DView> selectedViews = new HashSet<DView>();
        for (DAnalysis analysis : analyses) {
            selectedViews.addAll(analysis.getSelectedViews());
        }
        return selectedViews;
    }

    @Override
    public void addSelectedView(DView view, IProgressMonitor monitor) throws IllegalArgumentException {
        try {
            monitor.beginTask("View selection", 3);
            DAnalysis foundAnalysis = null;
            for (final DAnalysis dAnalysis : allAnalyses()) {
                if (dAnalysis.getOwnedViews().contains(view)) {
                    foundAnalysis = dAnalysis;
                    break;
                }
            }
            if (foundAnalysis == null) {
                throw new IllegalArgumentException("The view is not contained in the analysis");
            }
            foundAnalysis.getSelectedViews().add(view);
            monitor.worked(1);
            updateSelectedViewpointsData(new NullProgressMonitor());
            monitor.worked(1);
            notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            monitor.worked(1);
            configureInterpreter();
        } finally {
            monitor.done();
        }
    }

    @Override
    public void removeSelectedView(final DView view, IProgressMonitor monitor) {
        try {
            monitor.beginTask("View unselection", 1);
            mainDAnalysis.getSelectedViews().remove(view);
            updateSelectedViewpointsData(new SubProgressMonitor(monitor, 1));
            notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            configureInterpreter();
        } finally {
            monitor.done();
        }
    }

    @Override
    public Collection<DView> getOwnedViews() {
        Collection<DView> ownedViews = new HashSet<DView>();
        for (DAnalysis analysis : allAnalyses()) {
            ownedViews.addAll(analysis.getOwnedViews());
        }
        return ownedViews;
    }

    @Override
    public void createView(Viewpoint viewpoint, Collection<EObject> semantics, IProgressMonitor monitor) {
        createView(viewpoint, semantics, true, monitor);
    }

    @Override
    public void createView(final Viewpoint viewpoint, final Collection<EObject> semantics, final boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask("View creation for Sirius : " + viewpoint.getName(), 3 + 10 * semantics.size());
            Set<DView> intializedDViews = new LinkedHashSet<DView>();
            for (DAnalysis analysis : allAnalyses()) {
                if (!hasAlreadyDViewForViewpoint(analysis, viewpoint)) {
                    DView view = getOrCreateFreshDView(analysis);
                    view.setViewpoint(viewpoint);
                    view.setInitialized(true);
                    analysis.getOwnedViews().add(view);
                    analysis.getSelectedViews().add(view);
                    intializedDViews.add(view);
                }
            }
            monitor.worked(1);

            /* need to prepare for the init */
            configureInterpreter();
            if (createNewRepresentations) {
                monitor.subTask("Initialize representations");
                for (final EObject semantic : semantics) {
                    DialectManager.INSTANCE.initRepresentations(viewpoint, semantic, new SubProgressMonitor(monitor, 10));
                }
            }
            updateSelectedViewpointsData(new SubProgressMonitor(monitor, 1));
            monitor.worked(1);
            /* DVIew created are automatically selected */
            if (!intializedDViews.isEmpty()) {
                notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            }
            monitor.worked(1);
        } finally {
            monitor.done();
        }
    }

    private static DView getOrCreateFreshDView(DAnalysis analysis) {
        DView orphan = new DAnalysisQuery(analysis).getFirstOrphanDView();
        if (orphan != null) {
            return orphan;
        } else {
            return ViewpointFactory.eINSTANCE.createDRepresentationContainer();
        }
    }

    private boolean hasAlreadyDViewForViewpoint(DAnalysis dAnalysis, Viewpoint viewpoint) {
        for (DView ownedDView : dAnalysis.getOwnedViews()) {
            Viewpoint vp = ownedDView.getViewpoint();
            if (vp != null && EqualityHelper.areEquals(vp, viewpoint)) {
                return true;
            }
        }
        return false;
    }

    private void updateSelectedViewpointsData(IProgressMonitor monitor) {
        try {
            Set<Viewpoint> selectedViewpoints = Sets.newLinkedHashSet();
            for (Viewpoint viewpoint : getSelectedViewpoints(false)) {
                if (viewpoint.eResource() != null) {
                    selectedViewpoints.add(SiriusResourceHelper.getCorrespondingViewpoint(this, viewpoint));
                } else {
                    selectedViewpoints.add(viewpoint);
                }
            }
            SetView<Viewpoint> difference = Sets.difference(Sets.newHashSet(getActivatedViewpoints()), Sets.newHashSet(selectedViewpoints));
            monitor.beginTask("Update selected Viewpoints data", selectedViewpoints.size() + difference.size() + 1);
            // FIXME : it is useful?
            for (Viewpoint viewpoint : selectedViewpoints) {
                Resource viewpointResource = viewpoint.eResource();
                if (viewpointResource != null) {
                    registerResourceInCrossReferencer(viewpointResource);
                }
                monitor.worked(1);
            }
            for (Viewpoint viewpoint : difference) {
                Resource viewpointResource = viewpoint.eResource();
                if (viewpointResource != null) {
                    unregisterResourceInCrossReferencer(viewpointResource);
                }
                monitor.worked(1);
            }
            super.getActivatedViewpoints().addAll(selectedViewpoints);
            super.getActivatedViewpoints().retainAll(selectedViewpoints);
            // tell the accessor and the interpreter which metamodels we know
            // of.
            final ModelAccessor accessor = getModelAccessor();
            Collection<MetamodelDescriptor> metamodels = MetamodelDescriptorManager.INSTANCE.provides(this.getSelectedViewpoints(false));
            if (accessor != null) {
                accessor.activateMetamodels(metamodels);
            }
            if (getInterpreter() != null) {
                getInterpreter().activateMetamodels(metamodels);
            }
        } finally {
            monitor.done();
        }
    }

    /**
     * Returns a view that can receive the given representation or
     * <code>null</code> if no view is found.
     * 
     * @param representation
     *            the representation.
     * @param analysis
     *            the owner.
     * @return a view that can receive the given representation or
     *         <code>null</code> if no view is found.
     */
    private DView findViewForRepresentation(final DRepresentation representation, final DAnalysis analysis) {
        final Viewpoint vp = getViewpoint(representation);
        for (final DView view : analysis.getOwnedViews()) {
            if (view.getViewpoint() != null && EqualityHelper.areEquals(view.getViewpoint(), vp)) {
                return view;
            }
        }
        return null;
    }

    /**
     * Returns the viewpoint of the given representation.
     * 
     * @param representation
     *            the representation.
     * @return the viewpoint of the given representation.
     */
    private Viewpoint getViewpoint(final DRepresentation representation) {
        return ((DView) representation.eContainer()).getViewpoint();
    }

    // *******************
    // Session opening and closing
    // *******************

    @Override
    public void open(IProgressMonitor monitor) {
        try {
            monitor.beginTask("Open session", 33);
            if (!SessionManager.INSTANCE.getSessions().contains(this)) {
                SessionManager.INSTANCE.add(this);
            }
            monitor.worked(1);
            notifyListeners(SessionListener.OPENING);
            monitor.worked(1);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_SESSION_KEY);
            dAnalysisRefresher = new DAnalysisRefresher(this);

            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).registerClient(resourcesSynchronizer);
            monitor.worked(1);
            this.representationNameListener = new RepresentationNameListener(this);
            monitor.worked(1);
            saver.initialize();

            final Collection<DAnalysis> allAnalyses = allAnalyses();
            if (allAnalyses.isEmpty()) {
                throw new RuntimeException("A analysis session could not be opened without at least a valid analyis");
            }
            /*
             * Resolves all models needed by the session because GMF installs a
             * CrossReferencerAdapter that resolves the resource set.
             */
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.RESOLVE_ALL_KEY);
            // First resolve all VSM resources used for Sirius to ignore VSM
            // resources and VSM linked resources (as viewpoint:/environment
            // resource) as new semantic element
            dAnalysisRefresher.resolveAllVSMResources(allAnalyses);
            // Then resolve all resources (to automatically add new semantic
            // resources)
            List<Resource> resourcesBeforeLoadOfSession = Lists.newArrayList(getTransactionalEditingDomain().getResourceSet().getResources());
            dAnalysisRefresher.forceLoadingOfEveryLinkedResource();
            monitor.worked(10);

            // Add the unknown resources to the semantic resources of this
            // session.
            dAnalysisRefresher.addAutomaticallyLoadedResourcesToSemanticResources(resourcesBeforeLoadOfSession);
            monitor.worked(1);
            setSynchronizeStatusofEveryResource();
            monitor.worked(1);

            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.RESOLVE_ALL_KEY);
            // Look for controlled resources after load of every linked
            // resources.
            handlePossibleControlledResources();
            monitor.worked(1);
            dAnalysisRefresher.init();
            monitor.worked(1);
            if (!getSemanticResources().isEmpty()) {
                configureInterpreter();
            }
            monitor.worked(1);
            initializeAccessor();
            monitor.worked(1);
            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain);
            monitor.worked(1);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_SESSION_KEY);

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

            super.setOpen(true);
            notifyListeners(SessionListener.OPENED);
            monitor.worked(1);
            updateSelectedViewpointsData(new SubProgressMonitor(monitor, 10));
            initLocalTriggers();

            getTransactionalEditingDomain().addResourceSetListener(saver);
        } catch (OperationCanceledException e) {
            close(new SubProgressMonitor(monitor, 10));
            throw e;
        } finally {
            monitor.done();
        }
    }

    private void handlePossibleControlledResources() {
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

    @Override
    public void close(IProgressMonitor monitor) {
        if (!isOpen()) {
            return;
        }
        if (saver != null && getTransactionalEditingDomain() != null) {
            getTransactionalEditingDomain().removeResourceSetListener(saver);
        }
        ViewpointRegistry.getInstance().removeListener(this.vsmUpdater);
        this.vsmUpdater = null;
        notifyListeners(SessionListener.CLOSING);
        disableAndRemoveECrossReferenceAdapters();

        if (controlledResourcesDetector != null) {
            controlledResourcesDetector.dispose();
            controlledResourcesDetector = null;
        }
        if (dAnalysisRefresher != null) {
            dAnalysisRefresher.dispose();
            dAnalysisRefresher = null;
        }
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
        unloadResource();
        if (disposeEditingDomainOnClose) {
            // To remove remaining resource like environment:/viewpoint
            for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
                resource.unload();
                resourceSet.getResources().remove(resource);
            }
        }
        // Notify that the session is closed.
        notifyListeners(SessionListener.CLOSED);
        SessionManager.INSTANCE.remove(this);
        if (semanticResourcesUpdater != null) {
            semanticResourcesUpdater.dispose();
            semanticResourcesUpdater = null;
        }
        if (semanticResources != null) {
            semanticResources.clear();
        }
        reenableECrossReferenceAdaptersBeforeEndOfClosing();
        crossReferencer = null;
        saver.dispose();

        if (disposeEditingDomainOnClose) {
            transactionalEditingDomain.dispose();
            doDisposePermissionAuthority(resourceSet);
            transactionalEditingDomain = null;
        }
        getActivatedViewpoints().clear();
        services = null;
        sessionResource = null;
        mainDAnalysis = null;
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
    private void unloadResource() {
        for (final DAnalysis analysis : Iterables.filter(allAnalyses(), Predicates.notNull())) {
            final Resource resource = analysis.eResource();
            if (resource != null) {
                if (resource.getResourceSet() != null) {
                    resource.unload();
                }
                transactionalEditingDomain.getResourceSet().getResources().remove(resource);
            }
        }
        /*
         * We need to remove the remaining resources. For instance if a resource
         * was unload, DAnalysis do not refer anymore the resource.
         */
        for (final Resource resource : super.getResources()) {
            if (couldBeUnload(transactionalEditingDomain.getResourceSet(), resource)) {
                resource.unload();
            }
            transactionalEditingDomain.getResourceSet().getResources().remove(resource);
        }
        for (final Resource res : getSemanticResources()) {
            if (res.getResourceSet() != null) {
                if (couldBeUnload(res.getResourceSet(), res)) {
                    res.unload();
                }
            }
            transactionalEditingDomain.getResourceSet().getResources().remove(res);
        }
        for (final Resource res : super.getControlledResources()) {
            if (res.getResourceSet() != null) {
                if (couldBeUnload(res.getResourceSet(), res)) {
                    res.unload();
                }
            }
            transactionalEditingDomain.getResourceSet().getResources().remove(res);
        }
        super.getAnalyses().clear();
        super.getResources().clear();
        super.getControlledResources().clear();
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
            services = new DAnalysisSessionServicesImpl(super.getAnalyses());
        }
        return services;
    }

    @Override
    public String getID() {
        String id = Session.INVALID_SESSION;
        final StringBuilder builder = new StringBuilder();
        final Collection<DAnalysis> allAnalyses = allAnalyses();
        if (!allAnalyses.isEmpty()) {
            for (final DAnalysis analysis : allAnalyses) {
                Resource analysisResource = analysis.eResource();
                if (analysisResource != null && analysisResource.getURI() != null) {
                    builder.append(analysisResource.getURI().toString()).append(' ');
                } else {
                    return Session.INVALID_SESSION;
                }
            }
            id = builder.toString();
        }
        return id;
    }

    @Override
    public String toString() {
        String prefix = "Local Session: ";
        final StringBuilder builder = new StringBuilder();
        for (final DAnalysis analysis : allAnalyses()) {
            final Resource resource = analysis.eResource();
            if (resource != null && resource.getURI() != null) {
                URI uri = resource.getURI();
                if (new URIQuery(uri).isInMemoryURI()) {
                    prefix = "Transient Session: ";
                }
                if (uri.segments().length > 0) {
                    builder.append(URI.decode(uri.lastSegment())).append("  ");
                } else {
                    builder.append(uri.opaquePart()).append(" ");
                }
            }
        }
        // Remove the last two spaces if needed
        builder.insert(0, prefix);
        if (builder.length() > 2 && "  ".equals(builder.substring(builder.length() - 2, builder.length()))) {
            builder.delete(builder.length() - 2, builder.length());
        }
        return builder.toString();
    }

    /*
     * unload only if resource has not an http scheme => this prevent special
     * resources such as http://www.eclipse.org/EMF/2002 to be unloaded
     */
    private boolean couldBeUnload(ResourceSet rset, Resource resource) {
        return resource.getURI() != null && rset.getPackageRegistry().getEPackage(resource.getURI().toString()) == null;
    }
}
