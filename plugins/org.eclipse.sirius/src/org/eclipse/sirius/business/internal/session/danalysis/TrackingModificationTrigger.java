/*******************************************************************************
 * Copyright (c) 2014, Obeo.
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
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.ModelChangeTrigger;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.Sets;

/**
 * A change trigger which maintains the isModified flag on resources without
 * relying on a specific EContentAdapter.
 * 
 * @author cedric
 */
public class TrackingModificationTrigger implements ModelChangeTrigger {
    /**
     * Filter notifications which reflect a change of values that are
     * serialized.
     */
    public static final NotificationFilter IS_CHANGE = new NotificationFilter.Custom() {
        @Override
        public boolean matches(Notification notification) {
            return !notification.isTouch() && !new NotificationQuery(notification).isTransientNotification();
        }
    };

    private final TransactionalEditingDomain domain;

    /**
     * Create the trigger.
     * 
     * @param domain
     *            the editing domain to use to create new commands.
     */
    public TrackingModificationTrigger(TransactionalEditingDomain domain) {
        this.domain = domain;
    }

    @Override
    public Option<Command> localChangesAboutToCommit(Collection<Notification> notifications) {
        /*
         * eContainer() calls can become costly and as such eResource() ones.
         * Let's make sure first we won't process several time the same EObject
         * as often there are several changes per EObject.
         */
        final Set<Resource> impactedResources = Sets.newLinkedHashSet();
        Set<EObject> impactedObjects = Sets.newLinkedHashSet();
        for (Notification change : notifications) {
            if (change.getNotifier() instanceof EObject && !(change.getNotifier() instanceof Resource)) {
                EObject changed = (EObject) change.getNotifier();
                if (impactedObjects.add(changed)) {
                    Resource res = changed.eResource();
                    if (res != null && !res.isModified()) {
                        impactedResources.add(res);
                    }
                }
            }
        }
        if (!impactedResources.isEmpty()) {
            Command setIsModified = new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    for (Resource resource : impactedResources) {
                        resource.setModified(true);
                    }
                }
            };
            return Options.newSome(setIsModified);
        }
        return Options.newNone();
    }

    @Override
    public int priority() {
        /*
         * this trigger should work best if pretty much all the others already
         * did their work and as such modified their resources.
         */
        return 10;
    }

}
