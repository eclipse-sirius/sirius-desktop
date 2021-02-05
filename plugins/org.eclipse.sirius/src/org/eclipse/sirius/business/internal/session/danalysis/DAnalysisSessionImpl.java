/*******************************************************************************
 * Copyright (c) 2013, 2021 THALES GLOBAL SERVICES and others,
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session.danalysis;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.impl.InternalTransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.transaction.util.ValidateEditSupport;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.RepresentationHelper;
import org.eclipse.sirius.business.api.migration.AirdResourceVersionMismatchException;
import org.eclipse.sirius.business.api.migration.DescriptionResourceVersionMismatchException;
import org.eclipse.sirius.business.api.migration.ResourceVersionMismatchDiagnostic;
import org.eclipse.sirius.business.api.query.DAnalysisQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.query.FileQuery;
import org.eclipse.sirius.business.api.query.RepresentationDescriptionQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.query.SiriusReferenceFinder;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategyRegistry;
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
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationManager;
import org.eclipse.sirius.business.internal.resource.ResourceModifiedFieldUpdater;
import org.eclipse.sirius.business.internal.session.IsModifiedSavingPolicy;
import org.eclipse.sirius.business.internal.session.ReloadingPolicyImpl;
import org.eclipse.sirius.business.internal.session.RepresentationNameListener;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync;
import org.eclipse.sirius.common.tools.api.resource.ResourceSetSync.ResourceStatus;
import org.eclipse.sirius.common.tools.api.resource.ResourceSyncClient;
import org.eclipse.sirius.common.tools.api.util.CommandStackUtil;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.common.tools.api.util.SiriusCrossReferenceAdapter;
import org.eclipse.sirius.ecore.extender.business.api.accessor.EcoreMetamodelDescriptor;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ecore.extender.business.api.permission.IPermissionAuthority;
import org.eclipse.sirius.ecore.extender.business.api.permission.PermissionAuthorityRegistry;
import org.eclipse.sirius.ecore.extender.business.api.permission.exception.LockedInstanceException;
import org.eclipse.sirius.ecore.extender.business.internal.accessor.ModelAccessorAdapter;
import org.eclipse.sirius.tools.api.command.ui.NoUICallback;
import org.eclipse.sirius.tools.api.interpreter.InterpreterRegistry;
import org.eclipse.sirius.tools.api.profiler.SiriusTasksKey;
import org.eclipse.sirius.tools.api.ui.RefreshEditorsPrecommitListener;
import org.eclipse.sirius.tools.internal.interpreter.SessionInterpreter;
import org.eclipse.sirius.tools.internal.resource.ResourceSetUtil;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.Messages;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.impl.DAnalysisSessionEObjectImpl;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A session which store data in {@link DAnalysis} references.
 * 
 * @author cbrun
 */
public class DAnalysisSessionImpl extends DAnalysisSessionEObjectImpl implements Session, DAnalysisSession, ResourceSyncClient {

    /**
     * The inverse cross referencer to retrieve DRep and DRepElement.
     */
    protected SiriusReferenceFinder siriusReferenceFinder;

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

    private LocalResourceCollectorCrossReferencer crossReferencer;

    private IInterpreter interpreter;

    private final ListenerList listeners = new ListenerList();

    private int lastNotification = -1;

    // Default listeners

    /** The listener suitable for refresh the opened viewpoint editors. */
    private RefreshEditorsPrecommitListener refreshEditorsListeners;

    private RepresentationsChangeAdapter representationsChangeAdapter;

    private RepresentationNameListener representationNameListener;

    private DRepresentationChangeListener dRepresentationChangeListener;

    private ChangeIdUpdaterListener changeIdUpdaterListener;

    private final String id;

    /**
     * Listener that clears the sub diagram decoration descriptors when a {@link DRepresentation} is either created or
     * deleted.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     */
    private final class DRepresentationChangeListener extends ResourceSetListenerImpl {

        private DAnalysisSession session;

        private DRepresentationChangeListener(DAnalysisSession session) {
            this.session = session;
        }

