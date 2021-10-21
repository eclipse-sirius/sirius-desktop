/*******************************************************************************
 * Copyright (c) 2016, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.tools.api.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.business.api.query.ResourceQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A class providing useful methods for refresh.
 * 
 * @author mbats
 */
public final class RefreshHelper {
    private static List<Predicate<Notification>> impactingNotificationPredicates = new ArrayList<>();

    /**
     * Prevent instantiation.
     */
    private RefreshHelper() {
    }

    /**
     * Checks whether at least one change of which we are notified, concerns a semantic model or a specific graphical
     * change (registered through {@link #registerImpactingNotification(Predicate)}).
     *
     * @param notifications
     *            the model changes.
     * @return <code>true</code> if the changes impact a semantic model or a specific graphical change.
     */
    public static boolean isImpactingNotification(final Collection<Notification> notifications) {
        // Create caches for the resource of a notifier and for the result of the method "new
        // ResourceQuery(Resource).isAirdOrSrmResource()". Both of them can be "time consuming".
        Map<EObject, Boolean> notifierIsInAirdOrSrmResource = new HashMap<>();
        Map<EObject, Resource> notifierWithResource = new HashMap<>();
        Set<EObject> alreadyDoneNotifiers = new HashSet<>();
        for (Notification notification : notifications) {
            Object notifier = notification.getNotifier();
            if (notifier instanceof EObject) {
                EObject eObjectNotifier = (EObject) notifier;
                if (!alreadyDoneNotifiers.contains(eObjectNotifier)
                        && isImpactingNotification(notification, eObjectNotifier, alreadyDoneNotifiers, notifierWithResource, notifierIsInAirdOrSrmResource)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks whether this notification concerns a semantic model change or a specific graphical change (registered
     * through {@link #registerImpactingNotification(Predicate)}).
     *
     * @param notification
     *            the model change.
     * @param notifier
     *            the EObject which is concerned by this notification
     * @param alreadyDoneNotifiers
     *            list of notifiers that have already been checked before
     * @param notifierWithResource
     *            a cache map used to retrieve the resource from a notifier
     * @param notifierIsInAirdOrSrmResource
     *            a cache map used to retrieve the result of the method
     *            <code>ResourceQuery(Resource).isAirdOrSrmResource()</code> from a notifier
     * @return <code>true</code> if the change impact a semantic model or a specific graphical change.
     */
    protected static boolean isImpactingNotification(Notification notification, EObject notifier, Set<EObject> alreadyDoneNotifiers, Map<EObject, Resource> notifierWithResource,
            Map<EObject, Boolean> notifierIsInAirdOrSrmResource) {
        Resource notifierResource = notifierWithResource.get(notifier);
        Boolean isAirdOrSrmResourceCache = notifierIsInAirdOrSrmResource.get(notifier);
        boolean isImpactingNotification = false;
        if (notifierResource != null) {
            isImpactingNotification = !isAirdOrSrmResourceCache.booleanValue();
        } else {
            notifierResource = notifier.eResource();
            if (notifierResource != null) {
                if (impactingNotificationPredicates.isEmpty()) {
                    // If the impactingNotificationPredicates is not empty we must check every notification but in other
                    // case, you have only one check by notifier.
                    alreadyDoneNotifiers.add(notifier);
                }
                isImpactingNotification = !new ResourceQuery(notifierResource).isAirdOrSrmResource();
                notifierWithResource.put(notifier, notifierResource);
                notifierIsInAirdOrSrmResource.put(notifier, !isImpactingNotification);
            }
        }
        if (notifierResource != null && !isImpactingNotification && isSpecificImpactingNotification(notification)) {
            // The impacting notification is a graphical one, so if we are in manual refresh, we must add
            // the corresponding representation to the force refresh list
            Option<DRepresentation> optionalDRepresentation = Options.newNone();
            Session session = SessionManager.INSTANCE.getExistingSession(notifierResource.getURI());
            if (session == null) {
                // Maybe the notifier resource is not the session resource (for example a SRM resource in
                // Collab), in this case, use a more global session search.
                optionalDRepresentation = new EObjectQuery(notifier).getRepresentation();
                if (optionalDRepresentation.some()) {
                    session = new EObjectQuery(optionalDRepresentation.get()).getSession();
                }
            }
            if (session != null && !session.getSiriusPreferences().isAutoRefresh()) {
                if (!optionalDRepresentation.some()) {
                    optionalDRepresentation = new EObjectQuery(notifier).getRepresentation();
                }
                if (optionalDRepresentation.some()) {
                    session.getRefreshEditorsListener().addRepresentationToForceRefresh(optionalDRepresentation.get());
                }
            }
            isImpactingNotification = true;
        }
        return isImpactingNotification;
    }

    /**
     * Check this notification with all registered predicates.
     * 
     * @param notification
     *            The notification to check
     * @return true if at least one predicate consider this notification as impacting, false otherwise.
     */
    private static boolean isSpecificImpactingNotification(Notification notification) {
        // Check graphical modification considered as impacting by customers
        for (Predicate<Notification> predicate : impactingNotificationPredicates) {
            if (predicate.test(notification)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Register a predicate to consider a graphical notification (notification concerning the representations file and
     * not the semantic file) as impacting and requiring a refresh of the diagram.
     * 
     * @param impactingNotificationPredicate
     *            a predicate to consider a notification as impacting.
     */
    public static void registerImpactingNotification(Predicate<Notification> impactingNotificationPredicate) {
        Objects.requireNonNull(impactingNotificationPredicate, Messages.RefreshHelper_notNullPredicate);
        impactingNotificationPredicates.add(impactingNotificationPredicate);
    }

    /**
     * Unregister a predicate, previously register through {@link #registerImpactingNotification(Predicate)}.
     * 
     * @param impactingNotificationPredicate
     *            a predicate that must no longer consider a notification as impacting.
     */
    public static void unregisterImpactingNotification(Predicate<Notification> impactingNotificationPredicate) {
        Objects.requireNonNull(impactingNotificationPredicate, Messages.RefreshHelper_notNullPredicate);
        impactingNotificationPredicates.remove(impactingNotificationPredicate);
    }
}
