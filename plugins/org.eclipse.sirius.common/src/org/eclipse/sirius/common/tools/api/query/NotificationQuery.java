/*******************************************************************************
 * Copyright (c) 2011, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.api.query;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;

import com.google.common.base.Preconditions;

/**
 * Queries on EMF Notifications.
 * 
 * @author pcdavid
 */
public class NotificationQuery {
    private final Notification notification;

    /**
     * Constructor.
     * 
     * @param notification
     *            the notification to query.
     */
    public NotificationQuery(Notification notification) {
        this.notification = Preconditions.checkNotNull(notification);
    }

    /**
     * Check if the feature of the notification is a transient
     * EStructuralFeature.
     * 
     * @return true if this notification does not need to be consider as a
     *         resource modification (because it is transient), false otherwise.
     */
    public boolean isTransientNotification() {
        if (isNotificationOnTransientFeature()) {
            return true;
        } else {
            return isNotificationOnEObjectContainedThroughTransientFeature();
        }
    }

    private boolean isNotificationOnTransientFeature() {
        return notification.getFeature() instanceof EStructuralFeature && ((EStructuralFeature) notification.getFeature()).isTransient();
    }

    private boolean isNotificationOnEObjectContainedThroughTransientFeature() {
        if (notification.getNotifier() instanceof EObject) {
            EObject obj = (EObject) notification.getNotifier();
            return isContainedThroughTransientFeature(obj);
        } else {
            return false;
        }
    }

    private boolean isContainedThroughTransientFeature(EObject obj) {
        EObject current = obj;
        while (current.eContainer() != null) {
            if (current.eContainingFeature().isTransient() && !isFeatureMap(current.eContainer())) {
                return true;
            }
            current = current.eContainer();
        }
        return false;
    }

    /**
     * Return true if the EObject is a feature map as defined in section 3.5 of
     * https
     * ://www.eclipse.org/modeling/emf/docs/overviews/XMLSchemaToEcoreMapping
     * .pdf
     * 
     * @param obj
     *            object to test
     * @return true if the EObject is a feature map
     */
    private boolean isFeatureMap(EObject obj) {

        EAnnotation annotation = obj.eClass().getEAnnotation("http:///org/eclipse/emf/ecore/util/ExtendedMetaData");
        if (annotation != null && annotation.getDetails() != null) {
            String kind = annotation.getDetails().get("kind");
            // Is it sufficient to test kind.equals("mixed")?
            if ("mixed".equals(kind)) {
                // If not (or just to be safe), check if type is
                // EFeatureMapEntry

                // Not exactly sure the difference between getEAllAttributes
                // and getEAttributes.
                EList<EAttribute> attribs = obj.eClass().getEAllAttributes();
                for (EAttribute eAttribute : attribs) {
                    if (eAttribute.getEType().equals(EcorePackage.eINSTANCE.getEFeatureMapEntry())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
