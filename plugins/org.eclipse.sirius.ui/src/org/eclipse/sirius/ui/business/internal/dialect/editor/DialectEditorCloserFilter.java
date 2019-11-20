/*******************************************************************************
 * Copyright (c) 2012, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.ui.business.internal.dialect.editor;

import java.util.Collection;
import java.util.Optional;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.sirius.business.internal.session.danalysis.DanglingRefRemovalTrigger;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.DView;
import org.eclipse.sirius.viewpoint.ViewpointPackage;

/**
 * A {@link NotificationFilter} to be notifier of deletion of the current {@link DRepresentationDescriptor} target if it
 * is a DSemanticDecorator.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DialectEditorCloserFilter extends NotificationFilter.Custom {

    private DRepresentationDescriptor dRepDescriptor;

    /**
     * Default constructor.
     * 
     * @param dRepresentationDescriptor
     *            the {@link DRepresentationDescriptor} on which to be notifier of target deletion
     */
    public DialectEditorCloserFilter(DRepresentationDescriptor dRepresentationDescriptor) {
        Assert.isNotNull(dRepresentationDescriptor);
        this.dRepDescriptor = dRepresentationDescriptor;
    }

    @Override
    public boolean matches(Notification notification) {
        return !notification.isTouch() && (isTargetUnset(notification) || isRepresentationDeletion(notification) || isTargetDetachment(notification));
    }

    /**
     * In some cases (external resource modification for instance) the dRepDescriptor can be "proxyfied". This method
     * try to resolve this proxy using the notifier but without resolving any additional resource. This is necessary
     * since the {@link DRepresentationDescriptor#repPath} introduction: the {@link DRepresentationDescriptor} needs to
     * be attached to a resource to resolve the repPath URI.
     * 
     * @param notification
     *            the current {@link Notification}.
     */
    private void resolveDRepDescriptorProxy(Notification notification) {
        if (dRepDescriptor.eIsProxy()) {
            dRepDescriptor = Optional.ofNullable(notification.getNotifier()).filter(EObject.class::isInstance).map(EObject.class::cast).map(eObject -> eObject.eResource())
                    .map(eResource -> eResource.getResourceSet()).map(resourceSet -> resourceSet.getEObject(EcoreUtil.getURI(dRepDescriptor), false))
                    .filter(DRepresentationDescriptor.class::isInstance).map(DRepresentationDescriptor.class::cast).orElse(dRepDescriptor);
        }

    }

    private boolean isTargetUnset(Notification notification) {
        boolean unsetNotification = isUnsetNotification(notification);
        return unsetNotification && notification.getNotifier() == dRepDescriptor && notification.getFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET;
    }

    private boolean isUnsetNotification(Notification notification) {
        int eventType = notification.getEventType();
        boolean setToNull = eventType == Notification.SET && notification.getNewValue() == null;
        return setToNull || eventType == Notification.REMOVE || eventType == Notification.UNSET || eventType == Notification.REMOVE_MANY;
    }

    private boolean isRepresentationDeletion(Notification notification) {
        boolean representationDeleted = false;
        resolveDRepDescriptorProxy(notification);
        if (notification.getFeature() == ViewpointPackage.Literals.DVIEW__OWNED_REPRESENTATION_DESCRIPTORS && wasInOldValue(notification, dRepDescriptor)) {
            // If the representation descriptor eContainer is still a DView,
            // this remove notification does not indicate a delete but a move.
            // No need to close the editor.
            representationDeleted = !(dRepDescriptor.eContainer() instanceof DView);
        } else if (notification.getFeature() == ViewpointPackage.Literals.DANALYSIS__OWNED_VIEWS && wasInOldValue(notification, dRepDescriptor.eContainer())) {
            // If it is a undo or a rollback but not a
            // DView moved from a DAnalysis to another
            representationDeleted = dRepDescriptor.eContainer() == null || !(dRepDescriptor.eContainer() != null && dRepDescriptor.eContainer().eContainer() instanceof DView);
        } else if (notification.getFeature() == ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__REPRESENTATION) {
            representationDeleted = notification.getNewValue() == null && notification.getOldValue() != null && notification.getNotifier() == dRepDescriptor;
        }
        return representationDeleted;
    }

    private boolean wasInOldValue(Notification notification, EObject eObject) {
        boolean isCurrentDRepresentationRemove = false;
        if (isUnsetNotification(notification)) {
            isCurrentDRepresentationRemove = isInOldValue(notification, eObject);
        }
        return isCurrentDRepresentationRemove;
    }

    private boolean isInOldValue(Notification notification, EObject obj) {
        if (notification.getOldValue() instanceof Collection) {
            return ((Collection<?>) notification.getOldValue()).contains(obj);
        } else {
            return notification.getOldValue() == obj;
        }
    }

    private boolean isTargetDetachment(Notification notification) {
        boolean detachedTarget = false;
        if (DanglingRefRemovalTrigger.IS_DETACHMENT.apply(notification)) {
            resolveDRepDescriptorProxy(notification);
            DRepresentation representation = dRepDescriptor.getRepresentation();
            if (representation instanceof DSemanticDecorator) {
                EObject target = ((DSemanticDecorator) representation).getTarget();
                detachedTarget = isInOldValue(notification, target) && target.eContainer() == null;
            } else {
                // Get target might have been already unset by someone but since we receive all notification at once we
                // want to close the editor as soon as we detect it has to be closed
                EObject target = dRepDescriptor.getTarget();
                detachedTarget = isInOldValue(notification, target) && target.eContainer() == null;
            }
        }

        return detachedTarget;
    }

}
