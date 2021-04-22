/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.representation;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.query.DRepresentationDescriptorQuery.DRepresentationDescriptorValidityAdapter;
import org.eclipse.sirius.business.api.session.danalysis.DAnalysisSession;
import org.eclipse.sirius.common.tools.api.query.NotificationQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Listener that clears the sub diagram decoration descriptors when a {@link DRepresentation} is either created or
 * deleted.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 */
public class DRepresentationChangeListener extends ResourceSetListenerImpl {

    /**
     * Simple marker to indicate that the shouldHaveSubDiagDecoration returned false.
     * 
     */
    public static final class NoSubDecorationDescriptor {
    
    }

    private DAnalysisSession session;

    /**
     * Default constructor.
     * 
     * @param session
     *            the current session
     */
    public DRepresentationChangeListener(DAnalysisSession session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void resourceSetChanged(ResourceSetChangeEvent event) {
        List<Notification> notifications = event.getNotifications();
        boolean subDiagramDecorationDesciptorCleared = false;
        Collection<DRepresentation> allLoadedRepresentations = DialectManager.INSTANCE.getAllLoadedRepresentations(session);
        Set<DRepresentationDescriptor> dRepresentationDescriptorsSetToValidate = new LinkedHashSet<DRepresentationDescriptor>();
        for (Notification notification : notifications) {
            if (notification.getNewValue() instanceof DRepresentation || notification.getOldValue() instanceof DRepresentation) {
                allLoadedRepresentations.stream().forEach(rep -> rep.getUiState().getSubDiagramDecorationDescriptors().clear());
                subDiagramDecorationDesciptorCleared = true;
                break;
            } else if (notification.getNotifier() instanceof DView && ViewpointPackage.eINSTANCE.getDView_OwnedRepresentationDescriptors().equals(notification.getFeature())) {
                // Detection of addition or deletion of a DRepresentationDescriptor triggers here an update of its
                // validity adapter
                switch (notification.getEventType()) {
                case Notification.REMOVE:
                    dRepresentationDescriptorsSetToValidate.add((DRepresentationDescriptor) notification.getOldValue());
                    break;
                case Notification.REMOVE_MANY:
                    dRepresentationDescriptorsSetToValidate.addAll((Collection<? extends DRepresentationDescriptor>) notification.getOldValue());
                    break;
                case Notification.ADD:
                    dRepresentationDescriptorsSetToValidate.add((DRepresentationDescriptor) notification.getNewValue());
                    break;
                case Notification.ADD_MANY:
                    dRepresentationDescriptorsSetToValidate.addAll((Collection<? extends DRepresentationDescriptor>) notification.getNewValue());
                    break;
                default:
                    break;
                }
            } else if (notification.getNotifier() instanceof DRepresentationDescriptor) {
                dRepresentationDescriptorsSetToValidate.add((DRepresentationDescriptor) notification.getNotifier());
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

        for (DRepresentationDescriptor dRepresentationDescriptorToValidate : dRepresentationDescriptorsSetToValidate) {
            Optional<DRepresentationDescriptorValidityAdapter> findFirst = dRepresentationDescriptorToValidate.eAdapters().stream().filter(DRepresentationDescriptorValidityAdapter.class::isInstance)
                    .map(DRepresentationDescriptorValidityAdapter.class::cast).findFirst();
            if (findFirst.isPresent()) {
                findFirst.get().triggerRepresentationValidation();
            }
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