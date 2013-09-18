// CHECKSTYLE:OFF
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

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.emf.workspace.ResourceUndoContext;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.LazyCrossReferencer;
import org.eclipse.sirius.common.tools.api.util.Option;
import org.eclipse.sirius.DAnalysis;
import org.eclipse.sirius.DRepresentation;
import org.eclipse.sirius.DRepresentationContainer;
import org.eclipse.sirius.DSemanticDecorator;
import org.eclipse.sirius.DView;
import org.eclipse.sirius.MetaModelExtension;
import org.eclipse.sirius.SyncStatus;
import org.eclipse.sirius.SiriusFactory;
import org.eclipse.sirius.SiriusPackage;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.componentization.SiriusRegistry;
import org.eclipse.sirius.business.api.componentization.SiriusRegistryListener2;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.extender.MetamodelDescriptorManager;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.helper.SiriusResourceHelper;
import org.eclipse.sirius.business.api.query.AirDResouceQuery;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.business.api.session.ReloadingPolicy;
import org.eclipse.sirius.business.api.session.ReloadingPolicy.Action;
import org.eclipse.sirius.business.api.session.SavingPolicy;
import org.eclipse.sirius.business.api.session.SavingPolicyImpl;
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
import org.eclipse.sirius.business.api.session.resource.AirdResource;
import org.eclipse.sirius.business.internal.helper.display.VisibilityPropagatorAdapter;
import org.eclipse.sirius.business.internal.metamodel.helper.ComponentizationHelper;
import org.eclipse.sirius.business.internal.migration.resource.ResourceFileExtensionPredicate;
import org.eclipse.sirius.business.internal.movida.Movida;
import org.eclipse.sirius.business.internal.movida.registry.SiriusRegistryListener;
import org.eclipse.sirius.business.internal.query.DAnalysisesInternalQuery;
import org.eclipse.sirius.business.internal.resource.AirDCrossReferenceAdapter;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.business.internal.session.RepresentationNameListener;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.description.MetamodelExtensionSetting;
import org.eclipse.sirius.description.Sirius;
import org.eclipse.sirius.impl.DAnalysisSessionEObjectImpl;
import org.eclipse.sirius.tools.api.command.semantic.RemoveSemanticResourceCommand;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.tools.internal.interpreter.ODesignGenericInterpreter;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.MetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.exception.IllegalURIException;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;

/**
 * A session which store data in {@link DAnalysis} references.
 * 
 * @author cbrun
 */
// CHECKSTYLE:OFF
public class DAnalysisSessionImpl extends DAnalysisSessionEObjectImpl implements Session, DAnalysisSession, ResourceSyncClient, SiriusRegistryListener, SiriusRegistryListener2 {

    /** The {@link TransactionalEditingDomain} associated to this Session. */
    protected final TransactionalEditingDomain transactionalEditingDomain;

    // Session's state and helpers for its maintenance.
    // See also the following fields inherited from DAnalysisSessionEObject:
    // - resources: all the @*.aird@ resources in the session.
    // - controlledResources: all the semantic resources in the session which
    // are not in DASI.semanticResources and which are
    // contained/controled, directly or indirectly, by a semanticResource

    /** The main Session Resource. */
    protected Resource sessionResource;

    /** The {@link DAnalysis} of the main session resource (*.aird). */
    private DAnalysis mainDAnalysis;

    /** The semantic resources collection. */
    protected Collection<Resource> semanticResources;

    /** The semantic resources collection updater. */
    protected SemanticResourcesUpdater semanticResourcesUpdater;

    private final ControlledResourcesDetector controlledResourcesDetector;

    private DAnalysisRefresher dAnalysisRefresher;

    // Session's configuration

    /** The custom saving policy the session should use. */
    protected SavingPolicy savingPolicy;

    private ReloadingPolicy reloadingPolicy;

    private boolean disposeEditingDomainOnClose = true;

    private MovidaSupport movidaSupport = new MovidaSupport(this);

    private IResourceCollector currentResourceCollector;

    // Generic services offered by the session

    /**
     * The event broker suitable for identifying local or remote atomic changes.
     */
    protected SessionEventBroker broker;

    private SessionService services;

    private ECrossReferenceAdapter crossReferencer;

    private IInterpreter interpreter;

    private final ListenerList listeners = new ListenerList();

    private int lastNotification = -1;

    // Default listeners

    /** The listener suitable for refresh the opened viewpoint editors. */
    protected RefreshEditorsPrecommitListener refreshEditorsListeners;

    private VisibilityPropagatorAdapter visibilityPropagator;

    private final RepresentationsChangeAdapter representationsChangeAdapter;

    private final ResourceSetListener representationNameListener;

    /**
     * Create a new session.
     * 
     * @param mainDAnalysis
     *            the analysis keeping the session data.
     */
    public DAnalysisSessionImpl(DAnalysis mainDAnalysis) {
        Preconditions.checkNotNull(mainDAnalysis);
        Preconditions.checkNotNull(mainDAnalysis.eResource(), "A session must be inside a resource.");
        this.transactionalEditingDomain = Preconditions.checkNotNull(TransactionUtil.getEditingDomain(mainDAnalysis), "A session must be associated to an EditingDomain");
        this.mainDAnalysis = mainDAnalysis;
        this.sessionResource = mainDAnalysis.eResource();
        this.interpreter = new ODesignGenericInterpreter();
        this.visibilityPropagator = new VisibilityPropagatorAdapter(this);
        this.representationsChangeAdapter = new RepresentationsChangeAdapter(this);
        this.representationNameListener = new RepresentationNameListener();
        this.controlledResourcesDetector = new ControlledResourcesDetector(this);
        super.getAnalyses().add(mainDAnalysis);
        super.getResources().add(sessionResource);
        for (DAnalysis analysis : allAnalyses()) {
            addAdaptersOnAnalysis(analysis);
        }
        setAnalysisSelector(DAnalysisSelectorService.getSelector(this));
        setResourceCollector(new LocalResourceCollector(getTransactionalEditingDomain().getResourceSet()));
    }

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

    /**
     * {@inheritDoc}
     */
    public TransactionalEditingDomain getTransactionalEditingDomain() {
        return transactionalEditingDomain;
    }

