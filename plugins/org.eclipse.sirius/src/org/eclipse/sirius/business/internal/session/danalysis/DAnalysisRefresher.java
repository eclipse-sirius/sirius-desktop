/*******************************************************************************
 * Copyright (c) 2012, 2020 THALES GLOBAL SERVICES and others.
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

import java.util.Collection;
import java.util.Map.Entry;
import java.util.Objects;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.resource.ResourceDescriptor;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * A {@code ResourceSetListener} to update the {@link ViewpointPackage#DANALYSIS__MODELS} feature according changes in
 * representations resources. This class also contains some methods to load + * resources and detects new semantic
 * resources.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DAnalysisRefresher extends ResourceSetListenerImpl {
    /**
     * The events of interest for {@link DAnalysisRefresher}.
     */
    private static final NotificationFilter DANALYSIS_REFRESHER_EVENTS = NotificationFilter.createEventTypeFilter(Notification.ADD);

    private DAnalysisSessionImpl session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the session on which listens model changes.
     */
    public DAnalysisRefresher(DAnalysisSessionImpl session) {
        this.session = Objects.requireNonNull(session);
    }

    /**
     * Add this listener on the editingDomain.
     */
    public void initialize() {
        session.getTransactionalEditingDomain().addResourceSetListener(this);
    }

    @Override
    public NotificationFilter getFilter() {
        return DAnalysisRefresher.DANALYSIS_REFRESHER_EVENTS;
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
     * Overridden to update the {@link DAnalysis#getModels()} reference according to new added
     * {@link DSemanticDecorator}.
     * 
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        Multimap<DAnalysis, ResourceDescriptor> referencedSemanticModels = getRootSemanticResourceEltsPerRepresentationsResource(event.getNotifications());
        CompoundCommand result = new CompoundCommand();
        for (Entry<DAnalysis, Collection<ResourceDescriptor>> entry : referencedSemanticModels.asMap().entrySet()) {
            DAnalysis analysis = entry.getKey();
            Collection<ResourceDescriptor> semanticResourceDescriptors = entry.getValue();
            if (!analysis.getSemanticResources().containsAll(semanticResourceDescriptors)) {
                semanticResourceDescriptors.removeAll(analysis.getSemanticResources());
                result.append(AddCommand.create(event.getEditingDomain(), analysis, ViewpointPackage.Literals.DANALYSIS__SEMANTIC_RESOURCES, semanticResourceDescriptors));
            }
        }
        return result;
    }

    /**
     * Remove this listener from the editingDomain.
     */
    public void dispose() {
        if (session != null) {
            session.getTransactionalEditingDomain().removeResourceSetListener(this);
        }
        session = null;
    }

    /**
     * Get for each {@link DAnalysis} impacted a set of semantic resource root element whose semantic resource content
     * is referenced by a {@link DSemanticDecorator} from the specified collection of {@link Notification}s.
     * 
     * @param notifications
     *            the specified collection of {@link Notification}
     * @return a map associating for each {@link DAnalysis} semantic resource descriptor.
     */
    private Multimap<DAnalysis, ResourceDescriptor> getRootSemanticResourceEltsPerRepresentationsResource(Collection<Notification> notifications) {
        Multimap<DAnalysis, ResourceDescriptor> semanticResourceDescriptors = HashMultimap.create();
        for (Notification notification : notifications) {
            if (Notification.ADD == notification.getEventType() && notification.getNewValue() instanceof DSemanticDecorator) {
                DSemanticDecorator decorator = (DSemanticDecorator) notification.getNewValue();
                DAnalysis analysis = new EObjectQuery(decorator).getDAnalysis();
                if (decorator.getTarget() != null && analysis != null && analysis.eResource() != null) {
                    Resource targetResource = decorator.getTarget().eResource();
                    registerNewReferencedResource(semanticResourceDescriptors, analysis, targetResource);
                }
            }
        }
        return semanticResourceDescriptors;
    }

    private void registerNewReferencedResource(Multimap<DAnalysis, ResourceDescriptor> referencedSemanticModels, DAnalysis analysis, Resource semanticResource) {
        if (semanticResource != null && !new ResourceQuery(semanticResource).isAirdOrSrmResource()) {
            referencedSemanticModels.put(analysis, new ResourceDescriptor(semanticResource.getURI()));
        }
    }
}