        @Override
        public void resourceSetChanged(ResourceSetChangeEvent event) {
            List<Notification> notifications = event.getNotifications();
            boolean subDiagramDecorationDesciptorCleared = false;
            Collection<DRepresentation> allLoadedRepresentations = DialectManager.INSTANCE.getAllLoadedRepresentations(session);
            for (Notification notification : notifications) {
                if (notification.getNewValue() instanceof DRepresentation || notification.getOldValue() instanceof DRepresentation) {
                    allLoadedRepresentations.stream().forEach(rep -> rep.getUiState().getSubDiagramDecorationDescriptors().clear());
                    subDiagramDecorationDesciptorCleared = true;
                    break;
                }
            }

            if (!subDiagramDecorationDesciptorCleared) {
                // The model has changed, remove subDiagramDescriptors marked as no sub diagram descriptors as the
                // navigation tools might now have valid target in the next evaluation of their expressions.
                allLoadedRepresentations.stream().forEach(rep -> {
                    Iterator<Entry<Object, Object>> it = rep.getUiState().getSubDiagramDecorationDescriptors().entrySet().iterator();
                    while (it.hasNext()) {
                        Entry<Object, Object> next = it.next();
                        if (next.getValue() instanceof NoSubDecorationDescriptor) {
                            it.remove();
                        }
                    }
                });
            }
        }

        @Override
        public NotificationFilter getFilter() {
            NotificationFilter filter = super.getFilter();
            filter = filter.and(new NotificationFilter.Custom() {

                @Override
                public boolean matches(Notification notification) {
                    Object notifier = notification.getNotifier();
                    if (!notification.isTouch() && notifier instanceof EObject && !new NotificationQuery(notification).isTransientNotification()) {
                        return true;
                    }
                    return false;
                }
            });
            return filter;
        }

        @Override
        public boolean isPostcommitOnly() {
            return true;
        }
    }

    /**
     * Simple marker to indicate that the shouldHaveSubDiagDecoration returned false.
     * 
     */
    public static final class NoSubDecorationDescriptor {

    }

    /**
     * Listen to any change to a {@link DRepresentation} or one of its {@link DRepresentationElement} and update the
     * associated {@link DRepresentationDescriptorn} change id.
     * 
     * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
     *
     */
    public class ChangeIdUpdaterListener extends ResourceSetListenerImpl {

        Set<EObject> notifierWithoutRepresentationDescriptors;

        Map<EObject, DRepresentationDescriptor> notifierToDRepMap;

        @Override
        public boolean isPrecommitOnly() {
            return true;
        }

        @Override
        public boolean isAggregatePrecommitListener() {
            return true;
        }

        @Override
        public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
            List<Notification> notifications = event.getNotifications();
            notifierWithoutRepresentationDescriptors = new HashSet<>();
            notifierToDRepMap = new HashMap<>();
            Set<DRepresentationDescriptor> descriptorsToUpdate = new HashSet<>();
            // Descriptors to ignore because their change ids have already been updated (because of Rollback command or
            // a diff-merge for example).
            Set<DRepresentationDescriptor> descriptorsToIgnore = new HashSet<>();

            Iterator<Notification> notifIterator = notifications.iterator();