    /**
     * {@inheritDoc}
     */
    public void addAnalysis(Resource analysisResource) {
        Preconditions.checkArgument(analysisResource.getContents().get(0) instanceof DAnalysis);
        DAnalysis analysis = (DAnalysis) analysisResource.getContents().get(0);
        super.getResources().add(analysisResource);
        super.getAnalyses().add(analysis);
        registerResourceInCrossReferencer(analysisResource);
        addAdaptersOnAnalysis(analysis);
        notifyListeners(SessionListener.REPRESENTATION_CHANGE);
        updateSelectedSiriussData(new NullProgressMonitor());
        initInterpreter();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    public void addReferencedAnalysis(final DAnalysis newAnalysis, final Collection<DAnalysis> referencers) {
        if (referencers != null && !referencers.isEmpty()) {
            for (DAnalysis referencer : referencers) {
                referencer.getReferencedAnalysis().add(newAnalysis);
            }
            addAdaptersOnAnalysis(newAnalysis);
            registerResourceInCrossReferencer(newAnalysis.eResource());
            notifyListeners(SessionListener.REPRESENTATION_CHANGE);
        } else {
            throw new IllegalStateException("Cant add a referenced analysis if no parent analysis exists");
        }
    }

    /**
     * {@inheritDoc}
     */
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
    Collection<DAnalysis> allAnalyses() {
        return new DAnalysisesInternalQuery(super.getAnalyses()).getAllAnalyses();
    }

    /*
     * unload only if resource has not an http scheme => this prevent special
     * resources such as http://www.eclipse.org/EMF/2002 to be unloaded
     */
    private boolean couldBeUnload(ResourceSet rset, Resource resource) {
        return resource.getURI() != null && rset.getPackageRegistry().getEPackage(resource.getURI().toString()) == null;
    }

    /**
     * {@inheritDoc}
     */
    public final void open() {
        open(new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
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

            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).registerClient(this);
            monitor.worked(1);
            transactionalEditingDomain.addResourceSetListener(representationNameListener);
            monitor.worked(1);

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
                this.interpreter = new ODesignGenericInterpreter();
                initInterpreter();
            }
            monitor.worked(1);
            /* Set File property */
            // setFilesPropertyToIntepreters();
            // TODO initialize authority...
            initializeAccessor();
            monitor.worked(1);
            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain);
            monitor.worked(1);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_SESSION_KEY);

            if (Movida.isEnabled()) {
                org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry registry = (org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry) SiriusRegistry.getInstance();
                registry.addListener((SiriusRegistryListener) this);
            } else {
                SiriusRegistry.getInstance().addListener(this);
            }
            super.setOpen(true);
            notifyListeners(SessionListener.OPENED);
            monitor.worked(1);
            updateSelectedSiriussData(new SubProgressMonitor(monitor, 10));
            addRefreshEditorsListener();
        } catch (OperationCanceledException e) {
            close(new SubProgressMonitor(monitor, 10));
            throw e;
        } finally {
            monitor.done();
        }
    }

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
        Collection<ResourceStatusChange> changes = Lists.newArrayList();
        for (Resource resource : Sets.newHashSet(resourcesToConsider)) {
            ResourceStatus oldStatus = ResourceSetSync.getStatus(resource);
            ResourceStatus newStatus = resource.isModified() ? ResourceStatus.CHANGED : ResourceStatus.SYNC;
            changes.add(new ResourceStatusChange(resource, newStatus, oldStatus));
        }
        rsSetSync.statusesChanged(changes);
    }

    private void initializeAccessor() {
        try {
            final ModelAccessor accessor = getModelAccessor();
            if (accessor != null) {
                accessor.init(transactionalEditingDomain.getResourceSet());
            }
        } catch (final IllegalURIException e) {
            // The blocked state is on when the user needs more permissions for
            // a given ecore URI.
            super.setBlocked(true);
        }
    }

    /**
     * Add all the representation description files to the interpreter
     */
    private void setFilesPropertyToIntepreters() {
        // Calculate paths of the activated representation description files
        final List<String> filePaths = new ArrayList<String>();
        for (final Sirius vp : getSelectedSiriussSpecificToGeneric()) {
            if (vp.eResource() != null) {
                filePaths.add(vp.eResource().getURI().toPlatformString(true));
            }
        }
        this.interpreter.setProperty(IInterpreter.FILES, filePaths);
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

    private void initInterpreter() {
        // Reset all the odesign files of the interpreter by setting NULL
        this.interpreter.setProperty(IInterpreter.FILES, null);
        setFilesPropertyToIntepreters();
        final EObject eObject = getSemanticResources().iterator().next().getContents().get(0);
        Session session = SessionManager.INSTANCE.getSession(eObject);
        InterpreterRegistry.prepareImportsFromSession(this.interpreter, session);
        this.interpreter.setCrossReferencer(getSemanticCrossReferencer());
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Sirius> getSelectedSiriuss() {
        return getSelectedSiriuss(true);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Sirius> getSelectedSiriuss(boolean includeReferencedAnalysis) {
        final SortedSet<Sirius> result = new TreeSet<Sirius>(new SiriusRegistry.SiriusComparator());
        if (includeReferencedAnalysis) {
            if (!super.isBlocked()) {
                final Collection<DView> selectedViews = getSelectedViews();
                for (final DView view : selectedViews) {
                    final Sirius viewpoint = view.getSirius();
                    if (viewpoint != null) {
                        result.add(viewpoint);
                    }
                }
            }
        } else {
            if (!super.isBlocked()) {
                for (final DView dView : mainDAnalysis.getSelectedViews()) {
                    Sirius viewpoint = dView.getSirius();
                    if (viewpoint != null && !viewpoint.eIsProxy()) {
                        result.add(viewpoint);
                    }
                }
            }
        }
        return Collections.unmodifiableSet(result);
    }

    /**
     * Get the selected viewpoints sorted form more specifis to generics.
     * 
     * @return a collection of selected viewpoints for this session.
     */
    @Deprecated
    public Collection<Sirius> getSelectedSiriussSpecificToGeneric() {
        // Sort the selected viewpoints by alphabetic order
        final SortedSet<Sirius> viewpoints = new TreeSet<Sirius>(new SiriusRegistry.SiriusComparator());
        viewpoints.addAll(getSelectedSiriuss(false));
        // Then orders specific to generic
        final List<Sirius> orderedSiriuss = new ArrayList<Sirius>(viewpoints.size());
        for (final Sirius viewpoint : viewpoints) {
            int insertPosition = orderedSiriuss.size();
            for (final Sirius viewpoint2 : orderedSiriuss) {
                if (ComponentizationHelper.isExtendedBy(viewpoint, viewpoint2)) {
                    insertPosition = orderedSiriuss.indexOf(viewpoint2);
                } else if (ComponentizationHelper.isExtendedBy(viewpoint2, viewpoint)) {
                    insertPosition = orderedSiriuss.indexOf(viewpoint2) + 1;
                }
            }
            orderedSiriuss.add(insertPosition, viewpoint);
        }
        return Collections.unmodifiableCollection(orderedSiriuss);
    }

    /**
     * {@inheritDoc}
     */
    public Resource getSessionResource() {
        return sessionResource;
    }

    /**
     * {@inheritDoc}
     */
    public Set<Resource> getReferencedSessionResources() {
        List<Resource> allSessionResources = Lists.newArrayList(getAllSessionResources());
        allSessionResources.remove(getSessionResource());
        return new HashSet<Resource>(allSessionResources);
    }

    /**
     * {@inheritDoc}
     */
    public Collection<Resource> getSemanticResources() {
        if (semanticResources == null) {
            semanticResources = new CopyOnWriteArrayList<Resource>();
            semanticResourcesUpdater = new SemanticResourcesUpdater(this, semanticResources);
            if (!super.isBlocked()) {
                RunnableWithResult<Collection<Resource>> semanticResourcesGetter = new SemanticResourceGetter(this);
                try {
                    TransactionUtil.runExclusive(getTransactionalEditingDomain(), semanticResourcesGetter);
                } catch (InterruptedException e) {
                    SiriusPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, SiriusPlugin.ID, "Error while accessing semantic resources"));
                }
                semanticResources.addAll(semanticResourcesGetter.getResult());
            }
        }
        return Collections.unmodifiableCollection(semanticResources);
    }

    /**
     * {@inheritDoc}
     */
    public SessionService getServices() {
        if (services == null) {
            services = new DAnalysisSessionServicesImpl(super.getAnalyses());
        }
        return services;
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    private void addSemanticResource(final Resource newResource, final boolean addCrossReferencedResources, final IProgressMonitor monitor) {
        final ResourceSet set = transactionalEditingDomain.getResourceSet();
        doAddSemanticResource(newResource, set);
        if (addCrossReferencedResources) {
            for (Resource res : collectAllReferencedResources(newResource)) {
                doAddSemanticResource(res, set);
            }
        } else {
            doAddSemanticResource(newResource, set);
        }
    }

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

    /**
     * {@inheritDoc}
     */
    public synchronized void addSemanticResource(final Resource newResource, final boolean addCrossReferencedResources) {
        addSemanticResource(newResource, addCrossReferencedResources, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void addSemanticResource(URI semanticModelURI, boolean addCrossReferencedResources) {
        addSemanticResource(semanticModelURI, addCrossReferencedResources, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void addSemanticResource(final URI semanticModelURI, final boolean addCrossReferencedResources, IProgressMonitor monitor) {

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
                if (!getSemanticResources().contains(newSemanticResource)) {
                    addSemanticResource(newSemanticResource, addCrossReferencedResources, monitor);
                }
            } finally {
                monitor.done();
                ResourceSetUtil.resetProgressMonitor(resourceSet);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void addSemanticResource(URI semanticResourceURI, IProgressMonitor monitor) {
        addSemanticResource(semanticResourceURI, true, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public synchronized void createSemanticResource(URI semanticModelURI) {
        ResourceSet rs = transactionalEditingDomain.getResourceSet();
        Resource resource = rs.createResource(semanticModelURI);
        rs.getResources().remove(resource);
        addSemanticResource(resource, true, null);
    }

    private void doAddSemanticResource(final Resource newResource, final ResourceSet set) {
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
        enableTrackingModification(newResource);
    }

    /**
     * Enable modification tracking in the specified resource.
     * 
     * @param res
     *            a resource
     */
    protected void enableTrackingModification(Resource res) {
        if (!res.isTrackingModification()) {
            res.setTrackingModification(true);
        }
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

    /**
     * {@inheritDoc}
     */
    public ModelAccessor getModelAccessor() {
        return SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(transactionalEditingDomain.getResourceSet());
    }

    /**
     * 
     * {@inheritDoc}
     */
    public final void save() {
        save(new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public final void save(IProgressMonitor monitor) {
        save((Map<?, ?>) null, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public void save(Map<?, ?> options) {
        save(options, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void save(Map<?, ?> options, IProgressMonitor monitor) {
        try {
            monitor.beginTask("Session saving", 3);
            Collection<Resource> allResources = Lists.newArrayList();
            allResources.addAll(getAllSessionResources());
            Collection<Resource> semanticResourcesCollection = getSemanticResources();
            allResources.addAll(semanticResourcesCollection);
            allResources.addAll(getControlledResources());
            monitor.worked(1);
            Collection<Resource> savedResources = getSavingPolicy().save(allResources, options, new SubProgressMonitor(monitor, 7));
            boolean semanticSave = false;
            for (final Resource resource : savedResources) {
                if (semanticResourcesCollection.contains(resource) || super.getControlledResources().contains(resource)) {
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
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getID() {
        String id = Session.INVALID_SESSION;
        final StringBuilder builder = new StringBuilder();
        final Collection<DAnalysis> allAnalyses = allAnalyses();
        if (!allAnalyses.isEmpty()) {
            for (final DAnalysis analysis : allAnalyses) {
                if (analysis.eResource() != null && analysis.eResource().getURI() != null) {
                    builder.append(analysis.eResource().getURI().toString()).append(' ');
                } else {
                    return Session.INVALID_SESSION;
                }
            }
            id = builder.toString();
        }
        return id;
    }

    /**
     * {@inheritDoc}
     */
    public void addSelectedView(final DView view) throws IllegalArgumentException {
        addSelectedView(view, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
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
            updateSelectedSiriussData(new NullProgressMonitor());
            monitor.worked(1);
            notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            monitor.worked(1);
            initInterpreter();
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public Collection<DView> getSelectedViews() {
        final Collection<DView> selectedViews = new HashSet<DView>();
        for (final DAnalysis analysis : this.allAnalyses()) {
            for (final Object object : analysis.getSelectedViews()) {
                if (object instanceof DView) {
                    selectedViews.add((DView) object);
                }
            }
        }
        return selectedViews;
    }

    /**
     * {@inheritDoc}
     */
    public Collection<DView> getOwnedViews() {
        final Collection<DView> ownedViews = new HashSet<DView>();
        for (final DAnalysis analysis : allAnalyses()) {
            for (final DView dView : analysis.getOwnedViews()) {
                ownedViews.add(dView);
            }
        }
        return ownedViews;
    }

    /**
     * Unselect this specified {@link Sirius} on this {@link Session}.
     * 
     * @param viewpoint
     *            the {@link Sirius} to unselect on this {@link Session}
     */
    void unselectSirius(Sirius viewpoint) {
        for (DView selectedDView : mainDAnalysis.getSelectedViews()) {
            if (EqualityHelper.areEquals(selectedDView.getSirius(), viewpoint)) {
                removeSelectedView(selectedDView);
                break;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectedView(final DView view) {
        mainDAnalysis.getSelectedViews().remove(view);
        updateSelectedSiriussData(new NullProgressMonitor());
        notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
        initInterpreter();
    }

    /**
     * {@inheritDoc}
     */
    public void removeSelectedView(final DView view, IProgressMonitor monitor) {
        try {
            monitor.beginTask("View unselection", 1);
            mainDAnalysis.getSelectedViews().remove(view);
            updateSelectedSiriussData(new SubProgressMonitor(monitor, 1));
            notifyListeners(SessionListener.SELECTED_VIEWS_CHANGE_KIND);
            initInterpreter();
        } finally {
            monitor.done();
        }
    }

    /**
     * {@inheritDoc}
     */
    public IInterpreter getInterpreter() {
        if (this.crossReferencer == null) {
            this.interpreter.setCrossReferencer(getSemanticCrossReferencer());
        }
        return this.interpreter;
    }

    /**
     * {@inheritDoc}
     */
    public void createView(final Sirius viewpoint, final Collection<EObject> semantics) {
        createView(viewpoint, semantics, true, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void createView(Sirius viewpoint, Collection<EObject> semantics, IProgressMonitor monitor) {
        createView(viewpoint, semantics, true, monitor);
    }

    /**
     * {@inheritDoc}
     */
    public void createView(Sirius viewpoint, Collection<EObject> semantics, boolean createNewRepresentations) {
        createView(viewpoint, semantics, createNewRepresentations, new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     */
    public void createView(final Sirius viewpoint, final Collection<EObject> semantics, final boolean createNewRepresentations, IProgressMonitor monitor) {
        try {
            monitor.beginTask("View creation for Sirius : " + viewpoint.getName(), 3 + 10 * semantics.size());
            Set<DView> intializedDViews = new LinkedHashSet<DView>();
            for (DAnalysis dAnalysis : allAnalyses()) {
                if (!hasAlreadyDViewForSirius(dAnalysis, viewpoint)) {
                    // Try to get a orphan DView to reuse it
                    DView firstOrphanDView = new DAnalysisQuery(dAnalysis).getFirstOrphanDView();
                    DView dView = null;
                    // Otherwise create a new one
                    if (firstOrphanDView == null) {
                        dView = SiriusFactory.eINSTANCE.createDRepresentationContainer();
                    } else {
                        dView = firstOrphanDView;
                    }
                    dView.setSirius(viewpoint);
                    dAnalysis.getOwnedViews().add(dView);
                    dAnalysis.getSelectedViews().add(dView);

                    dView.setInitialized(true);
                    intializedDViews.add(dView);
                }
            }
            monitor.worked(1);

            /* need to prepare for the init */
            initInterpreter();
            InterpreterRegistry.prepareImportsFromSession(this.interpreter, this);
            if (createNewRepresentations) {
                monitor.subTask("Initialize representations");
                for (final EObject semantic : semantics) {
                    DialectManager.INSTANCE.initRepresentations(viewpoint, semantic, new SubProgressMonitor(monitor, 10));
                }
            }
            updateSelectedSiriussData(new SubProgressMonitor(monitor, 1));

            /* Initializes extension. */
            for (DView dView : intializedDViews) {
                if (dView instanceof DRepresentationContainer) {
                    DRepresentationContainer dRepresentationContainer = (DRepresentationContainer) dView;
                    for (final MetamodelExtensionSetting extensionSetting : viewpoint.getOwnedMMExtensions()) {
                        final ModelAccessor accessor = getModelAccessor();
                        if (extensionSetting.getExtensionGroup() != null && accessor.eInstanceOf(extensionSetting.getExtensionGroup(), "ExtensionGroup")) {
                            final EObject group = extensionSetting.getExtensionGroup();
                            final MetaModelExtension extension = SiriusFactory.eINSTANCE.createMetaModelExtension();
                            extension.setExtensionGroup(group);
                            dRepresentationContainer.setOwnedExtensions(extension);
                        }
                    }
                }
            }
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

    private boolean hasAlreadyDViewForSirius(DAnalysis dAnalysis, Sirius viewpoint) {
        boolean hasAlreadyDViewForSirius = false;
        for (DView ownedDView : dAnalysis.getOwnedViews()) {
            if (ownedDView.getSirius() != null && EqualityHelper.areEquals(ownedDView.getSirius(), viewpoint)) {
                hasAlreadyDViewForSirius = true;
                break;
            }
        }
        return hasAlreadyDViewForSirius;
    }

    private void updateSelectedSiriussData(IProgressMonitor monitor) {
        try {
            Set<Sirius> selectedSiriuss = Sets.newLinkedHashSet();
            for (Sirius viewpoint : getSelectedSiriuss(false)) {
                if (viewpoint.eResource() != null) {
                    selectedSiriuss.add(SiriusResourceHelper.getCorrespondingSirius(this, viewpoint));
                } else {
                    selectedSiriuss.add(viewpoint);
                }
            }
            SetView<Sirius> difference = Sets.difference(Sets.newHashSet(getActivatedSiriuss()), Sets.newHashSet(selectedSiriuss));
            monitor.beginTask("Update selected Siriuss data", selectedSiriuss.size() + difference.size() + 1);
            // FIXME : it is useful?
            for (Sirius viewpoint : selectedSiriuss) {
                if (viewpoint.eResource() != null) {
                    registerResourceInCrossReferencer(viewpoint.eResource());
                }
                monitor.worked(1);
            }
            for (Sirius viewpoint : difference) {
                if (viewpoint.eResource() != null) {
                    unregisterResourceInCrossReferencer(viewpoint.eResource());
                }
                monitor.worked(1);
            }
            super.getActivatedSiriuss().addAll(selectedSiriuss);
            super.getActivatedSiriuss().retainAll(selectedSiriuss);
            if (Movida.isEnabled()) {
                movidaSupport.updatePhysicalVSMResourceURIs(selectedSiriuss);
            }
            // tell the accessor and the interpreter which metamodels we know
            // of.
            final ModelAccessor accessor = getModelAccessor();
            Collection<MetamodelDescriptor> metamodels = MetamodelDescriptorManager.INSTANCE.provides(this.getSelectedSiriuss(false));
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
     * {@inheritDoc}
     */
    public void addListener(final SessionListener listener) {
        listeners.add(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void removeListener(final SessionListener listener) {
        listeners.remove(listener);
    }

    /**
     * {@inheritDoc}
     */
    public ECrossReferenceAdapter getSemanticCrossReferencer() {
        if (crossReferencer == null) {
            // use a lazy cross referencer to avoid big memory consumption on
            // session load
            crossReferencer = new LazyCrossReferencer() {
                @Override
                protected void initialize() {
                    super.initialize();
                    for (Resource res : getSemanticResources()) {
                        List<Adapter> adapters = res.eAdapters();
                        // add only if it was not added between creation and
                        // initialization
                        if (!adapters.contains(crossReferencer)) {
                            adapters.add(crossReferencer);
                        }
                    }
                    for (DAnalysis analysis : allAnalyses()) {
                        List<Adapter> adapters = analysis.eResource().eAdapters();
                        // add only if it was not added between creation and
                        // initialization
                        if (!adapters.contains(crossReferencer)) {
                            adapters.add(crossReferencer);
                        }
                    }
                }
            };
            // Precondition expression prevents a diagram to be
            // created when creating the session
            // Update the interpreter
            if (interpreter != null) {
                interpreter.setCrossReferencer(crossReferencer);
            }
        }
        return crossReferencer;
    }

    /**
     * Uncached EObjects IDs of the given resources.
     * 
     * @param resources
     *            the resources.
     */
    public static void uncachedEObejctIDs(final List<Resource> resources) {
        final Iterator<Resource> iterResources = resources.iterator();
        while (iterResources.hasNext()) {
            final Resource currentResource = iterResources.next();
            if (currentResource instanceof ResourceImpl) {
                ((ResourceImpl) currentResource).setIntrinsicIDToEObjectMap(null);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setAnalysisSelector(final DAnalysisSelector selector) {
        if (this.getServices() instanceof DAnalysisSessionService) {
            ((DAnalysisSessionService) this.getServices()).setAnalysisSelector(selector);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Set<Resource> getAllSessionResources() {
        final Set<Resource> analysisResources = new LinkedHashSet<Resource>();
        for (final DAnalysis analysis : allAnalyses()) {
            if (analysis.eResource() != null) {
                analysisResources.add(analysis.eResource());
            }
        }
        return Collections.unmodifiableSet(analysisResources);
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
        final Sirius vp = getSirius(representation);
        for (final DView view : analysis.getOwnedViews()) {
            if (view.getSirius() != null && EqualityHelper.areEquals(view.getSirius(), vp)) {
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
    private Sirius getSirius(final DRepresentation representation) {
        return ((DView) representation.eContainer()).getSirius();
    }

    /**
     * {@inheritDoc}
     */
    public void addAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.visibilityPropagator != null) {
            analysis.eAdapters().add(this.visibilityPropagator);
        }
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.registerAnalysis(analysis);
        }
        if (semanticResourcesUpdater != null && !analysis.eAdapters().contains(semanticResourcesUpdater)) {
            analysis.eAdapters().add(semanticResourcesUpdater);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeAdaptersOnAnalysis(final DAnalysis analysis) {
        if (this.visibilityPropagator != null) {
            analysis.eAdapters().remove(this.visibilityPropagator);
        }
        if (this.representationsChangeAdapter != null) {
            this.representationsChangeAdapter.unregisterAnalysis(analysis);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void moveRepresentation(final DAnalysis newContainer, final DRepresentation representation) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(representation.eContainer());
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
                createView(getSirius(representation), Lists.newArrayList(semantic), false);
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
     * Notify the listeners.
     */
    private void notifyListeners(final int notification) {
        if (notification == SessionListener.REPRESENTATION_CHANGE || notification == SessionListener.SELECTED_VIEWS_CHANGE_KIND || notification == SessionListener.VSM_UPDATED
                || notification != lastNotification) {
            for (final SessionListener listener : Iterables.filter(Lists.newArrayList(listeners.getListeners()), SessionListener.class)) {
                listener.notify(notification);
            }
            lastNotification = notification;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeReferencedRepresentation(final DAnalysis analysis, final DRepresentation representation) {
        final DView view = this.findViewForRepresentation(representation, analysis);
        if (view != null && view.getReferencedRepresentations().contains(representation)) {
            final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(view);
            if (authority.canEditFeature(view, SiriusPackage.eINSTANCE.getDView_ReferencedRepresentations().getName())) {
                view.getReferencedRepresentations().remove(representation);
            } else {
                throw new LockedInstanceException(view);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void notifyControlledModel(final Resource newControlled) {
        // Set the already controlled resource to modified because they can
        // reference the new resource.
        for (final Resource controlledResource : super.getControlledResources()) {
            controlledResource.setModified(true);
        }
        super.getControlledResources().add(newControlled);
        notifyListeners(SessionListener.SEMANTIC_CHANGE);
    }

    /**
     * {@inheritDoc}
     */
    public void notifyUnControlledModel(final EObject uncontrolled, final Resource resource) {
        if (resource.getContents().size() == 0) {
            super.getControlledResources().remove(resource);
        }
        notifyListeners(SessionListener.SEMANTIC_CHANGE);
    }

    /**
     * {@inheritDoc}
     */
    public void removeSemanticResource(final Resource resource, final boolean removeCrossReferencedResources) {
        final ResourceSet set = transactionalEditingDomain.getResourceSet();
        if (removeCrossReferencedResources) {
            removeSemanticResource(resource);
        } else {
            doRemoveSemanticResource(resource, set);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeSemanticResource(Resource semanticResource, IProgressMonitor monitor) {
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        for (final Resource res : collectAllReferencingResources(semanticResource)) {
            doRemoveSemanticResource(res, resourceSet);
        }
        doRemoveSemanticResource(semanticResource, resourceSet);
    }

    /**
     * {@inheritDoc}
     */
    public void removeSemanticResource(Resource semanticResource) {
        removeSemanticResource(semanticResource, new NullProgressMonitor());
    }

    private void doRemoveSemanticResource(final Resource res, final ResourceSet set) {
        disableTrackingModification(res);
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

    /**
     * Disable modification tracking in the specified resource.
     * 
     * @param res
     *            a resource
     */
    protected void disableTrackingModification(Resource res) {
        if (res != null && res.isTrackingModification()) {
            res.setTrackingModification(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void statusChanged(final Resource resource, final ResourceStatus oldStatus, final ResourceStatus newStatus) {
        // Do nothing while processing.
        // See statusesChanged(Collection<ResourceStatusChange>
    }

    /**
     * {@inheritDoc}
     */
    public void statusesChanged(Collection<ResourceStatusChange> changes) {
        Multimap<ResourceStatus, Resource> newStatuses = getImpactingNewStatuses(changes);
        boolean allResourcesSync = allResourcesAreInSync();
        for (ResourceStatus newStatus : newStatuses.keySet()) {
            switch (newStatus) {
            case SYNC:
                if (allResourcesSync) {
                    notifyListeners(SessionListener.SYNC);
                }
                break;
            case CHANGED:
                notifyListeners(SessionListener.DIRTY);
                break;
            case EXTERNAL_CHANGED:
            case CONFLICTING_CHANGED:
            case CONFLICTING_DELETED:
            case DELETED:
                Collection<Resource> collection = newStatuses.get(newStatus);
                for (Resource resource : collection) {
                    try {
                        /*
                         * if the project was renamed, deleted or closed we
                         * should not try to reload any thing, this does not
                         * make sense
                         */
                        if (isInProjectDeletedRenamedOrClosed(resource)) {
                            processAction(Action.CLOSE_SESSION, resource);
                            return;
                        }
                        processActions(getReloadingPolicy().getActions(this, resource, newStatus), resource);

                        // CHECKSTYLE:OFF
                    } catch (final Exception e) {
                        // CHECKSTYLE:ON
                        SiriusPlugin.getDefault().error("Can't handle resource change : " + resource.getURI(), e);

                    }
                }
                // Reload were launched, get global status.
                allResourcesSync = allResourcesAreInSync();
                if (allResourcesSync) {
                    notifyListeners(SessionListener.SYNC);
                } else {
                    notifyListeners(SessionListener.DIRTY);
                }
                break;
            default:
                break;
            }
        }

        if (allResourcesSync) {
            super.setSynchronizationStatus(SyncStatus.SYNC);
        } else {
            super.setSynchronizationStatus(SyncStatus.DIRTY);
        }
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
        boolean isResourceOfSession = false;
        ResourceSyncClientNotificationFilter resourceSyncClientNotificationFilter = new ResourceSyncClientNotificationFilter(resource);
        isResourceOfSession = Iterables.any(resources, resourceSyncClientNotificationFilter);
        return isResourceOfSession;
    }

    private Multimap<ResourceStatus, Resource> getImpactingNewStatuses(Collection<ResourceStatusChange> changes) {
        Multimap<ResourceStatus, Resource> impactingNewStatuses = LinkedHashMultimap.create();
        Iterable<Resource> resources = Iterables.concat(getSemanticResources(), getAllSessionResources(), getControlledResources());
        for (ResourceStatusChange change : changes) {
            if (isResourceOfSession(change.getResource(), resources)) {
                impactingNewStatuses.put(change.getNewStatus(), change.getResource());
            }
        }
        return impactingNewStatuses;
    }

    /**
     * Check if this resource is in an non-existent project or a closed project.
     * 
     * @param resource
     *            the resource to check
     * @return true if this resource is in an non-existent project or a closed
     *         project, false otherwise
     */
    private boolean isInProjectDeletedRenamedOrClosed(Resource resource) {
        IFile file = WorkspaceSynchronizer.getFile(resource);
        if (file != null) {
            IProject project = file.getProject();
            if (project != null)
                return !project.exists() || !project.isOpen();
        }
        return false;
    }

    private void processActions(final List<Action> actions, final Resource resource) throws Exception {
        for (final Action action : actions) {
            processAction(action, resource);
        }
    }

    private void processAction(final Action action, final Resource resource) throws Exception {
        switch (action) {
        case CLOSE_SESSION:
            close();
            break;
        case RELOAD:
            if (isOpen())
                reloadResource(resource);
            break;
        case REMOVE:
            removeResourceFromSession(resource);
            break;
        default:
            break;
        }
    }

    /**
     * Remove a resource from the current session and close the current session
     * if it contains no more analysis resource.
     * 
     * @param resource
     *            The resource to remove
     */
    private void removeResourceFromSession(Resource resource) {
        if (this.getSemanticResources().contains(resource)) {
            getTransactionalEditingDomain().getCommandStack().execute(new RemoveSemanticResourceCommand(this, resource, false, new NullProgressMonitor()));
        } else if (this.getAllSessionResources().contains(resource)) {
            this.removeAnalysis(resource);
        }
        if (this.getResources().contains(resource)) {
            for (final EObject root : Lists.newArrayList(resource.getContents())) {
                EcoreUtil.remove(root);
            }
            this.getResources().remove(resource);
        }
        // delete session only if no more aird file is open
        if (this.getAllSessionResources().isEmpty()) {
            this.close();
        }
    }

    private void reloadResource(final Resource resource) throws IOException {
        notifyListeners(SessionListener.ABOUT_TO_BE_REPLACED);

        Command reloadingAnalysisCmd = null;
        ResourceQuery resourceQuery = new ResourceQuery(resource);
        boolean representationsResource = resourceQuery.isRepresentationsResource();
        if (representationsResource) {
            reloadingAnalysisCmd = new ReloadRepresentationsFileCmd(this, transactionalEditingDomain, "Reload " + resource.getURI() + "file", resource);
        }
        List<Resource> resourcesBeforeReload = Lists.newArrayList(getTransactionalEditingDomain().getResourceSet().getResources());
        /* execute the reload operation as a read-only transaction */
        RunnableWithResult<?> reload = new RunnableWithResult.Impl<Object>() {
            public void run() {
                resource.unload();
                try {
                    resource.load(Collections.EMPTY_MAP);
                    EcoreUtil.resolveAll(resource);
                } catch (IOException e) {
                    setResult(e);
                }
            }
        };
        try {
            transactionalEditingDomain.runExclusive(reload);
            if (reload.getResult() != null) {
                throw (IOException) reload.getResult();
            } else if (!reload.getStatus().isOK()) {
                SiriusPlugin.getDefault().error("a reload operation failed for unknown reason", null);
            } else {
                if (representationsResource) {
                    transactionalEditingDomain.getCommandStack().execute(reloadingAnalysisCmd);
                    if (resource.getURI().equals(sessionResource.getURI())) {
                        // sessionResource's contents before reload can be proxy
                        // then need to be reassigned with reloaded
                        // sessionResource reference.
                        sessionResource = resource;
                        mainDAnalysis = (DAnalysis) sessionResource.getContents().get(0);
                    }
                }
                // Add the unknown resources to the semantic resources of this
                // session.
                dAnalysisRefresher.addAutomaticallyLoadedResourcesToSemanticResources(resourcesBeforeReload);
                notifyListeners(SessionListener.REPLACED);
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setReloadingPolicy(ReloadingPolicy reloadingPolicy) {
        this.reloadingPolicy = reloadingPolicy;
    }

    /**
     * {@inheritDoc}
     */
    public ReloadingPolicy getReloadingPolicy() {
        return reloadingPolicy != null ? reloadingPolicy : new ReloadingPolicyImpl(new NoUICallback());
    }

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
    protected SavingPolicy getSavingPolicy() {
        return savingPolicy != null ? savingPolicy : new SavingPolicyImpl(transactionalEditingDomain);
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
        final Iterable<? extends Resource> it = Iterables.concat(getSemanticResources(), getAllSessionResources(), super.getControlledResources());
        return checkResourcesAreInSync(it);
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
        boolean allResourceAreInSync = true;
        for (Resource resource : resourcesToConsider) {
            ResourceStatus status = ResourceSetSync.getStatus(resource);
            URI uri = resource.getURI();
            allResourceAreInSync = status == ResourceStatus.SYNC || (!uri.isPlatformResource() && !new URIQuery(uri).isInMemoryURI() && !new URIQuery(uri).isCDOURI());
            if (!allResourceAreInSync)
                break;
        }
        return allResourceAreInSync;
    }

    /**
     * {@inheritDoc}
     */
    public SessionStatus getStatus() {
        if (allResourcesAreInSync()) {
            return SessionStatus.SYNC;
        } else {
            return SessionStatus.DIRTY;
        }
    }

    /**
     * {@inheritDoc}
     */
    public void transfertNotification(final int notification) {
        notifyListeners(notification);
    }

    /**
     * {@inheritDoc}
     */
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
            transactionalEditingDomain.addResourceSetListener(refreshEditorsListeners);
            this.addListener(refreshEditorsListeners);
        }
    }

    /**
     * Remove the refresh editors preCommit listener of the editingDomain.
     */
    protected void removeRefreshEditorsListener() {
        if (refreshEditorsListeners != null) {
            transactionalEditingDomain.removeResourceSetListener(refreshEditorsListeners);
        }
    }

    /**
     * Get the PrecommitListener that is responsible to refresh all opened
     * Sirius editors.
     * 
     * @return a ResourceSetListener
     */
    public RefreshEditorsPrecommitListener getRefreshEditorsListener() {
        return refreshEditorsListeners;
    }

    /**
     * {@inheritDoc}
     */
    public final void close() {
        close(new NullProgressMonitor());
    }

    /**
     * {@inheritDoc}
     * 
     * @throws IllegalAccessException
     */
    public void close(IProgressMonitor monitor) {
        if (!isOpen())
            return;
        if (Movida.isEnabled()) {
            org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry registry = (org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry) SiriusRegistry.getInstance();
            registry.removeListener((SiriusRegistryListener) this);
        } else {
            SiriusRegistry.getInstance().removeListener(this);
        }
        notifyListeners(SessionListener.CLOSING);
        // Disable resolution of proxy for all airdCrossReferenceAdapter of
        // session resources and for semanticCrossReferencer during the closing
        List<AirDCrossReferenceAdapter> airdCrossReferenceAdapters = disableCrossReferenceAdaptersResolution(Iterables.filter(getAllSessionResources(), AirdResource.class));
        if (getSemanticCrossReferencer() instanceof LazyCrossReferencer) {
            ((LazyCrossReferencer) getSemanticCrossReferencer()).disableResolve();
        }
        removeRefreshEditorsListener();
        if (controlledResourcesDetector != null) {
            controlledResourcesDetector.dispose();
        }
        if (dAnalysisRefresher != null) {
            dAnalysisRefresher.dispose();
            dAnalysisRefresher = null;
        }
        // Let's clear the cross referencer if it's still there.
        for (final Resource res : getSemanticResources()) {
            unregisterResourceInCrossReferencer(res);
        }
        for (final DAnalysis analysis : Iterables.filter(allAnalyses(), Predicates.notNull())) {
            removeAdaptersOnAnalysis(analysis);
            if (analysis.eResource() != null) {
                unregisterResourceInCrossReferencer(analysis.eResource());
            }
        }
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        if (interpreter != null) {
            interpreter.dispose();
        }
        ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).unregisterClient(this);
        ResourceSetSync.uninstallResourceSetSync(transactionalEditingDomain);
        super.setOpen(false);
        /*
         * Let's clear the cross referencer of the VSM resource if it's still
         * there (added by the updateSelectedSiriussData).
         */
        Iterable<Resource> resources = Lists.newArrayList(resourceSet.getResources());
        for (Resource resource : Iterables.filter(resources, new ResourceFileExtensionPredicate(SiriusUtil.DESCRIPTION_MODEL_EXTENSION, false))) {
            unregisterResourceInCrossReferencer(resource);
        }
        if (currentResourceCollector != null) {
            currentResourceCollector.dispose();
            currentResourceCollector = null;
        }
        interpreter = null;
        crossReferencer = null;
        visibilityPropagator = null;

        transactionalEditingDomain.removeResourceSetListener(representationNameListener);
        // TODO deinitialize model accessor, authority..
        notifyListeners(SessionListener.CLOSED);
        SessionManager.INSTANCE.remove(this);
        // dispose the SessionEventBroker
        if (broker != null) {
            broker.dispose();
            broker = null;
        }
        // FIXME : why can't dispose this
        // SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(resourceSet).dispose();
        flushOperations();
        // Unload all referenced resources
        unloadResource();
        if (semanticResourcesUpdater != null) {
            semanticResourcesUpdater.dispose();
            semanticResourcesUpdater = null;
        }
        if (semanticResources != null) {
            semanticResources.clear();
        }
        // Enable resolution for all airdCrossReferenceAdapter of session
        // resources after the closing
        for (AirDCrossReferenceAdapter airDCrossReferenceAdapter : airdCrossReferenceAdapters) {
            airDCrossReferenceAdapter.enableResolve();
        }
        if (getSemanticCrossReferencer() instanceof LazyCrossReferencer) {
            ((LazyCrossReferencer) getSemanticCrossReferencer()).enableResolve();
        }
        if (disposeEditingDomainOnClose) {
            // To remove remaining resource like environment:/viewpoint
            for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
                resource.unload();
                resourceSet.getResources().remove(resource);
            }
            transactionalEditingDomain.dispose();
            doDisposePermissionAuthority(resourceSet);
        }
        mainDAnalysis = null;
    }

    private void flushOperations() {
        CommandStack commandStack = transactionalEditingDomain.getCommandStack();
        ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
        if (commandStack != null) {
            // Remove also ResourceUndoContext to have operations really
            // removed from IOperationHistory
            // This must be done before commandStack.flush(); to have
            // the Undo/RedoActionHandler update the actions
            if (commandStack instanceof IWorkspaceCommandStack) {
                IWorkspaceCommandStack workspaceCommandStack = (IWorkspaceCommandStack) commandStack;
                for (Resource resource : new ArrayList<Resource>(resourceSet.getResources())) {
                    workspaceCommandStack.getOperationHistory().dispose(new ResourceUndoContext(transactionalEditingDomain, resource), true, true, true);
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
     * @param resources
     *            List of resources that potentially having a
     *            AirDCrossReferenceAdapter.
     * @return The list of airDCrossReferenceAdapter for which the resolution
     *         has been disabled.
     */
    private List<AirDCrossReferenceAdapter> disableCrossReferenceAdaptersResolution(Iterable<AirdResource> resources) {
        List<AirDCrossReferenceAdapter> airdCrossReferenceAdapters = Lists.newArrayList();
        for (AirdResource representationsFileResource : resources) {
            Option<AirDCrossReferenceAdapter> optionalAirdCrossReferenceAdapter = new AirDResouceQuery(representationsFileResource).getAirDCrossReferenceAdapter();
            if (optionalAirdCrossReferenceAdapter.some()) {
                airdCrossReferenceAdapters.add(optionalAirdCrossReferenceAdapter.get());
                optionalAirdCrossReferenceAdapter.get().disableResolve();
            }
        }
        return airdCrossReferenceAdapters;
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

    /**
     * Temporary method to clean deprecated NodesDone in INodeMappingExt.
     * {@inheritDoc}
     */
    public void modelerDesciptionFilesLoaded() {
        if (!Movida.isEnabled()) {
            Collection<Resource> allResources = Lists.newArrayList(transactionalEditingDomain.getResourceSet().getResources());
            for (Resource res : Iterables.filter(allResources, new ResourceFileExtensionPredicate(SiriusUtil.DESCRIPTION_MODEL_EXTENSION, true))) {
                // Unload emtpy odesign.
                if (!res.isModified() && res.isLoaded() && res.getContents().isEmpty()) {
                    unregisterResourceInCrossReferencer(res);
                    res.unload();
                }
                // Reload unloaded odesign (SiriusRegistry can unload them).
                IFile correspondingFile = WorkspaceSynchronizer.getFile(res);
                if (!res.isLoaded() && correspondingFile != null && correspondingFile.exists()) {
                    try {
                        res.load(Collections.emptyMap());
                        if (res.isLoaded() && !res.getContents().isEmpty()) {
                            registerResourceInCrossReferencer(res);
                            // Refresh the imports of interpreter in case of new
                            // Java Extension
                            InterpreterRegistry.prepareImportsFromSession(this.interpreter, this);
                        }
                    } catch (IOException e) {
                        SiriusPlugin.getDefault().warning(MessageFormat.format("Unable to load the VSM at {0}", res.getURI()), e);
                    }
                }
            }
        }
        notifyListeners(SessionListener.VSM_UPDATED);
    }

    /**
     * {@inheritDoc}
     */
    public void registryChanged(final org.eclipse.sirius.business.internal.movida.registry.SiriusRegistry registry, Set<URI> removed, Set<URI> added, Set<URI> changed) {
        movidaSupport.registryChanged(registry, removed, added, changed);
    }

    public void setResourceCollector(IResourceCollector collector) {
        this.currentResourceCollector = collector;
    }
}
