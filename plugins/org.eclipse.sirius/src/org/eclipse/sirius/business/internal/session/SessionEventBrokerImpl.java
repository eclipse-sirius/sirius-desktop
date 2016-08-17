/*******************************************************************************
 * Copyright (c) 2010, 2016 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.business.api.session.SessionEventBroker;
import org.eclipse.sirius.ext.base.Option;

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

        @Override
        public Integer apply(ModelChangeTrigger from) {
            return Integer.valueOf(from.priority());
        }
    };

    private TransactionalEditingDomain domain;

    private Multimap<EObject, ModelChangeTrigger> eObjectsToListeners = HashMultimap.create();

    private Map<EStructuralFeature, Multimap<EObject, ModelChangeTrigger>> featuresToListeners = Maps.newHashMap();

    private Multimap<NotificationFilter, ModelChangeTrigger> scopedTriggers = HashMultimap.create();

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
     * Converts a Guava predicate on EMF Notification into a NotificationFilter
     * as expected by the
     * {@link #addLocalTrigger(NotificationFilter, ModelChangeTrigger)} method.
     * 
     * @param pred
     *            a Guava predicate on EMF notifications.
     * @return an equivalent NotificationFilter.
     */
    public static NotificationFilter asFilter(final Predicate<Notification> pred) {
        return new NotificationFilter.Custom() {
            @Override
            public boolean matches(Notification notification) {
                return pred.apply(notification);
            }
        };
    }

    @Override
    public void addLocalTrigger(EObject element, EStructuralFeature feature, ModelChangeTrigger trigger) {
        Multimap<EObject, ModelChangeTrigger> listeners = featuresToListeners.get(feature);
        if (listeners == null) {
            listeners = HashMultimap.create();
            featuresToListeners.put(feature, listeners);
        }
        listeners.put(element, trigger);

    }

    @Override
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

    private void collectScopedListeners(final Notification msg, Multimap<ModelChangeTrigger, Notification> result) {
        Iterable<NotificationFilter> filteredScoped = Iterables.filter(scopedTriggers.keys(), new Predicate<NotificationFilter>() {
            @Override
            public boolean apply(NotificationFilter input) {
                return input.matches(msg);
            }
        });
        for (NotificationFilter predicate : filteredScoped) {
            Collection<ModelChangeTrigger> triggersValidForThisNotification = scopedTriggers.get(predicate);
            if (triggersValidForThisNotification != null) {
                for (ModelChangeTrigger modelChangeTrigger : triggersValidForThisNotification) {
                    result.put(modelChangeTrigger, msg);
                }
            }

        }
    }

    @Override
    public void addLocalTrigger(NotificationFilter scope, ModelChangeTrigger trigger) {
        if (!scopedTriggers.containsEntry(scope, trigger)) {
            scopedTriggers.put(scope, trigger);
        }
    }

    private Multimap<ModelChangeTrigger, Notification> collectListenersToNotify(List<Notification> notifications) {
        Multimap<ModelChangeTrigger, Notification> result = HashMultimap.create();
        for (Notification msg : notifications) {
            collectScopedListeners(msg, result);
            if (msg.getNotifier() instanceof EObject) {
                EObject changedObj = (EObject) msg.getNotifier();
                collectListeners(msg, changedObj, eObjectsToListeners, result);
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

    @Override
    public void removeLocalTrigger(ModelChangeTrigger listenerToRemove) {
        removeListenerFromMap(listenerToRemove, eObjectsToListeners);
        for (Multimap<EObject, ModelChangeTrigger> map : featuresToListeners.values()) {
            removeListenerFromMap(listenerToRemove, map);
        }
        removeListenerFromMap(listenerToRemove, scopedTriggers);
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        CompoundCommand compoundCommand = new CompoundCommand();
        Multimap<ModelChangeTrigger, Notification> listenersToNotify = collectListenersToNotify(event.getNotifications());
        if (listenersToNotify != null && !listenersToNotify.isEmpty()) {
            Ordering<ModelChangeTrigger> priorityOrdering = Ordering.natural().onResultOf(SessionEventBrokerImpl.getPriorityFunction);
            List<ModelChangeTrigger> sortedKeys = priorityOrdering.sortedCopy(listenersToNotify.keySet());
            for (ModelChangeTrigger key : sortedKeys) {
                Collection<Notification> notif = listenersToNotify.get(key);
                if (notif != null && !notif.isEmpty()) {
                    Option<Command> triggerCmdOption = key.localChangesAboutToCommit(notif);
                    if (triggerCmdOption.some() && triggerCmdOption.get().canExecute()) {
                        compoundCommand.append(triggerCmdOption.get());
                    }
                }
            }
        }
        return compoundCommand;
    }

    @Override
    public void dispose() {
        domain.removeResourceSetListener(this);
        eObjectsToListeners.clear();
        featuresToListeners.clear();
        scopedTriggers.clear();
    }

}
