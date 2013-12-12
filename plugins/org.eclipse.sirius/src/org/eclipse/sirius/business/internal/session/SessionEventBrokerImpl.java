/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.common.tools.api.util.Option;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Ordering;

/**
 * This class keeps track of registered listeners and notify them when something
 * did change on the elements they are listening. It has pretty much the same
 * focus as the GMF DiagramEventBroker except it's not GMF's dependent.
 * 
 * @author cbrun
 * 
 */
public class SessionEventBrokerImpl extends ResourceSetListenerImpl implements SessionEventBroker {

    private static Function<ModelChangeTrigger, Integer> getPriorityFunction = new Function<ModelChangeTrigger, Integer>() {

        public Integer apply(ModelChangeTrigger from) {
            return Integer.valueOf(from.priority());
        }
    };

    private TransactionalEditingDomain domain;

    private Multimap<EObject, ModelChangeTrigger> eObjectsToListeners = HashMultimap.create();

    private Map<EStructuralFeature, Multimap<EObject, ModelChangeTrigger>> featuresToListeners = Maps.newHashMap();

    private Multimap<Predicate<Notification>, ModelChangeTrigger> scopedTriggers = HashMultimap.create();

    /**
     * create a new broker.
     * 
     * @param domain
     *            the editing domain to listen.
     */
    public SessionEventBrokerImpl(TransactionalEditingDomain domain) {
        super(NotificationFilter.NOT_TOUCH);
        this.domain = domain;
        domain.addResourceSetListener(this);
    }

    /**
     * {@inheritDoc}
     */
    public void addLocalTrigger(EObject element, EStructuralFeature feature, ModelChangeTrigger trigger) {
        Multimap<EObject, ModelChangeTrigger> listeners = featuresToListeners.get(feature);
        if (listeners == null) {
            listeners = HashMultimap.create();
            featuresToListeners.put(feature, listeners);
        }
        listeners.put(element, trigger);

    }

    /**
     * {@inheritDoc}
     */
    public void addLocalTrigger(EObject element, ModelChangeTrigger trigger) {
        eObjectsToListeners.put(element, trigger);
    }

    private void collectListeners(Notification msg, EObject changedObj, Multimap<EObject, ModelChangeTrigger> map, Multimap<ModelChangeTrigger, Notification> result) {
        Collection<ModelChangeTrigger> listeners = map.get(changedObj);
        if (listeners != null) {
            for (ModelChangeTrigger preCommitListener : listeners) {
                result.put(preCommitListener, msg);
            }
        }
    }

    private void collectScopedListeners(final Notification msg, final EObject changedObj, Multimap<ModelChangeTrigger, Notification> result) {
        Iterable<Predicate<Notification>> filteredScoped = Iterables.filter(scopedTriggers.keys(), new Predicate<Predicate<Notification>>() {

            public boolean apply(Predicate<Notification> input) {
                return input.apply(msg);
            }
        });
        for (Predicate<Notification> predicate : filteredScoped) {
            Collection<ModelChangeTrigger> triggersValidForThisNotification = scopedTriggers.get(predicate);
            if (triggersValidForThisNotification != null) {
                for (ModelChangeTrigger modelChangeTrigger : triggersValidForThisNotification) {
                    result.put(modelChangeTrigger, msg);
                }
            }

        }
    }

    /**
     * {@inheritDoc}
     */
    public void addLocalTrigger(Predicate<Notification> scope, ModelChangeTrigger trigger) {
        if (!scopedTriggers.containsEntry(scope, trigger)) {
            scopedTriggers.put(scope, trigger);
        }
    }

    private Multimap<ModelChangeTrigger, Notification> collectListenersToNotify(List<Notification> notifications) {
        Multimap<ModelChangeTrigger, Notification> result = HashMultimap.create();
        for (Notification msg : notifications) {
            if (msg.getNotifier() instanceof EObject) {
                EObject changedObj = (EObject) msg.getNotifier();
                collectListeners(msg, changedObj, eObjectsToListeners, result);
                collectScopedListeners(msg, changedObj, result);
                if (msg.getFeature() instanceof EStructuralFeature) {
                    Multimap<EObject, ModelChangeTrigger> eObhectsToListenersMap = featuresToListeners.get(msg.getFeature());
                    if (eObhectsToListenersMap != null) {
                        collectListeners(msg, changedObj, eObhectsToListenersMap, result);
                    }
                }

            }

        }
        return result;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    private void removeListenerFromMap(ModelChangeTrigger listenerToRemove, Multimap<?, ModelChangeTrigger> map) {
        Iterator<ModelChangeTrigger> it = map.values().iterator();
        while (it.hasNext()) {
            ModelChangeTrigger cur = it.next();
            if (cur == listenerToRemove) {
                it.remove();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void removeLocalTrigger(ModelChangeTrigger listenerToRemove) {
        removeListenerFromMap(listenerToRemove, eObjectsToListeners);
        for (Multimap<EObject, ModelChangeTrigger> map : featuresToListeners.values()) {
            removeListenerFromMap(listenerToRemove, map);
        }
        removeListenerFromMap(listenerToRemove, scopedTriggers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        final Multimap<ModelChangeTrigger, Notification> listenersToNotify = collectListenersToNotify(event.getNotifications());
        if (listenersToNotify != null && listenersToNotify.size() > 0) {
            return new PreCommitPriorityNotifyListenersCommand(domain, listenersToNotify);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    public void dispose() {
        domain.removeResourceSetListener(this);
        eObjectsToListeners.clear();
        featuresToListeners.clear();
        scopedTriggers.clear();
    }

    /**
     * Specific command to notify listeners, regarding their priority and local
     * changes.
     * 
     * @author mporhel
     */
    private static final class PreCommitPriorityNotifyListenersCommand extends RecordingCommand {

        private final Multimap<ModelChangeTrigger, Notification> listenersToNotify;

        /**
         * Constructor.
         * 
         * @param domain
         *            the editing domain.
         * @param listenersToNotify
         *            the listeners to notify.
         */
        public PreCommitPriorityNotifyListenersCommand(TransactionalEditingDomain domain, Multimap<ModelChangeTrigger, Notification> listenersToNotify) {
            super(domain, "Notify listerners from SessionEventBroker");
            this.listenersToNotify = listenersToNotify;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void doExecute() {
            Ordering<ModelChangeTrigger> priorityOrdering = Ordering.natural().onResultOf(getPriorityFunction);
            List<ModelChangeTrigger> sortedKeys = priorityOrdering.sortedCopy(listenersToNotify.keySet());
            for (ModelChangeTrigger key : sortedKeys) {
                launchCommands(key);
            }
        }

        private void launchCommands(ModelChangeTrigger key) {
            Collection<Notification> notif = listenersToNotify.get(key);
            if (notif != null && notif.size() > 0) {
                Option<Command> triggerCmd = key.localChangesAboutToCommit(notif);
                if (triggerCmd.some() && triggerCmd.get().canExecute()) {
                    triggerCmd.get().execute();
                }

            }
        }
    }

}
