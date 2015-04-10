/*******************************************************************************
 * Copyright (c) 2012, 2015 THALES GLOBAL SERVICES and others.
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
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListener;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DAnalysis;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

import com.google.common.base.Preconditions;

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
        this.session = Preconditions.checkNotNull(session);
    }

    /**
     * Add this listener on the editingDomain.
     */
    public void initialize() {
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
     * Remove this listener from the editingDomain.
     */
    public void dispose() {
        session.getTransactionalEditingDomain().removeResourceSetListener(this);
        session = null;
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

}
