/*******************************************************************************
 * Copyright (c) 2012, 2014 THALES GLOBAL SERVICES and others.
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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.query.URIQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ecore.extender.tool.api.ModelUtils;
import org.eclipse.sirius.ext.emf.EReferencePredicate;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A {@link ResourceSetListener} to update the
 * {@link ViewpointPackage#DANALYSIS__MODELS} feature according changes in
 * representations resources. This class also contains some methods to load + *
 * resources and detects new semantic resources.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DAnalysisRefresher extends ResourceSetListenerImpl implements ResourceSetListener {

    private Session session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the {@link Session} on which listens model changes.
     */
    public DAnalysisRefresher(Session session) {
        this.session = session;
    }

    /**
     * Add this listener on the editingDomain.
     */
    public void init() {
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    /**
     * Overridden to say that we are interested only in added elements.
     * 
     * {@inheritDoc}
     */
    @Override
    public NotificationFilter getFilter() {
        return NotificationFilter.createEventTypeFilter(Notification.ADD);
    }
    
    @Override
    public boolean isPrecommitOnly() {
        return true;
    }
    
    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    /**
     * Overridden to update the {@link DAnalysis#getModels()} reference
     * according to new added {@link DSemanticDecorator}.
     * 
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand refreshDAnalysisCmds = new CompoundCommand();
        Map<DAnalysis, Set<EObject>> rootSemanticResourceEltsPerRepresentationsResource = getRootSemanticResourceEltsPerRepresentationsResource(event.getNotifications());
        for (Entry<DAnalysis, Set<EObject>> entry : rootSemanticResourceEltsPerRepresentationsResource.entrySet()) {
            DAnalysis dAnalysis = entry.getKey();
            Set<EObject> rootSemanticResourceElts = entry.getValue();
            if (!dAnalysis.getModels().containsAll(rootSemanticResourceElts)) {
                rootSemanticResourceElts.removeAll(dAnalysis.getModels());
                Command refreshDAnalysisCmd = AddCommand.create(event.getEditingDomain(), dAnalysis, ViewpointPackage.Literals.DANALYSIS__MODELS, rootSemanticResourceElts);
                refreshDAnalysisCmds.append(refreshDAnalysisCmd);

            }
        }
        return refreshDAnalysisCmds;
    }

    /**
     * Check the resources in the resourceSet and add all new resources as
     * semantic resources of this session. New resources are :
     * <UL>
     * <LI>Resources that are not in the <code>knownResources</code> list</LI>
     * <LI>Resources that are not in the semantic resources of this session</LI>
     * <LI>Resources that are not in the referenced representations files
     * resources of this session</LI>
     * <LI>Resources that are not the Sirius environment resource</LI>
     * </UL>
     * 
     * @param knownResources
     *            List of resources that is already loaded before the resolveAll
     *            of the representations file load.
     */
    public void addAutomaticallyLoadedResourcesToSemanticResources(List<Resource> knownResources) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        List<Resource> resourcesAfterLoadOfSession = Lists.newArrayList(domain.getResourceSet().getResources());
        // Remove the known resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), knownResources);
        // Remove the known semantic resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), session.getSemanticResources());
        // Remove the known referenced representations file resources
        Iterators.removeAll(resourcesAfterLoadOfSession.iterator(), session.getReferencedSessionResources());
        // Remove the Sirius Environment resource
        final Iterable<Resource> newSemanticResourcesIterator = Iterables.filter(resourcesAfterLoadOfSession, new Predicate<Resource>() {
            public boolean apply(Resource resource) {
                return !(new URIQuery(resource.getURI()).isEnvironmentURI());
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

    /**
     * Resolve all resources of the resource set of this session. Some
     * references are ignored (derived features, containment/container
     * references).
     */
    public void forceLoadingOfEveryLinkedResource() {
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
    public void resolveAllVSMResources(Collection<DAnalysis> allAnalysis) {
        List<Resource> resolvedResources = Lists.newArrayList();
        for (DAnalysis dAnalysis : allAnalysis) {
            for (DView dView : dAnalysis.getOwnedViews()) {
                if (dView.getViewpoint() != null && dView.getViewpoint().eResource() != null) {
                    Resource vsmResource = dView.getViewpoint().eResource();
                    if (!resolvedResources.contains(vsmResource)) {
                        ModelUtils.resolveAll(vsmResource, true);
                        resolvedResources.add(vsmResource);
                    }
                }
            }
        }
    }

    /**
     * Get for each {@link DAnalysis} impacted a set of semantic resource root
     * element whose semantic resource content is referenced by a
     * {@link DSemanticDecorator} from the specified collection of
     * {@link Notification}s.
     * 
     * @param notifications
     *            the specified collection of {@link Notification}
     * @return a map associating for each {@link DAnalysis} semantic resource
     *         root element
     */
    private Map<DAnalysis, Set<EObject>> getRootSemanticResourceEltsPerRepresentationsResource(Collection<Notification> notifications) {
        Map<DAnalysis, Set<EObject>> rootSemanticResourceEltsPerRepresentationsResource = new HashMap<DAnalysis, Set<EObject>>();
        for (Notification notification : notifications) {
            if (Notification.ADD == notification.getEventType()) {
                Object newValue = notification.getNewValue();
                if (newValue instanceof DSemanticDecorator) {
                    DSemanticDecorator dSemanticDecorator = (DSemanticDecorator) newValue;
                    Resource representationsResource = dSemanticDecorator.eResource();
                    if (representationsResource != null && dSemanticDecorator.getTarget() != null) {
                        EObject representationsResourceRoot = representationsResource.getContents().get(0);
                        assert representationsResourceRoot instanceof DAnalysis;
                        DAnalysis dAnalysis = (DAnalysis) representationsResourceRoot;
                        Resource targetResource = dSemanticDecorator.getTarget().eResource();
                        updateMap(rootSemanticResourceEltsPerRepresentationsResource, targetResource, dAnalysis);
                    }
                }
            }
        }
        return rootSemanticResourceEltsPerRepresentationsResource;
    }

    private void updateMap(Map<DAnalysis, Set<EObject>> rootSemanticResourceEltsPerRepresentationsResource, Resource targetResource, DAnalysis dAnalysis) {
        if (targetResource != null) {
            EObject rootSemanticResourceElement = targetResource.getContents().get(0);
            Set<EObject> rootSemanticResourceElts = rootSemanticResourceEltsPerRepresentationsResource.get(dAnalysis);
            if (rootSemanticResourceElts == null) {
                rootSemanticResourceElts = new HashSet<EObject>();
                rootSemanticResourceEltsPerRepresentationsResource.put(dAnalysis, rootSemanticResourceElts);
            }
            rootSemanticResourceElts.add(rootSemanticResourceElement);
        }
    }

    /**
     * Remove this listener from the editingDomain.
     */
    public void dispose() {
        session.getTransactionalEditingDomain().removeResourceSetListener(this);
        session = null;
    }

}
