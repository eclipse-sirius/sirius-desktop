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
package org.eclipse.sirius.business.internal.session.danalysis;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.ResourceSetChangeEvent;
import org.eclipse.emf.transaction.ResourceSetListenerImpl;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.sirius.business.api.helper.RepresentationHelper;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * Listen to any change to a {@link DRepresentation} or one of its {@link DRepresentationElement} and update the
 * associated {@link DRepresentationDescriptorn} change id.
 * 
 * @author <a href="mailto:pierre.guilet@obeo.fr">Pierre Guilet</a>
 *
 */
public class ChangeIdUpdaterListener extends ResourceSetListenerImpl {

    private final DAnalysisSessionImpl dAnalysisSessionImpl;

    /**
     * @param dAnalysisSessionImpl
     */
    ChangeIdUpdaterListener(DAnalysisSessionImpl dAnalysisSessionImpl) {
        this.dAnalysisSessionImpl = dAnalysisSessionImpl;
    }

    @Override
    public boolean isPrecommitOnly() {
        return true;
    }

    @Override
    public boolean isAggregatePrecommitListener() {
        return true;
    }

    @Override
    public Command transactionAboutToCommit(ResourceSetChangeEvent event) throws RollbackException {
        List<Notification> notifications = event.getNotifications();
        Set<EObject> notifierWithoutRepresentationDescriptors = new HashSet<>();
        Map<EObject, DRepresentationDescriptor> notifierToDRepMap = new HashMap<>();
        Set<DRepresentationDescriptor> descriptorsToUpdate = new HashSet<>();
        // Descriptors to ignore because their change ids have already been updated (because of Rollback command or
        // a diff-merge for example).
        Set<DRepresentationDescriptor> descriptorsToIgnore = new HashSet<>();

        Iterator<Notification> notifIterator = notifications.iterator();

        while (notifIterator.hasNext()) {
            Notification notification = notifIterator.next();
            Object notifier = notification.getNotifier();
            boolean isEObject = notifier instanceof EObject;
            boolean isTransient = notification.getFeature() instanceof EStructuralFeature && ((EStructuralFeature) notification.getFeature()).isTransient();
            if (isEObject && !isTransient && ViewpointPackage.Literals.IDENTIFIED_ELEMENT.isInstance(notifier)) {
                final DRepresentationDescriptor representationDescriptor = getDRepresentationDescriptor((EObject) notifier, notifierWithoutRepresentationDescriptors, notifierToDRepMap);
                if (representationDescriptor != null) {
                    descriptorsToUpdate.add(representationDescriptor);
                }
                if (ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR.isInstance(notifier)
                        && ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__CHANGE_ID.equals(notification.getFeature())) {
                    // Ignore descriptor with a change of "ChangeId"
                    descriptorsToIgnore.add((DRepresentationDescriptor) notifier);
                }
            } else if (isEObject && !isTransient) {
                EClass eClass = ((EObject) notifier).eClass();
                EPackage ePackage = eClass.getEPackage();
                if ("notation".equals(ePackage.getNsPrefix())) { //$NON-NLS-1$
                    final DRepresentationDescriptor representationDescriptor = getDRepresentationDescriptor((EObject) notifier, notifierWithoutRepresentationDescriptors, notifierToDRepMap);
                    if (representationDescriptor != null) {
                        descriptorsToUpdate.add(representationDescriptor);

                    }
                }
            }
        }
        descriptorsToUpdate.removeAll(descriptorsToIgnore);
        if (!descriptorsToUpdate.isEmpty()) {
            RecordingCommand changeIdRecordingCommand = new RecordingCommand(this.dAnalysisSessionImpl.getTransactionalEditingDomain()) {
                @Override
                protected void doExecute() {
                    for (DRepresentationDescriptor dRepresentationDescriptor : descriptorsToUpdate) {
                        RepresentationHelper.updateChangeId(dRepresentationDescriptor);
                    }
                }
            };
            return changeIdRecordingCommand;
        }
        return null;
    }

    private DRepresentationDescriptor getDRepresentationDescriptor(EObject eObject, Set<EObject> notifierWithoutRepresentationDescriptors,
            Map<EObject, DRepresentationDescriptor> notifierToDRepMap) {
        DRepresentationDescriptor dRepresentationDescriptor = null;
        DRepresentationDescriptor repAssociatedToEObject = notifierToDRepMap.get(eObject);
        if (repAssociatedToEObject == null && !notifierWithoutRepresentationDescriptors.contains(eObject)) {
            EObject eContainer = eObject.eContainer();
            if (!(eObject.eContainingFeature() != null && eObject.eContainingFeature().isTransient())) {
                // We check for a descriptor from the top element to the current element to be sure the current
                // element is not related to a transient feature.
                if (eContainer != null && !(eObject instanceof DRepresentation)) {
                    dRepresentationDescriptor = getDRepresentationDescriptor(eContainer, notifierWithoutRepresentationDescriptors, notifierToDRepMap);
                    if (dRepresentationDescriptor == null) {
                        // the parent is not associated to any descriptor so are the children.
                        notifierWithoutRepresentationDescriptors.add(eObject);
                    } else {
                        // we have a valid descriptor from the parent so we associate it to this eObject in case
                        // we parse it again later.
                        notifierToDRepMap.put(eObject, dRepresentationDescriptor);
                    }
                } else if (eObject instanceof DRepresentation) {
                    // we have reached the parent representation so we retrieve its descriptor.
                    dRepresentationDescriptor = new DRepresentationQuery((DRepresentation) eObject).getRepresentationDescriptor();
                    notifierToDRepMap.put(eObject, dRepresentationDescriptor);
                }
            } else {
                // eObject is associated to a transient feature so we ignore it.
                notifierWithoutRepresentationDescriptors.add(eObject);
            }
        } else {
            dRepresentationDescriptor = repAssociatedToEObject;
        }
        return dRepresentationDescriptor;
    }
}