            while (notifIterator.hasNext()) {
                Notification notification = notifIterator.next();
                Object notifier = notification.getNotifier();
                boolean isEObject = notifier instanceof EObject;
                boolean isTransient = notification.getFeature() instanceof EStructuralFeature && ((EStructuralFeature) notification.getFeature()).isTransient();
                if (isEObject && !isTransient && ViewpointPackage.Literals.IDENTIFIED_ELEMENT.isInstance(notifier)) {
                    final DRepresentationDescriptor representationDescriptor = getDRepresentationDescriptor((EObject) notifier);
                    if (representationDescriptor != null) {
                        descriptorsToUpdate.add(representationDescriptor);
                    }
                    if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(notifier)
                            && ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__CHANGE_ID.equals(notification.getFeature())) {
                        // Ignore descriptor with a change of "ChangeId"
                        descriptorsToIgnore.add((DRepresentationDescriptor) notifier);
                    }
                } else if (isEObject && !isTransient) {
                    EClass eClass = ((EObject) notifier).eClass();
                    EPackage ePackage = eClass.getEPackage();
                    if ("notation".equals(ePackage.getNsPrefix())) { //$NON-NLS-1$
                        final DRepresentationDescriptor representationDescriptor = getDRepresentationDescriptor((EObject) notifier);
                        if (representationDescriptor != null) {
                            descriptorsToUpdate.add(representationDescriptor);

                        }
                    }
                }
            }
            descriptorsToUpdate.removeAll(descriptorsToIgnore);
            if (!descriptorsToUpdate.isEmpty()) {
                RecordingCommand changeIdRecordingCommand = new RecordingCommand(transactionalEditingDomain) {
                    @Override
                    protected void doExecute() {
                        for (DRepresentationDescriptor dRepresentationDescriptor : descriptorsToUpdate) {
                            RepresentationHelper.updateChangeId(dRepresentationDescriptor);
                        }
                    }
                };
                return changeIdRecordingCommand;
            }
            return null;
        }

        private DRepresentationDescriptor getDRepresentationDescriptor(EObject eObject) {
            DRepresentationDescriptor dRepresentationDescriptor = null;
            DRepresentationDescriptor repAssociatedToEObject = notifierToDRepMap.get(eObject);
            if (repAssociatedToEObject == null && !notifierWithoutRepresentationDescriptors.contains(eObject)) {
                EObject eContainer = eObject.eContainer();
                if (!(eObject.eContainingFeature() != null && eObject.eContainingFeature().isTransient())) {
                    // We check for a descriptor from the top element to the current element to be sure the current
                    // element is not related to a transient feature.
                    if (eContainer != null && !(eObject instanceof DRepresentation)) {
                        dRepresentationDescriptor = getDRepresentationDescriptor(eContainer);
                        if (dRepresentationDescriptor == null) {
                            // the parent is not associated to any descriptor so are the children.
                            notifierWithoutRepresentationDescriptors.add(eObject);
                        } else {
                            // we have a valid descriptor from the parent so we associate it to this eObject in case
                            // we parse it again later.
                            notifierToDRepMap.put(eObject, dRepresentationDescriptor);
                        }
                    } else if (eObject instanceof DRepresentation) {
                        // we have reached the parent representation so we retrieve its descriptor.
                        dRepresentationDescriptor = new DRepresentationQuery((DRepresentation) eObject).getRepresentationDescriptor();
                        notifierToDRepMap.put(eObject, dRepresentationDescriptor);
                    }
                } else {
                    // eObject is associated to a transient feature so we ignore it.
                    notifierWithoutRepresentationDescriptors.add(eObject);
                }
            } else {
                dRepresentationDescriptor = repAssociatedToEObject;
            }
            return dRepresentationDescriptor;
        }
    }

    /**
     * Create a new session.
     * 
     * @param mainDAnalysis
     *            the analysis keeping the session data.
     */
    public DAnalysisSessionImpl(DAnalysis mainDAnalysis) {
        Preconditions.checkNotNull(mainDAnalysis);
        this.sessionResource = mainDAnalysis.eResource();
        Preconditions.checkNotNull(this.sessionResource, Messages.DAnalysisSessionImpl_noRessourceErrorMsg);
        this.id = sessionResource.getURI().toString();
        this.transactionalEditingDomain = Preconditions.checkNotNull(TransactionUtil.getEditingDomain(mainDAnalysis), Messages.DAnalysisSessionImpl_noEditingDomainErrorMsg);
        this.mainDAnalysis = mainDAnalysis;
        this.saver = new Saver(this);
        this.interpreter = new SessionInterpreter();
        this.representationsChangeAdapter = new RepresentationsChangeAdapter(this);
        super.getAnalyses().add(mainDAnalysis);
        super.getResources().add(sessionResource);
        setAnalysisSelector(DAnalysisSelectorService.getSelector(this));
        LocalResourceCollectorCrossReferencer semanticCrossReferencer = getSemanticCrossReferencer();
        setResourceCollector(semanticCrossReferencer);
        setDeferSaveToPostCommit(true);
        setSaveInExclusiveTransaction(true);
        dRepresentationChangeListener = new DRepresentationChangeListener(this);
        getTransactionalEditingDomain().addResourceSetListener(dRepresentationChangeListener);
        changeIdUpdaterListener = new ChangeIdUpdaterListener();
        getTransactionalEditingDomain().addResourceSetListener(changeIdUpdaterListener);
    }

    // *******************
    // Session interpreter
    // *******************

    @Override
    public IInterpreter getInterpreter() {
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
    }

    // *******************
    // Cross-referencer
    // *******************
    @Override
    public LocalResourceCollectorCrossReferencer getSemanticCrossReferencer() {
        if (crossReferencer == null) {
            crossReferencer = createSemanticCrossReferencer();
            crossReferencer.setFeatureToBeCrossReferencedWhiteList(Arrays.asList(ViewpointPackage.eINSTANCE.getDRepresentationDescriptor_Representation()));
            interpreter.setCrossReferencer(crossReferencer);
        }
        return crossReferencer;
    }

    /**
     * Create the semantic cross referencer.
     * 
     * @return a new cross referencer adapter
     */
    protected LocalResourceCollectorCrossReferencer createSemanticCrossReferencer() {
        return new LocalResourceCollectorCrossReferencer(this);
    }

    /**
     * Add the cross referencer (if exists and is not present) to the eAdapters list of the given resource.
     * 
     * @param newResource
     *            the resource on which the semantic cross reference should be added.
     */
    public void registerResourceInCrossReferencer(final Resource newResource) {
        if (crossReferencer != null) {
            if (!newResource.eAdapters().contains(crossReferencer)) {
                newResource.eAdapters().add(crossReferencer);
            }
        }
    }

    /**
     * Remove the cross referencer (if exists and is present) from the eAdapters list of the given resource.
     * 
     * @param resource
     *            the resource from which the semantic cross reference should be removed.
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
        // Also disable resolveProxy capability and remove semantic cross
        // referencer from all resources where it was installed
        // (SessionLazyCrossReferencer.initialize()).
        Collection<Resource> semanticResources = getSemanticResources();
        Collection<Resource> controlledResources = getControlledResources();
        Set<Resource> allSessionResources = getAllSessionResources();
        Collection<Resource> srmResources = getSrmResources();
        Object semanticCrossReferencer = getSemanticCrossReferencer();
        Iterable<Resource> resources = Iterables.concat(semanticResources, controlledResources, allSessionResources, srmResources);
        for (Resource resource : resources) {
            if (!(resourceSet.getResources().contains(resource))) {
                disableCrossReferencerResolve(resource);
                resource.eAdapters().remove(semanticCrossReferencer);
            }
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
        List<DAnalysis> sources = new ArrayList<>();
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
            throw new IllegalStateException(Messages.DAnalysisSessionImpl_addNoParentAnalysisErrorMsg);
        }
    }

    @Override
    public void removeReferencedAnalysis(final DAnalysis analysis) {
        assert analysis.eResource() != null;
        Collection<DAnalysis> referencers = new ArrayList<>();
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
            throw new IllegalStateException(Messages.DAnalysisSessionImpl_removeNoParentAnalysisErrorMsg);
        }
    }

    /**
     * Return all valid(i.e. not null) owned and referenced analyses.
     * 
     * @return all valid(i.e. not null) owned and referenced analyses.
     */
    @Override
    public Collection<DAnalysis> allAnalyses() {
        Collection<DAnalysis> analysisAndReferenced = new LinkedHashSet<>();
        for (DAnalysis analysis : new ArrayList<DAnalysis>(super.getAnalyses())) {
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
    public void moveRepresentation(final DAnalysis newDAnalysisContainer, final DRepresentationDescriptor repDescriptor) {
        final IPermissionAuthority authority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(repDescriptor.eContainer());
        IProgressMonitor pm = new NullProgressMonitor();
        if (!authority.canDeleteInstance(repDescriptor)) {
            throw new LockedInstanceException(repDescriptor);
        }
        final EObject semantic;
        DView dView = (DView) repDescriptor.eContainer();
        if (!dView.getModels().isEmpty()) {
            semantic = dView.getModels().iterator().next();
        } else {
            semantic = null;
        }
        Viewpoint viewpoint = dView.getViewpoint();
        DView receiverDView = findViewForRepresentation(viewpoint, newDAnalysisContainer);
        if (receiverDView == null) {
            final IPermissionAuthority analysisAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(newDAnalysisContainer);
            if (analysisAuthority.canCreateIn(newDAnalysisContainer)) {
                createView(viewpoint, Lists.newArrayList(semantic), false, pm);
                receiverDView = findViewForRepresentation(viewpoint, newDAnalysisContainer);
            } else {
                throw new LockedInstanceException(newDAnalysisContainer);
            }
        }
        final IPermissionAuthority receiverAuthority = PermissionAuthorityRegistry.getDefault().getPermissionAuthority(receiverDView);
        if (receiverAuthority.canCreateIn(receiverDView)) {
            DRepresentation dRepresentation = repDescriptor.getRepresentation();
            Resource sourceRepResource = dRepresentation.eResource();

            // move representation
            Resource targetRepResource = new DRepresentationLocationManager().getOrCreateRepresentationResource(dRepresentation, receiverDView.eResource());
            if (targetRepResource != null) {
                targetRepResource.getContents().add(dRepresentation);

                // We call updateRepresentation to update the new path. We need to update the path before updating
                // the
                // repDescritor's parent which triggers the model explorer refresh with a wrong repPath attribute.
                repDescriptor.updateRepresentation(dRepresentation);
                receiverDView.getOwnedRepresentationDescriptors().add(repDescriptor);
                // Add all semantic root elements pointed by the target of all
                // DSemanticDecorator of this representation (except of this root is
                // a root of a referencedAnalysis)
                if (receiverDView.eContainer() instanceof DAnalysis) {
                    DAnalysisSessionHelper.updateModelsReferences(receiverDView);
                }

                // delete the resource if it is empty
                Arrays.asList(sourceRepResource, targetRepResource).stream().filter(res -> res.getContents().isEmpty()).forEach(res -> {
                    try {
                        res.delete(Collections.emptyMap());
                    } catch (IOException e) {
                        SiriusPlugin.getDefault().error(Messages.SiriusUncontrolCommand_resourceDeletionFailedMsg, e);
                    }
                });
            }
        } else {
            throw new LockedInstanceException(receiverDView);
        }
        for (EObject object : getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, repDescriptor.getRepresentation())) {
            getServices().putCustomData(CustomDataConstants.GMF_DIAGRAMS, repDescriptor.getRepresentation(), object);
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
     * Find all the resources referenced by any object from the specified resource. Ignore "http" resources.
     * 
     * @param res
     *            the resource to start from.
     * @return all the resources referenced by elements from res, except "http" resources.
     */
    protected Collection<Resource> collectAllReferencedResources(Resource res) {
        Collection<Resource> result = Collections.emptyList();
        if (currentResourceCollector != null) {
            result = currentResourceCollector.getAllReferencedResources(res);
        }
        return result;
    }

    /**
     * Find all the resources referencing by any object from the specified resource. Ignore "http" resources.
     * 
     * @param res
     *            the resource to start from.
     * @return all the resources referencing by elements from res, except "http" resources.
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
            FileQuery fileQuery = new FileQuery(semanticModelURI.fileExtension());
            if (fileQuery.isSessionResourceFile() || fileQuery.isSrmFile()) {
                throw new IllegalArgumentException(Messages.DAnalysisSessionImpl_addSemanticErrorMsg);
            }
            ResourceSet resourceSet = transactionalEditingDomain.getResourceSet();
            try {
                monitor.beginTask(MessageFormat.format(Messages.DAnalysisSessionImpl_addSemanticResourceMsg, semanticModelURI.lastSegment()), 3);

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
                // Make ResourceSet aware of resource loading with progress
                // monitor
                ResourceSetUtil.setProgressMonitor(resourceSet, new SubProgressMonitor(monitor, 2));
                newSemanticResource = resourceSet.getResource(semanticModelURI, true);
                // If it is a new resource, register it with all its referenced
                // resources as semantic models.
                Set<Resource> alreadyKnownResources = new HashSet<>(getSemanticResources());
                if (!alreadyKnownResources.contains(newSemanticResource)) {
                    doAddSemanticResource(newSemanticResource, resourceSet, alreadyKnownResources);
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
     * @param collector
     *            a set which allows to know which resource have already been processed.
     */
    protected void doAddSemanticResource(final Resource newResource, final ResourceSet set, final Set<Resource> collector) {
        collector.add(newResource);

        if (new ResourceQuery(newResource).isAirdOrSrmResource()) {
            throw new IllegalArgumentException(Messages.DAnalysisSessionImpl_addSemanticErrorMsg);
        }
        if (newResource.getResourceSet() != set) {
            set.getResources().add(newResource);
        }
        notifyNewMetamodels(newResource);
        for (final DAnalysis analysis : this.allAnalyses()) {
            analysis.getSemanticResources().add(new ResourceDescriptor(newResource.getURI()));
        }

        ControlledResourcesDetector.refreshControlledResources(this);

        registerResourceInCrossReferencer(newResource);

        for (Resource res : collectAllReferencedResources(newResource)) {
            // Avoid to process resources twice.
            if (!collector.contains(res)) {
                doAddSemanticResource(res, set, collector);
            }
        }
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
     * Allow semanticResources to be recomputed when calling <code>getSemanticResources()</code>.
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
        if (removeReferencingResources) {
            for (final Resource res : collectAllReferencingResources(semanticResource)) {
                doRemoveSemanticResource(res);
            }
        }
        doRemoveSemanticResource(semanticResource);
    }

    /**
     * Unregisters the resource from the list of semantic resources and remove the resource from its ResourceSet.
     * 
     * @param res
     *            the semantic resource to unregister.
     */
    protected void doRemoveSemanticResource(final Resource res) {
        ResourceSet resourceSet = res.getResourceSet();
        if (resourceSet != null) {
            // update models in aird resource
            for (final DAnalysis analysis : this.allAnalyses()) {
                analysis.getSemanticResources().remove(new ResourceDescriptor(res.getURI()));
            }

            unregisterResourceInCrossReferencer(res);
            disableCrossReferencerResolve(res);
            ResourceStrategyRegistry.getInstance().unloadAtResourceSetDispose(res, new NullProgressMonitor());
            enableCrossReferencerResolve(res);

            resourceSet.getResources().remove(res);

            ControlledResourcesDetector.refreshControlledResources(this);
        }
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
        List<Resource> result = new ArrayList<>();
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
     * Sets the Synchronization status of every resources of this Session's resourceSet to SYNC or changed regarding
     * their modified status.
     */
    protected void setSynchronizeStatusofEveryResource() {
        setSynchronizeStatusofEveryResource(transactionalEditingDomain.getResourceSet().getResources());
    }

    /**
     * Sets the Synchronization status of considered resources of this Session's resourceSet to SYNC or changed
     * regarding their modified status.
     * 
     * Should only be called from setSynchronizeStatusofEveryResource method (and overriding ones).
     * 
     * @param resourcesToConsider
     *            the resources to consider.
     */
    protected final void setSynchronizeStatusofEveryResource(Iterable<Resource> resourcesToConsider) {
        ResourceSetSync rsSetSync = ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain);
        Collection<ResourceSyncClient.ResourceStatusChange> changes = new ArrayList<>();
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
     *            whether or not to execute the saving in an exclusive transaction.
     */
    protected void doSave(final Map<?, ?> options, final IProgressMonitor monitor, boolean runExclusive) {
        try {
            monitor.beginTask(Messages.DAnalysisSessionImpl_saveMsg, 3);
            final Collection<Resource> allResources = new ArrayList<>();
            allResources.addAll(getAllSessionResources());
            Collection<Resource> semanticResourcesCollection = getSemanticResources();
            allResources.addAll(semanticResourcesCollection);
            allResources.addAll(getControlledResources());
            allResources.addAll(getSrmResources());

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
                 * launching the save itself in a read-only transaction will block any other operation on the model
                 * which could come in the meantime.
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
            SiriusPlugin.getDefault().warning(Messages.DAnalysisSessionImpl_saveInterruptedMsg, e);
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
     * Tells if the specified resource is one of {@link Session#getSemanticResources()},
     * {@link Session#getAllSessionResources()} or DAnalysisSessionEObject#getControlledResources().
     * 
     * @param resource
     *            the specified {@link Resource}
     * @param resources
     *            the session resources
     * @return true if the specified {@link Resource} is one of the {@link Session}, false otherwise
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
     * Returns the custom saving policy the session should use ; if no SavingPolicy has been defined, creates a default
     * one.<br/>
     * Subclasses can override this method to define a new default Saving Policy.
     * 
     * @return the custom saving policy the session should use
     */
    @Override
    public SavingPolicy getSavingPolicy() {
        return savingPolicy != null ? savingPolicy : new IsModifiedSavingPolicy(transactionalEditingDomain);
    }

    /**
     * Indicates whether all resources (semantic and Danalysises) of this Session are whether
     * {@link ResourceStatus#SYNC} or {@link ResourceStatus#READONLY}.
     * 
     * @return true if all resources (semantic and Danalysises) of this Session are whether {@link ResourceStatus#SYNC}
     *         or {@link ResourceStatus#READONLY}, false otherwise
     */
    protected boolean allResourcesAreInSync() {
        return resourcesSynchronizer.allResourcesAreInSync();
    }

    /**
     * Indicates whether considered resources are whether {@link ResourceStatus#SYNC} or {@link ResourceStatus#READONLY}
     * .
     * 
     * @param resourcesToConsider
     *            the resources to inspect.
     * @return true if all considered are whether {@link ResourceStatus#SYNC} or {@link ResourceStatus#READONLY}, false
     *         otherwise
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
     * Set to true to do saving in a read-only EMF Transaction, false otherwise. Note that if the {@link SavingPolicy}
     * execute some EMF Command, this must be at false.
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
     * This method allows adding {@code ModelChangeTrigger} to the current session {@link SessionEventBroker}. This
     * method is called during the opening of the Session, before setting the open attribute to true and before
     * launching the SessionListener.OPENED notifications.
     */
    protected void initLocalTriggers() {
        Predicate<Notification> danglingRemovalPredicate = Predicates.or(DanglingRefRemovalTrigger.IS_DETACHMENT, DanglingRefRemovalTrigger.IS_ATTACHMENT);
        DanglingRefRemovalTrigger danglingRemovalTrigger = new DanglingRefRemovalTrigger(this);
        getEventBroker().addLocalTrigger(SessionEventBrokerImpl.asFilter(danglingRemovalPredicate), danglingRemovalTrigger);

        addRefreshEditorsListener();
        /*
         * Make sure these adapters are added after the rest, and in particular after the semantic cross-referencer, so
         * that they can rely on an up-to-date cross-referencer when invoked.
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
    public void open(final IProgressMonitor monitor) {
        try {
            monitor.beginTask(Messages.DAnalysisSessionImpl_openMsg, 33);
            SessionManager.INSTANCE.add(this);
            monitor.worked(1);
            notifyListeners(SessionListener.OPENING);
            monitor.worked(1);
            DslCommonPlugin.PROFILER.startWork(SiriusTasksKey.OPEN_SESSION_KEY);

            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).registerClient(resourcesSynchronizer);
            monitor.worked(1);
            this.representationNameListener = new RepresentationNameListener(this);
            monitor.worked(1);

            // Some modelers, as Xbase, modify the models during resolution. So we need to initialize the tracker in
            // a
            // write transaction
            RecordingCommand initializeTrackerCommand = new RecordingCommand(transactionalEditingDomain) {
                @Override
                protected void doExecute() {
                    tracker.initialize(monitor);
                }
            };
            transactionalEditingDomain.getCommandStack().execute(initializeTrackerCommand);
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

            // We initialize the cross referencer and put it as adapter of this session before initializing local
            // triggers to be sure that RepresentationChangedAdapter will always be after the cross referencer in
            // adapted objects. It avoids refreshing the Model Explorer listening to the RCA before updating the
            // cross
            // referencer with new created representations. And thus missing the new created representation under
            // its
            // associated model element when refreshing Model Explorer.
            registerResourceInCrossReferencer(sessionResource);

            DViewOperations.on(this).updateSelectedViewpointsData(new SubProgressMonitor(monitor, 10));
            initLocalTriggers();

            checkResourceErrors();

            super.setOpen(true);
            DslCommonPlugin.PROFILER.stopWork(SiriusTasksKey.OPEN_SESSION_KEY);
            notifyListeners(SessionListener.OPENED);
            monitor.worked(1);
            // CHECKSTYLE:OFF
        } catch (RuntimeException e) {
            // CHECKSTYLE:ON
            super.setOpen(true);
            close(new SubProgressMonitor(monitor, 10));
            throw e;
        } finally {
            monitor.done();
        }
    }

    /**
     * Throw an {@link RuntimeException} if one of the resource of the selected viewpoints or session resources contain
     * an error.
     */
    private void checkResourceErrors() {
        Collection<ResourceVersionMismatchDiagnostic> diagnostics = new ArrayList<>();

        // Prevent loading a session which VSM resource contains errors
        Collection<Viewpoint> activatedViewpoints = getSelectedViewpoints(true);
        for (Viewpoint viewpoint : activatedViewpoints) {
            for (Diagnostic diagnostic : viewpoint.eResource().getErrors()) {
                if (diagnostic instanceof ResourceVersionMismatchDiagnostic) {
                    diagnostics.add((ResourceVersionMismatchDiagnostic) diagnostic);
                }
            }
        }
        if (!diagnostics.isEmpty()) {
            throw new DescriptionResourceVersionMismatchException(diagnostics);
        }

        // Prevent loading a session which Aird resource contains errors
        Iterable<Resource> representationResources = Iterables.concat(getReferencedSessionResources(), Sets.newHashSet(getSessionResource()));
        for (Resource resource : representationResources) {
            for (Diagnostic diagnostic : resource.getErrors()) {
                if (diagnostic instanceof ResourceVersionMismatchDiagnostic) {
                    diagnostics.add((ResourceVersionMismatchDiagnostic) diagnostic);
                }
            }
        }
        if (!diagnostics.isEmpty()) {
            throw new AirdResourceVersionMismatchException(diagnostics);
        }
    }

    @Override
    public void close(IProgressMonitor monitor) {
        boolean isOpened = isOpen();
        // Some operations are done even if the session is not opened, as they are initialized in the constructor of the
        // DASI for example.
        if (isOpened) {
            ViewpointRegistry.getInstance().removeListener(this.vsmUpdater);
        }
        this.vsmUpdater = null;
        if (isOpened) {
            notifyListeners(SessionListener.CLOSING);
        }
        disableAndRemoveECrossReferenceAdapters();
        if (isOpened) {
            if (getRefreshEditorsListener() != null) {
                removeListener(getRefreshEditorsListener());
            }
        }
        getTransactionalEditingDomain().removeResourceSetListener(dRepresentationChangeListener);
        dRepresentationChangeListener = null;
        getTransactionalEditingDomain().removeResourceSetListener(changeIdUpdaterListener);
        changeIdUpdaterListener = null;
        refreshEditorsListeners = null;
        reloadingPolicy = null;
        savingPolicy = null;
        if (interpreter != null) {
            interpreter.dispose();
        }
        if (isOpened) {
            ResourceSetSync.getOrInstallResourceSetSync(transactionalEditingDomain).unregisterClient(resourcesSynchronizer);
            // We do not reset resourcesSynchronizer to null as some of the session
            // methods which are delegated to it must still be available when the
            // session is closed. It does not hold to any state or resource so
            // that's OK.
            ResourceSetSync.uninstallResourceSetSync(transactionalEditingDomain);
            super.setOpen(false);
        }
        /*
         * Let's clear the cross referencer of the VSM resource if it's still there (added by the
         * updateSelectedViewpointsData).
         */
        ResourceSet resourceSet = getTransactionalEditingDomain().getResourceSet();

        if (currentResourceCollector != null) {
            currentResourceCollector.dispose();
            currentResourceCollector = null;
        }
        interpreter = null;
        if (isOpened) {
            if (representationNameListener != null) {
                representationNameListener.dispose();
                representationNameListener = null;
            }
        }
        representationsChangeAdapter = null;
        // dispose the SessionEventBroker
        if (broker != null) {
            broker.dispose();
            broker = null;
        }

        CommandStackUtil.flushOperations(transactionalEditingDomain.getCommandStack());
        // Unload all referenced resources
        unloadAllResources(monitor);

        if (isOpened) {
            // Notify that the session is closed.
            notifyListeners(SessionListener.CLOSED);
            SessionManager.INSTANCE.remove(this);
        }
        if (tracker != null) {
            tracker.dispose();
            tracker = null;
        }
        crossReferencer = null;
        saver.dispose();

        transactionalEditingDomain.dispose();
        doDisposePermissionAuthority(resourceSet);
        ModelAccessorAdapter.disposeModelAccessor(resourceSet);
        transactionalEditingDomain = null;
        getActivatedViewpoints().clear();
        services = null;
        sessionResource = null;
        mainDAnalysis = null;
    }

    /**
     * Disable {@link SiriusCrossReferenceAdapter} resolveProxy capability on resource and all its contents
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
     * Enable {@link SiriusCrossReferenceAdapter} resolveProxy capability on resource and all its contents
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

    /**
     * Disposes the permission authority corresponding to this session (if any found).
     * 
     * @param resourceSet
     *            the resourceSet associated to the current session (given in parameter as the EditingDomain may already
     *            have been disposed)
     */
    protected void doDisposePermissionAuthority(ResourceSet resourceSet) {
        PermissionAuthorityRegistry.getDefault().getPermissionAuthority(resourceSet).dispose(resourceSet);
    }

    /**
     * Method called at {@link Session#close(IProgressMonitor)} to unload all referenced {@link Resource}s.
     * 
     * @param monitor
     */
    private void unloadAllResources(IProgressMonitor monitor) {
        ResourceSet rs = transactionalEditingDomain.getResourceSet();
        for (Resource resource : new ArrayList<Resource>(rs.getResources())) {
            ResourceStrategyRegistry.getInstance().unloadAtResourceSetDispose(resource, monitor);
        }
        for (Resource resource : new ArrayList<Resource>(rs.getResources())) {
            rs.getResources().remove(resource);
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
            services = new DAnalysisSessionServicesImpl(this);
        }
        return services;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return MessageFormat.format(Messages.DAnalysisSessionImpl_toStringMsg, getID());
    }

    /**
     * Get collection of available {@link DView} for the {@link RepresentationDescription}.
     * 
     * @param representationDescription
     *            the representation description.
     * @return available representation containers
     */
    public Collection<DView> getAvailableRepresentationContainers(RepresentationDescription representationDescription) {
        final Viewpoint viewpoint = new RepresentationDescriptionQuery(representationDescription).getParentViewpoint();
        Collection<DAnalysis> allAnalysis = allAnalyses();

        final List<DView> containers = new ArrayList<DView>();

        for (DAnalysis analysis : allAnalysis) {
            DView container = null;

            for (final DView view : analysis.getOwnedViews()) {
                if (view != null && viewpoint == view.getViewpoint() && view.eContainer() instanceof DAnalysis) {
                    container = view;
                    break;
                }
            } // for

            if (container != null) {
                containers.add(container);
            }
        }

        return containers;
    }

    /**
     * returns the representation file resources.
     * 
     * @return a collection of resource.
     */
    @Override
    public Collection<Resource> getSrmResources() {
        Collection<Resource> repFiles = new LinkedHashSet<>();

        if (transactionalEditingDomain != null && transactionalEditingDomain.getResourceSet() != null) {
            Collection<Resource> allResources = new ArrayList<Resource>(transactionalEditingDomain.getResourceSet().getResources());
            for (Resource res : allResources) {
                if (!res.getContents().isEmpty() && res.getContents().get(0) instanceof DRepresentation) {
                    repFiles.add(res);
                }
            }
        }
        return repFiles;
    }

    /**
     * Return the {@link SiriusReferenceFinder} of this Session.
     * 
     * @return the {@link SiriusReferenceFinder}
     */
    public SiriusReferenceFinder getSiriusReferenceFinder() {
        if (siriusReferenceFinder == null) {
            siriusReferenceFinder = new SiriusReferenceFinderImpl(this);
        }
        return siriusReferenceFinder;
    }
}
