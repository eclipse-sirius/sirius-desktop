/*******************************************************************************
 * Copyright (c) 2011, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.refresh;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.internal.session.SessionEventBrokerImpl;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.SiriusPlugin;

import com.google.common.base.Predicate;

/**
 * Specific {@link ModelChangeTrigger} per Session to synchronize the GMF notation models from the Session Resource
 * changes (DAnalysis).
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public final class SiriusDiagramSessionEventBroker implements ModelChangeTrigger {

    /**
     * Priority of this {@link ModelChangeTrigger}.
     */
    public static final int PRIORITY = 4;

    /**
     * Use of Weak reference to have {@link SiriusDiagramSessionEventBroker} automatically garbage collected once the
     * {@link Session}'s SessionEventBroker has disposed all its ModelChangeTrigger
     **/
    private static final Map<Session, SiriusDiagramSessionEventBroker> INSTANCES_MAP = new WeakHashMap<Session, SiriusDiagramSessionEventBroker>();

    private static Predicate<Notification> scope = new DSemanticDiagramScopePredicate();

    private static SiriusGMFSynchronizerDispatcher viewpointGMFSynchronizerDispatcher = new SiriusGMFSynchronizerDispatcher();

    private SiriusDiagramSessionEventBroker() {
        // Can't instantiate this singleton
    }

    /**
     * Return a SiriusDiagramSessionEventBroker for this session, create a new one if not already existing.
     * 
     * @param session
     *            {@link Session} for which to get a SiriusDiagramSessionEventBroker
     * 
     * @return a SiriusDiagramSessionEventBroker for this session
     */
    public static SiriusDiagramSessionEventBroker getInstance(Session session) {
        return initializeDiagramEventBroker(session);
    }

    private static SiriusDiagramSessionEventBroker initializeDiagramEventBroker(Session session) {
        SiriusDiagramSessionEventBroker reference = INSTANCES_MAP.get(session);
        if (reference == null) {
            SiriusDiagramSessionEventBroker viewpointDiagramSessionEventBroker = new SiriusDiagramSessionEventBroker();
            session.getEventBroker().addLocalTrigger(SessionEventBrokerImpl.asFilter(scope), viewpointDiagramSessionEventBroker);
            reference = viewpointDiagramSessionEventBroker;
            INSTANCES_MAP.put(session, reference);
        }
        return reference;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        if (!SiriusPlugin.getDefault().isRepairInProgress()) {
            TransactionalEditingDomain domain = getSession().getTransactionalEditingDomain();
            Option<Command> triggerCommand = Options.newSome(viewpointGMFSynchronizerDispatcher.getGMFNotationModelSynchronizationCmd(domain, notifications));
            return triggerCommand;
        } else {
            return Options.newNone();
        }
    }

    private Session getSession() {
        Session session = null;
        for (Entry<Session, SiriusDiagramSessionEventBroker> entry : INSTANCES_MAP.entrySet()) {
            if (this.equals(entry.getValue())) {
                session = entry.getKey();
                break;
            }
        }
        return session;
    }

    /**
     * Return the low priority to get call after others synchronizers (DDiagramSynchronizer, DTreeSynchronizer,
     * DTableSynchronizer, ...), excepts for RefreshLayoutTrigger from sequence dialect which must be called of this one
     * to avoid StackOverflow.
     * 
     * {@inheritDoc}
     */
    @Override
    public int priority() {
        return PRIORITY;
    }

}
