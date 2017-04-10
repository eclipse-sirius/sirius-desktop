/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tools.internal.ui;

import java.util.Collection;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.ResourceQuery;

import com.google.common.collect.Sets;

/**
 * A class providing useful methods for refresh.
 * 
 * @author mbats
 */
public final class RefreshHelper {
    /**
     * Prevent instantiation.
     */
    private RefreshHelper() {
    }

    /**
     * Checks whether the changes we are notified changes semantic models (i.e. not just Sirius representations state).
     *
     * @param notifications
     *            the model changes.
     * @return <code>true</code> if the changes impact semantic models.
     */
    public static boolean isImpactingNotification(final Collection<Notification> notifications) {
        boolean isImpactingNotification = false;
        Set<EObject> alreadyDoneNotifiers = Sets.newHashSet();
        for (Notification notification : notifications) {
            Object notifier = notification.getNotifier();
            if (notifier instanceof EObject) {
                EObject eObjectNotifier = (EObject) notifier;
                if (!alreadyDoneNotifiers.contains(eObjectNotifier)) {
                    alreadyDoneNotifiers.add(eObjectNotifier);
                    Resource notifierResource = eObjectNotifier.eResource();
                    if (notifierResource != null && !new ResourceQuery(notifierResource).isAirdOrSrmResource()) {
                        isImpactingNotification = true;
                        break;
                    }
                }
            }
        }
        return isImpactingNotification;
    }
}
